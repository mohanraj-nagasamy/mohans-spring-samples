package com.octanner.conversion.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import com.octanner.conversion.dao.ConversionDAO;
import com.octanner.conversion.enums.ConversionStageStatus;
import com.octanner.conversion.enums.ConversionType;
import com.octanner.conversion.model.AccessCodePointsConversionStage;
import com.octanner.conversion.model.Bank;
import com.octanner.conversion.model.ExternalAccessCodePoints;
import com.octanner.conversion.model.FixedDateExpirationRule;
import com.octanner.conversion.model.Program;
import com.octanner.conversion.model.UserBankAccount;
import com.octanner.conversion.model.UserTransaction;

/**
 * Steps
 * Find access code points stage with NEW staus 
 * 1) Insert into ExternalAccessCodePoints - keep the ID
 * 	1.1) find system user id based on employee number for a given client
 * 	1.2) get hard-coded program id from app-config.properties
 * 2) find user_account
 * 	2.1 if there are no user_account
 NOTES: 1) insert the values in new table - pk_id, sys_user_id, access_code, estimated_dollar_value, expiry_data, bank_id, points, ----program_id, customer_id, stp number. 
2) create new a feature_set for conversion (File upload transaction). Feature set group - data mgmt.
feature_set --> FILE UPLOAD TRANSACTION  -- feature_group--> Data Management
2) insert in user_account if there is no user a/c.
3) insert points in user_transaction against new feature_set and the source transation id will be pk_id of the new table.
4) need seq,status for stage table too...
 */
@Named
public class AccessCodeConversion {

	private static final Logger log = Logger.getLogger(AccessCodeConversion.class);

	@Inject
	private ConversionDAO conversionDAO;

	@Value("${number-of-records-to-fetch-from-db}")
	private int pageChunkSize;

	@Value("${client-stp-number}")
	private String soldToPartyNumber;

	@Value("${iso-country-code}")
	private String isoCountryCode;

	@Value("${client-program-id}")
	private Long programId;

	@Value("${feature-set-name}")
	private String featureSetName;

	private Date expiryDate;

	public void doConversion() {

		List<AccessCodePointsConversionStage> csList = conversionDAO.findAccessCodePointsConversionStageByStatus(
				ConversionStageStatus.NEW.name(), pageChunkSize);

		for (AccessCodePointsConversionStage cs : csList) {
			try {
				doConversionByEachRecord(cs);
				cs.setLoadStatus(ConversionStageStatus.CONVERTED.name());
			} catch (RuntimeException e) {
				cs.setLoadStatus(ConversionStageStatus.NEW.name());
				log.error(e.getMessage());
				log.error(e.getMessage(), e);
			}

			conversionDAO.createOrUpdateAccessCodePointsConversionStage(cs);
		}

		//		while (true) {
		//				
		//			if (csList.size() == 0) {
		//				break;
		//			}
		//		}
	}

	@Transactional
	private void doConversionByEachRecord(AccessCodePointsConversionStage cs) {

		Long systemUserId = conversionDAO.findSystemUserIdByEmployeeIdAndSTPNumber(cs.getEmployeeUniqueId(),
				soldToPartyNumber);

		if (systemUserId == null) {
			throw new RuntimeException("SYSTEM_USER_ID is NULL for employee ID {" + cs.getEmployeeUniqueId() + "}");
		}

		cs.setSystemUserId(systemUserId);

		log.info(String.format("systemUserId = %s, EmployeeUniqueId = %s, soldToPartyNumber = %s", systemUserId, cs
				.getEmployeeUniqueId(), soldToPartyNumber));

		UserBankAccount uba = conversionDAO.findUserBankAccount(systemUserId, programId);

		if (uba == null) {
			log.info("There is no USER_ACCOUNT for systemUserId = " + systemUserId);
			Long bankId = conversionDAO.findBankIdByProgramId(programId);

			uba = new UserBankAccount();
			uba.setBankId(bankId);
			uba.setSystemUserId(systemUserId);

			conversionDAO.createOrUpdateUserBankAccount(uba);

			createUserTransaction(uba, cs, true);

		} else {
			log.info("USER_ACCOUNT is found for systemUserId = " + systemUserId);
			createUserTransaction(uba, cs, false);
		}

	}

	private void createUserTransaction(UserBankAccount uba, AccessCodePointsConversionStage conversionStage,
			boolean newUserAccount) {

		//
		Long featureSetId = conversionDAO.findFeatureSetIdByName(featureSetName);

		if (featureSetId == null) {
			throw new RuntimeException("FeatureSet has not been created for featureSet name {" + featureSetName + "}");
		}

		//Create External Access code - this PK Id is being used in UserTransaction as source transactionID
		createExternalAccessCodePoints(conversionStage);

		UserTransaction ut = new UserTransaction();
		ut.setUserAccount(uba);
		ut.setProgramId(programId);
		ut.setSourceTransactionID(conversionStage.getId());
		ut.setFeatureSetId(featureSetId);

		ut.setReturnTransactionFlag(false);
		ut.setPointsStatusInd('A');
		ut.setTransactionTimestamp(Calendar.getInstance().getTime());
		ut.setPointsRemaining(0L);

		Long pointsDeposited = getPoints(conversionStage);
		ut.setPointsDeposited(pointsDeposited);

		log.info("pointsDeposited -> " + pointsDeposited);

		ut.setDateOfExpiry(expiryDate);

		Long totalPointsRemaining = conversionDAO.findPointsInPreviousTransaction(uba.getUserAccountId());
		ut.setTotalPointsBalance(totalPointsRemaining.longValue() + pointsDeposited.longValue());

		//ut.setPointsWithdrawn(pointsWithdrawn);
		//ut.setAccessCodeId(accessCodeId);
		//ut.setReasonCode(reasonCode);

		uba.getUserTransactions().add(ut);
		conversionDAO.createOrUpdateUserBankAccount(uba);

	}

	private Long getPoints(AccessCodePointsConversionStage conversionStage) {

		if (conversionStage.getAwardAmount() != null && StringUtils.isNotBlank(conversionStage.getAccessCode())) {
			return calcualtePointsByAwardAmount(conversionStage.getAwardAmount());
		} else if (conversionStage.getPoints() != null) {
			return conversionStage.getPoints().longValue();
		} else {
			throw new RuntimeException("Employee ID {" + conversionStage.getEmployeeUniqueId()
					+ "} does not have Points and access code");
		}

	}

	private Long calcualtePointsByAwardAmount(BigDecimal awardAmount) {
		BigDecimal pointConversionFactor = conversionDAO.findPointConversionFactorByCountryCodeAndSTPNumber(
				isoCountryCode, soldToPartyNumber);

		BigDecimal price = awardAmount;
		long points = price.divide(pointConversionFactor, 0, RoundingMode.HALF_UP).longValue();

		log.info(String.format("pointConversionFactor -> {%s}, price/AwardAmount -> {%s}, points -> {%s} ",
				pointConversionFactor.doubleValue(), price.doubleValue(), points));

		return points;
	}

	@PostConstruct
	public void fetchExpiryDate() {

		validatePropValues();

		Program program = conversionDAO.findProgramById(programId);

		if (program.getBankExpirationOverrideFlag()) {
			expiryDate = fetchExpiryDateFromProgram(program);
		} else if (program.getBank() != null) {
			expiryDate = fetchExpiryDateFromBank(program.getBank());
		}

		log.info(String.format("expiryDate -> %s for program %s", expiryDate, programId));

	}

	private void validatePropValues() {
		if (programId == null) {
			throw new RuntimeException(
					"programId is NULL. Please set the value for client-program-id in app-config.properties");
		}

		if (soldToPartyNumber == null) {
			throw new RuntimeException(
					"soldToPartyNumber is NULL. Please set the value for client-stp-number in app-config.properties");
		}

	}

	private Date fetchExpiryDateFromProgram(Program program) {
		Date expireDate = null;
		if (program.getExpirationTypeCode().equals("FIXEDDATE")) {
			if (program.getExpirationRules() == null)
				throw new IllegalStateException("program.expirationRules should have been initialized but is null.");

			expireDate = fetchingFixedDateExpirationRules(program.getExpirationRules());
		} else if (program.getExpirationTypeCode().equals("MONROLLEXPRT")) {
			Long monthlyRollingNumber = program.getMonthlyRollingExpirationNumber();
			expireDate = calculateExpireDateFromRollingNumber(monthlyRollingNumber);
		}
		return expireDate;
	}

	/**
	 * Method used to fetch the expiration rules from Bank.
	 * 
	 * @param pBank
	 * @return expireDate
	 */
	public Date fetchExpiryDateFromBank(Bank pBank) {
		Date expireDate = null;

		if (pBank.getExpirationTypeCode().equals("FIXEDDATE")) {
			expireDate = fetchingFixedDateExpirationRules(pBank.getExpirationRules());
		} else if (pBank.getExpirationTypeCode().equals("MONROLLEXPRT")) {
			Long monthlyRollingNumber = pBank.getMonthlyRollingExpirationNumber();
			expireDate = calculateExpireDateFromRollingNumber(monthlyRollingNumber);
		}
		return expireDate;
	}

	/**
	 * Fetches the expire date from the FixedDateExpirationRule.
	 * 
	 * @param fixedDateExpirationRules
	 * @return expireDate
	 */
	private Date fetchingFixedDateExpirationRules(Set<FixedDateExpirationRule> fixedDateExpirationRules) {
		Date expireDate = null;
		if (fixedDateExpirationRules != null) {
			Date currentDate = new Date();
			for (FixedDateExpirationRule fixedDateExpirationRule : fixedDateExpirationRules) {
				if (((fixedDateExpirationRule.getFromDate().compareTo(currentDate) < 0) && (fixedDateExpirationRule
						.getToDate().compareTo(currentDate) > 0))
						|| (DateUtils.isSameDay(fixedDateExpirationRule.getFromDate(), currentDate) || (DateUtils
								.isSameDay(fixedDateExpirationRule.getFromDate(), currentDate)))) {
					expireDate = fixedDateExpirationRule.getExpirationDate();
				}
			}
		}
		return expireDate;
	}

	/**
	 * Calculates the expire date from the given monthly rolling expiration number.
	 * 
	 * @param monthlyRollingNumber
	 * @return expireDate
	 */
	private Date calculateExpireDateFromRollingNumber(Long monthlyRollingNumber) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, monthlyRollingNumber.intValue());
		Date expireDate = cal.getTime();
		return expireDate;
	}

	private void createExternalAccessCodePoints(AccessCodePointsConversionStage cs) {
		ExternalAccessCodePoints eacp = new ExternalAccessCodePoints();
		eacp.setAccessCode(cs.getAccessCode());
		eacp.setAwardAmount(cs.getAwardAmount());
		eacp.setPoints(cs.getPoints());
		eacp.setProgramId(programId);

		eacp.setSystemUserId(cs.getSystemUserId());

		conversionDAO.createOrUpdateExternalAccessCodePoints(eacp);
	}

}

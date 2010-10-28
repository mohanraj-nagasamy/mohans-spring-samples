package com.test.conversion.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.test.conversion.model.AccessCodePointsConversionStage;
import com.test.conversion.model.ExternalAccessCodePoints;
import com.test.conversion.model.Program;
import com.test.conversion.model.UserBankAccount;
import com.test.conversion.model.UserTransaction;

@Named
@Transactional
public class ConversionDAO {

	//private static final Logger log = Logger.getLogger(ConversionDAO.class);

	@PersistenceContext
	private EntityManager em;

	public void createOrUpdateAccessCodePointsConversionStage(AccessCodePointsConversionStage conversionStage) {

		if (conversionStage.getId() == null) {
			em.persist(conversionStage);
		} else {
			em.merge(conversionStage);
		}
	}

	public void createOrUpdateAccessCodePointsConversionStage(List<AccessCodePointsConversionStage> csList) {

		for (AccessCodePointsConversionStage conversionStage : csList) {
			if (conversionStage.getId() == null) {
				em.persist(conversionStage);
			} else {
				em.merge(conversionStage);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AccessCodePointsConversionStage> findAccessCodePointsConversionStageByStatus(String status, int pageChunkSize) {
		Query q = em.createQuery("from AccessCodePointsConversionStage cs where cs.loadStatus = :status");
		q.setParameter("status", status);
		q.setMaxResults(pageChunkSize);

		return q.getResultList();

	}

	@Transactional(readOnly = true)
	public Long findSystemUserIdByEmployeeIdAndSTPNumber(String employeeUniqueId, String soldToPartyNumber) {

		String sql = "select su.SYSTEM_USER_ID from SYSTEM_USER su, ACCESS_CODE_POINTS_STAGE acps, CUSTOMER c "
				+ "where su.EMPLOYEE_ID = acps.EMPLOYEE_ID and su.CUSTOMER_ID = c.CUSTOMER_ID  "
				+ "and c.SOLD_TO_PARTY_NUM = :stp and su.EMPLOYEE_ID = :employeeUniqueId";

		Query q = em.createNativeQuery(sql);
		q.setParameter("employeeUniqueId", employeeUniqueId);
		q.setParameter("stp", soldToPartyNumber);

		BigDecimal systemUserId;
		try {
			systemUserId = (BigDecimal) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			String sqlWithParamValues = sql.replaceAll(":stp", soldToPartyNumber).replaceAll(":employeeUniqueId",
					employeeUniqueId);
			throw new RuntimeException("There is more than one SYSTEM_USER_ID from the query - " + sqlWithParamValues);
		}

		return systemUserId.longValue();
	}

	public ExternalAccessCodePoints createOrUpdateExternalAccessCodePoints(ExternalAccessCodePoints eacp) {
		if (eacp.getId() == null) {
			em.persist(eacp);
		} else {
			em.merge(eacp);
		}

		return eacp;
	}

	@Transactional(readOnly = true)
	public UserBankAccount findUserBankAccount(Long systemUserId, Long programId) {
		Long bankId = findBankIdByProgramId(programId);

		if (bankId == null) {
			throw new RuntimeException("Bank id is NULL for program id {" + programId + "}");
		}

		String sql = "from UserBankAccount ua where ua.systemUserId = :systemUserId and ua.bankId = :bankId";

		Query q = em.createQuery(sql);
		q.setParameter("systemUserId", systemUserId);
		q.setParameter("bankId", bankId);

		UserBankAccount ua;
		try {
			ua = (UserBankAccount) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			String sqlWithParamValues = sql.replaceAll(":systemUserId", Long.toString(systemUserId)).replaceAll(
					":bankId", Long.toString(bankId));
			throw new RuntimeException("There is more than one UserBankAccount from the query - " + sqlWithParamValues);
		}

		return ua;

	}

	@Transactional(readOnly = true)
	public Long findBankIdByProgramId(Long programId) {
		String sql = "select bank_id from program where program_id = :programId";

		Query q = em.createNativeQuery(sql);
		q.setParameter("programId", programId);

		BigDecimal bankId;
		try {
			bankId = (BigDecimal) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			String sqlWithParamValues = sql.replaceAll(":programId", Long.toString(programId));
			throw new RuntimeException("There is more than one bank_id from the query - " + sqlWithParamValues);
		}

		return bankId.longValue();
	}

	public void createOrUpdateUserBankAccount(UserBankAccount uba) {

		if (uba.getUserAccountId() == null) {
			em.persist(uba);
		} else {
			em.merge(uba);
		}

	}

	@Transactional(readOnly = true)
	public Long findFeatureSetIdByName(String featureSetName) {
		String sql = "select feature_set_id from feature_Set where feature_set_name = :featureSetName";

		Query q = em.createNativeQuery(sql);
		q.setParameter("featureSetName", featureSetName);

		BigDecimal featureSetId;
		try {
			featureSetId = (BigDecimal) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			String sqlWithParamValues = sql.replaceAll(":featureSetName", (featureSetName));
			throw new RuntimeException("There is more than one feature_set_id from the query - " + sqlWithParamValues);
		}

		return featureSetId.longValue();
	}

	@Transactional(readOnly = true)
	public BigDecimal findPointConversionFactorByCountryCodeAndSTPNumber(String isoCountryCode, String soldToPartyNumber) {
		String sql = "select bsor.POINT_CURRENCY_VALUE from COUNTRY_CONFIG cc, COUNTRY_SALES_ORG_RLTNP csor, CUSTOMER cu, "
				+ "CUSTOMER_SALES_ORG_COUNTRY_RLT csocr, BANK_SALES_ORG_RLTNP bsor where "
				+ "cc.country_config_id = csor.country_config_id "
				+ "and csor.country_sales_org_id = csocr.country_sales_org_id"
				+ "and cu.customer_id = csocr.customer_id"
				+ "and csocr.customer_sales_org_country_id = bsor.customer_sales_org_country_id "
				+ "and cc.iso_country_code = :isoCountryCode and cu.sold_to_party_num = :soldToPartyNumber";

		Query q = em.createNativeQuery(sql);
		q.setParameter("isoCountryCode", isoCountryCode);
		q.setParameter("soldToPartyNumber", soldToPartyNumber);

		BigDecimal pointConversionFactor;
		try {
			pointConversionFactor = (BigDecimal) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			String sqlWithParamValues = sql.replaceAll(":isoCountryCode", (isoCountryCode)).replaceAll(
					":soldToPartyNumber", soldToPartyNumber);
			throw new RuntimeException("There is more than one POINT_CURRENCY_VALUE from the query - "
					+ sqlWithParamValues);
		}

		return pointConversionFactor;
	}

	public Program findProgramById(Long programId) {
		Program program = em.find(Program.class, programId);

		if (program == null) {
			throw new RuntimeException("There is no program found for program - program_id {" + programId + "}");

		}

		return program;
	}

	public UserTransaction findLatestUserTransactionByUserBankAccountId(Long userAccountId) {

		return null;
	}

	/**
	 * Method to retrieve points from previous latest 'point' transaction
	 */
	public Long findPointsInPreviousTransaction(Long userAccountId) {
		String query = "SELECT userTransaction.totalPointsBalance from UserTransaction userTransaction "
				+ "where  userTransaction.transactionTimestamp = (select max(userTransaction.transactionTimestamp)"
				+ " from UserTransaction userTransaction where "
				+ " userTransaction.accessCode.accessCodeId IS NULL AND userTransaction.userAccount.userAccountId ="
				+ userAccountId + ") " + "AND  userTransaction.userAccount.userAccountId =" + userAccountId;

		Query q = em.createQuery(query);

		Long totalPointsRemaining = 0L;
		try {
			totalPointsRemaining = (Long) q.getSingleResult();
		} catch (NoResultException e) {
			//Just ignore it...
		}

		return totalPointsRemaining;
	}

}

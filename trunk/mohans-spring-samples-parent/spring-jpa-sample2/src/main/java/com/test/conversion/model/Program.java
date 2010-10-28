/*
 * Copyright 2007 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id: Program.java 22101 2010-10-01 19:33:43Z Siddharth.Gupta $ 
 */
package com.test.conversion.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

import com.test.conversion.model.listener.AuditListener;

/**
 * Entity class for Program.
 * 
 */
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "PROGRAM", uniqueConstraints = @UniqueConstraint(columnNames = { "CUSTOMER_ID", "PROGRAM_NAME",
		"PROGRAM_TYPE_CODE" }))
public class Program implements Auditable, Serializable {
	private static final long serialVersionUID = 1L;
	private Long programId;
	private String programTypeCode;
	private String programName;
	private String programDescription;
	private boolean activeFlag = Boolean.FALSE;
	// private Set<FieldProgramRltnp> fieldProgramRltnps;
	private String programStatus = "CONFIGURATION";
	private AuditTrail auditTrail = new AuditTrail();
	private String programNumber;

	// TODO: string
	private String emblemQuantity;
	private BigDecimal overrideGoldBase;
	private BigDecimal overrideSilverBase;

	private Bank bank;

	private String brochureTypeCode;
	private Date programStartDate;
	private Date programEndDate;
	private String programNotes;
	private Boolean dataFileusageFlag;
	private Character programEndDateMessgaeInd;
	private Boolean bankExpirationOverrideFlag;
	private String expirationTypeCode;
	private Date redemptionEndDate;
	private Character redemptionEndDateMessageInd;
	private String reviewTypeCode;
	private Date reviewDate;
	private Set<FixedDateExpirationRule> expirationRules;
	private Long monthlyRollingExpirationNumber;
	private Boolean awardLevelUsageFlag = Boolean.FALSE;
	private Boolean displayConfiguredMessageFlag = Boolean.FALSE;
	private Boolean hubDisplayFlag = Boolean.FALSE;
	private boolean displayButGreyOutFlag = Boolean.FALSE;
	private boolean displayCustomMessageFlag = Boolean.FALSE;
	private Boolean globalPrsnlReqFlag = Boolean.FALSE;
	private Boolean participantOptalPrsnlFlag = Boolean.FALSE;
	private Boolean customPrsnlReqFlag = Boolean.FALSE;
	private Boolean associateBusinessHierarchyFlag = Boolean.FALSE;
	private Boolean useDifferentBussinessHeirarchyFlag = Boolean.FALSE;
	private boolean validationInProgress;
	private Long programNameStringId;
	private Long customerId;

	/**
	 * gets the Program ID
	 * 
	 * @return programID
	 */
	@Id
	@SequenceGenerator(name = "SEQ_PROGRAM", sequenceName = "SEQ_PROGRAM")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROGRAM")
	@Column(name = "PROGRAM_ID")
	public Long getProgramId() {
		return programId;
	}

	/**
	 * sets Program ID
	 * 
	 * @param pProgramId
	 */
	public void setProgramId(Long pProgramId) {
		this.programId = pProgramId;
	}

	/**
	 * gets the Program Name
	 * 
	 * @return Program Name
	 */
	@Column(name = "PROGRAM_NAME")
	public String getProgramName() {
		return programName;
	}

	/**
	 * sets Program Name
	 * 
	 * @param pProgramName
	 */
	public void setProgramName(String pProgramName) {
		this.programName = pProgramName;
	}

	/**
	 * @return the programStatus
	 */
	@Column(name = "PROGRAM_STATUS")
	public String getProgramStatus() {
		return programStatus;
	}

	/**
	 * @param programStatus
	 *            the programStatus to set
	 */
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}

	/**
	 * gets the Program Type Code
	 * 
	 * @return Program Type Code
	 */
	@Column(name = "PROGRAM_TYPE_CODE")
	public String getProgramTypeCode() {
		return programTypeCode;
	}

	/**
	 * sets the Program Type Code
	 * 
	 * @param pProgramTypeCode
	 */
	public void setProgramTypeCode(String pProgramTypeCode) {
		this.programTypeCode = pProgramTypeCode;
	}

	/**
	 * gets the Program Description
	 * 
	 * @return Program Description
	 */
	@Column(name = "PROGRAM_DESCRIPTION")
	public String getProgramDescription() {
		return programDescription;
	}

	/**
	 * sets the Program Description
	 * 
	 * @param pProgramDescription
	 */
	public void setProgramDescription(String pProgramDescription) {
		this.programDescription = pProgramDescription;
	}

	@Column(name = "CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * sets the Customer
	 * 
	 * @param pCustomer
	 */
	public void setCustomerId(Long pCustomer) {
		this.customerId = pCustomer;
	}

	/**
	 * gets the Audit Trail
	 * 
	 * @return Audit Trail
	 */
	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	/**
	 * 
	 * @param pAuditTrail
	 */
	public void setAuditTrail(AuditTrail pAuditTrail) {
		this.auditTrail = pAuditTrail;
	}

	/**
	 * checks whether Active Flag or not
	 * 
	 * @return Active Flag
	 */
	@Column(name = "ACTIVE_FLAG", nullable = false)
	public boolean isActiveFlag() {
		return this.activeFlag;
	}

	/**
	 * sets the Active Flag
	 * 
	 * @param pActiveFlag
	 */
	public void setActiveFlag(boolean pActiveFlag) {
		this.activeFlag = pActiveFlag;
	}

	/**
	 * Gets the programNumber.
	 * 
	 * @return programNumber
	 */
	@Column(name = "PROGRAM_NUM")
	public String getProgramNumber() {
		return programNumber;
	}

	/**
	 * Sets the programNumber.
	 * 
	 * @param pProgramNumber
	 */
	public void setProgramNumber(String pProgramNumber) {
		this.programNumber = pProgramNumber;
	}

	/**
	 * Getter method for bank.
	 * 
	 * @return the bank
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BANK_ID")
	public Bank getBank() {
		return bank;
	}

	/**
	 * Setter method for bank.
	 * 
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * Getter method for brochureTypeCode.
	 * 
	 * @return the brochureTypeCode
	 */
	@Column(name = "BROCHURE_TYPE_CODE")
	public String getBrochureTypeCode() {
		return brochureTypeCode;
	}

	/**
	 * Setter method for brochureTypeCode.
	 * 
	 * @param brochureTypeCode
	 *            the brochureTypeCode to set
	 */
	public void setBrochureTypeCode(String brochureTypeCode) {
		this.brochureTypeCode = brochureTypeCode;
	}

	/**
	 * Getter method for programStartDate.
	 * 
	 * @return the programStartDate
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PROGRAM_START_DATE")
	public Date getProgramStartDate() {
		return programStartDate;
	}

	/**
	 * Setter method for programStartDate.
	 * 
	 * @param programStartDate
	 *            the programStartDate to set
	 */
	public void setProgramStartDate(Date programStartDate) {
		this.programStartDate = programStartDate;
	}

	/**
	 * Getter method for programEndDate.
	 * 
	 * @return the programEndDate
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PROGRAM_END_DATE")
	public Date getProgramEndDate() {
		return programEndDate;
	}

	/**
	 * Setter method for programEndDate.
	 * 
	 * @param programEndDate
	 *            the programEndDate to set
	 */
	public void setProgramEndDate(Date programEndDate) {
		this.programEndDate = programEndDate;
	}

	@Column(name = "EMBLEM_QUANTITY")
	public String getEmblemQuantity() {
		return emblemQuantity;
	}

	public void setEmblemQuantity(String emblemQuantity) {
		this.emblemQuantity = emblemQuantity;
	}

	@Column(name = "OVERRIDE_GOLD_BASE")
	public BigDecimal getOverrideGoldBase() {
		return overrideGoldBase;
	}

	@Column(name = "OVERRIDE_SILVER_BASE")
	public BigDecimal getOverrideSilverBase() {
		return overrideSilverBase;
	}

	public void setOverrideGoldBase(BigDecimal overrideGoldBase) {
		this.overrideGoldBase = overrideGoldBase;
	}

	public void setOverrideSilverBase(BigDecimal overrideSilverBase) {
		this.overrideSilverBase = overrideSilverBase;
	}

	/**
	 * @return the programNotes
	 */
	@Column(name = "PROGRAM_NOTES")
	public String getProgramNotes() {
		return programNotes;
	}

	/**
	 * @return the dataFileusageFlag
	 */
	@Column(name = "DATA_FILE_USAGE_FLAG")
	public Boolean getDataFileusageFlag() {
		return dataFileusageFlag;
	}

	/**
	 * @return the programEndDateMessgaeInd
	 */
	@Column(name = "PROGRAM_END_DATE_MESSAGE_IND")
	public Character getProgramEndDateMessgaeInd() {
		return programEndDateMessgaeInd;
	}

	/**
	 * @return the bankExpirationOverrideFlag
	 */
	@Column(name = "BANK_EXPIRATION_OVERRIDE_FLAG")
	public Boolean getBankExpirationOverrideFlag() {
		return bankExpirationOverrideFlag;
	}

	/**
	 * @return the expirationTypeCode
	 */
	@Column(name = "EXPIRATION_TYPE_CODE")
	public String getExpirationTypeCode() {
		return expirationTypeCode;
	}

	/**
	 * @return the redemptionEndDate
	 */
	@Column(name = "REDEMPTION_END_DATE")
	public Date getRedemptionEndDate() {
		return redemptionEndDate;
	}

	/**
	 * @return the redemptionEndDateMessageInd
	 */
	@Column(name = "REDEMPTION_END_DATE_MSG_IND")
	public Character getRedemptionEndDateMessageInd() {
		return redemptionEndDateMessageInd;
	}

	/**
	 * @return the reviewTypeCode
	 */
	@Column(name = "REVIEW_TYPE_CODE")
	public String getReviewTypeCode() {
		return reviewTypeCode;
	}

	/**
	 * @return the reviewDate
	 */
	@Column(name = "REVIEW_DATE")
	public Date getReviewDate() {
		return reviewDate;
	}

	/**
	 * @param programNotes
	 *            the programNotes to set
	 */
	public void setProgramNotes(String programNotes) {
		this.programNotes = programNotes;
	}

	/**
	 * @param dataFileusageFlag
	 *            the dataFileusageFlag to set
	 */
	public void setDataFileusageFlag(Boolean dataFileusageFlag) {
		this.dataFileusageFlag = dataFileusageFlag;
	}

	/**
	 * @param programEndDateMessgaeInd
	 *            the programEndDateMessgaeInd to set
	 */
	public void setProgramEndDateMessgaeInd(Character programEndDateMessgaeInd) {
		this.programEndDateMessgaeInd = programEndDateMessgaeInd;
	}

	/**
	 * @param bankExpirationOverrideFlag
	 *            the bankExpirationOverrideFlag to set
	 */
	public void setBankExpirationOverrideFlag(Boolean bankExpirationOverrideFlag) {
		this.bankExpirationOverrideFlag = bankExpirationOverrideFlag;
	}

	/**
	 * @param expirationTypeCode
	 *            the expirationTypeCode to set
	 */
	public void setExpirationTypeCode(String expirationTypeCode) {
		this.expirationTypeCode = expirationTypeCode;
	}

	/**
	 * @param redemptionEndDate
	 *            the redemptionEndDate to set
	 */
	public void setRedemptionEndDate(Date redemptionEndDate) {
		this.redemptionEndDate = redemptionEndDate;
	}

	/**
	 * @param redemptionEndDateMessageInd
	 *            the redemptionEndDateMessageInd to set
	 */
	public void setRedemptionEndDateMessageInd(Character redemptionEndDateMessageInd) {
		this.redemptionEndDateMessageInd = redemptionEndDateMessageInd;
	}

	/**
	 * @param reviewTypeCode
	 *            the reviewTypeCode to set
	 */
	public void setReviewTypeCode(String reviewTypeCode) {
		this.reviewTypeCode = reviewTypeCode;
	}

	/**
	 * @param reviewDate
	 *            the reviewDate to set
	 */
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	/**
	 * @return the expirationRules
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "program")
	@JoinColumn(name = "PROGRAM_ID", updatable = false)
	@Cascade( { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	public Set<FixedDateExpirationRule> getExpirationRules() {
		if (expirationRules == null) {
			expirationRules = new HashSet<FixedDateExpirationRule>();
		}
		return expirationRules;
	}

	/**
	 * @param expirationRules
	 *            the expirationRules to set
	 */
	public void setExpirationRules(Set<FixedDateExpirationRule> expirationRules) {
		this.expirationRules = expirationRules;
	}

	/**
	 * Gets the hubDisplayFlag.
	 * 
	 * @return hubDisplayFlag
	 */
	@Column(name = "HUB_DISPLAY_FLAG", nullable = false)
	public boolean getHubDisplayFlag() {
		return hubDisplayFlag;
	}

	/**
	 * Sets the hubDisplayFlag.
	 * 
	 * @param pHubDisplayFlag
	 */
	public void setHubDisplayFlag(boolean pHubDisplayFlag) {
		this.hubDisplayFlag = pHubDisplayFlag;
	}

	/**
	 * Gets the displayButGreyOutFlag.
	 * 
	 * @return displayButGreyOutFlag
	 */
	@Column(name = "DISPLAY_BUT_GREYOUT_FLAG", nullable = false)
	public boolean getDisplayButGreyOutFlag() {
		return displayButGreyOutFlag;
	}

	/**
	 * Sets the displayButGreyOutFlag.
	 * 
	 * @param pDisplayButGreyOutFlag
	 */
	public void setDisplayButGreyOutFlag(boolean pDisplayButGreyOutFlag) {
		this.displayButGreyOutFlag = pDisplayButGreyOutFlag;
	}

	/**
	 * Gets the displayCustomMessageFlag.
	 * 
	 * @return displayCustomMessageFlag
	 */
	@Column(name = "DISPLAY_CUSTOM_MESSAGE_FLAG", nullable = false)
	public boolean getDisplayCustomMessageFlag() {
		return displayCustomMessageFlag;
	}

	/**
	 * Sets the displayCustomMessageFlag.
	 * 
	 * @param pDisplayCustomMessageFlag
	 */
	public void setDisplayCustomMessageFlag(boolean pDisplayCustomMessageFlag) {
		this.displayCustomMessageFlag = pDisplayCustomMessageFlag;
	}

	/**
	 * Gets the AwardLevelUsageFlag.
	 * 
	 * @return awardLevelUsageFlag
	 */
	@Column(name = "AWARD_LEVEL_USAGE_FLAG")
	public Boolean getAwardLevelUsageFlag() {
		return awardLevelUsageFlag;
	}

	/**
	 * Sets the AwardLevelUsageFlag.
	 * 
	 * @param pAwardLevelUsageFlag
	 */
	public void setAwardLevelUsageFlag(Boolean pAwardLevelUsageFlag) {
		this.awardLevelUsageFlag = pAwardLevelUsageFlag;
	}

	/**
	 * Gets the displayConfiguredMessageFlag.
	 * 
	 * @return displayConfiguredMessageFlag
	 */
	@Column(name = "DISPLAY_CONFIGURED_MESSAGE_FLA")
	public Boolean getDisplayConfiguredMessageFlag() {
		return displayConfiguredMessageFlag;
	}

	/**
	 * Sets the displayConfiguredMessageFlag.
	 * 
	 * @param pDisplayConfiguredMessageFlag
	 */
	public void setDisplayConfiguredMessageFlag(Boolean pDisplayConfiguredMessageFlag) {
		this.displayConfiguredMessageFlag = pDisplayConfiguredMessageFlag;
	}

	/**
	 * Gets the monthlyRollingExpirationNumber.
	 * 
	 * @return monthlyRollingExpirationNumber
	 */
	@Column(name = "MONTHLY_ROLLING_EXPIRATION_NUM")
	public Long getMonthlyRollingExpirationNumber() {
		return monthlyRollingExpirationNumber;
	}

	/**
	 * Sets the monthlyRollingExpirationNumber.
	 * 
	 * @param pMonthlyRollingExpirationNumber
	 */
	public void setMonthlyRollingExpirationNumber(Long pMonthlyRollingExpirationNumber) {
		this.monthlyRollingExpirationNumber = pMonthlyRollingExpirationNumber;
	}

	/**
	 * Gets the globalPrsnlReqFlag.
	 * 
	 * @return globalPrsnlReqFlag
	 */
	@Column(name = "GLOBAL_PRSNL_REQ_FLAG")
	public Boolean getGlobalPrsnlReqFlag() {
		return globalPrsnlReqFlag;
	}

	/**
	 * Sets the globalPrsnlReqFlag.
	 * 
	 * @param pGlobalPrsnlReqFlag
	 */
	public void setGlobalPrsnlReqFlag(Boolean pGlobalPrsnlReqFlag) {
		this.globalPrsnlReqFlag = pGlobalPrsnlReqFlag;
	}

	/**
	 * Gets the participantOptalPrsnlFlag.
	 * 
	 * @return participantOptalPrsnlFlag
	 */
	@Column(name = "PARTICIPANT_OPTAL_PRSNL_FLAG")
	public Boolean getParticipantOptalPrsnlFlag() {
		return participantOptalPrsnlFlag;
	}

	/**
	 * Sets the participantOptalPrsnlFlag.
	 * 
	 * @param pParticipantOptalPrsnlFlag
	 */
	public void setParticipantOptalPrsnlFlag(Boolean pParticipantOptalPrsnlFlag) {
		this.participantOptalPrsnlFlag = pParticipantOptalPrsnlFlag;
	}

	/**
	 * Gets the customPrsnlReqFlag.
	 * 
	 * @return customPrsnlReqFlag
	 */
	@Column(name = "CUSTOM_PRSNL_REQ_FLAG")
	public Boolean getCustomPrsnlReqFlag() {
		return customPrsnlReqFlag;
	}

	/**
	 * Sets the customPrsnlReqFlag.
	 * 
	 * @param pCustomPrsnlReqFlag
	 */
	public void setCustomPrsnlReqFlag(Boolean pCustomPrsnlReqFlag) {
		this.customPrsnlReqFlag = pCustomPrsnlReqFlag;
	}

	/**
	 * @return the associateBusinessHierarchyFlag
	 */
	@Column(name = "ASSOC_BUS_UNIT_HIERARCHY_FLAG")
	public Boolean getAssociateBusinessHierarchyFlag() {
		return associateBusinessHierarchyFlag;
	}

	/**
	 * @return the userDifferentBussinessHeirarchyFlag
	 */
	@Column(name = "USE_DIFF_HIERARCHY_TEST_FLAG")
	public Boolean getUseDifferentBussinessHeirarchyFlag() {
		return useDifferentBussinessHeirarchyFlag;
	}

	/**
	 * @param associateBusinessHierarchyFlag
	 *            the associateBusinessHierarchyFlag to set
	 */

	public void setAssociateBusinessHierarchyFlag(Boolean associateBusinessHierarchyFlag) {
		this.associateBusinessHierarchyFlag = associateBusinessHierarchyFlag;
	}

	/**
	 * @param userDifferentBussinessHeirarchyFlag
	 *            the userDifferentBussinessHeirarchyFlag to set
	 */
	public void setUseDifferentBussinessHeirarchyFlag(Boolean useDifferentBussinessHeirarchyFlag) {
		this.useDifferentBussinessHeirarchyFlag = useDifferentBussinessHeirarchyFlag;
	}

	@Column(name = "VALIDATION_PROGRESS_FLAG")
	/*
	 * @return the validationInProgress
	 */
	public boolean isValidationInProgress() {
		return validationInProgress;
	}

	/**
	 * @param validationInProgress
	 *            the validationInProgress to set
	 */
	public void setValidationInProgress(boolean validationInProgress) {
		this.validationInProgress = validationInProgress;
	}

	/**
	 * Gets the programNameStringId.
	 * 
	 * @return programNameStringId
	 */
	@Column(name = "PROGRAM_NAME_STRING_ID")
	public Long getProgramNameStringId() {
		return programNameStringId;
	}

	/**
	 * Sets the programNameStringId.
	 * 
	 * @param pProgramNameStringId
	 */
	public void setProgramNameStringId(Long pProgramNameStringId) {
		this.programNameStringId = pProgramNameStringId;
	}

	@Override
	public String toString() {
		return "Program [programId=" + programId + ", programName=" + programName + "]";
	}
}

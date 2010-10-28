/*
 * Copyright 2007 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id$
 */
package com.octanner.conversion.model;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

import com.octanner.conversion.model.listener.AuditListener;

/**
 * Entity class for Bank Table.
 */
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "BANK", uniqueConstraints = @UniqueConstraint(columnNames = { "BANK_ID" }))
public class Bank implements Auditable, java.io.Serializable {
	/**
	 * Generated serialVersionId.
	 */
	private static final long serialVersionUID = 5969500599955629279L;
	private Long bankId;
	private String bankName;
	private Boolean bankActiveFlag;
	private Long customerId;
	private String individualPointName;
	private Boolean residentCountryDisplayFlag;
	private String countryDisplayOrderCode;
	private String pointBankTypeCode;
	private Boolean enlargedFontDisplayFlag;
	private String awardShippingCode;
	private Boolean lockByLocationFlag;
	private Boolean pointExpiryRuleOverrideFlag;
	private String statementScheduleCode;
	private String formatTypeCode;
	private Boolean thankYouButtonDisplayFlag;
	private Boolean nominationAtRedemptionSiteFlag;
	private Boolean survayButtonDisplayAtNominationSiteFlag;
	private Boolean shippingLabelLineRequiredFlag;
	private String displayCountryRuleCode;
	private String expirationTypeCode;
	private Long monthlyRollingExpirationNumber;
	private String deductionTypeCode;
	private Long bankNameStringId;
	private String pointPriceInclusionCode;
	private Set<FixedDateExpirationRule> expirationRules = new HashSet<FixedDateExpirationRule>();
	private Boolean catalogInclusionOnlyFlag = Boolean.FALSE;
	private Boolean catalogGiftWrapFlag = Boolean.FALSE;
	private Boolean catalogPackagingFlag = Boolean.FALSE;
	private Boolean catalogPersonalizationFlag = Boolean.FALSE;
	private String catalogPersonalizationType;
	private Integer catalogPriceVariance;
	private String catalogPackagingType;

	private Boolean filterProgramTypeFlag = Boolean.FALSE;
	private Boolean filterCategoryTypeFlag = Boolean.FALSE;;
	private Boolean filterBrandNameFlag = Boolean.FALSE;;
	private Boolean filterBrandHierachyFlag = Boolean.FALSE;;
	private Boolean filterMaterialAttributeFlag = Boolean.FALSE;;
	private Boolean filterIconFlag = Boolean.FALSE;;
	private Boolean useRedemptionImagesFlag = Boolean.FALSE;
	private Boolean atgPriceSyncFlag = Boolean.FALSE;

	// added for award price

	private Boolean contractFlag = Boolean.FALSE;
	private Date contractStartDate;
	private Date contractEndDate;
	private Boolean costPlusFlag = Boolean.FALSE;
	private Boolean byPassMinimumPriceLogicFlag = Boolean.FALSE;
	private Boolean includeIntFrieghtChargeFlag = Boolean.FALSE;
	private Boolean excludeGpaOnInvoiceFlag = Boolean.FALSE;
	private Boolean levelPriceMaterialDiscFlag = Boolean.FALSE;
	private Boolean includeOrderProcessingFeeFlag = Boolean.FALSE;
	private BigDecimal concessionFactor;
	private Boolean customerDiscountLevelFlag = Boolean.FALSE;
	private Boolean programDiscountLevelflag = Boolean.FALSE;
	private Boolean catalogDiscountFlag = Boolean.FALSE;
	private Boolean productPricingDiscountLevelFlag = Boolean.FALSE;
	private Boolean individualAwardDiscountFlag = Boolean.FALSE;
	private Boolean maximumDiscountFlag = Boolean.FALSE;
	private BigDecimal discountPercentage;
	private Boolean maximumIncreaseFlag = Boolean.FALSE;
	private BigDecimal increasePercentage;

	private String thinkBigText1;
	private String thinkBigText2;
	private Long thinkBigText1StringId;
	private Long thinkBigText2StringId;

	private String pricingNotes;

	private Long pointNameStringId;
	private Set<Program> programs;

	/**
	 * Getter method for bankId.
	 * 
	 * @return the bankId
	 */
	@Id
	@Column(name = "BANK_ID")
	@SequenceGenerator(name = "SEQ_BANK", sequenceName = "SEQ_BANK")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BANK")
	public Long getBankId() {
		return bankId;
	}

	/**
	 * Setter method for bankId.
	 * 
	 * @param bankId
	 *            the bankId to set
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	/**
	 * Getter method for programs.
	 * 
	 * @return the programs
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "bank")
	@Cascade( { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	public Set<Program> getPrograms() {
		return programs;
	}

	/**
	 * Setter method for programs.
	 * 
	 * @param programs
	 *            the programs to set
	 */
	public void setPrograms(Set<Program> programs) {
		this.programs = programs;
	}

	private AuditTrail auditTrail = new AuditTrail();
	private BigDecimal pointCurrencyValue;

	/**
	 * Getter method for pointCurrencyValue.
	 * 
	 * @return the pointCurrencyValue
	 */
	@Column(name = "POINT_CURRENCY_VALUE")
	public BigDecimal getPointCurrencyValue() {
		return pointCurrencyValue;
	}

	/**
	 * Setter method for pointCurrencyValue.
	 * 
	 * @param pointCurrencyValue
	 *            the pointCurrencyValue to set
	 */
	public void setPointCurrencyValue(BigDecimal pointCurrencyValue) {
		this.pointCurrencyValue = pointCurrencyValue;
	}

	/**
	 * Getter method for bankName.
	 * 
	 * @return the bankName
	 */
	@Column(name = "BANK_NAME")
	public String getBankName() {
		return bankName;
	}

	/**
	 * Setter method for bankName.
	 * 
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * Getter method for bankActiveFlag.
	 * 
	 * @return the bankActiveFlag
	 */
	@Column(name = "BANK_ACTIVE_FLAG")
	public Boolean getBankActiveFlag() {
		return bankActiveFlag;
	}

	/**
	 * Setter method for bankActiveFlag.
	 * 
	 * @param bankActiveFlag
	 *            the bankActiveFlag to set
	 */
	public void setBankActiveFlag(Boolean bankActiveFlag) {
		this.bankActiveFlag = bankActiveFlag;
	}

	@Column(name = "INDIVIDUAL_POINT_NAME")
	public String getIndividualPointName() {
		return individualPointName;
	}

	/**
	 * Setter method for individualPointName.
	 * 
	 * @param individualPointName
	 *            the individualPointName to set
	 */
	public void setIndividualPointName(String individualPointName) {
		this.individualPointName = individualPointName;
	}

	/**
	 * Getter method for residentCountryDisplayFlag.
	 * 
	 * @return the residentCountryDisplayFlag
	 */
	@Column(name = "RESIDENT_COUNTRY_DISPLAY_FLAG")
	public Boolean getResidentCountryDisplayFlag() {
		return residentCountryDisplayFlag;
	}

	/**
	 * Setter method for residentCountryDisplayFlag.
	 * 
	 * @param residentCountryDisplayFlag
	 *            the residentCountryDisplayFlag to set
	 */
	public void setResidentCountryDisplayFlag(Boolean residentCountryDisplayFlag) {
		this.residentCountryDisplayFlag = residentCountryDisplayFlag;
	}

	/**
	 * Getter method for countryDisplayOrderCode.
	 * 
	 * @return the countryDisplayOrderCode
	 */
	@Column(name = "COUNTRY_DISPLAY_ORDER_CODE")
	public String getCountryDisplayOrderCode() {
		return countryDisplayOrderCode;
	}

	/**
	 * Setter method for countryDisplayOrderCode.
	 * 
	 * @param countryDisplayOrderCode
	 *            the countryDisplayOrderCode to set
	 */
	public void setCountryDisplayOrderCode(String countryDisplayOrderCode) {
		this.countryDisplayOrderCode = countryDisplayOrderCode;
	}

	/**
	 * Getter method for pointBankTypeCode.
	 * 
	 * @return the pointBankTypeCode
	 */
	@Column(name = "POINT_BANK_TYPE_CODE")
	public String getPointBankTypeCode() {
		return pointBankTypeCode;
	}

	/**
	 * Setter method for pointBankTypeCode.
	 * 
	 * @param pointBankTypeCode
	 *            the pointBankTypeCode to set
	 */
	public void setPointBankTypeCode(String pointBankTypeCode) {
		this.pointBankTypeCode = pointBankTypeCode;
	}

	/**
	 * Getter method for enlargedFontDisplayFlag.
	 * 
	 * @return the enlargedFontDisplayFlag
	 */
	@Column(name = "ENLARGED_FONT_DISPLAY_FLAG")
	public Boolean getEnlargedFontDisplayFlag() {
		return enlargedFontDisplayFlag;
	}

	/**
	 * Setter method for enlargedFontDisplayFlag.
	 * 
	 * @param enlargedFontDisplayFlag
	 *            the enlargedFontDisplayFlag to set
	 */
	public void setEnlargedFontDisplayFlag(Boolean enlargedFontDisplayFlag) {
		this.enlargedFontDisplayFlag = enlargedFontDisplayFlag;
	}

	/**
	 * Getter method for awardShippingCocde.
	 * 
	 * @return the awardShippingCocde
	 */
	@Column(name = "AWARD_SHIPPING_CODE")
	public String getAwardShippingCode() {
		return awardShippingCode;
	}

	/**
	 * Setter method for awardShippingCocde.
	 * 
	 * @param awardShippingCocde
	 *            the awardShippingCocde to set
	 */
	public void setAwardShippingCode(String awardShippingCode) {
		this.awardShippingCode = awardShippingCode;
	}

	/**
	 * Getter method for lockByLocationFlag.
	 * 
	 * @return the lockByLocationFlag
	 */
	@Column(name = "LOCK_BY_LOCATION_FLAG")
	public Boolean getLockByLocationFlag() {
		return lockByLocationFlag;
	}

	/**
	 * Setter method for lockByLocationFlag.
	 * 
	 * @param lockByLocationFlag
	 *            the lockByLocationFlag to set
	 */
	public void setLockByLocationFlag(Boolean lockByLocationFlag) {
		this.lockByLocationFlag = lockByLocationFlag;
	}

	/**
	 * Getter method for pointExpiryRuleOverrideFlag.
	 * 
	 * @return the pointExpiryRuleOverrideFlag
	 */
	@Column(name = "POINT_EXPIRY_RULE_OVERRIDE_FLA")
	public Boolean getPointExpiryRuleOverrideFlag() {
		return pointExpiryRuleOverrideFlag;
	}

	/**
	 * Setter method for pointExpiryRuleOverrideFlag.
	 * 
	 * @param pointExpiryRuleOverrideFlag
	 *            the pointExpiryRuleOverrideFlag to set
	 */
	public void setPointExpiryRuleOverrideFlag(Boolean pointExpiryRuleOverrideFlag) {
		this.pointExpiryRuleOverrideFlag = pointExpiryRuleOverrideFlag;
	}

	/**
	 * Getter method for statementScheduleCode.
	 * 
	 * @return the statementScheduleCode
	 */
	@Column(name = "STATEMENT_SCHEDULE_CODE")
	public String getStatementScheduleCode() {
		return statementScheduleCode;
	}

	/**
	 * Setter method for statementScheduleCode.
	 * 
	 * @param statementScheduleCode
	 *            the statementScheduleCode to set
	 */
	public void setStatementScheduleCode(String statementScheduleCode) {
		this.statementScheduleCode = statementScheduleCode;
	}

	/**
	 * Getter method for formatTypeCode.
	 * 
	 * @return the formatTypeCode
	 */
	@Column(name = "FORMAT_TYPE_CODE")
	public String getFormatTypeCode() {
		return formatTypeCode;
	}

	/**
	 * Setter method for formatTypeCode.
	 * 
	 * @param formatTypeCode
	 *            the formatTypeCode to set
	 */
	public void setFormatTypeCode(String formatTypeCode) {
		this.formatTypeCode = formatTypeCode;
	}

	/**
	 * Getter method for thankYouButtonDisplayFlag.
	 * 
	 * @return the thankYouButtonDisplayFlag
	 */
	@Column(name = "THANKYOU_BUTTON_DISPLAY_FLAG")
	public Boolean getThankYouButtonDisplayFlag() {
		return thankYouButtonDisplayFlag;
	}

	/**
	 * Setter method for thankYouButtonDisplayFlag.
	 * 
	 * @param thankYouButtonDisplayFlag
	 *            the thankYouButtonDisplayFlag to set
	 */
	public void setThankYouButtonDisplayFlag(Boolean thankYouButtonDisplayFlag) {
		this.thankYouButtonDisplayFlag = thankYouButtonDisplayFlag;
	}

	/**
	 * Getter method for nominationAtRedemptionSiteFlag.
	 * 
	 * @return the nominationAtRedemptionSiteFlag
	 */
	@Column(name = "NOMINATION_AT_REDEMPTION_SITE_")
	public Boolean getNominationAtRedemptionSiteFlag() {
		return nominationAtRedemptionSiteFlag;
	}

	/**
	 * Setter method for nominationAtRedemptionSiteFlag.
	 * 
	 * @param nominationAtRedemptionSiteFlag
	 *            the nominationAtRedemptionSiteFlag to set
	 */
	public void setNominationAtRedemptionSiteFlag(Boolean nominationAtRedemptionSiteFlag) {
		this.nominationAtRedemptionSiteFlag = nominationAtRedemptionSiteFlag;
	}

	/**
	 * Getter method for survayButtonDisplayAtNominationSiteFlag.
	 * 
	 * @return the survayButtonDisplayAtNominationSiteFlag
	 */
	@Column(name = "SURVEY_BUTTON_DISPLAY_AT_NOMIN")
	public Boolean getSurvayButtonDisplayAtNominationSiteFlag() {
		return survayButtonDisplayAtNominationSiteFlag;
	}

	/**
	 * Setter method for survayButtonDisplayAtNominationSiteFlag.
	 * 
	 * @param survayButtonDisplayAtNominationSiteFlag
	 *            the survayButtonDisplayAtNominationSiteFlag to set
	 */
	public void setSurvayButtonDisplayAtNominationSiteFlag(Boolean survayButtonDisplayAtNominationSiteFlag) {
		this.survayButtonDisplayAtNominationSiteFlag = survayButtonDisplayAtNominationSiteFlag;
	}

	/**
	 * Getter method for shippingLabelLineRequiredFlag.
	 * 
	 * @return the shippingLabelLineRequiredFlag
	 */
	@Column(name = "SHIPPING_LABEL_LINE_REQ_FLAG")
	public Boolean getShippingLabelLineRequiredFlag() {
		return shippingLabelLineRequiredFlag;
	}

	/**
	 * Setter method for shippingLabelLineRequiredFlag.
	 * 
	 * @param shippingLabelLineRequiredFlag
	 *            the shippingLabelLineRequiredFlag to set
	 */
	public void setShippingLabelLineRequiredFlag(Boolean shippingLabelLineRequiredFlag) {
		this.shippingLabelLineRequiredFlag = shippingLabelLineRequiredFlag;
	}

	/**
	 * Getter method for displayCountryRuleCode.
	 * 
	 * @return the displayCountryRuleCode
	 */
	@Column(name = "DISPLAY_COUNTRY_RULE_CODE")
	public String getDisplayCountryRuleCode() {
		return displayCountryRuleCode;
	}

	/**
	 * Setter method for displayCountryRuleCode.
	 * 
	 * @param displayCountryRuleCode
	 *            the displayCountryRuleCode to set
	 */
	public void setDisplayCountryRuleCode(String displayCountryRuleCode) {
		this.displayCountryRuleCode = displayCountryRuleCode;
	}

	/**
	 * Getter method for expirationTypeCode.
	 * 
	 * @return the expirationTypeCode
	 */
	@Column(name = "EXPIRATION_TYPE_CODE")
	public String getExpirationTypeCode() {
		return expirationTypeCode;
	}

	/**
	 * Setter method for expirationTypeCode.
	 * 
	 * @param expirationTypeCode
	 *            the expirationTypeCode to set
	 */
	public void setExpirationTypeCode(String expirationTypeCode) {
		this.expirationTypeCode = expirationTypeCode;
	}

	/**
	 * Getter method for monthlyRollingExpirationNumber.
	 * 
	 * @return the monthlyRollingExpirationNumber
	 */
	@Column(name = "MONTHLY_ROLLING_EXPIRATION_NUM")
	public Long getMonthlyRollingExpirationNumber() {
		return monthlyRollingExpirationNumber;
	}

	/**
	 * Setter method for monthlyRollingExpirationNumber.
	 * 
	 * @param monthlyRollingExpirationNumber
	 *            the monthlyRollingExpirationNumber to set
	 */
	public void setMonthlyRollingExpirationNumber(Long monthlyRollingExpirationNumber) {
		this.monthlyRollingExpirationNumber = monthlyRollingExpirationNumber;
	}

	/**
	 * Getter method for deductionTypeCode.
	 * 
	 * @return the deductionTypeCode
	 */
	@Column(name = "DEDUCTION_TYPE_CODE")
	public String getDeductionTypeCode() {
		return deductionTypeCode;
	}

	/**
	 * Setter method for deductionTypeCode.
	 * 
	 * @param deductionTypeCode
	 *            the deductionTypeCode to set
	 */
	public void setDeductionTypeCode(String deductionTypeCode) {
		this.deductionTypeCode = deductionTypeCode;
	}

	/**
	 * Getter method for bankNameStringId.
	 * 
	 * @return the bankNameStringId
	 */

	@Column(name = "BANK_NAME_STRING_ID")
	public Long getBankNameStringId() {
		return bankNameStringId;
	}

	/**
	 * Setter method for bankNameStringId.
	 * 
	 * @param bankNameStringId
	 *            the bankNameStringId to set
	 */
	public void setBankNameStringId(Long bankNameStringId) {
		this.bankNameStringId = bankNameStringId;
	}

	/**
	 * Getter method for pointPriceInclusionCode.
	 * 
	 * @return the pointPriceInclusionCode
	 */
	@Column(name = "POINT_PRICE_INCLUSION_CODE")
	public String getPointPriceInclusionCode() {
		return pointPriceInclusionCode;
	}

	/**
	 * Setter method for pointPriceInclusionCode.
	 * 
	 * @param pointPriceInclusionCode
	 *            the pointPriceInclusionCode to set
	 */
	public void setPointPriceInclusionCode(String pointPriceInclusionCode) {
		this.pointPriceInclusionCode = pointPriceInclusionCode;
	}

	/**
	 * gets the audit trial instance.
	 */
	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	/**
	 * sets the audit trail instance.
	 */
	public void setAuditTrail(AuditTrail pAuditTrail) {
		this.auditTrail = pAuditTrail;
	}

	/**
	 * @return the expirationRules
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bank")
	@JoinColumn(name = "BANK_ID", updatable = false)
	@Cascade( { org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	public Set<FixedDateExpirationRule> getExpirationRules() {
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
	* Gets the catalogInclusionOnlyInd.
	* 
	* @return catalogInclusionOnlyInd
	*/
	@Column(name = "CATALOG_INCLUSION_ONLY_FLAG")
	public Boolean getCatalogInclusionOnlyFlag() {
		return catalogInclusionOnlyFlag;
	}

	/**
	 * Sets the catalogInclusionOnlyInd.
	 * 
	 * @param pCatalogInclusionOnlyInd
	 */
	public void setCatalogInclusionOnlyFlag(Boolean pCatalogInclusionOnlyFlag) {
		this.catalogInclusionOnlyFlag = pCatalogInclusionOnlyFlag;
	}

	/**
	 * Gets the catalogGiftWrapInd.
	 * 
	 * @return catalogGiftWrapInd
	 */
	@Column(name = "CATALOG_GIFT_WRAP_FLAG")
	public Boolean getCatalogGiftWrapFlag() {
		return catalogGiftWrapFlag;
	}

	/**
	 * Sets the catalogGiftWrapInd.
	 * 
	 * @param pCatalogGiftWrapInd
	 */
	public void setCatalogGiftWrapFlag(Boolean pCatalogGiftWrapFlag) {
		this.catalogGiftWrapFlag = pCatalogGiftWrapFlag;
	}

	/**
	 * Gets the catalogPackagingInd.
	 * 
	 * @return catalogPackagingInd
	 */
	@Column(name = "CATALOG_PACKAGING_FLAG")
	public Boolean getCatalogPackagingFlag() {
		return catalogPackagingFlag;
	}

	/**
	 * Sets the catalogPackagingInd.
	 * 
	 * @param pCatalogPackagingInd
	 */
	public void setCatalogPackagingFlag(Boolean pCatalogPackagingFlag) {
		this.catalogPackagingFlag = pCatalogPackagingFlag;
	}

	/**
	 * Gets the catalogPersonalizationInd.
	 * 
	 * @return catalogPersonalizationInd
	 */
	@Column(name = "CATALOG_PERSONALIZATION_FLAG")
	public Boolean getCatalogPersonalizationFlag() {
		return catalogPersonalizationFlag;
	}

	/**
	 * Sets the catalogPersonalizationInd.
	 * 
	 * @param pCatalogPersonalizationInd
	 */
	public void setCatalogPersonalizationFlag(Boolean pCatalogPersonalizationFlag) {
		this.catalogPersonalizationFlag = pCatalogPersonalizationFlag;
	}

	/**
	 * Gets the catalogPersonalizationType.
	 * 
	 * @return catalogPersonalizationType
	 */
	@Column(name = "CATALOG_PERSONALIZATION_TYPE")
	public String getCatalogPersonalizationType() {
		return catalogPersonalizationType;
	}

	/**
	 * Sets the catalogPersonalizationType.
	 * 
	 * @param pCatalogPersonalizationType
	 */
	public void setCatalogPersonalizationType(String pCatalogPersonalizationType) {
		this.catalogPersonalizationType = pCatalogPersonalizationType;
	}

	/**
	 * Gets the catalogPriceVariance.
	 * 
	 * @return catalogPriceVariance
	 */
	@Column(name = "CATALOG_PRICE_VARIANCE")
	public Integer getCatalogPriceVariance() {
		return catalogPriceVariance;
	}

	/**
	 * Sets the catalogPriceVariance.
	 * 
	 * @param pCatalogPriceVariance
	 */
	public void setCatalogPriceVariance(Integer pCatalogPriceVariance) {
		this.catalogPriceVariance = pCatalogPriceVariance;
	}

	/**
	 * Gets the catalogPackagingType.
	 * 
	 * @return catalogPackagingType
	 */
	@Column(name = "CATALOG_PACKAGING_TYPE")
	public String getCatalogPackagingType() {
		return catalogPackagingType;
	}

	/**
	 * Sets the catalogPackagingType.
	 * 
	 * @param pCatalogPackagingType
	 */
	public void setCatalogPackagingType(String pCatalogPackagingType) {
		this.catalogPackagingType = pCatalogPackagingType;
	}

	/**
	 * Gets the filterProgramType.
	 * 
	 * @return filterProgramTypeFlag
	 */
	@Column(name = "FILTER_ALL_PROGRAM_TYPE_FLAG")
	public Boolean getFilterProgramTypeFlag() {
		return filterProgramTypeFlag;
	}

	/**
	 * Sets the filterprogramType.
	 * 
	 * @param filterProgramTypeFlag
	 */
	public void setFilterProgramTypeFlag(Boolean filterProgramTypeFlag) {
		this.filterProgramTypeFlag = filterProgramTypeFlag;
	}

	/**
	 * Gets the filterCategoryTypeFlag.
	 * 
	 * @return filterCategoryTypeFlag
	 */

	@Column(name = "FILTER_ALL_CATEGORY_TYPE_FLAG")
	public Boolean getFilterCategoryTypeFlag() {
		return filterCategoryTypeFlag;
	}

	/**
	 * Sets the filterCategoryTypeFlag.
	 * 
	 * @param filterCategoryTypeFlag
	 */

	public void setFilterCategoryTypeFlag(Boolean filterCategoryTypeFlag) {
		this.filterCategoryTypeFlag = filterCategoryTypeFlag;

	}

	/**
	 * Gets the filterBrandNameFlag.
	 * 
	 * @return filterBrandNameFlag
	 */

	@Column(name = "FILTER_ALL_BRAND_NAME_FLAG")
	public Boolean getFilterBrandNameFlag() {
		return filterBrandNameFlag;
	}

	/**
	 * Sets the filterBrandNameFlag.
	 * 
	 * @param filterBrandNameFlag
	 */

	public void setFilterBrandNameFlag(Boolean filterBrandNameFlag) {
		this.filterBrandNameFlag = filterBrandNameFlag;
	}

	/**
	 * Gets the filterBrandHierachyFlag.
	 * 
	 * @return filterBrandHierachyFlag
	 */
	@Column(name = "FILTER_ALL_BRAND_HRCHY_FLAG")
	public Boolean getFilterBrandHierachyFlag() {
		return filterBrandHierachyFlag;
	}

	/**
	 * Sets the filterBrandHierachyFlag.
	 * 
	 * @param pFilterBrandHierachyFlag
	 */
	public void setFilterBrandHierachyFlag(Boolean pFilterBrandHierachyFlag) {
		this.filterBrandHierachyFlag = pFilterBrandHierachyFlag;
	}

	/**
	 * Gets the filterMaterialAttributeFlag.
	 * 
	 * @return filterMaterialAttributeFlag
	 */
	@Column(name = "FILTER_ALL_MATERIAL_ATTR_FLAG")
	public Boolean getFilterMaterialAttributeFlag() {
		return filterMaterialAttributeFlag;
	}

	/**
	 * Sets the filterMaterialAttributeFlag.
	 * 
	 * @param pFilterMaterialAttributeFlag
	 */
	public void setFilterMaterialAttributeFlag(Boolean pFilterMaterialAttributeFlag) {
		this.filterMaterialAttributeFlag = pFilterMaterialAttributeFlag;
	}

	/**
	 * Gets the filterIconFlag.
	 * 
	 * @return filterIconFlag
	 */
	@Column(name = "FILTER_ALL_ICON_FLAG")
	public Boolean getFilterIconFlag() {
		return filterIconFlag;
	}

	/**
	 * Sets the filterIconFlag.
	 * 
	 * @param pFilterIconFlag
	 */
	public void setFilterIconFlag(Boolean pFilterIconFlag) {
		this.filterIconFlag = pFilterIconFlag;
	}

	@Column(name = "PRICING_NOTES")
	public String getPricingNotes() {
		return pricingNotes;
	}

	/**
	 * sets the pricingNotes.
	 * 
	 * @param pricingNotes
	 */
	public void setPricingNotes(String pricingNotes) {
		this.pricingNotes = pricingNotes;
	}

	/**
	 * gets the contractFlag.
	 * 
	 * @return contractFlag
	 */
	@Column(name = "CONTRACT_FLAG")
	public Boolean getContractFlag() {
		return contractFlag;
	}

	/**
	 * sets the contractFlag.
	 * 
	 * @param contractFlag
	 */
	public void setContractFlag(Boolean contractFlag) {
		this.contractFlag = contractFlag;
	}

	/**
	 * gets the contractStartDate.
	 * 
	 * @return contractStartDate
	 */
	@Column(name = "CONTRACT_START_DATE")
	public Date getContractStartDate() {
		return contractStartDate;
	}

	/**
	 * sets the contractStartDate.
	 * 
	 * @param contractStartDate
	 */
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	/**
	 * gets the contractEndDate.
	 * 
	 * @return contractEndDate
	 */
	@Column(name = "CONTRACT_END_DATE")
	public Date getContractEndDate() {
		return contractEndDate;
	}

	/**
	 * sets the contractEndDate.
	 * 
	 * @param contractEndDate
	 */
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	/**
	 * gets the costPlusFlag.
	 * 
	 * @return costPlusFlag
	 */
	@Column(name = "COST_PLUS_FLAG")
	public Boolean getCostPlusFlag() {
		return costPlusFlag;
	}

	/**
	 * sets the costPlusFlag.
	 * 
	 * @param costPlusFlag
	 */
	public void setCostPlusFlag(Boolean costPlusFlag) {
		this.costPlusFlag = costPlusFlag;
	}

	/**
	 * gets the byPassMinimumPriceLogicFlag.
	 * 
	 * @return byPassMinimumPriceLogicFlag
	 */
	@Column(name = "BYPASS_MINIMUM_PRICE_LOGIC_FLA")
	public Boolean getByPassMinimumPriceLogicFlag() {
		return byPassMinimumPriceLogicFlag;
	}

	/**
	 * sets the byPassMinimumPriceLogicFlag.
	 * 
	 * @param byPassMinimumPriceLogicFlag
	 */
	public void setByPassMinimumPriceLogicFlag(Boolean byPassMinimumPriceLogicFlag) {
		this.byPassMinimumPriceLogicFlag = byPassMinimumPriceLogicFlag;
	}

	/**
	 * gets the includeIntFrieghtChargeFlag.
	 * 
	 * @return includeIntFrieghtChargeFlag
	 */
	@Column(name = "INCLUDE_INT_FRIEGHT_CHARGE_FLA")
	public Boolean getIncludeIntFrieghtChargeFlag() {
		return includeIntFrieghtChargeFlag;
	}

	/**
	 * sets the includeIntFrieghtChargeFlag.
	 * 
	 * @param includeIntFrieghtChargeFlag
	 */
	public void setIncludeIntFrieghtChargeFlag(Boolean includeIntFrieghtChargeFlag) {
		this.includeIntFrieghtChargeFlag = includeIntFrieghtChargeFlag;
	}

	/**
	 * gets the excludeGpaOnInvoiceFlag.
	 * 
	 * @return excludeGpaOnInvoiceFlag
	 */
	@Column(name = "EXCLUDE_GPA_ON_INVOICE_FLAG")
	public Boolean getExcludeGpaOnInvoiceFlag() {
		return excludeGpaOnInvoiceFlag;
	}

	/**
	 * sets the excludeGpaOnInvoiceFlag.
	 * 
	 * @param excludeGpaOnInvoiceFlag
	 */
	public void setExcludeGpaOnInvoiceFlag(Boolean excludeGpaOnInvoiceFlag) {
		this.excludeGpaOnInvoiceFlag = excludeGpaOnInvoiceFlag;
	}

	/**
	 * gets the levelPriceMaterialDiscFlag.
	 * 
	 * @return levelPriceMaterialDiscFlag
	 */
	@Column(name = "LEVEL_PRICE_MATERIAL_DISC_FLAG")
	public Boolean getLevelPriceMaterialDiscFlag() {
		return levelPriceMaterialDiscFlag;
	}

	/**
	 * sets the levelPriceMaterialDiscFlag.
	 * 
	 * @param levelPriceMaterialDiscFlag
	 */
	public void setLevelPriceMaterialDiscFlag(Boolean levelPriceMaterialDiscFlag) {
		this.levelPriceMaterialDiscFlag = levelPriceMaterialDiscFlag;
	}

	/**
	 * gets the includeOrderProcessingFeeFlag.
	 * 
	 * @return includeOrderProcessingFeeFlag
	 */
	@Column(name = "INCLD_ORD_PROCESSING_FEE_FLAG")
	public Boolean getIncludeOrderProcessingFeeFlag() {
		return includeOrderProcessingFeeFlag;
	}

	/**
	 * sets the includeOrderProcessingFeeFlag.
	 * 
	 * @param includeOrderProcessingFeeFlag
	 */
	public void setIncludeOrderProcessingFeeFlag(Boolean includeOrderProcessingFeeFlag) {
		this.includeOrderProcessingFeeFlag = includeOrderProcessingFeeFlag;
	}

	/**
	 * gets the concessionFactor.
	 * 
	 * @return concessionFactor
	 */
	@Column(name = "CONCESSION_FACTOR")
	public BigDecimal getConcessionFactor() {
		return concessionFactor;
	}

	/**
	 * sets the concessionFactor.
	 * 
	 * @param concessionFactor
	 */
	public void setConcessionFactor(BigDecimal concessionFactor) {
		this.concessionFactor = concessionFactor;
	}

	/**
	 * gets the customerDiscountLevelFlag.
	 * 
	 * @return customerDiscountLevelFlag
	 */
	@Column(name = "CUSTOMER_DISC_LEVEL_FLAG")
	public Boolean getCustomerDiscountLevelFlag() {
		return customerDiscountLevelFlag;
	}

	/**
	 * sets the customerDiscountLevelFlag.
	 * 
	 * @param customerDiscountLevelFlag
	 */
	public void setCustomerDiscountLevelFlag(Boolean customerDiscountLevelFlag) {
		this.customerDiscountLevelFlag = customerDiscountLevelFlag;
	}

	/**
	 * gets the programDiscountLevelflag.
	 * 
	 * @return programDiscountLevelflag
	 */
	@Column(name = "PROGRAM_DISC_LEVEL_FLAG")
	public Boolean getProgramDiscountLevelflag() {
		return programDiscountLevelflag;
	}

	/**
	 * sets the programDiscountLevelflag.
	 * 
	 * @param programDiscountLevelflag
	 */
	public void setProgramDiscountLevelflag(Boolean programDiscountLevelflag) {
		this.programDiscountLevelflag = programDiscountLevelflag;
	}

	/**
	 * gets the productPricingDiscountLevelFlag.
	 * 
	 * @return productPricingDiscountLevelFlag
	 */
	@Column(name = "PRODUCT_PRICING_DISC_LVL_FLAG")
	public Boolean getProductPricingDiscountLevelFlag() {
		return productPricingDiscountLevelFlag;
	}

	/**
	 * sets the productPricingDiscountLevelFlag.
	 * 
	 * @param productPricingDiscountLevelFlag
	 */
	public void setProductPricingDiscountLevelFlag(Boolean productPricingDiscountLevelFlag) {
		this.productPricingDiscountLevelFlag = productPricingDiscountLevelFlag;
	}

	/**
	 * gets the individualAwardDiscountFlag.
	 * 
	 * @return individualAwardDiscountFlag
	 */
	@Column(name = "INDIVIDUAL_AWARD_DISC_FLAG")
	public Boolean getIndividualAwardDiscountFlag() {
		return individualAwardDiscountFlag;
	}

	/**
	 * sets the individualAwardDiscountFlag.
	 * 
	 * @param individualAwardDiscountFlag
	 */
	public void setIndividualAwardDiscountFlag(Boolean individualAwardDiscountFlag) {
		this.individualAwardDiscountFlag = individualAwardDiscountFlag;
	}

	/**
	 * gets the maximumDiscountFlag.
	 * 
	 * @return maximumDiscountFlag
	 */
	@Column(name = "MAXIMUM_DISC_FLAG")
	public Boolean getMaximumDiscountFlag() {
		return maximumDiscountFlag;
	}

	/**
	 * sets the maximumDiscountFlag.
	 * 
	 * @param maximumDiscountFlag
	 */
	public void setMaximumDiscountFlag(Boolean maximumDiscountFlag) {
		this.maximumDiscountFlag = maximumDiscountFlag;
	}

	/**
	 * gets the discountPercentage.
	 * 
	 * @return discountPercentage
	 */
	@Column(name = "DISC_PERCENTAGE")
	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	/**
	 * sets the discountPercentage.
	 * 
	 * @param discountPercentage
	 */
	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	/**
	 * gets the maximumIncreaseFlag.
	 * 
	 * @return maximumIncreaseFlag
	 */
	@Column(name = "MAXIMUM_INCREASE_FLAG")
	public Boolean getMaximumIncreaseFlag() {
		return maximumIncreaseFlag;
	}

	/**
	 * sets the maximumIncreaseFlag.
	 * 
	 * @param maximumIncreaseFlag
	 */
	public void setMaximumIncreaseFlag(Boolean maximumIncreaseFlag) {
		this.maximumIncreaseFlag = maximumIncreaseFlag;
	}

	/**
	 * gets the increasePercentage.
	 * 
	 * @return increasePercentage
	 */
	@Column(name = "INCREASE_PERCENTAGE")
	public BigDecimal getIncreasePercentage() {
		return increasePercentage;
	}

	/**
	 * sets the increasePercentage.
	 * 
	 * @param increasePercentage
	 */
	public void setIncreasePercentage(BigDecimal increasePercentage) {
		this.increasePercentage = increasePercentage;
	}

	/**
	 * gets the catalogDiscountFlag.
	 * 
	 * @return catalogDiscountFlag
	 */
	@Column(name = "CATALOG_DISC_FLAG")
	public Boolean getCatalogDiscountFlag() {
		return catalogDiscountFlag;
	}

	/**
	 * sets the catalogDiscountFlag.
	 * 
	 * @param catalogDiscountFlag
	 */
	public void setCatalogDiscountFlag(Boolean catalogDiscountFlag) {
		this.catalogDiscountFlag = catalogDiscountFlag;
	}

	/**
	 * @return the thinkBigText1
	 */
	@Column(name = "THINK_BIG_TEXT_1")
	public String getThinkBigText1() {
		return thinkBigText1;
	}

	/**
	 * @param thinkBigText1
	 *            the thinkBigText1 to set
	 */
	public void setThinkBigText1(String thinkBigText1) {
		this.thinkBigText1 = thinkBigText1;
	}

	/**
	 * @return the thinkBigText2
	 */
	@Column(name = "THINK_BIG_TEXT_2")
	public String getThinkBigText2() {
		return thinkBigText2;
	}

	/**
	 * @param thinkBigText2
	 *            the thinkBigText2 to set
	 */
	public void setThinkBigText2(String thinkBigText2) {
		this.thinkBigText2 = thinkBigText2;
	}

	/**
	 * @return the thinkBigText1StringId
	 */
	@Column(name = "THINK_BIG_TEXT_1_STRING_ID")
	public Long getThinkBigText1StringId() {
		return thinkBigText1StringId;
	}

	/**
	 * @param thinkBigText1StringId
	 *            the thinkBigText1StringId to set
	 */
	public void setThinkBigText1StringId(Long thinkBigText1StringId) {
		this.thinkBigText1StringId = thinkBigText1StringId;
	}

	/**
	 * @return the thinkBigText2StringId
	 */
	@Column(name = "THINK_BIG_TEXT_2_STRING_ID")
	public Long getThinkBigText2StringId() {
		return thinkBigText2StringId;
	}

	/**
	 * @param thinkBigText2StringId
	 *            the thinkBigText2StringId to set
	 */
	public void setThinkBigText2StringId(Long thinkBigText2StringId) {
		this.thinkBigText2StringId = thinkBigText2StringId;
	}

	/**
	 * @return the useRedemptionImagesFlag
	 */
	@Column(name = "USE_REDEMPTION_IMAGES_FLAG")
	public Boolean getUseRedemptionImagesFlag() {
		return useRedemptionImagesFlag;
	}

	/**
	 * @param useRedemptionImagesFlag
	 *            the useRedemptionImagesFlag to set
	 */
	public void setUseRedemptionImagesFlag(Boolean useRedemptionImagesFlag) {
		this.useRedemptionImagesFlag = useRedemptionImagesFlag;
	}

	/**
	 * @return the useRedemptionFlag
	 */

	@Column(name = "POINT_NAME_STRING_ID")
	public Long getPointNameStringId() {
		return pointNameStringId;
	}

	public void setPointNameStringId(Long pointNameStringId) {
		this.pointNameStringId = pointNameStringId;
	}

	/**
	 * @return the atgPriceSyncFlag
	 */
	@Column(name = "ATG_PRICE_SYNC_FLAG")
	public Boolean getAtgPriceSyncFlag() {
		return atgPriceSyncFlag;
	}

	/**
	 * set the value of the atgPriceSyncFlag
	 * @param atgPriceSyncFlag the value to set to
	 */
	public void setAtgPriceSyncFlag(Boolean atgPriceSyncFlag) {
		this.atgPriceSyncFlag = atgPriceSyncFlag;
	}

	/**
	 * Give the increase or discount percentage as a positive or negative number respectively. Throw
	 * IllegalStateException if both are present.
	 * 
	 * @return
	 */
	@Transient
	public BigDecimal getPriceAdjustmentPercentage() {
		BigDecimal disc = getDiscountPercentage();
		BigDecimal inc = getIncreasePercentage();

		if (disc != null && disc.doubleValue() > 0.0) {
			if (inc != null && inc.doubleValue() > 0.0)
				throw new IllegalStateException("Increase and discount are both present on Bank " + getBankId());
			return disc.negate();
		} else if (inc != null && inc.doubleValue() > 0.0) {
			return inc;
		} else {
			return BigDecimal.ZERO;
		}
	}

	@Column(name = "CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

}

/*
 * Copyright 2007 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id$
 */
package com.octanner.conversion.model;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.octanner.conversion.model.listener.AuditListener;

/**
 * Entity class for FixedDateExpirationRule.
 */
@Entity
@EntityListeners(AuditListener.class)
@Table(name = "FIXED_DATE_EXPIRATION_RULE")
public class FixedDateExpirationRule implements Auditable, java.io.Serializable {

	/**
	 * Generated serialVersionId.
	 */
	private static final long serialVersionUID = 6385772120199474636L;

	public Long fixedDateExpirationRuleId;
	public Date fromDate;
	public Date toDate;
	public Date expirationDate;
	private Bank bank;
	private Program program;
	private String frmDate;
	private String toDates;
	private String expDate;

	private AuditTrail auditTrail = new AuditTrail();

	/**
	 * Getter method for bankSalesOrganizationId.
	 * 
	 * @return the bankSalesOrganizationId
	 */
	@Id
	@Column(name = "FIXED_DATE_EXPIRATION_RULE_ID")
	@SequenceGenerator(name = "SEQ_FIXEDDATEEXPIRATIONRULE", sequenceName = "SEQ_FIXEDDATEEXPIRATIONRULE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FIXEDDATEEXPIRATIONRULE")
	public Long getFixedDateExpirationRuleId() {
		return fixedDateExpirationRuleId;
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
	 * @return the fromDate
	 */
	@Column(name = "DATE_OF_ISSUANCE_FROM")
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @return the toDate
	 */
	@Column(name = "DATE_OF_ISSUANCE_TO")
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @return the expirationDate
	 */
	@Column(name = "EXPIRY_DATE_FOR_INTERVAL")
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @return the program
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PROGRAM_ID")
	public Program getProgram() {
		return program;
	}

	/**
	 * @param fixedDateExpirationRuleId
	 *            the fixedDateExpirationRuleId to set
	 */
	public void setFixedDateExpirationRuleId(Long fixedDateExpirationRuleId) {
		this.fixedDateExpirationRuleId = fixedDateExpirationRuleId;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @param expirationDate
	 *            the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @param program
	 *            the program to set
	 */
	public void setProgram(Program program) {
		this.program = program;
	}

	/**
	 * gets the auditTrail instance.
	 * 
	 * @return auditTrail instance
	 */
	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	/**
	 * sets the auditTrail Instance.
	 * 
	 * @param pSuperAuditTrail
	 */
	public void setAuditTrail(AuditTrail pAuditTrail) {
		this.auditTrail = pAuditTrail;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object pOther) {
		if ((this == pOther)) {
			return true;
		}
		if (!(pOther instanceof FixedDateExpirationRule)) {
			return false;
		}
		FixedDateExpirationRule rule = (FixedDateExpirationRule) pOther;
		EqualsBuilder eBuilder = new EqualsBuilder();
		eBuilder.append(this.fromDate, rule.fromDate).append(this.toDate, rule.toDate).append(this.expirationDate,
				rule.expirationDate);
		return eBuilder.isEquals();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hBuilder = new HashCodeBuilder();
		hBuilder.append(this.fromDate).append(this.toDate).append(this.expirationDate);
		return hBuilder.toHashCode();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()

	{
		ToStringBuilder tBuilder = new ToStringBuilder(this);
		tBuilder.append("bank", this.bank).append("fromDate", this.fromDate).append("toDate", this.toDate).append(
				"expirationDate", this.expirationDate);
		return tBuilder.toString();
	}

	/**
	 * Getter method for frmDate.
	 * @return the frmDate
	 */
	@Transient
	public String getFrmDate() {
		Date date = getFromDate();
		String strDate = stringToDate(date);
		frmDate = strDate;
		return frmDate;
	}

	/**
	 * Setter method for frmDate.
	 * @param frmDate the frmDate to set
	 */
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	/**
	 * Getter method for toDates.
	 * @return the toDates
	 */
	@Transient
	public String getToDates() {
		Date date = getToDate();
		String strDate = stringToDate(date);
		toDates = strDate;
		return toDates;
	}

	/**
	 * Setter method for toDates.
	 * @param toDates the toDates to set
	 */
	public void setToDates(String toDates) {
		this.toDates = toDates;
	}

	/**
	 * Getter method for expDate.
	 * @return the expDate
	 */
	@Transient
	public String getExpDate() {
		Date date = getExpirationDate();
		String strDate = stringToDate(date);
		expDate = strDate;
		return expDate;
	}

	/**
	 * Setter method for expDate.
	 * @param expDate the expDate to set
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	/**
	 * Converts a String to DATE.
	 * 
	 * @param pStrDate
	 * @return converted date object
	 */
	private String stringToDate(Date pStrDate) {

		String strDate = "";
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		strDate = format.format(pStrDate);

		return strDate;
	}

}

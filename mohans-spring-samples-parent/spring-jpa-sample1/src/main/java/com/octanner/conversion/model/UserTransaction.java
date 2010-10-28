/*
 * Copyright 2007 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id$
 */
package com.octanner.conversion.model;

import java.io.Serializable;
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

import com.octanner.conversion.model.listener.AuditListener;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "USER_TRANSACTION")
public class UserTransaction implements Auditable, Serializable {

	private static final long serialVersionUID = 1L;
	private Long userTransactionID;
	private UserBankAccount userAccount;

	private Long programId;
	private Long pointsDeposited;
	/**
	 * No Of points withdrawn
	 */
	private Long pointsWithdrawn;

	private Long featureSetId;
	/**
	 * Parent transaction ID
	 */
	private Long sourceTransactionID;
	/**
	 * Access code
	 */
	private Long accessCodeId;
	/**
	 * Remaining points for a transaction
	 */
	private Long pointsRemaining;
	/**
	 * Flag indicating weather the transaction is returned or not
	 */
	private boolean returnTransactionFlag;
	/**
	 * Expire date of points 
	 */
	private Date dateOfExpiry;
	/**
	 * Points status
	 */
	private Character pointsStatusInd;
	/**
	 * Reason Code
	 */
	private String reasonCode;
	/**
	 * Audit Trail
	 */
	protected AuditTrail auditTrail = new AuditTrail();
	/**
	 *Total points remaining 
	 * 
	 */
	private Long totalPointsBalance;

	private Date transactionTimestamp;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERTRANSACTION")
	@SequenceGenerator(name = "SEQ_USERTRANSACTION", sequenceName = "SEQ_USERTRANSACTION", allocationSize = 1)
	@Column(name = "USER_TRANSACTION_ID")
	public Long getUserTransactionID() {
		return userTransactionID;
	}

	public void setUserTransactionID(Long userTransactionID) {
		this.userTransactionID = userTransactionID;
	}

	@Column(name = "POINTS_DEPOSITED")
	public Long getPointsDeposited() {
		return pointsDeposited;
	}

	public void setPointsDeposited(Long pointsDeposited) {
		this.pointsDeposited = pointsDeposited;
	}

	@Column(name = "POINTS_WITHDRAWN")
	public Long getPointsWithdrawn() {
		return pointsWithdrawn;
	}

	public void setPointsWithdrawn(Long pointsWithdrawn) {
		this.pointsWithdrawn = pointsWithdrawn;
	}

	@Column(name = "SOURCE_TRANSACTION_ID")
	public Long getSourceTransactionID() {
		return sourceTransactionID;
	}

	public void setSourceTransactionID(Long sourceTransactionID) {
		this.sourceTransactionID = sourceTransactionID;
	}

	@Column(name = "POINTS_REMAINING")
	public Long getPointsRemaining() {
		return pointsRemaining;
	}

	public void setPointsRemaining(Long pointsRemaining) {
		this.pointsRemaining = pointsRemaining;
	}

	@Column(name = "RETURN_TRANSACTION_FLAG")
	public boolean isReturnTransactionFlag() {
		return returnTransactionFlag;
	}

	public void setReturnTransactionFlag(boolean returnTransactionFlag) {
		this.returnTransactionFlag = returnTransactionFlag;
	}

	@Column(name = "DATE_OF_EXPIRY")
	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	@Column(name = "POINTS_STATUS_IND")
	public Character getPointsStatusInd() {
		return pointsStatusInd;
	}

	public void setPointsStatusInd(Character pointsStatusInd) {
		this.pointsStatusInd = pointsStatusInd;
	}

	@Column(name = "FEATURE_OF_TRANSACTION")
	public Long getFeatureSetId() {
		return featureSetId;
	}

	public void setFeatureSetId(Long featureSetId) {
		this.featureSetId = featureSetId;
	}

	@Column(name = "ACCESS_CODE_ID")
	public Long getAccessCodeId() {
		return accessCodeId;
	}

	public void setAccessCodeId(Long accessCodeId) {
		this.accessCodeId = accessCodeId;
	}

	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ACCOUNT_ID")
	public UserBankAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserBankAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Column(name = "PROGRAM_ID")
	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	@Column(name = "REASON_CODE")
	public String getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	@Column(name = "TOTAL_POINTS_BALANCE")
	public Long getTotalPointsBalance() {
		return totalPointsBalance;
	}

	public void setTotalPointsBalance(Long totalPointsBalance) {
		this.totalPointsBalance = totalPointsBalance;
	}

	@Column(name = "TRANSACTION_TIMESTAMP")
	public Date getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Date transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

}

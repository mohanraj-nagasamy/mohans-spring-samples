/*
 * Copyright 2007 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id$
 */

package com.test.conversion.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.test.conversion.model.listener.AuditListener;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "USER_ACCOUNT")
public class UserBankAccount implements Auditable, Serializable {
	private static final long serialVersionUID = 1L;
	private Long userAccountId;

	private Long systemUserId;
	private Long bankId;

	private Set<UserTransaction> userTransactions = new HashSet<UserTransaction>();

	private AuditTrail auditTrail = new AuditTrail();

	@Id
	@SequenceGenerator(name = "SEQ_USERACCOUNT", sequenceName = "SEQ_USERACCOUNT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERACCOUNT")
	@Column(name = "USER_ACCOUNT_ID")
	public Long getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Long userAccountId) {
		this.userAccountId = userAccountId;
	}

	@Column(name = "SYSTEM_USER_ID")
	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	@Column(name = "BANK_ID")
	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userAccount")
	public Set<UserTransaction> getUserTransactions() {
		return userTransactions;
	}

	public void setUserTransactions(Set<UserTransaction> userTransactions) {
		this.userTransactions = userTransactions;
	}

}

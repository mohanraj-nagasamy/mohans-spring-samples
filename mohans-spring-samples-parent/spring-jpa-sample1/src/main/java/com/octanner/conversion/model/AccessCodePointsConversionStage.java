/**
 * Copyright 2008 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id: 
 */
package com.octanner.conversion.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.octanner.conversion.model.listener.AuditListener;

@Entity()
@EntityListeners(AuditListener.class)
@Table(name = "ACCESS_CODE_POINTS_STAGE")
public class AccessCodePointsConversionStage implements Auditable, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String employeeUniqueId;

	private String accessCode;

	private BigDecimal awardAmount;

	private BigDecimal points;

	private String soldToPartyNumber;
	private String loadStatus;
	
	private Long systemUserId;

	private AuditTrail auditTrail = new AuditTrail();

	@Id
	@SequenceGenerator(name = "SEQ_ACCESSCODEPOINTSSTAGE", sequenceName = "SEQ_ACCESSCODEPOINTSSTAGE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCESSCODEPOINTSSTAGE")
	@Column(name = "ACCESS_CODE_POINTS_STAGE_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "EMPLOYEE_ID")
	public String getEmployeeUniqueId() {
		return employeeUniqueId;
	}

	public void setEmployeeUniqueId(String employeeUniqueId) {
		this.employeeUniqueId = employeeUniqueId;
	}

	@Column(name = "ACCESS_CODE")
	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	@Column(name = "ESTIMATED_DOLLAR_VALUE")
	public BigDecimal getAwardAmount() {
		return awardAmount;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}

	@Column(name = "POINTS")
	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	@Column(name = "SOLD_TO_PARTY_NUM")
	public String getSoldToPartyNumber() {
		return soldToPartyNumber;
	}

	public void setSoldToPartyNumber(String soldToPartyNumber) {
		this.soldToPartyNumber = soldToPartyNumber;
	}

	@Column(name = "LOAD_STATUS")
	public String getLoadStatus() {
		return loadStatus;
	}

	public void setLoadStatus(String loadStatus) {
		this.loadStatus = loadStatus;
	}

	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	@Transient
	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

}

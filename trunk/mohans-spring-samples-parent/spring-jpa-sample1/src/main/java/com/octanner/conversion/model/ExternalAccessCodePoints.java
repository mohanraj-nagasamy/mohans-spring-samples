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

import com.octanner.conversion.model.listener.AuditListener;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "EXTERNAL_ACCESS_CODE_POINTS")
public class ExternalAccessCodePoints implements Auditable, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long systemUserId;
	private Long programId;

	private String accessCode;
	private BigDecimal points;

	private BigDecimal awardAmount;

	private AuditTrail auditTrail = new AuditTrail();

	@Id
	@SequenceGenerator(name = "SEQ_EXTERNALACCESSCODEPOINTS", sequenceName = "SEQ_EXTERNALACCESSCODEPOINTS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EXTERNALACCESSCODEPOINTS")
	@Column(name = "EXTERNAL_ACCESS_CD_POINTS_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	@Column(name = "SYSTEM_USER_ID")
	public Long getSystemUserId() {
		return systemUserId;
	}

	public void setSystemUserId(Long systemUserId) {
		this.systemUserId = systemUserId;
	}

	@Column(name = "PROGRAM_ID")
	public Long getProgramId() {
		return programId;
	}

	public void setProgramId(Long programId) {
		this.programId = programId;
	}

	@Column(name = "ACCESS_CODE")
	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	@Column(name = "POINTS")
	public BigDecimal getPoints() {
		return points;
	}

	public void setPoints(BigDecimal points) {
		this.points = points;
	}

	@Column(name = "ESTIMATED_DOLLAR_VALUE")
	public BigDecimal getAwardAmount() {
		return awardAmount;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}

}

package com.octanner.conversion.model.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.octanner.conversion.model.Auditable;

/**
 * 
 * Listener to insert and update Audit info like created and modified details for all entities.
 * 
 */
public class AuditListener {

	@PrePersist
	public void setCreatedBy(Auditable model) {

		model.getAuditTrail().setCreatedBy("conversion");
		model.getAuditTrail().setCreatedOn(new Date());
	}

	@PreUpdate
	public void setModifiedBy(Auditable model) {
		model.getAuditTrail().setModifiedBy("conversion");
		model.getAuditTrail().setModifiedOn(new Date());
	}

}

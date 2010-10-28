/**
 * Copyright 2008 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id: 
 */
package com.octanner.conversion.model;

/**
 * 
 * Audit interface that all entities should implement
 *
 */
public interface Auditable {

	public AuditTrail getAuditTrail();

	public void setAuditTrail(AuditTrail auditTrail);

}

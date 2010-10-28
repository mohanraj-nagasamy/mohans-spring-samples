/*
 * Copyright 2008 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * $Id:$ : FeatureGroup.java Feb 04, 2010 8:22:59 PM Justin.John
 */
package com.octanner.conversion.model;

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
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.octanner.conversion.model.listener.AuditListener;

@Entity
@EntityListeners(AuditListener.class)
@Table(name = "FEATURE_GROUP", uniqueConstraints = @UniqueConstraint(columnNames = "FEATURE_GROUP_NAME"))
public class FeatureGroup implements Auditable, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long featureGroupId;
	private String featureGroupName;
	private AuditTrail auditTrail = new AuditTrail();
	private Set<FeatureSet> featureSets = new HashSet<FeatureSet>(0);

	@Id
	@Column(name = "FEATURE_GROUP_ID", nullable = false)
	@SequenceGenerator(name = "SEQ_FEATUREGROUP", sequenceName = "SEQ_FEATUREGROUP")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FEATUREGROUP")
	public Long getFeatureGroupId() {
		return this.featureGroupId;
	}

	public void setFeatureGroupId(Long featureGroupId) {
		this.featureGroupId = featureGroupId;
	}

	@Column(name = "FEATURE_GROUP_NAME", unique = true, nullable = false, length = 25)
	public String getFeatureGroupName() {
		return this.featureGroupName;
	}

	public void setFeatureGroupName(String featureGroupName) {
		this.featureGroupName = featureGroupName;
	}

	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "featureGroup")
	public Set<FeatureSet> getFeatureSets() {
		return this.featureSets;
	}

	public void setFeatureSets(Set<FeatureSet> featureSets) {
		this.featureSets = featureSets;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof FeatureGroup)) {
			return false;
		}
		FeatureGroup rhs = (FeatureGroup) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(this.auditTrail, rhs.auditTrail).append(
				this.featureGroupName, rhs.featureGroupName).append(this.featureGroupId, rhs.featureGroupId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(66634629, 1922132159).appendSuper(super.hashCode()).append(this.auditTrail).append(
				this.featureGroupName).append(this.featureGroupId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("auditTrail", this.auditTrail).append("featureGroupName",
				this.featureGroupName).append("featureGroupId", this.featureGroupId).toString();
	}

}

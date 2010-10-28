/**
 * Copyright 2008 - 2009 OC Tanner Company.  All Rights Reserved.
 *
 * This software is the property of OC Tanner Company.  Use of this software in whole or in
 * part without the express written consent of OC Tanner is strictly prohibited.
 *
 * Id: FeatureSet.java Apr 23, 2009 2:07:38 PM Mohanraj.Nagasamy
 */
package com.test.conversion.model;

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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.test.conversion.model.listener.AuditListener;


@Entity
@EntityListeners(AuditListener.class)
@Table(name = "FEATURE_SET", uniqueConstraints = @UniqueConstraint(columnNames = "FEATURE_SET_NAME"))
@NamedQuery(name = "getFeatureSet", query = "select fs.featureSetId from FeatureSet fs where fs.featureSetName=:featureSetName")
public class FeatureSet implements Auditable, java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long featureSetId;
	private String featureSetName;

	private AuditTrail auditTrail = new AuditTrail();
	private FeatureGroup featureGroup;
	//private Set<UserTransaction> userTransactions = new HashSet<UserTransaction>(0);

	@Id
	@SequenceGenerator(name = "SEQ_FEATURESET", sequenceName = "SEQ_FEATURESET")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FEATURESET")
	@Column(name = "FEATURE_SET_ID", nullable = false)
	public Long getFeatureSetId() {
		return this.featureSetId;
	}

	/**
	 * sets the featureSetId.
	 * 
	 * @param pFeatureSetId
	 */
	public void setFeatureSetId(Long pFeatureSetId) {
		this.featureSetId = pFeatureSetId;
	}

	/**
	 * gets the featureSetName.
	 * 
	 * @return featureSetName
	 */
	@Column(name = "FEATURE_SET_NAME", unique = true, nullable = false, length = 130)
	public String getFeatureSetName() {
		return this.featureSetName;
	}

	/**
	 * sets the featureSetName.
	 * 
	 * @param pFeatureSetName
	 */
	public void setFeatureSetName(String pFeatureSetName) {
		this.featureSetName = pFeatureSetName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FEATURE_GROUP_ID")
	public FeatureGroup getFeatureGroup() {
		return this.featureGroup;
	}

	public void setFeatureGroup(FeatureGroup featureGroup) {
		this.featureGroup = featureGroup;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "featureSet")
	public Set<UserTransaction> getUserTransactions() {
		return this.userTransactions;
	}

	public void setUserTransactions(Set<UserTransaction> userTransactions) {
		this.userTransactions = userTransactions;
	}
*/
	/**
	 * gets the AuditTrail.
	 * 
	 * @see com.octanner.common.entity.Auditable#getAuditTrail()
	 * @return auditTrail
	 */
	@Embedded
	public AuditTrail getAuditTrail() {
		return auditTrail;
	}

	/**
	 * sets the AuditTrail.
	 * 
	 * @see com.octanner.common.entity.Auditable#setAuditTrail(com.octanner.common.entity.AuditTrail)
	 */
	public void setAuditTrail(AuditTrail auditTrail) {
		this.auditTrail = auditTrail;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		if (!(object instanceof FeatureSet)) {
			return false;
		}
		FeatureSet rhs = (FeatureSet) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(this.auditTrail, rhs.auditTrail).append(
				this.featureSetId, rhs.featureSetId).append(this.featureSetName, rhs.featureSetName).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(926913199, -2053649097).appendSuper(super.hashCode()).append(this.auditTrail)
				.append(this.featureSetId).append(this.featureSetName).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("auditTrail", this.auditTrail).append("featureSetName",
				this.featureSetName).append("featureSetId", this.featureSetId).toString();
	}

}

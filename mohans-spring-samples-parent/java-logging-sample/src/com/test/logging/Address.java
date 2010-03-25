/***********************************************************************************************************/
/* Filename: Address.java                                                                                   */
/* Author  : Neeraj Arora                                                                                  */
/* Date    : 02/06/07                                                                                      */
/* Purpose : This file is the ket billing service file for SPOT
/* Version : SPOT 1.4                                                                                      */
/***********************************************************************************************************/
package com.test.logging;

public class Address {
	private String streetNo;
	private String streetName;
	private String aptNo;
	private String city;
	private String state;
	private String zipCode;
	private String countryCode;
	private String additionalInfo;
	private String county;

	public Address() {
	}

	public Address(Address myAddress) {
		this.streetNo = myAddress.streetNo;
		this.streetName = myAddress.streetName;
		this.aptNo = myAddress.aptNo;
		this.city = myAddress.city;
		this.state = myAddress.state;
		this.zipCode = myAddress.zipCode;
		this.countryCode = myAddress.countryCode;
		this.additionalInfo = myAddress.additionalInfo;
		this.county = myAddress.county;
	}

	public void setStreetNo(String sStreetNo) {
		streetNo = sStreetNo;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetName(String sStreetName) {
		streetName = sStreetName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setAptNo(String sAptNo) {
		aptNo = sAptNo;
	}

	public String getAptNo() {
		return aptNo;
	}

	public void setCity(String sCity) {
		city = sCity;
	}

	public String getCity() {
		return city;
	}

	public void setState(String sState) {
		state = sState;
	}

	public String getState() {
		return state;
	}

	public void setCountryCode(String sCountryCode) {
		countryCode = sCountryCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setZipCode(String sZipCode) {
		zipCode = sZipCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCounty() {
		return county;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [additionalInfo=");
		builder.append(additionalInfo);
		builder.append(", aptNo=");
		builder.append(aptNo);
		builder.append(", city=");
		builder.append(city);
		builder.append(", countryCode=");
		builder.append(countryCode);
		builder.append(", county=");
		builder.append(county);
		builder.append(", state=");
		builder.append(state);
		builder.append(", streetName=");
		builder.append(streetName);
		builder.append(", streetNo=");
		builder.append(streetNo);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append("]");
		return builder.toString();
	}

}

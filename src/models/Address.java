package models;

public class Address {
	private String houseNumber;
	private String firstLine;
	private String town = "";
	private String countyState;
	private String postcode;
	private String country;
	
	public Address(String houseNum, String firstLine, String town, String countyState, String postcode, String country) {
		this.houseNumber = houseNum;
		this.firstLine = firstLine;
		this.town = town;
		this.countyState = countyState;
		this.postcode = postcode;
		this.country = country;
	}
	// Getters
	public String getHouseNumber() {
		return houseNumber;
	}
	
	public String getFirstLine() {
		return firstLine;
	}
	
	public String getTown() {
		return town;
	}
	
	public String getCountyState() {
		return countyState;
	}

	public String getPostcode() {
		return postcode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String toString() {
		return houseNumber + ", " + firstLine + ", " + town + ", " + countyState + ", " + postcode + ", " + country;
	}
	
	// Setters
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}
	public void setSecondLine(String town) {
		this.town = town;
	}
	public void setCountyState(String countyState) {
		this.countyState = countyState;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
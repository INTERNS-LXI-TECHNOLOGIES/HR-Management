package com.lxisoft.appraisal.domain;

public class UserDataBean {
	
	String  firstName;
	String LastName;
	String company;
	
	public UserDataBean(String firstName, String lastName, String company) {
		super();
		this.firstName = firstName;
		LastName = lastName;
		this.company = company;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

}

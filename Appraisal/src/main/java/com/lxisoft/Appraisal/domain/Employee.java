package com.lxisoft.Appraisal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity

@Table(name = "employee")
public class Employee
{
	@Id
    public int id;
 
    @Column
    public String firstName;
 
    @Column
    public String emailID;
 
    @Column
    public String lastName;
	
    @Column
    public String company;
	
	
    public int getId() {
		return id;
	}
    public void setId(int id) {
		this.id = id;
	}
    public String getFirstName() {
		return firstName;
	}
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    public String getLastName() {
		return lastName;
	}
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    public String getEmailID() {
		return emailID;
	}
    public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
    public String getCompany() {
		return company;
	}
    public void setCompany(String company) {
		this.company = company;
	}
}
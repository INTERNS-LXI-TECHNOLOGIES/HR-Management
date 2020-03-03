package com.lxisoft.Appraisal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	
	
	private int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	private String getFirstName() {
		return firstName;
	}
	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	private String getLastName() {
		return lastName;
	}
	private void setLastName(String lastName) {
		this.lastName = lastName;
	}
	private String getEmailID() {
		return emailID;
	}
	private void setEmailID(String emailID) {
		this.emailID = emailID;
	}
}
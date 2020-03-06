package com.lxisoft.Appraisal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity


public class Employee
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	public Long id;
	
	public Employee()
	{
		
	}
    
    public String firstName;
   
    private String lastName;	
    @Column
    private String emailID;
    @Column
    public String company;
    



	public Long getId() {
		
	
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCompany() {
		return company;
	}
    public void setCompany(String company) {
		this.company = company;
	}

}
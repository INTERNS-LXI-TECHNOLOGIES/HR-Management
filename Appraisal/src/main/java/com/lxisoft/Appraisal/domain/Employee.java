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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column 
	public int id;
    @Column 
    public String firstName;
    @Column
    private String emailID;
    @Column
    private String lastName1;	
    @Column
    public String company;
    @Column
    public String lastName;



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

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getLastName() {
		return lastName1;
	}

	public void setLastName(String lastName) {
		this.lastName1 = lastName;
	}
	
	public String getCompany() {
		return company;
	}
    public void setCompany(String company) {
		this.company = company;
	}

}
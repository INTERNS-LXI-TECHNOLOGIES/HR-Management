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
<<<<<<< HEAD
	private int id;
 
    @Column
    private String firstName;
=======
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column 
	public int id;
 
    @Column 
    public String firstName;
>>>>>>> 14ce7a9adaa4de003eeffb634ac43e29cb6a8e6b
 
    @Column
    private String emailID;
 
    @Column
<<<<<<< HEAD
    private String lastName;
	
    @Column
    public String company;
		
    public int getId() {
=======
    public String lastName;
    @Column
    public String company;


	public int getId() {
>>>>>>> 14ce7a9adaa4de003eeffb634ac43e29cb6a8e6b
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
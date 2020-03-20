package com.lxisoft.Appraisal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.lxisoft.Appraisal.model.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lxisoft.Appraisal.model.LateArrival;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "user",uniqueConstraints={@UniqueConstraint(columnNames={"username"})})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message="must not empty")
	@Size(min=2,max=20, message="First name must be min 2 char")
	private String firstName;
	
	@NotEmpty(message="must not empty")
	@Size(min=2,max=20, message="First name must be min 2 char")
	private String lastName;
	
	@NotEmpty(message="must not empty")
	@Email (message="invalid e mail")
	private String emailID;
	
	@NotEmpty(message="must not empty")
	private String company;
	
	
	private LocalDate joiningDate;
	
	private String fileContentType;
	
	private LocalDate dob;
	@Lob
	private byte[] image;
	
	@NotEmpty(message="must not empty")
	private String position;
	
	@NotEmpty(message="must not empty")
	private String username;
	
	@NotEmpty(message="must not empty")
	private String password;
	
	 @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	    @JoinTable(
	        name = "users_roles",
	        joinColumns = @JoinColumn(
	            name = "user_id"),
	        inverseJoinColumns = @JoinColumn(
	            name = "role_id"))
	  @JsonManagedReference
	 private Set < Role > roles;

	 @OneToMany(cascade = CascadeType.ALL)
	    @JoinColumn(name = "user_id")
	  @JsonManagedReference
	 private List<Leave> leave;
	 
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "user_id")
	  @JsonManagedReference
	 private List<LateArrival> lateArrival;
	 
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "user_id")
	  @JsonManagedReference
	 private List<reportStatus> reportStatus;
	 
	 @OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "user_id")
	  @JsonManagedReference
	 private List<EvaluationTest> evaluationTest;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(Long id,
			@NotEmpty @Size(min = 2, max = 20, message = "First name must be min 2 char") String firstName) {
		super();
		this.id = id;
		this.firstName = firstName;
	}
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
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set < Role > roles) {
		this.roles=roles;
	}
	public List<Leave> getLeave() {
		return leave;
	}
	public void setLeave(List<Leave> leave) {
		this.leave = leave;
	}
	public List<LateArrival> getLateArrival() {
		return lateArrival;
	}
	public void setLateArrival(List<LateArrival> lateArrival) {
		this.lateArrival = lateArrival;
	}
	public List<reportStatus> getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(List<reportStatus> reportStatus) {
		this.reportStatus = reportStatus;
	}
	public List<EvaluationTest> getEvaluationTest() {
		return evaluationTest;
	}
	public void setEvaluationTest(List<EvaluationTest> evaluationTest) {
		this.evaluationTest = evaluationTest;
	}
	
	

}

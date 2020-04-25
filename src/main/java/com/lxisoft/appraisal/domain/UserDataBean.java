package com.lxisoft.appraisal.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * A UserDataBean.
 */
@Entity
@Table(name = "user_data_bean")
public class UserDataBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "company")
    private String company;

    @Column(name = "position")
    private String position;

    @Column(name = "email")
    private String email;

    @Column(name = "attendance")
    private Long attendance;

    @Column(name = "punctuality")
    private Long punctuality;

    @Column(name = "meeting_targets")
    private Long meetingTargets;

    @Column(name = "company_policy")
    private Long companyPolicy;

    @Column(name = "code_quality")
    private Long codeQuality;
    
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public UserDataBean() {
		super();
	}

	public UserDataBean(String firstName, String lastName, String company, String position, String email,
			Long attendance, Long punctuality, Long meetingTargets, Long companyPolicy, Long codeQuality) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.position = position;
		this.email = email;
		this.attendance = attendance;
		this.punctuality = punctuality;
		this.meetingTargets = meetingTargets;
		this.companyPolicy = companyPolicy;
		this.codeQuality = codeQuality;
	}

	public void setId(Long id) {
        this.id = id;
    }

    

	public String getFirstName() {
        return firstName;
    }

    public UserDataBean firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDataBean lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public UserDataBean company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public UserDataBean position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public UserDataBean email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAttendance() {
        return attendance;
    }

    public UserDataBean attendance(Long attendance) {
        this.attendance = attendance;
        return this;
    }

    public void setAttendance(Long attendance) {
        this.attendance = attendance;
    }

    public Long getPunctuality() {
        return punctuality;
    }

    public UserDataBean punctuality(Long punctuality) {
        this.punctuality = punctuality;
        return this;
    }

    public void setPunctuality(Long punctuality) {
        this.punctuality = punctuality;
    }

    public Long getMeetingTargets() {
        return meetingTargets;
    }

    public UserDataBean meetingTargets(Long meetingTargets) {
        this.meetingTargets = meetingTargets;
        return this;
    }

    public void setMeetingTargets(Long meetingTargets) {
        this.meetingTargets = meetingTargets;
    }

    public Long getCompanyPolicy() {
        return companyPolicy;
    }

    public UserDataBean companyPolicy(Long companyPolicy) {
        this.companyPolicy = companyPolicy;
        return this;
    }

    public void setCompanyPolicy(Long companyPolicy) {
        this.companyPolicy = companyPolicy;
    }

    public Long getCodeQuality() {
        return codeQuality;
    }

    public UserDataBean codeQuality(Long codeQuality) {
        this.codeQuality = codeQuality;
        return this;
    }

    public void setCodeQuality(Long codeQuality) {
        this.codeQuality = codeQuality;
    }

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDataBean)) {
            return false;
        }
        return id != null && id.equals(((UserDataBean) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserDataBean{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", company='" + getCompany() + "'" +
            ", position='" + getPosition() + "'" +
            ", email='" + getEmail() + "'" +
            ", attendance=" + getAttendance() +
            ", punctuality=" + getPunctuality() +
            ", meetingTargets=" + getMeetingTargets() +
            ", companyPolicy=" + getCompanyPolicy() +
            ", codeQuality=" + getCodeQuality() +
            "}";
    }
}

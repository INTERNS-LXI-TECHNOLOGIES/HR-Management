package com.lxisoft.appraisal.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A UsersDataBean.
 */
@Entity
@Table(name = "users_data_bean")
public class UsersDataBean implements Serializable {

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

    @Column(name = "attendence")
    private Long attendence;

    @Column(name = "punctuality")
    private Long punctuality;

    @Column(name = "meeting_targets")
    private Long meetingTargets;

    @Column(name = "company_policy")
    private Long companyPolicy;

    @Column(name = "code_quality")
    private Long codeQuality;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public UsersDataBean firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersDataBean lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public UsersDataBean company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public UsersDataBean position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public UsersDataBean email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAttendence() {
        return attendence;
    }

    public UsersDataBean attendence(Long attendence) {
        this.attendence = attendence;
        return this;
    }

    public void setAttendence(Long attendence) {
        this.attendence = attendence;
    }

    public Long getPunctuality() {
        return punctuality;
    }

    public UsersDataBean punctuality(Long punctuality) {
        this.punctuality = punctuality;
        return this;
    }

    public void setPunctuality(Long punctuality) {
        this.punctuality = punctuality;
    }

    public Long getMeetingTargets() {
        return meetingTargets;
    }

    public UsersDataBean meetingTargets(Long meetingTargets) {
        this.meetingTargets = meetingTargets;
        return this;
    }

    public void setMeetingTargets(Long meetingTargets) {
        this.meetingTargets = meetingTargets;
    }

    public Long getCompanyPolicy() {
        return companyPolicy;
    }

    public UsersDataBean companyPolicy(Long companyPolicy) {
        this.companyPolicy = companyPolicy;
        return this;
    }

    public void setCompanyPolicy(Long companyPolicy) {
        this.companyPolicy = companyPolicy;
    }

    public Long getCodeQuality() {
        return codeQuality;
    }

    public UsersDataBean codeQuality(Long codeQuality) {
        this.codeQuality = codeQuality;
        return this;
    }

    public void setCodeQuality(Long codeQuality) {
        this.codeQuality = codeQuality;
    }

    public byte[] getImage() {
        return image;
    }

    public UsersDataBean image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public UsersDataBean imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsersDataBean)) {
            return false;
        }
        return id != null && id.equals(((UsersDataBean) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UsersDataBean{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", company='" + getCompany() + "'" +
            ", position='" + getPosition() + "'" +
            ", email='" + getEmail() + "'" +
            ", attendence=" + getAttendence() +
            ", punctuality=" + getPunctuality() +
            ", meetingTargets=" + getMeetingTargets() +
            ", companyPolicy=" + getCompanyPolicy() +
            ", codeQuality=" + getCodeQuality() +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            "}";
    }
}

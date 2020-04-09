package com.lxisoft.appraisal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Appraisal.
 */
@Entity
@Table(name = "appraisal")
public class Appraisal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_extra_id")
    @JsonIgnore
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttendance() {
        return attendance;
    }

    public Appraisal attendance(Long attendance) {
        this.attendance = attendance;
        return this;
    }

    public void setAttendance(Long attendance) {
        this.attendance = attendance;
    }

    public Long getPunctuality() {
        return punctuality;
    }

    public Appraisal punctuality(Long punctuality) {
        this.punctuality = punctuality;
        return this;
    }

    public void setPunctuality(Long punctuality) {
        this.punctuality = punctuality;
    }

    public Long getMeetingTargets() {
        return meetingTargets;
    }

    public Appraisal meetingTargets(Long meetingTargets) {
        this.meetingTargets = meetingTargets;
        return this;
    }

    public void setMeetingTargets(Long meetingTargets) {
        this.meetingTargets = meetingTargets;
    }

    public Long getCompanyPolicy() {
        return companyPolicy;
    }

    public Appraisal companyPolicy(Long companyPolicy) {
        this.companyPolicy = companyPolicy;
        return this;
    }

    public void setCompanyPolicy(Long companyPolicy) {
        this.companyPolicy = companyPolicy;
    }

    public Long getCodeQuality() {
        return codeQuality;
    }

    public Appraisal codeQuality(Long codeQuality) {
        this.codeQuality = codeQuality;
        return this;
    }

    public void setCodeQuality(Long codeQuality) {
        this.codeQuality = codeQuality;
    }

    public UserExtra getUserExtra() {
        return userExtra;
    }

    public Appraisal userExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
        return this;
    }

    public void setUserExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Appraisal)) {
            return false;
        }
        return id != null && id.equals(((Appraisal) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Appraisal{" +
            "id=" + getId() +
            ", attendance=" + getAttendance() +
            ", punctuality=" + getPunctuality() +
            ", meetingTargets=" + getMeetingTargets() +
            ", companyPolicy=" + getCompanyPolicy() +
            ", codeQuality=" + getCodeQuality() +
            "}";
    }
}

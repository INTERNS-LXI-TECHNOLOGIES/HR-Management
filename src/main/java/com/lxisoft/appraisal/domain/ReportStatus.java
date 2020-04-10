package com.lxisoft.appraisal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A ReportStatus.
 */
@Entity
@Table(name = "report_status")
public class ReportStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporting_time")
    private Instant reportingTime;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JsonIgnoreProperties("reportStatuses")
    @JoinColumn(name="user_extra_id")
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getReportingTime() {
        return reportingTime;
    }

    public ReportStatus reportingTime(Instant reportingTime) {
        this.reportingTime = reportingTime;
        return this;
    }

    public void setReportingTime(Instant reportingTime) {
        this.reportingTime = reportingTime;
    }

    public String getType() {
        return type;
    }

    public ReportStatus type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserExtra getUserExtra() {
        return userExtra;
    }

    public ReportStatus userExtra(UserExtra userExtra) {
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
        if (!(o instanceof ReportStatus)) {
            return false;
        }
        return id != null && id.equals(((ReportStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ReportStatus{" +
            "id=" + getId() +
            ", reportingTime='" + getReportingTime() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}

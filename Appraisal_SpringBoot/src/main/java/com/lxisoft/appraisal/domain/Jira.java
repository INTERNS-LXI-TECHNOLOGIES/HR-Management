package com.lxisoft.appraisal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Jira.
 */
@Entity
@Table(name = "jira")
public class Jira implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "hour")
    private Float hour;

    @ManyToOne
    @JsonIgnoreProperties(value = "jiras", allowSetters = true)
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Jira date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getHour() {
        return hour;
    }

    public Jira hour(Float hour) {
        this.hour = hour;
        return this;
    }

    public void setHour(Float hour) {
        this.hour = hour;
    }

    public UserExtra getUserExtra() {
        return userExtra;
    }

    public Jira userExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
        return this;
    }

    public void setUserExtra(UserExtra userExtra) {
        this.userExtra = userExtra;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Jira)) {
            return false;
        }
        return id != null && id.equals(((Jira) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Jira{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", hour=" + getHour() +
            "}";
    }
}

package com.lxisoft.appraisal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Hackathon.
 */
@Entity
@Table(name = "hackathon")
public class Hackathon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "mark")
    private Long mark;

    @ManyToOne
    @JsonIgnoreProperties("hackathons")
    private UserExtra userExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Hackathon date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getMark() {
        return mark;
    }

    public Hackathon mark(Long mark) {
        this.mark = mark;
        return this;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }

    public UserExtra getUserExtra() {
        return userExtra;
    }

    public Hackathon userExtra(UserExtra userExtra) {
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
        if (!(o instanceof Hackathon)) {
            return false;
        }
        return id != null && id.equals(((Hackathon) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Hackathon{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", mark=" + getMark() +
            "}";
    }
}

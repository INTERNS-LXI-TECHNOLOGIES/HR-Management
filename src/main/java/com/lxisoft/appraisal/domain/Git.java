package com.lxisoft.appraisal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Git.
 */
@Entity
@Table(name = "git")
public class Git implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "mark")
    private Long mark;

    @ManyToOne
    @JsonIgnoreProperties("gits")
    private User user;

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

    public Git date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getMark() {
        return mark;
    }

    public Git mark(Long mark) {
        this.mark = mark;
        return this;
    }

    public void setMark(Long mark) {
        this.mark = mark;
    }

    public User getUser() {
        return user;
    }

    public Git user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Git)) {
            return false;
        }
        return id != null && id.equals(((Git) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Git{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", mark=" + getMark() +
            "}";
    }
}

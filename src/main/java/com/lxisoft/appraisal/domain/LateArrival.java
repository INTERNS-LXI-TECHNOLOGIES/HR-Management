package com.lxisoft.appraisal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A LateArrival.
 */
@Entity
@Table(name = "late_arrival")
public class LateArrival implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reached_time")
    private Instant reachedTime;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JsonIgnoreProperties("lateArrivals")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getReachedTime() {
        return reachedTime;
    }

    public LateArrival reachedTime(Instant reachedTime) {
        this.reachedTime = reachedTime;
        return this;
    }

    public void setReachedTime(Instant reachedTime) {
        this.reachedTime = reachedTime;
    }

    public String getType() {
        return type;
    }

    public LateArrival type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public LateArrival user(User user) {
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
        if (!(o instanceof LateArrival)) {
            return false;
        }
        return id != null && id.equals(((LateArrival) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LateArrival{" +
            "id=" + getId() +
            ", reachedTime='" + getReachedTime() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}

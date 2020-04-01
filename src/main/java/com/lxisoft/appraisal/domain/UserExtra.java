package com.lxisoft.appraisal.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A UserExtra.
 */
@Entity
@Table(name = "user_extra")
public class UserExtra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "company")
    private String company;

    @Column(name = "position")
    private String position;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "dob")
    private LocalDate dob;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "username")
    private String username;

    @OneToOne

    @MapsId
    @JoinColumn(name = "id")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public UserExtra company(String company) {
        this.company = company;
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public UserExtra position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public UserExtra joiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
        return this;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getDob() {
        return dob;
    }

    public UserExtra dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public byte[] getImage() {
        return image;
    }

    public UserExtra image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public UserExtra imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getUsername() {
        return username;
    }

    public UserExtra username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public UserExtra user(User user) {
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
        if (!(o instanceof UserExtra)) {
            return false;
        }
        return id != null && id.equals(((UserExtra) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserExtra{" +
            "id=" + getId() +
            ", company='" + getCompany() + "'" +
            ", position='" + getPosition() + "'" +
            ", joiningDate='" + getJoiningDate() + "'" +
            ", dob='" + getDob() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", username='" + getUsername() + "'" +
            "}";
    }
}

package com.lxisoft.appraisal.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "userExtra")
    private Set<Git> gits = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "userExtra")
    private Set<LateArrival> lateArrivals = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "userExtra")
    private Set<ReportStatus> reportStatuses = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "userExtra")
    private Set<Hackathon> hackathons = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "userExtra")
    private Set<Leave> leaves = new HashSet<>();

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "userExtra")
    private Set<Appraisal> appraisals = new HashSet<>();

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

    public Set<Git> getGits() {
        return gits;
    }

    public UserExtra gits(Set<Git> gits) {
        this.gits = gits;
        return this;
    }

    public UserExtra addGit(Git git) {
        this.gits.add(git);
        git.setUserExtra(this);
        return this;
    }

    public UserExtra removeGit(Git git) {
        this.gits.remove(git);
        git.setUserExtra(null);
        return this;
    }

    public void setGits(Set<Git> gits) {
        this.gits = gits;
    }

    public Set<LateArrival> getLateArrivals() {
        return lateArrivals;
    }

    public UserExtra lateArrivals(Set<LateArrival> lateArrivals) {
        this.lateArrivals = lateArrivals;
        return this;
    }

    public UserExtra addLateArrival(LateArrival lateArrival) {
        this.lateArrivals.add(lateArrival);
        lateArrival.setUserExtra(this);
        return this;
    }

    public UserExtra removeLateArrival(LateArrival lateArrival) {
        this.lateArrivals.remove(lateArrival);
        lateArrival.setUserExtra(null);
        return this;
    }

    public void setLateArrivals(Set<LateArrival> lateArrivals) {
        this.lateArrivals = lateArrivals;
    }

    public Set<ReportStatus> getReportStatuses() {
        return reportStatuses;
    }

    public UserExtra reportStatuses(Set<ReportStatus> reportStatuses) {
        this.reportStatuses = reportStatuses;
        return this;
    }

    public UserExtra addReportStatus(ReportStatus reportStatus) {
        this.reportStatuses.add(reportStatus);
        reportStatus.setUserExtra(this);
        return this;
    }

    public UserExtra removeReportStatus(ReportStatus reportStatus) {
        this.reportStatuses.remove(reportStatus);
        reportStatus.setUserExtra(null);
        return this;
    }

    public void setReportStatuses(Set<ReportStatus> reportStatuses) {
        this.reportStatuses = reportStatuses;
    }

    public Set<Hackathon> getHackathons() {
        return hackathons;
    }

    public UserExtra hackathons(Set<Hackathon> hackathons) {
        this.hackathons = hackathons;
        return this;
    }

    public UserExtra addHackathon(Hackathon hackathon) {
        this.hackathons.add(hackathon);
        hackathon.setUserExtra(this);
        return this;
    }

    public UserExtra removeHackathon(Hackathon hackathon) {
        this.hackathons.remove(hackathon);
        hackathon.setUserExtra(null);
        return this;
    }

    public void setHackathons(Set<Hackathon> hackathons) {
        this.hackathons = hackathons;
    }

    public Set<Leave> getLeaves() {
        return leaves;
    }

    public UserExtra leaves(Set<Leave> leaves) {
        this.leaves = leaves;
        return this;
    }

    public UserExtra addLeave(Leave leave) {
        this.leaves.add(leave);
        leave.setUserExtra(this);
        return this;
    }

    public UserExtra removeLeave(Leave leave) {
        this.leaves.remove(leave);
        leave.setUserExtra(null);
        return this;
    }

    public void setLeaves(Set<Leave> leaves) {
        this.leaves = leaves;
    }

    public Set<Appraisal> getAppraisals() {
        return appraisals;
    }

    public UserExtra appraisals(Set<Appraisal> appraisals) {
        this.appraisals = appraisals;
        return this;
    }

    public UserExtra addAppraisal(Appraisal appraisal) {
        this.appraisals.add(appraisal);
        appraisal.setUserExtra(this);
        return this;
    }

    public UserExtra removeAppraisal(Appraisal appraisal) {
        this.appraisals.remove(appraisal);
        appraisal.setUserExtra(null);
        return this;
    }

    public void setAppraisals(Set<Appraisal> appraisals) {
        this.appraisals = appraisals;
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

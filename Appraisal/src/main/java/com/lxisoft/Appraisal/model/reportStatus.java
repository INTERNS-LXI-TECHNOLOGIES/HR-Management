package com.lxisoft.Appraisal.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "report_status")
public class reportStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Instant reportingTime;
	private String type;
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonBackReference
    private User user;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getReportingTime() {
		return reportingTime;
	}
	public void setReportingTime(Instant reportingTime) {
		this.reportingTime = reportingTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}

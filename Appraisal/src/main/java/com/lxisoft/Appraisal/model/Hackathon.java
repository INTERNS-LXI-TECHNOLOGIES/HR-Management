package com.lxisoft.Appraisal.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "hackathon_mark")
public class Hackathon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private Long hackathon;	
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
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Long gethackathon() {
		return hackathon;
	}
	public void setHackathon( Long hackathon)
	{
		this.hackathon = hackathon;
	}	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}

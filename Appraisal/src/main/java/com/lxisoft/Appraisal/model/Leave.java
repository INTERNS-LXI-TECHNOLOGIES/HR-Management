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
@Table(name = "Leaves")
public class Leave {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private String type;
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonBackReference
    private User user;
	public Leave()
	{
	}
	
	public Leave(Long id, LocalDate date, String type, User user) {
		super();
		this.id = id;
		this.date = date;
		this.type = type;
		this.user = user;
	}

	public Leave(LocalDate date, String type, User user) {
		super();
		this.date = date;
		this.type = type;
		this.user = user;
	}
	public Leave(LocalDate date, String type) {
		super();
		this.date = date;
		this.type = type;
	}
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
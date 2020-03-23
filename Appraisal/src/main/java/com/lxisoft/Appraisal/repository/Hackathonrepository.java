package com.lxisoft.Appraisal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.Hackathon;

public interface Hackathonrepository extends JpaRepository<Hackathon, Long>
{
	public	Optional<Hackathon> findByUserId(Long user);
	
}

package com.lxisoft.Appraisal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.Hackathon;
import java.util.List;
public interface Hackathonrepository extends JpaRepository<Hackathon, Long>
{
	public	List<Hackathon> findByUserId(Long user);
	
}

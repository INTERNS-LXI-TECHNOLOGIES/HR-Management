package com.lxisoft.Appraisal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long>
{
	public	List<Leave> findByUserId(Long user);
	//public List<Leave> findByDate(LocalDate date);

}
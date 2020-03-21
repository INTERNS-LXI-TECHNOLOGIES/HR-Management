package com.lxisoft.Appraisal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.reportStatus;

public interface reportRepository extends JpaRepository<reportStatus, Long> {
	
	public	List<reportStatus> findByUserId(Long user);
	
}

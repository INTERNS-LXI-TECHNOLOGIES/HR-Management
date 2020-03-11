package com.lxisoft.Appraisal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long>
{
	Optional<Leave> findByUserId(Long id);

}

package com.lxisoft.Appraisal.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lxisoft.Appraisal.model.Leave;

public interface LeaveRepository extends JpaRepository<Leave, Long>
{
	public	List<Leave> findByUserId(Long user);

}

package com.lxisoft.Appraisal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.LateArrival;
import com.lxisoft.Appraisal.model.Leave;

public interface LateRepository extends JpaRepository<LateArrival, Long>
{
	public	List<LateArrival> findByUserId(Long user);

}

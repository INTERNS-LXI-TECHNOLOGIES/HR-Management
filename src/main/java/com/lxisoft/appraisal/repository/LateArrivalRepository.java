package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.UserExtra;

import java.time.Instant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LateArrival entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LateArrivalRepository extends JpaRepository<LateArrival, Long> 
{
	List<LateArrival> findByUserExtraId(Long id);
	
	//List <LateArrival> findByUserExtraAndInstantBetween(UserExtra userEx, Instant dateTime,Instant  dateTime1);
	
	List<LateArrival> findAllByUserExtraId(Long id);


	List<LateArrival> findAll();
}

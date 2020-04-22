package com.lxisoft.appraisal.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.UserExtra;

import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.repository.LateArrivalRepository;
/**
 * Service Implementation for managing {@link LateArrival}.
 */
@Service
@Transactional
public class LateArrivalService 
{
	@Autowired
	LateArrivalRepository lateRepo;
	/**
	 * save lateArrival
	 * @param late
	 */
	public void setLate(LateArrival late)
	{
		lateRepo.save(late);
	}
	/**
	 * find lateArrival by UserExtra id
	 * @param id
	 * @return List: list of lateArrival
	 */
	public  List<LateArrival> findLate(Long id)
	{
		List<LateArrival> em=lateRepo.findByUserExtraId(id);		 
		 return em;

	}	
	/*
	 * public List<LateArrival> findLateOfUserBetween(UserExtra userEx, Instant
	 * dateTime, Instant dateTime1) {
	 * 
	 * return lateRepo.findByUserExtraAndInstantBetween(userEx, dateTime,
	 * dateTime1); }
	 */
	
	 public List<LateArrival> findAllLate(Long id)
	 {
		return lateRepo.findAllByUserExtraId(id);		 
	 }


	public List<LocalDate> getLocalDate(Instant em)
	{
		//yourInstant.atZone(yourZoneId).toLocalDate(); Will work with earlier versions for LocalDate...
		
		 ((ChronoLocalDateTime<LocalDate>) ZoneId.of("Asia/Kolkata")).toLocalDate();
		 List<LocalDate> Date=new ArrayList<LocalDate>();
	    return Date;
	}
	/**
	 * find all lateArrival
	 * @return List: list of lateArrival
	 */
	public List<LateArrival> findAll() {
		
		return lateRepo.findAll();
	}	

}

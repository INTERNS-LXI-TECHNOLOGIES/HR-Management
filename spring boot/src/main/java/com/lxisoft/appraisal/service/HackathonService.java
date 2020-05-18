package com.lxisoft.appraisal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.HackathonRepository;
/**
 * Service Implementation for managing {@link Hackathon}.
 */
@Service
@Transactional
public class HackathonService {

	@Autowired
	HackathonRepository hackRepo;
	/**
	 * save hackathon 
	 * @param hack1
	 */
	public void setHackathon(Hackathon hack1)
	{
		hackRepo.save(hack1);
	}
	/**
	 * find hackathon by UserExtraId
	 * @param userEx
	 * @return
	 */
	public  List<Hackathon> findHack(Long userEx)
	{
		List<Hackathon> hack =hackRepo.findByUserExtraId(userEx);		 
		return hack;
	}
	/**
	 * find hackathon of UserExtra between two date.
	 * @param userEx
	 * @param second
	 * @param first
	 * @return
	 */
	public List<Hackathon> findHackathonOfUserBetween(UserExtra userEx, LocalDate second, LocalDate first)
	{
		
		return hackRepo.findByUserExtraAndDateBetween(userEx, second, first);
	}
	/**
	 * find all hackathon By date.
	 * @param localDate
	 * @return
	 */
	public List<Hackathon> findByDate(LocalDate localDate)
	{
		
		return hackRepo.findAllByDate(localDate);
	}	


}

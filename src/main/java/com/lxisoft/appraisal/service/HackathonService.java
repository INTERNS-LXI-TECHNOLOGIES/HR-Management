package com.lxisoft.appraisal.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.HackathonRepository;


@Service
@Transactional
public class HackathonService {

	@Autowired
	HackathonRepository hackRepo;
	
	public void setHackathon(Hackathon hack1)
	{
		hackRepo.save(hack1);
	}

	public  List<Hackathon> findHack(Long userEx)
	{
		List<Hackathon> hack =hackRepo.findByUserExtraId(userEx);		 
		return hack;
	}

	public List<Hackathon> findHackathonOfUserBetween(UserExtra userEx, LocalDate second, LocalDate first)
	{
		
		return hackRepo.findByUserExtraAndDateBetween(userEx, second, first);
	}
	public List<Hackathon> findByDate(LocalDate localDate)
	{
		
		return hackRepo.findAllByDate(localDate);
	}	


}

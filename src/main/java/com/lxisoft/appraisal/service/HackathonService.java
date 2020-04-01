package com.lxisoft.appraisal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Hackathon;
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
	public  List<Hackathon> findHack(Long id)
	{
		List<Hackathon> hack =hackRepo.findByid(id);		 
		return hack;
	}
}

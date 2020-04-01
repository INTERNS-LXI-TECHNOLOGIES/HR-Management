package com.lxisoft.appraisal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.repository.HackathonRepository;


@Service
@Transactional
public class HackathonService {

	@Autowired
	HackathonRepository HackRepo;
	
	public void setHackathon(Hackathon hack1)
	{
		HackRepo.save(hack1);
	}
}

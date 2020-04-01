package com.lxisoft.appraisal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.repository.LateArrivalRepository;

@Service
@Transactional
public class LateArrivalService {
	@Autowired
	LateArrivalRepository lateRepo;
	
	public void setLate(LateArrival late)
	{
		lateRepo.save(late);
	}
	public  List<LateArrival> findLate(Long id)
	{

		List<LateArrival> em=lateRepo.findByUserId(id);
		 
		 return em;
	}

}

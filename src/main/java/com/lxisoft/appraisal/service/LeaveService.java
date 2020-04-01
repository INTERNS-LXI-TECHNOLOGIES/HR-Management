package com.lxisoft.appraisal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.repository.LeaveRepository;

@Service
@Transactional
public class LeaveService {
	
	@Autowired
	LeaveRepository leaveRepo;
	
	public List<Leave> getAllLeave() {
		 List<Leave> list=leaveRepo.findAll();
		return list;
	}
	public void setLeave(Leave leave)
	{
		leaveRepo.save(leave);
	}
	public  List<Leave> findLeave(Long id)
	{
		List<Leave> em=leaveRepo.findByUserId(id);

		 return em;
	}	

}

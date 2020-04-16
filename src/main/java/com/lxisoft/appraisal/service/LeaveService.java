package com.lxisoft.appraisal.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.UserExtra;
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
		List<Leave> em=leaveRepo.findByUserExtraId(id);

		 return em;
	}

	public List<Leave> findByDate(LocalDate localDate) {
		
		return leaveRepo.findAllByDate(localDate);
	}	
	public List<Leave> findLeavesOfUserBetween(UserExtra userEx, LocalDate second, LocalDate first)
	{
		
		return leaveRepo.findByUserExtraAndDateBetween(userEx, second, first);
	}
	
	

}

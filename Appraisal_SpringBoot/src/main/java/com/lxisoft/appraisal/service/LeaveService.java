package com.lxisoft.appraisal.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.LeaveRepository;
import com.lxisoft.appraisal.service.dto.LeaveDTO;
/**
 * Service Implementation for managing {@link Leave}.
 */
@Service
@Transactional
public class LeaveService {

	@Autowired
	LeaveRepository leaveRepo;
	/**
	 * find all leaves
	 * @return List: list of Leave
	 */
	public List<Leave> getAllLeave()
	{
		 List<Leave> list=leaveRepo.findAll();
		return list;
	}
	/**
	 * for saving leave
	 * @param leaveDTO
	 */
	public void setLeave(Leave leave)
	{   try{
                leaveRepo.save(leave);
                leaveRepo.flush();
             }
             catch(Exception e)
             {
                    e.printStackTrace();
             }
	}
	/**
	 * find leave by UserExtra id
	 * @param id
	 * @return List: list of Leave
	 */
	public  List<Leave> findLeave(Long id)
	{
		List<Leave> em=leaveRepo.findByUserExtraId(id);

		 return em;
	}
	/**
	 * find all leave by Date
	 * @param localDate
	 * @return List: list of Leave
	 */
	public List<Leave> findByDate(LocalDate localDate)
	{

		return leaveRepo.findAllByDate(localDate);
	}
	/**
	 * find leave of UserExtra between two date
	 * @param userEx
	 * @param second
	 * @param first
	 * @return List: list of Leave
	 */
	public List<Leave> findLeavesOfUserBetween(UserExtra userEx, LocalDate second, LocalDate first)
	{

		return leaveRepo.findByUserExtraAndDateBetween(userEx, second, first);
	}



}

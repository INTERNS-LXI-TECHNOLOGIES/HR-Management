package com.lxisoft.appraisal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.repository.AppraisalRepository;

/**
 * Service Implementation for managing {@link Appraisal}.
 */
@Service
@Transactional
public class AppraisalService {
	@Autowired
	AppraisalRepository appRepo;
	@Autowired 
	UserExtraService service;
	/**
	 * to get all appraisal details
	 * @return list of appraisal
	 */
	
	public List getAllDetails()
	{
		return appRepo.findAll();
	}
	public void setAppraisal(long id) 
	{
		Appraisal appraisal=new Appraisal();
		appraisal.setId(id);
		appraisal.setAttendance(service.getAttendance(id));
		appraisal.setCodeQuality(service.getCodeQuality(id));
		appraisal.setCompanyPolicy(service.getcompanyPolicy(id));
		appraisal.setMeetingTargets(service.getTargets(id));
		appraisal.setPunctuality(service.getPunctuality(id));
		appraisal.setDate(LocalDate.now());
		appraisal.setUserExtra(service.findExtraByid(id).get());
		appRepo.save(appraisal);
		
	}
	public Appraisal getOneAppraisal(long id)
	{
		return (appRepo.getOneByUserExtraId(service.findExtraByid(id).get())).get(0);
	}
	public void setAppraisal(Long id, String aStart, String aLast) {
		Appraisal appraisal=new Appraisal();
		appraisal.setId(id);
		appraisal.setAttendance(service.getAttendance(id));
		appraisal.setCodeQuality(service.getCodeQuality(id));
		appraisal.setCompanyPolicy(service.getcompanyPolicy(id));
		appraisal.setMeetingTargets(service.getTargets(id));
		appraisal.setPunctuality(service.getPunctuality(id));
		appraisal.setDate(LocalDate.now());
		appraisal.setUserExtra(service.findExtraByid(id).get());
		appRepo.save(appraisal);
		
		
	}
	

}

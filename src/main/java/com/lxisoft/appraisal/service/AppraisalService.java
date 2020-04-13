package com.lxisoft.appraisal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.repository.AppraisalRepository;

@Service
@Transactional
public class AppraisalService {
	@Autowired
	AppraisalRepository appRepo;
	List list=new ArrayList();
	public List getAllDetails()
	{
		return appRepo.findAll();
	}
	public void setDetails()
	{
		list=appRepo.findAll();
	}
	public List getDetails()
	{
		return list;
	}
	

}

package com.lxisoft.appraisal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.repository.ReportStatusRepository;

@Service
@Transactional
public class ReportStatusService {
	
	@Autowired
	ReportStatusRepository reportRepo;
	
	public void setReport(ReportStatus status)
	{
		reportRepo.save(status);
	}
	public List<ReportStatus> findReport(Long id)
	{

		List<ReportStatus> status=reportRepo.findByUserId(id);
		 
		 return status;
	}
}

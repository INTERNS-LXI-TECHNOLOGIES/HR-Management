package com.lxisoft.appraisal.service;

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

}

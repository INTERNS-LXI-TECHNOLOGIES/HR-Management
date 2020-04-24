package com.lxisoft.appraisal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.repository.ReportStatusRepository;
/**
 * Service Implementation for managing {@link ReportStatus}.
 */
@Service
@Transactional
public class ReportStatusService {
	
	@Autowired
	ReportStatusRepository reportRepo;
	/**
	 * for saving report status
	 * @param status
	 */
	public void setReport(ReportStatus status)
	{
		reportRepo.save(status);
	}
	/**
	 * find reportStatus by UserExtraId
	 * @param id
	 * @return List: List of reportStatus
	 */
	public List<ReportStatus> findReport(Long id)
	{

		List<ReportStatus> status=reportRepo.findByUserExtraId(id);
		 
		 return status;
	}
	 public List<ReportStatus> findAllReport(Long id)
	 {
		return reportRepo.findAllByUserExtraId(id);		 
	 }

}

package com.lxisoft.Appraisal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.reportStatus;

public interface reportRepository extends JpaRepository<reportStatus, Long> {

}

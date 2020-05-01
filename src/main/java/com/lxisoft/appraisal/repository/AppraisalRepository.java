package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Appraisal;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Appraisal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppraisalRepository extends JpaRepository<Appraisal, Long> {
}

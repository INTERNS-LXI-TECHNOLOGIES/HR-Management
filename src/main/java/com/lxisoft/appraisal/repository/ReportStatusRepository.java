package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.ReportStatus;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ReportStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportStatusRepository extends JpaRepository<ReportStatus, Long> {
	public	List<ReportStatus> findByUserId(Long user);
}

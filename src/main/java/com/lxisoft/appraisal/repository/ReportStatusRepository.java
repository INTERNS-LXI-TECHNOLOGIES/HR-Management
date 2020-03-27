package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.ReportStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ReportStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportStatusRepository extends JpaRepository<ReportStatus, Long> {

    @Query("select reportStatus from ReportStatus reportStatus where reportStatus.user.login = ?#{principal.username}")
    List<ReportStatus> findByUserIsCurrentUser();
}

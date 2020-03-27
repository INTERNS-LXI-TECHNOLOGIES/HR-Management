package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Leave;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Leave entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    @Query("select leave from Leave leave where leave.user.login = ?#{principal.username}")
    List<Leave> findByUserIsCurrentUser();
}

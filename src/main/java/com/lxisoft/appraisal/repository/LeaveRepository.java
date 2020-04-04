package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Leave;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Leave entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
	public	List<Leave> findByUserExtraId(Long user);
}

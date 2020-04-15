package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.UserExtra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Leave entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long>
{
	List<Leave> findByUserExtraId(Long id);
	@Query("SELECT u FROM Leave u WHERE u.userExtra = ?1 AND u.date<=?2 AND u.date>=?3")
	List <Leave> findLeavesOfUserBetween(UserExtra userEx, LocalDate second,LocalDate first);
	@Query("SELECT u FROM Leave u WHERE u.date=?1")
	
	

	List<Leave> findAllByDate(LocalDate localDate);
}

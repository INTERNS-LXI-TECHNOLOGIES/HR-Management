package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.UserExtra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Git entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GitRepository extends JpaRepository<Git, Long>
{
	List<Git> findByUserExtraId(Long userex);
	@Query("SELECT u FROM Git u WHERE u.userExtra = ?1 AND u.date<=?2 AND u.date>=?3")
	List <Git> findGitOfUserBetween(UserExtra userEx, LocalDate second,LocalDate first);
	@Query("SELECT u FROM Git u WHERE u.date=?1")
	
	public List<Git> findAllByDate(LocalDate localDate);

	
}

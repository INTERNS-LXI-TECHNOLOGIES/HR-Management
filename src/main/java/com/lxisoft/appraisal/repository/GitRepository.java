package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.UserExtra;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Git entity.
 */

@SuppressWarnings("unused")
@Repository
public interface GitRepository extends JpaRepository<Git, Long> {

	public	List<Git> findByUserExtraId(Long userEx);
	@Query("SELECT u FROM Git u WHERE u.userExtra = ?1 AND u.date<=?2 AND u.date>=?3")
	List <Git> findGitOfUserBetween(UserExtra userEx, LocalDate second,LocalDate first);
}

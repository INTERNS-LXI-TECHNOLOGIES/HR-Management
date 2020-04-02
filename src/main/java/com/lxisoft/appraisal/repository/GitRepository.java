package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.UserExtra;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Git entity.
 */

@Repository
public interface GitRepository extends JpaRepository<Git, Long> 
{
//	public	Set<Git> findByUserId(Long id);
	public	Set<Git> findByUserExtra(UserExtra userEx);
}

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

@SuppressWarnings("unused")
@Repository
public interface GitRepository extends JpaRepository<Git, Long> {

	public	List<Git> findByUserExtraId(Long userEx);
}

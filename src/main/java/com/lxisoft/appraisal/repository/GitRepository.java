package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Git;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Git entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GitRepository extends JpaRepository<Git, Long> {
}

package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Jira;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Jira entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JiraRepository extends JpaRepository<Jira, Long> {
}

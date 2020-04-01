package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Git;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Git entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GitRepository extends JpaRepository<Git, Long> {

    @Query("select git from Git git where git.user.login = ?#{principal.username}")
    List<Git> findByUserIsCurrentUser();
}

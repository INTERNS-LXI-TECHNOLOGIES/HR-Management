package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Hackathon;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Hackathon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {

    @Query("select hackathon from Hackathon hackathon where hackathon.user.login = ?#{principal.username}")
    List<Hackathon> findByUserIsCurrentUser();
}

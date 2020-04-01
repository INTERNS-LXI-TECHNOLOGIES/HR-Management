package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Hackathon;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Hackathon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
	public	List<Hackathon> findByUserId(Long user);
}

package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.UserExtra;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Data  repository for the Hackathon entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional
public interface HackathonRepository extends JpaRepository<Hackathon, Long> {
	List<Hackathon> findByUserExtraId(Long userEx);
	List<Hackathon> findByUserExtraAndDateBetween(UserExtra userExtra, LocalDate second, LocalDate first);
	List<Hackathon> findAllByDate(LocalDate localDate);
}

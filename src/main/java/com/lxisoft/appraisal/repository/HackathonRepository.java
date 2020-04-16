package com.lxisoft.appraisal.repository;

import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.UserExtra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Hackathon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HackathonRepository extends JpaRepository<Hackathon, Long>
{
	List<Hackathon> findByUserExtraId(Long userEx);

	List <Hackathon> findByUserExtraAndDateBetween(UserExtra userEx, LocalDate second,LocalDate first);
	
	public List<Hackathon> findAllByDate(LocalDate localDate);

}

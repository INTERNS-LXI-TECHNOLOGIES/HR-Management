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
	@Query("SELECT u FROM Hackathon u WHERE u.userExtra = ?1 AND u.date<=?2 AND u.date>=?3")
	List <Hackathon> findHackathonOfUserBetween(UserExtra userEx, LocalDate second,LocalDate first);
	@Query("SELECT u FROM Hackathon u WHERE u.date=?1")
	
	public List<Hackathon> findAllByDate(LocalDate localDate);

}

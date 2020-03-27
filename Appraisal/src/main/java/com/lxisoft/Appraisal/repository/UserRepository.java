package com.lxisoft.Appraisal.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lxisoft.Appraisal.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{	
	User findByUsername(String username);
	@Query(value = "SELECT u from User u inner join u.leave l  on l.user=u.id where l.date between :startdate AND :enddate")
	List<User> getLeavesFromUserBetween(@Param ("startdate") LocalDate date,@Param("enddate")LocalDate endDate);
	
	
}

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
	@Query(value = "SELECT * from User inner join leaves on leaves.user_id=user.id where leaves.date between ?1 AND ?2", nativeQuery = true)
	List<User> getLeavesFromUserBetween(String da,String te);
	
}

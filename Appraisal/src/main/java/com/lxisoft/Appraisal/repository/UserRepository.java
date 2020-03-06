package com.lxisoft.Appraisal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lxisoft.Appraisal.domain.Employee;
import com.lxisoft.Appraisal.model.User;

public interface UserRepository extends JpaRepository<User, Long>
{
	
	User findByUsername(String username);
	
	
}

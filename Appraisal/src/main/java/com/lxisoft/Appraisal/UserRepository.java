package com.lxisoft.Appraisal;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lxisoft.Appraisal.domain.Employee;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

	
}

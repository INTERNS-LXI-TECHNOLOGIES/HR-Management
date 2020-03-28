package com.lxisoft.Appraisal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.LateArrival;

public interface LateRepository extends JpaRepository<LateArrival, Long>
{
	public	List<LateArrival> findByUserId(Long user);

}

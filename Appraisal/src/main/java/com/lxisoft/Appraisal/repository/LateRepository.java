package com.lxisoft.Appraisal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.LateArrival;

public interface LateRepository extends JpaRepository<LateArrival, Long>
{

	Optional<LateArrival> findByUserId(Long id);

}

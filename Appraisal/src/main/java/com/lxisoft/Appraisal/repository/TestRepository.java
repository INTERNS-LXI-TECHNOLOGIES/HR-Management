package com.lxisoft.Appraisal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.EvaluationTest;

public interface TestRepository extends JpaRepository<EvaluationTest, Long>
{
	public	List<EvaluationTest> findByUserId(Long user);
}

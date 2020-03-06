package com.lxisoft.Appraisal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.lxisoft.Appraisal.domain.Employee;

@Repository


public interface MysqlRepo extends JpaRepository<Employee, Long>
{
	
	
}
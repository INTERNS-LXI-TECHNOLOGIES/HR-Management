package com.lxisoft.Appraisal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxisoft.Appraisal.domain.Employee;
import com.lxisoft.Appraisal.repository.MysqlRepo;

@Service
public class JPAService {

	@Autowired
	private MysqlRepo repo;
	
		public void addUser(Employee employee) 
		{
			repo.save(employee);
		}

		public List<Employee> getAllUsers() {
			 List<Employee> list=repo.findAll();
			return list;
		}
}

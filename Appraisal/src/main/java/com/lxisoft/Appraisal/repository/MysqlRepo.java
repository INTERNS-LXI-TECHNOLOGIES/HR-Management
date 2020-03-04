package com.lxisoft.Appraisal.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lxisoft.Appraisal.domain.Employee;

@Repository
@Transactional


public class MysqlRepo {
	@PersistenceContext
	private EntityManager em;


	public void addUser(Employee employee) 
	{
		
		em.persist(employee);
	}


	public  List<Employee> getAllUsers() {
		TypedQuery<Employee> query = em.createQuery( "SELECT g FROM Employee g ORDER BY g.id", Employee.class);
        return query.getResultList();
	}
	

}

package com.lxisoft.Appraisal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.Gitmark;

import java.util.List;

public interface Gitrepository extends JpaRepository<Gitmark, Long>
{
	public	List<Gitmark> findByUserId(Long user);
	
}

package com.lxisoft.Appraisal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lxisoft.Appraisal.model.Gitmark;

public interface Gitrepository extends JpaRepository<Gitmark, Long>
{
	public	Optional<Gitmark> findByUserId(Long user);
	
}

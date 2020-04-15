package com.lxisoft.appraisal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.GitRepository;

@Service
@Transactional
public class GitService {
	@Autowired
	GitRepository gitRepo;
	
	public void setGit(Git git)
	{
		gitRepo.save(git);
		
	}
	
	public  List<Git> findGit(Long userex)
	{
		List<Git> git =gitRepo.findByUserExtraId(userex);
		 
		 return git;
	}


	public List<Git> findGitOfUserBetween(UserExtra userEx, LocalDate second, LocalDate first)
	{
		
		return gitRepo.findGitOfUserBetween(userEx, second, first);
	}
	public List<Git> findByDate(LocalDate localDate)
	{
		
		return gitRepo.findAllByDate(localDate);
	}	

}

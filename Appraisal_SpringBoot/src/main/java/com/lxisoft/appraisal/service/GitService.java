package com.lxisoft.appraisal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.GitRepository;
/**
 * Service Implementation for managing {@link Git}.
 */
@Service
@Transactional
public class GitService {
	@Autowired
	GitRepository gitRepo;
	/**
	 * for saving git status 
	 * @param git
	 */
	public void setGit(Git git)
	{
		gitRepo.save(git);
		
	}
	/**
	 * find git by userExtraId
	 * @param userex
	 * @return List: list of git mark
	 */
	public  List<Git> findGit(Long userex)
	{
		List<Git> git =gitRepo.findByUserExtraId(userex);
		 
		 return git;
	}
	/**
	 * find git of UserExtra between two date
	 * @param userEx
	 * @param second
	 * @param first
	 * @return List: list of git mark
	 */
	public List<Git> findGitOfUserBetween(UserExtra userEx, LocalDate second, LocalDate first)
	{
		
		return gitRepo.findByUserExtraAndDateBetween(userEx, second, first);
	}
	/**
	 * find git by Date
	 * @param localDate
	 * @return List: list of git mark
	 */
	public List<Git> findByDate(LocalDate localDate)
	{
		
		return gitRepo.findAllByDate(localDate);
	}	

}

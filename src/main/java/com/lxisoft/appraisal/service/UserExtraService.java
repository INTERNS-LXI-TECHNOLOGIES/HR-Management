package com.lxisoft.appraisal.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.repository.UserRepository;

@Service
@Transactional
public class UserExtraService {
	
	@Autowired 
	UserRepository userRepository;
	@Autowired
	 UserExtraRepository userExtraRepository;
	

    private final Logger log = LoggerFactory.getLogger(UserExtraService.class);
    public void createUser(User user,UserExtra us)
    {
    	 userRepository.save(user);
         userExtraRepository.save(us);
    }
    public List<User> getAllUsers()
    {
    	List<User> list=userRepository.findAll();
    	return list;
    }
    public List<UserExtra> getAllExtraUsers()
    {
    	List<UserExtra> list=userExtraRepository.findAll();
    	return list;
    }
	public Optional<User> getUserByusername(String login) {
		
		return userRepository.findOneByLogin(login);
	}
	public void deleteUser(Long id) 
	{
		userExtraRepository.deleteById(id);
		userRepository.deleteById(id);
		
	}
	public ArrayList<UserExtra> findByCompany(String company) 
	{
		ArrayList<UserExtra> users=(ArrayList<UserExtra>)getAllExtraUsers();
		for(Iterator<UserExtra> u = users.iterator(); u.hasNext();)
		{
			UserExtra use= u.next();
			if(!use.getCompany().equalsIgnoreCase(company))
				u.remove(	);
		}
		return users;
	}
	public ArrayList<UserExtra> findByPosition(ArrayList<UserExtra> users, String position) 
	{
		for(Iterator<UserExtra> u = users.iterator(); u.hasNext();)
		{
			UserExtra use= u.next();
			if(!use.getPosition().equalsIgnoreCase(position))
				u.remove(	);
		}
		return users;
	}
}

package com.lxisoft.appraisal.service;

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

}

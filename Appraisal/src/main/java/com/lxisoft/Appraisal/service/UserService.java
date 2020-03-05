package com.lxisoft.Appraisal.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import  com.lxisoft.Appraisal.repository.UserRepository;
import com.lxisoft.Appraisal.config.UserPrincipal;


import com.lxisoft.Appraisal.model.User;
import com.lxisoft.Appraisal.model.Role;
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=repo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("user not found");
		
		return new UserPrincipal(user);
	}
	
	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }


		
	

}
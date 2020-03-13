package com.lxisoft.Appraisal.service;


import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import  com.lxisoft.Appraisal.repository.UserRepository;
import com.lxisoft.Appraisal.repository.LateRepository;
import  com.lxisoft.Appraisal.repository.LeaveRepository;
import com.lxisoft.Appraisal.config.UserPrincipal;


import com.lxisoft.Appraisal.model.User;
import com.lxisoft.Appraisal.model.Role;
import com.lxisoft.Appraisal.model.LateArrival;
import com.lxisoft.Appraisal.model.Leave;
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	@Autowired
	private LeaveRepository repos;
	@Autowired
	private LateRepository repol;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		
		User user=repo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("user not found");
		
		return new UserPrincipal(user);
	}
	
	private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) 
	{
        return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }
	

	public void addUser(User user) 
	{
		repo.save(user);
	}

	public List<User> getAllUsers() {
		 List<User> list=repo.findAll();
		return list;
	}

	public  Optional <User> findByid(Long id)
	{
		 Optional <User> em=repo.findById(id);
		 return em;
	}	
	public void setLeave(Leave leave)
	{
		repos.save(leave);
	}
	public void setLate(LateArrival late)
	{
		repol.save(late);
	}
	public  List<Leave> findLeave(Long id)
	{
		List<Leave> em=repos.findByUserId(id);
		 
		 return em;
	}	
	public  List<LateArrival> findLate(Long id)
	{
		List<LateArrival> em=repol.findByUserId(id);
		 
		 return em;
	}
//	public List<Leave> findLeaveByDate(LocalDate l)
//	{
//		List<Leave> lea=repos.findByDate(l);
//		return lea;
//	}
	public User getUserByusername(String username) {
		
		return repo.findByUsername(username);
	}

	public ArrayList<User> findByCompany(String company) {
		ArrayList<User> users=(ArrayList<User>) getAllUsers();
		for(Iterator<User> u = users.iterator(); u.hasNext();)
		{
			User use= u.next();
			if(!use.getCompany().equalsIgnoreCase(company))
				u.remove(	);
		}
		return users;
	}

	public void deleteUser(Long id) {
		repo.deleteById(id);
		
	}

	public void updateUser(@Valid User user) {
	long id=user.getId();
	
	repo.save(user);
	
		
	}
}

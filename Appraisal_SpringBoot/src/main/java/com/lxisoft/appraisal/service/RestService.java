package com.lxisoft.appraisal.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.lxisoft.appraisal.domain.Authority;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.service.dto.UserViewDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestService {
    @Autowired
    UserExtraService userService;

    public void addUser(UserViewDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setLogin(userDTO.getLogin());
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        user.setPassword(encode.encode(userDTO.getPassword()));

        UserExtra userEx = new UserExtra();
        userEx.setCompany(userDTO.getCompany());
        userEx.setPosition(userDTO.getPosition());
        userEx.setDob(LocalDate.parse(userDTO.getDob()));
        userEx.setJoiningDate(LocalDate.parse(userDTO.getJoiningDate()));
        userEx.setUser(user);

        Set<Authority> authorities = new HashSet<>();
		String auth="ROLE_ADMIN";
		authorities.add(new Authority(auth));

		user.setAuthorities(authorities);
            userService.createUser(user, userEx);

    }
    
    // public void setLeave(UserViewDTO userDTO)
    // {

    //     LeaveService.setLeave(leave);
    // }


}

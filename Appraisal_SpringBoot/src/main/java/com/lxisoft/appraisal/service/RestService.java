package com.lxisoft.appraisal.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.Multipart;

import com.lxisoft.appraisal.domain.Authority;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.service.dto.UserViewDTO;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestService {
    private final Logger log = LoggerFactory.getLogger(RestService.class);

    @Autowired
    UserExtraService userService;

    public boolean addUser(UserViewDTO userDTO) throws IOException {
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
        byte[] bytes=userDTO.getImage().getBytes();
        userEx.setImage(bytes);
        userEx.setImageContentType(userDTO.getImage().getContentType());

        Set<Authority> authorities = new HashSet<>();
		String auth=userDTO.getAuthorities();
		authorities.add(new Authority(auth));
        user.setAuthorities(authorities);

        // if(!userDTO.getImage().isEmpty())
        // {
        //     byte[] bytes = userDTO.getImage().getBytes();
        //     userEx.setImage(bytes);
        //     userEx.setImageContentType(userDTO.getImage().getContentType());

        // }

        List<User> users=userService.getAllUsers();
        boolean isUsed=false;
        for(User u:users)
        {
            if(u.getLogin().equalsIgnoreCase(user.getLogin()))
            isUsed=true;
        }
        if(!isUsed)
        userService.createUser(user, userEx);
        return isUsed;
    }

	public void editUser(UserViewDTO userDTO) throws IOException {
        User user = userService.findByid(userDTO.getId()).get();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        // user.setLogin(userDTO.getLogin());
        // BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        // user.setPassword(encode.encode(userDTO.getPassword()));

        UserExtra userEx = userService.findExtraByid(userDTO.getId()).get();
        userEx.setId(userDTO.getId());
        userEx.setCompany(userDTO.getCompany());
        userEx.setPosition(userDTO.getPosition());
        userEx.setDob(LocalDate.parse(userDTO.getDob()));
        userEx.setJoiningDate(LocalDate.parse(userDTO.getJoiningDate()));
        userEx.setUser(user);
        if(userDTO.getImage()!= null)
        {
            byte[] bytes=userDTO.getImage().getBytes();
            userEx.setImage(bytes);
            userEx.setImageContentType(userDTO.getImage().getContentType());
        }
        userService.createUser(user, userEx);

	}

    // public void setLeave(UserViewDTO userDTO)
    // {

    //     LeaveService.setLeave(leave);
    // }


}

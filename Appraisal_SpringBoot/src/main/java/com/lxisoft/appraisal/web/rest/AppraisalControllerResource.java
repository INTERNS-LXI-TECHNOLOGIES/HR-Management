package com.lxisoft.appraisal.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.lxisoft.appraisal.service.dto.UserDTO;
import java.util.*;
/**
 * AppraisalControllerResource controller
 */
@RestController
@RequestMapping("/api/appraisal-controller-resource")
public class AppraisalControllerResource {
	@Autowired
	UserResource userRes;

    private final Logger log = LoggerFactory.getLogger(AppraisalControllerResource.class);


    @RequestMapping(value="/")
   	public String index()
   	{
       	return "value";
   	}

    /**
    * GET defaultAction
    */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser()
    {
    	Pageable pageable=null;
    	return userRes.getAllUsers(pageable);
    }
    @PostMapping("/addUser")
    public ResponseEntity<List<UserDTO>> addUser(UserDTO ob)
    {
        Pageable pageable=null;
        log.info("getn value from server----------"+ob.getEmail());
        log.info("...........................");
    	return userRes.getAllUsers(pageable);
    }




}

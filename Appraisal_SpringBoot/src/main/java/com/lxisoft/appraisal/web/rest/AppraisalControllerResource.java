package com.lxisoft.appraisal.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.config.Constants;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.service.UserService;
import com.lxisoft.appraisal.service.dto.UserDTO;
import com.lxisoft.appraisal.service.dto.UserExtraDTO;

import io.github.jhipster.web.util.ResponseUtil;

import java.util.*;
/**
 * AppraisalControllerResource controller
 */
@RestController
@RequestMapping("/api/appraisal-controller-resource")
public class AppraisalControllerResource {
	@Autowired
	UserResource userRes;
	@Autowired
	UserService userService;
	@Autowired
	UserExtraRepository userExtraRepository;

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
    @GetMapping("/user-extras/{user}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserExtraDTO> getUserExtra(@PathVariable User us) {
    	log.debug("REST request to get User : {}", us.getLogin());
    	Optional<User> user=userService.getUserWithAuthoritiesByLogin(us.getLogin());
        log.debug("REST request to get UserExtra : {}", us.getId());
        Optional<UserExtra> userExtra = userExtraRepository.findById(us.getId());
        UserExtraDTO u=new UserExtraDTO(user.get(),userExtra.get());
        Optional<UserExtraDTO> dto=Optional.of(u);
        return ResponseUtil.wrapOrNotFound(dto);
    }
   
	

}

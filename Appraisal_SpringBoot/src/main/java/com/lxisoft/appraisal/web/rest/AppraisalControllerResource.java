package com.lxisoft.appraisal.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.config.Constants;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.User;

import com.lxisoft.appraisal.service.RestService;

import com.lxisoft.appraisal.service.UserExtraService;

import com.lxisoft.appraisal.service.UserService;
import com.lxisoft.appraisal.service.dto.LeaveDTO;
import com.lxisoft.appraisal.service.dto.UserDTO;
import com.lxisoft.appraisal.service.dto.UserExtraDTO;
import com.lxisoft.appraisal.service.dto.UserViewDTO;

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
    @Autowired
    RestService restService;
    @Autowired
	UserExtraService userexService;
    @Autowired
    LeaveResource leaveRes;

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
    	final Pageable pageable = null;
        return userRes.getAllUsers(pageable);
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody UserViewDTO userDTO) {
        boolean isUsed = false;

        log.info("getn value from server----------");
        isUsed = restService.addUser(userDTO);

        return isUsed;
    }

    @PostMapping("/setLeave")
    public boolean leaves(@RequestBody  Leave leave) {
        boolean isUsed = false;
        log.info("getn value from server----------");
       isUsed= restService.setLeave(leave);
        return isUsed;
    }

    @GetMapping("/user-extras/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserExtraDTO> getUserExtra(@PathVariable Long id) {
    	Optional <User> user = userexService.findByid(id);
        log.debug("REST request to get UserExtra : {}", id);
        final Optional<UserExtra> userExtra = userExtraRepository.findById(id);
        log.debug("REST  get UserExtra : {}", userExtra);
        UserExtra us=userExtra.get();
        String image=(Base64.getEncoder().encodeToString(us.getImage()));
        us.setImageContentType(Base64.getEncoder().encodeToString(us.getImage()));
        log.info("imageeee:::::::"+image);
        UserExtraDTO u=new UserExtraDTO(user.get(),userExtra.get());
        Optional<UserExtraDTO> dto=Optional.of(u);
        return ResponseUtil.wrapOrNotFound(dto);
    }

    @PostMapping("/setLeave")
    public ResponseEntity<List<UserDTO>> leaves(@RequestBody UserViewDTO userDTO)
    {
        Pageable pageable=null;
        log.info("getn value from server----------");
       // restService.setLeave(userDTO);

    	return userRes.getAllUsers(pageable);
    }
//    @GetMapping("/image/{id}")
//    public String getImage(@PathVariable Long id)
//    {
//    	Pageable pageable=null;
//    	Optional<UserExtra> userExtra = userExtraRepository.findById(id);
//    	String image=Base64.getEncoder().encodeToString(userExtra.get().getImage());
//    	return image;
//    }


}

package com.lxisoft.appraisal.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.config.Constants;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.service.AppraisalService;
import com.lxisoft.appraisal.service.GitService;
import com.lxisoft.appraisal.service.HackathonService;
import com.lxisoft.appraisal.service.LateArrivalService;
import com.lxisoft.appraisal.service.LeaveService;
import com.lxisoft.appraisal.service.ReportStatusService;
import com.lxisoft.appraisal.service.RestService;
import com.lxisoft.appraisal.service.UserExtraService;
import com.lxisoft.appraisal.service.UserService;
import com.lxisoft.appraisal.service.dto.UserDTO;
import com.lxisoft.appraisal.service.dto.UserExtraDTO;
import com.lxisoft.appraisal.service.dto.UserViewDTO;

import io.github.jhipster.web.util.ResponseUtil;
import java.util.stream.Collectors;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.io.IOException;
import javax.ws.rs.Consumes;
import java.util.Base64;


/**
 * AppraisalControllerResource controller
 */
@RestController
@RequestMapping("/api/appraisal-controller-resource")
@CrossOrigin("*")
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
    AppraisalService appraisalService;


    private final Logger log = LoggerFactory.getLogger(AppraisalControllerResource.class);
    /**
     * to get all user info.
     * @return - list of user
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        Pageable pageable = null;
        return userRes.getAllUsers(pageable);
    }
    /**
     * for adding user
     * @param userDTO
     * @return
     */
    @Consumes("multipart/form-data")
    @PostMapping("/addUser")
    public boolean addUser(@ModelAttribute UserViewDTO userDTO) {
        boolean isUsed = false;
        log.info("get value from server ----------:{}", userDTO.getEmail());
        log.info("get file from server ----------:{}", userDTO.getImage().getContentType());
        try {
            isUsed = restService.addUser(userDTO);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    	return isUsed;
    }
     /**
     * to get single user details
     * @param id
     * @return
     */
    @GetMapping("/user-extras/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserExtraDTO> getUserExtra(@PathVariable Long id)
    {
        Optional <User> user = userexService.findByid(id);
        log.debug("REST request to get UserExtra : {}", id);
        Optional<UserExtra> userExtra = userExtraRepository.findById(id);
        log.debug("REST  get UserExtra : {}", userExtra);
        UserExtraDTO u=new UserExtraDTO(user.get(),userExtra.get());
        Optional<UserExtraDTO> dto=Optional.of(u);

        appraisalService.setAppraisal(id);
        return ResponseUtil.wrapOrNotFound(dto);
    }
    @GetMapping("/image/{id}")
    public String getUserImage(@PathVariable Long id)
    {
    	Optional<UserExtra> userExtra = userExtraRepository.findById(id);
        log.debug("REST  get UserExtra : {}", userExtra);
        byte[] encoded = Base64.getEncoder().encode(userExtra.get().getImage());
        return (new String(encoded));
    }
    /**
     * for setting leave
     * @param userDTO
     * @return
     */
    @PostMapping("/setLeave")
    public ResponseEntity<List<UserDTO>> leaves(@RequestBody UserViewDTO userDTO)
    {
        Pageable pageable=null;
        log.info("getn value from server----------");
//        restService.setLeave(userDTO);
    	return userRes.getAllUsers(pageable);
    }
    /**
     * to get user full work status
     * @param id
     * @return - full status of user
     */
    @GetMapping("/status/{id}")
    public List<Integer> getWorkStatus(@PathVariable Long id)
    {
    	 List<Integer> status=restService.getUserStatus(id);
    	 return status;
    }
    /**
     * to edit user
     * @param userView
     * @return
     */
    @Consumes("multipart/form-data")
    @PostMapping("/editUser")
    public boolean editUser(@ModelAttribute UserViewDTO userView)
    {
        log.info("get model from server ----------:{}", userView);
        try {
            restService.editUser(userView);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
    @GetMapping("/appraisal/{id}")
    public Appraisal getAppraisal(@PathVariable Long id)
    {
        log.info("get id from server ----------:{}", id);

        Appraisal details=appraisalService.getOneAppraisal(id);
    	 return details;
    }


}

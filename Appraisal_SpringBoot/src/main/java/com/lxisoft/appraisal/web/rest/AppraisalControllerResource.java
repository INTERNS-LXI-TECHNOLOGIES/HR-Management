package com.lxisoft.appraisal.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.config.Constants;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.repository.UserExtraRepository;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.domain.User;
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
	LeaveService leaveSer;
	@Autowired
	LateArrivalService lateServ;
	@Autowired
	ReportStatusService reportServ;
	@Autowired
	GitService gitServ;
	@Autowired
	HackathonService hackServ;


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
    public  boolean addUser(@RequestBody UserViewDTO userDTO)
    {
        boolean isUsed=false;

        log.info("getn value from server "+userDTO.getImage()+"----------:{}",userDTO);
        isUsed= restService.addUser(userDTO);

    	return isUsed;
    }
    @GetMapping("/user-extras/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserExtraDTO> getUserExtra(@PathVariable Long id) {
    	Optional <User> user = userexService.findByid(id);
        log.debug("REST request to get UserExtra : {}", id);
        Optional<UserExtra> userExtra = userExtraRepository.findById(id);
        log.debug("REST  get UserExtra : {}", userExtra);
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
    /**
     * for getting leaves and late arrival status
     * @param id- id of user
     * @return 
     */
    @GetMapping("/status/{id}")
    public List<Integer> getUserStatus(@PathVariable Long id)
    {
    	Optional <User> user = userexService.findByid(id);
        log.debug("REST request to get UserExtra for status : {}", id);
        List<Leave> leave = leaveSer.findLeave(id);
        List<Integer> number=new ArrayList<Integer>();
		 List<LateArrival> late =lateServ.findLate(id);
		 List<Leave> auth=new ArrayList<Leave>();
		 List<Leave> unauth=new ArrayList<Leave>();
		 for(int i=0;i<leave.size();i++)
		 {
			 if(leave.get(i).getType().equals("Authorized"))
			 {
				 auth.add(leave.get(i));
			 }
			 if(leave.get(i).getType().equals("NonAuthorized"))
			 {
				 unauth.add(leave.get(i));
			 }
		 }
		 number.add(auth.size());
		 number.add(unauth.size());
		 List<LateArrival> a=new ArrayList<LateArrival>();
		 List<LateArrival> un=new ArrayList<LateArrival>();
		 for(int i=0;i<late.size();i++)
		 {
			 if(late.get(i).getType().equals("Authorized"))
			 {
				 a.add(late.get(i));
			 }
			 if(late.get(i).getType().equals("NonAuthorized"))
			 {
				 un.add(late.get(i));
			 }
		 }
		 number.add(a.size());
		 number.add(un.size());	 
		 List<Integer> num=getUserWorkingStatus(id,auth,unauth,number);
		 List<Integer> value=getMarks(id,num);
        return value;
    }

    public List<Integer> getUserWorkingStatus(Long id,List<Leave> auth,List<Leave> unauth,List<Integer> number)
    {
    	Optional <UserExtra> userEx = userexService.findExtraByid(id);
    	 List<ReportStatus> status=reportServ.findReport(id);
		 List<ReportStatus> unreportdays=new ArrayList<ReportStatus>();
		 for(int i=0;i<status.size();i++)
		 {
			unreportdays.add(status.get(i));
		 }
		 LocalDate first=userEx.get().getJoiningDate();
		 LocalDate second= LocalDate.now();
		 long workingDays= ChronoUnit.DAYS.between(first,second);
		 int workingHour=(int)(workingDays*7);
		 int leaveSize=((auth.size())+(unauth.size()));
		 int absence=leaveSize*7;
		 int workedHour=(workingHour-absence);
		 int workedDays=((int)workingDays)-leaveSize;
		 number.add((int)workingDays);
		 number.add(workedDays);
		 number.add(workingHour);
		 number.add(workedHour);
		 number.add(leaveSize);
		 number.add(unreportdays.size());
		 return number;
    }
    public List<Integer> getMarks(Long id,List<Integer> num)
    {
    	 List<Git> git=gitServ.findGit(userexService.findExtraByid(id).get().getId());
		 List<Hackathon> hack=hackServ.findHack(userexService.findExtraByid(id).get().getId());
		 if(git.size()!=0) 
		 {
			Iterator it=git.iterator();
			while (it.hasNext())
			{
				Git object = (Git)it.next();
				long gits= object.getMark();
				num.add((int)gits);
			 }	
		}	
		else { num.add(git.size());}
		if(hack.size()!=0)
		{
			Iterator i=hack.iterator();
			while (i.hasNext())
			{
				Hackathon object = (Hackathon)i.next();
				long hacks= object.getMark();
				num.add((int)hacks);
			 }	
		}
		else { num.add(git.size());}
		return num;
		
    }


}

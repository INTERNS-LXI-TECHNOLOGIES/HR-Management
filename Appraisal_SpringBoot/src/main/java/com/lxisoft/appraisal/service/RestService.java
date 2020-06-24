package com.lxisoft.appraisal.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.Multipart;

import com.lxisoft.appraisal.domain.Authority;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.domain.ReportStatus;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Transactional
public class RestService {
    private final Logger log = LoggerFactory.getLogger(RestService.class);

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

    public boolean addUser(UserViewDTO userDTO) throws IOException
    {
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

        List<User> users=userexService.getAllUsers();
        boolean isUsed=false;
        for(User u:users)
        {
            if(u.getLogin().equalsIgnoreCase(user.getLogin()))
            isUsed=true;
        }
        if(!isUsed)
        userexService.createUser(user, userEx);
        return isUsed;
    }

	public void editUser(UserViewDTO userDTO) throws IOException {
        User user = userexService.findByid(userDTO.getId()).get();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        // user.setLogin(userDTO.getLogin());
        // BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        // user.setPassword(encode.encode(userDTO.getPassword()));

        UserExtra userEx = userexService.findExtraByid(userDTO.getId()).get();
        userEx.setId(userDTO.getId());
        userEx.setCompany(userDTO.getCompany());
        userEx.setPosition(userDTO.getPosition());
        userEx.setDob(LocalDate.parse(userDTO.getDob()));
        userEx.setJoiningDate(LocalDate.parse(userDTO.getJoiningDate()));
        userEx.setUser(user);
        if(userDTO.getImage()!= null)
        {
            // log.info("get image from server ----------:{}", userDTO.getImage());
            byte[] bytes=userDTO.getImage().getBytes();
            userEx.setImage(bytes);
            userEx.setImageContentType(userDTO.getImage().getContentType());
        }
        userexService.createUser(user, userEx);

	}
	/**
     * for getting leaves and late arrival status
     * @param id- id of user
     * @return full status of user
     */
    public List<Integer> getUserStatus(Long id)
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

    public List<Integer> getBydate(Long id,LocalDate first, LocalDate second)
    {
        Optional <User> user = userexService.findByid(id);
        Optional <UserExtra> userEx = userexService. findExtraByid(id);
        log.debug("REST request to get UserExtra for status : {}", id);
        List<Leave> leave = leaveSer.findLeavesOfUserBetween(userEx.get(),first,second);
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
		 List<Integer> value=getMarks(id,num,first,second);
        return value;
    }

    /**
     * to get work profile of user
     * @param id  - id of user
     * @param auth  - authorized leaves list
     * @param unauth - un authorized leaves list
     * @param number - list to store workin status
     * @return - list of work status
     */
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
    /**
     * to get git and hackathon mark
     * @param id  - id of user
     * @param num - list to store remaining work status
     * @return - list of work status.
     */
    public List<Integer> getMarks(Long id,List<Integer> num,LocalDate first, LocalDate second)
    {
         List<Git> git=gitServ.findGitOfUserBetween(userexService.findExtraByid(id).get(),first,second);

		 List<Hackathon> hack=hackServ.findHackathonOfUserBetween(userexService.findExtraByid(id).get(),first,second);
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
    public String getComment(long val)
	{
		String co=null;
		int v=(int)val;
		switch(v)
		{
			case 0: co="Very Poor"; break;
			case 1: co="Poor"; break;
			case 2: co="Below Average"; break;
			case 3: co="Average"; break;
			case 4: co="Good"; break;
			case 5: co="Excellent"; break;

		}
		return co;
	}

}

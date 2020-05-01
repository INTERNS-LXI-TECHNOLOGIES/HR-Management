package com.lxisoft.appraisal.web;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

import org.apache.logging.log4j.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.Authority;
import com.lxisoft.appraisal.domain.Git;
import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.LateArrival;
import com.lxisoft.appraisal.domain.ReportStatus;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.domain.Leave;
import com.lxisoft.appraisal.repository.AuthorityRepository;
import com.lxisoft.appraisal.service.AppraisalService;
import com.lxisoft.appraisal.service.GitService;
import com.lxisoft.appraisal.service.HackathonService;
import com.lxisoft.appraisal.service.InvalidPasswordException;
import com.lxisoft.appraisal.service.JasperService;
import com.lxisoft.appraisal.service.LateArrivalService;
import com.lxisoft.appraisal.service.LeaveService;
import com.lxisoft.appraisal.service.ReportStatusService;
import com.lxisoft.appraisal.service.UserDataBeanService;
import com.lxisoft.appraisal.service.UserExtraService;
import com.lxisoft.appraisal.service.dto.UserExtraDTO;

import net.sf.jasperreports.engine.JRException;
/**
 * ControllerResource controller
 */

@Controller
public class ControllerResource {
	private static final Object Invalid = null;
	public List<UserDataBean> reportList;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserExtraService userService;
	@Autowired
	LeaveService leaveSer;
	@Autowired
	LateArrivalService lateServ;
	@Autowired
	GitService gitServ;
	@Autowired
	HackathonService hackServ;
	@Autowired
	ReportStatusService reportServ;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	JasperService jasperService;
	@Autowired
	AppraisalService appraisalService;
	@Autowired
	UserDataBeanService userDataBeanService;

    private final Logger log = LoggerFactory.getLogger(ControllerResource.class);
    /**
     * redirecting based on user or admin
     * @return
     */
    @RequestMapping(value="/")
	public ModelAndView index(@RequestParam(name="userAdded",required=false )boolean success)
	{
    	ModelAndView mv=new ModelAndView(); 
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	log.info("authentication detail"+authentication);
		boolean isAdmin=authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		boolean isUser=authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		String username = authentication.getName();
		log.info("username:////////////////////"+username+ " is admin: "+ isAdmin+" user: "+isUser);
		if(success)mv.addObject("userAdded",true);
		if(isAdmin)
		{
			mv.addObject("username",username);
			mv.setViewName("redirect:/viewuser");
			return mv;
		}
		else if(isUser)
		{
			Optional<User> user=userService.getUserByusername(username);
			mv.addObject("id",user.get().getId());
			mv.setViewName("redirect:/userDetails");
			return mv;
		}
		else 
		{
			mv.setViewName("/login");
			return mv;
		}

	}
    /**
     * login page
     * @return
     */
    @RequestMapping(value= "/login")
    public String login() {
    	
        return "login";
    }
    /**
     * view all user page.
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/viewuser")
	public ModelAndView viewUsers(@RequestParam(name="userAdded",required=false )boolean success)
	{
    	ModelAndView mv= new ModelAndView("viewAllUser");
    	try {
			List<User> users = null;
			List<UserExtra> userEx = null;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			
			if(success)mv.addObject("success",true);
			Optional<User> user=userService.getUserByusername(username);
			
			Optional<UserExtra> u=userService.findExtraByid(user.get().getId());
			if(u.get().getCompany().equalsIgnoreCase("Lxisoft"))
			{
				users=(ArrayList<User>) userService.getAllUsers();
				userEx=userService.getAllExtraUsers();
			}
			else
			{
				userEx=userService.findByCompany(u.get().getCompany());
				users=(ArrayList<User>) userService.getUsersFromUserExtra(userEx);
			}
			
			for (UserExtra us:userEx) 
			{
				us.setImageContentType(Base64.getEncoder().encodeToString(us.getImage()));
			}
			List <UserExtraDTO> dto=getAllUser(users,userEx);
			mv.addObject("list",dto);
		}
		catch (Exception e)
		{
			mv.setViewName("redirect:/logout-success");
		}
		return mv;
	}
    /**
     * getting single userdetails.
     * @param id
     * @param model
     * @param aStart
     * @param aLast
     * @return
     */
    @RequestMapping("/userDetails") 
	 public ModelAndView userDetail(@RequestParam(name="id") Long id,ModelAndView model, 
			 @RequestParam (name="start" ,required=false)String aStart,@RequestParam (name="end", required=false)String aLast,
	 		 @RequestParam(name="success",required=false )boolean success,@RequestParam(name="mismatch",required=false )boolean mismatch,
	 	@RequestParam(name="samePassword",required=false )boolean samePassword,@RequestParam(name="shortPassword",required=false )boolean shortPassword,
	 	@RequestParam(name="passwordChanged",required=false )boolean passwordChanged,
	 	@RequestParam(name="randomApp",required=false )boolean randomApp)
	 {
		 ModelAndView mv= new ModelAndView("userDetail"); 
		 Optional <User> user = userService.findByid(id);
		 Optional <UserExtra> userEx = userService.findExtraByid(id);
		 List<Leave> leave = leaveSer.findLeave(id);
		 List<LateArrival> late =lateServ.findLate(id);
		 List<LocalDateTime> time=new ArrayList<LocalDateTime>();
		 for(int i=0;i<late.size();i++)
		 {
			 Instant in=late.get(i).getReachedTime();
			 LocalDateTime t= LocalDateTime.ofInstant(in,ZoneId.systemDefault());
			 time.add(t);
		 }
		 UserExtraDTO dto=getUser(user.get(),userEx.get());
		 mv.addObject("employee",dto);
		 LocalDate first=userEx.get().getJoiningDate();
		 LocalDate second= LocalDate.now();
		 long days= ChronoUnit.DAYS.between(first,second);
		 long total=(days*7);
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
		 if(!userEx.get().getImageContentType().isEmpty())
		 {
			 String image=Base64.getEncoder().encodeToString(userEx.get().getImage());
			 mv.addObject("image",image);
		 }		
		 int l=((auth.size())+(unauth.size()));
		 long absence=l*7;
		 long workedHour=(total-absence);
		 
		 List<ReportStatus> status=reportServ.findReport(id);
		 List<ReportStatus> unreportdays=new ArrayList<ReportStatus>();
		 for(int i=0;i<status.size();i++)
		 {
			unreportdays.add(status.get(i));
		 }

		 List<Git> git=gitServ.findGit(userService.findExtraByid(id).get().getId());
		 List<Hackathon> hack=hackServ.findHack(userService.findExtraByid(id).get().getId());
		 if(git.size()!=0) 
		 {
			Iterator it=git.iterator();
			while (it.hasNext())
			{
				Git object = (Git)it.next();
				Long mar= object.getMark();
				 mv.addObject("git",mar);
			 }	
		}		
		if(hack.size()!=0)
		{
			Iterator i=hack.iterator();
			while (i.hasNext())
			{
				Hackathon object = (Hackathon)i.next();
				Long mark = object.getMark();
				mv.addObject("hack",mark);
			 }	
		}	
		if(!randomApp) {appraisalService.setAppraisal(id);}
		else {}

		Appraisal appraisal=appraisalService.getOneAppraisal(id);
		 mv.addObject("appraisal",appraisal);
		
		 mv.addObject("auth",auth);
		 mv.addObject("unauth",unauth);
		 mv.addObject("a",a);
		 mv.addObject("un",un);
		 mv.addObject("time",time);
		 mv.addObject("day",days);
		 mv.addObject("total",total);
		 mv.addObject("workedHour",workedHour);
		 mv.addObject("unreportdays",unreportdays);
		 
		 if(success)mv.addObject("success",true);
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 boolean isAdmin=authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		 boolean isUser=authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		 if(isAdmin)mv.addObject("isAdmin",true);
		 if(isUser)mv.addObject("isUser",true);
		 if(mismatch)mv.addObject("mismatch",true);
		 if(shortPassword)mv.addObject("shortPassword",true);
		 if(samePassword)mv.addObject("samePassword",true);

		 if(passwordChanged)mv.addObject("passwordChange",true);
		 		 
		 Set<Authority> authorities=user.get().getAuthorities();
		 Iterator<Authority> it=authorities.iterator();
		
		
		 while(it.hasNext())
		 {
			 Authority au=(Authority) it.next();
		
			 if(au.toString().equalsIgnoreCase("ROLE_ADMIN"))
			 {
			 	 mv.addObject("userIsAdmin",true);
			 }
			 else mv.addObject("userIsUser",true);
		 }
		 mv.addObject("lastAction", "entity has been created/updated successfully");
		 return mv ;  
	 }
   /**
    * get a list of UserExtraDTO from list of user and userExtra.
    * @param users
    * @param userextra
    * @return
    */
    public List<UserExtraDTO> getAllUser(List<User> users,List<UserExtra> userextra)
    {
    	List <UserExtraDTO> dto=new ArrayList<UserExtraDTO>();
		for(int i=0;i<users.size();i++)
		{
			for(int j=0;j<userextra.size();j++)
			{
				if(i==j)
				{
					UserExtraDTO u=new UserExtraDTO(users.get(i),userextra.get(j));
					dto.add(u);
				}
			}
		}
		return dto;
    }
    /**
     * getting userExtraDTO from user and userExtra.
     * @param user
     * @param ex
     * @return
     */
    public UserExtraDTO getUser(User user,UserExtra ex)
    {
    	UserExtraDTO u=new UserExtraDTO(user,ex);
    	return u;
    }
    /**
     * redirect to add user page
     * @param model
     * @return
     */
    @GetMapping(value= "/add")
    public String add(Model model) {
    	
    	model.addAttribute("user",new User());
    	model.addAttribute("success", true);
    	    
    	return "addUser";
    }
    /**
     * for adding user
     * @param user
     * @param bindingResult
     * @param request
     * @param file
     * @param re
     * @return
     */
    @PostMapping("/addU")
	public ModelAndView addUser(@Valid @ModelAttribute  User user,BindingResult bindingResult, HttpServletRequest request,
			@RequestParam (name="picture") MultipartFile file, RedirectAttributes re)
	{
		ModelAndView mv;
		UserExtra us=new UserExtra();
		
		if (bindingResult.hasErrors()) 
		{
			mv=new ModelAndView("addUser");
			mv.addObject("error",true);
			
			if(file.isEmpty())
			{
				re.addFlashAttribute("message","select a file to upload");
			}
		}	
		try
		{
			byte[] bytes=file.getBytes();
			us.setImage(bytes);
			us.setImageContentType(file.getContentType());
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}		
		Set<Authority> authorities = new HashSet<>();
		String auth=request.getParameter("authority");
		authorities.add(new Authority(auth));
				
		user.setAuthorities(authorities);
		BCryptPasswordEncoder encode=new BCryptPasswordEncoder(); 
		user.setPassword(encode.encode(user.getPassword()));
			
		us.setCompany(request.getParameter("company"));
		us.setPosition(request.getParameter("position"));
		us.setJoiningDate(LocalDate.parse(request.getParameter("joinDate")));
		us.setDob(LocalDate.parse(request.getParameter("dob")));
		us.setUser(user);
		
		mv=new ModelAndView("redirect:/");
		try{
			userService.createUser(user,us);
			mv.addObject("userAdded",true);
			
		}catch(Exception e)
		{
			mv=new ModelAndView("addUser");
			mv.addObject("error",true);
//			mv.addObject("message",e.getLocalizedMessage());
			
		}
		return mv;
	}
    /**
     * for logout 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logout-success")

	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/login";  
     } 
    /**
     * getting search result
     * @param firstName
     * @return list of user
     */
	@RequestMapping(value = "/statusform",method = RequestMethod.GET)
	public @ResponseBody List<User> getName(@RequestParam("firstName") String firstName)
	{

		return simulateSearchResult(firstName);

	}
	/**
	 * searching user based on first name, used in autocomplete implementation.
	 * @param firstName
	 * @return list of user
	 */
	private List<User> simulateSearchResult(String firstName)
	{

		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<User> result = new ArrayList<User>();
		for (User u : user) {
			if (u.getFirstName().contains(firstName)) {
				result.add(u);
			}
		}

		return result;
	}
	/**
	 * getting UserExtraDTO based on userExtra.
	 * @param list
	 * @return
	 */
	public List<UserExtraDTO> getSpecificUser(Set<UserExtra> list)	
	{
		List<UserExtraDTO> users=new ArrayList<UserExtraDTO>();
		
		Iterator<UserExtra> it=list.iterator();
		while(it.hasNext())
		{
			UserExtra userEx=it.next();
			Optional<User> user=userService.findByid(userEx.getId());
			users.add(new UserExtraDTO(user.get(),userEx));
		}
		
		
		
		return users;
	}
	/**
	 * coverting date to localdate
	 * @param dateToConvert
	 * @return
	 */
	public LocalDate ToLocalDate(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	/**
	 * view leave page with listing leave status on the corresponding date
	 * @return
	 */
	@RequestMapping("/leave")
	public ModelAndView Leave(Long id,String msg)
	{
		Set<UserExtra> list=new HashSet<UserExtra>();
		LocalDate localDate = LocalDate.now();
		List<Leave> l=leaveSer.findByDate(localDate);
		
		for(Leave u:l)
		{
				list.add(u.getUserExtra());
		}
		ModelAndView mv= new ModelAndView("Leave");
		List<UserExtraDTO> dto=getSpecificUser(list);
		mv.addObject("leavelist",dto);	
		return mv;
		
	}
	/**
	 * setting leave status and redirect to same page
	 * @param name
	 * @param subject
	 * @return
	 */
	@RequestMapping("/setLeave")
	public ModelAndView setLeave(@RequestParam String name,@RequestParam (name="subject",required=false, defaultValue="NonAuthorized")String subject)
	{
		Long id=null;
		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<UserExtra> userextra=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		Leave leave=new Leave();
		
		boolean validUser = true ;
		String msg = "unvalid";
		LocalDate localDate = LocalDate.now();		
		ModelAndView mv= new ModelAndView("Leave");		
		List<Leave> l=leaveSer.findByDate(localDate);
		for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			if(name.equals(m))
			{
				validUser = false;
				id=user.get(i).getId();
				msg = "valid";
			}
			else 
			{

			}
		}
		if (validUser==true)
		{
			System.out.print("sssssssss33333333333344444444ssssssss33333333222333333333333");
			//return mv;
		}
		else
		{
		boolean isExist = false;
		
		for(Leave u:l)
		{
			
			if(id.equals(u.getUserExtra().getId()))
			{
				isExist=true;
			}
			else
			{
				System.out.print("pppppppwwwwwwwwwwwwwqqqqqqqqqqqqq");
			}
		}
		
		if(isExist)
		{
			//return mv;
		}
		else
		{
			for(int j=0;j<userextra.size();j++)
			{
				if(id.equals(userextra.get(j).getId()))
				{
					UserExtra u=userextra.get(j);
					leave.setDate(localDate);
					leave.setUserExtra(u);
					leave.setType(subject);
					leaveSer.setLeave(leave);
				}
			}
		}	
	}
		Set<UserExtra> list=new HashSet<UserExtra>();
		for(Leave u:l)
		{
				list.add(u.getUserExtra());
		}
		list.add(leave.getUserExtra());
		List<UserExtraDTO> dto=getSpecificUser(list);
		mv.addObject("leavelist",dto);
		mv.addObject("msg",msg);

		return mv;
	}
	@RequestMapping("/evaluation")
	public String evaluation()
	{
		return "evaluation";
	}
	/**
	 * for setting evaluation result
	 * @param name
	 * @param num
	 * @param hack
	 * @return
	 */
	@RequestMapping("/setTest")
	public ModelAndView setTest(@RequestParam String name,Long num,Long hack )
	{
		ModelAndView mv = new ModelAndView("evaluation");
		String msg = "unvalid";
		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<UserExtra> userextra=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		for(int i=0;i<user.size();i++)
		{
			Git git=new Git();
			Hackathon hack1 = new Hackathon();
			String m=user.get(i).getFirstName();
			Long id=user.get(i).getId();
			LocalDate local=LocalDate.now();
			if(name.contains(m))
			{
				 msg = "valid";
				for(int j=0;j<userextra.size();j++)
				{
					if(id.equals(userextra.get(j).getId()))
					{
						git.setUserExtra(userextra.get(j));
						git.setDate(local);
						git.setMark(num);
						gitServ.setGit(git);
						hack1.setUserExtra(userextra.get(j));
						hack1.setDate(local);
						hack1.setMark(hack);
						hackServ.setHackathon(hack1);
						 msg = "valid";
					}
				}
			}
		}
		mv.addObject("msg", msg);
		return mv;
	}
	/**
	 * view report status page
	 * @param model
	 * @return
	 */
	@RequestMapping("/reportStatus")
	public String statusPage(Model model) 
	{
		model.addAttribute("status",new ReportStatus());
		return "reportStatus";
	}
    /**
     * for setting late reporting status and redirecting to the same page
     * @param name
     * @param subject
     * @param t
     * @return
     */
	@RequestMapping("/setReport")
	public ModelAndView setReport(@RequestParam String name,String subject,String t)
	{
		ModelAndView mv = new ModelAndView("reportStatus");
		String msg = "unvalid";
		if(t ==null)
		{
			return mv;
		}
		else
		{			
		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<UserExtra> userextra=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		for(int i=0;i<user.size();i++)
		{
			ReportStatus status=new ReportStatus();
			String m=user.get(i).getFirstName();
			LocalDate localDate = LocalDate.now();
			LocalTime localtime = LocalTime.parse(t);
			
			Instant instant=LocalDateTime.of(localDate,localtime).atZone(ZoneId.systemDefault()).toInstant();
			if(name.contains(m))
			{    
				msg = "valid";
				Long id=user.get(i).getId();
				for(int j=0;j<userextra.size();j++)
				{
					if(id.equals(userextra.get(j).getId()))
					{
						status.setReportingTime(instant);
						status.setUserExtra(userextra.get(j));
						status.setType(subject);
						reportServ.setReport(status);
						msg = "valid";
					}
				}
			}
		}
		} mv.addObject("msg", msg);
		
		return mv;
	}
	/**
	 * view late arrival page with listing late arrival status on current date
	 * @return
	 */
	@RequestMapping("/lateArrival")
	public ModelAndView LateArrival()
	{
		Set<UserExtra> list=new HashSet<UserExtra>();
		List<LateArrival> l=lateServ.findAll();
		LocalDate local= LocalDate.now();
		for(int i=0;i<l.size();i++)
		{
			Instant in=l.get(i).getReachedTime();
			LocalDate localDate = in.atZone(ZoneId.systemDefault()).toLocalDate();
			if(local.equals(localDate))
			{
				list.add(l.get(i).getUserExtra());
			}
		}
		
		ModelAndView mv= new ModelAndView("lateArrival");
		List<UserExtraDTO> dto=getSpecificUser(list);
		mv.addObject("latelist",dto);	
		return mv;
	}
	/**
	 * for setting late arrival and redirecting to same page
	 * @param name
	 * @param subject
	 * @param ltime
	 * @return
	 */
	@RequestMapping("/setLate")
	public ModelAndView setLate(@RequestParam String name,String subject,String ltime)
	{
		ArrayList<User> user=(ArrayList<User>) userService.getAllUsers();
		ArrayList<UserExtra> userextra=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		LateArrival late=new LateArrival();
		String msg ="unvalid";
		ModelAndView mv= new ModelAndView("/lateArrival");
		LocalDate localDate = LocalDate.now();
		LocalTime localtime = LocalTime.parse(ltime);
		Instant instant=LocalDateTime.of(localDate,localtime).atZone(ZoneId.systemDefault()).toInstant();
		for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			User u=user.get(i);
			
			Long id=user.get(i).getId();
			
			if(name.contains(m))
			{ 
				msg = "valid";
				for(int j=0;j<userextra.size();j++)
				{
					if(id.equals(userextra.get(j).getId()))
					{
						late.setUserExtra(userextra.get(j));
						late.setType(subject);
						late.setReachedTime(instant);
						lateServ.setLate(late);
						msg = "valid";
					}
				}
			}
		}
		mv.addObject("msg", msg);
		return mv;
	}
	/**
	 * for deleting user
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam (name="id") Long id)
	{
		userService.deleteUser(id);
		 return "redirect:/";  
	}
	/**
	 * for filtering user based on position and company
	 * @param company
	 * @param position
	 * @param model
	 * @return
	 */
	@RequestMapping("/filter")
	public String filter(@RequestParam (name="company")String company,@RequestParam (name="position")String position, Model model)
	{
		ArrayList<UserExtra> users;
		if(!company.equalsIgnoreCase("--select--"))
			{
				users=userService.findByCompany(company);
			}
		else users=(ArrayList<UserExtra>) userService.getAllExtraUsers();
		if(!position.equalsIgnoreCase("--select--"))
		{
			users=userService.findByPosition(users,position);
		}
		ArrayList<User> user=(ArrayList<User>) userService.getUsersFromUserExtra(users);
		
		for(UserExtra userEx:users)
		{
			if(!userEx.getImageContentType().isEmpty())
			 {
				 String image=Base64.getEncoder().encodeToString(userEx.getImage());
				userEx.setImageContentType(image);
			 }	
		}
		List <UserExtraDTO> dto=getAllUser(user,users);
		model.addAttribute("list",dto);
		return "viewAllUser";
	}
	/**
	 * view edit user page
	 * @param id
	 * @return
	 */
	@RequestMapping("/editUser")
	public ModelAndView editUser(@RequestParam (name="id") Long id,@RequestParam (name="error", required=false) boolean error)
	{
		ModelAndView mv=new ModelAndView("editUserPage");
		Optional<User> user=userService.findByid(id);
		Optional<UserExtra> userEx=userService.findExtraByid(id);
		mv.addObject("image",Base64.getEncoder().encodeToString(userEx.get().getImage()));
		mv.addObject("user",user.get());
		String date=userEx.get().getDob().toString();
		String join=userEx.get().getJoiningDate().toString();
		mv.addObject("date",date);
		mv.addObject("join",join);
		 if(error)
			 mv.addObject("error",true);
		
		return mv;
	}
	/**
	 *  for editing user
	 * @param formUser
	 * @param bindingResult
	 * @param roleName
	 * @param date
	 * @param join
	 * @param company
	 * @param file
	 * @param position
	 * @return
	 */
	@PostMapping("/edit")
	public ModelAndView edit(@ModelAttribute @Valid User formUser,BindingResult bindingResult, @RequestParam (name="position") String position,
			@RequestParam (name="date") String date , @RequestParam (name="join") String join, @RequestParam (name="company") String company,
			@RequestParam (name="image")MultipartFile file)
	{
		ModelAndView mv=null;
		long id=formUser.getId();
		Optional<User> user=userService.findByid(id);
		Optional<UserExtra> userEx=userService.findExtraByid(id);
		if (bindingResult.hasErrors()) 
		{
			if(!formUser.getEmail().contentEquals(user.get().getEmail()))
			{
				mv=new ModelAndView("redirect:/editUser");
				mv.addObject("error",true);
				mv.addObject("id", id);
				return mv;
			}
		}
		mv=new ModelAndView("redirect:/userDetails");
		
		if(!file.isEmpty()) {
			try {
				userEx.get().setImage(file.getBytes());
				userEx.get().setImageContentType(file.getContentType());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		user.get().setFirstName(formUser.getFirstName());
		user.get().setLastName(formUser.getLastName());
		user.get().setEmail(formUser.getEmail());
		userEx.get().setDob(LocalDate.parse(date));
		userEx.get().setJoiningDate(LocalDate.parse(join));
		userEx.get().setCompany(company);
		userEx.get().setPosition(position);
		mv.addObject("id", id);
		mv.addObject("success",true);
		
		try {
			userService.createUser(user.get(),userEx.get());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return mv; 
	}
	/**
	 * get appraisal result of single user
	 * @param id
	 * @return
	 */
	@GetMapping("/getPdf")
	public ResponseEntity<byte[]>  getPdf(@RequestParam (name="id")long id)
	{
		Appraisal appraisal=appraisalService.getOneAppraisal(id);
		long attVal=appraisal.getAttendance();
		long punVal=appraisal.getPunctuality();
		long codeVal=appraisal.getCodeQuality();
		long policyVal=appraisal.getCompanyPolicy();
		long targetVal=appraisal.getMeetingTargets();
		String att=getComment(attVal);
		String pun=getComment(punVal);
		String code=getComment(codeVal);
		String policy=getComment(policyVal);
		String target=getComment(targetVal);		
		
		byte[] pdfContents=null;
		try {
			pdfContents=jasperService.getReportAsPdfUsingDatabase(id,att,pun,code,policy,target);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName="Appraisal.pdf";
		headers.add("content dis-position","attachment: filename="+fileName);
		ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(pdfContents,headers,HttpStatus.OK);
		return response;
	}
	/**
	 * get comment for appraisal
	 * @param val
	 * @return
	 */
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
	
	/**
	 * getting appraisal form all employees
	 * @return
	 */
	@GetMapping("/report")
	public ResponseEntity<byte[]> report()
	{
		byte[] pdfContents=null;
		try {
			pdfContents=jasperService.getReportAsPdfUsingJavaBeans(reportList);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		String fileName="Appraisal.pdf";
		headers.add("content dis-position","attachment: filename="+fileName);
		ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(pdfContents,headers,HttpStatus.OK);
		return response;
	}
	/**
	 * to get appraisal details between two date
	 * @param id
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping("/getPdfBetweenTwoDate")
	public ModelAndView pdfBydate(@RequestParam Long id,@RequestParam (name="astart") String start,
			@RequestParam (name="aend") String end)
	{
	     ModelAndView mv= new ModelAndView("redirect:/userDetails"); 	
		 LocalDate first=LocalDate.parse(start);
		 LocalDate second=LocalDate.parse(end);
		 long days= ChronoUnit.DAYS.between(first,second);
		 appraisalService.setAppraisalByDate(id, first, second);
		 mv.addObject("randomApp",true);
		 mv.addObject("id",id);
		return mv;
	}
	@RequestMapping("/getPdfByMonth")
	public ModelAndView pdfByMonth(@RequestParam Long id,@RequestParam (name="month") String month)
	{
		ModelAndView mv= new ModelAndView("redirect:/userDetails"); 	
		System.out.println("month "+month);
		String[] values = month.split("-");
		Calendar calendar = Calendar.getInstance();
		int year = Integer.parseInt(values[0]);
		int monthValue =(Integer.parseInt(values[1]))-1;
		int date = 1;
		calendar.set(year, monthValue, date);
		Date one = calendar.getTime();
		Calendar mycal = new GregorianCalendar(year, monthValue, date);
		int days = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(year, monthValue, days);
		Date two = calendar.getTime();
		LocalDate first=one.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate second=two.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("date 1: " + first);
		System.out.println("date 2: " + second);
		System.out.println("Number of Days: " + days);
		
		appraisalService.setAppraisalByDate(id, first, second);
		mv.addObject("randomApp",true);
		mv.addObject("id",id);
		return mv;
	}
	/**
	 * to get user details between two date
	 * @param id
	 * @param start
	 * @param end
	 * @return
	 */
	@RequestMapping("/sortDate")
	public ModelAndView statusBydate(@RequestParam Long id,@RequestParam (name="start") String start,
			@RequestParam (name="end") String end)
	{
		 ModelAndView mv= new ModelAndView("userDetail"); 	
		 LocalDate start1=LocalDate.parse(start);
		 LocalDate end1=LocalDate.parse(end);		
		 
		 Optional <User> user = userService.findByid(id);
		 Optional <UserExtra> userEx = userService.findExtraByid(id);
		 List<Git> git=gitServ.findGitOfUserBetween(userEx.get(),start1,end1);
		 List<Hackathon> hack=hackServ.findHackathonOfUserBetween(userEx.get(),start1,end1);
		// List<Git> mar=new ArrayList<Git>();
		 //List<Hackathon> mark=new ArrayList<Hackathon>();
		// int n=git.size()-1;
		 //int m = hack.size()-1;
		 if(git.size()!=0) 
		 {
			 Iterator<Git> it=git.iterator();
			 while (it.hasNext())
			{
				Git object = (Git)it.next();
				Long mar= object.getMark();
				 mv.addObject("git",mar);
			 }	
		}		
		if(hack.size()!=0)
		{
			Iterator<Hackathon> i=hack.iterator();
			while (i.hasNext())
			{
				Hackathon object = (Hackathon)i.next();
				Long mark = object.getMark();
				mv.addObject("hack",mark);
			 }	
			//mark.add(hack.get(m));
			//mv.addObject("hack",mark);
		}	
		 List<Leave> leave = leaveSer.findLeavesOfUserBetween(userEx.get(),start1,end1);
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
		 mv.addObject("auth",auth);
		 mv.addObject("unauth",unauth);
		 	 
		 List<LateArrival> lateAll=lateServ.findAllLate(id);
		 List<LateArrival> late = new ArrayList<LateArrival>();
		 for(int i=0;i<lateAll.size();i++)
		 {
			 Instant insta =lateAll.get(i).getReachedTime();
			 LocalDate localdate = insta.atZone(ZoneId.systemDefault()).toLocalDate();
			if(isWithinRange(localdate,start1,end1)==true)
			{
				late.add(lateAll.get(i));
			}			 
		 }
		 
		 List<LocalDateTime> time=new ArrayList<LocalDateTime>();
		 for(int i=0;i<late.size();i++)
		 {
			 Instant in=late.get(i).getReachedTime();
			 LocalDateTime t= LocalDateTime.ofInstant(in,ZoneId.systemDefault());
			 time.add(t);
		 }
		
		  UserExtraDTO dto=getUser(user.get(),userEx.get());
		  mv.addObject("employee",dto);		
		
		 long days= ChronoUnit.DAYS.between(start1,end1);
		 long total=(days*7);
		
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
		 if(!userEx.get().getImageContentType().isEmpty())
		 {
			 String image=Base64.getEncoder().encodeToString(userEx.get().getImage());
			 mv.addObject("image",image);
		 }		
		 int l=((auth.size())+(unauth.size()));
		 long absence=l*7;
		 long workedHour=(total-absence);
		 
		 List<ReportStatus> status=reportServ.findAllReport(id);
		 List<ReportStatus> unreportdays=new ArrayList<ReportStatus>();
		 for(int i=0;i<status.size();i++)
		 {
			 Instant insta =status.get(i).getReportingTime();
			 LocalDate localdate = insta.atZone(ZoneId.systemDefault()).toLocalDate();
			if(isWithinRange(localdate,start1,end1)==true)
			{
				unreportdays.add(status.get(i));
			}			 
		 }

		Appraisal appraisal=appraisalService.getOneAppraisal(id);
		mv.addObject("appraisal",appraisal);		
		 mv.addObject("a",a);
		 mv.addObject("un",un);
		 mv.addObject("time",time);
		 mv.addObject("day",days);
		 mv.addObject("total",total);
		 mv.addObject("workedHour",workedHour);
		 mv.addObject("unreportdays",unreportdays);
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 boolean isAdmin=authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		 boolean isUser=authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		 if(isAdmin)mv.addObject("isAdmin",true);
		 if(isUser)mv.addObject("isUser",true);
		 return mv ;  
	}
	public boolean isWithinRange(LocalDate start1,LocalDate end1,LocalDate Localdate) 
	{
		return Localdate.isAfter(start1) && Localdate.isBefore(end1);
	}
	@RequestMapping("makeAdmin")
	public ModelAndView makeADmin(@RequestParam(name="id")Long id)
	{
		userService.makeAsAdmin(id);
		ModelAndView mv=new ModelAndView("redirect:/userDetails");
		mv.addObject("id",id);
		return mv;
	}
	@RequestMapping("makeUser")
	public ModelAndView makeUser(@RequestParam(name="id")Long id)
	{
		userService.makeAsUser(id);
		ModelAndView mv=new ModelAndView("redirect:/userDetails");
		mv.addObject("id",id);
		return mv;
	}
	@RequestMapping("changePassword")
	public ModelAndView changePassword(@RequestParam(name="oldPassword")String oldPassword,@RequestParam(name="newPassword")String newPassword,
			@RequestParam(name="id")Long id)
	{
		ModelAndView mv=new ModelAndView("redirect:/userDetails");
		UserExtra userEx=userService.findExtraByid(id).get();
		User user=userService.findByid(id).get();
		String currentEncryptedPassword = user.getPassword();
		if (!passwordEncoder.matches(oldPassword, currentEncryptedPassword)) {
			
		    mv.addObject("mismatch",true);
		    mv.addObject("id",id);
		    return mv;
		}
		if(newPassword.length()<2)
		{
			mv.addObject("shortPassword",true);
		    mv.addObject("id",id);
		    return mv;
			
		}
		if(newPassword.equalsIgnoreCase(oldPassword))
		{
			mv.addObject("samePassword",true);
		    mv.addObject("id",id);
		    return mv;
			
		}
		 String encryptedPassword = passwordEncoder.encode(newPassword);	
		 user.setPassword(encryptedPassword);
		 try {
			userService.createUser(user, userEx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 mv.addObject("passwordChanged",true);
		    mv.addObject("id",id);
			
			
		
		return mv;
	}
	@RequestMapping("changeUsername")
	public ModelAndView changeUsername(@RequestParam(name="oldUsername")String oldUsername,@RequestParam(name="newUsername")String newUsername,
			@RequestParam(name="id")Long id)
	{
		ModelAndView mv=new ModelAndView("redirect:/userDetails");
		UserExtra userEx=userService.findExtraByid(id).get();
		User user=userService.findByid(id).get();
		
		
		
		return mv;
		
	}
	@RequestMapping("allReport")
	public ModelAndView allUsersReport()
	{
		reportList=userDataBeanService.getAllUserDataBeans();
		ModelAndView mv=new ModelAndView("allUserReport");
		
		mv.addObject("list", reportList);
	
		return mv;
	}
	@RequestMapping("/getReportByMonth")
	public ModelAndView reportByMonth(@RequestParam (name="month") String month)
	{
		reportList=null;
		ModelAndView mv=new ModelAndView("allUserReport");
		String[] values = month.split("-");
		Calendar calendar = Calendar.getInstance();
		int year = Integer.parseInt(values[0]);
		int monthValue =(Integer.parseInt(values[1]))-1;
		int date = 1;
		calendar.set(year, monthValue, date);
		Date one = calendar.getTime();
		Calendar mycal = new GregorianCalendar(year, monthValue, date);
		int days = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(year, monthValue, days);
		Date two = calendar.getTime();
		LocalDate first=one.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate second=two.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		reportList=userDataBeanService.findAllUserDataBeanByDate(first,second);
		mv.addObject("list", reportList);
			return mv;
	}
	@RequestMapping("/getReportBetweenTwoDate")
	public ModelAndView reportBydate(@RequestParam (name="astart") String start,@RequestParam (name="aend") String end)
	{
		reportList=null;
		 ModelAndView mv=new ModelAndView("allUserReport");
		 LocalDate first=LocalDate.parse(start);
		 LocalDate second=LocalDate.parse(end);
		 long days= ChronoUnit.DAYS.between(first,second);
		 reportList=userDataBeanService.findAllUserDataBeanByDate(first,second);
		 mv.addObject("list", reportList);
			return mv;
	}
}
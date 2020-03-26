package com.lxisoft.Appraisal.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
//import com.thoughtworks.xstream.core.util.Base64Encoder;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lxisoft.Appraisal.model.Role;
import com.lxisoft.Appraisal.model.User;
import com.lxisoft.Appraisal.model.reportStatus;
import com.lxisoft.Appraisal.model.Gitmark;
import com.lxisoft.Appraisal.model.Hackathon;
import com.lxisoft.Appraisal.model.LateArrival;
import com.lxisoft.Appraisal.model.Leave;
import com.lxisoft.Appraisal.service.UserService;

@Controller
public class AppraisalController {

	@Autowired
	private UserService service;

	@RequestMapping("/")
	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView(); 
		String timeStand =new SimpleDateFormat ("yyyy/MM/dd").format( Calendar.getInstance().getTime());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		  String username = ((UserDetails)principal).getUsername();
		boolean hasUserRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		if(hasUserRole)
		{
			mv.setViewName("redirect:/viewUsers");
			mv.addObject("username",username);
		}
		else 
			{
			User user=service.getUserByusername(username);
			mv.addObject("id",user.getId());
			mv.setViewName("redirect:/userDetails");
			}
		
		
		return  mv;
	}

	@RequestMapping("/viewUsers")
	public ModelAndView viewUsers(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv= new ModelAndView("viewAllUsers");
		try {
			ArrayList<User> users;
			
			User user=service.getUserByusername(request.getParameter("username"));
			if(user.getCompany().equalsIgnoreCase("Lxisoft"))
			users=(ArrayList<User>) service.getAllUsers();
			else	users=service.findByCompany(user.getCompany());
			
			
			for (User us:users) 
			{
				us.setFileContentType(Base64.getEncoder().encodeToString(us.getImage()));
				
			}
			
			mv.addObject("fName",user.getFirstName());
			mv.addObject("list", users);
		}
		catch (Exception e)
		{
			mv.setViewName("redirect:/logout-success");
		}
		return mv;
	}
	@RequestMapping("/addUser")
	public String addUser(Model model) {
		model.addAttribute("user",new User());
		return "addUser";
	}
	
	@PostMapping("/addU")
	public ModelAndView addUser(@Valid @ModelAttribute  User user,BindingResult bindingResult, HttpServletRequest request,
			@RequestParam (name="picture") MultipartFile file, RedirectAttributes re)
	{
		ModelAndView mv;
//		System.out.println(request.getParameter("do"));
//		System.out.println(request.getParameter("joinDate"));
		
		
		if (bindingResult.hasErrors()) {
			mv=new ModelAndView("addUser");
			
			if(file.isEmpty())
			{
				re.addFlashAttribute("message","select a file to upload");
				
			}
		}
					
				try
				{
					byte[] bytes=file.getBytes();
					user.setImage(bytes);
					user.setFileContentType(file.getContentType());
					
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			Role role=new Role(request.getParameter("name"));
			Set < Role > roles=new HashSet < Role >();
			roles.add(role);
			user.setRoles(roles);
			user.setJoiningDate(LocalDate.parse(request.getParameter("joinDate")));
			user.setDob(LocalDate.parse(request.getParameter("do")));
			
			mv=new ModelAndView("redirect:/");
			try{
				service.addUser(user);
			}catch(Exception e)
			{
				mv=new ModelAndView("addUser");
			}
			
			
		return mv;
	}

	@RequestMapping("/logout-success")

	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
        }  
         return "redirect:/";  
     }  


	@RequestMapping("/userDetails") 
	 public ModelAndView userDetail(@RequestParam Long id,ModelAndView model) 
	 {
		 ModelAndView mv= new ModelAndView("userDetail"); 
		 Optional <User> user = service.findByid(id);
		 List<Leave> leave = service.findLeave(id);
		 List<LateArrival> late = service.findLate(id);
		 List<LocalDateTime> time=new ArrayList<LocalDateTime>();
		 for(int i=0;i<late.size();i++)
		 {
			 Instant in=late.get(i).getReachedTime();
			 LocalDateTime t= LocalDateTime.ofInstant(in,ZoneId.systemDefault());
			 time.add(t);
		 }
		 mv.addObject("employee",user.get());
		 LocalDate first=user.get().getJoiningDate();
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

		 if(!user.get().getFileContentType().isEmpty())
		 {
			 String image=Base64.getEncoder().encodeToString(user.get().getImage());
			 mv.addObject("image",image);
		 }		
		 int l=((auth.size())+(unauth.size()));
		 long absence=l*7;
		 long workedHour=(total-absence);

		 List<reportStatus> status=service.findReport(id);
		 List<reportStatus> unreportdays=new ArrayList<reportStatus>();
		 for(int i=0;i<status.size();i++)
		 {
			unreportdays.add(status.get(i));
		 }
//		 List<Gitmark> git=service.findGit(id);
		 List<Hackathon> hack=service.findHack(id);
		 if (!user.get().getGitMark().isEmpty()) {
			 mv.addObject("git",(user.get().getGitMark()).get((user.get().getGitMark().size()-1)));
		 }
		 if (!user.get().getHackathon().isEmpty()) {
			mv.addObject("hack",(user.get().getHackathon()).get((user.get().getHackathon().size()-1)));
		 }
				 
		 mv.addObject("auth",auth);
		 mv.addObject("unauth",unauth);
		 mv.addObject("a",a);
		 mv.addObject("un",un);
		 mv.addObject("time",time);
		 mv.addObject("day",days);
		 mv.addObject("total",total);
		 mv.addObject("workedHour",workedHour);
		 mv.addObject("unreportdays",unreportdays);
		 return mv ;  
	 }

	@RequestMapping(value = "/statusform",method = RequestMethod.GET)
	public @ResponseBody
	List<User> getName(@RequestParam("firstName") String firstName) {

		return simulateSearchResult(firstName);

	}

	private List<User> simulateSearchResult(String firstName) {

		ArrayList<User> user=(ArrayList<User>) service.getAllUsers();
		ArrayList<User> result = new ArrayList<User>();
		for (User u : user) {
			if (u.getFirstName().contains(firstName)) {
				result.add(u);
			}
		}

		return result;
	}

	
	

	@RequestMapping("/reportStatus")
	public String statusPage(Model model) 
	{
		model.addAttribute("newReportStatus",new reportStatus());
		return "reportStatus";
	}


	@RequestMapping("/setTest")
	public String setTest(@RequestParam String name,Long num,Long hack )
	{
		ArrayList<User> user=(ArrayList<User>) service.getAllUsers();
		for(int i=0;i<user.size();i++)
		{
			Gitmark git=new Gitmark();
			Hackathon hack1 = new Hackathon();
			String m=user.get(i).getFirstName();
			User u=user.get(i);
			LocalDate local=LocalDate.now();
			if(name.contains(m))
			{
				git.setUser(u);
				git.setDate(local);
				git.setGitMark(num);
				service.setGit(git);
			}
			if(name.contains(m))
			{
				hack1.setUser(u);
				hack1.setDate(local);
				hack1.setHackathon(num);
				service.setHackathon(hack1);
			}
		}
		return "evaluation";
		

	}
	@RequestMapping("/setReport")
	public String setReport(@RequestParam String name,String subject,String t)
	{
		ArrayList<User> user=(ArrayList<User>) service.getAllUsers();
		for(int i=0;i<user.size();i++)
		{
			reportStatus status=new reportStatus();
			String m=user.get(i).getFirstName();
			User u=user.get(i);
			LocalDate localDate = LocalDate.now();
			LocalTime localtime = LocalTime.parse(t);
			Instant instant=LocalDateTime.of(localDate,localtime).atZone(ZoneId.systemDefault()).toInstant();
			if(name.contains(m))
			{
				status.setReportingTime(instant);
				status.setUser(u);
				status.setType(subject);
				service.setReport(status);
			}
		}
		return "reportStatus";
	}
	@RequestMapping("/setLate")
	public String setLate(@RequestParam String name,String subject,String ltime)
	{
		ArrayList<User> user=(ArrayList<User>) service.getAllUsers();
		LateArrival late=new LateArrival();
		ModelAndView mv= new ModelAndView();
		for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			User u=user.get(i);
			LocalDate localDate = LocalDate.now();
			LocalTime localtime = LocalTime.parse(ltime);
			Instant instant=LocalDateTime.of(localDate,localtime).atZone(ZoneId.systemDefault()).toInstant();
			if(name.contains(m))
			{
				late.setUser(u);
				late.setType(subject);
				late.setReachedTime(instant);
				service.setLate(late);
			}
			
		}
		return "lateArrival";
		
	}
	@RequestMapping("/leave")
	public ModelAndView Leave()
	{
		List<Leave> l=service.getAllLeave();
		List<User> list=new ArrayList<User>();
		LocalDate localDate = LocalDate.now();
		ModelAndView mv= new ModelAndView("Leave");
		for(int i=0;i<l.size();i++)
		{
			if((l.get(i).getDate()).equals(localDate))
			{
				list.add(l.get(i).getUser());
			}
		}
		List<User> lea=removeDuplicates(list);
		mv.addObject("leavelist",lea);
		return mv;
		
	}
	@RequestMapping("/setLeave")
	public ModelAndView setLeave(@RequestParam String name,String subject)
	{
		ArrayList<User> user=(ArrayList<User>) service.getAllUsers();
		Leave leave=new Leave();
		List<User> list=new ArrayList<User>();
		List<Leave> l=service.getAllLeave();
		LocalDate localDate = LocalDate.now();
		ModelAndView mv= new ModelAndView("Leave");
		for(int i=0;i<l.size();i++)
		{
			if((l.get(i).getDate()).equals(localDate))
			{
				list.add(l.get(i).getUser());
			}
		}
		for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			User u=user.get(i);
			if(name.contains(m))
			{
				leave.setDate(localDate);
				leave.setUser(u);
				leave.setType(subject);
				service.setLeave(leave);
				list.add(leave.getUser());
			}
		}
		List<User> lea=removeDuplicates(list);
		mv.addObject("leavelist",lea);
		return mv;
	}
	 public <T>List<T> removeDuplicates(List<T> list) 
    { 
        Set<T> set = new LinkedHashSet<>(); 
        set.addAll(list); 
        list.clear(); 
        list.addAll(set); 
        return list; 
    } 
	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam (name="id") Long id)
	{
		service.deleteUser(id);
		 return "redirect:/";  
	}
	@RequestMapping("/editUser")
	public ModelAndView editUser(@RequestParam (name="id") Long id)
	{
		ModelAndView mv=new ModelAndView("editUserPage");
		Optional<User> user=service.findByid(id);
		user.get().setFileContentType(Base64.getEncoder().encodeToString(user.get().getImage()));
		mv.addObject("user",user.get());
		String date=user.get().getDob().toString();
		String join=user.get().getJoiningDate().toString();
		mv.addObject("date",date);
		mv.addObject("join",join);
		
		
		return mv;
	}
	@PostMapping("/edit")
	public String edit(@ModelAttribute @Valid User formUser,BindingResult bindingResult,@RequestParam (name="name") String roleName,
			@RequestParam (name="date") String date , @RequestParam (name="join") String join, @RequestParam (name="id") long id,
			@RequestParam (name="image")MultipartFile file)
	{
		Optional<User> user=service.findByid(id);
		
//		if (bindingResult.hasErrors()) {
//			return "editUserPage";
//			}
		if(!file.isEmpty()) {
			try {
				user.get().setImage(file.getBytes());
				user.get().setFileContentType(file.getContentType());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		user.get().setFirstName(formUser.getFirstName());
		user.get().setLastName(formUser.getLastName());
		user.get().setEmailID(formUser.getEmailID());
		user.get().setDob(LocalDate.parse(date));
		user.get().setJoiningDate(LocalDate.parse(join));
		user.get().setCompany(formUser.getCompany());
		user.get().setPosition(formUser.getPosition());
		user.get().setUsername(formUser.getUsername());
		user.get().setPassword(formUser.getPassword());
		
		service.updateUser(user.get());
		
		return "redirect:/"; 
	}
	
	@RequestMapping("/filter")
	public String filter(@RequestParam (name="company")String company,@RequestParam (name="position")String position, Model model)
	{
		ArrayList<User> users;
		if(!company.equalsIgnoreCase("--select--"))
			{
				users=service.findByCompany(company);
			}
		else users=(ArrayList<User>) service.getAllUsers();
		if(!position.equalsIgnoreCase("--select--"))
		{
			users=service.findByPosition(users,position);
		}
		
		model.addAttribute("list",users);
		return "viewAllUsers";
		
	}
	@RequestMapping("/getAppraisalResult")
	public String getAppraisalResult(@RequestParam long id, Model model)
	{
		long attendance=service.getAttendance(id);
		
		long punctuality=service.getPunctuality(id);
		
		long meetingTargets=service.getTargets(id);
		
		long companyPolicy=service.getcompanyPolicy(id);
		
		long codeQuality=service.getCodeQuality(id);
		
		model.addAttribute("attendance",attendance);
		model.addAttribute("punctuality",punctuality);
		model.addAttribute("meetingTargets",meetingTargets);
		model.addAttribute("companyPolicy",companyPolicy);
		model.addAttribute("codeQuality",codeQuality);
		
//		System.out.println(attendance+"  "+punctuality+ " "+punctuality+"  "+companyPolicy+" "+codeQuality);
		return "AppraisalReport";
	}
}

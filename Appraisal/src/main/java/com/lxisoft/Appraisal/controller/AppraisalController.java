package com.lxisoft.Appraisal.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import com.lxisoft.Appraisal.model.LateArrival;
import com.lxisoft.Appraisal.model.Leave;
import com.lxisoft.Appraisal.service.UserService;

@Controller
public class AppraisalController {

	@Autowired
	private UserService service;
	 private static String folder="F:\\KP_ZONE\\java\\Apprisal\\HR-Management\\Appraisal\\src\\main\\resources\\static\\img\\";

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
		System.out.println(request.getParameter("do"));
		System.out.println(request.getParameter("joinDate"));
		
		
		if (bindingResult.hasErrors()) {
			mv=new ModelAndView("addUser");
			
			if(file.isEmpty())
			{
				re.addFlashAttribute("message","select a file to upload");
				
			}
		}
		else {
			
			
				try
				{
					byte[] bytes=file.getBytes();
					Path path=Paths.get(folder+file.getOriginalFilename());
					Files.write(path, bytes);
					user.setImage(bytes);
					
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
			service.addUser(user);
			mv=new ModelAndView("redirect:/");
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
//			 LocalDateTime time=Instant.of(localDate,localtime).atZone(ZoneId.systemDefault()).toInstant();
			 LocalDateTime t= LocalDateTime.ofInstant(in,ZoneId.systemDefault());
			 time.add(t);
		 }
		 mv.addObject("employee",user.get());
		 mv.addObject("leave",leave);
		 mv.addObject("late",late);
		 mv.addObject("time",time);
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
	@RequestMapping("/leave")
	public String Leave(Model model)
	{
		model.addAttribute("newLeave",new Leave());
		return "leave";
		
	}
	@RequestMapping("/lateArrival")
	public String LateArrival(Model model)
	{
		model.addAttribute("newLate",new LateArrival());
		return "lateArrival";
		
	}
	@RequestMapping("/setLeave")
	public String setLeave(Model model,@ModelAttribute Leave leave,@RequestParam String name,String subject)
	{
		ArrayList<User> user=(ArrayList<User>) service.getAllUsers();
		for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			User u=user.get(i);
			LocalDate localDate = LocalDate.now();
			System.out.println("today is:::::::::"+localDate);
			if(name.contains(m))
			{
				leave.setDate(localDate);
				leave.setUser(u);
				leave.setType(subject);
				service.setLeave(leave);
			}
		}
		model.addAttribute("newLeave",new Leave());
		return "Leave";
	}
	@RequestMapping("/setLate")
	public String setLate(Model model,@ModelAttribute LateArrival late,@RequestParam String name,String subject,String ltime)
	{
		ArrayList<User> user=(ArrayList<User>) service.getAllUsers();
		for(int i=0;i<user.size();i++)
		{
			String m=user.get(i).getFirstName();
			User u=user.get(i);
			LocalDate localDate = LocalDate.now();
			System.out.println("today is:::::::::"+localDate);
			LocalTime localtime = LocalTime.parse(ltime);
			Instant instant=LocalDateTime.of(localDate,localtime).atZone(ZoneId.systemDefault()).toInstant();
			System.out.println("today :::::::"+instant);
			if(name.contains(m))
			{
				late.setUser(u);
				late.setType(subject);
				late.setReachedTime(instant);
				service.setLate(late);
			}
		}
		model.addAttribute("newLate",new LateArrival());
		return "lateArrival";
		
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
		mv.addObject("user",user);
		String date=user.get().getDob().toString();
		String join=user.get().getJoiningDate().toString();
		mv.addObject("date",date);
		mv.addObject("join",join);
		
		return mv;
	}
	@RequestMapping("/edit")
	public String edit(@ModelAttribute @Valid User user,BindingResult bindingResult,@RequestParam (name="name") String roleName,
			@RequestParam (name="date") String date , @RequestParam (name="join") String join)
	{
		
		if (bindingResult.hasErrors()) {
			return "editUserPage";
			}
		
		Role role=new Role(roleName);
		Set < Role > roles=new HashSet < Role >();
		roles.add(role);
		user.setRoles(roles);
		user.setDob(LocalDate.parse(date));
		user.setJoiningDate(LocalDate.parse(join));
		
		service.updateUser(user);
		
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
}

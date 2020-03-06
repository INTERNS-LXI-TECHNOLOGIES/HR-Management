package com.lxisoft.Appraisal.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxisoft.Appraisal.domain.Employee;
import com.lxisoft.Appraisal.service.JPAService;

import io.micrometer.core.ipc.http.HttpSender.Request;

@Controller
public class AppraisalController {

	@Autowired
	private JPAService service;

	@RequestMapping("/")

	public ModelAndView home()
	{
		ModelAndView mv=new ModelAndView(); 
		String timeStand =new SimpleDateFormat ("yyyy/MM/dd").format( Calendar.getInstance().getTime());
		
		mv.addObject("date",timeStand);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasUserRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
		if(hasUserRole)
			mv.setViewName("adminLogin");
		else mv.setViewName("redirect:/userPage");
		
		
		return  mv;
	}
	@RequestMapping("/userPage")
	public String user()
	{
		
		return "UserLogin";
	}

	@RequestMapping("/viewUsers")
	public ModelAndView viewUsers() {
		ArrayList<Employee> employees = (ArrayList<Employee>) service.getAllUsers();
		ModelAndView mv = new ModelAndView("viewAllUsers");
		mv.addObject("list", employees);
		return mv;
	}

	@RequestMapping("/addUser")
	public String addUser() {

		return "addUser";
	}

	@RequestMapping("/addU")
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) {

		Employee employee = new Employee();
		employee.setFirstName(request.getParameter("firstname"));
		employee.setLastName(request.getParameter("lastname"));
		employee.setEmailID(request.getParameter("email"));
		employee.setCompany(request.getParameter("company"));
		service.addUser(employee);
		ModelAndView mv=viewUsers();
		return mv;
	}

	@RequestMapping("/login")

	public String loginPage() {
		return "login";

		

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
		// mv.addObject("id",id); 	 
	  Optional <Employee> optional = service.findByid(id);	  	 
		 mv.addObject("employee",optional.get());     
	 	return mv ;  
	 
 }
 
}

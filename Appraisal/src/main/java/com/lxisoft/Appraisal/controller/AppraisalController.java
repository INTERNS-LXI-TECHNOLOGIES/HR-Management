package com.lxisoft.Appraisal.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lxisoft.Appraisal.domain.Employee;
import com.lxisoft.Appraisal.service.JPAService;

@Controller
public class AppraisalController {

	@Autowired
	private JPAService service;
	
	@RequestMapping("/")
	public String home()
	{
		return  "adminLogin";
	}
	@RequestMapping("/viewUsers")
	public ModelAndView viewUsers()
	{
		ArrayList<Employee> employees=(ArrayList<Employee>) service.getAllUsers();
		ModelAndView mv= new ModelAndView("viewAllUsers");
		mv.addObject("list", employees);

		return mv;
	}
	@RequestMapping("/addUser")
	public String addUser()
	{
	
		return "addUser.html";
	}
	@RequestMapping("/addU")
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response)
	{
		
		Employee employee= new Employee();
		employee.setFirstName(request.getParameter("firstname"));
		employee.setLastName(request.getParameter("lastname"));
		employee.setEmailID(request.getParameter("email"));
		employee.setCompany(request.getParameter("company"));
		service.addUser(employee);
		
		ModelAndView mv=viewUsers();
		
		return mv;
	}

	@RequestMapping("/login")
	public String loginPage()
	{
		return  "login.html";
	}
	@RequestMapping("/logout-success")
	public String logoutPage()
	{
		return  "logout.html";

	}
	@RequestMapping("/userDetails")
	public String userDetail()
	{
		return "userDetail"; 

	}
}


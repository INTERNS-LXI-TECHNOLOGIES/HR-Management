package com.lxisoft.Appraisal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lxisoft.Appraisal.model.User;
import com.lxisoft.Appraisal.service.UserService;


@Controller
public class MainController {
	@Autowired
	private UserService service;
	
	
	@RequestMapping("/userPage")
	public String user()
	{
		
		return "UserLogin";
	}
	
	@RequestMapping("/addUser")
	public String addUser() {

		return "addUser";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping("/status")
	public String status()
	{
		
		return "status";
	}

}

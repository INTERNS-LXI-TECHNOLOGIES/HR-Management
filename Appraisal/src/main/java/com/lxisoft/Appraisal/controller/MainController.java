package com.lxisoft.Appraisal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
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

}

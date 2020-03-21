package com.lxisoft.Appraisal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lxisoft.Appraisal.model.EvaluationTest;
import com.lxisoft.Appraisal.model.LateArrival;
import com.lxisoft.Appraisal.model.Leave;
import com.lxisoft.Appraisal.model.Role;
import com.lxisoft.Appraisal.model.User;
import com.lxisoft.Appraisal.model.reportStatus;
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
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping("/status")
	public String status()
	{
		
		return "status";
	}
	@RequestMapping("/lateArrival")
	public String LateArrival()
	{
		return "lateArrival";
		
	}

	@RequestMapping("/reportStatus")
	public String statusPage() 
	{
		return "reportStatus";
	}
	@RequestMapping("/evaluation")
	public String evaluation()
	{
		return "evaluation";
		
	}
	@RequestMapping("/adminLogin")
	public String adminLogin()
	{
		return "adminLogin";
	}
}

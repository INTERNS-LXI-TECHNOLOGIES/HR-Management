package com.lxisoft.Appraisal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppraisalController {

	@RequestMapping("/")
	public String home()
	{
		return  "adminLogin.html";
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
	@RequestMapping("/userDetail")
	public String userDetail()
	{
		return "userDetail.html"; 

	}
}


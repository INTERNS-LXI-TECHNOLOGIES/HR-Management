package com.lxisoft.Appraisal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppraisalController {

	@RequestMapping("/")
	public String home()
	{
		return  "index.html";
	}
}


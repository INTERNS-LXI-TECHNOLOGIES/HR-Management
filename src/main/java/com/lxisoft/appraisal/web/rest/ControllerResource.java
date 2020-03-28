package com.lxisoft.appraisal.web.rest;

import java.io.IOException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
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
import javax.xml.bind.DatatypeConverter;

import org.springframework.web.bind.annotation.*;
import com.lxisoft.appraisal.domain.User;
import com.lxisoft.appraisal.domain.UserExtra;
import com.lxisoft.appraisal.service.UserService;


/**
 * ControllerResource controller
 */

@Controller
public class ControllerResource {
	@Autowired
	UserService userService;

    private final Logger log = LoggerFactory.getLogger(ControllerResource.class);

    @GetMapping(value= "/login")
    public String login() {
    	
        return "login";
    }
    /**
    * GET map
    */
    @GetMapping(value= "/map")
    public String map() {
    	
        return "home";
    }
    @GetMapping(value= "/add")
    public String add(Model model) {
    	
    	model.addAttribute("user",new User());
    	return "addUser";
    }
    @PostMapping("/addU")
	public ModelAndView addUser(@Valid @ModelAttribute  User user,BindingResult bindingResult, HttpServletRequest request,
			@RequestParam (name="picture") MultipartFile file, RedirectAttributes re)
	{
		ModelAndView mv;
		UserExtra us=new UserExtra();
		
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
					us.setImage(bytes);
					us.setImageContentType(file.getContentType());
					
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			us.setCompany(request.getParameter("company"));
			us.setCompany(request.getParameter("postion"));
			us.setJoiningDate(LocalDate.parse(request.getParameter("joinDate")));
			us.setDob(LocalDate.parse(request.getParameter("do")));
			
			mv=new ModelAndView("redirect:/");
			try{
				User u=userService.createUser(user,us);
			}catch(Exception e)
			{
				mv=new ModelAndView("addUser");
			}
			
			
		return mv;
	}
}

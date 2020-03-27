package com.lxisoft.appraisal.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * ControllerResource controller
 */

@Controller
public class ControllerResource {

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
    public String add() {
    	
        return "addUser";
    }
   
}

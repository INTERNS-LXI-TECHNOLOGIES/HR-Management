package com.lxisoft.Appraisal.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MultipartException.class)
	public String error1(MultipartException e,RedirectAttributes re)
	{
		re.addFlashAttribute("message",e.getCause().getMessage());
		return "viewAllUsers";
	}

}

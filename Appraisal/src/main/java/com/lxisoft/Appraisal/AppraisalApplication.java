package com.lxisoft.Appraisal;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;

import com.lxisoft.Appraisal.domain.Employee;
import com.lxisoft.Appraisal.model.User;
import com.lxisoft.Appraisal.repository.UserRepository;

@SpringBootApplication

public class AppraisalApplication {


	public static void main(String[] args)
	{
		SpringApplication.run(AppraisalApplication.class, args);
	}
	

}
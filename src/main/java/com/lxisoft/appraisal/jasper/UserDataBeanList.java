package com.lxisoft.appraisal.jasper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lxisoft.appraisal.domain.Appraisal;
import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.service.AppraisalService;

public class UserDataBeanList {
	@Autowired
	static
	AppraisalService appService;
	/**
	 * get list of User with dummy data
	 * @return List: UserDataBean object list
	 */
	public static List getUserDataBeanList()
	{
		List<UserDataBean>dataBeanList=new ArrayList<UserDataBean>();
//		Long a=(long) 45,b=(long) 456,c=(long) 678,d=(long) 5678,e=(long) 6789;
//		dataBeanList.add(new UserDataBean("push","pu","lxisof","inter","ajith@gmail.com",a,b,c,d,e));
		return dataBeanList;
	}

}

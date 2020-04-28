package com.lxisoft.appraisal.jasper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lxisoft.appraisal.domain.UsersDataBean;
import com.lxisoft.appraisal.service.AppraisalService;

public class UsersDataBeanList {
	
@Autowired
static
AppraisalService appService;
/**
 * get list of User with dummy data
 * @return List: UserDataBean object list
 */
public static List getUsersDataBeanList()
{
	List<UsersDataBean>dataBeanList=new ArrayList<UsersDataBean>();
	
	return dataBeanList;
}
}

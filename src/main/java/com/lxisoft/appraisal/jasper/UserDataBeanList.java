package com.lxisoft.appraisal.jasper;

import java.util.ArrayList;
import java.util.List;

import com.lxisoft.appraisal.domain.UserDataBean;

public class UserDataBeanList {

	public static List getUserDataBeanList()
	{
		List<UserDataBean> dataBeanList=new ArrayList<UserDataBean>();
		dataBeanList.add(new UserDataBean("ajith","kp","lxisoft"));
		dataBeanList.add(new UserDataBean("push","pu","lxisof\t"));
		dataBeanList.add(new UserDataBean("abhi","jith","lxist"));
		dataBeanList.add(new UserDataBean("mehar","thatha","lxi"));
		return dataBeanList;
	}
	public static UserDataBean getProduceData(String fname,String lname,String company)
	{
		UserDataBean userDataBean=new UserDataBean(fname,lname,company);
		return userDataBean;
	}
	public static List<UserDataBean> getProduceDataList(String fname,String lname,String company)
	{
		List<UserDataBean> list=new ArrayList<UserDataBean>();
		list.add(new UserDataBean(fname,lname,company));
		return list;
	}
}

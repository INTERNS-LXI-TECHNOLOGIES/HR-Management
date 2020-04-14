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

	public static List getUserDataBeanList()
	{
//		Long attendance=(long) 741,punctuality=(long) 852,meetingTargets=(long) 4852,companyPolicy=(long) 8529,codeQuality=(long) 85;
		List<UserDataBean>dataBeanList=new ArrayList<UserDataBean>();
//		dataBeanList= list;
		Long a=(long) 45,b=(long) 456,c=(long) 678,d=(long) 5678,e=(long) 6789;
		dataBeanList.add(new UserDataBean("push","pu","lxisof","inter","ajith@gmail.com",a,b,c,d,e));
//	
		return dataBeanList;
	}
//	public static Appraisal getProduceData(Long attendance, Long punctuality, Long meetingTargets, Long companyPolicy,
//			Long codeQuality)
//	{
//		Appraisal appraisal=new Appraisal(attendance,punctuality,meetingTargets,companyPolicy,codeQuality);
//		return appraisal;
//	}
//	public static List<Appraisal> getProduceDataList()
//	{
//		List<Appraisal> list=new ArrayList<Appraisal>();
//		System.out.println(".,,,,,,,,,,,,,,,,,,,,"+appService.getDetails());
//		list=appService.getDetails();
//		return list;
//	}
}

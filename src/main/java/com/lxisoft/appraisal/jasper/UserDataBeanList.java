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
		Long attendance=(long) 741,punctuality=(long) 852,meetingTargets=(long) 4852,companyPolicy=(long) 8529,codeQuality=(long) 85;
		List<Appraisal>dataBeanList=new ArrayList<Appraisal>();
		dataBeanList= getProduceDataList();
		dataBeanList.add(new Appraisal(attendance,punctuality,meetingTargets,companyPolicy,codeQuality));
//		dataBeanList.add(new UserDataBean("push","pu","lxisof\t"));
//		dataBeanList.add(new UserDataBean("abhi","jith","lxist"));
//		dataBeanList.add(new UserDataBean("mehar","thatha","lxi"));
		return dataBeanList;
	}
//	public static Appraisal getProduceData(Long attendance, Long punctuality, Long meetingTargets, Long companyPolicy,
//			Long codeQuality)
//	{
//		Appraisal appraisal=new Appraisal(attendance,punctuality,meetingTargets,companyPolicy,codeQuality);
//		return appraisal;
//	}
	public static List<Appraisal> getProduceDataList()
	{
		List<Appraisal> list=new ArrayList<Appraisal>();
		System.out.println(".,,,,,,,,,,,,,,,,,,,,"+appService.getDetails());
		list=appService.getDetails();
		return list;
	}
}

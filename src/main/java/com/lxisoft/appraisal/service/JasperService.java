package com.lxisoft.appraisal.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxisoft.appraisal.domain.UserDataBean;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
@Transactional
public class JasperService {
	private final static Logger log =LoggerFactory . getLogger ( JasperService.class );
	@Autowired
	DataSource dataSource;
	
	public byte[] getReportAsPdfUsingDatabase()throws JRException
	{
		JasperReport jr=JasperCompileManager.compileReport("src/main/resources/Tree.jrxml");
		
		//preparing parameteres
		Map <String,Object> parameters=new HashMap<String,Object>();
		Connection con=null;
		try {
			con=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JasperPrint jp=JasperFillManager.fillReport(jr,parameters,con);
		return JasperExportManager.exportReportToPdf(jp);
		
	}
	public static List getUserDataBeanList()
	{
		List<UserDataBean> dataBeanList=new ArrayList<UserDataBean>();
		dataBeanList.add(new UserDataBean("ajith","kp","lxisoft"));
		dataBeanList.add(new UserDataBean("push","pu","lxisof\t"));
		dataBeanList.add(new UserDataBean("abhi","jith","lxist"));
		dataBeanList.add(new UserDataBean("mehar","thatha","lxi"));
		return dataBeanList;
	}

}

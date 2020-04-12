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
import com.lxisoft.appraisal.jasper.UserDataBeanList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
		Map parameters=new HashMap();
		parameters.put("Report Heading","This is the title of the report");
		parameters.put("id_ap","2");
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
	
	public byte[] getReportAsPdfUsingJavaBeans()throws JRException
	{
		JasperReport jr=JasperCompileManager.compileReport("src/main/resources/TreeBean.jrxml");
		JRBeanCollectionDataSource collectionDataSource=new JRBeanCollectionDataSource(UserDataBeanList.
				getUserDataBeanList());
		Map < String , Object > parameters = new HashMap < String ,	Object >();
		JasperPrint jp=JasperFillManager.fillReport(jr,parameters,collectionDataSource);
		return JasperExportManager.exportReportToPdf(jp);
		
	}
}

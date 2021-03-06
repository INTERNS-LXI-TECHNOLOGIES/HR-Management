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

import com.lxisoft.appraisal.domain.Hackathon;
import com.lxisoft.appraisal.domain.UserDataBean;
import com.lxisoft.appraisal.jasper.UserDataBeanList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 * Service Implementation for managing Jasper report.
 */
@Service
@Transactional
public class JasperService {
	private final static Logger log =LoggerFactory . getLogger ( JasperService.class );
	@Autowired
	DataSource dataSource;
	/**
	 * get report as pdf of single user using database
	 * @param id
	 * @param end 
	 * @param start 
	 * @param month 
	 * @return
	 * @throws JRException
	 */
	public byte[] getReportAsPdfUsingDatabase(long id,String att,String pun,String code,String policy,String target, String start, String end, String month)throws JRException
	{
		JasperReport jr=JasperCompileManager.compileReport("src/main/resources/jasper/UserReport.jrxml");
		
		//preparing parameteres
		Map parameters=new HashMap();
		parameters.put("head","Appraisal report");
		parameters.put("id",id);
		parameters.put("Parameter1",att);
		parameters.put("Parameter2",pun);
		parameters.put("Parameter3",code);
		parameters.put("Parameter4",policy);
		parameters.put("Parameter5",target);
		parameters.put("start", start);
		parameters.put("end", end);
		parameters.put("type",month);
		
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
	/**
	 * get report as pdf of all user using javabeans
	 * @param list
	 * @param month 
	 * @return
	 * @throws JRException
	 */
	public byte[] getReportAsPdfUsingJavaBeans(List<UserDataBean> list, String month)throws JRException
	{
		JasperReport jr=JasperCompileManager.compileReport("src/main/resources/jasper/C.jrxml");
		JRBeanCollectionDataSource collectionDataSource=new JRBeanCollectionDataSource(list);
		Map < String , Object > parameters = new HashMap < String ,	Object >();
		parameters.put("month", month);
		JasperPrint jp=JasperFillManager.fillReport(jr,parameters,collectionDataSource);
		return JasperExportManager.exportReportToPdf(jp);
		
	}
	
}

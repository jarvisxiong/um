/**  
 * CtdbBillingAppAction.java  
 * com.hhz.ump.web.ctdb  
 *  
 * Function： 结算审批数据库  
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-21        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.ctdb;  

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.ctdb.CtdbBillingAppManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.ctdb.CtdbBillingApp;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.web.bid.PoiStyle;

/**  
 * ClassName:CtdbBillingAppAction  
 * Function:  结算审批数据库 
 * Reason:    结算审批数据库 
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-12-21        下午05:18:39  
 *
 */
@Namespace("/ctdb")
@Results( { @Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel","inputName", "excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" }) })
public class CtdbBillingAppAction extends CrudActionSupport<CtdbBillingApp> {

	
	private static final long serialVersionUID = 6519363946685781927L;
	
	/**
	 * service注入点
	 */
	@Autowired
	protected CtdbBillingAppManager ctdbBillingAppManager; 
	@Autowired
	protected ResApproveInfoManager resApproveInfoManager; 
	
	
	
	/**
	 * 日志工具
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 搜索Map
	 */
	private Map <String,Object>map;
	/**
	 * 搜索SQL
	 */
	private StringBuffer hql;
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;
	/**
	 * 通过URL传输的参数,需要转码
	 */
	private String ecode;
	
	
	
	/**
	 * 搜索前的准备操作
	 */
	public void prepareLoadList(){
		//根据请求参数构建搜索语句和搜索条件
		map=new HashMap<String,Object>();	
		//构建搜索SQL
		hql=new StringBuffer().append(" from CtdbBillingApp cba where 1=1 ");
		//项目ID
		String projectCds=Struts2Utils.getParameter("selectedItems");
		//项目名称
		String projectName=Struts2Utils.getParameter("projectName");
		//合同名称
		String ctName=Struts2Utils.getParameter("ctName");
		//乙方
		String impSupName=Struts2Utils.getParameter("impSupName");
		//结算日期
		String billingDate=Struts2Utils.getParameter("billingDate");
		//结算日期到
		String billingDateTo=Struts2Utils.getParameter("billingDateTo");
		//开始封装HQL搜索语句和搜索条件Map
		if(StringUtils.isNotBlank(projectCds)){
			map.put("projectCd", projectCds.split(","));
			hql.append(" and cba.projectCd in( :projectCd )");
		}
		//项目名称不为空
		if(StringUtils.isNotBlank(projectName)){
			String projectNameTmp=projectName;
			//如果需要转码
			if(StringUtils.isNotBlank(ecode)){
				try {
					projectNameTmp=ChangeCharset.decode2UTF_8(projectName);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
					
				}
			}
			map.put("projectName", projectNameTmp);
			hql.append(" and cba.projectName like '%"+projectNameTmp+"%' ");
		}
		//合同名称不为空
		if(StringUtils.isNotBlank(ctName)){
			String ctNameTmp=ctName;
			//如果需要转码
			if(StringUtils.isNotBlank(ecode)){
				try {
					ctNameTmp=ChangeCharset.decode2UTF_8(ctName);
				} catch (Exception e) {
					e.printStackTrace(); 
					logger.error(e.getMessage());			
				}
			}
			map.put("ctName", ctNameTmp);
			hql.append(" and cba.ctName like '%"+ctNameTmp+"%' ");
		}
		//乙方
		if(StringUtils.isNotBlank(impSupName)){
			String impSupNameTmp=impSupName;
			//需要转码
			if(StringUtils.isNotBlank(ecode)){
				try {
					impSupNameTmp=ChangeCharset.decode2UTF_8(impSupName);
				} catch (Exception e) {
					e.printStackTrace();  
					logger.error(e.getMessage());
				}
			}
			map.put("impSupName", impSupNameTmp);
			hql.append(" and cba.impSupCd like '%"+impSupNameTmp+"%' ");
		}
		//结算日期从
		if(StringUtils.isNotBlank(billingDate)){
			String billingDateTmp=billingDate;
			//需要转码
			if(StringUtils.isNotBlank(ecode)){
				try {
					billingDateTmp=ChangeCharset.decode2UTF_8(billingDate);
				} catch (Exception e) {
					e.printStackTrace();  
					logger.error(e.getMessage());
				}
			}
			map.put("billingDate", billingDateTmp);
			hql.append(" and  cba.billingDate >= to_date(:billingDate,'yyyy-mm-dd')");
		}
		////结算日期到
		if(StringUtils.isNotBlank(billingDateTo)){
			String billingDateTmp=billingDateTo;
			//需要转码
			if(StringUtils.isNotBlank(ecode)){
				try {
					billingDateTmp=ChangeCharset.decode2UTF_8(billingDateTo);
				} catch (Exception e) {
					e.printStackTrace();  
					logger.error(e.getMessage());
				}
			}
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			Calendar c=Calendar.getInstance();
			try {
				c.setTime(f.parse(billingDateTmp));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.add(Calendar.DAY_OF_MONTH, 1);
			map.put("billingDateTo", f.format(c.getTime()));
			hql.append(" and cba.billingDate <= to_date(:billingDateTo,'yyyy-mm-dd') ");
		}
		
		//页码信息
		String pageNo = Struts2Utils.getParameter("pageNo");
		String rows = Struts2Utils.getParameter("rows");
		if(StringUtils.isNotBlank(rows)){
			page.setPageSize(Integer.parseInt(rows));
		}
		if(StringUtils.isNotBlank(pageNo)){
			page.setPageNo(Integer.parseInt(pageNo));
		}
		
	}
	
	/**
	 * 结算数据库列表搜索
	 * @return
	 */
	public String loadList(){
		page=ctdbBillingAppManager.findPage(page, hql.toString(), map);

		
		// 处理网批查询号
		if(page != null && page.getResult()!= null && page.getResult().size() > 0){
			String resId = null;
			ResApproveInfo res = null;
			for (CtdbBillingApp bid : page.getResult()) {
				resId = bid.getBillingResCd();
				if(StringUtils.isNotBlank(resId)){
					res = resApproveInfoManager.getResApproveInfoById(resId);
					if(res != null &&  res.getDisplayNo() != null){
						bid.setApproveCd(String.valueOf(res.getDisplayNo()));
					}
				}
			}
		}
		
		return "loadList";
	}
	
	
	
	/**
	 * 导入历史结算数据
	 * @return
	 */
	public String importHistory(){
		
		//1、材料设备结算审批表(新) 	material-settlement-approve.jsp 	com.hhz.ump.web.res.bean.MaterialSettlementApprove FKGL_CLSBJS
		//   材料设备结算审批表(新) 材料设备结算审批表(不超出合同金额3%) CBCGGL_GCJS_10
		//   材料设备结算审批表(新) 材料设备结算审批表(超出合同金额3%) CBCGGL_GCJS_12
		//2、工程结算审批表(新) 	project-balance-bill.jsp 	com.hhz.ump.web.res.bean.ProjectBalanceBill
		//   工程结算审批表(新)   工程结算审批表(不超出合同金额3%) CBCGGL_GCJS_20
		 //  工程结算审批表(新)    工程结算审批表(超出合同金额3%) CBCGGL_GCJS_25
		ctdbBillingAppManager.importHistory();
		return null;
	}
	
	/**
	 * 导出结算数据库列表
	 * @return
	 */
	public String exportToExcel(){
		//执行搜索条件前置条件准备
		prepareLoadList();
		//文件名
		String sheetName="结算数据库";
		//输出文件流
		ByteArrayOutputStream outExcelFile=null;
		try {	
			excelFileName=sheetName;
			outExcelFile= new ByteArrayOutputStream();
			//将分析数据填入excel
			fillInExcel(sheetName,outExcelFile);
			//将数据读出到数组
			byte[] data = outExcelFile.toByteArray();
			//将数据写入文件，执行导出
			excelFile=new ByteArrayInputStream(data);
			//文件名编码转换，防止乱码
			excelFileName=new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "export";
	}
	
	/**
	 * 结算数据库数据填充EXCEL
	 * @param sheetName 表名
	 * @param outExcelFileTmp 输出文件流
	 */
	private void fillInExcel(String sheetName, ByteArrayOutputStream outExcelFileTmp) {
		  
		// 声明一个工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(sheetName);
	      // 设置表格默认列宽度为15个字节
	      sheet.setDefaultColumnWidth((short) 22);
	      sheet.setDefaultRowHeight((short)25);
	      // 生成一个样式
	      HSSFCellStyle style = PoiStyle.buildStyle(workbook);	      
	      // 生成并设置另一个样式
	      HSSFCellStyle style2 = PoiStyle.buildStyle2(workbook);	      
	      // 生成并设置另一个样式
	      HSSFCellStyle style3 = PoiStyle.buildStyle3(workbook);
	      // 生成并设置另一个样式
	      HSSFCellStyle style4 = PoiStyle.buildStyle4(workbook);    
	     
	      //单元格
	      HSSFCell cell=null;
	      //单元格内容
	      HSSFRichTextString text=null;
	      //合并单元区域
	      CellRangeAddress address=null;
	      //第1行
	      HSSFRow row = sheet.createRow(0);
	      //项目
	      PoiExcelTool.addCell(row,cell,text,0,style4,"1","项目",null);
	      //合同
	      PoiExcelTool.addCell(row,cell,text,1,style4,"1","合同",null);
	      //乙方
	      PoiExcelTool.addCell(row,cell,text,2,style4,"1","乙方",null);
	      //承包范围
	      PoiExcelTool.addCell(row,cell,text,3,style4,"1","承包范围",null);
	      //合同性质
	      PoiExcelTool.addCell(row,cell,text,4,style4,"1","合同性质",null);
	      //面积
	      PoiExcelTool.addCell(row,cell,text,5,style4,"1","面积",null);
	      //结算价
	      PoiExcelTool.addCell(row,cell,text,6,style4,"1","结算价",null);
	      //单位造价
	      PoiExcelTool.addCell(row,cell,text,7,style4,"1","单位造价",null);
	      //结算日期
	      PoiExcelTool.addCell(row,cell,text,8,style4,"1","结算日期",null);
	      //网批编号
	      PoiExcelTool.addCell(row,cell,text,9,style4,"1","网批编号",null);
	      
	      //第2行
	      List<CtdbBillingApp> ctdbBillingApps=ctdbBillingAppManager.find(hql.toString(), map);
	      SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	      if(ctdbBillingApps!=null){
	    	  for(int i=0;i<ctdbBillingApps.size();i++ ){
	    		  row = sheet.createRow(i+1);
	    		  CtdbBillingApp cba=ctdbBillingApps.get(i);
	    		  //项目
	    		  if(cba.getProjectName()!=null) {
						PoiExcelTool.addCell(row,cell,text,0,style2,"1",cba.getProjectName(),null);
					}else{
						PoiExcelTool.addCell(row,cell,text,0,style2,"1","",null);
					}
	    		  //合同
	    		  if(cba.getCtName()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,1,style2,"1",cba.getCtName(),null);
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,1,style2,"1","",null);
					}
	    	      //乙方
	    		  if(cba.getImpSupCd()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,2,style2,"1",cba.getImpSupCd(),null);
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,2,style2,"1","",null);
					}
	    	      //承包范围
	    		  if(cba.getCtArea()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,3,style2,"1",cba.getCtArea(),null);
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,3,style2,"1","",null);
					}
	    	      //合同性质
	    		  if(cba.getCtProperty()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,4,style2,"1",cba.getCtProperty(),null);
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,4,style2,"1","",null);
					}
	    	      //面积
	    		  if(cba.getArea()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,5,style2,"0",null,cba.getArea().doubleValue());
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,5,style2,"1","",null);
					}
	    	      //结算价
	    		  if(cba.getBillingPrice()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,6,style2,"1",null,cba.getBillingPrice().doubleValue());
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,6,style2,"1","",null);
					}
	    	      //单位造价
	    		  if(cba.getUnitPrice()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,7,style2,"0",null,cba.getUnitPrice().doubleValue());
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,7,style2,"1","",null);
					}
	    	      //结算日期
	    		  if(cba.getBillingDate()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,8,style2,"1",f.format(cba.getBillingDate()),null);
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,8,style2,"1","",null);
					}
	    	      //网批编号
	    		  if(cba.getApproveCd()!=null) {
	    			  PoiExcelTool.addCell(row,cell,text,9,style2,"1",cba.getApproveCd(),null);
	    		  }else{
						PoiExcelTool.addCell(row,cell,text,9,style2,"1","",null);
					}
	    		  
		      }
	    	  try {
	    			if(workbook!=null) {
						workbook.write(outExcelFileTmp);
					}
	  			} catch (IOException e) {
	  				e.printStackTrace();			
	  			}finally{
	  				if(workbook!=null){
	  					workbook=null;
	  				}
	  			}
	      }
		
	}

	
	
	@Override
	public String delete() throws Exception {
		return null;  
		
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;  		
	}

	@Override
	public String input() throws Exception {
		return null;  		
	}

	@Override
	public String list() throws Exception {
		return null;  		
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	@Override
	public String save() throws Exception {
		return null;  		
	}

	@Override
	public CtdbBillingApp getModel() {
		return null;  		
	}
	
	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	
	
	
	

}
  

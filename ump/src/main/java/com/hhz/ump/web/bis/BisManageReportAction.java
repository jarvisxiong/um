package com.hhz.ump.web.bis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisReport;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.web.vo.BisManageProjectVo;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商业费用报表
 * 
 * @author jiaoxf
 */
@Namespace("/bis")
public class BisManageReportAction extends ActionSupport {

	private static final long serialVersionUID = 2806347865620189538L;
	private String reportDate;
	private String year;
	private String month;
	private Map<String, BisReport> resultMap = new HashMap<String, BisReport>();
	
	private String reportTypeStr;	//查看的报表类型   total:现金流   manage:经营
	
	@Autowired
	private BisProjectManager bisProjectManager;
	private List<BisManageProjectVo> inlist = new ArrayList<BisManageProjectVo>();
	private List<BisManageProjectVo> outlist = new ArrayList<BisManageProjectVo>();
	private List<BisManageProjectVo> detailInlist = new ArrayList<BisManageProjectVo>();
	private List<BisManageProjectVo> detailOutlist = new ArrayList<BisManageProjectVo>();
	private List<BisManageProjectVo> totallist = new ArrayList<BisManageProjectVo>();
	private List<BisManageProjectVo> detaillist = new ArrayList<BisManageProjectVo>();
	private List<BisManageProjectVo> projects = new ArrayList<BisManageProjectVo>();
	private String projectIds;
	public String getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}
	@Override
	public String execute() throws Exception {
		if(StringUtils.isBlank(reportDate)) {
			reportDate = DateOperator.formatDate(DateOperator.addMonthes(new Date(), -1), "yyyy-MM");
		}
		reportTypeStr = "total";
		return SUCCESS;
	}
	public List<BisManageProjectVo> getDetailInlist() {
		return detailInlist;
	}
	public void setDetailInlist(List<BisManageProjectVo> detailInlist) {
		this.detailInlist = detailInlist;
	}
	public List<BisManageProjectVo> getDetailOutlist() {
		return detailOutlist;
	}
	public void setDetailOutlist(List<BisManageProjectVo> detailOutlist) {
		this.detailOutlist = detailOutlist;
	}
	public List<BisManageProjectVo> getProjects() {
		return projects;
	}
	public void setProjects(List<BisManageProjectVo> projects) {
		this.projects = projects;
	}
	
	public List<BisManageProjectVo> getInlist() {
		return inlist;
	}
	public List<BisManageProjectVo> getOutlist() {
		return outlist;
	}
	public void setOutlist(List<BisManageProjectVo> outlist) {
		this.outlist = outlist;
	}
	public void setInlist(List<BisManageProjectVo> inlist) {
		this.inlist = inlist;
	}
	public List<BisProject> getProjectSouth(){
		return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_SOUTH);
	}
	public List<BisProject> getProjectNorth(){
		return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_NORTH);
	}
	public List<BisProject> getProjectSh(){
		return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_SH);
	}
	
	/**
	 * 搜索经营情况汇总
	 */
	public String total(){
//		Date date = DateOperator.parse(reportDate, "yyyy-MM");
//		year = String.valueOf(DateOperator.getYear(date));
//		month = String.valueOf(DateOperator.getMonth12(date));
//		Struts2Utils.renderJson(bisProjectManager.getBisProjectTotal(year, currMonth));
//		inlist = bisProjectManager.getProjectInTotal(year, month);
		
		String statDimansion = "";
		String returnPage = "total";
		if("MANAGE".equalsIgnoreCase(reportTypeStr)) {
			statDimansion = "2";
			returnPage = "total-manage";
		} else {
			statDimansion = "1";
		}
		
		if(StringUtils.isBlank(reportDate)) {
			reportDate = DateOperator.formatDate(DateOperator.addMonthes(new Date(), -1), "yyyy-MM");
		}
		List<BisReport> bisReportList = bisProjectManager.getReportData(reportDate, "1", statDimansion, null);
		for(BisReport bisReport : bisReportList) {
			addToResultMap(bisReport);
		}
		
		return returnPage;
	}
	
	private void addToResultMap(BisReport bisReport) {
		String key = "";
		if("1".equals(bisReport.getOpenBl().trim()) && "2".equals(bisReport.getTimeDimansion().trim())) {
			key = bisReport.getChargeTypeCd()+"-1";
		} else if("1".equals(bisReport.getOpenBl().trim()) && "1".equals(bisReport.getTimeDimansion().trim())) {
			key = bisReport.getChargeTypeCd()+"-2";
		} else if("0".equals(bisReport.getOpenBl().trim()) && "2".equals(bisReport.getTimeDimansion().trim())) {
			key = bisReport.getChargeTypeCd()+"-3";
		} else if("0".equals(bisReport.getOpenBl().trim()) && "1".equals(bisReport.getTimeDimansion().trim())) {
			key = bisReport.getChargeTypeCd()+"-4";
		}
		
		resultMap.put(key, bisReport);
	}

	/**
	 * 搜索指定项目的汇总表
	 */
	public String detailProjects(){
		Date date = DateOperator.parse(reportDate, "yyyy-MM");
		String year = String.valueOf(DateOperator.getYear(date));
		String currMonth = String.valueOf(DateOperator.getMonth12(date));
		String bisProjectIds = Struts2Utils.getParameter("bisProjectIds");
		String[] projectss = bisProjectIds.split(",");
		List<BisProject> tmp  = bisProjectManager.getBisProjectList(projectss);
		for(int i = 0 ; i <tmp.size();i++){
			BisProject obj = tmp.get(i);
			BisManageProjectVo vo = new BisManageProjectVo();
			vo.setProjectid(obj.getBisProjectId());
			vo.setProjectName(obj.getProjectName());
			projects.add(vo);
		}
		detailInlist = bisProjectManager.getProjectInDetail(year, currMonth,bisProjectIds);
		//将支出置空
		detailOutlist = bisProjectManager.getProjectOutDetail(year, currMonth,bisProjectIds);
		
		detaillist = new ArrayList<BisManageProjectVo>();
		BisManageProjectVo o = new BisManageProjectVo();
		detaillist.add(o);
		//Struts2Utils.renderJson(bisProjectManager.getProjectDetail(year, currMonth, bisProjectIds));
		if("MANAGE".equalsIgnoreCase(reportTypeStr))
			return "detail-manage";
		return "detail";
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportTypeStr() {
		return reportTypeStr;
	}
	public void setReportTypeStr(String reportTypeStr) {
		this.reportTypeStr = reportTypeStr;
	}
	public List<BisManageProjectVo> getTotallist() {
		return totallist;
	}
	public void setTotallist(List<BisManageProjectVo> totallist) {
		this.totallist = totallist;
	}
	public List<BisManageProjectVo> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<BisManageProjectVo> detaillist) {
		this.detaillist = detaillist;
	}
	public Map<String, BisReport> getResultMap() {
		return resultMap;
	}
	
}

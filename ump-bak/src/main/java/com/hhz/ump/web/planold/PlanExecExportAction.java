package com.hhz.ump.web.planold;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.planold.PlanExecPlanDetailOldManager;
import com.hhz.ump.dao.planold.PlanExecutionPlanMainOldManager;
import com.hhz.ump.dao.planold.PlanExecutionPlanOldManager;
import com.hhz.ump.dao.planold.PlanProjectNodeRelOldManager;
import com.hhz.ump.entity.planold.PlanExecPlanDetailOld;
import com.hhz.ump.entity.planold.PlanExecutionPlanMainOld;
import com.hhz.ump.entity.planold.PlanExecutionPlanOld;
import com.hhz.ump.entity.planold.PlanProjectNodeRelOld;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.PlanExecExportUtil;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/planold")
@Results( 
		{ @Result(name = "export", type = "stream",params = { "contentType", "application/vnd.ms-excel", 
			"inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
		})
public class PlanExecExportAction extends ActionSupport{
	private static final long serialVersionUID = 3386773069776360371L;
	@Autowired
	private PlanExecutionPlanMainOldManager planExecutionPlanMainManager;
	@Autowired
	private PlanExecutionPlanOldManager planExecutionPlanManager;
	@Autowired
	private PlanExecPlanDetailOldManager planExecPlanDetailManager;
	@Autowired
	private PlanProjectNodeRelOldManager planProjectNodeRelOldManager;
	
 
	private InputStream is;
	private String downFileName;
	private String year;
	private String month;
	private String yearMonth;
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	public String getDownFileName() {
		return downFileName;
	}
	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
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
	
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	
	/**
	 * 按年、月,导出项目执行情况统计
	 * @param year
	 * @param month
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String export() throws UnsupportedEncodingException{
		
		if(StringUtils.isBlank(getYearMonth())){
			year = String.valueOf(DateOperator.getYear(new Date()));
			month = String.valueOf(DateOperator.getMonth12(new Date()));
			yearMonth = year + month;
		}else{
			yearMonth = yearMonth.trim();
			String[] arr = yearMonth.split("-");
			if(arr.length == 2){
				year = arr[0];
				month = arr[1];
				yearMonth = year + month;
			}
		}
		is = new PlanExecExportUtil().createExcelPlanFile(planExecPlanDetailManager.getStatData(yearMonth),year,month);
		String fileName = year + "年 " +month+ "月中心完成节点率统计.xls";
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	/**
	 * 按年、月,导出项目执行情况统计
	 * @param projectCd
	 * @param resOrgName
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String exportPlanExecPlanDetail() throws UnsupportedEncodingException{
		
		String projectCd = Struts2Utils.getParameter("projectCd");
		String resOrgName = Struts2Utils.getParameter("resOrgName");
		
		String planTypeCd = DictContants.PLAN_TYPE_EXC;
		
		if(StringUtils.isBlank(projectCd)){
			Struts2Utils.renderText("项目cd为空");
			return null;
		}

		//明细状态
		Map<String, String> statusMap = planExecPlanDetailManager.buildPlanDetailStatusMap();
		
		//计划主表
		PlanExecutionPlanMainOld main = planExecutionPlanMainManager.getExecutionPlanMainByProjCd(projectCd,planTypeCd);
		
		//所有节点
		List<PlanProjectNodeRelOld> planNodes = planProjectNodeRelOldManager.getValidProjNodes(projectCd, planTypeCd, null,null, resOrgName,null,false,null);

		//业态
		List<PlanExecutionPlanOld> plans = planExecutionPlanManager.getPlansPerProjCd(projectCd, planTypeCd, true);
		
		Map<String,String> nodeMap = new HashMap<String,String>();
		//明细数据
		String[] nodeIds = new String[planNodes.size()];
		PlanProjectNodeRelOld n = null;
		for (int i = 0; i < planNodes.size(); i++) {
			n = planNodes.get(i);
			nodeIds[i] = n.getPlanProjectNodeRelId();
			//映射
			nodeMap.put(n.getPlanProjectNodeRelId(), "["+ n.getTreeType()+"]"+n.getNodeName());
		}
		
		Map<String, PlanExecPlanDetailOld> planDetailMap = new HashMap<String, PlanExecPlanDetailOld>();
		List<PlanExecPlanDetailOld> detailList = planExecPlanDetailManager.getDetailsByProjPlanNodes(nodeIds, null);
		String key = null;
		for (PlanExecPlanDetailOld d : detailList) {
			//{relId_planCd: detail}
			key = d.getPlanProjectNodeRel().getPlanProjectNodeRelId() + "_" + d.getExecutionPlanCd();
			planDetailMap.put(key, d);
		}
		
		String fileName = ""+main.getProjectName()+" 执行计划明细";
		
		is = new PlanExecExportUtil().createExcelPlanDetailOld(fileName, planNodes, plans, planDetailMap, statusMap, nodeMap);
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		
		return "export";
	} 
}

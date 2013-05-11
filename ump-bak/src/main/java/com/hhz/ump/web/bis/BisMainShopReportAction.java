package com.hhz.ump.web.bis;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.dao.bis.BisMainShopReportManager;
import com.hhz.ump.entity.bis.BisMainShopReport;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.HelperUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 主力店工作周报
 * 
 * @author 张冬宝
 * 
 */
@Namespace("/bis")
public class BisMainShopReportAction extends ActionSupport {

	private static final long serialVersionUID = -1064950441266152542L;
	@Autowired
	private BisMainShopReportManager bisMainShopReportManager;
	private BisMainShopReport mainShopReport;
	private Map<String, List<BisProject>> projects;
	private Map<String, BisMainShopReport> mainShopReports;
	public HelperUtil helper=new HelperUtil();
	/**审核人*/
	private String updator;

	
	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	@Override
	public String execute() throws Exception {
			projects = bisMainShopReportManager.getBisProjects(Struts2Utils.getParameter("projectId"));
			//显示审核人的信息
			setUpdator(bisMainShopReportManager.getProjectFecher());
			return SUCCESS;
	}
	
	public String list() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startDay", Struts2Utils.getParameter("startDay"));
		param.put("endDay", Struts2Utils.getParameter("endDay"));
		/**筛选出做显示的项目，其他的忽略*/
		String projectIds = bisMainShopReportManager.getBisProjectIds(param);
		//projectIds = null;
		projects = bisMainShopReportManager.getBisProjects(projectIds);
		param.put("projects", projects);
		mainShopReports = bisMainShopReportManager.listBisMainShopReport(param);
		if(mainShopReports.isEmpty()){
			projects.clear();
		}
		//设定显示审核人的信息
		setUpdator(bisMainShopReportManager.getProjectFecher(Struts2Utils.getParameter("startDay"),Struts2Utils.getParameter("endDay")));
		return "list";
	}
	/**
	 * 向web打出审核人信息
	 */
	public String reGetUpdator(){
		Struts2Utils.renderText(bisMainShopReportManager.getProjectFecher(Struts2Utils.getParameter("startDay"),Struts2Utils.getParameter("endDay")));
		return null;
	}
	/**
	 * 新增周报
	 * @return
	 * @throws Exception
	 */
	public String add()throws Exception {
		String startDay = Struts2Utils.getParameter("startDay");
		String endDay = Struts2Utils.getParameter("endDay");
		if(StringUtils.isNotBlank(startDay)&&StringUtils.isNotBlank(endDay)) {
			projects = bisMainShopReportManager.getBisProjects(null);
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("projects", projects);
			param.put("startDay", getLastDate(Struts2Utils.getParameter("startDay")));
			param.put("endDay", getLastDate(Struts2Utils.getParameter("endDay")));
			mainShopReports = bisMainShopReportManager.listBisMainShopReport(param);
			
			if(mainShopReports.isEmpty()){
				for (String key : projects.keySet()) {
					List<BisProject> list = projects.get(key);
					BisMainShopReport bisMainShopReport = null;
					for (BisProject bisProject : list) {
						bisMainShopReport = new BisMainShopReport(bisProject.getBisProjectId(), startDay, endDay);
						bisMainShopReport.setStatusFlg("0");
						bisMainShopReportManager.saveBisMainShopReprot(bisMainShopReport);
					}
				}
			}else{
				for (BisMainShopReport shopReport : mainShopReports.values()) {
					BisMainShopReport bisMainShopReport = new BisMainShopReport(shopReport.getBisProjectId(), startDay, endDay);
					bisMainShopReport.setStatusFlg("0");
					bisMainShopReport.setSuperMarket(getVlaue(shopReport.getSuperMarket()));
					bisMainShopReport.setDepartmentStore(getVlaue(shopReport.getDepartmentStore()));
					bisMainShopReport.setCinema(getVlaue(shopReport.getCinema()));
					bisMainShopReport.setVideoGames(getVlaue(shopReport.getVideoGames()));
					bisMainShopReport.setKtv(getVlaue(shopReport.getKtv()));
					bisMainShopReport.setElectricEquipment(getVlaue(shopReport.getElectricEquipment()));
					bisMainShopReport.setOtherDesc(getVlaue(shopReport.getOtherDesc()));

					bisMainShopReportManager.saveBisMainShopReprot(bisMainShopReport);
					
				}
			}
		}
		return list();
	}
	/**
	 * 提交周报
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public String submit() throws Exception {
	
		this.setMainShopReports();
		for (BisMainShopReport mainShopReport : mainShopReports.values()) {
			mainShopReport.setStatusFlg("1");
			bisMainShopReportManager.saveBisMainShopReprot(mainShopReport);
		}
		return list();
	}
	/**
	 * 确认周报
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public String affirm() throws Exception {
	
		this.setMainShopReports();
		for (BisMainShopReport mainShopReport : mainShopReports.values()) {
			mainShopReport.setStatusFlg("2");
			bisMainShopReportManager.saveBisMainShopReprot(mainShopReport);
		}
		return list();
	}

	/**
	 * 驳回周报
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public String reject() throws Exception {
		this.setMainShopReports();
		for (BisMainShopReport mainShopReport : mainShopReports.values()) {
			mainShopReport.setStatusFlg("3");
			bisMainShopReportManager.saveBisMainShopReprot(mainShopReport);
		}
		return list();
	}
	private void setMainShopReports(){
		projects = bisMainShopReportManager.getBisProjects(null);
		Map<String, Object> param = new HashMap<String, Object>();

		String startDay = Struts2Utils.getParameter("startDay");
		String endDay = Struts2Utils.getParameter("endDay");
		if(!projects.isEmpty()&& StringUtils.isNotBlank(startDay)&&StringUtils.isNotBlank(endDay)) {
			param.put("projects", projects);
			param.put("startDay", Struts2Utils.getParameter("startDay"));
			param.put("endDay", Struts2Utils.getParameter("endDay"));
			mainShopReports = bisMainShopReportManager.listBisMainShopReport(param);
		}else{
			mainShopReports.clear();
			projects.clear();
		}
	}
	private String getVlaue(String val) {
		if(val!=null && val.length()>18 && val.substring(0, 18).equals("<font color='red'>") && val.substring(val.length()-7).equals("</font>"))

			return val.substring(18,val.length()-7);
		return val;

	}
	private String getLastDate(String date) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.yyyy_mm_dd.parse(date));
			calendar.add(Calendar.DATE, -7);
			return DateUtil.yyyy_mm_dd.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	@Override
	public String input() throws Exception {
			projects = bisMainShopReportManager.getBisProjects(null);
		if (StringUtils.isNotBlank(Struts2Utils.getParameter("id"))) {
			mainShopReport = bisMainShopReportManager.getEntity(Struts2Utils
					.getParameter("id"));
		} else {
			mainShopReport = new BisMainShopReport();
			mainShopReport.setBisProjectId(Struts2Utils.getParameter("pId"));
		}
		return INPUT;
	}

	public String save() throws Exception {
		if (StringUtils.isNotBlank(mainShopReport.getBisMainShopReprotId())) {
			BisMainShopReport entity = bisMainShopReportManager
					.getEntity(mainShopReport.getBisMainShopReprotId());
			entity.setSuperMarket(mainShopReport.getSuperMarket());
			entity.setDepartmentStore(mainShopReport.getDepartmentStore());
			entity.setCinema(mainShopReport.getCinema());
			entity.setVideoGames(mainShopReport.getVideoGames());
			entity.setKtv(mainShopReport.getKtv());
			entity.setElectricEquipment(mainShopReport.getElectricEquipment());
			entity.setOtherDesc(mainShopReport.getOtherDesc());
			bisMainShopReportManager.saveBisMainShopReprot(entity);
		} else {
			bisMainShopReportManager.saveBisMainShopReprot(mainShopReport);
		}
		Struts2Utils.renderText("true");
		return null;
	}
	public String getStartEndDay() throws Exception {

		String year = Struts2Utils.getParameter("year");
		String month = Struts2Utils.getParameter("month");
		String weekOfMonth = Struts2Utils.getParameter("weekOfMonth");
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)
				&& StringUtils.isNotBlank(weekOfMonth)) {
			String[] weekStartEndDay = DateUtil.getWeekStartEndDay(
					Integer.parseInt(year), Integer.parseInt(month),
					Integer.parseInt(weekOfMonth));
			if (weekStartEndDay != null) {
				Struts2Utils.renderText(weekStartEndDay[0] + "~"
						+ weekStartEndDay[1]);
			}
		}
		return null;
	}
	public BisMainShopReport getMainShopReport() {
		return mainShopReport;
	}

	public void setMainShopReport(BisMainShopReport mainShopReport) {
		this.mainShopReport = mainShopReport;
	}

	public Map<String, List<BisProject>> getProjects() {
		return projects;
	}

	public Map<String, BisMainShopReport> getMainShopReports() {
		return mainShopReports;
	}

}

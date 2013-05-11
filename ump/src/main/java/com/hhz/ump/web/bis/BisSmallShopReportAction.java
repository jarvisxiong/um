package com.hhz.ump.web.bis;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisSmallShopReportManager;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisSmallShopReport;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.web.bis.vo.VoBisSmallShopReport;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 小商铺工作周报
 * 
 * @author Aspenn
 * 
 */
@Namespace("/bis")
public class BisSmallShopReportAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BisSmallShopReportManager bisSmallShopReportManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	
	private BisSmallShopReport smallShopReport;
	private List<BisProject> projects;
	private List<VoBisSmallShopReport> voProjects;
	private List<BisSmallShopReport> smallShopReports;
	public HelperUtil helper=new HelperUtil();
	/**是小商铺还是商业街*/
	private String typeLogo;
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
//			projects = bisSmallShopReportManager.getBisProjects(Struts2Utils.getParameter("projectId"));
			projects = bisSmallShopReportManager.getBisProjectsBeta(Struts2Utils.getParameter("projectId"));
			//显示审核人的信息
			setUpdator(bisSmallShopReportManager.getProjectFecher());
			return SUCCESS;
	}
	/**
	 * 搜索小商铺集合
	 * @param projectId
	 * @param startDay
	 * @param endDay
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		String tmpStartDate = Struts2Utils.getParameter("startDay");
		String tmpEndDate = Struts2Utils.getParameter("endDay");
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("startDay", tmpStartDate);
		values.put("endDay", tmpEndDate);
		 
		voProjects = bisSmallShopReportManager.getReportVoList(tmpStartDate,tmpEndDate,0);
		values.put("projects", voProjects);
		//设定显示审核人的信息
		setUpdator(bisSmallShopReportManager.getProjectFecher(Struts2Utils.getParameter("startDay"),Struts2Utils.getParameter("endDay")));
		return "list";
	}
	/**
	 * 向web打出审核人信息
	 */
	public String reGetUpdator(){
		Struts2Utils.renderText(bisSmallShopReportManager.getProjectFecher(Struts2Utils.getParameter("startDay"),Struts2Utils.getParameter("endDay")));
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
			List<VoBisSmallShopReport> voList = bisSmallShopReportManager.getReportVoList(getLastDate(Struts2Utils.getParameter("startDay")),getLastDate(Struts2Utils.getParameter("endDay")),1);
			//郑州专用
			List<VoBisSmallShopReport> voList4zz = new ArrayList<VoBisSmallShopReport>();
			BisSmallShopReport po = null;
			for(VoBisSmallShopReport vo:voList){
				
				//如果该字段为空，则表示上个周期亦未添加报表,需要重新添加。
				if(StringUtils.isBlank(vo.getBisSmallShopReportId())){
					po = new BisSmallShopReport(vo.getBisProjectId(),vo.getBisItemCategoryId(),startDay,endDay);
					if("郑州".equals(vo.getCity())){//如果是郑州，多建立一条数据
						//重复插入使用
						voList4zz.add(vo);
					}
				}else{//从上月中获取已有的值
					po = new BisSmallShopReport(startDay,endDay,vo.getBisProjectId(),vo.getBisItemCategoryId(),vo.getMerchantsArea2012(),
							vo.getMerchantsArea(),vo.getContractAreaNew(),vo.getContractArea(),vo.getSigningRate(),vo.getPlanSigningRate(),vo.getOpenedRateShop(),
							vo.getOpenedRate(),vo.getInvestmentArea(),vo.getHeaderSign(),vo.getSmallShopStatus(),vo.getRemark());
				}
				po.setSmallShopStatus("0");
				bisSmallShopReportManager.saveBisSmallShopReport(po);
			}
			for (VoBisSmallShopReport vo : voList4zz) {
				po = new BisSmallShopReport(vo.getBisProjectId(),vo.getBisItemCategoryId(),startDay,endDay);
				po.setSmallShopStatus("0");
				bisSmallShopReportManager.saveBisSmallShopReport(po);
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

		this.setSmallShopReports();
		for (BisSmallShopReport smallShopReport : smallShopReports) {
			smallShopReport.setSmallShopStatus("1");
			bisSmallShopReportManager.saveBisSmallShopReport(smallShopReport);
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

		this.setSmallShopReports();
		for (BisSmallShopReport smallShopReport : smallShopReports) {
			smallShopReport.setSmallShopStatus("2");
			bisSmallShopReportManager.saveBisSmallShopReport(smallShopReport);
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

		this.setSmallShopReports();
		for (BisSmallShopReport smallShopReport : smallShopReports) {
			smallShopReport.setSmallShopStatus("3");
			bisSmallShopReportManager.saveBisSmallShopReport(smallShopReport);
		}
		return list();
	}
	/***
	 * 初始化周报
	 */
	private void setSmallShopReports(){
//		projects = bisSmallShopReportManager.getBisProjects(null);
		Map<String, Object> param = new HashMap<String, Object>();

		String startDay = Struts2Utils.getParameter("startDay");
		String endDay = Struts2Utils.getParameter("endDay");
		if(StringUtils.isNotBlank(startDay)&&StringUtils.isNotBlank(endDay)) {
//		if(!projects.isEmpty()&& StringUtils.isNotBlank(startDay)&&StringUtils.isNotBlank(endDay)) {
			param.put("projects", projects);
			param.put("startDay", Struts2Utils.getParameter("startDay"));
			param.put("endDay", Struts2Utils.getParameter("endDay"));
			smallShopReports = bisSmallShopReportManager.listBisSmallShopReport(param);
		}else{
			smallShopReports.clear();
			projects.clear();
		}
	}
	private String getVlaue(String val) {
		if(val!=null && val.length()>18 && val.substring(0, 18).equals("<font color='red'>") && val.substring(val.length()-7).equals("</font>"))
			return val.substring(18,val.length()-7);
		return val;

	}
	/***
	 * 获取上周时间
	 * @param date
	 * @return
	 */
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
	/**
	 * 根据cd搜索城市名字的方法
	 * @return
	 */
	public String getCityName(String proId) {
		BisProject bisPro = bisProjectManager.getBisProjectByProjId(proId);
		if(bisPro!=null&&!bisPro.getBisProjectId().isEmpty())
			return bisPro.getCity();
		else
			return "";
	}
	@Override
	/**到新增修改页面*/
	public String input() throws Exception {
		projects = bisSmallShopReportManager.getBisProjects(null);
		String reportId = Struts2Utils.getParameter("reportId");
		String proId = Struts2Utils.getParameter("proId");
		String categoryId = Struts2Utils.getParameter("cId");
		
		if (StringUtils.isNotBlank(reportId)&&StringUtils.isNotBlank(proId)&&StringUtils.isNotBlank(categoryId)){
			smallShopReport = bisSmallShopReportManager.getEntity(reportId);
		} else {
			smallShopReport = new BisSmallShopReport();
			smallShopReport.setBisProjectId(proId);
			smallShopReport.setBisItemCategoryId(categoryId);
		}
		return INPUT;
	}
	/***
	 * 保存数据
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		if (StringUtils.isNotBlank(smallShopReport.getBisSmallShopReportId())) {
			BisSmallShopReport entity = bisSmallShopReportManager
					.getEntity(smallShopReport.getBisSmallShopReportId());
			entity.setBisItemCategoryId(smallShopReport.getBisProjectId());
			entity.setBisItemCategoryId(smallShopReport.getBisItemCategoryId());
			entity.setMerchantsArea2012(smallShopReport.getMerchantsArea2012());
			entity.setMerchantsArea(smallShopReport.getMerchantsArea());
			entity.setContractArea(smallShopReport.getContractArea());
			entity.setContractAreaNew(smallShopReport.getContractAreaNew());
			entity.setSigningRate(smallShopReport.getSigningRate());
			entity.setPlanSigningRate(smallShopReport.getPlanSigningRate());
			entity.setOpenedRate(smallShopReport.getOpenedRate());
			entity.setOpenedRateShop(smallShopReport.getOpenedRateShop());
			entity.setInvestmentArea(smallShopReport.getInvestmentArea());
			entity.setHeaderSign(smallShopReport.getHeaderSign());
			entity.setRemark(smallShopReport.getRemark());
			bisSmallShopReportManager.saveBisSmallShopReport(entity);
		} else {
			bisSmallShopReportManager.saveBisSmallShopReport(smallShopReport);
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
			String[] weekStartEndDay = DateUtil.getWeekStartEndDay_rpt(
					Integer.parseInt(year), Integer.parseInt(month),
					Integer.parseInt(weekOfMonth));
			System.out.println(weekStartEndDay);
			if (weekStartEndDay != null) {
				Struts2Utils.renderText(weekStartEndDay[0] + "~"
						+ weekStartEndDay[1]);
			}
		}
		return null;
	}

	
	public List<BisProject> getProjects() {
		return projects;
	}

	public BisSmallShopReportManager getBisSmallShopReportManager() {
		return bisSmallShopReportManager;
	}

	public BisSmallShopReport getSmallShopReport() {
		return smallShopReport;
	}

	public void setSmallShopReport(BisSmallShopReport smallShopReport) {
		this.smallShopReport = smallShopReport;
	}

	public List<BisSmallShopReport> getSmallShopReports() {
		return smallShopReports;
	}

	public void setSmallShopReports(List<BisSmallShopReport> smallShopReports) {
		this.smallShopReports = smallShopReports;
	}

	public String getTypeLogo() {
		return typeLogo;
	}

	public void setTypeLogo(String typeLogo) {
		this.typeLogo = typeLogo;
	}

	public List<VoBisSmallShopReport> getVoProjects() {
		return voProjects;
	}

	public void setVoProjects(List<VoBisSmallShopReport> voProjects) {
		this.voProjects = voProjects;
	}
	
}

package com.hhz.ump.web.plan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.plan.PlanExecutionPlanMainManager;
import com.hhz.ump.entity.plan.PlanCenterDetailsVO;
import com.hhz.ump.util.Constants;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.opensymphony.xwork2.ActionSupport;

public class PlanCenterQueryAction extends ActionSupport {

	private static final long serialVersionUID = -353552570930219691L;

	@Autowired
	private PlanExecutionPlanMainManager planMainManager;

	private String centerCd;
	private String centerName;

	List<PlanCenterDetailsVO> centers;

	public String queryPlanCenter() throws Exception {
		// 如果是中心用户权限
		if (SpringSecurityUtils.hasRole(Constants.PLAN_WORK_USER)||SpringSecurityUtils.hasRole(Constants.PLAN_WORK_VIEW)) {
			String curCenterCd = SpringSecurityUtils.getCurrentCenterCd();
			if (curCenterCd == null||"".equals(curCenterCd)) {
				centerCd = SpringSecurityUtils.getCurrentDeptCd();
				centerName = SpringSecurityUtils.getCurrentDeptName();
			} else {
				WsPlasOrg org = SpringSecurityUtils
				.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
				if(org!=null){
					centerCd = org.getOrgCd();
					centerName = org.getOrgName();
				}else{
					centerCd = SpringSecurityUtils.getCurrentDeptCd();
					centerName = SpringSecurityUtils.getCurrentDeptName();
				}
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = format.format(DateOperator.getDateNow());
			String nextMonthDate = format.format(DateOperator.addMonthes(
					DateOperator.getDateNow(), 1));
			centers = planMainManager.fetchCenterDetails(nowDate, nextMonthDate, centerCd, "",true);
			Struts2Utils.getRequest().setAttribute("centerCd", centerCd);
			Struts2Utils.getRequest().setAttribute("now_year", DateOperator.getYear(new Date()));
			Struts2Utils.getRequest().setAttribute("now_week_of_year", DateOperator.getWeekOfYear(new Date()));		
		}
		return "index";
	}

	public String getCenterCd() {
		return centerCd;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	public List<PlanCenterDetailsVO> getCenters() {
		return centers;
	}

	public void setCenters(List<PlanCenterDetailsVO> centers) {
		this.centers = centers;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

}

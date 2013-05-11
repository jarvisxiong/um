package com.hhz.ump.web.res;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.app.CommonManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.res.ResApproveHis;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResApproveNode;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 网上审批延期次数
 * 
 * @author baolm
 *
 * 2011-5-4
 */
@Namespace("/res")
@Results( {
	@Result(name = "reload", location = "res-delay-times!list.action", type = "redirect", params = { "approveDate", "${approveDate}",
			"userCd", "${userCd}", "delayTimesSmall", "${delayTimesSmall}", "delayTimesLarge", "${delayTimesLarge}", "page.pageNo", "${page.pageNo}" })
})
public class ResDelayTimesAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4385639965963813368L;

	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private ResApproveInfoManager resApproveInfoManager;
	
	private Page page = new Page(15);
	
	private String approveDate; // 审批时间
	private String userCd; // 审批人CD
	private String userName; // 审批人姓名
	private String delayTimesSmall; // 延期次数
	private String delayTimesLarge; // 延期次数
	
	private String selectedOrderBy; // 用户点选的排序字段名
	private String selectedOrder; // 用户点选的排序方式
	
	private String detailId;
	private String detailUserCd;
	
	private List details;
	
	@Override
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}
	
	public String list() {
		
		if(StringUtils.isBlank(approveDate)){
			approveDate = DateOperator.formatDate(new Date(), "yyyy-MM");;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCd", userCd);
		map.put("approveDate", approveDate);
		map.put("delayTimesSmall", delayTimesSmall);
		map.put("delayTimesLarge", delayTimesLarge);
		
		StringBuilder sql = new StringBuilder("");
		sql.append("SELECT * from (");
		sql.append(" SELECT USER_CD, TO_CHAR (APPROVE_DATE, 'yyyy-MM') AS year_month, SUM (flag) CNT")
			.append(" from VW_RES_APPROVE_DELAY")
			.append(" GROUP BY USER_CD, TO_CHAR(APPROVE_DATE, 'yyyy-MM') ");
		sql.append(") h WHERE h.year_month = :approveDate");
		if(StringUtils.isNotBlank(userCd)){
			sql.append(" AND h.USER_CD = :userCd");
		}
		if(StringUtils.isNotBlank(delayTimesSmall)){
			sql.append(" AND h.CNT >= :delayTimesSmall");
		}
		if(StringUtils.isNotBlank(delayTimesLarge)){
			sql.append(" AND h.CNT < :delayTimesLarge");
		}
		sql.append(" ORDER BY cnt desc, USER_CD");
		
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		
		page = commonManager.findPageSql(page, sql.toString(), map, mapClazz);
		return "list";
	}
	
	public String detail() {
		
		String userCd = Struts2Utils.getParameter("userCd");
		String yearMonth = Struts2Utils.getParameter("yearMonth");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCd", userCd);
		map.put("yearMonth", yearMonth.trim());
		
		String sql = "SELECT HOURS,to_char(PRE_APPROVE_DATE,'yyyy-MM-dd HH24:mi'),to_char(APPROVE_DATE,'yyyy-MM-dd HH24:mi'),USER_CD,USER_NAME,APPROVE_OPTION_CD,TIME_LIMIT,VERIFY_CD,RES_APPROVE_INFO_ID,TITLE_NAME,LAND_PROJECT,AUTH_TYPE_CD,FLAG from VW_RES_APPROVE_DELAY where USER_CD = :userCd and TO_CHAR(APPROVE_DATE, 'yyyy-MM') = :yearMonth order by PRE_APPROVE_DATE desc";
		details = commonManager.findBySql(sql, map);
		
		return "detail";
	}
	
	public String cancelTimeLimit() {

		ResApproveInfo resApproveInfo = resApproveInfoManager.getEntity(detailId);
		resApproveInfo.setTimeLimit(null);
		for (ResApproveNode node : resApproveInfo.getResApproveNodes()) {
			if (StringUtils.equals(node.getUserCd(), detailUserCd)) {
				node.setTimeLimit(null);
			}
		}
		for (ResApproveHis his : resApproveInfo.getResApproveHises()) {
			if (StringUtils.equals(his.getUserCd(), detailUserCd)) {
				his.setTimeLimit(null);
			}
		}
		resApproveInfoManager.getDao().save(resApproveInfo);

		return null;
	}

	public CommonManager getCommonManager() {
		return commonManager;
	}

	public void setCommonManager(CommonManager commonManager) {
		this.commonManager = commonManager;
	}

	public ResApproveInfoManager getResApproveInfoManager() {
		return resApproveInfoManager;
	}

	public void setResApproveInfoManager(ResApproveInfoManager resApproveInfoManager) {
		this.resApproveInfoManager = resApproveInfoManager;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDelayTimesSmall() {
		return delayTimesSmall;
	}

	public void setDelayTimesSmall(String delayTimesSmall) {
		this.delayTimesSmall = delayTimesSmall;
	}

	public String getDelayTimesLarge() {
		return delayTimesLarge;
	}

	public void setDelayTimesLarge(String delayTimesLarge) {
		this.delayTimesLarge = delayTimesLarge;
	}

	public String getSelectedOrderBy() {
		return selectedOrderBy;
	}

	public void setSelectedOrderBy(String selectedOrderBy) {
		this.selectedOrderBy = selectedOrderBy;
	}

	public String getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(String selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public List getDetails() {
		return details;
	}

	public void setDetails(List details) {
		this.details = details;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getDetailUserCd() {
		return detailUserCd;
	}

	public void setDetailUserCd(String detailUserCd) {
		this.detailUserCd = detailUserCd;
	}
	
}

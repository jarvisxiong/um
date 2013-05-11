package com.hhz.ump.web.res;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.dao.app.CommonManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 网上审批历史记录
 * 
 * @author baolm
 *
 * 2011-5-4
 */
@Namespace("/res")
public class ResApproveHisAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4385639965963813368L;

	@Autowired
	private CommonManager commonManager;
	
	@Autowired
	private ResApproveInfoManager resApproveInfoManager;
	
	private Page page = new Page(15);
	
	private String isDelay; // 是否延期
	private String isApproach; //接近延期
	private String userCd; // 审批人CD
	private String userName; // 审批人姓名
	private String preDateBegin; // 审批开始时间
	private String preDateEnd; // 审批结束时间
	
	private String selectedOrderBy; // 用户点选的排序字段名
	private String selectedOrder; // 用户点选的排序方式
	
	@Override
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}
	
	public String list() {
		
		if(StringUtils.isBlank(isDelay)){
			isDelay = "false";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userCd", userCd);
		map.put("preDateBegin", preDateBegin);
		map.put("preDateEnd", preDateEnd);
		StringBuilder sql = new StringBuilder("");
		if(StringUtils.equals(isDelay, "true")){
			sql.append(" select HOURS,to_char(PRE_APPROVE_DATE,'yyyy-MM-dd HH24:mi'),to_char(APPROVE_DATE,'yyyy-MM-dd HH24:mi'),USER_CD,USER_NAME,APPROVE_OPTION_CD,TIME_LIMIT,VERIFY_CD,RES_APPROVE_INFO_ID,TITLE_NAME,LAND_PROJECT,AUTH_TYPE_CD,FLAG from VW_RES_APPROVE_DELAY where FLAG = 1");
		} else if(StringUtils.equals(isApproach, "true")) {
			sql.append(" select REDUCE,to_char(PRE_APPROVE_DATE,'yyyy-MM-dd HH24:mi'),to_char(APPROVE_DATE,'yyyy-MM-dd HH24:mi'),USER_CD,USER_NAME,APPROVE_OPTION_CD,TIME_LIMIT,VERIFY_CD,RES_APPROVE_INFO_ID,TITLE_NAME,LAND_PROJECT,AUTH_TYPE_CD,FLAG,APPROVE_CD from VW_RES_APPROVE_TIME_REDUCE where 1 = 1");
		} else {
			sql.append(" select HOURS,to_char(PRE_APPROVE_DATE,'yyyy-MM-dd HH24:mi'),to_char(APPROVE_DATE,'yyyy-MM-dd HH24:mi'),USER_CD,USER_NAME,APPROVE_OPTION_CD,TIME_LIMIT,VERIFY_CD,RES_APPROVE_INFO_ID,TITLE_NAME,LAND_PROJECT,AUTH_TYPE_CD,FLAG from VW_RES_APPROVE_HIS where 1 = 1");
		}
		if(StringUtils.isNotBlank(userCd)){
			sql.append(" and USER_CD = :userCd");
		}
		if(StringUtils.isNotBlank(preDateBegin)){
			sql.append(" and PRE_APPROVE_DATE > to_date(:preDateBegin,'yyyy-MM-dd')");
		}
		if(StringUtils.isNotBlank(preDateEnd)){
			sql.append(" AND PRE_APPROVE_DATE < to_date(:preDateEnd,'yyyy-MM-dd')+1");
		}
		if(StringUtils.isNotBlank(selectedOrderBy)){
			sql.append(" order by ").append(selectedOrderBy).append(" ").append(selectedOrder);
		} else if(StringUtils.equals(isApproach, "true")) {
			sql.append(" order by REDUCE asc");
		} else {
			sql.append(" order by USER_CD asc, PRE_APPROVE_DATE desc nulls last, APPROVE_DATE desc");
		}
		
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		
		page = commonManager.findPageSql(page, sql.toString(), map, mapClazz);
		return "list";
	}
	
	public String notifyUser()throws Exception  {
		
		String userCd = Struts2Utils.getParameter("userCd");
		String id = Struts2Utils.getParameter("id");
		String typeCd = Struts2Utils.getParameter("typeCd");
		String typeName = Struts2Utils.getParameter("typeName");
		String titleName = Struts2Utils.getParameter("titleName");
		String reduce = Struts2Utils.getParameter("reduce");
		String approveCd = Struts2Utils.getParameter("approveCd");
		
		String title = "【网批接近延期提醒】";
		StringBuffer smsMsg = new StringBuffer();
		smsMsg.append(title).append(":").append("\n");
		smsMsg.append("有您相关的网上审批即将到期").append("\n");
		smsMsg.append("剩余时间：").append(reduce).append("小时").append("\n");
		smsMsg.append("审批编号：").append(approveCd).append("\n");
		
		StringBuffer emailMsg = new StringBuffer();
		emailMsg.append(title).append(":").append("<br/>");
		emailMsg.append("有您相关的网上审批即将到期").append("<br/>");
		emailMsg.append("剩余时间：").append(reduce).append("小时").append("<br/>");
		emailMsg.append("编　　号：").append(approveCd).append("<br/>");
		emailMsg.append("类　　别：").append(typeName).append("<br/>");
		emailMsg.append("标　　题：").append(titleName).append("<br/>");
		emailMsg.append("<div style=\"width: 30px;cursor: pointer;text-decoration:underline;color:blue;\" onclick=\"parent.parent.showAll('")
				.append(Struts2Utils.getRequest().getContextPath()).append("/res/res-approve-info.action?id=").append(id)
				.append("&resAuthTypeCd=").append(typeCd).append("','resApprove');\">进入</div>");
		
		resApproveInfoManager.notifyUser(userCd, title, smsMsg.toString(), emailMsg.toString());
		Struts2Utils.renderText("success");
		return null;
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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getIsDelay() {
		return isDelay;
	}

	public void setIsDelay(String isDelay) {
		this.isDelay = isDelay;
	}

	public String getIsApproach() {
		return isApproach;
	}

	public void setIsApproach(String isApproach) {
		this.isApproach = isApproach;
	}

	public String getPreDateBegin() {
		return preDateBegin;
	}

	public void setPreDateBegin(String preDateBegin) {
		this.preDateBegin = preDateBegin;
	}

	public String getPreDateEnd() {
		return preDateEnd;
	}

	public void setPreDateEnd(String preDateEnd) {
		this.preDateEnd = preDateEnd;
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
	
}

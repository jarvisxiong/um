package com.hhz.ump.web.wap;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.dao.res.ResNodeManager;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.res.ResApproveInfoAction;
import com.hhz.ump.web.res.ResApproveInfoVO;
import com.hhz.ump.web.res.ResConstants;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

/**
 * <p>
 * 手机版网上审批
 * </p>
 * 
 * @author hy
 * @version 1.00 2011-10-10
 */

@Namespace("/wap")
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "wap-approve-info.action", type = "redirect", params = {
				"resAuthTypeCd", "${resAuthTypeCd}", "approveCdSrh", "${approveCdSrh}", "selectNodeCd",
				"${selectNodeCd}", "listMode", "${listMode}", "qsCondition", "${qsCondition}", "selectDealOpinion",
				"${selectDealOpinion}", "selectDutyOpinion", "${selectDutyOpinion}", "selectRecoOpinion",
				"${selectRecoOpinion}", "filter_LIKES_statusCd", "${filter_LIKES_statusCd}", "filter_GED_startDate",
				"${filter_GED_startDate}", "filter_LTD_startDate", "${filter_LTD_startDate}",
				"filter_LIKES_landproject", "${filter_LIKES_landproject}", "filter_LIKES_titlename",
				"${filter_LIKES_titlename}", "filter_LIKES_approveUserName", "${filter_LIKES_approveUserName}",
				"filter_LIKES_approveUserCd", "${filter_LIKES_approveUserCd}", "filter_LIKES_userName",
				"${filter_LIKES_userName}", "filter_LIKES_randomNo", "${filter_LIKES_randomNo}",
				"filter_LIKES_authTypeCd", "${filter_LIKES_authTypeCd}", "filter_LIKES_authTypeName",
				"${filter_LIKES_authTypeName}", "page.pageNo", "${page.pageNo}" }),
		@Result(name = "say", location = "/WEB-INF/content/wap/wap-approve-info-say.ftl", type = "freemarker"),
		@Result(name = "approve", location = "/WEB-INF/content/wap/wap-approve-info-approve.ftl", type = "freemarker") })
public class WapApproveInfoAction extends ResApproveInfoAction {
	private static final long serialVersionUID = -5337955004566695936L;

	@Autowired
	private ResNodeManager resNodeManager;

	@Autowired
	private ResApproveInfoManager resApproveInfoManager;

	/**
	 * 顶级机构节点
	 */
	private List<TreePanelNode> rootNoodes;
	private List<WsPlasOrg> childOrgs;
	private List<WsPlasUser> childUsers;
	// 搜索员工
	private List<WsPlasUser> searchUserResult;
	public List<TreePanelNode> getRootNoodes() {
		return rootNoodes;
	}

	@Override
	public String load() throws Exception {
		if (StringUtils.equals(ResConstants.QUICK_SEARCH_MYDEAL, getQsCondition()))
			return myDeal();
		else if (StringUtils.equals(ResConstants.QUICK_SEARCH_MYDUTY, getQsCondition()))
			return myDuty();
		return myDuty();
	}

	public String myDeal() {
		return super.queryMyDeal();
	}

	// 我的审批
	public String myDuty() {
		return super.queryMyDuty();
	}

	@Override
	public String list() throws Exception {
		if (StringUtils.equals(ResConstants.QUICK_SEARCH_MYDEAL, super.getQsCondition())){
			//myDeal();
		}
		return SUCCESS;
	}
	
	/**
	 * 我的审批中的节点
	 */
	@Override
	public Map<String, String> buildDutyOpinions() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(ResConstants.RES_APPROVE_STATUS_ING, "审批中");
		// map.put(RES_APPROVE_STATUS_MYDUTY, "即将审批");
		map.put(ResConstants.RES_APPROVE_STATUS_SHARE, "共享给我");
		map.put(ResConstants.RES_APPROVE_STATUS_ACCT, "其他");
		return map;
	}

	public String reinput() throws Exception {
		return "reinput";
	}

	public String doShare() throws Exception {
		initRootNodes();
		return "share";
	}
	public String userChoose() throws Exception {
		initRootNodes();
		return "userChoose";
	}

	private String goNormalPattern() throws Exception {
		Date start = new Date();
		page.setPageSize(30);
		Map<String, Object> pram = new HashMap<String, Object>();
		String hql = buildSql(pram);
		Map<String, Class> mapClazz=new HashMap<String, Class>();
		mapClazz.put("res", ResApproveInfo.class);
		page = resApproveInfoManager.findPageSql(page, hql, pram,mapClazz);
		Map<String, ResApproveInfoVO> mapMyApprove = new HashMap<String, ResApproveInfoVO>();
		Map<String, ResApproveInfoVO> mapByShare = new HashMap<String, ResApproveInfoVO>();
		String uiid = SpringSecurityUtils.getCurrentUiid();
		for (ResApproveInfo rai : page.getResult()) {
			if (isMyApprove(rai)) {
				// 我的审批
				addToMap(rai, mapMyApprove);
			} else if (isSharedUser(rai)) {
				// 共享给我
				addToMap(rai, mapByShare);
			}
		}
		super.getByApprove().addAll(mapMyApprove.values());
		super.getByShare().addAll(mapByShare.values());
		super.getMapResult().put("我的审批", super.getByApprove());
		super.getMapResult().put("我的共享", super.getByShare());
		Date end = new Date();
		logger.error("耗时:" + (end.getTime() - start.getTime()) / 1000d);
		return "load";
	}

	/**
	 * <p>
	 * 判断是否是我的审批
	 * </p>
	 * 
	 * @param approveInfo
	 * @return
	 */
	private boolean isMyApprove(ResApproveInfo approveInfo) {
		boolean flag = false;
		if (StringUtils.equals(approveInfo.getApproveUserCd(), SpringSecurityUtils.getCurrentUiid())) {
			flag = true;
		}
		if (approveInfo.getApproveUserCd() != null
				&& approveInfo.getApproveUserCd().contains(SpringSecurityUtils.getCurrentUiid() + ";")) {
			flag = true;
		}
		return flag;
	}

	private void addToMap(ResApproveInfo rai, Map<String, ResApproveInfoVO> map) {
		String statusCd = rai.getStatusCd();
		if (!map.keySet().contains(statusCd)) {
			map.put(statusCd, new ResApproveInfoVO(statusCd));
		}
		map.get(statusCd).add(rai);
	}
	private String buildSql(Map<String, Object> pram) {
		String userCd = SpringSecurityUtils.getCurrentUiid();
		pram.put("userCd", userCd);
		
		StringBuilder hql = new StringBuilder("from ");
		hql.append("(");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("exists(");
		hql.append(" select 1 from Res_Approve_User u where u.res_Approve_Info_Id = res.res_Approve_Info_Id and u.user_Cd=:userCd ");
		hql.append(")");
		hql.append(" union ");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("exists(");
		hql.append(" select 1 from Res_Approve_User u,Res_Accredit_Info a where u.res_Approve_Info_Id = res.res_Approve_Info_Id AND U.USER_CD = A.USER_CD and A.ACC_USER_CD=:userCd ");
		hql.append(")");
		hql.append(")res where 1=1 ");
		
		boolean hasFilter = wrapSql(hql, pram);
		hql.append(" ORDER BY ");
		if (StringUtils.isNotEmpty(super.getSelectedOrderBy())) {
			hql.append(super.getSelectedOrderBy() + " " + super.getSelectedOrder());
		} else {
			hql.append("res.last_ApproveDate desc ");
		}
		return hql.toString();
	}
	/**
	 * 我的审批HQL
	 */
	private String buildHql(Map<String, Object> pram) {
		StringBuilder hql = new StringBuilder("from ResApproveInfo as res where 1=1 ");
		boolean hasFilter = wrapCondition(hql, pram);
		hql.append(" and res.statusCd =:statusCd ");
		pram.put("statusCd", ResConstants.RES_APPROVE_STATUS_ING);

		// 当前审批人
		StringBuffer myApprove = new StringBuffer(
				" (res.approveUserCd=:userCd or res.approveUserCd like :userCd_like or res.delayApproveUser=:userCd )");
		// 共享审批
		StringBuffer myShare = new StringBuffer(
				" exists(select 1 from ResApproveShare as ras where res.resApproveInfoId = ras.resApproveInfo.resApproveInfoId and ras.userCd=:userCd )");

		hql.append(" and (");
		hql.append(myApprove);
		hql.append(" or ");
		hql.append(myShare);
		hql.append(")");

		String userCd = SpringSecurityUtils.getCurrentUiid();
		pram.put("userCd", userCd);
		pram.put("userCd_like", "%" + userCd + "%");

		// order by
		hql.append(" ORDER BY ");
		if (StringUtils.isNotEmpty(super.getSelectedOrderBy())) {
			hql.append(super.getSelectedOrderBy() + " " + super.getSelectedOrder());
		} else {
			hql.append("res.lastApproveDate desc ");
		}
		return hql.toString();
	}

	/**
	 * 拼装页面传入的搜索条件
	 * 
	 * @return
	 */
	private boolean wrapCondition(StringBuilder hql, Map<String, Object> pram) {
		boolean hasFilter = false;
		// ----拼装搜索条件--start

		// 标题、审批编号及随机码
		if (StringUtils.isNotBlank(super.getFilter_LIKES_titlename())) {
			hasFilter = true;
			// 标题
			hql.append("and ( res.titleName like :titleName ");
			pram.put("titleName", "%" + super.getFilter_LIKES_titlename() + "%");

			// 审批编号
			super.setApproveCdSrh(super.getFilter_LIKES_titlename().trim().toUpperCase());
			String approveCdTmp = super.getApproveCdSrh().substring(0, super.getApproveCdSrh().lastIndexOf("_") + 1);
			String serialNoTmpStr = super.getApproveCdSrh().substring(super.getApproveCdSrh().lastIndexOf("_") + 1);
			serialNoTmpStr = StringUtils.trim(serialNoTmpStr);
			if (NumberUtils.isNumber(serialNoTmpStr)) {
				hql.append("or res.serialNo=:serialNo ");
				pram.put("serialNo", Long.valueOf(serialNoTmpStr));
			} else {
				approveCdTmp = super.getApproveCdSrh();
			}
			if (StringUtils.isNotBlank(approveCdTmp)) {
				hql.append("or res.approveCd like:approveCd ");
				pram.put("approveCd", "%" + approveCdTmp + "%");
			}

			// 随机码
			hql.append("or res.randomNo=:randomNo ) ");
			pram.put("randomNo", super.getFilter_LIKES_titlename());
		}

		// ----拼装搜索条件--end
		return hasFilter;
	}
	private boolean wrapSql(StringBuilder Sql, Map<String, Object> pram) {
		boolean hasFilter = false;
		// ----拼装搜索条件--start
		
		// 标题、审批编号及随机码
		if (StringUtils.isNotBlank(super.getFilter_LIKES_titlename())) {
			hasFilter = true;
			// 标题
			Sql.append("and ( res.title_Name like :titleName ");
			pram.put("title_Name", "%" + super.getFilter_LIKES_titlename() + "%");
			
			// 审批编号
			super.setApproveCdSrh(super.getFilter_LIKES_titlename().trim().toUpperCase());
			String approveCdTmp = super.getApproveCdSrh().substring(0, super.getApproveCdSrh().lastIndexOf("_") + 1);
			String serialNoTmpStr = super.getApproveCdSrh().substring(super.getApproveCdSrh().lastIndexOf("_") + 1);
			serialNoTmpStr = StringUtils.trim(serialNoTmpStr);
			if (NumberUtils.isNumber(serialNoTmpStr)) {
				Sql.append("or res.serial_No=:serialNo ");
				pram.put("serialNo", Long.valueOf(serialNoTmpStr));
			} else {
				approveCdTmp = super.getApproveCdSrh();
			}
			if (StringUtils.isNotBlank(approveCdTmp)) {
				Sql.append("or res.approve_Cd like:approveCd ");
				pram.put("approveCd", "%" + approveCdTmp + "%");
			}
			
			// 随机码
			Sql.append("or res.random_No=:randomNo ) ");
			pram.put("randomNo", super.getFilter_LIKES_titlename());
		}
		
		// ----拼装搜索条件--end
		return hasFilter;
	}

	/**
	 * 查看审批历史
	 */
	@Override
	public String showApproveHis() {
		super.showApproveHis();
		return "showHis";
	}

	/**
	 * 初始化顶级机构节点
	 */
	private void initRootNodes() {
		TreePanelNode node = PlasCache.getPhysicalOrgTree();
		rootNoodes = node.getChildren();
	}

	/**
	 * 根据上级机构获取子机构
	 * 
	 * @param parentNode
	 * @return
	 */
	public String getChildNodes() {
		String parentId=Struts2Utils.getParameter("orgId");
		childOrgs= PlasCache.getDirectOrgListPhy(parentId);
		childUsers=PlasCache.getDirectUserList(parentId);
		return "shareChild";
	}
	/**
	 * 功能: 模糊搜索用户列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchUserList() throws Exception {

		String value = Struts2Utils.getParameter("value");
//		String strPageNo = Struts2Utils.getParameter("pageNo");
		WsPlasUser user = null;
		if (StringUtils.isNotEmpty(value)) {
			user = new WsPlasUser();
			user.setUiid(value);
			user.setUserName(value);
//			user.setEmail(value);
//			user.setFixedPhone(value);
//			user.setMobilePhone(value);
			
			// 前台匹配
			Struts2Utils.getRequest().setAttribute("queryCondition", value);
			searchUserResult = Util.getPlasService().getUserList(user, 1, 30);
		}
		return "shareSearch";
	}

	public List<WsPlasOrg> getChildOrgs() {
		return childOrgs;
	}

	public List<WsPlasUser> getChildUsers() {
		return childUsers;
	}

	public List<WsPlasUser> getSearchUserResult() {
		return searchUserResult;
	}

}

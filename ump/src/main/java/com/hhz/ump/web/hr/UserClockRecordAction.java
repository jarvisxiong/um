package com.hhz.ump.web.hr;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.CommonManager;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasSysPosition;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用户上下班打卡记录
 * @author shixy
 *
 * 2011-2-25
 */
@Namespace("/hr")
public class UserClockRecordAction extends ActionSupport {
	
	private static final long serialVersionUID = -5401256125836104149L;
	
	private static final String USER_CLOCK_ADMIN = "A_USER_CLOCK_ADMIN";
	
	@Autowired
	private CommonManager commonManager;
	
	private List result;
	
	private String slotMonth;
	private String isUnusual;
	private String userCd;
	
	
	@Override
	public String execute() throws Exception {
		list();
		return SUCCESS;
	}
	
	public String list() {
		if(StringUtils.isBlank(slotMonth)){
			slotMonth = DateOperator.formatDate(new Date(), "yyyy-MM");;
		}
		if (isUnusual!=null||!ArrayUtils.contains(SpringSecurityUtils.getCurrentRoleCds(), USER_CLOCK_ADMIN)){
			String centerCd = SpringSecurityUtils.getCurrentCenterCd();
			if(StringUtils.isBlank(isUnusual)){
				isUnusual = "false";
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("slotMonth", slotMonth);
			
			map.put("centerCd", centerCd);
			StringBuilder hql = new StringBuilder("select * from vm_emp_card_rec where slot_month = :slotMonth");
			if(!ArrayUtils.contains(SpringSecurityUtils.getCurrentRoleCds(), USER_CLOCK_ADMIN)){
				userCd = SpringSecurityUtils.getCurrentUiid();
			}
			if(StringUtils.isNotBlank(userCd)){
				hql.append(" and user_cd = :userCd");
			}else {
				if(ArrayUtils.contains(SpringSecurityUtils.getCurrentRoleCds(), USER_CLOCK_ADMIN)){
					hql.append(" and center_cd = :centerCd");
				}
			}
			if(isUnusual.equals("true")){
				hql.append(" and is_unusual = 1");
			}
			hql.append(" order by user_cd asc, slot_date asc");
			map.put("userCd", userCd);
			result = commonManager.findBySql(hql.toString(),map);
		}
		return "list";
	}
	
	public void buildCenterTree() {
		String uiid=SpringSecurityUtils.getCurrentUiid();
		List<WsPlasSysPosition> wsPlasSysPositions= PlasCache.getPosListByUiid2(uiid);
		for (WsPlasSysPosition wsPlasSysPosition : wsPlasSysPositions) {
			WsPlasOrg wsPlasOrg= PlasCache.getOrgById(wsPlasSysPosition.getOrgId());
//			String curCenterCd = SpringSecurityUtils.getCurrentCenterCd();
			String curCenterCd = wsPlasOrg.getOrgCd();
			if (wsPlasOrg.getOrgName().equals("决策层")){
				break;
			}
			TreePanelNode rootNode = new TreePanelNode();
			rootNode.setId(PlasCache.getOrgByCd(curCenterCd).getPlasOrgId());
			rootNode.setText(CodeNameUtil.getDeptNameByCd(curCenterCd));
			rootNode.setEntityCd(curCenterCd);
			rootNode.setOrgOrUser(TreePanelUtil.NODE_TYPE_ORG);
			List<WsPlasOrg> orgs = PlasCache.getOrgEnableList();
			List<WsPlasUser> users = PlasCache.getUserActiveList();
			TreePanelUtil.buildPhysicalOrgUserTree(rootNode, orgs, users);
			
			Struts2Utils.renderJson(rootNode);
		}
	}

	public List getResult() {
		return result;
	}

	public void setResult(List result) {
		this.result = result;
	}

	public String getSlotMonth() {
		return slotMonth;
	}

	public void setSlotMonth(String slotMonth) {
		this.slotMonth = slotMonth;
	}

	public String getIsUnusual() {
		return isUnusual;
	}

	public void setIsUnusual(String isUnusual) {
		this.isUnusual = isUnusual;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	
	

}

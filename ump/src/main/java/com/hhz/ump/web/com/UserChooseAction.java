/**
 * 
 */
package com.hhz.ump.web.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.dao.oa.OaEmailGroupManager;
import com.hhz.ump.dao.res.ResAccreditInfoManager;
import com.hhz.ump.entity.oa.OaEmailGroup;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.UserDisplayInfo;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 人员选择
 * 
 * @author huangj 2010-4-14
 */
@Namespace("/com")
public class UserChooseAction extends ActionSupport {
	private static final long serialVersionUID = 9203354086129387603L;

	@Autowired
	private AppParamManager appParamManager;

	@Autowired
	private ResAccreditInfoManager resAccreditInfoManager;

	@Autowired
	private OaEmailGroupManager oaEmailGroupManager;

	private String orgCd;

	private List<WsPlasUser> wsPlasUsers;

	private String parentOrgCd;

	// 根据用户列出机构
	private String isByUser = "0";

	// 只列出用户列表
	private String isOnlyUser = "0";

	private List<OaEmailGroup> oaEmailGroups;

	/**
	 * 选择用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String member() throws Exception {
		if (isOnlyUser.equals("1")) {
			setWsPlasUsers(resAccreditInfoManager.loadAllAcc(SpringSecurityUtils.getCurrentUiid()));
		} else {
			String currentOrgCd = SpringSecurityUtils.getCurrentDeptCd();
			if (parentOrgCd != null) {
				// WsPlasOrg uaapOrg =
				// Util.getUaapService().getUaapOrgByOrgCd(parentOrgCd);
				// if (uaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG))
				// {
				currentOrgCd = parentOrgCd;
				// }
			}
			if (StringUtils.isNotEmpty(currentOrgCd)) {
				setOrgCd(currentOrgCd);
				
				String orgId = PlasCache.getOrgByCd(currentOrgCd).getPlasOrgId();
				List<WsPlasUser> currentOrgUsers = PlasCache.getDirectUserList(orgId);
				setWsPlasUsers(currentOrgUsers);
			}
		}
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(filter);
		oaEmailGroups = oaEmailGroupManager.find(filters);
		return "member";
	}

	/**
	 * 获取机构树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getOrgTree() throws Exception {
		String isOnlyOrgStr = Struts2Utils.getParameter("isOnlyOrg");
		String isMultiStr = Struts2Utils.getParameter("isMulti");
		String isOnlyProjectStr = Struts2Utils.getParameter("isOnlyProject");
		String isCenterStr = Struts2Utils.getParameter("isCenter");
		String orgType = Struts2Utils.getParameter("orgType");
		boolean isOnlyOrg = BooleanUtils.toBooleanObject(StringUtils.isEmpty(isOnlyOrgStr) ? "false" : isOnlyOrgStr);
		boolean isMulti = BooleanUtils.toBooleanObject(StringUtils.isEmpty(isMultiStr) ? "false" : isMultiStr);
		boolean isOnlyProject = BooleanUtils.toBooleanObject(StringUtils.isEmpty(isOnlyProjectStr) ? "false" : isOnlyProjectStr);
		boolean isCenter = BooleanUtils.toBooleanObject(StringUtils.isEmpty(isCenterStr) ? "false" : isCenterStr);
		TreePanelNode rootNode = new TreePanelNode();
		if (isOnlyProject) {
			TreePanelNode nodeBLDC = genTreePanelNode(Constants.ORG_CD_BLDC);// 宝龙地产
			TreePanelNode nodeBLSY = genTreePanelNode(Constants.ORG_CD_BLSY);// 宝龙商业
			TreePanelNode nodeBLHY = genTreePanelNode(Constants.ORG_CD_BLHY);// 宝龙行业
			TreePanelNode nodeDCGS = genTreePanelNode(Constants.ORG_CD_DCGS);// 各地产公司
			TreePanelNode nodeSYGS = genTreePanelNode(Constants.ORG_CD_SYGS);// 各商业公司
			TreePanelNode nodeJDGS = genTreePanelNode(Constants.ORG_CD_JDGS);// 各酒店公司
			nodeDCGS = TreePanelUtil.buildPhysicalOrgTree(nodeDCGS, PlasCache.getOrgEnableList(), true);
			nodeSYGS = TreePanelUtil.buildPhysicalOrgTree(nodeSYGS, PlasCache.getOrgEnableList(), true);
			nodeJDGS = TreePanelUtil.buildPhysicalOrgTree(nodeJDGS, PlasCache.getOrgEnableList(), true);
			//nodeBLDC.getChildren().add(TreePanelUtil.buildPhysicalOrgTree(nodeBLDC, PlasCache.getOrgEnableList(), true));
			nodeBLDC.getChildren().add(nodeBLSY);
			nodeBLDC.getChildren().add(nodeBLHY);
			nodeBLDC.getChildren().add(nodeDCGS);
			nodeBLDC.getChildren().add(nodeSYGS);
			nodeBLDC.getChildren().add(nodeJDGS);
			rootNode = nodeBLDC;
		} else {
			if (isByUser.equals("1")) {
				rootNode = TreePanelUtil.getTreeNodePanelCost(SpringSecurityUtils.getCurrentUiid(), false);
			} else {
				if (StringUtils.isEmpty(parentOrgCd)) {
					rootNode = genRootNode();
				} else {
					// String centerCd = ((AcgiUser)
					// SpringSecurityUtils.getCurrentUser()).getWsPlasUser().getCenterOrgCd();
					String centerCd = parentOrgCd;// ((AcgiUser)
					// SpringSecurityUtils.getCurrentUser()).getWsPlasUser().getCenterOrgCd();
					rootNode.setId(centerCd);
					rootNode.setText(CodeNameUtil.getDeptNameByCd(centerCd));
					rootNode.setEntityId(centerCd);// 特殊处理
				}
				rootNode.setOrgOrUser(TreePanelUtil.NODE_TYPE_ORG);

				if (StringUtils.isNotEmpty(parentOrgCd)) {
					WsPlasOrg uaapOrg = PlasCache.getOrgByCd(parentOrgCd);
					if (uaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG)) {
						rootNode.setEntityCd(parentOrgCd);
						if (isOnlyOrg && !isMulti) {
							rootNode = TreePanelUtil.buildLogicalOrgTree(rootNode, PlasCache.getOrgEnableList());
						} else {
							rootNode = TreePanelUtil.buildLogicalOrgTreeNoChecked(rootNode, PlasCache.getOrgEnableList());
						}
					} else {
						if (orgType.equals("Physical")) {
							if (isOnlyOrg && !isMulti) {
								rootNode = TreePanelUtil.buildPhysicalOrgTree(rootNode, PlasCache.getOrgEnableList());
							} else {
								rootNode = TreePanelUtil.buildPhysicalOrgTreeNoChecked(rootNode, PlasCache.getOrgEnableList());
							}
						}else{
							if (isOnlyOrg && !isMulti) {
								rootNode = TreePanelUtil.buildLogicalOrgTree(rootNode, PlasCache.getOrgEnableList());
							} else {
								rootNode = TreePanelUtil.buildLogicalOrgTreeNoChecked(rootNode, PlasCache.getOrgEnableList());
							}
						}
					}
				} else {
					if (orgType.equals("Physical")) {
						if (isOnlyOrg && !isMulti) {
							rootNode = TreePanelUtil.buildPhysicalOrgTree(rootNode, PlasCache.getOrgEnableList());
						} else {
							rootNode = TreePanelUtil.buildPhysicalOrgTreeNoChecked(rootNode, PlasCache.getOrgEnableList());
						}
					}else{
						if (isOnlyOrg && !isMulti) {
							rootNode = TreePanelUtil.buildLogicalOrgTree(rootNode, PlasCache.getOrgEnableList());
						} else {
							rootNode = TreePanelUtil.buildLogicalOrgTreeNoChecked(rootNode, PlasCache.getOrgEnableList());
						}
					}
				}

			}
		}
		Struts2Utils.renderJson(rootNode);
		return null;
	}

	private TreePanelNode genTreePanelNode(String nodeCd) {
		WsPlasOrg plasOrg= PlasCache.getOrgByCd(nodeCd);
		TreePanelNode treePanelNode = new TreePanelNode();
		treePanelNode.setId(plasOrg.getPlasOrgId());
		treePanelNode.setText(CodeNameUtil.getDeptNameByCd(nodeCd));
		treePanelNode.setEntityId(nodeCd);
		return treePanelNode;

	}

	private TreePanelNode genRootNode() {
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(appParamManager.getAppOrgTreeRootCd());
		rootNode.setText(appParamManager.getAppOrgTreeRootName());
		rootNode.setEntityId(appParamManager.getAppOrgTreeRootCd());// 特殊处理
		rootNode.setEntityCd(appParamManager.getAppOrgTreeRootCd());// 特殊处理
		return rootNode;

	}

	/**
	 * 获取对应部门或角色下所有的用户 <br/>
	 * 人员从缓存中获取
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUsersbyOrg() throws Exception {
		String orgCds = Struts2Utils.getParameter("orgCds");
		if (StringUtils.isNotEmpty(orgCds)) {
			List<UserDisplayInfo> userInfos = new ArrayList<UserDisplayInfo>();
			
			List<WsPlasUser> users = null;
			for (String tmpOrgCd : orgCds.split(",")) {
				users = PlasCache.getDirectUserListByCd(tmpOrgCd);
				if (users != null && users.size() > 0) {
					userInfos.addAll(transf2UserInfo(users));
				}
			}
			Struts2Utils.renderJson(userInfos);
		}

		return null;
	}

	/**
	 *根据关键字搜索人员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUsersByFilter() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String maxNum = Struts2Utils.getParameter("maxNum");
		if (StringUtils.isNotEmpty(value)) {
			WsPlasUser user = new WsPlasUser();
			user.setUiid(value);
			user.setUserName(value);
			if (!StringUtils.equals(appParamManager.getAppOrgTreeRootCd(), parentOrgCd)) {
//				TODO:FILL CENTER CD?
//				user.setCenterOrgCd(parentOrgCd);
			}
			List<WsPlasUser> result = Util.getPlasService().getUserListByFilter(user);
			List<UserDisplayInfo> list = transf2UserInfo(result);
			if (StringUtils.isNotEmpty(maxNum)) {
				int num = Integer.valueOf(maxNum);
				List<UserDisplayInfo> newList;
				if (list.size() > num) {
					newList = list.subList(0, num);
				} else {
					newList = list.subList(0, list.size());
				}
				Struts2Utils.renderJson(newList);
			} else {
				Struts2Utils.renderJson(list);
			}
		}
		return null;
	}
	public void quickSearch() throws Exception {
		
		String value = Struts2Utils.getParameter("value");
		WsPlasUser user = new WsPlasUser();
		user.setUiid(value);
		user.setUserName(value);
//		if (!StringUtils.equals(appParamManager.getAppOrgTreeRootCd(), parentOrgCd)) {
//		}
		List<WsPlasUser> result = Util.getPlasService().getUserList(user, 1, 10);
		List<Map<String, String> > list = new ArrayList<Map<String, String> >();
		Map<String, String> map = null;
		for(WsPlasUser cl : result){
			map = new HashMap<String, String>();
			map.put("userName", cl.getUserName());
			map.put("uiid", cl.getUiid());
			map.put("orgName", cl.getOrgName());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}
	private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception {
		List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
		if (users == null)
			return list;
		for (WsPlasUser user : users) {
			UserDisplayInfo info = new UserDisplayInfo();
			//TODO
//			info.setCenterOrgCd(user.getCenterOrgCd());
			// info.setCenterOrgName(user.getCenterOrgName());
			info.setOrgBizCd(user.getOrgBizCd());
			info.setOrgCd(user.getOrgCd());
			// info.setOrgName(user.getOrgName());// 这里取的是逻辑机构名称
			String phyOrgName = CodeNameUtil.getDeptNameByCd(user.getOrgCd());
			info.setOrgName(phyOrgName);
			info.setSexCd(user.getSexCd());
			info.setUiid(user.getUiid());
			info.setUserBizCd(user.getUserBizCd());
			info.setUserCd(user.getUserCd());
			info.setUserName(user.getUserName());
			list.add(info);
		}
		return list;
	}

	public String getOrgCd() {
		return orgCd;
	}

	public void setOrgCd(String orgBizCd) {
		this.orgCd = orgBizCd;
	}

	public List<WsPlasUser> getWsPlasUsers() {
		return wsPlasUsers;
	}

	public void setWsPlasUsers(List<WsPlasUser> wsUaapUsers) {
		this.wsPlasUsers = wsUaapUsers;
	}

	public String getParentOrgCd() {
		return parentOrgCd;
	}

	public void setParentOrgCd(String parentOrgCd) {
		this.parentOrgCd = parentOrgCd;
	}

	public AppParamManager getAppParamManager() {
		return appParamManager;
	}

	public void setAppParamManager(AppParamManager appParamManager) {
		this.appParamManager = appParamManager;
	}

	public String getIsByUser() {
		return isByUser;
	}

	public void setIsByUser(String isByUser) {
		this.isByUser = isByUser;
	}

	public String getIsOnlyUser() {
		return isOnlyUser;
	}

	public void setIsOnlyUser(String isOnlyUser) {
		this.isOnlyUser = isOnlyUser;
	}

	public List<OaEmailGroup> getOaEmailGroups() {
		return oaEmailGroups;
	}

	public void setOaEmailGroups(List<OaEmailGroup> oaEmailGroups) {
		this.oaEmailGroups = oaEmailGroups;
	}
}

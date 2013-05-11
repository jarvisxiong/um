package com.hhz.uums.web.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.Constants;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.vw.UserDisplayInfo;
import com.hhz.uums.vo.ws.WsAcctSysposRel;
import com.hhz.uums.vo.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

public class SelectAction extends ActionSupport {
	
	private static final long serialVersionUID = -2348567539219817603L;

	@Autowired
	private AppParamManager appParamManager;
	@Autowired
	private PlasSysPositionManager plasSysPostionManager;
	@Autowired
	private PlasUserManager plasUserManager;
	@Autowired
	private PlasOrgManager plasOrgManager;
	private List<UserDisplayInfo> PlasUsers;

	
	private Map<String, String> rankMap;
	
	/////////////////////////////////////////////////////////////////

	//是否显示自定义用户组
	private boolean showGroupFlg = false;
	
	//默认显示某一机构的所有人员
	private String defaultParentOrgCd;
	//是否可以勾选(多选)树
	private boolean orgMuti = false;
	//父亲机构cd
	private String parentOrgCd;
	
	//机构树类型
	// 1 ：完整的机构数
	// 2：项目机构树
	// 3：用户所管辖中心/部门机构树
	private int orgTreeType;
	
	//页面类型    user 人员选择   org 机构选择
	private String pageType;
	
	//是否显示系统职位
	private boolean showSysPos = false;
	
	//是否显示按照职级选择人员
	private boolean showRank = false;
	
	private String dataId;

	
	@Override
	public String execute() throws Exception {
		//默认显示当前部门所有人员
		String currentOrg = SpringSecurityUtils.getCurrentDeptCd();
		if (defaultParentOrgCd == null) {
			defaultParentOrgCd = currentOrg;
		}
		if (StringUtils.isNotEmpty(defaultParentOrgCd)) {
			//瀑布查找下属员工
			List<PlasUser> currentOrgUsers = plasOrgManager.getDescendantUsersByCd(defaultParentOrgCd);
			setPlasUsers(transf2UserInfo2(currentOrgUsers));
		}
		//查询自定义组
/*		if(showGroupFlg){
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			PropertyFilter filter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurUiid());
			filters.add(filter);
			oaEmailGroups = oaEmailGroupManager.find(filters);
		}*/
		//查询职级列表
/*		if(showRank){
			rankMap = PlasCache.getMapRankList();
		}*/
		
		return super.execute();
	}
	
	public String test(){
		return "test";
	}
	
	/**
	 * 获取机构树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getOrgTree() throws Exception {
		//根节点
		TreePanelNode rootNode = new TreePanelNode();
		
		//显示某一指定父结构下的机构树
		if(StringUtils.isNotBlank(parentOrgCd)){
			PlasOrg org = plasOrgManager.getPlasOrgByCd(parentOrgCd);
			rootNode.setId(org.getPlasOrgId());
			rootNode.setText(org.getOrgName());
			rootNode.setEntityId(parentOrgCd);
			rootNode.setEntityCd(parentOrgCd);
			rootNode.setOrgOrUser(TreePanelUtil.NODE_TYPE_ORG);
			
			if (org.getOrgTypeCd().equals(DictContants.PLAS_ORG_TYPE_BRANCH)) {
				rootNode.setEntityCd(parentOrgCd);
				if(orgMuti){
					rootNode = TreePanelUtil.buildPhysicalOrgTreeNoChecked(rootNode);
				}else{
					rootNode = TreePanelUtil.buildPhysicalOrgTree(rootNode);
				}
			}else{
				if(orgMuti){
					rootNode = TreePanelUtil.buildPhysicalOrgTreeNoChecked(rootNode);
				}else{
					rootNode = TreePanelUtil.buildPhysicalOrgTree(rootNode);
				}
			}
			
		}else{
			switch (orgTreeType) {
			case 1://显示整个机构树
				rootNode = genRootNode();
				rootNode.setOrgOrUser(TreePanelUtil.NODE_TYPE_ORG);
				if(orgMuti){
					rootNode = TreePanelUtil.buildPhysicalOrgTreeNoChecked(rootNode);
				}else{
					rootNode = TreePanelUtil.buildPhysicalOrgTree(rootNode);
				}
				break;
				
			case 2://显示项目机构树
				TreePanelNode nodeBLDC = genTreePanelNode(Constants.ORG_CD_BLDC);// 宝龙地产
				TreePanelNode nodeBLSY = genTreePanelNode(Constants.ORG_CD_BLSY);// 宝龙商业
				TreePanelNode nodeBLHY = genTreePanelNode(Constants.ORG_CD_BLHY);// 宝龙行业
				TreePanelNode nodeDCGS = genTreePanelNode(Constants.ORG_CD_DCGS);// 各地产公司
				TreePanelNode nodeSYGS = genTreePanelNode(Constants.ORG_CD_SYGS);// 各商业公司
				TreePanelNode nodeJDGS = genTreePanelNode(Constants.ORG_CD_JDGS);// 各酒店公司
				if(orgMuti){
					nodeDCGS = TreePanelUtil.buildPhysicalOrgTreeNoChecked(nodeDCGS, true);
					nodeSYGS = TreePanelUtil.buildPhysicalOrgTreeNoChecked(nodeSYGS, true);
					nodeJDGS = TreePanelUtil.buildPhysicalOrgTreeNoChecked(nodeJDGS, true);
				}else{
					nodeDCGS = TreePanelUtil.buildPhysicalOrgTree(nodeDCGS,  true);
					nodeSYGS = TreePanelUtil.buildPhysicalOrgTree(nodeSYGS,  true);
					nodeJDGS = TreePanelUtil.buildPhysicalOrgTree(nodeJDGS,  true);
				}
				nodeBLDC.getChildren().add(nodeBLSY);
				nodeBLDC.getChildren().add(nodeBLHY);
				nodeBLDC.getChildren().add(nodeDCGS);
				nodeBLDC.getChildren().add(nodeSYGS);
				nodeBLDC.getChildren().add(nodeJDGS);
				rootNode = nodeBLDC;
				break;
				
			case 3://显示所管辖中心/部门机构树
				rootNode = TreePanelUtil2.getTreeNodePanelOrgPhysical(SpringSecurityUtils.getCurUiid());
				break;

			default:
				break;
			}
		}

		//移除其他邮箱
		TreePanelUtil2.refreshMoveOrgOtherEmailGroup(rootNode);
		Struts2Utils.renderJson(rootNode);
		return null;
	}

	private TreePanelNode genTreePanelNode(String nodeCd) {
		PlasOrg org = plasOrgManager.getPlasOrgByCd(nodeCd);
		return getTreePanelOrgNoChild(org);

	}
	public static TreePanelNode getTreePanelOrgNoChild(PlasOrg org) {
		return getTreePanelOrgNoChild(org, TreePanelUtil.NODE_CHECKED_UNDEFINED);
	}

	public static TreePanelNode getTreePanelOrgNoChild(PlasOrg org, String checked) {
		TreePanelNode orgNode = new TreePanelNode();
		orgNode.setId(org.getPlasOrgId());
		orgNode.setText(org.getOrgName());
		orgNode.setEntityId(org.getPlasOrgId());
		orgNode.setEntityCd(org.getOrgCd());
		orgNode.setEntityBizCd(org.getOrgBizCd());
		orgNode.setEntityName(org.getOrgName());
		orgNode.setOrgOrUser(TreePanelUtil.NODE_TYPE_ORG);
		orgNode.setNodeType(TreePanelUtil.NODE_TYPE_ORG);
		orgNode.setChecked(checked);
		orgNode.setExtParam(org.getOrgTypeCd());
		return orgNode;
	}
	private TreePanelNode genRootNode() {
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(TreePanelUtil.DEFAULT_ROOT_ORG_ID);
		rootNode.setText(TreePanelUtil.DEFAULT_ROOT_ORG_NAME);
		rootNode.setEntityId(TreePanelUtil.DEFAULT_ROOT_ORG_ID);// 特殊处理
		rootNode.setEntityCd(TreePanelUtil.DEFAULT_ROOT_ORG_CD);// 特殊处理
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
		String showSysPos = Struts2Utils.getParameter("showSysPos");
		if (StringUtils.isNotEmpty(orgCds)) {
			List<UserDisplayInfo> userInfos = new ArrayList<UserDisplayInfo>();
			for (String orgCd : orgCds.split(",")) {
				System.out.println(">>>>>>>>>> " + orgCd);
				List<UserDisplayInfo> tmps =  null;
				if(StringUtils.isBlank(showSysPos)||"false".equals(showSysPos)){
					tmps = plasOrgManager.getDirectUsers2UserDis(orgCd, false);
				}else if("true".equals(showSysPos)){
					tmps = plasOrgManager.getDirectPos2PosDisWithEmpty(orgCd, false);
				}
				if (tmps != null && tmps.size() > 0) {
					userInfos.addAll(tmps);
				}
				//只取第一个机构
				break;
			}
			Struts2Utils.renderJson(userInfos);
		}

		return null;
	}

	/**
	 *根据关键字查询人员列表
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
//			if("3".equals(orgTreeType)){
//				String centerCd = SpringSecurityUtils.getCurrentCenterCd();
//				user.setCenterOrgCd(centerCd);
//			}
//			if (!StringUtils.equals(appParamManager.getAppOrgTreeRootCd(), parentOrgCd)) {
//				user.setCenterOrgCd(parentOrgCd);
//			}
			List<WsPlasUser> result = plasUserManager.getWsUsersByFilter(user);
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
	/**
	 * 将系统职位转换为WsAcctSysposRel
	 */
	private List<WsAcctSysposRel> transf2WsAcctSysposRel(List<PlasSysPosition> sysPoss){
		List<WsAcctSysposRel> result = new ArrayList<WsAcctSysposRel>();
		WsAcctSysposRel rel = null;
		for(PlasSysPosition sysPos : sysPoss){
			rel = new WsAcctSysposRel();
			rel.setAcctId(sysPos.getPlasAcct().getPlasAcctId());
			rel.setUiid(sysPos.getPlasAcct().getUiid());
			rel.setSysPosId(sysPos.getPlasSysPositionId());
			rel.setSysPosId(sysPos.getSysPosCd());
			rel.setSysPosId(sysPos.getSysPosName());
			rel.setSysPosId(sysPos.getShortName());
			result.add(rel);
		}
		return result;
	}
	/**
	 * 人员信息重新组合,用于构造js需要的json数据
	 **/
	private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception {
		List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
		if (users == null)
			return list;
		for (WsPlasUser user : users) {
			UserDisplayInfo info = new UserDisplayInfo();
			info.setCenterOrgCd(plasOrgManager.getCenterOrgIdByOrgId(user.getOrgId()));
			info.setOrgBizCd(user.getOrgBizCd());
			info.setOrgCd(user.getOrgCd());
			info.setOrgName(user.getOrgName());
			info.setSexCd(user.getSexCd());
			info.setUiid(user.getUiid());
			info.setUserBizCd(user.getUserBizCd());
			info.setUserCd(user.getUserCd());
			info.setUserName(user.getUserName());
			
			if(showSysPos){
				List<WsAcctSysposRel> posList = transf2WsAcctSysposRel( plasSysPostionManager.getSysPosListByUiid(user.getUiid()));
				for(WsAcctSysposRel sysPos : posList){
					//设置系统职位cd
					info.setSysPosCd(sysPos.getSysPosCd());
					//设置系统职位名称
					info.setSysPosName(sysPos.getSysPosName());
					list.add(info);
				}
			}else{
				list.add(info);
			}
			
		}
		return list;
	}
	private List<UserDisplayInfo> transf2UserInfo2(List<PlasUser> users) throws Exception {
		List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
		if (users == null)
			return list;
		for (PlasUser user : users) {
			UserDisplayInfo info = new UserDisplayInfo();
			info.setCenterOrgCd(plasOrgManager.getCenterOrgIdByOrgId(user.getPlasOrg().getPlasOrgId()));
			info.setOrgBizCd(user.getPlasOrg().getOrgBizCd());
			info.setOrgCd(user.getPlasOrg().getOrgCd());
			info.setOrgName(user.getPlasOrg().getOrgName());
			info.setSexCd(user.getSexCd());
			info.setUiid(user.getUiid());
			info.setUserBizCd(user.getUserBizCd());
			info.setUserCd(user.getUserCd());
			info.setUserName(user.getUserName());
			
			if(showSysPos){
				List<WsAcctSysposRel> posList = transf2WsAcctSysposRel( plasSysPostionManager.getSysPosListByUiid(user.getUiid()));
				for(WsAcctSysposRel sysPos : posList){
					//设置系统职位cd
					info.setSysPosCd(sysPos.getSysPosCd());
					//设置系统职位名称
					info.setSysPosName(sysPos.getSysPosName());
					list.add(info);
				}
			}else{
				list.add(info);
			}
			
		}
		return list;
	}
	/**
	 * 按照自定义组加载人员
	 */
	public void loadGroup() throws Exception {
/*		OaEmailGroup emailGroup = oaEmailGroupManager.getEntity(dataId);
		String[] names = Util.clob2String(emailGroup.getGroupMemberNames()).split(";");
		String[] ids = Util.clob2String(emailGroup.getGroupMemberIds()).split(";");
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("[");
		
		String uiid = null;
		String statusCd = null;
		for (int i = 0; i < names.length; i++) {
			uiid = ids[i];
			statusCd = PlasCache.getAcctByUiid(uiid).getStatusCd();
			if(DictContants.UAAP_USER_STATUS_CREATE.equalsIgnoreCase(statusCd)
					|| DictContants.UAAP_USER_STATUS_UNFREEZE.equalsIgnoreCase(statusCd)){
				if (i != 0) {
					jsonStr.append(",");
				}
				jsonStr.append("{");
				jsonStr.append("'userName':");
				jsonStr.append("'" + names[i] + "'");
				jsonStr.append(",");
				jsonStr.append("'uiid':");
				jsonStr.append("'" + ids[i] + "'");
				jsonStr.append("}");
			}
		}
		jsonStr.append("]");
		Struts2Utils.renderText(jsonStr.toString());*/
	}
	
	/**
	 * 按照职级加载人员
	 */
	public void loadRank() throws Exception {
/*		List<PlasUser> list = PlasCache.getUserListByRankCd(dataId);
		List<UserDisplayInfo> userInfos = transf2UserInfo(list);
		Struts2Utils.renderJson(userInfos);*/
	}
	

	public List<UserDisplayInfo> getPlasUsers() {
		return PlasUsers;
	}

	public void setPlasUsers(List<UserDisplayInfo> wsUaapUsers) {
		this.PlasUsers = wsUaapUsers;
	}

	public String getParentOrgCd() {
		return parentOrgCd;
	}

	public void setParentOrgCd(String parentOrgCd) {
		this.parentOrgCd = parentOrgCd;
	}


	public boolean isShowGroupFlg() {
		return showGroupFlg;
	}

	public void setShowGroupFlg(boolean showGroupFlg) {
		this.showGroupFlg = showGroupFlg;
	}


	public boolean isOrgMuti() {
		return orgMuti;
	}

	public void setOrgMuti(boolean orgMuti) {
		this.orgMuti = orgMuti;
	}

	public int getOrgTreeType() {
		return orgTreeType;
	}

	public void setOrgTreeType(int orgTreeType) {
		this.orgTreeType = orgTreeType;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getDefaultParentOrgCd() {
		return defaultParentOrgCd;
	}

	public void setDefaultParentOrgCd(String defaultParentOrgCd) {
		this.defaultParentOrgCd = defaultParentOrgCd;
	}

	public boolean isShowSysPos() {
		return showSysPos;
	}

	public void setShowSysPos(boolean showSysPos) {
		this.showSysPos = showSysPos;
	}

	public boolean isShowRank() {
		return showRank;
	}

	public void setShowRank(boolean showRank) {
		this.showRank = showRank;
	}

	public Map<String, String> getRankMap() {
		return rankMap;
	}

	public void setRankMap(Map<String, String> rankMap) {
		this.rankMap = rankMap;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

}
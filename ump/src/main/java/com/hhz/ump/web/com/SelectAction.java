/**
 * 
 */
package com.hhz.ump.web.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.dao.oa.OaEmailGroupManager;
import com.hhz.ump.entity.oa.OaEmailGroup;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.UserDisplayInfo;
import com.hhz.uums.entity.ws.WsAcctSysposRel;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 人员/机构选择
 * 
 * @author shixy 2011-5-16
 */
@Namespace("/com")
public class SelectAction extends ActionSupport {
	
	private static final long serialVersionUID = -2348567539219817603L;

	@Autowired
	private OaEmailGroupManager oaEmailGroupManager;

	private List<UserDisplayInfo> wsPlasUsers;

	private List<OaEmailGroup> oaEmailGroups;
	
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
	
	//选择职级
	private String permCds;
	
	// 用于保证根据职位获取人员，若出现人员占用多个职位，则指出来一个人员。
	private List<String> uiidList;

	
	@Override
	public String execute() throws Exception {
		
		//默认显示当前部门所有人员
		String currentOrg = SpringSecurityUtils.getCurrentDeptCd();
		if (StringUtils.isBlank(defaultParentOrgCd)) {
			defaultParentOrgCd = currentOrg;
		}
		if (StringUtils.isNotEmpty(defaultParentOrgCd)) {
			
			//hidden by huangbijin 2012-02-27
//			List<WsPlasUser> currentOrgUsers = PlasCache.getDirectUserListByCd(defaultParentOrgCd);
			
			//add by huangbijin 2012-02-27 直接取机构下的系统职位
			List<WsPlasUser> currentOrgUsers= PlasCache.getDirectPosUserListByCd(defaultParentOrgCd);
			setWsPlasUsers(transf2UserInfo(currentOrgUsers));
		}
		//搜索自定义组
		if(showGroupFlg){
			
//			hidden by huangbijin 2012-02-07
//			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
//			PropertyFilter filter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
//			filters.add(filter);
//			oaEmailGroups = oaEmailGroupManager.find(filters);

//			add by huangbijin 2012-02-07
			String hql = "from OaEmailGroup t where t.creator = ? order by t.dispOrderNo asc ";
			oaEmailGroups = oaEmailGroupManager.find(hql, SpringSecurityUtils.getCurrentUiid());
		}
		//搜索职级列表
		if(showRank){
			rankMap = PlasCache.getMapRankList();
		}
		
		return super.execute();
	}
	
	public String test(){
		return "test";
	}
	
	/**
	 * 获取机构树
	 * @param parentOrgCd
	 * @param orgMuti
	 * @param orgTreeType
	 * @param permCds
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getOrgTree() throws Exception {
		//根节点
		TreePanelNode rootNode = new TreePanelNode();
		
		//显示某一指定父结构下的机构树
		if(StringUtils.isNotBlank(parentOrgCd)){
			rootNode.setId(PlasCache.getOrgByCd(parentOrgCd).getPlasOrgId());
			rootNode.setText(CodeNameUtil.getDeptNameByCd(parentOrgCd));
			rootNode.setEntityId(parentOrgCd);
			rootNode.setEntityCd(parentOrgCd);
			rootNode.setOrgOrUser(TreePanelUtil.NODE_TYPE_ORG);
			
			WsPlasOrg uaapOrg = PlasCache.getOrgByCd(parentOrgCd);
			if (uaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG)) {
				rootNode.setEntityCd(parentOrgCd);
				if(orgMuti){
					rootNode = TreePanelUtil.buildLogicalOrgTreeNoChecked(rootNode, PlasCache.getOrgEnableList());
				}else{
					rootNode = TreePanelUtil.buildLogicalOrgTree(rootNode, PlasCache.getOrgEnableList());
				}
			}else{
				if(orgMuti){
					rootNode = TreePanelUtil.buildPhysicalOrgTreeNoChecked(rootNode, PlasCache.getOrgEnableList());
				}else{
					rootNode = TreePanelUtil.buildPhysicalOrgTree(rootNode, PlasCache.getOrgEnableList());
				}
			}
			
		}else{
			switch (orgTreeType) {
			case 1://显示整个机构树
				rootNode = genRootNode();
				rootNode.setOrgOrUser(TreePanelUtil.NODE_TYPE_ORG);
				if(orgMuti){
					rootNode = TreePanelUtil.buildPhysicalOrgTreeNoChecked(rootNode, PlasCache.getOrgEnableList());
				}else{
					rootNode = TreePanelUtil.buildPhysicalOrgTree(rootNode, PlasCache.getOrgEnableList());
				}
				break;
				
			case 2://显示项目机构树
				TreePanelNode nodeBLDC = genTreePanelNode(Constants.ORG_CD_BLDC);// 宝龙地产
				TreePanelNode nodeBLSY = genTreePanelNode(Constants.ORG_CD_BLSY);// 宝龙商业
				TreePanelNode nodeBLHY = genTreePanelNode(Constants.ORG_CD_BLHY);// 宝龙行业
				TreePanelNode nodeDCGS = genTreePanelNode(Constants.ORG_CD_DCGS);// 各地产公司
				TreePanelNode nodeSYGS = genTreePanelNode(Constants.ORG_CD_SYGS);// 各商业公司
				TreePanelNode nodeJDGS = genTreePanelNode(Constants.ORG_CD_JDGS);// 各酒店公司
				TreePanelNode nodeBHGS = genTreePanelNode(Constants.ORG_CD_BHGS);// 各百货公司
				TreePanelNode nodeKTVGS = genTreePanelNode(Constants.ORG_CD_KTVGS);// 各KTV公司
				if(orgMuti){
					nodeDCGS = TreePanelUtil.buildPhysicalOrgTreeNoChecked(nodeDCGS, PlasCache.getOrgEnableList(), true);
					nodeSYGS = TreePanelUtil.buildPhysicalOrgTreeNoChecked(nodeSYGS, PlasCache.getOrgEnableList(), true);
					nodeJDGS = TreePanelUtil.buildPhysicalOrgTreeNoChecked(nodeJDGS, PlasCache.getOrgEnableList(), true);
					nodeBHGS = TreePanelUtil.buildPhysicalOrgTreeNoChecked(nodeBHGS, PlasCache.getOrgEnableList(), true);
					nodeKTVGS = TreePanelUtil.buildPhysicalOrgTreeNoChecked(nodeKTVGS, PlasCache.getOrgEnableList(), true);
				}else{
					nodeDCGS = TreePanelUtil.buildPhysicalOrgTree(nodeDCGS, PlasCache.getOrgEnableList(), true);
					nodeSYGS = TreePanelUtil.buildPhysicalOrgTree(nodeSYGS, PlasCache.getOrgEnableList(), true);
					nodeJDGS = TreePanelUtil.buildPhysicalOrgTree(nodeJDGS, PlasCache.getOrgEnableList(), true);
					nodeBHGS = TreePanelUtil.buildPhysicalOrgTree(nodeBHGS, PlasCache.getOrgEnableList(), true);
					nodeKTVGS = TreePanelUtil.buildPhysicalOrgTree(nodeKTVGS, PlasCache.getOrgEnableList(), true);
				}
				nodeBLDC.getChildren().add(nodeBLSY);
				nodeBLDC.getChildren().add(nodeBLHY);
				nodeBLDC.getChildren().add(nodeDCGS);
				nodeBLDC.getChildren().add(nodeSYGS);
				nodeBLDC.getChildren().add(nodeJDGS);
				nodeBLDC.getChildren().add(nodeBHGS);
				nodeBLDC.getChildren().add(nodeKTVGS);
				rootNode = nodeBLDC;
				break;
				
			case 3://显示所管辖中心/部门机构树
				rootNode = TreePanelUtil.getTreeNodePanelCost(SpringSecurityUtils.getCurrentUiid(), false,orgMuti);
				break;

			default:
				break;
			}
		}
		
		//过滤职级
//		if(StringUtils.isNotBlank(permCds)){
//			refreshPermNode(rootNode, permCds);
//		}
		
		//移除非不显示机构  add by huangbijin 2012-5-31
		TreePanelUtil.refreshMoveOrgHidden(rootNode);
		
		Struts2Utils.renderJson(rootNode);
		return null;
	}
	
	/**
	 * 只保留选中职级的人员
	 * @param rootNode
	 * @param cds
	 */
	private void refreshPermNode(TreePanelNode rootNode, String cds){
		if(StringUtils.isBlank(cds))
			return;
		
		//移除不需要的节点
		List<TreePanelNode> newNodeList = new ArrayList<TreePanelNode>();
		for (TreePanelNode tNode : rootNode.getChildren()) {
			if(processPermNode(getPermCds(cds), tNode)){
				newNodeList.add(tNode);
			}
		}
		rootNode.setChildren(newNodeList);
	}
	
	//只显示的职级
	private List<String> getPermCds(String cds){
		String[] arrList = StringUtils.split(cds, ",");
		List<String> cdList = new ArrayList<String>();
		for (String t : arrList) {
			cdList.add(t.trim());
		}
		return cdList;
	}
	
	private boolean processPermNode(List<String> cdList, TreePanelNode node){
		//System.out.println(">>>>>>>>>>>>>>>>>>> " + node.getNodeType() + "[" + node.getEntityName() +"]") ;
		if(TreePanelUtil.NODE_TYPE_USER.equals(node.getNodeType())){
			if(cdList.contains(node.getLevelCd()))
				return true;
			else
				return false;
		}
		else if(TreePanelUtil.NODE_TYPE_ORG.equals(node.getNodeType())){
			List<TreePanelNode> newNodeList = new ArrayList<TreePanelNode>();
			for (TreePanelNode tNode : node.getChildren()) {
				if(processPermNode(cdList, tNode)){
					newNodeList.add(tNode);
				}
			}
			if(newNodeList.size() > 0){
				node.setChildren(newNodeList);
				return true;
			} else
				return false;
		} else
			return true;
	}

	private TreePanelNode genTreePanelNode(String nodeCd) {
		WsPlasOrg org = PlasCache.getOrgByCd(nodeCd);
		return PlasCacheUtil.getTreePanelOrgNoChild(org);

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
	 * @param orgCds
	 * @param showSysPos
	 * @param permCds
	 * 
	 * @return
	 * @throws Exception
	 */
	//机构人员树
	public String getUsersbyOrg() throws Exception {
		String orgCds = Struts2Utils.getParameter("orgCds");
		
		List<WsPlasUser> users = null;
		List<UserDisplayInfo> tmpList = null;
		if (StringUtils.isNotEmpty(orgCds)) {
			List<UserDisplayInfo> rtnList = new ArrayList<UserDisplayInfo>();
			for (String orgCd : orgCds.split(",")) {
				users = PlasCache.getDirectUserListByCd(orgCd);
				if (users != null && users.size() > 0) {
					tmpList = transf2UserInfo(users, getPermCds(permCds));
					rtnList.addAll(tmpList);
				}
			}
			Struts2Utils.renderJson(rtnList);
		}

		return null;
	}
	//系统职位树
	public String getUsersbyOrgPos() throws Exception {
		String orgCds = Struts2Utils.getParameter("orgCds");
		
		List<WsPlasUser> users = null;
		List<UserDisplayInfo> tmpList = null;
		if (StringUtils.isNotEmpty(orgCds)) {
			List<UserDisplayInfo> rtnList = new ArrayList<UserDisplayInfo>();
			for (String orgCd : orgCds.split(",")) {
//				hidden by huangbijin 2012-02-17
//				List<WsPlasUser> users = PlasCache.getDirectUserListByCd(orgCd);
				users = PlasCache.getDirectPosUserListByCd(orgCd);
				if (users != null && users.size() > 0) {
					tmpList = transf2UserInfo(users, getPermCds(permCds));
					rtnList.addAll(tmpList);
				}
			}
			Struts2Utils.renderJson(rtnList);
		}

		return null;
	}

	/**
	 *根据关键字搜索人员列表
	 * @param value
	 * @param maxNum
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
			List<WsPlasUser> result = Util.getPlasService().getUserList(user,1,30);
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
	 * 人员信息重新组合,用于构造js需要的json数据
	 * @throws Exception 
	 */
	private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception{
		return transf2UserInfo(users, null);
	}
	private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users,List<String> cdList) throws Exception {
		
		List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
		if (users == null)
			return list;
		
		UserDisplayInfo info  = null;
		for (WsPlasUser user : users) {
			
			if(cdList != null && cdList.size() >0 ){
				if(!cdList.contains(user.getPermissionLevelCd())){
					continue;
				}
			}
			
			//避免人员重复进入列表
			if(uiidList == null){
				uiidList = new ArrayList<String>();
				//System.out.println(">>>>>>>>>>>>>> " + user.getUiid());
			}
			if(uiidList.contains(user.getUiid())){
				continue;
				//System.out.println(">>>>>>>>>>>>>>重复: " + user.getUiid());
			}
			
			
			uiidList.add(user.getUiid());
			
			info = new UserDisplayInfo();
			info.setCenterOrgCd(PlasCache.getCenterOrgCdByUiid(user.getUiid()));
			info.setOrgBizCd(user.getOrgBizCd());
			info.setOrgCd(user.getOrgCd());
			String phyOrgName =CodeNameUtil.getDeptNameByCd(user.getOrgCd());
			info.setOrgName(phyOrgName);
			info.setSexCd(user.getSexCd());
			info.setUiid(user.getUiid());
			info.setUserBizCd(user.getUserBizCd());
			info.setUserCd(user.getUserCd());
			info.setUserName(user.getUserName());
			
			if(showSysPos){
				List<WsAcctSysposRel> posList = PlasCache.getPosListByUiid(user.getUiid());
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
		OaEmailGroup emailGroup = oaEmailGroupManager.getEntity(dataId);
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
		Struts2Utils.renderText(jsonStr.toString());
	}
	
	/**
	 * 按照职级加载人员
	 */
	public void loadRank() throws Exception {
		List<WsPlasUser> list = PlasCache.getUserListByRankCd(dataId);
		List<UserDisplayInfo> userInfos = transf2UserInfo(list);
		Struts2Utils.renderJson(userInfos);
	}
	

	public List<UserDisplayInfo> getWsPlasUsers() {
		return wsPlasUsers;
	}

	public void setWsPlasUsers(List<UserDisplayInfo> wsUaapUsers) {
		this.wsPlasUsers = wsUaapUsers;
	}

	public String getParentOrgCd() {
		return parentOrgCd;
	}

	public void setParentOrgCd(String parentOrgCd) {
		this.parentOrgCd = parentOrgCd;
	}

	public List<OaEmailGroup> getOaEmailGroups() {
		return oaEmailGroups;
	}

	public void setOaEmailGroups(List<OaEmailGroup> oaEmailGroups) {
		this.oaEmailGroups = oaEmailGroups;
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

	public String getPermCds() {
		return permCds;
	}

	public void setPermCds(String permCds) {
		this.permCds = permCds;
	}
	
}

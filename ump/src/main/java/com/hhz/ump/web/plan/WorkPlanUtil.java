package com.hhz.ump.web.plan;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;

public class WorkPlanUtil {

	/**
	 * 构造用户权限
	 * 
	 * @param uiid
	 */
	public static boolean[] getWorkPlanPermission(String projectCd) {

		boolean superAdmin = false;
		boolean projAdmin = false;
		boolean infoInputor = false;

		if (SpringSecurityUtils.hasRole(GlobalConstants.A_EXECPLAN_SUP_ADMIN)) {
			superAdmin = true;
		} else if (SpringSecurityUtils.hasRole(GlobalConstants.A_EXECPLAN_INPUTOR)) {
			infoInputor = true;
		} else if (SpringSecurityUtils.hasRole(GlobalConstants.A_EXECPLAN_PJ_ADMIN)) {
			// 如果当前用户有项目管理员角色，则需要根据下面规则判断是否对当前项目有管理权限：
			// 1. 首先判断当前用户是否隶属当前项目，如果是，则是当前项目的项目管理员
			// 2. 如果不是，则判断是否拥有对该项目管理的权限，如果拥有，则是当前项目的管理员
			// 3. 如果1 2都不符合，则不是当前项目的项目管理员

			List<WsPlasOrg> orgList = PlasCache.getPhysicalBubbleOrgListByOrgCd(SpringSecurityUtils.getCurrentDeptCd());
			// 是否需要检查跨机构管理员
			boolean bCrossProject = true;
			for (WsPlasOrg org : orgList) {
				if (projectCd.equals(org.getOrgCd())) {
					projAdmin = true;
					bCrossProject = false;
				}
			}

			// 若需要检查是否跨机构管理员(即 A机构下的成员,却是B项目管理员)
			if (bCrossProject) {
				String tmpProjCd = "";
				String[] roleList = SpringSecurityUtils.getCurrentRoleCds();
				for (String role : roleList) {
					if (role.startsWith(GlobalConstants.A_EXECPLAN_PJ_ADMIN_PREFIX)) {
						tmpProjCd = role.substring(GlobalConstants.A_EXECPLAN_PJ_ADMIN_PREFIX.length());
						if (projectCd.equals(tmpProjCd)) {
							projAdmin = true;
						}
					}
				}
			}
		}
		return new boolean[] { superAdmin, projAdmin, infoInputor };
	}

	/**
	 * 搜索"各地产公司"下的所有地产公司列表
	 * 
	 * @return
	 */
	public static List<WsPlasOrg> getOrgEstateOrgList() {
		List<WsPlasOrg> list = PlasCache.getPhysicalDirectOrgListByOrgCd(GlobalConstants.PROJECT_ORG_CD);
		if (list == null) {
			list = new ArrayList<WsPlasOrg>();
		}
		return list;
	}

	/**
	 * 搜索"宝龙地产"下的所有分管和大中心列表(除去各地产公司节点)
	 * 
	 * @return
	 */
	public static List<WsPlasOrg> getOrgBranchCenterList() {
		
		List<WsPlasOrg> list = new ArrayList<WsPlasOrg>();

		List<WsPlasOrg> branchList = PlasCache.getLogicalDirectOrgListByOrgCd(GlobalConstants.ESTATE_ORG_CD,DictContants.UAAP_ORG_TYPE_BRANCH);
		if (branchList == null) {
			branchList = new ArrayList<WsPlasOrg>();
		}
		// 除去各地产公司
		for (int i = 0; i < branchList.size(); i++) {
			WsPlasOrg t = branchList.get(i);
			if (GlobalConstants.EXEC_ORG_CD.equals(t.getOrgCd())) {
				branchList.remove(i);
				break;
			}
		}
		List<WsPlasOrg> centerList = PlasCache.getLogicalDirectOrgListByOrgCd(GlobalConstants.ESTATE_ORG_CD,DictContants.UAAP_ORG_TYPE_CENTER);
		if (centerList == null) {
			centerList = new ArrayList<WsPlasOrg>();
		}
		
		list.addAll(branchList);
		list.addAll(centerList);
		
		return list;
	}

	/**
	 * 构造以"宝龙地产"为根节点的2级别机构节点(除地产公司)
	 * 
	 * @return
	 */
	public static TreePanelNode estateTreeNode() {
		return TreePanelUtil.buildLogicalOrgTree(getEstateNode(GlobalConstants.ESTATE_ORG_CD), getOrgBranchCenterList());
	}

	/**
	 * 构造节点
	 * 
	 * @return
	 */
	public static TreePanelNode getEstateNode(String orgCd) {
		WsPlasOrg org = PlasCache.getOrgByCd(orgCd);
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(org.getOrgCd());
		rootNode.setText(org.getOrgName());
		rootNode.setEntityId(org.getPlasOrgId());
		rootNode.setEntityCd(org.getOrgCd());
		rootNode.setEntityBizCd(org.getOrgBizCd());
		rootNode.setEntityName(org.getOrgName());
		rootNode.setOrgOrUser(TreePanelUtil.NODE_TYPE_ORG);
		rootNode.setNodeType(TreePanelUtil.NODE_TYPE_ORG);
		return rootNode;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}

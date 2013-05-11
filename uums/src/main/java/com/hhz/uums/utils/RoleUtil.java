package com.hhz.uums.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.service.impl.AcegiUser;
import com.hhz.uums.vo.ws.WsPlasRole;

public class RoleUtil {
	
//	private static final Log log = LogFactory.getLog(RoleUtil.class);
	
	private static  List<String> globalRoleList = new ArrayList<String>();
	private static  List<String> loginRoleList = new ArrayList<String>();
	static{
		//全局
		globalRoleList.add(GlobalConstants.A_ADMIN);
		globalRoleList.add(GlobalConstants.A_ADMIN_SUPER);
		globalRoleList.add(GlobalConstants.A_BIZ_CENTER);
		
		//管理员
		loginRoleList.add(GlobalConstants.A_ADMIN);
		loginRoleList.add(GlobalConstants.A_ADMIN_SUPER);
		loginRoleList.add(GlobalConstants.A_BIZ_CENTER);
		loginRoleList.add(GlobalConstants.A_HR_ADMIN);
		loginRoleList.add(GlobalConstants.A_HR_GROUP);
		
		//区域
		loginRoleList.add(GlobalConstants.A_HR_AREA);
		loginRoleList.add(GlobalConstants.A_HR_NQ);
		loginRoleList.add(GlobalConstants.A_HR_BQ);
		loginRoleList.add(GlobalConstants.A_HR_SY);
//		loginRoleList.add(GlobalConstants.A_HR_HY);
		loginRoleList.add(GlobalConstants.A_HR_YS);
		loginRoleList.add(GlobalConstants.A_HR_JD);
//		loginRoleList.add(GlobalConstants.A_HR_DW);
		
		//项目
		loginRoleList.add(GlobalConstants.A_HR_ESTATE);
		loginRoleList.add(GlobalConstants.A_HR_ESSY);
		loginRoleList.add(GlobalConstants.A_HR_JDGS);
		loginRoleList.add(GlobalConstants.A_HR_YSGS);
		
		loginRoleList.add(GlobalConstants.A_HR_BHGS);
		loginRoleList.add(GlobalConstants.A_HR_KTVGS);
		loginRoleList.add(GlobalConstants.A_HR_DWGS);
		
		loginRoleList.add(GlobalConstants.A_ADMIN_UAAP_ORG);
	}
	
	//HR管理
	public static boolean isHrGroup(List<WsPlasRole> roleList) {
		return validateRole(GlobalConstants.A_HR_GROUP);
	}
	public static boolean isHrBizCenter(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_BIZ_CENTER);
	}
	public static boolean isHrAdmin(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_ADMIN);
	}
	
	//区域公司
	public static boolean isHrArea(List<WsPlasRole> roleList) {
		if( validateRole(roleList, GlobalConstants.A_HR_NQ))
			return true;
		else if( validateRole(roleList, GlobalConstants.A_HR_BQ))
			return true;
		else if( validateRole(roleList, GlobalConstants.A_HR_SY))
			return true;
//		else if( validateRole(roleList, GlobalConstants.A_HR_HY))
//			return true;
		else if( validateRole(roleList, GlobalConstants.A_HR_YS))
			return true;
		else if( validateRole(roleList, GlobalConstants.A_HR_JD))
			return true;
		else if( validateRole(roleList, GlobalConstants.A_HR_BLHK))
//			return true;
//		else if( validateRole(roleList, GlobalConstants.A_HR_DW))
			return true;
		else
			return false;
	}

	public static boolean isHrDcQy(List<WsPlasRole> roleList) {
		return isHrNq(roleList)|| isHrBq(roleList);
	}
	public static boolean isHrNq(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_NQ);
	}
	public static boolean isHrBq(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_BQ);
	}
	public static boolean isHrSy(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_SY);
	}
//	public static boolean isHrHy(List<WsPlasRole> roleList) {
//		return validateRole(roleList, GlobalConstants.A_HR_HY);
//	}
	public static boolean isHrYS(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_YS);
	}
	public static boolean isHrJd(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_JD);
	}
	public static boolean isHrBlhk(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_BLHK);
	}
//	public static boolean isHrDw(List<WsPlasRole> roleList) {
//		return validateRole(roleList, GlobalConstants.A_HR_DW) /*|| validateRole(roleList, GlobalConstants.A_HR_HY)*/;
//	}
	
	//项目公司
	public static boolean isHrDcgs(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_ESTATE);
	}
	public static boolean isHrSygs(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_ESSY);
	}
	public static boolean isHrYsgs(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_YSGS);
	}
	public static boolean isHrJdgs(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_JDGS);
	}
	public static boolean isHrBhgs(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_BHGS);
	}
	public static boolean isHrKtvgs(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_KTVGS);
	}
	public static boolean isHrDwgs(List<WsPlasRole> roleList) {
		return validateRole(roleList, GlobalConstants.A_HR_DWGS);
	}
	

	// 超级管理员
	public static boolean isAdminSupser() {
		return validateRole(GlobalConstants.A_ADMIN_SUPER);
	}

	// 应用管理员
	public static boolean isAdmin() {
		return validateRole(GlobalConstants.A_ADMIN);
	}

	// 机构管理员
	public static boolean isAdminUaapOrg() {
		return validateRole(GlobalConstants.A_ADMIN_UAAP_ORG);
	}
	


	// 比较角色(当前用户)
	public static boolean validateRole(String roleCd) {
		AcegiUser user = (AcegiUser) SpringSecurityUtils.getCurrentUser();
		return validateRole(user.getRoleList(), roleCd);
	}
	

	public static boolean validateRole(List<WsPlasRole> roleCdList, String roleCd) {
		WsPlasRole role = null;
		for (int i = 0; i < roleCdList.size(); i++) {
			role = roleCdList.get(i);
			if (role!= null && roleCd.equals(role.getRoleCd()))
				return true;
		}
		return false;
	}
 
	public static boolean isGlobalRole(List<PlasRole> roles) {
		if(roles == null || roles.size() == 0)
			return false;

		for (PlasRole tRole : roles) {
			if(globalRoleList.contains(tRole.getRoleCd()))
				return true;
		}
		return false;
	}

	public static boolean isLoginRole(List<WsPlasRole> roles) {
		if(roles == null || roles.size() == 0)
			return false;
 
		for (WsPlasRole wsPlasRole : roles) {
			if(loginRoleList.contains(wsPlasRole.getRoleCd()))
				return true;
		}
		return false;
	}
	public static boolean isGlobalWsRole(List<WsPlasRole> roles) {
		if(roles == null || roles.size() == 0)
			return false;
 
		for (WsPlasRole wsPlasRole : roles) {
			if(loginRoleList.contains(wsPlasRole.getRoleCd()))
				return true;
		}
		return false;
	}

	// 超级管理员
	public static boolean isAdminSupser(List<PlasRole> roles) {
		return validatePlasRole(GlobalConstants.A_ADMIN_SUPER, roles);
	}

	// 应用管理员
	public static boolean isAdmin(List<PlasRole> roles) {
		return validatePlasRole(GlobalConstants.A_ADMIN, roles);
	}

	// 机构管理员
	public static boolean isAdminPlasOrg(List<PlasRole> roles) {
		return validatePlasRole(GlobalConstants.A_ADMIN_UAAP_ORG, roles);
	}

	// 比较角色(指定对象)
	private static boolean validatePlasRole(String roleCd, List<PlasRole> roles) {
		PlasRole role = null;
		for (int i = 0; i < roles.size(); i++) {
			role  = roles.get(i);
			if (role != null && roleCd.equals(role.getRoleCd()))
				return true;
		}
		return false;
	}
	
	
	public static String getHeightRoleCd(List<WsPlasRole> roleList){
		if(isHrBizCenter(roleList))
			return GlobalConstants.A_BIZ_CENTER;
		else if(isHrAdmin(roleList))
			return GlobalConstants.A_HR_ADMIN;
		else if(isHrGroup(roleList))
			return GlobalConstants.A_HR_GROUP;
		else if(isHrArea(roleList))
			return GlobalConstants.A_HR_AREA;
		else
			return "";
	}
	
	/**
	 * 是否需要提交管理员操作
	 * 控制范围: 
	 * @return
	 */
	public static boolean isUnderCtrlFlg(String orgId){
		//还是按原来的逻辑执行,不控制人员调动,删除代码见svn
		return false;
	}
	
	/**
	 * 获取当期用户的角色清单
	 * @return
	 */
	public static List<WsPlasRole> getMyWsRoleList(){
		AcegiUser user = (AcegiUser) SpringSecurityUtils.getCurrentUser();
		List<WsPlasRole> roles = user.getRoleList();
		if(roles == null){
			roles = new ArrayList<WsPlasRole>();
		}
		return roles;
	}
	public static List<String> getMyRoleList(){
		
		AcegiUser user = (AcegiUser) SpringSecurityUtils.getCurrentUser();
		return getRoleCdList(user.getRoleList());
	}
	public static List<String> getRoleCdList(List<WsPlasRole> roles){
		List<String> roleCdList = new ArrayList<String>();
		if(roles == null)
			return roleCdList;
		for(WsPlasRole vo : roles){
			roleCdList.add(vo.getRoleCd());
		}
		return roleCdList;
	}
	public static String getCurRoleCds(){
		AcegiUser user = (AcegiUser) SpringSecurityUtils.getCurrentUser();
		List<WsPlasRole> roles = user.getRoleList();
		StringBuffer roleCds = new StringBuffer();
		for(WsPlasRole obj : roles){
			roleCds.append(obj.getRoleCd()).append(",");
		}
		return roleCds.toString();
	}
	
	//************************************************/ 
	
	public static final String FLOW_DEFAULT = "DEFAULT";
	//总部
	public static final String FLOW_0101 = "0101";//总经理级及以上
    public static final String FLOW_0102 = "0102";//副总经理级及以下
    //区域、商业总部、行业
    public static final String FLOW_0201 = "0201";//总经理级及以上

    public static final String FLOW_0202_01NQ = "0202-01NQ";//副总经理级
    public static final String FLOW_0202_02BQ = "0202-02BQ";
    public static final String FLOW_0202_03SY = "0202-03SY";
//    public static final String FLOW_0202_04HY = "0202-04HY";
    public static final String FLOW_0202_05YS = "0202-05FY";
    public static final String FLOW_0202_06JD = "0202-06JD";
    public static final String FLOW_0202_07BLHK = "0202-07BLHK";
    public static final String FLOW_0202_08DW = "0202-08DW";
    
    public static final String FLOW_0203_01NQ = "0203-01NQ";//其他
    public static final String FLOW_0203_02BQ = "0203-02BQ";
    public static final String FLOW_0203_03SY = "0203-03SY";
    public static final String FLOW_0203_05YS = "0203-05YS";
    public static final String FLOW_0203_06JD = "0203-06JD";
    public static final String FLOW_0203_07BLHK = "0203-07BLHK";
    
    //地产公司、商业公司
    public static final String FLOW_0301_01NQ = "0301-01NQ";//总经理级
    public static final String FLOW_0301_02BQ = "0301-02BQ";
    public static final String FLOW_0301_03SY = "0301-03SY";
    public static final String FLOW_0301_05YS = "0301-05YS";
    public static final String FLOW_0301_06JD = "0301-06JD";
    
    public static final String FLOW_0302_01NQ = "0302-01NQ";//副总经理级
    public static final String FLOW_0302_02BQ = "0302-02BQ";
    public static final String FLOW_0302_03SY = "0302-03SY";
    public static final String FLOW_0302_05YS = "0302-05YS";
    public static final String FLOW_0302_06JD = "0302-06JD";
    
    public static final String FLOW_0303_01NQ = "0303-01NQ";//其他
    public static final String FLOW_0303_02BQ = "0303-02BQ";
    public static final String FLOW_0303_03SY = "0303-03SY";
    public static final String FLOW_0303_05YS = "0303-05YS";
    public static final String FLOW_0303_06JD = "0303-06JD";


	public static final String ORG_DC = "23";//宝龙地产
	
	
    //南北区
	public static final String AREA_ORG_NORTH = "ORG_NORTH";
	public static final String AREA_ORG_SOUTH = "ORG_SOUTH";
	public static final String AREA_ORG_SH = "ORG_SH";

	public static final String ORG_DC_JD = "134";//酒店开发中心
	public static final String ORG_DC_JD_GJDGS = "443";//各酒店公司

	public static final String ORG_DC_QYGS = "705";//区域公司
	public static final String ORG_DC_NQ = "706";//区域公司-南区
	public static final String ORG_DC_BQ = "707";//区域公司-北区
	public static final String ORG_DC_GDCGS = "135";//各地产公司

	public static final String ORG_BLHK = "899";//宝龙华康(北京)投资有限公司
	

	//宝龙商业下属
	public static final String ORG_SY = "153";// 宝龙商业
	public static final String ORG_SY_GSYGS = "512";// 各商业公司
//	public static final String ORG_SY_BHGLZX = "453";// 百货管理中心
	public static final String ORG_SY_GBHGS = "459";// 各百货公司
//	public static final String ORG_SY_KTVGLZX = "454";// KTV管理中心
	public static final String ORG_SY_GKTVGS = "454";// 各KTV公司 1035

	
	//非上市
	public static final String ORG_YS = "1065";//非上市
	public static final String ORG_HY_DWGLZX = "497";// 电玩管理中心
	public static final String ORG_HY_GDWMD = "751";// 各电玩门店

	
	//企业管理一部
	public static final String ORG_YS_BXZC = "310";//	宝信资产管理部
	
	//企业管理二部
	public static final String ORG_YS_XMJS = "536";//	厦门泰景顺企管公司
	public static final String ORG_YS_DFHA = "304";//	东方海岸
	public static final String ORG_YS_SHML = "630";//	上海茂龙装饰设计工程有限公司
	public static final String ORG_YS_SHHLD = "636";//	上海合立道建筑设计有限公司
	public static final String ORG_YS_SHBLPM = "897";//	上海宝龙拍卖有限公司
	   
	
	public static final String ORG_DC_SHCS = "722";//上海城市公司 > 管理以下三公司
	public static final String ORG_DC_SHHX = "954";//上海华新项目
	public static final String ORG_DC_SHXL = "673";//上海曹路项目
	public static final String ORG_DC_SHGFL= "526";//上海广富林项目


	private static boolean isDc(List<String> orgCdList){
		return orgCdList.contains(ORG_DC);
	}
	
	private static boolean isGdcgs(List<String> orgCdList){
		return orgCdList.contains(ORG_DC_GDCGS);
	}

	private static boolean isJd(List<String> orgCdList){
		return orgCdList.contains(ORG_DC_JD);
	}
	public static boolean isSy(List<String> orgCdList){
		return orgCdList.contains(ORG_SY);
	}
	private static boolean isDw(List<String> orgCdList){
		return orgCdList.contains(ORG_HY_DWGLZX);
	}

	public static boolean isBq(List<String> orgCdList){
		return orgCdList.contains(ORG_DC_BQ);
	}

	public static boolean isNq(List<String> orgCdList){
		return orgCdList.contains(ORG_DC_NQ);
	}
	//宝龙华康
	public static boolean isBlHk(List<String> orgCdList){
		return orgCdList.contains(ORG_BLHK);
	}
	
	//上海城市公司
	private static boolean isShGs(List<String> orgCdList){
		return orgCdList.contains(ORG_DC_SHCS);
	}
	
	//酒店公司
	private static boolean isGJdGs(List<String> orgCdList){
		return orgCdList.contains(ORG_DC_JD_GJDGS);
	}
	//商业公司
	private static boolean isSyGs(List<String> orgCdList){
		return isGbhGs(orgCdList)//各百货
				|| isGktvGs(orgCdList)//各KTV
					|| isGsyGs(orgCdList);//各商业公司
	}

	// 各商业公司
	private static boolean isGsyGs(List<String> orgCdList){
		return orgCdList.contains(ORG_SY_GSYGS);
	}
	
	// 各百货公司
	private static boolean isGbhGs(List<String> orgCdList){
		return orgCdList.contains(ORG_SY_GBHGS);
	}

	// 各KTV
	private static boolean isGktvGs(List<String> orgCdList){
		return orgCdList.contains(ORG_SY_GKTVGS);
	}
	

	private static boolean isYs(List<String> orgCdList){
		return orgCdList.contains(ORG_YS);
	}
	
	
	private static boolean isGysGs(List<String> orgCdList){
		return isGdwGs(orgCdList);
		
//		return cdList.contains(ORG_YS_BXZC)
//			||cdList.contains(ORG_YS_XMJS)
//			||cdList.contains(ORG_YS_DFHA)
//			||cdList.contains(ORG_YS_SHML)
//			||cdList.contains(ORG_YS_SHHLD)
//			||cdList.contains(ORG_YS_SHBLPM);
	}

	// 各电玩门店
	private static boolean isGdwGs(List<String> orgCdList){
		return orgCdList.contains(ORG_HY_GDWMD);
	}
	
	
	/**
	 * 获取流程类型
	 * @param doOrgId 被操作用户所属机构id
	 * @param permCd 职级
	 * @param uiid 操作管理员的uiid
	 * @return {flowTypeCd, applyRoleCd}
	 * @throws Exception 
	 */
	public static String[] getFlowTypeCd(String doOrgId,String permCd, String uiid) throws Exception{
		
//		System.out.println(">>>>>>>>>>>>>>>> orgId: " + orgId + ", permCd: " + permCd + ", uiid: " + uiid);
		
		PlasOrgManager plasOrgManager = SpringContextHolder.getBean("plasOrgManager");
		PlasRoleManager plasRoleManager = SpringContextHolder.getBean("plasRoleManager");
		
		List<PlasOrg> doOrgList = plasOrgManager.getBubbleOrgsByOrgId(doOrgId, TreePanelUtil2.TREE_TYPE_PHYSICAL);
		if(doOrgList== null || doOrgList.size() == 0)
			throw new Exception("对不起，未找到当前机构下人员的审批流程!");
		else{
			
			List<WsPlasRole> roleList = plasRoleManager.getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, uiid);

			List<String> orgCdList = new ArrayList<String>();
			for (PlasOrg org : doOrgList) {
				System.out.println(" >>>>>>>>>>>>>>> " + org.getOrgName());
				orgCdList.add(org.getOrgCd());
			}

			//************************************************/
			//第二部分
			//区域公司,酒店中心(除"各酒店公司"),宝龙商业(除"各商业公司",除"各百货公司",除"各KTV公司"),非上市(除"各电玩公司")
			//	总经理级及以上
			//	副总经理级及以下 
			
			if(	
				  (isNq(orgCdList) && (!isShGs(orgCdList))) //南区
				|| isBq(orgCdList)							//北区
				||(isJd(orgCdList) && (!isGJdGs(orgCdList)))//酒店中心(除"各酒店公司")
				||(isSy(orgCdList) && (!isSyGs(orgCdList))) //宝龙商业(除"各商业公司",除"各百货公司",除"各KTV公司")
				||(isYs(orgCdList) && (!isGysGs(orgCdList)))//非上市(除"各电玩门店")
				||isBlHk(orgCdList)//宝龙华康
			){
				//总经理及以上
				if(DictContants.PERM_ZC.equals(permCd)
						||DictContants.PERM_FZC.equals(permCd) 
							|| DictContants.PERM_ZJL.equals(permCd) ){
					
					String tipDesc = "对不起，您没有权限发起!（正常流程:总部人事[发起]->人事管理员->总管理员）";
					if(isHrBizCenter(roleList))
						return new String[]{FLOW_0201, GlobalConstants.A_BIZ_CENTER};
					else if(isHrAdmin(roleList))
						return new String[]{FLOW_0201, GlobalConstants.A_HR_ADMIN};
					else if(isHrGroup(roleList))
						return new String[]{FLOW_0201, GlobalConstants.A_HR_GROUP};
					else if(isHrArea(roleList)){
						if(isNq(orgCdList) && isHrNq(roleList))
							return new String[]{FLOW_0201, GlobalConstants.A_HR_NQ};
						else if(isBq(orgCdList) && isHrBq(roleList))
							return new String[]{FLOW_0201, GlobalConstants.A_HR_BQ};
						else if(isSy(orgCdList) && isHrSy(roleList))
							return new String[]{FLOW_0201, GlobalConstants.A_HR_SY};
						else if(isYs(orgCdList) && isHrYS(roleList))
							return new String[]{FLOW_0201, GlobalConstants.A_HR_YS};//细分
						else if(isJd(orgCdList) && isHrJd(roleList))
							return new String[]{FLOW_0201, GlobalConstants.A_HR_JD};
						else if(isBlHk(orgCdList) && isHrBlhk(roleList))
							return new String[]{FLOW_0201, GlobalConstants.A_HR_BLHK};
						else 
							throw new Exception(tipDesc);
					} 
					else
						throw new Exception(tipDesc);
				}
				//副总经理
				else if( DictContants.PERM_FZJL.equals(permCd)){ 
					String tipDesc = "对不起，您没有权限发起!（正常流程:总部人事/区域人事/商业人事/非上市人事[发起]->人事管理员）";
					
					if(isHrBizCenter(roleList))
						return new String[]{FLOW_DEFAULT, GlobalConstants.A_BIZ_CENTER};
					
					else if(isHrAdmin(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0202_01NQ, GlobalConstants.A_HR_ADMIN};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0202_02BQ, GlobalConstants.A_HR_ADMIN};
						else if(isSy(orgCdList))
							return new String[]{FLOW_0202_03SY, GlobalConstants.A_HR_ADMIN};
						else if(isYs(orgCdList))
							return new String[]{FLOW_0202_05YS, GlobalConstants.A_HR_ADMIN};
						else if(isJd(orgCdList))
							return new String[]{FLOW_0202_06JD, GlobalConstants.A_HR_ADMIN};
						else if(isBlHk(orgCdList))
							return new String[]{FLOW_0202_07BLHK, GlobalConstants.A_HR_ADMIN};
						else 
							throw new Exception(tipDesc);
					}
					else if(isHrGroup(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0202_01NQ, GlobalConstants.A_HR_GROUP};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0202_02BQ, GlobalConstants.A_HR_GROUP};
						else if(isSy(orgCdList))
							return new String[]{FLOW_0202_03SY, GlobalConstants.A_HR_GROUP};
						else if(isYs(orgCdList))
							return new String[]{FLOW_0202_05YS, GlobalConstants.A_HR_GROUP};
						else if(isJd(orgCdList))
							return new String[]{FLOW_0202_06JD, GlobalConstants.A_HR_GROUP};
						else if(isBlHk(orgCdList))
							return new String[]{FLOW_0202_07BLHK, GlobalConstants.A_HR_GROUP};
						else 
							throw new Exception(tipDesc);
					}
					else if(isHrArea(roleList)){
						if(isNq(orgCdList) && isHrNq(roleList))
							return new String[]{FLOW_0202_01NQ, GlobalConstants.A_HR_NQ};
						else if(isBq(orgCdList) && isHrBq(roleList))
							return new String[]{FLOW_0202_02BQ, GlobalConstants.A_HR_BQ};
						else if(isSy(orgCdList) && isHrSy(roleList))
							return new String[]{FLOW_0202_03SY, GlobalConstants.A_HR_SY};
						else if(isYs(orgCdList) && isHrYS(roleList))
							return new String[]{FLOW_0202_05YS, GlobalConstants.A_HR_YS};//细分
						else if(isJd(orgCdList) && isHrJd(roleList))
							return new String[]{FLOW_0202_06JD, GlobalConstants.A_HR_JD};
						else if(isBlHk(orgCdList) && isHrBlhk(roleList))
							return new String[]{FLOW_0202_07BLHK, GlobalConstants.A_HR_BLHK};
						else 
							throw new Exception(tipDesc);
					} 
					else
						throw new Exception(tipDesc);
				}
				//其他
				else{

					String tipDesc = "对不起，您没有权限发起!（正常流程:总部人事/区域人事/商业人事/非上市人事[发起]->立即生效）";
					if(isHrBizCenter(roleList))
						return new String[]{FLOW_DEFAULT, GlobalConstants.A_BIZ_CENTER};
					else if(isHrAdmin(roleList))
						return new String[]{FLOW_DEFAULT, GlobalConstants.A_HR_ADMIN};
					else if(isHrGroup(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0203_01NQ, GlobalConstants.A_HR_GROUP};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0203_02BQ, GlobalConstants.A_HR_GROUP};
						else if(isSy(orgCdList))
							return new String[]{FLOW_0203_03SY, GlobalConstants.A_HR_GROUP}; 
						else if(isYs(orgCdList))
							return new String[]{FLOW_0203_05YS, GlobalConstants.A_HR_GROUP};
						else if(isJd(orgCdList))
							return new String[]{FLOW_0203_06JD, GlobalConstants.A_HR_GROUP};
						else if(isBlHk(orgCdList))
							return new String[]{FLOW_0203_07BLHK, GlobalConstants.A_HR_GROUP};
						else 
							throw new Exception(tipDesc);
					}
					else if(isHrArea(roleList)){ 
						if(isNq(orgCdList) && isHrNq(roleList))
							return new String[]{FLOW_0203_01NQ, GlobalConstants.A_HR_NQ};
						else if(isBq(orgCdList) && isHrBq(roleList))
							return new String[]{FLOW_0203_02BQ, GlobalConstants.A_HR_BQ};
						else if(isSy(orgCdList) && isHrSy(roleList))
							return new String[]{FLOW_0203_03SY, GlobalConstants.A_HR_SY};
						else if(isYs(orgCdList) && isHrYS(roleList))
							return new String[]{FLOW_0203_05YS, GlobalConstants.A_HR_YS};
						else if(isJd(orgCdList) && isHrJd(roleList))
							return new String[]{FLOW_0203_06JD, GlobalConstants.A_HR_JD};
						else if(isBlHk(orgCdList) && isHrBlhk(roleList))
							return new String[]{FLOW_0203_07BLHK, GlobalConstants.A_HR_BLHK};
						else 
							throw new Exception(tipDesc);
					} 
					else
						throw new Exception(tipDesc);
				}
			}

			//第三部分
			//各地产公司、各商业公司、各百货公司、各KTV公司
			//	总经理级
			//	副总经理级
			//  其他
			else if(isGdcgs(orgCdList)//各地产公司
					||isShGs(orgCdList)//上海城市公司
						||isGJdGs(orgCdList)//各酒店公司
							||isSyGs(orgCdList)//各商业公司
								||isGbhGs(orgCdList)//各百货公司
//									||isGdwGs(orgCdList)//各电玩公司
									||isDw(orgCdList)//各电玩公司中心(含"各电玩公司")
			){

				//这段很重要：若项目公司,在路径上加南北区的机构.
				String tmpQYCd = null;
				

				if(orgCdList.contains(ORG_DC_SHCS)){
					tmpQYCd = ORG_DC_NQ;
				}else{
					List<String> cdListNorth = TreePanelUtil2.getProjectCds(RoleUtil.AREA_ORG_NORTH);
					List<String> cdListSouth = TreePanelUtil2.getProjectCds(RoleUtil.AREA_ORG_SOUTH);

					for (String v : orgCdList) {
						if(cdListNorth!= null && cdListNorth.size() > 0){
							if(cdListNorth.contains(v)){
								tmpQYCd = ORG_DC_BQ;
								break;
							}
						}
						if(cdListSouth!= null && cdListSouth.size() > 0){
							if(cdListSouth.contains(v)){
								tmpQYCd = ORG_DC_NQ;
								break;
							}
						}
					}
				}

				if(StringUtils.isNotBlank(tmpQYCd)){
					orgCdList.add(tmpQYCd);
				}
				
				//总经理及以上
				if(DictContants.PERM_ZC.equals(permCd)
						||DictContants.PERM_FZC.equals(permCd)
							||DictContants.PERM_ZJL.equals(permCd)){
					
					String tipDesc = "对不起，未找到审批流程!（正常流程:总部人事/区域人事/商业人事/非上市人事[发起]->人事管理员->总管理员）";

					if(isHrBizCenter(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0301_01NQ, GlobalConstants.A_BIZ_CENTER};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0301_02BQ, GlobalConstants.A_BIZ_CENTER};
						else if(isSyGs(orgCdList))
							return new String[]{FLOW_0301_03SY, GlobalConstants.A_BIZ_CENTER};
						else if(isYs(orgCdList))
							return new String[]{FLOW_0301_05YS, GlobalConstants.A_BIZ_CENTER};
						else if(isGJdGs(orgCdList))
							return new String[]{FLOW_0301_06JD, GlobalConstants.A_BIZ_CENTER};
						else
							throw new Exception(tipDesc);
					}
					else if(isHrAdmin(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0301_01NQ, GlobalConstants.A_HR_ADMIN};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0301_02BQ, GlobalConstants.A_HR_ADMIN};
						else if(isSyGs(orgCdList))
							return new String[]{FLOW_0301_03SY, GlobalConstants.A_HR_ADMIN};
						else if(isYs(orgCdList))
							return new String[]{FLOW_0301_05YS, GlobalConstants.A_HR_ADMIN};
						else if(isGJdGs(orgCdList))
							return new String[]{FLOW_0301_06JD, GlobalConstants.A_HR_ADMIN};
						else
							throw new Exception(tipDesc);
					}
					else if(isHrGroup(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0301_01NQ, GlobalConstants.A_HR_GROUP};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0301_02BQ, GlobalConstants.A_HR_GROUP};
						else if(isSyGs(orgCdList))
							return new String[]{FLOW_0301_03SY, GlobalConstants.A_HR_GROUP};
						else if(isYs(orgCdList))
							return new String[]{FLOW_0301_05YS, GlobalConstants.A_HR_GROUP};
						else if(isGJdGs(orgCdList))
							return new String[]{FLOW_0301_06JD, GlobalConstants.A_HR_GROUP};
						else
							throw new Exception(tipDesc);
					}
					else if (isHrArea(roleList)){
						if(isNq(orgCdList) && isHrNq(roleList))
							return new String[]{FLOW_0301_01NQ, GlobalConstants.A_HR_NQ};
						else if(isBq(orgCdList) && isHrBq(roleList))
							return new String[]{FLOW_0301_02BQ, GlobalConstants.A_HR_BQ};
						else if(isSyGs(orgCdList) && isHrSy(roleList))
							return new String[]{FLOW_0301_03SY, GlobalConstants.A_HR_SY};
						else if(isYs(orgCdList) && isHrYS(roleList))
							return new String[]{FLOW_0301_05YS, GlobalConstants.A_HR_YS};
						else if(isGJdGs(orgCdList) && isHrJd(roleList))
							return new String[]{FLOW_0301_06JD, GlobalConstants.A_HR_JD};
						else
							throw new Exception(tipDesc);
					} 
					else
						throw new Exception(tipDesc);
				}
				//副总经理级
				else if(DictContants.PERM_FZJL.equals(permCd)){
					
					String tipDesc = "对不起，未找到审批流程!（正常流程:总部人事/区域人事/商业人事/非上市人事[发起]->人事管理员）";
					if(isHrBizCenter(roleList))
						return new String[]{FLOW_DEFAULT, GlobalConstants.A_BIZ_CENTER};
					else if(isHrAdmin(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0302_01NQ, GlobalConstants.A_HR_ADMIN};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0302_02BQ, GlobalConstants.A_HR_ADMIN};
						else if(isSyGs(orgCdList))
							return new String[]{FLOW_0302_03SY, GlobalConstants.A_HR_ADMIN};
						else if(isYs(orgCdList))
							return new String[]{FLOW_0302_05YS, GlobalConstants.A_HR_ADMIN};
						else if(isGJdGs(orgCdList))
							return new String[]{FLOW_0302_06JD, GlobalConstants.A_HR_ADMIN};
						throw new Exception(tipDesc);
					}
					else if(isHrGroup(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0302_01NQ, GlobalConstants.A_HR_GROUP};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0302_02BQ, GlobalConstants.A_HR_GROUP};
						else if(isSyGs(orgCdList) )
							return new String[]{FLOW_0302_03SY, GlobalConstants.A_HR_GROUP};
						else if(isYs(orgCdList))
							return new String[]{FLOW_0302_05YS, GlobalConstants.A_HR_GROUP};
						else if(isGJdGs(orgCdList))
							return new String[]{FLOW_0302_06JD, GlobalConstants.A_HR_GROUP};
						throw new Exception(tipDesc);
					}
					else if(isHrArea(roleList)){
						if(isNq(orgCdList) && isHrNq(roleList))
							return new String[]{FLOW_0302_01NQ, GlobalConstants.A_HR_NQ};
						else if(isBq(orgCdList) && isHrBq(roleList))
							return new String[]{FLOW_0302_02BQ, GlobalConstants.A_HR_BQ};
						else if(isSyGs(orgCdList) && isHrSy(roleList))
							return new String[]{FLOW_0302_03SY, GlobalConstants.A_HR_SY};
						else if(isYs(orgCdList) && isHrYS(roleList))
							return new String[]{FLOW_0302_05YS, GlobalConstants.A_HR_YS};
						else if(isGJdGs(orgCdList) && isHrJd(roleList))
							return new String[]{FLOW_0302_06JD, GlobalConstants.A_HR_JD};
						throw new Exception(tipDesc);
					}
					else
						throw new Exception(tipDesc);
				}
				//其他
				else{
					String tipDesc = "对不起，未找到审批流程!（正常流程:下属公司人事[发起]->区域人事/商业人事/非上市人事）";
					if(isHrBizCenter(roleList))
						return new String[]{FLOW_DEFAULT, GlobalConstants.A_BIZ_CENTER};
					else if(isHrAdmin(roleList))
						return new String[]{FLOW_DEFAULT, GlobalConstants.A_HR_ADMIN};
					else if(isHrArea(roleList)){
						if(isNq(orgCdList) && isHrNq(roleList))
							return new String[]{FLOW_0303_01NQ, GlobalConstants.A_HR_NQ};
						else if(isBq(orgCdList) && isHrBq(roleList))
							return new String[]{FLOW_0303_02BQ, GlobalConstants.A_HR_BQ};
						else if(isSyGs(orgCdList) && isHrSy(roleList))
							return new String[]{FLOW_0303_03SY, GlobalConstants.A_HR_SY};
						else if(isYs(orgCdList) && isHrYS(roleList))
							return new String[]{FLOW_0303_05YS, GlobalConstants.A_HR_YS};
						else if(isGJdGs(orgCdList) && isHrJd(roleList))
							return new String[]{FLOW_0303_06JD, GlobalConstants.A_HR_JD};
						else
							throw new Exception(tipDesc);
					}
					else if(isHrGroup(roleList)){
						if(isNq(orgCdList))
							return new String[]{FLOW_0303_01NQ, GlobalConstants.A_HR_GROUP};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0303_02BQ, GlobalConstants.A_HR_GROUP};
						else if(isSyGs(orgCdList))
							return new String[]{FLOW_0303_03SY, GlobalConstants.A_HR_GROUP};
						else if(isYs(orgCdList))
							return new String[]{FLOW_0303_05YS, GlobalConstants.A_HR_GROUP};
						else if(isGJdGs(orgCdList))
							return new String[]{FLOW_0303_06JD, GlobalConstants.A_HR_GROUP};
						else
							throw new Exception(tipDesc);
					}
					else {
						//含有上海区域公司
						if(isNq(orgCdList))
							return new String[]{FLOW_0303_01NQ, GlobalConstants.A_HR_ESTATE};
						else if(isBq(orgCdList))
							return new String[]{FLOW_0303_02BQ, GlobalConstants.A_HR_ESTATE};
						//各商业公司
						else if(isGsyGs(orgCdList))
							return new String[]{FLOW_0303_03SY, GlobalConstants.A_HR_ESSY};
						//各酒店公司
						else if(isGJdGs(orgCdList))
							return new String[]{FLOW_0303_06JD, GlobalConstants.A_HR_JDGS};
						//各百货公司  
						else if(isGbhGs(orgCdList) && isHrBhgs(roleList))
							return new String[]{FLOW_0303_03SY, GlobalConstants.A_HR_BHGS};
						//各KTV公司
						else if(isGktvGs(orgCdList) && isHrKtvgs(roleList))
							return new String[]{FLOW_0303_03SY, GlobalConstants.A_HR_KTVGS};
						//各电玩公司
						else if(isGdwGs(orgCdList) && isHrDwgs(roleList))
							return new String[]{FLOW_0303_05YS, GlobalConstants.A_HR_DWGS};
						//各事业公司
						else if(isGysGs(orgCdList) && isHrYsgs(roleList))
							return new String[]{FLOW_0303_05YS, GlobalConstants.A_HR_YSGS};
						//电玩中心
//						else if(isDw(orgCdList) && isHrDw(roleList))
//							return new String[]{FLOW_0303_05YS, GlobalConstants.A_HR_DW};//ADD 
						else
							throw new Exception(tipDesc);
					}
				}
			}

			//第一部分
			//总部(除02类,03类，剩下的所有) -->01
			//	总经理级及以上
			//	副总经理级及以下  
			else{
				//总经理级及以上
				if(DictContants.PERM_ZC.equals(permCd)||DictContants.PERM_FZC.equals(permCd)||DictContants.PERM_ZJL.equals(permCd)){
					String tipDesc = "对不起，您没有权限发起! （正常流程：总部人事->人事管理员->总管理员）";
					if (isHrBizCenter(roleList))
						return new String[]{FLOW_0101, GlobalConstants.A_BIZ_CENTER};
					else if(isHrAdmin(roleList))
						return new String[]{FLOW_0101, GlobalConstants.A_HR_ADMIN};
					else if(isHrGroup(roleList))
						return new String[]{FLOW_0101, GlobalConstants.A_HR_GROUP};
					else
						throw new Exception(tipDesc);
				//副总经理级及以下  
				}else{
					if(isHrBizCenter(roleList))
						return new String[]{FLOW_DEFAULT, GlobalConstants.A_BIZ_CENTER};
					else if(isHrAdmin(roleList))
						return new String[]{FLOW_0102, GlobalConstants.A_HR_ADMIN};
					else if(isHrGroup(roleList))
						return new String[]{FLOW_0102, GlobalConstants.A_HR_GROUP};
					else
						throw new Exception("对不起，您没有权限发起!（正常流程：总部人事->人事管理员）");
				}
			}
		}
	} 
}

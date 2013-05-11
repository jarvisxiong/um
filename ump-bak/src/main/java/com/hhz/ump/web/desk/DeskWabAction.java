package com.hhz.ump.web.desk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasSysPosition;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.hhz.uums.service.WSPlasService;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/desk")
// 通讯录
public class DeskWabAction extends ActionSupport {

	private static final long serialVersionUID = -3391023528385226997L;

	@Autowired
	private AppParamManager appParamManager;

	// 搜索员工
	private List<WsPlasUser> searchUserResult = new ArrayList<WsPlasUser>();

	/**
	 * ajax加载通讯录,机构-人员树
	 * 
	 * @return
	 */
	public String getWabTree(){
		TreePanelNode node  = PlasCache.getPhysicalOrgTree();
		PlasCacheUtil.refreshOnlineFlg(node, PlasCache.getOnlineUiidSet());
		TreePanelUtil.refreshMoveOrgHidden(node);
		Struts2Utils.renderJson(node);
		return null;
	}
	/**
	 * ajax加载通讯录,机构-职位树
	 * @return
	 */
	public String getWabTreePos(){
		TreePanelNode rootNode  = PlasCache.getPhyOrgPosTree();
		PlasCacheUtil.refreshOnlineFlg(rootNode, PlasCache.getOnlineUiidSet());
		TreePanelUtil.refreshMoveOrgHidden(rootNode);
		Struts2Utils.renderJson(rootNode);
		return null;
	}
	
	/**
	 * 获取指定机构的下级
	 * @return
	 */
	public String getWabTreeByOrgId(){
		String orgId=Struts2Utils.getParameter("orgId");
		TreePanelNode node = PlasCache.getTreeNodeByOrgId(orgId);
		PlasCacheUtil.refreshOnlineFlg(node, PlasCache.getOnlineUiidSet());
		Struts2Utils.renderJson(node);
		return null;
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
			user.setEmail(value);
			user.setFixedPhone(value);
			user.setMobilePhone(value);
			
			// 前台匹配
			Struts2Utils.getRequest().setAttribute("queryCondition", value);
			searchUserResult = Util.getPlasService().getUserList(user, 1, 30);
		}
		return "user";
	}
	
	/**
	 * 功能: 新版模糊搜索用户列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String searchNewUserList() throws Exception {

		String value = Struts2Utils.getParameter("value");
//		String strPageNo = Struts2Utils.getParameter("pageNo");
		WsPlasUser user = null;
		if (StringUtils.isNotEmpty(value)) {
			user = new WsPlasUser();
			user.setUiid(value);
			user.setUserName(value);
			user.setEmail(value);
			user.setFixedPhone(value);
			user.setMobilePhone(value);
			
			// 前台匹配
			Struts2Utils.getRequest().setAttribute("queryCondition", value);
			searchUserResult = Util.getPlasService().getUserList(user, 1, 30);
		}
		return "newuser";
	}

	/**
	 * 快速搜索用户
	 * @param value
	 * @throws Exception
	 */
	public void quickSearch() throws Exception {
		
		String value = Struts2Utils.getParameter("value");
		WsPlasUser userCond = new WsPlasUser();
		userCond.setUiid(value);
		userCond.setUserName(value);
		searchUserResult = Util.getPlasService().getUserList(userCond, 1, 30);
		
		List<Map<String, String> > list = new ArrayList<Map<String, String> >();
		Map<String,String> map = null;
		for (WsPlasUser user : searchUserResult) {
			map = new HashMap<String,String>();
			map.put("uiid", user.getUiid());
			map.put("userName", user.getUserName());
			map.put("userCd", user.getUserCd());
			map.put("orgName", user.getOrgName());
			map.put("centerName", PlasCache.getCenterOrgNameByOrgId(user.getOrgId()));
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}
	/**
	 * 功能: 显示用户名片信息(座机/手机/职位/中心/部门)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showUserPic() throws Exception {

		String tUiid = Struts2Utils.getParameter("uiid");
		String tPosId = Struts2Utils.getParameter("posId");//plas_sys_position
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (StringUtils.isBlank(tUiid)) {
			map.put("resultFlg", "none");
			Struts2Utils.renderJson(map);
			return null;
		}else{
			WSPlasService service = Util.getPlasService();
			WsPlasUser tUser = service.getUserByUiid(tUiid);
			map.put("resultFlg", "ok");
			
			if(tUser != null){
				//姓名
				String tUserName = tUser.getUserName() + (StringUtils.isBlank(tUser.getResponsibility())?"":("["+tUser.getResponsibility()+"]"));
				//部门
				String tDeptName = null;
				//中心
				String tCenterName = null;
				
				//机构Id
				String tOrgId = null;
				
				WsPlasSysPosition tPos = PlasCache.getSysIdPosMap().get(tPosId);
				if(tPos != null){
					tOrgId = tPos.getOrgId();
				}else{
					tOrgId = tUser.getOrgId();
				}
				
				WsPlasOrg tOrg = PlasCache.getOrgById(tOrgId);
				if(tOrg != null){
					tDeptName = tOrg.getOrgName();
					tCenterName = PlasCache.getCenterOrgNameByOrgId(tOrgId);
					
					//add by huangbijin 2012-02-09 若找不到中心,则找分管
					if(StringUtils.isBlank(tCenterName)){
						tCenterName = PlasCache.getBranchOrgNameByOrgId(tOrgId);
					}
				}

				map.put("userName", tUserName);
				map.put("deptName", StringUtils.isBlank(tDeptName)?"":tDeptName);
				map.put("centOrgName", StringUtils.isBlank(tCenterName)?"":tCenterName);
				map.put("positionName", StringUtils.isBlank(tUser.getWorkDutyDesc()) ? "" : tUser.getWorkDutyDesc().trim());
				map.put("fixedPhone", StringUtils.isBlank(tUser.getFixedPhone())? "": tUser.getFixedPhone());
				map.put("email", StringUtils.isBlank(tUser.getEmail())? "": tUser.getEmail());
				map.put("sexCd", StringUtils.isBlank(tUser.getSexCd())? "": tUser.getSexCd());
				
				String partPic = service.getPicturePath(tUser.getPlasUserId());
				if(StringUtils.isNotBlank(partPic)){
					map.put("picFullPath", appParamManager.getUaapUrl() + partPic);
				}else{
					map.put("picFullPath", "");
				}
	
				// 决策层:不显示移动电话(同属决策层则显示)
				String mobilePhone1 = StringUtils.isBlank(tUser.getMobilePhone())? "": tUser.getMobilePhone();
				String mobilePhone2 = StringUtils.isBlank(tUser.getMobilePhone2())? "": tUser.getMobilePhone2();
				
				String currentUserDeptCd = SpringSecurityUtils.getCurrentDeptCd();
				
				// 当前用户是决策层
				// 当前用户非决策层,且被搜索的用户非决策层.
				boolean enableFlg = GlobalConstants.EXEC_ORG_CD.equals(currentUserDeptCd)||(!GlobalConstants.EXEC_ORG_CD.equals(tUser.getOrgCd()));
				if(enableFlg){
					map.put("mobilePhone", mobilePhone1 + " " + mobilePhone2);
				}else{
					map.put("mobilePhone", "");
				}
				
				// add by huangbijin 20110-10-24
				// 允许显示手机号码的高层 
				String[] showArr = new String[]{"guojun"};
				for (String tmpUiid : showArr) {
					if(StringUtils.isNotBlank(tmpUiid) && tmpUiid.equals(tUser.getUiid())){
						map.put("mobilePhone",mobilePhone1);
					}
				}
			}
		}
		Struts2Utils.renderJson(map);
		return null;
	}
	
	/**
	 * 加载个人照片
	 */
	
	public String loadUserPic(){

		String tmpUiid = SpringSecurityUtils.getCurrentUiid();
		
		WSPlasService service = Util.getPlasService();
		WsPlasUser tUser = service.getUserByUiid(tmpUiid);
		String partPic = service.getPicturePath(tUser.getPlasUserId());
		
		String url = null;
		if(StringUtils.isBlank(partPic)){
			url = "";
		}else{
			url = appParamManager.getUaapUrl() + partPic;
		}
		Struts2Utils.renderText(url);
		
		return null;
	}
	/**
	 * 功能:搜索在线人数
	 * 
	 * @return
	 */
	public String getCurrentOnlineCount() {
		Struts2Utils.renderText("success," + PlasCache.getUserOnlineCount());
		return null;
	}

	/**
	 * 功能:搜索在线人员
	 * 
	 * @return
	 */
	public String getCurrentOnlineUsers() {
		Struts2Utils.renderJson(PowerUtils.array2String(PlasCache.getOnlineUiidSet()));
		return null;
	}
	
	public List<WsPlasUser> getSearchUserResult() {
		return searchUserResult;
	}

	public void setSearchUserResult(List<WsPlasUser> searchUserResult) {
		this.searchUserResult = searchUserResult;
	}
}

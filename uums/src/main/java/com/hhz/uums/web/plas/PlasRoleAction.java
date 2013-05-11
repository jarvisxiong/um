/**
 * 
 */
package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.app.AppPageManager;
import com.hhz.uums.dao.app.AppSeqManager;
import com.hhz.uums.dao.plas.PlasAppManager;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasRoleGroupManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPosRoleRelManager;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasRoleGroup;
import com.hhz.uums.entity.plas.PlasSysPosRoleRel;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.Util;
import com.hhz.uums.vo.log.LogPlasRole;
import com.hhz.uums.vo.log.LogUtil;

/**
 *-------------------------------------------------------
 * date    			||  author      ||  Description    ||
 * 2011-1-26		|| jiaoxiaofeng ||  create         ||PlasRoleAction.java
 * Description:角色管理
 * -------------------------------------------------------
 **/
@SuppressWarnings("unused")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "plas-role.action", type = "redirect") })
public class PlasRoleAction extends CrudActionSupport<PlasRole> {


	private static final long serialVersionUID = -6091430231692926187L;

	@Autowired
	private PlasAppManager plasAppmManager;	

	@Autowired
	private PlasRoleManager plasRoleManager;
	//新增
	@Autowired
	private PlasRoleGroupManager plasGroupRoleManager;

	@Autowired
	private AppPageManager appPageManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppSeqManager appSeqManager;

	private PlasRole entity;

	private String plasAppId;// 这很重要,否则无法保存对应的应用
	private String plasRoleGroupId;// 这很重要,否则无法保存对应的应用
	
	private String parentId;

	// 操作日志
	private LogPlasRole oldRole;
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	@Autowired
	private PlasSysPosRoleRelManager sysPosRoleRelManager;
	
	
	private Map<String,String> mapGroup;

	public PlasRoleAction() {

	}

	@Override
	public String list() throws Exception {
		return null;
	}

	/**
	 * 功能:编辑关系
	 */
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public void prepareInput() throws Exception{
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		
		
		boolean isNew = false;
		PlasRole t = plasRoleManager.getPlasRoleByRoleCd(entity.getRoleCd());

		// 注意: 这一步很重要

		plasRoleManager.savePlasRole(entity);
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
		
		plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SYSPOS, OperConst.ADD,
				new StringBuffer("[").append(entity.getRoleCd()).append(",").append(entity.getRoleName()).append(
						"]新增角色成功!").toString());
	
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public void prepareSave() {

		if (StringUtils.isNotBlank(getId())) {
			entity = plasRoleManager.getEntity(getId());

			// 记录修改日志使用
			oldRole = LogUtil.transfer(entity);
		} else {
			entity = new PlasRole();
			entity.setPlasApp(plasAppmManager.getEntity(plasAppId));
			// 记录修改日志使用
			oldRole = LogUtil.transfer(entity);
		}
		if(StringUtils.isNotBlank(plasRoleGroupId)) {
			entity.setPlasRoleGroup(plasGroupRoleManager.getEntity(plasRoleGroupId));
		}
	}

	/**
	 * 功能:删除角色,同时删除关联职位
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String delete() throws Exception {
		PlasRole role = plasRoleManager.getEntity(getId());
		String tmpRoleCd = role.getRoleCd();
		String tmpRoleName = role.getRoleName();
		
		int effectRowCount = plasRoleManager.deleteWithPosRel(getId());

		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
		plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SYSPOS, OperConst.ADD,
				new StringBuffer("[").append(tmpRoleCd).append(",").append(tmpRoleName).append("]删除成功!影响职位数：").append(effectRowCount).toString());
	
		Struts2Utils.renderText("success");
		
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return SUCCESS;
	}



	@Override
	protected void prepareModel() throws Exception {

		if (StringUtils.isNotBlank(getId())) {
			entity = plasRoleManager.getEntity(getId());
		}else{
			entity = new PlasRole();
			if(StringUtils.isNotBlank(getPlasAppId())){
				entity.setPlasApp(plasAppmManager.getEntity(getPlasAppId()));
			}
			if(StringUtils.isNotBlank(getPlasAppId())){
				entity.setPlasRoleGroup(plasGroupRoleManager.getEntity(getPlasRoleGroupId()));
			}
			entity.setRecordVersion(1);
		}
		

		//这里个appId肯定不空
		if(entity.getPlasApp() != null){
			mapGroup = new LinkedHashMap<String,String>();
			mapGroup.put("","");
			List<PlasRoleGroup>  tmpList = plasGroupRoleManager.getAppRoleGroup(entity.getPlasApp().getPlasAppId());
			for (PlasRoleGroup tmpGroup : tmpList) {
				mapGroup.put(tmpGroup.getPlasRoleGroupId(), tmpGroup.getRoleGroupName());
			}
		}
	}
	public void prepareCopyRole() throws Exception{
		prepareModel();
	}
	/**
	 *  复制角色，同时复制角色与系统职位的关系
	 */
	public void copyRole(){
		PlasRole role = copyEntity(entity);
		plasRoleManager.savePlasRole(role);
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
		
		plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.ROLE, OperConst.ADD,
				new StringBuffer()
				.append("[").append(role.getRoleCd()).append(",").append(role.getRoleName()).append("]复制角色成功!")
				.append("角色来源:" + entity.getRoleCd() + "," + entity.getRoleName())
				.toString());
		
		Struts2Utils.renderText("success," + role.getPlasRoleId());
	}
	//私有方法
	private PlasRole copyEntity(PlasRole srcRole){
		PlasRole destRole = new PlasRole();
		
		//复制属性：应用、角色组
		destRole.setPlasApp(srcRole.getPlasApp());
		destRole.setPlasRoleGroup(srcRole.getPlasRoleGroup());
		destRole.setRecordVersion(0);
		
		//复制关系
		List<PlasSysPosRoleRel> destRelList = new ArrayList<PlasSysPosRoleRel>();
		List<PlasSysPosRoleRel> srcRelList = srcRole.getPlasSysPosRoleRels();
		if(null!=srcRelList && srcRelList.size()>0){
			PlasSysPosRoleRel destRel = null;
			for(PlasSysPosRoleRel srcRel : srcRelList){
				destRel = new PlasSysPosRoleRel();
				destRel.setPlasSysPosition(srcRel.getPlasSysPosition());
				destRel.setPlasRole(destRole);
				destRelList.add(destRel);
			}
		}
		destRole.setPlasSysPosRoleRels(destRelList);
		
		//处理角色BIZ_CD
		String newRoleBizCd = srcRole.getRoleBizCd();
		if(newRoleBizCd.indexOf("_CP")>0){
			newRoleBizCd = newRoleBizCd.substring(0,newRoleBizCd.length()-6);
		}
		newRoleBizCd +="_CP"+Util.makeRandom(3);
		
		boolean flag= true;
		while(flag){
			if(plasRoleManager.isPropertyUnique("roleBizCd", newRoleBizCd, srcRole.getRoleBizCd())){
				destRole.setRoleBizCd(newRoleBizCd);
				flag = false;
			}else{
				flag=true;
			}
		}
		flag= true;
		//处理角色CD
		String newRoleCd = srcRole.getRoleCd();
		if(newRoleCd.indexOf("_CP")>0){
			newRoleCd = newRoleCd.substring(0,newRoleCd.length()-6);
		}
		newRoleCd +="_CP"+Util.makeRandom(3);
		
		while(flag){
			if(plasRoleManager.isPropertyUnique("roleCd", newRoleCd, srcRole.getRoleCd())){
				destRole.setRoleCd(newRoleCd);
				flag = false;
			}else{
				flag=true;
			}
		}
		flag= true;
		//处理角色NAME
		String newRoleName = srcRole.getRoleName();
		if(newRoleName.indexOf("_CP")>0){
			newRoleName = newRoleName.substring(0,newRoleName.length()-6);
		}
		newRoleName +="_CP"+Util.makeRandom(3);
		
		while(flag){
			if(plasRoleManager.isPropertyUnique("roleName", newRoleName, srcRole.getRoleName())){
				destRole.setRoleName(newRoleName);
				flag = false;
			}else{
				flag=true;
			}
		}
		return destRole;
	}

	public void isRoleCdExist(){
		String newRoleCd = Struts2Utils.getParameter("roleCd").trim();
		String oldRoleCd = Struts2Utils.getParameter("oldRoleCd").trim();

		if (plasRoleManager.isPropertyUnique("roleCd", newRoleCd, oldRoleCd)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("failure");
		}
	}
	public void isRoleBizCdExist(){
		String newroleBizCd = Struts2Utils.getParameter("roleBizCd").trim();
		String oldroleBizCd = Struts2Utils.getParameter("oldRoleBizCd").trim();

		if (plasRoleManager.isPropertyUnique("roleBizCd", newroleBizCd, oldroleBizCd)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("failure");
		}
	}
	public void isRoleNameExist(){
		String newroleName = Struts2Utils.getParameter("roleName").trim();
		String oldroleName = Struts2Utils.getParameter("oldRoleName").trim();

		if (plasRoleManager.isPropertyUnique("roleBizCd", newroleName, oldroleName)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("failure");
		}
	}
	
	/**
	 * 快捷查询角色
	 * @param value 模糊匹配值
	 * @author liuzh
	 */
	public void quickSearchRoleList() {
		String tmpName = Struts2Utils.getParameter("value").toLowerCase();
		
		List<PlasRole> result = plasRoleManager.getFindRoleList(tmpName, "10");
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>();
		for (PlasRole role : result) {
			map = new HashMap<String, String>();
			map.put("plasRoleId", role.getPlasRoleId());
			map.put("roleCd", role.getRoleCd());
			map.put("appChnName", role.getPlasApp().getAppChnName());
			map.put("roleGroupName", role.getPlasRoleGroup().getRoleGroupName());
			map.put("roleName", role.getRoleName());
			tmpList.add(map);
		}
		Struts2Utils.renderJson(tmpList);
	}
	
	public String getPlasAppId() {
		return plasAppId;
	}
	
	public void setPlasAppId(String plasAppId) {
		this.plasAppId = plasAppId;
	}
	
	public String getPlasRoleGroupId() {
		return plasRoleGroupId;
	}
	
	public void setPlasRoleGroupId(String plasRoleGroupId) {
		this.plasRoleGroupId = plasRoleGroupId;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public PlasRole getModel() {
		return entity;
	}

	public Map<String, String> getMapGroup() {
		return mapGroup;
	}

	public void setMapGroup(Map<String, String> mapGroup) {
		this.mapGroup = mapGroup;
	}
	 
}

package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.res.ResAuthTypeManager;
import com.hhz.ump.dao.res.ResOptionManager;
import com.hhz.ump.dao.res.ResOptionValueManager;
import com.hhz.ump.dao.res.ResRightModuleRelManager;
import com.hhz.ump.dao.res.ResRightsManager;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResOption;
import com.hhz.ump.entity.res.ResOptionValue;
import com.hhz.ump.entity.res.ResRights;
import com.hhz.ump.web.res.vo.ResRightsVo;

@Namespace("/res")
public class ResRightsAction extends CrudActionSupport<ResRights> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6577982913622518364L;
	
	@Autowired
	private ResRightsManager resRightsManager;
	@Autowired
	private ResAuthTypeManager resAuthTypeManager;
	@Autowired
	private ResOptionManager resOptionManager;
	@Autowired
	private ResRightModuleRelManager resRightModuleRelManager;
	@Autowired
	private ResOptionValueManager resOptionValueManager;

	
	private List<ResRights> resRights;
	private ResRights entity;
	private String id;
	private String step;
	private String moduleId;
	
	public String buildTree(){
		List<ResRights> rights = new ArrayList<ResRights>();
		
	    String resRightsId = Struts2Utils.getParameter("resRightsId");
		Map<String, Object> values = new HashMap<String, Object>();
		//values.put("ifActive", true);
		String hql = "from ResRights r where r.parentId = '0' order by r.sequenceNo";
		if(StringUtils.isNotBlank(resRightsId)){
			hql = "from ResRights r where r.parentId = :parentId order by r.sequenceNo";
		    values.put("parentId", resRightsId);	
		}else{
			rights.add(new ResRights("0","网批权责",1,"true"));
			
		}
		
	    rights.addAll(resRightsManager.find(hql,values));
	    Struts2Utils.renderJson(rights);
		return null;
	}
	
	/**
	 * 根据编号获取权限树 数据
	 * 返回的list中 authtypeId已经改变成Cd
	 * @author CP
	 * @param id 父Id
	 * @return null
	 */
	public String getTreeDataById(){
		List<ResRightsVo> tlist =  new ArrayList<ResRightsVo>();
		if(StringUtils.isNotBlank(id)){
			resRights = resRightsManager.getListByParentId(id, "");
			for (ResRights rights : resRights) {
				ResRightsVo t = new ResRightsVo(rights.getResRightsId(),rights.getRightsName(),rights.getParentId(),rights.getResAuthTypeId(),"",rights.getSequenceNo(),rights.getIfActive(),rights.getIfActive_input(),rights.getResOptionId(),rights.getResOptionValueId(),rights.getTurnAfterOptionId(),rights.getRemark());
				if(StringUtils.isNotBlank(t.getResAuthTypeId())){
					t.setResAuthTypeCd(resAuthTypeManager.getEntity(t.getResAuthTypeId()).getAuthTypeCd());
				}
				tlist.add(t);
			}
			Struts2Utils.renderJson(tlist);
//			Struts2Utils.renderJson(resRights);
		}
		return null;
	}
	/**
	 * 根据Id查出cd
	 * @return
	 */
	public String getAuthCdById(){
		ResAuthType tobject = resAuthTypeManager.getEntity(moduleId);
		Struts2Utils.renderText(tobject.getAuthTypeCd());
		return null;
	}
	/**
	 * 根据resRightsId查出resRightsCd
	 * @return
	 */
	public String getAuthCdByResRightsId(){
		String resRightsId = Struts2Utils.getParameter("resRightsId");
		ResAuthType tobject = resAuthTypeManager.getEntity(resRightsId);
		Struts2Utils.renderText(tobject.getAuthTypeCd());
		return null;
	}
	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return SUCCESS;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}
	
	public String tree(){
		
		return "tree";
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isBlank(id)) {
			entity = new ResRights();
		}else{
			entity = resRightsManager.getEntity(id);
		}
		
	}

	@Override
	public String save() throws Exception {
		resRightsManager.saveResRights(entity);
		//Struts2Utils.renderText("ss");
		return null;
	}

	@Override
	public ResRights getModel() {
		return entity;
	}
	
	private String resAuthTypeId;

	public String getResAuthTypeId() {
		return resAuthTypeId;
	}


	public void setResAuthTypeId(String resAuthTypeId) {
		this.resAuthTypeId = resAuthTypeId;
	}


	public String getResAuthName(){
		if(StringUtils.isNotBlank(resAuthTypeId)){
		ResAuthType ra = resAuthTypeManager.getEntity(resAuthTypeId);
		Struts2Utils.renderText(ra.getAuthTypeName());
		}else{
			Struts2Utils.renderText("");	
		}
		return null;
	}
	
	private String resOptionId;
	
	public String getResOptionId() {
		return resOptionId;
	}

	public void setResOptionId(String resOptionId) {
		this.resOptionId = resOptionId;
	}

	public String getResOptionName(){
		if(StringUtils.isNotBlank(resOptionId)){
			ResOption ro = resOptionManager.getEntity(resOptionId);
			Struts2Utils.renderText(ro.getOptionName());
		}
		return null;
	}
	
	private String resOptionValueId;
	

	public String getResOptionValueId() {
		return resOptionValueId;
	}

	public void setResOptionValueId(String resOptionValueId) {
		this.resOptionValueId = resOptionValueId;
	}
	
	public String getResOptionValueName(){
		if(StringUtils.isNotBlank(resOptionValueId)){
			ResOptionValue rov = resOptionValueManager.getEntity(resOptionValueId);
			Struts2Utils.renderText(rov.getOptionName());
		}
		return null;		
	}

	public String removeNode(){
		try{
		resRightsManager.deleteResRights(id);
		Struts2Utils.renderText("success");
		}catch(Exception e){
			Struts2Utils.renderText(e.getMessage());
			
		}
		return null;
	}
	/**
	 * 选择向导
	 * @return
	 */
   public String addSelect(){
	   id = resRightModuleRelManager.getResRightsIdByMId(moduleId);
	   return "add-select";
   }
   
   public String resTree(){
	   return "resAuthTree";
   }

	public ResRights getEntity() {
		return entity;
	}


	public void setEntity(ResRights entity) {
		this.entity = entity;
	}


	@Override
	public String getId() {
		return id;
	}


	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public List<ResRights> getResRights() {
		return resRights;
	}

	public void setResRights(List<ResRights> resRights) {
		this.resRights = resRights;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	
	//liwei3
	public void prepareAddSelect(){
		System.out.println("------------------=========>>>>>>>>>prepareAddSelect");
		entityTmpId = RandomUtils.generateTmpEntityId();
		System.out.println("------------------=========>>>>>>>>>entityTmpId:"+entityTmpId);
	}
	//新增记录时，附件上传使用的临时ID
	private String entityTmpId;
	public String getEntityTmpId() {
		return entityTmpId;
	}
	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}
}

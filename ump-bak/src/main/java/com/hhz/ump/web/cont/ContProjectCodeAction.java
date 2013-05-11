package com.hhz.ump.web.cont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.util.CodeNameUtil;

public class ContProjectCodeAction extends CrudActionSupport<ContProjectCode> {
	private static final long serialVersionUID = 8071052318603147332L;
	@Autowired
	private ContProjectCodeManager contProjectCodeManager;
	private ContProjectCode entity;

	
	//保存更新授权信息
	private String userCds;
	private String projectName;
	private String remark;

	@Override
	public ContProjectCode getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	/** 
	 * @param contTypeCd 类型
	 * @param remark  关键字（备注）
	 * @param authUiid 授权用户ID
	 */
	@Override
	public String list() throws Exception {

		//String tProjectName = Struts2Utils.getParameter("projectName");
		String tContTypeCd = Struts2Utils.getParameter("contTypeCd");
		String tRemark = Struts2Utils.getParameter("remark");
		String tAuthUiid = Struts2Utils.getParameter("authUiid");
		 
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from ContProjectCode t where 1=1 ");
		
		  
		if(StringUtils.isNotBlank(tRemark)){
			hql.append(" and t.remark like :remark");
			values.put("remark","%" + tRemark.trim() + "%");
		}
		  
		if(StringUtils.isNotBlank(tContTypeCd)){
			hql.append(" and t.codeType = :contType ");
			values.put("contType",tContTypeCd.trim());
		}
		  
		if(StringUtils.isNotBlank(tAuthUiid)){
			hql.append(" and (");
			String arr[] = tAuthUiid.split(";");
			for(int i = 0; i<arr.length; i++){
				if(i>0){
					hql.append(" or ");
				}
				hql.append(" t.authority like :authority" + i).append(" ");
				values.put(("authority"+String.valueOf(i)), "%"+arr[i]+"%");
			}
			hql.append(" ) ");
		}
		hql.append(" order by t.codeType asc, t.remark asc ");
		
		//不分页
		List<ContProjectCode> list = contProjectCodeManager.find(hql.toString(), values);
		page.setPageSize(list.size());
		page.setTotalCount(list.size());
		page.setResult(list);
		return "list";
	}

	/**
	 * 快速搜索合同库项目列表
	 * 
	 * @param value 项目名称
	 * @param codeType 1-地产 2-商业 3-酒店
	 * 
	 * @return
	 * @throws Exception
	 */
	public void quickSearch() throws Exception {
		
		projectName = Struts2Utils.getParameter("value");
		String codeType = Struts2Utils.getParameter("codeType");
		if (StringUtils.isBlank(codeType)) {
			codeType = "1";
		}
		
		//contentd by huangbijin 2012-06-28
//		// 搜索合同项目
//		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
//		filters.add(new PropertyFilter("EQS_codeType", codeType));
//		// 模糊搜索工程名称
//		if (StringUtils.isNotBlank(projectName)) {
//			filters.add(new PropertyFilter("LIKES_projectName", projectName));
//		}
		
		//add by huangbijin 2012-06-28
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("codeType", codeType);
		
		StringBuffer hql = new StringBuffer();
		hql.append(" select distinct(t) from ContProjectCode t where 1=1 and codeType = :codeType ");
		if(StringUtils.isNotBlank(projectName)){
			hql.append(" and t.projectName like :projectName");
			values.put("projectName", "%"+projectName+"%");
		}
		hql.append(" order by t.codeType asc, t.remark asc ");
		page.setPageSize(100);
		page = contProjectCodeManager.findPage(page, hql.toString(), values);
		

		//返回结果列表
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		//构造抬头行
		
		Map<String, String> map = null;
		/*
		map = new HashMap<String, String>();
		map.put("headFlg", "1");//标题
		map.put("projectCd", "项目编号");
		map.put("projectName", "请选择项目");
		list.add(map);
		*/
		

		Map<String,String> tmpMap = new HashMap<String,String>();
		String tCd = null;
		String tName = null;
		
		for (ContProjectCode code : page.getResult()) {
			tCd = code.getProjectCd();
			tName = CodeNameUtil.getDeptNameByCd(code.getProjectCd());
			if(StringUtils.isNotBlank(tCd)){
				if(tmpMap != null && (!tmpMap.keySet().contains(tCd))){
					map = new HashMap<String, String>();
					map.put("projectCd", tCd);// 项目CD
					map.put("projectName", tName);
					list.add(map);
					tmpMap.put(tCd,tName);
				}
			}
		}
		
		Struts2Utils.renderJson(list);

	}

	@Override
	public String input() throws Exception {
		return "input";
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = contProjectCodeManager.getEntity(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new ContProjectCode();
		}
	}

	@Override
	public String save() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity.setAuthority(userCds);
			entity.setRemark(remark);
		}
		contProjectCodeManager.saveContProjectCode(entity);
		Struts2Utils.renderText("ok");
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	public String getUserCds() {
		return userCds;
	}

	public void setUserCds(String userCds) {
		this.userCds = userCds;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * 进入项目权限 设置主页面
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String main() throws Exception {

		list();

		return "main";
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

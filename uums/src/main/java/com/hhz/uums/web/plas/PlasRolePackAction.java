package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.plas.PlasRolePackManager;
import com.hhz.uums.dao.plas.PlasRolePackRelManager;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasRolePack;
import com.hhz.uums.entity.plas.PlasRolePackRel;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.web.CrudActionSupport;

/**
 * 打包管理
 * 
 * @author liuzhihui  |  2012-02-01
 *
 */
public class PlasRolePackAction extends CrudActionSupport<PlasRolePack> {
	
	private static final long serialVersionUID = 1L;
	
	private Page<PlasRolePack> page = new Page<PlasRolePack>(20);// 每页20条记录
	
	@Autowired
	private PlasRolePackManager plasRolePackManager;
	@Autowired
	private PlasRolePackRelManager plasRolePackRelManager;

	private PlasRolePack entity;
	
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		String filter_LIKES_packName = Struts2Utils.getParameter("filter_LIKES_packName");
		String enableFlg = Struts2Utils.getParameter("filter_EQB_enableFlg");
		Boolean filter_EQB_enableFlg=BooleanUtils.toBooleanObject(enableFlg);
		
		StringBuffer hql = new StringBuffer("from PlasRolePack as t where 1=1 ");
		Map<String, Object> pram = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(filter_LIKES_packName)) {
			hql.append(" and packName like :packName");
			pram.put("packName", "%" + filter_LIKES_packName + "%");
		}
		if (filter_EQB_enableFlg!=null) {
			hql.append(" and enableFlg =:enableFlg");
			pram.put("enableFlg", filter_EQB_enableFlg);
		}
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		
		//默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			hql.append(" order by createdDate desc");
		} else {
			hql.append(" order by ").append(sortField).append(" ").append(order);
		}
		page = plasRolePackManager.findPage(page, hql.toString(), pram);
		//将PlasRolePack的子属性plasRolePackRels,plasRolePackPosRels置为空,由于hibernate设置为延迟加载，故为空。
		JsonUtil.renderJson(page, new String[] { "plasRolePackRels", "plasRolePackPosRels" });
		
		return null;
	}
	
	@Override
	public String save() throws Exception {
		if(entity.getEnableFlg().equals("true")){
			entity.setEnableFlg("1");
		}else{
			entity.setEnableFlg("0");
		}
		plasRolePackManager.savePlasRolePack(entity);
		Struts2Utils.renderHtml("success");
		return null;
	}
	
	/**
	 * 保存角色包的对应的角色
	 * @return
	 * @throws Exception
	 */
	public String saveBatch() throws Exception {
		if(StringUtils.isBlank(getId())){
			Struts2Utils.renderText("plasRolePackId is blank!");
		}else{
			String addRoleIds = Struts2Utils.getParameter("addRoleIds");
			String delRoleIds = Struts2Utils.getParameter("delRoleIds");
			plasRolePackRelManager.saveBatch(getId(), addRoleIds, delRoleIds);
			Struts2Utils.renderText("success");
		}
		return null;
	}
	
	@Override
	public String delete() throws Exception {
		try {
			plasRolePackManager.deletePlasRolePack(getId());
			Struts2Utils.renderHtml("success");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderHtml("fail");
		}
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}
	
	/**
	 * 加载角色树
	 */
	public void loadRoleTree() throws Exception {
		//isChecked=true去除没勾选的，否则全部显示
		String isChecked = Struts2Utils.getParameter("isChecked");
		List<PlasRole> roleCheckList = new ArrayList<PlasRole>();
		List<PlasRolePackRel> plasRolePackRelList = plasRolePackRelManager.getCurRolePackRelList(getId());
		for (PlasRolePackRel rolePackRel : plasRolePackRelList) {
			roleCheckList.add(rolePackRel.getPlasRole());
		}
		if("true".equals(isChecked)){
			Struts2Utils.renderJson(TreePanelUtil.createRolePackTreeChecked(roleCheckList));
		}else{
			Struts2Utils.renderJson(TreePanelUtil.createRoleGroupTreeCheck(roleCheckList));
		}
	}
	
	/**
	 * 启用/停用角色包
	 * @return
	 */
	public String enableRolePack() throws Exception{
		String status = Struts2Utils.getParameter("status");
		plasRolePackManager.enableRolePack(getId(),status);
		Struts2Utils.renderHtml("success");
		return null;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = plasRolePackManager.getEntity(getId());
			if("1".equals(entity.getEnableFlg())){
				entity.setEnableFlg(String.valueOf("true"));
			}else{
				entity.setEnableFlg(String.valueOf("false"));
			}
		}else{
			entity = new PlasRolePack();
		}
	}

	@Override
	public PlasRolePack getModel() {
		return entity;
	}

}

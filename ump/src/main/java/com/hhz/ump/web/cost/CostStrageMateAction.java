package com.hhz.ump.web.cost;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostStrageMateManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.cost.CostStrageMate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.CodeNameUtil;

@Namespace("/cost")
public class CostStrageMateAction extends CrudActionSupport<CostStrageMate> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CostStrageMateManager costStrageMateManager;
	@Autowired
	private ResApproveInfoManager resApproveInfoManager;

	private CostStrageMate entity;
	private String id;
	private String authTypeName;
	
	private String title;
	private String mateName;

	public String main(){
		return "main";
	}
	@Override
	public String list() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from CostStrageMate t where 1=1");
		if (StringUtils.isNotBlank(title)) {
			hql.append(" and t.title like :title");
			values.put("title", "%" + title.trim() + "%");
		}
		if (StringUtils.isNotBlank(mateName)) {
			hql.append(" and t.mateName like :mateName");
			values.put("mateName", "%" + mateName.trim() + "%");
		}
		hql.append(" order by t.createdDate desc");
		page.setPageSize(30);
		page = costStrageMateManager.findPage(page, hql.toString(), values);
		return "list";
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
		return "input";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = costStrageMateManager.getEntity(getId());
			ResApproveInfo resInfo = resApproveInfoManager.getEntity(entity.getResApproveInfoId());
			if (resInfo != null) {
				authTypeName = CodeNameUtil.getResAuthTypeNameByCd(resInfo.getAuthTypeCd());
			}
		} else {
			entity = new CostStrageMate();
		}
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public CostStrageMate getModel() {
		return entity;
	}

	public CostStrageMate getEntity() {
		return entity;
	}

	public void setEntity(CostStrageMate entity) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}
	public String getAuthTypeName() {
		return authTypeName;
	}
	public void setAuthTypeName(String authTypeName) {
		this.authTypeName = authTypeName;
	}

}

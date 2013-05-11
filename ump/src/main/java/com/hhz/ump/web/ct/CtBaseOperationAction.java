package com.hhz.ump.web.ct;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.ct.CtBaseOperationManager;
import com.hhz.ump.dao.ct.CtOperItemRelManager;
import com.hhz.ump.entity.ct.CtBaseOperation;
import com.hhz.ump.entity.ct.CtOperItemRel;

@Namespace("/ct")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "ct-base-operation!list.action", type = "redirect") })
public class CtBaseOperationAction extends CrudActionSupport<CtBaseOperation> {

	private static final long serialVersionUID = -849240083339798922L;

	@Autowired
	private CtBaseOperationManager ctBaseOperationManager;
	@Autowired
	private CtOperItemRelManager ctOperItemRelManager;

	// 分页搜索
	private CtBaseOperation entity;
	private String queOperTypeName;
	private String queCtLedgerId;
	private String operName;
	private String operDesc;

	@Override
	public CtBaseOperation getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		queryOperation();
		return "list";
	}

	/**
	 * 业态搜索
	 * 
	 * @throws Exception
	 */
	public void queryOperation() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from CtBaseOperation where 1=1 ");
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		if (StringUtils.isNotBlank(queOperTypeName)) {
			hql.append(" and operName like :queOperTypeName");
			param.put("queOperTypeName", "%" + queOperTypeName + "%");
		}
		hql.append(" order by operName,ctBaseOperationId ");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField + ",createdDate");
			page.setOrder(order + "," + Page.DESC);
		}
		page = ctBaseOperationManager.findPage(page, hql.toString(), param);
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String vilidateOperName() {
		Boolean status = false;
		if (getId() != null) {
			entity = ctBaseOperationManager.getEntity(getId());
		} else {
			entity = new CtBaseOperation();
		}
		if (StringUtils.isNotBlank(operName)) {// 判断业态名称重复
			List<CtBaseOperation> list = ctBaseOperationManager.getOperationByName(operName);
			int count = list.size();
			if (count > 0) {
				if (count > 2) {
					status = true;
				}else if(!list.get(0).getCtBaseOperationId().equals(getId())){
					
					status = true;
					
				}
			}
		}
		if (status) {
			Struts2Utils.renderText("业态名称已存在");
			return null;
		} else {
			try {
				save();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(entity.getOperCd())) {
			entity.setOperCd(RandomUtils.generateTmpEntityId());
		}
		entity.setOperName(operName);
		entity.setOperDesc(operDesc);
		ctBaseOperationManager.saveCtBaseOperation(entity);
		return null;
	}

	public String editOperation() throws Exception {
		entity = ctBaseOperationManager.getEntity(getId());
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (entity != null) {
			map.put("id", entity.getCtBaseOperationId());
			if (entity.getOperDesc() != null) {
				map.put("operDesc", entity.getOperDesc());
			}
			if (entity.getOperName() != null) {
				map.put("operName", entity.getOperName());
			}
		}
		Struts2Utils.renderJson(map);
		return null;
	}

	@Override
	public String delete() throws Exception {
		if (getId() != null) {
			entity = ctBaseOperationManager.getEntity(getId());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("ctOperationId", getId());
			StringBuffer hql = new StringBuffer();
			hql.append(" from CtOperItemRel where 1=1 ");
			hql.append("and ctOperation.ctOperationId =:ctOperationId");
			List<CtOperItemRel> list = ctOperItemRelManager.find(hql.toString(), param);
			if (list == null || list.size() <= 0) {
				ctBaseOperationManager.delete(entity);
				Struts2Utils.renderHtml("success");
			} else {
				Struts2Utils.renderHtml("该业态已经被占用");
			}
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (getId() != null) {
			entity = ctBaseOperationManager.getEntity(getId());
		} else {
			entity = new CtBaseOperation();
		}
	}

	public String getQueOperTypeName() {
		return queOperTypeName;
	}

	public String getQueCtLedgerId() {
		return queCtLedgerId;
	}

	public void setQueOperTypeName(String queOperTypeName) {
		this.queOperTypeName = queOperTypeName;
	}

	public void setQueCtLedgerId(String queCtLedgerId) {
		this.queCtLedgerId = queCtLedgerId;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getOperDesc() {
		return operDesc;
	}

	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}

}

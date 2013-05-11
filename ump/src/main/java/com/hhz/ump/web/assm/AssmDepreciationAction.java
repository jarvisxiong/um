package com.hhz.ump.web.assm;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.assm.AssmDepreciationManager;
import com.hhz.ump.entity.assm.AssmDepreciation;

@Namespace("/assm")
public class AssmDepreciationAction extends CrudActionSupport<AssmDepreciation> {

	private static final long serialVersionUID = -8818821430740848524L;
	@Autowired
	private AssmDepreciationManager assmDepreciationManager;
	
	private Page<AssmDepreciation> page = new Page<AssmDepreciation>(10);
	
	/**
	 * 搜索资产折旧列表
	 */
	@Override
	public String list() throws Exception {
		Map<String,Object> values=new HashMap<String,Object>();
		String hql = " from AssmDepreciation t order by t.assmModelId asc";
		page = assmDepreciationManager.findPage(page, hql, values);
		return "loadList";
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
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}
	@Override
	public String save() throws Exception {
		return null;
	}
	@Override
	public AssmDepreciation getModel() {
		return null;
	}
	
	@Override
	public Page<AssmDepreciation> getPage() {
		return page;
	}
	
	public void setPage(Page<AssmDepreciation> page) {
		this.page = page;
	}
	
}
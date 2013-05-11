package com.hhz.ump.web.assm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.assm.AssmModelManager;
import com.hhz.ump.dao.assm.AssmModelStandardManager;
import com.hhz.ump.entity.assm.AssmModel;
import com.hhz.ump.entity.assm.AssmModelStandard;

@Namespace("/assm")
public class AssmModelAction   extends CrudActionSupport<AssmModel> {

	private static final long serialVersionUID = 8419603820380225544L;
	
	@Autowired
	protected AssmModelManager assmModelManager;
	@Autowired
	protected AssmModelStandardManager assmModelStandardManager;
	
	private AssmModel entity;
	private Page<AssmModel> page=new Page<AssmModel>(10);
	
	/**
	 * 设备型号搜索列表
	 * @return
	 */
	public String loadList(){
		//搜索条件
		String assmName = Struts2Utils.getParameter("s_assmName");
		String assmCode = Struts2Utils.getParameter("s_assmCode");
		String proCode = Struts2Utils.getParameter("s_proCode");
		String fullCode = Struts2Utils.getParameter("s_fullCode");
		String parentId=Struts2Utils.getParameter("parentId");

		Map<String,Object> values=new HashMap<String,Object>();
		StringBuffer hql=new StringBuffer();
		hql.append(" from AssmModel t where 1=1 ");
		if(StringUtils.isNotBlank(parentId)){
			hql.append(" and t.pratentId = :parentId");
			values.put("parentId", parentId);
		}
		if(StringUtils.isNotBlank(assmName)) {
			hql.append(" and t.assmName like :assmName");
			values.put("assmName", "%"+assmName.trim()+"%");
		}
		if(StringUtils.isNotBlank(assmCode)) {
			hql.append(" and t.assmCode like :assmCode");
			values.put("assmCode", "%"+assmCode.trim()+"%");
		}
		if(StringUtils.isNotBlank(proCode)) {
			hql.append(" and t.proCode like :proCode");
			values.put("proCode", "%"+proCode.trim()+"%");
		}
		if(StringUtils.isNotBlank(fullCode)) {
			hql.append(" and t.fullCode like :fullCode");
			values.put("fullCode", "%"+fullCode.trim()+"%");
		}
		hql.append(" order by t.assmLevel desc,t.createdDate asc");
		page=assmModelManager.findPage(page, hql.toString(), values);
		return "loadList";
	}
	
	private void prepareNewMode(){
		getModel();
	}
	
	/**
	 * 新增设备型号
	 * @return
	 */
	public String newMode(){
		String inp_pratentId = Struts2Utils.getParameter("inp_pratentId");
		String inp_assmName = Struts2Utils.getParameter("inp_assmName");
		String inp_assmCode = Struts2Utils.getParameter("inp_assmCode");
		String inp_proCode = Struts2Utils.getParameter("inp_proCode");
		String inp_fullCode = Struts2Utils.getParameter("inp_fullCode");
		entity = new AssmModel();
		if(StringUtils.isBlank(inp_proCode)) {
			entity.setProCode("/");
		}else{
			entity.setProCode(inp_proCode);
		}
		Short assmLevel = 0;
		if(StringUtils.isNotBlank(inp_pratentId)){
			entity.setPratentId(inp_pratentId);
			AssmModel parent=assmModelManager.getEntity(inp_pratentId);
			assmLevel = Short.valueOf(String.valueOf(parent.getAssmLevel().intValue()+1));
			entity.setAssmLevel(assmLevel);
		}else{
			//叶子节点
			entity.setAssmLevel(Short.valueOf("4"));
		}
		entity.setAssmName(inp_assmName);
		entity.setAssmCode(inp_assmCode);
		entity.setFullCode(inp_fullCode);
		//如果是新增的情况
		if(!assmModelManager.modelExsit(entity)){			
			assmModelManager.saveAssmModel(entity);
			
			//保存三级设备时同时保存标准配置  -Add by liuzhihui 2012-05-08
			if (assmLevel == 3) {
				assmModelStandardManager.findNotExsitProject(entity);
			}
			if(Short.valueOf("4").equals(entity.getAssmLevel())){
				Struts2Utils.renderText("success,0");
			}else{
				Struts2Utils.renderText("success,1,"+entity.getPratentId()+","+entity.getAssmModelId());
			}
			
		}else{
			Struts2Utils.renderText("本设备型号已经存在");
		}		
		return null;
	}
	
	
	/**
	 * 快速搜索设备型号
	 * @param value 输入值
	 * @return
	 */
	public String quickSearch() throws Exception {
		String value =Struts2Utils.getParameter("value");
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from AssmModel t where 1=1");
		if(StringUtils.isNotBlank(value)){
			hql.append(" and( t.assmName like :modelValue");
			
			hql.append(" or t.assmCode like :modelValue");
			hql.append(" or t.proCode like :modelValue");
			hql.append(" or t.fullCode like :modelValue)");
			param.put("modelValue", "%"+value.trim()+"%");
		}
		hql.append(" order by t.createdDate asc");
		page.setPageSize(20);
		page = assmModelManager.findPage(page, hql.toString(),param);
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (AssmModel tmp : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("assmModelId", tmp.getAssmModelId());
			map.put("assmName", tmp.getAssmName());
			map.put("assmCode", tmp.getAssmCode());
			String proCode = "";
			String fullCode = "";
			if (StringUtils.isNotBlank(tmp.getProCode())) {
				proCode = tmp.getProCode();
			}
			if (StringUtils.isNotBlank(tmp.getFullCode())) {
				fullCode = tmp.getFullCode();
			}
			map.put("proCode", proCode);
			map.put("fullCode", fullCode);
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}
	
	public String getParentId() throws Exception {
		String modelId = Struts2Utils.getParameter("modelId");
		if(StringUtils.isNotBlank(modelId)){
			AssmModel model = assmModelManager.getEntity(modelId);
			if(model != null){
				String str = null;
				if(model.getAssmLevel() == 4){
					AssmModel thridModel = assmModelManager.getEntity(model.getPratentId());
					str = thridModel.getPratentId()+","+thridModel.getAssmModelId();
				}else{
					String parentId = model.getPratentId();
					str = parentId+","+model.getAssmModelId();
				}
				Struts2Utils.renderText(str);
			}
		}
		return null;
	}
	
	/**
	 * 验证设备名称是否重复
	 * @return
	 * @throws Exception
	 */
	public String checkAssmName() throws Exception {
		String assmName = Struts2Utils.getParameter("assmName");
		if (StringUtils.isNotBlank(assmName)) {
			if(assmModelManager.assmNameExsit(assmName)){
				Struts2Utils.renderText("exsit");
			}else{
				Struts2Utils.renderText("success");
			}
		}
		return null;
	}
	
	
	/**
	 * 快速搜索搜索选择数据后查看单条记录
	 * 
	 * @param assmModelId 设备型号ID
	 * @return
	 */
	public String loadModelById() throws Exception {
		//搜索条件
		String assmModelId=Struts2Utils.getParameter("assmModelId");
		Map<String,Object> values=new HashMap<String,Object> ();
		StringBuffer hql=new StringBuffer();
		hql.append(" from AssmModel t where 1=1 ");
		if(StringUtils.isNotBlank(assmModelId)){
			hql.append(" and t.assmModelId = :assmModelId");
			values.put("assmModelId", assmModelId);
			hql.append(" order by t.createdDate asc");
			page=assmModelManager.findPage(page, hql.toString(), values);
		}
		return "loadList";
	}
	
	@Override
	public String delete() throws Exception {
		return null;
	}

	/**
	 * 批量删除设备型号
	 */
	@Override
	public String deleteBatch() throws Exception {
		//需要删除的设备型号IDS
		String modelIds=Struts2Utils.getParameter("modelIds");
		//如果不为空，则删除相应的设备型号信息
		if(StringUtils.isNotBlank(modelIds)){
			String [] mids=modelIds.split(",");
			AssmModel model = null;
			String str1 = "";
			String str2 = "";
			String str = "";
			StringBuffer sb1 = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			for (int i = 0; i < mids.length; i++) {
				model = assmModelManager.getEntity(mids[i]);
				if (model != null) {
					List<AssmModel> mList = null;
					if (model.getAssmLevel()==3) { //三级设备
						mList = assmModelManager.getAssmModelByPratentId(model.getAssmModelId());
						if (mList != null && mList.size() > 0) {
							sb1.append("‘"+model.getAssmName()+"’,");
						}else{
							if (model.getAssmModelStandards().size() > 0) {
								for(AssmModelStandard tmp : model.getAssmModelStandards()) {
									//删除三级节点对应的标准配置
									assmModelStandardManager.delete(tmp);
								}
							}
							assmModelManager.delete(model);
						}
					}else if (model.getAssmLevel()==4) { //四级设备
						if (model.getAssmAccounts().size() > 0) {
							sb2.append("‘"+model.getAssmName()+"’,");
						}else{
							assmModelManager.delete(model);
						}
					}
				}
			}
			//assmModelManager.deleteBatch(mids);
			String[] s1 = sb1.toString().split(",");
			String[] s2 = sb2.toString().split(",");
			if (s1.length > 0 && StringUtils.isNotBlank(s1[0])) {
				str1 = sb1.toString().substring(0,sb1.toString().length()-1);
			}
			if (s2.length > 0 && StringUtils.isNotBlank(s2[0])) {
				str2 = sb2.toString().substring(0,sb2.toString().length()-1);
			}
			if (StringUtils.isBlank(sb1.toString()) && StringUtils.isBlank(sb2.toString())) {
				str = "success";
			}
			if (!StringUtils.equals(str, "success")) {
				str = str1+"|"+str2;
			}
			Struts2Utils.renderText(str);
		}else{
			Struts2Utils.renderText("error");
		}
		return null;
	}

	@Override
	public String input() throws Exception {
		getModel();
		return "input";
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {	
		getModel();
	}

	/**
	 * 编辑设备型号信息
	 */
	@Override
	public String save() throws Exception {
		//修改设备型号信息相关字段
		if(entity!=null){
			assmModelManager.saveAssmModel(entity);
			Struts2Utils.renderText(entity.getPratentId()+","+entity.getAssmModelId());
		}	
		return null;
	}

	@Override
	public AssmModel getModel() {
		String assmModelId=Struts2Utils.getParameter("assmModelId");
		if(StringUtils.isNotBlank(assmModelId)){
			entity=assmModelManager.getEntity(assmModelId);
		}
		return entity;
	}


	public AssmModel getEntity() {
		return entity;
	}


	public void setEntity(AssmModel entity) {
		this.entity = entity;
	}


	@Override
	public Page getPage() {
		return page;
	}


	public void setPage(Page page) {
		this.page = page;
	}
	
	

}

package com.hhz.uums.web.bis;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.bis.BisSecGroupManager;
import com.hhz.uums.entity.bis.BisSecGroup;
import com.hhz.uums.utils.DictContants;

/**
 * @author huangbj 2009-12-25
 */
public class BisSecGroupAction extends CrudActionSupport<BisSecGroup> {
 
	private static final long serialVersionUID = 7963479781508025924L;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	private BisSecGroup entity;

	@Autowired
	private BisSecGroupManager secGroupManager;

	// 标签类型
	private String tmpTagTypeCd;


	//是否固化,标签组
//	private Map<String, String> mapFixedFlg = new HashMap<String, String>();
//	private Map<String, String> mapUaapTagType  = new HashMap<String, String>();

	@Override
	public String delete() throws Exception {
		secGroupManager.deleteBisSecGroup(getId());
		Struts2Utils.renderText("true");
		return null;
	}

	public void prepareDelete() throws Exception {

		if (StringUtils.isNotBlank(getId())) {
			entity = secGroupManager.getEntity(getId());
		} else
			throw new Exception("delete secGroup! id is not exsits!");
	}

	@Override
	public String deleteBatch() throws Exception {

		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		return "input";
	}

	@Override
	public void prepareInput() {

		if (StringUtils.isNotBlank(getId())) {
			entity = secGroupManager.getEntity(getId());
		} else {
			entity = new BisSecGroup();
		}
	}
	@Override
	public String list() throws Exception {

		page.setPageSize(1000);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		
		if(StringUtils.isBlank(tmpTagTypeCd)){
			Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_TAG_TYPE);
			if(map.keySet().size()>0){
				String t = map.keySet().iterator().next();
				if(StringUtils.isNotBlank(t) && !"0".equals(t)) {
					tmpTagTypeCd = t;
				}
			}
		}
		
		if(StringUtils.isNotBlank(tmpTagTypeCd)){
			filters.add(new PropertyFilter("EQS_tagTypeCd", tmpTagTypeCd));
		}
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("dispOrderNo");
			page.setOrder(Page.ASC);
		}
		
		page = secGroupManager.findPage(page, filters);

		return "list";
	}

	@Override
	public String save() throws Exception {
		secGroupManager.saveBisSecGroup(entity);
		Struts2Utils.renderText("success," + entity.getBisSecGroupId() + "," + entity.getGroupName());
		return null;
	}
	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = secGroupManager.getEntity(getId());
		} else {
			entity = new BisSecGroup();
		}
	}

	public BisSecGroup getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (entity == null) {
			entity = new BisSecGroup();
		}
	}

	public BisSecGroup getEntity() {
		return entity;
	}

	public void setEntity(BisSecGroup entity) {
		this.entity = entity;
	}
	
	//是否固化:1-是0-否
	public Map<String, String> getMapFixedFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COM_ENABLED_FLG);
	}

	//标签组
	public Map<String, String> getMapUaapTagType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAS_TAG_TYPE);
	}
	public String getTmpTagTypeCd() {
		return tmpTagTypeCd;
	}

	public void setTmpTagTypeCd(String tmpTagTypeCd) {
		this.tmpTagTypeCd = tmpTagTypeCd;
	}
}

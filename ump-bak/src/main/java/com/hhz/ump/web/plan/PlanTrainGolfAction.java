/**
 * 
 */
package com.hhz.ump.web.plan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.plan.PlanTrainGolfManager;
import com.hhz.ump.entity.plan.PlanTrainGolf;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.UserDisplayInfo;
import com.hhz.uums.entity.ws.WsPlasUser;

/**
 * @author wuzm 2010-3-31
 */
@Namespace("/plan")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "planTrainGolf!list.action", type = "redirect"),
	        @Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
			"is", "contentDisposition", "attachment;filename=${downFileName}.xls" })})
public class PlanTrainGolfAction extends CrudActionSupport<PlanTrainGolf> {
	private static final long serialVersionUID = -4351025260577645427L;

	private PlanTrainGolf entity;

	@Autowired
	private PlanTrainGolfManager planTrainGolfManager;
	
	private long trainPeriod;
	
	private String downFileName;
	
	private InputStream is;

	
	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = planTrainGolfManager.getEntity(getId());
		} else {
			entity = new PlanTrainGolf();
		}
	}
	public PlanTrainGolf getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String delete() throws Exception {
		planTrainGolfManager.deletePlanTrainGolf(getId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			planTrainGolfManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		if (getId() == null) {
		}
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		page.setPageSize(60);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("trainPeriod,updatedDate");
			page.setOrder(Page.DESC+","+Page.DESC);
		}
		page = planTrainGolfManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = planTrainGolfManager.getEntity(getId());
		} else {
			entity = new PlanTrainGolf();
		}
	}
	
	@Override
	public String save() throws Exception {
		String planTrainGolfId = planTrainGolfManager.getSameRecordId(trainPeriod, entity.getQuestionNo(), SpringSecurityUtils.getCurrentUiid());
		if(!"".equalsIgnoreCase(planTrainGolfId)){
			entity.setPlanTrainGolfId(planTrainGolfId);
		}
		planTrainGolfManager.savePlanTrainGolf(entity);
		Struts2Utils.renderText("success");
		return null;
	}
	
	/**
	 *根据关键字搜索人员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUsersByFilter() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String maxNum = Struts2Utils.getParameter("maxNum");
		if (StringUtils.isNotEmpty(value)) {
			WsPlasUser wsUaapUser = new WsPlasUser();
			wsUaapUser.setUiid(value);
			wsUaapUser.setUserName(value);
			List<WsPlasUser> result = Util.getPlasService().getUserListByFilter(wsUaapUser);
			List<UserDisplayInfo> list = transf2UserInfo(result);
			if (StringUtils.isNotEmpty(maxNum)) {
				int num = Integer.valueOf(maxNum);
				List<UserDisplayInfo> newList;
				if (list.size() > num) {
					newList = list.subList(0, num);
				} else {
					newList = list.subList(0, list.size());
				}
				Struts2Utils.renderJson(newList);
			} else {
				Struts2Utils.renderJson(list);
			}
		}
		return null;
	}
	
	private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception {
		List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
		if (users == null)
			return list;
		
		UserDisplayInfo info = null;
		for (WsPlasUser user : users) {
			info = new UserDisplayInfo();
			info.setCenterOrgCd(PlasCache.getCenterOrgCdByUserId(user.getPlasUserId()));
			// info.setCenterOrgName(user.getCenterOrgName());
			info.setOrgBizCd(user.getOrgBizCd());
			info.setOrgCd(user.getOrgCd());
			// info.setOrgName(user.getOrgName());// 这里取的是逻辑机构名称
			String phyOrgName = CodeNameUtil.getDeptNameByCd(user.getOrgCd());// UaapCache.getOrgNameByCd(user.getUaapPhysicalOrgCd());
			info.setOrgName(phyOrgName);
			info.setSexCd(user.getSexCd());
			info.setUiid(user.getUiid());
			info.setUserBizCd(user.getUserBizCd());
			info.setUserCd(user.getUserCd());
			info.setUserName(user.getUserName());
			list.add(info);
		}
		return list;
	}
	
	
	/**
	 * 操作日志Excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		List<PlanTrainGolf> result = planTrainGolfManager.find(filters);
		for (PlanTrainGolf planTrainGolf : result) {
			planTrainGolfManager.getDao().evict(planTrainGolf);
			planTrainGolf.setCreator(CodeNameUtil.getUserNameByCd(planTrainGolf.getCreator()));
		}
		Map beans = new HashMap();
		beans.put("result", result);

		XLSTransformer transformer = new XLSTransformer();
		InputStream isTemplate = PlanTrainGolfAction.class.getClassLoader().getResourceAsStream(
				"jxlsTemplates/golfResult.xls");
		HSSFWorkbook hssfWorkbook = transformer.transformXLS(isTemplate, beans);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		hssfWorkbook.write(bos);

		byte[] data = bos.toByteArray();
		is = new ByteArrayInputStream(data);

		String fileName = "搜索结果" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}
	
	public long getTrainPeriod() {
		return trainPeriod;
	}
	public void setTrainPeriod(long trainPeriod) {
		this.trainPeriod = trainPeriod;
	}
	public PlanTrainGolf getEntity() {
		return entity;
	}
	public void setEntity(PlanTrainGolf entity) {
		this.entity = entity;
	}
	public PlanTrainGolfManager getPlanTrainGolfManager() {
		return planTrainGolfManager;
	}
	public void setPlanTrainGolfManager(PlanTrainGolfManager planTrainGolfManager) {
		this.planTrainGolfManager = planTrainGolfManager;
	}
	public String getDownFileName() {
		return downFileName;
	}
	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
	public InputStream getIs() {
		return is;
	}
	public void setIs(InputStream is) {
		this.is = is;
	}
	
}

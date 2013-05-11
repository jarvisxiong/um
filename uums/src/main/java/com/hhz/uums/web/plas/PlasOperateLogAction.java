package com.hhz.uums.web.plas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.entity.plas.PlasOperateLog;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.web.CrudActionSupport;

@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "plas-operate-log.action", type = "redirect"),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
				"is", "contentDisposition", "attachment;filename=${downFileName}.xls" }) })
public class PlasOperateLogAction extends CrudActionSupport<PlasOperateLog> {

	private static final long serialVersionUID = 1142806465722614617L;

	@Autowired
	private PlasOperateLogManager plasOperateLogManager;

	@Autowired
	private PlasOrgManager plasOrgManager;
	
	private InputStream is;
	private String downFileName;
	
	private PlasOperateLog entity;
	@Override
	public PlasOperateLog getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(getId())){
			entity = plasOperateLogManager.getEntity(getId());
		}
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
	public String save() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");

		String moduleCd = Struts2Utils.getParameter("moduleCd");
		String sumarry = Struts2Utils.getParameter("sumary");
		String operateDetailDesc = Struts2Utils.getParameter("operateDetailDesc");
		String uiid = Struts2Utils.getParameter("uiid");
		String userName = Struts2Utils.getParameter("userName");
		String gCreatedDate = Struts2Utils.getParameter("filter_GED_createdDate");
		String lCreatedDate = Struts2Utils.getParameter("filter_LED_createdDate");
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("moduleCd", moduleCd);
		values.put("sumarry", sumarry);
		values.put("operateDetailDesc", "%"+operateDetailDesc+"%");
		values.put("uiid", uiid);
		values.put("userName", userName);
		
		if(StringUtils.isNotBlank(gCreatedDate)){
			values.put("gCreatedDate", DateParser.parse(gCreatedDate, "yyyy-MM-dd") );
		}
		if(StringUtils.isNotBlank(lCreatedDate)){
			values.put("lCreatedDate", DateParser.parse(lCreatedDate, "yyyy-MM-dd") );
		}
		
		StringBuffer hqlBuf = new StringBuffer()
			.append(" from PlasOperateLog t where 1=1 ");

		if(StringUtils.isNotBlank(moduleCd)){
			hqlBuf.append(" and moduleCd = :moduleCd");
		}
		if(StringUtils.isNotBlank(sumarry)){
			hqlBuf.append(" and sumarry = :sumarry ");
		}
		if(StringUtils.isNotBlank(operateDetailDesc)){
			hqlBuf.append(" and operateDetailDesc like :operateDetailDesc");
		}
		if(StringUtils.isNotBlank(uiid)){
			hqlBuf.append(" and (uiid = :uiid) ");
		}
		
		if(RoleUtil.isAdmin()||RoleUtil.isAdminSupser()){
			//管理员可任意查询
		}
		else{
			List<String> tmpOrgIdList = plasOrgManager.getMgrOrgIdList(DictContants.TREE_DIME_LOGICAL,SpringSecurityUtils.getCurUiid());
			String[] idList = new String[tmpOrgIdList.size()];
			for(int i=0; i<tmpOrgIdList.size(); i++){
				idList[i] = tmpOrgIdList.get(i);
			}
			values.put("tmpOrgIdList", idList);
			hqlBuf.append(" and exists(select 1 from PlasUser tt where tt.uiid = t.uiid and tt.plasOrg.plasOrgId in (:tmpOrgIdList)) ");
		}
		
		
		if(StringUtils.isNotBlank(gCreatedDate)){
			hqlBuf.append(" and createdDate >= :gCreatedDate ");
		}
		if(StringUtils.isNotBlank(lCreatedDate)){
			hqlBuf.append(" and createdDate <= :lCreatedDate ");
		}
		

		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		} 
		// 设置默认排序方式
		if (StringUtils.isBlank(sortField)) {
//			page.setOrderBy("createdDate");
//			page.setOrder(Page.DESC);
			hqlBuf.append(" order by createdDate desc ");//注意：这里根据排序显示
		} else {
//			page.setOrderBy(sortField);
//			page.setOrder(order);
			hqlBuf.append(" order by "+sortField+" "+ order);//注意：这里根据排序显示
		}
		
		page =  plasOperateLogManager.findPage(page, hqlBuf.toString(), values);
		
		JsonUtil.renderJson(page, new String[] { "plasOperateLog" });
		return null;
	}
	public String listMy() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("creator", SpringSecurityUtils.getCurUiid());//当前用户
		
		StringBuffer hqlBuf = new StringBuffer()
			.append(" from PlasOperateLog t where (creator = :creator) ");
		 

		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		} 
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
//			page.setOrderBy("createdDate");
//			page.setOrder(Page.DESC);
			hqlBuf.append(" order by createdDate desc ");//注意：这里根据排序显示
		} else {
//			page.setOrderBy(sortField);
//			page.setOrder(order);
			hqlBuf.append(" order by "+sortField+" "+ order);//注意：这里根据排序显示
		}
		
		page =  plasOperateLogManager.findPage(page, hqlBuf.toString(), values);
		
		JsonUtil.renderJson(page, new String[] { "plasOperateLog" });
		return null;
	}

	/**
	 * 操作日志Excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		List<PlasOperateLog> result = plasOperateLogManager.find(filters);
		for (PlasOperateLog plasOperateLog : result) {
			plasOperateLogManager.getDao().evict(plasOperateLog);
		}

		Map beans = new HashMap();
		beans.put("result", result);

		XLSTransformer transformer = new XLSTransformer();
		InputStream isTemplate = PlasOperateLogAction.class.getClassLoader().getResourceAsStream(
				"jxlsTemplates/operLogResult.xls");
		HSSFWorkbook hssfWorkbook = transformer.transformXLS(isTemplate, beans);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		hssfWorkbook.write(bos);

		byte[] data = bos.toByteArray();
		is = new ByteArrayInputStream(data);

		String fileName = "查询结果" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
	
	/**
	 * 查看日志明细
	 * @param id
	 * 
	 * @return
	 * @throws Exception 
	 */
	public void prepareDetail() throws Exception{
		prepareModel();
	}
	public String detail(){
		return "detail";
	}

	public PlasOperateLog getEntity() {
		return entity;
	}

	public void setEntity(PlasOperateLog entity) {
		this.entity = entity;
	}
}

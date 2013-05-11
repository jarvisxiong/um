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
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.uums.dao.plas.PlasOperBatchManager;
import com.hhz.uums.entity.plas.PlasOperBatch;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.web.CrudActionSupport;

@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "plas-oper-batch.action", type = "redirect"),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
				"is", "contentDisposition", "attachment;filename=${downFileName}.xls" }) })
public class PlasOperBatchAction extends CrudActionSupport<PlasOperBatch> {

	private static final long serialVersionUID = 1142806465722614617L;

	@Autowired
	private PlasOperBatchManager plasOperBatchManager;
	private InputStream is;
	private String downFileName;
	
	private PlasOperBatch entity;
	
	//查询条件
	private String firstFlg;
	private String applyStatusCd;
	private String applyDate;
	
	private String isExactFlag;
	private String applyUserName;
	private String applyUserCd;
	
	
	private String filter_EQS_briefTitle;
	private Date filter_EQS_applyDate;
	private Date filter_EQS_replyDate; 
	
	
	private String curUiid = SpringSecurityUtils.getCurUiid();
	
	private String todayDate;
	
	@Override
	public PlasOperBatch getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(getId())){
			entity = plasOperBatchManager.getEntity(getId());
		}else{
			entity = new PlasOperBatch();
		}
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	}
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public void prepareSave()throws Exception {
		prepareModel();
	}
	@Override
	public String save() throws Exception {

		if(StringUtils.isBlank(getId())){
			entity.setApplyDate(new Date());
			entity.setApplyUserCd(SpringSecurityUtils.getCurUiid());
			entity.setApplyUserName(SpringSecurityUtils.getCurUserName());
		}
		
		plasOperBatchManager.savePlasOperBatch(entity);
		Struts2Utils.renderText("success,"+ entity.getPlasOperBatchId());
		return null;
	}

	public void prepareDelete() throws Exception{
		prepareModel();
	}
	@Override
	public String delete() throws Exception {
		entity = plasOperBatchManager.getEntity(getId());
		if(entity == null){
			Struts2Utils.renderText("未找到记录!");
			return null;
		}
		if(!RoleUtil.isAdmin()){
			if(!PlasOperBatchManager.APPLY_STATUS_CD_APPLY.equals(entity.getApplyStatusCd())){
				Struts2Utils.renderText("申请已处理,不能删除!");
				return null;
			}
			if(!entity.getApplyUserCd().equals(curUiid)){
				Struts2Utils.renderText("非本人申请的记录,不能删除!");
				return null;
			}
		}
 
		plasOperBatchManager.deletePlasOperBatch(getId());
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public String list() throws Exception {
		
		todayDate = DateParser.formatDate(new Date(), "yyyy-MM-dd");
		
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");

		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());

		
		if(RoleUtil.isAdmin()){
			//若第一次
			if(StringUtils.isBlank(firstFlg)){
				filters.add(new PropertyFilter("EQS_applyStatusCd","1"));//待审批
			}else{
				if(StringUtils.isNotBlank(applyStatusCd)){
					filters.add(new PropertyFilter("EQS_applyStatusCd",applyStatusCd));
				}
			}
		}else{
			//若第一次
			if(StringUtils.isBlank(firstFlg)){
				filters.add(new PropertyFilter("INA_applyStatusCd",new String[]{"1","3"}));//待审批,不予处理
			}else{
				if(StringUtils.isNotBlank(applyStatusCd)){
					filters.add(new PropertyFilter("EQS_applyStatusCd",applyStatusCd));
				}
			}
		}
		
		//角色限制记录
		if(RoleUtil.isAdmin()){
			if("on".equals(isExactFlag)){
				if(StringUtils.isNotBlank(applyUserCd)){
					filters.add(new PropertyFilter("EQS_applyUserCd",applyUserCd));
				}
			}else{
				if(StringUtils.isNotBlank(applyUserName)){
					filters.add(new PropertyFilter("LIKES_applyUserName",applyUserName));
				}
			}
		}else{
			filters.add(new PropertyFilter("EQS_applyUserCd",SpringSecurityUtils.getCurUiid()));
		}

		
		if(StringUtils.isNotBlank(applyDate)){
			filters.add(new PropertyFilter("GTD_applyDate",DateParser.parse(applyDate, "yyyy-MM-dd")));
			filters.add(new PropertyFilter("LTD_applyDate",DateParser.addDate(DateParser.parse(applyDate, "yyyy-MM-dd"),1)));
		}
		
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}else{
			page.setPageSize(30);
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField + ",createdDate");
			page.setOrder(order + "," + Page.DESC);
		}

		page = plasOperBatchManager.findPage(page, filters);
		JsonUtil.renderJson(page, new String[] {"applyDesc", "replyDesc"});
		
		return null;
	}

	/**
	 * Excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		List<PlasOperBatch> result = plasOperBatchManager.find(filters);
		for (PlasOperBatch plasOperateLog : result) {
			plasOperBatchManager.getDao().evict(plasOperateLog);
		}

		Map beans = new HashMap();
		beans.put("result", result);

		XLSTransformer transformer = new XLSTransformer();
		InputStream isTemplate = PlasOperBatchAction.class.getClassLoader().getResourceAsStream(
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

	public PlasOperBatch getEntity() {
		return entity;
	}

	public void setEntity(PlasOperBatch entity) {
		this.entity = entity;
	}

	public PlasOperBatchManager getPlasOperBatchManager() {
		return plasOperBatchManager;
	}

	public void setPlasOperBatchManager(PlasOperBatchManager plasOperBatchManager) {
		this.plasOperBatchManager = plasOperBatchManager;
	}
 
	public Date getFilter_EQS_applyDate() {
		return filter_EQS_applyDate;
	}

	public void setFilter_EQS_applyDate(Date filter_EQS_applyDate) {
		this.filter_EQS_applyDate = filter_EQS_applyDate;
	}

	public String getFilter_EQS_briefTitle() {
		return filter_EQS_briefTitle;
	}

	public void setFilter_EQS_briefTitle(String filter_EQS_briefTitle) {
		this.filter_EQS_briefTitle = filter_EQS_briefTitle;
	}

	public Date getFilter_EQS_replyDate() {
		return filter_EQS_replyDate;
	}

	public void setFilter_EQS_replyDate(Date filter_EQS_replyDate) {
		this.filter_EQS_replyDate = filter_EQS_replyDate;
	}
	
	/**
	 * 标记已处理
	 * @param id 
	 * @param replyDesc
	 * @return
	 * @throws Exception 
	 */
	public void prepareDone() throws Exception{
		prepareModel();
	}
	public String done(){
		entity.setReplyDate(new Date());
		entity.setReplyUserCd(SpringSecurityUtils.getCurUiid());
		entity.setReplyUserName(SpringSecurityUtils.getCurUserName());
		plasOperBatchManager.savePlasOperBatch(entity);
		Struts2Utils.renderText("success,"+ entity.getPlasOperBatchId());
		return null;
	}
	
	/**
	 * 标记暂不处理
	 * @param id 
	 * @param replyDesc
	 * @return
	 * @throws Exception 
	 */
	public void prepareSuspend() throws Exception{
		prepareModel();
	}
	public String suspend(){

		entity.setReplyDate(new Date());
		entity.setReplyUserCd(SpringSecurityUtils.getCurUiid());
		entity.setReplyUserName(SpringSecurityUtils.getCurUserName());
		plasOperBatchManager.savePlasOperBatch(entity);
		Struts2Utils.renderText("success,"+ entity.getPlasOperBatchId());
		return null;
	}

	public String getIsExactFlag() {
		return isExactFlag;
	}

	public void setIsExactFlag(String isExactFlag) {
		this.isExactFlag = isExactFlag;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApplyUserCd() {
		return applyUserCd;
	}

	public void setApplyUserCd(String applyUserCd) {
		this.applyUserCd = applyUserCd;
	}

	public String getFirstFlg() {
		return firstFlg;
	}

	public void setFirstFlg(String firstFlg) {
		this.firstFlg = firstFlg;
	}

	public String getApplyStatusCd() {
		return applyStatusCd;
	}

	public void setApplyStatusCd(String applyStatusCd) {
		this.applyStatusCd = applyStatusCd;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String getCurUiid() {
		return curUiid;
	}

	public void setCurUiid(String curUiid) {
		this.curUiid = curUiid;
	}
 
}

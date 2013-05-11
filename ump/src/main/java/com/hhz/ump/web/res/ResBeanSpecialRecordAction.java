/**
 * 
 */
package com.hhz.ump.web.res;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.dao.res.ResBeanSpecialRecordManager;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResBeanSpecialRecord;

/**
 * <p>特别费台账
 * </p>
 * 
 * @author huangjian
 * @create 2012-7-2
 */
@Results({@Result(name=CrudActionSupport.RELOAD, type="redirect",location="res-bean-special-record!list.action")})
public class ResBeanSpecialRecordAction extends CrudActionSupport<ResBeanSpecialRecord> {
	private static final long serialVersionUID = 1973721979313520482L;
	@Autowired
	private ResApproveInfoManager resApproveInfoManager;
	@Autowired
	private ResBeanSpecialRecordManager resBeanSpecialRecordManager;
	private ResBeanSpecialRecord entity;
	
	private String srcResDisplayNo;
	private String payResDisplayNo;
	

	@Override
	public ResBeanSpecialRecord getModel() {
		if(entity == null) {
			entity = new ResBeanSpecialRecord();
		}
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list(){

		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		page.setPageSize(20);
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
		filters.add(new PropertyFilter("EQS_enableFlg", "1"));//1-可用
		
		page = resBeanSpecialRecordManager.findPage(page, filters);
		
		return "list";
	}
	
	/**
	 * 台账主界面
	 * @return
	 */
	public String main(){
		list();
		return "main";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())){
			//resBeanSpecialRecordManager.deleteResBeanSpecialRecord(getId());
			ResBeanSpecialRecord t = resBeanSpecialRecordManager.getEntity(getId());
			if(t!= null){
				t.setEnableFlg("0");//不可用
				resBeanSpecialRecordManager.saveResBeanSpecialRecord(t);
				Struts2Utils.renderText("success");
			}
		}else{
			Struts2Utils.renderText("删除不成功！");
		}
		return null;
	}
	/**
	 * 费用台账  - 确认付款
	 * @return
	 * @throws Exception
	 */
	public String confirm()throws Exception {
		if (StringUtils.isNotBlank(getId())){
			resBeanSpecialRecordManager.confirm(getId());
		}
		Struts2Utils.renderText("success");
		return null;
	}
	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}
	
	/**
	 * 是否已导入
	 * @return
	 */
	public String isConfirmFlg(){

		//支付网批ID
		String payResId = Struts2Utils.getParameter("payResId");
		ResApproveInfo srcRes = resApproveInfoManager.getResApproveInfoById(payResId);
		if(srcRes == null){
			Struts2Utils.renderText("找不到网批,请联系管理员!");
			return null;
		}

		ResBeanSpecialRecord record = resBeanSpecialRecordManager.getEntityByPayResId(payResId);
		if(record == null){
			Struts2Utils.renderText("找不到费用,请联系管理员!");
			return null;
		}
		
		String enableFlg = record.getEnableFlg();
		if("0".equals(enableFlg) || StringUtils.isBlank(enableFlg)){
			Struts2Utils.renderText("对应记录已删除!");
			return null;
		}

		String statusCd = record.getStatusCd();
		if("1".equals(statusCd)){
			Struts2Utils.renderText("0");
			return null;
		}
		else if("2".equals(statusCd)){
			Struts2Utils.renderText("1");
			return null;
		}
		else {
			Struts2Utils.renderText("0");
			return null;
		}
	}
	
	/**
	 * 网批 - 确认付款
	 * @param payResId 发起网批ID
	 * @return
	 */
	public String confirmResPay(){
		
		//支付网批ID
		String payResId = Struts2Utils.getParameter("payResId");
		
		if(StringUtils.isBlank(payResId)){
			Struts2Utils.renderText("参数不正确");
			return null;
		}

		ResApproveInfo payRes = resApproveInfoManager.getResApproveInfoById(payResId);
		if(payRes == null){
			Struts2Utils.renderText("找不到网批,请联系管理员!");
			return null;
		}
		
		ResBeanSpecialRecord record = resBeanSpecialRecordManager.getEntityByPayResId(payResId);
		if(record == null){
			Struts2Utils.renderText("找不到费用,请联系管理员!");
			return null;
		}
		
		String enableFlg = record.getEnableFlg();
		if("0".equals(enableFlg) || StringUtils.isBlank(enableFlg)){
			Struts2Utils.renderText("对应记录已删除!");
			return null;
		}
		
		String statusCd = record.getStatusCd();
		//若是0-	立项未同意付款、
		if("0".equals(statusCd)){
			Struts2Utils.renderText("费用状态不对,请联系管理员!");
			return null;
		}
		//1-同意付款
		else if("1".equals(statusCd)){
			record.setStatusCd("2");//2-设置已确认付款.
			resBeanSpecialRecordManager.saveResBeanSpecialRecord(record);
			Struts2Utils.renderText("success");
			return null;
		}
		//2-已付款
		else if("2".equals(statusCd)){
			Struts2Utils.renderText("已确认过,请联系管理员!");
			return null;
		}
		else{
			Struts2Utils.renderText("状态不对,请联系管理员!");
		}
		return null;
	}

	public String getSrcResDisplayNo() {
		return srcResDisplayNo;
	}

	public void setSrcResDisplayNo(String srcResDisplayNo) {
		this.srcResDisplayNo = srcResDisplayNo;
	}

	public String getPayResDisplayNo() {
		return payResDisplayNo;
	}

	public void setPayResDisplayNo(String payResDisplayNo) {
		this.payResDisplayNo = payResDisplayNo;
	} 
}

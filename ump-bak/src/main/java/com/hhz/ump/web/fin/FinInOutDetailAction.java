package com.hhz.ump.web.fin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.fin.FinInOutDetailManager;
import com.hhz.ump.dao.fin.FinItemManager;
import com.hhz.ump.dao.fin.FinProjectAcctRelManager;
import com.hhz.ump.dao.fin.FinProjectManager;
import com.hhz.ump.entity.fin.FinInOutDetail;
import com.hhz.ump.entity.fin.FinProject;
import com.hhz.ump.entity.fin.FinProjectAcctRel;
import com.hhz.ump.util.FinDetailUtil;

@Namespace("/fin")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "fin-in-out-detail.action", type = "redirect", params = {
		"bankId", "${bankId}" }) })
public class FinInOutDetailAction extends CrudActionSupport<FinInOutDetail> {

	private static final long serialVersionUID = 8071052318603147332L;
	@Autowired
	private FinInOutDetailManager finInOutDetailManager;
	@Autowired
	private FinProjectAcctRelManager finProjectAcctRelManager;
	@Autowired
	private FinItemManager finItemManager;
	@Autowired
	private FinProjectManager finProjectManager;

	private FinInOutDetail entity;

	private String bankId;

	private BigDecimal balance;

	private BigDecimal oriInAmount;

	private BigDecimal oriOutAmount;

	private BigDecimal oriBalance;

	private String beginTime;

	private String endTime;

	private String haveNowDate;

	private String haveChecked;

	private List<FinDetailUtil> results;

	private int pageSize = 200;

	private Map<String, String> mapToItemNames = new HashMap<String, String>();


	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = finInOutDetailManager.getFinInOutDetail(getId());
			if (bankId != null && !"".equals(bankId)) {
				FinProjectAcctRel acctRel = finProjectAcctRelManager
						.getEntity(bankId);
				finInOutDetailManager.deleteFinInOutDetail(getId(), acctRel, bankId, entity);
			}
			addActionMessage(getText("common.success"));
			Struts2Utils.renderText("ok");
		}
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		if (bankId == null) {
			// bankId = (String)
			// Struts2Utils.getRequest().getAttribute("bankId");
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (beginTime == null || "".equals(beginTime)) {
			beginTime = format.format(DateOperator.getDateNow());
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = format.format(DateOperator.getDateNow());
		}

		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		filters.add(new PropertyFilter(
				"EQS_finProjectAcctRel.finProjectAcctRelId", bankId));
		java.sql.Date begin = new java.sql.Date(format.parse(beginTime)
				.getTime());
		java.sql.Date end = new java.sql.Date(format.parse(endTime).getTime());
		filters.add(new PropertyFilter("GED_createdDate", begin));
		filters.add(new PropertyFilter("LTD_createdDate", end));
		page.setOrderBy("createdDate,finInOutDetailId");
		page.setOrder(Page.ASC + "," + Page.ASC);
		page.setPageSize(pageSize);
		page = finInOutDetailManager.findPage(page, filters);

		String compDate = "";
		// 日收入、支出合计 and 余额
		BigDecimal inSum = new BigDecimal(0);
		BigDecimal outSum = new BigDecimal(0);
		BigDecimal dateBalance = new BigDecimal(0);
		// 本期合计
		BigDecimal totInSum = new BigDecimal(0);
		BigDecimal totOutSum = new BigDecimal(0);
		List<FinDetailUtil> inOuts = new ArrayList<FinDetailUtil>();
		for (FinInOutDetail finInOutDetail : page.getResult()) {
			String createDate = format.format(finInOutDetail.getCreatedDate());
			if (!compDate.equals(createDate)) {
				FinDetailUtil detailUtil = new FinDetailUtil();
				if (compDate != "") {
					// 本日合计
					detailUtil.setColspanNum(3);
					detailUtil.setSummaryDesc("本日合计");
					detailUtil.setInAmount(inSum);
					detailUtil.setOutAmount(outSum);
					detailUtil.setBalance(dateBalance);
					detailUtil.setCreatedDate(format.parse(compDate));
					inOuts.add(detailUtil);
					inSum = new BigDecimal(0);
					outSum = new BigDecimal(0);
					dateBalance = new BigDecimal(0);
				}
				// 上期结转
				detailUtil = new FinDetailUtil();
				detailUtil.setColspanNum(5);
				detailUtil.setSummaryDesc("上期结转");
				detailUtil.setCreatedDate(finInOutDetail.getCreatedDate());
				// 上期结转的余额：下一条记录的总数
				BigDecimal bal = ((finInOutDetail.getBalance()
						.add(finInOutDetail.getOutAmount()))
						.subtract(finInOutDetail.getInAmount()));
				detailUtil.setBalance(bal);
				inOuts.add(detailUtil);
				compDate = createDate;

			}
			FinDetailUtil detailUtil = new FinDetailUtil();
			detailUtil
					.setFinInOutDetailId(finInOutDetail.getFinInOutDetailId());
			detailUtil.setInAmount(finInOutDetail.getInAmount());
			detailUtil.setOutAmount(finInOutDetail.getOutAmount());
			detailUtil.setSummaryDesc(finInOutDetail.getSummaryDesc());
			detailUtil.setCreatedDate(finInOutDetail.getCreatedDate());
			detailUtil.setFinItemCd(finInOutDetail.getFinItemCd());
			detailUtil.setBalance(finInOutDetail.getBalance());
			detailUtil.setColspanNum(1);
			inOuts.add(detailUtil);
			inSum = inSum.add(finInOutDetail.getInAmount());
			totInSum = totInSum.add(finInOutDetail.getInAmount());
			outSum = outSum.add(finInOutDetail.getOutAmount());
			totOutSum = totOutSum.add(finInOutDetail.getOutAmount());
			dateBalance = finInOutDetail.getBalance();
		}
		if (page.getResult() != null && page.getResult().size() > 0) {
			// 最后一次本日合计
			FinInOutDetail detail = page.getResult().get(
					page.getResult().size() - 1);
			FinDetailUtil detailUtil = new FinDetailUtil();
			detailUtil.setColspanNum(3);
			detailUtil.setSummaryDesc("本日合计");
			detailUtil.setInAmount(inSum);
			detailUtil.setOutAmount(outSum);
			detailUtil.setBalance(dateBalance);
			detailUtil.setCreatedDate(detail.getCreatedDate());
			inOuts.add(detailUtil);
			detailUtil = new FinDetailUtil();
			detailUtil.setColspanNum(3);
			detailUtil.setSummaryDesc("本期合计");
			detailUtil.setInAmount(totInSum);
			detailUtil.setOutAmount(totOutSum);
			detailUtil.setBalance(dateBalance);
			inOuts.add(detailUtil);
		}
		// 选中按钮
		haveChecked = "1";
		haveNowDate = format.format(DateOperator.getDateNow());
		results = inOuts;
		List<FinProjectAcctRel> result = finInOutDetailManager
				.getBalance(bankId);
		BigDecimal balance = new BigDecimal(0);
		if (result != null && result.size() > 0) {
			balance = result.get(0).getBalance();
			Struts2Utils.getRequest().setAttribute("accNo",
					result.get(0).getAccountNo());
			Struts2Utils.getRequest().setAttribute("curCode",
					result.get(0).getCurrencyCd());
			Struts2Utils.getRequest().setAttribute("bankName",
					result.get(0).getBankName());
			String projectCd = result.get(0).getProjectCd();
			List<FinProject> project = finProjectManager.getProject(null,
 projectCd, false,null);
			if (project != null) {
				String statusCd = project.get(0).getStatusCd();
				if (statusCd != null && statusCd.equals(haveNowDate + "-2")) {
					haveChecked = "2";
				}
			}

		}
		Struts2Utils.getRequest().setAttribute("balance", balance);
		Struts2Utils.getRequest().setAttribute("bankId", bankId);
		Date nowDate = DateOperator.getDateNow();
		Struts2Utils.getRequest().setAttribute("nowDate",
				format.format(nowDate));
		buildToItemNames(page.getResult());
		return SUCCESS;
	}

	public void buildToItemNames(List<FinInOutDetail> finInOutDetailList) {
		for (FinInOutDetail finInOutDetails : finInOutDetailList) {
			String finItemCd = finInOutDetails.getFinItemCd();
			String itemName = finItemManager.getItemName(finInOutDetails
					.getFinItemCd());
			if (!"".equals(itemName)) {
				mapToItemNames.put(finItemCd, itemName);
			}
		}
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = finInOutDetailManager.getFinInOutDetail(getId());
		} else {
			entity = new FinInOutDetail();
		}
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = finInOutDetailManager.getFinInOutDetail(getId());
			oriInAmount = entity.getInAmount();
			oriOutAmount = entity.getOutAmount();
			oriBalance = entity.getBalance();
		} else {
			entity = new FinInOutDetail();
		}
	}
	@Override
	public String save() throws Exception {
		if (StringUtils.isNotEmpty(bankId)) {
			finInOutDetailManager.saveFinInOutDetail(entity, bankId, false, entity.getCreatedDate(),oriInAmount,oriOutAmount,oriBalance);
		}
		return RELOAD;
	}

	public String updateBalance() throws Exception {
		if (StringUtils.isNotEmpty(bankId)) {
			finInOutDetailManager.updateBalance(beginTime, bankId);
		}
		return RELOAD;
	}

	public FinInOutDetail getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public Map<String, String> getMapToItemNames() {
		return mapToItemNames;
	}

	public void setMapToItemNames(Map<String, String> mapToItemNames) {
		this.mapToItemNames = mapToItemNames;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getOriInAmount() {
		return oriInAmount;
	}

	public void setOriInAmount(BigDecimal oriInAmount) {
		this.oriInAmount = oriInAmount;
	}

	public BigDecimal getOriOutAmount() {
		return oriOutAmount;
	}

	public void setOriOutAmount(BigDecimal oriOutAmount) {
		this.oriOutAmount = oriOutAmount;
	}

	public BigDecimal getOriBalance() {
		return oriBalance;
	}

	public void setOriBalance(BigDecimal oriBalance) {
		this.oriBalance = oriBalance;
	}

	public List<FinDetailUtil> getResults() {
		return results;
	}

	public void setResults(List<FinDetailUtil> results) {
		this.results = results;
	}

	public String getHaveNowDate() {
		return haveNowDate;
	}

	public void setHaveNowDate(String haveNowDate) {
		this.haveNowDate = haveNowDate;
	}

	public String getHaveChecked() {
		return haveChecked;
	}

	public void setHaveChecked(String haveChecked) {
		this.haveChecked = haveChecked;
	}

}

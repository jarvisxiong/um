package com.hhz.ump.web.fin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.fin.FinInOutDetailManager;
import com.hhz.ump.dao.fin.FinProjectAcctRelManager;
import com.hhz.ump.dao.fin.FinProjectManager;
import com.hhz.ump.entity.fin.FinInOutDetail;
import com.hhz.ump.entity.fin.FinProject;
import com.hhz.ump.entity.fin.FinProjectAcctRel;
import com.hhz.ump.util.FinDetailUtil;
import com.hhz.ump.util.FinProjectAccUtil;
import com.hhz.ump.util.IExcelExporter;
import com.hhz.ump.util.JXLExcelUtil;

@Namespace("/fin")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "fin-project-acct-rel.action", type = "redirect", params = {
				"projectCd", "${projectCd}" }),
		@Result(name = "Exported", location = "fin-project-acct-rel!doPeriExcel.action", type = "stream", params = {
				"contentType", "application/vnd.ms-excel",
				"contentDisposition",
				"attachment;filename=${excelFileName}.xls", "inputName",
				"excelFile" })})
public class FinProjectAcctRelAction extends
		CrudActionSupport<FinProjectAcctRel> implements IExcelExporter {

	private static final long serialVersionUID = 8071052318603147332L;
	@Autowired
	private FinProjectAcctRelManager finProjectAcctRelManager;
	@Autowired
	private FinProjectManager finProjectManager;
	@Autowired
	private FinInOutDetailManager finInOutDetailManager;

	private FinProjectAcctRel entity;

	private String projectCd;

	private String projectName;

	private String beginTime;

	private String endTime;

	private BigDecimal amount;

	private String finished;

	private String haveFinished;

	private InputStream outStream;
	private String downFileName;
	// 导出Excel需要的参数
	private InputStream excelFile;
	private String excelFileName;

	private List<FinProjectAccUtil> results;
	private List<FinProjectAccUtil> accList = new ArrayList<FinProjectAccUtil>();

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		// 搜索该银行是否有明细记录，如果有责不能删除
		List<FinInOutDetail> details = finInOutDetailManager.queryDetailByDate(
				null, getId());
		if (details != null && details.size() > 0) {
			Struts2Utils.renderText("no");
		} else {
			finProjectAcctRelManager.deleteFinProjectAcctRel(getId());
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
		if (StringUtils.isNotBlank(getId())) {
			entity = finProjectAcctRelManager.getFinProjectAcctRel(getId());
			projectCd = entity.getProjectCd();
		}
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		// 得到该用户所搜索的项目公司的ID
		// 通过项目公司ID查找该项目公司下的银行用户
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (beginTime == null || "".equals(beginTime)) {
			beginTime = format.format(DateOperator.getDateNow());
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = format.format(DateOperator.getDateNow());
		}
		results = finProjectAcctRelManager
.getACCTByConditions(beginTime
				+ " 00:00:00", endTime + " 23:59:59", projectCd, null, null,
				false);
		String name1 = "可动用银行存款及现金小计";
		String name2 = "不可动用银行存款及现金小计";
		for (FinProjectAccUtil accUtil : results) {
			String currencyCd = accUtil.getCurrencyCd();
			String enableFlg = accUtil.getEnableFlg();
			boolean isAdd = true;
			if (accList != null && accList.size() > 0) {
			for (int i = 0; i < accList.size(); i++) {
				FinProjectAccUtil acc = accList.get(i);
				if (enableFlg.equals(acc.getEnableFlg())
						&& currencyCd.equals(acc.getCurrencyCd())) {
					if(accUtil.getBefAmount()!=null){
						acc.setBefAmount(accUtil.getBefAmount().add(
								acc.getBefAmount()==null?new BigDecimal(0):acc.getBefAmount()));
					}
					acc.setInAmount(accUtil.getInAmount()
							.add(acc.getInAmount()));
					acc.setOutAmount(accUtil.getOutAmount().add(
							acc.getOutAmount()));
					if(accUtil.getAftAmount()!=null) {
						acc.setAftAmount(accUtil.getAftAmount().add(
								acc.getAftAmount()==null?new BigDecimal(0):acc.getAftAmount()));
					}
					isAdd = false;
					break;
				}
			}
			}
			if (isAdd) {
				FinProjectAccUtil acc = new FinProjectAccUtil();
				if (accUtil.getEnableFlg().equals("1")) {
					acc.setBankName(name1);
				} else {
					acc.setBankName(name2);
				}
				acc.setEnableFlg(enableFlg);
				acc.setCurrencyCd(currencyCd);
				acc.setBefAmount(accUtil.getBefAmount());
				acc.setInAmount(accUtil.getInAmount());
				acc.setOutAmount(accUtil.getOutAmount());
				acc.setAftAmount(accUtil.getAftAmount());
				accList.add(acc);
			}
		}
		List<FinProject> projects = finProjectManager.getProject(null,
 projectCd, false,null);
		if (projects != null && projects.size() > 0) {
			FinProject project = projects.get(0);
			projectName = project.getProjectName();
			String nowDate = format.format(DateOperator.getDateNow());
			if (project.getStatusCd() != null) {
				if (project.getStatusCd().indexOf(nowDate) >= 0) {
					if (project.getStatusCd().equals(nowDate + "-1")) {
						haveFinished = "1";
					} else {
						haveFinished = "2";
					}
				} else {
					haveFinished = "0";
				}
			} else {
				haveFinished = "0";
			}

		}
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = finProjectAcctRelManager.getFinProjectAcctRel(getId());
			amount = entity.getTotalAmount();
		} else {
			entity = new FinProjectAcctRel();
		}
	}

	public void doProFinsh() {
		if (finished != null && !"".equals(finished) && projectCd != null
				&& !"".equals(projectCd)) {
			String statusCD = "-0";
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if ("1".equals(finished)) {
				statusCD = format.format(DateOperator.getDateNow()) + "-1";
			} else if ("2".equals(finished)) {
				statusCD = format.format(DateOperator.getDateNow()) + "-2";
			}
			List<FinProject> finProjects = finProjectManager.getProject("",
 projectCd, false,null);
			if (finProjects != null) {
				FinProject finProject = finProjects.get(0);
				finProject.setStatusCd(statusCD);
				finProjectManager.saveFinProject(finProject);
				Struts2Utils.renderText(finished);
			}
		}
	}
	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(projectCd)) {
			List<FinProject> finProject = finProjectManager.getProject("",
 projectCd, false,null);
			if (finProject != null && finProject.size() > 0) {
				entity.setFinProject(finProject.get(0));
			}
		}
		if (StringUtils.isBlank(getId())&&entity.getTotalAmount() != null) {
			BigDecimal totalAmount = entity.getTotalAmount();
			entity.setBalance(totalAmount);
			entity.setStatusCd("0");
		}
		if (entity.getAccountNo() == null) {
			entity.setAccountNo("");
		}
		if (entity.getTotalAmount() != null) {
			if (amount != null) {
				if (entity.getTotalAmount().compareTo(amount) != 0) {
					// 总金额有改变,暂不改变金额
					entity.setTotalAmount(amount);
				}
			}
		}
		finProjectAcctRelManager.saveFinProjectAcctRel(entity);
		return RELOAD;
	}

	public FinProjectAcctRel getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = finProjectAcctRelManager.getFinProjectAcctRel(getId());
			amount = entity.getTotalAmount();
			projectCd = entity.getProjectCd();
		} else {
			entity = new FinProjectAcctRel();
		}
	}
	public String doPeriExcel() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (beginTime == null || "".equals(beginTime)) {
			beginTime = format.format(DateOperator.getDateNow());
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = format.format(DateOperator.getDateNow());
		}
		excelFile = buildExportedExcelInputStream();
		List<FinProject> projects = finProjectManager.getProject(null,
			 projectCd, false,null);
		projectName ="";
		if(projects!=null&&projects.size()>0){
		    projectName =projects.get(0).getProjectName();
		}
		Date now = Calendar.getInstance().getTime();
		String d = DateOperator.formatDate(now, "yyyy-MM-dd");
		String fileName = projectName + d;
		excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "Exported";
	}

	public InputStream buildExportedExcelInputStream() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {

			try {
				populateData(wbook);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JXLExcelUtil.closeWorkBook(wbook);
		}

		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(data);

		return bis;
	}

	private void populateData(WritableWorkbook wbook) throws Exception {
		List<FinProject> projects = finProjectManager.getProject(null,
				projectCd, false,null);
		if (projects != null && projects.size() > 0) {
			projectName = projects.get(0).getProjectName();
		}
		WritableSheet wsheet0 = wbook.createSheet(projectName, 0);
		doSimProjExcel(wsheet0, beginTime, endTime, projectCd, projectName);
		WritableSheet wsheet1 = wbook.createSheet("日日收支明细", 1);
		WritableSheet wsheet2 = wbook.createSheet("银行日记账", 2);
		WritableSheet wsheet4 = wbook.createSheet("现金日记账", 3);
		doDetailExcel(wsheet1, wsheet2, wsheet4, beginTime, endTime, projectCd,
				projectName);
		WritableSheet wsheet5 = wbook.createSheet(projectName + "现金流", 4);
		finProjectAcctRelManager.doCashExcel(wsheet5, beginTime, endTime,
				projectCd);
	}


	/**
	 * 
	 * @param wsheet1
	 * @param beginTime
	 * @param endTime
	 * @param projectCd
	 * @param projectName
	 * @throws WriteException
	 */
	public void doDetailExcel(WritableSheet wsheet1,WritableSheet wsheet2,WritableSheet wsheet4,String beginTime,String endTime,String projectCd,String projectName) throws WriteException{
		HashMap font = finProjectAcctRelManager.fontMap();
    	WritableCellFormat wcfN_notCol_Bold =(WritableCellFormat)font.get("wcfN_notCol_Bold");
    	WritableCellFormat wcfN_Bold =(WritableCellFormat)font.get("wcfN_Bold");
		// WritableCellFormat wcfN_noBold
		// =(WritableCellFormat)font.get("wcfN_noBold");
    	WritableCellFormat wcfN_N_noBold =(WritableCellFormat)font.get("wcfN_N_noBold");
    	WritableCellFormat wcfN_Y_Bold =(WritableCellFormat)font.get("wcfN_Y_Bold");
    	WritableCellFormat wcfN_N_Y_noBold =(WritableCellFormat)font.get("wcfN_N_Y_noBold");
    	WritableCellFormat wcfN_noBold_center =(WritableCellFormat)font.get("wcfN_noBold_center");
		// 增加日日收支明细表表头
		wsheet1.addCell(new Label(0, 0, projectName + beginTime + " - "
				+ endTime + "日日收支明细表", wcfN_notCol_Bold));
		wsheet1.mergeCells(0, 0, 10, 0);
		wsheet1.setRowView(0, 500);
		wsheet1.addCell(new Label(0, 1, "项目", wcfN_Bold));
		wsheet1.mergeCells(0, 1, 0, 2);
		wsheet1.addCell(new Label(1, 1, "昨日余额", wcfN_Bold));
		wsheet1.mergeCells(1, 1, 1, 2);
		wsheet1.addCell(new Label(2, 1, "收入", wcfN_Bold));
		wsheet1.mergeCells(2, 1, 5, 1);
		wsheet1.addCell(new Label(6, 1, "支出", wcfN_Bold));
		wsheet1.mergeCells(6, 1, 9, 1);
		wsheet1.addCell(new Label(2, 2, "摘要", wcfN_Bold));
		wsheet1.addCell(new Label(3, 2, "现金流量项目", wcfN_Bold));
		wsheet1.addCell(new Label(4, 2, "收支类别", wcfN_Bold));
		wsheet1.addCell(new Label(5, 2, "金额", wcfN_Bold));
		wsheet1.addCell(new Label(6, 2, "摘要", wcfN_Bold));
		wsheet1.addCell(new Label(7, 2, "现金流量项目", wcfN_Bold));
		wsheet1.addCell(new Label(8, 2, "收支类别", wcfN_Bold));
		wsheet1.addCell(new Label(9, 2, "金额", wcfN_Bold));
		wsheet1.addCell(new Label(10, 1, "本日余额", wcfN_Bold));
		wsheet1.mergeCells(10, 1, 10, 2);
		wsheet1.setRowView(1, 500);
		int dayRowi = 3;// 日日收支明细表格行数;
		String spaceStr ="";
		int rowi = 0;// 记录wsheet2页面当前行数(每个银行累加）
		int cashRowi = 0;// 记录wsheet4页面当前行数(现金)
		int j = 3;// 日记账起头行数
		// 通过projectCd,搜索项目明细
		List<FinProjectAccUtil> accRel = finProjectAcctRelManager
				.getACCTByConditions(beginTime + " 00:00:00", endTime
						+ " 23:59:59", projectCd, null, null, true);
		for (FinProjectAccUtil accUtil : accRel) {
			String acctRelId = accUtil.getFinProjectAcctRelId();
			List<FinDetailUtil> details = finInOutDetailManager
					.queryDetailByPeri(beginTime + " 00:00:00", endTime
							+ " 23:59:59", acctRelId);
			if (details != null && details.size() > 0) {
				// 增加银行明细表头记录
				String name = "银行名称:" + accUtil.getBankName() + "    账号："
						+ accUtil.getAccountNo() + "    币种："
						+ accUtil.getCurrencyCd() + "    日期:" + beginTime
						+ " - " + endTime;
				if (accUtil.getAccountTypeCd().compareTo("1") == 0) {
					// 银行日记账
					wsheet2.addCell(new Label(0, rowi, name, wcfN_notCol_Bold));
					wsheet2.mergeCells(0, rowi, 5, 0);
					wsheet2.setRowView(rowi, 500);
					rowi++;
					wsheet2.addCell(new Label(0, rowi, "序号", wcfN_Bold));
					wsheet2.addCell(new Label(1, rowi, "摘要", wcfN_Bold));
					wsheet2.addCell(new Label(2, rowi, "现金流量项目", wcfN_Bold));
					wsheet2.addCell(new Label(3, rowi, "收入", wcfN_Bold));
					wsheet2.addCell(new Label(4, rowi, "支出", wcfN_Bold));
					wsheet2.addCell(new Label(5, rowi, "余额", wcfN_Bold));
					wsheet2.setRowView(rowi, 500);
					rowi++;
				} else {
					wsheet4.addCell(new Label(0, cashRowi, name,
							wcfN_notCol_Bold));
					wsheet4.mergeCells(0, cashRowi, 5, 0);
					wsheet4.setRowView(cashRowi, 500);
					cashRowi++;
					wsheet4.addCell(new Label(0, cashRowi, "序号", wcfN_Bold));
					wsheet4.addCell(new Label(1, cashRowi, "摘要", wcfN_Bold));
					wsheet4
							.addCell(new Label(2, cashRowi, "现金流量项目", wcfN_Bold));
					wsheet4.addCell(new Label(3, cashRowi, "收入", wcfN_Bold));
					wsheet4.addCell(new Label(4, cashRowi, "支出", wcfN_Bold));
					wsheet4.addCell(new Label(5, cashRowi, "余额", wcfN_Bold));
					wsheet4.setRowView(cashRowi, 500);
					cashRowi++;
				}
				int i = 1;
				for (FinDetailUtil detail : details) {
					if (detail.getInAmount().compareTo(new BigDecimal(0)) != 0) {
						// 收入
						String inName = "收入";
						wsheet1.addCell(new Label(2, dayRowi, detail
								.getSummaryDesc(), wcfN_noBold_center));
						wsheet1.addCell(new Label(3, dayRowi, detail
								.getFinItemName(), wcfN_noBold_center));
						wsheet1.addCell(new Label(4, dayRowi, inName,
								wcfN_noBold_center));
						wsheet1.addCell(new Number(5, dayRowi, detail
								.getInAmount().doubleValue(), wcfN_N_noBold));
						wsheet1.addCell(new Label(6, dayRowi, spaceStr,
								wcfN_noBold_center));
						wsheet1.addCell(new Label(7, dayRowi, spaceStr,
								wcfN_noBold_center));
						wsheet1.addCell(new Label(8, dayRowi, spaceStr,
								wcfN_noBold_center));
						wsheet1.addCell(new Label(9, dayRowi, spaceStr,
								wcfN_noBold_center));
					} else {
						String outName = "支出";
						wsheet1.addCell(new Label(2, dayRowi, spaceStr,
								wcfN_noBold_center));
						wsheet1.addCell(new Label(3, dayRowi, spaceStr,
								wcfN_noBold_center));
						wsheet1.addCell(new Label(4, dayRowi, spaceStr,
								wcfN_noBold_center));
						wsheet1.addCell(new Label(5, dayRowi, spaceStr,
								wcfN_noBold_center));
						wsheet1.addCell(new Label(6, dayRowi, detail
								.getSummaryDesc(), wcfN_noBold_center));
						wsheet1.addCell(new Label(7, dayRowi, detail
								.getFinItemName(), wcfN_noBold_center));
						wsheet1.addCell(new Label(8, dayRowi, outName,
								wcfN_noBold_center));
						wsheet1.addCell(new Number(9, dayRowi, detail
								.getOutAmount().doubleValue(), wcfN_N_noBold));
					}
					wsheet1.setRowView(dayRowi, 500);
					dayRowi++;
					if ("1".equals(accUtil.getAccountTypeCd())) {
						// 银行
						wsheet2.addCell(new Label(0, rowi, "" + i,
								wcfN_noBold_center));
						wsheet2.addCell(new Label(1, rowi, detail
								.getSummaryDesc(), wcfN_N_noBold));
						wsheet2.addCell(new Label(2, rowi, detail
								.getFinItemName(), wcfN_N_noBold));
						wsheet2.addCell(new Number(3, rowi, detail
								.getInAmount().doubleValue(), wcfN_N_noBold));
						wsheet2.addCell(new Number(4, rowi, detail
								.getOutAmount().doubleValue(), wcfN_N_noBold));
						wsheet2.addCell(new Number(5, rowi, detail.getBalance()
								.doubleValue(), wcfN_N_noBold));
						wsheet2.setRowView(rowi, 500);
						rowi++;
					} else {
						wsheet4.addCell(new Label(0, cashRowi, "" + i,
								wcfN_noBold_center));
						wsheet4.addCell(new Label(1, cashRowi, detail
								.getSummaryDesc(), wcfN_N_noBold));
						wsheet4.addCell(new Label(2, cashRowi, detail
								.getFinItemName(), wcfN_N_noBold));
						wsheet4.addCell(new Number(3, cashRowi, detail
								.getInAmount().doubleValue(), wcfN_N_noBold));
						wsheet4.addCell(new Number(4, cashRowi, detail
								.getOutAmount().doubleValue(), wcfN_N_noBold));
						wsheet4.addCell(new Number(5, cashRowi, detail
								.getBalance().doubleValue(), wcfN_N_noBold));
						wsheet4.setRowView(cashRowi, 500);
						cashRowi++;
					}
					i++;
				}
			}

			if (details != null && details.size() > 0) {
				wsheet1.addCell(new Label(0, j, accUtil.getBankName(),
						wcfN_noBold_center));
				wsheet1.addCell(new Number(1, j, accUtil.getBefAmount()
						.doubleValue(), wcfN_N_noBold));
				wsheet1.addCell(new Number(10, j, accUtil.getAftAmount()
						.doubleValue(), wcfN_N_noBold));
				wsheet1.mergeCells(0, j, 0, j + details.size() - 1);
				wsheet1.mergeCells(1, j, 1, j + details.size() - 1);
				wsheet1.mergeCells(10, j, 10, j + details.size() - 1);
				j = j + details.size();
				// 日日收支明细合计
				wsheet1.addCell(new Label(0, j, "合计", wcfN_Y_Bold));
				wsheet1.addCell(new Number(1, j, accUtil.getBefAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet1.addCell(new Label(2, j, "", wcfN_N_Y_noBold));
				wsheet1.addCell(new Label(3, j, "", wcfN_N_Y_noBold));
				wsheet1.addCell(new Label(4, j, "", wcfN_N_Y_noBold));
				wsheet1.addCell(new Number(5, j, accUtil.getInAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet1.addCell(new Label(6, j, "", wcfN_N_Y_noBold));
				wsheet1.addCell(new Label(7, j, "", wcfN_N_Y_noBold));
				wsheet1.addCell(new Label(8, j, "", wcfN_N_Y_noBold));
				wsheet1.addCell(new Number(9, j, accUtil.getOutAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet1.addCell(new Number(10, j, accUtil.getAftAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet1.setRowView(j, 500);
				dayRowi++;
				j++;
				// 求合计
				if ("1".equals(accUtil.getAccountTypeCd())) {
					wsheet2.addCell(new Label(0, rowi, "本日合计", wcfN_Y_Bold));
					wsheet2.addCell(new Number(3, rowi, accUtil.getInAmount()
							.doubleValue(), wcfN_N_Y_noBold));
					wsheet2.addCell(new Number(4, rowi, accUtil.getOutAmount()
							.doubleValue(), wcfN_N_Y_noBold));
					wsheet2.addCell(new Number(5, rowi, accUtil.getAftAmount()
							.doubleValue(), wcfN_N_Y_noBold));
					wsheet2.mergeCells(0, rowi, 2, rowi);
					wsheet2.setRowView(rowi, 500);
					rowi = rowi + 2;
				} else {
					wsheet4
							.addCell(new Label(0, cashRowi, "本日合计", wcfN_Y_Bold));
					wsheet4.addCell(new Number(3, cashRowi, accUtil
							.getInAmount().doubleValue(), wcfN_N_Y_noBold));
					wsheet4.addCell(new Number(4, cashRowi, accUtil
							.getOutAmount().doubleValue(), wcfN_N_Y_noBold));
					wsheet4.addCell(new Number(5, cashRowi, accUtil
							.getAftAmount().doubleValue(), wcfN_N_Y_noBold));
					wsheet4.mergeCells(0, cashRowi, 2, cashRowi);
					wsheet4.setRowView(cashRowi, 500);
					cashRowi = cashRowi + 2;
				}
			}
		}
		// 日日收支明细
		wsheet1.setColumnView(0, 20);
		wsheet1.setColumnView(1, 21);
		wsheet1.setColumnView(2, 17);
		wsheet1.setColumnView(3, 15);
		wsheet1.setColumnView(4, 6);
		wsheet1.setColumnView(5, 20);
		wsheet1.setColumnView(6, 17);
		wsheet1.setColumnView(7, 15);
		wsheet1.setColumnView(8, 6);
		wsheet1.setColumnView(9, 20);
		wsheet1.setColumnView(10, 21);
		// 银行明细每行宽度
		wsheet2.setColumnView(0, 10);
		wsheet2.setColumnView(1, 30);
		wsheet2.setColumnView(2, 30);
		wsheet2.setColumnView(3, 20);
		wsheet2.setColumnView(4, 20);
		wsheet2.setColumnView(5, 25);
		
		wsheet4.setColumnView(0, 10);
		wsheet4.setColumnView(1, 30);
		wsheet4.setColumnView(2, 30);
		wsheet4.setColumnView(3, 20);
		wsheet4.setColumnView(4, 20);
		wsheet4.setColumnView(5, 25);
		
	}

	/**
	 * 输出某项目公司期间内的资金日报表
	 * 
	 * @param wsheet
	 * @param begin
	 * @param end
	 * @param cd
	 * @param name
	 * @throws WriteException
	 */
	public void doSimProjExcel(WritableSheet wsheet, String begin, String end,
			String cd, String name) throws WriteException {
		HashMap font = finProjectAcctRelManager.fontMap();
		WritableCellFormat wcfN_gray_Bold = (WritableCellFormat) font
				.get("wcfN_gray_Bold");
		WritableCellFormat wcfN_notCol_Bold = (WritableCellFormat) font
				.get("wcfN_notCol_Bold");
		WritableCellFormat wcfN_Bold = (WritableCellFormat) font
				.get("wcfN_Bold");
		WritableCellFormat wcfN_N_noBold = (WritableCellFormat) font
				.get("wcfN_N_noBold");
		WritableCellFormat wcfN_noBold = (WritableCellFormat) font
				.get("wcfN_noBold");
		WritableCellFormat wcfN_Y_Bold = (WritableCellFormat) font
				.get("wcfN_Y_Bold");
		WritableCellFormat wcfN_N_Y_noBold = (WritableCellFormat) font
				.get("wcfN_N_Y_noBold");
		wsheet.addCell(new Label(0, 0, name + begin + " - " + end + "资金日报简表",
				wcfN_notCol_Bold));
		wsheet.mergeCells(0, 0, 6, 0);
		wsheet.setRowView(0, 500);
		wsheet.addCell(new Label(0, 1, "类型", wcfN_Bold));
		wsheet.addCell(new Label(1, 1, "开户行名称", wcfN_Bold));
		wsheet.addCell(new Label(2, 1, "币种", wcfN_Bold));
		wsheet.addCell(new Label(3, 1, "期初金额", wcfN_Bold));
		wsheet.addCell(new Label(4, 1, "收入", wcfN_Bold));
		wsheet.addCell(new Label(5, 1, "支出", wcfN_Bold));
		wsheet.addCell(new Label(6, 1, "期末金额", wcfN_Bold));
		wsheet.setRowView(1, 500);
		// 输出银行\现金记录,只记录人民币的
		results = finProjectAcctRelManager.getACCTByConditions(beginTime
 + " 00:00:00", endTime + " 23:59:59", cd, null, "RMB", true);
		int rowi = 2;// 当前记录
		int totRowi = 0;// 合计插入的银行行数；
		boolean haveCash = false;
		for (FinProjectAccUtil acc : results) {
			String accName = "银行";
			if (acc.getAccountTypeCd() != null
					&& "2".equals(acc.getAccountTypeCd())) {
				accName = "现金";
				// 若为现金，则跳两行，注入合计部分
				if (!haveCash) {
					totRowi = rowi;
					rowi = rowi + 2;
					haveCash = true;
				}
			}
			wsheet.addCell(new Label(0, rowi, accName, wcfN_noBold));
			wsheet.addCell(new Label(1, rowi, acc.getBankName(),
					wcfN_noBold));
			wsheet.addCell(new Label(2, rowi, acc.getCurrencyCd(),
					wcfN_noBold));
			wsheet.addCell(new Number(3, rowi, (acc.getBefAmount())
					.doubleValue(),wcfN_N_noBold));
			wsheet.addCell(new Number(4, rowi, (acc.getInAmount())
					.doubleValue(),wcfN_N_noBold));
			wsheet.addCell(new Number(5, rowi, (acc.getOutAmount())
					.doubleValue(),wcfN_N_noBold));
			wsheet.addCell(new Number(6, rowi, (acc.getAftAmount())
					.doubleValue(),wcfN_N_noBold));
			wsheet.setRowView(rowi, 500);
			rowi++;
		}
		if (totRowi == 0) {
			totRowi = rowi;
		}
		// 得到合计数据;
		List<FinProjectAccUtil> accUtils = finProjectAcctRelManager
				.getProjectSumByAcc(beginTime + " 00:00:00", endTime
						+ " 23:59:59", cd, "RMB", "all");
		for (FinProjectAccUtil accUtil : accUtils) {
			String accName = "";
			if ("1".equals(accUtil.getAccountTypeCd())) {
				if ("1".equals(accUtil.getEnableFlg())) {
					// 可动用
					accName = "银行可动用小计";
				} else {
					accName = "银行不可动用小计";
				}
				wsheet.mergeCells(0, totRowi, 1, totRowi);
				wsheet.addCell(new Label(0, totRowi, accName, wcfN_Y_Bold));
				wsheet.addCell(new Label(2, totRowi, "RMB", wcfN_Y_Bold));
				wsheet.addCell(new Number(3, totRowi, accUtil.getBefAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet.addCell(new Number(4, totRowi, accUtil.getInAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet.addCell(new Number(5, totRowi, accUtil.getOutAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet.addCell(new Number(6, totRowi, accUtil.getAftAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet.setRowView(totRowi, 500);
				totRowi++;
			} else {
				// 现金
				if ("1".equals(accUtil.getEnableFlg())) {
					accName = "现金可动用小计";
				} else {
					accName = "现金不可动用小计";
				}
				wsheet.mergeCells(0, rowi, 1, rowi);
				wsheet.addCell(new Label(0, rowi, accName, wcfN_Y_Bold));
				wsheet.addCell(new Label(2, rowi, "RMB", wcfN_Y_Bold));
				wsheet.addCell(new Number(3, rowi, accUtil.getBefAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet.addCell(new Number(4, rowi, accUtil.getInAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet.addCell(new Number(5, rowi, accUtil.getOutAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet.addCell(new Number(6, rowi, accUtil.getAftAmount()
						.doubleValue(), wcfN_N_Y_noBold));
				wsheet.setRowView(rowi, 500);
				rowi++;
			}
		}
		accUtils = finProjectAcctRelManager.getProjectSumByAcc(beginTime
				+ " 00:00:00", endTime + " 23:59:59", cd, null, null);
		rowi++;
		for (FinProjectAccUtil accUtil : accUtils) {
			String accName = "可动用银行存款及现金小计";
			if (accUtil.getEnableFlg() != null
					&& "2".equals(accUtil.getEnableFlg())) {
				accName = "不可动用银行存款及现金小计";
			}
			wsheet.mergeCells(0, rowi, 1, rowi);
			wsheet.addCell(new Label(0, rowi, accName, wcfN_Y_Bold));
			wsheet.addCell(new Label(2, rowi, accUtil.getCurrencyCd(),
					wcfN_Y_Bold));
			wsheet.addCell(new Number(3, rowi, accUtil.getBefAmount()
					.doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(4, rowi, accUtil.getInAmount()
					.doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(5, rowi, accUtil.getOutAmount()
					.doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(6, rowi, accUtil.getAftAmount()
					.doubleValue(), wcfN_N_Y_noBold));
			wsheet.setRowView(rowi, 500);
			rowi++;
		}
		// 每一列宽度
		wsheet.setColumnView(0, 12);
		wsheet.setColumnView(1, 30);
		wsheet.setColumnView(2, 12);
		wsheet.setColumnView(3, 25);
		wsheet.setColumnView(4, 20);
		wsheet.setColumnView(5, 20);
		wsheet.setColumnView(6, 25);

	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
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

	public List<FinProjectAccUtil> getResults() {
		return results;
	}

	public void setResults(List<FinProjectAccUtil> results) {
		this.results = results;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getFinished() {
		return finished;
	}

	public void setFinished(String finished) {
		this.finished = finished;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public InputStream getOutStream() {
		return outStream;
	}

	public void setOutStream(InputStream outStream) {
		this.outStream = outStream;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public List<FinProjectAccUtil> getAccList() {
		return accList;
	}

	public void setAccList(List<FinProjectAccUtil> accList) {
		this.accList = accList;
	}

	public String getHaveFinished() {
		return haveFinished;
	}

	public void setHaveFinished(String haveFinished) {
		this.haveFinished = haveFinished;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

}

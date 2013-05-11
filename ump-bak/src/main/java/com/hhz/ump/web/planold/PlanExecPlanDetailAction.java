package com.hhz.ump.web.planold;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.dao.planold.PlanExecPlanDetailOldManager;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * 类名 PlanExecPlanDetailAction 创建者 李劲 创建日期 2010-7-2 描述
 * 执行计划详情Action,提供对计划详情的操作,减轻PlanExecutionPlanAction负担
 */
@Namespace("/planold")
@Results( { @Result(name = ActionSupport.SUCCESS, location = "plan-exec-plan-detail!exportExcel.action", type = "stream", params = {
		"contentType", "application/vnd.ms-excel", "contentDisposition", "attachment;filename=${excelFileName}.xls",
		"inputName", "excelFile" }) })
public class PlanExecPlanDetailAction extends ActionSupport {
    
	private static final long serialVersionUID = -1888757653586857836L;

	@Autowired
	private PlanExecPlanDetailOldManager detailManager;

	private String planDetailId;
	private String projectCd;

	private InputStream excelFile;
	private String excelFileName;
	
	//计划类型
	private String planTypeCd =DictContants.PLAN_TYPE_EXC;

	/**
	 * 审核确认计划详情信息，只有超级管理员有这个权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String confirmPlanInfo() throws Exception {
		if (SpringSecurityUtils.hasRole(GlobalConstants.A_EXECPLAN_SUP_ADMIN)) {
			if (detailManager.confirmPlanDetailInfo(planDetailId)) {
				Struts2Utils.renderText("succ");
			}
		}
		return null;
	}

	/**
	 * 功能: 批量确认计划详情信息，只有管理员才有此权限
	 * @param projectCd
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String bachConfirmPlanInfo() throws Exception {
		if (SpringSecurityUtils.hasRole(GlobalConstants.A_EXECPLAN_SUP_ADMIN)) {
			if (detailManager.batchConfirmPlanDetailInfo(projectCd,planTypeCd)) {
				Struts2Utils.renderText("succ");
			}
		}
		return null;
	}

	/**
	 * 功能: 导出EXCEL
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");

		HSSFRow row = sheet.createRow(0);// 创建第一行

		HSSFCell cell = row.createCell(0); // 创建第一列
		cell.setCellValue(new HSSFRichTextString("id"));

		cell = row.createCell(1);// 创建第二列
		cell.setCellValue(new HSSFRichTextString("姓名"));

		cell = row.createCell(2);// 创建第三列
		cell.setCellValue(new HSSFRichTextString("年龄"));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			workbook.write(baos);// 写入
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		byte[] ba = baos.toByteArray();
		excelFile = new ByteArrayInputStream(ba);
		excelFileName = new String("文件wen文件_222".getBytes("GBK"), "ISO8859-1");

		return SUCCESS;
	}

	public void setPlanDetailId(String planDetailId) {
		this.planDetailId = planDetailId;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public String getPlanTypeCd() {
		return planTypeCd;
	}

	public void setPlanTypeCd(String planTypeCd) {
		this.planTypeCd = planTypeCd;
	}

}

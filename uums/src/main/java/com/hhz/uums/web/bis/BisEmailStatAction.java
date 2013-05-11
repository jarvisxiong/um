/**
 * 
 */
package com.hhz.uums.web.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.uums.dao.bis.BisEmailStatManager;
import com.hhz.uums.entity.bis.BisEmailStat;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.vo.vw.VoBisEmailStat;
import com.hhz.uums.web.CrudActionSupport;

@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "bis-email-stat!main.action", type = "redirect"),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
				"excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" }) })
public class BisEmailStatAction extends CrudActionSupport<BisEmailStat> {

	private static final long serialVersionUID = 5329186552354300980L;

	@Autowired
	private BisEmailStatManager bisEmailStatManager;

	private BisEmailStat entity;
	private List<BisEmailStat> topList;
	private String usedSpace;
	private String noReadNums;
	private String noReadNames;
	private String topSort="";
	private List<VoBisEmailStat> sometimesList;
 
	private String filter_EQS_uiid;
	private String filter_LIKES_userName;
	private String filter_GED_createdDate;
	private String filter_LED_createdDate;
	private String filter_LIKES_relOrgName;
	private String filter_topSort;

	/**
	 * 日志工具
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 查询Map
	 */
	private Map<String, Object> map;
	/**
	 * 查询SQL
	 */
	private StringBuffer hql;
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;
	/**
	 * 通过URL传输的参数,需要转码
	 */
	private String ecode;
	
	// 这里不能定义page,否则显示不出查询结果;若要默认记录数,必须加上getPage()方法重写.
	private Page<BisEmailStat> page;
	private Page<VoBisEmailStat> voPage;

	@Override
	public BisEmailStat getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(Struts2Utils.getParameter("id"))) {
			entity = null;
			entity = bisEmailStatManager.getEntity(getId());
		} else {
			entity = null;
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

		this.prepareLoadList();

		JsonUtil.renderJson(voPage);

		return null;
	}

	private List<VoBisEmailStat> transfer(List<BisEmailStat> list) {
		List<VoBisEmailStat> voList = new ArrayList<VoBisEmailStat>();

		VoBisEmailStat vo = null;
		for (BisEmailStat t : list) {
			vo = new VoBisEmailStat();

			vo.setBisEmailStatId(t.getBisEmailStatId()); // 邮件id
			vo.setUiid(t.getUiid()); // 账号
			vo.setUserName(t.getUserName()); // 用户姓名
			vo.setRelOrgId(t.getRelOrgId()); // 部门Id
			vo.setRelOrgName(t.getRelOrgName()); // 部门名称

			vo.setNoReadCount(t.getNoReadCount()); // 未读邮件数
			vo.setTotalCount(t.getTotalCount()); // 邮件总数
			vo.setNoReadCountSize(t.getNoReadCountSize()); // 未读邮件占用空间
			vo.setTotalCountSize(t.getTotalCountSize()); // 总占用空间

			vo.setRemark(t.getRemark()); // 备注
			vo.setRecordDate(t.getRecordDate()); // 统计日期
			vo.setCreator(t.getCreator()); // 创建者
			vo.setCreatedCenterCd(t.getCreatedDeptCd()); //
			vo.setCreatedDeptCd(t.getCreatedDeptCd()); //
			vo.setCreatedPositionCd(t.getCreatedPositionCd()); //
			vo.setCreatedDate(t.getCreatedDate()); // 创建日期
			vo.setUpdator(t.getUpdator()); // 更新者
			vo.setUpdatedCenterCd(t.getUpdatedCenterCd()); //
			vo.setUpdatedDeptCd(t.getUpdatedDeptCd()); //
			vo.setUpdatedPositionCd(t.getUpdatedPositionCd()); //
			vo.setUpdatedDate(t.getUpdatedDate()); // 更新日期
			voList.add(vo);
		}
		return voList;
	}

	public void singerDraw() throws Exception {
		map = new HashMap<String,Object>();
		if(StringUtils.isBlank(filter_EQS_uiid)){
			if(entity != null && StringUtils.isNotBlank(entity.getUiid()) ){
				filter_EQS_uiid = entity.getUiid();
			}
		}
		this.createHql();
		this.returnArrayInfo(hql.toString(), map);
		
		

	}

	public void prepareToplist() throws Exception {
		try {
			map = new HashMap<String, Object>();
			// 创建SQL
			createHql();
			// 返回信息
			returnArrayInfo(hql.toString(),map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createHql(){
		
		hql = new StringBuffer().append(" from BisEmailStat cba where 1=1");
		// 第一次进入
		if(StringUtils.isBlank(filter_EQS_uiid) && StringUtils.isBlank(filter_topSort)){
			topSort = "20";
		}
		// 关联账号
		if(StringUtils.isNotBlank(filter_EQS_uiid) && StringUtils.isBlank(filter_topSort)){
			map.put("uiid", filter_EQS_uiid);
			hql.append(" and cba.uiid = :uiid  ");
		}
		// 用户姓名
		if(StringUtils.isNotBlank(filter_LIKES_userName)){
			map.put("filter_LIKES_userName", "%" + filter_LIKES_userName + "%");
			hql.append(" and cba.userName like :filter_LIKES_userName  ");
		}
		// 所在部门
		if(StringUtils.isNotBlank(filter_LIKES_relOrgName)){
			map.put("filter_LIKES_relOrgName", "%" + filter_LIKES_relOrgName + "%");
			hql.append(" and cba.relOrgName like :filter_LIKES_relOrgName  ");
		}
		// 开始日期从
		if (StringUtils.isNotBlank(filter_GED_createdDate)) {
			String str1 = filter_GED_createdDate + " 00:00";
			map.put("filter_GED_createdDate", str1);
			hql.append(" and  cba.createdDate >= to_date(:filter_GED_createdDate,'yyyy-mm-dd HH24:MI')");
		}
		// 结算日期到
		if (StringUtils.isNotBlank(filter_LED_createdDate)) {
			String str1 = filter_LED_createdDate + " 23:59";
			map.put("filter_LED_createdDate", str1);
			hql.append(" and cba.createdDate <= to_date(:filter_LED_createdDate,'yyyy-mm-dd HH24:MI')");
		}
		// 前多少名
		if (StringUtils.isNotBlank(filter_topSort) && StringUtils.isBlank(filter_EQS_uiid)) {
			topSort = filter_topSort;
		}
		// 排序方式
		if(StringUtils.isNotBlank(filter_EQS_uiid) && StringUtils.isBlank(filter_topSort)){
			hql.append(" order by cba.createdDate asc");
		}else{
			hql.append(" order by cba.noReadCount desc,cba.createdDate asc");
		}
		
		
	}
	
	//返回排行榜查询信息
	public void returnTopListInfo(List<VoBisEmailStat> list){
		noReadNums = "";
		noReadNames = "";
		if(list.size()<=0){
			
		}else{
			for (VoBisEmailStat bisEmailStat : list) {
				if (bisEmailStat.getNoReadCount() != null) {
					noReadNums += bisEmailStat.getNoReadCount() + ",";
				} else {
					noReadNums += "0,";
				}
				if (bisEmailStat.getUserName() != null) {
					noReadNames += list.indexOf(bisEmailStat)+1+bisEmailStat.getUserName() + ",";
				} else {
					noReadNames += list.indexOf(bisEmailStat)+1+"无名氏,";
				}
			}
			noReadNums.substring(0, noReadNums.lastIndexOf(","));
			noReadNames.substring(0, noReadNames.lastIndexOf(","));
		}
	}
	
	//返回排行榜查询信息
	public void returnSingerInfo(List<VoBisEmailStat> list){
		noReadNums = "";
		noReadNames = "";
		usedSpace = "";
		if(list.size() <= 0){
			
		}else{
			for (VoBisEmailStat bisEmailStat : sometimesList) {
				if (bisEmailStat.getUpdatedDate() != null) {
					String temp = DateOperator.formatDate(bisEmailStat.getUpdatedDate());
					noReadNames += temp + ",";
				} else {
					noReadNames += "未知,";
				}
				if (bisEmailStat.getNoReadCountSizeDivi() != null) {
					usedSpace += bisEmailStat.getNoReadCountSizeDivi() + ",";
				} else {
					usedSpace += "0,";
				}
				if (bisEmailStat.getNoReadCount() != null) {
					noReadNums += bisEmailStat.getNoReadCount() + ",";
				} else {
					noReadNums += "0,";
				}
			}
			usedSpace = usedSpace.substring(0, usedSpace.length() - 1);
			noReadNums = noReadNums.substring(0, noReadNums.length() - 1);
			noReadNames = noReadNames.substring(0, noReadNames.length() - 1);
		}
	}
	
	//返回所需查询信息
	public void returnArrayInfo(String hql,final Map<String, Object> map){
		topList = bisEmailStatManager.getDao().find(hql, map);
		if(StringUtils.isNotBlank(topSort)){
			int endIndex = Integer.parseInt(topSort);
			int count = topList.size();
			endIndex =(endIndex > count)?count:endIndex;
			List<BisEmailStat> tmpList = topList.subList(0, endIndex);
			sometimesList = transfer(tmpList);
		}else{
			sometimesList = transfer(topList);
		}
		if(StringUtils.isBlank(filter_EQS_uiid) && StringUtils.isNotBlank(filter_topSort)){
			this.returnTopListInfo(sometimesList);
		}else if(StringUtils.isBlank(filter_EQS_uiid) && StringUtils.isBlank(filter_topSort)){
			this.returnTopListInfo(sometimesList);
		}else{
			this.returnSingerInfo(sometimesList);
		}
	}


	/**
	 * 显示绘图
	 * @throws Exception
	 */
	public void prepareDraw() throws Exception {
		prepareModel();
		singerDraw();
	}
	public String draw() {

		return "draw";
	}

	public String toplist() {

		return "toplist";
	}

	public String main() {

		return "main";
	}

	/**
	 * 未读邮件统计Excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		List<BisEmailStat> result = bisEmailStatManager.find(filters);
		for (BisEmailStat t : result) {
			bisEmailStatManager.getDao().evict(t);
		}

		Map beans = new HashMap();
		beans.put("result", result);

		XLSTransformer transformer = new XLSTransformer();
		InputStream isTemplate = BisEmailStatAction.class.getClassLoader().getResourceAsStream(
				"jxlsTemplates/bisEmailStatResult.xls");
		HSSFWorkbook hssfWorkbook = transformer.transformXLS(isTemplate, beans);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		hssfWorkbook.write(bos);

		byte[] data = bos.toByteArray();
		excelFile = new ByteArrayInputStream(data);

		String fileName = "查询结果" + DateOperator.formatDate(new Date(), "MMddHHmm");
		excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	/**
	 * 查看日志明细
	 * 
	 * @param id
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepareDetail() throws Exception {
		prepareModel();
	}

	public String detail() {
		return "detail";
	}

	public BisEmailStat getEntity() {
		return entity;
	}

	public void setEntity(BisEmailStat entity) {
		this.entity = entity;
	}

	public List<BisEmailStat> getTopList() {
		return topList;
	}

	public void setTopList(List<BisEmailStat> topList) {
		this.topList = topList;
	}

	public String getNoReadNums() {
		return noReadNums;
	}

	public void setNoReadNums(String noReadNums) {
		this.noReadNums = noReadNums;
	}

	public String getUsedSpace() {
		return usedSpace;
	}

	public void setUsedSpace(String usedSpace) {
		this.usedSpace = usedSpace;
	}
	
	public List<VoBisEmailStat> getSometimesList() {
		return sometimesList;
	}

	public void setSometimesList(List<VoBisEmailStat> sometimesList) {
		this.sometimesList = sometimesList;
	}

	public String getNoReadNames() {
		return noReadNames;
	}

	public void setNoReadNames(String noReadNames) {
		this.noReadNames = noReadNames;
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

	/**
	 * 结算数据库数据填充EXCEL
	 * 
	 * @param sheetName
	 *            表名
	 * @param outExcelFileTmp
	 *            输出文件流
	 */
	private void fillInExcel(String sheetName, ByteArrayOutputStream outExcelFileTmp) {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 22);
		sheet.setDefaultRowHeight((short) 25);
		// 生成一个样式
		HSSFCellStyle style = PoiStyle.buildStyle(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = PoiStyle.buildStyle2(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style3 = PoiStyle.buildStyle3(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style4 = PoiStyle.buildStyle4(workbook);

		// 单元格
		HSSFCell cell = null;
		// 单元格内容
		HSSFRichTextString text = null;
		// 合并单元区域
		CellRangeAddress address = null;
		// 第1行
		HSSFRow row = sheet.createRow(0);
		// 登录账号
		PoiExcelTool.addCell(row, cell, text, 0, style2, "1", "登录账号", null);
		// 用户姓名
		PoiExcelTool.addCell(row, cell, text, 1, style2, "1", "用户姓名", null);
		// 所在部门
		PoiExcelTool.addCell(row, cell, text, 2, style2, "1", "所在部门", null);
		// 未读邮件数
		PoiExcelTool.addCell(row, cell, text, 3, style2, "1", "未读邮件数", null);
		// 总邮件数
		PoiExcelTool.addCell(row, cell, text, 4, style2, "1", "总邮件数", null);
		// 占比(%)
		PoiExcelTool.addCell(row, cell, text, 5, style2, "1", "占比(%)", null);
		// 未读邮件(M)
		PoiExcelTool.addCell(row, cell, text, 6, style2, "1", "未读邮件(M)", null);
		// 总邮件(M)
		PoiExcelTool.addCell(row, cell, text, 7, style2, "1", "总邮件(M)", null);
		// 占比(%)
		PoiExcelTool.addCell(row, cell, text, 8, style2, "1", "占比(%)", null);
		// 更新时间
		PoiExcelTool.addCell(row, cell, text, 9, style2, "1", "记录时间", null);

		// 第2行
		List<BisEmailStat> list = bisEmailStatManager.find(hql.toString(), map);
		List<VoBisEmailStat> applist = this.transfer(list);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		if (applist != null) {
			for (int i = 0; i < applist.size(); i++) {
				row = sheet.createRow(i + 1);
				VoBisEmailStat cba = applist.get(i);
				// 登录账号
				if (cba.getUiid() != null) {
					PoiExcelTool.addCell(row, cell, text, 0, style4, "1", cba.getUiid(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 0, style4, "1", "", null);
				}
				// 用户姓名
				if (cba.getUserName() != null) {
					PoiExcelTool.addCell(row, cell, text, 1, style4, "1", cba.getUserName(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 1, style4, "1", "", null);
				}
				// 所在部门
				if (cba.getRelOrgName() != null) {
					PoiExcelTool.addCell(row, cell, text, 2, style4, "1", cba.getRelOrgName(), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 2, style4, "1", "", null);
				}
				// 未读邮件数
				if (cba.getNoReadCount() != null) {
					PoiExcelTool.addCell(row, cell, text, 3, style4, "0", null, cba.getNoReadCount().longValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 3, style4, "1", "", null);
				}
				// 总邮件数
				if (cba.getTotalCount() != null) {
					PoiExcelTool.addCell(row, cell, text, 4, style4, "0", null, cba.getTotalCount().longValue());
				} else {
					PoiExcelTool.addCell(row, cell, text, 4, style4, "1", "", null);
				}
				// 占比(%)
				if (cba.getCountDivi() != null) {
					PoiExcelTool.addStrCell(row, cell, text, 5, style4, "0", null, cba.getCountDivi());
				} else {
					PoiExcelTool.addCell(row, cell, text, 5, style4, "1", "", null);
				}
				// 未读邮件(M)
				if (cba.getNoReadCountSizeDivi() != null) {
					PoiExcelTool.addDoubleCell(row, cell, text, 6, style4, "0", null, Double.valueOf(cba.getNoReadCountSizeDivi()));
				} else {
					PoiExcelTool.addCell(row, cell, text, 6, style4, "1", "", null);
				}
				// 总邮件(M)
				if (cba.getTotalCountSizeDivi() != null) {
					PoiExcelTool.addDoubleCell(row, cell, text, 7, style4, "0", null, Double.valueOf(cba.getTotalCountSizeDivi()));
				} else {
					PoiExcelTool.addCell(row, cell, text, 7, style4, "1", "", null);
				}
				// 占比(%)
				if (cba.getSizeDivi() != null) {
					PoiExcelTool.addStrCell(row, cell, text, 8, style4, "0", null, cba.getSizeDivi());
				} else {
					PoiExcelTool.addCell(row, cell, text, 8, style4, "1", "", null);
				}
				// 更新时间
				if (cba.getUpdatedDate() != null) {
					PoiExcelTool.addCell(row, cell, text, 9, style4, "1", f.format(cba.getUpdatedDate()), null);
				} else {
					PoiExcelTool.addCell(row, cell, text, 9, style4, "1", "", null);
				}
			}
			try {
				if (workbook != null) {
					workbook.write(outExcelFileTmp);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (workbook != null) {
					workbook = null;
				}
			}
		}

	}

	/**
	 * 查询前的准备操作
	 */
	public void prepareLoadList() {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");

		map = new HashMap<String,Object>();
		page = new Page<BisEmailStat>(10);
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式

		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("createdDate,noReadCount");
			page.setOrder(Page.DESC + "," + Page.DESC);
		} else {
			page.setOrderBy(sortField + ",createdDate,noReadCount");
			page.setOrder(order + "," + Page.DESC + "," + Page.DESC);
		}
		createHql();
		page = bisEmailStatManager.findPage(page, hql.toString(), map);
		voPage = new Page<VoBisEmailStat>(10);
		voPage.setPageNo(page.getPageNo());
		voPage.setTotalCount(page.getTotalCount());
		voPage.setResult(transfer(page.getResult()));
	}

	/**
	 * 导出结算数据库列表
	 * 
	 * @return
	 */
	public String exportToExcel() throws Exception {
		// 执行查询条件前置条件准备
		prepareLoadList();
		// this.list();
		// 文件名
		String sheetName = "邮件统计列表";
		// 输出文件流
		ByteArrayOutputStream outExcelFile = null;
		try {
			excelFileName = sheetName;
			outExcelFile = new ByteArrayOutputStream();
			// 将分析数据填入excel
			fillInExcel(sheetName, outExcelFile);
			// 将数据读出到数组
			byte[] data = outExcelFile.toByteArray();
			// 将数据写入文件，执行导出
			excelFile = new ByteArrayInputStream(data);
			// 文件名编码转换，防止乱码
			excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "export";
	}

	public static void main(String[] args) {
		
		System.out.println();

	}

	public Page<VoBisEmailStat> getVoPage() {
		return voPage;
	}

	public void setVoPage(Page<VoBisEmailStat> voPage) {
		this.voPage = voPage;
	}

	public String getFilter_EQS_uiid() {
		return filter_EQS_uiid;
	}

	public void setFilter_EQS_uiid(String filter_EQS_uiid) {
		this.filter_EQS_uiid = filter_EQS_uiid;
	}

	public String getFilter_GED_createdDate() {
		return filter_GED_createdDate;
	}

	public void setFilter_GED_createdDate(String filter_GED_createdDate) {
		this.filter_GED_createdDate = filter_GED_createdDate;
	}

	public String getFilter_LED_createdDate() {
		return filter_LED_createdDate;
	}

	public void setFilter_LED_createdDate(String filter_LED_createdDate) {
		this.filter_LED_createdDate = filter_LED_createdDate;
	}

	public String getFilter_topSort() {
		return filter_topSort;
	}

	public void setFilter_topSort(String filter_topSort) {
		this.filter_topSort = filter_topSort;
	}

	public String getTopSort() {
		return topSort;
	}

	public void setTopSort(String topSort) {
		this.topSort = topSort;
	}

	public String getFilter_LIKES_userName() {
		return filter_LIKES_userName;
	}

	public void setFilter_LIKES_userName(String filter_LIKES_userName) {
		this.filter_LIKES_userName = filter_LIKES_userName;
	}

	public String getFilter_LIKES_relOrgName() {
		return filter_LIKES_relOrgName;
	}

	public void setFilter_LIKES_relOrgName(String filter_LIKES_relOrgName) {
		this.filter_LIKES_relOrgName = filter_LIKES_relOrgName;
	}

	
	
}

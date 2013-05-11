package com.hhz.ump.web.bis;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisMainShopReportManager;
import com.hhz.ump.dao.bis.BisManageDayManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisRentalCollectionRateManager;
import com.hhz.ump.dao.bis.BisSellConditionManager;
import com.hhz.ump.entity.bis.BisMainShopReport;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisRentalCollectionRate;
import com.hhz.ump.entity.bis.BisSellCondition;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.BisRentalCollectionRateUtil;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.util.HelperUtil;
import com.hhz.ump.web.vo.BisManageDayVo;
import com.hhz.ump.web.vo.BisSellConditionVo;
import com.hhz.ump.web.vo.VoSellConditionCount;

/**
 * 决策平台-报表
 * 
 * @author liuzhihui 2012-06-11
 * @version 1.0
 */
@Namespace("/bis")
@Results({ 
	@Result(name = "export", 
			type = "stream",params = {"contentType","application/vnd.ms-excel","inputName", "excelFile","contentDisposition", "attachment;filename=${excelFileName}.xls"})
})
public class BisDecisionAction extends CrudActionSupport<BisSellCondition> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private BisSellConditionManager bisSellConditionManager;
	@Autowired
	private BisRentalCollectionRateManager bisRentalCollectionRateManager;
	@Autowired
	private BisMainShopReportManager bisMainShopReportManager;
	@Autowired
	private BisManageDayManager bisManageDayManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	public HelperUtil helper=new HelperUtil();
	
	private BisSellCondition entity;
	private String id;
	
	//报表类型（1、2、3、4）
	private String reportType;

	// 导出Excel需要的参数
	private InputStream excelFile;
	private String excelFileName;
	private File importFile;
	private String year; //年
	private String month; //月
	private String week; //周

	private List<BisSellConditionVo> bisSellConditionVoList;
	private List<BisRentalCollectionRateUtil> bisRentalCollectionRateUtilList;
	private List<BisMainShopReport> bisMainShopReportList;
	private List<BisManageDayVo> bisManageDayVoList;
	private VoSellConditionCount voSellCount;
	private BisRentalCollectionRateUtil voRentalCollectionRate;
	private BisManageDayVo bisManageDayCount;
	
	/**
	 * 跳转主页面
	 */
	public String main() throws Exception {
		return "main";
	}
	
	/**
	 * 跳转至销售情况报表导入导出页面
	 */
	public String addFileReport() throws Exception {
		return "file";
	}
	
	/**
	 * 查询数据及跳转页面
	 */
	@Override
	public String list() throws Exception {
		if(StringUtils.isNotBlank(reportType)){
			//销售情况
			if("1".equals(reportType.trim())){
				loadSellConditionList();
				return "bisSell";
			}
			//商业收费情况
			else if ("2".equals(reportType.trim())) {
				loadBisChargeList();
				return "bisCharge";
			}
			//主力店招商情况
			else if ("3".equals(reportType.trim())) {
				loadBisMainShopList();
				return "mainShop";
			}
			//商家营业业绩状况
			else if ("4".equals(reportType.trim())) {
				loadBisManageDayList();
				return "bisYeji";
			}
		}
		return null;
	}

	/**
	 * 销售情况报表数据
	 */
	private void loadSellConditionList() throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		page.setPageSize(100);
		page = bisSellConditionManager.findPage(page, returnHql(params), params);
		//将金额格式化后设值到vo
		this.getSellByFormateMoney(page.getResult());
	}
	
	public String loadCount() throws Exception{
		//查询合计
		String projectIds = Struts2Utils.getParameter("projectIds");
		voSellCount = bisSellConditionManager.getSellCount(year,month,projectIds);
		return "sellCount";
	}
	
	/**
	 * 商业收费情况报表
	 * @throws Exception
	 */
	private void loadBisChargeList() throws Exception{
		//取值参考BisRentalCollectionRateAction中的cumuList()方法
		if(StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)){
			Map<String, List<BisProject>> rateProjects = bisRentalCollectionRateManager.getBisProjects("1");
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("projects", rateProjects);
			this.getYearMonth(param); //处理年月
			bisRentalCollectionRateUtilList = bisRentalCollectionRateManager.bisRentSumaryList(param);
		}
	}
	
	/**
	 * 主力店招商情况报表
	 */
	private void loadBisMainShopList() throws Exception{
		//取值参考BisMainShopReportAction中的list()方法
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startDay", Struts2Utils.getParameter("startDay"));
		param.put("endDay", Struts2Utils.getParameter("endDay"));
		/**筛选出做显示的项目，其他的忽略*/
		String projectIds = bisMainShopReportManager.getBisProjectIds(param);
		Map<String, List<BisProject>> projects = bisMainShopReportManager.getBisProjects(projectIds);
		param.put("projects", projects);
		bisMainShopReportList  = bisMainShopReportManager.getBisMainShopReportList(param);
	}
	
	/**
	 * 商家营业业绩状况报表
	 */
	public void loadBisManageDayList() throws Exception{
		bisManageDayVoList = bisManageDayManager.getBisManageDayList(year,month);
		bisManageDayCount = bisManageDayManager.getBisManageDayCount(bisManageDayVoList);
	}
	
	/**
	 * 处理年月
	 * @param param
	 */
	private void getYearMonth(Map<String, Object> param){
		int dyear =Integer.parseInt(year);
		int dmonth = Integer.parseInt(month);
		int lastYear,beforeYear = 0;
		int lastM,beforeM = 0;
		if(dmonth == 1){
			lastYear = dyear-1;
			lastM = 12;
			beforeYear = dyear -1;
			beforeM = 11;
		}else if(dmonth == 2){
			lastYear = dyear;
			lastM = 1;
			beforeYear = dyear -1;
			beforeM = 12;
		}else{
			lastYear = dyear;
			lastM = dmonth-1;
			beforeYear = dyear;
			beforeM = dmonth-2;
		}
		String smonth = String.valueOf(dmonth);
		if(dmonth < 10){
			smonth ="0"+dmonth;
		}
		String slastMonth = String.valueOf(lastM);
		if(lastM < 10){
			slastMonth ="0"+lastM;
		}
		String sbeforeMonth = String.valueOf(beforeM);
		if(beforeM < 10){
			sbeforeMonth ="0"+beforeM;
		}
		String theMonth = dyear+"-"+smonth;
		String curMonth = dyear+"-"+smonth;
		String lastMonth = lastYear+"-"+slastMonth;
		String beforeMonth = beforeYear+"-"+sbeforeMonth;
		param.put("theMonth", theMonth);
		param.put("curMonth", curMonth);
		param.put("lastMonth", lastMonth);
		param.put("beforeMonth", beforeMonth);
		Struts2Utils.getRequest().setAttribute("curMonth", curMonth);
		Struts2Utils.getRequest().setAttribute("lastMonth", lastMonth);
	}

	/**
	 * 提交、确认、驳回月报
	 */
	public String changeReportStatus() throws Exception{
		String statusFlg = Struts2Utils.getParameter("statusFlg");
		List<BisSellCondition> list = this.getBisSellConditionList();
		if(StringUtils.isNotBlank(statusFlg) && !list.isEmpty()){
			for (BisSellCondition tmpSell : list) {
				if("submit".equals(statusFlg)){
					tmpSell.setStatusFlg("1"); //1:提交
				}else if("confirm".equals(statusFlg)){
					tmpSell.setStatusFlg("2"); //2:确认
					String updator = SpringSecurityUtils.getCurrentUiid();
					tmpSell.setUpdator(updator); //确认人
					tmpSell.setUpdatedDate(new Date()); //确认时间
				}else if("reject".equals(statusFlg)){
					tmpSell.setStatusFlg("3"); //3:驳回
				}
				bisSellConditionManager.saveBisSellCondition(tmpSell);
			}
		}
		this.loadSellConditionList();
		return "bisSell";
	}
	
	/**
	 * 查询销售情况报表数据
	 * @return
	 * @throws Exception
	 */
	private List<BisSellCondition> getBisSellConditionList() throws Exception{
		List<BisSellCondition> list = new ArrayList<BisSellCondition>();
		Map<String, Object> params = new HashMap<String, Object>();
		list = bisSellConditionManager.find(returnHql(params), params);
		return list;
	}

	/**
	 * 构建销售情况查询hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	private String returnHql(Map<String, Object> params) throws Exception{
		StringBuffer hql = new StringBuffer();
		hql.append(" from BisSellCondition t  where 1=1");
		if (StringUtils.isNotBlank(year)) {
			hql.append(" and t.sellYear =:sellYear");
			params.put("sellYear", Long.valueOf(year.trim()));
		}
		if (StringUtils.isNotBlank(month)) {
			hql.append(" and t.sellMonth =:sellMonth");
			params.put("sellMonth", Long.valueOf(month.trim()));
		}
		//具有A_DEC_SELL_VIEW查阅权限的人，只能查看状态已确认(statusFlg="3")的数据
		String[] role = SpringSecurityUtils.getCurrentRoleCds();
		for (int i = 0; i < role.length; i++) {
			if ("A_DEC_SELL_VIEW".equals(role[i])){
				hql.append(" and t.statusFlg =:statusFlg");
				params.put("statusFlg", "2"); //2:确认状态
			}
		}
		hql.append(" and t.bisProjectId in(select b.bisProjectId from BisProject b where b.sellCondition=:sellCondition)");
		params.put("sellCondition", "1"); //BisProject中sellCondition=‘1’代表销售报表的公司
		hql.append(" order by t.projectName asc");
		return hql.toString();
	}
	
	/**
	 * 查询月的第二个双周是否有数据
	 * @return
	 * @throws Exception
	 */
	public String checkIsExistTwoWeek() throws Exception{
		String startDate = Struts2Utils.getParameter("startDate");
		String endDate = Struts2Utils.getParameter("endDate");
		String str = bisRentalCollectionRateManager.isExistTwoWeek(startDate, endDate);
		Struts2Utils.renderText(str);
		return null;
	}
	
	/**
	 * 导出‘销售情况’、‘商业收费情况’报表模板
	 * @param reportType 1-销售情况、 2-商业收费情况
	 * @return
	 * @throws Exception
	 */
	public String exportTemplate() throws Exception{
		if(StringUtils.isNotBlank(reportType)){
			Map<String,Object> params = new HashMap<String,Object>();
			//销售情况报表
			if("1".equals(reportType)){
				params.put("year", year);
				params.put("month", month);
				params.put("reportType", reportType);
				//1-9月转成01-09
				String m = null; 
				if(Integer.valueOf(month) < 10) {
					m = "0"+month;
				} else {
					m = month;
				}
				excelFileName = "销售情况汇报表"+"("+year+"-"+m+")";
			}
			//商业收费情况报表
			else if("2".equals(reportType)){
				String startDate = Struts2Utils.getParameter("startDate");
				String endDate = Struts2Utils.getParameter("endDate");
				params.put("startDate", startDate);
				params.put("endDate", endDate);
				params.put("reportType", reportType);
				if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
					excelFileName = "商业收费情况报表("+startDate+"~"+endDate+")";
				}else{
					excelFileName = "商业收费情况报表("+startDate+")";
				}
			}
			excelFile = bisSellConditionManager.buildTemplateExcel(params);
			excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		}
		return "export";
	}
	
	/**
	 * 导入模板
	 * @return
	 * @throws Exception
	 */
	public String importTemplate() throws Exception{
		if(StringUtils.isNotBlank(reportType)){
			if("1".equals(reportType)){
				//导入销售情况
				importSellDataPoi();
			}else if("2".equals(reportType)){
				//导入商业收费情况
				importChargeDataPoi();
			}
		}
		return null;
	}
	
	
	/**
	 * 导入‘商业收费月报’模板数据
	 * @return
	 * @throws Exception
	 */
	public String importChargeDataPoi() throws Exception{
		long begin = new Date().getTime();
		int insert = 0;
		int update = 0;
		int r = 0;
		int c = 0;

		int row = 0;
		try {
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(importFile));
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			
			List<BisRentalCollectionRate> rateList = new ArrayList<BisRentalCollectionRate>();
			List<String> ymList = new ArrayList<String>(); //存放年月list
			
			/**
			 * 注意：以下代码所写的注释请参考对应的导出的excel模板看
			 */
			
			//开始读取数据
			for (row = 1; row <= hssfSheet.getLastRowNum()-1; row++) {
				HSSFRow hssfRow = hssfSheet.getRow(row);
				if(row == 1){
					//将第2行的年月加载到list列表中
					for (int col = 2; col < hssfRow.getLastCellNum(); col++) {
						String ymStr = hssfRow.getCell(col).getStringCellValue();
						if(StringUtils.isNotBlank(ymStr)){
							ymStr = ymStr.replace("(能耗)", "");//去除“(能耗)”字符串
							ymList.add(ymStr);
						}
					}
					continue;
				}
				if(row == 2){
					continue;//第3行跳过
				}
				
				if (hssfRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					continue;
				}
				//得到第一列影藏的‘项目ID’
				String hiddenProjectId = hssfRow.getCell(0).getStringCellValue();
				if(StringUtils.isNotBlank(hiddenProjectId)){
					//得到项目公司
					BisProject tmpBisProject = bisProjectManager.getEntity(hiddenProjectId);
					if(tmpBisProject == null){
						Struts2Utils.renderText(",error, 未导入成功，第" + (row + 1) + "行的项目信息已在项目表中被删除，请删除该行数据在导入,");
						return null;
					}
					//每循环4列用于计算的索引值(每一行中每循环4列增加一条数据)
					int index = 4;
					//用于从ymList取年月的索引
					int listIndex = 0;
					//循环的开始列与结束列(默认从第2列循环到第6列，具体看导出的excel模板即可)
					int startCol = 2;
					int endCol = 6;
					
					//开始循环整列
					for (int col = 2; col < hssfRow.getLastCellNum(); col++) {
						//当索引值为4时读取相应列的数据(当index==4时，即读取下一个月的数据)
						if(index == 4){
							//从上面list列表中取得年月值(yyyy-MM)
							String queryYm = ymList.get(listIndex);
							//查询数据是否存在(存在则直接覆盖，不存在则添加)
							BisRentalCollectionRate bisRental = bisRentalCollectionRateManager.getExistsBisRental(hiddenProjectId,queryYm);
							if(bisRental == null){
								continue;
								//由于导出的数据都是数据库中已存在的，所以屏蔽增加
								/*insert++;
								bisRental = new BisRentalCollectionRate();
								bisRental.setStatusFlg("0"); // 0:新增
								*/							
							}else{
								update++;
							}
							index = 0; //将索引值置为0,用于重新计算其是否等于4
							listIndex++; //取年月索引值没循环4列后加1
							
							//开始读取指定开始列到结束列的值
							for (int col2 = startCol; col2 < endCol; col2++) {
								c = col2;
								HSSFCell hssfCell = hssfRow.getCell(col2);
								if(hssfCell!=null){
									String cellValue = null;
									if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING  || hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
										cellValue = hssfCell.getStringCellValue();
									}
									if(StringUtils.isNotBlank(cellValue)){
										if (hssfCell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC){
											Struts2Utils.renderText(",error, 未导入成功，请于第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列检查是否为数字类型,");
											return null;
										}
									}
									BigDecimal cellValueBig = new BigDecimal(0);
									if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
										cellValueBig = new BigDecimal(hssfCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
									}
									bisRental.setBisProjectId(tmpBisProject.getBisProjectId());
									//bisRental.setCreatedDate(new Date());
									//bisRental.setCreator(SpringSecurityUtils.getCurrentUiid());
									//bisRental.setUpdatedDate(new Date());
									//bisRental.setUpdator(SpringSecurityUtils.getCurrentUiid());
									//列如：2012-01-01 ~ 2012-01-15代表月的第一个双周
									//     2012-01-16 ~ 2012-01-31代表月的第二个双周
									//这里值默认插入第二个双周
									//bisRental.setStartDay(queryYm+"-16");
									//bisRental.setEndDay(queryYm+"-31");
									if(col2 == startCol){
										bisRental.setCumulativeRecAmountEner(cellValueBig);//累计应收额
									}
									if(col2 == (startCol+1)){
										bisRental.setCumulativeColRateEner(cellValueBig);//累计实收额
									}
									if(col2 == (startCol+2)){
										bisRental.setCumulativeRecAmountCurEner(cellValueBig);//本月应收额
									}
									if(col2 == (startCol+3)){
										bisRental.setCumulativeColRateCurEner(cellValueBig);//本月实收额
									}
								}
							}
							//开始、结束列每读取一次+4，方便定位下一个月的数据的读取列
							startCol = startCol+4; 
							endCol = endCol+4;
							rateList.add(bisRental);
						}
						index++; //当index++到4时，即读取下一个月的数据
					}
				}
			}
			if(!rateList.isEmpty()){
				List<BisRentalCollectionRate> list = new ArrayList<BisRentalCollectionRate>();
				for (BisRentalCollectionRate tmpRental : rateList) {
					/**
					 * 计算累计收缴率
					 */
					BigDecimal cumulativeColRateEner = new BigDecimal(0); //累计实收额
					BigDecimal cumulativeRecAmountEner = new BigDecimal(0); //累计应收额
					if(tmpRental.getCumulativeColRateEner() != null){
						cumulativeColRateEner = tmpRental.getCumulativeColRateEner();
					}
					if(tmpRental.getCumulativeRecAmountEner() != null){
						cumulativeRecAmountEner = tmpRental.getCumulativeRecAmountEner();
					}
					//若果除数不为0才计算
					if(cumulativeColRateEner.compareTo(BigDecimal.ZERO) != 0){
						//累计收缴率(能耗) = 累计实收额/累计应收额
						tmpRental.setRentalCollPointEner(cumulativeRecAmountEner.divide(cumulativeColRateEner,2,BigDecimal.ROUND_HALF_UP));
					}
					list.add(tmpRental);
				}
				bisRentalCollectionRateManager.batchExecute(list);
			}
			long end = new Date().getTime();
			Struts2Utils.renderText(",success," + insert + "," + update + "," + (end - begin) / 1000 + ",");
		} catch (Exception e) {
			e.printStackTrace();
			String errorLocation = (r + 1) + "行 " + BisConstants.letters.get(c + 1) + "列";
			Struts2Utils.renderText(",error," + e + ", " + errorLocation + ",");
		}
		return null;
	}
	
	/**
	 * 导入‘销售情况月报’模板数据
	 * @return
	 * @throws Exception
	 */
	public String importSellDataPoi() throws Exception{
		long begin = new Date().getTime();
		int insert = 0;
		int update = 0;
		int r = 0;
		int c = 0;

		int row = 0;
		int col = 0;
		try {
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(importFile));
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			HSSFRow firstrow = hssfSheet.getRow(0);
			//得到年月
			String ym = firstrow.getCell(0).getStringCellValue();
			String tmpYm = this.subStr(ym);
			String[] date = tmpYm.split("-");
			String sellYear = date[0];
			String sellMonth = date[1];
			
			List<BisSellCondition> sellConditionList = new ArrayList<BisSellCondition>();
			//开始读取数据
			for (row = 4; row <= hssfSheet.getLastRowNum()-1; row++) {
				HSSFRow hssfRow = hssfSheet.getRow(row);
				if (hssfRow.getCell(0).getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					continue;
				}
				//得到第一列影藏的’项目ID‘
				String hiddenProjectId = hssfRow.getCell(0).getStringCellValue();
				if(StringUtils.isNotBlank(hiddenProjectId)){
					//得到项目公司
					BisProject tmpBisProject = bisProjectManager.getEntity(hiddenProjectId);
					if(tmpBisProject == null){
						Struts2Utils.renderText(",error, 未导入成功，第" + (row + 1) + "行的项目信息已在项目表中被删除，请删除该行数据在导入,");
						return null;
					}
					// 查询数据是否存在
					BisSellCondition bisSellCondition = bisSellConditionManager.getExistsSellCondition(hiddenProjectId,sellYear,sellMonth);
					//查询数据是否存在(存在则直接覆盖，不存在则添加)
					if(bisSellCondition == null){
						insert++;
						bisSellCondition = new BisSellCondition();
						bisSellCondition.setStatusFlg("0"); // 0:新增
					}else{
						update++;
					}
					for (col = 2; col < hssfRow.getLastCellNum(); col++) {
						c = col;
						HSSFCell hssfCell = hssfRow.getCell(col);
						if(hssfCell!=null){
							String cellValue = null;
							if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING  || hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
								cellValue = hssfCell.getStringCellValue();
							}
							if(StringUtils.isNotBlank(cellValue)){
								if (hssfCell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC){
									Struts2Utils.renderText(",error, 未导入成功，请于第" + (row + 1) + "行第" + BisConstants.letters.get(c + 1) + "列检查是否为数字类型,");
									return null;
								}
							}
							bisSellCondition.setBisProjectId(tmpBisProject.getBisProjectId());
							bisSellCondition.setProjectName(tmpBisProject.getProjectName());
							bisSellCondition.setSellYear(Long.valueOf(sellYear));
							bisSellCondition.setSellMonth(Long.valueOf(sellMonth));
							this.doSetSellValue(bisSellCondition, col, hssfCell);
						}
					}
					sellConditionList.add(bisSellCondition);
				}
			}
			bisSellConditionManager.batchExecute(sellConditionList);
			long end = new Date().getTime();
			Struts2Utils.renderText(",success," + insert + "," + update + "," + (end - begin) / 1000 + ",");
		} catch (Exception e) {
			e.printStackTrace();
			String errorLocation = (r + 1) + "行 " + BisConstants.letters.get(c + 1) + "列";
			Struts2Utils.renderText(",error," + e + ", " + errorLocation + ",");
		}
		return null;
	}
	
	//设置值
	public void doSetSellValue(BisSellCondition bisSellCondition,int col,HSSFCell hssfCell) throws Exception{
		BigDecimal cellValueBig = new BigDecimal(0);
		String cellValueStr = null;
		if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			cellValueBig = new BigDecimal(hssfCell.getNumericCellValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
			cellValueStr = hssfCell.getStringCellValue().trim();
		}
		if(StringUtils.isBlank(cellValueStr)){
			cellValueStr=cellValueBig.toString();
		}
		if(col == 2){
			bisSellCondition.setSignMoneyJhMonth(cellValueBig);
		}else if(col == 3){
			bisSellCondition.setSignMoneySjMonth(cellValueBig);
		}else if(col == 4){
			bisSellCondition.setReturnMoneyJhMonth(cellValueBig);
		}else if(col == 5){
			bisSellCondition.setReturnMoneySjMonth(cellValueBig);
		}else if(col == 6){
			bisSellCondition.setSignMoneyJhYear(cellValueBig);
		}else if(col == 7){
			bisSellCondition.setSignMoneySjYear(cellValueBig);
		}else if(col == 8){
			bisSellCondition.setReturnMoneyJhYear(cellValueBig);
		}else if(col == 9){
			bisSellCondition.setReturnMoneySjYear(cellValueBig);
		}else if(col == 10){
			bisSellCondition.setDsMoney(cellValueBig);
		}else if(col == 11){
			bisSellCondition.setDsArea(cellValueBig);
		}else if(col == 12){
			bisSellCondition.setDsSuiteNum(cellValueBig);
		}else if(col == 13){
			bisSellCondition.setXkMoney(cellValueBig);
		}else if(col == 14){
			bisSellCondition.setXkArea(cellValueBig);
		}else if(col == 15){
			bisSellCondition.setXkSuiteNum(cellValueBig);
		}else if(col == 16){
			bisSellCondition.setNotSignMoney(cellValueBig);
		}else if(col == 17){
			bisSellCondition.setNotSignSuiteNum(cellValueBig);
		}else if(col == 18){
			bisSellCondition.setMortgageArrears(cellValueBig);
		}else if(col == 19){
			bisSellCondition.setOtherArrears(cellValueBig);
		}
	}
	
	/**
	 * 截取字符串
	 * @param str
	 * @return
	 */
	public String subStr(String str){
		Pattern p = Pattern.compile("\\((.*?)\\)");
	    Matcher m = p.matcher(str);
	    String retStr = null;
	    while (m.find()) {
	    	String tmpStr = m.group(1);
	        if(StringUtils.isNotBlank(tmpStr)){
	        	retStr = tmpStr.replace("年", "-").replace("月", "");
	        }
	      }
	    return retStr;
	}
	

	/**
	 * 将结果部分列的金额格式化为千分位
	 * @param list
	 * @throws Exception
	 */
	private void getSellByFormateMoney(List<BisSellCondition> list) throws Exception{
		if(!list.isEmpty()){
			bisSellConditionVoList = new ArrayList<BisSellConditionVo>();
			for (BisSellCondition tmpSell : list) {
				BisSellConditionVo vo = new BisSellConditionVo();
				vo.setBisSellConditionId(tmpSell.getBisSellConditionId());
				vo.setBisProjectId(tmpSell.getBisProjectId());
				vo.setProjectName(tmpSell.getProjectName());
				vo.setSellYear(tmpSell.getSellYear());
				vo.setSellMonth(tmpSell.getSellMonth());
				vo.setStatusFlg(tmpSell.getStatusFlg());
				vo.setSignMoneyJhMonth(FormatUtil.formatMoney(tmpSell.getSignMoneyJhMonth(),2));
				vo.setSignMoneySjMonth(FormatUtil.formatMoney(tmpSell.getSignMoneySjMonth(),2));
				vo.setMonthQyPercent(BisDecisionAction.calcuPercent(tmpSell.getSignMoneySjMonth(), tmpSell.getSignMoneyJhMonth()));
				vo.setReturnMoneyJhMonth(FormatUtil.formatMoney(tmpSell.getReturnMoneyJhMonth(),2));
				vo.setReturnMoneySjMonth(FormatUtil.formatMoney(tmpSell.getReturnMoneySjMonth(),2));
				vo.setMonthHkPercent(BisDecisionAction.calcuPercent(tmpSell.getReturnMoneySjMonth(), tmpSell.getReturnMoneyJhMonth()));
				vo.setSignMoneyJhYear(FormatUtil.formatMoney(tmpSell.getSignMoneyJhYear(),2));
				vo.setSignMoneySjYear(FormatUtil.formatMoney(tmpSell.getSignMoneySjYear(),2));
				vo.setYearQyPercent(BisDecisionAction.calcuPercent(tmpSell.getSignMoneySjYear(), tmpSell.getSignMoneyJhYear()));
				vo.setReturnMoneyJhYear(FormatUtil.formatMoney(tmpSell.getReturnMoneyJhYear(),2));
				vo.setReturnMoneySjYear(FormatUtil.formatMoney(tmpSell.getReturnMoneySjYear(),2));
				vo.setYearHkPercent(BisDecisionAction.calcuPercent(tmpSell.getReturnMoneySjYear(), tmpSell.getReturnMoneyJhYear()));
				vo.setDsMoney(FormatUtil.formatMoney(tmpSell.getDsMoney(),2));
				vo.setDsArea(FormatUtil.formatMoney(tmpSell.getDsArea(),2));
				vo.setDsSuiteNum(FormatUtil.formatMoney(tmpSell.getDsSuiteNum(),2));
				vo.setXkMoney(FormatUtil.formatMoney(tmpSell.getXkMoney(),2));
				vo.setXkArea(FormatUtil.formatMoney(tmpSell.getXkArea(),2));
				vo.setXkSuiteNum(FormatUtil.formatMoney(tmpSell.getXkSuiteNum(),2));
				vo.setNotSignMoney(FormatUtil.formatMoney(tmpSell.getNotSignMoney(),2));
				vo.setNotSignSuiteNum(FormatUtil.formatMoney(tmpSell.getNotSignSuiteNum(),2));
				vo.setMortgageArrears(FormatUtil.formatMoney(tmpSell.getMortgageArrears(),2));
				vo.setOtherArrears(FormatUtil.formatMoney(tmpSell.getOtherArrears(),2));
				vo.setRemark(tmpSell.getRemark());
				vo.setCreator(tmpSell.getCreator());
				vo.setCreatedCenterCd(tmpSell.getCreatedCenterCd());
				vo.setCreatedDeptCd(tmpSell.getCreatedDeptCd());
				vo.setCreatedPositionCd(tmpSell.getCreatedPositionCd());
				vo.setCreatedDate(tmpSell.getCreatedDate());
				vo.setUpdator(tmpSell.getUpdator());
				vo.setUpdatedCenterCd(tmpSell.getUpdatedCenterCd());
				vo.setUpdatedPositionCd(tmpSell.getUpdatedPositionCd());
				vo.setUpdatedDate(tmpSell.getUpdatedDate());
				bisSellConditionVoList.add(vo);
			}
		}
	}
	
	public static String calcuPercent(BigDecimal value1,BigDecimal value2){
		BigDecimal val = new BigDecimal(0);
		if(value1 != null && value2 != null){
			val = value1.divide(value2,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
		}
		String ret = "0";
		String tmpStr = val.toString();
		String[] s = tmpStr.split("\\.");
		if(s.length>1){
			if(s[1] != null && "" !=s[1]){
				if("00".equals(s[1])){
					ret = s[0];
				}else{
					ret = tmpStr;
				}
			}else{
				ret = tmpStr;
			}
		}
		
		return ret+"%";
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisSellConditionManager.getEntity(getId());
		} else {
			entity = new BisSellCondition();
		}
	}

	@Override
	public String delete() throws Exception {
		return null;
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
	public BisSellCondition getModel() {
		return entity;
	}

	public BisSellCondition getEntity() {
		return entity;
	}

	public void setEntity(BisSellCondition entity) {
		this.entity = entity;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
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

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public VoSellConditionCount getVoSellCount() {
		return voSellCount;
	}

	public void setVoSellCount(VoSellConditionCount voSellCount) {
		this.voSellCount = voSellCount;
	}

	public List<BisSellConditionVo> getBisSellConditionVoList() {
		return bisSellConditionVoList;
	}

	public void setBisSellConditionVoList(
			List<BisSellConditionVo> bisSellConditionVoList) {
		this.bisSellConditionVoList = bisSellConditionVoList;
	}

	public List<BisMainShopReport> getBisMainShopReportList() {
		return bisMainShopReportList;
	}

	public void setBisMainShopReportList(
			List<BisMainShopReport> bisMainShopReportList) {
		this.bisMainShopReportList = bisMainShopReportList;
	}

	public HelperUtil getHelper() {
		return helper;
	}

	public void setHelper(HelperUtil helper) {
		this.helper = helper;
	}

	public List<BisManageDayVo> getBisManageDayVoList() {
		return bisManageDayVoList;
	}

	public void setBisManageDayVoList(List<BisManageDayVo> bisManageDayVoList) {
		this.bisManageDayVoList = bisManageDayVoList;
	}

	public BisManageDayVo getBisManageDayCount() {
		return bisManageDayCount;
	}

	public void setBisManageDayCount(BisManageDayVo bisManageDayCount) {
		this.bisManageDayCount = bisManageDayCount;
	}

	public List<BisRentalCollectionRateUtil> getBisRentalCollectionRateUtilList() {
		return bisRentalCollectionRateUtilList;
	}

	public void setBisRentalCollectionRateUtilList(
			List<BisRentalCollectionRateUtil> bisRentalCollectionRateUtilList) {
		this.bisRentalCollectionRateUtilList = bisRentalCollectionRateUtilList;
	}

	public BisRentalCollectionRateUtil getVoRentalCollectionRate() {
		return voRentalCollectionRate;
	}

	public void setVoRentalCollectionRate(
			BisRentalCollectionRateUtil voRentalCollectionRate) {
		this.voRentalCollectionRate = voRentalCollectionRate;
	}

}

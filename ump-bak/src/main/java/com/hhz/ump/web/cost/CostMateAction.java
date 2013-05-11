package com.hhz.ump.web.cost;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostMateColManager;
import com.hhz.ump.dao.cost.CostMateManager;
import com.hhz.ump.dao.cost.CostMateModuleManager;
import com.hhz.ump.dao.cost.CostStrageMateManager;
import com.hhz.ump.entity.cost.CostMate;
import com.hhz.ump.entity.cost.CostMateCol;
import com.hhz.ump.entity.cost.CostMateModule;
import com.hhz.ump.entity.cost.CostMatePrice;
import com.hhz.ump.entity.cost.CostStrageMate;
import com.hhz.ump.entity.cost.CostStrageMateDetail;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.web.vo.EntityVO;

@Namespace("/cost")
public class CostMateAction extends CrudActionSupport<CostMate> {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(CostMateAction.class);
	
	@Autowired
	private CostMateManager costMateManager;
	@Autowired
	private CostMateModuleManager costMateModuleManager;
	@Autowired
	private CostMateColManager costMateColManager;
	@Autowired
	private CostStrageMateManager costStrageMateManager;

	private List<CostMate> costMateList;
	private List<EntityVO> parentIdList;
	private List<CostStrageMateDetail> costStrageMateDetailList;
	private CostMateModule costMateModule;
	private CostStrageMate costStrageMate;
	private CostMate entity;
	private String id;
	
	private String[] parentModuleId;
	private String inputFlag;
	private String parentId;
	private String authTypeName;
	//导入excle文件
	private File upload;
	private String uploadFileName;
	
	
	//类型路径
	private String parentModulePathName;
	

	public static String GROUP_NAME = "[关键字1]";
	public static String GROUP_NAME2 = "[关键字2]";
	public static String GROUP_NAME3 = "[关键字3]";
	public static String GROUP_NAME4 = "[关键字4]";
	public static String MATERIAL_NAME = "[材料名称]";
	public static String MATERIAL_NAME2 = "[设备名称]";
	public static String SPEC_NAME = "[规格]";
	public static String MODEL_NAME = "[型号]";
	public static String PRICE = "[单价]";
	public static String PRICE_MILLION = "[单价[万元]]";
	public static String MEMO_DESC = "[备注]";
	
	
	public static String EXT_FLG = "[可选]";
	public static String EXT_PRICE = "[可选单价]";
	

	public static int DB_FIELD_COUNT = 30;
	
	
	
	public String main() throws Exception {
		return "main";
	}
	
	
	/**
	 * 是否关键字段
	 * @param value
	 * @return
	 */
	public boolean isContainMap(String value){
		if((value.indexOf(GROUP_NAME) != -1) || (value.indexOf(GROUP_NAME2) != -1)|| (value.indexOf(GROUP_NAME3) != -1)|| (value.indexOf(GROUP_NAME4) != -1)
				|| MATERIAL_NAME.equals(value) || MATERIAL_NAME2.equals(value)
					|| SPEC_NAME.equals(value)
						|| MODEL_NAME.equals(value)
							|| PRICE.equals(value) 
							|| PRICE_MILLION.equals(value)
								|| MEMO_DESC.equals(value))
			return true;
		else
			return false;
		 
	}
	
	/**
	 * 根据设备分类得到材料设备
	 * @param mateBizCd 编号
	 * @param mateName 名称
	 * @param enableFlg 可用状态
	 * @param calcTypeCd 计算方式
	 * @param parentModuleIds 设备分类ids
	 */
	@Override
	public String list() throws Exception {
		
		String mateBizCd = Struts2Utils.getParameter("mateBizCd");
		String mateName = Struts2Utils.getParameter("mateName");
		String enableFlg = Struts2Utils.getParameter("enableFlg");
		String calcTypeCd = Struts2Utils.getParameter("calcTypeCd");
		
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from CostMate t where 1=1 ");
		if(parentModuleId != null && parentModuleId.length != 0){
			if (StringUtils.isNotBlank(parentModuleId[0])) {
				parentIdList = new ArrayList<EntityVO>();
				for (int j = 0; j < parentModuleId.length; j++) {
					EntityVO vo = new EntityVO();
					vo.setEntityId(parentModuleId[j]);
					parentIdList.add(vo);
				}
				hql.append(" and t.parentModuleId in(:parentModuleId)");
				values.put("parentModuleId",parentModuleId);
				if(StringUtils.isNotBlank(mateName)){
					hql.append(" and t.mateName like :mateName");
					values.put("mateName", "%"+mateName.trim()+"%");
				}
				if(StringUtils.isNotBlank(mateBizCd)){
					hql.append(" and t.mateBizCd like :mateBizCd");
					values.put("mateBizCd","%"+mateBizCd.trim()+"%");
				}
				if(StringUtils.isNotBlank(enableFlg)){
					hql.append(" and t.enableFlg = :enableFlg");
					values.put("enableFlg", Long.valueOf(enableFlg));
				}
				if(StringUtils.isNotBlank(calcTypeCd)){
					hql.append(" and t.calcTypeCd = :calcTypeCd");
					values.put("calcTypeCd", calcTypeCd);
				}
			}
		}else{
			
		}
		hql.append(" order by t.createdDate desc,t.parentModuleId desc");
		costMateList = costMateManager.findPage(page, hql.toString(),values).getResult();
		return "list";
	}

	@Override
	public String save() throws Exception {
		try {
			costMateManager.saveCostMate(entity);
			inputFlag = "保存成功!";
		} catch (Exception e) {
			e.printStackTrace();
			inputFlag = "保存失败!";
		}
		return "input";
	}
	
	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = costMateManager.getEntity(getId());
			if(entity != null){
				//有型号则不删除，无型号则删除并删除关联的属性
				if (entity.getCostMatePrices().size() > 0) {
					Struts2Utils.renderText("fail");
				}else{
					for (CostMateCol col : entity.getCostMateCols()) {
						costMateColManager.delete(col);
					}
					costMateManager.delete(entity);
					Struts2Utils.renderText("success");
				}
			}
		}
		return null;
	}
	
	/**
	 * 检测设备名称是否唯一
	 * @param  costMateId 
	 * @param  mateName
	 * @return
	 * @throws Exception
	 */
	public String checkMateName() throws Exception {
		String mateName = Struts2Utils.getParameter("mateName");
		String costMateId = Struts2Utils.getParameter("costMateId");
		String message = null;
		if (StringUtils.isNotBlank(mateName)) {
			List<CostMate> mateList = costMateManager.checkMateName(mateName);
			if (mateList != null && mateList.size() > 0) {
				CostMate mate = null;
				if (StringUtils.isNotBlank(costMateId)) {
					mate = costMateManager.getEntity(costMateId);
				}
				if (mate != null && mateName.trim().equals(mate.getMateName())) {
					message = "fail";
				}else{
					message = "success";
				}
			}else{
				message = "fail";
			}
		}
		Struts2Utils.renderText(message);
		return null;
	}
	
	//得到有效的大类ids
	public String getMateModuleId(){
		StringBuffer sb = new StringBuffer();
		List<String> moudleList = new ArrayList<String>();
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer()
		.append("from CostMateModule t where t.enableFlg = :enableFlg");
		values.put("enableFlg", Long.valueOf(1));
		List<CostMateModule> moduleList = costMateModuleManager.find(hql.toString(), values);
		for (CostMateModule tModule : moduleList) {
			moudleList.add(tModule.getCostMateModuleId());
			CostMateModule pModule = costMateModuleManager.getCostMateModule(tModule.getParentId());
			if (pModule != null && pModule.getEnableFlg() == 0) {
				moudleList.remove(tModule.getCostMateModuleId());
			}
		}
		for (String str : moudleList) {
			sb.append("'" + str + "',");
		}
		String idList = sb.toString();
		String ids = idList.substring(0,idList.length()-1);
		return ids;
		
	}
//	public String quickSearch(){
//		return null;
//	}
	/**
	 * 快速搜索(战略网上下单)
	 *    strategy-online-order.jsp
	 * @return
	 * @throws Exception
	 */
	public String quickSearch() throws Exception{
 		String value =Struts2Utils.getParameter("value");
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer()
		.append("from CostMate t where t.enableFlg = :enableFlg and t.parentModuleId")
		.append(" in(" + getMateModuleId() + ")");
		param.put("enableFlg", Long.valueOf(1));
		if(StringUtils.isNotBlank(value)){
			hql.append(" and t.mateName like :mateName");
			param.put("mateName", "%" + value.trim() + "%");
		}
		hql.append("  order by t.mateName asc");
		page.setPageSize(20);
		page = costMateManager.findPage(page, hql.toString(),param);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (CostMate tMate : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("materialId", tMate.getCostMateId()); // 设备ID
			map.put("materialName", tMate.getMateName()); // 设备名称
			map.put("specTypeCd", tMate.getSpecTypeCd()); // 特殊类型
			String totalFloatRate = null;
			if(tMate.getTotalFloatRate() != null){
				if(StringUtils.isNotBlank(tMate.getTotalFloatRate().toString())){
					totalFloatRate = tMate.getTotalFloatRate().divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				}else{
					totalFloatRate = "0";
				}
			}else{
				totalFloatRate = "0";
			}
			map.put("totalFloatRate", totalFloatRate); // 总价浮动比例
			String lowPrice = "";
			String highPrice = "";
			if(tMate.getLowPrice() != null){
				lowPrice = tMate.getLowPrice().toString();
			}
			if(tMate.getHighPrice() != null){
				highPrice = tMate.getHighPrice().toString();
			}
			map.put("lowPrice", lowPrice); // 最低价
			map.put("highPrice", highPrice); // 最高价
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}
	
	/**
	 * 计算设备型号的实时价格(战略网上下单)
	 * 
	 * @param costMateId 设备ID
	 * @param currentPrice 实时价格
	 * @param price 当前型号的录入价格
	 * @return
	 * @throws Exception
	 */
	public String getCurrPrice() throws Exception{
		String costMateId =Struts2Utils.getParameter("costMateId");
		String currPrice =Struts2Utils.getParameter("currentPrice");
		String price =Struts2Utils.getParameter("price");
		BigDecimal resultPrice = new BigDecimal(0);
		if (StringUtils.isNotBlank(costMateId)) {
			
			CostMate mate = costMateManager.getEntity(costMateId);
			if (mate != null) {
				BigDecimal rate = mate.getFloatRate(); //上下浮动比列(0.08)
				BigDecimal basePrice = mate.getBasePrice(); // 均单价
			    BigDecimal lowPrice = mate.getLowPrice(); // 最低价
			    BigDecimal highPrice = mate.getHighPrice(); //最高价
			    
			    BigDecimal floatRate = new BigDecimal(0);
			    BigDecimal currentPrice = new BigDecimal(0); 
			    BigDecimal priceSingle = new BigDecimal(0);
			    if (rate != null) {
			    	floatRate = rate.divide(new BigDecimal(100));
			    }
			    if (lowPrice == null) {
			    	basePrice = new BigDecimal(0);
			    }
			    if (highPrice == null) {
			    	highPrice = new BigDecimal(0);
			    }
			    if (StringUtils.isNotBlank(currPrice)) {
			    	currentPrice= BigDecimal.valueOf(Double.valueOf(currPrice)); //输入的时价
			    }
			    if (StringUtils.isNotBlank(price)) {
			    	 priceSingle = BigDecimal.valueOf(Double.valueOf(price)); //设备型号的录入价
			    }
			    //判断输入的时价是否在设备的最低价和最高价之间
			    if ((currentPrice.compareTo(lowPrice)==1||currentPrice.compareTo(lowPrice)==0) 
			    		&& (currentPrice.compareTo(highPrice)==-1||currentPrice.compareTo(highPrice)==0)) {
			    	//计算公式 ：实时价格=(1+(时价-均单价)/1000*上下浮动比列)*型号的录入价格
			    	resultPrice = (new BigDecimal(1).add(((currentPrice.subtract(basePrice)).divide(new BigDecimal(1000))).multiply(floatRate))).multiply(priceSingle);
			    	BigDecimal ret = resultPrice.setScale(00, BigDecimal.ROUND_HALF_UP); //结果四舍五入(不要保留小数点)
			    	Struts2Utils.renderText(ret.toString());
			    }else{
			    	Struts2Utils.renderText("error");
			    }
			}
		}
		return null;
	}
	
	/**
	 * 根据“领用单位合同号”得到该合同的最早导入战略库的预算调整量
	 * 	(战略网上下单)
	 * @paramt akeUnitContNo:领用单位合同号
	 * @return
	 */
	public String getFirstBudgetAdjustNum(){
		String takeUnitContNo = Struts2Utils.getParameter("takeUnitContNo");
		if (StringUtils.isNotBlank(takeUnitContNo)) {
			CostStrageMate strageMate = costStrageMateManager.getStrageMateByTakeUnitContNo(takeUnitContNo);
			if (strageMate != null) {
				BigDecimal budgetAdjustNum = strageMate.getBudgetAdjustNum();
				Struts2Utils.renderText(budgetAdjustNum.toString());
			}
		}
		return null;
	}
	
	/**
	 * 导入页面
	 * @return
	 * @throws Exception
	 */
	public String costMateImport() throws Exception{
		return "import";
	}

	/**
	 * 导入Excel设备、型号、抬头等数据
	 * 
	 * @param parentId 类型ID
	 * @param uploadFileName 文件名称
	 * @return
	 */
	public String importExlPoi() throws Exception{
		
		if(upload !=null && StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(uploadFileName)){
			
			CostMateModule module = costMateModuleManager.getEntity(parentId);
			
			long begin = new Date().getTime();
			Workbook excelWorkbook = null;
			Sheet excleSheet = null;
			Row excelRow = null;
			Cell excelCell = null;
			try {
				int index = uploadFileName.lastIndexOf(".");
				String str = uploadFileName.substring(index);
				if(".xlsx".equals(str)){//支持excel 2007的读取
					excelWorkbook = new XSSFWorkbook(new FileInputStream(upload));
				}else if(".xls".equals(str)){ //支持excel 2003的读取
					excelWorkbook = new HSSFWorkbook(new FileInputStream(upload));
				}else{
					Struts2Utils.renderText(",error,您导入的不是Excel文件,");
					return null;
				}
				List<String> mateNamelList = new ArrayList<String>();
				
				// sheet总数
				int sheetCount = excelWorkbook.getNumberOfSheets(); 
				//验证格式
				String validateStr = validateExcel(sheetCount, excleSheet, excelWorkbook, excelRow, excelCell, mateNamelList);
				//保存数据
				if (StringUtils.isNotBlank(validateStr)) {
					saveExcel(sheetCount, excleSheet, excelWorkbook, excelRow, excelCell, module);
				}
				long end = new Date().getTime();
				log.info("导入用时-POI：" + (end - begin) + "毫秒");
				Struts2Utils.renderText(",success," + (end - begin) / 1000 + ",");
			}catch (Exception e) {
				e.printStackTrace();
				Struts2Utils.renderText(",error," + "未导入成功!" + ", " + e + ",");
			} finally{
				upload.delete();
			}
		}
		return null;
	}
	/**
	 * 验证excel
	 * @param sheetCount sheet总个数
	 * @param excelSheet sheet对象
	 * @param excelWorkbook workbook对象
	 * @param excelRow cellRow对象
	 * @param excelCell cell对象
	 * @param mateNamelList  材料/设备名称类表
	 * @return
	 */
	private String validateExcel(int sheetCount,Sheet excelSheet,Workbook excelWorkbook,Row excelRow,Cell excelCell,List<String> mateNamelList){
		
		
		//遍历sheet
		for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
			
			int priceColNum = -1;
			excelSheet = excelWorkbook.getSheetAt(sheetIndex); //得到一个sheet
			int countRow = excelSheet.getPhysicalNumberOfRows(); //总行数
			
			//遍历行
			for(int row = 0; row < countRow; row++) {
				List<String> colNameList = new ArrayList<String>();
				int fColCount = 0; //f属性列统计
				excelRow = excelSheet.getRow(row); //得到一行
				int totalColNum = excelRow.getLastCellNum(); //总列数
				
				//遍历列
				for (int tColNum = 1; tColNum < totalColNum; tColNum++) {
					excelCell = excelRow.getCell(tColNum); //得到一个单元格
					if (excelCell != null) {
						
						String cellValue = this.getCellValue(excelCell).trim(); //单元格的值
						if(excelSheet.isColumnHidden(tColNum)){ //判断excel隐藏列
							continue;
						}
						
						//验证第一行(即材料/设备名称)
						if (row == 0 && tColNum == 1) {
							Cell cell = excelRow.getCell(0);
							String value = this.getCellValue(cell).trim();
							if(StringUtils.isBlank(value)){
								Struts2Utils.renderText(",error, 未导入成功，请于第"+(sheetIndex+1)+"个工作表(Sheet)填写设备名称!,");
								return null;
							}
							
							//检查材料/设备名称是否已存在
							List<CostMate> mateList = costMateManager.checkMateName(value);
							if (mateList != null && mateList.size() > 0) {
								Struts2Utils.renderText(",error, 未导入成功，第"+(sheetIndex+1)+"个工作表(Sheet)设备名称数据库中已存在!,");
								return null;
							}
							if (mateNamelList.contains(value)) {
								Struts2Utils.renderText(",error, 未导入成功，请检查第"+(sheetIndex+1)+"个工作表(Sheet)设备名称是否与其他相同!,");
								return null;
							}
							mateNamelList.add(value);
						}
						
						//验证第二行的表头
						if(row == 1){ 
							if(excelCell.getCellType() == HSSFCell.CELL_TYPE_BLANK || StringUtils.isBlank(getCellValue(excelCell))){
								Struts2Utils.renderText(",error, 未导入成功，请于第"+(sheetIndex+1)+"个工作表第"+(row+1)+"行第"+BisConstants.letters.get(tColNum+1)+"列填写表头名称!,");
								return null;
							}

							//设置列头属性(规则：材料设备名称、规格、型号、价格 3个属性在excel的前3列)
							if(this.isContainMap(cellValue)){
								if(PRICE.equals(cellValue)) {
									priceColNum = tColNum;
								}
							}else{
								fColCount ++;
								//检查列名是否重复
								if (colNameList.contains(cellValue) && fColCount <=DB_FIELD_COUNT ) {//允许30个列
									Struts2Utils.renderText(",error, 未导入成功，请于第"+(sheetIndex+1)+"个工作表第"+(row+1)+"行第"+BisConstants.letters.get(tColNum+1)+"列检查列头名称是否相同!,");
									return null;
								}
								colNameList.add(cellValue);
							}
							continue;
						}else{
							//检查价格是否为数字类型
							if(priceColNum != -1 && tColNum == priceColNum){
								
								int cellType = excelCell.getCellType();
								if( cellType != HSSFCell.CELL_TYPE_BLANK 
										&& StringUtils.isNotBlank(cellValue)){
									//数字或公式
									if(cellType != HSSFCell.CELL_TYPE_NUMERIC && cellType != HSSFCell.CELL_TYPE_FORMULA){
										Struts2Utils.renderText(",error, 未导入成功，请于第"+(sheetIndex+1)+"个工作表第"+(row+1)+"行第"+BisConstants.letters.get(tColNum+1)+"列检查价格是否为数字!,");
										return null;
									}
								}
							}
						}
					}
				}
			}
		}
		return "success";
	}
	/**
	 * 保存excel数据
	 * 
	 * @param sheetCount sheet总数
	 * @param excleSheet sheet对象
	 * @param excelWorkbook  workbook对象
	 * @param excelRow 行
	 * @param excelCell
	 */
	private void saveExcel(int sheetCount,Sheet excleSheet,Workbook excelWorkbook,Row excelRow,Cell excelCell, CostMateModule module){
		
		String tmpModuleName = module.getModuleName();
		CostMate costMate = null;
		for (int i = 0; i < sheetCount; i++) {
			costMate = new CostMate();
//			costMate.setseq //TODO:sequenceNo
			costMate.setSpecTypeCd("0");//特殊类型：0-其他 1-电缆

			if("电缆".equals(tmpModuleName)){
				costMate.setSpecTypeCd("1");//特殊类型：0-其他  1-电缆
				costMate.setFomuDesc("(1+(时价-均单价)/1000*上下浮动比例)*型号价格");//公式说明
				//以上海有色金属网现货行情1#电解铜铜均单价 55000 元/吨为基准单价，铜单价在 45000 -  70000  区间每上下浮动1000元/吨，则线缆价格在以上报价的基础上上下浮动比例为：  1.5   %(线缆单价已含运输费用)
				costMate.setHighPrice(new BigDecimal(70000));
				costMate.setLowPrice(new BigDecimal(45000));
				costMate.setBasePrice(new BigDecimal(55000));
				costMate.setFloatRate(new BigDecimal(1.5));//1.5%
			}
			else if("停车收费系统".equals(tmpModuleName)){
				costMate.setSpecTypeCd("0");//特殊类型：0-其他 1-电缆
				costMate.setCalcTypeCd("3");//3-浮动公式
				costMate.setTotalFloatRate(new BigDecimal(8));//浮动:8%
			}

			//关键字1
			int groupNmmeColNum = -1;
			//关键字2
			int groupNmmeColNum2 = -1;
			//关键字3
			int groupNmmeColNum3 = -1;
			//关键字4
			int groupNmmeColNum4 = -1;
			//设备名称
			int materialNameColNum = -1;
			//型号
			int modelNameColNum = -1;
			//规格
			int specNameColNum = -1;
			//单价
			int priceColNum = -1;
			//行
			int memoColNum = -1;
			

			//含万元你的列
			List<String> milionColList = new ArrayList<String>();
			
			boolean priceMillionFlg = false;
			
			//自定义咧
			int indexCount = 0;
			
			//存放列头
			List<CostMateCol> costMateColList = new ArrayList<CostMateCol>();
			//存放型号
			List<CostMatePrice> costMatePriceList = new ArrayList<CostMatePrice>();
			excleSheet = excelWorkbook.getSheetAt(i); //得到一个sheet
			int countRowNum = excleSheet.getPhysicalNumberOfRows(); //总行数

			if(countRowNum >0){
				
				//遍历行
				for(int rowNum = 0; rowNum < countRowNum; rowNum++) {
					
					int fColCount = 0; //自定义f属性列统计
					excelRow = excleSheet.getRow(rowNum); //得到一行
					int rowColCount = excelRow.getLastCellNum(); //总列数
					CostMatePrice tmpMatePrice = new CostMatePrice();
					
					//临时存放累加的自定义咧(数字)
					int setValColNum = 0;
					
					//遍历列
					for (int col = 1; col < rowColCount; col++) {
						excelCell = excelRow.getCell(col); //得到一个单元格
						if (excelCell != null) {
							String cellValue = this.getCellValue(excelCell).trim().replace(" ", "").replace(" ", ""); //单元格的值,一定要去空格
							if(excleSheet.isColumnHidden(col)){ //判断excel隐藏列
								continue;
							}
							//第一行：保存设备名称
							if (rowNum == 0) {
								if(col == 1){
									Cell cell = excelRow.getCell(0);
									//若无标题，跳过
									String value = this.getCellValue(cell).trim();
									costMate.setParentModuleId(parentId);
									costMate.setMateName(value);
									costMate.setEnableFlg(Long.valueOf(1));
									costMate.setCalcTypeCd("1");
								}
							}
							//第二行：保存材料设备列头
							else if(rowNum == 1){

								if (cellValue.indexOf(GROUP_NAME) != -1){
									groupNmmeColNum = col;
									indexCount++;
								}else if (cellValue.indexOf(GROUP_NAME2) != -1){
									groupNmmeColNum2 = col;
									indexCount++; 
								}else if (cellValue.indexOf(GROUP_NAME3) != -1){
									groupNmmeColNum3 = col;
									indexCount++; 
								}else if (cellValue.indexOf(GROUP_NAME4) != -1){
									groupNmmeColNum4 = col;
									indexCount++; 
								}
								else if (MATERIAL_NAME.equals(cellValue) || MATERIAL_NAME2.equals(cellValue)) {
									materialNameColNum = col;
									indexCount++;
								}else if (SPEC_NAME.equals(cellValue)) {
									specNameColNum = col;
									indexCount++;
								}else if (MODEL_NAME.equals(cellValue)) {
									modelNameColNum = col;
									indexCount++;
								}else if (PRICE.equals(cellValue) 
										|| PRICE_MILLION.equals(cellValue)
										) {
									priceColNum = col;
									indexCount++;
									if( PRICE_MILLION.equals(cellValue)){
										priceMillionFlg = true;
									}
								}else if(MEMO_DESC.equals(cellValue)){
									memoColNum = col;
									indexCount ++;
								}
								
								//设置列头属性(规则：材料设备名称、规格、型号、价格3个属性在excel的前3列)
								//自定义字段
								if(!this.isContainMap(cellValue)){
									log.error(">>>>>>>>>>>>>>>>> 行"+ rowNum +", 列" + fColCount);
									fColCount ++;
									setValColNum ++;
									
									
									if (fColCount <= DB_FIELD_COUNT) { //最多只设置30个属性，超过30的列的属性忽略
										
										CostMateCol mateCol = new CostMateCol();
//										int setCol = col;
//										if (indexCount != 0) {
//											setCol = col-indexCount;
//										}
										
										if(setValColNum < 10){ //colField值为f01~f30
											mateCol.setColField("f0"+(setValColNum));
										}else{
											mateCol.setColField("f"+(setValColNum));
										}

										if(cellValue.indexOf(EXT_FLG) != -1){
											mateCol.setExtFlg("1");//是否扩展字段默认：0-否 //1-是
											mateCol.setExtPrice("0");//是否价格字段默认：0-否
											mateCol.setColDataTypeCd("1");//1-varchar2
											cellValue = cellValue.replace(EXT_FLG, "");//清除
										}
										else if(cellValue.indexOf(EXT_PRICE) != -1){
											mateCol.setExtFlg("1");//是否扩展字段默认：0-否 //1-是
											mateCol.setExtPrice("1");//1-扩展单价
											mateCol.setColDataTypeCd("3");//列数据类型:1-VARCHAR2 2-integer 3-number(18,2)
											
											//序号存起来,设置price的时，自动累加 *10000
											if(cellValue.indexOf("万元")!= -1){
												milionColList.add(String.valueOf(setValColNum));
											}
											cellValue = cellValue.replace(EXT_PRICE, "");//清除
										}
										else {
											mateCol.setExtFlg("0");//是否扩展字段默认：0-否 //1-是
											mateCol.setExtPrice("0");//是否价格字段默认：0-否
											mateCol.setColDataTypeCd("1");//1-varchar2
										}
										
										
										mateCol.setColName(cellValue);//列名称,放在这里可以去除"[可选单价]", "[可选]"
										mateCol.setLevelCd(new BigDecimal(1));//属性级别:1-普通 2-高级 3-核心 (暂不使用)
										mateCol.setSequenceNo(Long.valueOf(setValColNum));

										costMateColList.add(mateCol);
									}
								}
								continue;
							}else{

								if(col == groupNmmeColNum){
									tmpMatePrice.setGroupName(formatDefault(cellValue));
								}else if(col == groupNmmeColNum2){
									tmpMatePrice.setGroupName2(formatDefault(cellValue));
								}else if(col == groupNmmeColNum3){
									tmpMatePrice.setGroupName3(formatDefault(cellValue));
								}else if(col == groupNmmeColNum4){
									tmpMatePrice.setGroupName4(formatDefault(cellValue));
								}
								
								else if(col == materialNameColNum){
									tmpMatePrice.setMaterialName(formatDefault(cellValue));
								}else if(col == specNameColNum){
									
									if(cellValue != null && cellValue.endsWith(".0")){
										cellValue = cellValue.replace(".0", "");
									} 
									
									tmpMatePrice.setSpecName(formatDefault(cellValue));
								}else if(col == modelNameColNum){
									tmpMatePrice.setModelName(formatDefault(cellValue));
								}else if(col == priceColNum){

									if(StringUtils.isNotBlank(cellValue)){
										if(priceMillionFlg){
											tmpMatePrice.setPrice(BigDecimal.valueOf(Double.valueOf(cellValue).doubleValue() * 10000));
										}else{
											tmpMatePrice.setPrice(BigDecimal.valueOf(Double.valueOf(cellValue)));
										}
									}
									
								}else if(col == memoColNum){
									tmpMatePrice.setMemoDesc(cellValue);
								}else{
									//自定义列值
									setValColNum ++;
									
//									int setCol = col;
//									if (indexCount != 0) {
//										//if (specNameIndex > col || moduleNameIndex > col ||priceIndex>col) {
//											//setCol = (col-indexCount)+1;
//										//}else{
//											//setCol = (col-indexCount)+1;
//											//  计算F列数  update by liuzhihui 2012-05-10
//											setCol = col-indexCount;
//										//}
//									}
									if (fColCount <= DB_FIELD_COUNT) {//f属性超过 30 的列忽略
										
										//若是万元列
										if(milionColList.contains(String.valueOf(setValColNum))){
											if(cellValue != null){
												if(StringUtils.isNotBlank(cellValue)){
													cellValue = cellValue.replace(",", "");
													
													double d = Double.valueOf(cellValue).doubleValue();
													cellValue = String.valueOf(d * 10000);
													
												}else{
													cellValue = null;
												}
											}
										}
										
										
//										//保存自定义列的值
										this.setCostMatePriceF(tmpMatePrice, cellValue, setValColNum);
									}
								}
							}
						}
						if(rowNum > 1){
							tmpMatePrice.setEnableFlg(Long.valueOf(1));
							costMatePriceList.add(tmpMatePrice);
						}
					}
				}
				//保存设备
				costMateManager.saveCostMate(costMate, costMateColList, costMatePriceList);
				
			}
		}
	}
	
	private String formatDefault(String str){
		return StringUtils.isBlank(str)?"-":str.trim();
	}
	
	//获取单元格的值
	public String getCellValue(Cell cell){

		String rtnVal = "";
		if(cell != null) { 
			int cellType = cell.getCellType();
			if( cellType == HSSFCell.CELL_TYPE_STRING) {
				rtnVal = cell.getStringCellValue();
			} else if(cellType == HSSFCell.CELL_TYPE_BOOLEAN) {
				rtnVal = String.valueOf(cell.getBooleanCellValue());
			} else if(cellType == HSSFCell.CELL_TYPE_FORMULA){
				try{
					rtnVal = String.valueOf(cell.getNumericCellValue());
				}catch(Exception e){
					rtnVal = String.valueOf(cell.getStringCellValue());
				}
			}
			else if(cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				rtnVal = String.valueOf(cell.getNumericCellValue());
			}
		}
		return rtnVal;
	}
	
	//设置CostMatePrice: f01~f30属性的值
	public void setCostMatePriceF(CostMatePrice matePrice,String value,int col){
		
		String cellValue = null;
		if(StringUtils.isNotBlank(value)){
			if(value.endsWith(".0")){
				cellValue = value.replace(".0", "");
			}else{
				cellValue = value;
			}
		}else{
			
		}
		
		if(col == 1){
			matePrice.setF01(cellValue);
		}else if(col == 2){
			matePrice.setF02(cellValue);
		}else if(col == 3){
			matePrice.setF03(cellValue);
		}else if(col == 4){
			matePrice.setF04(cellValue);
		}else if(col == 5){
			matePrice.setF05(cellValue);
		}else if(col == 6){
			matePrice.setF06(cellValue);
		}else if(col == 7){
			matePrice.setF07(cellValue);
		}else if(col == 8){
			matePrice.setF08(cellValue);
		}else if(col == 9){
			matePrice.setF09(cellValue);
		}else if(col == 10){
			matePrice.setF10(cellValue);
		}else if(col == 11){
			matePrice.setF11(cellValue);
		}else if(col == 12){
			matePrice.setF12(cellValue);
		}else if(col == 13){
			matePrice.setF13(cellValue);
		}else if(col == 14){
			matePrice.setF14(cellValue);
		}else if(col == 15){
			matePrice.setF15(cellValue);
		}else if(col == 16){
			matePrice.setF16(cellValue);
		}else if(col == 17){
			matePrice.setF17(cellValue);
		}else if(col == 18){
			matePrice.setF18(cellValue);
		}else if(col == 19){
			matePrice.setF19(cellValue);
		}else if(col == 20){
			matePrice.setF20(cellValue);
		}else if(col == 21){
			matePrice.setF21(cellValue);
		}else if(col == 22){
			matePrice.setF22(cellValue);
		}else if(col == 23){
			matePrice.setF23(cellValue);
		}else if(col == 24){
			matePrice.setF24(cellValue);
		}else if(col == 25){
			matePrice.setF25(cellValue);
		}else if(col == 26){
			matePrice.setF26(cellValue);
		}else if(col == 27){
			matePrice.setF27(cellValue);
		}else if(col == 28){
			matePrice.setF28(cellValue);
		}else if(col == 29){
			matePrice.setF29(cellValue);
		}else if(col == 30){
			matePrice.setF30(cellValue);
		}
	}
	
	
	/**
	 * 设备类型Map
	 * @return
	 */
	public Map<String, String> getMapCostMateModule() {
		Map<String, String> mapCostMateModule = new LinkedHashMap<String, String>();
		List<CostMateModule> costMateModuleList = costMateModuleManager.getAll();
		for (CostMateModule t : costMateModuleList) {
			mapCostMateModule.put(t.getCostMateModuleId(), t.getModuleName());
		}
		return mapCostMateModule;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	@Override
	public String deleteBatch() throws Exception {
		return null;
	}
	
	@Override
	public CostMate getModel() {
		return entity;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = costMateManager.getEntity(getId());
		} else {
			entity = new CostMate();
		}
		parentModulePathName = costMateModuleManager.getBubblePath(entity.getParentModuleId());
	}
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public CostMate getEntity() {
		return entity;
	}
	public void setEntity(CostMate entity) {
		this.entity = entity;
	}
	public CostMateModule getCostMateModule() {
		return costMateModule;
	}
	public void setCostMateModule(CostMateModule costMateModule) {
		this.costMateModule = costMateModule;
	}

	public String getInputFlag() {
		return inputFlag;
	}

	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}

	public List<CostMate> getCostMateList() {
		return costMateList;
	}

	public void setCostMateList(List<CostMate> costMateList) {
		this.costMateList = costMateList;
	}

	public String[] getParentModuleId() {
		return parentModuleId;
	}

	public void setParentModuleId(String[] parentModuleId) {
		this.parentModuleId = parentModuleId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<EntityVO> getParentIdList() {
		return parentIdList;
	}

	public void setParentIdList(List<EntityVO> parentIdList) {
		this.parentIdList = parentIdList;
	}

	public List<CostStrageMateDetail> getCostStrageMateDetailList() {
		return costStrageMateDetailList;
	}

	public void setCostStrageMateDetailList(
			List<CostStrageMateDetail> costStrageMateDetailList) {
		this.costStrageMateDetailList = costStrageMateDetailList;
	}

	public CostStrageMate getCostStrageMate() {
		return costStrageMate;
	}

	public void setCostStrageMate(CostStrageMate costStrageMate) {
		this.costStrageMate = costStrageMate;
	}

	public String getAuthTypeName() {
		return authTypeName;
	}

	public void setAuthTypeName(String authTypeName) {
		this.authTypeName = authTypeName;
	}

	public String getParentModulePathName() {
		return parentModulePathName;
	}

	public void setParentModulePathName(String parentModulePathName) {
		this.parentModulePathName = parentModulePathName;
	}
	
	public static void main(String[] args) {
		System.out.println("xx.0".endsWith(".0"));
		System.out.println("xx.00".endsWith(".0"));
	}
	/**
	 * 检查是否有材料设备 
	 * @return 1-可用 0-不可用
	 */
	public String validateHasMate(){
		
		String moduleId = Struts2Utils.getParameter("moduleId");
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("moduleId", moduleId);
		values.put("enableFlg", new Long(1));//可用
		String hql = " from CostMate t where t.parentModuleId = :moduleId and t.enableFlg = :enableFlg ";
		List<CostMate> list = costMateManager.find(hql, values);
		if(list == null || list.size() == 0){
			Struts2Utils.renderText("0");
		}else{
			StringBuffer sb = new StringBuffer();
			CostMate tmp = list.get(0);
			//若是电缆
			
			String tmpCalcTypeCd = tmp.getCalcTypeCd();
			String tmpSpecTypeCd = tmp.getSpecTypeCd();
			

			//定量 : calcTypeCd =1
			if("1".equals(tmpCalcTypeCd)){
				sb.append("");
			}
			//公式: calctypeCd =2
			else if("2".equals(tmpCalcTypeCd)){
				//电缆: specTypeCd = 1
				if("1".equals(tmpSpecTypeCd)){
					sb.append("( 铜价 必须介于 " + parseStr(String.valueOf(tmp.getLowPrice())) + " 和 " + parseStr(String.valueOf(tmp.getHighPrice()) + " 之间)"));
				}else{
					sb.append("( 公式自动计算 )");
				}
				
			}
			//总价浮动： calcTypeCd = 3
			else if("3".equals(tmpCalcTypeCd)){
				sb.append("");
			}
			
			
			Struts2Utils.renderText("1," + sb.toString());
		}
		return null;
	}
	private String parseStr(String str){
		return StringUtils.isBlank(str)?"":str.toString();
	}
}

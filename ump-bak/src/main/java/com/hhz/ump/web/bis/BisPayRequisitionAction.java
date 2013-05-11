package com.hhz.ump.web.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisFactManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.PayRequisitionVo;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.res.ResApproveInfoAction;
import com.hhz.ump.web.vo.VoFact;

@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
		"excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" })
public class BisPayRequisitionAction extends CrudActionSupport<PayRequisitionVo> {
	private String bisProjectId;
	private String bisProjectName;
	private VoFact voFact;
	private InputStream excelFile;// 导出Excel需要的参数
	private String excelFileName;
	private String floorNum;
	private String nameShop;
	private String storeNo;
	private String factYear;
	private String factMonth;
	private BigDecimal pageNo;
	private Page<PayRequisitionVo> voPayPage;
	@Autowired
	private BisFactManager bisFactManager;
	@Autowired
	private BisProjectManager bisProjectManager;

	/**
	 * 费用缴费单
	 */
	@Override
	public PayRequisitionVo getModel() {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
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

	@SuppressWarnings("static-access")
	@Override
	protected void prepareModel() throws Exception {
		String pageNo = Struts2Utils.getParameter("pageNo");
		String rows = Struts2Utils.getParameter("rows");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		} else {
			page.setPageSize(Integer.valueOf("30"));

		}
		page.setTotalCount(0);

		voFact = new VoFact();
		String tmp = Struts2Utils.getParameter("bisFactId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisFactId(tmp);
		}
		tmp = Struts2Utils.getParameter("bisTenantId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisTenantId(tmp.trim());
		}
		tmp = Struts2Utils.getParameter("bisFlatId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisFlatId(tmp);
		}
		tmp = Struts2Utils.getParameter("bisMultiId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisMultiId(tmp);
		}
		tmp = Struts2Utils.getParameter("bisProjectId");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setBisProjectId(tmp);
		}
		tmp = Struts2Utils.getParameter("factYear");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setFactYear(tmp);
		}
		tmp = Struts2Utils.getParameter("factMonth");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setFactMonth(tmp);
		}
		tmp = Struts2Utils.getParameter("chargeTypeCd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setChargeTypeCd(tmp);
		}
		tmp = Struts2Utils.getParameter("contLayOutCd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setContLayOutCd(tmp);
		}
		tmp = Struts2Utils.getParameter("creator");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setCreator(tmp);
		}
		tmp = Struts2Utils.getParameter("statusCd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setStatusCd(tmp);
		}
		tmp = Struts2Utils.getParameter("minMonth");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setMinMonth(tmp);
		}
		tmp = Struts2Utils.getParameter("maxMonth");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setMaxMonth(tmp);
		}
		tmp = Struts2Utils.getParameter("shopTypeCd");
		if (bisFactManager.notBlank(tmp)) {
			voFact.setShopTypeCd(tmp);
		}
		String factDate = Struts2Utils.getParameter("factDate");
		if (factDate != null && bisFactManager.notBlank(factDate)) {
			voFact.setFactDate(factDate);
		}
		String minMoney = Struts2Utils.getParameter("minMoney");
		if (bisFactManager.notBlank(minMoney)) {
			voFact.setMinMoney(new BigDecimal(minMoney));
		}
		String mustMoney = Struts2Utils.getParameter("mustMoney");
		if (bisFactManager.notBlank(mustMoney)) {
			voFact.setMustMoney(new BigDecimal(mustMoney));
		}
		String maxMoney = Struts2Utils.getParameter("maxMoney");
		if (bisFactManager.notBlank(maxMoney)) {
			voFact.setMaxMoney(new BigDecimal(maxMoney));
		}
		String money = Struts2Utils.getParameter("money");
		if (bisFactManager.notBlank(money)) {
			voFact.setMoney(new BigDecimal(money));
		}
		String overdue = Struts2Utils.getParameter("overdue");
		if (bisFactManager.notBlank(overdue)) {
			voFact.setOverdue(overdue);
		}
		String storeId = Struts2Utils.getParameter("storeId");
		if (bisFactManager.notBlank(storeId)) {
			voFact.setBisStoreId(storeId);
		}
		if (voFact.getMustOrFact() == 1) {

			// 实收日期
			String factInBegin = Struts2Utils.getParameter("factInBegin");
			if (bisFactManager.notBlank(factInBegin)) {
				voFact.setInDateBegin(factInBegin);
			}
			String factInEnd = Struts2Utils.getParameter("factInEnd");
			if (bisFactManager.notBlank(factInEnd)) {
				voFact.setInDateEnd(factInEnd);
			}
		} else {
			// 应收日期
			String mustInBegin = Struts2Utils.getParameter("mustInBegin");
			if (bisFactManager.notBlank(mustInBegin)) {
				voFact.setInDateBegin(mustInBegin);
			}
			String mustInEnd = Struts2Utils.getParameter("mustInEnd");
			if (bisFactManager.notBlank(mustInEnd)) {
				voFact.setInDateEnd(mustInEnd);
			}
		}
		String checkUserCd = Struts2Utils.getParameter("checkUserCd");
		if (bisFactManager.notBlank(checkUserCd)) {
			voFact.setCheckUserCd(checkUserCd);
		}
		String buildingNum = Struts2Utils.getParameter("buildingNum");
		if (bisFactManager.notBlank(buildingNum)) {
			voFact.setBuildingNum(buildingNum);
		}
		String bisContId = Struts2Utils.getParameter("bisContId");
		if (bisFactManager.notBlank(bisContId)) {
			voFact.setBisContId(bisContId);
		}
	}

	/**
	 * 导出缴费单
	 * 
	 * @throws Exception
	 */
	public String exportPayRequisitionFile() throws Exception {
		prepareModel();
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		excelFile = buildTemplateExcel();
		excelFileName = new String(bisProjectName.getBytes("GBK"), "ISO8859-1");

		return "export";
	}

	/**
	 * 生成文件
	 * 
	 * @throws IOException
	 */
	private InputStream buildTemplateExcel() throws IOException {
		// 读取模板
		InputStream isTemplate = getTemplet(bisProjectId);
		// 生成excle
		return initJxlsInputStream(isTemplate, searchPayRequisitions());
	}

	/**
	 * 生成结果文件
	 */
	private InputStream initJxlsInputStream(InputStream isTemplate, List voList) throws IOException {
		HSSFWorkbook hssfWorkbook = transformXLS(isTemplate, voList);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		hssfWorkbook.write(bos);

		byte[] data = bos.toByteArray();
		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}

	public HSSFWorkbook transformXLS(InputStream is, List beanList) throws ParsePropertyException {
		HSSFWorkbook hssfWorkbook = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(is);
			hssfWorkbook = new HSSFWorkbook(fs);
			hssfWorkbook = transformWorkbook(hssfWorkbook, beanList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hssfWorkbook;
	}

	public HSSFWorkbook transformWorkbook(HSSFWorkbook hssfWorkbook, List beanList) {
		// 处理list-》map key为list中Object-》field
		HSSFSheet se = hssfWorkbook.getSheetAt(0);
		// 处理合并单元格
		int numMergedRegions = se.getNumMergedRegions();

		Map<String, List<String>> properties = processProperty(beanList, se);
		int overRow = se.getLastRowNum() - se.getFirstRowNum() + 1;// 跳过行 例如
																	// 表格3行，遍历3次，那cell的行值为
																	// 0、3、6,列值相同
		int lastRowNume = se.getLastRowNum();
		for (int i = se.getFirstRowNum(); i <= lastRowNume; i++) {
			HSSFRow hssfRow = se.getRow(i);

			if (hssfRow != null) {
				for (int j = hssfRow.getFirstCellNum(); j <= hssfRow.getLastCellNum(); j++) {
					HSSFCell cell = hssfRow.getCell(j);
					if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						String value = cell.getRichStringCellValue().getString();
						value = processCellKeyValue(value);
						if (null != properties.get(value)) {
							if (properties.get(value).size() == 0) {
								exposePOICellsCopy(se, beanList.size(), cell, overRow);
							} else {
								exposePOICells(se, properties.get(value), cell, overRow);
							}
						}
					}
				}
			}
		}
		for(int i =1 ; i<=beanList.size(); i++){
			se.setRowBreak(i*overRow-1);
		}
		processMergeRegions(se, overRow, beanList.size(), numMergedRegions);
		processRemoveRow(se,se.getFirstRowNum(),lastRowNume);
		return hssfWorkbook;
	}
	private void processRemoveRow(HSSFSheet sheet,int fromRow,int lastRow){
		short firtstRowHeight = sheet.getRow(0).getHeight();
		short secondRowHeight = sheet.getRow(1).getHeight();
		sheet.shiftRows(lastRow+1, sheet.getLastRowNum(),fromRow-lastRow-1);
		sheet.getRow(0).setHeight(firtstRowHeight);
		sheet.getRow(1).setHeight(secondRowHeight);
	}
	private void processMergeRegions(HSSFSheet sheet, int overRow, int listNum, int numMergedRegions) {
		for (int i = 0; i < numMergedRegions; i++) {
			Region fromRegion = sheet.getMergedRegionAt(i);
			for (int j = 0; j <= listNum; j++) {
				int rowFrom = fromRegion.getRowFrom() + (j * overRow);
				int rowTo = fromRegion.getRowTo() + (j * overRow);
				sheet.addMergedRegion(new Region(rowFrom, fromRegion.getColumnFrom(), rowTo, fromRegion.getColumnTo()));
				if (sheet.getRow(rowFrom) == null) {
					continue;
				}
				HSSFCell source = sheet.getRow(rowFrom).getCell(fromRegion.getColumnFrom());
				for (int k = fromRegion.getColumnFrom() + 1; k <= fromRegion.getColumnTo(); k++) {
					HSSFCell dest = sheet.getRow(rowFrom).getCell(k);
					if (null == dest) {
						dest = sheet.getRow(rowFrom).createCell(k);
					}
					convertHSSFCell(source, dest);
				}
			}
		}
	}

	private void exposePOICellsCopy(HSSFSheet sheet, int listNum, HSSFCell cellTemplate, int overRow) {
		int row = cellTemplate.getRowIndex();
		int col = cellTemplate.getColumnIndex();
		int nextRow = row;
		for (int i = 0; i < listNum; i++) {
			nextRow = (1 + i) * overRow + row;
			HSSFCell processCell = null;
			if (null == sheet.getRow(nextRow)) {
				sheet.createRow(nextRow).setHeight(cellTemplate.getRow().getHeight());
			}
			if (null == sheet.getRow(nextRow).getCell(col)) {
				sheet.getRow(nextRow).createCell(col);
			}

			processCell = sheet.getRow(nextRow).getCell(col);
			processCell.setCellValue(getCellKeyValue(cellTemplate.getStringCellValue(), ""));
			convertHSSFCell(cellTemplate, processCell);
		}
	}

	private void exposePOICells(HSSFSheet sheet, List<String> fieldValueList, HSSFCell cellTemplate, int overRow) {
		int row = cellTemplate.getRowIndex();
		int col = cellTemplate.getColumnIndex();
		int nextRow = row;
		for (int i = 0; i < fieldValueList.size(); i++) {
			nextRow = (1 + i) * overRow + row;
			if (null == sheet.getRow(nextRow)) {
				sheet.createRow(nextRow).setHeight(cellTemplate.getRow().getHeight());
			}
			if (null == sheet.getRow(nextRow).getCell(col)) {
				sheet.getRow(nextRow).createCell(col);
			}
			HSSFCell processCell = sheet.getRow(nextRow).getCell(col);
			processCell.setCellValue(getCellKeyValue(cellTemplate.getStringCellValue(), fieldValueList.get(i)));
			convertHSSFCell(cellTemplate, processCell);

		}
	}

	private void convertHSSFCell(HSSFCell source, HSSFCell dest) {
		if (null == source)
			return;
		if (null == dest)
			return;
		dest.setCellStyle(source.getCellStyle());
		dest.setCellType(source.getCellType());
	}
	private String processCellKeyValue(String value){
		String key = value;
		if(value.indexOf("${")>=0){
			key = value.substring(value.indexOf("${"),(value.indexOf("}")+1));
		}
		return key;
	}
	private String getCellKeyValue(String value,String dest){
		if(value.indexOf("${")>=0)
			return value.substring(0, value.indexOf("${"))+dest+value.substring((value.indexOf("}")+1));
		else
			return value;
	}
	private Map<String, List<String>> processProperty(List beanList, HSSFSheet se) {
/*		cellKeyValue = new HashMap<String, String>();*/
		Map<String, List<String>> properties = new HashMap<String, List<String>>();
		for (int i = se.getFirstRowNum(); i <= se.getLastRowNum(); i++) {
			HSSFRow hssfRow = se.getRow(i);
			if (hssfRow != null) {
				for (int j = hssfRow.getFirstCellNum(); j <= hssfRow.getLastCellNum(); j++) {
					HSSFCell cell = hssfRow.getCell(j);
					if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
						String value = cell.getRichStringCellValue().getString();
						String key = processCellKeyValue(value);
						if (null == properties.get(key)) {
							//cellKeyValue .put(key, value);
							List<String> propertValues = new ArrayList<String>();
							properties.put(key, propertValues);
						}
					}
				}
			}
		}
		for (Object bean : beanList) {
			Field[] fields = bean.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = "${" + fields[i].getName() + "}";
				if (properties.keySet().contains(name)) {
					List<String> propertValues = properties.get(name);
					String value;
					try {
						Object o = Util.invokeMethod(bean, fields[i].getName(), null);
						if(Date.class.isInstance(o)){
								value = DateUtil.parseDateToString((Date)o);
						}else{
							value = String.valueOf(o);
						}
						if (StringUtils.isBlank(value)) {
							continue;
						}
						propertValues.add(value);
						properties.put(name, propertValues);
					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}
				}
			}
		}
		return properties;
	}

	/**
	 * 获取该项目缴费单模板
	 */
	private InputStream getTemplet(String bisProjectId) {
		String templateName = "";
		if (bisProjectId.equals("")) {
			templateName = "BisPayRequisitionFuZhou.xls";
		}
		templateName = "BisPayRequisitionFuZhou.xls";

		InputStream isTemplate = ResApproveInfoAction.class.getClassLoader().getResourceAsStream(
				"jxlsTemplates/" + templateName);
		return isTemplate;
	}

	private List<PayRequisitionVo> searchPayRequisitions() {
		PayRequisitionVo vo = new PayRequisitionVo();
		vo.setBisProjectId(bisProjectId);
		if (StringUtils.isNotBlank(nameShop)) {
			vo.setShopName(nameShop);
		}
		if (StringUtils.isNotBlank(storeNo)) {
			vo.setStoreNo(storeNo);
		}
		if (StringUtils.isNotBlank(factYear)) {
			vo.setYear(factYear);
		}
		if (StringUtils.isNotBlank(factMonth)) {
			vo.setMonth(factMonth);
		}
		if (null!=pageNo) {
			vo.setPageNo(pageNo);
		} else {
			vo.setPageNo(new BigDecimal(1));
		}
		long start = System.currentTimeMillis();
		voPayPage = bisFactManager.searchPayRequisitions(vo);
		long end_dime = System.currentTimeMillis();
		logger.info("////////////////导出缴费通知：" + (end_dime - start)/1000.00 +" 秒!");
		return voPayPage.getResult();
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getBisProjectName() {
		return bisProjectName;
	}

	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

	public VoFact getVoFact() {
		return voFact;
	}

	public void setVoFact(VoFact voFact) {
		this.voFact = voFact;
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

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public String getNameShop() {
		return nameShop;
	}

	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getFactYear() {
		return factYear;
	}

	public void setFactYear(String factYear) {
		this.factYear = factYear;
	}

	public String getFactMonth() {
		return factMonth;
	}

	public void setFactMonth(String factMonth) {
		this.factMonth = factMonth;
	}

	public Page<PayRequisitionVo> getVoPayPage() {
		return voPayPage;
	}

	public void setVoPayPage(Page<PayRequisitionVo> voPayPage) {
		this.voPayPage = voPayPage;
	}

	public BigDecimal getPageNo() {
		return pageNo;
	}

	public void setPageNo(BigDecimal pageNo) {
		this.pageNo = pageNo;
	}

}

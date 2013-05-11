package com.hhz.ump.web.bis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.bis.BisFlatManager;
import com.hhz.ump.dao.bis.BisFloorManager;
import com.hhz.ump.dao.bis.BisMultiManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisStoreManager;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.entity.bis.BisMulti;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisStore;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/bis")
@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
		"excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" })
@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
public class BisStoreCoordsAction extends ActionSupport {
	private static final long serialVersionUID = -4905629017584086924L;
	// 数据源
	String fileName;
	// 指定解析的工作薄
	Integer sheetNow = 0;
	// 指定开始解析行号
	int overlookno = 0;
	// 指定记录导入失败的列
	private int sheetLine = 2;
	@Autowired
	private BisStoreManager bisStoreManager;
	@Autowired
	private BisFloorManager bisFloorManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	@Autowired
	private BisMultiManager bisMultiManager;
	@Autowired
	private BisFlatManager bisFlatManager;
	
	private String bisProjectId;
	private String area;
	private String floorNum;
	private String bisFloorId;
	private String floorType;
	private String subFloorType;
	private BisStore entity;
	private BisFloor floor;
	private Map<String,String> mapFloor;
	private List<BisFloor> floors;
	private File importFact;

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public String importGuangGaoFloor(){
		String remark = Struts2Utils.getParameter("remark");
		String bigPicUrl = Struts2Utils.getParameter("bigPicUrl");
		String floorUrl = Struts2Utils.getParameter("floorUrl");
		String subFloorTypeName = Struts2Utils.getParameter("subFloorTypeName");
		bisFloorId = saveBisFloor(bisProjectId, remark, floorNum, subFloorType,subFloorTypeName,bigPicUrl,floorUrl).getBisFloorId();
		if(StringUtils.isNotBlank(fileName)) {
			importGuangGaoExl();
		}
		Struts2Utils.renderText("success");
		return null;
	}
	/**
	 * 导入广告图
	 */
	public String importGuangGaoExl() {
		floor = bisFloorManager.getEntity(bisFloorId);

		StringBuffer error = new StringBuffer();
		ResultVO result = new ResultVO(0);
		try {

			File inputXml = new File(fileName);
			SAXReader saxReader = new SAXReader();
			try {
				Document document = saxReader.read(inputXml);
				Element employees = document.getRootElement();
				for (Iterator i = employees.elementIterator(); i.hasNext();) {
					Element polygon = (Element) i.next();
					if("polygon".equals(polygon.getName())){
						if(!parseGg(polygon, bisProjectId,result)){
							error.append(result.mess).append(",");
						}
					}

				}
			} catch (DocumentException e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		if (error.equals("")) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("更新以下广告位坐标失败:" + error);
		}
		return null;
	}

	private boolean parseGg(Element polygon,String bisProjectId ,ResultVO result){
		String multiName = "" ;
		String coords ="";
		multiName = polygon.attributeValue("id");
		coords = polygon.attributeValue("points");
		result.mess=multiName;
		return searchUpdateMulti(bisProjectId, multiName, coords);
		
	}
	private boolean searchUpdateMulti(String bisProjectId,String multiName,String coords){
		BisMulti mul = 
			bisMultiManager.getMultiByPiAndName(bisProjectId, multiName);
		if(mul!=null){
			mul.setCoords(coords);
			bisMultiManager.updateMulti(mul.getBisMultiId(), coords,bisFloorId);
			return true;
		}
		return false;
	}
	private BisFloor saveBisFloor(String bisProjectId,String remark,String floorNum,String subFloorType,String subFloorTypeName,String bigPicUrl,String floorUrl){
		BisFloor floor = new BisFloor();
		floor.setSubFloorType(subFloorType);//
		floor.setSubFloorTypeName(subFloorTypeName);//
		floor.setFloorType("3");//广告图
		floor.setFloorNum(floorNum);
		floor.setRemark(remark);
		floor.setBuildingNum(remark);
		floor.setBigPicUrl(bigPicUrl);
		floor.setFloorUrl(floorUrl);
		
		BisProject project = new BisProject();
		project.setBisProjectId(bisProjectId);
		floor.setBisProject(project);
		 bisFloorManager.saveBisFloor(floor);
		 return floor;
	}

	private void updateBisStore(BisStore store, Cell[] cells) {
		// BisStore store =new BisStore();
		try {
			store.setSquareReal(new BigDecimal(cells[2].getContents()));// 建筑面积(测)
		} catch (Exception e) {

		}
		try {
			store.setInnerSquareReal(new BigDecimal(cells[3].getContents()));// 套内面积(测)
		} catch (Exception e) {

		}
		store.setSquare(new BigDecimal(cells[4].getContents()));// 建筑面积
		store.setInnerSquare(new BigDecimal(cells[5].getContents()));// 套内面积
		store.setPublicSquare(new BigDecimal(cells[6].getContents()));// 公摊面积
		store.setRentSquare(new BigDecimal(cells[7].getContents()));// 记租面积
		try {
			store.setSellPrice(new BigDecimal(cells[8].getContents()));// 销售单价
		} catch (Exception e) {

		}
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			store.setOpenDate(format.parse(cells[9].getContents()));// 开业日期
		} catch (Exception e) {
		}
		try {
			store.setRentStandard(new BigDecimal(cells[10].getContents()));// 租金标准
			//store.setRentStandard(cells[10].getContents());// 租金标准
		} catch (Exception e) {
		}
		store.setTenemStandard(new BigDecimal(cells[11].getContents()));// 物业标准
		store.setShopPosition(cells[12].getContents());// 商铺定位
		store.setLayoutCd(cells[13].getContents());// 规划业态
		store.setEquityNature(cells[14].getContents());// 产权性质
		try {
			store.setManagementStatus(cells[15].getContents());// 经营现状
		} catch (Exception e) {
		}
		try {
			store.setIfPractice(cells[16].getContents());// 是否开业
		} catch (Exception e) {
		}
		/*
		 * try{ store.setSplitStatus(cells[17].getContents());//是否拆分
		 * }catch(Exception e){ }
		 */
		store.setStatusCd("1");// 状态CD
		bisStoreManager.saveBisStore(store);
	}

	public void updateStore(ResultVO result, String bisProjectId, String floorNum, String storeNo, String coords,
			String area) {
		int n = bisStoreManager.updateStoreCoords(bisProjectId, String.valueOf(floorNum), storeNo, coords, area);
		if (n == 1) {
			result.flag = true;
		} else {
			result.flag = false;
		}
	}

	public void checkStore(ResultVO result, String bisProjectId, String floorNum, String storeNo, String coords,
			String area) {
		int n = bisStoreManager.getStoreCoords(bisProjectId, String.valueOf(floorNum), storeNo, area);
		if (n == 1) {
			result.flag = true;
			updateStore(result, bisProjectId, floorNum, storeNo, coords, area);
		} else {
			result.flag = false;
			entity = new BisStore();
			entity.setBisFloor(floor);
			entity.setBisProjectId(bisProjectId);
			entity.setCoords(coords.trim());
			entity.setStoreNo(storeNo.trim());
			entity.setRemark("系统初始化");
			entity.setStatusCd("1");
			if (StringUtils.isNotBlank(area)) {
				entity.setVirtualArea(area);
			}
			bisStoreManager.saveBisStore(entity);
		}
	}

	/**
	 * 下载空坐标的商铺信息
	 */
	public String exportAlertInfo() throws Exception {

		excelFile = buildTemplateExcel("aleatStoreInfo");
		excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");

		return "export";
	}

	/**
	 * 
	 * Description:更新商铺坐标和逻辑分区
	 */
	public void updateStoreInfo() throws Exception {
		String id = Struts2Utils.getParameter("bisStoreId");
		String coords = Struts2Utils.getParameter("coords");
		String virtualArea = Struts2Utils.getParameter("virtualArea");
		if (StringUtils.isNotBlank(id)) {
			entity = bisStoreManager.getEntity(id);
			if (StringUtils.isNotBlank(coords)) {
				entity.setCoords(coords);
			}
			if (StringUtils.isNotBlank(virtualArea)) {
				entity.setVirtualArea(virtualArea);
			}
			bisStoreManager.saveBisStore(entity);
		}
	}

	private InputStream excelFile;// 导出Excel需要的参数

	/**
	 * 下载基础数据
	 */
	public String exportTemplate() throws Exception {

		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		if(this.floorType.equals("1")){//商铺
			excelFile = buildTemplateExcel("populateData");
		}else if(this.floorType.equals("2")){//公寓
			
			excelFileName = "公寓基础信息-" + bisProjectName;
			Map<String, Object> beans = new HashMap<String, Object>();
			List<Map<String, Object> >result = new ArrayList<Map<String,Object>>(); 
			Map<String, Object> bisFlatMap = new HashMap<String, Object>();
			List<BisFlat> bisFlatList = bisFlatManager.getBisFlatList(bisProjectId,  bisFloorId.substring(0, bisFloorId.indexOf("`")));
			
			for (BisFlat bisFlat : bisFlatList) {
				bisFlatMap = new HashMap<String, Object>();
				bisFlatMap.put("flatNo", bisFlat.getFlatNo());
				bisFlatMap.put("square", bisFlat.getSquare());
				bisFlatMap.put("innerSquare", bisFlat.getInnerSquare());
				bisFlatMap.put("openDate", DateOperator.formatDate(bisFlat.getOpenDate(), "yyyy-MM-dd"));
				bisFlatMap.put("layoutCd", DictMapUtil.getMapFlatLayout().get(bisFlat.getLayoutCd()));
				bisFlatMap.put("houseStruCd",DictMapUtil.getMapHouseStru().get(bisFlat.getHouseStruCd()));
				result.add(bisFlatMap);
			}
			beans.put("result", result);
			excelFile = JXLExcelUtil.initJxlsInputStream(beans, "flatInfo.xls");
			
		}else if(this.floorType.equals("3")){//多径

			excelFileName = "多径基础信息-" + bisProjectName;
			Map<String, Object> beans = new HashMap<String, Object>();
			List<BisMulti> bisMultiList = bisMultiManager.getBisMultiList(bisProjectId);
			beans.put("result", bisMultiList);
			excelFile = JXLExcelUtil.initJxlsInputStream(beans, "multiInfo.xls");
		}
		excelFileName = new String(excelFileName.getBytes("GBK"), "ISO8859-1");

		return "export";
	}

	/**
	 * 
	 * Description:生成项目基础信息excel文件 author:jiaoxiaofeng 2011-9-23
	 * 
	 * @return InputStream
	 */
	private InputStream buildTemplateExcel(String functionName) {

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {
			try {
				if (functionName.equals("populateData")) {

					populateData(wbook);
				} else if (functionName.equals("aleatStoreInfo")) {
					aleatStoreInfo(wbook);
				}
			} catch (Exception e) {
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

		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}

	private String excelFileName;
	private String bisProjectName;

	private void populateData(WritableWorkbook wbook) throws Exception {

		excelFileName = "商铺基础信息-" + bisProjectName;

		WritableSheet wsheet0 = wbook.createSheet(bisProjectName + "项目商铺基础信息", 0);
		// 冻结第一行，第一、二列
		wsheet0.getSettings().setHorizontalFreeze(2);
		wsheet0.getSettings().setVerticalFreeze(1);
		// wsheet0.getSettings().setProtected(true);
		initStoreSheet(wsheet0);
	}

	private void aleatStoreInfo(WritableWorkbook wbook) throws Exception {

		excelFileName = "项目商铺空坐标-";

		WritableSheet wsheet0 = wbook.createSheet("项目商铺空坐标", 0);
		initStoreSheet1(wsheet0);
	}

	/**
	 * 
	 * Description：商鋪excel模板 author:jiaoxiaofeng 2011-9-23
	 * 
	 * @param wsheet
	 * @throws WriteException
	 *             void
	 */
	public void initStoreSheet(WritableSheet wsheet) throws WriteException {

		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);

		// 输入框样式
		WritableCellFormat format_unlock = new WritableCellFormat(NumberFormats.FORMAT3);
		format_unlock.setLocked(false); // 不加锁
		format_unlock.setAlignment(Alignment.RIGHT);
		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		// 数值型数据格式
		WritableCellFormat format_number = new WritableCellFormat(NumberFormats.FLOAT);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);

		wsheet.addCell(new Label(0, 0, "id", format_head2));
		wsheet.addCell(new Label(1, 0, "商铺编号", format_head2));
		wsheet.addCell(new Label(2, 0, "建筑面积"));
		wsheet.addCell(new Label(3, 0, "套内面积"));
		wsheet.addCell(new Label(4, 0, "公摊面积"));
		wsheet.addCell(new Label(5, 0, "计租面积"));
		wsheet.addCell(new Label(6, 0, "销售单价"));
		wsheet.addCell(new Label(7, 0, "开业日期", format_head2));
		wsheet.addCell(new Label(8, 0, "租金标准", format_head2));
		wsheet.addCell(new Label(9, 0, "物业标准", format_head2));
		wsheet.addCell(new Label(10, 0, "商铺定位", format_head2));
		wsheet.addCell(new Label(11, 0, "规划业态", format_head2));
		wsheet.addCell(new Label(12, 0, "产权性质", format_head2));
		wsheet.addCell(new Label(13, 0, "经营现状", format_head2));
		wsheet.addCell(new Label(14, 0, "是否开业", format_head2));
		wsheet.addCell(new Label(15, 0, "是否拆分", format_head2));

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 10);
		wsheet.setColumnView(2, 10);
		wsheet.setColumnView(3, 10);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 10);
		wsheet.setColumnView(6, 10);
		wsheet.setColumnView(7, 10);
		wsheet.setColumnView(8, 10);
		wsheet.setColumnView(9, 10);
		wsheet.setColumnView(10, 10);
		wsheet.setColumnView(11, 10);
		wsheet.setColumnView(12, 10);
		wsheet.setColumnView(13, 10);
		wsheet.setColumnView(14, 10);
		wsheet.setColumnView(15, 10);

		List<Object[]> tenantList = bisStoreManager.getStoreInfos(bisProjectId,bisFloorId);

		for (int i = 0; i < tenantList.size(); i++) {
			Object[] obj = tenantList.get(i);
			wsheet.addCell(new Label(0, i + 1, (String) obj[0], format_label));
			wsheet.addCell(new Label(1, i + 1, (String) obj[1], format_label));
			if (obj[2] != null) {
				wsheet.addCell(new jxl.write.Number(2, i + 1, Double.valueOf((String) obj[2])));
			}
			if (obj[3] != null) {
				wsheet.addCell(new jxl.write.Number(3, i + 1, Double.valueOf((String) obj[3])));
			}
			if (obj[4] != null) {
				wsheet.addCell(new jxl.write.Number(4, i + 1, Double.valueOf((String) obj[4])));
			}
			if (obj[5] != null) {
				wsheet.addCell(new jxl.write.Number(5, i + 1, Double.valueOf((String) obj[5])));
			}
			if (obj[6] != null) {
				wsheet.addCell(new jxl.write.Number(6, i + 1, Double.valueOf((String) obj[6])));
			}
			wsheet.addCell(new Label(8, i + 1, (String) obj[8], format_unlock));
			if (obj[6] != null) {
				wsheet.addCell(new jxl.write.Number(8, i + 1, Double.valueOf((String) obj[8])));
			}
			if (obj[9] != null) {
				wsheet.addCell(new jxl.write.Number(9, i + 1, Double.valueOf((String) obj[9])));
			}
			wsheet.addCell(new Label(10, i + 1, (String) obj[10], format_unlock));
			wsheet.addCell(new Label(11, i + 1, (String) obj[11], format_unlock));
			wsheet.addCell(new Label(12, i + 1, (String) obj[12], format_unlock));
			wsheet.addCell(new Label(13, i + 1, (String) obj[13], format_unlock));
			wsheet.addCell(new Label(14, i + 1, (String) obj[14], format_unlock));
			wsheet.addCell(new Label(15, i + 1, (String) obj[15], format_unlock));
		}

	}

	/**
	 * 
	 * Description:生成商铺坐标为空的列表 author:jiaoxiaofeng 2011-11-3
	 * 
	 * @param wsheet
	 * @throws WriteException
	 *             void
	 */
	public void initStoreSheet1(WritableSheet wsheet) throws WriteException {

		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);

		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat();
		format_label.setAlignment(Alignment.LEFT);

		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);

		wsheet.addCell(new Label(0, 0, "id", format_head2));
		wsheet.addCell(new Label(1, 0, "项目名称", format_head2));
		wsheet.addCell(new Label(2, 0, "商铺编号"));
		wsheet.addCell(new Label(3, 0, "逻辑分区"));
		wsheet.addCell(new Label(4, 0, "楼层"));
		wsheet.addCell(new Label(5, 0, "楼号"));
		wsheet.addCell(new Label(6, 0, "创建日期"));
		wsheet.addCell(new Label(7, 0, "更新日期"));
		wsheet.addCell(new Label(8, 0, "商铺拆分"));

		// 每一列宽度
		wsheet.setColumnView(0, 0);
		wsheet.setColumnView(1, 10);
		wsheet.setColumnView(2, 10);
		wsheet.setColumnView(3, 10);
		wsheet.setColumnView(4, 10);
		wsheet.setColumnView(5, 10);
		wsheet.setColumnView(6, 10);
		wsheet.setColumnView(7, 10);
		wsheet.setColumnView(8, 10);

		List<Object[]> tenantList = bisStoreManager.getStoreNullCoords();

		for (int i = 0; i < tenantList.size(); i++) {
			Object[] obj = tenantList.get(i);
			wsheet.addCell(new Label(0, i + 1, (String) obj[0], format_label));
			wsheet.addCell(new Label(1, i + 1, (String) obj[1], format_label));
			wsheet.addCell(new Label(2, i + 1, (String) obj[2], format_label));
			wsheet.addCell(new Label(3, i + 1, (String) obj[3], format_label));
			wsheet.addCell(new Label(4, i + 1, (String) obj[4], format_label));
			wsheet.addCell(new Label(5, i + 1, (String) obj[5], format_label));
			wsheet.addCell(new Label(6, i + 1, obj[6] == null ? "" : obj[6].toString(), format_label));
			wsheet.addCell(new Label(7, i + 1, obj[7] == null ? "" : obj[7].toString(), format_label));
			wsheet.addCell(new Label(8, i + 1, (String) obj[8], format_label));
		}

	}

	private class ResultVO {
		public boolean flag;
		public String mess;
		public int no;

		public ResultVO(int no) {
			this.no = no;
		}

		public ResultVO(boolean flag, String mess) {
			this.flag = flag;
			this.mess = mess;
		}

		public ResultVO(boolean flag, String mess, int no) {
			this.flag = flag;
			this.mess = mess;
			this.no = no;
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBisFloorId() {
		return bisFloorId;
	}

	public void setBisFloorId(String bisFloorId) {
		this.bisFloorId = bisFloorId;
	}

	public Map<String, String> getMapFloor() {
		List<BisFloor> result = bisFloorManager.getGgFloorByProject(bisProjectId, subFloorType, null);
		mapFloor = new LinkedHashMap<String, String>();
		for(BisFloor vo : result){
			mapFloor.put(vo.getBisFloorId(),vo.getBisProject().getProjectName()+"-\t"+vo.getRemark());
		}
		return mapFloor;
	}
	public String loadFloors() {
		floors = bisFloorManager.getGgFloorByProject(bisProjectId, null, null);
		return "floor";
	}
	public void updateFloor(){
		
		if(StringUtils.isNotBlank(bisFloorId)&&StringUtils.isNotBlank(subFloorType)){
			String remark = Struts2Utils.getParameter("remark");
			String parentId = Struts2Utils.getParameter("parentId");
			BisFloor vo = bisFloorManager.getEntity(bisFloorId);
			if(StringUtils.isNotBlank(remark)) {
				vo.setBuildingNum(remark);
			}
			if(StringUtils.isNotBlank(parentId)) {
				vo.setParentId(parentId);
			}
			vo.setSubFloorType(subFloorType);
			bisFloorManager.saveBisFloor(vo);
		}
	}
	public void setMapFloor(Map<String, String> mapFloor) {
		this.mapFloor = mapFloor;
	}

	public String getSubFloorType() {
		return subFloorType;
	}

	public void setSubFloorType(String subFloorType) {
		this.subFloorType = subFloorType;
	}

	public List<BisFloor> getFloors() {
		return floors;
	}

	public void setFloors(List<BisFloor> floors) {
		this.floors = floors;
	}

	public String getFloorType() {
		return floorType;
	}

	public void setFloorType(String floorType) {
		this.floorType = floorType;
	}
}

package com.hhz.ump.web.biz;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictDataManager;
import com.hhz.ump.dao.biz.BizHtlHoeManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.biz.BizHtlHoe;
import com.hhz.ump.entity.biz.BizRelaLib;
import com.hhz.ump.util.CodeNameUtil;

public class BizHtlHoeAction extends CrudActionSupport<BizHtlHoe> {
	
	@Autowired
	private BizHtlHoeManager bizHtlHoeManager;
	@Autowired
	private AppDictDataManager appDictDataManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	private BizHtlHoe entity;
	private Page<AppDictData> pageAppData = new Page<AppDictData>(15);
	//酒店类别数据字典
	private static String BIZ_HTL_DICT_CD = "BIZ_HOTEL_NAME";
	//酒店类别数据字典
	private static String BIZ_HOE_DICT_CD = "BIZ_HOE_TYPE";
	//搜索条件
	private String hotelName;
	private String hotelCd;
	private String hoeTypeName;//
	private String hoeName;
	private String noSearch="";//阻止搜索事件
	private String savesuccess;//保存成功标识
	public String getSavesuccess() {
		return savesuccess;
	}

	public void setSavesuccess(String savesuccess) {
		this.savesuccess = savesuccess;
	}

	/**
	 * 上传文件名
	 */
	private File[] upload;
	private String[] uploadFileName;
	private String[] uploadContentType;
	//附件列表
	private ArrayList<String> bizHoeAttaList = new ArrayList<String>();
	@Override
	public BizHtlHoe getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		String hql=buildHql(param);
		page=bizHtlHoeManager.findPage(page, hql, param);
		return "list";
	}
	private String buildHql(Map<String, Object> param) {
			StringBuffer hql = new StringBuffer().append(" from  BizHtlHoe  where 1=1 ");
			if (StringUtils.isNotBlank(hotelCd)) {
				hql.append(" and hotelCd  like :hotelCd");
				param.put("hotelCd", "%"+hotelCd+"%");
			}
			if (StringUtils.isNotBlank(hoeTypeName)) {
				hql.append(" and hoeTypeName  like :hoeTypeName");
				param.put("hoeTypeName", "%"+hoeTypeName+"%");
			}
			if (StringUtils.isNotBlank(hoeName)) {
				hql.append(" and hoeName  like :hoeName");
				param.put("hoeName", "%"+hoeName+"%");
			}
			hql.append(" order by hotelCd,hoeTypeCd,sequenceNo ");
			return hql.toString();
	}
	
	public String bizHtlDetail() throws Exception{
		if(StringUtils.isNotBlank(getId())){
			entity=bizHtlHoeManager.getEntity(getId());				
		}
		bizHoeAttaList=bizHoeAttaView();
		noSearch="noSearch";
		return "input";
	}
	
	public String bizHtlImport() throws Exception{
		return "import";
	}
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		bizHoeAttaList=bizHoeAttaView();
		return "input";
	}

	/**
	 * 导入excels-POI
	 * @param imHotelName 酒店名称
	 * @param imHotelCd 酒店CD
	 * @return
	 * @author liuzhihui 2012-02-17
	 */
	public String importExlPoi(){
		long begin = new Date().getTime();
		//单次导入数据时公用变量
		String exlHoeTypeName=null;
		String exlHoeTypeCd=null;
		String exlSupName=null;
		String imHotelName=Struts2Utils.getParameter("imHotelName");
		String imHotelCd=Struts2Utils.getParameter("imHotelCd");
		HSSFWorkbook hssfWorkbook = null;
		HSSFSheet hssfSheet = null;
		HSSFRow hssfRow = null;
		try {
			hssfWorkbook = new HSSFWorkbook(new FileInputStream(upload[0]));
			// sheet总数
			int sheetCount = hssfWorkbook.getNumberOfSheets();
			for (int i = 0; i < sheetCount; i++) {
				hssfSheet = hssfWorkbook.getSheetAt(i);
				int countRow = hssfSheet.getLastRowNum();
				for (int row = 0; row <= countRow; row++) {
					hssfRow = hssfSheet.getRow(row);
					if(row==0){
						continue;
					}
					if(row==1){
						continue;
					}
					if(row==2){
						String exlHoeTypeCdTemp = hssfRow.getCell(0).getStringCellValue();
						if(exlHoeTypeCdTemp.contains(":")){
							String [] htct = exlHoeTypeCdTemp.split(":");
							if(StringUtils.isNotBlank(htct[1].trim())){
								exlHoeTypeCd = htct[1].trim();
							}else{
								exlHoeTypeCd = hssfRow.getCell(1).getStringCellValue();
							}
						}
						if(exlHoeTypeCdTemp.contains("：")){
							String [] htct = exlHoeTypeCdTemp.split("：");
							if(StringUtils.isNotBlank(htct[1].trim())){
								 exlHoeTypeCd = htct[1].trim();
							}else{
								 exlHoeTypeCd = hssfRow.getCell(1).getStringCellValue();
							}
						}
						exlHoeTypeName = CodeNameUtil.getDictNameByCd(BIZ_HOE_DICT_CD, exlHoeTypeCd);
						continue;
					 }
					 if(row==3){
						 exlSupName = hssfRow.getCell(0).getStringCellValue();
						 continue;
					 }
					 if(row==4){
						 continue;
					 }
					 //总列数
					 int countColumn = hssfRow.getLastCellNum();
					 int h = 0;
					 if(hssfRow.getCell(1) != null && !"".equals(hssfRow.getCell(1))) {
						 if(StringUtils.isNotBlank(hssfRow.getCell(1).getStringCellValue())){
							 entity = new BizHtlHoe();
							 entity.setHoeTypeName(exlHoeTypeName);
							 entity.setHoeTypeCd(exlHoeTypeCd);
							 entity.setSupName(exlSupName);
							 entity.setHotelName(imHotelName);
							 entity.setHotelCd(imHotelCd);

							 for(int k = 0;k < countColumn;k++){
								 HSSFCell hssfCell = hssfRow.getCell(k);
								 String singelPriceTemp=null;
								 String totalPriceTemp=null;
								 String cellValue = null;
								 if(hssfSheet.isColumnHidden(k)){//判断excel隐藏列
									 continue;
								 }
								 if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
									 cellValue = hssfCell.getStringCellValue();
								 }
								 if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || hssfCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
									cellValue = String.valueOf(hssfCell.getNumericCellValue());
								 }
								 if(h==0) {
									 entity.setSequenceNo(Long.valueOf((long) hssfCell.getNumericCellValue()));
								 }
								 if(h==1) {
									 entity.setHoeName(cellValue);
								 }
								 if(h==2) {
									entity.setHoeModel(cellValue);
								 }
								 if(h==3) {
									entity.setHoeAmount(Long.valueOf((long) hssfCell.getNumericCellValue()));
								 }
								 if(h==4) {
									if(StringUtils.isBlank(cellValue)){
										singelPriceTemp = "0";
									}else{
										singelPriceTemp = cellValue;
									}
									entity.setSingelPrice(new BigDecimal(singelPriceTemp.trim().replace(",","")));
								 }
								 if(h==5) {
									if(StringUtils.isBlank(cellValue)){
										totalPriceTemp = "0";
									}else{
										totalPriceTemp = cellValue;
									}
									entity.setTotalPrice(new BigDecimal(totalPriceTemp.trim().replace(",", "")));
								 }
								 h++;
								 if(k==6){
									 h=++countColumn;
								 }
							 }
							bizHtlHoeManager.saveBizHtlHoe(entity);
						 }
					 }else{
						 break;
					 }
				}
			}
			long end = new Date().getTime();
			System.out.println("导入用时-Poi：" + (end - begin) + "毫秒");
			Struts2Utils.renderText("导入成功;请重新进入");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderHtml("error");
		}finally{
			upload[0].delete();
		}
		return null;
	}
	
	private String importExl(){
		StringBuffer error = new StringBuffer().append("");
		Workbook book = null;
		Sheet se = null;
		Cell[] cells = null;
		BizRelaLib info = null;
		//指定开始解析行号
		int overlookno = 1;
		//指定解析的工作薄
		Integer sheetNow = 0;
		//单次导入数据时公用变量
		String exlHoeTypeName=null;
		String exlHoeTypeCd=null;
		String exlSupName=null;
		String imHotelName=Struts2Utils.getParameter("imHotelName");
		String imHotelCd=Struts2Utils.getParameter("imHotelCd");

//		int l=0;
//		int r=0;
//		int n=0;
//		int p=0;
//		int w=0;
		//记录失败
		try {
			book = Workbook.getWorkbook(upload[0]);
//			String sheetName=CodeNameUtil.getDictNameByCd(DictContants.BIZ_RELA_TYPE, relaTypeCd);
			se = book.getSheet(sheetNow);//得到第1个sheet(Excel通常会有3个Sheet)  
			int rownum = se.getRows();//得到总行数
			int column = se.getColumns();
			//解析合并的单元gre
//			
//			System.out.println(se.getColumnView(4).isHidden());
				for (int i = overlookno; i < rownum; i++) {
					 cells = se.getRow(i);
					 if(i==2){
						 String exlHoeTypeCdTemp=cells[0].getContents();
						 if(exlHoeTypeCdTemp.contains(":")){
							 String [] htct =exlHoeTypeCdTemp.split(":");
							 if(StringUtils.isNotBlank(htct[1].trim())){
								 exlHoeTypeCd=htct[1].trim();
							 }else{
								 exlHoeTypeCd=cells[1].getContents();
							 }
						 }
						 if(exlHoeTypeCdTemp.contains("：")){
							 String [] htct =exlHoeTypeCdTemp.split("：");
							 if(StringUtils.isNotBlank(htct[1].trim())){
								 exlHoeTypeCd=htct[1].trim();
							 }else{
								 exlHoeTypeCd=cells[1].getContents();
							 }
						 }
						 exlHoeTypeName =CodeNameUtil.getDictNameByCd(BIZ_HOE_DICT_CD, exlHoeTypeCd);
						 continue;
					 }
					 if(i==1){
//						 String exlHoeTypeNameTemp=cells[0].getContents();
//						 exlHoeTypeCd=CodeNameUtil.getDictCdByName(DictContants.BIZ_HOE_TYPE, cells[1].getContents());
						 continue;
					 }
					 if(i==3){
						 exlSupName=cells[0].getContents();
						 continue;
					 }
					 if(i==4){
						 continue;
					 }
//					 if(i==4){
//						 for(int m=0;m<column;m++){
//							 if(cells[m].getContents().equals("序号")){
//								 k=m;
//								 continue;
//							 }
//							 if(cells[m].getContents().equals("品名")){
//								 l=m;
//								 continue;
//							 }
//							 if(cells[m].getContents().equals("详细规格")||cells[m].getContents().equals("规格型号")){
//								 r=m;
//								 continue;
//							 }
//							 if(cells[m].getContents().equals("数量")){
//								 n=m;
//								 continue;
//							 }
//							 if(cells[m].getContents().equals("单价")){
//								 p=m;
//								 continue;
//							 }
//							 if(cells[m].getContents().equals("总价")){
//								 w=m;
//								 continue;
//							 }
//							 
//						 }
//						 continue;
//					 }
//					for(int j=0;j<column;j++){
//						if(se.getColumnView(column).isHidden()){
//							 j++;
//							 continue;
//						 }
					 entity = new BizHtlHoe();
					 entity.setHoeTypeName(exlHoeTypeName);
					 entity.setHoeTypeCd(exlHoeTypeCd);
					 entity.setSupName(exlSupName);
					 entity.setHotelName(imHotelName);
					 entity.setHotelCd(imHotelCd);
					 int k=0;
					 if(cells[0]==null||StringUtils.isBlank(cells[0].getContents())){
						break;
					 }
						for(int j=0;j<column;j++){
							String hoeAmountTemp=null;
							String singelPriceTemp=null;
							String totalPriceTemp=null;
							if(se.getColumnView(j).isHidden()){
								 continue;
							}
							if(k==0) {
								entity.setSequenceNo(Long.parseLong(cells[j].getContents()));
							}
							if(k==1) {
								entity.setHoeName(cells[j].getContents());
							}
							if(k==2) {
								entity.setHoeModel(cells[j].getContents());
							}
							if(k==3) {
								if(null==cells[j].getContents().trim()||cells[j].getContents().trim().equals("")){
									 hoeAmountTemp="0";
								}else{
									 hoeAmountTemp=cells[j].getContents();
								}
								entity.setHoeAmount(Long.parseLong(hoeAmountTemp.trim().replace(",", "")));
							}
							if(k==4) {
								if(null==cells[j].getContents().trim()||cells[j].getContents().trim().equals("")){
									singelPriceTemp="0";
								}else{
									singelPriceTemp=cells[j].getContents();
								}
								entity.setSingelPrice(new BigDecimal(singelPriceTemp.trim().replace(",","")));
							}
							if(k==5) {
								if(null==cells[j].getContents().trim()||cells[j].getContents().trim().equals("")){
									totalPriceTemp="0";
								}else{
									totalPriceTemp=cells[j].getContents();
								}
								entity.setTotalPrice(new BigDecimal(totalPriceTemp.trim().replace(",", "")));
							}
							k++;
							if(k==6){
								j=++column;
							}
						}

//					 entity.setSequenceNo(Long.parseLong(cells[k].getContents()));
//					 entity.setHoeName(cells[l].getContents());
//					 entity.setHoeModel(cells[r].getContents());
//					 entity.setHoeAmount(Long.parseLong(cells[n].getContents().replace(",", "")));
//					 String singelPriceExl=cells[4].getContents();
//					 BigDecimal sd=new BigDecimal(singelPriceExl);
//					 entity.setSingelPrice(new BigDecimal(cells[p].getContents().replace(",", "")));
//					 entity.setTotalPrice(new BigDecimal(cells[w].getContents().replace(",", "")));
					 bizHtlHoeManager.saveBizHtlHoe(entity);
						 
					}
			

		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderText("导入失败");
			return null;
		} finally{
			if(book != null){
				book.close();
			}
			upload[0].delete();
		}
		if(error.equals("")){
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("导入成功;请重新进入");
		}
		return null;
	}
	
	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		bizHtlHoeManager.saveBizHtlHoe(entity);
		bizHoeAttaList=bizHoeAttaView(entity.getBizHtlHoeId());
		savesuccess="保存成功!";
		
		return "input";
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(getId())){
			bizHtlHoeManager.deleteBizHtlHoe(getId());
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (getId() != null) {
			entity = bizHtlHoeManager.getEntity(getId());
		} else {
			entity = new BizHtlHoe();
		}
	}
	/**
	 * 用于酒店项目字典选择
	 * @param value
	 * @throws Exception
	 */
	public void quickSearch() throws Exception {
		String value = Struts2Utils.getParameter("value");
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
			hql.append(" from AppDictData where  1=1 ");
			hql.append(" and appDictType.appDictTypeId =");
			hql.append("(select appDictTypeId from AppDictType t where t.dictTypeCd=:dictTypeCd )");
			param.put("dictTypeCd",BIZ_HTL_DICT_CD);
			hql.append(" and dictName like :dictName");
			param.put("dictName", "%"+value+"%");
			pageAppData = appDictDataManager.findPage(pageAppData, hql.toString(), param);
			List<Map<String, String> > list = new ArrayList<Map<String, String> >();
			for(AppDictData cl : pageAppData.getResult()){
				Map<String, String> map=new HashMap<String, String>();
				map.put("dictName", cl.getDictName());
				map.put("dictCd", cl.getDictCd());
				list.add(map);
			}
			Struts2Utils.renderJson(list);
	}
	/**
	 * 用于酒店项目字典选择
	 * @param value
	 * @throws Exception
	 */
	public void quickSearchHoe() throws Exception {
		String value = Struts2Utils.getParameter("value");
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from AppDictData where  1=1 ");
		hql.append(" and appDictType.appDictTypeId =");
		hql.append("(select appDictTypeId from AppDictType t where t.dictTypeCd=:dictTypeCd )");
		param.put("dictTypeCd",BIZ_HOE_DICT_CD);
		hql.append(" and dictName like :dictName");
		param.put("dictName", "%"+value+"%");
		pageAppData = appDictDataManager.findPage(pageAppData, hql.toString(), param);
		List<Map<String, String> > list = new ArrayList<Map<String, String> >();
		for(AppDictData cl : pageAppData.getResult()){
			Map<String, String> map=new HashMap<String, String>();
			map.put("dictName", cl.getDictName());
			map.put("dictCd", cl.getDictCd());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}
	//附件列表
	public ArrayList<String> bizHoeAttaView(){
		if(StringUtils.isNotBlank(getId())){//查看上传的附件
			entity = bizHtlHoeManager.getEntity(getId());
			if(StringUtils.isNotBlank(entity.getBizHoeAttaId())){
				List<AppAttachFile> file=appAttachFileManager.getAttaFileByBizEntityId(entity.getBizHoeAttaId());
				if(file!=null&&file.size()>0){
					bizHoeAttaList.add("1");
				}else{
					bizHoeAttaList.add("0");
				}
			}else{
				bizHoeAttaList.add("0");
			}
		}else{
			bizHoeAttaList.add("0");
		}
		return bizHoeAttaList;
	}
	public ArrayList<String> bizHoeAttaView(String bizHtlHoeId){
		if(StringUtils.isNotBlank(bizHtlHoeId)){//查看上传的附件
			entity = bizHtlHoeManager.getEntity(bizHtlHoeId);
			if(StringUtils.isNotBlank(entity.getBizHoeAttaId())){
				List<AppAttachFile> file=appAttachFileManager.getAttaFileByBizEntityId(entity.getBizHoeAttaId());
				if(file!=null&&file.size()>0){
					bizHoeAttaList.add("1");
				}else{
					bizHoeAttaList.add("0");
				}
			}else{
				bizHoeAttaList.add("0");
			}
		}else{
			bizHoeAttaList.add("0");
		}
		return bizHoeAttaList;
	}
	public BizHtlHoe getEntity() {
		return entity;
	}

	public void setEntity(BizHtlHoe entity) {
		this.entity = entity;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getHoeTypeName() {
		return hoeTypeName;
	}

	public void setHoeTypeName(String hoeTypeName) {
		this.hoeTypeName = hoeTypeName;
	}

	public String getHoeName() {
		return hoeName;
	}

	public void setHoeName(String hoeName) {
		this.hoeName = hoeName;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getHotelCd() {
		return hotelCd;
	}

	public void setHotelCd(String hotelCd) {
		this.hotelCd = hotelCd;
	}

	public ArrayList<String> getBizHoeAttaList() {
		return bizHoeAttaList;
	}

	public void setBizHoeAttaList(ArrayList<String> bizHoeAttaList) {
		this.bizHoeAttaList = bizHoeAttaList;
	}

	public String getNoSearch() {
		return noSearch;
	}

	public void setNoSearch(String noSearch) {
		this.noSearch = noSearch;
	}

}

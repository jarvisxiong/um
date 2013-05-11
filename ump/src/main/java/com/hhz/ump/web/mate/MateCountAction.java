package com.hhz.ump.web.mate;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.mate.MateCountBasicManager;
import com.hhz.ump.dao.mate.MateCountDetailManager;
import com.hhz.ump.dao.mate.MateOwnerTypeManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.mate.MateCountBasic;
import com.hhz.ump.entity.mate.MateCountDetail;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.CodeNameUtil;

public class MateCountAction extends CrudActionSupport<MateCountDetail> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4352232787116346923L;
	private MateCountDetail entity;
	private MateCountBasic mateBasic;
	private String mateCountBasicId; //根据基本信息表主键关联详细信息表
	private String registerNumber;
	private String useStatus;
	private String ownerMate;
	private String clearAudit;//详细表为清算状态，基本信息表该字段为清算总额
	private String clearStatus;//详细表为清算状态
    private String useUnitName;//领货单位名称
    private String instalBl;//是否安装,是：施工单位领用量、施工单位暂定单价不可填 ； 否：施工单位领用量、施工单位暂定单价为必填项
	private Date insDate;
	private Date insDateBetween;
	private String contTypes;
	private List<MateCountDetail> mateCountDetail;
	private String inputFlag;
	private ArrayList<String> mateAttaList = new ArrayList<String>();
	private Map<String, String> mateTypeMap = new HashMap<String, String>();
	private Map<String, String> mateDetailMap = new HashMap<String, String>();
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private MateCountDetailManager mateCountDetailManager;
	@Autowired
	private MateOwnerTypeManager mateOwnerTypeManager;
	@Autowired
	private MateCountBasicManager mateCountBasicManager;
	
	private File upload; //ecxel导入附件
	
	@Override
	public void prepareSave() throws Exception{
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		mateCountDetailManager.saveMateCountDetail(entity);
		inputFlag = "保存成功!";
		refreshMatebasic(entity);
		mateAttaList =mateAttaView();
		if("1".equals(clearAudit)){
			inputFlag = "清算成功!";
		}
		return "input";
	}
	
	/**
	 * 添加了详细表信息后，刷新基本表的数据
	 * 
	 * 2012-02-21由liuzhihui从save()方法中提取出来，
	 * 单独做一个方法,方便导入excel使用！
	 * 
	 * @param mateCount
	 */
	public void  refreshMatebasic(MateCountDetail mateCount){
		String basicId = mateCount.getMateCountBasic().getMateCountBasicId();
		if(StringUtils.isNotBlank(basicId)){//计算
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			filters.add(new PropertyFilter("EQS_mateCountBasic.mateCountBasicId", basicId));
			List<MateCountDetail> countDetail =mateCountDetailManager.find(filters);
			if(countDetail!=null){
				BigDecimal supplyTotal = new BigDecimal(0);//实际供应量（总）
				BigDecimal stockAllTotal = new BigDecimal(0);//库存量（总）
				BigDecimal exeUnitRealTotal = new BigDecimal(0);//施工单位实际领用量（总）
				BigDecimal endSupplyAllTotal = new BigDecimal(0);//采购合同合价（总）对应甲供料基本信息表的已供货总额
				BigDecimal clearAuditTotal = new BigDecimal(0);//采购合同合价（已清算）对应甲供料基本信息表已清算总额
				BigDecimal noClearAuditTotal = new BigDecimal(0);//采购合同合价（未清算）对应甲供料基本信息表的未清算总额
				for(MateCountDetail mc:countDetail){
					supplyTotal = supplyTotal.add(mc.getRealSupply()==null?new BigDecimal(0):mc.getRealSupply());
					stockAllTotal = stockAllTotal.add(mc.getStockAll()==null?new BigDecimal(0):mc.getStockAll());
					exeUnitRealTotal = exeUnitRealTotal.add(mc.getExeUnitRealUse()==null?new BigDecimal(0):mc.getExeUnitRealUse());
					endSupplyAllTotal = endSupplyAllTotal.add(mc.getBuyCoutGroup()==null?new BigDecimal(0):mc.getBuyCoutGroup());
					if(null!=mc.getClearAudit()&&mc.getClearAudit().equals("1")){
						clearAuditTotal=clearAuditTotal.add(mc.getBuyCoutGroup()==null?new BigDecimal(0):mc.getBuyCoutGroup());
					}else{
						noClearAuditTotal=noClearAuditTotal.add(mc.getBuyCoutGroup()==null?new BigDecimal(0):mc.getBuyCoutGroup());
					}
				}
				mateBasic=mateCountBasicManager.getEntity(basicId);
				if(noClearAuditTotal.equals(0)){
					mateBasic.setClearStatus("2");//2、完全清算 1、部分清算0、未清算
				}else if(clearAuditTotal.equals(0)){
					mateBasic.setClearStatus("0");
				}else{
					mateBasic.setClearStatus("1");
				}
				mateBasic.setRealSupplyAll(supplyTotal);
				mateBasic.setStockAll(stockAllTotal);
				mateBasic.setExeUnitRealUse(exeUnitRealTotal);
				mateBasic.setClearAudit(clearAuditTotal);
				mateBasic.setNoClearAudit(noClearAuditTotal);
				mateBasic.setEndSupplyAll(endSupplyAllTotal);
	//			mateBasic.setUseStatus(entity.getUseStatus());
				mateCountBasicManager.saveMateCountBasic(mateBasic);
			}
		}
	}

	
	@Override
	public MateCountDetail getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<String> mateAttaView(){
		if(StringUtils.isNotBlank(getId())){//查看上传的附件
			entity = mateCountDetailManager.getEntity(getId());
			if(StringUtils.isNotBlank(entity.getMateAttaId())){
				List<AppAttachFile> file=appAttachFileManager.getAttaFileByBizEntityId(entity.getMateAttaId());
				if(file!=null&&file.size()>0){
					mateAttaList.add("1");
				}else{
					mateAttaList.add("0");
				}
			}else{
				mateAttaList.add("0");
			}
			if(StringUtils.isNotBlank(entity.getMateAttaId())){
				List<AppAttachFile> file=appAttachFileManager.getAttaFileByBizEntityId(entity.getStoreSignAtta());
				if(file!=null&&file.size()>0){
					mateAttaList.add("1");
				}else{
					mateAttaList.add("0");
				}
			}else{
				mateAttaList.add("0");
			}
		}else{
			mateAttaList.add("0");
			mateAttaList.add("0");
		}
		return mateAttaList;
	}
	/**
	 * 拼装搜索条件
	 * @return
	 */
	private String mateDetailQuery(boolean isExport, Map<String,Object> values){
		if (values == null) {
			values = new HashMap<String, Object>();
		}
		StringBuffer hql = new StringBuffer("select a  from MateCountDetail a where 1=1 ");
		if (StringUtils.isNotBlank(mateCountBasicId)) {
			values.put("mateCountBasicId", mateCountBasicId);
			hql.append(" and a.mateCountBasic.mateCountBasicId = :mateCountBasicId ");
		} else {
			hql.append(" and a.mateCountBasic.mateCountBasicId  is null ");
		}
		if (StringUtils.isNotBlank(useUnitName)) {
			values.put("useUnitName", "%" + useUnitName + "%");
			hql.append(" and a.useUnitName like :useUnitName ");
		}
		if (StringUtils.isNotBlank(clearStatus)) {
			if (clearStatus.equals("1")) {
				values.put("clearAudit", "1");
				hql.append(" and  a.clearAudit = :clearAudit ");
			} else {
				values.put("clearAudit", clearStatus);
				hql.append(" and ( a.clearAudit = :clearAudit or  a.clearAudit is null )");
			}
		}
		if (insDate != null) {
			String toInsDate = DateOperator.formatDate(insDate, "yyyy-MM-dd");
			values.put("toInsDate", toInsDate);
			hql.append(" and a.insDate >= to_date(:toInsDate,'yyyy-mm-dd')");
		}
		if (insDateBetween != null) {
			String toInsDateBetween = DateOperator.formatDate(DateOperator.addDays(insDateBetween, 1), "yyyy-MM-dd");
			values.put("toInsDateBetween", toInsDateBetween);
			hql.append(" and a.insDate <= to_date(:toInsDateBetween,'yyyy-mm-dd')");
		}
		hql.append(" order by insDate desc, mateCountDetailId desc ");
		return hql.toString();
	}
	/**
	 * 取消清算状态
	 * @return
	 */
	public String updateClear(){
		if (StringUtils.isNotBlank(getId())) {
			entity=mateCountDetailManager.getEntity(getId());
			entity.setClearAudit(clearAudit);
			mateCountDetailManager.saveMateCountDetail(entity);
			Struts2Utils.renderHtml("success");
		}
		return null;
	}
	@Override
	public String list() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();
		//默认每页30条
		page.setPageSize(30);
		page = mateCountDetailManager.findPage(page, mateDetailQuery(false, values), values);
		//显示公司名称
		mateCountDetail=page.getResult();
		for(MateCountDetail mateDetail:mateCountDetail){
			mateDetailMap.put(mateDetail.getProjectCd(), CodeNameUtil.getDeptNameByCd(mateDetail.getProjectCd()));
		}
//		useStatus=useStatus;
		return "list";
	}

	@Override
	public String input() throws Exception {
		ownerMate=new String(Struts2Utils.getParameter("ownerMate").getBytes("ISO-8859-1"),"gbk");
		if(StringUtils.isNotBlank(entity.getMateCountBasic().getMateCountBasicId())){
//		String	mateCountBasicId = entity.getMateCountBasic().getMateCountBasicId();
			String tmpTypeCd = entity.getMateCountBasic().getOwnerMaterialType();
			if(StringUtils.isNotBlank(tmpTypeCd)){
				ownerMate = mateOwnerTypeManager.getMateNameByCd(tmpTypeCd);
			}
		}
		mateAttaList = mateAttaView();
		return "input";
	}
	@Override
	public String delete() throws Exception {
		if(StringUtils.isNotBlank(getMateCountBasicId())){
			mateBasic=mateCountBasicManager.getEntity(getMateCountBasicId());
			String useStatus= mateBasic.getUseStatus();
			if(null!=useStatus&&"1".equals(useStatus)){
				Struts2Utils.renderHtml("failure");
			}else{
				if (StringUtils.isNotBlank(getId())) {
					mateCountDetailManager.deleteMateCountDetail(getId());
					Struts2Utils.renderHtml("success");
				}
			}
		}
		return null;
	}

	public String mateCountImport(){
		return "import";
	}
	
	/**
	 * 批量导入甲供料材料明细信息-POI导入
	 * 
	 * @param mateCountBasicId 材料基本信息ID
	 * @author liuzhihui 2012-02-21
	 * @return
	 */
	public String importExlPoi() throws Exception{
		if(StringUtils.isNotBlank(mateCountBasicId)){
			mateBasic = mateCountBasicManager.getEntity(mateCountBasicId);
			long begin = new Date().getTime();
			if(mateBasic != null){
				HSSFWorkbook hssfWorkbook = null;
				HSSFSheet hssfSheet = null;
				HSSFRow hssfRow = null;
				int c = 0;
				try {
					List<MateCountDetail> mateCountDetailList = new ArrayList<MateCountDetail>();
					hssfWorkbook = new HSSFWorkbook(new FileInputStream(upload));
					int sheetCount = hssfWorkbook.getNumberOfSheets(); // sheet总数
					for (int i = 0; i < sheetCount; i++) {
						hssfSheet = hssfWorkbook.getSheetAt(i);
						int countRow = hssfSheet.getLastRowNum(); //总行数
						//从excel第4行开始循环
						for (int row = 3; row <= countRow; row++) {
							hssfRow = hssfSheet.getRow(row);
							int countColumn = hssfRow.getLastCellNum(); //总列数
							entity = new MateCountDetail();
							entity.setMateCountBasic(mateBasic);
							for (int col = 0; col < countColumn; col++) {
								c = col;
								++c;
								HSSFCell hssfCell = hssfRow.getCell(col); //得到每一个单元格
								String cellValue = null;
								if(hssfSheet.isColumnHidden(col)){ //判断excel隐藏列
									continue;
								}
								//必填项为空验证
								if(col == 0 || col == 4 || col == 9 || col == 10 || col == 11 || col == 12 || col == 14 ||col == 17){
									if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
										Struts2Utils.renderText(",error, 未导入成功，请于第"+(row+1)+"行第"+BisConstants.letters.get(c)+"列填写必填项!,");
										return null;
									}
								}
								//验证日期格式
								else if(col == 5){
									//如果是日期类型
									if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
										if (StringUtils.isBlank(DateOperator.formatDate(hssfCell.getDateCellValue(), "yyyy-MM-dd"))) {
											Struts2Utils.renderText(",error, 第"+(row+1)+"行第"+BisConstants.letters.get(c)+"列，进(退)货日期格式错误(填写如2012-01-01)!,");
											return null;
										}

									}
									//如果日期是字符串类型
									else if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
										String date = hssfCell.getStringCellValue();
										if(StringUtils.isNotBlank(date)){
											//判断是否符合日期格式 yyyy-MM-dd
											if(!date.matches("\\d{4}-\\d{2}-\\d{2}")){
												Struts2Utils.renderText(",error, 第"+(row+1)+"行第"+BisConstants.letters.get(c)+"列，进(退)货日期格式错误(填写如2012-01-01)!,");
												return null;
											}
										}
									}else if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_BLANK){
									}else{
										Struts2Utils.renderText(",error, 第"+(row+1)+"行第"+BisConstants.letters.get(c)+"列，进(退)货日期格式错误(填写如2012-01-01)!,");
										return null;
									}
								}
								//验证是否为数字
								else if(col == 9 || col == 10 || col == 11 || col == 12){
									if(hssfCell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC){
										Struts2Utils.renderText(",error, 未导入成功，请于第"+(row+1)+"行第"+BisConstants.letters.get(c)+"列检查格式是否为数字形式!,");
										return null;
									}
								}
								//施工单位申请预算量必须为数字类型 ,为空时值默认为0
								else if(col == 8){
									if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
										Struts2Utils.renderText(",error, 未导入成功，请于第"+(row+1)+"行第"+BisConstants.letters.get(c)+"列检查格式是否为数字形式!,");
										return null;
									}
								}
								if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
									if(StringUtils.isNotBlank(hssfCell.getStringCellValue())){
										cellValue = hssfCell.getStringCellValue().trim();
									}else{
										cellValue = "";
									}
								}
								if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || hssfCell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
									if(StringUtils.isNotBlank(String.valueOf(hssfCell.getNumericCellValue()))){
										cellValue = String.valueOf(hssfCell.getNumericCellValue()).trim();
									}else{
										cellValue = "";
									}
								}
								if(col == 0) {
									entity.setInsRegisterNumber(cellValue);
								}
								if(col == 1) {
									entity.setUseUnitName(cellValue);
								}
								if(col == 2) {
									entity.setMateTasname(cellValue);
								}
								if(col == 3) {
									entity.setMateStan(cellValue);
								}
								if(col == 4) {
									boolean insOutBl = false;
									if("进货".equals(cellValue)){
										insOutBl = true;
									}
									entity.setInsOutBl(insOutBl);
								}
								if(col == 5) {
									if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
										entity.setInsDate(hssfCell.getDateCellValue());
									}
									if(hssfCell.getCellType() == HSSFCell.CELL_TYPE_STRING){
										SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
										String date = hssfCell.getStringCellValue();
										if(StringUtils.isNotBlank(date)){
											entity.setInsDate(sf.parse(date));
										}
									}
								}
								if(col == 6) {
									entity.setMateBrand(cellValue);
								}
								if(col == 7) {
									entity.setMateType(cellValue);
								}
								if(col == 8) {
									BigDecimal exeUnitApplBud = returnBigValue(cellValue);
									entity.setExeUnitApplBud(exeUnitApplBud);
								}
								if(col == 9) {
									BigDecimal realSupply = returnBigValue(cellValue);
									entity.setRealSupply(realSupply);
								}
								if(col == 10) {
									BigDecimal exeUnitRealUse = returnBigValue(cellValue);
									entity.setExeUnitRealUse(exeUnitRealUse);
								}
								if(col == 11) {
									BigDecimal buyCoutSingle = returnBigValue(cellValue);
									entity.setBuyCoutSingle(buyCoutSingle);
								}
								if(col == 12) {
									BigDecimal exeCoutSingle = returnBigValue(cellValue);
									entity.setExeCoutSingle(exeCoutSingle);
								}
								if(col == 13) {
									entity.setStockManager(cellValue);
								}
								if(col == 14) {
									String priceWay = "";
									if("合同价".equals(cellValue)){
										priceWay = "1";
									}else if("战略价".equals(cellValue)){
										priceWay = "2";
									}else if("核定价".equals(cellValue)){
										priceWay = "3";
									}
									entity.setPriceWay(priceWay);
								}
								if(col == 15) {
									entity.setUseSpace(cellValue);
								}
								if(col == 16) {
									entity.setMateChar(cellValue);
								}
								if(col == 17) {
									entity.setUnit(cellValue);
								}
								if(col == 18) {
									entity.setRemark(cellValue);
								}
							}
							//采购合同合价 = 采购合同单价 * 实际供应量
							entity.setBuyCoutGroup(entity.getBuyCoutSingle().multiply(entity.getRealSupply()));
							//施工合同暂定合价 = 施工单位实际领用量 * 施工合同暂定单价
							entity.setExeCoutGroup(entity.getExeUnitRealUse().multiply(entity.getExeCoutSingle()));
							//库存量 = 实际供应量 - 施工单位实际领用量
							entity.setStockAll(entity.getRealSupply().subtract(entity.getExeUnitRealUse()));
							entity.setProjectCd(mateBasic.getProjectCd());
							entity.setContNo(mateBasic.getContNo());
							
							//模板导入 add by huangbijin 2012-02-22
							entity.setImportTypeFlg(new Boolean(true));
							
							mateCountDetailList.add(entity);
						}
					}
					this.batchExecute(mateCountDetailList);
					long end = new Date().getTime();
					System.out.println("导入用时-POI：" + (end - begin) + "毫秒");
					Struts2Utils.renderText(",success," + (end - begin) / 1000 + ",");
				} catch (Exception e) {
					e.printStackTrace();
					//String errorLocation = "未导入成功，请于第"+(r + 1) + "行第" + BisConstants.letters.get(c) + "列检查数据有效性!";
					Struts2Utils.renderText(",error," + "未导入成功!" + ", " + e + ",");
				} finally{
					upload.delete();
				}
			}
		}
		return null;
	}
	
	public void batchExecute(List<MateCountDetail> mateCountDetailList){
		for (MateCountDetail tmpMate : mateCountDetailList) {
			mateCountDetailManager.saveMateCountDetail(tmpMate);
			this.refreshMatebasic(tmpMate); //刷新材料基本信息表MateCountBasic数据
		}
	}
	
	public BigDecimal returnBigValue(String value){
		BigDecimal bigDecimal = null;
		if(StringUtils.isNotBlank(value)){
			bigDecimal = new BigDecimal(value);
		}else{
			bigDecimal = new BigDecimal(0);
		}
		return bigDecimal;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(getMateCountBasicId())){
			mateBasic = mateCountBasicManager.getEntity(mateCountBasicId);
		}else{
			mateBasic = new MateCountBasic();
		}
		if (getId() != null) {
			entity = mateCountDetailManager.getEntity(getId());
		} else {
			entity = new MateCountDetail();
			// add by huangbijin 是否末班导入 1-是  0-默认手动输入
			entity.setImportTypeFlg(new Boolean(false));
		}
		entity.setMateCountBasic(mateBasic);
		
	}
	
	public MateCountDetail getEntity() {
		return entity;
	}
	public void setEntity(MateCountDetail entity) {
		this.entity = entity;
	}
	public String main(){
		return "main";
	}
	public Map<String, String> getMateTypeMap() {
		return mateTypeMap;
	}
	public void setMateTypeMap(Map<String, String> mateTypeMap) {
		this.mateTypeMap = mateTypeMap;
	}
	public String getInputFlag() {
		return inputFlag;
	}
	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}	
	public String getContTypes() {
		return contTypes;
	}
	public void setContTypes(String contTypes) {
		this.contTypes = contTypes;
	}
	public List<MateCountDetail> getMateCountDetail() {
		return mateCountDetail;
	}
	public void setMateCountDetail(List<MateCountDetail> mateCountDetail) {
		this.mateCountDetail = mateCountDetail;
	}
	public String getMateCountBasicId() {
		return mateCountBasicId;
	}
	public void setMateCountBasicId(String mateCountBasicId) {
		this.mateCountBasicId = mateCountBasicId;
	}
	public Map<String, String> getMateDetailMap() {
		return mateDetailMap;
	}
	public void setMateDetailMap(Map<String, String> mateDetailMap) {
		this.mateDetailMap = mateDetailMap;
	}
	public MateCountBasic getMateBasic() {
		return mateBasic;
	}
	public void setMateBasic(MateCountBasic mateBasic) {
		this.mateBasic = mateBasic;
	}
	public ArrayList<String> getMateAttaList() {
		return mateAttaList;
	}
	public void setMateAttaList(ArrayList<String> mateAttaList) {
		this.mateAttaList = mateAttaList;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public String getClearAudit() {
		return clearAudit;
	}
	public void setClearAudit(String clearAudit) {
		this.clearAudit = clearAudit;
	}
	public String getOwnerMate() {
		return ownerMate;
	}
	public void setOwnerMate(String ownerMate) {
		this.ownerMate = ownerMate;
	}
	public String getUseUnitName() {
		return useUnitName;
	}
	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
	}
	public Date getInsDate() {
		return insDate;
	}
	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}
	public Date getInsDateBetween() {
		return insDateBetween;
	}
	public void setInsDateBetween(Date insDateBetween) {
		this.insDateBetween = insDateBetween;
	}
	public String getClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(String clearStatus) {
		this.clearStatus = clearStatus;
	}
	public String getInstalBl() {
		return instalBl;
	}
	public void setInstalBl(String instalBl) {
		this.instalBl = instalBl;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
}

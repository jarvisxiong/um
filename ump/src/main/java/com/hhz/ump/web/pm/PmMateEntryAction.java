package com.hhz.ump.web.pm;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.pm.PmEntryResRelationManager;
import com.hhz.ump.dao.pm.PmMateEntryManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.pm.PmEntryResRelation;
import com.hhz.ump.entity.pm.PmMateEntry;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.web.pm.vo.PmMateEntryVo;
import com.hhz.ump.web.vo.EntityVO;

@Namespace("/pm")
public class PmMateEntryAction extends CrudActionSupport<PmMateEntry> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PmMateEntryManager pmMateEntryManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private PmEntryResRelationManager pmEntryResRelationManager;
	private List<PmMateEntry> pmMateEntryList;
	private List<PmEntryResRelation> pmEntryResRelationList;
	List<PmMateEntryVo> pmMateEntryVoList;
	private PmMateEntry entity;
	private AppAttachFile appAttachFile;
	private List<AppAttachFile> appAttachFileList;
	private int displayNameSize;
	private String pmMateModuId; //点击左边树触发的ID
	private String pmMateEntryId;
	private String bizEntityId;
	private String uploadSn;
	private String bizEntityTempId;
	private List<EntityVO> parentIdList;
	private String[] parentModuleId;
	private String fileName;
	
	private File[] upload_pic;
	private String[] upload_picFileName;
	private String[] upload_picContentType;

	private File[] upload_prj;
	private String[] upload_prjFileName;
	private String[] upload_prjContentType;
	
	private String fileSn_pic;
	private String fileSn_prj;
	private boolean isUploaded = false;
	/**
	 * office文件
	 */
	public static final String FILTER_TYPE_OFFICE = "office";
	/**
	 * 图片文件
	 */
	public static final String FILTER_TYPE_IMAGE = "image";

	/**
	 * 视频文件
	 */
	public static final String FILTER_TYPE_VIDEO = "video";
	/**
	 * 视频文件
	 */
	public static final String FILTER_TYPE_PDF = "pdf";

	/**
	 * 视频文件
	 */
	public static final String FILTER_TYPE_IMAGE_OR_OFFICE = "image|office";
	
	private String[] allowTypes = CoreContants.FILE_TYPE;
	/**
	 * 允许上传的文件类型代码
	 */
	private String filterType;
	
	private String flag;
	
	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = pmMateEntryManager.getEntity(getId());
			if (entity != null) {
				pmMateEntryManager.delete(entity);
			}
		}
		return null;
	}
	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		//需要删除的企划案例IDS
		String entryIds=Struts2Utils.getParameter("mateEntryIds");
		//如果不为空，则删除相应的企划案例信息
		if(StringUtils.isNotBlank(entryIds)){
			String[] mateEntryIds=entryIds.split(",");
			//删除企划案例信息
			pmMateEntryManager.deleteBatch(mateEntryIds);
			//删除相关联的企划案例对应的附件
			appAttachFileManager.delAttachFiles(mateEntryIds);
			Struts2Utils.renderText("success");
		}else{
			Struts2Utils.renderText("fail");
		}
		return null;
	}
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		//如果是新增入口：状态为0,修改入口：状态为1;
		flag = Struts2Utils.getParameter("flag");
		pmMateModuId = Struts2Utils.getParameter("pmMateModuId");
		pmMateEntryId = Struts2Utils.getParameter("pmMateEntryId");
		if(null!=pmMateEntryId){
			entity = pmMateEntryManager.getPmMateEntry(pmMateEntryId);
			//根据外键id和模块id查询出相关附件
			appAttachFileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(pmMateEntryId,DictContants.BIZ_MODULE_CD_PROJECTMANAGER);
		
			displayNameSize = 0;
			//查询出企划案例库中所包含的网批记录
			pmEntryResRelationList = pmEntryResRelationManager.getPmEntryResRelation(pmMateEntryId);
			if(null!=pmEntryResRelationList && pmEntryResRelationList.size()>0){
				for (PmEntryResRelation pmEntryResRelation :pmEntryResRelationList) {
					displayNameSize++;
				}
			}
		}
		return INPUT;
	}
	
	public String quickSearch() throws Exception{
		String value =Struts2Utils.getParameter("value");
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from PmMateEntry f where 1=1");
		if(StringUtils.isNotBlank(value)){
			hql.append(" and f.activeTitle like :activeTitle");
			values.put("activeTitle", "%"+value.trim()+"%");
		}
		hql.append(" order by f.createdDate desc ");
		page.setPageSize(10);
		page = pmMateEntryManager.findPage(page, hql.toString(), values);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (PmMateEntry pmMateEntry :page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("mateEntryId",pmMateEntry.getPmMateEntryId());
			map.put("activeTitle",pmMateEntry.getActiveTitle());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}
	
	public String quickSearchMateEntry() throws Exception{
		String value =Struts2Utils.getParameter("value");
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from PmMateEntry f where 1=1");
		if(StringUtils.isNotBlank(value)){
			hql.append(" and f.pmMateEntryNo like :pmMateEntryNo");
			values.put("pmMateEntryNo", "%"+value.trim()+"%");
		}
		hql.append(" order by f.createdDate desc ");
		page.setPageSize(10);
		page = pmMateEntryManager.findPage(page, hql.toString(), values);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (PmMateEntry pmMateEntry :page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("pmMateEntryId",pmMateEntry.getPmMateEntryId());
			map.put("pmMateEntryNo",pmMateEntry.getPmMateEntryNo());
			map.put("activeTitle",pmMateEntry.getActiveTitle());
			map.put("activePeriod",pmMateEntry.getActivePeriod());
			map.put("expensesBudget",pmMateEntry.getExpensesBudget().toString());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}
	
	/**
	 * 根据企划案例条目ID
	 * @return
	 * @throws Exception
	 */
	public String getMateEntryByEntryId() throws Exception {
		String entryId = Struts2Utils.getParameter("mateEntryId");
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from PmMateEntry f where 1=1");
		if(StringUtils.isNotBlank(entryId)){
			hql.append(" and f.pmMateEntryId = :pmMateEntryId");
			values.put("pmMateEntryId",entryId);
		}
		hql.append(" order by f.createdDate desc ");
		page.setPageSize(10);
		page = pmMateEntryManager.findPage(page, hql.toString(), values);
		return "list";
	}
	
	public String doQuery() throws Exception{
		String activeTitle = Struts2Utils.getParameter("activeTitle");
		String activePeriod = Struts2Utils.getParameter("activePeriod");
		String expectedResults = Struts2Utils.getParameter("expectedResults");
		String expensesBudget_min = Struts2Utils.getParameter("expensesBudget_min");
		String expensesBudget_max = Struts2Utils.getParameter("expensesBudget_max");
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from PmMateEntry f where 1=1");
		
		if(StringUtils.isNotBlank(activeTitle)){
			hql.append(" and f.activeTitle like :activeTitle");
			values.put("activeTitle", "%"+activeTitle+"%");
		}
		if(StringUtils.isNotBlank(activePeriod)){
			hql.append(" and f.activePeriod like :activePeriod");
			values.put("activePeriod", "%"+activePeriod+"%");
		}
		if(StringUtils.isNotBlank(expectedResults)){
			hql.append(" and f.expectedResults like :expectedResults");
			values.put("expectedResults", "%"+expectedResults+"%");
		}
		if(StringUtils.isNotBlank(expensesBudget_min)){
			hql.append(" and f.expensesBudget>= :expensesBudget_min");
			values.put("expensesBudget_min", BigDecimal.valueOf(Double.valueOf(expensesBudget_min)));
		}
		if(StringUtils.isNotBlank(expensesBudget_max)){
			hql.append(" and f.expensesBudget<= :expensesBudget_max");
			values.put("expensesBudget_max", BigDecimal.valueOf(Double.valueOf(expensesBudget_max)));
		}
		hql.append(" order by f.createdDate desc ");
		
		page.setPageSize(10);
		page = pmMateEntryManager.findPage(page, hql.toString(), values);
		return "list";
	}
	
	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		String pmMateModuId = Struts2Utils.getParameter("pmMateModuId");
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append(" from PmMateEntry f where 1=1");
		if(StringUtils.isNotBlank(pmMateModuId)){
			hql.append(" and f.pmMateModu.pmMateModuId = :pmMateModuId");
			values.put("pmMateModuId", pmMateModuId==null?"":pmMateModuId);
		}
		hql.append(" order by f.createdDate desc ");
		page.setPageSize(10);
		page = pmMateEntryManager.findPage(page, hql.toString(), values);
		return "list";
	}
	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(getPmMateEntryId())){
			entity = pmMateEntryManager.getPmMateEntry(getPmMateEntryId());
		}else{
			entity = new PmMateEntry();
		}
		
	}
	
	/**
	 * 添加操作，判断添加的编号是否已经存在
	 * @return
	 */
	public String noRepeat(){
		String pmMateEntryNo = Struts2Utils.getParameter("pmMateEntryNo");
		try {
			if(StringUtils.isNotBlank(pmMateEntryNo)){
				entity = pmMateEntryManager.noRepeat(pmMateEntryNo);
				if(null!=entity){
					Struts2Utils.renderText(entity.getPmMateEntryNo());
				}else{
					Struts2Utils.renderText("");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void prepareSave() {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = pmMateEntryManager.getEntity(getId());
		} else {
			entity = new PmMateEntry();
		}
	}
	
	
	@Override
	public String save() throws Exception {
		
		try {
			//保存企划案例信息
			if (StringUtils.isNotBlank(fileSn_pic)) {
				entity.setUploadPicFlg(BigDecimal.valueOf(Long.valueOf(fileSn_pic)));//1表示有图片
			}else{
				entity.setUploadPicFlg(BigDecimal.valueOf(Long.valueOf(fileSn_pic)));//0表示无图片
			}
			//System.out.println("1111: "+entity.getPmMateEntryId()+" 222: "+entity.getPmMateModu().getPmMateModuId());
			pmMateEntryManager.savePmMateEntry(entity);
			if(StringUtils.isNotBlank(bizEntityTempId)){
				appAttachFileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(bizEntityTempId,DictContants.BIZ_MODULE_CD_PROJECTMANAGER);
				for(AppAttachFile appAttachFile :appAttachFileList){
					//更新附件表中的外键bizEntityId
					appAttachFileManager.updateBizEntityId(bizEntityTempId,entity.getPmMateEntryId(),DictContants.BIZ_MODULE_CD_PROJECTMANAGER);
				}
			}
			if(StringUtils.isNotBlank(entity.getPmMateEntryId())){
				Struts2Utils.renderJson("{'status':'success','pmMateEntryId':'"+entity.getPmMateEntryId()+"','pmMateModuId':'"+entity.getPmMateModu().getPmMateModuId()+"','pmMateEntryNo':'"+entity.getPmMateEntryNo()+"'}");
			}else{
				Struts2Utils.renderJson("{'status':'success','pmMateEntryId':'','pmMateModuId':'','pmMateEntryNo':''}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderText("{'status':'fail','pmMateEntryId':''}");
//			Struts2Utils.renderHtml("fail");
		}
		return null;
	}
	
	/**
	 * 生成文件名
	 * 
	 * @param uploadFileName1
	 * @return
	 */
	private String getFileName(String uploadFileName1) {
		String tmpName;
		if (!StringUtils.isEmpty(fileName)) {
			tmpName = fileName;
		} else {
			Date now = new Date();
			String dateFormat = DateOperator.formatDate(now, "yyyyMMddHHmmss");
			tmpName = dateFormat + RandomUtils.generateString(4) + getSuffix(uploadFileName1);
		}
		return tmpName;
	}
	
	/**
	 * 取得文件后缀
	 * 
	 * @param fileNameTmp
	 * @return
	 */
	private String getSuffix(String fileNameTmp) {
		String suffix = null;
		if (StringUtils.isNotEmpty(fileNameTmp)) {
			suffix = fileNameTmp.substring(fileNameTmp.lastIndexOf("."));
		}
		return suffix;
	}
	
	/**
	 * 上传附件(弹出框)--用一个页面过度
	 * @return
	 */
	public String attachUpload() {
		uploadSn = Struts2Utils.getParameter("uploadSn");
		return "attachmentUpload";
	}
	
	@Override
	public PmMateEntry getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<PmMateEntry> getPmMateEntryList() {
		return pmMateEntryList;
	}
	public void setPmMateEntryList(List<PmMateEntry> pmMateEntryList) {
		this.pmMateEntryList = pmMateEntryList;
	}
	public PmMateEntry getEntity() {
		return entity;
	}
	public void setEntity(PmMateEntry entity) {
		this.entity = entity;
	}
	public List<EntityVO> getParentIdList() {
		return parentIdList;
	}
	public void setParentIdList(List<EntityVO> parentIdList) {
		this.parentIdList = parentIdList;
	}
	public String[] getParentModuleId() {
		return parentModuleId;
	}
	public void setParentModuleId(String[] parentModuleId) {
		this.parentModuleId = parentModuleId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File[] getUpload_pic() {
		return upload_pic;
	}
	public void setUpload_pic(File[] uploadPic) {
		upload_pic = uploadPic;
	}
	public String[] getUpload_picFileName() {
		return upload_picFileName;
	}
	public void setUpload_picFileName(String[] uploadPicFileName) {
		upload_picFileName = uploadPicFileName;
	}
	public String[] getUpload_picContentType() {
		return upload_picContentType;
	}
	public void setUpload_picContentType(String[] uploadPicContentType) {
		upload_picContentType = uploadPicContentType;
	}
	public File[] getUpload_prj() {
		return upload_prj;
	}
	public void setUpload_prj(File[] uploadPrj) {
		upload_prj = uploadPrj;
	}
	public String[] getUpload_prjFileName() {
		return upload_prjFileName;
	}
	public void setUpload_prjFileName(String[] uploadPrjFileName) {
		upload_prjFileName = uploadPrjFileName;
	}
	public String[] getUpload_prjContentType() {
		return upload_prjContentType;
	}
	public void setUpload_prjContentType(String[] uploadPrjContentType) {
		upload_prjContentType = uploadPrjContentType;
	}
	public String[] getAllowTypes() {
		return allowTypes;
	}
	public void setAllowTypes(String[] allowTypes) {
		this.allowTypes = allowTypes;
	}
	public String getFilterType() {
		return filterType;
	}
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	public String getFileSn_pic() {
		return fileSn_pic;
	}
	public void setFileSn_pic(String fileSnPic) {
		fileSn_pic = fileSnPic;
	}
	public String getFileSn_prj() {
		return fileSn_prj;
	}
	public void setFileSn_prj(String fileSnPrj) {
		fileSn_prj = fileSnPrj;
	}
	public String getPmMateModuId() {
		return pmMateModuId;
	}
	public void setPmMateModuId(String pmMateModuId) {
		this.pmMateModuId = pmMateModuId;
	}
	public AppAttachFile getAppAttachFile() {
		return appAttachFile;
	}
	public void setAppAttachFile(AppAttachFile appAttachFile) {
		this.appAttachFile = appAttachFile;
	}
	public String getPmMateEntryId() {
		return pmMateEntryId;
	}
	public void setPmMateEntryId(String pmMateEntryId) {
		this.pmMateEntryId = pmMateEntryId;
	}
	public String getUploadSn() {
		return uploadSn;
	}
	public void setUploadSn(String uploadSn) {
		this.uploadSn = uploadSn;
	}
	public List<PmMateEntryVo> getPmMateEntryVoList() {
		return pmMateEntryVoList;
	}
	public void setPmMateEntryVoList(List<PmMateEntryVo> pmMateEntryVoList) {
		this.pmMateEntryVoList = pmMateEntryVoList;
	}
	public List<AppAttachFile> getAppAttachFileList() {
		return appAttachFileList;
	}
	public void setAppAttachFileList(List<AppAttachFile> appAttachFileList) {
		this.appAttachFileList = appAttachFileList;
	}
	public String getBizEntityId() {
		return bizEntityId;
	}
	public void setBizEntityId(String bizEntityId) {
		this.bizEntityId = bizEntityId;
	}
	public String getBizEntityTempId() {
		return bizEntityTempId;
	}
	public void setBizEntityTempId(String bizEntityTempId) {
		this.bizEntityTempId = bizEntityTempId;
	}
	public List<PmEntryResRelation> getPmEntryResRelationList() {
		return pmEntryResRelationList;
	}
	public void setPmEntryResRelationList(List<PmEntryResRelation> pmEntryResRelationList) {
		this.pmEntryResRelationList = pmEntryResRelationList;
	}
	public int getDisplayNameSize() {
		return displayNameSize;
	}
	public void setDisplayNameSize(int displayNameSize) {
		this.displayNameSize = displayNameSize;
	}
	
	

}

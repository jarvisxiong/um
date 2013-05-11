package com.hhz.ump.web.oa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.oa.OaMeetingFileManager;
import com.hhz.ump.dao.oa.OaMeetingFolderManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaMeeting;
import com.hhz.ump.entity.oa.OaMeetingFile;
import com.hhz.ump.entity.oa.OaMeetingFolder;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.Util;

/**
 * 
 * 类名 CompanyMeetingAction 创建者 huangbijin 创建日期 2010-9-20 描述 会议纪要Action类
 */
@Namespace("/oa")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "company-meeting.action", type = "redirect") })
public class CompanyMeetingAction extends CrudActionSupport<OaMeeting> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OaMeetingFolderManager oaMeetingFolderManager;
	@Autowired
	private OaMeetingFileManager oaMeetingFileManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	private Page<OaMeetingFile> filePage = new Page<OaMeetingFile>(15);
	private List<CompanyMeetingFileVO> fileVoList = new ArrayList<CompanyMeetingFileVO>();

	private OaMeetingFolder folderEntity;
	private OaMeetingFile fileEntity;

	private String folderId;
	private String parentFolderId;
	private String parentFolderName;
	private String folderName;
	private String visibleToPersons;

	private String visibleToPersonNames;
	private String visibleToPersonCds;

	private String visibleToPersonData;
	private String tempEntityId;
	
	private String fileId;
	private String fileName;
	private String compere;
	private String recorder;
	private Date   beginDate;
	private long   sequenceNo;
	
	private String bizEntityId;
	private List<AppAttachFile> attachList;

	// 把创建者用户名转为中文名字
	private Map<String, String> mapCreatorName = new HashMap<String, String>();

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	/**
	 * 构造会议纪要目录树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String loadFolderTree() throws Exception {
		String folderJSON = "";
		if (SpringSecurityUtils.hasRole(DictContants.A_COMPANYMEET_ADMIN)) {
			folderJSON = oaMeetingFolderManager.buildFolderTreeJSON(folderId,
					DictContants.COMPANY_FILING_MODULE_CD_MEETING, null);
		} else {
			folderJSON = oaMeetingFolderManager.buildFolderTreeJSON(folderId,
					DictContants.COMPANY_FILING_MODULE_CD_MEETING, SpringSecurityUtils.getCurrentUiid());
		}
		Struts2Utils.renderText(folderJSON);
		return null;
	}

	/**
	 * 显示指定目录的文件列表，将文件和它的附件列表包装为一个VO用于页面展示
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fileList() throws Exception {
		if (StringUtils.isBlank(folderId))
			throw new IllegalArgumentException("目录ID不能为空");

		filePage.setOrderBy("createdDate");
		filePage.setOrder(Page.DESC);

		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_oaMeetingFolder.oaMeetingFolderId", folderId));
		filters.add(new PropertyFilter("EQS_deletedFlg", "0"));
		filters.add(new PropertyFilter("EQS_disabledFlg", "0"));
		filePage = oaMeetingFileManager.findPage(filePage, filters);

		for (OaMeetingFile file : filePage.getResult()) {
			CompanyMeetingFileVO vo = new CompanyMeetingFileVO();
			vo.setFile(file);
			vo.setAttachList(buildAttachList(file.getOaMeetingFileId()));
			fileVoList.add(vo);

			mapCreatorName.put(file.getCreator(), CodeNameUtil.getUserNameByCd(file.getCreator()));
		}

		return "fileList";
	}

	/**
	 * 打开新增文件界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inputFile() throws Exception {
		if (StringUtils.isBlank(folderId))
			throw new IllegalArgumentException("目录ID不能为空");

		bizEntityId = fileId;
		if (StringUtils.isBlank(fileId)) {
			tempEntityId = RandomUtils.generateTmpEntityId();
			bizEntityId = tempEntityId;
		} else {
			fileEntity = oaMeetingFileManager.getEntity(fileId);
		}
		attachList = buildAttachList(bizEntityId);

		return "inputFile";
	}

	/**
	 * 保存文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveFile() throws Exception {
		if (StringUtils.isBlank(folderId))
			throw new IllegalArgumentException("目录ID不能为空");

		folderEntity = oaMeetingFolderManager.getEntity(folderId);
		if (folderEntity == null)
			throw new IllegalArgumentException("目录不存在id=" + folderId);

		if (StringUtils.isNotBlank(fileId)) {
			fileEntity = oaMeetingFileManager.getEntity(fileId);
		} else {
			fileEntity = new OaMeetingFile();
			fileEntity.setOaMeetingFolder(folderEntity);
			fileEntity.setDisabledFlg("0");
			fileEntity.setDeletedFlg("0");
		}
		fileEntity.setFileName(fileName);
		fileEntity.setCompere(compere);
		fileEntity.setRecorder(recorder);
		fileEntity.setBeginDate(beginDate);
		oaMeetingFileManager.saveOaMeetingFile(fileEntity);

		// 如果是新建，则要把附件替换为真正的实体ID.
		if (StringUtils.isBlank(fileId)) {
			appAttachFileManager.updateTmpFile(tempEntityId, fileEntity.getOaMeetingFileId());
		}

		Struts2Utils.renderText("done");
		return null;
	}

	/**
	 * 删除文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteFile() throws Exception {
		if (StringUtils.isBlank(fileId))
			throw new IllegalArgumentException("文件ID不能为空");

		fileEntity = oaMeetingFileManager.getEntity(fileId);
		if (fileEntity == null)
			throw new IllegalArgumentException("文件ID无效");

		fileEntity.setDeletedFlg("1");
		oaMeetingFileManager.saveOaMeetingFile(fileEntity);

		Struts2Utils.renderText("done");
		return null;
	}

	/**
	 * 增加新的目录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String inputFolder() throws Exception {
		if (StringUtils.isNotBlank(folderId)) {
			folderEntity = oaMeetingFolderManager.getEntity(folderId);
			String parentId = folderEntity.getParentFolderId();

			//上级目录
			if ("-1".equals(parentId)) {
				parentFolderName = "";
			} else {
				parentFolderName = oaMeetingFolderManager.getEntity(parentId).getFolderName();
			}

			//可见人员
			visibleToPersonCds = Util.clob2String(folderEntity.getVisibleToPersons());
			if ("all".equalsIgnoreCase(visibleToPersonCds)) {
				visibleToPersonNames = "全部";
			} else {
				visibleToPersonNames = CodeNameUtil.getUserNamesByUiids(visibleToPersonCds, ";");
			}

			visibleToPersonData = buildVisibleToPersonData(visibleToPersonCds);
		}
		return "inputFolder";
	}

	private String buildVisibleToPersonData(String personIds) {
		if (StringUtils.isBlank(personIds) || "all".equals(personIds))
			return "";

		String tempId = null;
		String tempName = null;

		String[] ids = personIds.split(";");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < ids.length; i++) {
			tempId = ids[i];
			if (StringUtils.isBlank(tempId)) {
				continue;
			}
			tempName = CodeNameUtil.getUserNameByCd(tempId);
			if (StringUtils.isBlank(tempName)) {
				if (i == ids.length - 1) {
					result = result.deleteCharAt(result.length() - 1);
				}
				continue;
			}
			result.append("{\"uiid\":\"" + tempId + "\", \"userName\":\"" + tempName + "\"}");
			if (i < ids.length - 1) {
				result.append("|");
			}
		}

		return result.toString();
	}

	/**
	 * 保存目录信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveFolder() throws Exception {
		if (StringUtils.isNotBlank(folderId)) {
			folderEntity = oaMeetingFolderManager.getEntity(folderId);
		} else {
			folderEntity = new OaMeetingFolder();
		}

		if (StringUtils.isNotBlank(visibleToPersonCds)) {//5- all;x;
			if(visibleToPersonCds.indexOf("all;") == 0 && visibleToPersonCds.length()>=5){
				String tmp = visibleToPersonCds.replace("all;", "");
				folderEntity.setVisibleToPersons(new ClobImpl(tmp));
			}else{
				folderEntity.setVisibleToPersons(new ClobImpl(visibleToPersonCds));
			}
		} else {
			folderEntity.setVisibleToPersons(new ClobImpl("all"));
		}


		folderEntity.setFolderName(this.getFolderName());
		folderEntity.setParentFolderId(StringUtils.isNotBlank(parentFolderId) ? parentFolderId : "-1");
		folderEntity.setDeletedFlg("0");
		folderEntity.setSequenceNo(sequenceNo);
		System.out.println("sequenceNo="+sequenceNo);
		folderEntity.setBizModuleCd(DictContants.COMPANY_FILING_MODULE_CD_MEETING);

		oaMeetingFolderManager.saveOaMeetingFolder(folderEntity);

		return SUCCESS;
	}

	/**
	 * 删除目录信息: 删除并非真正的物理删除，而是将删除字段设置为 1，表明该记录已经删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delFolder() throws Exception {
		if (StringUtils.isBlank(folderId))
			throw new IllegalArgumentException("待删除的目录ID不能为空");

		folderEntity = oaMeetingFolderManager.getEntity(folderId);

		if (folderEntity == null)
			throw new IllegalArgumentException("指定的会议纪要目录不存在");

		folderEntity.setDeletedFlg("1");
		oaMeetingFolderManager.saveOaMeetingFolder(folderEntity);

		return SUCCESS;
	}

	/**
	 * 获取附件列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchAttachList() throws Exception {
		attachList = buildAttachList(bizEntityId);
		return "attachList";
	}

	/**
	 * 获取指定文件的附件列表
	 * 
	 * @param entityId
	 * @return
	 */
	private List<AppAttachFile> buildAttachList(String entityId) {
//		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
//		filters.add(new PropertyFilter("EQS_bizEntityId", entityId));
//		filters.add(new PropertyFilter("NEQS_statusCd", "0"));
//		return appAttachFileManager.find(filters);

    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("bizEntityId", entityId);
    	params.put("statusCd", "0");
    	String hql = "from AppAttachFile t where t.bizEntityId = :bizEntityId and t.statusCd != :statusCd order by t.createdDate desc";
    	return appAttachFileManager.find(hql, params);
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public OaMeeting getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public OaMeetingFolder getFolderEntity() {
		return folderEntity;
	}

	public String getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(String parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getParentFolderName() {
		return parentFolderName;
	}

	public Page<OaMeetingFile> getFilePage() {
		return filePage;
	}

	public void setFilePage(Page<OaMeetingFile> filePage) {
		this.filePage = filePage;
	}

	public String getTempEntityId() {
		return tempEntityId;
	}

	public void setTempEntityId(String tempEntityId) {
		this.tempEntityId = tempEntityId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public OaMeetingFile getFileEntity() {
		return fileEntity;
	}

	public String getBizEntityId() {
		return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
		this.bizEntityId = bizEntityId;
	}

	public List<AppAttachFile> getAttachList() {
		return attachList;
	}

	public void setAttachList(List<AppAttachFile> attachList) {
		this.attachList = attachList;
	}

	public List<CompanyMeetingFileVO> getFileVoList() {
		return fileVoList;
	}

	public void setFileVoList(List<CompanyMeetingFileVO> fileVoList) {
		this.fileVoList = fileVoList;
	}

	public Map<String, String> getMapCreatorName() {
		return mapCreatorName;
	}

	public void setMapCreatorName(Map<String, String> mapCreatorName) {
		this.mapCreatorName = mapCreatorName;
	}

	public void setFolderEntity(OaMeetingFolder folderEntity) {
		this.folderEntity = folderEntity;
	}

	public void setFileEntity(OaMeetingFile fileEntity) {
		this.fileEntity = fileEntity;
	}

	public String getVisibleToPersons() {
		return visibleToPersons;
	}

	public void setVisibleToPersons(String visibleToPersons) {
		this.visibleToPersons = visibleToPersons;
	}

	public String getVisibleToPersonNames() {
		return visibleToPersonNames;
	}

	public void setVisibleToPersonNames(String visibleToPersonNames) {
		this.visibleToPersonNames = visibleToPersonNames;
	}

	public String getVisibleToPersonData() {
		return visibleToPersonData;
	}

	public void setVisibleToPersonData(String visibleToPersonData) {
		this.visibleToPersonData = visibleToPersonData;
	}

	public String getCompere() {
		return compere;
	}

	public void setCompere(String compere) {
		this.compere = compere;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public long getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getVisibleToPersonCds() {
		return visibleToPersonCds;
	}

	public void setVisibleToPersonCds(String visibleToPersonCds) {
		this.visibleToPersonCds = visibleToPersonCds;
	}
	
}

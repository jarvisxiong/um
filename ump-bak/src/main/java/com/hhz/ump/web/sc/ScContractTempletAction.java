package com.hhz.ump.web.sc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.sc.ScContractTempletManager;
import com.hhz.ump.dao.sc.ScContractTempletTypeManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.sc.ScContractAttachUpload;
import com.hhz.ump.entity.sc.ScContractTemplet;
import com.hhz.ump.entity.sc.ScContractTempletInfo;
import com.hhz.ump.entity.sc.ScContractTempletType;
import com.hhz.ump.web.sc.vo.VoScContractTemplet;

/**
 * 合同模板
 * 
 * @author qlb 2/8/2012 version 1.0.0
 * 
 */
public class ScContractTempletAction extends CrudActionSupport<ScContractTemplet> {
	
	/**
	 * 上传文件最大限制,默认20M
	 */
	public static final Long MAX_FILE_SIZE = 20480000L;
	private static final long serialVersionUID = 1L;
	/*
	 * 附件
	 */
	private File[] attachment;
	private String[]attachmentFileName;
	//附件类型
	private String[] attachmentContentType;
	

	private File[] upload;
	private String[] uploadFileName;
	private String CON_STANDARD = "1";
	private String CON_NONSTANDARD = "0";
	private ScContractAttachUpload conUpload;
	

	private List<VoScContractTemplet> voContractTempletList;
	private ScContractTemplet entity;
	@Autowired
	private ScContractTempletManager contractTempletManager;
	@Autowired
	private ScContractTempletTypeManager contractTempletTypeManager;
	
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	// 
	private Map<String, String> coOpinions = new LinkedHashMap<String, String>();
	
	private List<AppAttachFile>appAttachFileList=new ArrayList<AppAttachFile>();
	

	private String selectOptions;
	// 分页搜索
	@SuppressWarnings("unchecked")
	private Page voPage = new Page(10);
	/**
	 * 模板最大序號
	 */
	private Long templetMaxSeq = Long.valueOf("1");

	
	
	
	
	/**
	 * @return the coOpinions
	 */
	public Map<String, String> getCoOpinions() {
		return coOpinions;
	}

	/**
	 * @param coOpinions
	 *            the coOpinions to set
	 */
	public void setCoOpinions(Map<String, String> coOpinions) {
		this.coOpinions = coOpinions;
	}

	/**
	 * @return the selectOptions
	 */
	public String getSelectOptions() {
		return selectOptions;
	}

	/**
	 * @param selectOptions
	 *            the selectOptions to set
	 */
	public void setSelectOptions(String selectOptions) {
		this.selectOptions = selectOptions;
	}

	/**
	 * @return the templetMaxSeq
	 */
	public Long getTempletMaxSeq() {
		return templetMaxSeq;
	}

	/**
	 * @param templetMaxSeq
	 *            the templetMaxSeq to set
	 */
	public void setTempletMaxSeq(Long templetMaxSeq) {
		this.templetMaxSeq = templetMaxSeq;
	}

	/**
	 * @return the voPage
	 */
	public Page getVoPage() {
		return voPage;
	}

	/**
	 * @param voPage
	 *            the voPage to set
	 */
	public void setVoPage(Page voPage) {
		this.voPage = voPage;
	}

	/**
	 * @return the voContractTempletList
	 */
	public List<VoScContractTemplet> getVoContractTempletList() {
		return voContractTempletList;
	}

	/**
	 * @param voContractTempletList
	 *            the voContractTempletList to set
	 */
	public void setVoContractTempletList(List<VoScContractTemplet> voContractTempletList) {
		this.voContractTempletList = voContractTempletList;
	}

	/**
	 * @return the uploadFileName
	 */
	public String[] getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * @return the upload
	 */
	public File[] getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	@Override
	public String delete() throws Exception {
		StringBuffer strBuffer = new StringBuffer("{status:");
		// TODO Auto-generated method stub
		try {
			String scId = this.getId();
			prepareModel();

			entity.setIsDel(Byte.valueOf("1"));
			contractTempletManager.saveScContractTemplet(entity);
			if (entity != null && entity.getScContractTempletInfos() != null && entity.getScContractTempletInfos().size() > 0) {
				List<ScContractTempletInfo> delContInfoList = new ArrayList<ScContractTempletInfo>();

				delContInfoList.addAll(entity.getScContractTempletInfos());
				Session session = contractTempletManager.getDao().getSession();
				int count = delContInfoList.size();
				int submitTime = count % 100;// 获取插入记录个数

				for (int i = 0; i < count; i++) {
					ScContractTempletInfo scConTempletInfo = delContInfoList.get(i);
					// 设置状态为逻辑删除
					scConTempletInfo.setIsDel(Byte.valueOf("1"));
					session.saveOrUpdate(delContInfoList.get(i));
					if (i % 100 == 0) { // 每100条提交一次数据
						session.flush();
						session.clear();

					} else if (submitTime > 0 && i == count - 1) {
						// 如果余数不为0则说明需要多提交一次数据，否则数据则会少生成数据
						session.flush();
						session.clear();

					}
				}
			}
			strBuffer.append("true}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}

		Struts2Utils.renderText(strBuffer.toString());
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
		String bizModuleCd=Struts2Utils.getParameter("bizmodulecd");
		if(StringUtils.isBlank(bizModuleCd)){			
			bizModuleCd="sctemplet";
		}
		if (StringUtils.isNotBlank(getId())) {
			entity = contractTempletManager.getEntity(getId());
			
			appAttachFileList=appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(getId(), bizModuleCd);
		} else {

			entity = new ScContractTemplet();
			getRecordSequenceNo();
			entity.setSequenceNo(templetMaxSeq);
		}

		return "input";
	}

	/**
	 * @return the entity
	 */
	public ScContractTemplet getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(ScContractTemplet entity) {
		this.entity = entity;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = contractTempletManager.getEntity(getId());
		} else {

			entity = new ScContractTemplet();
		}

	}

	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}

	@Override
	public String save() throws Exception {

		return null;
	}

	@Override
	public ScContractTemplet getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	/**
	 * 获取合同状态列表
	 * 
	 * @return
	 */
	public String constacdlist() {

		queryMyConStandardType();

		return "statusCdList";

	}
/**
 * 合同模板增加｜修改 相当于save()
 * @author qilb 2012-6-8
 * @throws IOException
 */
	public void uploadSingleFile() throws IOException {
		// 附件操作实体类
		conUpload = new ScContractAttachUpload();
		StringBuffer strBuffer = new StringBuffer("{status:");

		String sequenceNo = Struts2Utils.getParameter("sequenceNo");
		String templetName = Struts2Utils.getParameter("templetName");
		String isvalid = Struts2Utils.getParameter("isvalid");
		String isstandard = Struts2Utils.getParameter("isstandard");
		String iscurversion = Struts2Utils.getParameter("iscurversion");
		// String enabledDate=Struts2Utils.getParameter("enabledDate");

		String templetId = Struts2Utils.getParameter("templetId");
		String remark = Struts2Utils.getParameter("remark");
		String templetPath = Struts2Utils.getParameter("templetPath");
		String contractTempletTypeId = Struts2Utils.getParameter("templetTypeId");
		String recordVersion = Struts2Utils.getParameter("recordVersion");
		String bizModuleCd=Struts2Utils.getParameter("bizmodulecd");
		if(StringUtils.isBlank(bizModuleCd)){			
			bizModuleCd="sctemplet";
		}
		if (StringUtils.isBlank(recordVersion)) {

			recordVersion = "0";
		}
		try {
			prepareModel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String savPath = conUpload.getSavePath("sctemplet", null, true);
		try {
			conUpload.getDir(null);

			
			Long maxFileSize = 0l;
			//统计一次上传总的附件大小
			for (int i = 0; upload != null && i < upload.length; i++) {
				maxFileSize += upload[i].length();
			}			
		
			for (int i = 0; attachment != null && i < attachment.length; i++) {
				
				maxFileSize += attachment[i].length();
			}
			//一次上传总的附件大小不能大于20M
			if (maxFileSize > MAX_FILE_SIZE) {
				Struts2Utils.renderHtml("maxSizeError-" + MAX_FILE_SIZE / (1024 * 1000));
				return ;
			}
			
			
			for (int i = 0; upload != null && i < upload.length; i++) {
				if (upload[i] != null) {
					if (StringUtils.isNotBlank(templetId) && StringUtils.isNotBlank(templetPath)) {

						// 如果修改模板时上传了新的模板文件则删除旧文件
						conUpload.deleteFile(templetPath);

						templetPath = "";
					}

					String newFileName = conUpload.genFileName(uploadFileName[i]);
					File fout = new File(savPath, newFileName);
					FileOutputStream fos = new FileOutputStream(fout);
					FileInputStream fin = new FileInputStream(upload[i]);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fin.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					fin.close();
					upload[i].delete();
					templetPath = savPath + "\\" + newFileName;

				}
			}
			if (StringUtils.isNotBlank(isvalid) && isvalid.equals("on")) {
				isvalid = "1";

			} else {
				isvalid = "0";

			}
			if (StringUtils.isNotBlank(isstandard) && isstandard.equals("on")) {
				isstandard = "1";
			} else {

				isstandard = "0";
			}
			if (StringUtils.isNotBlank(iscurversion) && iscurversion.equals("on")) {
				iscurversion = "1";

				Map<String, Object> values = new HashMap<String, Object>();
				values.put("templetTypeId", contractTempletTypeId);
				values.put("isstandard", Byte.valueOf(isstandard));
				StringBuffer sqlBuf = new StringBuffer();

				sqlBuf.append("update ScContractTemplet t");
				sqlBuf.append(" set t.iscurversion=0 ,t.isvalid=0 ");
				sqlBuf.append(" where t.scContractTempletType.contractTempletTypeId=:templetTypeId and t.isstandard=:isstandard ");
				Query query = contractTempletManager.getDao().createQuery(sqlBuf.toString(), values);

				query.executeUpdate();
			} else {
				iscurversion = "0";

			}

			if (StringUtils.isNotBlank(templetId)) {// 如果ID不为空则是增加，否则为修改
				entity = contractTempletManager.getEntity(templetId);
				entity.setUpdatedDate(new Date());
			} else {
				entity = new ScContractTemplet();
				entity.setCreatedDate(new Date());
				entity.setRecordVersion(Long.valueOf(recordVersion));
			}
			ScContractTempletType contractTempletType = contractTempletTypeManager.getEntity(contractTempletTypeId);
			entity.setScContractTempletType(contractTempletType);
			entity.setSequenceNo(Long.valueOf(sequenceNo));
			entity.setTempletName(templetName);
			entity.setTempletPath(templetPath);
			entity.setIsvalid(Byte.valueOf(isvalid));
			entity.setIscurversion(Byte.valueOf(iscurversion));
			// 是否标准｜非标准合同
			entity.setIsstandard(Byte.valueOf(isstandard));
			entity.setRemark(remark);

			if (entity.getIsvalid() == 1) {

				entity.setEnabledDate(new Date());
			}

			contractTempletManager.saveScContractTemplet(entity);
			
			List<AppAttachFile> lstAdd = new ArrayList<AppAttachFile>();
			
			//上传附件
			for (int i = 0; attachment != null && i < attachment.length; i++) {
				if (attachment[i] != null) {				
					String newFileName = "sct_"+conUpload.genFileName(attachmentFileName[i]);
					File fout = new File(savPath, newFileName);
					FileOutputStream fos = new FileOutputStream(fout);
					FileInputStream fin = new FileInputStream(attachment[i]);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fin.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					fin.close();
					attachment[i].delete();
			
					//公共附件
					AppAttachFile appAttachFile=new AppAttachFile();
					appAttachFile.setBizEntityId(entity.getContractTempletId());
					appAttachFile.setBizModuleCd(bizModuleCd);
					appAttachFile.setRealFileName(attachmentFileName[i]);
					appAttachFile.setFileName(newFileName);
					appAttachFile.setFilePath(savPath);
					appAttachFile.setFileSize(new BigDecimal(fout.length()));
					appAttachFile.setFileTypeName(attachmentContentType[i]);
					//不能为空或为0否则数据无法搜索
					appAttachFile.setStatusCd("1");
					lstAdd.add(appAttachFile);
							
					

				}
			}
			if(lstAdd!=null && lstAdd.size()>0){
				
				//保存附件
				appAttachFileManager.saveAppAttachFiles(lstAdd,null,entity.getContractTempletId());
				
			}

			strBuffer.append("true}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
	}
	/**
	 * @return the attachmentContentType
	 */
	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}

	/**
	 * @param attachmentContentType the attachmentContentType to set
	 */
	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	/**
	 * 合同模板对应的附件
	 * @param scTempletId 模板ID
	 * @return 附件列表
	 */
	public String attachLoadList(){
		
		// TODO Auto-generated method stub
		String bizModuleCd=Struts2Utils.getParameter("bizmodulecd");
		if(StringUtils.isBlank(bizModuleCd)){			
			bizModuleCd="sctemplet";
		}
		if (StringUtils.isNotBlank(getId())) {
			entity = contractTempletManager.getEntity(getId());
			
			appAttachFileList=appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(getId(), bizModuleCd);
		} 

		return "attachLoadList";
	}

	/**
	 * 删除附件
	 * @param 
	 */
public void delAttach(){
	
	try {
		// 附件操作实体类
		conUpload = new ScContractAttachUpload();
		AppAttachFile appAttachFile = appAttachFileManager.getEntity(getId());
		
		if(appAttachFile!=null && appAttachFile.getBizEntityId()!=null){
			appAttachFileManager.delete(appAttachFile);
			conUpload.deleteFile(appAttachFile.getFilePath()+"\\"+appAttachFile.getFileName());
			Struts2Utils.renderText("true");
			return;
		}
		
		Struts2Utils.renderText("false");
		
	} catch (ServiceException e) {
		logger.error(e.getMessage(), e);
		Struts2Utils.renderText("false");
	}
	
	
}
	/**
	 * 获取记录中最大序号
	 */
	@SuppressWarnings("unchecked")
	public void getRecordSequenceNo() {

		Map<String, Object> values = new HashMap<String, Object>();
		Object[] obj = null;
		StringBuffer sqlBuf = new StringBuffer();

		sqlBuf.append("select ");
		sqlBuf.append(" to_char(max(t.sequence_no))");

		sqlBuf.append(" from sc_contract_templet t ");

		List maxSequenceList = contractTempletManager.findBySql(sqlBuf.toString(), values);
		String value = maxSequenceList.toString();
		if (maxSequenceList != null && StringUtils.isNotBlank(value) && value.indexOf("null") < 0 && maxSequenceList.size() > 0) {

			templetMaxSeq = Long.valueOf((String) maxSequenceList.get(0));
		}
		templetMaxSeq++;
	}

	/**
	 * 根据搜索条件,搜索投标台账列表
	 * <p/>
	 * 搜索条件有
	 * <p/>
	 * 
	 * @param selectItemCds
	 * @param ...TODO
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String loadList() {

		String pageNo = Struts2Utils.getParameter("pageNo");
		String rows = Struts2Utils.getParameter("rows");
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String isView = Struts2Utils.getParameter("isView");
		String isstandard = Struts2Utils.getParameter("divisstandard");
		String curUser = SpringSecurityUtils.getCurrentUiid();
		// 高级搜索条件
		String sctempletTypeId = Struts2Utils.getParameter("sctempletTypeId");

		Map<String, Object> values = new HashMap<String, Object>();
		Object[] obj = null;
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select t.contract_templet_id,");

		sqlBuf.append(" t.templet_name,");
		sqlBuf.append(" to_char(t.isvalid),");
		sqlBuf.append(" to_char(t.sequence_no),");
		sqlBuf.append(" t.templet_path,");
		sqlBuf.append(" t.remark,");
		sqlBuf.append(" t.contract_templet_type_id,");
		sqlBuf.append(" to_char(t.iscurversion),");
		sqlBuf.append(" to_char(t.record_version),");
		sqlBuf.append(" to_char(t.isstandard)");
		sqlBuf.append(" from sc_contract_templet t ").append(" where 1=1 and t.is_del is null ");
		/*
		 * if (StringUtils.isNotBlank(curUser)) {
		 * 
		 * sqlBuf.append(" and t.creator=:curUser "); values.put("curUser",
		 * curUser); }
		 */
		if (StringUtils.isNotBlank(isstandard)) {
			sqlBuf.append(" and t.isstandard=:isstandard ");
			values.put("isstandard", isstandard);

		}
		if (StringUtils.isNotBlank(sctempletTypeId)) {
			sqlBuf.append(" and t.contract_templet_type_id =:sctempletTypeId ");
			values.put("sctempletTypeId", sctempletTypeId);
		}

		// 设置默认排序方式
		if (StringUtils.isBlank(sortField)) {
			sqlBuf.append(" order by t.created_date desc ");
		} else {
			sqlBuf.append(" order by t." + sortField + " " + order);
		}

		if (pageNo != null) {
			voPage.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			voPage.setPageSize(Integer.valueOf(rows));
		}

		voPage = contractTempletManager.findPageSql(voPage, sqlBuf.toString(), values, new HashMap<String, Class>());
		List tmpList = voPage.getResult();
		voContractTempletList = new ArrayList<VoScContractTemplet>();

		VoScContractTemplet tmpVo = null;
		for (int i = 0; i < tmpList.size(); i++) {
			obj = (Object[]) tmpList.get(i);
			tmpVo = new VoScContractTemplet();

			try {

				tmpVo.setContractTempletId((String) obj[0]);
				tmpVo.setTempletName((String) obj[1]);
				tmpVo.setIsvalid(Byte.valueOf((String) obj[2]));
				tmpVo.setSequenceNo(Long.valueOf((String) obj[3]));
				tmpVo.setTempletPath((String) obj[4]);
				tmpVo.setRemark((String) obj[5]);
				tmpVo.setContractTempletTypeId((String) obj[6]);
				tmpVo.setIscurversion(obj[7]!=null?Byte.valueOf((String) obj[7]):0);
				tmpVo.setRecordVersion(Long.valueOf((String) obj[8]));
				tmpVo.setIsstandard(obj[9] != null ? Byte.valueOf((String) obj[9]) : 0);
				if (StringUtils.isNotBlank(isView) && tmpVo.getIscurversion() > 0) {
					tmpVo.setIsView(isView);
				} else if (StringUtils.isNotBlank(isView) && tmpVo.getIscurversion() < 1) {
					// 用户使用合同模板创建合同时只使用当前版本
					continue;
				} else {

					tmpVo.setIsView("0");
				}
				ScContractTempletType contractTempletType = contractTempletTypeManager.getEntity(tmpVo.getContractTempletTypeId());
				if (contractTempletType != null) {
					tmpVo.setContractTempletTypeName(contractTempletType.getTypeName());

				}

			} catch (Exception ee) {
				ee.printStackTrace();
			}
			voContractTempletList.add(tmpVo);
		}
		return "loadList";
	}

	@SuppressWarnings("unchecked")
	public void loadTemplet() {
		String sctempletTypeId = Struts2Utils.getParameter("sctempletTypeId");

		Map<String, Object> values = new HashMap<String, Object>();

		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select t.contract_templet_id,");

		sqlBuf.append(" t.templet_name,");
		sqlBuf.append(" to_char(t.isvalid),");
		sqlBuf.append(" to_char(t.sequence_no),");
		sqlBuf.append(" t.templet_path,");
		sqlBuf.append(" t.remark,");
		sqlBuf.append(" t.contract_templet_type_id,");
		sqlBuf.append(" to_char(t.iscurversion),");
		sqlBuf.append(" to_char(t.record_version)");
		sqlBuf.append(" from sc_contract_templet t ").append(" where 1=1 and t.is_del is null ");

		if (StringUtils.isNotBlank(sctempletTypeId)) {
			sqlBuf.append(" and t.contract_templet_type_id =:sctempletTypeId ");
			values.put("sctempletTypeId", sctempletTypeId);
		}

		voPage = contractTempletManager.findPageSql(voPage, sqlBuf.toString(), values, new HashMap<String, Class>());
		List tmpList = voPage.getResult();
		voContractTempletList = new ArrayList<VoScContractTemplet>();
		Object[] obj = null;
		VoScContractTemplet tmpVo = null;
		int count = 0;
		for (int i = 0; i < tmpList.size(); i++) {
			obj = (Object[]) tmpList.get(i);
			tmpVo = new VoScContractTemplet();
			try {

				tmpVo.setContractTempletId((String) obj[0]);
				tmpVo.setTempletName((String) obj[1]);
				tmpVo.setIsvalid(Byte.valueOf((String) obj[2]));
				tmpVo.setSequenceNo(Long.valueOf((String) obj[3]));
				tmpVo.setTempletPath((String) obj[4]);
				tmpVo.setRemark((String) obj[5]);
				tmpVo.setContractTempletTypeId((String) obj[6]);
				tmpVo.setIscurversion(Byte.valueOf((String) obj[7]));
				tmpVo.setRecordVersion(Long.valueOf((String) obj[8]));

				count++;
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			voContractTempletList.add(tmpVo);
		}
		Struts2Utils.renderJson(voContractTempletList);
	}

	public void queryMyConStandardType() {

		Date start = new Date();
		page.setPageSize(30);
		StringBuffer sqlBuf = new StringBuffer("");
		Map<String, Object> param = new HashMap<String, Object>();
		String sctempletTypeId = Struts2Utils.getParameter("sctempletTypeId");
		Map<String, Object> values = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(sctempletTypeId)) {
			sqlBuf.append(" and t.contract_templet_type_id =:sctempletTypeId ");
			values.put("sctempletTypeId", sctempletTypeId);
		}
		String projectCd = Struts2Utils.getParameter("isstandard");

		for (Iterator<Entry<String, String>> iter = buildcoOpinions().entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> e = iter.next();

			String hql = buildMyRecoHql(param, e.getKey());

			if (StringUtils.isNotBlank(sqlBuf.toString())) {
				param.putAll(values);
				hql += " " + sqlBuf.toString();
			}
			long count = contractTempletManager.countSqlResult(hql, param);
			if (count > 0) {
				coOpinions.put(e.getKey(), e.getValue() + "(" + count + ")");
			}
		}

		if (StringUtils.isBlank(selectOptions) && coOpinions.size() > 0) {
			selectOptions = coOpinions.entrySet().iterator().next().getKey();
		}

	    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//	Date end = new Date();
		// logger.error("耗时:" + (end.getTime() - start.getTime()) / 1000d);
	}

	/**
	 * 合同类型HQL
	 */
	private String buildMyRecoHql(Map<String, Object> pram, String isstandard) {
		StringBuilder hql = new StringBuilder("select t.* from sc_contract_templet t where 1=1 and t.is_del is null  ");

		// 模板类别
		if (StringUtils.isNotBlank(isstandard)) {
			hql.append("  and t.isstandard=:isstandard");
		}

		pram.put("isstandard", isstandard);

		return hql.toString();
	}

	/**
	 * 合类标准类别
	 */
	public Map<String, String> buildcoOpinions() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(CON_STANDARD, "标准合同");
		map.put(CON_NONSTANDARD, "非标准合同");

		return map;
	}

	/**
	 * @return the attachment
	 */
	public File[] getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment
	 *            the attachment to set
	 */
	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}
	/**
	 * @return the attachmentFileName
	 */
	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}

	/**
	 * @param attachmentFileName the attachmentFileName to set
	 */
	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	/**
	 * @return the appAttachFileList
	 */
	public List<AppAttachFile> getAppAttachFileList() {
		return appAttachFileList;
	}

	/**
	 * @param appAttachFileList the appAttachFileList to set
	 */
	public void setAppAttachFileList(List<AppAttachFile> appAttachFileList) {
		this.appAttachFileList = appAttachFileList;
	}
}

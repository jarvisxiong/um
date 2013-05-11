package com.hhz.ump.web.bid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.bid.pl.PlAppAttachFileManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.bid.pl.PlAppAttachFile;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 用于供应商注册模块的附件上传
 * 
 * @author shixy
 * 
 *         2011-4-22
 */
public class UploadAction extends ActionSupport {
	private static final long serialVersionUID = -3445152342227169047L;
	
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private PlAppAttachFileManager plAppAttachFileManager;
	@Autowired
	private BidSupManager bidSupManager;
	@Autowired
	private BidLedgerManager bidLedgerManager;

	// 上传文件最大限制,默认20M
	public static final Long MAX_FILE_SIZE = 20480000L;

	// 上传文件
	private File upload;

	// 上传文件名
	private String uploadFileName;
	private String uploadContentType;
	
	// 业务数据主键
	private String bizEntityId;
	
	private String bizModuleCd;
	// 供应商Id上传保证金
	private BidSup bidSup;
	private BidLedger bidLedger;

	/**
	 * 产生随机文件名
	 * 
	 * @param fileName
	 * @return
	 */
	private String genFileName(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		Date now = new Date();
		String dateFormat = DateOperator.formatDate(now, "yyyyMMddHHmmss");
		String genName = dateFormat + RandomUtils.generateString(4) + suffix;
		return genName;
	}

	/**
	 * 文件上传
	 */
	@Override
	public String execute() throws Exception {
		File dir = getDir();
		String fileName = genFileName(uploadFileName);
		File fout = new File(dir, fileName);
		FileOutputStream fos = new FileOutputStream(fout);
		if (upload.length() > MAX_FILE_SIZE) {
			fos.close();
			fout.delete();
			upload.delete();
			Struts2Utils.renderHtml("{'error':'上传文件最大不能超过" + MAX_FILE_SIZE / (1024 * 1000) + "M'}");
			return null;
		}
		
		// 保存数据到附件表
		AppAttachFile appAttachFile = new AppAttachFile();
		appAttachFile.setBizEntityId(bizEntityId);
		appAttachFile.setBizModuleCd("bidLedgerGurantee");// PD附件类型与官网保持一致
		appAttachFile.setFileName(fileName);
		appAttachFile.setFilePath(getSavePath());
		appAttachFile.setRealFileName(uploadFileName);
		appAttachFile.setFileTypeName(uploadContentType);
		appAttachFile.setFileSize(BigDecimal.valueOf(upload.length()));
		appAttachFile.setStatusCd("1");// 1-有效 0-删除 2-临时
		appAttachFileManager.saveAppAttachFile(appAttachFile);

		// 保证金已经上传附件
		bidLedger = bidLedgerManager.getEntity(bizEntityId);
		bidLedger.setBidContAttachId("1");
		bidLedgerManager.saveBidLedger(bidLedger);

		// 写文件流
		FileInputStream fin = new FileInputStream(upload);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fin.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		fin.close();
		upload.delete();
		// 将附件同步到PL
		bidLedgerManager.syncAttaFile(bizEntityId, fileName);
		
		Struts2Utils.renderHtml("{'success':'"+appAttachFile.getAppAttachFileId()+"'}");

		return null;
	}
	
	public void deleteAtta(){
		String attaId = Struts2Utils.getParameter("attaId");// 附件表唯一ID
		AppAttachFile appAttachFile = appAttachFileManager.getEntity(attaId);
		String filePath = appAttachFile.getFilePath()+File.separator+appAttachFile.getFileName();
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		
		// 搜索PL端对应招标文件附件
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer().append(" from PlAppAttachFile where statusCd='1' ");
		hql.append(" and fileName =:fileName ");
		param.put("fileName", appAttachFile.getFileName());
		PlAppAttachFile plFile = plAppAttachFileManager.getDao().findUnique(hql.toString(), param);
		String plfilePath = plFile.getFilePath()+File.separator+plFile.getFileName();
		// 删除PL端对应招标文件附件
		File plfile = new File(plfilePath);
		if(plfile.exists()){
			plfile.delete();
		}
		appAttachFileManager.delete(appAttachFile);
		plAppAttachFileManager.delete(plFile);

	}
	
	private File getDir() {
		File dir = new File(getSavePath());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}
	private String getSavePath() {
		return PowerUtils.getFilePath(bizModuleCd,true);
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

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getBizEntityId() {
		return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
		this.bizEntityId = bizEntityId;
	}

	public String getBizModuleCd() {
		return bizModuleCd;
	}

	public void setBizModuleCd(String bizModuleCd) {
		this.bizModuleCd = bizModuleCd;
	}


}

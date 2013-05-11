/**
 * 
 */
package com.hhz.ump.web.bid;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.ChangeCharset;
import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.pl.PlAppAttachFileManager;
import com.hhz.ump.dao.plan.ExecPlanDetailManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;

/**
 * 附件管理
 * 
 * @author huangj 2009-12-4
 */
@Namespace("/bid")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "bid-app-attachment!list.action", type = "redirect", params = {
		"bizEntityId", "${bizEntityId}", "bizEntityName", "${bizEntityName}", "bizModuleCd", "${bizModuleCd}",
		"bizFieldName", "${bizFieldName}", "filterType", "${filterType}", "uiid", "${uiid}", "attachModelType",
		"${attachModelType}", "onlyUpload", "${onlyUpload}" }) })
public class BidAppAttachmentAction extends CrudActionSupport<AppAttachFile> {
	private static final long serialVersionUID = -3445152342227169047L;

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
	 * PDF文件
	 */
	public static final String FILTER_TYPE_PDF = "pdf";

	/**
	 * word 2007
	 */
	public static final String FILTER_TYPE_WORD2007 = "word2007";

	/**
	 * office文件
	 */
	public static final String FILTER_TYPE_IMAGE_OR_OFFICE = "image|office";

	/**
	 * 附件下载
	 */
	public static final String OPERATE_ATTACHMENT = "attachment;";

	/**
	 * 直接打开
	 */
	public static final String OPERATE_INLINE = "inline;";

	private AppAttachFile entity;

	private String bizEntityId;
	// 用于搜索的多文件ID
	private String fileIds;
	// 用于显示的dom元素ID
	private String domId;
	// 判断当前用户是否能删除附件
	private boolean canEdit;
	private String bizEntityName;
	private String fieldName;

	private String bizModuleCd;
	private String bizFieldName;// 对应实体的字段名
	

	private String isUpdate = "0";

	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private ResApproveInfoManager resApproveInfoManager;
	@Autowired
	private ExecPlanDetailManager detailManager;
	@Autowired
	private BidLedgerManager bidLedgerManager;
	@Autowired
	private PlAppAttachFileManager plAppAttachFileManager;

	private String fileName;

	private String realFileName;

	private String[] allowTypes = CoreContants.FILE_TYPE;

	/**
	 * 上传模式：
	 * 默认值 null 多个附件（默认） 非空 单个附件
	 */
	private String attachModelType;
	private String onlyUpload;

	private String uiid;

	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
	}

	/**
	 * 允许上传的文件类型代码
	 */
	private String filterType;

	/**
	 * 上传文件名
	 */
	private File[] upload;

	private String[] uploadFileName;

	private String[] uploadContentType;

	// ---下载参数
	private String contentDisposition;

	private String contentType;

	private InputStream is;

	// 设置直接打开或者弹出下载对话框
	private String operator = OPERATE_INLINE;// inline;--直接IE打开，attachment;--附件下载形式

	private String downFileName;

	private String filename;

	// 字符集
	private String charset = ";charset=utf-8";
	private List<AppAttachFile> attachFiles;

	// ----
	private String getSavePath(String uiid, boolean isUpload) {
		return PowerUtils.getFilePath(bizModuleCd, uiid, isUpload);
	}

	private boolean filterType(String cty) {
		String fileTypes=PowerUtils.getPropKey("file-type.properties", bizModuleCd);
		boolean flag = false;
		flag = ArrayUtils.contains(allowTypes, cty);
		if (fileTypes!=null && fileTypes.contains(cty)){
			flag = false;
		}
		return flag;
	}

	private void initFilterType() {
		if (StringUtils.isNotEmpty(filterType)) {
			String[] stoke = filterType.split("\\|");
			Set<String> typeSet = new HashSet<String>();
			for (String string : stoke) {
				if (string.equals(FILTER_TYPE_IMAGE)) {
					for (String fileType : CoreContants.FILE_TYPE_IMAGE) {
						typeSet.add(fileType);
					}
				} else if (string.equals(FILTER_TYPE_OFFICE)) {
					for (String fileType : CoreContants.FILE_TYPE_OFFICE) {
						typeSet.add(fileType);
					}
				} else if (string.equals(FILTER_TYPE_VIDEO)) {
					for (String fileType : CoreContants.FILE_TYPE_VIDEO) {
						typeSet.add(fileType);
					}
				} else if (string.equals(FILTER_TYPE_PDF)) {
					typeSet.add("application/pdf");
				} else if (string.equals(FILTER_TYPE_WORD2007)) {
					typeSet.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				}
			}
			allowTypes = new String[typeSet.size()];
			typeSet.toArray(allowTypes);
		}

	}

	private File getDir(String uiid) {
		// String strPath =
		// ServletActionContext.getServletContext().getRealPath(savePath);
		File dir = new File(getSavePath(uiid, true));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	public String updateAppatch() {
		appAttachFileManager.updateApp();
		return null;
	}

	/**
	 * 清除已删除的文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String clearDelFile() throws Exception {
		appAttachFileManager.clearDelFile();
		return null;
	}

	/**
	 * 彻底删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deepDelete() throws Exception {
		try {
			AppAttachFile attachFileTmp = appAttachFileManager.getEntity(getId());
			appAttachFileManager.deleteAppAttachFile(attachFileTmp, false);
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	/**
	 * 逻辑删除
	 */
	@Override
	public String delete() throws Exception {
		try {
			AppAttachFile appAttachFile = appAttachFileManager.getEntity(getId());
			bidLedgerManager.deleteAppAttachFile(appAttachFile, bizEntityName, bizEntityId, false);
			// 主要为关注模块，节点发生变化时，往关注库做标志 start (2011-11-22)
			detailManager.setAttentionChange("execPlan", appAttachFile.getBizEntityId());
			// end (2011-11-22)
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return CrudActionSupport.RELOAD;
	}

	/**
	 * 生成文件名
	 * 
	 * @param uploadFileName1
	 * @return
	 */
	private String genFileName(String uploadFileName1) {
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
	 * 权责审批文件上传
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uploadRes() throws Exception {
		upload();
		Struts2Utils.renderHtml("success");// 上传特殊,不能renderText
		return null;
	}

	/**
	 * 总裁审批副本
	 * 
	 * @param bizEntityId
	 * @return
	 * @throws Exception
	 */
	public String uploadChiefAttach() throws Exception {
		// 失效旧数据
		appAttachFileManager.invalidModuleData(bizModuleCd, bizEntityId);

		upload();

		// 更新总裁附件字段(signFile:app_attachment_id)
		ResApproveInfo info = resApproveInfoManager.getEntity(bizEntityId);
		List<AppAttachFile> tmpList = appAttachFileManager.getAttachFiles(bizEntityId, bizModuleCd, "1");// 正常
		if (tmpList != null && tmpList.size() == 1) {
			String signFileId = tmpList.get(0).getAppAttachFileId();
			if (bizModuleCd.equalsIgnoreCase(DictContants.BIZ_MODULE_CD_RES_CHIEF_XIAO)) {
				info.setSignFileX(signFileId);
			} else {
				info.setSignFile(signFileId);
			}
			resApproveInfoManager.saveResApproveInfo(info, null);
			Struts2Utils.renderHtml("success," + signFileId);// 上传特殊,不能renderText
		} else {
			Struts2Utils.renderHtml("上传不成功!");
		}
		return null;
	}

	public String uploadSingleFile() throws Exception {
		// 失效旧数据
		bidLedgerManager.invalidModuleData(bizModuleCd,bizEntityId,bizFieldName);
		List<AppAttachFile> result = uploadFiles();
		if (result != null && result.size() == 1) {
			String signFileId = result.get(0).getAppAttachFileId();
			Struts2Utils.renderHtml("success," + bizModuleCd + "," + signFileId);// 上传特殊,不能renderText
		} else {
			Struts2Utils.renderHtml("error");
		}
		return null;
	}
	
	public String uploadMultiFile() throws Exception {
		
		List<AppAttachFile> result = uploadFiles();
		if (result != null && result.size() == 1) {
			String signFileId = result.get(0).getAppAttachFileId();
			Struts2Utils.renderHtml("success," + bizModuleCd + "," + signFileId);// 上传特殊,不能renderText
		} else {
			Struts2Utils.renderHtml("error");
		}
		return null;
	}

	private void genSmallPic(File fi, File dir, String newFileName) {
		try {
			File fo = new File(dir, newFileName); // 将要转换出的小图文件

			AffineTransform transform = new AffineTransform();
			BufferedImage bis = ImageIO.read(fi);

			int w = bis.getWidth();
			int h = bis.getHeight();
			double scale = (double) w / h;

			int nw = 130;
			int nh = (nw * h) / w;
			if (nh > 120) {
				nh = 120;
				nw = (nh * w) / h;
			}

			double sx = (double) nw / w;
			double sy = (double) nh / h;

			transform.setToScale(sx, sy);

			AffineTransformOp ato = new AffineTransformOp(transform, null);
			BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
			ato.filter(bis, bid);
			ImageIO.write(bid, "jpeg", fo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传文件
	 * @return
	 * @throws Exception
	 */
	private List<AppAttachFile> uploadFiles() throws Exception {
		Date beginDate = new Date();
		logger.info(uiid + "开始上传文件" + beginDate.toGMTString());
		File dir = getDir(uiid);

		initFilterType();
		List<AppAttachFile> lstAdd = new ArrayList<AppAttachFile>();
		if (upload != null) { // upload multiple files
			if (!StringUtils.isEmpty(this.attachModelType)) {
				// upload only single file 只有一个附件时 失效旧数据
				appAttachFileManager.invalidModuleData(bizModuleCd, bizEntityId);
			}

			Long maxFileSize = 0l;
			for (int i = 0; i < upload.length; i++) {
				maxFileSize += upload[i].length();
			}
			if (maxFileSize > Constants.MAX_FILE_SIZE) {
				Struts2Utils.renderHtml("maxSizeError-" + Constants.MAX_FILE_SIZE / (1024 * 1000));
				return null;
			}
			for (int i = 0; i < upload.length; i++) {
				if (upload[i] != null) {
					boolean isAllow = true;
					if (!StringUtils.equals(filterType, "all")) {
						isAllow = filterType(uploadContentType[i]);
					}
					if (!isAllow) {
						Struts2Utils.renderHtml("cantUploadType-" + StringUtils.substringAfterLast(uploadFileName[i],"."));
						return null;
					} else {
						String newFileName = genFileName(uploadFileName[i]);
						File fout = new File(dir, newFileName);
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
						saveOrUpdateAppAttachment(lstAdd, newFileName, uploadFileName[i], uploadContentType[i],
								new BigDecimal(fout.length()));
						
					}
				}
			}
			bidLedgerManager.saveAppAttachFiles(lstAdd, bizEntityName, bizEntityId,fieldName);
			
			// 将附件同步到PL
			//搜索是否存在附件，有则删除
			List l=bidLedgerManager.plAttachFilesByFileName(bizEntityId,bizModuleCd,fieldName,null);
			if(l!=null&&l.size()>0){
				plAppAttachFileManager.delete(l);
			}
			bidLedgerManager.syncAttaFileTwo(bizEntityId, fieldName);
			

			
		}
		return lstAdd;
	}

	/**
	 * 如果上传时传入记录Id,将删除旧文件，视为单文件上传 文件上传
	 */
	public String upload() throws Exception {
		List<AppAttachFile> result = uploadFiles();
		if (result == null)
			return null;
		StringBuffer sbFileIds = new StringBuffer();
		for (AppAttachFile attachFile : result) {
			if (sbFileIds.length() > 0) {
				sbFileIds.append(",");
			}
			sbFileIds.append(attachFile.getAppAttachFileId());
		}
		setFileIds(sbFileIds.toString());
		return CrudActionSupport.RELOAD;
	}

	/**
	 * 上传文件后提示关注有更新
	 */
	public String uploadForAttention() throws Exception {
		List<AppAttachFile> result = uploadFiles();
		if (result == null)
			return null;
		StringBuffer sbFileIds = new StringBuffer();
		for (AppAttachFile attachFile : result) {
			if (sbFileIds.length() > 0) {
				sbFileIds.append(",");
			}
			sbFileIds.append(attachFile.getAppAttachFileId());
			// 主要为关注模块，节点发生变化时，往关注库做标志 start (2011-11-22)
			detailManager.setAttentionChange("execPlan", attachFile.getBizEntityId());
			// end (2011-11-22)
		}
		setFileIds(sbFileIds.toString());
		return CrudActionSupport.RELOAD;
	}

	/** 文件对象 */
	private transient File[] filedata;

	/** 文件名 */
	private transient String[] filedataFileName;

	/** 文件内容类型 */
	private transient String[] filedataContentType;

	public String ajaxUpload() throws Exception {
		System.out.println("filedataFileName length:" + filedataFileName.length);
		File dir = getDir(uiid);
		List<AppAttachFile> lstAdd = new ArrayList<AppAttachFile>();
		// initFilterType();
		if (filedata != null) {
			Long maxFileSize = 0l;
			for (int i = 0; i < filedata.length; i++) {
				maxFileSize += filedata[i].length();
			}
			if (maxFileSize > Constants.MAX_FILE_SIZE) {
				Struts2Utils.renderHtml("maxSizeError-" + Constants.MAX_FILE_SIZE / (1024 * 1000));
				return null;
			}
			for (int i = 0; i < filedata.length; i++) {
				if (filedata[i] != null) {
					// boolean isAllow = filterType(filedataContentType[i]);
					// if (!isAllow) {
					// addActionMessage(getText("file.canotallow", new String[]
					// { filedataContentType[i] }));
					// } else {
					String newFileName = genFileName(filedataFileName[i]);
					File fout = new File(dir, newFileName);
					FileOutputStream fos = new FileOutputStream(fout);
					FileInputStream fin = new FileInputStream(filedata[i]);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fin.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					fin.close();
					saveOrUpdateAppAttachment(lstAdd, newFileName, filedataFileName[i], filedataContentType[i],
							new BigDecimal(fout.length()));
					// }
				}
			}
			appAttachFileManager.saveAppAttachFiles(lstAdd, bizEntityName, bizEntityId);
		}
		return SUCCESS;
	}

	private void saveOrUpdateAppAttachment(List<AppAttachFile> lstAdd, String newfileName, String realFileNameTmp,
			String fileTypeName, BigDecimal fileSize) {
		AppAttachFile attachFile = null;
		if (StringUtils.isEmpty(getId())) {
			attachFile = new AppAttachFile();
			attachFile.setBizModuleCd(bizModuleCd);
			attachFile.setBizEntityId(bizEntityId);
		} else {
			attachFile = entity;
			// 删除旧文件
			try {
				appAttachFileManager.deleteAppAttachFile(attachFile, true);
			} catch (Exception e) {
				attachFile = new AppAttachFile();
				attachFile.setBizModuleCd(bizModuleCd);
				attachFile.setBizEntityId(bizEntityId);
			}
		}
		attachFile.setRealFileName(realFileNameTmp);
		attachFile.setFileName(newfileName);
		attachFile.setFilePath(getSavePath(uiid, true));
		attachFile.setFileTypeName(fileTypeName);
		attachFile.setFileSize(fileSize);
		// attachFile.setIpAddr(ipAddr);
		attachFile.setBizFieldName(bizFieldName);
		String strStatus = AppAttachFileManager.STATUS_NORMAL;
		if (bizEntityId.length() == 10) {
			strStatus = AppAttachFileManager.STATUS_TEMP;
		}
		attachFile.setStatusCd(strStatus);// 1-正常，0-删除

		if (ArrayUtils.contains(CoreContants.FILE_SUFFIX_IMAGE, getSuffix(newfileName).toLowerCase())) {
			File dir = getDir(uiid);
			File fout = new File(dir, newfileName);
			String smallFileName = genFileName(newfileName);
			genSmallPic(fout, dir, smallFileName);
			attachFile.setSmallPicName(smallFileName);
		}
		lstAdd.add(attachFile);

	}

	/**
	 * 文件下载
	 * 
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		try {
			// 根据jsp传入的参数寻找文件
			File file = new File(getSavePath(uiid, false) + "\\" + fileName);
			if (!file.exists()) {
				// 如果找不到文件，根据file.properties配置文件寻找
				file = new File(getSavePath(null, false) + "\\" + fileName);
				if (!file.exists()) {
					// 如果找不到，根据对应附件实体寻找文件
					if (StringUtils.isNotEmpty(getId())) {
						AppAttachFile attachFile = appAttachFileManager.getEntity(getId());
						file = new File(attachFile.getFilePath() + "\\" + fileName);
						if (!file.exists()) {
							file = new File(attachFile.getFilePath() + "\\" + attachFile.getCreator() + "\\" + fileName);
						}
					}
					if (!file.exists()) {
						// 如果找不到，根据文件名寻找文件
						AppAttachFile attachFile = appAttachFileManager.getAttachFile(fileName);
						if (attachFile != null) {
							file = new File(attachFile.getFilePath() + "\\" + fileName);
							if (!file.exists()) {
								file = new File(attachFile.getFilePath() + "\\" + attachFile.getCreator() + "\\"
										+ fileName);
							}
						}
					}
				}
			}
			if (!file.exists())
				return null;
			String fileSuf = fileName.substring(fileName.indexOf("."));
			// ChangeCharset.toISO_8859_1(realFileName);
			// setContentDisposition(operator);
			logger.info(realFileName);
			String realFileNameTmp = realFileName;
			if (StringUtils.isNotEmpty(getId())) {
				realFileNameTmp = appAttachFileManager.getRealFileNameById(getId());
			} else {
				realFileNameTmp = ChangeCharset.decode2UTF_8(realFileName);
			}

			HttpServletRequest request = Struts2Utils.getRequest();
			String agentStr = request.getHeader("User-Agent");
			if (agentStr == null) {
				agentStr = "MSIE";
			}
			logger.info("enter file download....agent=" + agentStr);

			agentStr = agentStr.toUpperCase();
 			if (agentStr != null && agentStr.indexOf("MSIE") != -1) {
				setDownFileName(java.net.URLEncoder.encode(realFileNameTmp, "UTF-8"));
			} else {
				setDownFileName(new String(realFileNameTmp.getBytes("UTF-8"), "ISO8859-1"));
			}

			// HttpServletResponse response = Struts2Utils.getResponse();
			// if (agentStr != null && agentStr.indexOf("MSIE") != -1) {
			// logger.debug("IE browser");
			// response.setHeader("Content-disposition", "attachment; filename="
			// + java.net.URLEncoder.encode(realFileNameTmp, "UTF-8"));
			// } else if (agentStr != null && agentStr.indexOf("CHROME") != -1)
			// {
			// logger.debug("chrome browser");
			// response.setHeader("Content-disposition", "attachment; filename="
			// + MimeUtility.encodeText(realFileNameTmp, "UTF-8", "B"));
			// } else if (agentStr != null && agentStr.indexOf("SAFARI") != -1)
			// {
			// logger.debug("Safari browser");
			// setDownFileName(new String(realFileNameTmp.getBytes("UTF-8"),
			// "ISO8859-1"));
			// //response.setHeader("Content-disposition",
			// "attachment; filename=" + new
			// String(realFileNameTmp.getBytes("UTF-8"), "ISO8859-1"));
			// } else if (agentStr != null && agentStr.indexOf("MOZILLA") != -1)
			// {
			// logger.debug("Mozilla browser");
			// response.setHeader("Content-disposition", "attachment; filename="
			// + MimeUtility.encodeText(realFileNameTmp, "UTF-8", "B"));
			// } else {
			// response.setHeader("Content-disposition", "attachment; filename="
			// + java.net.URLEncoder.encode(realFileNameTmp, "UTF-8"));
			// }

			// setDownFileName(new String(realFileNameTmp.getBytes("GBK"),
			// "ISO8859-1"));
			String fileType = PowerUtils.getSamPlaceValue(CoreContants.FILE_TYPE, CoreContants.FILE_SUFFIX, fileSuf);
			setContentType(fileType + charset);
			// setFileName(null);
			is = new FileInputStream(file);
		} catch (Exception e) {

		}
		return SUCCESS;
	}

	public String downloadOld() throws Exception {
		String fileId = Struts2Utils.getParameter("attaId");
		String attaName = Struts2Utils.getParameter("attaName");
		realFileName = new String(attaName.getBytes("ISO8859_1"), "UTF-8");
		setDownFileName(new String(realFileName.getBytes("GBK"), "ISO8859-1"));
		String[] fileFolders = fileId.split("_");
		String folder = fileFolders[0];
		String fileNameTemp = fileFolders[1];
		String filePath = PowerUtils.getFilePath(bizModuleCd, false);
		File file = new File(filePath + "\\" + folder + "\\" + fileNameTemp + "." + realFileName);
		String fileSuf = realFileName.substring(realFileName.indexOf("."));
		String fileType = PowerUtils.getSamPlaceValue(CoreContants.FILE_TYPE, CoreContants.FILE_SUFFIX, fileSuf);
		setContentType(fileType + ";charset=utf-8");
		is = new FileInputStream(file);
		return SUCCESS;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>(); // HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		page.setPageSize(100);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
		if (StringUtils.isEmpty(bizEntityId) && StringUtils.isNotEmpty(getId())) {
			filters.add(new PropertyFilter("EQS_appAttachFileId", getId()));
		} else {
			filters.add(new PropertyFilter("EQS_bizEntityId", bizEntityId));
		}
		filters.add(new PropertyFilter("NEQS_statusCd", "0"));// 状态为正常
		if (StringUtils.isNotEmpty(bizModuleCd)) {
			filters.add(new PropertyFilter("EQS_bizModuleCd", bizModuleCd));
		}
		if (StringUtils.isNotEmpty(bizFieldName)
				&& !"null".equalsIgnoreCase(bizFieldName)
				&& !"undefined".equalsIgnoreCase(bizFieldName)) {
			filters.add(new PropertyFilter("EQS_bizFieldName", bizFieldName));
		}
		if (StringUtils.isNotEmpty(uiid)) {
			filters.add(new PropertyFilter("EQS_creator", uiid));
		}
		page = appAttachFileManager.findPage(page, filters);
		// 只上传
		if (StringUtils.isNotBlank(onlyUpload))
			return "onlyUpload";
		// 只查看符合条件的附件列表
		String onlyShow = Struts2Utils.getParameter("onlyShow");
		if (StringUtils.isNotBlank(onlyShow))
			return "onlyShow";
		String single = Struts2Utils.getParameter("single");
		if (StringUtils.isNotBlank(single)) {
			attachFiles = page.getResult();
			return "singleShow";
		}

		//仅仅给上传人能操作
		String onlyCreator = Struts2Utils.getParameter("onlyCreator");
		if("onlyCreator".equalsIgnoreCase(onlyCreator))
			return "onlyCreator";
		else
			return SUCCESS;
	}

	public String load() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_appAttachFileId", getId()));
		attachFiles = appAttachFileManager.find(filters);
		return "singleShow";
	}
	
	public String loadMulti() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_appAttachFileId", getId()));
		attachFiles = appAttachFileManager.find(filters);
		return "multiShow";
	}

	public String show() throws Exception {
		ResApproveInfo approveInfo = resApproveInfoManager.getEntity(bizEntityId);
		if (approveInfo != null) {

		}
		list();
		return "show";
	}

	public String bisShow() throws Exception {

		page.setOrderBy("realFileName");
		page.setOrder("asc");
		list();
		return "bisShow";
	}

	private List<AppAttachFile> contractFiles = new ArrayList<AppAttachFile>();

	private List<AppAttachFile> otherFiles = new ArrayList<AppAttachFile>();
	private List<AppAttachFile> jcwFiles = new ArrayList<AppAttachFile>();

	private List<AppAttachFile> shareFiles = new ArrayList<AppAttachFile>();

	private List<AppAttachFile> resApproveInfoFiles = new ArrayList<AppAttachFile>();

	private boolean isUploaded = false;

	public String resShow() throws Exception {
		// list();
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_bizEntityId", bizEntityId));
		attachFiles = appAttachFileManager.find(filters);

		if (attachFiles.size() > 0) {
			isUploaded = true;
		}
		for (AppAttachFile file : attachFiles) {
			if (DictContants.BIZ_MODULE_CD_RESCONTRACT.equalsIgnoreCase(file.getBizModuleCd())) {
				contractFiles.add(file);
			} else if (DictContants.BIZ_MODULE_CD_RESOTHER_CONT.equalsIgnoreCase(file.getBizModuleCd())) {
				otherFiles.add(file);
			} else if (DictContants.BIZ_MODULE_CD_RESSHARE.equalsIgnoreCase(file.getBizModuleCd())) {
				shareFiles.add(file);
			} else if (DictContants.BIZ_MODULE_CD_RESAPPROVEINFO.equalsIgnoreCase(file.getBizModuleCd())) {
				resApproveInfoFiles.add(file);
			} else if (DictContants.BIZ_MODULE_CD_RESOTHER.equalsIgnoreCase(file.getBizModuleCd())) {
				otherFiles.add(file);
			} else if (DictContants.BIZ_MODULE_CD_RESJCW.equalsIgnoreCase(file.getBizModuleCd())) {
				jcwFiles.add(file);
			} else {
				logger.info("## file will not list on attachment page,bizModuleCd=" + file.getBizModuleCd());
			}
		}

		String myMod = Struts2Utils.getParameter("myMod");
		String lockUser = Struts2Utils.getParameter("lockUser");
		String resUserCd = Struts2Utils.getParameter("resUserCd");
		String userCd = Struts2Utils.getParameter("userCd");
		String nodeCd = Struts2Utils.getParameter("nodeCd");
		Struts2Utils.getRequest().setAttribute("myMod", myMod);
		Struts2Utils.getRequest().setAttribute("lockUser", lockUser);
		Struts2Utils.getRequest().setAttribute("bizEntityId", bizEntityId);
		Struts2Utils.getRequest().setAttribute("resUserCd", resUserCd);
		Struts2Utils.getRequest().setAttribute("nodeCd", nodeCd);
		Struts2Utils.getRequest().setAttribute("userCd", userCd);
		return "resShow";
	}

	// to upload contract page
	public String contractUpload() {
		return "contractUpload";
	}

	// to upload chief page
	public String chiefAttachUpload() {
		return "chiefAttachUpload";
	}

	public String singleUpload() {
		return "singleUpload";
	}
	
	public String multiUpload() {
		return "multiUpload";
	}

	public String getAllImage() {
		String result = null;
		result = PowerUtils.array2String(CoreContants.FILE_SUFFIX_IMAGE);
		return result;
	}

	public String isImage() {
		// HttpServletRequest request = ServletActionContext.getRequest();
		// String newDictTypeCd = request.getParameter("dictTypeCd");
		// String oldDictTypeCd = request.getParameter("oldDictTypeCd");

		if (ArrayUtils.contains(CoreContants.FILE_SUFFIX_IMAGE, getSuffix(fileName))) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	/**
	 * 提供下载的数据流
	 * 
	 * @return
	 * @throws Exception
	 */

	public InputStream getTargetFile() throws Exception {
		return is;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setBizEntityId(String entityId) {
		this.bizEntityId = entityId;
	}

	public String getBizModuleCd() {
		return bizModuleCd;
	}

	public void setBizModuleCd(String moudelKey) {
		this.bizModuleCd = moudelKey;
	}

	public String getBizEntityId() {
		return bizEntityId;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = appAttachFileManager.getEntity(getId());
		} else {
			entity = new AppAttachFile();
		}
	}

	public String hasAttachment() throws Exception {

		List<AppAttachFile> list = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(bizEntityId, bizModuleCd);

		if (list.isEmpty()) {
			Struts2Utils.renderText("false");
		} else {
			Struts2Utils.renderText("true");
		}
		return null;
	}

	// public void prepareDownload() throws Exception {
	// prepareModel();
	// }
	public void prepareUpload() throws Exception {
		prepareModel();
	}

	public void prepareUploadSingleFile() throws Exception {
		prepareModel();
	}

	public void prepareUploadRes() throws Exception {
		prepareModel();
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public AppAttachFile getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public String getBizEntityName() {
		return bizEntityName;
	}

	public void setBizEntityName(String bizEntityName) {
		this.bizEntityName = bizEntityName;
	}

	public File[] getFiledata() {
		return filedata;
	}

	public void setFiledata(File[] filedata) {
		this.filedata = filedata;
	}

	public String[] getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String[] filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public String[] getFiledataContentType() {
		return filedataContentType;
	}

	public void setFiledataContentType(String[] filedataContentType) {
		this.filedataContentType = filedataContentType;
	}

	public String getAttachModelType() {
		return attachModelType;
	}

	public void setAttachModelType(String attachModelType) {
		this.attachModelType = attachModelType;
	}

	public List<AppAttachFile> getContractFiles() {
		return contractFiles;
	}

	public void setContractFiles(List<AppAttachFile> contractFiles) {
		this.contractFiles = contractFiles;
	}

	public List<AppAttachFile> getOtherFiles() {
		return otherFiles;
	}

	public void setOtherFiles(List<AppAttachFile> otherFiles) {
		this.otherFiles = otherFiles;
	}

	public List<AppAttachFile> getShareFiles() {
		return shareFiles;
	}

	public void setShareFiles(List<AppAttachFile> shareFiles) {
		this.shareFiles = shareFiles;
	}

	public List<AppAttachFile> getResApproveInfoFiles() {
		return resApproveInfoFiles;
	}

	public void setResApproveInfoFiles(List<AppAttachFile> resApproveInfoFiles) {
		this.resApproveInfoFiles = resApproveInfoFiles;
	}

	public AppAttachFileManager getAppAttachFileManager() {
		return appAttachFileManager;
	}

	public void setAppAttachFileManager(AppAttachFileManager appAttachFileManager) {
		this.appAttachFileManager = appAttachFileManager;
	}

	public boolean isSharedUser(String idEntityId) {
		return resApproveInfoManager.isResSharedUser(idEntityId);
	}

	public boolean getIsUploaded() {
		return isUploaded;
	}

	public void setIsUploaded(boolean isUploaded) {
		this.isUploaded = isUploaded;
	}

	public List<AppAttachFile> getJcwFiles() {
		return jcwFiles;
	}

	public void setJcwFiles(List<AppAttachFile> jcwFiles) {
		this.jcwFiles = jcwFiles;
	}

	public String getDomId() {
		return domId;
	}

	public void setDomId(String domId) {
		this.domId = domId;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}

	public String getOnlyUpload() {
		return onlyUpload;
	}

	public void setOnlyUpload(String onlyUpload) {
		this.onlyUpload = onlyUpload;
	}

	public String getBizFieldName() {
		return bizFieldName;
	}

	public void setBizFieldName(String bizFieldName) {
		this.bizFieldName = bizFieldName;
	}

	public List<AppAttachFile> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<AppAttachFile> attachFiles) {
		this.attachFiles = attachFiles;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	

}

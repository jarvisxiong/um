/**
 * 
 */
package com.hhz.uums.web.app;

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

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.uums.dao.app.AppAttachFileManager;
import com.hhz.uums.entity.app.AppAttachFile;
import com.hhz.uums.web.CrudActionSupport;

/**
 * 附件管理
 * 
 * @author huangj 2009-12-4
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-attachment!list.action", type = "redirect", params = { "bizEntityId",
		"${bizEntityId}", "bizEntityName", "${bizEntityName}", "bizModuleCd", "${bizModuleCd}", "filterType", "${filterType}", "fileLevel",
		"${fileLevel}", "attachModelType", "${attachModelType}", "bizMainEnableFlg","${bizMainEnableFlg}" }) })
public class AppAttachmentAction extends CrudActionSupport<AppAttachFile> {
	private static final long serialVersionUID = -3445152342227169047L;

	/**
	 * 上传文件最大限制,默认20M
	 */
	public static final Long MAX_FILE_SIZE = 20480000L;

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

	private String bizEntityName;

	private String bizModuleCd;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	private String fileName;

	private String realFileName;

	private String[] allowTypes = CoreContants.FILE_TYPE;

	/**
	 * 上传模式：
	 * 
	 * 默认值 null 多个附件（默认） 非空 单个附件
	 * 
	 */
	private String attachModelType;

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

	// 字符集
	private String charset = ";charset=utf-8";
	
	// 是否允许操作主图片  1-是 0-否
	private String bizMainEnableFlg;

	private boolean filterType(String cty) {

		boolean flag = false;
		flag = ArrayUtils.contains(allowTypes, cty);
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

	// ----
	private String getSavePath() {
		return getSavePath(null,true);
	}
	private File getDir() {
		String strPath = getSavePath();
		File dir = new File(strPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	private void deleteAllFile() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_bizEntityId", bizEntityId));
		filters.add(new PropertyFilter("EQS_bizModuleCd", bizModuleCd));
		List<AppAttachFile> files = appAttachFileManager.find(filters);
		for (AppAttachFile appAttachFile : files) {
			appAttachFileManager.deleteAppAttachFile(appAttachFile);
		}
	}

	/**
	 * 彻底删除，no uiid
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String delete() throws Exception {
		try {
			AppAttachFile attachFileTmp = appAttachFileManager.getEntity(getId());
			appAttachFileManager.deleteAppAttachFile(attachFileTmp);
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
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
			tmpName = genPreFileName() + getSuffix(uploadFileName1);
		}
		return tmpName;
	}

	private String genPreFileName() {
		Date now = new Date();
		String dateFormat = DateOperator.formatDate(now, "yyyyMMddHHmmss");
		String tmpName = dateFormat + RandomUtils.generateString(4);
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
	 * 文件上传
	 */
	public String upload() throws Exception {
		Date beginDate = new Date();
		logger.info("开始上传文件" + beginDate.toGMTString());
		File dir = getDir();
		List<AppAttachFile> lstAdd = new ArrayList<AppAttachFile>();
		initFilterType();

		if (upload != null) { // upload multiple files
			if (!StringUtils.isEmpty(this.attachModelType)) {
				// upload only single file 只有一个附件时 失效旧数据
				appAttachFileManager.invalidModuleData(bizModuleCd, bizEntityId);
				deleteAllFile();
			}

			Long maxFileSize = 0l;
			for (int i = 0; i < upload.length; i++) {
				maxFileSize += upload[i].length();
			}
			if (maxFileSize > MAX_FILE_SIZE) {
				Struts2Utils.renderHtml("maxSizeError-" + MAX_FILE_SIZE / (1024 * 1000));
				return null;
			}
			for (int i = 0; i < upload.length; i++) {
				if (upload[i] != null) {
					boolean isAllow = true;
					if (filterType == null || !StringUtils.equals(filterType, "all")) {
						isAllow = filterType(uploadContentType[i]);
					}
					if (!isAllow) {
						addActionMessage(getText("file.canotallow", new String[] { uploadContentType[i] }));
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
						addAppAttachment(lstAdd, newFileName, uploadFileName[i], uploadContentType[i], new BigDecimal(fout.length()));
					}
				}
			}
			appAttachFileManager.saveAppAttachFiles(lstAdd, bizEntityName, bizEntityId);
		}

		Date endDate = new Date();
		logger.info("结束上传文件" + beginDate.toGMTString() + "一共耗时：" + (endDate.getTime() - endDate.getTime()) / 1000 + "s");
		return CrudActionSupport.RELOAD;
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

	private void addAppAttachment(List<AppAttachFile> lstAdd, String newfileName, String realFileNameTmp, String fileTypeName, BigDecimal fileSize) {
		AppAttachFile attachFile = null;
		if (StringUtils.isEmpty(getId())) {
			attachFile = new AppAttachFile();
			attachFile.setBizModuleCd(bizModuleCd);
			attachFile.setBizEntityId(bizEntityId);
		} else {
			attachFile = entity;
		}
		attachFile.setRealFileName(realFileNameTmp);
		attachFile.setFileName(newfileName);
		attachFile.setFilePath(PowerUtils.getFilePath(bizModuleCd,true));
		attachFile.setFileTypeName(fileTypeName);
		attachFile.setFileSize(fileSize);
		String strStatus = AppAttachFileManager.STATUS_NORMAL;
		if (bizEntityId.length() == 10) {
			strStatus = AppAttachFileManager.STATUS_TEMP;
		}
		attachFile.setStatusCd(strStatus);// 1-正常，0-删除

		if (ArrayUtils.contains(CoreContants.FILE_SUFFIX_IMAGE, getSuffix(newfileName).toLowerCase())) {
			File dir = getDir();
			File fout = new File(dir, newfileName);
			String smallFileName = genFileName(newfileName);
			genSmallPic(fout, dir, smallFileName);
			attachFile.setSmallPicName(smallFileName);
		}

		lstAdd.add(attachFile);

	}
	private String getSavePath(String uiid,boolean isUpload) {
		return PowerUtils.getFilePath(bizModuleCd, uiid,isUpload);
	}

	private String uiid;

	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
	}
	/**
	 * 文件下载
	 * 
	 * @return
	 * @throws Exception
	 */
	public String download() throws Exception {
		try {
//			File file = new File(getRealPath(entity.getFilePath()) + "\\" + entity.getFileName());
			File file = new File(getSavePath(uiid,false) + "\\" + entity.getFileName());
			if(!file.exists()){
				 file = new File(getSavePath(null,false) + "\\" + entity.getFileName());
			}
			
			String fileSuf = entity.getFileName().substring(entity.getFileName().indexOf("."));
			logger.info(entity.getRealFileName());
			setDownFileName(new String(entity.getRealFileName().getBytes("GBK"), "ISO8859-1"));
			String fileType = PowerUtils.getSamPlaceValue(CoreContants.FILE_TYPE, CoreContants.FILE_SUFFIX, fileSuf);
			setContentType(fileType + charset);
			is = new FileInputStream(file);
		} catch (Exception e) {

		}
		return SUCCESS;
	}

	/** 文件对象 */
	private transient File filedata;

	/** 文件名 */
	private transient String filedataFileName;

	/** 文件内容类型 */
	private transient String filedataContentType;

	public String ajaxUpload() throws Exception {
		File dir = getDir();
		// initFilterType();
		List<AppAttachFile> lstAdd = new ArrayList<AppAttachFile>();
		if (filedata != null) {
			Long maxFileSize = 0l;
			maxFileSize = filedata.length();
			if (maxFileSize > MAX_FILE_SIZE) {
				Struts2Utils.renderText("{'err':'上传文件最大不能超过" + MAX_FILE_SIZE / (1024 * 1000) + "M','msg':''}");
				filedata.delete();
				return null;
			}
			boolean isAllow = true;
			if (filterType != null || !StringUtils.equals(filterType, "all")) {
				isAllow = filterType(filedataContentType);
			}
			if (!isAllow) {
				Struts2Utils.renderText("{'err':'不允许上传该类型文件','msg':''}");
				filedata.delete();
				return null;
			} else {
				String newFileName = genFileName(filedataFileName);
				File fout = new File(dir, newFileName);
				FileOutputStream fos = new FileOutputStream(fout);
				FileInputStream fin = new FileInputStream(filedata);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fin.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				addAppAttachment(lstAdd, newFileName, filedataFileName, filedataContentType, new BigDecimal(fout.length()));
				appAttachFileManager.saveAppAttachFiles(lstAdd, bizEntityName, bizEntityId);
				String rootPath = Struts2Utils.getRequest().getContextPath() + "/" + PowerUtils.getFilePath(bizModuleCd,false);// ServletActionContext.getServletContext().getRealPath(PowerUtils.getFilePath(bizModuleCd));
				String path = rootPath + "/" + newFileName;
				Struts2Utils.renderText("{'err':'','msg':'" + path + "'}");
			}
		}
		return null;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>(); // HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		page.setPageSize(100);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("mainFlg,createdDate");
			page.setOrder(Page.DESC + "," + Page.DESC);
		}
		filters.add(new PropertyFilter("EQS_bizEntityId", bizEntityId));
		filters.add(new PropertyFilter("NEQS_statusCd", "0"));// 状态为正常
		if (StringUtils.isNotEmpty(bizModuleCd)) {
			filters.add(new PropertyFilter("EQS_bizModuleCd", bizModuleCd));
		}
		page = appAttachFileManager.findPage(page, filters);
		return SUCCESS;
	}

	public String show() throws Exception {
		list();
		return "show";
	}

	public String getAllImage() {
		String result = null;
		result = PowerUtils.array2String(CoreContants.FILE_SUFFIX_IMAGE);
		return result;
	}

	public String isImage() {

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

	public void prepareUpload() throws Exception {
		prepareModel();
	}
	public void prepareDownload() throws Exception {
		prepareModel();
	}

	public void prepareUploadRes() throws Exception {
		prepareModel();
	}

	@Override
	public String save() throws Exception {
		appAttachFileManager.saveAppAttachFile(entity);
		return RELOAD;
	}

	public void prepareChangeMainFlg() throws Exception {
		prepareModel();
	}

	/**
	 * id : appAttachFileId,
	 * bizEntityId: bizEntityId,
	 * bizEntityName: bizEntityName,
	 * mainFlg: mainFlg
	 * @return
	 * @throws Exception
	 */
	public String changeMainFlg() throws Exception {
		appAttachFileManager.updateMainFilePath(entity, bizEntityName, bizEntityId);
		return RELOAD;
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

	public String getAttachModelType() {
		return attachModelType;
	}

	public void setAttachModelType(String attachModelType) {
		this.attachModelType = attachModelType;
	}

	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public String getFiledataContentType() {
		return filedataContentType;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}

	public String getBizMainEnableFlg() {
		return bizMainEnableFlg;
	}

	public void setBizMainEnableFlg(String bizMainEnableFlg) {
		this.bizMainEnableFlg = bizMainEnableFlg;
	}
}

/**
 * 
 */
package com.hhz.ump.entity.sc;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;

/**
 * 附件操作类
 * @author qlb 2/23/2012 version 1.0.0
 * 
 */
public class ScContractAttachUpload {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 删除旧文件
	 * 
	 * @param dir
	 */
	@SuppressWarnings("unused")
	public void deleteFile(String filePath) {
		File file = new File(filePath);

		if (file.exists()) {
			boolean flag = file.delete();
			if (flag) {
				logger.info("删除成功:" + file.getPath());
			}
		}
	}
	/**
	 * 判断文件是否已存在
	 * @param filePath
	 * @return
	 */
	public boolean isExsitFile(String filePath){
		
			File file = new File(filePath);
			if (!file.exists())
				return false;
		
			return true;
	}

	/**
	 * 生成文件名
	 * 
	 * @param uploadFileName1
	 * @return
	 */
	public String genFileName(String uploadFileName1) {
		String tmpName = null;
		if (!StringUtils.isEmpty(uploadFileName1)) {
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
	public String getSuffix(String fileNameTmp) {
		String suffix = null;
		if (StringUtils.isNotEmpty(fileNameTmp)) {
			suffix = fileNameTmp.substring(fileNameTmp.lastIndexOf("."));
		}
		return suffix;
	}

	public File getDir(String uiid) {
		// String strPath =
		// ServletActionContext.getServletContext().getRealPath(savePath);
		File dir = new File(getSavePath(uiid, true));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir;
	}

	// ----
	public String getSavePath(String uiid, boolean isUpload) {
		return PowerUtils.getFilePath("sctemplet", uiid, isUpload);
	}
	
	// ----
	public String getSavePath(String bizModuleCd, String uiid, boolean isUpload) {
		return PowerUtils.getFilePath(bizModuleCd, uiid, isUpload);
	}

	/**
	 * 获取文件名
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public String getFileName(String filePath) {
		String fileName = "";
		if (StringUtils.isNotBlank(filePath)) {
			if(filePath.lastIndexOf('/')>0){
			fileName = filePath.substring(filePath.lastIndexOf('/') + 1, filePath.length());
			}else{
			fileName = filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.length());
			}
			return fileName;
		}

		return "";

	}

}

package com.hhz.uums.dao.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppAttachFile;
import com.hhz.uums.utils.Constants;

@Service
@Transactional
public class AppAttachFileManager extends BaseService<AppAttachFile, String> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AppAttachFileDao appAttachFileDao;

	/**
	 * 删除状态
	 */
	public static final String STATUS_DELETE = "0";

	/**
	 * 正常状态
	 */
	public static final String STATUS_NORMAL = "1";

	/**
	 * 临时状态
	 */
	public static final String STATUS_TEMP = "2";

	public void deleteAppAttachFile(String id) {
		AppAttachFile appAttachFile = getEntity(id);
		deleteAppAttachFile(appAttachFile);
	}

	public void saveAppAttachFile(AppAttachFile appAttachFile) {
		PowerUtils.setEmptyStr2Null(appAttachFile);
		appAttachFileDao.save(appAttachFile);
	}

	public void saveAppAttachFiles(List<AppAttachFile> lstAppAttachFile) {
		saveAppAttachFiles(lstAppAttachFile, null, null);
	}

	public void saveAppAttachFiles(List<AppAttachFile> lstAppAttachFile, String entityName, String bizEntityId) {
		for (AppAttachFile appAttachFile : lstAppAttachFile) {
			saveAppAttachFile(appAttachFile);
			updateMainFilePath(appAttachFile, entityName, bizEntityId);
		}
		if (entityName != null && bizEntityId != null) {
			updateAttachFlg(entityName, bizEntityId);
		}
	}

	private void updateAttachFlg(String entityName, String bizEntityId) {
		if (StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(bizEntityId)) {
			boolean attachFlg = false;
			if (isUpload(bizEntityId)) {
				attachFlg = true;
			}
			String entityId = entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + "Id";
			StringBuilder hql = new StringBuilder("update ");
			hql.append(entityName).append(" set attachFlg=? where ").append(entityId).append("=?");
			appAttachFileDao.batchExecute(hql.toString(), attachFlg, bizEntityId);
		}
	}

	/**
	 * @param appAttachFile
	 * @param entityName
	 *            : 表明对应的实体名(例如 "PrsProd")
	 * @param bizEntityId
	 */
	public void updateMainFilePath(AppAttachFile appAttachFile, String entityName, String bizEntityId) {
		if (appAttachFile != null && StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(bizEntityId)) {
			if (appAttachFile.getMainFlg() != null && appAttachFile.getMainFlg()) {
				String filePath = null;
				if (appAttachFile.getSmallPicName() != null) {
					filePath = appAttachFile.getFilePath() + "/" + appAttachFile.getSmallPicName();
				} else {
					filePath = appAttachFile.getFilePath() + "/" + appAttachFile.getFileName();
				}
				String entityId = entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + "Id";
				StringBuilder hql = new StringBuilder("update ");
				hql.append(entityName).append(" set mainFilePath=? where ").append(entityId).append("=?");
				appAttachFileDao.batchExecute(hql.toString(), filePath, bizEntityId);
			}
		}
		saveAppAttachFile(appAttachFile);
	}

	public String getRealFileNameById(String id) {
		AppAttachFile attachFile = appAttachFileDao.get(id);
		return attachFile.getRealFileName();
	}

	public void invalidModuleData(String bizModuleCd, String bizEntityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusCdDisable", "0");
		map.put("statusCdEnable", "1");
		map.put("statusCdTmp", "2");
		map.put("bizEntityId", bizEntityId);
		map.put("bizModuleCd", bizModuleCd);
		map.put("updator", SpringSecurityUtils.getLoginCode());
		map.put("updatedDate", new Date());
		StringBuilder hql = new StringBuilder(
				"update AppAttachFile set statusCd=:statusCdDisable, updator= :updator, updatedDate= :updatedDate where bizEntityId = :bizEntityId and bizModuleCd = :bizModuleCd and  (statusCd=:statusCdEnable or  statusCd=:statusCdTmp)");
		appAttachFileDao.batchExecute(hql.toString(), map);
	}

	/**
	 * 更新临时文件
	 * 
	 * @param entityTmpId
	 * @param entityId
	 */
	public void updateTmpFile(String entityTmpId, String entityId) {
		updateTmpFile(entityTmpId, null, entityId);
	}

	public void updateTmpFile(String entityTmpId, String entityName, String entityId) {
		if (StringUtils.isNotEmpty(entityTmpId)) {
			List<AppAttachFile> lstAppAttachFile = getAttaFileByBizEntityId(entityTmpId);
			for (AppAttachFile attachFile : lstAppAttachFile) {
				attachFile.setBizEntityId(entityId);
				attachFile.setStatusCd(AppAttachFileManager.STATUS_NORMAL);
			}
			saveAppAttachFiles(lstAppAttachFile, entityName, entityId);
		}
	}

	/**
	 * 根据实体ID和模块CD查找跟某实体关联的附件
	 * 
	 * @param bizEntityId
	 * @param bizModuleCd
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppAttachFile> getAttachFilesByEntityIdAndModuleCd(String bizEntityId, String bizModuleCd) {
		if (StringUtils.isBlank(bizEntityId))
			throw new IllegalArgumentException("实体ID不能为空");

		if (StringUtils.isBlank(bizModuleCd))
			throw new IllegalArgumentException("模块CD不能为空");

		String hql = "from AppAttachFile where bizEntityId = :bizEntityId "
				+ "and bizModuleCd = :bizModuleCd and statusCd != '0' order by createdDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizEntityId", bizEntityId);
		params.put("bizModuleCd", bizModuleCd);

		return find(hql, params);
	}

	/**
	 * 删除不在列表内的附件<br>
	 * 如果appAttachFileIds为空则全部删除
	 * 
	 * @param entityId
	 * @param appAttachFileIds
	 */
	public void deleteFileNotExist(String entityId, List<String> appAttachFileIds) {
		List<AppAttachFile> lstAppAttachFile = getAttaFileByBizEntityId(entityId);
		if (appAttachFileIds == null) {
			for (AppAttachFile attachFile : lstAppAttachFile) {
				this.delete(attachFile);
			}
		} else {
			for (AppAttachFile attachFile : lstAppAttachFile) {
				if (appAttachFileIds.contains(attachFile.getAppAttachFileId())) {
					continue;
				}
				this.delete(attachFile);
			}
		}
	}

	/**
	 * 删除菜单图标
	 * 
	 * @param entityId
	 */
	public void deleteAllFile(String entityId) {
		deleteFileNotExist(entityId, null);
	}

	/**
	 * 是否上传过附件
	 * 
	 * @param entityId
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isUpload(String entityId) {
		Criterion cBizEntityId = Restrictions.eq("bizEntityId", entityId);
		Criterion cStatusCd = Restrictions.eq("statusCd", STATUS_NORMAL);
		int cnt = countCriteriaResult(cBizEntityId, cStatusCd);
		return cnt > 0;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppAttachFile> getAttaFileByBizEntityId(String bizEntityId) {

		PropertyFilter filter = new PropertyFilter("EQS_bizEntityId", bizEntityId);
		PropertyFilter filter2 = new PropertyFilter("NEQS_statusCd", STATUS_DELETE);
		Page<AppAttachFile> page = new Page<AppAttachFile>(100);
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		filterList.add(filter);
		filterList.add(filter2);
		page.setOrderBy("createdDate");
		page.setOrder(Page.DESC);
		return findPage(page, filterList).getResult();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppAttachFile> getLastFile(String bizEntityId, String bizModuleCd) {

		PropertyFilter filter = new PropertyFilter("EQS_bizEntityId", bizEntityId);
		PropertyFilter filter1 = new PropertyFilter("EQS_bizModuleCd", bizModuleCd);
		PropertyFilter filter2 = new PropertyFilter("NEQS_statusCd", STATUS_DELETE);
		Page<AppAttachFile> page = new Page<AppAttachFile>(100);
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		filterList.add(filter);
		filterList.add(filter1);
		filterList.add(filter2);
		page.setOrderBy("createdDate");
		page.setOrder(Page.DESC);
		return findPage(page, filterList).getResult();
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 */
	public void deleteLogic(String id, String entityName, String bizEntityId) {
		AppAttachFile attachFileTmp = appAttachFileDao.get(id);
		attachFileTmp.setStatusCd(STATUS_DELETE);
		appAttachFileDao.save(attachFileTmp);
		updateAttachFlg(entityName, bizEntityId);
	}

	/**
	 * 删除旧文件
	 * 
	 * @param dir
	 */
	private void deleteFile(File dir, String fileNameOld) {
		if (StringUtils.isNotEmpty(fileNameOld)) {
			File file = new File(dir, fileNameOld);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	public void deleteByBizEntityId(String bizEntityId) {
		List<AppAttachFile> appAttachFiles = getAttaFileByBizEntityId(bizEntityId);
		for (AppAttachFile appAttachFile : appAttachFiles) {
			deleteAppAttachFile(appAttachFile);
		}
	}

	public void deleteAppAttachFile(AppAttachFile attachFileTmp) {
		File dir =new File(attachFileTmp.getFilePath());// getDir(attachFileTmp.getBizModuleCd());
		deleteFile(dir, attachFileTmp.getFileName());
		if (StringUtils.isNotEmpty(attachFileTmp.getSmallPicName())) {
			deleteFile(dir, attachFileTmp.getSmallPicName());
		}
		appAttachFileDao.delete(attachFileTmp);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppAttachFile, String> getDao() {
		return appAttachFileDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppAttachFile> getAttachFiles(String entityBizId, String bizModuleCd, String statusCd) {
		return appAttachFileDao.createCriteria(AppAttachFile.class).add(Restrictions.eq("bizEntityId", entityBizId)).add(
				Restrictions.eq("bizModuleCd", bizModuleCd)).add(Restrictions.eq("statusCd", statusCd)).list();
	}

	/**
	 * @param userId
	 * @return
	 */
	public String getPicturePath(String userId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>(); // HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		filters.add(new PropertyFilter("EQS_bizEntityId", userId));
		filters.add(new PropertyFilter("NEQS_statusCd", "0"));// 状态为正常
		List<AppAttachFile> list = find(filters);
		if(list == null || list.size() == 0)
			return "";
		else{
			StringBuffer urlBuf = new StringBuffer();
			AppAttachFile file = list.get(0);
			urlBuf.append("/app/download.action?fileName=")
				  .append(file.getFileName())
				  .append("&realFileName=")
				  .append(file.getRealFileName())
				  .append("&bizModuleCd=").append(Constants.APP_ATTACH_FILE_USER);
			
			return urlBuf.toString();
		}
	}
}

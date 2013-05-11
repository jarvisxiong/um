package com.hhz.ump.dao.app;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
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
import com.hhz.ump.entity.app.AppAttachFile;

@Service
@Transactional
public class AppAttachFileManager extends BaseService<AppAttachFile, String> {

	private Logger logger = Logger.getLogger(AppAttachFileManager.class);

	@Autowired
	private AppAttachFileDao appAttachFileDao;

	/**
	 *删除状态
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
		}
		if (entityName != null && bizEntityId != null) {
			updateAttachFlg(entityName, bizEntityId);
		}
	}
	
	public void saveAppAttachFiles(List<AppAttachFile> lstAppAttachFile, String entityName, String bizEntityId, String bizFiledName) {
		for (AppAttachFile appAttachFile : lstAppAttachFile) {
			saveAppAttachFile(appAttachFile);
		}
		if (entityName != null && bizEntityId != null) {
			updateAttachFlg(entityName, bizEntityId, bizFiledName);
		}
	}

	private void updateAttachFlg(String entityName, String bizEntityId) {
		if (StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(bizEntityId)) {
			String attachFlg = null;
			if (isUpload(bizEntityId)) {
				attachFlg = "1";
			} else {
				attachFlg = "0";
			}
			String entityId = entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + "Id";
			StringBuilder hql = new StringBuilder("update ");
			hql.append(entityName).append(" set attachFlg=? where ").append(entityId).append("=?");
			appAttachFileDao.batchExecute(hql.toString(), attachFlg, bizEntityId);
		}
	}

	private void updateAttachFlg(String entityName, String bizEntityId, String bizFiledName) {
		if (StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(bizEntityId)) {
			String attachFlg = null;
			if (isUpload(bizEntityId)) {
				attachFlg = "1";
			} else {
				attachFlg = "0";
			}
			String entityId = entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + "Id";
			StringBuilder hql = new StringBuilder("update ");
			if(StringUtils.isNotEmpty(bizFiledName)){
				hql.append(entityName).append(" set "+bizFiledName+"=? where ").append(entityId).append("=?");
			}else{
				hql.append(entityName).append(" set attachFlg=? where ").append(entityId).append("=?");
			}
			appAttachFileDao.batchExecute(hql.toString(), attachFlg, bizEntityId);
		}
	}

	public String getRealFileNameById(String id) {
		AppAttachFile attachFile = appAttachFileDao.get(id);
		return attachFile.getRealFileName();
	}

	public void deleteByBizEntityId(String bizEntityId, boolean onlyFile) {
		List<AppAttachFile> appAttachFiles = getAttaFileByBizEntityId(bizEntityId);
		for (AppAttachFile appAttachFile : appAttachFiles) {
			deleteAppAttachFile(appAttachFile, onlyFile);
		}
	}

	public void deleteAppAttachFile(AppAttachFile attachFileTmp, String entityName, String bizEntityId, boolean onlyFile) {
		String strPath = attachFileTmp.getFilePath();
		deleteFile(strPath, attachFileTmp.getFileName(), attachFileTmp.getCreator());
		if (StringUtils.isNotEmpty(attachFileTmp.getSmallPicName())) {
			deleteFile(strPath, attachFileTmp.getSmallPicName(), attachFileTmp.getCreator());
		}
		if (!onlyFile) {
			// 不保留记录
			appAttachFileDao.delete(attachFileTmp);
			updateAttachFlg(entityName, bizEntityId);
		}
	}

	public void deleteAppAttachFile(AppAttachFile attachFileTmp, boolean onlyFile) {
		deleteAppAttachFile(attachFileTmp, null, null, onlyFile);
	}

	/**
	 * 删除旧文件
	 * 
	 * @param dir
	 */
	private void deleteFile(String filePath, String fileName, String uiid) {
		File file = new File(filePath + "\\" + fileName);
		if (!file.exists()) {
			file = new File(filePath + "\\" + uiid + "\\" + fileName);
		}
		if (file.exists()) {
			boolean flag = file.delete();
			if (flag) {
				logger.info("删除成功:" + file.getPath());
			}
		}
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
	
	public void updateTmpFile(String entityTmpId, String entityName, String entityId,String bizFieldName) {
		if (StringUtils.isNotEmpty(entityTmpId)) {
			List<AppAttachFile> lstAppAttachFile;
			if (StringUtils.isNotEmpty(bizFieldName)) {
				lstAppAttachFile = getAttaFileByBizEntityId(entityTmpId,true,bizFieldName);
			}else{
				lstAppAttachFile = getAttaFileByBizEntityId(entityTmpId);
			}
			for (AppAttachFile attachFile : lstAppAttachFile) {
				attachFile.setBizEntityId(entityId);
				attachFile.setStatusCd(AppAttachFileManager.STATUS_NORMAL);
				if (StringUtils.isNotEmpty(bizFieldName)) {
					attachFile.setBizFieldName(bizFieldName);
				}
			}
			saveAppAttachFiles(lstAppAttachFile, entityName, entityId, bizFieldName);
		}
	}

	public AppAttachFile getByEntityId(String entityId) {
		try {
			return getEntity(entityId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public void updateApp() {
		Map<String, Object> params = new HashMap<String, Object>();
		String sqlProject = "select com_Bas_File_Sn,com_Rang_File_Sn,com_Zx_File_Sn,com_Zz_File_Sn,com_Save_File_Sn,com_Mana_File_Sn,"
				+ "com_Qual_File_Sn,com_Hono_File_Sn,com_Fin_File_Sn,com_Pro_File_Sn,com_Exam_File_Sn,com_Eval_File_Sn,com_Main_File_Sn,cooperated_Sn"
				+ " from SUP_DETAIL where creator is null";
		List projects = appAttachFileDao.findBySql(sqlProject, params);
		for (int i = 0; i < projects.size(); i++) {
			Object[] objPro = (Object[]) projects.get(i);
			for (int j = 0; j < objPro.length; j++) {
				String str = (String) objPro[j];
				if (str != null && !"".equals(str)) {
					// 搜索UPLOADFILES记录
					String oldSql = "select * from UPLOADFILES where batchid='" + str + "'";
					List oldList = appAttachFileDao.findBySql(oldSql, params);
					if (oldList != null && oldList.size() > 0) {
						for (int k = 0; k < oldList.size(); k++) {
							Object[] oldObject = (Object[]) oldList.get(k);
							AppAttachFile app = new AppAttachFile();
							app.setRemark((String) oldObject[0]);
							app.setBizEntityId((String) oldObject[1]);
							app.setBizModuleCd("suppliers");
							app.setFileName((String) oldObject[4]);
							app.setRealFileName((String) oldObject[3]);
							app.setFilePath("E:\\suppliers");
							app.setFileTypeName("text/plain");
							app.setFileSize(new BigDecimal(100));
							app.setStatusCd("2");
							app.setRecordVersion(0);
							// PowerUtils.setEmptyStr2Null(app);
							appAttachFileDao.save(app);

						}
					}
				}
			}
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
	 * 根据文件名取得附件实体
	 * 
	 * @param fileName
	 * @return
	 */
	public AppAttachFile getAttachFile(String fileName) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_fileName", fileName));
		List<AppAttachFile> files = find(filters);
		if (files.size() > 0)
			return files.get(0);
		return null;
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
	public void deleteAppMenu(String entityId) {
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
		// int cnt = countCriteriaResult(cBizEntityId);
		return cnt > 0;
	}

	public List<AppAttachFile> getAttaFileByBizEntityId(String bizEntityId) {
		return getAttaFileByBizEntityId(bizEntityId, true);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppAttachFile> getAttaFileByBizEntityId(String bizEntityId, boolean isOrder) {

		// Criterion criterion = Restrictions.eq("bizEntityId", bizEntityId);
		// Criterion cStatusCd = Restrictions.ne("statusCd", STATUS_DELETE);
		// return findBy(criterion, cStatusCd);

		PropertyFilter filter = new PropertyFilter("EQS_bizEntityId", bizEntityId);
		PropertyFilter filter2 = new PropertyFilter("NEQS_statusCd", STATUS_DELETE);
		Page<AppAttachFile> page = new Page<AppAttachFile>(100);
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		filterList.add(filter);
		filterList.add(filter2);
		if (isOrder) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
		return findPage(page, filterList).getResult();
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppAttachFile> getAttaFileByBizEntityId(String bizEntityId, boolean isOrder, String bizFieldName) {
		PropertyFilter filter = new PropertyFilter("EQS_bizEntityId", bizEntityId);
		PropertyFilter filter2 = new PropertyFilter("NEQS_statusCd", STATUS_DELETE);
		Page<AppAttachFile> page = new Page<AppAttachFile>(100);
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		filterList.add(filter);
		filterList.add(filter2);
		if (StringUtils.isNotEmpty(bizFieldName)) {
			PropertyFilter filter3 = new PropertyFilter("EQS_bizFieldName", bizFieldName);
			filterList.add(filter3);
		}
		if (isOrder) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
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
	 * 判断实体是否包含附件
	 * 
	 * @param bizEntityId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean ifEntityHasAttach(String bizEntityId) {
		if (StringUtils.isNotBlank(bizEntityId)) {
			String hql = "select count(*) from AppAttachFile where bizEntityId='" + bizEntityId + "' and statusCd!='"
					+ STATUS_DELETE + "'";
			List result = getDao().getSession().createQuery(hql).list();
			Long m = (Long) result.get(0);
			if (m != null && m > 0)
				return true;
		}
		return false;
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

	public void clearDelFile() {
		Criterion criterion = Restrictions.ne("statusCd", AppAttachFileManager.STATUS_NORMAL);
		List<AppAttachFile> files = findBy(criterion);
		for (AppAttachFile appAttachFile : files) {
			deleteAppAttachFile(appAttachFile, false);
		}
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppAttachFile, String> getDao() {
		return appAttachFileDao;
	}

	public void invalidModuleData(String bizModuleCd, String bizEntityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusCdDisable", "0");
		map.put("statusCdEnable", "1");
		map.put("statusCdTmp", "2");
		map.put("bizEntityId", bizEntityId);
		map.put("bizModuleCd", bizModuleCd);
		map.put("updator", SpringSecurityUtils.getCurrentUiid());
		map.put("updatedDate", new Date());
		StringBuilder hql = new StringBuilder(
				"update AppAttachFile set statusCd=:statusCdDisable, updator= :updator, updatedDate= :updatedDate where bizEntityId = :bizEntityId and bizModuleCd = :bizModuleCd and  (statusCd=:statusCdEnable or  statusCd=:statusCdTmp)");
		try {
			appAttachFileDao.batchExecute(hql.toString(), map);
		} catch (Exception e) {
			logger.error("附件失效异常", e);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppAttachFile> getAttachFiles(String entityBizId, String bizModuleCd, String... statusCd) {
		return appAttachFileDao.createCriteria(AppAttachFile.class).add(Restrictions.eq("bizEntityId", entityBizId))
				.add(Restrictions.eq("bizModuleCd", bizModuleCd)).add(Restrictions.in("statusCd", statusCd)).list();
	}

	/**
	 * 根据实体ID和fileName(唯一)查找跟某实体关联的附件
	 * 
	 * @param bizEntityId
	 * @param bizModuleCd
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<AppAttachFile> getAttachFilesByEntityIdAndFileName(String bizEntityId, String fileName) {
		if (StringUtils.isBlank(bizEntityId))
			throw new IllegalArgumentException("实体ID不能为空");

		if (StringUtils.isBlank(fileName))
			throw new IllegalArgumentException("模块CD不能为空");

		String hql = "from AppAttachFile where bizEntityId = :bizEntityId "
				+ "and fileName = :fileName and statusCd != '0' order by createdDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizEntityId", bizEntityId);
		params.put("fileName", fileName);

		return find(hql, params);
	}
	public void reduceDownloadTime(String id){
		String sql="update APP_ATTACH_FILE f set F.DOWNLOAD_TIMES = F.DOWNLOAD_TIMES - 1 where f.APP_ATTACH_FILE_ID=:attachFileId";
		Map<String, Object> values=new HashMap<String, Object>();
		values.put("attachFileId", id);
		appAttachFileDao.batchExecuteSql(sql, values);
	}
	
	/**
	 * 根据企划资料信息主键ID删除
	 * @param pmMateEntryIds
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delAttachFiles(String...pmMateEntryIds){
		Map<String, Object> values=new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("delete from AppAttachFile f where f.bizEntityId in (:pmMateEntryIds)");
		values.put("pmMateEntryIds", pmMateEntryIds);
		appAttachFileDao.batchExecute(hql.toString(), values);
	}
	
	/**
	 * 更新附件外键Id
	 * @param bizEntityTempId
	 * @param bizEntityId
	 * @param bizModuleCd
	 */
	public void updateBizEntityId(String bizEntityTempId,String bizEntityId,String bizModuleCd){
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		sb.append(" UPDATE app_attach_file t ");
		sb.append(" SET t.biz_entity_id = :bizEntityId ");
		map.put("bizEntityId", bizEntityId);
		sb.append(" where 1=1");
		if(StringUtils.isNotBlank(bizEntityTempId)&&StringUtils.isNotBlank(bizModuleCd)){
			sb.append(" and t.biz_module_cd = :bizModuleCd ");
			map.put("bizModuleCd", bizModuleCd);
			sb.append(" and t.biz_entity_id = :bizEntityTempId");
			map.put("bizEntityTempId", bizEntityTempId);
		}
		Query query = appAttachFileDao.createSQLQuery(sb.toString(), map);
		query.executeUpdate();
	}
}

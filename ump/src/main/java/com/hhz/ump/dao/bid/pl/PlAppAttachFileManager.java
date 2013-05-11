package com.hhz.ump.dao.bid.pl;

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
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.pl.PlAppAttachFile;

@Service
@Transactional
public class PlAppAttachFileManager extends BaseService<PlAppAttachFile, String> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PlAppAttachFileDao plAppAttachFileDao;

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

	public void savePlAppAttachFile(PlAppAttachFile plAppAttachFile) {
		PowerUtils.setEmptyStr2Null(plAppAttachFile);
		plAppAttachFileDao.save(plAppAttachFile);
	}

	public void savePlAppAttachFiles(List<PlAppAttachFile> lstPlAppAttachFile) {
		savePlAppAttachFiles(lstPlAppAttachFile, null, null);
	}

	public void savePlAppAttachFiles(List<PlAppAttachFile> lstPlAppAttachFile, String entityName, String bizEntityId) {
		for (PlAppAttachFile plPlAppAttachFile : lstPlAppAttachFile) {
			savePlAppAttachFile(plPlAppAttachFile);
		}
		if (entityName != null && bizEntityId != null) {
			updateAttachFlg(entityName, bizEntityId);
		}
	}

	private void updateAttachFlg(String entityName, String bizEntityId) {
		if (StringUtils.isNotEmpty(entityName) && StringUtils.isNotEmpty(bizEntityId)) {
			String attachFlg = null;
			if (isUpload(bizEntityId)) {
				attachFlg = STATUS_NORMAL;
			} else {
				attachFlg = STATUS_DELETE;
			}
			String entityId = entityName.substring(0, 1).toLowerCase() + entityName.substring(1) + "Id";
			StringBuilder hql = new StringBuilder("update ");
			hql.append(entityName).append(" set attachFlg=? where ").append(entityId).append("=?");
			plAppAttachFileDao.batchExecute(hql.toString(), attachFlg, bizEntityId);
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
			List<PlAppAttachFile> lstPlAppAttachFile = getAttaFileByBizEntityId(entityTmpId);
			for (PlAppAttachFile attachFile : lstPlAppAttachFile) {
				attachFile.setBizEntityId(entityId);
				attachFile.setStatusCd(PlAppAttachFileManager.STATUS_NORMAL);
			}
			savePlAppAttachFiles(lstPlAppAttachFile, entityName, entityId);
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
	public List<PlAppAttachFile> getAttachFilesByEntityIdAndModuleCd(String bizEntityId, String bizModuleCd) {
		if (StringUtils.isBlank(bizEntityId))
			throw new IllegalArgumentException("实体ID不能为空");

		if (StringUtils.isBlank(bizModuleCd))
			throw new IllegalArgumentException("模块CD不能为空");

		String hql = "from PlAppAttachFile where bizEntityId = :bizEntityId "
				+ "and bizModuleCd = :bizModuleCd and statusCd != '0' order by createdDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizEntityId", bizEntityId);
		params.put("bizModuleCd", bizModuleCd);

		return find(hql, params);
	}

	/**
	 * 根据实体ID和fileName(唯一)查找跟某实体关联的附件
	 * 
	 * @param bizEntityId
	 * @param bizModuleCd
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlAppAttachFile> getAttachFilesByEntityIdAndFileName(String bizEntityId, String fileName) {
		if (StringUtils.isBlank(bizEntityId))
			throw new IllegalArgumentException("实体ID不能为空");

		if (StringUtils.isBlank(fileName))
			throw new IllegalArgumentException("模块CD不能为空");

		String hql = "from PlAppAttachFile where bizEntityId = :bizEntityId "
				+ "and fileName = :fileName and statusCd != '0' order by createdDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("bizEntityId", bizEntityId);
		params.put("fileName", fileName);

		return find(hql, params);
	}

	/**
	 * 删除不在列表内的附件<br>
	 * 如果PlAppAttachFileIds为空则全部删除
	 * 
	 * @param entityId
	 * @param PlAppAttachFileIds
	 */
	public void deleteFileNotExist(String entityId, List<String> PlAppAttachFileIds) {
		List<PlAppAttachFile> lstPlAppAttachFile = getAttaFileByBizEntityId(entityId);
		if(PlAppAttachFileIds == null){
			for (PlAppAttachFile attachFile : lstPlAppAttachFile) {
				this.delete(attachFile);
			}
		} else {
			for (PlAppAttachFile attachFile : lstPlAppAttachFile) {
				if (PlAppAttachFileIds.contains(attachFile.getAppAttachFileId())) {
					continue;
				}
				this.delete(attachFile);
			}
		}
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

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlAppAttachFile> getAttaFileByBizEntityId(String bizEntityId) {
		Criterion criterion = Restrictions.eq("bizEntityId", bizEntityId);
		return findBy(criterion);
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 */
	public void deleteLogic(String id, String entityName, String bizEntityId) {
		PlAppAttachFile attachFileTmp = plAppAttachFileDao.get(id);
		attachFileTmp.setStatusCd(STATUS_DELETE);
		plAppAttachFileDao.save(attachFileTmp);
		updateAttachFlg(entityName, bizEntityId);
	}

	public void deletePlAppAttachFile(PlAppAttachFile attachFileTmp) {
		plAppAttachFileDao.delete(attachFileTmp);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<PlAppAttachFile, String> getDao() {
		return plAppAttachFileDao;
	}

}

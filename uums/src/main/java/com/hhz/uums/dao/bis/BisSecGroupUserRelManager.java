package com.hhz.uums.dao.bis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.entity.bis.BisSecGroup;
import com.hhz.uums.entity.bis.BisSecGroupUserRel;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.OperConst;

@Service
@Transactional
public class BisSecGroupUserRelManager extends BaseService<BisSecGroupUserRel, String> {
	private static final Log log = LogFactory.getLog(BisSecGroupUserRelManager.class);
	@Autowired
	private BisSecGroupUserRelDao bisSecGroupUserRelDao;
	@Autowired
	private BisSecGroupManager secGroupManager;

	@Autowired
	private PlasOperateLogManager uaapOperateLogManager;

	public void saveBisSecGroupUserRel(BisSecGroupUserRel bisSecGroupUserRel) {
		PowerUtils.setEmptyStr2Null(bisSecGroupUserRel);
		bisSecGroupUserRelDao.save(bisSecGroupUserRel);
	}

	public void deleteBisSecGroupUserRel(String id) {
		bisSecGroupUserRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisSecGroupUserRel, String> getDao() {
		return bisSecGroupUserRelDao;
	}
	/**
	 * 获取虚拟机构的用户清单
	 * 
	 * @param secGroupId
	 * @return
	 */
	public List<String> getDispatchedOrgUsers(String secGroupId) {

		String hql = " select t.plasUserId from PlasUser t, SecGroupUserRel t2 where t2.secGroup.secGroupId = ? and t.plasUserId = t2.userId  ";
		log.debug("查询指定组ID对应的人员清单:" + hql);
		return getDao().createQuery(hql, secGroupId).list();
	}

	/**
	 * 保存虚拟机构的用户清单
	 * 
	 * @param groupId
	 * @param addUserIds
	 * @param delUserIds
	 */
	public void saveBatchGroupUsers(String groupId, String addUserIds, String addUserTexts, String delUserIds,
			String delUserTexts, String addExtParams, String delExtParams) {

		BisSecGroup secGroup = secGroupManager.getEntity(groupId);

		if (StringUtils.isBlank(groupId)) {
			log.error(" 保存加密组员,组ID为空! ");
			return;
		}

		log.info("保存加密组[" + secGroup.getGroupBizCd() + "," + secGroup.getGroupName() + "] 新增 " + addUserIds.length()
				+ "个 ,删除 " + delUserIds.length() + "个 ");

		log.info("保存加密组[" + secGroup.getGroupBizCd() + "," + secGroup.getGroupName() + "] 新增组员:" + addUserTexts + "("
				+ addExtParams + "),删除组员:" + delUserTexts + "(" + delExtParams + ")");

		String[] arrayAddIds = StringUtils.split(addUserIds, ",");
		// String[] arrayAddTexts = StringUtils.split(addUserTexts, ",");
		String[] arrayDelIds = StringUtils.split(delUserIds, ",");
		// String[] arrayDelTexts = StringUtils.split(delUserTexts, ",");
		// String[] arrayAddExtParams = StringUtils.split(addExtParams, ",");
		// String[] arrayDelExtParams = StringUtils.split(delExtParams, ",");

		// 新增组员
		for (int i = 0; i < arrayAddIds.length; i++) {
			String userId = arrayAddIds[i];
			BisSecGroupUserRel rel = new BisSecGroupUserRel();
			rel.setBisSecGroup(secGroup);
			rel.setPlasUserId(userId);
			this.getDao().save(rel);
			//sbAdd.append(arrayAddTexts[i]).append("(").append(arrayAddExtParams[i]).append(") ");
		}

		// 收回组员
		for (int i = 0; i < arrayDelIds.length; i++) {
			String userId = arrayDelIds[i];
			this.deleteByGroupIdUserId(groupId, userId);
			//sbAdd.append(arrayDelTexts[i]).append("(").append(arrayDelExtParams[i]).append(") ");
		}
		

		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();

		// 操作日志
		uaapOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SEC, OperConst.BATCH,
				new StringBuffer("[").append(secGroup.getBisSecGroupId()).append(",").append(secGroup.getGroupBizCd())
						.append(",").append(secGroup.getGroupName()).append("]批量调整组员! ").append(" 收回组员  共").append(
								arrayDelIds.length).append(" 个").append(" 新增组员  共").append(arrayAddIds.length).append(
								" 个").toString());

	}

	/**
	 * 删除组关系
	 * 
	 * @param groupId
	 * @param userId
	 */
	private int deleteByGroupIdUserId(String secGroupId, String userId) {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("secGroupId",secGroupId);
		values.put("userId",userId);
		
		String hql = " delete from SecGroupUserRel t where t.secGroup.secGroupId = :secGroupId and t.userId = :userId ";
		
		int delRecords = getDao().batchExecute(hql,values);
		log.info(" 删除组成员关系 : secGroupId(" + secGroupId + ") userId(" + userId + ")共" + delRecords + " 条记录!");
		return delRecords;
	}

	/**
	 * 查询指定组ID对应的人员ID关系清单
	 * @return
	 */
	public List<Object> getGroupUserRels() {

		String hql = " select t2.bisSecGroup.bisSecGroupId,t2.plasUserId from PlasUser t, BisSecGroupUserRel t2 where t.plasUserId = t2.plasUserId and t.serviceStatusCd = :userEnable ";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userEnable", DictContants.PLAS_SERVICE_STATUS_ON);

		log.debug("查询指定组ID对应的人员ID关系清单:" + hql);
		return getDao().createQuery(hql, values).list();
	}

	/**
	 * 查询指定组ID对应的人员ID关系清单
	 * @param secGroupId
	 * @return
	 */
	public List<Object> getGroupUserRelsByGroup(String secGroupId) {
		String hql = " select t2.bisSecGroup.bisSecGroupId,t2.plasUserId from PlasUser t, BisSecGroupUserRel t2 where t.plasUserId = t2.plasUserId and t.serviceStatusCd = :userEnable and t2.bisSecGroup.bisSecGroupId = :secGroupId";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userEnable", DictContants.PLAS_SERVICE_STATUS_ON);
		values.put("secGroupId", secGroupId);

		log.debug("查询指定组ID对应的人员ID关系清单:" + hql);
		return getDao().createQuery(hql, values).list();
	}
	
	
	/**
	 * 功能: 更新用户所在的职级组
	 * @param uaapUserId
	 * @param oldPositionLevelCd
	 * @param newPositionLevelCd
	 */
	public void saveChangePermLevel(String userId,String oldGroupBizCd,String newGroupBizCd){
		if(StringUtils.isNotBlank(oldGroupBizCd)){
			deleteByGroupBizCdUserId(DictContants.PLAS_TAG_TYPE_LEVEL,oldGroupBizCd, userId);
		}
		if(StringUtils.isNotBlank(newGroupBizCd)){
			BisSecGroup secGroup  = secGroupManager.getSecGroupByBizCd(DictContants.PLAS_TAG_TYPE_LEVEL, newGroupBizCd);
			if(secGroup == null){
				log.warn("组业务编号("+ newGroupBizCd +")无效");
			}else{
				BisSecGroupUserRel rel = new BisSecGroupUserRel();
				rel.setBisSecGroup(secGroup);
				rel.setPlasUserId(userId);
				saveBisSecGroupUserRel(rel);
				log.debug("已将用户["+userId+"]并入职级组["+secGroup.getGroupBizCd()+","+secGroup.getGroupName()+"]");
			}
		}
	}

	/**
	 * 功能: 删除组成员关系
	 * @param groupBizCd
	 * @param userId
	 */
	private int deleteByGroupBizCdUserId(String tagTypeCd, String groupBizCd, String userId) {
		BisSecGroup secGroup = secGroupManager.getSecGroupByBizCd(tagTypeCd,groupBizCd);
		if(secGroup == null) return 0;
		return deleteByGroupIdUserId(secGroup.getBisSecGroupId(), userId);
	}
}


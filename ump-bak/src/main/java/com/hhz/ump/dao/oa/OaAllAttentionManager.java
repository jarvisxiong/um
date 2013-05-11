package com.hhz.ump.dao.oa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.lob.SerializableClob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.ump.entity.oa.OaAllAttention;
import com.hhz.ump.util.Util;

@Service
@Transactional
public class OaAllAttentionManager extends BaseService<OaAllAttention, String> {
	@Autowired
	private OaAllAttentionDao oaAllAttentionDao;

	//通用的保存方法，先搜索，再保存
	public void saveByParam(String moduleCd,String entityId,long moduleRecordVersion,String uiid){
		try {
			OaAllAttention entity = new OaAllAttention();
	    	int sameNumber = getAttention(moduleCd, uiid, entityId);
	    	if(0==sameNumber){
	    		entity.setModuleCd(moduleCd);
	    		entity.setEntityId(entityId);
				entity.setModuleRecordVersion(moduleRecordVersion);
		    	entity.setUserCd(uiid);
		    	saveOaAllAttention(entity);
				Struts2Utils.renderText("success");
	    	}
		} catch (Exception e) {
			Struts2Utils.renderText("failure");
		}
	}
	//通用的保存方法，先搜索，再保存
	public void saveByParamNoRender(String moduleCd,String entityId,long moduleRecordVersion,String uiid){
		try {
			OaAllAttention entity = new OaAllAttention();
	    	int sameNumber = getAttention(moduleCd, uiid, entityId);
	    	if(0==sameNumber){
	    		entity.setModuleCd(moduleCd);
	    		entity.setEntityId(entityId);
				entity.setModuleRecordVersion(moduleRecordVersion);
		    	entity.setUserCd(uiid);
		    	saveOaAllAttention(entity);
	    	}
		} catch (Exception e) {
		}
	}
	
	public void saveOaAllAttention(OaAllAttention oaAllAttention) {
		//PowerUtils.setEmptyStr2Null(oaAllAttention);
		oaAllAttentionDao.save(oaAllAttention);
	}

	public void deleteOaAllAttention(String id) {
		oaAllAttentionDao.delete(id);
	}
	
	@Override
	public HibernateDao<OaAllAttention, String> getDao() {
		return oaAllAttentionDao;
	}
	/**
	 * 保存关注事项
	 * @param oaAllAttention
	 */
   public void save(OaAllAttention oaAllAttention){
	   oaAllAttentionDao.save(oaAllAttention);
   }
   
	/**
	 * 删除关注事项
	 * @param id
	 */
	public void delete(String id) {
		oaAllAttentionDao.delete(id);
	}

   /**
    * 删除关注事项
    * @param moduleCd 模块名称
    * @param userCd 用户姓名
    * @param entityId 删除的记录的Id
    */
   public void delete(String moduleCd,String uiid,String entityId){
	   String sql = "delete from OaAllAttention where entityId=? and userCd=? and moduleCd=?";
	   oaAllAttentionDao.batchExecute(sql, entityId, uiid, moduleCd);
   }
//
//   /**
//    * 在关注存储前，差一下有没有重复的记录
//    * @param entity
//    * @param userCd
//    * @return
//    */
//   public OaAllAttention getToSaveAttention(String moduleCd,String userCd,String entityId){
//	String sql = "select * from OaAllAttention where moduleCd=? and userCd=? and entityId=?";
//	List<OaAllAttention> oaAll =  oaAllAttentionDao.find(sql.toString(), moduleCd, userCd,entityId);
//	return (OaAllAttention) oaAll;
//   }
   
   /**
    * 搜索关注事项
    * @param entity
    * @param userCd
    * @return
    */
   public OaAllAttention search(String moduleCd,String userCd){
	String sql = "select * from OaAllAttention where moduleCd=? and userCd=?";
	List<OaAllAttention> oaAll =  oaAllAttentionDao.find(sql.toString(), moduleCd, userCd);
	return (OaAllAttention) oaAll;
   }
   /**
    * 提供模块关注条数
    * @param entity
    * @param userCd
    * @return
    */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getMaxNo(String entity,String userCd) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from OaAllAttention ").append("where moduleCd=? and userCd=?");
		List lstResult = oaAllAttentionDao.find(hql.toString(), entity,userCd);
		int sn = lstResult.size();
		return sn;
	}
	
	/**
	 * 获取某个用户在某个模块的关注的记录的列表
	 * @param moduleCd 被关注表的表名
	 * @param userCd 用户Uiid
	 * @return
	 */
	public Map<String, String> getMyAttention(String moduleCd, String userCd) {
		String sql = "select entityId from OaAllAttention where moduleCd=? and userCd=?";
		List<String> attentionIds = oaAllAttentionDao.find(sql, moduleCd,
				userCd);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for(String entityId:attentionIds){
			map.put(entityId, "attention");
		}
		return map;
	}
	
	/**
	 * 获取某个用户在首页上的关注提示数，当关注的内容有更新的时候该关注表的数值加1
	 * @return
	 */
	public int getAttention(String moduleCd, String userCd,String entityId) {
		int returnInt = 0;
		Map<String, Object> map = new HashMap<String ,Object>();
		try{
			String sql = "select count(*) from OA_ALL_ATTENTION a where a.ENTITY_ID like '"+entityId+"' and a.MODULE_CD like '"+moduleCd+"' and a.USER_CD like '"+userCd+"' ";
			List attentionIds = oaAllAttentionDao.findBySql(sql, map);
			BigDecimal returnBigDecimal = (BigDecimal)attentionIds.get(0);
			returnInt = Integer.parseInt(returnBigDecimal.toString());
		}catch(Exception e){
		}
		return returnInt;
	}

	/**
	 * 获取某个用户在首页上的关注总数
	 * @param moduleCd 被关注表的表名
	 * @param userCd 用户Uiid
	 * @param targetTableName 被关注表的表名
	 * @param targetEntityIdName 被关注表的主键字段名称
	 * @return
	 */
	public int getHomeByModuleCd(String moduleCd, String userCd,String targetTableName, String targetEntityIdName) {
		int returnInt = 0;
		Map<String, Object> map = new HashMap<String ,Object>();
		try{
			String sql = "select count(*) from OA_ALL_ATTENTION a left join "
					+ targetTableName + " b on a.ENTITY_ID=b."
					+ targetEntityIdName + " where a.USER_CD like '"+userCd
					+"' and a.MODULE_CD like '"+moduleCd+"' and a.MODULE_RECORD_VERSION != b.RECORD_VERSION";
			//String sql = "select count(*) from OA_ALL_ATTENTION a where a.USER_CD like '"+userCd
			//	+"' and a.MODULE_CD like '"+moduleCd+"' ";
			List attentionIds = oaAllAttentionDao.findBySql(sql, map);
			BigDecimal returnBigDecimal = (BigDecimal)attentionIds.get(0);
			returnInt = Integer.parseInt(returnBigDecimal.toString());
		}catch(Exception e){
		}
		return returnInt;
	}

	/**
	 * 获取某用户在某模块下的 有更新的关注的list
	 * @param moduleCd 被关注表的表名
	 * @param userCd 用户Uiid
	 * @param targetTableName 被关注表的表名
	 * @param targetEntityIdName 被关注表的主键字段名称
	 * @return
	 */
	public List getUnreadIds(String moduleCd, String userCd,String targetTableName, String targetEntityIdName) {
		Map<String, Object> map = new HashMap<String ,Object>();	
		String sql = "select a.ENTITY_ID from OA_ALL_ATTENTION a left join "
				+ targetTableName + " b on a.ENTITY_ID=b."
				+ targetEntityIdName + " where a.USER_CD like '"+userCd
				+"' and a.MODULE_CD like '"+moduleCd+"' and a.MODULE_RECORD_VERSION != b.RECORD_VERSION"
				+" order by b.UPDATED_DATE desc";
		List entityIds = oaAllAttentionDao.findBySql(sql, map);
		return entityIds;
	}
	
	/**
	 * 获取某用户在某模块下的没有更新的关注的list
	 * @param moduleCd 被关注表的表名
	 * @param userCd 用户Uiid
	 * @param targetTableName 被关注表的表名
	 * @param targetEntityIdName 被关注表的主键字段名称
	 * @return
	 */
	public List getReadIds(String moduleCd, String userCd,String targetTableName, String targetEntityIdName) {
		Map<String, Object> map = new HashMap<String ,Object>();	
		String sql = "select a.ENTITY_ID from OA_ALL_ATTENTION a left join "
				+ targetTableName + " b on a.ENTITY_ID=b."
				+ targetEntityIdName + " where a.USER_CD like '"+userCd
				+"' and a.MODULE_CD like '"+moduleCd+"' and a.MODULE_RECORD_VERSION = b.RECORD_VERSION"
				+" order by b.UPDATED_DATE desc";
		List entityIds = oaAllAttentionDao.findBySql(sql, map);
		return entityIds;
	}

	/**
	 * 更新某用户
	 * @param moduleCd 被关注表的表名
	 * @param userCd 被关注表的记录的id
	 * @param userCd 要更新到的moduleRecordVersion
	 * @param userCd 用户uiid
	 * @return
	 */
	public void setAttentionRead(String moduleCd, String entityId,String moduleRecordVersion,String ifDelete, String userCd) {
		String sql = "";
		if("true".equalsIgnoreCase(ifDelete)){
			sql = "delete OaAllAttention where entityId=? and userCd=? and moduleCd=?";
		}else{
			sql = "update OaAllAttention set moduleRecordVersion='"+moduleRecordVersion+"'" +
				" where entityId=? and userCd=? and moduleCd=?";
		}
		oaAllAttentionDao.batchExecute(sql,entityId,userCd,moduleCd);
	}
	
	/**
	 * 获取某用户在某模块下的有更新的关注的list，首页用，提取标题，状态，更新时间
	 * @param moduleCd 被关注表的表名
	 * @param userCd 用户Uiid
	 * @param targetTableName 被关注表的表名
	 * @param targetEntityIdName 被关注表的主键字段名称
	 * @return
	 */
	public List<Map<String,String>> getModuleEntitys(String userCd) {
		Map<String, Object> resultMap = new LinkedHashMap<String ,Object>();
		List returnList = new ArrayList();
		List moduleEntitys = new ArrayList();
		String sql = "";

		//指令单跟踪
		sql = "select a.ENTITY_ID,b.BUSINESS,b.STATUS,b.UPDATED_DATE from OA_ALL_ATTENTION a left join OA_MEETING b"
			+ " on a.ENTITY_ID=b.OA_MEETING_ID where a.USER_CD like '"+userCd
			+ "' and a.MODULE_CD like 'oaMeeting' and a.MODULE_RECORD_VERSION != b.RECORD_VERSION"
			+ " order by b.UPDATED_DATE desc";
		moduleEntitys = oaAllAttentionDao.findBySql(sql, new HashMap<String ,Object>());
		for(int i=0;null!=moduleEntitys&&i<moduleEntitys.size();i++){
			try {
				Object[] obj=(Object[])moduleEntitys.get(i);
				resultMap = new HashMap();
				resultMap.put("moduleCd", "oaMeeting");
				resultMap.put("entityId", (String)obj[0]);
				resultMap.put("content", "【指令单】"+Util.clob2String(((SerializableClob)obj[1])));
				String statusCd = (String)obj[2];
				resultMap.put("statusCd", statusCd);
				if("1".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","进行中");
				}else if("2".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","预完成");
				}else if("3".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","完成");
				}
				resultMap.put("updatedDate", (Date)obj[3]);
				returnList.add(resultMap);
			} catch (Exception e) {
			}
		}
		
		//中心月计划
		sql = "select a.ENTITY_ID,b.CONTENT,b.STATUS_CD,b.UPDATED_DATE from OA_ALL_ATTENTION a left join PLAN_WORK2 b"
			+ " on a.ENTITY_ID=b.PLAN_WORK2_ID where a.USER_CD like '"+userCd
			+ "' and a.MODULE_CD like 'planWork2' and a.MODULE_RECORD_VERSION != b.RECORD_VERSION"
			+ " order by b.UPDATED_DATE desc";
		moduleEntitys = oaAllAttentionDao.findBySql(sql, new HashMap<String ,Object>());
		for(int i=0;null!=moduleEntitys&&i<moduleEntitys.size();i++){
			try {
				Object[] obj=(Object[])moduleEntitys.get(i);
				resultMap = new HashMap();
				resultMap.put("moduleCd", "planWork2");
				resultMap.put("entityId", (String)obj[0]);
				resultMap.put("content", "【中心月计划】"+(String)obj[1]);
				String statusCd = (String)obj[2];
				resultMap.put("statusCd", statusCd);
				if("0".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","未确认");
				}else if("1".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","进行中");
				}else if("2".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","预完成");
				}else if("3".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","申请删除");
				}else if("4".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","完成");
				}else if("5".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","删除");
				}else if("6".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","隐藏");
				}else if("7".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","非本月任务");
				}else if("8".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","延迟");
				}
				resultMap.put("updatedDate", (Date)obj[3]);
				returnList.add(resultMap);
			} catch (Exception e) {
			}
		}

		//中心内部任务
		sql = "select a.ENTITY_ID,b.CONTENT,b.STATUS_CD,b.UPDATED_DATE from OA_ALL_ATTENTION a left join PLAN_WORK_CENTER b"
			+ " on a.ENTITY_ID=b.PLAN_WORK_CENTER_ID where a.USER_CD like '"+userCd
			+ "' and a.MODULE_CD like 'planWorkCenter' and a.MODULE_RECORD_VERSION != b.RECORD_VERSION"
			+ " order by b.UPDATED_DATE desc";
		moduleEntitys = oaAllAttentionDao.findBySql(sql, new HashMap<String ,Object>());
		for(int i=0;null!=moduleEntitys&&i<moduleEntitys.size();i++){
			try {
				Object[] obj=(Object[])moduleEntitys.get(i);
				resultMap = new HashMap();
				resultMap.put("moduleCd", "planWorkCenter");
				resultMap.put("entityId", (String)obj[0]);
				resultMap.put("content", "【中心内部任务】"+(String)obj[1]);
				String statusCd = (String)obj[2];
				resultMap.put("statusCd", statusCd);
				if("0".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","未确认");
				}else if("1".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","进行中");
				}else if("2".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","预完成");
				}else if("3".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","完成");
				}else if("4".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","删除");
				}else if("5".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","隐藏");
				}
				resultMap.put("updatedDate", (Date)obj[3]);
				returnList.add(resultMap);
			} catch (Exception e) {
			}
		}
		
		//文件跟踪
		sql = "select a.ENTITY_ID,b.CONTENT,b.STATUS,b.UPDATED_DATE from OA_ALL_ATTENTION a left join OA_FILE_FOLLOWED b"
			+ " on a.ENTITY_ID=b.OA_FILE_FOLLOW_ID where a.USER_CD like '"+userCd
			+ "' and a.MODULE_CD like 'oaFileFolllowed' and a.MODULE_RECORD_VERSION != b.RECORD_VERSION"
			+ " order by b.UPDATED_DATE desc";
		moduleEntitys = oaAllAttentionDao.findBySql(sql, new HashMap<String ,Object>());
		for(int i=0;null!=moduleEntitys&&i<moduleEntitys.size();i++){
			try {
				Object[] obj=(Object[])moduleEntitys.get(i);
				resultMap = new HashMap();
				resultMap.put("moduleCd", "oaFileFollowed");
				resultMap.put("entityId", (String)obj[0]);
				resultMap.put("content", "【文件跟踪】"+(String)obj[1]);
				String statusCd = (String)obj[2];
				resultMap.put("statusCd", statusCd);
				if("1".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","新建文件");
				}else if("2".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","已送出");
				}else if("3".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","未确认");
				}else if("4".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","已确认");
				}else if("5".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","已处理");
				}else if("6".equalsIgnoreCase(statusCd)){
					resultMap.put("statusName","已完成");
				}
				resultMap.put("updatedDate", (Date)obj[3]);
				returnList.add(resultMap);
			} catch (Exception e) {
			}
		}
		
		return returnList;
	}

	
	/**
	 * 暂时不用
	 * @param moduleCd 被关注表的表名
	 * @param userCd 用户Uiid
	 * @param entityId 被关注表的表的记录的ID
	 * @return
	 */
	public long getIfLastAttention(String moduleCd, String userCd, String entityId) {
		long returnLong = 0;
		Map<String, Object> map = new HashMap<String ,Object>();
		try{
			String sql = "select IF_LAST_ATTENTION from OA_ALL_ATTENTION where ENTITY_ID like '"+entityId
				+"' and USER_CD like '"+userCd+"' and MODULE_CD like '"+moduleCd+"'";
			List returnList = oaAllAttentionDao.findBySql(sql, map);
			BigDecimal returnBigDecimal = (BigDecimal)returnList.get(0);
			returnLong = Long.parseLong(returnBigDecimal.toString());
		}catch(Exception e){
		}
		return returnLong;
	}
	
	/**
	 * 更新关注了该模块的所有用户的关注变化
	 * @param moduleCd 被关注表的表名
	 * @param moduleRecordVersion 要更新到的moduleRecordVersion
	 * @param entityId 节点
	 * @return
	 */
	public void setAttentionChange(String moduleCd, String entityId) {
		String moduleRecordVersion = "0";
		if (StringUtils.isNotBlank(entityId)) {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from OaAllAttention where");
			sb.append(" entityId = :entityId order by updatedDate desc");
			params.put("entityId", entityId);
			List<OaAllAttention> arr = this.getDao()
					.find(sb.toString(), params);
			if (null != arr && arr.size() > 0){
				OaAllAttention mess = arr.get(0);
				if(mess.getRemark()!=null){
					moduleRecordVersion = String.valueOf(Integer.parseInt(mess.getRemark())+1);
				}else{
					moduleRecordVersion = "1";
				}
			}else{
				moduleRecordVersion = "0";
			}
		}
		String sql = "";
			sql = "update OaAllAttention set remark='"+moduleRecordVersion+"'" +
				" where entityId=? and moduleCd=?";
		oaAllAttentionDao.batchExecute(sql,entityId,moduleCd);
	}
}


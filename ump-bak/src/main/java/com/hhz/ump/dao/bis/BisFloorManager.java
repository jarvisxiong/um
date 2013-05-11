package com.hhz.ump.dao.bis;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisFloor;

@Service
@Transactional
public class BisFloorManager extends BaseService<BisFloor, String> {
	@Autowired
	private BisFloorDao bisFloorDao;

	public void saveBisFloor(BisFloor bisFloor) {
		PowerUtils.setEmptyStr2Null(bisFloor);
		bisFloorDao.save(bisFloor);
	}

	public void deleteBisFloor(String id) {
		bisFloorDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisFloor, String> getDao() {
		return bisFloorDao;
	}
	
	/**
	 * 获取商铺类型楼层信息
	 * 修收人：qilb 5/2/2012
	 * 修改内容：判断楼层是否为空  1.
	 * @return
	 */
	public Map<String, String> getMapFloor() {
		Map<String, String> mapFloor = new LinkedHashMap<String, String>();
		mapFloor.put("", "--选择--");
		String hql = "from BisFloor where floorType='1' order by sequenceNo,bisFloorId";
		List<BisFloor> list = bisFloorDao.find(hql);
		for (BisFloor bisFloor : list) {
			try{
			String floorProject = bisFloor.getBisFloorId() + "`" + bisFloor.getBisProject().getBisProjectId();
			String NumBuilding;
			if (StringUtils.isNotBlank(bisFloor.getBuildingNum())) {
				//1.需判断楼层号是否为空，否则为空时则会抛异常
				NumBuilding = bisFloor.getBuildingNum() + "~" + (bisFloor.getFloorNum()!=null?bisFloor.getFloorNum().toString():"");
			} else {
				NumBuilding = bisFloor.getFloorNum().toString();
			}
			mapFloor.put(floorProject, NumBuilding);
			}catch(Exception ee){
				
				ee.printStackTrace();
			}
		}
		return mapFloor;
	}
	
	/**
	 * 获取楼层信息
	 * @return
	 */
	public Map<String, String> getMapFloorSel(String projectId, String floorType) {
		Map<String, String> mapFloorSel = new LinkedHashMap<String, String>();
		mapFloorSel.put("", "--选择--");
		String hql = "from BisFloor where floorType=? and bisProject.bisProjectId=? order by sequenceNo,bisFloorId ";
		List<BisFloor> list = bisFloorDao.find(hql, floorType, projectId);
		for (BisFloor bisFloor : list) {
			String NumBuilding;
			if (StringUtils.isNotBlank(bisFloor.getBuildingNum())) {
				NumBuilding = bisFloor.getBuildingNum() + "~" + (bisFloor.getFloorNum()!=null?bisFloor.getFloorNum().toString():"");
			} else {
				NumBuilding = bisFloor.getFloorNum().toString();
			}
			mapFloorSel.put(bisFloor.getBisFloorId(), NumBuilding);
		}
		return mapFloorSel;
	}
	
	/**
	 * 获取楼层信息
	 * @return
	 */
	public Map<String, String> getMapBuildSel(String projectId, String floorType) {
		Map<String, String> mapBuildSel = new LinkedHashMap<String, String>();
		mapBuildSel.put("", "--选择--");
		String hql = "from BisFloor where floorType=? and bisProject.bisProjectId=? order by sequenceNo,bisFloorId ";
		List<BisFloor> list = bisFloorDao.find(hql, floorType, projectId);
		for (BisFloor bisBuild : list) {
			mapBuildSel.put(bisBuild.getBisFloorId(), bisBuild.getBuildingNum());
		}
		return mapBuildSel;
	}
	
	/**
	 * 获取公寓类型楼号信息
	 * @return
	 */
	public Map<String, String> getMapBuilding() {
		Map<String, String> mapBuilding = new LinkedHashMap<String, String>();
		mapBuilding.put("", "--选择--");
		String hql = "from BisFloor where floorType='2' order by sequenceNo,bisFloorId ";
		List<BisFloor> list = bisFloorDao.find(hql);
		for (BisFloor bisBuilding : list) {
			mapBuilding.put(bisBuilding.getBisFloorId() + "`" + bisBuilding.getBisProject().getBisProjectId(),
					bisBuilding.getBuildingNum());
		}
		return mapBuilding;
	}
	
	/**
	 * 根据bisProjectId获取楼层
	 */
	public List<BisFloor> getFloorByProject(String bisProjectId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		StringBuffer hql = new StringBuffer(" from BisFloor where floorType='1' and subFloorType='1'");
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and bisProject.bisProjectId = :bisProjectId");
		} else {
			hql.append(" and bisProject.bisProjectId is null");// 防止编辑多经信息时无法获取公用项目
		}
		hql.append(" order by sequenceNo,bisFloorId ");

		return getDao().find(hql.toString(), param);
	}
	
	/**
	 * 根据bisProjectId获取广告
	 */
	public List<BisFloor> getAdByProject(String bisProjectId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		StringBuffer hql = new StringBuffer(" from BisFloor where floorType='3' and subFloorType='0'");
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and bisProject.bisProjectId = :bisProjectId");
		} else {
			hql.append(" and bisProject.bisProjectId is null");// 防止编辑多经信息时无法获取公用项目
		}
		hql.append(" order by sequenceNo,bisFloorId ");
		
		return getDao().find(hql.toString(), param);
	}
	
	public List<BisFloor> getFloorByProject(String bisProjectId, int floorNum) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("floorNum", String.valueOf(floorNum));
		StringBuffer hql = new StringBuffer(" from BisFloor where floorType='1' and subFloorType='1'");
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and bisProject.bisProjectId = :bisProjectId");
		} else {
			hql.append(" and bisProject.bisProjectId is null");// 防止编辑多经信息时无法获取公用项目
		}
			hql.append(" and floorNum=:floorNum").append(" order by sequenceNo,bisFloorId ");

		return getDao().find(hql.toString(), param);
	}
	
	/**
	 * 
	 * Description:搜索楼层逻辑分区
	 * author:jiaoxiaofeng  2011-9-29
	 * @param bisProjectId
	 * @param floorNum
	 * @param floorVirId	楼层逻辑分区id
	 * @return
	 * List<BisFloor>
	 */
	public List<BisFloor> getFloorByProject(String bisProjectId, String floorNum, String floorVirId, String floorId) {
		return getFloorByProject(bisProjectId, floorNum, floorVirId, floorId,null);
	}
	public List<BisFloor> getFloorByProject(String bisProjectId, String floorNum, String floorVirId, String floorId,String virtualArea) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("floorNum", floorNum);
		param.put("floorVirId", floorVirId);
		param.put("floorId", floorId);
		param.put("virtualArea", virtualArea);
		StringBuffer hql = new StringBuffer(" from BisFloor where floorType='4' and subFloorType='4'");
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and bisProject.bisProjectId = :bisProjectId");
		} else {
			hql.append(" and bisProject.bisProjectId is null");// 防止编辑多经信息时无法获取公用项目
		}
		if (StringUtils.isNotBlank(floorVirId)) {
			hql.append(" and bisFloorId=:floorVirId");
		}
		if (StringUtils.isNotBlank(floorId)) {
			hql.append(" and parentId=:floorId");
		}
		if (StringUtils.isNotBlank(virtualArea)) {
			hql.append(" and buildingNum=:virtualArea");
		}
		hql.append("   and floorNum=:floorNum").append(" order by sequenceNo,bisFloorId ");
		return getDao().find(hql.toString(), param);
	}
	/**
	 * 根据楼层id，获取广告所在楼层
	 */
	public BisFloor getGgFloor (String bisFloorId){
		List<BisFloor> result = getGgFloorByProject(null, null, bisFloorId);
		if(result.size()==1)
			return result.get(0);
		else
			return null;
	}
	/**
	 * 搜索广告所在楼层
	 */
	public List<BisFloor> getGgFloorByProject(String bisProjectId,String subFloorType,String parentFloorId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("subFloorType", subFloorType);
		param.put("parentFloorId", parentFloorId);
		StringBuffer hql = new StringBuffer(" from BisFloor f where floorType='3' ");
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and f.bisProject.bisProjectId = :bisProjectId");
		} 
	/*	else {
			hql.append(" and f.bisProject.bisProjectId is null");// 防止编辑多经信息时无法获取公用项目
		}*/
		if(StringUtils.isNotBlank(subFloorType)){
			hql.append(" and f.subFloorType = :subFloorType");
		}
		if(StringUtils.isNotBlank(parentFloorId)){
			hql.append(" and f.parentId = :parentFloorId");
		}
		hql.append(" order by f.bisProject.bisProjectId,f.sequenceNo asc ");
		return getDao().find(hql.toString(), param);
	}
	/**
	 * 加载第一级广告位
	 */
	public Map<String,String> getGgSbFloor(String bisProjectId) {
		List<BisFloor> floors = getGgFloorByProject(bisProjectId, null, null);
		Map<String,String> result = new HashMap<String, String>();
		for(BisFloor vo : floors){
			if("0".equals(vo.getSubFloorType())){
				result.put(vo.getBisFloorId(),vo.getBuildingNum());
			}
		}
		return result;
	}
	
}


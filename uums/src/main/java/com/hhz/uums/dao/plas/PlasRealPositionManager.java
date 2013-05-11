package com.hhz.uums.dao.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasRealPosition;
import com.hhz.uums.vo.ws.WsPlasRealPosition;

@Service
@Transactional
public class PlasRealPositionManager extends BaseService<PlasRealPosition, String> {
	@Autowired
	private PlasRealPositionDao plasRealPositionDao;

	public void savePlasRealPosition(PlasRealPosition plasRealPosition) {
		PowerUtils.setEmptyStr2Null(plasRealPosition);
		plasRealPositionDao.save(plasRealPosition);
	}

	public void deletePlasRealPosition(String id) {
		plasRealPositionDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasRealPosition, String> getDao() {
		return plasRealPositionDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasRealPosition> getAllOrderedPosition() {
		// return uaapUserDao.getAll();
		return find("from PlasRealPosition where 1=1  order by sequenceNo asc , realPosCd asc ");
	}

	/**
	 * 模糊查询职位列表
	 * 
	 * @param positionCd
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasRealPosition> getPlasRealPositionList(String realPosName, String maxNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("realPosName", "%" + realPosName + "%");
		Page<PlasRealPosition> page = new Page<PlasRealPosition>(new Integer(maxNum));
		findPage(page," from PlasRealPosition where realPosName like :realPosName order by sequenceNo asc , realPosCd asc ",
				map);
		return page.getResult();
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getPositionName(String positionCd) {
		List result = findBy(Restrictions.eq("realPosCd", positionCd));
		if (result == null || result.size() == 0)
			return "";
		return ((PlasRealPosition) result.get(0)).getRealPosNameShow();
	}
	/**
	 * 批量保存
	 * @param insertedRecords
	 * @param updatedRecords
	 * @param deletedRecords
	 */
	public void saveOrUpdateRealPositions(List<PlasRealPosition> insertedRecords,List<PlasRealPosition> updatedRecords,List<PlasRealPosition> deletedRecords){
		for(PlasRealPosition realPos : insertedRecords){
			this.saveRealPosition(realPos);
		}
		for(PlasRealPosition realPos : updatedRecords){
			this.saveRealPosition(realPos);
		}
		for(PlasRealPosition realPos : deletedRecords){
			this.delete(realPos);
		}
		
	}
	public void saveRealPosition(PlasRealPosition realPos) {
		PowerUtils.setEmptyStr2Null(realPos);
		plasRealPositionDao.save(realPos);
	}

	public void deleteRealPosition(String id) {
		plasRealPositionDao.delete(id);
	}
	

	/* *********************************************************************************/

	public List<WsPlasRealPosition> getWsAll(){
		String hql = "select  plasRealPositionId, realPosCd, realPosName, realPosNameShow, sequenceNo, remark from  PlasRealPosition ";
		Map<String,Object> values = new HashMap<String, Object>();
		List list = this.getDao().find(hql,values);
		
		List<WsPlasRealPosition> rtnList = new ArrayList<WsPlasRealPosition>();
		if(list != null){
			Object[] t = null;
			WsPlasRealPosition tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new WsPlasRealPosition();
				tmp.setPlasRealPositionId((String)t[0]);
				tmp.setRealPosCd((String)t[1]);
				tmp.setRealPosName((String)t[2]);
				tmp.setSequenceNo((Long)t[3]);
				tmp.setRemark((String)t[4]);
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}
}


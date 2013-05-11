package com.hhz.uums.dao.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasApproveInfo;
import com.hhz.uums.entity.plas.PlasApproveNodeHis;

@Service
@Transactional
public class PlasApproveNodeHisManager extends BaseService<PlasApproveNodeHis, String> {
	@Autowired
	private PlasApproveNodeHisDao plasApproveNodeHisDao;

	public void savePlasApproveNodeHis(PlasApproveNodeHis plasApproveNodeHis) {
		PowerUtils.setEmptyStr2Null(plasApproveNodeHis);
		plasApproveNodeHisDao.save(plasApproveNodeHis);
	}

	public void deletePlasApproveNodeHis(String id) {
		plasApproveNodeHisDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasApproveNodeHis, String> getDao() {
		return plasApproveNodeHisDao;
	}

	/**
	 * 获取审批历史节点
	 * @param plasApproveInfoId
	 * @return
	 */
	public List<PlasApproveNodeHis> getNodeList(String plasApproveInfoId){
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("plasApproveInfoId", plasApproveInfoId);
		String hql = "from PlasApproveNodeHis t where t.plasApproveInfo.plasApproveInfoId = :plasApproveInfoId order by t.sequenceNo asc ";
		List<PlasApproveNodeHis> list = find(hql, values);
		if(list == null || list.size() == 0)
			return new ArrayList<PlasApproveNodeHis>();
		else
			return list;
	}
	
	
//
//	/**
//	 * 获取当前节点以及之后的所有节点
//	 * @param flowTypeCd
//	 * @param curRoleCd
//	 * @return
//	 */
//	public List<PlasApproveNodeHis> getCurNextNodes(String flowTypeCd,String curRoleCd){
//
//		List<PlasApproveNodeHis> rtnList = new ArrayList<PlasApproveNodeHis>();
//		Map<String, Object> values = new HashMap<String,Object>();
//		values.put("flowTypeCd", flowTypeCd);
//		values.put("approveRoleCd", curRoleCd);
//		String hql = " from PlasApproveNodeHis t where t.plasApproveInfo.flowTypeCd = :flowTypeCd order by t.sequenceNo asc ";
//		List<PlasApproveNodeHis> list = find(hql, values);
//		if(list != null && list.size() > 0){
//			boolean findFlg = false;
//			String tmpRoleCd = null;
//			for (PlasApproveNodeHis tNode : list) {
//				if(findFlg){
//					rtnList.add(tNode);
//				}else{
//					tmpRoleCd = tNode.getApproveRoleCd();
//					if(StringUtils.isNotBlank(curRoleCd)){
//						String[] tmpArr = StringUtils.split(tmpRoleCd,",");
//						for(int i=0; i<tmpArr.length; i++){
//							if( curRoleCd.equals(tmpArr[i])){
//								rtnList.add(tNode);
//								findFlg = true;
//								break;
//							}
//						}
//					}
//				}
//			}
//		}
//		return rtnList;
//	}
//	
//	
//	public List<PlasApproveNodeHis> getNextNodes(String flowTypeCd,String curRoleCd){
//		List<PlasApproveNodeHis> rtnList = getCurNextNodes(flowTypeCd, curRoleCd);
//		if(rtnList.size()>1){
//			rtnList.remove(0);
//			return rtnList;
//		} else
//			return new ArrayList<PlasApproveNodeHis>();
//	}

	/**
	 * 获取当前节点
	 * @param approve
	 * @param roleCd
	 * @return
	 * PlasApproveNode
	 */
	public PlasApproveNodeHis getCurNode(PlasApproveInfo approve,String curRoleCd){

		Map<String, Object> values = new HashMap<String,Object>();
		values.put("plasApproveInfoId", approve.getPlasApproveInfoId());
		values.put("approveRoleCd", "%"+curRoleCd+"%");
		String hql = " from PlasApproveNodeHis t where t.plasApproveInfo.plasApproveInfoId = :plasApproveInfoId and approveRoleCd like :approveRoleCd  ";
		List<PlasApproveNodeHis> list = find(hql, values);
		if(list != null && list.size() > 0)
			return list.get(0);
		else 
			return null;
	}
//	/*
//	 * 获取流程的第二个节点
//	 * @param flowTypeCd
//	 * @return
//	 */
//	public PlasApproveNodeHis getSecondNode(String flowTypeCd){
//		
//		Map<String, Object> values = new HashMap<String,Object>();
//		values.put("flowTypeCd", flowTypeCd);
//		String hql = " from PlasApproveNodeHis t where t.plasApproveInfo.flowTypeCd = :flowTypeCd order by t.sequenceNo asc ";
//		List<PlasApproveNodeHis> list = find(hql, values);
//		if(list != null && list.size() > 1)
//			return list.get(1);
//		else 
//			return null;
//	}
	/**
	 * 获取下一个节点
	 * @param info 申请明细
	 * @return
	 */
	public PlasApproveNodeHis getNextApproveNode(PlasApproveInfo info){
		
		return getNextApproveNode(info.getPlasApproveInfoId(), info.getApproveRoleCd());
	}
	public PlasApproveNodeHis getNextApproveNode(String plasApproveInfoId, String curNodeCd){
		
		if(StringUtils.isBlank(curNodeCd))
			return null;
		
		//如果curNodeCd为空,认为新建
		//判断流程的走向类型
		
		Map<String, Object> values = new HashMap<String,Object>();
		values.put("plasApproveInfoId", plasApproveInfoId);
		
		String hql = " from PlasApproveNodeHis t where t.plasApproveInfo.plasApproveInfoId = :plasApproveInfoId order by t.sequenceNo asc ";
		List<PlasApproveNodeHis> list = find(hql, values);
		
		if(list != null && list.size() > 0){
			boolean findFlg = false;
			for (PlasApproveNodeHis tmp : list) {
				if(tmp != null){
					if(findFlg)
						return tmp;
					
					if(StringUtils.isNotBlank(tmp.getApproveRoleCd())){
						String[] ts = StringUtils.split(tmp.getApproveRoleCd(),",");
						for (String t : ts) {
							if(curNodeCd.equals(t)){
								findFlg = true;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取下一个节点
	 * @param info 申请明细
	 * @return
	 */
	public PlasApproveNodeHis getPrevApproveNode(PlasApproveInfo info){
		return getPrevApproveNode(info.getPlasApproveInfoId(),info.getApproveRoleCd());
	}
	public PlasApproveNodeHis getPrevApproveNode(String plasApproveId, String curNodeCd){
		
		if(StringUtils.isBlank(curNodeCd))
			return null;
		
		//如果curNodeCd为空,认为新建
		//判断流程的走向类型
		
		Map<String, Object> values = new HashMap<String,Object>();
		values.put("plasApproveId", plasApproveId);
		
		String hql = " from PlasApproveNodeHis t where t.plasApproveInfo.plasApproveInfoId = :plasApproveId order by t.sequenceNo asc ";//升序
		List<PlasApproveNodeHis> list = find(hql, values);
		
		if(list != null && list.size() > 0){

			PlasApproveNodeHis tmp = null;
			for(int i=0; i< list.size(); i++){
				tmp = list.get(i);
				if(tmp != null){
					if(StringUtils.isNotBlank(tmp.getApproveRoleCd())){
						String[] ts = StringUtils.split(tmp.getApproveRoleCd(),",");
						for (String t : ts) {
							if(curNodeCd.equals(t)){
								if(i==0)
									return null;
								else
									return list.get(i-1);
							}
						}
					}
				}
			} 
		}
		return null;
	}
	
	/**
	 * 清空历史审批记录
	 * @param id
	 */
	public void cleanAllHisByApproveId(String id){
		String hql = "delete from PlasApproveNodeHis t where t.plasApproveInfo.plasApproveInfoId = ? ";
		int size = this.getDao().batchExecute(hql, id);
		System.out.println(">>>>>>>>>>>>>>>> 删除历史审批节点! 影响 " + size +" 个!");
	}
	
	

	/**
	 * 获取某条申请已审批过的历史节点
	 */
	public List<PlasApproveNodeHis> getApproveHisList(String plasApproveInfoId) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("plasApproveInfoId", plasApproveInfoId);
		
		String hql = "from PlasApproveNodeHis t where t.plasApproveInfo.plasApproveInfoId = :plasApproveInfoId  order by t.sequenceNo asc";
		return find(hql, values);
	}
}


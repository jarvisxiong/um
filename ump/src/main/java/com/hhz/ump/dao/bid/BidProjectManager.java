package com.hhz.ump.dao.bid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidProject;

@Service
@Transactional
public class BidProjectManager extends BaseService<BidProject, String> {
	@Autowired
	private BidProjectDao bidProjectDao;

	public void saveBidProject(BidProject bidProject) {
		PowerUtils.setEmptyStr2Null(bidProject);
		bidProjectDao.save(bidProject);
	}

	public void deleteBidProject(String id) {
		bidProjectDao.delete(id);
	}

	@Override
	public HibernateDao<BidProject, String> getDao() {
		return bidProjectDao;
	}

	/**
	 * 搜索项目列表
	 * 
	 * @param bidLedgerId
	 * @return
	 */
	public List<BidProject> getEnableProjectList(String bidLedgerId) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("bidLedgerId", bidLedgerId);
		values.put("deleteFlg", "0");

		String hql = " from BidProject t where t.bidLedger.bidLedgerId = :bidLedgerId and t.deleteFlg = :deleteFlg order by t.createdDate asc";
		List list = getDao().find(hql, values);
		if (list == null)
			return new ArrayList<BidProject>();
		return list;
	}
	
	/**
	 * 根据 标段搜索项目列表按照工程名升序排列
	 * 
	 * @param bidLedgerId
	 * @return
	 */
	public List<BidProject> getEnableProjectListByName(String bidLedgerId) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("bidLedgerId", bidLedgerId);
		values.put("deleteFlg", "0");
		
		String hql = " from BidProject t where t.bidLedger.bidLedgerId = :bidLedgerId and t.deleteFlg = :deleteFlg order by t.projectName asc";
		List list = getDao().find(hql, values);
		if (list == null)
			return new ArrayList<BidProject>();
		return list;
	}

	/**
	 * 
	 * projectHasValidateData:(搜索指定工程是否存在有效数据)
	 * 
	 * @param @param projectId
	 * @param @return 设定文件
	 * @return boolean DOM对象
	 * @throws Exception 
	 * @throws
	 * @since 　Ver 1.1
	 */
	public boolean projectHasValidateData(String projectId) throws Exception {
		boolean flag = false;
		if(selectByProject("BidDivisiton",projectId)||	
				selectByProject("BidDivisionSupRel",projectId)||	
				selectByProject("BidMeasureSupRel",projectId)||	
				selectByProject("BidMeasureSupRel2",projectId)||	
				selectByProject("BidOtherItemRel",projectId)||	
				selectByProject("BidSporadicSupRel",projectId)||	
				selectByProject("BidFeesSupRel",projectId)||	
				selectByProject("BidTaxsSupRel",projectId))
			return flag=true;
		return flag;
	}
	
	/**
	 * 
	 *校验是否已经存在工程名
	 */
	public boolean hasExsitProject(String bidLedgerId,String projectName){
		boolean flg=false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bidLedgerId", bidLedgerId);
		map.put("projectName", projectName);		
		String hql=" from BidProject bp where bp.projectName = :projectName and bp.bidLedger.bidLedgerId = :bidLedgerId" ;
		List l=bidProjectDao.find(hql, map);
		if(l!=null&&l.size()>0){
			flg=true;
		}
		return flg;
	}

	public boolean  selectByProject(String tableName, String projectId) throws Exception {
		boolean flag=false;
		StringBuffer hql = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(tableName)) {			
			map.put("projectId", projectId);
			// 如果是分部分项(标底)
			if ("BidDivisiton".equals(tableName)) {
				hql.append(" from BidDivisiton bd ").append(" where bd.bidProject.bidProjectId = :projectId");
			} 
			// 如果是分部分项(投标单位)
			else if ("BidDivisionSupRel".equals(tableName)) {
				hql.append(" from BidDivisionSupRel bdsr where ")
			 	.append(" bdsr.bidDivisiton.bidDivisitonId in( ")
			 	.append(" select bd.bidDivisitonId from BidDivisiton bd where bd.bidProject.bidProjectId = :projectId)")
			 	.append(" )");
			}
			//其他表 
			else {
				hql.append(" from " + tableName + " bds ").append(" where bds.bidProject.bidProjectId = :projectId");
			}

		} else
			throw new Exception("表名为空!");
		
		if(this.getDao().countHqlResult(hql.toString(), map)>0){
			 flag=true; 
		 }
		 
		 return flag;	

	}

	public BidProject getProjectById(String bidProjectId) {
		  
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bidProjectId", bidProjectId);		
		String hql=" from BidProject bp where bp.bidProjectId = :bidProjectId" ;
		List <BidProject>l=bidProjectDao.find(hql, map);
		if(l!=null&&l.size()>0)
			return l.get(0);
		return null;
		
	}

	
}

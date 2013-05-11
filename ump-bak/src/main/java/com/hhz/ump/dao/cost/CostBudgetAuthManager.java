package com.hhz.ump.dao.cost;

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
import com.hhz.ump.entity.cost.CostBudgetAuth;

@Service
@Transactional
public class CostBudgetAuthManager extends BaseService<CostBudgetAuth, String> {
	@Autowired
	private CostBudgetAuthDao costBudgetAuthDao;

	public void saveCostBudgetAuth(CostBudgetAuth costBudgetAuth) {
		PowerUtils.setEmptyStr2Null(costBudgetAuth);
		costBudgetAuthDao.save(costBudgetAuth);
	}

	public void deleteCostBudgetAuth(String id) {
		costBudgetAuthDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostBudgetAuth, String> getDao() {
		return costBudgetAuthDao;
	}
	
	/**
	 * 搜索已经授权的项目和年月
	 * @param map
	 * @param year
	 * @return
	 */
	public Map<String, String> findUncreatedProjectYear(Map<String, Object> map) {
		Map<String, String>  rs=new HashMap<String, String>();
		StringBuffer sql=new StringBuffer()
		.append("select a.cost_project_section_id as prid,p.section_name sname from cost_budget_auth a")
		.append(",cost_project_section p ")
		.append("where p.cost_project_section_id = a.cost_project_section_id")
		.append(" and a.auth_uiid = :uiid");
		if(map.get("projectCd")!=null&&StringUtils.isNotBlank(map.get("projectCd").toString())){
			sql.append(" and a.cost_project_section_id = :projectCd");
		}
		//Map<String,Object> params=new HashMap<String,Object>();
		//params.put("uiid", map);
		List<Object[]> pros=costBudgetAuthDao.findBySql(sql.toString(), map);
		if(pros!=null&&pros.size()>0){
			for(int i=0;i<pros.size();i++){
				Object [] o=pros.get(i);
				rs.put((String)o[0], (String)o[1]);				
			}
		}
		return rs;
	}
	
	
	/**
	 * 搜索被分配的项目
	 * @param uuid
	 * @return
	 */
	public List<String> hasAuthedProject(String uuid){
		List<String> rs=new ArrayList<String>();
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuffer sql=new StringBuffer()
		.append("select a.cost_project_section_id as prjectId,a.cost_budget_auth_id auid from cost_budget_auth a where a.auth_uiid= :uuid");		
		map.put("uiid", uuid);
		List<Object[]> pros=costBudgetAuthDao.findBySql(sql.toString(), map);
		if(pros!=null&&pros.size()>0){
			for(int i=0;i<pros.size();i++){
				Object [] o=pros.get(i);
				rs.add((String)o[0]);				
			}
		}
		return rs;
		
	}
	
	
	/**
	 * 搜索某年，授权没有建立的项目
	 * @param uuid
	 * @return
	 */
	public List<String> hasAuthedUnnewProject(String uuid,Long year){
		List<String> rs=new ArrayList<String>();
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuffer sql=new StringBuffer()
		.append("select distinct t.cost_project_section_id, t.cost_budget_auth_id from (select  a.cost_project_section_id,a.cost_budget_auth_id ")
		.append(" from cost_budget_auth a,cost_project_section p  where a.cost_project_section_id=p.cost_project_section_id ")
		.append(" and  a.auth_uiid = :uiid ")
		.append(" and a.cost_project_section_id not in ( ")
		.append(" select y.cost_project_section_id from cost_budget_year y where y.budget_year = :year )")
		.append(" order by p.section_name desc ) t");
		map.put("uiid", uuid);
		map.put("year", year);
		List<Object[]> pros=costBudgetAuthDao.findBySql(sql.toString(), map);
		if(pros!=null&&pros.size()>0){
			for(int i=0;i<pros.size();i++){
				Object [] o=pros.get(i);
				rs.add((String)o[0]);				
			}
		}
		return rs;
		
	}
	
	/**
	 * 根据项目期数搜索授权信息
	 * @param costProjectSectionId 
	 * @return
	 */
	public List<CostBudgetAuth> getBudgetAuthBySectionId(String sectionId){
		String hql = " from CostBudgetAuth where costProjectSectionId = :costProjectSectionId order by authUiid asc";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("costProjectSectionId", sectionId);
		List<CostBudgetAuth> authList = getDao().createQuery(hql, values).list();
		if(authList != null && authList.size() > 0)
			return authList;
		return null;
	}
	
	public CostBudgetAuth getBudgetAuth(String uiid,String sectionId){
		String hql = " from CostBudgetAuth t where t.costProjectSectionId = :sectionId and t.authUiid = :authUiid";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("sectionId", sectionId);
		values.put("authUiid", uiid);
		List<CostBudgetAuth> authList = getDao().createQuery(hql, values).list();
		if(authList != null && authList.size() > 0)
			return authList.get(0);
		return null;
	}

	/**
	 * 删除项目相关的授权
	 * @param sectionId
	 */
	public void deleteBySection(String sectionId) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("sectionId", sectionId);
		String hql = "delete from CostBudgetAuth t where t.costProjectSectionId = :sectionId";
		costBudgetAuthDao.batchExecute(hql, values);
		
	}
}


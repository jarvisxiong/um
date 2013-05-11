package com.hhz.ump.dao.ct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.ct.CtLedgerRole;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.web.ct.CtLedgerRoleVo;

@Service
@Transactional
public class CtLedgerRoleManager extends BaseService<CtLedgerRole, String> {
	@Autowired
	private CtLedgerRoleDao ctLedgerRoleDao;
	//可用
	public static final String ACTIVE_YES="1";

	public void saveCtLedgerRole(CtLedgerRole ctLedgerRole) {
		PowerUtils.setEmptyStr2Null(ctLedgerRole);
		ctLedgerRoleDao.save(ctLedgerRole);
	}

	public void deleteCtLedgerRole(String id) {
		ctLedgerRoleDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtLedgerRole, String> getDao() {
		return ctLedgerRoleDao;
	}
	
	/**
	 * 
	 * getProjectList:(根据地区搜索所有权限) 
	 *  
	 * @param  @return    设定文件  
	 * @return List    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public List getProjectList(){
		List<CtLedgerRoleVo> prvs=new ArrayList<CtLedgerRoleVo>(); 
		//搜索所有区的的用户
		List<CtLedgerRole> bprs=getProjectUser(ACTIVE_YES);
		//转化为map
		Map<String,String> bprMap=new HashMap<String,String>();
		if(bprs!=null&&!bprs.isEmpty()){
			for(CtLedgerRole r:bprs){				
				bprMap.put(r.getProjectCd(), r.getUserCd());
			}
		}
		//南区
		getAreaList(prvs,DictContants.AREA_ORG_NORTH,"北区",bprMap);
		//北区
		getAreaList(prvs,DictContants.AREA_ORG_SOUTH,"南区",bprMap);	
		
		return prvs;
	}
	
	/**
	 * 
	 * getAreaList:(获取区域项目)	
	 * @return List    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public void getAreaList(List<CtLedgerRoleVo> prvs,String code,String name,Map<String,String> bprMap){ 
		Map<String,String> map = PlasCache.getDictDataMap(code);
		CtLedgerRoleVo prv=null;
		if( map != null &&! map.keySet().isEmpty()){
			for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
				String key=iter.next();
				prv=new CtLedgerRoleVo();
				prv.setAreaCd(DictContants.AREA_ORG_NORTH);
				prv.setAreaName(name);
				prv.setProjectCd(key.trim());
				prv.setProjectName(map.get(key));
				//搜索是否存在匹配的项目				
				if(bprMap.get(key)!=null){
					prv.setUserCds(bprMap.get(key));					
				}
				
				prvs.add(prv);
			}
		}
	}
	
	/**
	 * 
	 * getProjectUser:(搜索所有可用的项目权限)
	 *  
	 * @param  @param active
	 * @param  @return    设定文件  
	 * @return List    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public List getProjectUser(String active){
		Map<String, Object> values = new HashMap<String, Object>();	
		values.put("active", active);
		String hql="from CtLedgerRole bpr where bpr.active = :active";	
		return this.ctLedgerRoleDao.find(hql, values);
	}
	
	/**
	 * 
	 * getProjectUserByProjectCd:(根据项目搜索)  
	 *  
	 * @param  @param active
	 * @param  @param projectCd
	 * @param  @return    设定文件  
	 * @return List<CtLedgerRole>    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public List<CtLedgerRole> getProjectUserByProjectCd(String active,String projectCd){
		Map<String, Object> values = new HashMap<String, Object>();	
		values.put("active", active);
		values.put("projectCd", projectCd);
		String hql="from CtLedgerRole bpr where bpr.active = :active and bpr.projectCd = :projectCd";	
		return this.ctLedgerRoleDao.find(hql, values);
	}
	
	/**
	 * 
	 * getProjectUserByUserCd:(根据用户ID搜索可以访问模块)
	 *  
	 * @param  @param active
	 * @param  @param userCd
	 * @param  @return    设定文件  
	 * @return List<CtLedgerRole>    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public List<CtLedgerRole> getProjectUserByUserCd(String active,String userCd){
		Map<String, Object> values = new HashMap<String, Object>();	
		values.put("active", active);
		//values.put("userCd", userCd);
		String hql="from CtLedgerRole bpr where bpr.active = :active and bpr.userCd like '%"+userCd+";%'";	
		return this.ctLedgerRoleDao.find(hql, values);
	}
	
	public String getProjectByUserCd(String active,String userCd){
		List<CtLedgerRole> prs=getProjectUserByUserCd(active,userCd);
		StringBuffer sb=new StringBuffer();
		if(prs!=null){
			for(CtLedgerRole r:prs){
				if(sb.length()<1){
					sb.append("'"+r.getProjectCd()+"'");
				}else{
					sb.append(",'"+r.getProjectCd()+"'");
				}
			}
		}
		//如果没有，则返回default
		if(sb.length()<1){
			sb.append("'default'");
		}
		return sb.toString();
	}
	
	public boolean canNewBid(String projectCd,String userCd){
		Map<String, Object> values = new HashMap<String, Object>();	
		values.put("projectCd", projectCd);
		values.put("userCd", userCd);
		//values.put("userCd", userCd);
		String hql="from CtLedgerRole bpr where bpr.active = '1' and bpr.userCd like '%"+userCd+";%' and bpr.projectCd = :projectCd";	
		List l=this.ctLedgerRoleDao.find(hql, values);
		if(l!=null&&l.size()>0)
			return true;
		return false;
	}
	
	
}


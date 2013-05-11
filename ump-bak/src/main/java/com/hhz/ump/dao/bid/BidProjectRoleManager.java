package com.hhz.ump.dao.bid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.bid.BidProjectRole;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.web.bid.BidProjectRoleVo;

@Service
@Transactional
public class BidProjectRoleManager extends BaseService<BidProjectRole, String> {
	@Autowired
	private BidProjectRoleDao bidProjectRoleDao;
	//可用
	public static final String ACTIVE_YES="1";

	public void saveBidProjectRole(BidProjectRole bidProjectRole) {
		PowerUtils.setEmptyStr2Null(bidProjectRole);
		bidProjectRoleDao.save(bidProjectRole);
	}

	public void deleteBidProjectRole(String id) {
		bidProjectRoleDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidProjectRole, String> getDao() {
		return bidProjectRoleDao;
	}
	
	/**
	 * 
	 * getProjectList:(根据地区搜索所有权限) 
	 *   
	 * @param authUiid
	 * @param remark
	 * @return
	 */
	public List getProjectList(String authUiid,String remark) {
		// 搜索所有区的的用户
		List<BidProjectRole> queryList = getProjectUser(ACTIVE_YES, authUiid, remark);
		// 转化为map
		Map<String, BidProjectRole> queryMap = new HashMap<String, BidProjectRole>();
		if (queryList != null && (queryList.size() > 0)) {
			for (BidProjectRole r : queryList) {
				queryMap.put(r.getProjectCd(), r);
			}
		}
		List<BidProjectRoleVo> rtnList = new ArrayList<BidProjectRoleVo>();
		// 南区
		getAreaList(rtnList, DictContants.AREA_ORG_NORTH, "北区", queryMap);
		// 北区
		getAreaList(rtnList, DictContants.AREA_ORG_SOUTH, "南区", queryMap);

		return rtnList;
	}
	
	/**
	 * 
	 * getAreaList:(获取区域项目)	
	 * @return List    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public void getAreaList(List<BidProjectRoleVo> rtnList, String areaCode,String areaName,Map<String,BidProjectRole> queryMap){ 
		//项目cd -> 名称
		Map<String, String> projectCdMap = PlasCache.getDictDataMap(areaCode);
		BidProjectRoleVo tmp = null;
		if (projectCdMap != null && !projectCdMap.keySet().isEmpty()) {
			for (Iterator<String> iter = projectCdMap.keySet().iterator(); iter.hasNext();) {
				String key = iter.next();
				if (queryMap.get(key) != null) {
					// 搜索是否存在匹配的项目
					tmp = new BidProjectRoleVo();
					tmp.setAreaCd(DictContants.AREA_ORG_NORTH);
					tmp.setAreaName(areaName);
					tmp.setProjectCd(key.trim());
					tmp.setProjectName(projectCdMap.get(key));
					BidProjectRole tRole = queryMap.get(key);
					tmp.setUserCds(tRole.getUserCd());
					tmp.setRemark(tRole.getRemark());
					rtnList.add(tmp);
				}
			}
		}
	}
	
	/**
	 * 
	 * getProjectUser:(搜索所有可用的项目权限)
	 *   
	 * @param active
	 * @param authUiid
	 * @param remark
	 * @return
	 */
	public List getProjectUser(String active,String authUiid,String remark){
		Map<String, Object> values = new HashMap<String, Object>();	
		values.put("active", active);
		StringBuffer hql = new StringBuffer().append("from BidProjectRole t where t.active = :active ");
		if(StringUtils.isNotBlank(authUiid)){
			hql.append(" and (t.userCd like :leftMatch or t.userCd like :midMatch or t.userCd like :rightMatch or t.userCd = :eqMatch) ");
			values.put("eqMatch", authUiid);
			values.put("leftMatch", authUiid +";%");
			values.put("midMatch", "%;"+authUiid +";%");
			values.put("rightMatch", "%;"+authUiid);
		}
		if(StringUtils.isNotBlank(remark)){
			hql.append(" and t.remark like :remark ");
			values.put("remark",remark.trim());
		}
		
		return this.bidProjectRoleDao.find(hql.toString(), values);
	}
	
	/**
	 * 
	 * getProjectUserByProjectCd:(根据项目搜索)  
	 *  
	 * @param  @param active
	 * @param  @param projectCd
	 * @param  @return    设定文件  
	 * @return List<BidProjectRole>    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public List<BidProjectRole> getProjectUserByProjectCd(String active,String projectCd){
		Map<String, Object> values = new HashMap<String, Object>();	
		values.put("active", active);
		values.put("projectCd", projectCd);
		String hql="from BidProjectRole bpr where bpr.active = :active and bpr.projectCd = :projectCd";	
		return this.bidProjectRoleDao.find(hql, values);
	}
	
	/**
	 * 
	 * getProjectUserByUserCd:(根据用户ID搜索可以访问模块)
	 *  
	 * @param  @param active
	 * @param  @param userCd
	 * @param  @return    设定文件  
	 * @return List<BidProjectRole>    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public List<BidProjectRole> getProjectUserByUserCd(String active,String userCd){
		Map<String, Object> values = new HashMap<String, Object>();	
		values.put("active", active);
		//values.put("userCd", userCd);
		String hql="from BidProjectRole bpr where bpr.active = :active and bpr.userCd like '%"+userCd+";%'";	
		return this.bidProjectRoleDao.find(hql, values);
	}
	
	public String getProjectByUserCd(String active,String userCd){
		List<BidProjectRole> prs=getProjectUserByUserCd(active,userCd);
		StringBuffer sb=new StringBuffer();
		if(prs!=null){
			for(BidProjectRole r:prs){
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
		String hql="from BidProjectRole bpr where bpr.active = '1' and bpr.userCd like '%"+userCd+";%' and bpr.projectCd = :projectCd";	
		List l=this.bidProjectRoleDao.find(hql, values);
		if(l!=null&&l.size()>0)
			return true;
		return false;
	}
	
	
	
}


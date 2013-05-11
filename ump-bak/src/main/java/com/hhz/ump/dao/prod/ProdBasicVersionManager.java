package com.hhz.ump.dao.prod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.prod.ProdBasicVersion;

@Service
@Transactional
public class ProdBasicVersionManager extends BaseService<ProdBasicVersion, String> {
	
	//可用
	public static final String  ACTIVE_YES="1";
	//不可用
	public static final String  ACTIVE_NO="0";
	@Autowired
	private ProdBasicVersionDao prodBasicVersionDao;
	

	public void saveProdBasicVersion(ProdBasicVersion prodBasicVersion) {
		PowerUtils.setEmptyStr2Null(prodBasicVersion);
		prodBasicVersionDao.save(prodBasicVersion);
	}

	public void deleteProdBasicVersion(String id) {
		prodBasicVersionDao.delete(id);
	}
	
	@Override
	public HibernateDao<ProdBasicVersion, String> getDao() {
		return prodBasicVersionDao;
	}
	
	/**
	 * 
	 * activeLastVersion:(设置版本有无效) 
	 *  
	 * @param      设定文件  
	 * @return void    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public void activeLastVersion(String active){
		Map<String, Object> map = new HashMap<String, Object>();
		//1-可用的版本
		map.put("active", active);
		//搜索语句
		String strSql=" from ProdBasicVersion pv where pv.active = :active";
		//搜索结果
		List<ProdBasicVersion> rs = prodBasicVersionDao.find(strSql, map);
		if(rs!=null&&!rs.isEmpty()){
			ProdBasicVersion pv=rs.get(0);
			//设置为0-不可用,1-可用
			if(ACTIVE_YES.equals(active)) {
				pv.setActive(ACTIVE_NO);
			} else {
				pv.setActive(ACTIVE_YES);
			}
			//保存
			prodBasicVersionDao.save(pv);
		}
		
	}

	public ProdBasicVersion findNewestVersion() {
		  
		Map<String, Object> map = new HashMap<String, Object>();
		//1-可用的版本
		map.put("active", ACTIVE_YES);
		//搜索语句
		String strSql=" from ProdBasicVersion pv where pv.active = :active";
		//搜索结果
		List<ProdBasicVersion> rs = prodBasicVersionDao.find(strSql, map);
		
		if(rs!=null&&!rs.isEmpty())
			return rs.get(0);
		return null;
		
	}
	
	/**
	 * 
	 * hasExsitVersion:(搜索该年月的版本是否已经存在) 
	 *  
	 * @param  @param yearCd
	 * @param  @param monthCd
	 * @param  @return    设定文件  
	 * @return ProdBasicVersion    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public ProdBasicVersion hasExsitVersion(String yearCd ,String monthCd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yearCd", yearCd);
		map.put("monthCd", monthCd);
		//搜索语句
		String strSql=" from ProdBasicVersion pv where pv.yearCd = :yearCd and pv.monthCd = :monthCd";
		//搜索结果
		List<ProdBasicVersion> rs = prodBasicVersionDao.find(strSql, map);
		
		if(rs!=null&&!rs.isEmpty())
			return rs.get(0);
		return null;
	}
	
	/**
	 * 
	 * hasExsitVersion:(搜索该年月的版本是否已经存在) 
	 *  
	 * @param  @param yearCd
	 * @param  @param monthCd
	 * @param  @return    设定文件  
	 * @return ProdBasicVersion    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public boolean hasExsitVersionNo(String versionNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("versionNo", versionNo);
		//搜索语句
		String strSql=" from ProdBasicVersion pv where pv.versionNo = :versionNo";
		//搜索结果
		List<ProdBasicVersion> rs = prodBasicVersionDao.find(strSql, map);		
		if(rs!=null&&!rs.isEmpty())
			return true;
		return false;
	}
	
	/**
	 * 
	 * findBasicVersionById:(根据ID搜索版本信息实体)
	 *  
	 * @param  @param prodBasicVersionId
	 * @param  @return    设定文件  
	 * @return ProdBasicVersion    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public ProdBasicVersion findBasicVersionById(String prodBasicVersionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodBasicVersionId", prodBasicVersionId);
		//搜索语句
		String strSql=" from ProdBasicVersion pv where pv.prodBasicVersionId = :prodBasicVersionId";
		//搜索结果
		List<ProdBasicVersion> rs = prodBasicVersionDao.find(strSql, map);
		
		if(rs!=null&&!rs.isEmpty())
			return rs.get(0);
		return null;
	}
	
	/**
	 * 
	 * hasUsedBasicVersion:(判断该版本是否已经被关联使用)
	 *  
	 * @param  @param prodBasicVersionId
	 * @param  @return    设定文件  
	 * @return Long    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public Long hasUsedBasicVersion(String prodBasicVersionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodBasicVersionId", prodBasicVersionId);
		//搜索语句
		String strSql=" from ProdVersionDetail pd where pd.prodBasicVersion.prodBasicVersionId = :prodBasicVersionId";
		//搜索结果
		Long cnt = prodBasicVersionDao.countHqlResult(strSql, map);
		return cnt;
	}
	
	
}


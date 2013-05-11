package com.hhz.ump.dao.engineer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.bis.BisProjectDao;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.engineer.EngineerEquipAccount;

@Service
@Transactional
public class EngineerEquipAccountManager extends BaseService<EngineerEquipAccount, String> {
	@Autowired
	private EngineerEquipAccountDao engineerEquipAccountDao;
	@Autowired
	private BisProjectDao bisProjectDao;
	/**
	 * 通过条件查询集合对象
	 * @param proId项目ID
	 * @param belongTo所属项目
	 * @param typeCd 业态
	 * @return
	 */
	public List<EngineerEquipAccount> findListByTree(String proId,String belongTo,String layoutCd){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", proId);
		param.put("equipBelongtoCd", belongTo);
		param.put("layoutCd", layoutCd);
		StringBuffer hql = new StringBuffer("from EngineerEquipAccount t ");
		hql.append("where t.bisProjectId = :bisProjectId")
		.append(" and t.layoutCd = :layoutCd")
		.append(" and t.equipBelongtoCd = :equipBelongtoCd");
		hql.append(" order by t.updatedDate desc");
		return find(hql.toString(), param);
	}
	/**
	 * 通过条件查询页面对象
	 * @param page 页面对象
	 * @param proId项目ID
	 * @param belongTo所属项目
	 * @param typeCd 业态
	 * @return
	 */
	public Page<EngineerEquipAccount> findPageList(Page<EngineerEquipAccount> page, String bisProjectId,String equipBelongtoCd,String layoutCd){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer("from EngineerEquipAccount t where 1=1 ");
		if(StringUtils.isNotBlank(bisProjectId)) {
			sb.append(" and t.bisProjectId = :bisProjectId");
			param.put("bisProjectId", bisProjectId);
		}
		if(StringUtils.isNotBlank(equipBelongtoCd)) {
			sb.append(" and t.equipBelongtoCd = :equipBelongtoCd");
			param.put("equipBelongtoCd", equipBelongtoCd);
		}
		if(StringUtils.isNotBlank(layoutCd)) {
			sb.append(" and t.layoutCd = :layoutCd");
			param.put("layoutCd", layoutCd);
		}
		sb.append(" order by t.updatedDate desc");
		return engineerEquipAccountDao.findPage(page,sb.toString(), param);
	}
	/**
	 * 快速查询
	 * @param loginType
	 * @param page
	 * @param words
	 * @return
	 */
	public Page<EngineerEquipAccount> quickSearch(boolean loginType,Page<EngineerEquipAccount> page,String words){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", getCenterBisPid(loginType));//传入所需的list
		StringBuffer sb = new StringBuffer(" from EngineerEquipAccount t ");
		sb.append(" where t.bisProjectId in (:bisProjectId)");
		if(words.indexOf("%")!=-1){//有%的情况下
			param.put("words", "%"+words.replace("%", "").trim()+"%");
		}else{
			sb.append(" and t.equipCd like :words or t.equipName like :words ");
			param.put("words", "%"+words.trim()+"%");
		}
		sb.append(" order by t.updatedDate desc");
		return engineerEquipAccountDao.findPage(page, sb.toString(), param);
	}
	/**
	 * 根据id查询页面对象
	 * @param page
	 * @param Id
	 * @return
	 */
	public Page<EngineerEquipAccount> getPageById(Page<EngineerEquipAccount> page,String Id){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("engineerEquipAccountId", Id);//传入所需的list
		StringBuffer sb = new StringBuffer(" from EngineerEquipAccount t where engineerEquipAccountId = :engineerEquipAccountId");
		return engineerEquipAccountDao.findPage(page, sb.toString(), param);
	}
	/***
	 * 高级查询(支持模糊查询)
	 * @param loginType :true总部登陆
	 * @param equipCd
	 * @param equipName
	 * @param equipModels
	 * @param equipMaker
	 * @param equipSerialNum
	 * @param equipSerialDate
	 * @return
	 */
	public Page<EngineerEquipAccount> advanceSearch(boolean loginType,Page<EngineerEquipAccount> page, String equipCd,
			String equipName,String equipModels,String equipMaker,String equipSerialNum,String equipSerialDate,
			String bisProjectId,String equipBelongtoCd,String layoutCd){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer(" from EngineerEquipAccount t ");
		sb.append(" where t.bisProjectId in (:bisProjectId)");
		param.put("bisProjectId", getCenterBisPid(loginType));//传入所需的list
		if(StringUtils.isNotBlank(equipCd)) {
			sb.append(" and t.equipCd like :equipCd");
			param.put("equipCd", "%"+equipCd.trim()+"%");
		}
		if(StringUtils.isNotBlank(equipName)) {
			sb.append(" and t.equipName like :equipName");
			param.put("equipName", "%"+equipName.trim()+"%");
		}
		if(StringUtils.isNotBlank(equipModels)) {
			sb.append(" and t.equipModels like :equipModels");
			param.put("equipModels", "%"+equipModels.trim()+"%");
		}
		if(StringUtils.isNotBlank(equipMaker)) {
			sb.append(" and t.equipMaker like :equipMaker");
			param.put("equipMaker", "%"+equipMaker.trim()+"%");
		}
		if(StringUtils.isNotBlank(equipSerialNum)) {
			sb.append(" and t.equipSerialNum like :equipSerialNum");
			param.put("equipSerialNum", "%"+equipSerialNum.trim()+"%");
		}
		if(StringUtils.isNotBlank(equipSerialDate)) {
			sb.append(" and t.equipSerialDate like :equipSerialDate");
			param.put("equipSerialDate", equipSerialDate);
		}
		if(StringUtils.isNotBlank(bisProjectId)) {
			sb.append(" and t.bisProjectId = :bisProjectId");
			param.put("bisProjectId", bisProjectId);
		}
		if(StringUtils.isNotBlank(equipBelongtoCd)) {
			sb.append(" and t.equipBelongtoCd = :equipBelongtoCd");
			param.put("equipBelongtoCd", equipBelongtoCd);
		}
		if(StringUtils.isNotBlank(layoutCd)) {
			sb.append(" and t.layoutCd = :layoutCd");
			param.put("layoutCd", layoutCd);
		}
		sb.append(" order by t.updatedDate desc");
		return engineerEquipAccountDao.findPage(page, sb.toString(), param);
	}
	/**
	 * 验证cd是否存在
	 * 不存在返回ture
	 * 存在返回false
	 * @param equipCd
	 * @return
	 */
	public boolean ifExistEquipCd(String equipCd){
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("equipCd", equipCd);
		String hql = "from EngineerEquipAccount t where t.equipCd = :equipCd";
		return find(hql, param).isEmpty();
	}
	
	public void saveEngineerEquipAccount(EngineerEquipAccount engineerEquipAccount) {
		PowerUtils.setEmptyStr2Null(engineerEquipAccount);
		engineerEquipAccountDao.save(engineerEquipAccount);
	}
	public void deleteEngineerEquipAccount(String id) {
		engineerEquipAccountDao.delete(id);
	}
	
	@Override
	public HibernateDao<EngineerEquipAccount, String> getDao() {
		return engineerEquipAccountDao;
	}
	/***
	 * 根据登陆情况，返回可查询的bisProjectId
	 * @return
	 */
	private List<String> getCenterBisPid(boolean loginType){
		List<String> rtList = new ArrayList<String>(); 
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer(" from BisProject where orgCd <> 153");
		if(!loginType){//如果不是总部登陆，筛选掉"宝龙商业"和其他公司的数据
			param.put("orgCd",SpringSecurityUtils.getCurrentCenterCd());//根据当前登陆情况取OrgCd
			hql.append(" and orgCd = :orgCd");
		}
		List<BisProject> tlist = bisProjectDao.find(hql.toString(), param);
		if(!tlist.isEmpty()){
			for (BisProject bisProject : tlist) {
				rtList.add(bisProject.getBisProjectId());
			}
		}
		return rtList;
	}
	
}


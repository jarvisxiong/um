package com.hhz.ump.dao.cont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.util.Constants;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class ContProjectCodeManager extends BaseService<ContProjectCode, String> {
	@Autowired
	private ContProjectCodeDao contProjectCodeDao;

	public void saveContProjectCode(ContProjectCode contProjectCode) {
		PowerUtils.setEmptyStr2Null(contProjectCode);
		contProjectCodeDao.save(contProjectCode);
	}

	public void deleteContProjectCode(String id) {
		contProjectCodeDao.delete(id);
	}
	
	@Override
	public HibernateDao<ContProjectCode, String> getDao() {
		return contProjectCodeDao;
	}

	/**
	 * 获取所有的项目列表
	 * @param projectCdList
	 * @param codeTypeCd 1-地产 2-商业 3-酒店
	 * @return
	 */
	public  List<ContProjectCode> getAllProjectList(List<String> projectCdList,String codeTypeCd){
		Map<String, Object> values = new HashMap<String, Object>();

		StringBuffer hql = new StringBuffer();
		hql.append("select distinct t from ContProjectCode t where  1=1 ");
		
		if(projectCdList != null && projectCdList.size() > 0){ 
			values.put("projectCds", projectCdList);
			hql.append(" and t.projectCd in (:projectCds)");
		}
		if(StringUtils.isNotBlank(codeTypeCd)){
			values.put("codeTypeCd", codeTypeCd);
			hql.append(" and t.codeType  = :codeTypeCd");
		}
		hql.append(" order by t.projectName asc  ");
		List<ContProjectCode> result = contProjectCodeDao.find(hql.toString(), values);
		if(result == null)
			return new ArrayList<ContProjectCode>();
		
//		for (ContProjectCode code : result) {
//			System.out.println(">>>>>>>>>>>>>>>>> " + code.getProjectCd() +" -- " + code.getProjectName());
//		}
		return result;
	}
	/**
	 * 获取授权的项目列表
	 * @param uiid
	 * @param codeTypeCd
	 * @return
	 */
	public List<ContProjectCode> getAuthProjectList(String uiid, String contTypeCd){
		if(StringUtils.isBlank(uiid))
			return  new ArrayList<ContProjectCode>();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("authUiid", "%" + uiid + ";%");
		values.put("codeTypeCd", contTypeCd);

		StringBuffer hql = new StringBuffer();
		hql.append("from ContProjectCode t where t.mateAuthority like :authUiid and t.codeType = :codeTypeCd " );
		
		//这里很重要,查找中心拥有的角色
		WsPlasOrg org = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
		if(org!=null&&org.getOrgCd()!=null) {
			hql.append(" or t.projectCd = :myCenterCd ");
			values.put("myCenterCd", org.getOrgCd());
		}
		
		hql.append(" order by t.projectName asc ");
		
		List<ContProjectCode> result = contProjectCodeDao.find(hql.toString(), values);
		if(result == null)
			return new ArrayList<ContProjectCode>();
		return result;
	}
	
	public List<ContProjectCode> getProjectCode(String projectCd){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select a from "+ ContProjectCode.class.getSimpleName() + " a where 1=1"
				);
		if(StringUtils.isNotBlank(projectCd)){
			hql.append(" and a.projectCd=:projectCd");
			param.put("projectCd", projectCd);
		}
		List<ContProjectCode> result = contProjectCodeDao.find(hql.toString(), param);
		return result;
	}
	/**
	 * 得到合同台账所拥有的项目公司权限
	 */
	public List<HashMap<String,String>> queryAuthority (String ledgerType){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql=new StringBuffer("select a from ContProjectCode a where a.authority like :authority ");
		//and a.codeType=:ledgerType");
		param.put("authority", "%"+SpringSecurityUtils.getCurrentUiid()+";%");
		if(StringUtils.isNotBlank(ledgerType)){
			hql.append(" and a.codeType=:ledgerType");
			param.put("ledgerType", ledgerType);
		}
//		WsPlasOrg org = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
		String tOrgCd = SpringSecurityUtils.getCurrentCenterCd();
		
		if (StringUtils.isNotBlank(tOrgCd)) {
			hql.append(" or a.projectCd = :projectCd ");// +org.getOrgCd()+"'");
			param.put("projectCd", tOrgCd);
		}
		 
		List<ContProjectCode> result=contProjectCodeDao.find(hql.toString(), param);
		List<HashMap<String,String>> retResult = new ArrayList<HashMap<String,String>>();
		for(ContProjectCode code:result){
			//retResult.add(code.getProjectCd());
			HashMap<String,String> map = new HashMap<String,String>();
			map.put(code.getProjectCd(), code.getProjectName());
			retResult.add(map);
		}
		return retResult;
	}
	/**
	 * 得到甲供料所拥有的项目公司权限
	 */
	public List<HashMap<String,String>> queryMateAuthority (){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql=new StringBuffer("select a from ContProjectCode a where a.mateAuthority like :mateAuthority ");
		//and a.codeType=:ledgerType");
		param.put("mateAuthority", "%"+SpringSecurityUtils.getCurrentUiid()+";%");
		WsPlasOrg org = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
		if(org!=null&&org.getOrgCd()!=null) {
			hql.append(" or a.projectCd=:projectCd");//+org.getOrgCd()+"'");
			param.put("projectCd", org.getOrgCd());
		}
		List<ContProjectCode> result=contProjectCodeDao.find(hql.toString(), param);
		List<HashMap<String,String>> retResult = new ArrayList<HashMap<String,String>>();
		for(ContProjectCode code:result){
			//retResult.add(code.getProjectCd());
			HashMap map = new HashMap<String,String>();
			map.put(code.getProjectCd(), code.getProjectName());
			retResult.add(map);
		}
		return retResult;
	}
	
}


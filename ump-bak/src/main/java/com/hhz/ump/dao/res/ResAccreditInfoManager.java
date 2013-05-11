package com.hhz.ump.dao.res;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.res.ResAccreditInfo;
import com.hhz.uums.entity.ws.WsPlasUser;

@Service
@Transactional
public class ResAccreditInfoManager extends BaseService<ResAccreditInfo, String> {
	@Autowired
	private ResAccreditInfoDao resAccreditInfoDao;
	private final static String JBPM = "0";
	private final static String FILE = "1";
	private final static String RES = "2";

	public void saveResAccreditInfo(ResAccreditInfo resAccreditInfo) {
		PowerUtils.setEmptyStr2Null(resAccreditInfo);
		resAccreditInfoDao.save(resAccreditInfo);
	}

	public void deleteResAccreditInfo(String id) {
		resAccreditInfoDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<ResAccreditInfo, String> getDao() {
		return resAccreditInfoDao;
	}

	/**
	 * 判断是否被授权过
	 * 
	 * @param uiid
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isAccredited(String uiid) {
		Criterion c = Restrictions.eq("accUserCd", uiid);
		int result = countCriteriaResult(c);
		if (result > 0)
			return true;
		else
			return false;
	}

	public boolean isAccreditedBy(String uiid) {
		return isAccreditedBy(uiid, JBPM);
	}

	public boolean isAccreditedByFile(String uiid) {
		return isAccreditedBy(uiid, FILE);
	}

	public boolean isAccreditedByRes(String uiid) {
		return isAccreditedBy(uiid, RES);
	}

	/**
	 * 判断当前用户是否被指定人员授权
	 * 
	 * @param uiid
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isAccreditedBy(String uiid, String type) {
		if (StringUtils.isNotEmpty(uiid)) {
			Date now = new Date(DateOperator.truncDate(new java.util.Date()).getTime());
			StringBuffer sql = new StringBuffer();
			sql.append("from ResAccreditInfo where (userCd =:userCd or :userCd like '%'||userCd||';%') and accUserCd=:accUserCd and activeFlg=:activeFlg");
			sql.append(" and (startDate is null or :p_date>=startDate) and (endDate is null or :p_date<endDate+1) ");
			if (StringUtils.isNotBlank(type)) {
				if (type.equals(JBPM)) {
					sql.append("and isJbpm=true ");
				}
				if (type.equals(FILE)) {
					sql.append("and isFile=true ");
				}
				if (type.equals(RES)) {
					sql.append("and (isRes=true or  isFile=true) ");
				}

			}
			Map<String, Object> pram = new HashMap<String, Object>();
			pram.put("userCd", uiid);
			pram.put("accUserCd", SpringSecurityUtils.getCurrentUiid());
			pram.put("activeFlg", "1");
			pram.put("p_date", now);
			long result = countHqlResult(sql.toString(), pram);
			if (result > 0)
				return true;
			else
				return false;
		}
		return false;
	}

	/**
	 * 搜索某用户针对另一用户的授权
	 * 
	 * @param uiid
	 * @param accUiid
	 *            授权用户的uiid
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public ResAccreditInfo queryAccreditedBy(String uiid, String accUiid) {
		if (StringUtils.isNotEmpty(uiid)) {
			Date now = new Date(DateOperator.truncDate(new java.util.Date()).getTime());
			StringBuffer sql = new StringBuffer();
			sql.append("from ResAccreditInfo where userCd =:userCd and accUserCd=:accUserCd and activeFlg=:activeFlg");
			sql.append(" and (startDate is null or :p_date>=startDate) and (endDate is null or :p_date<endDate+1) ");
			Map<String, Object> pram = new HashMap<String, Object>();
			pram.put("userCd", uiid);
			pram.put("accUserCd", accUiid);
			pram.put("activeFlg", "1");
			pram.put("p_date", now);
			ResAccreditInfo result = resAccreditInfoDao.findUnique(sql.toString(), pram);
			return result;
		}
		return null;
	}

	/**
	 * 搜索授权人信息
	 * 
	 * @param uiid
	 * @return
	 */
	public List<ResAccreditInfo> loadAccInfo(String uiid) {
		return loadAccInfo(uiid, JBPM);
	}

	/**
	 * 取得授权给指定用户的人员
	 * 
	 * @param uiid
	 * @return
	 */
	public List<ResAccreditInfo> loadAccInfoRes(String uiid) {
		return loadAccInfo(uiid, RES);
	}

	public List<ResAccreditInfo> loadAccInfoFile(String uiid) {
		return loadAccInfo(uiid, FILE);
	}

	/**
	 * 取得授权给指定用户的人员
	 * 
	 * @param uiid
	 * @param type
	 * @return
	 */
	public List<ResAccreditInfo> loadAccInfo(String uiid, String type) {
		Date now = new Date(DateOperator.truncDate(new java.util.Date()).getTime());
		StringBuffer sql = new StringBuffer();
		sql.append("from ResAccreditInfo where accUserCd=:accUserCd and activeFlg=:activeFlg");
		sql.append(" and (startDate is null or :p_date>=startDate) and (endDate is null or :p_date<endDate+1) ");
		if (StringUtils.isNotBlank(type)) {
			if (type.equals(JBPM)) {
				sql.append("and isJbpm=true ");
			}
			if (type.equals(FILE)) {
				sql.append("and isFile=true ");
			}
			if (type.equals(RES)) {
				sql.append("and (isRes=true or  isFile=true) ");

			}
		}
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("accUserCd", uiid);
		pram.put("activeFlg", "1");
		pram.put("p_date", now);
		List<ResAccreditInfo> accreditInfos = find(sql.toString(), pram);
		return accreditInfos;
	}

	/**
	 * 取出所有授权人,并构造成WsPlasUser对象
	 * 
	 * @param uiid
	 * @return
	 */
	public List<WsPlasUser> loadAllAcc(String uiid) {
		List<ResAccreditInfo> accreditInfos = loadAccInfo(uiid);
		List<WsPlasUser> uaapUsers = new ArrayList<WsPlasUser>();
		for (ResAccreditInfo resAccreditInfo : accreditInfos) {
			WsPlasUser user = PlasCache.getUserByUiid(resAccreditInfo.getUserCd());
			uaapUsers.add(user);
		}
		return uaapUsers;
	}

}

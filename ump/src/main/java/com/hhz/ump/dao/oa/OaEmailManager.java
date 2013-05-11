package com.hhz.ump.dao.oa;

import java.sql.Clob;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaEmail;
import com.hhz.ump.entity.oa.OaEmailBody;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class OaEmailManager extends BaseService<OaEmail, String> {
	@Autowired
	private OaEmailDao oaEmailDao;
	@Autowired
	private OaEmailBodyDao oaEmailBodyDao;

	public void saveOaEmail(OaEmail oaEmail) {
		PowerUtils.setEmptyStr2Null(oaEmail);
		oaEmailDao.save(oaEmail);
	}

	public void deleteOaEmail(String id) {
		oaEmailDao.delete(id);
	}
	
	public void removeOaEmail(String id) {
		String hql = "update OaEmail set deleteFlg = ?,attentionFlg = ? where oaEmailId = ? ";
		oaEmailDao.batchExecute(hql, "1", "0", id);
	}
	

	public void removeOaEmails(String[] ids) {
		StringBuilder sbHql = new StringBuilder();
		sbHql.append("update OaEmail set deleteFlg = '1',attentionFlg = '0' where oaEmailId in (");
		for (int i = 0; i < ids.length; i++) {
			if (i != 0) {
				sbHql.append(",");
			}
			sbHql.append("'");
			sbHql.append(ids[i]);
			sbHql.append("'");
		}
		sbHql.append(")");
		oaEmailDao.batchExecute(sbHql.toString());
	}

	public void readOaEmail(String id) {
		String hql = "update OaEmail set readFlg = ? where oaEmailId = ? ";
		oaEmailDao.batchExecute(hql, "1", id);
	}

	public void readOaEmails(String[] ids) {
		for (String id : ids) {
			this.readOaEmail(id);
		}
	}

	/**
	 * 从垃圾箱中恢复邮件
	 * 
	 * @param ids
	 */
	public void recoverEmails(String[] ids) {
		for (String id : ids) {
			OaEmail oaEmail = this.getEntity(id);
			oaEmail.setDeleteFlg("0");
			this.saveOaEmail(oaEmail);
		}
	}

	public void deleteBatchWithUnRead(String[] ids) {
		for (String id : ids) {
			OaEmail oaEmail = this.getEntity(id);
			String oaEmailBodyId = oaEmail.getOaEmailBody().getOaEmailBodyId();
			String hql = "from OaEmail a where a.userTypeCd <> '0' and readFlg = '1' and a.oaEmailBody.oaEmailBodyId = ?";
			Long l = countHqlResult(hql, oaEmailBodyId);
			if (l > 0) {
				this.deleteOaEmail(id);
			} else {
				String delHql = "delete from OaEmail where oaEmailBody.oaEmailBodyId = ?";
				this.oaEmailDao.batchExecute(delHql, oaEmailBodyId);
				oaEmailBodyDao.delete(oaEmailBodyId);
			}
		}
	}

	public void clearDelBox() {
		String hql = "delete OaEmail where userCd = ? and deleteFlg = ?";
		this.oaEmailDao.batchExecute(hql, SpringSecurityUtils.getCurrentUiid(), "1");
	}

	@Override
	public HibernateDao<OaEmail, String> getDao() {
		return oaEmailDao;
	}
	
	/**
	 * 用于用户更换用户名来同步更新它的历史邮件
	 */
	public void updateUser4Email(String oldUiid, String newUiid) {
		String upEmailHql = "update OaEmail set user_cd = '" + newUiid + "' where user_cd = '" + oldUiid + "'";
		oaEmailDao.batchExecute(upEmailHql);
		String upEmailBodyHql = "update OaEmailBody set creator = '" + newUiid + "' where creator = '" + oldUiid + "'";
		oaEmailBodyDao.batchExecute(upEmailBodyHql);
		List<PropertyFilter> filtersTo = new ArrayList<PropertyFilter>();
		// String[] name = {"LIKEC_toUserCds","LIKEC_copyUserCds"};
		// String[] value = { oldUiid + ";", oldUiid + ";" };
		PropertyFilter filter = new PropertyFilter("LIKEC_toUserCds", oldUiid);
		filtersTo.add(filter);
		List<OaEmailBody> listTo = oaEmailBodyDao.find(filtersTo);

		List<PropertyFilter> filtersCopy = new ArrayList<PropertyFilter>();
		PropertyFilter filterCopy = new PropertyFilter("LIKEC_copyUserCds", oldUiid);
		filtersCopy.add(filterCopy);
		List<OaEmailBody> listCopy = oaEmailBodyDao.find(filtersCopy);

		for (OaEmailBody body : listTo) {
			Clob toUsers = body.getToUserCds();
			try {
				String toUserNew = toUsers.getSubString(1l, (int) toUsers.length());
				toUserNew = StringUtils.replace(toUserNew, oldUiid + ";", newUiid + ";");
				body.setToUserCds(new ClobImpl(toUserNew));
				oaEmailBodyDao.save(body);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (OaEmailBody body : listCopy) {
			Clob copyUsers = body.getCopyUserCds();
			try {
				String copyUserNew = copyUsers.getSubString(1l, (int) copyUsers.length());
				copyUserNew = StringUtils.replace(copyUserNew, oldUiid + ";", newUiid + ";");
				body.setCopyUserCds(new ClobImpl(copyUserNew));
				oaEmailBodyDao.save(body);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * 设置某条邮件为已回复状态
	 */
	public void setOaEmailReply(String reply_id) {
		String hql = "update OaEmail set replyFlg = ? where oaEmailId = ? ";
		oaEmailDao.batchExecute(hql, "1", reply_id);
	}
	
	
	//获取未读内部邮件
	public long getNoReadCount(String uiid){
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("uiid", uiid);
		values.put("deleteFlg", "0");
		values.put("readFlg", "0");
		values.put("userType1", "1");
		values.put("userType2", "2");
		values.put("userType3", "3");
		
		StringBuffer hqlBuf = new StringBuffer()
		.append(" from OaEmail t ")
		.append(" WHERE t.userCd    = :uiid ")
		.append("   and t.deleteFlg = :deleteFlg ")
		.append("   and t.readFlg   = :readFlg ")
		.append("   and t.userTypeCd in (:userType1, :userType2, :userType3) ");
		
		return countHqlResult(hqlBuf.toString(),values);
		
//		StringBuffer sqlNoRead = new StringBuffer();
//		sqlNoRead.append(" from OaEmail e");
//		sqlNoRead.append(" WHERE e.userCd = ? AND e.deleteFlg = 0 and e.readFlg='0' AND e.userTypeCd IN ('1', '2', '3') ");
//		return countHqlResult(sqlNoRead.toString(), uiid);
	}
	
	/**
	 * 判断是否需要添加一条关于项目开发计划的简报
	 * @param uiid
	 * @return
	 */
	public long getCountForDaiban(String uiid,String createdDeptCd){
		String newDate = DateOperator.defaultDateFormatter.format(DateOperator.getDateNow()) + " 00:00:00";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("createdDeptCd", createdDeptCd);
		values.put("moduleCd", "execPlan");
		values.put("userCd", uiid);
		try {
			values.put("newDate",DateOperator.defaultDateFormatter.parse(newDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		StringBuffer hqlBuf = new StringBuffer()
		.append(" from JbpmTask t ")
		.append(" WHERE t.createdCenterCd = :createdDeptCd ")//290
		.append("   and t.moduleCd = :moduleCd ")
		.append("   and t.userCd = :userCd ")
		.append("   and t.createdDate > :newDate ");
		return countHqlResult(hqlBuf.toString(),values);
	}
	public long getCountForSuspend(String uuid,String createdCenterCd,List<WsPlasOrg> projects){
		StringBuffer suf = new StringBuffer();
		String newDate = DateOperator.defaultDateFormatter.format(DateOperator.getDateNow()) + " 00:00:00";
		Map<String,Object> values = new HashMap<String,Object>();
		if(projects!=null){
			for(WsPlasOrg wo : projects){
				suf.append(" "+wo.getOrgCd()+",");
			}
		}
		values.put("createdCenterCd", createdCenterCd);
		values.put("moduleCd", "execPlan");
		values.put("userCd", uuid);
		try {
			values.put("newDate",DateOperator.defaultDateFormatter.parse(newDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		StringBuffer hqlBuf = new StringBuffer()
		.append(" from JbpmTask t ")
		.append(" WHERE (t.updatedCenterCd  in ("+suf.substring(0, suf.length()-1)+") ")//
		.append("   or t.createdCenterCd = :createdCenterCd )")
		.append("   and t.moduleCd = :moduleCd ")
		.append("   and t.userCd = :userCd ")
		.append("   and t.createdDate <  :newDate ");
		return countHqlResult(hqlBuf.toString(),values);
	}
	/**
	 * 获取项目开发计划总数
	 * @param orgCd
	 * @return
	 */
	public long getCountForExecPlan(String orgCd,List<WsPlasOrg> projects){
		StringBuffer suf = new StringBuffer();
		Map<String,Object> values = new HashMap<String,Object>();
		if(projects!=null){
			for(WsPlasOrg wo : projects){
				suf.append(" "+wo.getOrgCd()+",");
			}
		}
		values.put("moduleCd", "execPlan");
		StringBuffer hqlBuf = new StringBuffer()
		.append(" from JbpmTask t ")
//		.append(" WHERE t.createdDeptCd  in ("+suf.substring(0, suf.length()-1)+") ")//CREATED_CENTER_CD
		.append("where t.moduleCd = :moduleCd ");
		return countHqlResult(hqlBuf.toString(),values);
	}
}


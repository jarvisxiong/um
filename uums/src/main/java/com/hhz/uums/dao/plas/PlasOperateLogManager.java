package com.hhz.uums.dao.plas;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasOperateLog;
import com.hhz.uums.utils.Util;

@Service
@Transactional
public class PlasOperateLogManager extends BaseService<PlasOperateLog, String> {

	private static Log log = LogFactory.getLog(PlasOperateLogManager.class);
	
	@Autowired
	private PlasOperateLogDao plasOperateLogDao;

	public void savePlasOperateLog(PlasOperateLog plasOperateLog) {
		PowerUtils.setEmptyStr2Null(plasOperateLog);
		plasOperateLogDao.save(plasOperateLog);
	}

	public void deletePlasOperateLog(String id) {
		plasOperateLogDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasOperateLog, String> getDao() {
		return plasOperateLogDao;
	}

	/**
	 * 保存操作日志
	 * 
	 * @param uiid
	 *            统一登录编号
	 * @param userName
	 *            用户名
	 * @param moduleCd
	 *            模块编号
	 * @param sumarry
	 */
	public void saveUaapOperateLog(String uiid, String userName, String moduleCd, String sumarry) {
		saveUaapOperateLog(uiid, userName, moduleCd, sumarry, null, null);
	}

	/**
	 * 保存操作日志
	 * 
	 * @param uiid 被操作对象账号
	 * @param userName 被操作对象名称
	 * @param moduleCd 模块编号
	 * @param sumarry 简要说明(例如 修改用户信息)
	 * @param desc
	 */
	public void savePlasOperateLog(String uiid, String userName, String moduleCd, String sumarry, String desc) {
		saveUaapOperateLog(uiid, userName, moduleCd, sumarry, desc, null);
	}

	/**
	 * 保存操作日志
	 * 
	 * @param uiid
	 *            统一登录编号(操作人)
	 * @param userName
	 *            用户名(操作人)
	 * @param moduleCd
	 *            模块编号
	 * @param sumarry
	 *            简要说明(例如 修改用户信息)
	 * @param desc
	 *            详细描述(例如 字段:原值-旧值)
	 * @param remark
	 */
	public void saveUaapOperateLog(String uiid, String userName, String moduleCd, String sumarry, String desc,
			String remark) {
		
		
		PlasOperateLog operateLog = new PlasOperateLog();
		operateLog.setUiid(uiid);
		operateLog.setUserName(userName);
		operateLog.setModuleCd(moduleCd);
		operateLog.setSumarry(sumarry);

		String tmpDesc = "";
		try{
			tmpDesc = Util.bSubstring(desc, 4000);
			operateLog.setOperateDetailDesc(tmpDesc);
		}catch (Exception e) {
			log.error(" 保存日志,明细越界!" + e.getCause());
		}

		operateLog.setRemark(remark);

		operateLog.setCreatedDate(new Date());
		operateLog.setCreatedCenterCd("");
		operateLog.setUpdatedDate(new Date());
		operateLog.setUpdatedCenterCd("");
		operateLog.setRecordVersion(0);

		String ip = "";
		try{
			HttpServletRequest request = ServletActionContext.getRequest();
			if (request != null) {
				ip = request.getRemoteAddr();
			}
		}catch(Exception e){
			log.error("save operate log error! " + e.getMessage());
			log.error("desc :" + tmpDesc);
		}
		operateLog.setIp(ip);
		plasOperateLogDao.save(operateLog);
	}
}


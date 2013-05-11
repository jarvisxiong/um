package com.hhz.uums.dao.bis;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateParser;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.entity.bis.BisSmsTmp;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.service.SmsUtil;
import com.hhz.uums.utils.DictContants;

@Service
@Transactional
public class BisSmsTmpManager extends BaseService<BisSmsTmp, String> {
	
	@Autowired
	private BisSmsTmpDao bisSmsTmpDao;
	@Autowired
	private AppParamManager appParamManager;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	//定是开通账号
	public static String TYPE_CD_OPEN = "1";
	
	//0-未发 1-已发 2-放弃
	public static String STATUS_CD_TODO = "0";
	public static String STATUS_CD_DONE = "1";
	public static String STATUS_CD_DETROY = "2";
	
	//0-无/失败  1-成功
	public static String RTN_CD_NO = "0";
	public static String RTN_CD_OK = "1";
	
	
	public void saveBisSmsTmp(BisSmsTmp bisSmsTmp) {
		PowerUtils.setEmptyStr2Null(bisSmsTmp);
		bisSmsTmpDao.save(bisSmsTmp);
	}

	public void deleteBisSmsTmp(String id) {
		bisSmsTmpDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisSmsTmp, String> getDao() {
		return bisSmsTmpDao;
	}
	
	//若用户未登录,则发送邮件
	public String timerOpenAcctsPwd(){
		
		
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer("<br/> 成功账号有:");
		StringBuffer sb2 = new StringBuffer("<br/> 失败账号有:");
 
		Map<String,Object> desMap = new HashMap<String,Object>();
		
		desMap.put("statusCdTodo", STATUS_CD_TODO);
		desMap.put("statusCdClose", DictContants.PLAS_USER_STATUS_CLOSED);
		desMap.put("statusCdNoEnter", DictContants.PLAS_USER_STATUS_NOENTER);
		
		desMap.put("statusCdDestroy", STATUS_CD_DETROY);
		desMap.put("retCdNo", RTN_CD_NO);
		
		//满足以下条件(任意一种)的待发送命令设置为放弃
		//1.未启用
		//2.已注销
		//3.已登录过
		
		String destroyHql = new StringBuffer()
			.append(" update BisSmsTmp t ")
			.append("    set t.statusCd = :statusCdDestroy, ")
			.append("        t.remark   = (select (case when t3.lastLoginDate is null then '注销或未启用不对!' else '已登录!' end) from PlasAcct t3 where t3.uiid = t.uiid) ")
			.append("  where t.statusCd = :statusCdTodo ")
			.append("    and exists( ")
			.append("        select t2.plasAcctId from PlasAcct t2 ")
			.append("         where t2.uiid = t.uiid ")
			.append("           and (t2.statusCd = :statusCdClose or t2.statusCd = :statusCdNoEnter or t2.lastLoginDate is not null) ")
			.append("  ) ")
			.toString();
		int i = this.getDao().batchExecute(destroyHql, desMap);
		sb.append(" 放弃发送密码的记录有 "+i+" 条!");
		
		//满足以下条件(全部)的待发送命令,执行
		//1.非注销 且 非未启用 (即正常/冻结/解冻)
		//2.未登录
		
		
		String oriPwd = null;
		boolean rtnFlg = false;
		 	
		//更新条件
		Map<String,Object> pwdMap = null;
		String pwdHql = null;
		
		Map<String,Object> doneMap = null;
		String doneHql = null;
		
		
		String hql2 = new StringBuffer()
			.append("  select t.plasUser from PlasAcct t ")
			.append("   where t.statusCd != :statusCdClose and t.statusCd != :statusCdNoEnter and t.lastLoginDate is null ")
			.append("     and exists (select t2.bisSmsTmpId from BisSmsTmp t2 where t2.uiid = t.uiid and t2.statusCd = :statusCdTodo) ")
			.toString();
		
		List<PlasUser> users = this.getDao().createQuery(hql2, desMap).list();
		for (PlasUser user : users) {
			oriPwd = appParamManager.getPassword();
			rtnFlg = SmsUtil.sendRandomPassword(user.getUiid(), user.getUserName(), user.getSexCd(), user.getMobilePhone(), oriPwd, "PD");
			if(rtnFlg){
				pwdMap = new HashMap<String,Object>();
				pwdMap.put("loginInPassword", md5PasswordEncoder.encodePassword(oriPwd, ""));
				pwdMap.put("uiid", user.getUiid());
				pwdHql = " update PlasAcct t set t.loginInPassword = :loginInPassword where uiid = :uiid ";
				this.getDao().batchExecute(pwdHql, pwdMap);
				
				sb1.append(user.getUserName()+"("+user.getUiid()+"),");
				
				//标记已处理
				doneMap = new HashMap<String,Object>();
				doneMap.put("uiid", user.getUiid());
				doneMap.put("statusCdResult",STATUS_CD_DONE);
				doneMap.put("rtnCdResult", RTN_CD_OK);
				doneMap.put("statusCdTodo", STATUS_CD_TODO);
				doneHql = " update BisSmsTmp t set t.statusCd = :statusCdResult where t.uiid = :uiid and t.statusCd = :statusCdTodo ";
				this.getDao().batchExecute(doneHql, doneMap);
				
			}else{
				
				sb2.append(user.getUserName()+"("+user.getUiid()+"),");
				
				//标记已处理
				doneMap = new HashMap<String,Object>();
				doneMap.put("uiid", user.getUiid());
				doneMap.put("rtnCdResult", RTN_CD_NO);
				doneMap.put("statusCdTodo", STATUS_CD_TODO);
				doneMap.put("remark", DateParser.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss") + "发送失败!");
				doneHql = " update BisSmsTmp t set t.remark = :remark where t.uiid = :uiid and t.statusCd = :statusCdTodo ";
				this.getDao().batchExecute(doneHql, doneMap);
			}
		}
		
		return sb.append(sb1).append(sb2).toString();
	}
	
}


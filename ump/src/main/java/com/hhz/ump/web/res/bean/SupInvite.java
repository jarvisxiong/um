package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.ump.util.EmailUtil;
import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;
import com.hhz.uums.service.WSPlasService;
/**
 * 招标公告意向供应商入库邀请
 * @author Administrator
 *
 */
public class SupInvite extends BaseContLedgerTemplate  {
	private static Log log = LogFactory.getLog(SupInvite.class);
	//标题
	private String inviteTitle;
	//招标范围
	private String inviteScope;
	//招标项目
	private String inviteProject;
	//标段面积
	private String bidArea;
	//计价模式
	private String valuationModule;
	//施工工期
	private String beginDate;
	private String endDate;
	
	private List<SupInviUnitProperty> otherProperties = new ArrayList<SupInviUnitProperty>();

	public String getInviteScope() {
		return inviteScope;
	}

	public void setInviteScope(String inviteScope) {
		this.inviteScope = inviteScope;
	}

	public String getInviteProject() {
		return inviteProject;
	}

	public void setInviteProject(String inviteProject) {
		this.inviteProject = inviteProject;
	}

	public String getBidArea() {
		return bidArea;
	}

	public void setBidArea(String bidArea) {
		this.bidArea = bidArea;
	}

	public String getValuationModule() {
		return valuationModule;
	}

	public void setValuationModule(String valuationModule) {
		this.valuationModule = valuationModule;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<SupInviUnitProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(List<SupInviUnitProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	@Override
	public void doImport() {
		//向供应商发送电子邮件和短信
		WSPlasService wsplasService=Util.getPlasService();
		String title="宝龙招标";
		for(SupInviUnitProperty unit:otherProperties){
			if(!"1".equals(unit.getDeleteBl())&&StringUtils.isNotBlank(unit.getSignPhone())&&StringUtils.isNotBlank(unit.getSignMail())){
				StringBuffer msgs =new StringBuffer();
				msgs.append(unit.getSignProject());
				String[] mobileNos={unit.getSignPhone()};
				String[] toMails={unit.getSignMail()};
				msgs.append(",您好！非常感谢您就【").append(inviteTitle).append("】参与报名，烦请至宝龙官网/招标模块进行供应商注册，谢谢");
				try {			
					//发送短信
					wsplasService.sendCommonSms(title, mobileNos, msgs.toString());			
				} catch (Exception e) {
					log.error(">>>>>>>>>>>>>> 短信发送异常，号码有问题",e);
				}
				//发送邮件
				try {		
					EmailUtil.sendSimpleMail(SpringSecurityUtils.getCurrentUiid(), LoginUtil.getPwd(null), toMails, null, title, msgs.toString());
				} catch (Exception e) {
					log.error(">>>>>>>>>>>>>> 邮件帐号出错!",e);
					e.printStackTrace();  
					
				}
			}
		}
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getInviteTitle() {
		return inviteTitle;
	}

	public void setInviteTitle(String inviteTitle) {
		this.inviteTitle = inviteTitle;
	}
}

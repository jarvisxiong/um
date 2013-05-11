/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.dao.res.ResBeanSpecialRecordManager;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResBeanSpecialRecord;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.util.MoneyUtil;
import com.hhz.ump.web.res.ResConstants;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * <p>
 * 特别申请单
 * </p>
 * 
 * @author huangjian
 * @create 2012-5-15
 */
public class SpecialApproveBill extends BaseTemplate implements IAutoImport {

	// 申请中心/公司
	private String centerOrgName;
	private String centerOrgCd;

	// 申请人
	private String userName;
	private String userCd;

	private String applyMoney;// 申请金额(元)
	private String approveMoney;// *批准金额(元)
	// *考核
	private String checkFlag1;// 列入考核
	private String checkFlag2;// 不列入考核
	private String remark;// 备注

	@Override
	public String getResTitleName() {
		return applyMoney;
	}

	public String getCenterOrgName() {
		return centerOrgName;
	}

	public void setCenterOrgName(String centerOrgName) {
		this.centerOrgName = centerOrgName;
	}

	public String getCenterOrgCd() {
		return centerOrgCd;
	}

	public void setCenterOrgCd(String centerOrgCd) {
		this.centerOrgCd = centerOrgCd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(String applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getApproveMoney() {
		return approveMoney;
	}

	public void setApproveMoney(String approveMoney) {
		this.approveMoney = approveMoney;
	}

	public String getCheckFlag1() {
		return checkFlag1;
	}

	public void setCheckFlag1(String checkFlag1) {
		this.checkFlag1 = checkFlag1;
	}

	public String getCheckFlag2() {
		return checkFlag2;
	}

	public void setCheckFlag2(String checkFlag2) {
		this.checkFlag2 = checkFlag2;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public void doImport() throws Exception {
		// 特别费申请，审批完成后，将该表单置为‘已删除’
		// 自动新建‘银行付款单’，并新建台账记录
		ResApproveInfoManager approveInfoManager = SpringContextHolder.getBean("resApproveInfoManager");
		ResBeanSpecialRecordManager specialRecordManager = SpringContextHolder.getBean("resBeanSpecialRecordManager");
		ResApproveInfo srcRes = getResApproveInfo();
	
		// 发起“特别费用”的网批ID
		String srcResId = srcRes.getResApproveInfoId();
		
		// 初始化"银行付款单"
		FinBankPayExamBill payExamBill = new FinBankPayExamBill();
		payExamBill.setPayDeptName(centerOrgName);// 付款单位
		payExamBill.setPayAmtSmallDesc(approveMoney);// 付款金额
		payExamBill.setPayAmtCapitalDesc(MoneyUtil.toChinese(approveMoney));// 大写
		payExamBill.setSpecialApproveBillId(srcResId);

		// 发起付款网批单,并保存
		ResApproveInfo payRes = new ResApproveInfo();
		payRes.setUserCd(srcRes.getUserCd());
		payRes.setUserName(srcRes.getUserName());
		payRes.setLandProject(srcRes.getLandProject());
		payRes.setLandProjectCd(srcRes.getLandProjectCd());
		approveInfoManager.createBill(ResConstants.RES_AUTH_TYPE_CD_YHFK, payExamBill,payRes);
		

		// 保存“特别费用台账"
		ResBeanSpecialRecord record = new ResBeanSpecialRecord();
		record.setApplyOrgName(centerOrgName);
		record.setApproveDate(new Date());
		record.setApproveMoney(BigDecimal.valueOf(FormatUtil.formatDouble(approveMoney)));
		record.setUserName(srcRes.getUserName());
		record.setUserCd(srcRes.getUserCd());
		record.setStatusCd("1");// 状态：0-立项未同意付款、1-同意付款、2-已付款
		record.setRemark(remark);
		//是否列入考核 1-是 0-否
		if(StringUtils.isNotBlank(this.getCheckFlag1()) && "true".equals(this.getCheckFlag1())){
			record.setCheckFlg("1");
		}else{
			record.setCheckFlg("0");
		}
		record.setEnableFlg("1");//1-可用 0-不可用
		record.setResApproveInfoId(srcResId);//特殊费用网批ID
		record.setPayApproveInfoId(payRes.getResApproveInfoId());//付款网批ID
		specialRecordManager.saveResBeanSpecialRecord(record);
		
		
		
		//将发起“特殊费用”的表单置为‘已删除’
		srcRes.setStatusCd(ResConstants.RES_APPROVE_STATUS_DELETED);
		approveInfoManager.saveResApproveInfo(srcRes, null);
		
		srcRes.getResApproveUsers().clear();
	}

	@Override
	@Transient
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}

}

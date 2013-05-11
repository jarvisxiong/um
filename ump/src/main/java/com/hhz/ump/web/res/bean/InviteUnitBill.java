/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.bid.BidConsultManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.sup.SupApproveResManager;
import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.entity.bid.BidConsult;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.sup.SupApproveRes;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.entity.sup.SupContactor;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 邀标单位审批表
 * 
 * @author huangjian
 * 
 *         2011-3-16
 */
public class InviteUnitBill extends BaseTemplate implements IAutoImport {
	
	private static final Log log = LogFactory.getLog(InviteUnitBill.class);
	
	private String projectName;// 项目
	private String projectCd;// 项目Cd
	private String biaoDuan;// 标段
	private String planMoney;// 预计金额
	private String biaoShuNo;// 标书编号
	
	private String showFlag;// 明标
	private String hideFlag;// 暗标
	
	private String businessCompany; //商业公司发起
	private String businessGroup;	//商业集团发起
	private String overAllYes;//总包
	private String overAllNo;//非总包
	
	private List<InviteUnitProperty> otherProperties = new ArrayList<InviteUnitProperty>();
	
	//备注
	private String remark;
	
	//2012-06-26 add by huangbijin
	//招标采购ID
	private String ccbpId;
	//招标采购编号(serailType+no)
	private String ccbpNo; 
	//招标采购事项
	private String ccbpPlanContentDesc;
	

//	<s:checkbox name="templateBean.gbqdFlg" id="gbqdFlg" cssClass="group"></s:checkbox>国标清单
//	<s:checkbox name="templateBean.gsqdFlg" id="gsqdFlg" cssClass="group"></s:checkbox>港式清单
//	<s:checkbox name="templateBean.qitaFlg" id="qitaFlg" cssClass="group"></s:checkbox>其他

	private String gbqdFlg;//国标清单
	private String gsqdFlg;//港式清单
	private String qitaFlg;//其他
	
	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String centerName) {
		this.projectName = centerName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String centerCd) {
		this.projectCd = centerCd;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}

	@Override
	public String getResProjectName() {
		return projectName;
	}

	@Override
	public String getResTitleName() {
		return biaoDuan;
	}

	public String getBiaoDuan() {
		return biaoDuan;
	}

	public void setBiaoDuan(String biaoDuan) {
		this.biaoDuan = biaoDuan;
	}

	public String getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(String planMoney) {
		this.planMoney = planMoney;
	}

	public List<InviteUnitProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(List<InviteUnitProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	public String getBiaoShuNo() {
		return biaoShuNo;
	}

	public void setBiaoShuNo(String biaoShuNo) {
		this.biaoShuNo = biaoShuNo;
	}

	@Override
	public void doImport(){
		SupApproveResManager supApproveResManager = SpringContextHolder.getBean("supApproveResManager");
		SupBasicManager supBasicManager = SpringContextHolder.getBean("supBasicManager");
		for (InviteUnitProperty inviteUnitProperty : otherProperties) {
			if (StringUtils.isNotBlank(inviteUnitProperty.getSupBasicId())) {
				SupApproveRes approveRes = new SupApproveRes();
				approveRes.setResApproveInfoId(getResApproveInfo().getResApproveInfoId());
				approveRes.setSupAppType("0");// 邀标
				approveRes.setProjectName(projectName);
				approveRes.setProjectCd(projectCd);
				approveRes.setAppCompTime(getResApproveInfo().getCompleteDate());
//				approveRes.setAppProjectName(appProjectName);
				SupBasic supBasic= supBasicManager.getEntity(inviteUnitProperty.getSupBasicId());
				approveRes.setSupBasic(supBasic);
				supApproveResManager.saveSupApproveRes(approveRes);
			}
		}
		
		
		//如是总包才导入,导入投标台账库(投标台账表/投标台账供应商)
		//非总包也导入台帐
		if("true".equals(overAllYes)){
			saveToBidModule();
		}else{
			saveToBidModule();
		}
	}
	
	/**
	 * 导入投标台账库(投标台账表/投标台账供应商)
	 * @author zhongyb
	 * @return
	 * @throws ParseException 
	 */
	private void saveToBidModule(){
		
		BidLedgerManager bidLedgerManager = SpringContextHolder.getBean("bidLedgerManager");
		BidSupManager bidSupManager = SpringContextHolder.getBean("bidSupManager");
		SupBasicManager supBasicManager = SpringContextHolder.getBean("supBasicManager");
		BidConsultManager bidConsultManager = SpringContextHolder.getBean("bidConsultManager");
	
		try{
			/* 1.投标台帐信息
			 * 导入字段有:机构CD/项目名称/标段名称/邀请类型/预计金额/标书编号/预算类型/网批ID
			 */
			BidLedger bidLedger = new BidLedger();
			//导入类型： 非战略
			bidLedger.setSrcTypeCd(BidLedgerManager.SRC_TYPE_FZL);
			//机构CD
			bidLedger.setOrgCd(this.getProjectCd());
			//项目名称
			bidLedger.setOrgName(CodeNameUtil.getDeptNameByCd(this.getProjectCd()));
			//标段名称
			bidLedger.setBidSectionName(this.getBiaoDuan());
			//邀请类型:明标/暗标(默认:9-未知)
			bidLedger.setVisableFlg(BidLedgerManager.VISIBLE_FLG_UNKNOWN);
			if("true".equals(this.getShowFlag())){
				bidLedger.setVisableFlg(BidLedgerManager.VISIBLE_FLG_SHOW);
			} 
			if("true".equals(this.getHideFlag())){
				bidLedger.setVisableFlg(BidLedgerManager.VISIBLE_FLG_HIDE);
			}
			//预计金额
			if (StringUtils.isNotBlank(this.getPlanMoney())) {
				NumberFormat nf = NumberFormat.getInstance();
				bidLedger.setTargetAmt(BigDecimal.valueOf(nf.parse(this.getPlanMoney()).doubleValue()));
			}
			//标书编号
			bidLedger.setSectionNo(this.getBiaoShuNo());
			//预算类型
			bidLedger.setBudgetInFlg("true".equals(this.getInFlag())?BidLedgerManager.BUDGET_FLG_YES:BidLedgerManager.BUDGET_FLG_NO);
			bidLedger.setBudgetOutFlg("true".equals(this.getOutFlag())?BidLedgerManager.BUDGET_FLG_YES:BidLedgerManager.BUDGET_FLG_NO);
			//投标状态
			bidLedger.setBidStatusCd(BidLedgerManager.BID_STATUS_IMPORT);
			//网批ID		
			bidLedger.setResApproveInfoId(this.getResApproveInfo().getResApproveInfoId());

			//默认系统创建
			bidLedger.setCreator("system");
			bidLedger.setCreatedDate(new Date());
			//默认批次号
			bidLedger.setBatchNo(0L);
			//1-模拟清单 0-标底单位
			bidLedger.setConsultFlg("0");
			
			//导入招标计划ID add by huangbijin 2012-06-27
			bidLedger.setCcbpId(this.getCcbpId());
			bidLedger.setCcbpNo(this.getCcbpNo());
			bidLedger.setNeedGuarFlg("1");//是否需要保证金：1-默认需要保证金
			
			bidLedgerManager.saveBidLedger(bidLedger);
			
			// 2.标底单位/投标单位
			// 2.1构造标底单位(序号/关联供应商ID/供应商名称)
			
			//搜索模拟单位
			BidConsult bidConsult = bidConsultManager.getBiaodi();
			//咨询公司
			BidSup sup = new BidSup();
			sup.setDispOrderNo(new Long(0));//默认0
			if(	bidConsult == null){
//				sup.setRelSupBasicId(null);
				sup.setSupName("标底单位");
			}else{
				sup.setSupName("标底单位");
				bidLedger.setConsultFlg("0");//1-模拟清单，1-标底单位
				sup.setRelSupBasicId(bidConsult.getBidConsultId());
				sup.setSupName(bidConsult.getFullName());
			}
			sup.setTypeCd(BidSupManager.SUP_CONSULTING);
			sup.setCreator("system");
			sup.setCreatedDate(new Date());
			sup.setBidLedger(bidLedger);
			bidSupManager.saveBidSup(sup);
			
			/* 2.2供应商信息
			 * 导入字段:序号/关联供应商ID/供应商名称/*/

			List<String> tmpIdList = new ArrayList<String>();//用于过滤重复的单位
			BidSup bidSup = null;
			int i = 1;
			SupBasic supBasic = null;
			List<SupContactor> contactors = null;
			SupContactor contactor = null;
			for (InviteUnitProperty tmpProp : otherProperties) {
				
				if(tmpIdList.contains(tmpProp.getSupBasicId())){
					continue;
				}
				tmpIdList.add(tmpProp.getSupBasicId());
				
				
				bidSup = new BidSup();
				//对应台账主表
				bidSup.setBidLedger(bidLedger);
				//显示顺序号
				bidSup.setDispOrderNo(Long.parseLong(Integer.toString(i++)));
				//关联供应商ID(供应商台账库）
				bidSup.setRelSupBasicId(tmpProp.getSupBasicId());
				//供应商名称
				bidSup.setSupName(tmpProp.getUnitName());
				//是否接受(接受/拒绝）
				bidSup.setReceiveStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
				//是否提交保证金
				bidSup.setSubmitStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
				//是否退款 
				bidSup.setRefundStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
				//是否中标(未中标/中标)
				bidSup.setSupBidStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);	
				//是否有附件
				bidSup.setAttachFlg(BidSupManager.ATTACH_FILE_NO);
				//供应商
				bidSup.setTypeCd(BidSupManager.SUP_PROVIDOR);
				
				supBasic = supBasicManager.getEntity(tmpProp.getSupBasicId());
				if(supBasic != null){	
					//供方级别
					if(StringUtils.isNotBlank(supBasic.getSupManaEval())){						
						bidSup.setProvideLevelCd(supBasic.getSupManaEval());
					}
					//获取第一个联系人信息
					//标段列表中用户自动选择，不需要初始化
					/*contactors = supBasic.getSupContactors();
					if(contactors != null && contactors.size()>0){
						contactor = contactors.get(0);
						if(contactor!= null){
							//联系人
							bidSup.setLinkMan(contactor.getName());
							//联系方式
							bidSup.setLinkDesc(contactor.getTelephone());
							//电子邮件
							bidSup.setEmail(contactor.getEMail());
						}
					}*/
				}
				
				//默认系统创建
				bidSup.setCreator("system");
				bidSup.setCreatedDate(new Date());
				
				bidSup.setBidLedger(bidLedger);
				bidSupManager.saveBidSup(bidSup);
			}
			log.info(new StringBuffer()
					.append("导入投标台帐,网批ID[").append(bidLedger.getResApproveInfoId())
					.append("]转存投标台帐ID[").append(bidLedger.getBidLedgerId()).append("]供应商数[")
					.append(otherProperties.size())
					.toString()
			);
		}catch (Exception e) {
			log.error(new StringBuffer()
			.append("导入投标台帐异常,网批ID[").append(this.getResApproveInfo().getResApproveInfoId())
			.append("]").toString(),e);
		}
	}

	@Override
	public boolean isAutoImport() {
		return true;
	}

	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getHideFlag() {
		return hideFlag;
	}

	public void setHideFlag(String hideFlag) {
		this.hideFlag = hideFlag;
	}

	/**
	 * @return the businessCompany
	 */
	public String getBusinessCompany() {
		return businessCompany;
	}

	/**
	 * @param businessCompany the businessCompany to set
	 */
	public void setBusinessCompany(String businessCompany) {
		this.businessCompany = businessCompany;
	}

	/**
	 * @return the businessGroup
	 */
	public String getBusinessGroup() {
		return businessGroup;
	}

	/**
	 * @param businessGroup the businessGroup to set
	 */
	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	

	public String getOverAllYes() {
		return overAllYes;
	}

	public void setOverAllYes(String overAllYes) {
		this.overAllYes = overAllYes;
	}

	public String getOverAllNo() {
		return overAllNo;
	}

	public void setOverAllNo(String overAllNo) {
		this.overAllNo = overAllNo;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCcbpNo() {
		return ccbpNo;
	}

	public void setCcbpNo(String ccbpNo) {
		this.ccbpNo = ccbpNo;
	}

	public String getCcbpId() {
		return ccbpId;
	}

	public void setCcbpId(String ccbpId) {
		this.ccbpId = ccbpId;
	}

	public String getCcbpPlanContentDesc() {
		return ccbpPlanContentDesc;
	}

	public void setCcbpPlanContentDesc(String ccbpPlanContentDesc) {
		this.ccbpPlanContentDesc = ccbpPlanContentDesc;
	}

	public String getGbqdFlg() {
		return gbqdFlg;
	}

	public void setGbqdFlg(String gbqdFlg) {
		this.gbqdFlg = gbqdFlg;
	}

	public String getGsqdFlg() {
		return gsqdFlg;
	}

	public void setGsqdFlg(String gsqdFlg) {
		this.gsqdFlg = gsqdFlg;
	}

	public String getQitaFlg() {
		return qitaFlg;
	}

	public void setQitaFlg(String qitaFlg) {
		this.qitaFlg = qitaFlg;
	}
}

package com.hhz.ump.dao.sc;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.sc.ScContractTempletInfo;
import com.hhz.ump.web.sc.ScContractTempletInfoAction;

@Service
@Transactional
public class ScContractTempletInfoManager extends BaseService<ScContractTempletInfo, String> {
	@Autowired
	private ScContractTempletInfoDao scContractTempletInfoDao;
	@Autowired
	ResApproveInfoManager resApproveInfoManager;

	public void saveScContractTempletInfo(ScContractTempletInfo scContractTempletInfo) {
		PowerUtils.setEmptyStr2Null(scContractTempletInfo);
		scContractTempletInfoDao.save(scContractTempletInfo);
	}

	public void deleteScContractTempletInfo(String id) {
		scContractTempletInfoDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractTempletInfo, String> getDao() {
		return scContractTempletInfoDao;
	}


	/*
	 * 网批中调用的方法，判断是否被网批锁定
	 * oldStatuCd:旧状态； newStatuCd：新状态；resApproveInfoId:网批ID； contractTempletInfoId：标准合同ID； approveNo:合同编号
	 *  ResBillType：网批表单bean名称，通过这个来区分定标审批表，合同条款审批表，合同评审表
	 * StatuCd 新增:0, 审批中:1, 完成审批:2, 驳回:3, 已删除:4
	 */
	public void statusModified(String oldStatuCd, String newStatuCd, String resApproveInfoId, long approveNo, String contractTempletInfoId, String ResBillType) throws Exception {
		//System.out.println("====1111===================newStatuCd="+newStatuCd+"+resApproveInfoId="+resApproveInfoId+"+approveNo="+approveNo+"+contractTempletInfoId="+contractTempletInfoId+"+ResBillType="+ResBillType);
		if (StringUtils.isNotBlank(contractTempletInfoId)){
			ScContractTempletInfo scContractTempletInfo  = this.getEntity(contractTempletInfoId);
			//System.out.println("========scContractTempletInfo.getStatusCd()==="+scContractTempletInfo.getStatusCd());
			if(scContractTempletInfo != null && "1".equals(newStatuCd) ){
				//被网批应用到，增加锁定
				if("ContractItemApprove".equalsIgnoreCase(ResBillType)
						||"BizContractItemApprove".equalsIgnoreCase(ResBillType)){
					//如果是合同条款审批表，设置合同条款审批表
					scContractTempletInfo.setApproveId2(resApproveInfoId);
					scContractTempletInfo.setApproveNo2(Long.toString(approveNo));
				}else if("BidContractApprove".equalsIgnoreCase(ResBillType)
						||"PlanMoreLocationSheet".equalsIgnoreCase(ResBillType)
						||"RentPurposeBill".equalsIgnoreCase(ResBillType)){
					//如果是合同评审表、多经审批表、租赁合同审批表，设置合同评审表
					scContractTempletInfo.setApproveId3(resApproveInfoId);
					scContractTempletInfo.setApproveNo3(Long.toString(approveNo));
				}
				scContractTempletInfo.setResApproveInfoId(resApproveInfoId);
				scContractTempletInfo.setResApproveInfoNo(Long.toString(approveNo));
				scContractTempletInfo.setStatusCd(ScContractTempletInfoAction.CON_APPROVE_STATUS_ING);
				this.saveScContractTempletInfo(scContractTempletInfo);
			}
			if(scContractTempletInfo != null && ("0".equals(newStatuCd) || "3".equals(newStatuCd) || "4".equals(newStatuCd)) ){
				//网批驳回或者删除，取消锁定
				scContractTempletInfo.setResApproveInfoId(null);
				scContractTempletInfo.setResApproveInfoNo(null);
				scContractTempletInfo.setStatusCd(ScContractTempletInfoAction.CON_APPROVE_STATUS_MERGE);
				//System.out.println("========222scContractTempletInfo.getStatusCd()==="+scContractTempletInfo.getStatusCd());
				this.saveScContractTempletInfo(scContractTempletInfo);
			}
			if(scContractTempletInfo != null && "2".equals(newStatuCd) ){
				//网批结束，判断是否是合同评审表：如果是，则完成合同；如果不是，置入指定XX表的字段
				scContractTempletInfo.setResApproveInfoId(null);
				if("ContractItemApprove".equalsIgnoreCase(ResBillType)
						||"BizContractItemApprove".equalsIgnoreCase(ResBillType)){
					//如果是合同条款审批表，网批结束后返回可修改状态
					scContractTempletInfo.setApproveId2(resApproveInfoId);
					scContractTempletInfo.setApproveNo2(Long.toString(approveNo));
					scContractTempletInfo.setStatusCd(ScContractTempletInfoAction.CON_APPROVE_STATUS_MERGE);
				}
				if("BidContractApprove".equalsIgnoreCase(ResBillType)
						||"PlanMoreLocationSheet".equalsIgnoreCase(ResBillType)
						||"RentPurposeBill".equalsIgnoreCase(ResBillType)){
					//如果是合同评审表、多经审批表、租赁合同审批表，网批结束后自动进入可打印状态
					scContractTempletInfo.setApproveId3(resApproveInfoId);
					scContractTempletInfo.setApproveNo3(Long.toString(approveNo));
					scContractTempletInfo.setStatusCd(ScContractTempletInfoAction.CON_APPROVE_STATUS_FINISHL);
				}
				this.saveScContractTempletInfo(scContractTempletInfo);
			}
		}
	}
}


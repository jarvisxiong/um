package com.hhz.ump.dao.pm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.entity.pm.PmEntryResRelation;
import com.hhz.ump.entity.pm.PmMateEntry;
import com.hhz.ump.web.res.bean.BizPayChargeBill;

@Service
@Transactional
public class PmMateEntryManager extends BaseService<PmMateEntry, String> {
	@Autowired
	private PmMateEntryDao pmMateEntryDao;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private PmEntryResRelationManager pmEntryResRelationManager;

	@Transactional(readOnly = true)
	public PmMateEntry getPmMateEntry(String id) {
		return pmMateEntryDao.get(id);
	}
	
	//保存企划案例库
	public void savePmMateEntry(PmMateEntry pmMateEntry) {
		PowerUtils.setEmptyStr2Null(pmMateEntry);
		pmMateEntryDao.save(pmMateEntry);
	}
	
	public void deletePmMateEntry(String id) {
		pmMateEntryDao.delete(id);
	}
	
	@Override
	public HibernateDao<PmMateEntry, String> getDao() {
		return pmMateEntryDao;
	}
	
	
	public PmMateEntry noRepeat(String pmMateEntryNo){
		StringBuilder hql = new StringBuilder();
		List<PmMateEntry> pmMateEntryList = new ArrayList<PmMateEntry>();
		Map<String, Object> values = new HashMap<String, Object>();
		hql.append(" from PmMateEntry f where 1=1");
		if(StringUtils.isNotBlank(pmMateEntryNo)){
			hql.append(" and f.pmMateEntryNo = :pmMateEntryNo");
			values.put("pmMateEntryNo",pmMateEntryNo);
		}
		pmMateEntryList = pmMateEntryDao.find(hql.toString(), values);
		if(pmMateEntryList.size()>0)
			return (PmMateEntry)pmMateEntryList.get(0);
		else
			return null;
	}

	/**
	 * 网批导入"付款费用审批表"
	 * @param bill
	 */
	public void importResRelation(BizPayChargeBill bill) {
		PmEntryResRelation resRelation = new PmEntryResRelation();
		if(StringUtils.isNotBlank(bill.getResApproveInfo().getResApproveInfoId())&&StringUtils.isNotBlank(bill.getResAuthTypeId())&&StringUtils.isNotBlank(bill.getContLedgerId())&&StringUtils.isNotBlank(bill.getPmMateEntryId())){
			//类型Id
			resRelation.setResAuthTypeId(bill.getResAuthTypeId());
			//类型对应的表明 (eg: ‘商业公司企划费付款审批表’)
			resRelation.setDisplayName(bill.getDisplayName());
			//项目名称
			resRelation.setProjectName(bill.getProjectName());
			//合同台账ID（主键）
			resRelation.setContLedgerId(bill.getContLedgerId());
			//合同编号
			resRelation.setContractNo(bill.getContactNo());
			//网批结束的时间
			resRelation.setApproveEndDate(new Date());
			//相关网批关联的企划案例外键ID
			resRelation.setPmMateEntryId(bill.getPmMateEntryId());
			//相关网批对应的外键ID
			resRelation.setResApproveId(bill.getResApproveInfo().getResApproveInfoId());
			
			//网批导入"付款费用审批表"
			pmEntryResRelationManager.savePmEntryResRelation(resRelation);
		}
	}
	
}


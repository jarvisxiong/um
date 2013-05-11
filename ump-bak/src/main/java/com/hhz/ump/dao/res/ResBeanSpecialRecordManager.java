package com.hhz.ump.dao.res;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResBeanSpecialRecord;

@Service
@Transactional
public class ResBeanSpecialRecordManager extends BaseService<ResBeanSpecialRecord, String> {
	@Autowired
	private ResBeanSpecialRecordDao resBeanSpecialRecordDao;

	public void saveResBeanSpecialRecord(ResBeanSpecialRecord resBeanSpecialRecord) {
		PowerUtils.setEmptyStr2Null(resBeanSpecialRecord);
		resBeanSpecialRecordDao.save(resBeanSpecialRecord);
	}

	public void deleteResBeanSpecialRecord(String id) {
		resBeanSpecialRecordDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResBeanSpecialRecord, String> getDao() {
		return resBeanSpecialRecordDao;
	}
	public ResBeanSpecialRecord getEntityByResId(String resId){
		String hql="select res from ResBeanSpecialRecord res where res.resApproveInfoId=?";
		List<ResBeanSpecialRecord> list= find(hql, resId);
		if (list.size()>0)
			return list.get(0);
		return null;
	}
	public ResBeanSpecialRecord getEntityByPayResId(String payResId){
		String hql="select res from ResBeanSpecialRecord res where res.payApproveInfoId=?";
		List<ResBeanSpecialRecord> list= find(hql, payResId);
		if (list.size()>0)
			return list.get(0);
		return null;
	}
	public ResBeanSpecialRecord getEntityByIds(String srcResId, String payResId){
		String hql="select res from ResBeanSpecialRecord res where res.resApproveInfoId = ? and res.payApproveInfoId = ?";
		List<ResBeanSpecialRecord> list= find(hql, srcResId, payResId);
		if (list.size()>0)
			return list.get(0);
		return null;
	}
	
	public Page<ResBeanSpecialRecord> findByPage(Page<ResBeanSpecialRecord> page,ResBeanSpecialRecord record){
		StringBuffer hql = new StringBuffer("from ResBeanSpecialRecord b where 1=1 ");
		Map<String, Object> values = new HashMap<String, Object>();
		if(record != null) {
			// 银行账号
			if(StringUtils.isNotBlank(record.getApplyOrgName())) {
				hql.append("and b.applyOrgName like :applyOrgName ");
				values.put("applyOrgName", "%" + record.getApplyOrgName() + "%");
			}
			// 银行名称
			if(StringUtils.isNotBlank(record.getUserName())) {
				hql.append("and b.userName like :userName ");
				values.put("userName", "%" + record.getUserName() + "%");
			}
			// 开户公司
			if(StringUtils.isNotBlank(record.getStatusCd())) {
				hql.append("and b.statusCd = :statusCd ");
				values.put("statusCd", record.getStatusCd());
			}
		}
		hql.append("order by b.approveDate desc ");
		return this.findPage(page, hql.toString(), values);
	}
	public void confirm(String id){
		ResBeanSpecialRecord record=getEntity(id);
		record.setStatusCd("2");//状态：0-立项未同意付款、1-同意付款、2-已付款
		saveResBeanSpecialRecord(record);
	}
}


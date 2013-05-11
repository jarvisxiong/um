package com.hhz.uums.dao.app;

import java.math.BigDecimal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.app.AppSeq;
import com.hhz.uums.utils.GlobalConstants;

@Service
@Transactional
public class AppSeqManager extends BaseService<AppSeq, String> {
	
	private static Log log =LogFactory.getLog(AppSeqManager.class);
	
	@Autowired
	private AppSeqDao appSeqDao;

	public void saveAppSeq(AppSeq appSeq) {
		PowerUtils.setEmptyStr2Null(appSeq);
		appSeqDao.save(appSeq);
	}

	public void deleteAppSeq(String id) {
		appSeqDao.delete(id);
	}

	@Override
	public HibernateDao<AppSeq, String> getDao() {
		return appSeqDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public synchronized Integer createNextValue(String seqType) {
	// SEQ_APP_CD
	// SEQ_BRODCAST_CD
	// SEQ_DOMAIN_CD
	// SEQ_FUNCTION_CD
	// SEQ_MENU_CD
	// SEQ_MODULE_CD
	// SEQ_ORG_CD
	// SEQ_PAGE_CD
	// SEQ_PARAM_CD
	// SEQ_ROLE_CD
	// SEQ_SYSPOS_CD
	// SEQ_USER_CD
	// SEQ_PAGE_CD

	BigDecimal orderNo = BigDecimal.ZERO;

	if (GlobalConstants.SEQ_PAGE_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();
	} else if (GlobalConstants.SEQ_MODULE_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();
	} else if (GlobalConstants.SEQ_MENU_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();
	} else if (GlobalConstants.SEQ_ORG_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();

	} else if (GlobalConstants.SEQ_USER_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();

	} else if (GlobalConstants.SEQ_ROLE_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();

	} else if (GlobalConstants.SEQ_DOMAIN_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();

	} else if (GlobalConstants.SEQ_BRODCAST_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();

	} else if (GlobalConstants.SEQ_APP_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();
	} else if (GlobalConstants.SEQ_FUNCTION_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();
	} else if (GlobalConstants.SEQ_PARAM_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();
	}  else if (GlobalConstants.SEQ_SYSPOS_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();
	}  
	//角色组
	else if (GlobalConstants.SEQ_ROLE_GROUP_CD.equals(seqType)) {
			AppSeq seq = getDao().get(seqType);
			orderNo = seq.getSeqOrderNo();
			orderNo = orderNo.add(BigDecimal.ONE);
			seq.setSeqOrderNo(orderNo);
			appSeqDao.save(seq);
			return orderNo.intValue();
	} else {
		log.error(" 获取序列号输入参数有误!seqType:[" + seqType + "]");
	}
		return -1;
	}
}

package com.hhz.ump.dao.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppSeq;
import com.hhz.ump.util.GlobalConstants;

@Service
@Transactional
public class AppSeqManager extends BaseService<AppSeq, String> {

	private static final Log log = LogFactory.getLog(AppSeqManager.class);

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
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<AppSeq, String> getDao() {
		return appSeqDao;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Integer createNextValue(String seqType) {
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
		// SEQ_USER_CD
		// SEQ_PAGE_CD

		Integer orderNo = 0;
		
		if (GlobalConstants.SEQ_PAGE_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_PAGE_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}
		} else if (GlobalConstants.SEQ_MODULE_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_MODULE_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}
		} else if (GlobalConstants.SEQ_MENU_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_MENU_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}
		}else if (GlobalConstants.SEQ_ORG_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_ORG_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}

		} else if (GlobalConstants.SEQ_USER_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_USER_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}

		} else if (GlobalConstants.SEQ_ROLE_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_ROLE_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}

		} else if (GlobalConstants.SEQ_DOMAIN_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_DOMAIN_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}

		} else if (GlobalConstants.SEQ_BRODCAST_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_BRODCAST_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}

		} else if (GlobalConstants.SEQ_APP_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_APP_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}
		} else if (GlobalConstants.SEQ_FUNCTION_CD.equals(seqType)) {
			synchronized (GlobalConstants.SEQ_FUNCTION_CD) {
				AppSeq seq = getDao().get(seqType);
				orderNo = seq.getSeqOrderNo();
				orderNo++;
				seq.setSeqOrderNo(orderNo);
				appSeqDao.save(seq);
				return orderNo;
			}
		}
		else if (GlobalConstants.SEQ_PARAM_CD.equals(seqType)) {
				synchronized (GlobalConstants.SEQ_PARAM_CD) {
					AppSeq seq = getDao().get(seqType);
					orderNo = seq.getSeqOrderNo();
					orderNo++;
					seq.setSeqOrderNo(orderNo);
					appSeqDao.save(seq);
					return orderNo;
				}
		} else {
			log.warn(" 获取序列号输入参数有误!seqType:[" + seqType + "]");
			return -1;
		}

	}
}


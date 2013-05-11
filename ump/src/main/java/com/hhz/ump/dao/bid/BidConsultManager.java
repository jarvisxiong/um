package com.hhz.ump.dao.bid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidConsult;

@Service
@Transactional
public class BidConsultManager extends BaseService<BidConsult, String> {
	@Autowired
	private BidConsultDao bidConsultDao;

	public void saveBidConsult(BidConsult bidConsult) {
		PowerUtils.setEmptyStr2Null(bidConsult);
		bidConsultDao.save(bidConsult);
	}

	public void deleteBidConsult(String id) {
		bidConsultDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidConsult, String> getDao() {
		return bidConsultDao;
	}

	/**
	 * 获取模拟公司
	 * @return
	 */
	public BidConsult getSimulate() {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("isSimulateFlg", "1");//1-模拟公司
		values.put("consultTypeCd", "1");//1-网上投标
		 
		String hql = " from BidConsult t where t.isSimulateFlg = :isSimulateFlg and t.consultTypeCd = :consultTypeCd ";
		List<BidConsult> list = this.find(hql, values);
		if(list == null)
			return null;
		else
			return list.get(0);
	}
	/**
	 * 获取标底单位
	 * @return
	 */
	public BidConsult getBiaodi() {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("isSimulateFlg", "0");//1-模拟公司,0-标底单位
		values.put("consultTypeCd", "1");//1-网上投标
		 
		String hql = " from BidConsult t where t.isSimulateFlg = :isSimulateFlg and t.consultTypeCd = :consultTypeCd ";
		List<BidConsult> list = this.find(hql, values);
		if(list == null)
			return null;
		else
			return list.get(0);
	}
	/**
	 * 获取最新咨询公司
	 * @return
	 */
	public BidConsult getConsult() {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("isSimulateFlg", "0");//1-咨询公司
		values.put("consultTypeCd", "1");//1-网上投标
		 
		String hql = " from BidConsult t where t.isSimulateFlg = :isSimulateFlg and t.consultTypeCd = :consultTypeCd ";
		List<BidConsult> list = this.find(hql, values);
		if(list == null)
			return null;
		else
			return list.get(0);
	}
	
}


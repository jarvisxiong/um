package com.hhz.ump.dao.biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.biz.BizBankAccount;
import com.hhz.ump.util.PdCache;
import com.hhz.ump.util.PinyinComparator;

@Service
@Transactional
public class BizBankAccountManager extends BaseService<BizBankAccount, String> {
	@Autowired
	private BizBankAccountDao bizBankAccountDao;

	public void saveBizBankAccount(BizBankAccount bizBankAccount) {
		PowerUtils.setEmptyStr2Null(bizBankAccount);
		bizBankAccountDao.save(bizBankAccount);
	}

	/***
	 * 批量修改
	 * @param accountList
	 */
	public void batchSave(List<BizBankAccount> accountList) {
		if(CollectionUtils.isEmpty(accountList) == false) {
			Session session = bizBankAccountDao.getSession();
			for(int i=0; i<accountList.size(); i++) {
				session.saveOrUpdate(accountList.get(i));
				if(i % 50 == 0) {
					session.flush();
					session.clear();
				}
			}
		}
	}

	public void deleteBizBankAccount(String id) {
		bizBankAccountDao.delete(id);
	}
	
	/***
	 * 批量删除
	 */
	public void batchDelete() {
		String hql = "delete from BizBankAccount b";
		Session session = bizBankAccountDao.getSession();
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}
	
	/***
	 * 搜索银行账户信息(分页)
	 * @param page
	 * @param account
	 * @return
	 */
	public Page<BizBankAccount> findPage(Page<BizBankAccount> page, BizBankAccount account) {
		StringBuffer hql = new StringBuffer("from BizBankAccount b where 1=1 ");
		Map<String, Object> values = new HashMap<String, Object>();
		if(account != null) {
			// 银行账号
			if(StringUtils.isNotBlank(account.getBankNo())) {
				hql.append("and b.bankNo like :bankNo ");
				values.put("bankNo", "%" + account.getBankNo() + "%");
			}
			// 银行名称
			if(StringUtils.isNotBlank(account.getBankName())) {
				hql.append("and b.bankName like :bankName ");
				values.put("bankName", "%" + account.getBankName() + "%");
			}
			// 开户公司
			if(StringUtils.isNotBlank(account.getCompanyName())) {
				hql.append("and b.companyName like :companyName ");
				values.put("companyName", "%" + account.getCompanyName() + "%");
			}
		}
		hql.append("order by b.companyName asc, b.bankName asc ");
		return this.findPage(page, hql.toString(), values);
	}
		
	@Override
	public HibernateDao<BizBankAccount, String> getDao() {
		return bizBankAccountDao;
	}

	/**
	 * 获取公司列表
	 * 
	 * @return
	 */
	public List<String> loadCompanies() {
		Collection<String> coll = PdCache.getFieldList(BizBankAccount.class, "companyName");
		List list=new ArrayList<String>(coll);
		Collections.sort(list, new PinyinComparator());
		return list;
	}

	/**
	 * 
	 * @param companyName
	 * @return
	 */
	public Map<String, String> loadAccouts(String companyName) {
		Map<String, String> mapAccout = new LinkedHashMap<String, String>();
//		mapAccout.put("", "--选择--");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyName", companyName);
		List<BizBankAccount> list = PdCache.getEntityList(BizBankAccount.class, map);
		CollectionHelper.sortList(list, "bankNo");
		for (BizBankAccount account : list) {
			mapAccout.put(account.getBankNo(), account.getBankName());
		}
		return mapAccout;
		
	}
}

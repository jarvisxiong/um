package com.hhz.ump.dao.fin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.fin.FinItem;

@Service
@Transactional
public class FinItemManager extends BaseService<FinItem, String> {
	@Autowired
	private FinItemDao finItemDao;

	// FinItem Manager //
	@Transactional(readOnly = true)
	public FinItem getFinItem(String id) {
		return finItemDao.get(id);
	}

	public List<FinItem> getAllFinItem() {
		return finItemDao.getAll();
	}

	public void saveFinItem(FinItem finItem) {
		String finItemCd = finItem.getFinItemCd();
		if (finItemCd != null && !"".equals(finItemCd)) {
			List<FinItem> items = getFinItemByItemCd(finItemCd);
			if (items == null || items.size() <= 0) {
				PowerUtils.setEmptyStr2Null(finItem);
				finItemDao.save(finItem);
			}
		}
	}

	public List<FinItem> getFinItemByItemCd(String itemCd) {
		StringBuffer hql = new StringBuffer("from " + FinItem.class.getSimpleName() + " where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (itemCd != null && !"".equals(itemCd)) {
			hql.append(" and finItemCd =:itemCd ");
			params.put("itemCd", itemCd);
		}
		List<FinItem> result = this.find(hql.toString(), params);
		return result;

	}

	public void deleteFinItem(String id) {
		finItemDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<FinItem, String> getDao() {
		return finItemDao;
	}

	/**
	 * 搜索父类ID=parentItemCd的现金流项目
	 * 
	 * @param parentItemCd
	 * @return
	 */
	public List<FinItem> getParentItem(String parentItemCd) {
		String hql = "from " + FinItem.class.getSimpleName() + " where parentItemCd=? ORDER BY dispOrderNum";
		List<FinItem> result = finItemDao.find(hql, parentItemCd);
		return result;
	}

	/**
	 * 通过finItemCd得到item name
	 * 
	 * @param parentItemCd
	 * @return
	 */
	public String getItemName(String itemCd) {
		String hql = "from " + FinItem.class.getSimpleName() + " where finItemCd=?";
		List<FinItem> result = finItemDao.find(hql, itemCd);
		String itemName = "";
		if (result != null && result.size() > 0) {
			itemName = result.get(0).getFinItemName();
		}
		return itemName;
	}

	public List getItemListBetw(List dateList, String projectCd) {
		StringBuffer hql = new StringBuffer("select a.fin_item_name,a.fin_item_cd,a.PARENT_ITEM_CD");
		if (dateList != null && dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				hql.append(",(case when t.date").append(i).append(" is null then 0 else t.date").append(i).append(
						" end) as date").append(i);
			}
			hql.append(",(case when ");
			StringBuffer strDate = new StringBuffer("");
			for (int i = 0; i < dateList.size(); i++) {
				if (i != (dateList.size() - 1)) {
					strDate.append("date" + i + "+");
				} else {
					strDate.append("date" + i);
				}
			}
			hql.append(strDate.toString());
			hql.append(" is null then 0 else ").append(strDate.toString());
			hql.append(" end) as dete_end ");
		}
		hql.append(" from fin_item a left join(select b.FIN_ITEM_CD");
		if (dateList != null && dateList.size() > 0) {
			for (int i = 0; i < dateList.size(); i++) {
				String dateStr = (String) dateList.get(i);
				hql.append(",sum(case when b.CREATED_DATE >= ");

				// ms sql
				// hql.append(DateOperator.parse(dateStr + " 00:00:00",
				// "yyyy-MM-dd HH:mm:ss"));

				// oracle
				hql.append(" to_date('" + dateStr + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ");

				hql.append(" and b.CREATED_DATE<=").append(
				// ms sql
						// DateOperator.parse(dateStr + " 23:59:59",
						// "yyyy-MM-dd HH:mm:ss")
						// oracle
						" to_date('" + dateStr + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') "

				);

				hql.append(" then b.IN_AMOUNT+b.OUT_AMOUNT else 0 end) as date").append(i);
			}
		}
		hql.append(" from fin_in_out_detail b ");
		if (projectCd != null && !"".equals(projectCd)) {
			hql.append(",FIN_PROJECT_ACCT_REL d where d.PROJECT_CD='");
			hql.append(projectCd).append(
					"' and d.FIN_PROJECT_ACCT_REL_ID =b.FIN_PROJECT_ACCT_REL_ID and d.CURRENCY_CD ='RMB' ");
		}
		hql.append(" group by b.FIN_ITEM_CD )t on a.FIN_ITEM_CD =t.fin_item_cd ");
		hql.append(" ORDER BY a.FIN_ITEM_TYPE_CD,a.DISP_ORDER_NUM asc");
		Map<String, Object> params = new HashMap<String, Object>();
		List list = finItemDao.findBySql(hql.toString(), params);
		return list;
	}

}

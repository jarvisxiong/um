package com.hhz.ump.dao.jbpm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.jbpm.JbpmBudget;
import com.hhz.ump.entity.jbpm.JbpmBudgetDetail;

@Service
@Transactional
public class JbpmBudgetManager extends BaseService<JbpmBudget, String> {
	@Autowired
	private JbpmBudgetDao jbpmBudgetDao;

	@Autowired
	private JbpmBudgetDetailManager jbpmBudgetDetailManager;

	// JbpmBudget Manager //
	@Transactional(readOnly = true)
	public JbpmBudget getJbpmBudget(String id) {
		return jbpmBudgetDao.get(id);
	}

	public List<JbpmBudget> getAllJbpmBudget() {
		return jbpmBudgetDao.getAll();
	}

	/**
	 * 每次保存预算记录时，也要同时把它的预算详情信息保存
	 * 
	 * @param jbpmBudget
	 */
	public void saveJbpmBudget(JbpmBudget jbpmBudget) {
		PowerUtils.setEmptyStr2Null(jbpmBudget);
		jbpmBudgetDao.save(jbpmBudget);
		for (JbpmBudgetDetail budgetDetail : jbpmBudget.getJbpmBudgetDetails()) {
			PowerUtils.setEmptyStr2Null(budgetDetail);
			jbpmBudgetDetailManager.saveJbpmBudgetDetail(budgetDetail);
		}
	}

	public void saveJbpmBudget(List<JbpmBudget> budgets) {
		for (JbpmBudget jbpmBudget : budgets) {
			saveJbpmBudget(jbpmBudget);
		}
	}

	/**
	 * 删除一条预算记录时，要把属于它的各个预算详情信息也同时删除
	 * 
	 * @param id
	 */
	public void deleteJbpmBudget(String id) {
		JbpmBudget jbpmBudget = jbpmBudgetDao.get(id);
		for (JbpmBudgetDetail budgetDetail : jbpmBudget.getJbpmBudgetDetails()) {
			jbpmBudgetDetailManager.delete(budgetDetail);
		}
		jbpmBudgetDao.delete(id);
	}

	/**
	 * 是否在预算内
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public boolean isInBudget(Date date, BigDecimal amount) {
		List<JbpmBudget> budgets = findInBudget(date, amount);
		return isInBudget(budgets, amount);
	}

	/**
	 * 是否在预算内
	 * 
	 * @param budgets
	 * @param amount
	 * @return
	 */
	private boolean isInBudget(List<JbpmBudget> budgets, BigDecimal amount) {
		boolean flag = false;
		BigDecimal allAmount = new BigDecimal(0);
		for (JbpmBudget jbpmBudget : budgets) {
			allAmount = allAmount.add(jbpmBudget.getBudgetAmount());
		}
		if (allAmount.compareTo(amount) >= 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 从预算中扣除金额
	 * 
	 * @param date
	 * @param amount
	 */
	public void reduceBudget(Date date, BigDecimal amount) {
		List<JbpmBudget> budgets = findInBudget(date, amount);
		List<JbpmBudget> lstModified = new ArrayList<JbpmBudget>();
		CollectionHelper.sortList(budgets, "budgetAmount", true);
		if (isInBudget(budgets, amount)) {
			BigDecimal reduce = amount;
			for (JbpmBudget jbpmBudget : budgets) {
				if (reduce.compareTo(BigDecimal.ZERO) > 0) {
					if (jbpmBudget.getBudgetAmount().compareTo(amount) > 0) {
						jbpmBudget.setBudgetAmount(jbpmBudget.getBudgetAmount().subtract(amount));
						reduce = BigDecimal.ZERO;
					} else {
						jbpmBudget.setBudgetAmount(BigDecimal.ZERO);
						reduce = amount.subtract(jbpmBudget.getBudgetAmount());
					}
					lstModified.add(jbpmBudget);
				} else {
					break;
				}
			}
			saveJbpmBudget(lstModified);
		}
	}

	/**
	 * 取得预算内记录
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	private List<JbpmBudget> findInBudget(Date date, BigDecimal amount) {
		short year = (short) DateOperator.getYear(date);
		short quart = (short) DateOperator.getQuarter(date);
		int[] month = DateOperator.getMonthsByQuarter(quart);
		BigDecimal[] monthes = new BigDecimal[month.length];
		for (int i = 0; i < month.length; i++) {
			monthes[i] =new BigDecimal(month[i]);
		}
		Criterion cYear = Restrictions.eq("budgetYear", new BigDecimal(year));
		Criterion cMonth = Restrictions.in("budgetMonth", monthes);
		Criterion budgetAmount = Restrictions.gt("budgetAmount", BigDecimal.ZERO);
		List<JbpmBudget> budgets = jbpmBudgetDao.find(cYear, cMonth, budgetAmount);
		return budgets;
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<JbpmBudget, String> getDao() {
		return jbpmBudgetDao;
	}

}

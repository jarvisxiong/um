/**
 * 
 */
package org.springside.modules.orm.hibernate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;

/**
 * @author huangj 2010-4-22
 */
public class CustomerOrder extends Order {
	protected CustomerOrder(String propertyName, boolean ascending) {
		super(propertyName, ascending);
	}

	private static final long serialVersionUID = 2549923278103801689L;

	private String orderStr;

	/**
	 * Render the SQL fragment
	 * 
	 */
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		return orderStr;
	}

	public static CustomerOrder getInstance(String orderStr) {
		CustomerOrder customerOrder = new CustomerOrder(null, false);
		customerOrder.setOrderStr(orderStr);
		return customerOrder;
	}

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

}

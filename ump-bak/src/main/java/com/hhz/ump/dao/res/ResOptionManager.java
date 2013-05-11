package com.hhz.ump.dao.res;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResOption;

@Service
@Transactional
public class ResOptionManager extends BaseService<ResOption, String> {
	@Autowired
	private ResOptionDao resOptionDao;

	public void saveResOption(ResOption resOption) {
		PowerUtils.setEmptyStr2Null(resOption);
		resOptionDao.save(resOption);
	}

	public void deleteResOption(String id) {
		resOptionDao.delete(id);
	}
	
	/***
	 * 网批选项表分页结果
	 * @param page
	 * @param opt
	 * @return
	 */
	public Page<ResOption> findPage(Page<ResOption> page, ResOption opt) {
		StringBuffer hql = new StringBuffer("from ResOption r where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		// 选项的名称
		if(StringUtils.isNotBlank(opt.getOptionName())) {
			hql.append(" and r.optionName like :optionName ");
			params.put("optionName", "%" + opt.getOptionName() + "%");
		}
		// 选项的属性名
		if(StringUtils.isNotBlank(opt.getOptionIdName())) {
			hql.append(" and r.optionIdName like :optionIdName ");
			params.put("optionIdName", "%" + opt.getOptionIdName() + "%");
		}
		// 选择的类型
		if(opt.getOptionType() != null) {
			hql.append(" and r.optionType = :optionType ");
			params.put("optionType", opt.getOptionType());
		}
		String[] orders = page.getOrderBy().split(",");
		if(orders.length > 0) {
			hql.append(" order by ");
		}
		for(int i=0; i<orders.length; i++) {
			if(i == orders.length - 1) {
				hql.append("r." + orders[i] + " " + page.getOrder().split(",")[i]);
			} else {
				hql.append("r." + orders[i] + " " + page.getOrder().split(",")[i] + ", ");
			}
		}
		return this.findPage(page, hql.toString(), params);
	}
	
	/***
	 * 根据ID获取网批选项(ResOption)
	 * @param id
	 * @return
	 */
	public ResOption getResOptionById(String id) {
		return resOptionDao.get(id);
	}
	
	@Override
	public HibernateDao<ResOption, String> getDao() {
		return resOptionDao;
	}
	
}


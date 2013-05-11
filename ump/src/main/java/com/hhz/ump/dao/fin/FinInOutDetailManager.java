package com.hhz.ump.dao.fin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.fin.FinInOutDetail;
import com.hhz.ump.entity.fin.FinProjectAcctRel;
import com.hhz.ump.util.FinDetailUtil;

@Service
@Transactional
public class FinInOutDetailManager extends BaseService<FinInOutDetail, String> {
	@Autowired
	private FinInOutDetailDao finInOutDetailDao;
	@Autowired
	private FinProjectAcctRelManager finProjectAcctRelManager;

	// FinInOutDetail Manager //
	@Transactional(readOnly = true)
	public FinInOutDetail getFinInOutDetail(String id) {
		return finInOutDetailDao.get(id);
	}

	public List<FinInOutDetail> getAllFinInOutDetail() {
		return finInOutDetailDao.getAll();
	}

	public void saveFinInOutDetail(FinInOutDetail finInOutDetail, String bankId, boolean isDelete, Date createdDate,BigDecimal oriInAmount,BigDecimal oriOutAmount,BigDecimal oriBalance) {
		//得到银行账号实体VO
		FinProjectAcctRel acctRel = finProjectAcctRelManager.getFinProjectAcctRel(bankId);
		finInOutDetail.setFinProjectAcctRel(acctRel);
		BigDecimal balance =acctRel.getBalance();
        BigDecimal difAmount = new BigDecimal(0);// 差额
        // 更新finProjectAcct的余额字段
        if (StringUtils.isNotBlank(finInOutDetail.getFinInOutDetailId())) {
	      // 修改
	      //FinInOutDetail detaVo = queryFinInOutDetail(finInOutDetail.getFinInOutDetailId());
	      //if(detaVo!=null){
	    	if(oriInAmount!=null&&oriOutAmount!=null){
	    		  difAmount =(oriOutAmount.subtract(finInOutDetail.getOutAmount()))
	    		  .add(finInOutDetail.getInAmount().subtract(oriInAmount));
	    		  finInOutDetail.setBalance(oriBalance.add(difAmount));
	    		  // 总金额更新
	    		  acctRel.setBalance(balance.add(difAmount));
	    	}
	      //}
       } else {
	      // 新增
	     if (finInOutDetail.getInAmount().compareTo(new BigDecimal(0)) != 0
			&& balance != null) {
		  finInOutDetail.setBalance(balance.add(finInOutDetail.getInAmount()));
		  acctRel.setBalance(balance.add(finInOutDetail.getInAmount()));
	     } else if (finInOutDetail.getOutAmount().compareTo(new BigDecimal(0)) != 0
			&& balance != null) {
		  finInOutDetail.setBalance(balance.subtract(finInOutDetail.getOutAmount()));
		  acctRel.setBalance(balance.subtract(finInOutDetail.getOutAmount()));
	     }
        }
		// 若找不到银行项目的ID则不要更新
		if (acctRel.getFinProjectAcctRelId() != null && !"".equals(acctRel.getFinProjectAcctRelId())) {
		if (!isDelete) {
			PowerUtils.setEmptyStr2Null(finInOutDetail);
			finInOutDetailDao.save(finInOutDetail);
		}
			finProjectAcctRelManager.saveFinProjectAcctRel(acctRel);
			// 更新其他字段差额
		if (StringUtils.isNotBlank(bankId)) {
			if (createdDate != null && difAmount.compareTo(new BigDecimal(0)) != 0) {
				List<FinInOutDetail> details = queryDetailByDate(createdDate, bankId);
				for (FinInOutDetail detail : details) {
					FinInOutDetail modDetail = getFinInOutDetail(detail.getFinInOutDetailId());
					if (modDetail != null) {
						modDetail.setBalance(detail.getBalance().add(difAmount));
						finInOutDetailDao.save(modDetail);
					}
				}
			}
		}
		}
	}

	public void updateBalance(String beginTime, String accId) {
		String sql = "select fin_in_out_detail_id,in_amount,out_amount,balance from fin_in_out_detail where fin_project_acct_rel_id='" + accId 
		+ "' and created_date>:beginTime order by created_date asc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginTime", DateOperator.parse(beginTime,"yyyy-MM-dd"));
		List detailList = finInOutDetailDao.findBySql(sql, params);
		if (detailList != null && detailList.size() > 0) {
			Object[] detail = (Object[]) detailList.get(0);
			BigDecimal totBalance = (BigDecimal) detail[3];
			for (int i = 1; i < detailList.size(); i++) {
				Object[] objDetail = (Object[]) detailList.get(i);
				String detailId = (String) objDetail[0];
				BigDecimal inAmount = (BigDecimal) objDetail[1];
				BigDecimal outAmount = (BigDecimal) objDetail[2];
				BigDecimal balance = totBalance.add(inAmount).subtract(outAmount);
				// 更新明细表里的余额
				String sqlUpdate = "update FinInOutDetail set balance= " + balance + " where finInOutDetailId='" + detailId + "'";
				getDao().batchExecute(sqlUpdate, params);
				String updateAcc = "update FinProjectAcctRel set balance =" + balance + " where finProjectAcctRelId='" + accId + "'";
				getDao().batchExecute(updateAcc, params);
				totBalance = balance;
			}
		}
	}

	public void deleteFinInOutDetail(String id, FinProjectAcctRel acctRel, String bankId, FinInOutDetail detailVo) {
        BigDecimal difAmount = detailVo.getOutAmount().subtract(detailVo.getInAmount());
		finInOutDetailDao.delete(id);
		acctRel.setBalance(acctRel.getBalance().add(difAmount));
		finProjectAcctRelManager.saveFinProjectAcctRel(acctRel);
		// 更新其他字段差额
		if (StringUtils.isNotBlank(bankId)) {
			if (detailVo.getCreatedDate() != null && difAmount.compareTo(new BigDecimal(0)) != 0) {
				List<FinInOutDetail> details = queryDetailByDate(detailVo.getCreatedDate(), bankId);
				for (FinInOutDetail detail : details) {
					FinInOutDetail modDetail = getFinInOutDetail(detail.getFinInOutDetailId());
					if (modDetail != null) {
						modDetail.setBalance(detail.getBalance().add(difAmount));
						finInOutDetailDao.save(modDetail);
					}
				}
			}
		}
	}

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<FinInOutDetail, String> getDao() {
		return finInOutDetailDao;
	}

	public List<FinProjectAcctRel> getBalance(String id) {
		String hql = "from " + FinProjectAcctRel.class.getSimpleName() + " where finProjectAcctRelId=?";
		List<FinProjectAcctRel> result = finInOutDetailDao.find(hql, id);
		return result;
	}

	public List<FinInOutDetail> queryDetailByDate(Date createDate, String bankId) {
		StringBuffer hql = new StringBuffer("select FIN_IN_OUT_DETAIL_ID,BALANCE from FIN_IN_OUT_DETAIL where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (createDate != null && !"".equals(createDate)) {
			hql.append(" AND CREATED_DATE>:createDate ");
			params.put("createDate", createDate);
		}

		if (bankId != null && !"".equals(bankId)) {
			hql.append(" and FIN_PROJECT_ACCT_REL_ID =:bankId");
			params.put("bankId", bankId);
		}
		List list = finInOutDetailDao.findBySql(hql.toString(), params);
		List<FinInOutDetail> result = new ArrayList<FinInOutDetail>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				FinInOutDetail detail = new FinInOutDetail();
				Object[] obj = (Object[]) list.get(i);
				detail.setFinInOutDetailId((String) obj[0]);
				detail.setBalance((BigDecimal) obj[1]);
				result.add(detail);
			}
		}
		return result;
	}

	public List<FinDetailUtil> queryDetailByPeri(String beginTime, String endTime, String bankId) {
		StringBuffer hql = new StringBuffer("select a.SUMMARY_DESC,b.FIN_ITEM_NAME,a.IN_AMOUNT,a.OUT_AMOUNT,a.BALANCE from FIN_IN_OUT_DETAIL a,FIN_ITEM b ");
		hql.append("where  a.FIN_ITEM_CD =b.FIN_ITEM_CD");
		hql.append(" AND a.CREATED_DATE>=:beginTime AND a.CREATED_DATE<=:endTime ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginTime", DateOperator.parse(beginTime,
				"yyyy-MM-dd HH:mm:ss"));
		params.put("endTime", DateOperator
				.parse(endTime, "yyyy-MM-dd HH:mm:ss"));
		if (bankId != null && !"".equals(bankId)) {
			hql.append(" and a.FIN_PROJECT_ACCT_REL_ID =:bankId");
			params.put("bankId", bankId);
		}
		hql.append(" order by a.CREATED_DATE asc,a.FIN_IN_OUT_DETAIL_ID asc ");
		List list = finInOutDetailDao.findBySql(hql.toString(), params);
		List<FinDetailUtil> result = new ArrayList<FinDetailUtil>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				FinDetailUtil detail = new FinDetailUtil();
				Object[] obj = (Object[]) list.get(i);
				detail.setSummaryDesc((String) obj[0]);
				detail.setFinItemName((String) obj[1]);
				detail.setInAmount((BigDecimal) obj[2]);
				detail.setOutAmount((BigDecimal) obj[3]);
				detail.setBalance((BigDecimal) obj[4]);
				result.add(detail);
			}
		}
		return result;
	}
}


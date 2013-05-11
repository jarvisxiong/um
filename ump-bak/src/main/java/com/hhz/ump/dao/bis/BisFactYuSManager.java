package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisFact;
import com.hhz.ump.entity.bis.BisFactYuS;
import com.hhz.ump.entity.bis.BisTenant;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.web.vo.VoFact;
import com.hhz.ump.web.vo.VoFactOpera;

@Service
@Transactional
public class BisFactYuSManager extends BaseService<BisFactYuS, String> {
    @Autowired
    private BisFactYuSDao bisFactYuSDao;
    @Autowired
    private BisTenantManager bisTenantManager;
    @Autowired
    private BisFactManager bisFactManager;

    public void saveBisFactYuS(BisFactYuS bisFactYuS) {
        PowerUtils.setEmptyStr2Null(bisFactYuS);
        bisFactYuSDao.save(bisFactYuS);
    }

    public void deleteBisFactYuS(String id) {
        bisFactYuSDao.delete(id);
    }

    @Override
    public HibernateDao<BisFactYuS, String> getDao() {
        return bisFactYuSDao;
    }

    public Page<BisFactYuS> getFactYuSList(Page voPage, VoFact voFact) {
        StringBuffer sql = new StringBuffer();
        sql.append(" 	 from BisFactYuS s ")
        //.append(" 	where s.bisProjectId:= bisProjectId")
        ;
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("bisProjectId", voFact.getBisProjectId());
        return findPage(voPage, sql.toString(), values);
    }

    public void factRemove(String uiid, String bisFactId) {
        factOpera(uiid, getEntity(bisFactId), BisFactManager.BIS_FACT_REMOVE);
    }

    public void factRemove(String uiid, BisFactYuS fact) {
        factOpera(uiid, fact, BisFactManager.BIS_FACT_REMOVE);
    }

    public void factUpdate(String uiid, String bisFactId) {
        factOpera(uiid, getEntity(bisFactId), BisFactManager.BIS_FACT_MODIFY);
    }

    public void factPass(String uiid, String bisFactId) {
        factOpera(uiid, getEntity(bisFactId), BisFactManager.BIS_FACT_CHECK_PASS);
    }

    public void factReject(String uiid, String bisFactId) {
        factOpera(uiid, getEntity(bisFactId), BisFactManager.BIS_FACT_CHECK_REJECT);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void factOpera(String uiid, BisFactYuS fact, int opera) {
        factOpera(uiid, fact, opera, "");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void factOpera(String uiid, BisFactYuS fact, int opera,
                          String bisProjectId) {
        StringBuffer operaLog = new StringBuffer();
        VoFactOpera check = null;
        switch (opera) {
            case BisFactManager.BIS_FACT_CHECK_PASS: {
                fact.setStatusCd(DictContants.BIS_FACT_PASS);
                fact.setCheckUserCd(uiid);
                fact.setCheckDate(DateUtil.getCurrtDate());
                operaLog.append("[实收记录编号：").append(fact.getBisFactId())
                        .append("  审核通过]");
                break;
            }
            case BisFactManager.BIS_FACT_CHECK_REJECT: {
                fact.setStatusCd(DictContants.BIS_FACT_REJECT);
                fact.setCheckUserCd(uiid);
                fact.setCheckDate(DateUtil.getCurrtDate());
                operaLog.append("[实收记录编号：").append(fact.getBisFactId())
                        .append("  审核驳回]");
                break;
            }
            case BisFactManager.BIS_FACT_CHECK_NO: {
                BisTenant tenant = bisTenantManager
                        .getEntity(fact.getBisTenantId());
                operaLog.append("[实收租户(").append(tenant.getConnName())
                        .append(")金额人民币:").append(fact.getMoney()).append("]");
                break;
            }
            case BisFactManager.BIS_FACT_REMOVE: {// 删除：校验是否有权限，是否已审核
                operaLog.append("删除实收记录[").append("录入人：").append(fact.getCreator())
                        .append(",录入时间:").append(fact.getFactDate())
                        .append(",录入对应合同:").append(fact.getBisContId())
                        .append(",金额:").append(fact.getMoney()).append("]");
                this.delete(fact);
                return;
            }
            case BisFactManager.BIS_FACT_MODIFY: {// 修改：校验是否有权限，是否已审核
                operaLog.append("修改实收记录[").append("录入人：").append(fact.getCreator())
                        .append(",录入时间:").append(fact.getFactDate())
                        .append(",录入对应合同:").append(fact.getBisContId())
                        .append(",金额:").append(fact.getMoney()).append("]");
                break;
            }
        }
        saveBisFactYuS(fact);
        operaLog.append(" 操作人：").append(uiid);
        // 记录操作日志
    }

    public Page findPageSql(final Page voPage, final String sql,
                            final Map<String, Object> values) {
        Assert.notNull(voPage, "page不能为空");
        Query q = this.getDao().createSQLQuery(sql, values);
        if (voPage.isAutoCount()) {
            long totalCount = countSqlResult(sql, values);
            voPage.setTotalCount(totalCount);
        }

        setPageParameter(q, voPage);

        List result = q.list();
        voPage.setResult(result);
        return voPage;
    }

    @Override
    public long countSqlResult(final String sql,
                               final Map<String, Object> values) {
        String fromSql = sql;
        // select子句与order by子句会影响count搜索,进行简单的排除.
        fromSql = sql.substring(sql.indexOf("from"), sql.length());
        int orderIndex = fromSql.lastIndexOf("order");
        if (orderIndex > 0) {
            // fromSql = StringUtils.substringBefore(fromSql, "order by");
            fromSql = fromSql.substring(0, orderIndex);
        }
        String countSql = "select count(*) " + fromSql;

        try {
            BigDecimal count = (BigDecimal) this.getDao()
                    .createSQLQuery(countSql, values).uniqueResult();
            return count.longValue();
        } catch (Exception e) {
            throw new RuntimeException("hql can't be auto count, sql is:"
                    + countSql, e);
        }
    }

    /**
     * 设置分页参数到Query对象,辅助函数.
     */
    protected Query setPageParameter(final Query q, final Page page) {
        // hibernate的firstResult的序号从0开始
        q.setFirstResult(page.getFirst() - 1);
        q.setMaxResults(page.getPageSize());
        return q;
    }

    /**
     * 抵充方法
     */
    public void dichong(BisFactYuS bisFactYuS) {
        //可以用金额
        BigDecimal money = bisFactYuS.getMoney();
        //使用金额
        BigDecimal useMoney = new BigDecimal(Struts2Utils.getParameter("useMoney"));
        bisFactYuS.setSurplusMoney(money.subtract(useMoney));
        bisFactYuSDao.save(bisFactYuS);
        
        BisFact bisFact = new BisFact();
        bisFact.setBisProjectId(bisFactYuS.getBisProjectId());
        bisFact.setFactDate(bisFact.getFactDate());
        bisFact.setStatusCd(DictContants.BIS_FACT_PASS);
        bisFact.setFactYear(Struts2Utils.getParameter("factYear"));
        bisFact.setFactMonth(Struts2Utils.getParameter("factMonth"));
        bisFact.setChargeTypeCd(Struts2Utils.getParameter("chargeTypeCd"));
        bisFact.setFactDate(new Date());
        bisFact.setMoney(useMoney);
        bisFact.setRemark(Struts2Utils.getParameter("remarks"));
        bisFact.setBisContId(bisFactYuS.getBisContId());
        bisFact.setBisTenantId(bisFactYuS.getBisTenantId());

        if (StringUtils.isNotBlank(bisFactYuS.getBisTenantId())) {
            String sbisStoreId = "";
            for (String store : bisFactYuS.getBisTenantId().split("-")) {
                sbisStoreId += store;
            }
            bisFact.setBisStoreId(sbisStoreId);
        }
        bisFact.setBisFactYuSId(bisFactYuS.getBisFactId());
        
        // 如果实收记录已审核，不允许修改
        bisFactManager.factInput2Store(SpringSecurityUtils.getCurrentUiid(), bisFact);

    }
    
    
    /**
	 * 描述：根据条件批量更新处理(预收明细)
	 * @author wangming 2012-06-13
	 * @param contSubTargAmt
	 */
	public void updateBatchProcessing(String bisFactId,BigDecimal surplusMoney){
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		if(StringUtils.isNotBlank(bisFactId)){
			sb.append(" UPDATE BIS_FACT_YU_S t ");
			sb.append(" SET t.SURPLUS_MONEY = :surplusMoney ");
			map.put("surplusMoney", surplusMoney);
			sb.append(" where t.BIS_FACT_ID = :bisFactId ");
			map.put("bisFactId", bisFactId);
		}
		Query query=bisFactYuSDao.createSQLQuery(sb.toString(), map);
		query.executeUpdate();
	}
	
	/**
	 * 获取所有相关预收历史明细
	 * @param bisProjectId
	 * @return
	 */
	public List<BisFactYuS> getBisFactYuSList(String bisProjectId){
		Map<String, Object> values = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder();
		if(StringUtils.isNotBlank(bisProjectId)){
			hql.append(" from BisFactYuS fc where fc.bisProjectId = :bisProjectId");
		}
		values.put("bisProjectId",bisProjectId);
		List<BisFactYuS> bisFactYuSList = bisFactYuSDao.find(hql.toString(), values);
		if(null != bisFactYuSList&&bisFactYuSList.size()>0)
			return bisFactYuSList;
		return null;
	}
}


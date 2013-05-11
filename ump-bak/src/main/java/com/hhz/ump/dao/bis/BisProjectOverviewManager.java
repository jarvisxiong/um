package com.hhz.ump.dao.bis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisProjectOverview;

@Service
@Transactional
public class BisProjectOverviewManager extends BaseService<BisProjectOverview, String> {
	
	private static Log log = LogFactory.getLog(BisProjectOverviewManager.class);
	
	@Autowired
	private BisProjectOverviewDao bisProjectOverviewDao;

	public void saveBisProjectOverview(BisProjectOverview bisProjectOverview) {
		PowerUtils.setEmptyStr2Null(bisProjectOverview);
		bisProjectOverviewDao.save(bisProjectOverview);
	}

	public void deleteBisProjectOverview(String id) {
		bisProjectOverviewDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisProjectOverview, String> getDao() {
		return bisProjectOverviewDao;
	}
	
	@Transactional(readOnly = true)
	public List<BisProjectOverview> getOverviewList(String bisProjectId, String reportDate) {
		
		String hql = "from BisProjectOverview where bisProjectId=? and to_char(reportMonth,'yyyy-MM')=? order by sequenceNo,bisFloorId";
		return getDao().find(hql, bisProjectId, reportDate);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Object[]> getRegionOverviewList(String bisProjectId, String startDate, String endDate) {
		
		StringBuffer sql = new StringBuffer();
		sql .append(" select t.data_type,t.bis_floor_id,round(avg(t.rent_rate),2),round(avg(t.rent_unit_price),2),")
			.append(" round(avg(t.prop_leave),2),round(avg(t.prop_sell),2),round(avg(t.prop_unit_price),2),")
			.append(" round(avg(t.back_rate),2),round(avg(t.back_unit_price),2),round(avg(t.back_pay_balance),2),")
			.append(" round(avg(t.rent_collect_rate),2),round(avg(t.energy_collect_rate),2),round(avg(t.prop_collect_rate),2),")
			.append(" round(avg(t.store_count),0)")
			.append(" from bis_project_overview t")
			.append(" where t.bis_project_id=:bisProjectId")
			.append(" and t.report_month >= to_date(:startDate,'yyyy-MM') and t.report_month <= to_date(:endDate,'yyyy-MM')")
			.append(" group by t.data_type,t.bis_floor_id");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		return getDao().findBySql(sql.toString(), param);
	}
	
	/**
	 * 更新总况信息
	 */
	public void refreshData(String reportMonth, String bisProjectId) {
		
		@SuppressWarnings("deprecation")
		Connection conn = getDao().getSession().connection();
		try {
			CallableStatement callableStatement = conn.prepareCall("{ call bis_project_overview_stat(?,?)}");
			callableStatement.setString(1, reportMonth);
			callableStatement.setString(2, bisProjectId);
			callableStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	/**
	 * 定时调用(每天1:00)
	 */
	public void runOverviewStat() {
		
		try {
			long l1 = System.currentTimeMillis();
			refreshData(null, null);
			long l2 = System.currentTimeMillis();
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> 商业系统：统计总况信息 用时：" + (l2-l1)/1000.00 +" 秒!");
		} catch(Exception e) {
			log.error("/////////////////////////// 商业系统：统计总况信息 exception! " + e.getCause());
		}
	}
	
}


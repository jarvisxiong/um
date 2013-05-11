package com.hhz.ump.dao.bis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisReport;

@Service
@Transactional
public class BisReportManager extends BaseService<BisReport, String> {
	
	private static Log log = LogFactory.getLog(BisReportManager.class);
	
	@Autowired
	private BisReportDao bisReportDao;

	public void saveBisReport(BisReport bisReport) {
		PowerUtils.setEmptyStr2Null(bisReport);
		bisReportDao.save(bisReport);
	}

	public void deleteBisReport(String id) {
		bisReportDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisReport, String> getDao() {
		return bisReportDao;
	}
	
	/**
	 * 更新报表信息
	 */
	public void refreshData(String reportMonth) {
		
		log.info("商业系统：手动更新报表数据");
		@SuppressWarnings("deprecation")
		Connection conn = getDao().getSession().connection();
		try {
			CallableStatement callableStatement = conn.prepareCall("{ call bis_report_pkg.bis_report_stat(?)}");
			callableStatement.setString(1, reportMonth);
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
	 * 定时调用(每天23:00运行)
	 */
	public void runReportStat() {
		
		try {
			long l1 = System.currentTimeMillis();
			refreshData(null);
			long l2 = System.currentTimeMillis();
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> 商业系统：统计报表 用时：" + (l2-l1)/1000.00 +" 秒!");
		} catch(Exception e) {
			log.error("/////////////////////////// 商业系统：统计报表 exception! " + e.getCause());
		}
	}
	
}


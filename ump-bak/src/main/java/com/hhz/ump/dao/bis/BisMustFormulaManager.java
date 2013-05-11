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
import com.hhz.ump.entity.bis.BisMustFormula;

@Service
@Transactional
public class BisMustFormulaManager extends BaseService<BisMustFormula, String> {
	
	private static Log log = LogFactory.getLog(BisMustFormulaManager.class);
	
	@Autowired
	private BisMustFormulaDao bisMustFormulaDao;
	
	public void saveBisMustFormula(BisMustFormula bisMustFormula) {
		PowerUtils.setEmptyStr2Null(bisMustFormula);
		bisMustFormulaDao.save(bisMustFormula);
	}

	public void deleteBisMustFormula(String id) {
		bisMustFormulaDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisMustFormula, String> getDao() {
		return bisMustFormulaDao;
	}
	
	/**
	 * 定时调用(每月5号5:00)
	 */
	public void runCreateMustAuto() {
		
		try {
			long l1 = System.currentTimeMillis();
			
			@SuppressWarnings("deprecation")
			Connection conn = getDao().getSession().connection();
			try {
				CallableStatement callableStatement = conn.prepareCall("{ CALL BIS_MUST_AUTO_GENERATE }");
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
			
			long l2 = System.currentTimeMillis();
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> 商业系统：自动生成应收用时：" + (l2-l1)/1000.00 +" 秒!");
		} catch(Exception e) {
			log.error("/////////////////////////// 商业系统：自动生成应收 exception! " + e.getCause());
		}
	}
	
}


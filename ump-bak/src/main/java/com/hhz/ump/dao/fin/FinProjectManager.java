package com.hhz.ump.dao.fin;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.fin.FinProject;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.FinDetailUtil;
import com.hhz.ump.util.FinProjectUtil;

@Service
@Transactional
public class FinProjectManager extends BaseService<FinProject, String> {
	@Autowired
	private FinProjectDao finProjectDao;
	@Autowired
	private FinProjectAcctRelManager finProjectAcctRelManager;

	// FinProject Manager //
	@Transactional(readOnly = true)
	public FinProject getFinProject(String id) {
		return finProjectDao.get(id);
	}
	
	public List<FinProject> getAllFinProject() {
		return finProjectDao.getAll();
	}

	public List<FinProject> getFinProjectsByUserCd (String userCd) {
		if(StringUtils.isEmpty(userCd))
			return null;
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		filters.add(new PropertyFilter(
				"LIKES_userCds", "," + userCd + ","));
		return this.find(filters);
	}

	public void saveFinProject(FinProject finProject) {
		PowerUtils.setEmptyStr2Null(finProject);
		finProjectDao.save(finProject);
	}

	public void batchSaveFinProject(String userProject, String userCd) {
		List<FinProject> projects = getAllFinProject();
		for (FinProject project : projects) {
			if (userProject.indexOf("," + project.getProjectCd().trim() + ",") >= 0) {
				// 有该项目的数据权限
				if (project.getUserCds() == null || "".equals(project.getUserCds())) {
					// 添加
					project.setUserCds("," + userCd + ",");
					saveFinProject(project);
				} else if (project.getUserCds().indexOf(","+userCd + ",") < 0) {//modify by huangbijin 2012-04-01 谢华(userCd:166),已授权 员工(4166)，发现bug 前者授权不成功 
					// 添加
					project.setUserCds(project.getUserCds() + userCd + ",");
					saveFinProject(project);
				}

			} else {
				// 无该项目的数据权限
				if (project.getUserCds() != null && project.getUserCds().indexOf(userCd + ",") >= 0) {
					// 若原先有数据，则删除
					project.setUserCds(project.getUserCds().replace(userCd + ",", ""));
					saveFinProject(project);
				}

			}
		}
	}
	public void batchSave(List<FinProject> projectList){
		for(FinProject fin:projectList){
			saveFinProject(fin);
		}
	}

	public void deleteFinProject(String id) {
		finProjectDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<FinProject, String> getDao() {
		return finProjectDao;
	}

	public List<FinProject> getProject(String userCd, String projectCd, boolean notCompany, String publicFlg) {
		StringBuffer hql = new StringBuffer("from " + FinProject.class.getSimpleName() + " where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		if (userCd != null && !"".equals(userCd)) {
			hql.append(" and userCds like :userCd ");
			params.put("userCd", "%," + userCd + ",%");
		}
		if (projectCd != null && !"".equals(projectCd)) {
			hql.append(" and projectCd =:projectCd ");
			params.put("projectCd", projectCd);
		}
		if (notCompany) {
			hql.append(" and projectCd !='2000' ");
		}
		if (publicFlg != null) {
			if ("true".equals(publicFlg)) {
				hql.append(" and publicFlg ='true' ");
			} else {
				hql.append(" and (publicFlg is null or publicFlg ='false') ");
			}
		}
		hql.append(" and deleteFlg ='0'");
		hql.append(" order by sequenceNo asc");
		List<FinProject> result = this.find(hql.toString(), params);
		return result;
	}

	public List<FinProjectUtil> getUserProject(String beginTime, String endTime, String userCd) {
		StringBuffer hql = new StringBuffer(
				"select  t1.PROJECT_CD,t1.PROJECT_NAME,"
						+ "(t1.moveEable - (case when t2.op_moveEnable_inAmount is null then 0 else t2.op_moveEnable_inAmount end) "
						+ "+ (case when t2.op_moveEnable_outAmount is null then 0 else t2.op_moveEnable_outAmount end)) as moveEable,"
						+ "(t1.moveDisable - (case when t2.op_moveDisable_inAmount is null then 0 else t2.op_moveDisable_inAmount end) "
						+ "+ (case when t2.op_moveDisable_outAmount is null then 0 else t2.op_moveDisable_outAmount end)) as moveDisable,"
						+ "(case when t3.moveEnable_inAmount+t3.moveDisable_inAmount is null then 0 else t3.moveEnable_inAmount+t3.moveDisable_inAmount end) "
						+ "as moveEnable_inAmount,(case when t3.moveEnable_outAmount+t3.moveDisable_outAmount is null then 0 else t3.moveEnable_outAmount+t3.moveDisable_outAmount end) "
						+ "as moveEnable_outAmount,t1.STATUS_CD,t1.PUBLIC_FLG from( "
						+ "select a.PROJECT_CD,a.PROJECT_NAME,a.STATUS_CD,"
						+ "sum( case when b.enable_flg = 1 then b.balance else 0 end ) as moveEable,"
						+ "sum( case when b.enable_flg = 2 then b.balance else 0 end ) as moveDisable,a.SEQUENCE_NO,a.PUBLIC_FLG  "
						+ "from FIN_PROJECT a LEFT JOIN FIN_PROJECT_ACCT_REL b ON a.PROJECT_CD =b.PROJECT_CD "
						+ "where a.delete_flg='0' and a.USER_CDS like :userCd "
						+ "group by a.PROJECT_CD,a.PROJECT_NAME,a.STATUS_CD,a.SEQUENCE_NO,a.PUBLIC_FLG)t1 left join("
						+ "	select   b.PROJECT_CD,"
						+ "sum(case when b.enable_flg = 1 then c.in_amount  else 0 end ) as op_moveEnable_inAmount,"
						+ "sum(case when b.enable_flg = 2 then c.in_amount  else 0 end ) as op_moveDisable_inAmount,"
						+ "sum(case when b.enable_flg = 1 then c.out_amount else 0 end ) as op_moveEnable_outAmount,"
						+ "sum(case when b.enable_flg = 2 then c.out_amount else 0 end ) as op_moveDisable_outAmount "
						+ "from     FIN_PROJECT_ACCT_REL b ,FIN_IN_OUT_DETAIL c "
						+ "where 	 c.created_date > :endTime  "
						+ "and b.FIN_PROJECT_ACCT_REL_id = c.FIN_PROJECT_ACCT_REL_id "
						+ "group by b.PROJECT_CD)t2 on t1.PROJECT_CD = t2.PROJECT_CD "
						+ "left join( select   b.PROJECT_CD,"
						+ "sum(case when b.enable_flg = 1 then c.in_amount  else 0 end ) as moveEnable_inAmount,"
						+ "sum(case when b.enable_flg = 2 then c.in_amount  else 0 end ) as moveDisable_inAmount,"
						+ "sum(case when b.enable_flg = 1 then c.out_amount else 0 end ) as moveEnable_outAmount,"
						+ "sum(case when b.enable_flg = 2 then c.out_amount else 0 end ) as moveDisable_outAmount "
						+ "from  FIN_PROJECT_ACCT_REL b ,FIN_IN_OUT_DETAIL c "
						+ "where c.created_date >= :beginTime and c.created_date <= :endTime "
						+ " and b.FIN_PROJECT_ACCT_REL_id = c.FIN_PROJECT_ACCT_REL_id "
						+ "group by b.PROJECT_CD)t3 on t1.PROJECT_CD = t3.PROJECT_CD ORDER BY t1.SEQUENCE_NO");
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("beginTime", DateOperator.parse(beginTime, "yyyy-MM-dd HH:mm:ss"));
		params.put("endTime", DateOperator.parse(endTime, "yyyy-MM-dd HH:mm:ss"));
		params.put("userCd", "%," + userCd + ",%");
		List list = finProjectDao.findBySql(hql.toString(), params);
		List<FinProjectUtil> projectList = new ArrayList<FinProjectUtil>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				FinProjectUtil projectUtil = new FinProjectUtil();
				Object[] obj = (Object[]) list.get(i);
				projectUtil.setFinProjectCd((String) obj[0]);
				projectUtil.setProjectName((String) obj[1]);
				projectUtil.setUseableAmount((BigDecimal) obj[2]);
				projectUtil.setUnUseableAmount((BigDecimal) obj[3]);
				projectUtil.setInAmount((BigDecimal) obj[4]);
				projectUtil.setOutAmount((BigDecimal) obj[5]);
				projectUtil.setStatusCd((String) obj[6]);
				projectUtil.setPublicFlg((String) obj[7]);
				projectList.add(projectUtil);
			}
		}
		return projectList;
	}

	public List<FinProjectUtil> getFinanceTot(String beginTime, String endTime, String userCd, boolean haveAccCd,
			String publicFlg, String curType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("select ");
		sql.append("(t1.moveEable - (case when t2.op_moveEnable_inAmount is null then 0 else t2.op_moveEnable_inAmount end)");
		sql.append("+ (case when t2.op_moveEnable_outAmount is null then 0 else t2.op_moveEnable_outAmount end)) as moveEable,");
		sql.append("(t1.moveDisable - (case when t2.op_moveDisable_inAmount is null then 0 else t2.op_moveDisable_inAmount end)");
		sql.append("+ (case when t2.op_moveDisable_outAmount is null then 0 else t2.op_moveDisable_outAmount end)) as moveDisable,");
		sql.append("(case when t3.moveEnable_inAmount+t3.moveDisable_inAmount is null then 0 else t3.moveEnable_inAmount+t3.moveDisable_inAmount end) as moveEnable_inAmount,");
		sql.append("(case when t3.moveEnable_outAmount+t3.moveDisable_outAmount is null then 0 else t3.moveEnable_outAmount+t3.moveDisable_outAmount end) as moveEnable_outAmount,");
		sql.append("t1.STATUS_CD,t1.CURRENCY_CD,");
		if (haveAccCd) {
			sql.append("t1.ACCOUNT_TYPE_CD,");
		}
		sql.append("t1.PROJECT_CD,t1.PROJECT_NAME from(");
		sql.append("select a.PROJECT_CD,a.PROJECT_NAME,a.STATUS_CD,sum( case when b.enable_flg = 1 ");
		sql.append("then b.balance else 0 end ) as moveEable,");
		sql.append("sum( case when b.enable_flg = 2 then b.balance else 0 end ) as moveDisable,b.CURRENCY_CD");
		if (haveAccCd) {
			sql.append(",b.ACCOUNT_TYPE_CD");
		}
		sql.append(",a.SEQUENCE_NO from FIN_PROJECT a LEFT JOIN FIN_PROJECT_ACCT_REL b ON a.PROJECT_CD =b.PROJECT_CD");
		sql.append(" where a.delete_flg='0' ");
		if (userCd != null && !"".equals(userCd)) {
			sql.append("and a.USER_CDS like :userCd");
			params.put("userCd", "%," + userCd + ",%");
			// 导出报表时不要算projectCd=2000的数据
			// sql.append(" and a.PROJECT_CD!='2000' ");
		}
		// 如果限定报表的币种，则只查有报表的币种
		if (curType != null && !"".equals(curType)) {
			sql.append(" and b.CURRENCY_CD='" + curType + "'");
		}
		if (publicFlg != null) {
			if ("true".equals(publicFlg)) {
				sql.append(" and a.PUBLIC_FLG ='true' ");
			} else {
				sql.append(" and (a.PUBLIC_FLG is null or a.PUBLIC_FLG ='false') ");
			}
		}
		sql.append(" group by a.PROJECT_CD,a.PROJECT_NAME,a.STATUS_CD,b.CURRENCY_CD ");
		if (haveAccCd) {
			sql.append(",b.ACCOUNT_TYPE_CD");
		}
		sql.append(",a.SEQUENCE_NO) t1 ");
		sql.append("left join(	select b.PROJECT_CD,b.CURRENCY_CD,");
		if (haveAccCd) {
			sql.append("b.ACCOUNT_TYPE_CD,");
		}
		sql.append("sum(case when b.enable_flg = 1 then c.in_amount  else 0 end ) as op_moveEnable_inAmount,");
		sql.append("sum(case when b.enable_flg = 2 then c.in_amount  else 0 end ) as op_moveDisable_inAmount,");
		sql.append("sum(case when b.enable_flg = 1 then c.out_amount else 0 end ) as op_moveEnable_outAmount,");
		sql.append("sum(case when b.enable_flg = 2 then c.out_amount else 0 end ) as op_moveDisable_outAmount ");
		sql.append("from     FIN_PROJECT_ACCT_REL b ,FIN_IN_OUT_DETAIL c where 1=1 ");
		if (endTime != null && !"".equals(endTime)) {
			sql.append("and c.created_date > :endTime ");
			params.put("endTime", DateOperator.parse(endTime, "yyyy-MM-dd HH:mm:ss"));
		}

		sql.append(" and b.FIN_PROJECT_ACCT_REL_id = c.FIN_PROJECT_ACCT_REL_id ");
		sql.append("group by b.PROJECT_CD,b.CURRENCY_CD");
		if (haveAccCd) {
			sql.append(",b.ACCOUNT_TYPE_CD");
		}
		sql.append(")t2 ");
		sql.append("on t1.PROJECT_CD = t2.PROJECT_CD ");
		sql.append("and t1.CURRENCY_CD=t2.CURRENCY_CD ");
		if (haveAccCd) {
			sql.append(" and t1.ACCOUNT_TYPE_CD =t2.ACCOUNT_TYPE_CD ");
		}
		sql.append("left join( ");
		sql.append("select   b.PROJECT_CD,b.CURRENCY_CD,");
		if (haveAccCd) {
			sql.append("b.ACCOUNT_TYPE_CD,");
		}
		sql.append("sum(case when b.enable_flg = 1 then c.in_amount  else 0 end ) as moveEnable_inAmount,");
		sql.append("sum(case when b.enable_flg = 2 then c.in_amount  else 0 end ) as moveDisable_inAmount,");
		sql.append("sum(case when b.enable_flg = 1 then c.out_amount else 0 end ) as moveEnable_outAmount,");
		sql.append("sum(case when b.enable_flg = 2 then c.out_amount else 0 end ) as moveDisable_outAmount ");
		sql.append("from  FIN_PROJECT_ACCT_REL b ,FIN_IN_OUT_DETAIL c where 1=1 ");
		if (beginTime != null && !"".equals(beginTime)) {
			sql.append(" and c.created_date >= :beginTime");
			params.put("beginTime", DateOperator.parse(beginTime, "yyyy-MM-dd HH:mm:ss"));

		}
		if (endTime != null && !"".equals(endTime)) {
			sql.append(" and c.created_date <= :endTime ");
			params.put("endTime", DateOperator.parse(endTime, "yyyy-MM-dd HH:mm:ss"));
		}
		sql.append("and b.FIN_PROJECT_ACCT_REL_id = c.FIN_PROJECT_ACCT_REL_id ");
		sql.append("group by b.PROJECT_CD,b.CURRENCY_CD");
		if (haveAccCd) {
			sql.append(",b.ACCOUNT_TYPE_CD ");
		}
		sql.append(")t3 on ");
		sql.append("t1.PROJECT_CD = t3.PROJECT_CD and t1.CURRENCY_CD =t3.CURRENCY_CD");
		if (haveAccCd) {
			sql.append(" and t1.ACCOUNT_TYPE_CD =t3.ACCOUNT_TYPE_CD");
		}
		sql.append(" order by t1.SEQUENCE_NO asc,t1.PROJECT_CD asc,t1.CURRENCY_CD desc ");
		if (haveAccCd) {
			sql.append(",t1.ACCOUNT_TYPE_CD asc ");
		}
		List list = finProjectDao.findBySql(sql.toString(), params);
		List<FinProjectUtil> projectList = new ArrayList<FinProjectUtil>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				FinProjectUtil projectUtil = new FinProjectUtil();
				Object[] obj = (Object[]) list.get(i);

				projectUtil.setUseableAmount((BigDecimal) obj[0]);
				projectUtil.setUnUseableAmount((BigDecimal) obj[1]);
				projectUtil.setInAmount((BigDecimal) obj[2]);
				projectUtil.setOutAmount((BigDecimal) obj[3]);
				projectUtil.setStatusCd((String) obj[4]);
				projectUtil.setCurrencyCd((String) obj[5]);
				if (!haveAccCd) {
					projectUtil.setFinProjectCd((String) obj[6]);
					projectUtil.setProjectName((String) obj[7]);
				} else {
					projectUtil.setAccTypeCd((String) obj[6]);
					projectUtil.setFinProjectCd((String) obj[7]);
					projectUtil.setProjectName((String) obj[8]);
				}
				projectList.add(projectUtil);
			}
		}
		return projectList;
	}

	public List getFinSimple(String begin, String end, String weekBegin, String weekEnd, String monthBegin,
			String monthEnd) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("select A.PROJECT_CD,A.CURRENCY_CD,");
		sql.append("sum(CASE WHEN T.IN_MONEY IS NULL THEN 0 ELSE T.IN_MONEY END) AS IN_MONEY,");
		sql.append("sum(CASE WHEN T.OUT_MONEY IS NULL THEN 0 ELSE T.OUT_MONEY END) AS OUT_MONEY,");
		sql.append("sum(CASE WHEN T.WEEK_SUM IS NULL THEN 0 ELSE T.WEEK_SUM END) AS WEEK_SUM,");
		sql.append("sum(CASE WHEN T.MONTHINMON IS NULL THEN 0 ELSE T.MONTHINMON END) AS MONTHINMON,");
		sql.append("sum(CASE WHEN T.MONTHOUTMON IS NULL THEN 0 ELSE T.MONTHOUTMON END) AS MONTHOUTMON");
		sql.append(" from FIN_PROJECT_ACCT_REL A LEFT JOIN(");
		sql.append("SELECT C.FIN_PROJECT_ACCT_REL_ID,");
		sql.append("sum(case when c.IN_AMOUNT is NOT NULL AND ");
		sql.append("c.CREATED_DATE>= ").append(" to_date('" + begin + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ");
		// DateOperator.parse(begin + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		sql.append(" AND c.CREATED_DATE<= ").append(" to_date('" + end + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ")
		// DateOperator.parse(end + " 00:00:00", "yyyy-MM-dd HH:mm:ss"))
				.append(" then c.IN_AMOUNT else 0 end)as IN_MONEY,");
		sql.append("sum(case when c.OUT_AMOUNT is NOT NULL ");
		sql.append("and c.CREATED_DATE>= " + " to_date('" + begin + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') "
		// DateOperator.parse(begin + " 00:00:00", "yyyy-MM-dd HH:mm:ss")
				+ " AND c.CREATED_DATE<= " + " to_date('" + end + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ");
		// + DateOperator.parse(end + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		sql.append(" then c.OUT_AMOUNT else 0 end)as OUT_MONEY,");
		sql.append("sum(case when c.OUT_AMOUNT is NOT NULL ");
		sql.append("and c.CREATED_DATE>=");
		sql.append(" to_date('" + weekBegin + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ");
		// + DateOperator.parse(weekBegin + " 00:00:00", "yyyy-MM-dd HH:mm:ss")
		sql.append(" and c.CREATED_DATE<=");
		sql.append(" to_date('" + weekEnd + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ");
		// + DateOperator.parse(weekEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss")
		sql.append(" then c.OUT_AMOUNT else 0 end)as WEEK_SUM,");
		sql.append("sum(case when c.IN_AMOUNT is NOT NULL ");
		sql.append("AND c.CREATED_DATE>= ");
		sql.append(" to_date('" + monthBegin + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ")
				// .append(DateOperator.parse(monthBegin + " 00:00:00",
				// "yyyy-MM-dd HH:mm:ss"))
				.append(" AND c.CREATED_DATE<= ")
				.append(" to_date('" + monthEnd + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ");
		// DateOperator.parse(monthEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		sql.append(" then c.IN_AMOUNT else 0 end)as MONTHINMON,");
		sql.append("sum(case when c.OUT_AMOUNT is NOT NULL ");
		sql.append("AND c.CREATED_DATE>= ").append(" to_date('" + monthBegin + " 00:00:00', 'yyyy-mm-dd hh24:mi:ss') ")
				// DateOperator.parse(monthBegin + " 00:00:00", "yyyy-MM-dd HH:mm:ss"))
				.append("  AND c.CREATED_DATE<= ")
				.append(" to_date('" + monthEnd + " 23:59:59', 'yyyy-mm-dd hh24:mi:ss') ");
		// DateOperator.parse(monthEnd + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		sql.append(" then c.OUT_AMOUNT else 0 end)as MONTHOUTMON ");
		sql.append("FROM FIN_IN_OUT_DETAIL c where  c.FIN_ITEM_CD not in ('1011' ,'2011','1004','1006') GROUP BY C.FIN_PROJECT_ACCT_REL_ID ");
		sql.append(")T ON A.FIN_PROJECT_ACCT_REL_ID =T.FIN_PROJECT_ACCT_REL_ID ");
		sql.append("group by A.PROJECT_CD,A.CURRENCY_CD ");
		sql.append("ORDER BY A.PROJECT_CD,A.CURRENCY_CD DESC");
		List list = finProjectDao.findBySql(sql.toString(), params);
		return list;
	}

	public void doDayExport(WritableSheet wsheet, WritableSheet wsheetSimple, String fromDate, String toDate,
			String userCd, String publicFlg) throws Exception {
		HashMap font = finProjectAcctRelManager.fontMap();
		WritableCellFormat wcfN_notCol_Bold = (WritableCellFormat) font.get("wcfN_notCol_Bold");
		WritableCellFormat wcfN_Bold = (WritableCellFormat) font.get("wcfN_Bold");
		WritableCellFormat wcfN_noBold = (WritableCellFormat) font.get("wcfN_noBold");
		WritableCellFormat wcfN_N_noBold = (WritableCellFormat) font.get("wcfN_N_noBold");
		WritableCellFormat wcfN_Y_Bold = (WritableCellFormat) font.get("wcfN_Y_Bold");
		WritableCellFormat wcfN_N_Y_noBold = (WritableCellFormat) font.get("wcfN_N_Y_noBold");

		WritableCellFormat wcfN_N_noBold1 = (WritableCellFormat) font.get("wcfN_N_noBold1");
		WritableCellFormat wcfN_N_Y_noBold1 = (WritableCellFormat) font.get("wcfN_N_Y_noBold1");
		wsheet.addCell(new Label(0, 0, "各公司资金日余额表", wcfN_noBold));
		wsheet.mergeCells(0, 0, 17, 0);
		if (fromDate.equals(toDate)) {
			wsheet.addCell(new Label(0, 1, "时间：" + fromDate, wcfN_Bold));
		} else {
			wsheet.addCell(new Label(0, 1, "时间：" + fromDate + " - " + toDate, wcfN_Bold));
		}
		wsheet.mergeCells(0, 1, 2, 1);
		wsheet.addCell(new Label(3, 1, "单位：元", wcfN_Bold));
		wsheet.addCell(new Label(0, 2, "", wcfN_Bold));
		wsheet.mergeCells(0, 2, 3, 2);
		wsheet.addCell(new Label(4, 1, "", wcfN_Bold));
		wsheet.mergeCells(4, 1, 17, 1);
		wsheet.addCell(new Label(4, 2, "银行存款", wcfN_Bold));
		wsheet.mergeCells(4, 2, 10, 2);
		wsheet.addCell(new Label(11, 2, "现金", wcfN_Bold));
		wsheet.mergeCells(11, 2, 17, 2);
		wsheet.setRowView(2, 400);
		wsheet.addCell(new Label(0, 3, "序号", wcfN_Bold));
		wsheet.mergeCells(0, 3, 1, 3);
		wsheet.addCell(new Label(2, 3, "公司名称", wcfN_Bold));
		wsheet.addCell(new Label(3, 3, "币种", wcfN_Bold));
		wsheet.addCell(new Label(4, 3, "期初金额", wcfN_Bold));
		wsheet.addCell(new Label(5, 3, "收入", wcfN_Bold));
		wsheet.addCell(new Label(6, 3, "支出", wcfN_Bold));
		wsheet.addCell(new Label(7, 3, "期末金额", wcfN_Bold));
		wsheet.addCell(new Label(8, 3, "可动用", wcfN_Bold));
		wsheet.addCell(new Label(9, 3, "不可动用", wcfN_Bold));
		wsheet.addCell(new Label(10, 3, "备注", wcfN_Bold));
		wsheet.addCell(new Label(11, 3, "币种", wcfN_Bold));
		wsheet.addCell(new Label(12, 3, "期初金额", wcfN_Bold));
		wsheet.addCell(new Label(13, 3, "收入", wcfN_Bold));
		wsheet.addCell(new Label(14, 3, "支出", wcfN_Bold));
		wsheet.addCell(new Label(15, 3, "期末金额", wcfN_Bold));
		wsheet.addCell(new Label(16, 3, "备注", wcfN_Bold));
		wsheet.addCell(new Label(17, 3, "合计", wcfN_Bold));
		wsheet.setRowView(3, 400);
		int nowRowi = 3;// 当前行数
		List<FinProjectUtil> projectList = getFinanceTot(fromDate + " 00:00:00", toDate + " 23:59:59", userCd, true,
				publicFlg, null);
		String projectCd = "";
		String curCd = "";
		String curTot = "";
		List totList = new ArrayList();
		BigDecimal[] tot = new BigDecimal[12];
		// 建立excel要排列的公司数据
		List<FinDetailUtil> companyList = new ArrayList<FinDetailUtil>();
		for (FinProjectUtil project : projectList) {
			if (!projectCd.equals(project.getFinProjectCd()) || !curCd.equals(project.getCurrencyCd())) {
				// 若公司不同则加一行或公司相同币种不同，则加一行；
				nowRowi++;
				// 如果公司相同,则+i
				if (projectCd.equals(project.getFinProjectCd())) {
					for (int i = companyList.size(); i > 0; i--) {
						FinDetailUtil util = companyList.get(i - 1);
						if (projectCd.equals(util.getFinItemCd())) {
							util.setColspanNum(util.getColspanNum() + 1);
							break;
						}
					}
				} else {
					// 公司不同，新增一条数据
					FinDetailUtil util = new FinDetailUtil();
					util.setFinItemCd(project.getFinProjectCd());
					util.setFinItemName(project.getProjectName());
					util.setColspanNum(0);
					companyList.add(util);
				}
				projectCd = project.getFinProjectCd();
				curCd = project.getCurrencyCd();
				wsheet.setRowView(nowRowi, 400);
			}
			if (project.getCurrencyCd() != null && curTot.indexOf(project.getCurrencyCd()) < 0) {
				curTot = curTot + project.getCurrencyCd() + ",";
				List list = new ArrayList();
				list.add(project.getCurrencyCd());
				tot = new BigDecimal[12];
				for (int i = 0; i < 12; i++) {
					tot[i] = new BigDecimal(0);
				}
				list.add(tot);
				totList.add(list);
			}
			boolean haveSum = false;
			if (totList != null && totList.size() > 0) {
				for (int i = 0; i < totList.size(); i++) {
					String cur = (String) ((List) totList.get(i)).get(0);
					if (cur.equals(project.getCurrencyCd())) {
						tot = (BigDecimal[]) ((List) totList.get(i)).get(1);
						haveSum = true;
						break;
					}
				}
			}
			BigDecimal beforeSum = project.getUnUseableAmount().add(project.getUseableAmount())
					.add(project.getOutAmount()).subtract(project.getInAmount());
			if ("2".equals(project.getAccTypeCd())) {
				Cell cell = wsheet.getCell(3, nowRowi);
				if (cell.getContents() == null || "".equals(cell.getContents())) {
					// 若上一级公司名称一样，则合并公司
					//
					/**
					 * wsheet.addCell(new Label(0, nowRowi, (nowRowi - 3) + "", wcfN_notCol_Bold)); wsheet.mergeCells(0, nowRowi, 1, nowRowi);
					 * wsheet.addCell(new Label(2, nowRowi, project .getProjectName(), wcfN_notCol_Bold));
					 **/
					// 该行没填记录
					for (int ii = 3; ii < 11; ii++) {
						wsheet.addCell(new Label(ii, nowRowi, "", wcfN_notCol_Bold));
					}
				}
				wsheet.addCell(new Label(11, nowRowi, project.getCurrencyCd(), wcfN_notCol_Bold));
				wsheet.addCell(new Number(12, nowRowi, beforeSum.doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Number(13, nowRowi, project.getInAmount().doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Number(14, nowRowi, project.getOutAmount().doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Number(15, nowRowi, project.getUnUseableAmount().add(project.getUseableAmount())
						.doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Label(16, nowRowi, "", wcfN_notCol_Bold));
				if (haveSum) {
					tot[6] = tot[6].add(beforeSum);
					tot[7] = tot[7].add(project.getInAmount());
					tot[8] = tot[8].add(project.getOutAmount());
					tot[9] = tot[9].add(project.getUnUseableAmount().add(project.getUseableAmount()));
				}

			} else {
				/**
				 * wsheet.addCell(new Label(0, nowRowi, (nowRowi - 3) + "", wcfN_notCol_Bold)); wsheet.addCell(new Label(2, nowRowi, project.getProjectName(),
				 * wcfN_notCol_Bold));
				 */
				wsheet.addCell(new Label(3, nowRowi, project.getCurrencyCd(), wcfN_notCol_Bold));
				wsheet.addCell(new Number(4, nowRowi, beforeSum.doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Number(5, nowRowi, project.getInAmount().doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Number(6, nowRowi, project.getOutAmount().doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Number(7, nowRowi, project.getUnUseableAmount().add(project.getUseableAmount())
						.doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Number(8, nowRowi, project.getUseableAmount().doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Number(9, nowRowi, project.getUnUseableAmount().doubleValue(), wcfN_N_noBold));
				wsheet.addCell(new Label(10, nowRowi, "", wcfN_notCol_Bold));
				wsheet.addCell(new Label(11, nowRowi, "", wcfN_notCol_Bold));
				wsheet.addCell(new Label(12, nowRowi, "", wcfN_notCol_Bold));
				wsheet.addCell(new Label(13, nowRowi, "", wcfN_notCol_Bold));
				wsheet.addCell(new Label(14, nowRowi, "", wcfN_notCol_Bold));
				wsheet.addCell(new Label(15, nowRowi, "", wcfN_notCol_Bold));
				wsheet.addCell(new Label(16, nowRowi, "", wcfN_notCol_Bold));
				// wsheet.mergeCells(0, nowRowi, 1, nowRowi);
				if (haveSum) {
					tot[0] = tot[0].add(beforeSum);
					tot[1] = tot[1].add(project.getInAmount());
					tot[2] = tot[2].add(project.getOutAmount());
					tot[3] = tot[3].add(project.getUnUseableAmount().add(project.getUseableAmount()));
					tot[4] = tot[4].add(project.getUseableAmount());
					tot[5] = tot[5].add(project.getUnUseableAmount());
				}
			}
		}
		// 重写公司
		int utilRowi = 4;
		int utilReali = 4;
		for (FinDetailUtil util : companyList) {
			wsheet.addCell(new Label(0, utilReali, (utilRowi - 3) + "", wcfN_notCol_Bold));
			wsheet.addCell(new Label(2, utilReali, util.getFinItemName(), wcfN_notCol_Bold));
			wsheet.mergeCells(0, utilReali, 1, utilReali + util.getColspanNum());
			wsheet.mergeCells(2, utilReali, 2, utilReali + util.getColspanNum());
			utilReali = utilReali + util.getColspanNum();
			utilReali++;
			utilRowi++;
		}
		nowRowi++;
		String[] strCur = curTot.split(",");
		for (int i = 0; i < strCur.length; i++) {
			wsheet.addCell(new Label(0, nowRowi, "合计", wcfN_Y_Bold));
			wsheet.mergeCells(0, nowRowi, 2, nowRowi);
			wsheet.addCell(new Label(3, nowRowi, strCur[i], wcfN_Y_Bold));
			BigDecimal[] big = (BigDecimal[]) ((List) totList.get(i)).get(1);
			wsheet.addCell(new Number(4, nowRowi, big[0].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(5, nowRowi, big[1].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(6, nowRowi, big[2].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(7, nowRowi, big[3].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(8, nowRowi, big[4].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(9, nowRowi, big[5].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Label(10, nowRowi, "", wcfN_N_Y_noBold));
			wsheet.addCell(new Label(11, nowRowi, "", wcfN_N_Y_noBold));
			wsheet.addCell(new Number(12, nowRowi, big[6].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(13, nowRowi, big[7].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(14, nowRowi, big[8].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Number(15, nowRowi, big[9].doubleValue(), wcfN_N_Y_noBold));
			wsheet.addCell(new Label(16, nowRowi, "", wcfN_N_Y_noBold));
			wsheet.setRowView(nowRowi, 400);
			nowRowi++;
		}

		// 求合计
		for (int i = 4; i < nowRowi; i++) {
			Cell cell3 = wsheet.getCell(7, i);
			Cell cell2 = wsheet.getCell(15, i);
			if ((cell3 == null || "".equals(cell3.getContents())) && (cell2 == null || "".equals(cell2.getContents()))) {
				wsheet.addCell(new Number(17, i, new Double(0), wcfN_N_Y_noBold));
			} else if (cell2 == null || "".equals(cell2.getContents())) {
				String content = cell3.getContents();
				wsheet.addCell(new Number(17, i, Double.parseDouble(content), wcfN_N_Y_noBold));
			} else if (cell3 == null || "".equals(cell3.getContents())) {
				String content = cell2.getContents();
				wsheet.addCell(new Number(17, i, Double.parseDouble(content), wcfN_N_Y_noBold));
			} else {
				// 两个相加
				String content3 = cell3.getContents();
				String content2 = cell2.getContents();
				BigDecimal bigd3 = new BigDecimal(content3);
				BigDecimal bigd2 = new BigDecimal(content2);
				wsheet.addCell(new Number(17, i, (bigd3.add(bigd2)).doubleValue(), wcfN_N_Y_noBold));
			}
		}
		// 调整宽度
		wsheet.setColumnView(0, 4);
		wsheet.setColumnView(1, 3);
		wsheet.setColumnView(2, 30);
		wsheet.setColumnView(3, 8);
		wsheet.setColumnView(4, 20);
		wsheet.setColumnView(5, 15);
		wsheet.setColumnView(6, 15);
		wsheet.setColumnView(7, 20);
		wsheet.setColumnView(8, 15);
		wsheet.setColumnView(9, 15);
		wsheet.setColumnView(10, 20);
		wsheet.setColumnView(11, 8);
		wsheet.setColumnView(12, 20);
		wsheet.setColumnView(13, 15);
		wsheet.setColumnView(14, 15);
		wsheet.setColumnView(15, 20);
		wsheet.setColumnView(16, 20);
		wsheet.setColumnView(17, 20);
		// 冻结单元格
		wsheet.getSettings().setVerticalFreeze(4);
		wsheet.getSettings().setHorizontalFreeze(3);

		// 各公司日余额简表
		wsheetSimple.addCell(new Label(5, 0, "各公司资金日余额表", wcfN_Bold));
		wsheetSimple.mergeCells(5, 0, 13, 1);
		if (fromDate.equals(toDate)) {
			wsheetSimple.addCell(new Label(0, 0, "时间：" + fromDate, wcfN_Bold));
		} else {
			wsheetSimple.addCell(new Label(0, 0, "时间：" + fromDate + " - " + toDate, wcfN_Bold));
		}
		wsheetSimple.mergeCells(0, 0, 3, 1);
		wsheetSimple.addCell(new Label(4, 0, "单位：万元", wcfN_Bold));
		wsheetSimple.mergeCells(4, 0, 4, 1);
		// wsheetSimple.addCell(new Label(4, 1,"",wcfN_Bold));
		// wsheetSimple.mergeCells(4, 1, 12, 1);

		wsheetSimple.addCell(new Label(0, 2, "序号", wcfN_Bold));
		wsheetSimple.mergeCells(0, 2, 1, 2);
		wsheetSimple.addCell(new Label(2, 2, "公司名称", wcfN_Bold));
		wsheetSimple.addCell(new Label(3, 2, "币种", wcfN_Bold));
		wsheetSimple.addCell(new Label(4, 2, "当日收款(不含内部调拨)", wcfN_Bold));
		wsheetSimple.addCell(new Label(5, 2, "当日付款(不含内部调拨)", wcfN_Bold));
		wsheetSimple.addCell(new Label(6, 2, "本周累计付款(不含内部调拨)", wcfN_Bold));
		wsheetSimple.addCell(new Label(7, 2, "本周核定付款限额", wcfN_Bold));
		wsheetSimple.addCell(new Label(8, 2, "本月累计收款(不含内部调拨)", wcfN_Bold));
		wsheetSimple.addCell(new Label(9, 2, "本月累计付款(不含内部调拨)", wcfN_Bold));
		wsheetSimple.addCell(new Label(10, 2, "银行及现金余额", wcfN_Bold));
		wsheetSimple.addCell(new Label(11, 2, "可动用金额", wcfN_Bold));
		wsheetSimple.addCell(new Label(12, 2, "不可动用金额", wcfN_Bold));
		wsheetSimple.addCell(new Label(13, 2, "备注", wcfN_Bold));
		wsheetSimple.setColumnView(0, 4);
		wsheetSimple.setColumnView(1, 3);
		wsheetSimple.setColumnView(2, 30);
		wsheetSimple.setColumnView(3, 8);
		wsheetSimple.setColumnView(4, 15);
		wsheetSimple.setColumnView(5, 15);
		wsheetSimple.setColumnView(6, 20);
		wsheetSimple.setColumnView(7, 20);
		wsheetSimple.setColumnView(8, 25);
		wsheetSimple.setColumnView(9, 25);
		wsheetSimple.setColumnView(10, 25);
		wsheetSimple.setColumnView(11, 20);
		wsheetSimple.setColumnView(12, 20);
		wsheetSimple.setColumnView(13, 20);
		// 冻结单元格
		wsheetSimple.getSettings().setVerticalFreeze(3);
		wsheetSimple.getSettings().setHorizontalFreeze(4);
		int simRowi = 3;// 当前简表行数
		List<FinProjectUtil> simpleList = getFinanceTot(fromDate + " 00:00:00", toDate + " 23:59:59", userCd, false,
				publicFlg, "RMB");
		// 取得本周周一日期
		String weekBegin = DateUtil.getNowWeekDay(fromDate);
		// 取得第二个搜索日期的末周
		String weekEnd = DateUtil.getEndWeekDay(toDate);
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String monthBegin = format1.format(DateOperator.getFirstDayOfMonth(format1.parse(fromDate)));
		// 取得下个月的第一天
		Date newToDate = DateOperator.addMonthes(format1.parse(toDate), 1);
		String monthEnd = format1.format(DateOperator.getFirstDayOfMonth(newToDate));
		List objList = getFinSimple(fromDate, toDate, weekBegin, weekEnd, monthBegin, monthEnd);
		// 简表总和
		String curTotSim = "";
		List totListSim = new ArrayList();
		BigDecimal[] totSim = new BigDecimal[8];
		for (FinProjectUtil project : simpleList) {
			boolean haveSim = false;
			wsheetSimple.addCell(new Number(0, simRowi, simRowi - 2, wcfN_notCol_Bold));// 序号
			wsheetSimple.mergeCells(0, simRowi, 1, simRowi);
			wsheetSimple.addCell(new Label(2, simRowi, project.getProjectName(), wcfN_notCol_Bold));// 项目名称
			wsheetSimple.addCell(new Label(3, simRowi, project.getCurrencyCd(), wcfN_notCol_Bold));

			if (project.getCurrencyCd() != null && curTotSim.indexOf(project.getCurrencyCd()) < 0) {
				curTotSim = curTotSim + project.getCurrencyCd() + ",";
				List list = new ArrayList();
				list.add(project.getCurrencyCd());
				tot = new BigDecimal[12];
				for (int i = 0; i < 8; i++) {
					tot[i] = new BigDecimal(0);
				}
				list.add(tot);
				totListSim.add(list);
			}
			boolean haveSum = false;
			if (totListSim != null && totListSim.size() > 0) {
				for (int i = 0; i < totListSim.size(); i++) {
					String cur = (String) ((List) totListSim.get(i)).get(0);
					if (cur.equals(project.getCurrencyCd())) {
						tot = (BigDecimal[]) ((List) totListSim.get(i)).get(1);
						haveSum = true;
						break;
					}
				}
			}

			if (objList != null && objList.size() > 0) {
				for (int i = 0; i < objList.size(); i++) {
					Object[] obj = (Object[]) objList.get(i);
					if (((String) obj[0]).equals(project.getFinProjectCd())
							&& ((String) obj[1]).equals(project.getCurrencyCd())) {
						wsheetSimple.addCell(new Number(4, simRowi, ((BigDecimal) obj[2]).doubleValue() / 10000,
								wcfN_N_noBold1));
						wsheetSimple.addCell(new Number(5, simRowi, ((BigDecimal) obj[3]).doubleValue() / 10000,
								wcfN_N_noBold1));
						wsheetSimple.addCell(new Number(6, simRowi, ((BigDecimal) obj[4]).doubleValue() / 10000,
								wcfN_N_noBold1));
						wsheetSimple.addCell(new Number(7, simRowi, new Double(0), wcfN_N_noBold1));
						wsheetSimple.addCell(new Number(8, simRowi, ((BigDecimal) obj[5]).doubleValue() / 10000,
								wcfN_N_noBold1));
						wsheetSimple.addCell(new Number(9, simRowi, ((BigDecimal) obj[6]).doubleValue() / 10000,
								wcfN_N_noBold1));
						haveSim = true;
						if (haveSum) {
							tot[0] = tot[0].add((BigDecimal) obj[2]);
							tot[1] = tot[1].add((BigDecimal) obj[3]);
							tot[2] = tot[2].add((BigDecimal) obj[4]);
							tot[3] = tot[3].add((BigDecimal) obj[5]);
							tot[4] = tot[4].add((BigDecimal) obj[6]);
						}
						objList.remove(obj);
						break;
					}
				}
			}
			if (!haveSim) {
				wsheetSimple.addCell(new Number(4, simRowi, new Double(0), wcfN_N_noBold1));
				wsheetSimple.addCell(new Number(5, simRowi, new Double(0), wcfN_N_noBold1));
				wsheetSimple.addCell(new Number(6, simRowi, new Double(0), wcfN_N_noBold1));
				wsheetSimple.addCell(new Number(7, simRowi, new Double(0), wcfN_N_noBold1));
				wsheetSimple.addCell(new Number(8, simRowi, new Double(0), wcfN_N_noBold1));
				wsheetSimple.addCell(new Number(9, simRowi, new Double(0), wcfN_N_noBold1));
			}
			wsheetSimple.addCell(new Number(10, simRowi, (project.getUnUseableAmount().add(project.getUseableAmount())
					.doubleValue()) / 10000, wcfN_N_noBold1));
			wsheetSimple.addCell(new Number(11, simRowi, (project.getUseableAmount().doubleValue()) / 10000,
					wcfN_N_noBold1));
			wsheetSimple.addCell(new Number(12, simRowi, (project.getUnUseableAmount().doubleValue()) / 10000,
					wcfN_N_noBold1));
			wsheetSimple.addCell(new Label(13, simRowi, "", wcfN_notCol_Bold));
			wsheetSimple.setRowView(simRowi, 400);
			if (haveSum) {
				tot[5] = tot[5].add(project.getUnUseableAmount().add(project.getUseableAmount()));
				tot[6] = tot[6].add(project.getUseableAmount());
				tot[7] = tot[7].add(project.getUnUseableAmount());
			}
			simRowi++;
		}
		String[] strCurSim = curTotSim.split(",");
		for (int i = 0; i < strCurSim.length; i++) {
			wsheetSimple.addCell(new Label(0, simRowi, "合计", wcfN_Y_Bold));
			wsheetSimple.mergeCells(0, simRowi, 2, simRowi);
			wsheetSimple.addCell(new Label(3, simRowi, strCurSim[i], wcfN_Y_Bold));
			BigDecimal[] big = (BigDecimal[]) ((List) totListSim.get(i)).get(1);
			wsheetSimple.addCell(new Number(4, simRowi, (big[0].doubleValue()) / 10000, wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Number(5, simRowi, (big[1].doubleValue()) / 10000, wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Number(6, simRowi, (big[2].doubleValue()) / 10000, wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Label(7, simRowi, "", wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Number(8, simRowi, (big[3].doubleValue()) / 10000, wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Number(9, simRowi, (big[4].doubleValue()) / 10000, wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Number(10, simRowi, (big[5].doubleValue()) / 10000, wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Number(11, simRowi, (big[6].doubleValue()) / 10000, wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Number(12, simRowi, (big[7].doubleValue()) / 10000, wcfN_N_Y_noBold1));
			wsheetSimple.addCell(new Label(13, simRowi, "", wcfN_N_Y_noBold1));
			wsheetSimple.setRowView(simRowi, 400);
			simRowi++;
		}
	}

	public void updateBalance(String userCd, String publicFlg) {
		List<FinProject> projects = getProject(userCd, null, false, publicFlg);
		for (FinProject project : projects) {
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "select fin_project_acct_rel_id,total_amount,balance from fin_project_acct_rel where project_cd='"
					+ project.getProjectCd() + "'";
			List bankList = finProjectDao.findBySql(sql, params);
			if (bankList != null && bankList.size() > 0) {
				for (int i = 0; i < bankList.size(); i++) {
					Object[] obj = (Object[]) bankList.get(i);
					String accId = (String) obj[0];
					BigDecimal totBalance = (BigDecimal) obj[2];
					String sqlDeatil = "select fin_in_out_detail_id,in_amount,out_amount,balance from fin_in_out_detail where fin_project_acct_rel_id='"
							+ accId + "' order by created_date asc";
					List detailList = finProjectDao.findBySql(sqlDeatil, params);
					for (int j = 0; j < detailList.size(); j++) {
						Object[] objDetail = (Object[]) detailList.get(j);
						String detailId = (String) objDetail[0];
						BigDecimal inAmount = (BigDecimal) objDetail[1];
						BigDecimal outAmount = (BigDecimal) objDetail[2];
						BigDecimal balance = totBalance.add(inAmount).subtract(outAmount);
						// 更新明细表里的余额
						String sqlUpdate = "update FinInOutDetail set balance= " + balance
								+ " where finInOutDetailId='" + detailId + "'";
						getDao().batchExecute(sqlUpdate, params);
						String updateAcc = "update FinProjectAcctRel set balance =" + balance
								+ " where finProjectAcctRelId='" + accId + "'";
						getDao().batchExecute(updateAcc, params);
						totBalance = balance;
					}
				}
			}
		}
	}
}

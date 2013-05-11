package com.hhz.ump.dao.fin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.fin.FinProject;
import com.hhz.ump.entity.fin.FinProjectAcctRel;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.FinProjectAccUtil;

@Service
@Transactional
public class FinProjectAcctRelManager extends BaseService<FinProjectAcctRel, String> {
	@Autowired
	private FinProjectAcctRelDao finProjectAcctRelDao;
	@Autowired
	private FinItemManager finItemManager;

	// FinProjectAcctRel Manager //
	@Transactional(readOnly = true)
	public FinProjectAcctRel getFinProjectAcctRel(String id) {
		return finProjectAcctRelDao.get(id);
	}
	
	public List<FinProjectAcctRel> getAllFinProjectAcctRel() {
		return finProjectAcctRelDao.getAll();
	}
	
	public void saveFinProjectAcctRel(FinProjectAcctRel finProjectAcctRel) {
		PowerUtils.setEmptyStr2Null(finProjectAcctRel);
		finProjectAcctRelDao.save(finProjectAcctRel);
	}

	public void deleteFinProjectAcctRel(String id) {
		finProjectAcctRelDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<FinProjectAcctRel, String> getDao() {
		return finProjectAcctRelDao;
	}

	public String getFinProjectId(String projectCd) {
		String hql = "from " + FinProject.class.getSimpleName()
				+ " where projectCd=? ";
		List<FinProject> result = finProjectAcctRelDao.find(hql, projectCd);
		if (result != null && result.size() > 0)
			return result.get(0).getFinProjectId();
		return "";
	}

	public List<FinProjectAccUtil> getACCTByConditions(String beginTime,
			String endTime, String projectCd, String accTypeCd, String curCd,
			boolean isProjectExport) {
		StringBuffer hql = new StringBuffer(
"select a.bank_name,a.account_no,");
		hql
				.append("(a.balance-(case when c.in_amount is null then 0 else (c.aft_in_money-c.aft_out_money-c.out_amount+c.in_amount) end)) "
						+ "as bef_amount,");
		hql
				.append("(case when c.in_amount is null then 0 else c.in_amount end) as in_amount,");
		hql
				.append("(case when c.out_amount is null then 0 else c.out_amount end) as out_amount,");
		hql
				.append("(a.balance-(case when c.aft_in_money is null then 0 else (c.aft_in_money-c.aft_out_money) end)) as aft_amount,");
		hql
				.append("a.FIN_PROJECT_ACCT_REL_ID,a.ACCOUNT_TYPE_CD,a.ENABLE_FLG,a.CURRENCY_CD "
						+ "from FIN_PROJECT_ACCT_REL a left join (select "
				+ "sum(case when  b.CREATED_DATE>=:beginTime AND b.CREATED_DATE<=:endTime then b.in_amount else 0 end) "
				+ "AS IN_AMOUNT,sum(case when  b.CREATED_DATE>=:beginTime AND b.CREATED_DATE<=:endTime "
				+ "then b.out_amount else 0 end) AS OUT_AMOUNT,"
				+ "sum(case when b.CREATED_DATE>:endTime then b.IN_AMOUNT else 0 end) as aft_in_money,"
				+ "sum(case when b.CREATED_DATE>:endTime then b.OUT_AMOUNT else 0 end) as aft_out_money,"
				+ "b.FIN_PROJECT_ACCT_REL_ID from FIN_IN_OUT_DETAIL b where 1=1 "
						+ "group by b.FIN_PROJECT_ACCT_REL_ID) c on a.FIN_PROJECT_ACCT_REL_ID =c.FIN_PROJECT_ACCT_REL_ID "
						+ " where 1=1 ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginTime", DateOperator.parse(beginTime,
				"yyyy-MM-dd HH:mm:ss"));
		params.put("endTime", DateOperator
				.parse(endTime, "yyyy-MM-dd HH:mm:ss"));
		if (projectCd != null && !"".equals(projectCd)) {
			hql.append(" and a.PROJECT_CD=:projectCd ");
			params.put("projectCd", projectCd);
		}
		if (accTypeCd != null && !"".equals(accTypeCd)) {
			hql.append(" and a.ACCOUNT_TYPE_CD =:accTypeCd ");
			params.put("accTypeCd", accTypeCd);
		}
		if (curCd != null && !"".equals(curCd)) {
			hql.append(" and a.CURRENCY_CD =:curCd ");
			params.put("curCd", curCd);
		}
		// 排序
		if (isProjectExport) {
			hql.append("order by a.ACCOUNT_TYPE_CD,a.ENABLE_FLG asc");
		} else {
			hql.append("order by a.ENABLE_FLG,a.ACCOUNT_TYPE_CD asc");
		}
		List list = finProjectAcctRelDao.findBySql(hql.toString(), params);
		List<FinProjectAccUtil> accList = new ArrayList<FinProjectAccUtil>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				FinProjectAccUtil accUtil = new FinProjectAccUtil();
				Object[] obj = (Object[]) list.get(i);
				accUtil.setBankName((String) obj[0]);
				accUtil.setAccountNo((String) obj[1]);
				accUtil.setBefAmount((BigDecimal) obj[2]);
				accUtil.setInAmount((BigDecimal) obj[3]);
				accUtil.setOutAmount((BigDecimal) obj[4]);
				accUtil.setAftAmount((BigDecimal) obj[5]);
				accUtil.setFinProjectAcctRelId((String) obj[6]);
				accUtil.setAccountTypeCd((String) obj[7]);
				accUtil.setEnableFlg((String) obj[8]);
				accUtil.setCurrencyCd((String) obj[9]);
				accList.add(accUtil);
			}
		}
		return accList;

	}

	public HashMap fontMap() throws WriteException {
		HashMap map = new HashMap();
		int charNormal = 10;// 标题字体大小
		// 字体：黑体，背景颜色：灰色
		WritableFont nf_Bold = new WritableFont(WritableFont
				.createFont("Times New Roman"), 10);
		jxl.write.WritableCellFormat wcfN_Bold;
		nf_Bold.setBoldStyle(WritableFont.BOLD);
		wcfN_Bold = new jxl.write.WritableCellFormat(nf_Bold);
		wcfN_Bold.setBackground(Colour.GRAY_25);
		wcfN_Bold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_Bold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_Bold.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_Bold.setWrap(true); // 是否换行

		map.put("wcfN_Bold", wcfN_Bold);
		// 字体：黑体，背景颜色：黄色
		jxl.write.WritableCellFormat wcfN_Y_Bold;
		nf_Bold.setBoldStyle(WritableFont.BOLD);
		wcfN_Y_Bold = new jxl.write.WritableCellFormat(nf_Bold);
		wcfN_Y_Bold.setBackground(Colour.YELLOW);
		wcfN_Y_Bold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_Y_Bold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_Y_Bold.setAlignment(Alignment.CENTRE);// 水平对齐
		map.put("wcfN_Y_Bold", wcfN_Y_Bold);
		// 字体黑色，无背景颜色
		jxl.write.WritableCellFormat wcfN_notCol_Bold;
		nf_Bold.setBoldStyle(WritableFont.BOLD);
		wcfN_notCol_Bold = new jxl.write.WritableCellFormat(nf_Bold);
		wcfN_notCol_Bold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_notCol_Bold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_notCol_Bold.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_notCol_Bold.setWrap(true); // 是否换行
		map.put("wcfN_notCol_Bold", wcfN_notCol_Bold);
		// 字体：普通
		jxl.write.WritableCellFormat wcfN_noBold;
		nf_Bold.setBoldStyle(WritableFont.NO_BOLD);
		wcfN_noBold = new jxl.write.WritableCellFormat(nf_Bold);
		wcfN_noBold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_noBold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_noBold.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_noBold.setWrap(true); // 是否换行
		map.put("wcfN_noBold", wcfN_noBold);
		// 字体：粗体,黑色,左对齐
		WritableFont nf_Bold1 = new WritableFont(WritableFont
				.createFont("Times New Roman"), 10);
		jxl.write.WritableCellFormat wcfN_noBold_left;
		nf_Bold1.setBoldStyle(WritableFont.NO_BOLD);
		wcfN_noBold_left = new jxl.write.WritableCellFormat(nf_Bold1);
		wcfN_noBold_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_noBold_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_noBold_left.setAlignment(Alignment.LEFT);// 水平对齐
		wcfN_noBold_left.setWrap(true); // 是否换行
		map.put("wcfN_noBold_left", wcfN_noBold_left);
		// 字体：粗体,黑色,左对齐
		WritableFont nf_Bold2 = new WritableFont(WritableFont
				.createFont("Times New Roman"), 10);
		jxl.write.WritableCellFormat wcfN_noBold_center;
		nf_Bold1.setBoldStyle(WritableFont.NO_BOLD);
		wcfN_noBold_center = new jxl.write.WritableCellFormat(nf_Bold1);
		wcfN_noBold_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_noBold_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_noBold_center.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_noBold_center.setWrap(true); // 是否换行
		map.put("wcfN_noBold_center", wcfN_noBold_center);
		// 字体：数字,无背景颜色
		NumberFormat num = new NumberFormat("###,###,###,###,###,###,##0.00");
		// WritableFont nf_N_noBold = new
		// WritableFont(WritableFont.createFont("Times New Roman"), charNormal);
		jxl.write.WritableCellFormat wcfN_N_noBold;
		// nf_N_noBold.setBoldStyle(WritableFont.NO_BOLD);
		wcfN_N_noBold = new jxl.write.WritableCellFormat(num);
		wcfN_N_noBold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_N_noBold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_N_noBold.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_N_noBold.setWrap(true); // 是否换行
		map.put("wcfN_N_noBold", wcfN_N_noBold);
		// 字体：数字,有背景颜色
		// WritableFont nf_N_noBold = new
		// WritableFont(WritableFont.createFont("Times New Roman"), charNormal);
		jxl.write.WritableCellFormat wcfN_N_Y_noBold;
		// nf_N_noBold.setBoldStyle(WritableFont.NO_BOLD);
		wcfN_N_Y_noBold = new jxl.write.WritableCellFormat(num);
		wcfN_N_Y_noBold.setBackground(Colour.YELLOW);
		wcfN_N_Y_noBold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_N_Y_noBold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_N_Y_noBold.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_N_Y_noBold.setWrap(true); // 是否换行
		map.put("wcfN_N_Y_noBold", wcfN_N_Y_noBold);

		// 字体：黑体，背景颜色：灰色
		jxl.write.WritableCellFormat block_format_Bold;
		nf_Bold.setBoldStyle(WritableFont.BOLD);
		block_format_Bold = new jxl.write.WritableCellFormat(num);
		block_format_Bold.setBackground(Colour.GRAY_25);
		block_format_Bold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		block_format_Bold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		block_format_Bold.setAlignment(Alignment.CENTRE);// 水平对齐
		block_format_Bold.setWrap(true); // 是否换行

		map.put("block_format_Bold", block_format_Bold);

		// 字体：数字,无背景颜色,单位万元
		NumberFormat num1 = new NumberFormat("###,###,###,###,###,###");
		// WritableFont nf_N_noBold = new
		// WritableFont(WritableFont.createFont("Times New Roman"), charNormal);
		jxl.write.WritableCellFormat wcfN_N_noBold1;
		// nf_N_noBold.setBoldStyle(WritableFont.NO_BOLD);
		wcfN_N_noBold1 = new jxl.write.WritableCellFormat(num1);
		wcfN_N_noBold1.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_N_noBold1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_N_noBold1.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_N_noBold1.setWrap(true); // 是否换行
		map.put("wcfN_N_noBold1", wcfN_N_noBold1);
		// 字体：数字,有背景颜色，单位万元
		// WritableFont nf_N_noBold = new
		// WritableFont(WritableFont.createFont("Times New Roman"), charNormal);
		jxl.write.WritableCellFormat wcfN_N_Y_noBold1;
		// nf_N_noBold.setBoldStyle(WritableFont.NO_BOLD);
		wcfN_N_Y_noBold1 = new jxl.write.WritableCellFormat(num1);
		wcfN_N_Y_noBold1.setBackground(Colour.YELLOW);
		wcfN_N_Y_noBold1.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_N_Y_noBold1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_N_Y_noBold1.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_N_Y_noBold1.setWrap(true); // 是否换行
		map.put("wcfN_N_Y_noBold1", wcfN_N_Y_noBold1);
		return map;
	}
	public List<FinProjectAccUtil> getProjectSumByAcc(String beginTime,
			String endTime, String projectCd, String curCd, String isaccType) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer(
				"select a.CURRENCY_CD,a.ENABLE_FLG,");
		sql
				.append("sum(a.balance-(case when c.in_amount is null then 0 else (c.aft_in_money-c.aft_out_money-c.out_amount+c.in_amount) end)) as bef_amount,");
		sql
				.append("sum(case when c.in_amount is null then 0 else c.in_amount end) as inAmount,");
		sql
				.append("sum(case when c.out_amount is null then 0 else c.out_amount end) as outAmount,");
		sql
				.append("sum(a.balance-(case when c.aft_in_money is null then 0 else (c.aft_in_money-c.aft_out_money) end)) "
						+ "as aft_amount");
		if (isaccType != null && !"".equals(isaccType)) {

			sql.append(",a.ACCOUNT_TYPE_CD ");
		}
		sql
				.append(" from FIN_PROJECT_ACCT_REL a left join(select "
						+ "sum(case when  b.CREATED_DATE>=:beginTime AND b.CREATED_DATE<=:endTime then b.in_amount else 0 end) "
						+ "AS IN_AMOUNT,sum(case when  b.CREATED_DATE>=:beginTime AND b.CREATED_DATE<=:endTime "
						+ "then b.out_amount else 0 end) AS OUT_AMOUNT,sum(case when b.CREATED_DATE>:endTime then b.IN_AMOUNT else 0 end) "
						+ "as aft_in_money,sum(case when b.CREATED_DATE>:endTime then b.OUT_AMOUNT else 0 end) as aft_out_money,"
						+ "b.FIN_PROJECT_ACCT_REL_ID from FIN_IN_OUT_DETAIL b where 1=1 group by b.FIN_PROJECT_ACCT_REL_ID) "
						+ "c on a.FIN_PROJECT_ACCT_REL_ID =c.FIN_PROJECT_ACCT_REL_ID where 1=1 ");
		if (projectCd != null && !"".equals(projectCd)) {
			sql.append("and a.PROJECT_CD=:projectCd ");
			params.put("projectCd", projectCd);
		}
		params.put("beginTime", DateOperator.parse(beginTime,
				"yyyy-MM-dd HH:mm:ss"));
		params.put("endTime", DateOperator
				.parse(endTime, "yyyy-MM-dd HH:mm:ss"));

		if (curCd != null && !"".equals(curCd)) {
			sql.append(" and a.CURRENCY_CD =:curCd ");
			params.put("curCd", curCd);
		}
		sql.append(" group by a.CURRENCY_CD,a.ENABLE_FLG");
		if (isaccType != null && !"".equals(isaccType)) {
			sql.append(",a.ACCOUNT_TYPE_CD");
		}
		List list = finProjectAcctRelDao.findBySql(sql.toString(), params);
		List<FinProjectAccUtil> accList = new ArrayList<FinProjectAccUtil>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				FinProjectAccUtil accUtil = new FinProjectAccUtil();
				Object[] obj = (Object[]) list.get(i);
				accUtil.setCurrencyCd((String) obj[0]);
				accUtil.setEnableFlg((String) obj[1]);
				accUtil.setBefAmount((BigDecimal) obj[2]);
				accUtil.setInAmount((BigDecimal) obj[3]);
				accUtil.setOutAmount((BigDecimal) obj[4]);
				accUtil.setAftAmount((BigDecimal) obj[5]);
				if (isaccType != null && !"".equals(isaccType)) {
					accUtil.setAccountTypeCd((String) obj[6]);
				}
				accList.add(accUtil);
			}
		}
		return accList;
	}

	public void doCashExcel(WritableSheet wsheet, String beginTime,
			String endTime, String projectCd) throws RowsExceededException,
			WriteException {
		HashMap font = fontMap();
		WritableCellFormat wcfN_notCol_Bold = (WritableCellFormat) font
				.get("wcfN_notCol_Bold");
		WritableCellFormat wcfN_Bold = (WritableCellFormat) font
				.get("wcfN_Bold");
		WritableCellFormat wcfN_noBold = (WritableCellFormat) font
				.get("wcfN_noBold");
		WritableCellFormat wcfN_N_noBold = (WritableCellFormat) font
				.get("wcfN_N_noBold");
		WritableCellFormat wcfN_Y_Bold = (WritableCellFormat) font
				.get("wcfN_Y_Bold");
		WritableCellFormat wcfN_N_Y_noBold = (WritableCellFormat) font
				.get("wcfN_N_Y_noBold");
		WritableCellFormat wcfN_noBold_left = (WritableCellFormat) font
				.get("wcfN_noBold_left");
		wsheet.addCell(new Label(0, 0, "单位：元", wcfN_notCol_Bold));
		wsheet.setRowView(0, 350);
		wsheet.addCell(new Label(0, 2, "项目", wcfN_Bold));
		wsheet.setRowView(2, 350);
		wsheet.addCell(new Label(1, 2, "合计", wcfN_Bold));
		List listDate = new ArrayList();
		String nowDate = beginTime;
		int dayi = 1;
		while (!endTime.equals(nowDate)) {
			listDate.add(nowDate);
			wsheet.addCell(new Label(dayi + 1, 2, nowDate,
							wcfN_Bold));
			nowDate = DateUtil.getNextDay(nowDate);
			dayi++;
		}
		wsheet.addCell(new Label(dayi + 1, 2, endTime, wcfN_Bold));
		listDate.add(endTime);
		List list = finItemManager.getItemListBetw(listDate, projectCd);
		List listItem =new ArrayList();
		dayi = 3;
		BigDecimal[] inOutBal = new BigDecimal[listDate.size() + 1];
		for (int i = 0; i < inOutBal.length; i++) {
			inOutBal[i] = new BigDecimal(0);
		}
		List itemList = getItemOrderList(list, "0", listItem);// 排序后的项目
		if (itemList != null && itemList.size() > 0) {
			for (int i = 0; i < itemList.size(); i++) {
				Object[] obj = (Object[]) itemList.get(i);
				if ("0".equals(obj[2])) {
					// 根节点
					wsheet.addCell(new Label(0, i + 3, (String) obj[0],
							wcfN_Bold));
					for (int j = 3; j < obj.length - 1; j++) {
						wsheet.addCell(new Number(j - 1, i + 3,
								((BigDecimal) obj[j]).doubleValue(),
								wcfN_N_Y_noBold));
						if ("10".equals(obj[1])) {
							inOutBal[j - 2] = (BigDecimal) obj[j];
						} else {
							// 20 支出
							inOutBal[j - 2] = inOutBal[j - 2]
									.subtract((BigDecimal) obj[j]);
						}
					}
					wsheet.addCell(new Number(1, i + 3,
							((BigDecimal) obj[obj.length - 1]).doubleValue(),
							wcfN_N_Y_noBold));
					if ("10".equals(obj[1])) {
						inOutBal[0] = (BigDecimal) obj[obj.length - 1];
					} else {
						// 20 支出
						inOutBal[0] = inOutBal[0]
								.subtract((BigDecimal) obj[obj.length - 1]);
					}
				} else {
					wsheet.addCell(new Label(0, i + 3, (String) obj[0],
						wcfN_noBold_left));
					for (int j = 3; j < obj.length - 1; j++) {
					wsheet
							.addCell(new Number(j - 1, i + 3,
									((BigDecimal) obj[j]).doubleValue(),
							wcfN_N_noBold));
					}
					wsheet.addCell(new Number(1, i + 3,
						((BigDecimal) obj[obj.length - 1]).doubleValue(),
						wcfN_N_Y_noBold));
				}
				wsheet.setRowView(i + 3, 350);
			}
			dayi = dayi + itemList.size();
		}
		wsheet.addCell(new Label(0, dayi, "净现金流入(出)", wcfN_Bold));
		for(int i=0;i<inOutBal.length;i++){
			wsheet.addCell(new Number(i+1, dayi, inOutBal[i].doubleValue(),
					wcfN_N_Y_noBold));
		}
		wsheet.setRowView(dayi, 350);
		dayi++;
		// 可动用金额及现金小计
		List<FinProjectAccUtil> accUtils = getProjectSumByAcc(beginTime
				+ " 00:00:00", endTime + " 23:59:59", projectCd, null, null);
		BigDecimal beforeSum = new BigDecimal(0);
		for (FinProjectAccUtil accUtil : accUtils) {
			if ("RMB".equals(accUtil.getCurrencyCd()) && "1".equals(accUtil.getEnableFlg())) {
				beforeSum = accUtil.getBefAmount();
			}
		}
		wsheet.addCell(new Label(0, dayi, "期初现金余额", wcfN_Bold));
		wsheet.addCell(new Label(0, dayi + 1, "期末现金及现金等价物余额", wcfN_Bold));
		wsheet.addCell(new Number(1, dayi, beforeSum.doubleValue(),
				wcfN_N_Y_noBold));
		wsheet.addCell(new Number(2, dayi, beforeSum.doubleValue(),
				wcfN_N_Y_noBold));
		wsheet.addCell(new Number(1, dayi+1, beforeSum.add(inOutBal[0]).doubleValue(),
				wcfN_N_Y_noBold));
		BigDecimal inOut =beforeSum.add(inOutBal[1]);
		wsheet.addCell(new Number(2, dayi+1, inOut.doubleValue(),wcfN_N_Y_noBold));
		for(int i=2;i<inOutBal.length;i++){
			wsheet.addCell(new Number(i + 1, dayi, inOut.doubleValue(),
					wcfN_N_Y_noBold));
			inOut = inOut.add(inOutBal[i]);
			wsheet.addCell(new Number(i + 1, dayi + 1, inOut.doubleValue(),
					wcfN_N_Y_noBold));
		}
		wsheet.setRowView(dayi, 350);
		wsheet.setRowView(dayi+1, 350);
		wsheet.setColumnView(0, 30);
		for (int i = 0; i <= listDate.size(); i++) {
			wsheet.setColumnView(i + 1, 20);
		}
		wsheet.getSettings().setHorizontalFreeze(1);// .setVerticalFreeze(1);
		wsheet.getSettings().setVerticalFreeze(3);
	}

	private List getItemOrderList(List list,String parentCd,List listItem) {
		if (list !=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				if(obj[2]!=null&&parentCd.equals((String)obj[2])){
					listItem.add(obj);
					if(hasChildNode(list,(String)obj[1])){

						List items = getItemOrderList(list, (String) obj[1],
								listItem);
						for (int j = 0; j < items.size(); j++) {
							Object[] item = (Object[]) items.get(j);
							if (item[2] != null
									&& ((String) obj[1])
											.equals((String) item[2])) {
								for (int k = 3; k < obj.length; k++) {
									// 求和
								obj[k] = ((BigDecimal) obj[k])
										.add((BigDecimal) item[k]);
								}
							}
						}
					}else{
						// 没有孩子结点，obj[2]:父结点
						if(!("0".equals((String) obj[2])||"10".equals((String) obj[2])||"20".equals((String) obj[2]))){
							obj[0] = "    " + obj[0];
						}
					}
				}
			}
		}
		return listItem;
	}
	private boolean hasChildNode(List list,String parentCd){
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[]) list.get(i);
			if(obj[2]!=null&&parentCd.equals(obj[2]))
				return true;
		}
		return false;
	}
}


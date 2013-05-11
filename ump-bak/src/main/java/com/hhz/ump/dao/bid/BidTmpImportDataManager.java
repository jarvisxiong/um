package com.hhz.ump.dao.bid;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidProject;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.bid.BidTmpImportData;
import com.hhz.ump.util.Util;

@Service
@Transactional
public class BidTmpImportDataManager extends BaseService<BidTmpImportData, String> {
	@Autowired
	private BidTmpImportDataDao bidTmpImportDataDao;
	@Autowired
	protected BidLedgerManager bidLedgerManager;
	@Autowired
	protected BidSupManager bidSupManager;
	@Autowired
	protected BidProjectManager bidProjectManager;
	private static final Log log = LogFactory.getLog(BidTmpImportDataManager.class);
	public static final NumberFormat nf = NumberFormat.getInstance();
	public static final DecimalFormat df = new DecimalFormat("0.000");
	public static final DecimalFormat df2 = new DecimalFormat("0");

	public void saveBidTmpImportData(BidTmpImportData bidTmpImportData) {
		PowerUtils.setEmptyStr2Null(bidTmpImportData);
		bidTmpImportDataDao.save(bidTmpImportData);
	}

	public void deleteBidTmpImportData(String id) {
		bidTmpImportDataDao.delete(id);
	}

	@Override
	public HibernateDao<BidTmpImportData, String> getDao() {
		return bidTmpImportDataDao;
	}

	public void saveBidTmpImportDataList(List<BidTmpImportData> rowsData) {
		// 此可能情况影响性能，采用下面一种插入方法
		// for(BidTmpImportData tmp:rowsData){
		// this.saveBidTmpImportData(tmp);
		// }
		batchExecute(rowsData);

	}

	@Transactional
	public void batchExecute(List<BidTmpImportData> rowsData) {

		Session session = getDao().getSession();
		BidTmpImportData bidTmpImportData = null;
		for (int i = 0; i < rowsData.size(); i++) {
			bidTmpImportData = rowsData.get(i);
			saveBidTmpImportData(bidTmpImportData);
			if (i % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
	}

	/**
	 * 
	 * validateTmp:(验证初步导入的数据)
	 * 
	 * @param @param userId
	 * @param @param batchNo
	 * @param @param bidStatus 设定文件
	 * @return void DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	@SuppressWarnings("deprecation")
	public void validateTmp(String userId, String batchNo, String bidStatus) {

		Connection conn = null;
		try {
			conn = getDao().getSession().connection();
			Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(0, "BidDivisiton");
			map.put(1, userId);
			map.put(2, batchNo);
			map.put(3, bidStatus);
			// --分部分项（标底公司）
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidDivision(?,?,?,?)}", map, conn);

			// --分部分项（投标公司）
			map.remove(0);
			map.put(0, "BidDivisionSupRel");
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidDivisionSuperRel(?,?,?,?)}", map, conn);

			// --措施1
			map.remove(0);
			map.put(0, "BidMeasureSupRel");
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidMeasureSupRel(?,?,?,?)}", map, conn);

			// --措施2
			map.remove(0);
			map.put(0, "BidMeasureSupRel2");
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidMeasureSupRel2(?,?,?,?)}", map, conn);

			// --其他项目清单与计价汇总表
			map.remove(0);
			map.put(0, "BidOtherItemRel");
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidOtherItemRel(?,?,?,?)}", map, conn);

			// --零星工程费用表
			map.remove(0);
			map.put(0, "BidSporadicSupRel");
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidSporadicSupRel(?,?,?,?)}", map, conn);

			// --规费项目清单与计价表
			map.remove(0);
			map.put(0, "BidFeesSupRel");
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidFeesSupRel(?,?,?,?)}", map, conn);

			// --税金项目清单与计价表
			map.remove(0);
			map.put(0, "BidTaxsSupRel");
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidTaxsSupRel(?,?,?,?)}", map, conn);

			// --标段其他费用
			map.remove(0);
			map.put(0, "BidSupOtherRel");
			excuteProc("{ call powerdesk.bid_temp_pkg.validateBidSupOtherRel(?,?,?,?)}", map, conn);
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				log.error(e);
			}
		}

	}

	/**
	 * 
	 * findUnvalidateTmp:(搜索为验证通过的数据)
	 * 
	 * @param @param page
	 * @param @param values
	 * @param @return 设定文件
	 * @return Page<BidTmpImportData> DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public Page<BidTmpImportData> findUnvalidateTmp(Page<BidTmpImportData> page, Map<String, Object> values) {
		StringBuffer sbStr = new StringBuffer();
		sbStr.append(" from BidTmpImportData btid where btid.validDesc is not null and btid.creator = :creator");
		page = this.findPage(page, sbStr.toString(), values);
		return page;
	}

	/**
	 * 
	 * countUnvalidateTmp:(统计未通过验证的数据量)
	 * 
	 * @param @param values
	 * @param @return 设定文件
	 * @return Long DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	// 搜索是否存在验证未通过的结果
	public Long countUnvalidateTmp() {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("creator", SpringSecurityUtils.getCurrentUiid());
		StringBuffer sbStr = new StringBuffer();
		sbStr.append(" from BidTmpImportData btid where btid.validDesc is not null and btid.creator = :creator");
		return this.countHqlResult(sbStr.toString(), values);
	}

	/**
	 * 
	 * deleteUserImportedData:(这删除用户初步导入的数据)
	 * 
	 * @param @param userId 设定文件
	 * @return void DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public void deleteUserImportedData(String userId) {
		StringBuffer sbStr = new StringBuffer();
		sbStr.append("delete from BidTmpImportData btid where btid.creator = :creator");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("creator", userId);
		this.getDao().batchExecute(sbStr.toString(), values);
	}

	/**
	 * 
	 * excuteProc:(根据参数执行存储过程)
	 * 
	 * @param @param cmd
	 * @param @param map 设定文件
	 * @return void DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public void excuteProc(String cmd, Map<Integer, String> map, Connection conn) {
		CallableStatement callableStatement = null;
		try {
			log.info("excute:" + cmd);
			callableStatement = conn.prepareCall(cmd);
			for (int i = 0; i < map.size(); i++) {
				callableStatement.setString(i + 1, map.get(i));
			}
			callableStatement.execute();
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		} finally {
			try {
				if (callableStatement != null) {
					callableStatement.close();
				}
			} catch (Exception e2) {
				log.error(e2);
			}
		}
	}

	/**
	 * 
	 * doActiveImport:(调用存储过程导入正式表)
	 * 
	 * @param @param currentUiid
	 * @param @param bachNo 设定文件
	 * @return void DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	@SuppressWarnings("deprecation")
	public boolean doActiveImport(String currentUiid, String bachId, String bidLedgerId, String bidSupId,
			String bidProjectId, String qingdan, Long batchNo) {
		boolean flag = false;
		Connection conn = null;
		try {

			Session session = bidLedgerManager.getDao().getSession();
			BidLedger bidLedger = bidLedgerManager.getEntity(bidLedgerId);
			BidSup bidSup = bidSupManager.getEntity(bidSupId);
			BidProject bidProject = bidProjectManager.getEntity(bidProjectId);

			Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(0, currentUiid);
			map.put(1, bachId);
			map.put(2, "1");
			conn = session.connection();
			// /------2011-11-21修改
			// 如果导入清单数据
			if ("1".equals(qingdan)) {
				// 投标阶段
				if (BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())) {
					// 已经导入清单数据
					// if(bidLedgerManager.supHasImportedData(bidSup,bidProject
					// ,batchNo,"BidDivisiton")){
					// 清除相关数据
					bidLedgerManager.deleteBidDivisionSupRel(bidProject, batchNo);
					session.flush();
					bidLedgerManager.deleteBidDivisiton(null, bidProject, batchNo);
					// }
					// 导入清单
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidDivisiton(?,?,?)}", map, conn);
				}
				flag = true;
			}
			// 如果非导入清单数据
			else {
				// 导入分布分项
				if (this.getCntByTableName("BidDivisionSupRel", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidDivisionSupRel(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidDivisionSupRel(?,?,?)}", map, conn);
				}
				// 导入增添项(2012-6-25)
				if (this.getCntByTableName("BidDivisitonCust", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidDivisitonCust(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidDivisitonCust(?,?,?)}", map, conn);
				}
				// --导入措施1
				// 有新数据需要导入、投标状态
				if (BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())
						&& this.getCntByTableName("BidMeasureSupRel", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidMeasureSupRel(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidMeasureSupRel(?,?,?)}", map, conn);
				}

				// --导入措施2
				// 有新数据需要导入、投标状态
				if (BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())
						&& this.getCntByTableName("BidMeasureSupRel2", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidMeasureSupRel2(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidMeasureSupRel2(?,?,?)}", map, conn);
				}

				// --导入其他项目清单与计价汇总表
				// 有新数据需要导入、投标状态
				if (BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())
						&& this.getCntByTableName("BidOtherItemRel", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidOtherItemRel(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidOtherItemRel(?,?,?)}", map, conn);
				}

				// --导入零星工程费用表
				// 有新数据需要导入、投标状态
				if (BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())
						&& this.getCntByTableName("BidSporadicSupRel", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidSporadicSupRel(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidSporadicSupRel(?,?,?)}", map, conn);
				}

				// --导入规费项目清单与计价表
				// 有新数据需要导入、投标状态
				if (BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())
						&& this.getCntByTableName("BidFeesSupRel", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidFeesSupRel(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidFeesSupRel(?,?,?)}", map, conn);
				}

				// --导入税金项目清单与计价表
				// 有新数据需要导入、投标状态
				if (BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())
						&& this.getCntByTableName("BidTaxsSupRel", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidTaxsSupRel(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidTaxsSupRel(?,?,?)}", map, conn);
				}

				// --导入标段其他费用
				// 有新数据需要导入、投标状态
				if (BidLedgerManager.BID_STATUS_BIDDING.equals(bidLedger.getBidStatusCd())
						&& this.getCntByTableName("BidSupOtherRel", currentUiid, bachId, bidSupId, bidProjectId) > 0) {
					bidLedgerManager.deleteBidSupOtherRel(bidSup, bidProject, batchNo);
					session.flush();
					excuteProc("{ call powerdesk.bid_temp_pkg.importBidSupOtherRel(?,?,?)}", map, conn);
				}

				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
			}
		}

		return flag;
	}

	/**
	 * 
	 * getCntByTableName:(统计根据表名等参数获取即将导入数据的条数)
	 * 
	 * @param @param tableName
	 * @param @param userId
	 * @param @param batchId
	 * @param @param bidSupId
	 * @param @param bidProjectId
	 * @param @return 设定文件
	 * @return long DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public long getCntByTableName(String tableName, String userId, String batchId, String bidSupId, String bidProjectId) {
		StringBuffer sbSql = new StringBuffer().append(" from BidTmpImportData t where t.tableName = :tableName")
				.append("  and t.creator = :creator").append(" and t.bidSupId = :bidSupId").append(
						"  and t.bidProjectId = :bidProjectId ");
		// 条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("creator", userId);
		map.put("bidSupId", bidSupId);
		map.put("bidProjectId", bidProjectId);

		return this.countHqlResult(sbSql.toString(), map);

	}

	

	/**
	 * 
	 * getUnvalidateData:(分页搜索出未通过验证的数据)
	 * 
	 * @param @param page
	 * @param @param map
	 * @param @return 设定文件
	 * @return Page<BidTmpImportData> DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public Page<BidTmpImportData> getUnvalidateData(Page page, Map<String, Object> map) {

		StringBuffer sbSql = new StringBuffer().append(" from BidTmpImportData t where").append(
				"  t.creator = :creator").append(" and t.batchId = :batchId").append(" and t.validDesc is  not null");

		// 条件
		return this.findPage(page, sbSql.toString(), map);

	}


	/**
	 * 导入中间表
	 * 
	 * @param qingdan
	 * @return 是否成功 true-是 false否
	 * @throws
	 */
	public boolean insertToTemp(String qingdan, File importFile, BidSup bidSup, BidProject bidProject, String bachNo) {

		FileInputStream inpFile = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		boolean rtnFlg = false;
		try {
			inpFile = new FileInputStream(importFile);
			workbook = new HSSFWorkbook(inpFile);

			// 验证工程和供应商名
			sheet = workbook.getSheetAt(0);
			StringBuffer validateInfo = validateSupAndProject(bidSup, bidProject, sheet);
			if (validateInfo.length() > 0) {
				Struts2Utils.renderText(",error,false," + validateInfo.toString() + ",");
				return rtnFlg;
			}

			Long start = System.currentTimeMillis();
			log.info("开始导入EXCEL>>>>>>>>>>>");
			// 如果是导清单
			if ("1".equals(qingdan)) {
				sheet = workbook.getSheetAt(0);
				saveToTempTable("BidDivisiton", bidProject, bidSup, sheet, bachNo);
			} else {
				// 投标数据
				for (int sheetNumber = 0; sheetNumber <= 8; sheetNumber++) {
					sheet = workbook.getSheetAt(sheetNumber);
					switch (sheetNumber) {
					// (标底)分部分项
					case 0:
						saveToTempTable("BidDivisionSupRel", bidProject, bidSup, sheet, bachNo);
						break;
					// [增添项]BidDivisitonCust
					case 1:
						saveToTempTable("BidDivisitonCust", bidProject, bidSup, sheet, bachNo);
						break;
					// 措施项目清单与计价表(一)
					case 2:
						saveToTempTable("BidMeasureSupRel", bidProject, bidSup, sheet, bachNo);
						break;
					// 措施项目清单与计价表(二)
					case 3:
						saveToTempTable("BidMeasureSupRel2", bidProject, bidSup, sheet, bachNo);
						break;
					// 其他项目清单与计价汇总表
					case 4:
						saveToTempTable("BidOtherItemRel", bidProject, bidSup, sheet, bachNo);
						break;
					// [零星工程费用表]
					case 5:
						saveToTempTable("BidSporadicSupRel", bidProject, bidSup, sheet, bachNo);
						break;
					// [规费项目清单与计价表]BidFeesSupRel
					case 6:
						saveToTempTable("BidFeesSupRel", bidProject, bidSup, sheet, bachNo);
						break;
					// [税金项目清单与计价表]BidTaxsSupRel
					case 7:
						saveToTempTable("BidTaxsSupRel", bidProject, bidSup, sheet, bachNo);
						break;
					// [标段其他费用]BidSupOtherRel
					case 8:
						saveToTempTable("BidSupOtherRel", bidProject, bidSup, sheet, bachNo);
						break;

					}
				}
			}
			Long end = System.currentTimeMillis();
			log.info("end import data:<<<<<<<<<<<<<<<<<<<<<<<<<< 耗时(秒):"
					+ (end - start) / 1000.00);
			rtnFlg = true;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("导入出现投标excel 异常!", e);
		} finally {
			try {
				if (sheet != null) {
					sheet = null;
				}
				if (workbook != null) {
					workbook = null;
				}
				if (inpFile != null) {
					inpFile.close();
				}
			} catch (Exception e2) {
				log.error("关闭EXCEL文件异常", e2);
			}
		}
		return rtnFlg;
	}

	/**
	 * 
	 * validateSupAndProject:(验证供应商及工程)
	 * 
	 * @param @param sup
	 * @param @param project
	 * @param @param sheet
	 * @param @return 设定文件
	 * @return StringBuffer DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public StringBuffer validateSupAndProject(BidSup sup, BidProject project, HSSFSheet sheet) {
		StringBuffer sb = new StringBuffer();
		String proName = "";
		String supName = "";
		HSSFRow row = sheet.getRow(1);
		if (row != null && row.getCell(3) != null) {
			proName = row.getCell(3).toString();
		}
		row = sheet.getRow(2);
		if (row != null && row.getCell(3) != null) {
			supName = row.getCell(3).toString();
		}

		if (StringUtils.isBlank(proName)) {
			sb.append("EXCEL中[工程名称]为空;<br/>");
		} else {
			if (!proName.trim().equals(project.getProjectName().trim())) {
				sb.append("EXCEL中工程名称[" + proName + "]与选中的工程名称[" + project.getProjectName() + "]不一致;");
			}
		}
		if (StringUtils.isBlank(supName)) {
			sb.append("EXCEL中[投标单位]为空;<br/>");
		} else {
			if (!supName.trim().equals(sup.getSupName().trim())) {
				sb.append("EXCEL中投标单位[" + supName + "]与选中的投标单位[" + sup.getSupName() + "]不一致;");
			}
		}
		return sb;

	}
	
	/**
	 * 
	 * 保存投标数据
	 */
	private void saveToTempTable(String tableName, BidProject tmpBidProject, BidSup tmpBidSup, HSSFSheet sheet,
			String tmpBatchNo) {
		//开始时间
		long t1 = System.currentTimeMillis();
		//临时数据对象
		BidTmpImportData row = null;
		//返回临时数据
		BidTmpImportData bidTmpImportData = null;
		//单元格
		HSSFRow cells = null;
		// 当前用户
		String creator = SpringSecurityUtils.getCurrentUiid();
		// 总行数
		int totalCount = sheet.getLastRowNum();
		// 行数
		int realCount = 0;
		// 临时列表
		List<BidTmpImportData> bidTmpImportDataLists = new ArrayList<BidTmpImportData>();
		// 循环获取导入数据
		for (int startRow = 5; startRow < totalCount + 1; startRow++) {
			// 如果是标底公司
			row = new BidTmpImportData();
			cells = sheet.getRow(startRow);
			if (cells != null) {
				// 以每张表的项目编号或项目名称或费用名称判断是否还有下一行数据
				if ("BidDivisiton".equals(tableName) || "BidDivisionSupRel".equals(tableName)) {
					if ((cells.getCell(1) == null || StringUtils.isBlank(cells.getCell(1).toString()))) {
						continue;
					}
				} else {
					if ((cells.getCell(1) == null || StringUtils.isBlank(cells.getCell(1).toString()))) {
						continue;
					}
				}

				// 导入清单
				if ("BidDivisiton".equals(tableName)) {
					if ((cells.getCell(1) == null || StringUtils.isBlank(cells.getCell(1).toString()))||(cells.getCell(1)!=null&&cells.getCell(1).toString().length()<5)) {
						// 不做导入
						continue;
					} else {
						bidTmpImportData = buildRowData(row, "BidDivisiton", tmpBidProject, tmpBidSup, cells, startRow,
								tmpBatchNo);
					}
				}
				// 导入投标数据
				else if ("BidDivisionSupRel".equals(tableName)) {
					if ((cells.getCell(1) == null || StringUtils.isBlank(cells.getCell(1).toString()))) {
						// 不做导入
						continue;
					} else {
						bidTmpImportData = buildRowData(row, "BidDivisionSupRel", tmpBidProject, tmpBidSup, cells,
								startRow, tmpBatchNo);
					}
			      }
					// 导入增添项数据
				  else if ("BidDivisitonCust".equals(tableName)) {
						if ((cells.getCell(1) == null || StringUtils.isBlank(cells.getCell(1).toString()))) {
							// 不做导入
							continue;
						} else {
							bidTmpImportData = buildRowData(row, "BidDivisitonCust", tmpBidProject, tmpBidSup, cells,startRow, tmpBatchNo);
						}
					}  else {
					if ((cells.getCell(1) == null || StringUtils.isBlank(cells.getCell(1).toString()))) {
						// 不做导入
						continue;
					} else {
						bidTmpImportData = buildRowData(row, tableName, tmpBidProject, tmpBidSup, cells, startRow,
								tmpBatchNo);
					}

				}

				// 实际行数累加
				realCount++;

				// 创建人
				bidTmpImportData.setCreator(creator);
				// 加入保存列表
				bidTmpImportDataLists.add(bidTmpImportData);
				// 如果已经有1000条，则执行保存，然后释放内存，重新分配空间,继续构建导入数据
				if (realCount % 1000 == 0) {
					saveBidTmpImportDataList(bidTmpImportDataLists);
					bidTmpImportDataLists = new ArrayList<BidTmpImportData>();
				}
			}
		}
		if (bidTmpImportDataLists != null && bidTmpImportDataLists.size() > 0) {
			saveBidTmpImportDataList(bidTmpImportDataLists);
			bidTmpImportDataLists = null;
		}

		long t2 = System.currentTimeMillis();
		log.info("导入耗时(" + tableName + "): " + (t2 - t1) / 1000.00 + " 秒,一共" + realCount + "记录");
	}

	private BidTmpImportData buildRowData(BidTmpImportData row, String tableName, BidProject tmpBidProject,
			BidSup tmpBidSup, HSSFRow cells, Integer rowNum, String tmpBatchNo) {

		// 分部分项
		if ("BidDivisionSupRel".equals(tableName) || "BidDivisiton".equals(tableName) || "BidDivisitonCust".equals(tableName)) {
			// F1 DBID
			// F2 项目编号
			// F3 项目名称
			// F4 项目特征描述
			// F5 计量单位
			// F6 工程量
			// F7 综合单价
			// F8 合价
			// F9 分布分项ID
			row = makeBidDivisionSupRel(cells, row, tableName);
			// 行号
			if (cells != null && cells.getCell(0) != null && Util.isDecimal(cells.getCell(0).toString())) {// cells[0].getContents()				
				try {
					row.setRowNo(nf.parse(cells.getCell(0).toString().trim()).longValue());
				} catch (Exception e) {
					log.error(e);
				}
			}
		} else
		// 措施项目清单与计价表(一)
		if ("BidMeasureSupRel".equals(tableName)) {
			// F3 项目名称
			// F4 计算基础
			// F5 费率
			// F6 金额
			row = makeBidMeasureSupRel(cells, row);
		} else
		// 措施项目清单与计价表(二)
		if ("BidMeasureSupRel2".equals(tableName)) {
			// F2 项目编号
			// F3 项目名称
			// F5 计量单位
			// F6 工程量
			// F7 综合单价
			// F8 合价
			row = makeBidMeasureSupRel2(cells, row);
		} else
		// 其他项目清单与计价汇总表
		if ("BidOtherItemRel".equals(tableName)) {
			// F3 项目名称
			// F5计算单位
			// F8 金额
			// F9 备注
			row = makeBidOtherItemRel(cells, row);
		} else
		// [零星工程费用表]
		if ("BidSporadicSupRel".equals(tableName)) {
			// F3 项目名称
			// F5 单位
			// F6 暂定数量
			// F7 综合单价
			// F8 合价
			row = makeBidSporadicSupRel(cells, row);
		} else
		// [规费项目清单与计价表]
		if ("BidFeesSupRel".equals(tableName)) {
			// F3 项目名称
			// F4 计算基础
			// F5 费率
			// F6 金额
			row = makeBidFeesSupRel(cells, row);
		} else
		// [税金项目清单与计价表]BidTaxsSupRel
		if ("BidTaxsSupRel".equals(tableName)) {
			// F3 项目名称
			// F4 计算基础
			// F5 费率
			// F6 金额
			row = makeBidTaxsSupRel(cells, row);
		} else
		// [标段其他费用]BidSupOtherRel
		if ("BidSupOtherRel".equals(tableName)) {
			// F3 项目名称
			// F6 金额
			// F9 备注
			row = makeBidSupOtherRel(cells, row);
		}
		// 表名
		row.setTableName(tableName);
		if (row.getRowNo() == null) {
			row.setRowNo(Long.valueOf(rowNum - 4));
		}

		// 供应商ID
		row.setBidSupId(tmpBidSup.getBidSupId());
		// 工程ID
		row.setBidProjectId(tmpBidProject.getBidProjectId());
		row.setBatchId(tmpBatchNo);
		row.setBatchNo(tmpBidSup.getBidLedger().getBatchNo());
		return row;
	}

	public BidTmpImportData makeBidDivisionSupRel(HSSFRow cells, BidTmpImportData row, String tableName) {
		// F1 分部号
		// F2 项目编号
		// F3 项目名称
		// F4 项目特征描述
		// F5 计量单位
		// F6 工程量
		// F7 综合单价
		// F8 合价

		String tmp = null;
		int length = cells.getLastCellNum();
		//log.info("表格长度========================"+length);

		// 项目编号
		if (length > 1) {
			tmp = getCellContentsToString(cells, 1);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF2(tmp.trim());
				// 分部号
				if (tmp.length() > 4) {
					row.setF1(tmp.trim().substring(0, 4));
				}
			}
		}
		// 项目名称
		if (length > 2) {
			tmp = getCellContents(cells, 2);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF3(tmp);

			}
		}
		// 项目特征描述
		if (length > 3) {
			tmp = getCellContents(cells, 3);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF4(tmp);
			}
		}
		// 计量单位
		if (length > 4) {
			tmp = getCellContents(cells, 4);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF5(tmp);
			}
		}
		// 工程量
		if (length > 5) {
			tmp = getCellContents(cells, 5);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF6(tmp);
			}
		}
		if (!"BidDivisiton".equals(tableName)) {
			// 综合单价
			if (length > 6) {
				tmp = getCellContents(cells, 6);
				if (StringUtils.isNotBlank(tmp)) {
					row.setF7(tmp);
				}
			}

			// 合价
			if (length > 7) {
				tmp = getCellContents(cells, 7);
				if (StringUtils.isNotBlank(tmp)) {
					row.setF8(tmp);
				}
			}
		}
		return row;
	}

	public BidTmpImportData makeBidMeasureSupRel(HSSFRow cells, BidTmpImportData row) {
		// F3 项目名称
		// F4 计算基础
		// F5 费率
		// F6 金额
		int length = cells.getLastCellNum();
		//log.info("makeBidMeasureSupRel表格长度========================"+length);
		String tmp = null;
		if (length > 1) {
			tmp = getCellContents(cells, 1);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF3(tmp);
			}
		}
		if (length > 2) {
			tmp = getCellContents(cells, 2);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF4(tmp);
			}
		}
		if (length > 3) {
			tmp = getCellContents(cells, 3);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF5(tmp);
			}
		}
		if (length > 4) {
			tmp = getCellContents(cells, 4);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF6(tmp);
			}
		}
		return row;
	}

	public BidTmpImportData makeBidMeasureSupRel2(HSSFRow cells, BidTmpImportData row) {

		// F2 项目编号
		// F3 项目名称
		// F5 计量单位
		// F6 工程量
		// F7 综合单价
		// F8 合价
		int length = cells.getLastCellNum();
		//log.info("BidMeasureSupRel2表格长度========================"+length);
		String tmp = null;
		if (length > 1) {
			tmp = getCellContents(cells, 1);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF2(tmp);
			}
		}
		if (length > 2) {
			tmp = getCellContents(cells, 2);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF3(tmp);
			}
		}
		if (length > 3) {
			tmp = getCellContents(cells, 3);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF5(tmp);
			}
		}
		if (length > 4) {
			tmp = getCellContents(cells, 4);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF6(tmp);
			}
		}
		if (length > 5) {
			tmp = getCellContents(cells, 5);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF7(tmp);
			}
		}
		if (length > 6) {
			tmp = getCellContents(cells, 6);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF8(tmp);
			}
		}
		return row;
	}

	/**
	 * 获取单元格的值
	 * 
	 */
	private String getCellContents(HSSFRow cells, int colIndex) {
		if (cells != null && cells.getCell(colIndex) != null) {
			HSSFCell cell = cells.getCell(colIndex);
			//如果是公式类型
			 if (HSSFCell.CELL_TYPE_FORMULA == cell.getCellType()) {
				try {
					String rs = String.valueOf(cell.getSheet().getWorkbook().getCreationHelper()
							.createFormulaEvaluator().evaluate(cell).getNumberValue());
					if (Util.isDecimal(rs))
						return rs;
					else
						return "0";
				} catch (Exception e) {
					log.equals("公司涉及的数据有问题");
					return "0";
				}
			}
			 //如果是数值类型
			 else if(HSSFCell.CELL_TYPE_NUMERIC== cell.getCellType()){
				 try {
					 
					return df.format(cell.getNumericCellValue());
				} catch (Exception e) {
					log.equals("公司涉及的数据有问题");
					return "0";
				}
			 }
			 //如果是空类型
			 else if(HSSFCell.CELL_TYPE_BLANK== cell.getCellType())
				return "";
			 //如果是字符类型
			 else if(HSSFCell.CELL_TYPE_STRING== cell.getCellType())
				return cell.getStringCellValue().trim();
			 //其他情况
			else 
				return cell.toString();
		} else
			return "";
	}
	
	
	private String getCellContentsToString(HSSFRow cells, int colIndex) {
		if (cells != null && cells.getCell(colIndex) != null) {
			HSSFCell cell = cells.getCell(colIndex);
			if(HSSFCell.CELL_TYPE_NUMERIC== cell.getCellType()){
				try {
					df.setParseIntegerOnly(true);
					return df2.format(cell.getNumericCellValue());
				} catch (Exception e) {
					log.error(e);
					return "";
				}
			}else if(HSSFCell.CELL_TYPE_STRING== cell.getCellType())
				return cell.getStringCellValue().trim();
			else
				return cell.toString();
						
		}else
		return "";
	}

	public BidTmpImportData makeBidOtherItemRel(HSSFRow cells, BidTmpImportData row) {
		// F3 项目名称
		// F5计算单位
		// F8 金额
		// F9 备注
		int length = cells.getLastCellNum();
		String tmp = getCellContents(cells, 1);
		if (length > 1 && StringUtils.isNotBlank(tmp)) {
			row.setF3(tmp);
		}

		tmp = getCellContents(cells, 2);
		if (length > 2 && StringUtils.isNotBlank(tmp)) {
			row.setF5(tmp);
		}
		tmp = getCellContents(cells, 3);
		if (length > 3 && StringUtils.isNotBlank(tmp)) {
			row.setF8(tmp);
		}
		tmp = getCellContents(cells, 4);
		if (length > 4 && StringUtils.isNotBlank(tmp)) {
			row.setF9(tmp);
		}
		return row;
	}

	public BidTmpImportData makeBidSporadicSupRel(HSSFRow cells, BidTmpImportData row) {
		// F3 项目名称
		// F5 单位
		// F6 暂定数量
		// F7 综合单价
		// F8 合价
		String tmp = null;
		int length = cells.getLastCellNum();

		if (length > 1) {
			tmp = getCellContents(cells, 1);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF3(tmp);
			}
		}
		if (length > 2) {
			tmp = getCellContents(cells, 2);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF5(tmp);
			}
		}
		if (length > 3) {
			tmp = getCellContents(cells, 3);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF6(tmp);
			}
		}
		if (length > 4) {
			tmp = getCellContents(cells, 4);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF7(tmp);
			}
		}
		if (length > 5) {
			tmp = getCellContents(cells, 5);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF8(tmp);
			}
		}
		return row;
	}

	public BidTmpImportData makeBidFeesSupRel(HSSFRow cells, BidTmpImportData row) {
		// F3 项目名称
		// F4 计算基础
		// F5 费率
		// F6 金额
		String tmp = null;
		int length = cells.getLastCellNum();
		if (length > 1) {
			tmp = getCellContents(cells, 1);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF3(tmp);
			}
		}
		if (length > 2) {
			tmp = getCellContents(cells, 2);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF4(tmp);
			}
		}

		if (length > 3) {
			tmp = getCellContents(cells, 3);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF5(tmp);
			}
		}

		if (length > 4) {
			tmp = getCellContents(cells, 4);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF6(tmp);
			}
		}
		return row;
	}

	public BidTmpImportData makeBidTaxsSupRel(HSSFRow cells, BidTmpImportData row) {
		// F3 项目名称
		// F4 计算基础
		// F5 费率
		// F6 金额
		String tmp = null;
		int length = cells.getLastCellNum();
		if (length > 1) {
			tmp = getCellContents(cells, 1);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF3(tmp);
			}
		}
		if (length > 2) {
			tmp = getCellContents(cells, 2);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF4(tmp);
			}
		}

		if (length > 3) {
			tmp = getCellContents(cells, 3);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF5(tmp);
			}
		}
		if (length > 4) {
			tmp = getCellContents(cells, 4);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF6(tmp);
			}
		}
		return row;
	}

	public BidTmpImportData makeBidSupOtherRel(HSSFRow cells, BidTmpImportData row) {
		// F3 项目名称
		// F6 金额
		// F9 备注
		String tmp = null;
		int length = cells.getLastCellNum();

		if (length > 1) {
			tmp = getCellContents(cells, 1);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF3(tmp);
			}
		}
		if (length > 2) {
			tmp = getCellContents(cells, 2);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF6(tmp);
			}
		}
		if (length > 3) {
			tmp = getCellContents(cells, 3);
			if (StringUtils.isNotBlank(tmp)) {
				row.setF9(tmp);
			}
		}
		return row;
	}
}

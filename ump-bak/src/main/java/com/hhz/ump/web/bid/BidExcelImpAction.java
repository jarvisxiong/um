/**  
 * BidExcelImpAction.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-10-25        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
 */

package com.hhz.ump.web.bid;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidDivisionSupRelManager;
import com.hhz.ump.dao.bid.BidDivisitonManager;
import com.hhz.ump.dao.bid.BidFeesSupRelManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidMeasureSupRel2Manager;
import com.hhz.ump.dao.bid.BidMeasureSupRelManager;
import com.hhz.ump.dao.bid.BidOtherItemRelManager;
import com.hhz.ump.dao.bid.BidProjectManager;
import com.hhz.ump.dao.bid.BidSporadicSupRelManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.bid.BidSupOtherRelManager;
import com.hhz.ump.dao.bid.BidTaxsSupRelManager;
import com.hhz.ump.dao.bid.BidTmpImportDataManager;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidProject;
import com.hhz.ump.entity.bid.BidSup;

/**
 *
 * @author zhongyubing
 * @version
 * @since Ver 1.1
 * @Date 2011-10-25 下午02:10:09
 * 
 * 
 */
public class BidExcelImpAction extends CrudActionSupport<BidLedger> {

	private static final long serialVersionUID = 7620904614793696595L;
	@Autowired
	protected BidLedgerManager bidLedgerManager;
	@Autowired
	protected BidProjectManager bidProjectManager;
	@Autowired
	protected BidDivisitonManager bidDivisitonManager;
	@Autowired
	protected BidSupManager bidSupManager;
	@Autowired
	protected BidDivisionSupRelManager bidDivisionSupRelManager;
	@Autowired
	protected BidMeasureSupRelManager bidMeasureSupRelManager;
	@Autowired
	protected BidMeasureSupRel2Manager bidMeasureSupRel2Manager;
	@Autowired
	protected BidOtherItemRelManager bidOtherItemRelManager;
	@Autowired
	protected BidSporadicSupRelManager bidSporadicSupRelManager;
	@Autowired
	protected BidFeesSupRelManager bidFeesSupRelManager;
	@Autowired
	protected BidTaxsSupRelManager bidTaxsSupRelManager;
	@Autowired
	protected BidSupOtherRelManager bidSupOtherRelManager;

	@Autowired
	protected BidTmpImportDataManager bidTmpImportDataManager;
	/**
	 * 标段ID
	 */
	private String bidLedgerId;
	/**
	 * 标段
	 */
	private BidLedger bidLedger;
	/**
	 * 供应商ID
	 */
	private String bidSupId;
	/**
	 * 工程ID
	 */
	private String projectId;
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;

	/**
	 * 导入Excel需要的文件
	 */
	private File importFile;
	/**
	 * 导入Excel需要的文件
	 */
	private File importFile2;
	private String bachNo = "";
	/**
	 * 显示的招标台帐列表
	 */
	private List<BidLedger> loadBids;
	/**
	 * 工程
	 */
	private List<BidProject> bidPros;

	/**
	 * 供应商
	 */
	private List<BidSup> bidSups;
	/**
	 * 工程
	 */
	BidProject bidProject = null;
	/**
	 * 供应商
	 */
	BidSup bidSup = null;
	private static final Log log = LogFactory.getLog(BidExcelImpAction.class);
	/**
	 * 页对象
	 */
	Page voPage = new Page(15);

	@Override
	public String delete() throws Exception {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public String deleteBatch() throws Exception {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public String input() throws Exception {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public String list() throws Exception {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	protected void prepareModel() throws Exception {

		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public BidLedger getModel() {

		// TODO Auto-generated method stub
		return null;

	}
	/**
	 * PD端工程的对应供应商的导入和导出界面
	 */
	public String bisexcel() {
		if (StringUtils.isNotBlank(this.getBidLedgerId())) {
			//搜索标段
			bidLedger = bidLedgerManager.getEntity(this.getBidLedgerId());
			if (bidLedger != null) {
				//获取已经接受的供应商
				bidSups = bidSupManager.getReceivedSup(bidLedger.getBidSups());
				//标段对应的工程
				bidPros = bidProjectManager.getEnableProjectList(getBidLedgerId());
			}
		}
		return "bisexcel";
	}
	
	/**
	 *验证导入导出的表单
	 */
	public String validateForm() {
		// 如果工程ID不为空
		if (StringUtils.isNotBlank(this.getProjectId())) {
			// 工程搜索
			bidProject = bidProjectManager.getEntity(this.getProjectId());
			if (bidProject != null) {
				bidLedger = bidProject.getBidLedger();
				// 校验是否存在标底公司
				if (!bidSupManager.hasBiaoDiSup(bidProject.getBidLedger().getBidLedgerId())) {
					Struts2Utils.renderText(",error,<font color='red'>标段不存在标底单位！</font>");
					return null;
				}
			} else {
				Struts2Utils.renderText(",error,工程为空;请选择工程！");
				return null;
			}

		} else {// 如果工程ID为空
			Struts2Utils.renderText(",error,工程为空;请选择工程！");
			return null;
		}

		// 如果供应商ID不为空
		if (StringUtils.isNotBlank(this.getBidSupId())) {
			//
			bidSup = bidSupManager.getEntity(this.getBidSupId());
			if (bidSup != null) {
				// 如果是投标单位,需检测是否标底公司已经导入,如果标底公司没有导入则返回
				if (BidSupManager.SUP_PROVIDOR.equals(bidSup.getTypeCd())
						&& !bidDivisitonManager.hasImportedBiaodiData(bidProject.getBidLedger().getBidLedgerId())) {
					Struts2Utils.renderText(",error,标底单位还未导入数据,待标底单位导入之后,其他供应商才能导入数据！");
					return null;
				}
			} else {
				Struts2Utils.renderText(",error,供应商为空;请重新选择供应商！");
				return null;
			}

		} else {// 如果供应商ID为空
			Struts2Utils.renderText(",error,供应商为空;请选择供应商！");
			return null;
		}

		// 标底单位
		if (BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())) {
			// 只有在邀标、投标状态才能导入
			if (BidLedgerManager.BID_STATUS_INVITE.equals(bidProject.getBidLedger().getBidStatusCd())
					|| BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
			} else {
				Struts2Utils.renderText(",error,标底单位只能在[邀标]或[投标]状态才能导入！");
				return null;
			}
		} else
		// 非标底单位
		if (BidSupManager.SUP_PROVIDOR.equals(bidSup.getTypeCd())) {
			// 只有在投标状态才能导入
			if (!BidLedgerManager.BID_STATUS_BIDDING.equals(bidProject.getBidLedger().getBidStatusCd())) {
				Struts2Utils.renderText(",error,投标单位只能在[投标]状态才能导入！");
				return null;
			}
		}
		return "true";
	}

	
	/**
	 *EXCEL导入清单或者投标数据
	 */
	public String importDataValidate() {

		// 删除本用户上一次操作的数据
		bidTmpImportDataManager.deleteUserImportedData(SpringSecurityUtils.getCurrentUiid());

		// 导入开始时间
		bachNo = UUID.randomUUID().toString() + String.valueOf(new Date().getTime());
		// 表格行数
		if ("true".equals(validateForm())) {

			// 导入临时表
			String qingdan = Struts2Utils.getParameter("qingdan");
			boolean rtnFlg = bidTmpImportDataManager.insertToTemp(qingdan,importFile,bidSup,bidProject,bachNo);
			if (!rtnFlg)
				return null;

			// 验证数据字段合法性  **暂时取消验证 2012-6-26**
			// 默认为1-投标
			//doValidate(bachNo, "1");
		}
		// 搜索是否存在验证未通过数据,如果存在，则返回此此导入的相关信息
		if (bidTmpImportDataManager.countUnvalidateTmp() > 0) {
			Struts2Utils.renderText(",unvalidate," + bachNo + "," + bidLedger.getBidLedgerId() + "," + bidSupId + "," + projectId + ","
					+ bidLedger.getBatchNo() + ",");
		} else {
			Struts2Utils.renderText(",success," + bachNo + "," + bidLedger.getBidLedgerId() + "," + bidSupId + "," + projectId + "," + bidLedger.getBatchNo()
					+ ",");
		}

		return null;
	}

	

	/**
	 * 
	 * 实际数据库导入,将临时表导入正式数据库表
	 */
	public String doActiveImport() {
		//开始时间
		Long start = System.currentTimeMillis();
		// 是否清单,1-是0-非
		String qingdan = Struts2Utils.getParameter("qingdan");
		// 轮次号
		String batchNo = Struts2Utils.getParameter("batchNo");
		//当前用户ID
		String curUiid = SpringSecurityUtils.getCurrentUiid();
		// 导入数据
		boolean flag = bidTmpImportDataManager.doActiveImport(curUiid, bachNo, bidLedgerId, bidSupId, projectId, qingdan, Long.parseLong(batchNo));
		// 如果导入成功,搜索是否存在有清单项未导入的情况
		if (flag) {
			// 搜索条件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("projectId", projectId);
			map.put("bidSupId", bidSupId);
			map.put("batchno", Long.parseLong(batchNo));
			// 不为清单
			if ("1".equals(qingdan)) {
				Struts2Utils.renderText("success,0");
			} else {
				Long unimportNum = bidDivisionSupRelManager.countUnimportedData(map);
				if (unimportNum > 0) {
					Struts2Utils.renderText("success,1," + unimportNum + ",");

				} else {
					Struts2Utils.renderText("success,");
				}
			}

			// 删除本用户的临时数据
			bidTmpImportDataManager.deleteUserImportedData(curUiid);
		} else {
			Struts2Utils.renderText("error");
		}
		Long end = System.currentTimeMillis();
		log.info("####doActiveImport总耗时(秒):" + (end - start) / 1000.00);
		return null;
	}

	/**
	 * 
	 * 验证是否已经导入过数据
	 */
	public String valiHasImported() {
		//验证类型，原本有清单和投标数据两种
		String validateType = Struts2Utils.getParameter("validateType");
		//轮次号
		String batchno = Struts2Utils.getParameter("batchno");
		//工程
		String tmpProjectId = Struts2Utils.getParameter("projectId");
		//供应商
		String tmpBidSupId = Struts2Utils.getParameter("bidSupId");
		// 如果验证清单
		if ("qingdan".equals(validateType)) {
			// 搜索本轮是否已经导入过清单
			if (bidDivisitonManager.hasImportedBiaodiData(tmpProjectId, batchno)) {
				Struts2Utils.renderText(";error;已经导入过清单（如果再次导入，会将原有清单,供应商已经导入的数据覆盖清除,是否继续?）;");
			} else {
				Struts2Utils.renderText(";success;");
			}
		} else {
			// 如果是导入数据（即标底数据或投标数据）
			BidSup sup = bidSupManager.getEntity(tmpBidSupId);
			BidProject project = bidProjectManager.getEntity(tmpProjectId);
			if (bidLedgerManager.supHasImportedDataVersion2(sup, project, Long.parseLong(batchno))) {
				Struts2Utils.renderText(";error;已经导入过数据（如果再次导入，会将原有已经导入的数据覆盖清除,是否继续?）;");
			} else {
				Struts2Utils.renderText(";success;");
			}
		}
		return null;
	}
	/**
	 * 
	 * 标底是否已经导入过清单
	 */
	public String diaodiHasImported() {
		// 轮次
		String batchno = Struts2Utils.getParameter("batchno");
		// 工程
		String tmpProjectId = Struts2Utils.getParameter("projectId");		
		// 搜索本轮是否已经导入过清单
		if (bidDivisitonManager.hasImportedBiaodiData(tmpProjectId, batchno)) {
			Struts2Utils.renderText(";success;");
		} else {
			Struts2Utils.renderText(";error;");
		}
		return null;
	}
	
	/**
	 * 
	 * 获取验证未通过的数据列表
	 */
	public String unvalidateData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("creator", SpringSecurityUtils.getCurrentUiid());
		map.put("batchId", bachNo);
		// 默认50条提示数据
		voPage.setPageSize(50);
		voPage = bidTmpImportDataManager.getUnvalidateData(voPage, map);
		return "unvalidateData";
	}

	public String findUnvalidateData() {
		return "unvalidateData";
	}
	
	/**
	 * 
	 * 执行临时数据表的验证
	 */
	private String doValidate(String batchNo, String bidStatus) {
		//执行临时数据表的验证
		bidTmpImportDataManager.validateTmp(SpringSecurityUtils.getCurrentUiid(), batchNo, bidStatus);
		return null;
	}

	

	

	

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}

	public String getBidSupId() {
		return bidSupId;
	}

	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public File getImportFile() {
		return importFile;
	}

	public void setImportFile(File importFile) {
		this.importFile = importFile;
	}

	public String getBachNo() {
		return bachNo;
	}

	public void setBachNo(String bachNo) {
		this.bachNo = bachNo;
	}

	public List<BidLedger> getLoadBids() {
		return loadBids;
	}

	public void setLoadBids(List<BidLedger> loadBids) {
		this.loadBids = loadBids;
	}

	public List<BidSup> getBidSups() {
		return bidSups;
	}

	public void setBidSups(List<BidSup> bidSups) {
		this.bidSups = bidSups;
	}

	public List<BidProject> getBidPros() {
		return bidPros;
	}

	public void setBidPros(List<BidProject> bidPros) {
		this.bidPros = bidPros;
	}

	public Page getVoPage() {
		return voPage;
	}

	public void setVoPage(Page voPage) {
		this.voPage = voPage;
	}

	public File getImportFile2() {
		return importFile2;
	}

	public void setImportFile2(File importFile2) {
		this.importFile2 = importFile2;
	}

}

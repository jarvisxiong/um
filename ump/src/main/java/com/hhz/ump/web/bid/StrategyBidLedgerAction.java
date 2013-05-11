package com.hhz.ump.web.bid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bid.BidLedgAttaRelManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidSupAttachRelManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.bid.BidLedgAttaRel;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.res.ResApproveInfo;

@Namespace("/bid")
public class StrategyBidLedgerAction  extends CrudActionSupport<BidLedger> {

	
	private static final long serialVersionUID = -764276119054180737L;
	
	/**
	 * service注入点
	 */
	@Autowired
	protected BidLedgerManager bidLedgerManager;
	@Autowired
	protected BidSupAttachRelManager bidSupAttachRelManager;
	@Autowired
	protected BidSupManager bidSupManager;
	@Autowired
	protected ResApproveInfoManager resApproveInfoManager;
	@Autowired
	protected BidLedgAttaRelManager bidLedgAttaRelManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	
	/**
	 * 标段台帐
	 */
	private BidLedger entity;
	
	//投标轮次评标附件表
	private BidLedgAttaRel attaRel;
	/**
	 * 标段台帐ID
	 */
	private String bidLedgerId;
	
	/**
	 * 评标轮次
	 */
	private long batchNo;


	/**
	 * 分页搜索
	 */
	private Page voPage = new Page(10);
	/**
	 * 网批明细
	 */
	private ResApproveInfo resApproveInfo;//邀标网批
	private ResApproveInfo callResApproveInfo;//招标文件网批
	private ResApproveInfo bidResApproveInfo;//定标网批编号
	/**
	 * 附件列表
	 */
	private AppAttachFile attaTendFile;
	private AppAttachFile attaDrawFile;
	private AppAttachFile attaList;
	private AppAttachFile attaTechStan;	
	
	/**
	 * 评标附件列表
	 */
	private List<AppAttachFile> attaEval1s;
	private List<AppAttachFile> attaEval2s;
	private List<AppAttachFile> attaEval3s;
	private List<AppAttachFile> attaEval4s;
	private List<AppAttachFile> attaEval5s;
	private List<AppAttachFile> attaEval6s;
	private List<AppAttachFile> attaEval7s;
	
	
	

	//招标附件 2012-06-27
	private AppAttachFile callAttachFile;

	
	public List<AppAttachFile> getAttaEval2s() {
		return attaEval2s;
	}


	public void setAttaEval2s(List<AppAttachFile> attaEval2s) {
		this.attaEval2s = attaEval2s;
	}


	public List<AppAttachFile> getAttaEval3s() {
		return attaEval3s;
	}


	public void setAttaEval3s(List<AppAttachFile> attaEval3s) {
		this.attaEval3s = attaEval3s;
	}


	public List<AppAttachFile> getAttaEval4s() {
		return attaEval4s;
	}


	public void setAttaEval4s(List<AppAttachFile> attaEval4s) {
		this.attaEval4s = attaEval4s;
	}


	public List<AppAttachFile> getAttaEval5s() {
		return attaEval5s;
	}


	public void setAttaEval5s(List<AppAttachFile> attaEval5s) {
		this.attaEval5s = attaEval5s;
	}


	public List<AppAttachFile> getAttaEval9s() {
		return attaEval9s;
	}


	public void setAttaEval9s(List<AppAttachFile> attaEval9s) {
		this.attaEval9s = attaEval9s;
	}


	public List<AppAttachFile> getAttaEval6s() {
		return attaEval6s;
	}


	public void setAttaEval6s(List<AppAttachFile> attaEval6s) {
		this.attaEval6s = attaEval6s;
	}


	public List<AppAttachFile> getAttaEval7s() {
		return attaEval7s;
	}


	public void setAttaEval7s(List<AppAttachFile> attaEval7s) {
		this.attaEval7s = attaEval7s;
	}


	private List<AppAttachFile> attaEval9s;
	
	/**
	 * 是否存在未接受的客户
	 */
	private String hasSupNotReceive = "0";
	/**
	 * 存在未确认保证金
	 */
	private String hasGuarNotConfirmed = "0";
	//是否上传评标文件
	private String hasevalFiles = "0";
	

	public String getHasevalFiles() {
		return hasevalFiles;
	}


	public void setHasevalFiles(String hasevalFiles) {
		this.hasevalFiles = hasevalFiles;
	}


	@Override
	public String input() throws Exception {
		
		//RoleUtil.validateRole("A_BID_STRA_EVAL")
		
		return "input";
	}
	
	
	/**
	 * 查看明细
	 * 
	 * @param id
	 * @return
	 */
	public void prepareDetail() throws Exception {
		prepareModel();
	}
	
	public void prepareLoadEval() throws Exception{
		prepareModel();
	}
	
	public void prepareLoadEvalLast() throws Exception{
		prepareModel();
	}
	/**
	 * 明细页面
	 * @return
	 * @throws Exception 
	 */
	public String detail() throws Exception{
		if(entity!=null){
			bidLedgerId=entity.getBidLedgerId();
		}
		try {
			//图纸
			if ("1".equals(entity.getAttaDrawFileFlg())) {
				attaDrawFile = bidLedgerManager.listAttach(bidLedgerId, "bidLedgerGurantee", "attaDrawFileFlg");
			}
			//清单
			if ("1".equals(entity.getAttaListFlg())) {
				attaList = bidLedgerManager.listAttach(bidLedgerId, "bidLedgerGurantee", "attaListFlg");
			}
			//技术标
			if ("1".equals(entity.getAttaTechStanFlg())) {
				attaTechStan = bidLedgerManager.listAttach(bidLedgerId, "bidLedgerGurantee", "attaTechStanFlg");
			}
			//招标文件
			if ("1".equals(entity.getAttaTendFileFlg())) {
				attaTendFile = bidLedgerManager.listAttach(bidLedgerId, "bidLedgerGurantee", "attaTendFileFlg");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//loadAttaEval();
		
		
		//获取供应商列表
		List<BidSup> bidSups = entity.getBidSups();		
		if (bidSups != null) {
			//是否存在供应商未接受邀标的情况
			for (BidSup vo : bidSups) {			
				if (StringUtils.isBlank(vo.getReceiveStatusCd()) && bidSupManager.SUP_PROVIDOR.equals(vo.getTypeCd())) {
					hasSupNotReceive = "1";
					break;
				}
			}
			//查看是否存在未确认保证金的情况
			for (BidSup vo : bidSups) {	
				//提交保证金，但没确认保证金
				if (StringUtils.isNotBlank(vo.getSubmitAttachFlg()) &&"1".equals(vo.getSubmitAttachFlg())&&(StringUtils.isBlank(vo.getGuarAttaConfFlg())||"0".equals(vo.getGuarAttaConfFlg()))) {
					hasGuarNotConfirmed="1";
					break;
				}
			}
		}
		
		BidLedgAttaRel attaRel = bidLedgAttaRelManager.getByBidLedgIdAndBatchNo(bidLedgerId, entity.getBatchNo());
		//是否上传评标文件
		if("1".equals(attaRel.getBidAttaEval1Flg())){
			hasevalFiles = "1";
		}

		//招标附件 
		if(entity != null && StringUtils.isNotBlank(entity.getCallResAttachId())){
			callAttachFile = appAttachFileManager.getEntity(entity.getCallResAttachId());
		}
		
		return "detail";
	}
	
	public String hasEvalAtta(){
		
		BidLedgAttaRel attaRel = bidLedgAttaRelManager.getByBidLedgIdAndBatchNo(bidLedgerId, batchNo);
		//是否上传评标文件
		if("1".equals(attaRel.getBidAttaEval1Flg())){
			hasevalFiles = "1";
			Struts2Utils.renderText("hasEval");
		}
		
		return null;
	}


	/**
	 * 按轮次加载评标文件
	 * @throws Exception 
	 */
	public String loadEval() throws Exception{
		if(batchNo==0) {
			batchNo = entity.getBatchNo();
		}
		attaRel =  bidLedgAttaRelManager.getByBidLedgIdAndBatchNo(getId(), batchNo);
		loadAttaEval();
	 if("1".equals(entity.getSrcTypeCd()))
		return "loadEval";
	 else
		return "loadEvalForOther";
	}
	
	/**
	 * 按轮次加载评标文件
	 * @throws Exception 
	 */
	public String loadEvalLast() throws Exception{
		
		batchNo = entity.getBatchNo();
		
		attaRel =  bidLedgAttaRelManager.getByBidLedgIdAndBatchNo(getId(), batchNo);
		loadAttaEval();
		//1：战略
	 if("1".equals(entity.getSrcTypeCd()))
		return "loadEvalView";
	 else
		 //非战略
		return "loadEvalForOtherView";
	}
	
	private void loadAttaEval() throws Exception{
		if(attaRel!=null){
			if("1".equals(attaRel.getBidAttaEval1Flg())) {
				attaEval1s = bidLedgerManager.listAttachs(attaRel.getBidLedgAttaRelId(), "bidLedgerEval", "bidAttaEval1Flg");
			}
			
			if("1".equals(attaRel.getBidAttaEval2Flg())) {
				attaEval2s = bidLedgerManager.listAttachs(attaRel.getBidLedgAttaRelId(), "bidLedgerEval", "bidAttaEval2Flg");
			}
			
			if("1".equals(attaRel.getBidAttaEval3Flg())) {
				attaEval3s = bidLedgerManager.listAttachs(attaRel.getBidLedgAttaRelId(), "bidLedgerEval", "bidAttaEval3Flg");
			}
			
			if("1".equals(attaRel.getBidAttaEval4Flg())) {
				attaEval4s = bidLedgerManager.listAttachs(attaRel.getBidLedgAttaRelId(), "bidLedgerEval", "bidAttaEval4Flg");
			}

			if("1".equals(attaRel.getBidAttaEval5Flg())) {
				attaEval5s = bidLedgerManager.listAttachs(attaRel.getBidLedgAttaRelId(), "bidLedgerEval", "bidAttaEval5Flg");
			}
			
			if("1".equals(attaRel.getBidAttaEval6Flg())) {
				attaEval6s = bidLedgerManager.listAttachs(attaRel.getBidLedgAttaRelId(), "bidLedgerEval", "bidAttaEval6Flg");
			}
			
			if("1".equals(attaRel.getBidAttaEval7Flg())) {
				attaEval7s = bidLedgerManager.listAttachs(attaRel.getBidLedgAttaRelId(), "bidLedgerEval", "bidAttaEval7Flg");
			}
			
			if("1".equals(attaRel.getBidAttaEval9Flg())) {
				attaEval9s = bidLedgerManager.listAttachs(attaRel.getBidLedgAttaRelId(), "bidLedgerEval", "bidAttaEval9Flg");
			}
		}
	}
	/**
	 * 分页搜索列表
	 * @return
	 */
	public String loadList(){		
		String pageNo = Struts2Utils.getParameter("pageNo");
		//高级搜索条件
		//材料设备名称
		String bidSectionName = Struts2Utils.getParameter("bidSectionName");
		String bidStatusCd = Struts2Utils.getParameter("bidStatusCd");
		//保证金截止日期
		String lastGuarDate1 = Struts2Utils.getParameter("lastGuarDate1");
		String lastGuarDate2 = Struts2Utils.getParameter("lastGuarDate2");
		//应标截止日期
		String lastReceDate1 = Struts2Utils.getParameter("lastReceDate1");
		String lastReceDate2 = Struts2Utils.getParameter("lastReceDate2");
		//
		String createdDate1 = Struts2Utils.getParameter("createdDate1");
		String createdDate2 = Struts2Utils.getParameter("createdDate2");
		//网批CD
		String resCd = Struts2Utils.getParameter("resCd");
		
		//搜索条件MAP
		Map<String, Object> values = new HashMap<String, Object>();
		//战略材料投标类型
		values.put("srcTypeCd", "1");
		//搜索语句
		StringBuffer hql=new StringBuffer().append(" from BidLedger t where t.srcTypeCd = :srcTypeCd ");		
		
		
		if (StringUtils.isNotBlank(bidSectionName)) {
			hql.append(" and t.bidSectionName like :bidSectionName ");
			values.put("bidSectionName", "%"+bidSectionName+"%");
		}
		if (StringUtils.isNotBlank(resCd)) {
			
		}
		
		if (StringUtils.isNotBlank(lastGuarDate1)) {			
			hql.append(" and t.lastGuarDate >= to_date(:lastGuarDate1,'yyyy-mm-dd')");
			values.put("lastGuarDate1", lastGuarDate1);
		}
		
		if (StringUtils.isNotBlank(lastGuarDate2)) {
			hql.append(" and t.lastGuarDate < to_date(:lastGuarDate2,'yyyy-mm-dd')");
			values.put("lastGuarDate2", formatedDate(lastGuarDate2));
		}
		
		if (StringUtils.isNotBlank(lastReceDate1)) {
			hql.append(" and t.lastReceDate >= to_date(:lastReceDate1,'yyyy-mm-dd')");
			values.put("lastReceDate1", lastReceDate1);
		}
		
		if (StringUtils.isNotBlank(lastReceDate2)) {
			hql.append(" and t.lastReceDate < to_date(:lastReceDate2,'yyyy-mm-dd')");
			values.put("lastReceDate2", formatedDate(lastReceDate2));
		}
		if (StringUtils.isNotBlank(createdDate1)) {
			hql.append(" and t.createdDate >= to_date(:createdDate1,'yyyy-mm-dd')");
			values.put("createdDate1", createdDate1);
		}
		
		if (StringUtils.isNotBlank(createdDate2)) {
			hql.append(" and t.createdDate < to_date(:createdDate2,'yyyy-mm-dd')");
			values.put("createdDate2", formatedDate(createdDate2));
		}
		
		if (StringUtils.isNotBlank(bidStatusCd)) {
			// 5-全部状态
			if ("99,".equals(bidStatusCd)) {
				// 全选,不需要限制条件
			} else {
				hql.append(" and t.bidStatusCd in (:bidStatusCd) ");
				values.put("bidStatusCd", bidStatusCd.split(","));
			}

		}
		hql.append(" order by t.createdDate desc");
		if (pageNo != null) {
			voPage.setPageNo(Integer.valueOf(pageNo));
		}
		//执行分页搜索
		voPage=bidLedgerManager.findPage(voPage, hql.toString(), values);
		
		
		return "loadList";
	}
	
	/**
	 * 时间+1天
	 * @param dateStr
	 * @return
	 */
	private String formatedDate(String dateStr){
		//时间格式
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c=Calendar.getInstance();
		try {
			c.setTime(f.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DAY_OF_MONTH, 1);
		return f.format(c.getTime());
	}
	
	@Override
	public String save() throws Exception {
		// 邀请类型:明标/暗标(默认:9-未知)
		String showFlag = Struts2Utils.getParameter("showFlag");
		String hideFlag = Struts2Utils.getParameter("hideFlag");
		String needGuarFlg = Struts2Utils.getParameter("needGuarFlg");
		
		//有保证金
		if("true".equals(needGuarFlg)){
			entity.setNeedGuarFlg("1");
		}else{
			entity.setNeedGuarFlg("0");
		}
		//战略新增
		if(entity.getSrcTypeCd()==null) {
			//新增判断是否为重复的材料设备
			if(bidLedgerManager.strategyHasExsit(entity.getBidSectionName())){
				//如果已经存在
				Struts2Utils.renderText("error,材料设备已存在");
				return null;
			}else{
				entity.setVisableFlg(BidLedgerManager.VISIBLE_FLG_UNKNOWN);
				if ("true".equals(showFlag)) {
					entity.setVisableFlg(BidLedgerManager.VISIBLE_FLG_SHOW);
				}
				if ("true".equals(hideFlag)) {
					entity.setVisableFlg(BidLedgerManager.VISIBLE_FLG_HIDE);
				}
				entity.setSrcTypeCd("1");
				//默认需要保证金
				entity.setNeedGuarFlg("1");
				// 默认系统创建
				//entity.setCreator("system");
				entity.setCreatedDate(new Date());
				// 默认批次号
				entity.setBatchNo(0L);
				// 导入状态
				entity.setBidStatusCd(BidLedgerManager.BID_STATUS_IMPORT);
				
				// 计划编号 
				// 自动生成ccbpNo规则: ZBPT-PROJECT-ORDERNO
				entity.setSerialType(bidLedgerManager.getSerialType(entity.getOrgCd()));
				entity.setSerialNo(bidLedgerManager.getNextSerialNo(entity.getOrgCd()));
				
				entity.setOrgCd(Struts2Utils.getParameter("projectCd"));
				entity.setOrgName(Struts2Utils.getParameter("projectName"));
				
				bidLedgerManager.saveBidLedger(entity);
				// 2.标底单位/投标单位
				// 2.1构造标底单位(序号/关联供应商ID/供应商名称) ****2012-7-27 战略新增去掉标底单位****
//				BidConsultManager bidConsultManager = SpringContextHolder.getBean("bidConsultManager");
//				//搜索模拟单位
//				BidConsult bidConsult = bidConsultManager.getBiaodi();
//				//咨询公司
//				BidSup sup = new BidSup();
//				sup.setDispOrderNo(new Long(0));//默认0
//				if(	bidConsult == null){
//					sup.setSupName("标底单位");
//				}else{
//					sup.setSupName("标底单位");
//					entity.setConsultFlg("0");//1-模拟清单，1-标底单位
//					sup.setRelSupBasicId(bidConsult.getBidConsultId());
//					sup.setSupName(bidConsult.getFullName());
//				}
//				sup.setTypeCd(BidSupManager.SUP_CONSULTING);
//				sup.setCreator("system");
//				sup.setCreatedDate(new Date());
//				sup.setBidLedger(entity);
//				bidSupManager.saveBidSup(sup);
			}
					
		}else{
			bidLedgerManager.saveBidLedger(entity);			
		}
		
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// 不允许新增
		if (StringUtils.isBlank(getId())){
		}			
		else {
			entity = bidLedgerManager.getEntity(getId());
				
			String tmpResApproveId = entity.getResApproveInfoId();
			String tmpCallResApproveId = entity.getCallResId();
			String tmpBidResApproveId = entity.getBidResId();
			if (StringUtils.isNotBlank(tmpResApproveId)) {
				resApproveInfo = resApproveInfoManager.getEntity(tmpResApproveId);
			}
			if (StringUtils.isNotBlank(tmpCallResApproveId)) {
				callResApproveInfo = resApproveInfoManager.getEntity(tmpCallResApproveId);
			}
			if(StringUtils.isNotBlank(tmpBidResApproveId)) {
				bidResApproveInfo = resApproveInfoManager.getEntity(tmpBidResApproveId);
			}
				
		}
		
	}
	
	/**
	 * 进入下一个环节
	 * 
	 * @return
	 * @throws Exception
	 */
	public String gotoNext() throws Exception {
		//投标台帐ID
		String tmpId = Struts2Utils.getParameter("bidLedgerId");
		if (StringUtils.isBlank(tmpId)) {
			Struts2Utils.renderText(" bidLedgerId is blank! ");
			return null;
		}
		//搜索投标台帐
		entity = bidLedgerManager.getEntity(tmpId);
		//获取投标台帐状态
		String tBidStatusCd = entity.getBidStatusCd();
		// 如果是导入状态，进入邀标状态
		if (BidLedgerManager.BID_STATUS_IMPORT.equals(tBidStatusCd)) {
			//获取供应商
			List<BidSup> bidSups = entity.getBidSups();
			// 循环校验是否存在没有选择联系人的供应商
			for (BidSup bidsup : bidSups) {
				//如果是标底单位则继续
				if (BidSupManager.SUP_CONSULTING.equals(bidsup.getTypeCd())) {
					continue;
				}
				//如果联系人为空
				if (bidsup.getLinkMan() == null || StringUtils.isBlank(bidsup.getLinkMan())) {
					Struts2Utils.renderText("error,存在没有联系人的供应商，请维护好所有供应商的联系人，才能继续进行邀标.");
					return null;
				}
			}
			// 注意:搜索未开通帐号的供应商，开通帐号
			String rs = openPlacct();
			// 发送帐号
			for (BidSup bidsup : bidSups) {
				bidSupManager.sendUserNameAndPwd(bidsup, bidsup.getOpenPlacctCd(), bidsup.getOriPwd());
			}
			// 设置为邀标
			entity.setBidStatusCd(BidLedgerManager.BID_STATUS_INVITE);
			bidLedgerManager.saveBidLedger(entity);
			Struts2Utils.renderText("success");
			return null;
		}
		// 邀标至投标
		else if (BidLedgerManager.BID_STATUS_INVITE.equals(tBidStatusCd)) {
			//设置为投标状态
			entity.setBidStatusCd(BidLedgerManager.BID_STATUS_BIDDING);
			//初始化轮次为1， 设置为第一轮
			entity.setBatchNo(1L);
			//生成轮次
			bidSupAttachRelManager.generateBatch(entity);
			// 如果是第一次开标
			if (entity.getBidOpenRealDate() == null) {
				entity.setBidOpenRealDate(new Date());
			}
			bidLedgerManager.saveBidLedger(entity);
			//投标进入评标
		} else if (BidLedgerManager.BID_STATUS_BIDDING.equals(tBidStatusCd)) {
			entity.setBidStatusCd(BidLedgerManager.BID_STATUS_MARKING);
			bidLedgerManager.saveBidLedger(entity);
			//评标进入推荐中标
		} else if (BidLedgerManager.BID_STATUS_MARKING.equals(tBidStatusCd)){
			BidLedgAttaRel attaRel = bidLedgAttaRelManager.getByBidLedgIdAndBatchNo(entity.getBidLedgerId(), entity.getBatchNo());
			if(!"1".equals(attaRel.getBidAttaEval1Flg())){
				Struts2Utils.renderText("评标文件：“邀标单位资质审批表”未上传");
				return null;
			}
			entity.setBidStatusCd(BidLedgerManager.BID_STATUS_POP);
			bidLedgerManager.saveBidLedger(entity);
		}
		else {
			Struts2Utils.renderText(" operation is disable! ");
			return null;
		}
		bidLedgerManager.saveBidLedger(entity);
		Struts2Utils.renderText("success");

		return null;
	}
	
	/**
	 * 
	 * openPlacct:(开通帐号)
	 * 
	 * @param 设定文件
	 * @return void DOM对象
	 * @throws
	 * @since CodingExample　Ver 1.1
	 */
	private String openPlacct() {
		String strResult = "";
		StringBuffer sql = new StringBuffer().append("select  vs.bid_sup_id,vs.type_cd from vw_bid_sup vs ");
		sql.append(" where vs.sup_user_cd is null and vs.bid_ledger_id = :bidLedgerId");// and
																						// vs.type_cd
																						// =
																						// :typeCd
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bidLedgerId", this.getBidLedgerId());
		// 供应商
		// map.put("typeCd",BidSupManager.SUP_PROVIDOR);
		List<Object[]> unAccounted = bidLedgerManager.findBySql(sql.toString(), map);

		try {
			if (unAccounted != null && unAccounted.size() > 0) {
				for (int i = 0; i < unAccounted.size(); i++) {
					Object[] o = unAccounted.get(i);
					// 供应商ID
					String bidSupId = (String) o[0];
					String typeCd = (String) o[1];
					if (bidSupId != null) {
						try {
							bidSupManager.openPlacct(bidSupId, typeCd);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				strResult = "success," + unAccounted.size() + "家帐号开通成功!";
			}

		} catch (Exception e) {
			// strResult="error,"+e.getMessage();
			e.printStackTrace();
		}
		return strResult;
	}
	
	/**
	 * 在评标状态,允许进入下一轮投标
	 * 
	 * @return
	 */
	public String gotoNextBatchNo() {

		String tmpBidLedgerId = Struts2Utils.getParameter("bidLedgerId");
		if (StringUtils.isBlank(tmpBidLedgerId)) {
			Struts2Utils.renderText(" bidSupId is blank! ");
			return null;
		}
		BidLedger tmpBidLedger = bidLedgerManager.getEntity(tmpBidLedgerId);
		if (tmpBidLedger == null) {
			Struts2Utils.renderText("投标单位不存在!");
			return null;
		}
		List<BidSup> bidSups = tmpBidLedger.getBidSups();
		for (BidSup bidSup : bidSups) {
			if("2".equals(bidSup.getSupBidStatusCd())){
				Struts2Utils.renderText("已设置推荐中标单位，请先取消再进入下一轮！");
			    return null;	
			}
			   
				
		}
		

		Long tmpBatchNo = tmpBidLedger.getBatchNo();
		if (tmpBatchNo == null) {
			tmpBatchNo = 0L;
		}
		tmpBidLedger.setBatchNo(++tmpBatchNo);
		// 设置为投标状态
		tmpBidLedger.setBidStatusCd(BidLedgerManager.BID_STATUS_BIDDING);
		//生成轮次
		if(tmpBidLedger!= null){			
			bidSupAttachRelManager.generateBatch(tmpBidLedger);
		}
		bidLedgerManager.saveBidLedger(tmpBidLedger);
		Struts2Utils.renderText("success");

		return null;
	}


	/**
	 * 查看是否3个必上传附件都已经上传
	 * @return
	 */
	public String hasUploadAllAttach(){
		String tmpBidLedgerId = Struts2Utils.getParameter("bidLedgerId");
		BidLedger bl=bidLedgerManager.getEntity(tmpBidLedgerId);
		if(bl!=null){
			if(bl.getLastGuarDate()==null){
				Struts2Utils.renderText("请先完善并”保存“招标信息");
				return null;
			}
			if(/*"1".equals(bl.getAttaTendFileFlg()) &&*/
					"1".equals(bl.getAttaListFlg())
						&&"1".equals(bl.getAttaTechStanFlg())){
				Struts2Utils.renderText("success");
				return null;
			}else{
				if(!"1".equals(bl.getAttaTendFileFlg())&& bl.getCallResId()==null){
					Struts2Utils.renderText("请先上传招标文件");
					return null;
				}
				if(!"1".equals(bl.getAttaListFlg())){
					Struts2Utils.renderText("请先上传清单附件");
					return null;
				}
				if(!"1".equals(bl.getAttaTechStanFlg())){
					Struts2Utils.renderText("请先上传技术标准附件");
					return null;
				}
			}
		}else{
			Struts2Utils.renderText("faild");
		}
		return null;
	}

	@Override
	public BidLedger getModel() {
		return null;
	}
	
	@Override
	public String delete() throws Exception {		
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	public BidLedger getEntity() {
		return entity;
	}

	public void setEntity(BidLedger entity) {
		this.entity = entity;
	}

	
	public BidLedgAttaRel getAttaRel() {
		return attaRel;
	}


	public void setAttaRel(BidLedgAttaRel attaRel) {
		this.attaRel = attaRel;
	}


	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}

	public Page getVoPage() {
		return voPage;
	}

	public void setVoPage(Page voPage) {
		this.voPage = voPage;
	}

	public ResApproveInfo getResApproveInfo() {
		return resApproveInfo;
	}

	public void setResApproveInfo(ResApproveInfo resApproveInfo) {
		this.resApproveInfo = resApproveInfo;
	}


	public AppAttachFile getAttaTendFile() {
		return attaTendFile;
	}


	public void setAttaTendFile(AppAttachFile attaTendFile) {
		this.attaTendFile = attaTendFile;
	}


	public AppAttachFile getAttaDrawFile() {
		return attaDrawFile;
	}


	public void setAttaDrawFile(AppAttachFile attaDrawFile) {
		this.attaDrawFile = attaDrawFile;
	}


	public AppAttachFile getAttaList() {
		return attaList;
	}


	public void setAttaList(AppAttachFile attaList) {
		this.attaList = attaList;
	}


	public AppAttachFile getAttaTechStan() {
		return attaTechStan;
	}


	public void setAttaTechStan(AppAttachFile attaTechStan) {
		this.attaTechStan = attaTechStan;
	}


	public String getHasSupNotReceive() {
		return hasSupNotReceive;
	}


	public void setHasSupNotReceive(String hasSupNotReceive) {
		this.hasSupNotReceive = hasSupNotReceive;
	}


	public String getHasGuarNotConfirmed() {
		return hasGuarNotConfirmed;
	}


	public void setHasGuarNotConfirmed(String hasGuarNotConfirmed) {
		this.hasGuarNotConfirmed = hasGuarNotConfirmed;
	}




	public long getBatchNo() {
		return batchNo;
	}


	public void setBatchNo(long batchNo) {
		this.batchNo = batchNo;
	}


	public ResApproveInfo getCallResApproveInfo() {
		return callResApproveInfo;
	}


	public void setCallResApproveInfo(ResApproveInfo callResApproveInfo) {
		this.callResApproveInfo = callResApproveInfo;
	}


	


	public ResApproveInfo getBidResApproveInfo() {
		return bidResApproveInfo;
	}


	public void setBidResApproveInfo(ResApproveInfo bidResApproveInfo) {
		this.bidResApproveInfo = bidResApproveInfo;
	}


	public List<AppAttachFile> getAttaEval1s() {
		return attaEval1s;
	}


	public void setAttaEval1s(List<AppAttachFile> attaEval1s) {
		this.attaEval1s = attaEval1s;
	}


	public AppAttachFile getCallAttachFile() {
		return callAttachFile;
	}


	public void setCallAttachFile(AppAttachFile callAttachFile) {
		this.callAttachFile = callAttachFile;
	}


	
	
	
	

}

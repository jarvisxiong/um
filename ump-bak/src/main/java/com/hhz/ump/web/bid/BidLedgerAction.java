package com.hhz.ump.web.bid;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bid.BidConsultManager;
import com.hhz.ump.dao.bid.BidDivisitonManager;
import com.hhz.ump.dao.bid.BidLedgAttaRelManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidOpenRecordManager;
import com.hhz.ump.dao.bid.BidOpenRecordSupManager;
import com.hhz.ump.dao.bid.BidProjectManager;
import com.hhz.ump.dao.bid.BidProjectRoleManager;
import com.hhz.ump.dao.bid.BidSupAttachRelManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.dao.sup.SupContactorManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.bid.BidConsult;
import com.hhz.ump.entity.bid.BidLedgAttaRel;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidOpenRecord;
import com.hhz.ump.entity.bid.BidProjectRole;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.web.bid.exception.CanotSaveNewBidLedger;
import com.hhz.ump.web.res.bean.InviteUnitProperty;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Namespace("/bid")
@Result(name = CrudActionSupport.RELOAD, location = "bid-ledger!list.action", type = "redirect")
public class BidLedgerAction extends CrudActionSupport<BidLedger> {

	private static final long serialVersionUID = 585524610892781395L;

	@Autowired
	protected BidLedgerManager bidLedgerManager;
	@Autowired
	protected BidProjectManager bidProjectManager;
	@Autowired
	protected BidDivisitonManager bidDivisitonManager;
	@Autowired
	protected BidSupManager bidSupManager;
	@Autowired
	protected ResApproveInfoManager resApproveInfoManager;
	@Autowired
	protected SupContactorManager supContactorManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private BidConsultManager bidConsultManager;
	@Autowired
	BidProjectRoleManager bidProjectRoleManager;
	@Autowired
	BidLedgAttaRelManager bidLedgAttaRelManager;
	@Autowired
	protected BidSupAttachRelManager bidSupAttachRelManager;
	@Autowired
	private BidOpenRecordManager bidOpenRecordManager;
	@Autowired
	private BidOpenRecordSupManager bidOpenRecordSupManager;
	/**
	 * 标段台帐
	 */
	private BidLedger entity;
	/**
	 * 标段台帐ID
	 */
	private String bidLedgerId;
	/**
	 * 是否存在未接受的客户
	 */
	private String hasSupNotReceive = "0";
	//是否上传评标文件
	private String hasEvalFiles = "0";
	/**
	 * 邀标单位审批表
	 */
	private List<InviteUnitProperty> otherProperties = new ArrayList<InviteUnitProperty>();

	/**
	 * 分页搜索
	 */
	private Page voPage = new Page(10);
	/**
	 * 工程VO
	 */
	List<BidLedProjectVo> bidLedProjectVoList;

	/**
	 * 网批明细
	 */
	private ResApproveInfo resApproveInfo;
	private ResApproveInfo callResApproveInfo;//招标文件网批
	private ResApproveInfo bidResApproveInfo;//定标网批编号
	
	private List orgList;
	/**
	 * 未开通帐号数
	 */
	private Integer unAccountedSup;
	/**
	 * 标段附件列表
	 */
	private List<AppAttachFile> attaBaseList;
	
	//招标附件 2012-06-27
	private AppAttachFile callAttachFile;

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
		orgList = new ArrayList();
		String user = SpringSecurityUtils.getCurrentUiid();
		List _orgList = bidLedgerManager.findOrgList(user);
		for (Object org : _orgList) {
			 orgList.add(PlasCache.getOrgByCd(org.toString()));
		}
		
		return SUCCESS;
	}
	
	private int inviteSupNum;
	private int receiveSupNum;
	
	
	public int getInviteSupNum() {
		return inviteSupNum;
	}

	public void setInviteSupNum(int inviteSupNum) {
		this.inviteSupNum = inviteSupNum;
	}

	public int getReceiveSupNum() {
		return receiveSupNum;
	}

	public void setReceiveSupNum(int receiveSupNum) {
		this.receiveSupNum = receiveSupNum;
	}

	private String bidOpenRecordId;
	
	public String getBidOpenRecordId() {
		return bidOpenRecordId;
	}

	public void setBidOpenRecordId(String bidOpenRecordId) {
		this.bidOpenRecordId = bidOpenRecordId;
	}

	/**
	 * 保存或更新前准备
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#prepareModel()
	 */
	@Override
	protected void prepareModel() throws Exception {
		// 不允许新增
		if (StringUtils.isBlank(getId()))
			// entity = new BidLedger();
			throw new CanotSaveNewBidLedger(CanotSaveNewBidLedger.cannotSaveNew);
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
			
			List<BidSup> bidSups = entity.getBidSups();
			for (BidSup bidSup : bidSups) {
				if("1".equals(bidSup.getTypeCd())){
					inviteSupNum++;
				}
				if("1".equals(bidSup.getGuarAttaConfFlg())){
					receiveSupNum++;
				}
				
			}
			BidOpenRecord bidOpenRecord = bidOpenRecordManager.getByBidLedgerIdAndBatchNo(entity.getBidLedgerId(), entity.getBatchNo());
			if(bidOpenRecord!=null) {
				bidOpenRecordId = bidOpenRecord.getBidOpenRecordId();
			}
			
			
			
		}
	}

	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}
	/**
	 * 执行保存工作
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#save()
	 */
	@Override
	public String save() throws Exception {
		try {
			bidLedgerManager.saveBidLedger(entity);
			Struts2Utils.renderHtml("success");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderHtml("fail");
		}
		// 保存更新日志
		// String changeFieldName =
		// Struts2Utils.getParameter("changeFieldName");
		// String changeFieldValue =
		// Struts2Utils.getParameter("changeFieldValue");
		return null;
	}

	@Override
	public BidLedger getModel() {
		return entity;
	}

	/**
	 * 获取区域项目关系树
	 * 
	 * @param singleFlg
	 *            若为'true',单选;否则默认多选
	 * @return
	 */
	public String getAreaProjectTree() {

		String singleFlg = Struts2Utils.getParameter("singleFlg");
		TreePanelNode firstNode = PlasCache.getAreaProjectTree("true".equals(singleFlg) ? false : true);
		// 筛选可以用的node
		filterTreeNode(firstNode);
		Struts2Utils.renderJson(firstNode);
		// Struts2Utils.renderJson(firstNode);
		return null;
	}
	
	public String main(){
		return "main";
	}
	
	
	/**
	 * 
	 * 根据用户权限筛选左边菜单树
	 */
	private TreePanelNode filterTreeNode(TreePanelNode firstNode) {
		// 二级节点(南区，北区)
		List<TreePanelNode> oldSecondNodes = firstNode.getChildren();
		// 拷贝一个根节点
		TreePanelNode newNode = firstNode;
		newNode.setChildren(new ArrayList<TreePanelNode>());
		try {
			// 当前用户ID
			String user = SpringSecurityUtils.getCurrentUiid();
			// 搜索用户的所有区
			List<BidProjectRole> bprs = bidProjectRoleManager.getProjectUserByUserCd(BidProjectRoleManager.ACTIVE_YES,
					user);
			// 转化为map
			Map<String, String> bprMap = new HashMap<String, String>();
			if (bprs != null && !bprs.isEmpty()) {
				for (BidProjectRole r : bprs) {
					bprMap.put(r.getProjectCd(), r.getUserCd());
				}
			}
//			List<TreePanelNode> newsecondNodes = new ArrayList<TreePanelNode>();
			//数节点循环比对权权限列表中的节点，查看是否存在访问权限，则加入列表newNode
			if (oldSecondNodes != null && oldSecondNodes.size() > 0) {
				List<TreePanelNode> filteredThirdNodes = null;
				for (int i = 0; i < oldSecondNodes.size(); i++) {
					// eg.南区
					// 三级节点列表
					List<TreePanelNode> thridNodes = oldSecondNodes.get(i).getChildren();
					if (thridNodes != null) {
						filteredThirdNodes = new ArrayList();
						for (TreePanelNode thridNode : thridNodes) {
							if (bprMap.get(thridNode.getEntityCd()) != null) {
								filteredThirdNodes.add(thridNode);
							}
						}
						// 如果筛选后的列表不为空
						if (!filteredThirdNodes.isEmpty()) {
							TreePanelNode oldSecondNode = (TreePanelNode) oldSecondNodes.get(i);
							oldSecondNode.setChildren(filteredThirdNodes);
							newNode.addChild(oldSecondNode);
						} else {
							// 无操作
						}
					}

				}

			}
		} catch (Exception e) {

		}
		return newNode;
	}

	/**
	 * 根据搜索条件,搜索投标台账列表(工程列表)
	 * <p/>
	 * 搜索条件有
	 * <p/>
	 * 
	 * @param selectItemCds
	 * @param ...TODO
	 * 
	 * @return
	 */
	public String loadList() {
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");

		// 高级搜索条件
		// 状态
		String bidStatusCd = Struts2Utils.getParameter("bidStatusCd");
		// 机构列表
		String selectItemCds = Struts2Utils.getParameter("bidProject");
		// 标段名称
		String bidSectionName = Struts2Utils.getParameter("bidSectionName");
		// 跟踪人
		String followCds = Struts2Utils.getParameter("followCds");
//		String bidOpenRealDate1 = Struts2Utils.getParameter("bidOpenRealDate1");
//		String bidOpenRealDate2 = Struts2Utils.getParameter("bidOpenRealDate2");
//		String bidConfirmRealDate1 = Struts2Utils.getParameter("bidConfirmRealDate1");
//		String bidConfirmRealDate2 = Struts2Utils.getParameter("bidConfirmRealDate2");

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("deleteFlg", "0");// 0-正常
		values.put("srcTypeCd", "0");// 1-非战略

		StringBuffer sqlMid = new StringBuffer()
		.append(" select t.bid_ledger_id,")
		.append(" 	t.org_cd,")
		.append(" 	t.bid_section_name,")
		.append(" 	t.section_total_area,")
		.append(" 	t.follow_names,")
		.append(" 	t.bid_open_real_date,")
		.append(" 	t.bid_status_cd, ")
		.append("   t.a1,")
		.append("   t.a2,")
		.append(" 	t.created_date ,")
		.append("   t.bid_confirm_real_date, ")
		.append("   t.ccbp_no ")
		.append(" from VW_BID_LEDGER t ")
		.append(" where 1=1 and t.src_type_cd= :srcTypeCd ");

		if (StringUtils.isNotBlank(selectItemCds)) {
			sqlMid.append(" and t.org_cd in (:orgCd) ");
			values.put("orgCd", selectItemCds.split(","));
		}
		if (StringUtils.isNotBlank(bidStatusCd)) {
			// 5-全部状态
			if ("5,".equals(bidStatusCd)) {
				// 全选,不需要限制条件
			} else {
				sqlMid.append(" and t.bid_status_cd in (:bidStatusCd) ");
				values.put("bidStatusCd", bidStatusCd.split(","));
			}

		}
		if (StringUtils.isNotBlank(bidSectionName)) {
			sqlMid.append(" and t.bid_section_name =:bidSectionName ");
			values.put("bidSectionName", bidSectionName);
		}
		if (StringUtils.isNotBlank(followCds)) {
			sqlMid.append(" and t.follow_cds like :followCds ");
			values.put("followCds", followCds);
		}
//		if (StringUtils.isNotBlank(bidOpenRealDate1)) {
//			sqlMid.append(" and t.bid_open_real_date >= to_date(:bidOpenRealDate1,'yyyy-mm-dd')");
//			values.put("bidOpenRealDate1", bidOpenRealDate1);
//		}
//		if (StringUtils.isNotBlank(bidOpenRealDate2)) {
//			sqlMid.append(" and t.bid_open_real_date <= to_date(:bidOpenRealDate2,'yyyy-mm-dd')");
//			values.put("bidOpenRealDate2", bidOpenRealDate2);
//		}
//		if (StringUtils.isNotBlank(bidConfirmRealDate1)) {
//			sqlMid.append(" and t.bid_confirm_real_date >= to_date(:bidConfirmRealDate1,'yyyy-mm-dd')");
//			values.put("bidConfirmRealDate1", bidConfirmRealDate1);
//		}
//		if (StringUtils.isNotBlank(bidConfirmRealDate2)) {
//			sqlMid.append(" and t.bid_confirm_real_date <= to_date(:bidConfirmRealDate2,'yyyy-mm-dd')");
//			values.put("bidConfirmRealDate2", bidConfirmRealDate2);
//		}
		// 当前用户ID
		String user = SpringSecurityUtils.getCurrentUiid();
		// 根据此用户的权限
		String projectCds = bidProjectRoleManager.getProjectByUserCd(BidProjectRoleManager.ACTIVE_YES, user);
		if (StringUtils.isNotBlank(projectCds)) {
			sqlMid.append(" and t.org_cd in ( " + projectCds + ")");
			values.put("org_cd", projectCds);
		}
		// 设置默认排序方式
		if (StringUtils.isBlank(sortField)) {
			sqlMid.append(" order by t.created_date desc ");
		} else {
			sqlMid.append(" order by t." + sortField + " " + order);
		}

		if (pageNo != null) {
			voPage.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			voPage.setPageSize(Integer.valueOf(rows));
		}

		voPage = bidLedgerManager.findPageSql(voPage, sqlMid.toString(), values, new HashMap<String, Class>());
		List bidLedgerList = voPage.getResult();
		bidLedProjectVoList = new ArrayList<BidLedProjectVo>();
		Object[] obj = null;
		BidLedProjectVo bidLedProVo = null;
		for (int i = 0; i < bidLedgerList.size(); i++) {
			obj = (Object[]) bidLedgerList.get(i);
			bidLedProVo = new BidLedProjectVo();
			bidLedProVo.setBidLedgerId((String) obj[0]);
			bidLedProVo.setOrgCd((String) obj[1]);
			bidLedProVo.setBidSectionName((String) obj[2]);
			bidLedProVo.setSectionTotalArea(obj[3] == null ? new BigDecimal(0) : (BigDecimal) obj[3]);
			bidLedProVo.setFollowNames((String) obj[4]);

			bidLedProVo.setBidOpenRealDate((Date) obj[5]);
			bidLedProVo.setBidStatusCd((String) obj[6]);
			bidLedProVo.setProjectNum(obj[7].toString());
			bidLedProVo.setBidSupNum(obj[8].toString());
			bidLedProVo.setCreatedDate((Date) obj[9]);
			bidLedProVo.setBidConfirmRealDate((Date) obj[10]);
			bidLedProVo.setCcbpNo((String) obj[11]);
			bidLedProjectVoList.add(bidLedProVo);
		}
		return "loadList";
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
	private AppAttachFile attaTendFile;//招标文件
	
	public AppAttachFile getAttaTendFile() {
		return attaTendFile;
	}

	public void setAttaTendFile(AppAttachFile attaTendFile) {
		this.attaTendFile = attaTendFile;
	}

	public String getHasEvalFiles() {
		return hasEvalFiles;
	}

	public void setHasEvalFiles(String hasEvalFiles) {
		this.hasEvalFiles = hasEvalFiles;
	}

	private String hasGuarNotConfirmed = "0";
	
	
	public String getHasGuarNotConfirmed() {
		return hasGuarNotConfirmed;
	}

	public void setHasGuarNotConfirmed(String hasGuarNotConfirmed) {
		this.hasGuarNotConfirmed = hasGuarNotConfirmed;
	}

	public List getOrgList() {
		return orgList;
	}

	public void setOrgList(List orgList) {
		this.orgList = orgList;
	}

	/**
	 * 投标台帐明细页面
	 * @throws Exception 
	 */
	public String detail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			//获取附件页面
			getAllAttaFiles(getId());
		}
		//招标文件
		if ("1".equals(entity.getAttaTendFileFlg())) {
			attaTendFile = bidLedgerManager.listAttach(getId(), "bidLedgerGurantee", "attaTendFileFlg");
		}
		//获取供应商列表
		List<BidSup> bidSups = entity.getBidSups();
		//是否存在供应商未接受邀标的情况
		if (bidSups != null) {
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
		BidLedgAttaRel attaRel = bidLedgAttaRelManager.getByBidLedgIdAndBatchNo(entity.getBidLedgerId(), entity.getBatchNo());
		//是否上传评标文件
		if("1".equals(attaRel.getBidAttaEval1Flg())){
			hasEvalFiles = "1";
		}

		//招标附件 
		if(entity != null && StringUtils.isNotBlank(entity.getCallResAttachId())){
			callAttachFile = appAttachFileManager.getEntity(entity.getCallResAttachId());
		}
		
		return "detail";
	}

	/**
	 * 获取所有的附件列表
	 */
	private void getAllAttaFiles(String bidLedgerId) {
		// 招标文件附件列表
		if (StringUtils.isNotBlank(bidLedgerId)) {
			Map<String, Object> values = new HashMap<String, Object>();
			StringBuffer hqlPl = new StringBuffer().append(" from AppAttachFile where statusCd='1' ");
			hqlPl.append(" and bizEntityId =:bizEntityId ");
			values.put("bizEntityId", bidLedgerId);
			attaBaseList = appAttachFileManager.find(hqlPl.toString(), values);
		}
	}

	public BidLedger getEntity() {
		return entity;
	}

	public void setEntity(BidLedger entity) {
		this.entity = entity;
	}

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}

	public List<BidLedProjectVo> getBidLedProjectVoList() {
		return bidLedProjectVoList;
	}

	public void setBidLedProjectVoList(List<BidLedProjectVo> bidLedProjectVoList) {
		this.bidLedProjectVoList = bidLedProjectVoList;
	}

	public Page getVoPage() {
		return voPage;
	}

	public void setVoPage(Page voPage) {
		this.voPage = voPage;
	}

	public Integer getUnAccountedSup() {
		return unAccountedSup;
	}

	public void setUnAccountedSup(Integer unAccountedSup) {
		this.unAccountedSup = unAccountedSup;
	}
	
	/**
	 *执行中标操作
	 */
	public String gotoWin() {
		//投标台帐ID
		String tmpId = Struts2Utils.getParameter("bidLedgerId");
		if (StringUtils.isBlank(tmpId)) {
			Struts2Utils.renderText(" bidLedgerId is blank! ");
			return null;
		}
		entity = bidLedgerManager.getEntity(tmpId);
		//设置投标台帐状态为中标状态
		entity.setBidStatusCd(BidLedgerManager.BID_STATUS_WIN);
		bidLedgerManager.saveBidLedger(entity);
		Struts2Utils.renderText("success");

		return null;
	}
	
	/**
	 *执行推荐中标操作
	 */
	public String gotoPop() {
		//投标台帐ID
		String tmpId = Struts2Utils.getParameter("bidLedgerId");
		if (StringUtils.isBlank(tmpId)) {
			Struts2Utils.renderText(" bidLedgerId is blank! ");
			return null;
		}
		entity = bidLedgerManager.getEntity(tmpId);
		BidLedgAttaRel attaRel = bidLedgAttaRelManager.getByBidLedgIdAndBatchNo(entity.getBidLedgerId(), entity.getBatchNo());
		if(!"1".equals(attaRel.getBidAttaEval1Flg())){
			Struts2Utils.renderText("评标文件：“邀标单位资质审批表”未上传");
			return null;
		}
		//设置投标台帐状态为中标状态
		entity.setBidStatusCd(BidLedgerManager.BID_STATUS_POP);
		bidLedgerManager.saveBidLedger(entity);
		Struts2Utils.renderText("success");

		return null;
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
			//初始化轮次为1
			entity.setBatchNo(1L);// 设置为第一轮
			//生成投标单位附件关联表轮次
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
		} else {
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
	 * 回退上一个环节
	 * 
	 * @return
	 */
	public String gotoPrev() {
		String tmpId = Struts2Utils.getParameter("bidLedgerId");
		if (StringUtils.isBlank(tmpId)) {
			Struts2Utils.renderText(" bidLedgerId is blank! ");
			return null;
		}

		entity = bidLedgerManager.getEntity(tmpId);
		String tBidStatusCd = entity.getBidStatusCd();
		//回退到导入
		if (BidLedgerManager.BID_STATUS_INVITE.equals(tBidStatusCd)) {
			entity.setBidStatusCd(BidLedgerManager.BID_STATUS_IMPORT);
			bidLedgerManager.saveBidLedger(entity);
			//回退到导入
		} else if (BidLedgerManager.BID_STATUS_BIDDING.equals(tBidStatusCd)) {
			entity.setBidStatusCd(BidLedgerManager.BID_STATUS_INVITE);
			bidLedgerManager.saveBidLedger(entity);
			//回退到投标
		} else if (BidLedgerManager.BID_STATUS_MARKING.equals(tBidStatusCd)) {
			entity.setBidStatusCd(BidLedgerManager.BID_STATUS_BIDDING);
			bidLedgerManager.saveBidLedger(entity);
			//回退到评标
		} else if (BidLedgerManager.BID_STATUS_WIN.equals(tBidStatusCd)) {
			entity.setBidStatusCd(BidLedgerManager.BID_STATUS_MARKING);
			bidLedgerManager.saveBidLedger(entity);
			// 若是中标回退,则清空中标单位
			bidSupManager.cleanWinner(tmpId, null);
		} else {
			Struts2Utils.renderText(" operation is disable! ");
			return null;
		}
		Struts2Utils.renderText("success");

		return null;
	}

	/**
	 * 加载标段状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepareLoadPrevNextStatus() throws Exception {
		prepareModel();
	}
	
	/**
	 * 
	 * 加载下一状态
	 */
	public String loadPrevNextStatus() {
		if (StringUtils.isBlank(getId())) {
			Struts2Utils.renderText(" bidLedgerId is blank! ");
			return null;
		}
		return "prevNextStatus";
	}

	public ResApproveInfo getResApproveInfo() {
		return resApproveInfo;
	}

	public void setResApproveInfo(ResApproveInfo resApproveInfo) {
		this.resApproveInfo = resApproveInfo;
	}

	/**
	 * 下一轮
	 * 
	 * @return
	 */
	public String addBatchNo() {

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
		Long tmpBatchNo = tmpBidLedger.getBatchNo();
		if (tmpBatchNo == null) {
			tmpBatchNo = 0L;
		}
		tmpBidLedger.setBatchNo(++tmpBatchNo);
		bidLedgerManager.saveBidLedger(tmpBidLedger);
		Struts2Utils.renderText("success");

		return null;
	}

	/**
	 * 回退上一轮
	 * 
	 * @return
	 */
	public String backBatchNo() {

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
		Long tmpBatchNo = tmpBidLedger.getBatchNo();
		tmpBidLedger.setBatchNo(--tmpBatchNo);
		bidLedgerManager.saveBidLedger(tmpBidLedger);
		Struts2Utils.renderText("success");

		return null;
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
		//生成投标单位附件关联表轮次
		bidSupAttachRelManager.generateBatch(tmpBidLedger);
		bidLedgerManager.saveBidLedger(tmpBidLedger);
		Struts2Utils.renderText("success");

		return null;
	}

	/**
	 * 
	 * validateHasImportBiaodiData:(验证是否已经导入了标底数据)
	 * 
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @throws
	 * @since 　Ver 1.1
	 */
	public String validateHasImportBiaodiData() {
		String tmpBidLedgerId = Struts2Utils.getParameter("bidLedgerId");
		if (bidDivisitonManager.hasImportedBiaodiData(tmpBidLedgerId)) {
			Struts2Utils.renderText("yes");
		} else {
			Struts2Utils.renderText("no");
		}
		return null;
	}

	public List<AppAttachFile> getAttaBaseList() {
		return attaBaseList;
	}

	public void setAttaBaseList(List<AppAttachFile> attaBaseList) {
		this.attaBaseList = attaBaseList;
	}

	/**
	 * 
	 * sendMailAndMessage:(按照供应商发信息)
	 * 
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @throws Exception
	 * @throws
	 * @since CodingExample　Ver 1.1
	 */
	/*
	 * public String sendMailAndMessage() throws Exception{ String
	 * relSupContactIds = Struts2Utils.getParameter("relSupContactIds");
	 * if(relSupContactIds != null&&relSupContactIds.length()>1){ String []
	 * contactorIds = relSupContactIds.split(","); SupContactor supContactor =
	 * null; BidSup bidsup=null; for(String contactId:contactorIds){
	 * supContactor = supContactorManager.getEntity(contactId);
	 * if(supContactor!=null){
	 * bidsup=bidSupManager.getBidSupByLedgerIdAndRelSupId
	 * (bidLedgerId,supContactor.getSupBasic().getSupBasicId()); try{
	 * bidSupManager
	 * .sendUserNameAndPwd(bidsup,bidsup.getOpenPlacctCd(),bidsup.getOriPwd());
	 * }catch (Exception e){ e.printStackTrace(); }
	 * 
	 * } } Struts2Utils.renderText("success"); }
	 * 
	 * return null; }
	 */
	
	/**
	 * 新增一个投标台帐
	 */
	public String newBidLedger() {

		return "new";
	}
	
	/**
	 * 
	 * 新增一个投标台帐前准备
	 */
	public void prepareSaveNewBidLedger() {
		String projectCd = Struts2Utils.getParameter("projectCd");
		String projectName = Struts2Utils.getParameter("projectName");
		entity = new BidLedger();
		// 机构CD
		entity.setOrgCd(projectCd);
		// 项目名称
		entity.setOrgName(projectName);// (CodeNameUtil.getDeptNameByCd(this.getProjectCd()));
		// 标书编号
		String biaoShuNo = Struts2Utils.getParameter("biaoShuNo");
		entity.setSectionNo(biaoShuNo);
		// 标段
		String biaoDuan = Struts2Utils.getParameter("biaoDuan");
		entity.setBidSectionName(biaoDuan);
		// 总包
		String overAllYes = Struts2Utils.getParameter("overAllYes");
		String overAllNo = Struts2Utils.getParameter("overAllNo");
		//招标计划编号 add 2012-7-27
        String ccbpNo = Struts2Utils.getParameter("ccbpNo");
        String ccbpId = Struts2Utils.getParameter("ccpbId");
        entity.setCcbpId(ccbpId);
        entity.setCcbpNo(ccbpNo);
		// 邀请类型:明标/暗标(默认:9-未知)
		String showFlag = Struts2Utils.getParameter("showFlag");
		String hideFlag = Struts2Utils.getParameter("hideFlag");

		entity.setVisableFlg(BidLedgerManager.VISIBLE_FLG_UNKNOWN);
		if ("true".equals(showFlag)) {
			entity.setVisableFlg(BidLedgerManager.VISIBLE_FLG_SHOW);
		}
		if ("true".equals(hideFlag)) {
			entity.setVisableFlg(BidLedgerManager.VISIBLE_FLG_HIDE);
		}
		// 预计金额(元)
		String planMoney = Struts2Utils.getParameter("planMoney");
		if (StringUtils.isNotBlank(planMoney)) {
			planMoney=planMoney.replaceAll(",", "");
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumFractionDigits(2);
			nf.setRoundingMode(RoundingMode.HALF_UP);
			try {
				entity.setTargetAmt(BigDecimal.valueOf(nf.parse(planMoney).doubleValue()));
			} catch (ParseException e) {
				Log.error("预计金额非数值！");
				e.printStackTrace();

			}
		}
		// 预算类型
		String outFlag = Struts2Utils.getParameter("outFlag");
		String inFlag = Struts2Utils.getParameter("inFlag");
		entity.setBudgetInFlg("true".equals(inFlag) ? BidLedgerManager.BUDGET_FLG_YES : BidLedgerManager.BUDGET_FLG_NO);
		entity.setBudgetOutFlg("true".equals(outFlag) ? BidLedgerManager.BUDGET_FLG_YES
				: BidLedgerManager.BUDGET_FLG_NO);
		// 投标状态
		entity.setBidStatusCd(BidLedgerManager.BID_STATUS_IMPORT);

		// 默认系统创建
		entity.setCreator("system");
		entity.setCreatedDate(new Date());
		// 默认批次号
		entity.setBatchNo(0L);
		/*
		 * System.out.println(otherProperties.size()); for(InviteUnitProperty
		 * p:otherProperties){ System.out.println(p.getUnitName()); }
		 */
	}

	/**
	 * 快速搜索项目
	 */
	public void quickSearchOrg() {
		String value = Struts2Utils.getParameter("value");
		List<WsPlasOrg> orgs = PlasCache.getOrgAllList();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		int i = 0;
		for (WsPlasOrg org : orgs) {
			if (org.getOrgCd().indexOf(value) == 0 || org.getOrgName().indexOf(value) == 0) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("orgCd", org.getOrgCd());
				map.put("orgName", org.getOrgName());
				list.add(map);
				i++;
			}
			if (i > 10) {
				break;
			}
		}
		Struts2Utils.renderJson(list);
	}
	
	/**
	 * 
	 * 保存新的投标台帐
	 */
	public String saveNewBidLedger() {
		// 查看权限
		if (!bidProjectRoleManager.canNewBid(entity.getOrgCd(), SpringSecurityUtils.getCurrentUiid())) {
			Struts2Utils.renderText("false,没有权限！");
			return null;
		}
		// 保存投标台帐
		entity.setConsultFlg("0");// 1-模拟清单,0标底单位
		entity.setSrcTypeCd(BidLedgerManager.SRC_TYPE_FZL);//非战略
		bidLedgerManager.saveBidLedger(entity);

		// 默认插入标底公司搜索模拟单位
		BidConsult bidConsult = bidConsultManager.getBiaodi();
		// 标底单位
		BidSup sup = new BidSup();
		sup.setDispOrderNo(new Long(0));// 默认0
		if (bidConsult == null) {
			// sup.setRelSupBasicId(null);
			sup.setSupName("标底单位");
		} else {
			sup.setRelSupBasicId(bidConsult.getBidConsultId());
			sup.setSupName(bidConsult.getFullName());
		}
		sup.setTypeCd(BidSupManager.SUP_CONSULTING);
		sup.setCreator("system");
		sup.setCreatedDate(new Date());
		sup.setBidLedger(entity);
		bidSupManager.saveBidSup(sup);

		Struts2Utils.renderText("success");
		return null;
	}

	public List<InviteUnitProperty> getOtherProperties() {
		return otherProperties;
	}

	public void setOtherProperties(List<InviteUnitProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}

	public String getHasSupNotReceive() {
		return hasSupNotReceive;
	}

	public void setHasSupNotReceive(String hasSupNotReceive) {
		this.hasSupNotReceive = hasSupNotReceive;
	}

	/**
	 * 关闭投标项目
	 * @param id  投标台账ID
	 *  
	 */
	public void closeBid(){
		
		String tLedgerId = Struts2Utils.getParameter("id");
		if(StringUtils.isBlank(tLedgerId)){
			Struts2Utils.renderText("未找到台账!");
			return;
		}
		
		BidLedger tLedger =  bidLedgerManager.getEntity(tLedgerId);
		if(tLedger == null){
			Struts2Utils.renderText("未找到台账!");
			return;
		}
		
		tLedger.setBidStatusCd(BidLedgerManager.BID_STATUS_CLOSE);
		bidLedgerManager.saveBidLedger(tLedger);

		Struts2Utils.renderText("success");
	}
	
	/**
	 * 获取查询号
	 * @return
	 */
	public String getResDispNo(){
		
		String tmpResDispNo = null;
		String tmpResApproveId = entity.getResApproveInfoId();
		
		if(entity != null && StringUtils.isNotBlank(tmpResApproveId)){
			ResApproveInfo tmpResInfo = resApproveInfoManager.getEntity(tmpResApproveId);
			if(tmpResInfo != null){
				tmpResDispNo = String.valueOf(tmpResInfo.getDisplayNo());
			}
		}
		
		return tmpResDispNo;
	}

	public AppAttachFile getCallAttachFile() {
		return callAttachFile;
	}

	public void setCallAttachFile(AppAttachFile callAttachFile) {
		this.callAttachFile = callAttachFile;
	}

	private String formatStr(String t){
		return StringUtils.isBlank(t)?"":t.trim();
	}
	private String formatStr(String t,String defaultVal){
		return StringUtils.isBlank(t)? (StringUtils.isNotBlank(defaultVal)?defaultVal:""):t.trim();
	}

	/**
	 * 查询定标导入投标台账
	 * @param value
	 * @param projectCd
	 * @value 
	 */
	public String quickSearchBid(){

		String value = Struts2Utils.getParameter("value");
		String projectCd = Struts2Utils.getParameter("projectCd");
		String dataTypeCd = Struts2Utils.getParameter("dataTypeCd");//0-招标 1-采购 2-战略

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("bidEval", BidLedgerManager.BID_STATUS_MARKING);  //评标
		values.put("bid", BidSupManager.SUCC_STATUS_POP);  //推荐中标
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select t.bid_ledger_id,t.org_cd,t.ccbp_id, decode(t.src_type_cd,'1',(t.serial_type||t.serial_no),t.ccbp_no) as serial_no,t2.bid_sup_id,t2.sup_name,t2.rel_sup_basic_id,t.bid_section_name ");
		sql.append("   from bid_ledger t ");
		sql.append("   left join bid_sup t2 on t.bid_ledger_id = t2.bid_ledger_id and t2.sup_bid_status_cd = :bid ");
		sql.append("  where t.bid_status_cd = :bidEval ");
		
		if(StringUtils.isNotBlank(value)){
			values.put("ccbpNo", "%" + formatStr(value)+"%");//名称模糊匹配
			sql.append(" and (t.ccbp_no like :ccbpNo or (t.serial_type||t.serial_no) like :ccbpNo) ");
			
		}
		if(StringUtils.isNotBlank(projectCd)){
			values.put("projectCd", formatStr(projectCd));
			sql.append(" and t.org_cd = :projectCd ");
		}else{
			sql.append(" and t.org_cd is not null ");
		}

		//（计划平台）招采战略： 0-招标 1-采购 2-战略 -> （招标平台）导入类型: 0-其他 1-战略
		if(StringUtils.isNotBlank(dataTypeCd)){
			if("2".equals(dataTypeCd)) {
				values.put("srcTypeCd", "1");
			}else{
				values.put("srcTypeCd", "0");
			}
			sql.append(" and t.src_type_cd = :srcTypeCd ");
		}		
		
		sql.append(" order by t.created_date desc ");
		page.setPageSize(20);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		List ttList = bidLedgerManager.findBySql(sql.toString(), values);
		if(ttList != null){ 
			
			//标题行
			Map<String, String> map = new HashMap<String,String>();
			//标题行
			map.put("headFlg", "1");
			//招采编号
			map.put("ccbpNo", "计划编号");
			map.put("ccbpProjectName", "项目名称");
			map.put("ccbpPlanContentDesc", "标段名称");
			map.put("supName", "推荐中标单位");
			
			list.add(map);
			
			
			//列： 标段ID，项目cd,计划ID，计划no, 供应商id,供应商名称,事项
//			hql.append(" select t.bid_ledger_id,t.project_cd,t.ccbp_id, t.ccbp_no,t2.bid_sup_id,t2.sup_name,t2.rel_sup_basic_id,t3.content ");
			String projectName = null;
			WsPlasOrg torg = null; 
			Object[] t = null;
			for (Object tmp : ttList) {
				map = new HashMap<String, String>();
				t = (Object[])tmp;

				map.put("bidLedgerId", formatStr((String)t[0]));
				torg = PlasCache.getOrgByCd((String)t[1]);
				if(torg == null){
					projectName = "";
				}else{
					projectName = formatStr(torg.getOrgName());
				}
				map.put("ccbpProjectCd", formatStr((String)t[1]));
				map.put("ccbpProjectName", projectName);
				map.put("ccbpId",  formatStr(((String)t[2]),"-1"));//-1表示无
				map.put("ccbpNo", formatStr(((String)t[3])));//ccbpNo or  serialType+serialNo
				map.put("bidSupId", formatStr(((String)t[4])));
				map.put("supName", formatStr(((String)t[5])));
				map.put("supBasicId", formatStr(((String)t[6])));
				map.put("ccbpPlanContentDesc", formatStr(((String)t[7])).replace("/", " "));
				
				list.add(map);
			}
		}
		Struts2Utils.renderJson(list);
		
		return null;
	}
	

	/**
	 * 查询招标
	 * @param value
	 * @param projectCd
	 * @value 
	 */
	public String quickSearchZlzb(){

		String value = Struts2Utils.getParameter("value");
		String projectCd = Struts2Utils.getParameter("projectCd");

		String dataTypeCd = "2";//2-战略
		
		Map<String, Object> values = new HashMap<String, Object>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.bid_ledger_id,t.org_cd,(t.serial_type||t.serial_no) as serialTypeNo, t.bid_section_name ");
		sql.append("   from bid_ledger t ");
		sql.append("  where 1=1 ");
		
		if(StringUtils.isNotBlank(projectCd)){
			values.put("orgCd", projectCd);
			sql.append(" and t.org_cd = :orgCd ");
		}
		
		
		//（计划平台）招采战略： 0-招标 1-采购 2-战略 -> （招标平台）导入类型: 0-其他 1-战略
		if(StringUtils.isNotBlank(dataTypeCd)){
			if("2".equals(dataTypeCd)) {
				values.put("srcTypeCd", "1");
			}else{
				values.put("srcTypeCd", "0");
			}
			sql.append(" and t.src_type_cd = :srcTypeCd ");
		}	
		
		//计划编号
		if(StringUtils.isNotBlank(value)){
			values.put("serialTypeNo", "%"+ value +"%");
			sql.append(" and (t.serial_type||t.serial_no like :serialTypeNo) ");
		}
		sql.append(" order by t.created_date asc");
		
		page.setPageSize(20);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		List ttList = bidLedgerManager.findBySql(sql.toString(), values);
		if(ttList != null){ 
			
			//标题行
			Map<String, String> map = new HashMap<String,String>();
			//标题行
			map.put("headFlg", "1");
			//计划编号
			map.put("bidSerialTypeNo", "战略平台编号");
			map.put("bidProjectName", "项目名称");
			map.put("bidSectionName", "标段名称");
			
			list.add(map);
			

			//列： 标段ID，项目cd,战略计划no,标段名称
			String projectName = null;
			WsPlasOrg torg = null; 
			Object[] t = null;
			for (Object tmp : ttList) {
				map = new HashMap<String, String>();
				t = (Object[])tmp;

				map.put("bidLedgerId", formatStr((String)t[0]));
				torg = PlasCache.getOrgByCd((String)t[1]);
				if(torg == null){
					projectName = "";
				}else{
					projectName = formatStr(torg.getOrgName());
				}
				map.put("bidProjectCd", formatStr((String)t[1]));
				map.put("bidProjectName", projectName);
				map.put("bidSerialTypeNo", formatStr(((String)t[2])));
				map.put("bidSectionName", formatStr((String)t[3]));
				
				list.add(map);
			}
		}
		Struts2Utils.renderJson(list);
		
		return null;
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
	
	
	
	//加载主页面左边树

	public String getMainTree(){
	
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("root");
		rootNode.setText("招标平台");

		
	    String[] roles = SpringSecurityUtils.getCurrentRoleCds();
	    
	    List rolelist = java.util.Arrays.asList(roles);
	    if(
	       rolelist.indexOf("A_ADMIN_BID")!=-1||
	       rolelist.indexOf("A_BID_QUERY")!=-1||
	       rolelist.indexOf("A_BID_OPEN")!=-1||
	       rolelist.indexOf("A_BID_VIEW")!=-1||
	       rolelist.indexOf("A_BID_EDIT")!=-1)
	    {
		TreePanelNode node1 = new TreePanelNode();
		node1.setId("0");
		node1.setText("工程");
		rootNode.getChildren().add(node1);
		
		 TreePanelNode node1_1 = new TreePanelNode();
		 node1_1.setId("01");
		 node1_1.setText("总包");
		 
		 node1.getChildren().add(node1_1);
		 
		 TreePanelNode node1_2 = new TreePanelNode();
		 node1_2.setId("02");
		 node1_2.setText("精装");
		 
		 node1.getChildren().add(node1_2);
		 
		 TreePanelNode node1_3 = new TreePanelNode();
		 node1_3.setId("03");
		 node1_3.setText("园林景观");
		 
		 node1.getChildren().add(node1_3);
		 
		 TreePanelNode node1_4 = new TreePanelNode();
		 node1_4.setId("04");
		 node1_4.setText("消防");
		 
		 node1.getChildren().add(node1_4);
		 
		 TreePanelNode node1_5 = new TreePanelNode();
		 node1_5.setId("05");
		 node1_5.setText("暖通");
		 
		 node1.getChildren().add(node1_5);
		 
		 TreePanelNode node1_6 = new TreePanelNode();
		 node1_6.setId("06");
		 node1_6.setText("其他");
		 
		 node1.getChildren().add(node1_6);
		 
	    }
	   
	    TreePanelNode node3 = new TreePanelNode();
	    node3.setId("03");
	    node3.setText("材料设备");
	    rootNode.getChildren().add(node3);
	    
	    if(rolelist.indexOf("A_BID_STRA_VIEW")!=-1||
	       rolelist.indexOf("A_BID_STRA_USER")!=-1||
	       rolelist.indexOf("A_BID_STRA_OPEN")!=-1||
	       rolelist.indexOf("A_BID_STRA_EVAL")!=-1||
	       rolelist.indexOf("A_BID_STRA_ADMIN")!=-1){
        
		TreePanelNode node2 = new TreePanelNode();
		node2.setId("1");
		node2.setText("战略");
		rootNode.getChildren().add(node2);
	    }

		Struts2Utils.renderJson(rootNode);
		
		return null;
	}
	
	/**
	 * 加载工程主界面
	 * @return
	 */
	public String mainProject(){
		
		//获取数据
		
		return "mainProject";
	}
	
	public void prepareOpenRecord() throws Exception {
		prepareModel();
	}
	
	/**
	 * 开标记录页面
	 */
	public String openRecord(){
		
		return "openRecord";
	}
}

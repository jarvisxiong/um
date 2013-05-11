package com.hhz.ump.web.bid;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bid.BidConsultManager;
import com.hhz.ump.dao.bid.BidLedgerManager;
import com.hhz.ump.dao.bid.BidSupAttachRelManager;
import com.hhz.ump.dao.bid.BidSupManager;
import com.hhz.ump.dao.sup.SupContactorManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.bid.BidConsult;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.entity.sup.SupContactor;

@Namespace("/bid")
public class BidSupAction extends CrudActionSupport<BidSup> {

	/**
	 * serialNumber
	 */
	private static final long serialVersionUID = -1561030936539767384L;

	@Autowired
	protected BidSupManager bidSupManager;
	@Autowired
	protected BidLedgerManager bidLedgerManager;
	@Autowired
	protected AppAttachFileManager appAttachFileManager;
	
	@Autowired
	private BidConsultManager bidConsultManager;
	@Autowired
	private SupContactorManager supContactorManager;
	@Autowired
	private BidSupAttachRelManager bidSupAttachRelManager;
	
	/**
	 * 供应商实体
	 */
	private BidSup entity;
	/**
	 * 供应商ID
	 */
	private String bidSupId;

	/**
	 * 投标台帐ID(搜索投标单位使用)
	 */
	private String bidLedgerId;
	
	/**
	 * 投标台帐下的所有投标单位信息列表 
	 */
	private List<BidSupVo> listBidSupVo;
	
	private BidLedger bidLedger;
	/**
	 * 联系人 
	 */
	private List<SupContactor> contactors;
	/**
	 * 附件列表 
	 */
	private Map <String,List> attachMap;
	
	/**
	 * 删除供应商
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#delete()
	 */
	@Override
	public String delete() throws Exception { 
		//String bidLedgerId=Struts2Utils.getParameter("bidLedgerId");
		String [] ids={bidSupId};
		bidSupManager.deleteBatch(ids);
		Struts2Utils.renderText("success,"+bidLedgerId);
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}
	
	/**
	 * 搜索相应标段的投标单位
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#list()
	 */
	@Override
	public String list() throws Exception {

		if (StringUtils.isBlank(this.getBidLedgerId()))
			throw new Exception("bidLedgerId is blank!");

		bidLedger = bidLedgerManager.getEntity(getBidLedgerId());
		listBidSupVo = bidSupManager.getBidSupListByLedgerId(this.getBidLedgerId(),bidLedger);
		
		
		//特殊处理
		//评标以前，不能看到汇总
//		public static final String BID_STATUS_IMPORT = "0";
//		public static final String BID_STATUS_INVITE = "1";
//		public static final String BID_STATUS_BIDDING  = "2";
		
		String tmpBidStatusCd = bidLedger.getBidStatusCd();
		if(BidLedgerManager.BID_STATUS_IMPORT.equals(tmpBidStatusCd) 
				||BidLedgerManager.BID_STATUS_INVITE.equals(tmpBidStatusCd) 
					||BidLedgerManager.BID_STATUS_BIDDING.equals(tmpBidStatusCd) ){
			
			for (BidSupVo tmpVo : listBidSupVo) {
				tmpVo.setLastTotalAmt("***");
			}
			
		}
		listBidSupVo=bidSupManager.getBidSupByBidLedgerId(getBidLedgerId(),listBidSupVo);
		String flag = Struts2Utils.getParameter("flag");
		if("open".equals(flag)){
			BidSupVo bd = null;
			for (BidSupVo tmpVo : listBidSupVo) {
				 if("2".equals(tmpVo.getTypeCd())){
					 bd = tmpVo;
					 break;
				 }
			}
			listBidSupVo.remove(bd);
		
			return "listForOpen";
		}
		return "list";
	}
	
	
	/**
	 * 校验是否存在提交过保证金，但未确认保证金的情况
	 * @return
	 */
	public String unCheckedGuar(){
		bidLedger = bidLedgerManager.getEntity(getBidLedgerId());
		boolean flg=false;
		for(BidSup sup:bidLedger.getBidSups()){
				//上传过保证金
			if (StringUtils.isNotBlank(sup.getSubmitAttachFlg()) 
					&&"1".equals(sup.getSubmitAttachFlg())
					//未确认保证金
					&&(StringUtils.isBlank(sup.getGuarAttaConfFlg())||"0".equals(sup.getGuarAttaConfFlg()))) {
				flg=true;
				break;
			}
		}
		if(flg){
			Struts2Utils.renderText("has,1");
		}else{
			Struts2Utils.renderText("none,0");
		}
		return null;
	}
	
	
	/**
	 * 查看是否存在未上传投标文件的供应商
	 * @return
	 */
	public String unUploadAttach(){
		bidLedger = bidLedgerManager.getEntity(getBidLedgerId());
		//搜索是否存在未上传投标文件的供应商
		boolean flg=bidLedgerManager.hasNotUploadAttach(bidLedger);		
		if(flg){
			Struts2Utils.renderText("has,1");
		}else{
			Struts2Utils.renderText("none,0");
		}
		return null;
	}
	
	/**
	 * 战略材料投标供应商搜索(战略投标明细页面的供应商列表)
	 * @return
	 * @throws Exception
	 */
	public String strategyList() throws Exception{
		//执行搜索
		list();
		
		return "strategyList";
	}

	private List<BidSup> bidSups;
	
	public List<BidSup> getBidSups() {
		return bidSups;
	}

	public void setBidSups(List<BidSup> bidSups) {
		this.bidSups = bidSups;
	}

	/**
	 * 战略投标附件列表入口页面
	 * @param bidLedgerId
	 * 
	 * @return
	 */
	public String attachList() {
		bidLedgerId = Struts2Utils.getParameter("bidLedgerId");
		bidLedger = bidLedgerManager.getEntity(bidLedgerId);
		 bidSups = bidLedger.getBidSups();
		return "attachList";
	}
	
	
	/**
	 * 战略投标轮次列表
	 * @return
	 * @throws Exception
	 */
	public String supAttachList() throws Exception{
		//执行搜索
		list();
		//根据供应商获取投标列表
		listBidSupVo=bidSupAttachRelManager.findSupAttachList(listBidSupVo);
		attachMap=bidSupAttachRelManager.findAttachList(bidLedgerId);
		return "supAttachList";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(getId()))
			throw new Exception("SUPID为空！");
		else {
			entity = bidSupManager.getEntity(getId());
		}
	}
	
	/**
	 * 
	 *保存前准备动作
	 */
	public void preareSave() throws Exception{
		prepareModel();
	}
	
	/**
	 *保存供应商 
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#save()
	 */
	@Override
	public String save() throws Exception {
		bidSupManager.saveBidSup(entity);
		Struts2Utils.renderText("success");
		return null;
	}
	
	/**
	 * 增加新的供应商
	 */
	public String saveNewSup(){	
		
		String supBasicId=Struts2Utils.getParameter("supBasicId");		
		String typeCd=Struts2Utils.getParameter("typeCd");
		String bidLedgerId=Struts2Utils.getParameter("bidLedgerId");
		//校验在同一标段是否存在同样的供应商
		if(StringUtils.isNotBlank(supBasicId)&&StringUtils.isNotBlank(bidLedgerId)){
			if(bidSupManager.hasExistBidSup(supBasicId, bidLedgerId)){
				Struts2Utils.renderText("false,此投标单位已经存在，不能添加！");
				return null;
			}
		}
		//搜索原始供应商库
		SupBasic supBasic = bidSupManager.getSupBasic(supBasicId);
		//将原始供应商转换为头便单位 
		bidSupManager.saveNewBidSup(supBasic, bidLedgerId);
		Struts2Utils.renderText("success,"+supBasic.getSupName()+"添加成功！");
		return null;
	}
	
	
	
	/**
	 * 提交保证金
	 * @param id
	 * @throws Exception
	 */
	public void preareSubmitGurantee() throws Exception{
		prepareModel();
	}
	public String submitGurantee(){
		
		entity.setSubmitStatusCd("1");//
		Struts2Utils.renderText("success");
		return null;
	}
	
	/**
	 * 确认保证金
	 * @return
	 * @throws Exception 
	 */
	public String checkGurantee() throws Exception{
		if (StringUtils.isBlank(getId()))
			throw new Exception("SUPID为空！");
		else {
			entity = bidSupManager.getEntity(getId());
		}
		if(entity!=null){
			String guarAttaConfFlg=Struts2Utils.getParameter("guarAttaConfFlg");
			if(StringUtils.isNotBlank(guarAttaConfFlg)){
				entity.setGuarAttaConfFlg(guarAttaConfFlg);
			}
			entity.setGuarAttaConfName(SpringSecurityUtils.getCurrentUserName());
			entity.setGuarAttaConfUiid(SpringSecurityUtils.getCurrentUiid());
			entity.setGuarAttaConfDate(new Date());
			bidSupManager.saveBidSup(entity);
		}
		Struts2Utils.renderText("success,"+entity.getBidLedger().getBidLedgerId());
		return null;
	}

	@Override
	public BidSup getModel() {
		return null;
	}

	public String getBidLedgerId() {
		return bidLedgerId;
	}

	public void setBidLedgerId(String bidLedgerId) {
		this.bidLedgerId = bidLedgerId;
	}

	public BidSup getEntity() {
		return entity;
	}

	public void setEntity(BidSup entity) {
		this.entity = entity;
	}

	public List<BidSupVo> getListBidSupVo() {
		return listBidSupVo;
	}

	public void setListBidSupVo(List<BidSupVo> listBidSupVo) {
		this.listBidSupVo = listBidSupVo;
	}
	
	
	
	/**
	 * 
	 * 校验是否存已接受要标，但没有提交保证金的供应商
	 */
	public String validateHasNotCommitMargin(){
		String bidLedgerId=Struts2Utils.getParameter("bidLedgerId");
		if(StringUtils.isNotBlank(bidLedgerId)){
			StringBuffer sbHql=new StringBuffer()
			//搜索某标段中接受要标,且未提交保证金的数量
			.append("from BidSup sup where sup.receiveStatusCd = :receiveStatusCd ")
			.append(" and (sup.submitStatusCd = :submitStatusCd or sup.submitStatusCd is null)" )
			.append(" and sup.bidLedger.bidLedgerId = :bidLedgerId ");
			//搜索条件
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("receiveStatusCd", BidSupManager.RECEIVE_STATUS_YES);
			values.put("submitStatusCd", BidSupManager.SUBMIT_STATUS_NO);
			values.put("bidLedgerId", bidLedgerId);
			
			long cnt = bidSupManager.countHqlResult(sbHql.toString(), values);
			if(cnt>0){
				 Struts2Utils.renderText("failure,"+cnt);
			}else{
				 Struts2Utils.renderText("success,0"); 
			 }
			
		}else{
			Struts2Utils.renderText("success,0");
		}
		return null;
	}
	
	
	/**
	 * 
	 * 校验是否存已接受要标，但没有提交保证金的供应商
	 */
	public String strategyHasNotCommitMargin(){
		String bidLedgerId=Struts2Utils.getParameter("bidLedgerId");		
		
		if(StringUtils.isNotBlank(bidLedgerId)){
			bidLedger=bidLedgerManager.getEntity(bidLedgerId);
			if(bidLedger!=null){
				//如果需要保证金
				if(StringUtils.isNotBlank(bidLedger.getNeedGuarFlg())&&"1".equals(bidLedger.getNeedGuarFlg())){
					StringBuffer sbHql=new StringBuffer()
					//搜索某标段中接受要标,且未提交保证金的数量
					.append("from BidSup sup where sup.receiveStatusCd = :receiveStatusCd ")
					.append(" and (sup.submitStatusCd = :submitStatusCd or sup.submitStatusCd is null)" )
					.append(" and sup.bidLedger.bidLedgerId = :bidLedgerId ");
					//搜索条件
					Map<String, Object> values = new HashMap<String, Object>();
					values.put("receiveStatusCd", BidSupManager.RECEIVE_STATUS_YES);
					values.put("submitStatusCd", BidSupManager.SUBMIT_STATUS_NO);
					values.put("bidLedgerId", bidLedgerId);
					
					long cnt = bidSupManager.countHqlResult(sbHql.toString(), values);
					if(cnt>0){
						 Struts2Utils.renderText("failure,"+cnt);
					}else{
						 Struts2Utils.renderText("success,0"); 
					 }
				}
				//不需要保证金
				else{
					Struts2Utils.renderText("success,0");
				}
			}else{
				Struts2Utils.renderText("success,0");
			}			
			
		}else{
			Struts2Utils.renderText("success,0");
		}
		return null;
	}
	
	
	/**
	 *搜索未开通帐号的供应商
	 */
	public String validateHasNoAcctSup(){
		//搜索帐号未开通数量
		StringBuffer sql=new StringBuffer().append("select   vs.sup_name,vs.bid_sup_id,vs.bid_ledger_id from vw_bid_sup vs ");
		sql.append(" where vs.sup_user_cd is null and vs.bid_ledger_id = :bidLedgerId and type_cd = :typeCd");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidLedgerId", this.getBidLedgerId());
		//1为供应商
		map.put("typeCd", "1");
		List<Object[]> unAccounted=bidLedgerManager.findBySql(sql.toString(), map);
		StringBuffer unAccountedName=new StringBuffer();
		if(unAccounted!=null&&unAccounted.size()>0){
			for(int i=0;i<unAccounted.size();i++){
				unAccountedName.append(i+1+"."+(String)unAccounted.get(i)[0]+" \n ");
			}
			Struts2Utils.renderText("success;has;以下投标单位将自动开通官网账号，是否继续? \n "+unAccountedName);
		}else{
			Struts2Utils.renderText("success;none");
		}
		
		return null;
	}

	
	/**
	 * 检查投标单位相关的附件
	 * @param id: 投标单位id
	 * @param bizModuleCd: 附件类型
	 * 
	 * @throws Exception
	 */
	public void prepareValidateAttach() throws Exception {
		prepareModel();
	}
	
	/**
	 * 
	 *验证是否存在相应的附件，以及更新附件的标记信息
	 */
	public void validateAttach() {
		String bizModuleCd = Struts2Utils.getParameter("bizModuleCd");
		if (entity != null) {
			//bidSup-上传投标单位资料(附件)
			if("bidSup".equals(bizModuleCd)){
				List<AppAttachFile> fileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(getId(), "bidSup");
				if (fileList == null || fileList.size() == 0) {
					updateAttachFlgSup("0");
					Struts2Utils.renderText("0");
				} else {
					updateAttachFlgSup("1");
					Struts2Utils.renderText("1");
				}
			}
			//bidSupGurantee-上传保证金(附件)
			else if("bidSupGurantee".equals(bizModuleCd)){
				String updateFlg=Struts2Utils.getParameter("updateFlg");
				List<AppAttachFile> fileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(getId(), "bidSupGurantee");
				if (fileList == null || fileList.size() == 0) {
					updateAttachFlgSupGureantee("0",updateFlg);					
					Struts2Utils.renderText("0");
				} else {
					updateAttachFlgSupGureantee("1",updateFlg);
					//如果是存在需要更新字段的情况					
					Struts2Utils.renderText("1");
				}
			}
			//bidSupTech-供应商资料(附件)
			else if("bidSupTech".equals(bizModuleCd)){
				List<AppAttachFile> fileList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(getId(), "bidSupTech");
				if (fileList == null || fileList.size() == 0) {
					updateAttachFlgSupTech("0");
					Struts2Utils.renderText("0");
				} else {
					updateAttachFlgSupTech("1");
					Struts2Utils.renderText("1");
				}
			}
			else{
				Struts2Utils.renderText("bidModuleCd is unkowned!");
			}
			
		}else{
			Struts2Utils.renderText("bid sup is not founded!");
		}
	}

//	.append(" t.sup_bid_status_cd, " )//上传投标单位资料(附件)
//	.append(" t.attach_flg," )//上传保证金(附件)
//	.append(" t.submit_attach_flg," )//上传技术标(附件)
	
	/** 
	 * 
	 *投标单位  附件 
	*/  
	private void updateAttachFlgSup(String cd){
		if(StringUtils.isBlank(cd))
			return;
		if (!cd.equals(entity.getAttachFlg())) {
			entity.setAttachFlg(cd);
			bidSupManager.saveBidSup(entity);
		}
	}
	
	/**
	 * 
	 * 保证金 附件
	 */
	private void updateAttachFlgSupGureantee(String cd,String updateFlg){
		if(StringUtils.isBlank(cd))
			return;
//		pd端不上传保证金附件
//		if (!cd.equals(entity.getSubmitAttachFlg())) {
//			entity.setSubmitAttachFlg(cd);
//			entity.setSubmitStatusCd(cd);
//			bidSupManager.saveBidSup(entity);
//		}
		if(StringUtils.isNotBlank(updateFlg)){
			if("1".equals(cd)){
				
					//更新字段，保证金已上传
					entity.setSubmitAttachFlg("1");
					entity.setSubmitStatusCd("1");
					entity.setSubmitDate(new Date());
					bidSupManager.saveBidSup(entity);				
				
			}
			else if("0".equals(cd)){
				entity.setSubmitAttachFlg("0");
				entity.setSubmitStatusCd("0");
				entity.setSubmitDate(null);
				entity.setGuarAttaConfDate(null);
				entity.setGuarAttaConfFlg(null);
				bidSupManager.saveBidSup(entity);
			}
			bidSupManager.getDao().getSession().flush();
		}
		
	}
	
	/**  
	 * 技术标附件 
	*/  
	private void updateAttachFlgSupTech(String cd){
		if(StringUtils.isBlank(cd))
			return;
		if (!cd.equals(entity.getTechAttachStatusCd())) {
			entity.setTechAttachStatusCd(cd);
			bidSupManager.saveBidSup(entity);
		}
	}

	public BidLedger getBidLedger() {
		return bidLedger;
	}

	public void setBidLedger(BidLedger bidLedger) {
		this.bidLedger = bidLedger;
	}
	
	public List<SupContactor> getContactors() {
		return contactors;
	}

	public void setContactors(List<SupContactor> contactors) {
		this.contactors = contactors;
	}

	
	/**
	 * 开通官网账号(暂时不启用)
	 * 
	 */
	public String openPlacct(){
		//搜索未开通帐号的供应商，开通帐号
		StringBuffer sql=new StringBuffer().append("select  vs.bid_sup_id,vs.bid_ledger_id from vw_bid_sup vs ");
		sql.append(" where vs.sup_user_cd is null and vs.bid_ledger_id=:bidLedgerId ");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("bidLedgerId", this.getBidLedgerId());
		List<Object[]> unAccounted=bidLedgerManager.findBySql(sql.toString(), map);
		try {
			if (unAccounted != null && unAccounted.size() > 0) {
				for (int i = 0; i < unAccounted.size(); i++) {
					Object[] o = unAccounted.get(i);
					//供应商ID
					String bidSupId = (String) o[0];
					if (bidSupId != null) {
						bidSupManager.openPlacct(bidSupId,"1");
					}
				}
				Struts2Utils.renderText("success,"+unAccounted.size()+"家帐号开通成功!");
			}
			
		} catch (Exception e) {
			Struts2Utils.renderText("error,"+e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 设置中标
	 * 
	 */
	public String setBidSucc(){
		String tmpBidSupId = Struts2Utils.getParameter("bidSupId");
		if(StringUtils.isBlank(tmpBidSupId)){
			Struts2Utils.renderText(" bidSupId is blank! ");
			return null;
		}
		BidSup tmpSup = bidSupManager.getEntity(tmpBidSupId);
		if(tmpSup == null){
			Struts2Utils.renderText("投标单位不存在!");
			return null;
		}
		//标段表
		BidLedger tmpBidLedger = tmpSup.getBidLedger();
//		tmpBidLedger.setBidConfirmRealDate(new Date());
//		tmpBidLedger.setBidStatusCd(BidLedgerManager.BID_STATUS_WIN);
//		bidLedgerManager.saveBidLedger(tmpBidLedger);
		//投标单位
		//清空中标
		bidSupManager.cleanWinner(tmpBidLedger.getBidLedgerId(), tmpSup.getBidSupId());
		//设置推荐中标
		tmpSup.setSupBidStatusCd(BidSupManager.SUCC_STATUS_POP);
		tmpSup.setBidDate(new Date());
		bidSupManager.saveBidSup(tmpSup);
		Struts2Utils.renderText("success");
		
		return null;
	}
	
	/**
	 * 取消中标
	 * 
	 */
	public String cancelBidSucc(){
		String tmpBidSupId = Struts2Utils.getParameter("bidSupId");
		if(StringUtils.isBlank(tmpBidSupId)){
			Struts2Utils.renderText(" bidSupId is blank! ");
			return null;
		}
		BidSup tmpSup = bidSupManager.getEntity(tmpBidSupId);
		if(tmpSup == null){
			Struts2Utils.renderText("投标单位不存在!");
			return null;
		}
		//标段表
		BidLedger tmpBidLedger = tmpSup.getBidLedger();
//		tmpBidLedger.setBidStatusCd(BidLedgerManager.BID_STATUS_MARKING);
//		bidLedgerManager.saveBidLedger(tmpBidLedger);
		//投标单位
		tmpSup.setSupBidStatusCd(BidSupManager.SUCC_STATUS_NO);
		tmpSup.setBidDate(null);
		bidSupManager.saveBidSup(tmpSup);
		Struts2Utils.renderText("success");
		
		return null;
	}
	
	/**
	 * 退款
	 * 
	 */
	public String supRefund(){
		String tmpBidSupId = Struts2Utils.getParameter("bidSupId");
		if(StringUtils.isBlank(tmpBidSupId)){
			Struts2Utils.renderText(" bidSupId is blank! ");
			return null;
		}
		BidSup tmpSup = bidSupManager.getEntity(tmpBidSupId);
		if(tmpSup == null){
			Struts2Utils.renderText("投标单位不存在!");
			return null;
		}
		//投标单位
		tmpSup.setRefundStatusCd(BidSupManager.REFUND_STATUS_YES);
		tmpSup.setRefundDate(new Date());
		bidSupManager.saveBidSup(tmpSup);
		Struts2Utils.renderText("success");
		
		return null;
	}
	
	/**
	 * 
	 * 搜索多个联系人信息
	 */
	public String supHasMultiContactors(){
		BidLedger bidLedger = bidLedgerManager.getEntity(bidLedgerId);
		listBidSupVo=bidSupManager.getSupHasMultiContactors(bidLedger.getBidSups());
		return "contactors";
	}
	
	/**
	 * 
	 * 获取单位类型
	 */
	public String loadSupType(){
		//搜索标段的标底单位
		String cmd=Struts2Utils.getParameter("cmd");
		BidSup bidSup=bidSupManager.getBiaodiSup(bidLedgerId);
		BidLedger bl=null;
		//切换为咨询公司-"0"
		if("false".equals(cmd)){
			BidConsult bidConsult=bidConsultManager.getConsult();
			if(bidConsult!=null){
				bidSup.setRelSupBasicId(bidConsult.getBidConsultId());
				bidSup.setSupName(bidConsult.getBriefName());
				bl=bidSup.getBidLedger();
				bl.setConsultFlg("0");				
			}			
		}
		//切换为模拟公司-"1"
		else{
			BidConsult bidConsult=bidConsultManager.getSimulate();
			if(bidConsult!=null){
				bidSup.setRelSupBasicId(bidConsult.getBidConsultId());
				bidSup.setSupName(bidConsult.getBriefName());
				bl=bidSup.getBidLedger();
				bl.setConsultFlg("1");
			}	
		}
		bidSupManager.saveBidSup(bidSup);		
		bidLedgerManager.saveBidLedger(bl);
		Struts2Utils.renderText("success");
		return null;
	}
	
	/**
	 * 加载投标单位联系人列表
	 */
	public String loadSupContactor(){
		String supBasicId=Struts2Utils.getParameter("supBasicId");
		entity = bidSupManager.getEntity(bidSupId);
		contactors=bidSupManager.getSupBasicContactors(supBasicId);
		return "basicContactor";
	}
	
	/**
	 *更新或选择供应商联系人
	 */
	public String chooseContactor(){
		String supContactorId=Struts2Utils.getParameter("supContactorId");
		if(StringUtils.isNotBlank(supContactorId)){
			SupContactor contactor = supContactorManager.getEntity(supContactorId);
			BidSup bidSup = bidSupManager.getEntity(bidSupId);
			bidSup.setLinkMan(contactor.getName());
			bidSup.setLinkDesc(contactor.getTelephone());
			bidSup.setEmail(contactor.getEMail());
			bidSupManager.saveBidSup(bidSup);
			Struts2Utils.renderText("success,"+bidSup.getBidLedger().getBidLedgerId()+",");
		}
		
		return null;
	}

	public String getBidSupId() {
		return bidSupId;
	}

	public void setBidSupId(String bidSupId) {
		this.bidSupId = bidSupId;
	}

	public Map<String, List> getAttachMap() {
		return attachMap;
	}

	public void setAttachMap(Map<String, List> attachMap) {
		this.attachMap = attachMap;
	}


	
	private String formatStr(String t){
		return StringUtils.isBlank(t)?"":t.trim();
	}


	/**
	 * 查询投标单位 
	 *@param  value 投标单位名称, 可空
	 *@param  ccbpId 招标采购Id, 可空
	 *@param  ccbpNo 招标采购No, 不空
	 *
	 */
	public String quickSearchBidSup(){

		String value = Struts2Utils.getParameter("value");
		String ccbpNo = Struts2Utils.getParameter("ccbpNo");
		String ccbpId = Struts2Utils.getParameter("ccbpId");

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("typeCd", BidSupManager.SUP_PROVIDOR);//投标单位
		
		StringBuffer hql = new StringBuffer();
		hql.append(" from BidSup t where t.typeCd = :typeCd ");

		if(StringUtils.isNotBlank(value)){
			values.put("supName", "%" + formatStr(value) +"%");
			hql.append(" and t.supName like :supName");
		}
		
		if(StringUtils.isNotBlank(ccbpId)){
			values.put("ccbpId",ccbpId.trim());
			hql.append(" and t.bidLedger.bidLedgerId in( select t2.bidLedgerId from BidLedger t2 where t2.ccbpId = :ccbpId) ");
		}else{
			if(StringUtils.isNotBlank(ccbpNo)){
			values.put("ccbpNo", ccbpNo.trim());
			hql.append(" and t.bidLedger.bidLedgerId in( select t2.bidLedgerId from BidLedger t2 where t2.ccbpNo = :ccbpNo) ");
			}
		}
		
		page.setPageSize(20);
		page = bidSupManager.findPage(page, hql.toString(),values);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		Map<String, String> map = null;
		BidLedger tmpLedger = null;
		//列: bidSupId, supBasicId, supName
		for (BidSup tmp : page.getResult()) {
			tmpLedger = tmp.getBidLedger();
			map = new HashMap<String, String>();
			map.put("bidLedgerId", tmpLedger.getBidLedgerId());
			map.put("bidLedgerName", "["+ tmpLedger.getBidSectionName()+"]");
			map.put("bidSupId", tmp.getBidSupId());
			map.put("supBasicId", tmp.getRelSupBasicId());
			map.put("supName", tmp.getSupName());
			map.put("ccbpId", tmpLedger.getCcbpId());
			map.put("ccbpNo", tmpLedger.getCcbpNo());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		
		return null;
	}
}

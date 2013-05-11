package com.hhz.ump.dao.bid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.entity.bid.BidConsult;
import com.hhz.ump.entity.bid.BidLedger;
import com.hhz.ump.entity.bid.BidSup;
import com.hhz.ump.entity.sup.SupBasic;
import com.hhz.ump.entity.sup.SupContactor;
import com.hhz.ump.util.EmailUtil;
import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.PinyinConv;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.bid.BidSupVo;
import com.hhz.ump.web.bid.ContactorVo;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.hhz.uums.service.WSPlasService;

@Service
@Transactional
public class BidSupManager extends BaseService<BidSup, String> {
	

	private static Log log = LogFactory.getLog(BidSupManager.class);
	
	@Autowired
	private BidSupDao bidSupDao;
	@Autowired
	private SupBasicManager supBasicManager;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder; 
	@Autowired
	private BidConsultManager bidConsultManager;
	@Autowired
	private BidLedgerManager bidLedgerManager;


	//是否接受(1-是 0-否)
	public static final String RECEIVE_STATUS_YES = "1";
	public static final String RECEIVE_STATUS_NO = "0";
	public static final String RECEIVE_STATUS_UNKNOWN = null;
	
	//是否提交保证金(1-是 0-否)
	public static final String SUBMIT_STATUS_YES = "1";
	public static final String SUBMIT_STATUS_NO = "0";
	public static final String SUBMIT_STATUS_UNKNOWN = null;
	
	//是否退款(1-是 0-否)
	public static final String REFUND_STATUS_YES = "1";
	public static final String REFUND_STATUS_NO = "0";
	public static final String REFUND_STATUS_UNKNOWN = null;
	
	//是否中标(1-是 0-否2推荐中标)
	public static final String SUCC_STATUS_YES = "1";
	public static final String SUCC_STATUS_NO = "0";
	public static final String SUCC_STATUS_POP = "2";
	public static final String SUCC_STATUS_UNKNOWN = null;
	
	//是否附件(1-是 0-否)
	public static final String ATTACH_FILE_YES = "1";
	public static final String ATTACH_FILE_NO = "0";
	
	
	//是
	public static final String YES="1";
	public static final String HASUBMITED="已提交";
	public static final String RECEIVE="接受";
	//否
	public static final String NO="0";
	public static final String HASNOTUBMITED="未提交";
	public static final String REJECT="拒绝";
	
	//供应商
	public static final String SUP_PROVIDOR = "1";
	//咨询公司
	public static final String SUP_CONSULTING="2";

	public void saveBidSup(BidSup bidSup) {
		PowerUtils.setEmptyStr2Null(bidSup);
		bidSupDao.save(bidSup);
	}

	public void deleteBidSup(String id) {
		bidSupDao.delete(id);
	}
	
	@Override
	public HibernateDao<BidSup, String> getDao() {
		return bidSupDao;
	}
	 
	/**
	 * 根据投标台账ID搜索投标单位列表
	 * @param bidLedgerId
	 * @return
	 */
	public List<BidSupVo> getBidSupListByLedgerId(String bidLedgerId,BidLedger bidLedger) {
				
		String hql = new StringBuffer()
		.append(" select ")
		.append(" t.bid_ledger_id," )//1
		.append(" t.bid_sup_id," )//2
		.append(" t.sup_name," )//3
		.append(" t.link_man," )//4
		.append(" t.link_desc," )//5
		.append(" t.email," )//6
		.append(" t.provide_level_cd," )//7
		
		.append(" to_char(bid_division_sup_rel_count) as a1," )	//8
		.append(" to_char(bid_division_sup_rel ) as a2," )	//9
		.append(" to_char(bid_measure_sup_rel + bid_measure_sup_rel2) as a3," )	//10
		.append(" to_char(bid_division_sup_rel + bid_measure_sup_rel +bid_measure_sup_rel2+ bid_other_item_rel+bid_sporadic_sup_rel + bid_fees_sup_rel + bid_taxs_sup_rel + bid_sup_other_rel) as a4," )//11
		
		 
		.append(" t.attach_flg, " )//上传投标单位资料(附件)12
		.append(" t.submit_attach_flg," )//上传保证金(附件)13
		.append(" t.tech_attach_status_cd," )//上传技术标(附件)14
		.append(" t.receive_status_cd," )//15
		
		.append(" t.visable_flg, " )//明、暗标16
		.append(" t.type_cd, " )//单位类型17
		.append(" t.sup_user_cd, " )//官网(供应商账号)18
		
		.append(" t.refund_status_cd, ")//是否退款 19
		.append(" t.refund_date, ")//退款日期20
		.append(" t.sup_bid_status_cd, ")//是否中标 21
		.append(" t.bid_date, ")//日期22
		.append(" t.disp_order_no, ")//显示序号23
		.append("t.rel_sup_basic_id, ")//原始供应商ID24
		//--战略需要的字段
		.append(" t.GUAR_ATTA_CONF_FLG, ")//是否确认状态25
		.append(" t.GUAR_ATTA_CONF_NAME, ")//确认人26
		.append(" t.GUAR_ATTA_CONF_UIID, ")//确认人27
		.append(" t.GUAR_ATTA_CONF_DATE, ")//确认日期28
		.append(" t.receive_date ")//确认日期29
		.append(" from vw_bid_sup t " )
		.append(" where t.bid_ledger_id = :bidLedgerId ")
		.append(" order by t.type_cd desc,t.disp_order_no asc " )
				
		.toString();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bidLedgerId", bidLedgerId);
		
		List result = this.findBySql(hql, map);
		List<BidSupVo> list = new ArrayList<BidSupVo>();
		if(result != null){
			Object[] arr = null;
			BidSupVo vo = null;
			for (Object tmp : result) {
				arr = (Object[])tmp;
				vo = new BidSupVo();
				vo.setBidLedgerId((String)arr[0]);//标段ID
				
				vo.setBidSupId((String)arr[1]);// 投标单位Id
				vo.setBidSupName((String)arr[2]);// 投标单位名称
				vo.setLinkMan((String)arr[3]);// 联系人
				vo.setLinkDesc((String)arr[4]);// 联系方式
				vo.setEmail((String)arr[5]);//电子邮件
				vo.setSupLevelCd((String)arr[6]);// 供方级别
				
				vo.setTotalBatchCount((String)arr[7]);// 报价次数
				vo.setLastItemAmt((String)arr[8]);// 分部分项报价
			
				vo.setLastMeasureAmt((String)arr[9]);// 措施项目报价
				vo.setLastTotalAmt((String)arr[10]);// 合计
				//如果是标底公司
				//if(arr[16]!=null&&BidSupManager.SUP_CONSULTING.equals(arr[16].toString())){
					//BigDecimal bdfbfx=bidDivisitonManager.getBidDivisionTotalByBidLedger(bidLedgerId);
					//vo.setLastItemAmt(bdfbfx.toString());// 分部分项报价
					//BigDecimal t=BigDecimal.valueOf(Double.parseDouble(arr[10].toString()));
					//t=t.add(bdfbfx);
					//vo.setLastTotalAmt(t.toString());// 合计
				//}
				vo.setSupAttachFileFlg((String)arr[11]);// "投标单位"附件
				vo.setGuranteeStatusCd((String)arr[12]);// 是否上传保证金
				vo.setTechAttachFlg((String)arr[13]);// "技术标"附件
				vo.setReceiveStatusCd((String)arr[14]);// 应标状态
				vo.setBidVisibleFlg((String)arr[15]);
				vo.setTypeCd((String)arr[16]);//邀请类型
				vo.setSupUserCd((String)arr[17]);
				

				vo.setRefundStatusCd((String)arr[18]);//是否退款 
				vo.setRefundDate((Date)arr[19]);//退款日期
				vo.setSupBidStatusCd((String)arr[20]);//是否中标 
				vo.setBidDate((Date)arr[21]);//中标日期
				vo.setDisplayNo(StringUtils.leftPad(((BigDecimal)arr[22]).toString(), 3, "0"));
				vo.setRefBidSupId((String)arr[23]);//BasicSup的ID
				//投标次数
				vo.setTotalBatchCount(getBidTimes((String)arr[1]));
				vo.setGuarAttaConfFlg((String)arr[24]);
				vo.setGuarAttaConfName((String)arr[25]);
				vo.setGuarAttaConfUiid((String)arr[26]);				
				if(arr[27]!=null) {
					vo.setGuarAttaConfDate((Date)arr[27]);
				}
				//应标日期
				if(arr[28]!=null){
					vo.setReceiveDate((Date)arr[28]);
				}
				list.add(vo);
			}
		}
		return list;
	}
	
	
	public String getBidTimes(String bidSupId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bidSupId", bidSupId);
		
		StringBuffer sql=new StringBuffer()
		.append("select count(*) cnt,1 from ")
		.append("(select  bdsr.batch_no from bid_division_sup_rel bdsr ")
		.append(" where bdsr.bid_sup_id = :bidSupId group by bdsr.batch_no ) t" );
		List<Object[]> rs=this.findBySql(sql.toString(), map);
		try {
			if (rs != null && rs.size() > 0)
				return rs.get(0)[0].toString();
			else
				return "0";
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
		
	}
	/**
	 * 
	 * getUndeletedSup:(获取未删除的供应商) 
	 *  
	 * @param  @return    设定文件   
	 * @return List<BidSup>    DOM对象  
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	public List<BidSup> getReceivedSup(List<BidSup> sups){
		List<BidSup> unDeletedSups=new ArrayList<BidSup>();
		if(sups!=null){
			for(BidSup sup:sups){
				if(RECEIVE_STATUS_YES.equals(sup.getReceiveStatusCd())||BidSupManager.SUP_CONSULTING.equals(sup.getTypeCd())){
					unDeletedSups.add(sup);
				}
			}
		}
		return unDeletedSups;
		
	}

	
	/**
	 * 清空中标单位
	 * @param bidLedgerId
	 * @param exceptBidSupId 排除的bidSupId
	 */
	public void cleanWinner(String bidLedgerId, String exceptBidSupId) {
		if(StringUtils.isBlank(bidLedgerId))
			return;
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("supBidStatusCdNo", SUCC_STATUS_NO);
		map.put("supBidStatusCdYes", SUCC_STATUS_POP);
		map.put("bidLedgerId", bidLedgerId);
		map.put("bidSupId", exceptBidSupId);
		
		StringBuffer hqlBuf = new StringBuffer()
			.append(" update BidSup t set t.supBidStatusCd = :supBidStatusCdNo ")
			.append(" where t.bidLedger.bidLedgerId = :bidLedgerId ")
			.append("   and t.supBidStatusCd = :supBidStatusCdYes ");
		
		if(StringUtils.isNotBlank(exceptBidSupId)){
			hqlBuf.append("   and t.bidSupId != :bidSupId ");
			
		}
		int i = this.getDao().batchExecute(hqlBuf.toString(), map);
		log.debug("清空中标单位影响["+i+"]行 ，台账id [ " + bidLedgerId + "]");
	}
	/**
	 * 
	 * hasBiaoDiSup:(根据标段ID搜索是否存在标底单位)
	 *  
	 * @param  @param bidLedgerId
	 * @param  @return      
	 * @return boolean    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public boolean hasBiaoDiSup(String bidLedgerId){
		//标记
		boolean flag=false;
		Map <String,Object> map = new HashMap<String,Object>();
		//表段
		map.put("bidLedgerId", bidLedgerId);
		//标底单位
		map.put("typeCd", SUP_CONSULTING);
		String hql = " from BidSup b where b.bidLedger.bidLedgerId = :bidLedgerId and b.typeCd = :typeCd";		
		//结果集大于0
		if(this.countHqlResult(hql, map)>0){
			flag=true;
		}
		return flag;
	}
	/**
	 * 
	 * sendUserNameAndPwd:(发送用户名和密码) 
	 *  
	 * @param  @return    设定文件  
	 * @return boolean    DOM对象  
	 * @throws Exception 
	 * @throws   
	 * @since  CodingExample　Ver 1.1
	 */
	public boolean sendUserNameAndPwd(BidSup bidSup,String userName,String pswd) throws Exception{
		//如果是模拟清单,则不发送帐号及密码
		//if("模拟清单".equals(bidSup.getSupName())){
		//log.info(">>>>>>模拟清单不需要发送帐号及密码");
		//如果是标底,则不发送帐号及密码
//		if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())){
//			log.info(">>>>>标底单位不需要发送帐号及密码");
//			return true;
//		}			
//		else{
			StringBuffer msgs=new StringBuffer()
						.append(bidSup.getSupName()+",您好,宝龙["+bidSup.getBidLedger().getOrgName()+"]项目") 
						.append("["+bidSup.getBidLedger().getBidSectionName()+"]标段,邀标已开通,请使用帐号到'www.powerlong.com'查看！");
			if(StringUtils.isNotBlank(bidSup.getOriPwd())){
				msgs.append("官网帐号:"+userName+"密码:"+pswd+",请妥善保管。");
			}
			String title="宝龙开启邀标";
			//号码及邮件
			String[] mobileNos={"13636662720"};
			String[] toMails={"email_admin@powerlong.com"};
			//如果是咨询公司,则发送给给本邀标者
			if(BidSupManager.SUP_CONSULTING.equals(bidSup.getTypeCd())){
				WsPlasUser cuser = PlasCache.getUserByUiid(SpringSecurityUtils.getCurrentUiid());
				if(StringUtils.isNotBlank(cuser.getMobilePhone())) {
					mobileNos=cuser.getMobilePhone().split(",");
				}
				if(StringUtils.isNotBlank(cuser.getEmail())) {
					toMails=cuser.getEmail().split(",");
				}
			}else{
			//非咨询公司,则发送给相应供应商的联系人
				if (StringUtils.isNotBlank(bidSup.getLinkDesc())) {
					mobileNos = bidSup.getLinkDesc().split(";");
				}
				if(bidSup.getEmail()!=null) {
					toMails=bidSup.getEmail().split(";");
				}
			}
			try {			
				//发送短信
				WSPlasService wsplasService=Util.getPlasService();
				wsplasService.sendCommonSms(title, mobileNos, msgs.toString());			
			} catch (Exception e) {
				log.error(">>>>>>>>>>>>>> 短信发送异常，号码有问题",e);
			}
			
			//发送邮件
			try {		
				EmailUtil.sendSimpleMail(SpringSecurityUtils.getCurrentUiid(), LoginUtil.getPwd(null), toMails, null, title, msgs.toString());
			} catch (Exception e) {
				log.error(">>>>>>>>>>>>>> 邮件帐号出错!",e);
				e.printStackTrace();  
				
			}
			return true;
//		}
		
	}
	
	
	/**
	 * 开通投标单位的官网供应商账号
	 * @param bidSup
	 * @throws Exception 
	 */
	public String openPlacct(String bidSupId,String typeCd) throws Exception {

		BidSup bidSup = getEntity(bidSupId);
		if(bidSup == null)
			throw new Exception("未找到投标单位!");

		//获取供应商
		String tmpBaiscId = bidSup.getRelSupBasicId();
		SupBasic supBasic =null;
		if(SUP_PROVIDOR.equals(typeCd)) {
			supBasic=supBasicManager.getEntity(tmpBaiscId);
		}
		BidConsult bc=null;
		if(SUP_CONSULTING.equals(typeCd)) {
			bc=bidConsultManager.getEntity(tmpBaiscId);
		}
		if(supBasic == null&&bc==null)
			throw new Exception("未找到供应商!");
		
		if(SUP_PROVIDOR.equals(typeCd)&&(supBasic!=null&&StringUtils.isNotBlank(supBasic.getSupInfoId())))
			throw new Exception("已开通官网账号,不需要开通!");
		
		String supName = bidSup.getSupName();
		
		//获取账号
		String py = PinyinConv.cn2py(supName).replace("_", "").toLowerCase().trim();
		if(py.length()>=6){
			py = py.substring(0,6);
		}else{
			py = StringUtils.rightPad(py, 6, "0");//不足补充0
		}
		
		//返回唯一的帐号
		String plUserCd = convertUserCd(0,py);
		
		//获取随机密码(默认6位置)
		String oriPwd = RandomUtils.generateLowerString(6);
		
		//[官网]新增user
//		PL_USER_ID      VARCHAR2(50)                            
//		USER_CODE       VARCHAR2(50)                            
//		USER_NAME       VARCHAR2(50)                            
//		PWD             VARCHAR2(50)                            
//		USER_LEVEL      VARCHAR2(255) Y                         
//		PL_AUTHORITY_ID VARCHAR2(50)
		
		//获取唯一性质
		String plUserId = RandomUtils.generateTmpEntityId();
		String plUserCode = plUserCd;
		String plUserName = supName;
		String plMd5Pwd =   md5PasswordEncoder.encodePassword(oriPwd, "");// oriPwd;
		System.out.println("密码:"+oriPwd);
		String userLevel = "";
		String authority = "7";//投标单位/供应商
		String recordVersion="1";
		
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, plUserId);
		map.put(2, plUserCode);
		map.put(3, plUserName);
		map.put(4, plMd5Pwd);
		map.put(5, userLevel);
		map.put(6, authority);
		this.executeFunction("{call pl.pkg_user_process.create_pl_user(?,?,?,?,?,?,?)}", map, String.class);
		//[官网]新增供应商库(默认审核通过)
//		PL_USER_SUP_ID  VARCHAR2(50)                   Phisical Primary Key 
//		PL_AUTHORITY_ID VARCHAR2(50)  Y                                     
//		USER_CD         VARCHAR2(50)  Y                                     
//		USER_NAME       VARCHAR2(100) Y                                     
//		COMPANY_NAME    VARCHAR2(500) Y                                     
//		TEL             VARCHAR2(100) Y                                     
//		EMAIL           VARCHAR2(100) Y                                     
//		REMARK          VARCHAR2(200) Y                备注                 
//		CREATOR         VARCHAR2(20)  Y                创建人员             
//		CREATED_DATE    DATE          Y                创建时间             
//		UPDATOR         VARCHAR2(20)  Y                最近更新人员         
//		UPDATED_DATE    DATE          Y                最近更新时间         
//		RECORD_VERSION  NUMBER(10)                     RECORD TIME STAMP    
		
//		PlUserSup plUserSup = new PlUserSup();
//		String plUserSupId = RandomUtils.generateTmpEntityId();
//		plUserSup.setPlAuthorityId("7");//7 A_SUP_INV	供方注册用户
//		plUserSup.setUserCd(plUserCd);//账号
//		plUserSup.setUserName(supName);
//		
//		Map<Integer, Object> map2 = new HashMap<Integer, Object>();
//		map2.put(1, plUserSupId);
//		map2.put(2, plUserSup.getPlAuthorityId());
//		map2.put(3, plUserSup.getUserCd());
//		map2.put(4, plUserSup.getUserName());
//		map2.put(5, recordVersion);
//		this.executeFunction("{call pl.pkg_user_process.create_pl_user_sup(?,?,?,?,?,?)}", map2, String.class);
		//plUserSupManager.savePlUserSup(plUserSup);

//		SUP_INFO_ID      VARCHAR2(50)                    Phisical Primary Key 
//		SUP_USER_CD      VARCHAR2(50)   Y                                     
//		SUP_NO           VARCHAR2(50)   Y                编号                  
//		AREASN           VARCHAR2(50)   Y                                     
//		ADDRESS          VARCHAR2(400)  Y                                     
//		AREA_NAME        VARCHAR2(50)   Y                                     
//		COOPERATED       VARCHAR2(20)   Y                                     
//		COOPERATED_SN    VARCHAR2(50)   Y                                     
//		SUP_MANAGER      VARCHAR2(50)   Y                                     
//		SUP_MANAGER_TEL  VARCHAR2(50)   Y                                     
//		REMARK           VARCHAR2(1000) Y                备注                 
//		CREATOR          VARCHAR2(20)   Y                创建人员             
//		CREATED_DEPT_CD  VARCHAR2(20)   Y                创建部门             
//		CREATED_DATE     DATE           Y                创建时间             
//		UPDATOR          VARCHAR2(20)   Y                最近更新人员         
//		UPDATED_DEPT_CD  VARCHAR2(20)   Y                最近更新部门         
//		UPDATED_DATE     DATE           Y                最近更新时间         
//		RECORD_VERSION   NUMBER(10)                      RECORD TIME STAMP    
//		DATA_STATUS      VARCHAR2(1)    Y        0                            
//		OPERATE_RANGE    VARCHAR2(200)  Y                                     
//		COMPANY_AREA     VARCHAR2(100)  Y                                     
//		COMPANY_WEB_SITE VARCHAR2(200)  Y                                     
//		DELETE_FLG       VARCHAR2(2)    Y       

		String plSupInfoId = RandomUtils.generateTmpEntityId();
		
		Map<Integer, Object> map3 = new HashMap<Integer, Object>();
		map3.put(1, plSupInfoId);
		map3.put(2, plUserCd);
		map3.put(3, supName);
		map3.put(4, "0");		
		map3.put(5, 0);
		this.executeFunction("{ call pl.pkg_user_process.create_sup_info(?,?,?,?,?,?)}", map3, String.class);
		
		//更新PD供应商库的关联字段
		if(SUP_PROVIDOR.equals(typeCd)) {
		supBasic.setSupInfoId(plSupInfoId);
		supBasicManager.saveSupBasic(supBasic, null);
		}
		else{
			bc.setPlAcct(plSupInfoId);
			bc.setPlOriPwd(oriPwd);
			bidSup.setOpenPlacctCd(plUserCd);
			bidSup.setOriPwd(oriPwd);
			bidConsultManager.saveBidConsult(bc);
		}
		
		//更新PD投标单位
		bidSup.setOpenPlacctFlg("1");//开通账号
		bidSup.setOpenPlacctCd(plUserCd);
		bidSup.setOriPwd(oriPwd);
		saveBidSup(bidSup);
		
		return plUserCd;
	}
	public String createPlAcct(){
		
		return null;
	}
	
	/**
	 * 获取唯一账号,检查账号唯一性(若重复,特殊处理,如追加0,1,2)
	 * @param seqNo
	 * @param userCd
	 * @return
	 * @throws Exception 
	 */
	private String convertUserCd(int seqNo, String userCd) throws Exception{

		if(StringUtils.isBlank(userCd))
			throw new Exception(" userCd is blank! ");

		//6拼音+"_"+3随机数
		String tUserCd = userCd + "_" + RandomUtils.generateLowerString(3);
		
		
		Map<String,Object> values = new HashMap<String,Object>();
		if(seqNo == 0){
			values.put("userCd", tUserCd);
		}else{
			values.put("userCd", (tUserCd + String.valueOf(seqNo)));
		}
		
		//大小写唯一
//		String sql = "select t.pl_user_sup_id from pl.pl_user_sup t where lower(t.user_cd) = lower(:userCd) ";
		String sql = "select t.pl_user_id from pl.pl_user t where lower(t.user_code) = lower(:userCd) ";
		List list = getDao().findBySql(sql, values);
		if(list == null || list.size() == 0)
			return tUserCd;
		else
			return convertUserCd(seqNo+1, userCd);
	}
	
	/**
	 * 
	 * oneSupHasMultiContactor:(判断供应商是否存在多个联系人)
	 *  
	 * @param  @return    设定文件  
	 * @return boolean    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public boolean oneSupHasMultiContactor(List<BidSup> bidsups){		
		boolean flag=false;
		SupBasic supBasic=null;
		for(BidSup sup:bidsups){
			//非标底供应商
			if(BidSupManager.SUP_PROVIDOR.equals(sup.getTypeCd())){
				 supBasic = supBasicManager.getEntity(sup.getRelSupBasicId());
				 //发现有多个联系人的,设置为true,推出循环
				 if(supBasic!=null&&supBasic.getSupContactors()!=null&&supBasic.getSupContactors().size()>1){
					 flag=true;
					 break;
				 }
			}
		}
		return flag;
	}
	
	public List<BidSupVo> getSupHasMultiContactors(List<BidSup> bidsups){
		List<BidSupVo> supVos=new ArrayList<BidSupVo>();
		SupBasic supBasic=null;
		for(BidSup sup:bidsups){
			//非标底供应商
			if(BidSupManager.SUP_PROVIDOR.equals(sup.getTypeCd())){
				supBasic = supBasicManager.getEntity(sup.getRelSupBasicId());
				 //发现有多个联系人的,设置为true,推出循环
				List<SupContactor> contactors=supBasic.getSupContactors();
				 if(supBasic!=null&&contactors!=null){//&&contactors.size()>1
					 BidSupVo bidSupVo=new BidSupVo();
					 bidSupVo.setBidSupName(supBasic.getSupName());
					 bidSupVo.setBidLedgerId(sup.getBidLedger().getBidLedgerId());
					 List<ContactorVo> contactorVos=new ArrayList<ContactorVo>();
					 for(SupContactor cont:contactors){
						 ContactorVo cvo=new ContactorVo();
						 cvo.setName(cont.getName());
						 cvo.setMail(cont.getEMail());
						 cvo.setPhone(cont.getTelephone());
						 cvo.setContactId(cont.getSupContactorId());
						 contactorVos.add(cvo);
					 }
					 bidSupVo.setContactors(contactorVos);
					 supVos.add(bidSupVo);
				 }				
			}
			
		}
		return supVos;
		
	}
	
	/**
	 * 
	 * getBidSupByLedgerIdAndRelSupId:(根据标段和供应商REL的ID搜索供应商)
	 *  
	 * @param  @param bidLedgerId
	 * @param  @param relBasicSupId
	 * @param  @return    设定文件  
	 * @return BidSup    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public BidSup getBidSupByLedgerIdAndRelSupId(String bidLedgerId,String relBasicSupId){
		String hql=" from BidSup bs where bs.bidLedger.bidLedgerId = :bidLedgerId and bs.relSupBasicId = :relSupBasicId";
		//准备搜索条件
		Map map=new HashMap<String,Object>();
		map.put("bidLedgerId", bidLedgerId);
		map.put("relSupBasicId", relBasicSupId);
		//执行搜索
		List<BidSup> rs = this.find(hql, map);
		if(rs!=null&& rs.size()>0)
			return rs.get(0);
		else
			return null;
		
	}
	
	public BidSup getBiaodiSup(String bidLedgerId){
		String hql=" from BidSup bs where bs.bidLedger.bidLedgerId = :bidLedgerId and bs.typeCd = :typeCd";
		//准备搜索条件
		Map map=new HashMap<String,Object>();
		map.put("bidLedgerId", bidLedgerId);
		map.put("typeCd", SUP_CONSULTING);
		List<BidSup> rs = this.find(hql, map);
		if(rs!=null&& rs.size()>0)
			return rs.get(0);
		else
			return null;
		
	}
	
	public List<SupContactor> getSupBasicContactors(String basicId) {
		 SupBasic supBasic = supBasicManager.getEntity(basicId);
		 try{
			 if (supBasic != null)
					return supBasic.getSupContactors();
				else
					return null; 
		 }catch(Exception e){
			 return null; 
		 }
		

	}
	
	
	public SupBasic getSupBasic(String supBasicId){
		SupBasic basic=supBasicManager.getEntity(supBasicId);
		return basic;
	}
	
	public boolean hasExistBidSup(String relSupBasicId,String bidLedgerId){
		boolean flg=false;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("relSupBasicId", relSupBasicId);
		map.put("bidLedgerId", bidLedgerId);
		String hql=" from BidSup bs where bs.relSupBasicId= :relSupBasicId and bs.bidLedger.bidLedgerId = :bidLedgerId";
		List l=bidSupDao.find(hql, map);
		if(l!=null&&l.size()>0){
			flg=true;
		}
		return flg;
	}
	
	
	public BidSup saveNewBidSup(SupBasic supBasic,String bidLedgerId){
		BidSup bidSup = new BidSup();
		if(supBasic!=null){
			BidLedger bidLedger = bidLedgerManager.getEntity(bidLedgerId);
			//对应台账主表
			bidSup.setBidLedger(bidLedger);
			//显示顺序号
			bidSup.setDispOrderNo(getSupCountByBidLedger(bidLedgerId)+1);
			//关联供应商ID(供应商台账库）
			bidSup.setRelSupBasicId(supBasic.getSupBasicId());
			//供应商名称
			bidSup.setSupName(supBasic.getSupName());
			//是否接受(接受/拒绝）
			bidSup.setReceiveStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
			//是否提交保证金
			bidSup.setSubmitStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
			//是否退款 
			bidSup.setRefundStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);
			//是否中标(未中标/中标)
			bidSup.setSupBidStatusCd(BidSupManager.RECEIVE_STATUS_UNKNOWN);	
			//是否有附件
			bidSup.setAttachFlg(BidSupManager.ATTACH_FILE_NO);
			//供应商
			bidSup.setTypeCd(BidSupManager.SUP_PROVIDOR);
			//供应商级别
			bidSup.setProvideLevelCd(supBasic.getSupManaEval());
		}
		bidSupDao.save(bidSup);
		return bidSup;
		
	}
	
	
	public long getSupCountByBidLedger(String bidLedgerId){
		Map map=new HashMap<String,Object>();
		map.put("bidLedgerId", bidLedgerId);
		String hql=" from BidSup bs where bs.bidLedger.bidLedgerId = :bidLedgerId";
		long cnt = this.countHqlResult(hql, map);
		return cnt;
	}
	
	/**
	 * 根据标段ID搜索供应商
	 * @param bidLedgerId
	 * @return
	 */
	public List<BidSupVo> getBidSupByBidLedgerId(String bidLedgerId,List<BidSupVo> listBidSupVo){
		List<BidSupVo> rs=new ArrayList<BidSupVo>();
		//搜索条件
		Map map=new HashMap<String,Object>();
		map.put("bidLedgerId", bidLedgerId);
		
		String hql=" from BidSup bs where bs.bidLedger.bidLedgerId = :bidLedgerId";
		List<BidSup> l=this.getDao().find(hql.toString(), map);
		Set set=new HashSet(l);
		if(l != null && l.size()>0){
			//封装Vo
			for(BidSupVo tsup: listBidSupVo){
				for(BidSup ts:l){
					if(tsup.getBidSupId().equals(ts.getBidSupId())){
						tsup.setGuranteeStatusCd(ts.getSubmitAttachFlg());
						tsup.setGuarAttaConfDate(ts.getGuarAttaConfDate());
						tsup.setGuarAttaConfFlg(ts.getGuarAttaConfFlg());
						rs.add(tsup);
						break;
					}
				}
			}
		}
		return rs;
	}

}

 
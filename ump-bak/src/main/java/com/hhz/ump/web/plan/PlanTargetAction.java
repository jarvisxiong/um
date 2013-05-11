package com.hhz.ump.web.plan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.EncodeUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.plan.ExecPlanDetailManager;
import com.hhz.ump.dao.plan.PlanMessageManager;
import com.hhz.ump.dao.plan.PlanTargetManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.plan.PlanMessage;
import com.hhz.ump.entity.plan.PlanTarget;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.JXLExcelUtil;

/**
 * @author liwei 2012-5-8
 * 
 *         总部工作计划
 */
@Namespace("/plan")
@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "plan-target!monEnter.action", type = "redirect", params = { "currentCenterCd", "${currentCenterCd}", "currentPlanMonth",
				"${currentPlanMonth}", "currentPlanYear", "${currentPlanYear}" }),
		@Result(name = "show", location = "plan-target!yearEnter.action", type = "redirect", params = { "currentCenterCd", "${currentCenterCd}", "currentPlanYear", "${currentPlanYear}" }),
		@Result(name = "Exported", location = "plan-target!exportExcel.action", type = "stream", params = { "contentType", "application/vnd.ms-excel", "contentDisposition",
				"attachment;filename=${excelFileName}.xls", "inputName", "excelFile" }) })
public class PlanTargetAction extends CrudActionSupport<PlanTarget> {

	private static final long serialVersionUID = -6596522696018949444L;

	@Autowired
	private PlanTargetManager planTargetManager;
	@Autowired
	private PlanMessageManager planMessageManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private ExecPlanDetailManager detailManager;
	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;

	public static final String STATUS_CD_WEIZHIXING = "0"; // 未执行
	public static final String STATUS_CD_JINXINGZHONG = "1"; // 进行中
	public static final String STATUS_CD_YUWANCHENG = "2"; // 预完成
	public static final String STATUS_CD_WANCHENG = "3"; // 完成
	public static final String STATUS_CD_WANCHENGDANCJGQ = "4"; // 完成但曾经过期
	public static final String STATUS_CD_SUSPEND = "5"; // 过期
	public static final String STATUS_CD_NO_ASSESS = "6"; // 非考核性过期
	public static final String STATUS_CD_NO_THIS = "7"; // 非本月任务
	public static final String STATUS_CD_YINCANG = "8"; // 隐藏
	public static final String STATUS_CD_SHENQINGSHANCHU = "9"; // 申请删除
	public static final String STATUS_CD_SHANCHU = "10"; // 删除(系统状态)
	public static final String STATUS_CD_BOHUI = "-1"; // 驳回

	public static final String TYPE_CD_NIANJIHUA = "1"; // 年计划
	public static final String TYPE_CD_YUEJIHUA = "2"; // 月会工作
	public static final String TYPE_CD_XIANGMUGUANLI = "3"; // 项目管理
	public static final String TYPE_CD_ZHILINGDAN = "4"; // 指令单
	public static final String TYPE_CD_ZHONGHE = "5"; // 综合
	public static final String TYPE_CD_JIDUKPI = "6"; // 季度KPI
	public static final String TYPE_CD_SIJIJIHUA = "7"; // 四级计划
	public static final String TYPE_CD_ZHONGXINZHONGZUO = "8"; // 中心工作

	public static final String SYS_STATUS_0 = "0"; // 正常记录
	public static final String SYS_STATUS_1 = "1"; // 逻辑删除

	public static final String REMARK_CD_UNLOCK = "0"; // 解锁
	public static final String REMARK_CD_LOCK = "1"; // 锁定

	public static final String BY_SYS = "bySys"; // 系统留言标识

	public static final String MONTH_FLG = "1"; // 是月计划

	public static final String YYYY = "yyyy";
	public static final String MM = "MM";
	public static final String MM_DD = "MM-dd";
	public static final String M_D_H_M = "MM-dd HH:mm";

	private PlanTarget entity;
	private String targetId;
	// 点击状态修改标记
	private String statusFlg;
	// 发表留言标记
	private String msgPub;
	// 修改过的内容
	private String edited;
	// 排序项
	private String orderBy;
	// 排序类型
	private String order;

	// 每页显示10条记录
	private Page<AppAttachFile> filePage = new Page<AppAttachFile>(10);
	private String bizEntityId;
	private String bizModuleCd;
	private String bizFieldName;

	private String opened_entityid;

	// 附件Id
	private String attachId;
	// 当前新建中心CD
	private String currentCenterCd;
	// 当前新建计划月份
	private String currentPlanMonth;
	// 当前新建计划年份
	private String currentPlanYear;

	private String deptFlg;// 部门标识
	private Map<String, String> mapContentTips = new HashMap<String, String>(); // 保存content的title,包含content和前三条留言记录
	private String lockFlg;// 锁定标识
	private String message;// 留言
	private String owner;
	private String befContent;// 修改之前的内容
	// 导出Excel需要的参数
	private InputStream excelFile;
	private String excelFileName;

	private Map<String, String> attentionMap; // 关注id，用来判断是否有关注
	private Map<String, String> attentionMapUnread; // 未读的关注id，关注列表时用，用来判断关注是否已读

	private List<Map<String, String>> monthList;// 年计划里面对应到的月记录
	private String haveOperAuth;//是否有该页面的功能操作权限
	
	private int totCount;//显示的，除非本月外的记录数
	private int compCount;//完成相熟
	private String compRate;//完成比率
	private String bisFlg;//成本标识

	@Override
	public PlanTarget getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			planTargetManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub

		return null;
	}

	public String monEnter() throws Exception {
		// 判断待办事项进入的参数，如果是第一次进入并且带着待办事项ID，则自动跳到那个待办事项所在的月份
		if (StringUtils.isBlank(currentPlanYear)) {
			currentPlanYear = Integer.toString(DateOperator.getYear(new Date()));
		}
		if (StringUtils.isBlank(currentPlanMonth)) {
			currentPlanMonth = Integer.toString(DateOperator.getMonth12(new Date()));
		}
		if (StringUtils.isNotEmpty(opened_entityid)) {
			PlanTarget pt = planTargetManager.getEntity(opened_entityid);
			if (null != pt) {
				currentPlanYear = pt.getTargetYear().toString();
				currentPlanMonth = pt.getTargetMonth().toString();
			}
		}
		//若商业人员进入总部工作计划，则只能看到本商业的数据
		if(",155,156,154,157,869,439,".indexOf(SpringSecurityUtils.getCurrentCenterCd())>0){
			if((SpringSecurityUtils.hasRole("A_PLAN_WORK2_DEPT")||SpringSecurityUtils.hasRole("A_PLAN_WORK_STAFF"))
					&&!SpringSecurityUtils.hasRole("A_PLAN_WORK2_VIEW")
					&&!SpringSecurityUtils.hasRole("A_PLAN_WORK2_CENTER")){
				bisFlg="1";
			}
		}
		return "month";
	}

	public void prepareMonth() throws Exception {
		entity = new PlanTarget();
	}

	/**
	 * 月计划list入口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String month() throws Exception {
		// 每页显示10条记录
		page.setPageSize(999);
		if (StringUtils.isNotEmpty(orderBy) && StringUtils.isNotEmpty(order)) {
			page.setOrderBy(orderBy);
			page.setOrder(order);
		} else {
			if ("153".equals(currentCenterCd)) {
				page.setOrderBy("targetDate,planType,center,sequenceNumber");
				page.setOrder(Page.DESC + "," + Page.ASC + "," + Page.ASC + "," + Page.ASC);
			} else {
				page.setOrderBy("planType,sequenceNumber");
				page.setOrder(Page.ASC + "," + Page.ASC);
			}
		}
		Date nowDate = new Date();
		// 计划所属年份
		if (entity.getTargetYear() == null) {
			if (StringUtils.isNotEmpty(currentPlanYear)) {
				entity.setTargetYear(Integer.parseInt(currentPlanYear));
			} else {
				entity.setTargetYear(Integer.parseInt(DateOperator.formatDate(nowDate, YYYY)));
			}
		} else {
			currentPlanYear = entity.getTargetYear().toString();
		}
		// 计划所属月份
		if (entity.getTargetMonth() == null) {
			if (StringUtils.isNotEmpty(currentPlanMonth)) {
				entity.setTargetMonth(Integer.parseInt(currentPlanMonth));
			} else {
				entity.setTargetMonth(Integer.parseInt(DateOperator.formatDate(nowDate, MM)));
			}
		} else {
			currentPlanMonth = entity.getTargetMonth().toString();
		}
		entity.setCenter(getCurrentCenterCd());
		page = planTargetManager.findPage(page, entity);
		// 页面上的留言标识，同时赋值totCount;compCount;compRate;//完成比率
		contentTips(page.getResult());
		// 锁定标识
		if (page.getResult() != null && page.getResult().size() > 0) {
			lockFlg = lock(page.getResult());
		}

		// 获取关注的记录
		attentionMap = oaAllAttentionManager.getMyAttention("planTarget", SpringSecurityUtils.getCurrentUiid());
		// 获取关注且有未读更新的记录
		List list_unread_attention = oaAllAttentionManager.getUnreadIds("planTarget", SpringSecurityUtils.getCurrentUiid(), "PLAN_TARGET", "PLAN_TARGET_ID");
		attentionMapUnread = new LinkedHashMap<String, String>();
		for (int i = 0; null != list_unread_attention && i < list_unread_attention.size(); i++) {
			String attentionEntityId = (String) list_unread_attention.get(i);
			attentionMapUnread.put(attentionEntityId, "unread");
		}
		// 权限
		setAuthority(entity.getCenter());

		if (StringUtils.isNotBlank(deptFlg) && "bis".equals(deptFlg))
			return "bis";
		else
			return "monthList";
			

	}

	// 对分管副总裁赋权限PLAN_TARGET_VICE
	private void setAuthority(String centerCdByMonth) {
		String uiid = SpringSecurityUtils.getCurrentUiid();
		//如果是副总裁
		if (SpringSecurityUtils.hasRole("A_PLAN_WORK2_VICE")) {
		Map<String, String> viceMap = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAN_TARGET_VICE);
			String centerCd = viceMap.get(uiid);
			if (null!=centerCd&&!"".equals(centerCd)) {
				// 若没有某中心的权限，则不能执行操作，减少该角色
				if (centerCd.indexOf(centerCdByMonth) >= 0) {
					haveOperAuth="true";
				}
			}
		}
		if(SpringSecurityUtils.hasRole("A_PLAN_WORK2_ADMIN")
				||SpringSecurityUtils.hasRole("A_PLAN_WORK2_CEO")){
			haveOperAuth="true";
		}
		if(SpringSecurityUtils.hasRole("A_PLAN_WORK2_CENTER")||SpringSecurityUtils.hasRole("A_PLAN_WORK_STAFF")){
			String centerCd =SpringSecurityUtils.getCurrentCenterCd();
			if(StringUtils.isBlank(centerCd)){
				//若中心用户，中心为空
				Map<String, String> viceMap = appDictTypeManager.getDictDataByTypeCd(DictContants.PLAN_TARGET_VICE);
				centerCd = viceMap.get(uiid);
				if (null!=centerCd&&!"".equals(centerCd)) {
					// 若没有某中心的权限，则不能执行操作，减少该角色
					if (centerCd.indexOf(centerCdByMonth) >= 0) {
						haveOperAuth="true";
					}
				}
			}else if(centerCdByMonth.equals(centerCd)){
				haveOperAuth="true";
			}
		}
	}


	public String yearEnter() throws Exception {
		// 判断待办事项进入的参数，如果是第一次进入并且带着待办事项ID，则自动跳到那个待办事项所在的月份
		currentPlanYear = Integer.toString(DateOperator.getYear(new Date()));
		currentPlanMonth = Integer.toString(DateOperator.getMonth12(new Date()));
		return "year";
	}

	public void prepareYear() throws Exception {
		entity = new PlanTarget();
	}

	/**
	 * 年计划的LIST页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String year() throws Exception {
		// 每页显示10条记录
		page.setPageSize(999);
		Date nowDate = new Date();
		// 计划所属年份
		if (entity.getTargetYear() == null) {
			if (StringUtils.isNotEmpty(currentPlanYear)) {
				entity.setTargetYear(Integer.parseInt(currentPlanYear));
			} else {
				entity.setTargetYear(Integer.parseInt(DateOperator.formatDate(nowDate, YYYY)));
			}
		} else {
			currentPlanYear = entity.getTargetYear().toString();
		}

		entity.setCenter(getCurrentCenterCd());
		monthList = new ArrayList<Map<String, String>>();
		planTargetManager.findYarForMonth(page, entity, monthList);

		return "yearList";

	}

	public String bis() throws Exception {
		if (StringUtils.isBlank(currentPlanYear)) {
			currentPlanYear = Integer.toString(DateOperator.getYear(new Date()));
		}
		if (StringUtils.isBlank(currentPlanMonth)) {
			currentPlanMonth = Integer.toString(DateOperator.getMonth12(new Date()));
		}
		setDeptFlg("bis");
		prepareMonth();
		return month();
	}

	public void updateYearForMonth() throws Exception {
		planTargetManager.updateYearForMonth();
	}

	/***
	 * 成本系统#计划平台#成本任务
	 * 
	 * @return page
	 * @throws Exception
	 */
	public String costYearPlan() throws Exception {
		prepareMonth();
		month();
		return "costYear";
	}

	private String lock(List<PlanTarget> targetList) {
		String flg="0";
		for(PlanTarget target : targetList){
			if (target != null && target.getRemark() != null && "1".equals(target.getRemark())){
				flg = "1";
				break;
			}
		}
		return flg;
	}

	private void contentTips(List<PlanTarget> targetList) {
		totCount=0;compCount=0;
		for (PlanTarget target : targetList) {
			// 鼠标放在标题上时，显示标题和最新的3条评论
			StringBuffer tip = new StringBuffer();
			tip.append(EncodeUtils.htmlEscape(target.getContent()) + "<br>");
			List<PlanMessage> planMessages = target.getPlanMessages();
			DateFormat df = new SimpleDateFormat("MM-dd");
			String mesContent = "";
			if (planMessages.size() > 0) {
				//tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul>");
				int size = planMessages.size() >= 3 ? 3 : planMessages.size();
				for (int i = 0; i < size; i++) {
					PlanMessage planMessage = planMessages.get(i);
					String userName = CodeNameUtil.getUserNameByCd(planMessage.getCreator());
					mesContent = planMessage.getContent();
					if (planMessage.getContent() != null && planMessage.getContent().indexOf("#18478d") > 0) {
						mesContent = planMessage.getContent().replaceAll("#18478d", "");
					}
					tip.append("<li>" + userName + "&nbsp;" + df.format(planMessage.getCreatedDate()) + "：" + EncodeUtils.htmlEscape(mesContent) + "</li>");
				}
				//tip.append("</ul>");
			}
			mapContentTips.put(target.getPlanTargetId(), tip.toString());
			//统计数
			if(!"7".equals(target.getStatus())//非考核性过期不算
					||("6".equals(target.getStatus())&&StringUtils.isNotBlank(target.getPlanTargetId()))){
				totCount++;
				if("3".equals(target.getStatus())||"4".equals(target.getStatus())){
					compCount++;
				}
			}
		}
		if(0!=totCount){
			compRate=(new BigDecimal(compCount).multiply(new BigDecimal(100)).divide(new BigDecimal(totCount), 1,
					BigDecimal.ROUND_HALF_UP)).toString();
		}
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepareSave() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planTargetManager.getEntity(getId());
			befContent = entity.getContent();
		} else {
			entity = new PlanTarget();
		}
	}

	@Override
	public String save() throws Exception {
		// 是否是批量操作
		boolean isBatch = (getIds() != null && getIds().length > 0 && "".equals(getIds()[0]) == false);
		// 置为删除状态的target list
		List<PlanTarget> targetList = new ArrayList<PlanTarget>();
		// 批量操作
		if (isBatch) {
			// 批量状态修改--除删除以外
			if (StringUtils.isNotEmpty(statusFlg)) {
				for (int i = 0; i < getIds().length; i++) {
					PlanTarget pt = planTargetManager.getPlanTargetById(getIds()[i]);
					if (statusFlg.equals(STATUS_CD_JINXINGZHONG) && STATUS_CD_WEIZHIXING.equals(pt.getStatus())) { // 只能执行未执行状态的记录
						pt.setStatus(statusFlg);
						sysMessage(pt);
					} else if (statusFlg.equals(STATUS_CD_SUSPEND) || statusFlg.equals(STATUS_CD_NO_ASSESS)) {
						// 过期和非考核性过期
						// 若状态为未执行、执行、申请删除、预完成可修改记录
						if (STATUS_CD_WEIZHIXING.equals(pt.getStatus()) == true || STATUS_CD_JINXINGZHONG.equals(pt.getStatus()) == true || STATUS_CD_SHENQINGSHANCHU.equals(pt.getStatus())
								|| STATUS_CD_YUWANCHENG.equals(pt.getStatus())) {
							execOverDue(pt);
						} else if ((STATUS_CD_SUSPEND.equals(pt.getStatus()) || STATUS_CD_NO_ASSESS.equals(pt.getStatus())) && (StringUtils.isBlank(pt.getParentTargetId()))) {
							// 如果原状态为过期、非考核性过期，没有父类ID，则为正常数据，则可以修改记录
							execOverDue(pt);
						}

					} else {
						pt.setStatus(statusFlg);
						sysMessage(pt);
					}
					if (SYS_STATUS_1.equals(pt.getSysStatusFlg())) {
						// 如果原来是系统删除掉的数据，则还原
						pt.setSysStatusFlg(SYS_STATUS_0);
					}
					planTargetManager.savePlanTarget(pt);
				}
			}

			// 批量逻辑删除
			if (SYS_STATUS_1.equals(entity.getSysStatusFlg())) {
				for (int i = 0; i < getIds().length; i++) {
					PlanTarget pt = planTargetManager.getPlanTargetById(getIds()[i]);
					pt.setStatus(STATUS_CD_SHANCHU);
					pt.setSysStatusFlg(SYS_STATUS_1);
					planTargetManager.savePlanTarget(pt);
				}
			}
		} else {
			// 新增计划
			if (getId() == null) {

				entity.setStatus(STATUS_CD_JINXINGZHONG);
				entity.setMonthFlg(MONTH_FLG); // 是否是月计划
				String year = DateOperator.formatDate(entity.getTargetDate(), YYYY);
				String month = DateOperator.formatDate(entity.getTargetDate(), MM);
				if ("".equals(year) == false && "".equals(month) == false) {
					entity.setTargetYear(Integer.parseInt(year));
					entity.setTargetMonth(Integer.parseInt(month));
					currentPlanYear = year; // 记录当前年
					currentPlanMonth = month; // 记录当前计划月份
				}
				if (StringUtils.isEmpty(entity.getCenter())) {
					entity.setCenter(getCurrentCenterCd());
				} else {
					currentCenterCd = entity.getCenter(); // 记录当前中心CD
				}
				entity.setSysStatusFlg(SYS_STATUS_0); // 正常状态记录
				entity.setMonthFlg("1");// 月计划
				// 序号
				String str = getPlanNumberNew(entity.getCenter(), entity.getTargetYear(), entity.getTargetMonth());
				String sequenceNumber = str.split("-")[str.split("-").length - 1];
				entity.setSequenceNumber(sequenceNumber);
				currentPlanMonth = entity.getTargetMonth().toString();
				currentPlanYear = entity.getTargetYear().toString();
				//锁
				if(StringUtils.isNotBlank(lockFlg)){
					entity.setRemark(lockFlg); 
				}else{
					entity.setRemark(REMARK_CD_UNLOCK); // 解锁状态
				}
			} else {
				// 驳回(未执行状态(0)不能在驳回)
				if (STATUS_CD_BOHUI.equals(statusFlg) && STATUS_CD_WEIZHIXING.equals(entity.getStatus()) == false) {
					// 进行中状态(1)驳回为未执行状态(0)
					if (STATUS_CD_JINXINGZHONG.equals(entity.getStatus())) {
						entity.setStatus(STATUS_CD_WEIZHIXING);
					} else { // 除未执行和进行中状态的其它状态
						// 过期状态(5)或非考核性过期(6)的时候改为上一个月的记录
						if ((STATUS_CD_SUSPEND.equals(entity.getStatus()) || STATUS_CD_NO_ASSESS.equals(entity.getStatus()))) {

							// 查询上个月相同记录，将其置为删除状态
							if (StringUtils.isNotBlank(entity.getPlanTargetId())) {
								List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
								filters.add(new PropertyFilter("EQS_parentTargetId", entity.getPlanTargetId()));
								filters.add(new PropertyFilter("EQI_targetMonth", entity.getTargetMonth() - 1));
								targetList = planTargetManager.find(filters);
								if ((targetList != null && targetList.size() > 0)) {
									// 将父节点置为空
									entity.setParentTargetId("");

									if (entity.getTargetMonth() == 1) {
										entity.setTargetYear(entity.getTargetYear() - 1);
										entity.setTargetMonth(12);
									} else {
										entity.setTargetMonth(entity.getTargetMonth() - 1);
									}
									entity.setStatus(STATUS_CD_JINXINGZHONG); // 进行中
									sysMessage(entity);
								}
							}
							// end

						} else {
							entity.setStatus(STATUS_CD_JINXINGZHONG); // 进行中
							if (SYS_STATUS_1.equals(entity.getSysStatusFlg())) {
								entity.setSysStatusFlg(SYS_STATUS_0);
							}
							sysMessage(entity);
						}
					}
				} else if (StringUtils.isNotEmpty(statusFlg) && StringUtils.isNotEmpty(entity.getStatus())) { // 修改状态
					// 过期(5)和非考核性过期(6)只能修改进行中(1)状态的记录
					if ((STATUS_CD_SUSPEND.equals(statusFlg) || STATUS_CD_NO_ASSESS.equals(statusFlg)) && STATUS_CD_WANCHENG.equals(entity.getStatus()) == false) { // 过期和非考核性过期不能修改完成、完成但曾经过期状态的记录
						execOverDue(entity);
					} else if (STATUS_CD_WANCHENG.equals(statusFlg) && STATUS_CD_SUSPEND.equals(entity.getStatus())) {
						// 若原为过期状态，点击为完成，则显示为完成但曾过期
						entity.setStatus(STATUS_CD_WANCHENGDANCJGQ);
						sysMessage(entity);
					} else {
						entity.setStatus(statusFlg);
						sysMessage(entity);
					}
				} else { // 其它内容修改
					sysMessage(entity);
				}
				// 逻辑删除数据
				if (STATUS_CD_SHANCHU.equals(entity.getStatus())) {
					entity.setSysStatusFlg(SYS_STATUS_1);
				} else if (SYS_STATUS_1.equals(entity.getSysStatusFlg())) {
					// 如果原来是系统删除掉的数据，则还原
					entity.setSysStatusFlg(SYS_STATUS_0);
				}
			}

			planTargetManager.savePlanTarget(entity);
		}
		// 对删除的List置为删除状态
		for (PlanTarget target : targetList) {
			target.setSysStatusFlg(SYS_STATUS_1);
			target.setStatus(STATUS_CD_SHANCHU);
			planTargetManager.savePlanTarget(target);
		}
		// 新增重新加载页面
		if (getId() == null)
			return RELOAD;
		else {
			// 将修改后的信息返回页面
			Map<String, Map<String, String>> batchMap = null; // 批量Map
			Map<String, String> map = new HashMap<String, String>();
			// 如果是批量操作
			if (isBatch) {
				batchMap = new HashMap<String, Map<String, String>>();
				if (StringUtils.isNotEmpty(statusFlg)) { // 修改了状态
					for (int i = 0; i < getIds().length; i++) {
						PlanTarget pt = planTargetManager.getPlanTargetById(getIds()[i]);
						map.put("status_" + getIds()[i], pt.getStatus());
					}
					batchMap.put("batchStatus", map); // 记录修改后的状态(status)
				}

				for (int i = 0; i < getIds().length; i++) {
					PlanTarget pt = planTargetManager.getPlanTargetById(getIds()[i]);
					map.put("msgContent_" + getIds()[i], msgListMap(pt));
				}
				batchMap.put("batchMsgContent", map); // 记录留言列表
			} else {
				if (StringUtils.isNotEmpty(statusFlg)) {// 修改了状态
					map.put("status", entity.getStatus()); // 记录修改后的状态(status)
				} else { // 修改其它内容
					planContentMap(map, entity);
				}

				map.put("msgContent", msgListMap(entity)); // 记录留言列表
			}

			if (isBatch) {
				Struts2Utils.renderJson(batchMap);
			} else {
				Struts2Utils.renderJson(map);
			}
			return null;
		}
	}

	public void prepareSaveyear() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planTargetManager.getEntity(getId());
		} else {
			entity = new PlanTarget();
		}
	}

	public String saveyear() throws Exception {
		if (StringUtils.isBlank(entity.getPlanTargetId())) {
			// 新增保存
			entity.setYearFlg("1");
			entity.setStatus("1");
			entity.setSysStatusFlg(SYS_STATUS_0);
			planTargetManager.savePlanTarget(entity);
			if (StringUtils.isNotBlank(targetId)) {
				String[] months = targetId.split(",");
				for (int i = 0; i < months.length; i++) {
					saveYearTarget(entity, Integer.valueOf(months[i]));
				}
			}
			currentCenterCd = entity.getCenter();
		} else {
			// 修改保存
			entity.setYearFlg("1");
			planTargetManager.savePlanTarget(entity);
			Map<String, String> monthMap = new HashMap<String, String>();
			monthList = new ArrayList<Map<String, String>>();
			planTargetManager.findYarForMonth(page, entity, monthList);
			if (monthList != null && monthList.size() > 0) {
				for (int j = 0; j < monthList.size(); j++) {
					monthMap = monthList.get(j);
					if (getId().equals(monthMap.get("planTargetId"))) {
						break;
					}
				}
			}
			PropertyFilter filter = null;
			String[] months = targetId.split(",");
			for (int i = 1; i < 13; i++) {
				String value1 = monthMap.get("month" + i);// 原来的值
				String value2 = "0";// 后来的值,默认为没有
				for (int j = 0; j < months.length; j++) {
					if (months[j].equals(i + "")) {
						value2 = "1";
						break;
					}
				}
				if (!value1.equals(value2)) {
					// 查询原来记录
					List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
					filter = new PropertyFilter("EQS_yearId", entity.getPlanTargetId());
					filters.add(filter);
					filter = new PropertyFilter("EQS_center", entity.getCenter());
					filters.add(filter);
					filter = new PropertyFilter("EQI_targetYear", entity.getTargetYear());
					filters.add(filter);
					filter = new PropertyFilter("EQI_targetMonth", i);
					filters.add(filter);
					filter = new PropertyFilter("EQS_monthFlg", "1");
					filters.add(filter);
					List<PlanTarget> targetList = planTargetManager.find(filters);
					// 若原来值与后来值不等，则有变化
					if ("1".equals(value1)) {
						// 删除原来记录
						for (PlanTarget target : targetList) {
							target.setContent(entity.getContent());
							target.setYearTarget(entity.getYearTarget());
							target.setStatus(STATUS_CD_SHANCHU);
							target.setSysStatusFlg(SYS_STATUS_1);
							planTargetManager.savePlanTarget(target);
						}
					} else {
						// 原来为0，后来为1，新增
						if (targetList == null || targetList.size() <= 0) {
							saveYearTarget(entity, i);
						} else {
							// 恢复原来的值
							for (PlanTarget target : targetList) {
								target.setContent(entity.getContent());
								target.setYearTarget(entity.getYearTarget());
								target.setStatus(STATUS_CD_JINXINGZHONG);
								target.setSysStatusFlg(SYS_STATUS_0);
								planTargetManager.savePlanTarget(target);
							}
						}
					}
				} else {
					if ("1".equals(value1)) {
						// 重新保存一遍
						List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
						filter = new PropertyFilter("EQS_yearId", entity.getPlanTargetId());
						filters.add(filter);
						filter = new PropertyFilter("EQS_center", entity.getCenter());
						filters.add(filter);
						filter = new PropertyFilter("EQI_targetYear", entity.getTargetYear());
						filters.add(filter);
						filter = new PropertyFilter("EQI_targetMonth", i);
						filters.add(filter);
						filter = new PropertyFilter("EQS_monthFlg", "1");
						filters.add(filter);
						List<PlanTarget> targetList = planTargetManager.find(filters);
						for (PlanTarget target : targetList) {
							target.setContent(entity.getContent());
							target.setYearTarget(entity.getYearTarget());
							target.setYearTarget(entity.getYearTarget());
							planTargetManager.savePlanTarget(target);
						}
					}
				}
			}
			currentCenterCd = entity.getCenter();
		}

		return "show";
	}

	public void delyear() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planTargetManager.getEntity(getId());
			entity.setSysStatusFlg(SYS_STATUS_1);
			planTargetManager.savePlanTarget(entity);
			String hql = "from PlanTarget t where t.yearId=:yearId";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("yearId", entity.getPlanTargetId());
			List<PlanTarget> targetList = planTargetManager.find(hql, params);
			for (PlanTarget target : targetList) {
				target.setStatus(STATUS_CD_SHANCHU);
				target.setSysStatusFlg(SYS_STATUS_1);
				planTargetManager.savePlanTarget(target);
			}
			Struts2Utils.renderText("ok");
		}
	}

	private void saveYearTarget(PlanTarget planTarget, Integer month) {
		PlanTarget target = new PlanTarget();
		target.setYearId(entity.getPlanTargetId());// 年ID
		target.setMonthFlg("1"); // 月计划
		target.setPlanType("1");// 年计划
		target.setCenter(entity.getCenter());// 中心CD
		target.setTargetYear(entity.getTargetYear());// 年
		target.setTargetMonth(month);// 月
		target.setTargetDate(getLastDate(entity.getTargetYear(), month));// 取该月的最后一天
		target.setSysStatusFlg("0"); // 系统状态
		target.setStatus(STATUS_CD_JINXINGZHONG); // 进行中
		target.setContent(entity.getContent()); // 内容
		String str = getPlanNumberNew(planTarget.getCenter(), planTarget.getTargetYear(), month);
		String sequenceNumber = str.split("-")[str.split("-").length - 1];
		target.setSequenceNumber(sequenceNumber); // 序号
		target.setPlanNumber(str); // 编号
		planTargetManager.savePlanTarget(target);
	}

	public void prepareSavebis() throws Exception {
		entity = new PlanTarget();
	}

	public void savebis() throws Exception {
		// 新增计划
		entity.setStatus(STATUS_CD_JINXINGZHONG);
		entity.setMonthFlg(MONTH_FLG); // 是否是月计划
		entity.setRemark(REMARK_CD_UNLOCK); // 解锁状态
		String year = DateOperator.formatDate(entity.getTargetDate(), YYYY);
		String month = DateOperator.formatDate(entity.getTargetDate(), MM);
		if ("".equals(year) == false && "".equals(month) == false) {
			entity.setTargetYear(Integer.parseInt(year));
			entity.setTargetMonth(Integer.parseInt(month));
			currentPlanYear = year; // 记录当前年
			currentPlanMonth = month; // 记录当前计划月份
		}
		if (StringUtils.isEmpty(entity.getCenter())) {
			entity.setCenter(getCurrentCenterCd());
		} else {
			currentCenterCd = entity.getCenter(); // 记录当前中心CD
		}
		// 序号
		String str = getPlanNumberNew(entity.getCenter(), entity.getTargetYear(), entity.getTargetMonth());
		String sequenceNumber = str.split("-")[str.split("-").length - 1];
		entity.setSequenceNumber(sequenceNumber);
		entity.setPlanNumber(str); // 编号
		entity.setSysStatusFlg(SYS_STATUS_0); // 正常状态记录
		planTargetManager.savePlanTarget(entity);
		Struts2Utils.renderText("ok");

	}

	/***
	 * 执行过期或非考核性过期处理
	 * 
	 * @param pt
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void execOverDue(PlanTarget pt) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		pt.setStatus(statusFlg);
		sysMessage(pt);

		// 过期或非考核性过期，复制entity，重新创建一条新纪录，
		PlanTarget plan = new PlanTarget();
		PropertyUtils.copyProperties(plan, pt);
		plan.setPlanTargetId(null);
		plan.setRecordVersion(0);
		plan.setParentTargetId(pt.getPlanTargetId());
		plan.setPlanMessages(new ArrayList<PlanMessage>());
		/*
		 * for(PlanMessage msg : plan.getPlanMessages()) {
		 * msg.setPlanMessageId(null); msg.setRecordVersion(0); }
		 */
		planTargetManager.savePlanTarget(plan);

		// 过期状态(5)或非考核性过期(6)的时候延期到下一个月
		if (pt.getTargetMonth() == 12) {
			pt.setTargetYear(pt.getTargetYear() + 1);
			pt.setTargetMonth(1);
		} else {
			pt.setTargetMonth(pt.getTargetMonth() + 1);
		}
	}

	public void prepareMessage() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planTargetManager.getEntity(getId());
		} else {
			entity = new PlanTarget();
		}
	}

	public void message() {
		if (StringUtils.isNotBlank(message)) {
			PlanMessage planMessage = new PlanMessage();
			planMessage.setContent(message);
			planMessage.setSysFlg("1");
			planMessage.setPlanTarget(entity);
			planMessageManager.savePlanMessage(planMessage);
			Map<String, String> map = new HashMap<String, String>();
			map.put("msgContent", msgListMap(entity)); // 记录留言列表
			Struts2Utils.renderJson(map);
		}
	}

	/***
	 * 发表内容后将留言内容存入 map 传给页面
	 * 
	 * @param plan
	 * @return 留言内容
	 */
	private String msgListMap(PlanTarget plan) {
		String html = "";
		for (PlanMessage msg : plan.getPlanMessages()) {
			if (msg != null && StringUtils.isNotBlank(msg.getContent())) {
				html += "<div style='width: 90%;'><span style=\"color:#18478d;\">" + CodeNameUtil.getUserNameByCd(msg.getCreator()) + "</span>" + "("
						+ DateOperator.formatDate(msg.getCreatedDate(), M_D_H_M) + "):&nbsp;" + msg.getContent() + "</div>";

			}
		}
		return html;
	}

	/***
	 * 将修改后的值存入map传给页面
	 * 
	 * @param map
	 *            值map
	 * @param plan
	 *            PlanTarget
	 */
	private void planContentMap(Map<String, String> map, PlanTarget plan) {
		map.put("sequenceNumber", plan.getSequenceNumber());
		map.put("planNumber", plan.getPlanNumber());
		map.put("planType", plan.getPlanType());
		map.put("content", plan.getContent());
		map.put("targetDate", DateOperator.formatDate(plan.getTargetDate(), MM_DD));
		map.put("updatedDate", DateOperator.formatDate(plan.getUpdatedDate(), MM_DD));
	}

	/***
	 * 系统留言
	 * 
	 * @param plan
	 *            PlanTarget
	 */
	public void sysMessage(PlanTarget plan) {
		PlanMessage msg = null;
		// 修改了状态，添加系统留言
		if (StringUtils.isNotEmpty(statusFlg)) {
			String msgStatus = "";
			if (plan.getStatus().equals(STATUS_CD_WEIZHIXING)) {
				msgStatus = "未执行";
			} else if (plan.getStatus().equals(STATUS_CD_JINXINGZHONG)) {
				msgStatus = "进行中";
			} else if (plan.getStatus().equals(STATUS_CD_YUWANCHENG)) {
				msgStatus = "预完成";
			} else if (plan.getStatus().equals(STATUS_CD_WANCHENG)) {
				msgStatus = "完成";
			} else if (plan.getStatus().equals(STATUS_CD_WANCHENGDANCJGQ)) {
				msgStatus = "完成但曾经过期";
			} else if (plan.getStatus().equals(STATUS_CD_SUSPEND)) {
				msgStatus = "过期   到" + (plan.getTargetMonth() + 1) + "月";
			} else if (plan.getStatus().equals(STATUS_CD_NO_ASSESS)) {
				msgStatus = "非考核性过期   到" + (plan.getTargetMonth() + 1) + "月";
			} else if (plan.getStatus().equals(STATUS_CD_NO_THIS)) {
				msgStatus = "非本月任务";
			} else if (plan.getStatus().equals(STATUS_CD_YINCANG)) {
				msgStatus = "隐藏";
			} else if (plan.getStatus().equals(STATUS_CD_SHENQINGSHANCHU)) {
				msgStatus = "申请删除";
			} else if (plan.getStatus().equals(STATUS_CD_SHANCHU)) {
				msgStatus = "删除";
			}
			if (msgStatus.equals("") == false) {
				msg = new PlanMessage();
				msg.setContent("【修改状态为" + msgStatus + "】");
				List<PlanMessage> mesList =new ArrayList<PlanMessage>();
				mesList.add(msg);
				for(PlanMessage mes:plan.getPlanMessages()){
					mesList.add(mes);
				}
				plan.setPlanMessages(mesList);
			}
		}
		// 修改了其它内容，添加系统留言
		if (StringUtils.isNotBlank(edited)) {
			String[] editedCons = edited.split(",");
			if (editedCons.length > 0) {
				String editMsg = "【";
				msg = new PlanMessage();
				for (int i = 0; i < editedCons.length; i++) {
					if ("sequenceNumber".equals(editedCons[i])) {
						editMsg += "修改序号为" + plan.getSequenceNumber() + "、 ";
					}
					if ("planType".equals(editedCons[i])) {
						String planTypeName = "";
						if (TYPE_CD_NIANJIHUA.equals(plan.getPlanType())) {
							planTypeName = "年计划";
						} else if (TYPE_CD_YUEJIHUA.equals(plan.getPlanType())) {
							planTypeName = "月会工作";
						} else if (TYPE_CD_XIANGMUGUANLI.equals(plan.getPlanType())) {
							planTypeName = "项目管理";
						} else if (TYPE_CD_ZHILINGDAN.equals(plan.getPlanType())) {
							planTypeName = "指令单";
						} else if (TYPE_CD_ZHONGHE.equals(plan.getPlanType())) {
							planTypeName = "综合";
						} else if (TYPE_CD_JIDUKPI.equals(plan.getPlanType())) {
							planTypeName = "季度KPI";
						} else if (TYPE_CD_SIJIJIHUA.equals(plan.getPlanType())) {
							planTypeName = "四级计划";
						} else if (TYPE_CD_ZHONGXINZHONGZUO.equals(plan.getPlanType())) {
							planTypeName = "中心工作";
						}
						editMsg += "修改类型为" + planTypeName + "、 ";
					}
					if ("content".equals(editedCons[i])) {
						if (StringUtils.isNotBlank(befContent)) {
							editMsg += "<font color='#18478d'>" + befContent + "</font>";
						}
						editMsg += "内容修改为<font color='#18478d'>" + plan.getContent() + "</font>、 ";
					}
					if ("targetDate".equals(editedCons[i])) {
						editMsg += "修改目标时间为" + DateOperator.formatDate(plan.getTargetDate(), DateOperator.FORMAT_STR) + "、 ";
					}
				}
				editMsg = editMsg.substring(0, editMsg.lastIndexOf("、"));
				editMsg += "】";
				msg.setContent(editMsg);
			}
			//plan.getPlanMessages().add(msg);
			List<PlanMessage> mesList =new ArrayList<PlanMessage>();
			mesList.add(msg);
			for(PlanMessage mes:plan.getPlanMessages()){
				mesList.add(mes);
			}
			plan.setPlanMessages(mesList);
		}
	}

	/**
	 * 取某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private Date getLastDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		int curMonth = month;
		int curYear = year;
		if (month != 1) {
			curMonth = curMonth - 1;
		} else {
			curMonth = 12;
			curYear = curYear - 1;
		}
		cal.set(Calendar.YEAR, curYear);
		cal.set(Calendar.MONTH, curMonth);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}

	public void prepareLockOperation() throws Exception {
		entity = new PlanTarget();
	}

	/***
	 * 锁定、解锁操作
	 * 
	 * @return
	 * @throws Exception
	 */
	public String lockOperation() throws Exception {
		entity.setCenter(getCurrentCenterCd());
		entity.setTargetMonth(Integer.parseInt(getCurrentPlanMonth()));
		page.setPageSize(999);
		page = planTargetManager.findPage(page, entity);
		List<PlanTarget> result = page.getResult();
		if (result != null && result.size() > 0) {
			for (PlanTarget plan : result) {
				if (REMARK_CD_LOCK.equals(lockFlg)) { // 锁定
					plan.setRemark(REMARK_CD_LOCK);
				} else { // 解锁
					plan.setRemark(REMARK_CD_UNLOCK);
				}
				planTargetManager.savePlanTarget(plan);
			}
		}
		return "month";
	}

	@Override
	public String delete() throws Exception {
		planTargetManager.deletePlanTarget(getId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = planTargetManager.getEntity(getId());
		} else {
			entity = new PlanTarget();
		}
	}

	/***
	 * 新建时编号
	 * 
	 * @param centerCd
	 * @return
	 */
	public String getPlanNumberNew(String center, int year, int month) {
		Date newDate = new Date();
		String centerCd = SpringSecurityUtils.getCurrentCenterCd();
		if (StringUtils.isNotEmpty(entity.getCenter())) {
			centerCd = entity.getCenter();
		}
		// String year = getCurrentPlanYear();
		// String month = getCurrentPlanMonth();
		newDate = DateOperator.createDate(year, month - 1, 1);
		String planNo = CodeNameUtil.getDeptShortNameByCd(centerCd);
		Long count = planTargetManager.getPlanTargetCount(centerCd, year, month) + 1;
		if (month < 10) {
			planNo += "-0" + month;
		} else {
			planNo += "-" + month;
		}
		planNo += "-" + (count < 10 ? "0" + count : count);
		return planNo;
	}

	/***
	 * 逻辑删除附件
	 * 
	 * @throws Exception
	 */
	public void deleteFile() throws Exception {
		try {
			AppAttachFile appAttachFile = appAttachFileManager.getEntity(attachId);
			appAttachFileManager.deleteAppAttachFile(appAttachFile, PlanTarget.class.getSimpleName(), bizEntityId, false);
			// 主要为关注模块，节点发生变化时，往关注库做标志 start (2011-11-22)
			detailManager.setAttentionChange("execPlan", appAttachFile.getBizEntityId());
			// end (2011-11-22)
			addActionMessage(getText("common.success"));
			Struts2Utils.renderText("success");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
			Struts2Utils.renderText("fail");
		}
	}

	/**
	 * 是否有附件
	 * 
	 * @return "not"-没有附件 , "is"-有附件
	 * @throws Exception
	 */
	public void checkStatus() throws Exception {
		PlanTarget planTarget = planTargetManager.getPlanTargetById(targetId);
		if (planTarget != null && "1".equals(planTarget.getAttachFlg())) {
			Struts2Utils.renderText("is");
		} else {
			Struts2Utils.renderText("not");
		}
	}

	/***
	 * 获取附件列表
	 * 
	 * @throws Exception
	 */
	public void uploadFile() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>(); // HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		filePage.setPageSize(100);
		if (!filePage.isOrderBySetted()) {
			filePage.setOrderBy("createdDate");
			filePage.setOrder(Page.DESC);
		}
		if (StringUtils.isNotEmpty(bizEntityId)) {
			filters.add(new PropertyFilter("EQS_bizEntityId", bizEntityId));
		}
		if (StringUtils.isNotBlank(owner)) {
			filters.add(new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid()));
		}
		filters.add(new PropertyFilter("NEQS_statusCd", "0"));// 状态为正常
		if (StringUtils.isNotEmpty(bizModuleCd)) {
			filters.add(new PropertyFilter("EQS_bizModuleCd", bizModuleCd));
		}
		if (StringUtils.isNotEmpty(bizFieldName) && !"null".equalsIgnoreCase(bizFieldName) && !"undefined".equalsIgnoreCase(bizFieldName)) {
			filters.add(new PropertyFilter("EQS_bizFieldName", bizFieldName));
		}
		filePage = appAttachFileManager.findPage(filePage, filters);
		List<AppAttachFile> list = filePage.getResult();
		Map<String, Map<String, String>> fileMap = new HashMap<String, Map<String, String>>();
		for (AppAttachFile attFile : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("creator", CodeNameUtil.getUserNameByCd(attFile.getCreator()) + " " + DateOperator.formatDate(attFile.getUpdatedDate(), "MM-dd"));
			map.put("fileName", attFile.getFileName());
			map.put("realFileName", attFile.getRealFileName());
			map.put("bizMouduleCd", attFile.getBizModuleCd());
			map.put("fileType", attFile.getFileTypeName());
			map.put("id", attFile.getAppAttachFileId());

			map.put("bizEntityId", attFile.getBizEntityId());
			map.put("bizEntityName", PlanTarget.class.getSimpleName());

			fileMap.put(attFile.getAppAttachFileId(), map);
		}
		Struts2Utils.renderJson(fileMap);
	}

	public String exportExcel() throws Exception {
		excelFile = buildExportedExcelInputStream();
		String fileName = "各中心月份权重评分";
		excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "Exported";

	}

	private InputStream buildExportedExcelInputStream() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);

		if (wbook != null) {

			try {
				populateData(wbook);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JXLExcelUtil.closeWorkBook(wbook);
		}

		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(data);

		return bis;
	}

	private void populateData(WritableWorkbook wbook) throws Exception {
		Map<String, String> centerMap = new HashMap();
		if ("estate".equals(deptFlg)) {
			centerMap = getMapEstateCenterName();
		}
	
		if (null != centerMap) {
			String centerName;
			String centerCd;
			int i = 0;
			for (String key : centerMap.keySet()) {
				centerCd = key;
				centerName = centerMap.get(key);
				if (!"".equals(key)) {
					WritableSheet wsheet = wbook.createSheet(centerName, i);
					centerName =CodeNameUtil.getCenterOrgNameByOrgCd(key);
					doExcel(wsheet, centerName, centerCd);
					i++;
				}
			}
			//其它模块的录入
			centerMap=getMapOtherCenterName();
			for (String key : centerMap.keySet()) {
				centerCd = key;
				centerName = centerMap.get(key);
				if (!"".equals(key)) {
					WritableSheet wsheet = wbook.createSheet(centerName, i);
					centerName =CodeNameUtil.getCenterOrgNameByOrgCd(key);
					doExcel(wsheet, centerName, centerCd);
					i++;
				}
			}
		}
	}

	private void doExcel(WritableSheet wsheet, String centerName, String centerCd) throws WriteException {
		HashMap font = fontMap();
		WritableCellFormat wcfN_notCol_Bold = (WritableCellFormat) font.get("wcfN_notCol_Bold");
		WritableCellFormat wcfN_Bold = (WritableCellFormat) font.get("wcfN_Bold");
		WritableCellFormat wcfN_noBold = (WritableCellFormat) font.get("wcfN_noBold");
		WritableCellFormat wcfN_N_noBold = (WritableCellFormat) font.get("wcfN_N_noBold");
		WritableCellFormat wcfN_noBold_center = (WritableCellFormat) font.get("wcfN_noBold_center");

		wsheet.addCell(new Label(0, 0, centerName, wcfN_notCol_Bold));
		wsheet.mergeCells(0, 0, 6, 0);
		wsheet.setRowView(0, 500);
		wsheet.addCell(new Label(0, 1, "编号", wcfN_Bold));
		wsheet.addCell(new Label(1, 1, "类型", wcfN_Bold));
		wsheet.addCell(new Label(2, 1, "工作内容", wcfN_Bold));
		wsheet.addCell(new Label(3, 1, "月份", wcfN_Bold));
		wsheet.addCell(new Label(4, 1, "权重", wcfN_Bold));
		wsheet.addCell(new Label(5, 1, "执行总裁评价", wcfN_Bold));
		wsheet.addCell(new Label(6, 1, "权重得分", wcfN_Bold));
		wsheet.setRowView(1, 400);
		Map<String, Object> params = new HashMap<String, Object>();
		// 取数据
		String hql = "select a from PlanTarget a where a.center=:center and a.targetYear=:targetYear and a.targetMonth=:targetMonth"
				+ " and a.status in(0,1,2,3,4,5,6,7,9) and a.sysStatusFlg=0 order by a.planType asc a.sequenceNumber asc";
		if(StringUtils.isBlank(currentPlanYear)){
			currentPlanYear = DateOperator.formatDate(new Date(), YYYY);
		}
		if(StringUtils.isBlank(currentPlanMonth)){
			currentPlanMonth = DateOperator.formatDate(new Date(), MM);
		}
		params.put("targetYear", Integer.parseInt(currentPlanYear));
		params.put("targetMonth",  Integer.parseInt(currentPlanMonth));
		params.put("center", centerCd);
		List<PlanTarget> targetList = planTargetManager.find(hql, params);
		Map<String, String> planType =getMapPlanType();
		if (targetList != null && targetList.size() > 0) {
			for (int i = 0; i < targetList.size(); i++) {
				PlanTarget target = targetList.get(i);
				wsheet.addCell(new Number(0, i + 2, Integer.parseInt(target.getSequenceNumber()), wcfN_N_noBold));
				wsheet.addCell(new Label(1, i + 2,planType.get(target.getPlanType()), wcfN_noBold_center));
				if ("1".equals(target.getPlanType())) {
					wsheet.addCell(new Label(1, i + 2, "年计划", wcfN_noBold_center));
				} 
				wsheet.addCell(new Label(2, i + 2, target.getContent(), wcfN_noBold));
				wsheet.addCell(new Label(3, i + 2, target.getTargetMonth() + "月", wcfN_noBold_center));
				wsheet.addCell(new Label(4, i + 2, "", wcfN_noBold));
				wsheet.addCell(new Label(5, i + 2, "", wcfN_noBold));
				wsheet.addCell(new Label(6, i + 2, "", wcfN_noBold));
				// 行宽
				wsheet.setRowView(i + 2, 400);

			}
		}
		// 列宽
		wsheet.setColumnView(0, 5);
		wsheet.setColumnView(1, 10);
		wsheet.setColumnView(2, 82);
		wsheet.setColumnView(3, 5);
		wsheet.setColumnView(4, 5);
		wsheet.setColumnView(5, 13);
		wsheet.setColumnView(6, 9);
	}

	private HashMap fontMap() throws WriteException {
		HashMap map = new HashMap();
		int charNormal = 10;// 标题字体大小
		// 字体：黑体，背景颜色：灰色
		WritableFont nf_Bold = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		jxl.write.WritableCellFormat wcfN_Bold;
		nf_Bold.setBoldStyle(WritableFont.BOLD);
		WritableFont nf_NBold = new WritableFont(WritableFont.createFont("SimSun"), 9);
		jxl.write.WritableCellFormat wcfN_noBold_left;
		nf_NBold.setBoldStyle(WritableFont.NO_BOLD);

		wcfN_Bold = new jxl.write.WritableCellFormat(nf_Bold);
		// wcfN_Bold.setBackground(Colour.GRAY_25);
		wcfN_Bold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_Bold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_Bold.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_Bold.setWrap(true); // 是否换行

		map.put("wcfN_Bold", wcfN_Bold);
		// 字体黑色，无背景颜色
		jxl.write.WritableCellFormat wcfN_notCol_Bold;
		nf_Bold = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		nf_Bold.setBoldStyle(WritableFont.BOLD);
		wcfN_notCol_Bold = new jxl.write.WritableCellFormat(nf_Bold);
		// wcfN_notCol_Bold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_notCol_Bold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_notCol_Bold.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_notCol_Bold.setWrap(true); // 是否换行
		map.put("wcfN_notCol_Bold", wcfN_notCol_Bold);
		// 字体：普通
		jxl.write.WritableCellFormat wcfN_noBold;
		wcfN_noBold = new jxl.write.WritableCellFormat(nf_NBold);
		wcfN_noBold.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_noBold.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_noBold.setAlignment(Alignment.LEFT);// 水平对齐
		wcfN_noBold.setWrap(true); // 是否换行
		map.put("wcfN_noBold", wcfN_noBold);
		// 字体：粗体,黑色,左对齐
		nf_Bold = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		wcfN_noBold_left = new jxl.write.WritableCellFormat(nf_Bold);
		wcfN_noBold_left.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_noBold_left.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_noBold_left.setAlignment(Alignment.LEFT);// 水平对齐
		wcfN_noBold_left.setWrap(true); // 是否换行
		map.put("wcfN_noBold_left", wcfN_noBold_left);
		// 字体：不粗,黑色,左对齐
		jxl.write.WritableCellFormat wcfN_noBold_center;
		wcfN_noBold_center = new jxl.write.WritableCellFormat(nf_NBold);
		wcfN_noBold_center.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		wcfN_noBold_center.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		wcfN_noBold_center.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfN_noBold_center.setWrap(true); // 是否换行
		map.put("wcfN_noBold_center", wcfN_noBold_center);
		// 字体：数字,无背景颜色
		NumberFormat num = new NumberFormat("###,###,###,###,###,###,##0");
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

		return map;
	}

	// 计划类型MAP
	public Map<String, String> getMapPlanType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.PLAN_CENTER_TYPE);
	}

	// 地产中心名称MAP
	public Map<String, String> getMapEstateCenterName() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.ESTATE_CENTER_NAME);
	}

	// 商业中心名称MAP
	public Map<String, String> getMapBusinessCenterName() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BUSINESS_CENTER_NAME);
	}

	// 其它中心名称MAP
	public Map<String, String> getMapOtherCenterName() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.OTHER_CENTER_NAME);
	}

	// 获取弹出框记录
	public Map<String, String> getMapContentTips() {
		return mapContentTips;
	}

	public PlanTarget getEntity() {
		return entity;
	}

	public void setEntity(PlanTarget entity) {
		this.entity = entity;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	@Override
	public Page<PlanTarget> getPage() {
		return page;
	}

	public String getStatusFlg() {
		return statusFlg;
	}

	public void setStatusFlg(String statusFlg) {
		this.statusFlg = statusFlg;
	}

	public String getMsgPub() {
		return msgPub;
	}

	public void setMsgPub(String msgPub) {
		this.msgPub = msgPub;
	}

	public String getBizEntityId() {
		return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
		this.bizEntityId = bizEntityId;
	}

	public String getBizModuleCd() {
		return bizModuleCd;
	}

	public void setBizModuleCd(String bizModuleCd) {
		this.bizModuleCd = bizModuleCd;
	}

	public String getBizFieldName() {
		return bizFieldName;
	}

	public void setBizFieldName(String bizFieldName) {
		this.bizFieldName = bizFieldName;
	}

	public Page<AppAttachFile> getFilePage() {
		return filePage;
	}

	public void setFilePage(Page<AppAttachFile> filePage) {
		this.filePage = filePage;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getCurrentCenterCd() {
		if (StringUtils.isEmpty(currentCenterCd)) {
			currentCenterCd = SpringSecurityUtils.getCurrentCenterCd();
			// 查询currentCenterCd是否在中心列表里
			Map<String, String> centerMap = getMapEstateCenterName();
			if (centerMap.get(currentCenterCd) == null) {
				// 酒店
				centerMap = getMapBusinessCenterName();
				if (centerMap.get(currentCenterCd) == null) {
					// 其它
					centerMap = getMapOtherCenterName();
					if (centerMap.get(currentCenterCd) == null) {
						// 默认中心
						currentCenterCd = "147";
					}
				}
			}

		}
		return currentCenterCd;
	}

	public void setCurrentCenterCd(String currentCenterCd) {
		this.currentCenterCd = currentCenterCd;
	}

	public String getCurrentPlanMonth() {
		if (StringUtils.isEmpty(currentPlanMonth)) {
			currentPlanMonth = DateOperator.formatDate(new Date(), MM);
		}
		return currentPlanMonth;
	}

	public void setCurrentPlanMonth(String currentPlanMonth) {
		this.currentPlanMonth = currentPlanMonth;
	}

	public String getCurrentPlanYear() {
		if (StringUtils.isEmpty(currentPlanYear)) {
			currentPlanYear = DateOperator.formatDate(new Date(), YYYY);
		}
		return currentPlanYear;
	}

	public void setCurrentPlanYear(String currentPlanYear) {
		this.currentPlanYear = currentPlanYear;
	}

	public void setMapContentTips(Map<String, String> mapContentTips) {
		this.mapContentTips = mapContentTips;
	}

	public String getLockFlg() {
		return lockFlg;
	}

	public void setLockFlg(String lockFlg) {
		this.lockFlg = lockFlg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOpened_entityid() {
		return opened_entityid;
	}

	public void setOpened_entityid(String openedEntityid) {
		opened_entityid = openedEntityid;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Map<String, String> getAttentionMap() {
		return attentionMap;
	}

	public void setAttentionMap(Map<String, String> attentionMap) {
		this.attentionMap = attentionMap;
	}

	public Map<String, String> getAttentionMapUnread() {
		return attentionMapUnread;
	}

	public void setAttentionMapUnread(Map<String, String> attentionMapUnread) {
		this.attentionMapUnread = attentionMapUnread;
	}

	public String getBefContent() {
		return befContent;
	}

	public String getDeptFlg() {
		return deptFlg;
	}

	public void setDeptFlg(String deptFlg) {
		this.deptFlg = deptFlg;
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

	public List<Map<String, String>> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Map<String, String>> monthList) {
		this.monthList = monthList;
	}

	public String getHaveOperAuth() {
		return haveOperAuth;
	}

	public void setHaveOperAuth(String haveOperAuth) {
		this.haveOperAuth = haveOperAuth;
	}

	public int getTotCount() {
		return totCount;
	}

	public int getCompCount() {
		return compCount;
	}

	public String getCompRate() {
		return compRate;
	}

	public String getBisFlg() {
		return bisFlg;
	}

	public void setBisFlg(String bisFlg) {
		this.bisFlg = bisFlg;
	}


}

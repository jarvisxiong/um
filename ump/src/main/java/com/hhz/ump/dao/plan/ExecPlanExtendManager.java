package com.hhz.ump.dao.plan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.ump.entity.plan.ExecPlanDetail;
import com.hhz.ump.entity.plan.ExecPlanLayout;
import com.hhz.ump.entity.plan.ExecPlanNode;
import com.hhz.ump.entity.plan.ExecPlanProcessing;
import com.hhz.ump.web.plan.WorkPlanUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;
@Service
@Transactional
public class ExecPlanExtendManager extends BaseService<ExecPlanProcessing, String> {
	private static final String MAIL_ADMIN = "email_admin";
	@Autowired
	private ExecPlanExtendDao execPlanExtendDao;
	@Autowired
	private ExecPlanNodeManager execPlanNodeManager;
	@Autowired
	private ExecPlanLayoutManager execPlanLayoutManager;
	@Autowired
	private ExecPlanDetailDao execPlanDetailDao;
	@Autowired
	private ExecPlanDetailManager detailManager;
	private List<String> listResOrg;
	private List<ExecPlanDetail> planDetailMap;
	private int reply;

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

	public List<ExecPlanDetail> getPlanDetailMap() {
		return planDetailMap;
	}

	public void setPlanDetailMap(List<ExecPlanDetail> planDetailMap) {
		this.planDetailMap = planDetailMap;
	}

	public List<String> getListResOrg() {
		return listResOrg;
	}

	public void setListResOrg(List<String> listResOrg) {
		this.listResOrg = listResOrg;
	}

	@Override
	public HibernateDao<ExecPlanProcessing, String> getDao() {
		return execPlanExtendDao;
	}

	/**
	 * 自动统计功能
	 */
	public void automaticProcessingTasks() {
		// 搜索出所有的‘地产公司’
		List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
		Date date = DateOperator.getDateNow();
		/*System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
		
		Calendar calendarIn = Calendar.getInstance();
		calendarIn.setTime(new Date());
		calendarIn.add(Calendar.DATE, 1);
		date=calendarIn.getTime();
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
		*/
		if (list!=null){
			for(WsPlasOrg wo:list){
//				if (listResOrg!=null && listResOrg.size()!=0){
//					listResOrg.clear();
//				}
//				if (planDetailMap!=null && planDetailMap.size()!=0){
//					planDetailMap.clear();
//				}
//				reply = 0;
//				String projectCd = wo.getOrgCd();
//				this.processingTasks(projectCd, date);
				String projectCd = wo.getOrgCd();
				handProcessingTasks(projectCd, date);
			}
		}
	}

	/**
	 * 自动统计功能(手动与定时调用共享接口)
	 * @param projectCd
	 * @param date
	 */
	public void processingTasks(String projectCd,Date date){
		// 所有业态
		List<ExecPlanLayout> execPlanLayouts = execPlanLayoutManager.getLayouts(projectCd, "1", true, false);
		String searchPlans = "";
		if (true) {
			// 如果是部门切换，选择所有业态
			searchPlans = "";
			for (ExecPlanLayout epl : execPlanLayouts) {
				searchPlans += epl.getExecPlanLayoutId() + ",";
			}
			if (!"".equalsIgnoreCase(searchPlans)) {
				searchPlans = searchPlans.substring(0, searchPlans.length() - 1);
			}
		}
		// 所选业态
		List<ExecPlanLayout>  searchedPlans = new ArrayList<ExecPlanLayout>();
		try {
			if (null != searchPlans) {
				searchedPlans = new ArrayList<ExecPlanLayout>();
				String[] arr_plans = searchPlans.split(",");
				for (ExecPlanLayout epl : execPlanLayouts) {
					for (String compare_plan : arr_plans) {
						if (compare_plan.equalsIgnoreCase(epl.getExecPlanLayoutId())) {
							searchedPlans.add(epl);
							break;
						}
					}
				}
			} else {
				searchedPlans = new ArrayList<ExecPlanLayout>();
			}
		} catch (Exception e) {
			searchedPlans = execPlanLayouts;
		}
		List<ExecPlanNode> viewPlanNodes = execPlanNodeManager.searchNodes("1",  "3", "");
		// 节点与业态映射关系
		String[] nodeIds;
		nodeIds = new String[viewPlanNodes.size()];
		ExecPlanNode n = null;
		for (int i = 0; i < viewPlanNodes.size(); i++) {
			n = viewPlanNodes.get(i);
			nodeIds[i] = n.getExecPlanNodeId();
		}
		planDetailMap =  buildPlanDetailMap(nodeIds, "", searchPlans, "", "",
				"", "", "", "", "999", "1", "", null);
		//搜索当前地产公司所有的主责方
		SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(dateFormatter2.format(date));
		List<WsPlasOrg> list = new ArrayList<WsPlasOrg>();
		List<WsPlasOrg> listss = WorkPlanUtil.getOrgEstateOrgList();
		List<WsPlasOrg> newList = new ArrayList<WsPlasOrg>();
		for(WsPlasOrg wo : listss){
			if(wo.getOrgCd().equals(projectCd)){
				newList.add(wo);
			}
		}
		list = newList;
		this.executeDetail(list, listResOrg,date);
	}
//	/**
//	 * 具体执行细节
//	 * 
//	 * @param list
//	 * @param newResOrgList
//	 * @param viewPlanNodes
//	 */
//	public void executeDetail(List<WsPlasOrg> list, List<String> newResOrgList,Date newDate) {
//		if (list != null && !list.isEmpty()) {
//			for (WsPlasOrg m : list) {
//				// 从对应的节点中搜索出主责中心对应的节点
//				if (newResOrgList != null && !newResOrgList.isEmpty()) {
//					for (String orgName : newResOrgList) {
//						reply = 0;
//						int noConfirm = 0;
//						int going = 0;
//						int suspend = 0;
//						int complete = 0;
//						for(ExecPlanDetail  epd :planDetailMap){
//							String resOrgName = epd.getExecPlanNode().getResOrgName().trim().toString();
//							if (resOrgName.equals(orgName)){
//								String status = getPrintStatus(epd,newDate);
//								// 未确认数统计
//								if (status.equals("未确认")){
//									noConfirm++;
//								// 进行中数统计
//								}else if(status.equals("进行中")){
//									going++;
//								//过期数统计	
//								}else if(status.equals("过期")){
//									suspend++;
//								//完成数统计	
//								}else if(status.equals("完成")){
//									complete++;	
//								}
//							}
//							
//						}
//						
//						// 根据主责方进行入库
//						ExecPlanProcessing execPlanProcessing = new ExecPlanProcessing();
//						execPlanProcessing.setProjectCd(m.getOrgCd());
//						execPlanProcessing.setProjectName(m.getOrgName());
//						execPlanProcessing.setResOrgName(orgName);
//						execPlanProcessing.setNoConfirmStatus(String
//								.valueOf(noConfirm));
//						execPlanProcessing.setGoingStatus(String
//								.valueOf(going));
//						execPlanProcessing.setSuspendStatus(String
//								.valueOf(suspend));
//						execPlanProcessing.setCompleteStatus(String
//								.valueOf(complete));
//						execPlanProcessing.setNoReplyStatus(String
//								.valueOf(reply));
//						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//						String createDate = getDateYesterday(newDate) == null ? "" : df.format(getDateYesterday(newDate));
//						String format = "yyyy-MM-dd";//getDateYesterday()
//						execPlanProcessing.setCreatedDate(DateOperator.parse(createDate, format));
//						execPlanProcessing.setRemark("");
//						execPlanLayoutManager.saveExecPlanProcessing(execPlanProcessing);
//					}
//				}
//			}
//		}
//	}
	
	/**
	 * 功能: 构造计划详情
	 * 
	 * @param projNodes
	 * @param pTreeTypeCd
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "cast" })
	private List<ExecPlanDetail> buildPlanDetailMap(String[] nodeIds, String pTreeTypeCd, String searchLayouts,
			String filter_GED_scheduleStartDate, String filter_LED_scheduleStartDate, String filter_GED_scheduleEndDate, String filter_LED_scheduleEndDate,
			String filter_GED_realEndDate, String filter_LED_realEndDate, String search_status, String nowViewStyle, String nowResOrgNames, String planTypeCd) {
		Map<String, ExecPlanDetail> m = new HashMap<String, ExecPlanDetail>();

		List<ExecPlanDetail> details = detailManager.getDetailsByProjPlanNodes(nodeIds, pTreeTypeCd, searchLayouts, filter_GED_scheduleStartDate,
				filter_LED_scheduleStartDate, filter_GED_scheduleEndDate, filter_LED_scheduleEndDate, filter_GED_realEndDate, filter_LED_realEndDate,
				search_status, nowViewStyle, nowResOrgNames,planTypeCd);
		String key = null;
		for (ExecPlanDetail d : details) {
			key = d.getExecPlanNode().getResOrgName().toString();

			//如果是相同key，就不进入结果的map
			boolean has_same = false;
			try {
				Set<Map.Entry<String, ExecPlanDetail>> set = m.entrySet();
				for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
						String compareStr = entry.getKey();
						if (key.equalsIgnoreCase(compareStr)) {
							has_same = true;
							break;
						}
				}
			}catch(Exception e){}
			if(!has_same){
				m.put(key, d);
			}
		}
		listResOrg = new ArrayList<String>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String keys = it.next().toString();
			listResOrg.add(keys);
		} 
		return details;
	}
	
//	/**
//	 * 自动统计功能
//	 */
//	@SuppressWarnings("unchecked")
//	public void automaticProcessingTasks() {
//		// 列表出三级的所有节点
//		// List<ExecPlanNode> viewPlanNodes =
//		// execPlanNodeManager.searchNodesLvel("1", "3");
//		// 搜索出所有主责中心列表
//		List<ExecPlanNode> getResOrgList = execPlanNodeManager
//				.getResOrgList("1");
//		// 整理出最终的主责列表
//		List<String> newResOrgList = new ArrayList<String>();
//		if (getResOrgList != null && !getResOrgList.isEmpty()) {
//			for (ExecPlanNode epn : getResOrgList) {
//				if (epn.getResOrgName().contains(",")) {
//					String[] resOrgNameList = epn.getResOrgName().split(",");
//					for (int j = 0; j < resOrgNameList.length; j++) {
//						newResOrgList.add(resOrgNameList[j]);
//					}
//				} else if (epn.getResOrgName().contains("、")) {
//					String[] resOrgNameList = epn.getResOrgName().split("、");
//					for (int j = 0; j < resOrgNameList.length; j++) {
//						newResOrgList.add(resOrgNameList[j]);
//					}
//				} else {
//					newResOrgList.add(epn.getResOrgName());
//				}
//			}
//		}
//		HashSet h = new HashSet(newResOrgList);
//		newResOrgList.clear();
//		newResOrgList.addAll(h);
//		// 搜索出所有的‘地产公司’
//		List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
//		this.executeDetail(list, newResOrgList,DateOperator.getDateNow());
//	}
//	
	/**
	 * 自动统计功能(手动测试用)
	 */
	@SuppressWarnings("unchecked")
	public void handProcessingTasks(String projectCd,Date date) {
		// 列表出三级的所有节点
		// List<ExecPlanNode> viewPlanNodes =
		// execPlanNodeManager.searchNodesLvel("1", "3");
		// 搜索出所有主责中心列表
		List<ExecPlanNode> getResOrgList = execPlanNodeManager
				.getResOrgList("1");
		// 整理出最终的主责列表
		List<String> newResOrgList = new ArrayList<String>();
		if (getResOrgList != null && !getResOrgList.isEmpty()) {
			for (ExecPlanNode epn : getResOrgList) {
//				if (epn.getResOrgName().contains(",")) {
//					String[] resOrgNameList = epn.getResOrgName().split(",");
//					for (int j = 0; j < resOrgNameList.length; j++) {
//						newResOrgList.add(resOrgNameList[j]);
//					}
//				} else if (epn.getResOrgName().contains("、")) {
//					String[] resOrgNameList = epn.getResOrgName().split("、");
//					for (int j = 0; j < resOrgNameList.length; j++) {
//						newResOrgList.add(resOrgNameList[j]);
//					}
//				} else {
//					newResOrgList.add(epn.getResOrgName());
//				}
				newResOrgList.add(epn.getResOrgName().trim());
			}
		}
		HashSet h = new HashSet(newResOrgList);
		newResOrgList.clear();
		newResOrgList.addAll(h);
		// 搜索出所有的‘地产公司’
		List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
		List<WsPlasOrg> newList = new ArrayList<WsPlasOrg>();
		for(WsPlasOrg wo : list){
			if(wo.getOrgCd().equals(projectCd)){
				newList.add(wo);
			}
		}
		this.executeDetail(newList, newResOrgList,date);
	}
	
	/**
	 * 具体执行细节
	 * 
	 * @param list
	 * @param newResOrgList
	 * @param viewPlanNodes
	 */
	@SuppressWarnings( { "null" })
	public void executeDetail(List<WsPlasOrg> list, List<String> newResOrgList,Date newDate) {
		if (list != null && !list.isEmpty()) {
			for (WsPlasOrg m : list) {
				// 从对应的节点中搜索出主责中心对应的节点
				if (newResOrgList != null && !newResOrgList.isEmpty()) {
					for (String orgName : newResOrgList) {
						int reply = 0;
						// 未确认数统计
						List<Object> noConfirmList = this.getAllExecPlanDetail(
								m.getOrgCd(), "0", orgName, "0", "noConfirm",getDateYesterday(newDate));
						// 进行中数统计
						List<Object> goingList = this.getAllExecPlanDetail(m
								.getOrgCd(), "1", orgName, "0", "going",getDateYesterday(newDate));
						if (goingList != null && goingList.size() > 0) {
							for (int i = 0; i < goingList.size(); i++) {
								Object[] obj = (Object[]) goingList.get(i);
								if (!formateDate2YMd((Date) obj[47]).equals(
										formateDate2YMd(getDateYesterday(newDate)))) {
									reply++;
								}
							}
						}
						// 过期数统计
						List<Object> suspendList = this.getAllExecPlanDetail(m
								.getOrgCd(), "1", orgName, "0", "suspend",getDateYesterday(newDate));
						// 完成数统计
						List<Object> completeList = this.getAllExecPlanDetail(m
								.getOrgCd(), "1", orgName, "2", "complete",newDate);
						// 根据主责方进行入库
						if (reply!=0 || noConfirmList.size()!=0 || goingList.size()!=0 || suspendList.size()!=0 || completeList.size()!=0){
							ExecPlanProcessing execPlanProcessing = new ExecPlanProcessing();
							execPlanProcessing.setProjectCd(m.getOrgCd());
							execPlanProcessing.setProjectName(m.getOrgName());
							execPlanProcessing.setResOrgName(orgName);
							execPlanProcessing.setNoConfirmStatus(String
									.valueOf(noConfirmList.size()));
							execPlanProcessing.setGoingStatus(String
									.valueOf(goingList.size()));
							execPlanProcessing.setSuspendStatus(String
									.valueOf(suspendList.size()));
							execPlanProcessing.setCompleteStatus(String
									.valueOf(completeList.size()));
							execPlanProcessing.setNoReplyStatus(String
									.valueOf(reply));
							DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
							String createDate = getDateYesterday(newDate) == null ? "" : df.format(getDateYesterday(newDate));
							String format = "yyyy-MM-dd";//getDateYesterday()
							execPlanProcessing.setCreatedDate(DateOperator.parse(createDate, format));
							execPlanProcessing.setRemark("");
							execPlanLayoutManager.deleteExecPlanProcessing(m.getOrgCd(),orgName, getMonday(execPlanProcessing.getCreatedDate()), df.format(execPlanProcessing.getCreatedDate()));
							execPlanLayoutManager.saveExecPlanProcessing(execPlanProcessing);
						
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param project_cd
	 * @param info_confirmed_flg
	 * @param res_org_name
	 * @param status
	 * @param flag
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getAllExecPlanDetail(String project_cd,
			String info_confirmed_flg, String res_org_name, String status,
			String flag,Date date) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 根据条件搜索到相关执行计划的相关数据
		StringBuffer sql = new StringBuffer("select * from exec_plan_detail t");
		sql.append(" inner join exec_plan_node r on t.exec_plan_node_id = r.exec_plan_node_id");
		sql.append(" where t.project_cd =:project_cd");
		sql.append(" and r.plan_type_cd = '1'");
		sql.append(" and r.res_org_name like :res_org_name");
		//近期
		if (flag.equals("going")) {
			sql.append(" and t.info_confirmed_flg =:info_confirmed_flg");
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); //制定日期格式
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH,1); //将当前日期加一个月
			String validityDate=df.format(c.getTime());  //返回String型的时间
			sql.append(" and t.status In('0','1','3')");
			sql.append(" and t.schedule_end_date < to_date(:nowdate,'YYYY-MM-DD')");
			params.put("nowdate", validityDate);
		//过期数统计
		}else if (flag.equals("suspend")) {
			sql.append(" and t.info_confirmed_flg =:info_confirmed_flg");
			sql.append(" and t.status In('0','1','3')");
			sql.append(" and t.schedule_end_date < to_date(:nowdate,'YYYY-MM-DD') ");
			//params.put("nowdate", getMonday(date));
			params.put("nowdate", DateOperator.formatDate(date, "yyyy-MM-dd"));
		//完成
		}else if (flag.equals("complete")){
			sql.append(" and t.info_confirmed_flg =:info_confirmed_flg");
			sql.append(" and t.status = '2'");
		//未确认
		}else if (flag.equals("noConfirm")){
			sql.append(" and t.info_confirmed_flg =:info_confirmed_flg");
		}
		params.put("project_cd", project_cd);
		params.put("info_confirmed_flg", info_confirmed_flg);
		params.put("res_org_name", "%" + res_org_name + "%");
		List<Object> list = execPlanDetailDao.findBySql(sql.toString(), params);
		return list;
	}

	// /**
	// * 具体执行细节
	// * @param list
	// * @param newResOrgList
	// * @param viewPlanNodes
	// */
	// public void executeDetail(List<WsPlasOrg> list,List<String>
	// newResOrgList,List<ExecPlanNode> viewPlanNodes){
	// if (list!=null && !list.isEmpty()){
	// for (WsPlasOrg m : list) {
	// // 当前公司所有业态
	// List<ExecPlanLayout> execPlanLayouts =
	// execPlanLayoutManager.getLayouts(m.getOrgCd(), "1",true, false);
	// // 搜索出对应的所有节点
	// List<ExecPlanDetail> getListExecPlanDetail =
	// getListExecPlanDetail(execPlanLayouts, viewPlanNodes);
	// // 从对应的节点中搜索出主责中心对应的节点
	// if (newResOrgList != null && !newResOrgList.isEmpty()) {
	// for (String orgName : newResOrgList) {
	// int noActive = 0;
	// int noConfirm = 0;
	// int going = 0;
	// int suspend = 0;
	// int complete = 0;
	// int reply = 0;
	// Date scheduleEndDate = null;
	// if (getListExecPlanDetail != null
	// && !getListExecPlanDetail.isEmpty()) {
	// for (ExecPlanDetail epd : getListExecPlanDetail) {
	// // 主要通过主责方来进行节点状态的信息统计
	// if
	// (epd.getExecPlanNode().getResOrgName().trim().contains(orgName.trim())) {
	// String status = getPrintStatus(epd);
	// scheduleEndDate = epd.getScheduleEndDate();
	// if (status.equals("noActive")) {
	// // 此节点关闭
	// noActive++;
	// } else if (status.equals("noConfirm")) {
	// // 未确认
	// noConfirm++;
	// } else if (status.equals("going")) {
	// // 进行中
	// going++;
	// // 判断当天是否有更新
	// if
	// (!formateDate2YMd(epd.getUpdatedDate()).equals(formateDate2YMd(getDateYesterday())))
	// {
	// reply++;
	// }
	// } else if (status.equals("suspend")) {
	// // 过期
	// suspend++;
	// // 判断当天是否有更新
	// if
	// (!formateDate2YMd(epd.getUpdatedDate()).equals(formateDate2YMd(getDateYesterday())))
	// {
	// reply++;
	// }
	// } else if (status.equals("complete")) {
	// // 完成
	// complete++;
	// }
	// }
	// }
	// }
	// // 根据主责方进行入库
	// System.out.println("================================================");
	// System.out.println("地产公司代码:::"+ m.getOrgCd());
	// System.out.println("地产公司名称:::"+ m.getOrgName());
	// System.out.println("主责方:::" + orgName);
	// System.out.println("未确认状态数:::" + noConfirm);
	// System.out.println("进行中状态数:::" + going);
	// System.out.println("过期状态数:::" + suspend);
	// System.out.println("完成状态数:::" + complete);
	// System.out.println("节点关闭状态数:::" + noActive);
	// System.out.println("当天未回复节点数:::" + reply);
	// System.out.println("创建时间:::" + getDateYesterday());
	// System.out.println("备注:::");
	// System.out.println("计划结束时间:::" + scheduleEndDate);
	// ExecPlanProcessing execPlanProcessing = new ExecPlanProcessing();
	// execPlanProcessing.setProjectCd(m.getOrgCd());
	// execPlanProcessing.setProjectName(m.getOrgName());
	// execPlanProcessing.setResOrgName(orgName);
	// execPlanProcessing.setNoConfirmStatus(String.valueOf(noConfirm));
	// execPlanProcessing.setGoingStatus(String.valueOf(going));
	// execPlanProcessing.setSuspendStatus(String.valueOf(suspend));
	// execPlanProcessing.setCompleteStatus(String.valueOf(complete));
	// execPlanProcessing.setNoActiveStatus(String.valueOf(noActive));
	// execPlanProcessing.setNoReplyStatus(String.valueOf(reply));
	// execPlanProcessing.setCreatedDate(getDateYesterday());
	// execPlanProcessing.setRemark("");
	// execPlanLayoutManager.saveExecPlanProcessing(execPlanProcessing);
	// }
	// }
	// }
	// }
	// }
	//	
	// /*
	// * 获取单个节点的显示的状态
	// */
	// public String getPrintStatus(ExecPlanDetail pepd) {
	// String returnStr = "";
	// if (null == pepd || new ExecPlanDetail() == pepd
	// || null == pepd.getStatus())
	// return "";
	// if (!pepd.getActiveBl()) {
	// returnStr = "noActive";
	// } else if ("0".equalsIgnoreCase(pepd.getStatus())
	// && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
	// returnStr = "noConfirm";
	// } else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1"
	// .equalsIgnoreCase(pepd.getStatus()))
	// && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
	// if(pepd.getScheduleEndDate()!=null){
	// if (DateOperator.getDateNow().before(pepd.getScheduleEndDate())) {
	// returnStr = "going";
	// } else {
	// returnStr = "suspend";
	// }
	// }
	// } else if ("2".equalsIgnoreCase(pepd.getStatus())) {
	// if (null != pepd.getRealEndDate()
	// && null != pepd.getScheduleEndDate()
	// && pepd.getRealEndDate().before(pepd.getScheduleEndDate())) {
	// returnStr = "completeSuspend";
	// } else {
	// returnStr = "complete";
	// }
	// } else if ("3".equalsIgnoreCase(pepd.getStatus())) {
	// returnStr = "preComplete";
	// }
	// return returnStr;
	// }
	//	
	// /*
	// * 根据当前年份获取date的字符串，如果是同一年，则不显示前面的年份
	// */
	// public String getDateStrJudgeYear(int myYear, Date date) {
	// String returnStr = "";
	// if (null != date) {
	// if (myYear == DateOperator.getYear(date)) {
	// returnStr = DateOperator.formatDate(date, "MM/dd");
	// } else {
	// returnStr = DateOperator.formatDate(date, "yy/MM/dd");
	// }
	// }
	// return returnStr;
	// }
	//	
	// /*
	// * 获取单个节点的显示的HTML
	// */
	// public String getPrintHtml(ExecPlanDetail pepd) {
	// Date myDate = DateOperator.getDateNow();
	// int myYear = DateOperator.getYear(myDate);
	// String printStatus = getPrintStatus(pepd);
	// String returnStr = "";
	// if ("noActive".equalsIgnoreCase(printStatus)) {
	// returnStr = "<span title='此节点关闭'>/</span>";
	// } else if ("going".equalsIgnoreCase(printStatus) ||
	// ("preComplete".equalsIgnoreCase(printStatus))) {
	// if(null!=pepd.getScheduleStartDate()){
	// returnStr = "<span class='color_blue' title='进行中'>" +
	// getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "~"
	// + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
	// }else{
	// returnStr = "<span class='color_blue' title='进行中'>" +
	// getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
	// }
	// } else if ("suspend".equalsIgnoreCase(printStatus)) {
	// if(null!=pepd.getScheduleStartDate()){
	// returnStr = "<span class='color_red' title='过期'>" +
	// getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "~"
	// + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
	// }else{
	// returnStr = "<span class='color_red' title='过期'>" +
	// getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
	// }
	// } else if ("noConfirm".equalsIgnoreCase(printStatus)) {
	// if(null!=pepd.getScheduleStartDate()){
	// returnStr = "<span class='color_red' title='未确认'>" +
	// getDateStrJudgeYear(myYear, pepd.getScheduleStartDate())
	// + "~" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) +
	// "</span>";
	// }else{
	// returnStr = "<span class='color_red' title='未确认'>" +
	// getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
	// }
	// } else if ("complete".equalsIgnoreCase(printStatus)) {
	// returnStr = "<span class='color_green' title='完成'>" +
	// getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
	// } else if ("completeSuspend".equalsIgnoreCase(printStatus)) {
	// returnStr = "<span class='color_dark_green' title='完成(曾过期)'>" +
	// getDateStrJudgeYear(myYear, pepd.getRealEndDate())
	// + "(<span class='color_red'>"+getDateStrJudgeYear(myYear,
	// pepd.getScheduleEndDate()) + "</span>)</span>";
	// } else if ("preComplete".equalsIgnoreCase(printStatus)) {
	// returnStr = "<span class='color_yellow' title='预完成'>" +
	// getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
	// }
	// return returnStr;
	// }

	public  String getMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	/**
	 * 
	 * @param date
	 * @return
	 */
	private String formateDate2YMd(Date date) {
		SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormatter2.format(date);
	}

	/**
	 * 
	 * @return
	 */
	private Date getDateYesterday(Date newDate) {
		Calendar calendarIn = Calendar.getInstance();
		calendarIn.setTime(newDate);
		calendarIn.add(Calendar.DAY_OF_MONTH, -1);
		calendarIn.roll(Calendar.MONTH, 0);
		return calendarIn.getTime();
	}
	
	public List<ExecPlanProcessing> getAllExecPlanProcessing(String projectCd,Date createDate) {
		if(StringUtils.isNotBlank(projectCd) && StringUtils.isNotBlank(projectCd)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from ExecPlanProcessing where");
			sb.append(" projectCd=:projectCd and createdDate=to_date(:createDate,'YYYY/MM/DD') order by createdDate desc");
		    params.put("projectCd", projectCd);
//		    params.put("createDate", createDate);
		    params.put("createDate", DateOperator.formatDate(createDate, "yyyy-MM-dd"));
			List<ExecPlanProcessing> arr = this.getDao().find(sb.toString(), params);
			return arr;
		} else
			return null;
	}
	
	public List<ExecPlanProcessing> gettMonthExecPlanProcessing(String projectCd,Date startDate,Date endDate) {
		if(StringUtils.isNotBlank(projectCd) && StringUtils.isNotBlank(projectCd)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from ExecPlanProcessing where");
			sb.append(" projectCd=:projectCd and createdDate between to_date(:startDate,'YYYY/MM/DD') and to_date(:endDate,'YYYY/MM/DD') order by createdDate desc");
		    params.put("projectCd", projectCd);
//		    params.put("createDate", createDate);
		    params.put("startDate", DateOperator.formatDate(startDate, "yyyy-MM-dd"));
		    params.put("endDate", DateOperator.formatDate(endDate, "yyyy-MM-dd"));
			List<ExecPlanProcessing> arr = this.getDao().find(sb.toString(), params);
			return arr;
		} else
			return null;
	}
	
	/*
	 * 获取单个节点的显示的状态
	 */
	public String getPrintStatus(ExecPlanDetail pepd,Date newDate) {
		String printStatus = getStatus(pepd);
		String returnStr = "";
		if ("noActive".equalsIgnoreCase(printStatus)) {
			returnStr = "noActive";
		} else if ("going".equalsIgnoreCase(printStatus) || ("preComplete".equalsIgnoreCase(printStatus))) {
			if(null!=pepd.getScheduleStartDate()){
				returnStr = "进行中";
			}else{
				returnStr = "进行中";
			}
			if (!formateDate2YMd(pepd.getUpdatedDate()).equals(
					formateDate2YMd(getDateYesterday(newDate)))) {
				reply++;
			}
		} else if ("suspend".equalsIgnoreCase(printStatus)) {
			if(null!=pepd.getScheduleStartDate()){
				returnStr = "过期";
			}else{
				returnStr = "过期";
			}
		} else if ("noConfirm".equalsIgnoreCase(printStatus)) {
			if(null!=pepd.getScheduleStartDate()){
				returnStr = "未确认";
			}else{
				returnStr = "未确认";
			}
		} else if ("complete".equalsIgnoreCase(printStatus)) {
			returnStr = "完成";
		} 
		return returnStr;
	}
	/*
	 * 获取单个节点的显示的状态
	 */
	public String getStatus(ExecPlanDetail pepd) {
		String returnStr = "";
		if(null==pepd || new ExecPlanDetail()==pepd || null==pepd.getStatus())
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "noActive";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "noConfirm";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (null!=pepd.getScheduleEndDate()&&DateOperator.getDateNow().before(pepd.getScheduleEndDate())) {
				returnStr = "going";
			} else {
				returnStr = "suspend";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			if (null!=pepd.getRealEndDate()
					&&null!=pepd.getScheduleEndDate()
					&&pepd.getRealEndDate().before(pepd.getScheduleEndDate())) {
				returnStr = "completeSuspend";
			} else {
				returnStr = "complete";
			}
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "preComplete";
		}
		return returnStr;
	}
	
	/**
	 * 
	 * @param projectCd
	 * @param date
	 */
	public void delProcessingData(String projectCd,Date createdDate){
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("delete  from ExecPlanProcessing  where  projectCd =:projectCd and createdDate =to_date(:date,'YYYY/MM/DD')");
		values.put("projectCd", projectCd);
		values.put("date",DateOperator.formatDate(createdDate, "yyyy-MM-dd"));
	    execPlanDetailDao.batchExecute(hql.toString(), values);
	    System.out.println("ddddddddd");
	}
}

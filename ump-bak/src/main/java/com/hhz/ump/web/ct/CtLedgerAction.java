package com.hhz.ump.web.ct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Query;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.ct.CtContPlanManager;
import com.hhz.ump.dao.ct.CtLedgerHismarkManager;
import com.hhz.ump.dao.ct.CtLedgerManager;
import com.hhz.ump.dao.ct.CtLedgerRoleManager;
import com.hhz.ump.entity.ct.CtLedger;
import com.hhz.ump.entity.ct.CtLedgerHismark;
import com.hhz.ump.entity.ct.CtLedgerRole;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.web.ct.vo.VoCtLedger;

@Namespace("/ct")
@Result(name = CrudActionSupport.RELOAD, location = "ct-ledger!list.action", type = "redirect")
public class CtLedgerAction extends CrudActionSupport<CtLedger> {

	private static final long serialVersionUID = -849240083339798922L;

	@Autowired
	private CtLedgerManager ctLedgerManager;
	@Autowired
	private CtLedgerRoleManager ctLedgerRoleManager;
	@Autowired
	private CtLedgerHismarkManager ctLedgerhismarkManager;
	@Autowired
	private CtContPlanManager ctContPlanManager;
	// 分页搜索
	@SuppressWarnings("unchecked")
	private Page voPage = new Page(10);
	private CtLedger entity;
	private List<VoCtLedger> voLedgerList;
	private String approvePrivFlg;
	private List<CtLedgerHismark> hisRemarkList;

	@Override
	public CtLedger getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(approvePrivFlg)) {
			entity.setApprovePrivFlg(approvePrivFlg);
		}
		entity.setSearchFlg("0");
		ctLedgerManager.saveCtLedger(entity);
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

		if (StringUtils.isNotBlank(getId())) {
			entity = ctLedgerManager.getEntity(getId());
		} else {

			entity = new CtLedger();
		}

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
		return null;
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
			List<CtLedgerRole> bprs = ctLedgerRoleManager.getProjectUserByUserCd(CtLedgerRoleManager.ACTIVE_YES, user);
			// 转化为map
			Map<String, String> bprMap = new HashMap<String, String>();
			if (bprs != null && !bprs.isEmpty()) {
				for (CtLedgerRole r : bprs) {
					bprMap.put(r.getProjectCd(), r.getUserCd());
				}
			}
			// 数节点循环比对权权限列表中的节点，查看是否存在访问权限，则加入列表newNode
			if (oldSecondNodes != null && oldSecondNodes.size() > 0) {
				List<TreePanelNode> filteredThirdNodes = null;
				for (int i = 0; i < oldSecondNodes.size(); i++) {
					// eg.南区
					// 三级节点列表
					List<TreePanelNode> thridNodes = oldSecondNodes.get(i).getChildren();
					if (thridNodes != null) {
						filteredThirdNodes = new ArrayList<TreePanelNode>();
						for (TreePanelNode thridNode : thridNodes) {
							if (bprMap.get(thridNode.getEntityCd()) != null) {
								filteredThirdNodes.add(thridNode);
							}
						}
						// 如果筛选后的列表不为空
						if (!filteredThirdNodes.isEmpty()) {
							TreePanelNode oldSecondNode = oldSecondNodes.get(i);
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

	@SuppressWarnings("unchecked")
	public Page getVoPage() {
		return voPage;
	}

	@SuppressWarnings("unchecked")
	public void setVoPage(Page voPage) {
		this.voPage = voPage;
	}

	/**
	 * 根据搜索条件,搜索投标台账列表
	 * <p/>
	 * 搜索条件有
	 * <p/>
	 * 
	 * @param selectItemCds
	 * @param ...TODO
	 * 
	 * @return
	 */
	public String detail() {
		String ctLedgerId = getId();
		try {
			if (StringUtils.isNotBlank(ctLedgerId)) {

				entity = ctLedgerManager.getEntity(ctLedgerId);
				String ss = entity.getLedgerStatus();

				String ctPlanId = "";
				if (entity.getCtPlans() != null && entity.getCtPlans().size() > 0) {
					ctPlanId = entity.getCtPlans().get(0).getCtPlanId();// added
					// by
					// dido

				}
				Struts2Utils.getRequest().setAttribute("ctPlanId", ctPlanId);// added
				// by
				// dido
				Log.info(ss);

				Map<String, Object> values = new HashMap<String, Object>();
				values.put("ctLedgerId", ctLedgerId);//
				StringBuffer hql = new StringBuffer("from CtLedgerHismark t 	");
				hql.append(" where t.ctLedger.ctLedgerId=:ctLedgerId  order by t.sequenceNo desc");
				hisRemarkList = ctLedgerhismarkManager.find(hql.toString(), values);

			}
		} catch (Exception ee) {
			ee.printStackTrace();
			// Log.info(ee.getMessage());
		}
		return "detail";
	}

	/**
	 * 根据搜索条件,搜索投标台账列表
	 * <p/>
	 * 搜索条件有
	 * <p/>
	 * 
	 * @param selectItemCds
	 * @param ...TODO
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String loadList() {

		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");

		// 高级搜索条件

		// 高级搜索条件

		String selectItemCds = Struts2Utils.getParameter("selectItemCds");
		// 总面积
		String ctTotalArea1 = Struts2Utils.getParameter("ctTotalArea1");
		String ctTotalArea2 = Struts2Utils.getParameter("ctTotalArea2");
		// 目标总额
		String ctTargetTotalAmt = Struts2Utils.getParameter("ctTargetTotalAmt");
		String ctTargetTotalAmt1 = Struts2Utils.getParameter("ctTargetTotalAmt1");
		// 分解总额
		String ctRateUnitAmt = Struts2Utils.getParameter("ctRateUnitAmt");

		String ctRateUnitAmt1 = Struts2Utils.getParameter("ctRateUnitAmt1");

		// 开工日期
		String ctStartDate1 = Struts2Utils.getParameter("ctStartDate1");
		String ctStartDate2 = Struts2Utils.getParameter("ctStartDate2");
		// 交房日期
		String ctHandDate1 = Struts2Utils.getParameter("ctHandDate1");
		String ctHandDate2 = Struts2Utils.getParameter("ctHandDate2");

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("attachFlgDel", "0");// 0-可用

		StringBuffer sqlBuf = new StringBuffer().append(" select t.ct_ledger_id,");
		sqlBuf.append(" t.project_cd,");
		sqlBuf.append(" t.project_name,");
		sqlBuf.append(" t.periods,");
		sqlBuf.append(" t.approve_priv_flg,");
		sqlBuf.append(" t.search_flg,");
		sqlBuf.append(" t.start_date,");
		sqlBuf.append(" t.hand_date, ");
		sqlBuf.append(" t.total_cons_area, ");
		sqlBuf.append(" t.plot_rate_area, ");
		sqlBuf.append(" t.cost_target_total_amt, ");
		sqlBuf.append(" t.plot_rate_unit_amt, ");
		sqlBuf.append(" t.main_content_desc, ");
		sqlBuf.append(" t.res_approve_info_id, ");
		sqlBuf.append(" t.ledger_status ");
		// sqlBuf.append("(select sum(ctp.divi_total_amt) from ct_cont_plan ctp ");
		// sqlBuf.append(" where ctp.ct_cont_plan_id");
		// sqlBuf
		// .append(
		// " in(select distinct cti.ct_cont_plan_id from  ct_item cti where cti.ct_ledger_id= t.ct_ledger_id  ))   as totalDiviPlanContAmt ")

		sqlBuf.append(" from ct_ledger t ").append(" where 1=1 ");

		if (StringUtils.isNotBlank(selectItemCds)) {
			sqlBuf.append(" and t.project_name in (:projectNames) ");
			values.put("projectNames", selectItemCds.split(","));
		}

		if (StringUtils.isNotBlank(ctTotalArea1)) {
			sqlBuf.append(" and t.total_cons_area >=:ctTotalArea1 ");
			values.put("ctTotalArea1", ctTotalArea1.trim());
		}
		if (StringUtils.isNotBlank(ctTotalArea2)) {
			sqlBuf.append(" and t.total_cons_area <=:ctTotalArea2 ");
			values.put("ctTotalArea2", ctTotalArea2.trim());
		}

		if (StringUtils.isNotBlank(ctTargetTotalAmt)) {
			sqlBuf.append(" and t.cost_target_total_amt >=:ctTargetTotalAmt ");
			values.put("ctTargetTotalAmt", ctTargetTotalAmt.trim());
		}

		if (StringUtils.isNotBlank(ctTargetTotalAmt1)) {
			sqlBuf.append(" and t.cost_target_total_amt <=:ctTargetTotalAmt1 ");
			values.put("ctTargetTotalAmt1", ctTargetTotalAmt1.trim());
		}

		if (StringUtils.isNotBlank(ctStartDate1)) {
			sqlBuf.append(" and t.start_date >= to_date(:ctStartDatebid1,'yyyy-mm-dd')");
			values.put("ctStartDatebid1", ctStartDate1.trim());
		}
		if (StringUtils.isNotBlank(ctStartDate2)) {
			sqlBuf.append(" and t.start_date <= to_date(:ctStartDatebid2,'yyyy-mm-dd')");
			values.put("ctStartDatebid2", ctStartDate2.trim());
		}
		if (StringUtils.isNotBlank(ctHandDate1)) {
			sqlBuf.append(" and t.hand_date >= to_date(:ctHandDate1,'yyyy-mm-dd')");
			values.put("ctHandDate1", ctHandDate1.trim());
		}
		if (StringUtils.isNotBlank(ctHandDate2)) {
			sqlBuf.append(" and t.hand_date <= to_date(:ctHandDate2,'yyyy-mm-dd')");
			values.put("ctHandDate2", ctHandDate2.trim());
		}

		// 设置默认排序方式
		if (StringUtils.isBlank(sortField)) {
			sqlBuf.append(" order by t.created_date desc ");
		} else {
			sqlBuf.append(" order by t." + sortField + " " + order);
		}

		if (pageNo != null) {
			voPage.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			voPage.setPageSize(Integer.valueOf(rows));
		}

		voPage = ctLedgerManager.findPageSql(voPage, sqlBuf.toString(), values, new HashMap<String, Class>());
		List tmpList = voPage.getResult();
		voLedgerList = new ArrayList<VoCtLedger>();
		Object[] obj = null;
		VoCtLedger tmpVo = null;
		int count = 0;
		for (int i = 0; i < tmpList.size(); i++) {
			obj = (Object[]) tmpList.get(i);
			tmpVo = new VoCtLedger();

			try {
				sqlBuf = new StringBuffer("");
				// sqlBuf.append("select to_char(sum(ctp.divi_total_amt)) as totalAmt from ct_cont_plan ctp ");

				sqlBuf.append("select sum(ctp.divi_total_amt) as totalAmt from ct_cont_plan ctp ");

				sqlBuf.append(" where ctp.ct_cont_plan_id");
				sqlBuf.append(" in(select distinct cti.ct_cont_plan_id ");
				sqlBuf.append(" from  ct_item cti where cti.ct_ledger_id='" + (String) obj[0] + "')");

				Query query = ctContPlanManager.getDao().createSQLQuery(sqlBuf.toString(), values);
				List totalAmtList = query.list();
				String totalObj = "0";
				if (totalAmtList != null && totalAmtList.size() > 0) {
					totalObj = "" + totalAmtList.get(0);
					if (StringUtils.isBlank(totalObj) || totalObj.equals("null")) {
						totalObj = "0";
					}

					if (StringUtils.isNotBlank(ctRateUnitAmt)) {
						if (Long.valueOf(totalObj) < Long.valueOf(ctRateUnitAmt)) {
							continue;
						}

					}
					if (StringUtils.isNotBlank(ctRateUnitAmt1)) {
						if (Long.valueOf(totalObj) > Long.valueOf(ctRateUnitAmt)) {
							continue;
						}
					}
					tmpVo.setTotalDiviPlanContAmt(BigDecimal.valueOf(Long.valueOf(totalObj)));
				}
				count++;
				tmpVo.setCtLedgerId((String) obj[0]);
				tmpVo.setProjectCd((String) obj[1]);
				tmpVo.setProjectName((String) obj[2]);
				tmpVo.setPeriods((String) obj[3]);
				tmpVo.setApprovePrivFlg((String) obj[4]);

				tmpVo.setSearchFlg((String) obj[5]);
				tmpVo.setStartDate((Date) obj[6]);
				tmpVo.setHandDate((Date) obj[7]);
				tmpVo.setTotalConsArea(obj[8] == null ? null : (BigDecimal) obj[8]);
				tmpVo.setPlotRateArea(obj[9] == null ? null : (BigDecimal) obj[9]);

				tmpVo.setCostTargetTotalAmt(obj[10] == null ? null : (BigDecimal) obj[10]);
				tmpVo.setPlotRateUnitAmt(obj[11] == null ? null : (BigDecimal) obj[11]);
				tmpVo.setMainContentDesc((String) obj[12]);
				tmpVo.setResApproveInfoId((String) obj[13]);
				tmpVo.setLedgerStatus((String) obj[14]);

			} catch (Exception ee) {
				ee.printStackTrace();
			}
			voLedgerList.add(tmpVo);
		}
		voPage.setTotalCount(count);
		return "loadList";
	}

	/**
	 * 审核台账
	 */
	public void approveLedger() {

		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			String ctLedgerId = Struts2Utils.getParameter("ctLedgerId");
			String ledgerstatus = Struts2Utils.getParameter("status");
			entity = ctLedgerManager.getEntity(ctLedgerId);
			entity.setCtLedgerId(ctLedgerId);
			entity.setLedgerStatus(ledgerstatus);
			ctLedgerManager.saveCtLedger(entity);
			strBuffer.append("true}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
	}

	/**
	 * 驳回台账
	 */

	public void overruleLedger() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			String ctLedgerId = Struts2Utils.getParameter("ctLedgerId");
			String ledgerstatus = Struts2Utils.getParameter("status");
			entity = ctLedgerManager.getEntity(ctLedgerId);
			entity.setCtLedgerId(ctLedgerId);
			entity.setLedgerStatus(ledgerstatus);
			ctLedgerManager.saveCtLedger(entity);
			strBuffer.append("true}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
	}

	/**
	 * 变更台账
	 */
	public void modificationLegder() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			String ctLedgerId = Struts2Utils.getParameter("ctLedgerId");
			String ledgerstatus = Struts2Utils.getParameter("status");
			String modRemark = Struts2Utils.getParameter("modRemark");
			entity = ctLedgerManager.getEntity(ctLedgerId);
			entity.setCtLedgerId(ctLedgerId);
			entity.setLedgerStatus(ledgerstatus);
			ctLedgerManager.saveCtLedger(entity);

			if (StringUtils.isNotBlank(ledgerstatus) && (ledgerstatus.equals("1") || ledgerstatus.equals("9"))) {// 如是是变更后提交，则记录变更描述
				CtLedgerHismark ctLedgerHismark = new CtLedgerHismark();
				Map<String, Object> values = new HashMap<String, Object>();
				values.put("ctLedgerId", ctLedgerId);

				StringBuffer hql = new StringBuffer("from CtLedgerHismark clh ");
				hql.append("  where  clh.ctLedger.ctLedgerId=:ctLedgerId) ");
				Query query = ctLedgerhismarkManager.getDao().createQuery(hql.toString(), values);
				int count = query.list().size() + 1;
				ctLedgerHismark.setRemark(modRemark);
				ctLedgerHismark.setCtLedger(entity);
				ctLedgerHismark.setSequenceNo(Long.valueOf(count));
				ctLedgerhismarkManager.saveCtLedgerHismark(ctLedgerHismark);

			}

			strBuffer.append("true}");
			// ctLedgerhismarkManager.saveCtLedgerHismark(ctLedgerHismark);
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
	}

	public List<VoCtLedger> getVoLedgerList() {
		return voLedgerList;
	}

	public void setVoLedgerList(List<VoCtLedger> voLedgerList) {
		this.voLedgerList = voLedgerList;
	}

	public CtLedger getEntity() {
		return entity;
	}

	public void setEntity(CtLedger entity) {
		this.entity = entity;
	}

	public String getApprovePrivFlg() {
		return approvePrivFlg;
	}

	public void setApprovePrivFlg(String approvePrivFlg) {
		this.approvePrivFlg = approvePrivFlg;
	}

	/**
	 * @return the hisRemarkList
	 */
	public List<CtLedgerHismark> getHisRemarkList() {
		return hisRemarkList;
	}

	/**
	 * @param hisRemarkList
	 *            the hisRemarkList to set
	 */
	public void setHisRemarkList(List<CtLedgerHismark> hisRemarkList) {
		this.hisRemarkList = hisRemarkList;
	}

}

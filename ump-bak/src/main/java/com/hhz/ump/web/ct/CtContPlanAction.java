package com.hhz.ump.web.ct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.ct.CtContPlanManager;
import com.hhz.ump.dao.ct.CtItemManager;
import com.hhz.ump.dao.ct.CtLedgerManager;
import com.hhz.ump.dao.ct.CtPlanManager;
import com.hhz.ump.entity.ct.CtAliasToBeanLandAndOperationJson;
import com.hhz.ump.entity.ct.CtContPlan;
import com.hhz.ump.entity.ct.CtContPlanJson;
import com.hhz.ump.entity.ct.CtLandAndOperationParas;
import com.hhz.ump.entity.ct.CtLedger;
import com.hhz.ump.entity.ct.CtPlan;
import com.hhz.ump.util.TreePanelNode;

@Namespace("/ct")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "ct-cont-plan!list.action", type = "redirect", params = { "ctLedgerId", "${ctLedgerId}" }),
		@Result(name = CrudActionSupport.SUCCESS, location = "ct-ledger!detail.action", type = "redirect", params = { "id", "${ctLedgerId}" })})
public class CtContPlanAction extends CrudActionSupport<CtContPlan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1527322303752368298L;
	private CtContPlan entity;
	private CtPlan ctPlan;
	private CtLedger ctLedger;
	private String ctPlanId;// 合约规划大类Id
	private String changeAmt;//调整金额
	private String ctContPlanSelect;// 选取的合约规划小类Id
	private float totalAmt = 0;// 所选科目总金额
	private String ctContPlanId;// 合约规划小类Id
	private String itemSelec = "";// 所选分解科目信息
	private List<CtPlan> ctPlanList;
	private List<CtLandAndOperationParas> landAndOperationList = new ArrayList<CtLandAndOperationParas>();
	private List<CtContPlan> conPlanList = new ArrayList<CtContPlan>();

	private String ctDismantleJson;
	private StringBuffer items = new StringBuffer("");
	private String ctLedgerId;
	@Autowired
	CtContPlanManager ctContPlanManager;
	@Autowired
	CtItemManager ctItemManager;
	@Autowired
	CtPlanManager ctPlanManager;
	@Autowired
	CtLedgerManager ctLedgerManager;

	@Override
	public CtContPlan getModel() {
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
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(ctLedgerId)) {
			ctPlanList = ctPlanManager.getCtPlanByCtLedgerId(ctLedgerId);
		}
		return "list";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(ctLedgerId)) {
			ctLedger = ctLedgerManager.getEntity(ctLedgerId);
			ctPlan.setCtLedger(ctLedger);
		}
		ctPlanManager.saveCtPlan(ctPlan);
		return RELOAD;
	}

	public String contSave() throws Exception {
		ctContPlanManager.saveCtContPlan(entity);
		String planId=entity.getCtPlan().getCtPlanId();
		Struts2Utils.renderText(planId);
		return null;
	}

	/**
	 * 查看合约规划关联信息
	 * @param ctContPlanId:合约规划ID
	 * @return 返回relationDetail頁面
	 */
	@SuppressWarnings("unchecked")
	public String relationDetail() {
		try {
			Map<String, Object> values = new HashMap<String, Object>();
			String ctConPlanId = Struts2Utils.getParameter("ctContPlanId");

			StringBuffer hqlBuff = new StringBuffer("");
			hqlBuff.append("select  t.ct_item_id as ctItemId,t.item_name  as itemName,");
			hqlBuff.append(" t.ct_ledger_id as ctLedgerId,to_char(NVL(t.target_amt,'')) as targetAmt,");
			hqlBuff.append(" t.parent_id as parentId ,cl.ct_land_id as ctLandId ,");
			hqlBuff.append("cl.land_name as landName ,t.parent_id as parentId ,");

			hqlBuff.append("co.ct_operation_id as ctOperationId,co.oper_type_name as operName");
			hqlBuff.append(" from ct_item t left join ct_oper_item_rel coi");
			hqlBuff.append("   on  t.ct_item_id =coi.ct_item_id ");
			// 左关联业态表
			hqlBuff.append("   left join ct_operation co ");
			hqlBuff.append("   on coi.ct_operation_id  =co.ct_operation_id ");
			// 左关联地块表
			hqlBuff.append("   left join ct_land cl ");

			hqlBuff.append("   on coi.ct_land_id = cl.ct_land_id  ");
			hqlBuff.append("where t.ct_cont_plan_id ='" + ctConPlanId + "'");

			hqlBuff.append("order by t.item_level asc, t.sequence_no asc");
			Query query = ctItemManager.getDao().createSQLQuery(hqlBuff.toString(), values);
			query.setResultTransformer(new AliasToBeanResultTransformer(CtAliasToBeanLandAndOperationJson.class));
			List<CtAliasToBeanLandAndOperationJson> aliasToBeanList = query.list();
			landAndOperationList.clear();

			for (int i = 0; i < aliasToBeanList.size(); i++) {
				CtLandAndOperationParas landAndOperParam = new CtLandAndOperationParas();
				CtAliasToBeanLandAndOperationJson aliasToBeanLandAndOper = aliasToBeanList.get(i);
				ctLedgerId = aliasToBeanLandAndOper.getCTLEDGERID();
				landAndOperParam.setCtItemId(aliasToBeanLandAndOper.getCTITEMID());
				landAndOperParam.setCtLandId(aliasToBeanLandAndOper.getCTLANDID());
				landAndOperParam.setCtLedgerId(aliasToBeanLandAndOper.getCTLEDGERID());
				landAndOperParam.setCtOperationId(aliasToBeanLandAndOper.getCTOPERATIONID());
				landAndOperParam.setItemName(aliasToBeanLandAndOper.getITEMNAME());
				landAndOperParam.setLandName(aliasToBeanLandAndOper.getLANDNAME());
				landAndOperParam.setOperName(aliasToBeanLandAndOper.getOPERNAME());
				landAndOperParam.setParentId(aliasToBeanLandAndOper.getPARENTID());
				landAndOperParam.setTargetAmt(aliasToBeanLandAndOper.getTARGETAMT());
				landAndOperationList.add(landAndOperParam);

			}
			for (int i = 0; i < landAndOperationList.size(); i++) {
				if (StringUtils.isNotBlank(landAndOperationList.get(i).getCtItemId())) {
					itemSelec = itemSelec + landAndOperationList.get(i).getCtItemId() + ",";
				}
			}
			items = new StringBuffer("");
			TreePanelNode treePanelNode = new TreePanelNode();
			treePanelNode.setChecked("");
			treePanelNode.setId("-1");
			treePanelNode.setText("成本科目");
			treePanelNode.setTitle("成本科目");
			treePanelNode.setNodeType("root");

			treePanelNode.setChildren(setLandChildren(""));

			ctDismantleJson = JSONArray.fromObject(treePanelNode).toString();

			if (StringUtils.isNotBlank(ctLedgerId)) {
				ctPlanList = ctPlanManager.getCtPlanByCtLedgerId(ctLedgerId);
			}
			ctContPlanSelect = ctConPlanId;
			for (int i = 0; i < landAndOperationList.size(); i++) {
				if (StringUtils.isNotBlank(landAndOperationList.get(i).getCtItemId())) {
					itemSelec = itemSelec + landAndOperationList.get(i).getCtItemId() + ",";
				}
			}

			CtContPlan ctContPlan = ctContPlanManager.getEntity(ctConPlanId);
			if (ctContPlan != null && ctContPlan.getCtPlan() != null) {
				ctPlanId = ctContPlan.getCtPlan().getCtPlanId();
			}
		} catch (Exception ee) {
			ctDismantleJson = "[]";
		}

		return "relationDetail";
	}

	@Override
	/**
	 * 删除合约规划
	 * @param ctContPlanId  合约规划ID
	 * @return success:删除成功 failed :删除失败
	 */
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(ctContPlanId)) {
			entity = ctContPlanManager.getEntity(ctContPlanId);
			ctContPlanManager.delete(entity);
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("failed");
		}
		return null;
	}

	public String deletePlan() throws Exception {
		if (StringUtils.isNotBlank(ctPlanId)) {
			ctPlan = ctPlanManager.getEntity(ctPlanId);
			ctPlanId = ctPlan.getCtPlanId();
			ctPlanManager.delete(ctPlan);
			Struts2Utils.renderText(ctPlanId);
		} else {
			Struts2Utils.renderText("failed");
		}
		return null;
	}
    /**
     * 根据合约规划大类搜索下面的合约规度子类
     * @param ctPlanId 合约规划大类ID
     * @return 合约规划列表
     * @throws Exception
     */
	public String queryCtContPlan() throws Exception {

		try {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("ctPlanId", ctPlanId);
			StringBuffer hql = new StringBuffer("select ");
			hql.append("t.ctContPlanId, ");
			hql.append("t.ctPlan.ctPlanId, ");
			hql.append("t.ledgerTypeCd, ");
			hql.append("t.contMainCd, ");
			hql.append("t.contContentDesc, ");

			hql.append("t.contPropCd, ");
			hql.append("t.contFaceCd, ");
			hql.append("t.contInviPreCd, ");
			hql.append("t.consructArea, ");
			hql.append("t.remark,  ");

			hql.append("(select count(*) from CtItem ci ");
			hql.append("where ci.ctContPlan.ctContPlanId=t.ctContPlanId ");
			hql.append(") as updatedPositionCd, ");
			hql.append("t.creator,");
			hql.append("t.createdCenterCd as createdCenterCd,");
			hql.append("t.createdDate as createdDate,");
			hql.append("t.updator as updator,");
			hql.append("t.updatedCenterCd as updatedCenterCd,");

			hql.append("t.updatedDate as updatedDate,");
			hql.append("t.recordVersion as recordVersion , ");
			hql.append("t.diviTotalAmt as diviTotalAmt ");
			hql.append("from CtContPlan t ");
			hql.append("where t.ctPlan.ctPlanId=:ctPlanId");
			Query query = ctContPlanManager.getDao().createQuery(hql.toString(), values);
			List dataList = query.list();
			conPlanList.clear();
			for (int i = 0; i < dataList.size(); i++) {

				Object[] objectConPlan = (Object[]) dataList.get(i);

				if (objectConPlan != null && objectConPlan.length > 0) {

					CtContPlan conPlan = new CtContPlan();
					conPlan.setCtContPlanId((String) objectConPlan[0]);
					CtPlan plan = new CtPlan();
					plan.setCtPlanId((String) objectConPlan[1]);
					conPlan.setCtPlan(plan);
					conPlan.setLedgerTypeCd((String) objectConPlan[2]);
					conPlan.setContMainCd((String) objectConPlan[3]);
					conPlan.setContContentDesc((String) objectConPlan[4]);
					conPlan.setContPropCd((String) objectConPlan[5]);
					conPlan.setContFaceCd((String) objectConPlan[6]);
					conPlan.setContInviPreCd((String) objectConPlan[7]);
					String area = String.valueOf(objectConPlan[8]);

					if (StringUtils.isNotBlank(area) && objectConPlan[8] != null) {

						conPlan.setConsructArea(BigDecimal.valueOf(Float.valueOf(area)));
					}

					conPlan.setRemark((String) objectConPlan[9]);
					conPlan.setUpdatedPositionCd(String.valueOf(objectConPlan[10]));

					conPlanList.add(conPlan);
				}

			}
			dataList.clear();
		} catch (Exception ee) {
			ee.printStackTrace();
		}

		return "contList";
	}

	public String planDetail() throws Exception {
		if (StringUtils.isNotBlank(ctPlanId)) {
			ctPlan = ctPlanManager.getEntity(ctPlanId);
		}
		return "planDetail";
	}
	public String contPlanDetail(){
		if(StringUtils.isNotBlank(ctContPlanId)){
			entity=ctContPlanManager.getEntity(ctContPlanId);
		}
		return "contPlanDetail";
	}
	public String saveItemPlanConn() {
		if (StringUtils.isNotBlank(ctContPlanSelect) && StringUtils.isNotBlank(itemSelec)) {
			String flag = ctItemManager.updateCtContPlanId(ctContPlanSelect, itemSelec);// 更新科目关联的合约规划

		}
		if (totalAmt != 0 && StringUtils.isNotBlank(ctContPlanSelect)) {
			entity = ctContPlanManager.getEntity(ctContPlanSelect);
			//totalAmts = totalAmts.add(entity.getDiviTotalAmt() == null ? new BigDecimal(0) : entity.getDiviTotalAmt());
			if(StringUtils.isNotBlank(changeAmt)) {
				BigDecimal changeAmttotal = new BigDecimal(changeAmt);
				entity.setDiviTotalAmt(changeAmttotal);
			}else{
				BigDecimal totalAmtss = new BigDecimal(totalAmt);
				entity.setDiviTotalAmt(totalAmtss);
			}
			ctContPlanManager.saveCtContPlan(entity);
		}
		return SUCCESS;
	}

	public void cancelItemPlanConn() {
		if (StringUtils.isNotBlank(ctContPlanSelect) && StringUtils.isNotBlank(itemSelec)) {
			String cancel = "0";
			String flag = ctItemManager.updateCtContPlanId(ctContPlanSelect, itemSelec, cancel);// 删除科目关联的合约规划

		}
		entity = ctContPlanManager.getEntity(ctContPlanSelect);
		entity.setDiviTotalAmt(new BigDecimal(0));
		ctContPlanManager.saveCtContPlan(entity);
		Struts2Utils.renderText("取消成功");

	}

	@SuppressWarnings("unchecked")
	public String itemPlanConn() throws Exception {

		String jsonStr = Struts2Utils.getParameter("jsonData");
		ctLedgerId = Struts2Utils.getParameter("ctLedgerId");
		JSONArray array = JSONArray.fromObject(jsonStr);
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			landAndOperationList.add((CtLandAndOperationParas) JSONObject.toBean(jsonObject, CtLandAndOperationParas.class));
		}
		for (int i = 0; i < landAndOperationList.size(); i++) {
			if (StringUtils.isNotBlank(landAndOperationList.get(i).getTargetAmt())) {
				totalAmt += Float.valueOf(landAndOperationList.get(i).getTargetAmt());
			}
			if (StringUtils.isNotBlank(landAndOperationList.get(i).getCtItemId())) {
				itemSelec = itemSelec + landAndOperationList.get(i).getCtItemId() + ",";
			}
		}
		items = new StringBuffer("");
		TreePanelNode treePanelNode = new TreePanelNode();
		treePanelNode.setChecked("");
		treePanelNode.setId("-1");
		treePanelNode.setText("成本科目");
		treePanelNode.setTitle("成本科目");
		treePanelNode.setNodeType("root");

		treePanelNode.setChildren(setLandChildren(""));

		if (StringUtils.isNotBlank(jsonStr)) {

			ctDismantleJson = jsonStr;
		} else {
			ctDismantleJson = "[]";

		}

		ctDismantleJson = JSONArray.fromObject(treePanelNode).toString();

		if (StringUtils.isNotBlank(ctLedgerId)) {
			ctPlanList = ctPlanManager.getCtPlanByCtLedgerId(ctLedgerId);
		}

		return "itemPlanConn";
	}

	@SuppressWarnings("unchecked")
	public void getCtContPlan() {
		String plandId = Struts2Utils.getParameter("ctPlanId");
		String actType = Struts2Utils.getParameter("actType");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("planId", plandId);
		if (StringUtils.isBlank(actType)) {
			actType = "select";
		}
		StringBuffer hqlBuff = new StringBuffer("from CtContPlan t where t.ctPlan.ctPlanId=:planId");
		Query query = ctContPlanManager.getDao().createQuery(hqlBuff.toString(), values);
		List ctContPlanList = query.list();
		String flag = null;
		List<CtContPlanJson> contPlanList = new ArrayList<CtContPlanJson>();
		for (int i = 0; i < ctContPlanList.size(); i++) {
			CtContPlan ctConPlan = (CtContPlan) ctContPlanList.get(i);
			flag = ctItemManager.getContPlanConn(ctConPlan.getCtContPlanId());// flag=0表示该合约无关联1、表示已经关联
			if (flag.equals("0") || !actType.equals("select")) {
				CtContPlanJson planJson = new CtContPlanJson();
				planJson.setCtContPlanId(ctConPlan.getCtContPlanId());
				planJson.setLedgerTypeCd(ctConPlan.getLedgerTypeCd());//标段划分
//				planJson.setContMainCd(ctConPlan.getContMainCd());//合同主体
//				planJson.setContContentDesc(ctConPlan.getContContentDesc());//合同内容
//				planJson.setContPropCd(ctConPlan.getContPropCd());//合同属性
//				planJson.setContFaceCd(ctConPlan.getContFaceCd());//合同界面简要
//				planJson.setContInviPreCd(ctConPlan.getContInviPreCd());//招标前置条件
//				planJson.setConsructArea(ctConPlan.getConsructArea());//施工面积
//				planJson.setDiviTotalAmt(ctConPlan.getDiviTotalAmt());//调整金额
				contPlanList.add(planJson);
			}
		}
		Struts2Utils.renderJson(contPlanList);
	}

	private List<TreePanelNode> setLandChildren(String parentId) {

		List<TreePanelNode> childrens = new ArrayList<TreePanelNode>();
		StringBuffer landBuffer = new StringBuffer("");
		for (int i = 0; i < landAndOperationList.size(); i++) {

			CtLandAndOperationParas landAndOperation = landAndOperationList.get(i);
			if (landBuffer.toString().indexOf(landAndOperation.getCtLandId()) > -1) {
				continue;
			}
			landBuffer.append(landAndOperation.getCtLandId());
			TreePanelNode treePanelLandNode = new TreePanelNode();
			treePanelLandNode.setText(landAndOperation.getLandName());
			treePanelLandNode.setTitle(landAndOperation.getLandName());
			treePanelLandNode.setId(landAndOperation.getCtLandId());
			treePanelLandNode.setNodeType("land");
			treePanelLandNode.setChildren(setOperationChildren(landAndOperation.getCtLandId()));
			childrens.add(treePanelLandNode);
		}
		return childrens;
	}

	private List<TreePanelNode> setOperationChildren(String parentLandId) {

		List<TreePanelNode> childrens = new ArrayList<TreePanelNode>();
		StringBuffer operationBuffer = new StringBuffer("");
		for (int i = 0; i < landAndOperationList.size(); i++) {
			CtLandAndOperationParas landAndOperation = landAndOperationList.get(i);
			if (!parentLandId.equals(landAndOperation.getCtLandId()) || operationBuffer.toString().indexOf(landAndOperation.getCtOperationId()) > -1) {
				continue;
			}
			operationBuffer.append(landAndOperation.getCtOperationId());
			TreePanelNode treePanelOperNode = new TreePanelNode();
			treePanelOperNode.setText(landAndOperation.getOperName());
			treePanelOperNode.setTitle(landAndOperation.getOperName());
			treePanelOperNode.setId(landAndOperation.getCtOperationId());
			treePanelOperNode.setNodeType("operation");
			treePanelOperNode.setChildren(setItemChildren(landAndOperation.getCtOperationId(), ""));
			childrens.add(treePanelOperNode);

		}
		return childrens;
	}

	private List<TreePanelNode> setItemChildren(String parentOperationId, String parentId) {

		List<TreePanelNode> childrens = new ArrayList<TreePanelNode>();

		for (int i = 0; i < landAndOperationList.size(); i++) {
			CtLandAndOperationParas landAndOperation = landAndOperationList.get(i);
			if (!parentOperationId.equals(landAndOperation.getCtOperationId()) || items.toString().indexOf(landAndOperation.getCtItemId()) > -1) {
				continue;
			}
			if (StringUtils.isNotBlank(parentId) && !parentId.equals(landAndOperation.getParentId())) {
				continue;
			}

			items.append("'");
			items.append(landAndOperation.getCtItemId());
			items.append("'");
			TreePanelNode treePanelItemNode = new TreePanelNode();

			treePanelItemNode.setEntityStatusCd(landAndOperation.getCtLandId());
			treePanelItemNode.setExtParam(landAndOperation.getLandName());
			// 设置父母节点业态CD
			treePanelItemNode.setEntityId(landAndOperation.getCtOperationId());
			// 设置业态名称CD
			treePanelItemNode.setEntityName(landAndOperation.getOperName());
			// 设置landCD
			treePanelItemNode.setEntityBizCd(landAndOperation.getCtLandId());
			// 设置父级科目CD
			treePanelItemNode.setEntityCd(landAndOperation.getParentId());
			treePanelItemNode.setText(landAndOperation.getItemName());
			treePanelItemNode.setTitle(landAndOperation.getItemName());
			treePanelItemNode.setId(landAndOperation.getCtItemId());

			treePanelItemNode.setLevelCd(landAndOperation.getTargetAmt());

			treePanelItemNode.setNodeType("item");
			childrens.add(treePanelItemNode);
			treePanelItemNode.setChildren(setItemChildren(landAndOperation.getCtOperationId(), landAndOperation.getCtItemId()));

		}

		return childrens;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
	}

	public CtContPlan getEntity() {
		return entity;
	}

	public CtPlan getCtPlan() {
		return ctPlan;
	}

	public void setEntity(CtContPlan entity) {
		this.entity = entity;
	}

	public void setCtPlan(CtPlan ctPlan) {
		this.ctPlan = ctPlan;
	}

	public String getCtLedgerId() {
		return ctLedgerId;
	}

	public void setCtLedgerId(String ctLedgerId) {
		this.ctLedgerId = ctLedgerId;
	}

	public List<CtPlan> getCtPlanList() {
		return ctPlanList;
	}

	public void setCtPlanList(List<CtPlan> ctPlanList) {
		this.ctPlanList = ctPlanList;
	}

	public CtLedger getCtLedger() {
		return ctLedger;
	}

	public void setCtLedger(CtLedger ctLedger) {
		this.ctLedger = ctLedger;
	}

	public String getCtPlanId() {
		return ctPlanId;
	}

	public void setCtPlanId(String ctPlanId) {
		this.ctPlanId = ctPlanId;
	}

	public String getCtContPlanId() {
		return ctContPlanId;
	}

	public void setCtContPlanId(String ctContPlanId) {
		this.ctContPlanId = ctContPlanId;
	}

	public String getItemSelec() {
		return itemSelec;
	}

	public void setItemSelec(String itemSelec) {
		this.itemSelec = itemSelec;
	}

	public List<CtLandAndOperationParas> getLandAndOperationList() {
		return landAndOperationList;
	}

	public void setLandAndOperationList(List<CtLandAndOperationParas> landAndOperationList) {
		this.landAndOperationList = landAndOperationList;
	}

	public Float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(Float totalAmt) {
		this.totalAmt = totalAmt;
	}

	/**
	 * @return the ctDismantleJson
	 */
	public String getCtDismantleJson() {
		return ctDismantleJson;
	}

	/**
	 * @param ctDismantleJson
	 *            the ctDismantleJson to set
	 */
	public void setCtDismantleJson(String ctDismantleJson) {
		this.ctDismantleJson = ctDismantleJson;
	}

	public String getCtContPlanSelect() {
		return ctContPlanSelect;
	}

	public void setCtContPlanSelect(String ctContPlanSelect) {
		this.ctContPlanSelect = ctContPlanSelect;
	}

	/**
	 * @return the conPlanList
	 */
	public List<CtContPlan> getConPlanList() {
		return conPlanList;
	}

	/**
	 * @param conPlanList
	 *            the conPlanList to set
	 */
	public void setConPlanList(List<CtContPlan> conPlanList) {
		this.conPlanList = conPlanList;
	}

	public String getChangeAmt() {
		return changeAmt;
	}

	public void setChangeAmt(String changeAmt) {
		this.changeAmt = changeAmt;
	}
}

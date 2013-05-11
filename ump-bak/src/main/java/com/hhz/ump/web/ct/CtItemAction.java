/**
 * 
 */
package com.hhz.ump.web.ct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.ct.CtItemManager;
import com.hhz.ump.dao.ct.CtItemTemplateManager;
import com.hhz.ump.dao.ct.CtLedgerManager;
import com.hhz.ump.dao.ct.CtOperItemRelManager;
import com.hhz.ump.entity.ct.CtAliasToBeanItemJson;
import com.hhz.ump.entity.ct.CtContPlan;
import com.hhz.ump.entity.ct.CtItem;
import com.hhz.ump.entity.ct.CtItemTemplate;
import com.hhz.ump.entity.ct.CtLandAndOperationParas;
import com.hhz.ump.entity.ct.CtLedger;
import com.hhz.ump.entity.ct.CtOperItemRel;
import com.hhz.ump.entity.ct.CtOperation;
import com.hhz.ump.entity.ct.CtOperationItemTreeGridJson;

/**
 * 成本科目Action
 * 
 * @author qlb 11/10/2011 version 1.0.0
 * 
 * 
 */
public class CtItemAction extends CrudActionSupport<CtItem> {

	private static final long serialVersionUID = 8605595591871898369L;
	// 科目表 sevivce
	@Autowired
	CtItemManager itemManager;
	
	@Autowired
	CtLedgerManager ledgerManager;

	// 成本科目模板表 Service
	@Autowired
	private CtItemTemplateManager itemTemplateManager;

	// 业态科目表serivce
	@Autowired
	private CtOperItemRelManager ctOperItemRelManager;

	private String ctLdId;

	private CtItem entity;

	/**
	 * @return the ctLdId
	 */
	public String getCtLdId() {
		return ctLdId;
	}

	/**
	 * @param ctLdId
	 *            the ctLdId to set
	 */
	public void setCtLdId(String ctLdId) {
		this.ctLdId = ctLdId;
	}

	/**
	 * 删除科目
	 * 
	 * @param ctItemId 	     科目ID
	 * @param sequenctNo  序号
	 * @param parentid    父级科目ID
	 * @return 返回JSON格式数据{status:true}或{status:false,errorMsg:""}
	 *         ,status:true操作成功false:操作失败 errorMsg:出现异常信息
	 */
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub

		StringBuffer strBuffer = new StringBuffer("{status:");
		try {

			String ctItemId = Struts2Utils.getParameter("ctItemId");
			String sequenceNo = Struts2Utils.getParameter("sequenceNo");
			String parentId = Struts2Utils.getParameter("parentId");
			// 当前删除科目下的所有子孙节点科目ID
			String ctSubItemIds;

			Map<String, Object> values = new HashMap<String, Object>();

			values.put("ctItemId", ctItemId);
			values.put("parentId", parentId);
			values.put("sequenceNo", Long.valueOf(sequenceNo));

			ctSubItemIds = getItemIds(ctItemId, true);
			if (StringUtils.isNotBlank(ctItemId)) {
				// 第一步删除业态科目表中的数据
				StringBuffer hqlBuffer = new StringBuffer("delete from CtOperItemRel t");
				hqlBuffer.append(" where t.ctItem.ctItemId=:ctItemId");
				if (StringUtils.isNotEmpty(ctSubItemIds)) {

					hqlBuffer.append(" or  t.ctItem.ctItemId in(");
					hqlBuffer.append(ctSubItemIds);
					hqlBuffer.append(")  ");

				}
				Query query = ctOperItemRelManager.getDao().createQuery(hqlBuffer.toString(), values);
				query.executeUpdate();// 删除业态与科目关系数据

				// 第二步 删除科目表中科目
				hqlBuffer = new StringBuffer("delete from CtItem t ");
				hqlBuffer.append("where t.ctItemId=:ctItemId");

				if (StringUtils.isNotEmpty(ctSubItemIds)) {

					hqlBuffer.append(" or t.ctItemId in(");
					hqlBuffer.append(ctSubItemIds);
					hqlBuffer.append(")  ");
				}

				query = itemManager.getDao().createQuery(hqlBuffer.toString(), values);

				query.executeUpdate();// 删除业态所对应的科目表

				// 第3步 更新兄弟科目的序号，当前科目的后面的兄弟的节点序号前移一个
				hqlBuffer = new StringBuffer("update CtItem  t set  t.sequenceNo = t.sequenceNo-1  ");
				hqlBuffer.append("where t.parentId=:parentId and t.sequenceNo > :sequenceNo");

				query = itemManager.getDao().createQuery(hqlBuffer.toString(), values);
				query.executeUpdate();// 更新兄弟的科目序号
				strBuffer.append("true}");
			}

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
		return null;
	}

	/**
	 * 获取当前科目节点下的，子科目列表
	 * @param pItemId  父级科目Id
	 * @param isFirst 是否首次节点
	 * @return 返回科目IDS
	 */
	public String getItemIds(String pItemId, boolean isFirst) {
		boolean _isFirst = isFirst;
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("parentId", pItemId);
		StringBuffer hqlBuffer = new StringBuffer(" from CtItem t where (");
		hqlBuffer.append("  t.parentId=:parentId");
		hqlBuffer.append(" ) order by sequenceNo asc");
		List<CtItem> itemList = itemManager.getDao().find(hqlBuffer.toString(), values);
		StringBuffer subItemsBuffer = new StringBuffer("");
		for (int i = 0; i < itemList.size(); i++) {

			CtItem itemInstance = itemList.get(i);
			if (i > 0) {

				subItemsBuffer.append(",");
			}
			_isFirst = false;
			subItemsBuffer.append("'");
			subItemsBuffer.append(itemInstance.getCtItemId());
			subItemsBuffer.append("'");

			subItemsBuffer.append(getSubItemIds(itemInstance.getCtItemId(), _isFirst));
		}
		return subItemsBuffer.toString();
	}

	/**
	 * 获取子科目IDS	
	 * @param pItemId 上级科目ID
	 * @param isFirst 是否初次往buffer中增加值
	 * @return 返回科目IDS
	 */
	public String getSubItemIds(String pItemId, boolean isFirst) {
		boolean _isFirst = isFirst;
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("parentId", pItemId);
		StringBuffer hqlBuffer = new StringBuffer(" from CtItem t where (");
		hqlBuffer.append("  t.parentId=:parentId");
		hqlBuffer.append(" ) order by sequenceNo asc");
		List<CtItem> itemList = itemManager.getDao().find(hqlBuffer.toString(), values);
		StringBuffer subItemsBuffer = new StringBuffer("");
		for (int i = 0; i < itemList.size(); i++) {

			CtItem itemInstance = itemList.get(i);
			if (isFirst) {

				subItemsBuffer.append(",");
			}
			_isFirst = false;
			subItemsBuffer.append("'");
			subItemsBuffer.append(itemInstance.getCtItemId());
			subItemsBuffer.append("'");

			subItemsBuffer.append(getSubItemIds(itemInstance.getCtItemId(), _isFirst));
		}
		return subItemsBuffer.toString();
	}

	/**
	 * @return the entity
	 */
	public CtItem getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(CtItem entity) {
		this.entity = entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = itemManager.getEntity(getId());

		} else {
			entity = new CtItem();
		}
	}

	@Override
	public CtItem getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	/**
	 * 更新科目
	 * @param ctItemId 科目ID
	 * @param itemName 科目名称
	 * @param ctLedgerId 台账ID
	 * @return 返回JSON数据 status:true成功,false失败,errorMsg：异常信息
	 */
	public String save() throws Exception {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {

			String ctItemId = Struts2Utils.getParameter("ctItemId");

			String itemName = Struts2Utils.getParameter("itemName");
			String ctLedgerId = Struts2Utils.getParameter("ctLedgerId");

			Map<String, Object> values = new HashMap<String, Object>();
			values.put("ctItemId", ctItemId);
			values.put("itemName", itemName);
			values.put("ctLedgerId", ctLedgerId);

			StringBuffer hqlBuffer = new StringBuffer(" update CtItem t set t.itemName=:itemName where (");
			hqlBuffer.append("  t.ctItemId=:ctItemId ");
			hqlBuffer.append("  and t.ctLedger.ctLedgerId=:ctLedgerId )");

			Query query = itemManager.getDao().createQuery(hqlBuffer.toString(), values);
			// 更新
			int result = query.executeUpdate();
			if (0 < result) {
				strBuffer.append("true}");
			} else {
				strBuffer.append("false,errorMsg:\"科目更新失败！\"}");

			}

		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
		return null;
	}

	/**
	 *  科目排序
	 *@param ctItemId 科目ID
	 *@param parentId 父级科目Id
	 *@param ctLedgerId 台账ID
	 *@param sequenceNo 科目原始序号
	 *@param newSqequenceNo 新科目序号
	 *@param isAsc 排序类型  true 升序 ,false 降序
	 *@return 返回JSON数据 status:true成功,false失败,errorMsg：异常信息
	**/
	public void move() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {

			String ctItemId = Struts2Utils.getParameter("ctItemId");

			String parentId = Struts2Utils.getParameter("parentId");
			String ctLedgerId = Struts2Utils.getParameter("ctLedgerId");
			Long sequenceNo = Long.valueOf(Struts2Utils.getParameter("sequenceNo"));
			Long newSequenceNo = Long.valueOf(Struts2Utils.getParameter("newSequenceNo"));
			String isAsc = Struts2Utils.getParameter("isAsc");
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("ctItemId", ctItemId);
			values.put("parentId", parentId);
			values.put("ctLedgerId", ctLedgerId);
			values.put("sequenceNo", sequenceNo);
			values.put("newSequenceNo", newSequenceNo);

			StringBuffer hqlBuffer = new StringBuffer(" update CtItem t set t.sequenceNo=:newSequenceNo where (");
			hqlBuffer.append("  t.ctItemId=:ctItemId ");
			hqlBuffer.append(" and t.sequenceNo=:sequenceNo ");
			hqlBuffer.append("  and t.ctLedger.ctLedgerId=:ctLedgerId )");

			Query query = itemManager.getDao().createQuery(hqlBuffer.toString(), values);
			// 更新
			int result2 = query.executeUpdate();
			if (0 < result2) {
				strBuffer.append("true}");

				hqlBuffer = new StringBuffer(" update CtItem t set");

				if (isAsc.equals("true")) {// 升序

					hqlBuffer.append(" t.sequenceNo=:sequenceNo where (");
					hqlBuffer.append("  t.parentId=:parentId ");
					hqlBuffer.append(" and t.sequenceNo=:newSequenceNo ");
					hqlBuffer.append(" and t.ctItemId!=:ctItemId ");
					hqlBuffer.append("  and t.ctLedger.ctLedgerId=:ctLedgerId )");

				} else {// 降序

					hqlBuffer.append(" t.sequenceNo=:sequenceNo where (");
					hqlBuffer.append("  t.parentId=:parentId ");
					hqlBuffer.append(" and t.ctItemId!=:ctItemId ");
					hqlBuffer.append(" and t.sequenceNo=:newSequenceNo ");
					hqlBuffer.append("  and t.ctLedger.ctLedgerId=:ctLedgerId )");
				}

				query = itemManager.getDao().createQuery(hqlBuffer.toString(), values);
				// 更新
				query.executeUpdate();

			} else {
				strBuffer.append("false,errorMsg:\"科目排序失败,原因是此科目前已经被其他人操作或已删除！\"}");

			}

		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"出现异常：%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}
	/**
	 * 查看台账详情
	 * @param ctLdId 台账ID
	 * @return 台账详情页面
	 */
	public String ctOperItmeDetail(){
		ctLdId = Struts2Utils.getParameter("ctLdId");
		return "detail";
	}

	@SuppressWarnings( { "unused", "unchecked" })
	
	/**生成目标成本
	 * @param jsonData 地块、业态JSON数据
	 * @param  isCovert 是否覆盖之前生成的目标成本科目
	 * @return 返回地块、业态所对应的科目
	 */
	public String buildNewCostItem() {
		String jsonStr = Struts2Utils.getParameter("jsonData");
		String isCover = Struts2Utils.getParameter("isCovert");
		JSONArray array = JSONArray.fromObject(jsonStr);
		List landAndOperationList = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			landAndOperationList.add(JSONObject.toBean(jsonObject, CtLandAndOperationParas.class));
		}
		List<CtItemTemplate> itemTempList = itemTemplateManager.getAll("itemLevel", true);
		String itemName = null;
		CtLedger ledgerInstance = null;

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String ctLedgerId = "";
		try {

			String ctOperationId;
			String ctLandId;
			Map<String, String> ctItemMap = new LinkedMap();
			List<CtItem> insertItemList = new ArrayList<CtItem>();
			List<CtOperItemRel> insertOperItemRelLit = new ArrayList<CtOperItemRel>();
			for (int n = 0; n < landAndOperationList.size(); n++) {

				CtLandAndOperationParas landAndOperationParams = (CtLandAndOperationParas) landAndOperationList.get(n);
				ctLedgerId = landAndOperationParams.getCtLedgerId();
				ctOperationId = landAndOperationParams.getCtOperationId();
				ctLandId = landAndOperationParams.getCtLandId();

				if (StringUtils.isBlank(ctOperationId) || StringUtils.isBlank(ctLedgerId)) {
					continue;
				}
				ledgerInstance = new CtLedger();
				ledgerInstance.setCtLedgerId(ctLedgerId);

				CtOperation operationInstance = new CtOperation();
				operationInstance.setCtOperationId(ctOperationId);

				for (int i = 0; i < itemTempList.size(); i++) {

					entity = new CtItem();
					CtItemTemplate singleItemTemp = itemTempList.get(i);
					String Cd = singleItemTemp.getCtDimeCd();

					entity.setCtDimeCd(singleItemTemp.getCtDimeCd());

					entity.setSequenceNo(singleItemTemp.getSequenceNo());
					entity.setItemLevel(singleItemTemp.getItemLevel());
					entity.setItemName(singleItemTemp.getItemName());
					if (StringUtils.isBlank(singleItemTemp.getParentId())) {
						entity.setParentId(null);
					} else {
						String p = singleItemTemp.getParentId();
						entity.setParentId(ctItemMap.get(ctOperationId + "," + singleItemTemp.getParentId()));
					}

					entity.setTargetAmt(null);

					entity.setCtItemTemplateId(singleItemTemp.getCtItemTemplateId());
					entity.setCtItemTemplatePid(singleItemTemp.getParentId());
					entity.setCtLedger(ledgerInstance);
					entity.setRecordVersion(singleItemTemp.getRecordVersion());
					// 生成目标成本
					// itemManager.saveCtItem(entity);
					// 增加科目索引键，用于更新科目模板中的相应父级节点ID
					String key = ctOperationId + "," + singleItemTemp.getCtItemTemplateId();
					String v = entity.getCtItemId();
					ctItemMap.put(key, v);

					insertItemList.add(entity);

					// 增加地块与业态、目标成本之间的关系
					CtOperItemRel operItemRelInstance = new CtOperItemRel();
					operItemRelInstance.setCtItem(entity);
					operItemRelInstance.setCtLandId(ctLandId);
					operItemRelInstance.setCtLedgerId(ctLedgerId);
					operItemRelInstance.setCtOperation(operationInstance);

					insertOperItemRelLit.add(operItemRelInstance);

				}
			}

			ctItemMap.clear();

			int count = insertItemList.size();
			// 如果用户选择了覆盖科目信息
			if (StringUtils.isNotBlank(isCover) && isCover.equals("true")) {
				Map<String, Object> values = new HashMap<String, Object>();

				values.put("ctLedgerId", ctLedgerId);
				// 第一步
				StringBuffer delBuffer = new StringBuffer("delete from CtOperItemRel t ");
				delBuffer.append(" where t.ctLedgerId=:ctLedgerId");
				Query query = ctOperItemRelManager.getDao().createQuery(delBuffer.toString(), values);
				query.executeUpdate();

				// 第二步
				delBuffer = new StringBuffer("delete from CtItem t  ");
				delBuffer.append(" where t.ctLedger.ctLedgerId=:ctLedgerId");
				query = itemManager.getDao().createQuery(delBuffer.toString(), values);
				query.executeUpdate();

			}
			// 插入数据
			Session session = itemManager.getDao().getSession();
			Session operSession = ctOperItemRelManager.getDao().getSession();
			int submitTime = count % 100;// 获取插入记录个数
			for (int i = 0; i < count; i++) {

				CtItem item = insertItemList.get(i);

				if (!StringUtils.isBlank(item.getCtItemTemplatePid())) {
					String p = item.getCtItemTemplatePid();
					item.setParentId(ctItemMap.get(p));
				}
				session.saveOrUpdate(item);
				ctItemMap.put(item.getCtItemTemplateId(), item.getCtItemId());
				if (i % 100 == 0) { // 每100条提交一次数据
					session.flush();
					session.clear();
				} else if (submitTime > 0 && i == count - 1) {
					// 如果余数不为0则说明需要多提交一次数据，否则数据则会少生成数据
					session.flush();
					session.clear();
				}

			}

			for (int i = 0; i < count; i++) {

				CtOperItemRel operItemRel = insertOperItemRelLit.get(i);
				operSession.saveOrUpdate(operItemRel);
				if (i % 100 == 0) {
					operSession.flush();
					operSession.clear();
				} else if (submitTime > 0 && i == count - 1) {
					operSession.flush();
					operSession.clear();
				}

			}

			Struts2Utils.renderJson(landAndOperationList);
			landAndOperationList.clear();
		} catch (Exception ee) {
			ee.printStackTrace();

		}

		return null;
	}


	@SuppressWarnings( { "unchecked", "cast", "null" })
	
	/** 构建目标成本树型数据
	 * @param ctItemId 科目ID
	 * @param ctLedgerId 成本台账ID
	 * @param ctOperationId 业态ID
	 * @return  成本科目树型菜单JSON数据
	 */
	public void getCostItem() {

		String itemid = Struts2Utils.getParameter("ctItemId");
		String lederId = Struts2Utils.getParameter("ctLedgerId");
		String ctOperationId = Struts2Utils.getParameter("ctOperationId");
		try {
			Map<String, Object> values = new HashMap<String, Object>();

			values.put("ctOperationId", ctOperationId);
			values.put("lederId", lederId);
			values.put("ctItemId", itemid);
			StringBuffer hql = new StringBuffer("select coi.ct_item_id ");
			hql.append("from ct_oper_item_rel coi ");
			hql.append("where coi.ct_operation_id='");

			hql.append(ctOperationId);
			hql.append("'");

			StringBuffer hqlBuffer = new StringBuffer("select t.ct_item_id  as ctItemId,");
			hqlBuffer.append("t.ct_dime_cd as ctDimeCd,");
			hqlBuffer.append("to_char(NVL(t.sequence_no,'')) as sequenceNo,");
			hqlBuffer.append("to_char(NVL(t.item_level,'')) as itemLevel,");
			hqlBuffer.append("t.item_name as itemName,");
			hqlBuffer.append("t.parent_id as parentId,");
			hqlBuffer.append("t.target_amt as targetAmt,");
			// hqlBuffer.append("t.ct_item_template_id as ctItemTemplateId ,");
			// hqlBuffer.append("t.ct_item_template_pid as ctItemTemplatePid,");
			hqlBuffer.append("t.ct_ledger_id as ctLedgerId,");
			hqlBuffer.append("t.ct_cont_plan_id as ctContPlanId,");
			hqlBuffer.append("cp.ledger_type_cd as contractPlanName, ");
			hqlBuffer.append("to_char(NVL(t.record_version,'0')) as recordVersion, ");
			hqlBuffer.append("t.remark as remark, ");
			hqlBuffer.append("to_char((select count(*) from ct_item s where s.parent_id = t.ct_item_id ))as isParent ");
			hqlBuffer.append(" from ct_item t ");
			hqlBuffer.append("left join ");
			hqlBuffer.append("ct_cont_plan cp ");
			hqlBuffer.append("on t.ct_cont_plan_id = cp.ct_cont_plan_id ");
			hqlBuffer.append(" where 1=1 ");

			hqlBuffer.append(" and t.ct_item_id in(");
			hqlBuffer.append(hql.toString());
			hqlBuffer.append(")");

			hqlBuffer.append(" and (");

			if (StringUtils.isBlank(itemid)) {
				hqlBuffer.append(" t.parent_id is null");
			} else {

				hqlBuffer.append(" t.parent_id='" + itemid + "'");
			}
			hqlBuffer.append(" )");
			hqlBuffer.append("  order by t.sequence_no asc");
			Query query = itemManager.getDao().createSQLQuery(hqlBuffer.toString(), values);
			query.setResultTransformer(new AliasToBeanResultTransformer(CtAliasToBeanItemJson.class));

			List<CtAliasToBeanItemJson> aliasToBeanItemjsonList = query.list();

			List<CtOperationItemTreeGridJson> strJsonList = new ArrayList<CtOperationItemTreeGridJson>();

			if (aliasToBeanItemjsonList != null) {

				for (int i = 0; i < aliasToBeanItemjsonList.size(); i++) {
					CtAliasToBeanItemJson itemInstan = (CtAliasToBeanItemJson) aliasToBeanItemjsonList.get(i);

					CtOperationItemTreeGridJson treeGridJson = new CtOperationItemTreeGridJson();

					treeGridJson.setCtItemId(itemInstan.getCTITEMID());
					treeGridJson.setCtLedgerId(itemInstan.getCTLEDGERID());
					treeGridJson.setCtDimeCd(itemInstan.getCTDIMECD());
					if (StringUtils.isNotBlank(itemInstan.getITEMLEVEL())) {
						treeGridJson.setItemLevel(Long.valueOf(itemInstan.getITEMLEVEL()));
					}

					treeGridJson.setItemName(itemInstan.getITEMNAME());
					if (StringUtils.isNotBlank(itemInstan.getSEQUENCENO())) {
						treeGridJson.setSequenceNo(Long.valueOf(itemInstan.getSEQUENCENO()));
					}

					treeGridJson.setParentId(itemInstan.getPARENTID());
					treeGridJson.setCtOperationId(ctOperationId);

					treeGridJson.setRecordVersion(Long.valueOf(itemInstan.getRECORDVERSION()));
					if (itemInstan.getTARGETAMT() == null) {
						treeGridJson.setTargetAmt(BigDecimal.valueOf(-1));

					} else {

						treeGridJson.setTargetAmt(itemInstan.getTARGETAMT());
					}
					treeGridJson.setSequenceText(itemInstan.getSEQUENCENO());
					treeGridJson.setContPlanName(itemInstan.getCONTRACTPLANNAME());
					treeGridJson.setCtContPlanId(itemInstan.getCTCONTPLANID());
					values = new HashMap<String, Object>();
					values.put("lederId", lederId);
					values.put("ctItemId", itemInstan.getCTITEMID());
					/*
					 * if (treeGridJson.getItemLevel() > 2) {
					 * 
					 * treeGridJson.setChecked("true"); }
					 */
					String sequen = itemInstan.getSEQUENCENO();
					if (!itemInstan.getISPARENT().equals("0")) {
						treeGridJson.setState("closed");

						if (treeGridJson.getItemLevel() == 1) {
							sequen = "";
						}
						treeGridJson.setChildren(setChildren(ctOperationId, lederId, itemInstan.getCTITEMID(), treeGridJson.getItemLevel(), sequen));
					}

					treeGridJson.setIconCls("icon-ok");
					strJsonList.add(treeGridJson);
				}

				Struts2Utils.renderJson(strJsonList);

			}
			aliasToBeanItemjsonList.clear();
			strJsonList.clear();
		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}

	@SuppressWarnings( { "cast", "unchecked", "null" })
	private List<CtOperationItemTreeGridJson> setChildren(String ctOperationId, String lederId, String parentId, Long parentLevel, String sequenceText) {

		List<CtOperationItemTreeGridJson> children = new ArrayList<CtOperationItemTreeGridJson>();
		String sequence = sequenceText;
		try {
			Map<String, Object> values = new HashMap<String, Object>();

			values.put("ctOperationId", ctOperationId);
			values.put("lederId", lederId);
			values.put("ctItemId", parentId);
			StringBuffer hql = new StringBuffer("select coi.ct_item_id ");
			hql.append("from ct_oper_item_rel coi ");
			hql.append("where coi.ct_operation_id='");

			hql.append(ctOperationId);
			hql.append("'");

			StringBuffer hqlBuffer = new StringBuffer("select t.ct_item_id  as ctItemId,");
			hqlBuffer.append("t.ct_dime_cd as ctDimeCd,");
			hqlBuffer.append("to_char(NVL(t.sequence_no,'')) as sequenceNo,");
			hqlBuffer.append("to_char(NVL(t.item_level,'')) as itemLevel,");
			hqlBuffer.append("t.item_name as itemName,");
			hqlBuffer.append("t.parent_id as parentId,");
			hqlBuffer.append("t.target_amt as targetAmt,");
			hqlBuffer.append("t.ct_item_template_id as ctItemTemplateId ,");
			hqlBuffer.append("t.ct_item_template_pid as ctItemTemplatePid,");
			hqlBuffer.append("t.ct_ledger_id as ctLedgerId,");
			hqlBuffer.append("t.ct_cont_plan_id as ctContPlanId,");
			hqlBuffer.append("cp.ledger_type_cd as contractPlanName, ");
			hqlBuffer.append("to_char(NVL(t.record_version,'0')) as recordVersion, ");
			hqlBuffer.append("t.remark as remark, ");
			hqlBuffer.append("to_char((select count(*) from ct_item s where s.parent_id = t.ct_item_id ))as isParent ");
			hqlBuffer.append(" from ct_item t ");
			hqlBuffer.append("left join ");
			hqlBuffer.append("ct_cont_plan cp ");
			hqlBuffer.append("on t.ct_cont_plan_id = cp.ct_cont_plan_id ");
			hqlBuffer.append(" where 1=1 ");

			hqlBuffer.append(" and t.ct_item_id in(");
			hqlBuffer.append(hql.toString());
			hqlBuffer.append(")");

			hqlBuffer.append(" and (");

			if (StringUtils.isBlank(parentId)) {
				hqlBuffer.append(" t.parent_id is null");
			} else {

				hqlBuffer.append(" t.parent_id='" + parentId + "'");
			}
			hqlBuffer.append(" )");
			hqlBuffer.append("  order by t.sequence_no asc");
			Query query = itemManager.getDao().createSQLQuery(hqlBuffer.toString(), values);
			query.setResultTransformer(new AliasToBeanResultTransformer(CtAliasToBeanItemJson.class));

			List<CtAliasToBeanItemJson> aliasToBeanItemjsonList = query.list();

			if (aliasToBeanItemjsonList != null) {

				for (int i = 0; i < aliasToBeanItemjsonList.size(); i++) {
					CtAliasToBeanItemJson itemInstan = (CtAliasToBeanItemJson) aliasToBeanItemjsonList.get(i);

					CtOperationItemTreeGridJson subTreeGridJson = new CtOperationItemTreeGridJson();

					subTreeGridJson.setCtItemId(itemInstan.getCTITEMID());
					subTreeGridJson.setCtLedgerId(itemInstan.getCTLEDGERID());
					subTreeGridJson.setCtDimeCd(itemInstan.getCTDIMECD());
					if (StringUtils.isNotBlank(itemInstan.getITEMLEVEL())) {
						subTreeGridJson.setItemLevel(Long.valueOf(itemInstan.getITEMLEVEL()));
					}

					subTreeGridJson.setItemName(itemInstan.getITEMNAME());
					if (StringUtils.isNotBlank(itemInstan.getSEQUENCENO())) {
						subTreeGridJson.setSequenceNo(Long.valueOf(itemInstan.getSEQUENCENO()));
					}
					if (StringUtils.isNotBlank(sequenceText)) {

						sequence = sequenceText + "." + itemInstan.getSEQUENCENO();

					} else {
						sequence = itemInstan.getSEQUENCENO();

					}
					subTreeGridJson.setSequenceText(sequence);
					subTreeGridJson.setParentId(itemInstan.getPARENTID());
					subTreeGridJson.setCtOperationId(ctOperationId);
					subTreeGridJson.setRecordVersion(Long.valueOf(itemInstan.getRECORDVERSION()));
					if (itemInstan.getTARGETAMT() == null) {
						subTreeGridJson.setTargetAmt(BigDecimal.valueOf(-1));

					} else {

						subTreeGridJson.setTargetAmt(itemInstan.getTARGETAMT());
					}

					subTreeGridJson.setContPlanName(itemInstan.getCONTRACTPLANNAME());
					subTreeGridJson.setCtContPlanId(itemInstan.getCTCONTPLANID());
					values = new HashMap<String, Object>();
					values.put("lederId", lederId);
					values.put("ctItemId", itemInstan.getCTITEMID());
					if (subTreeGridJson.getItemLevel() > 2) {

						subTreeGridJson.setChecked("true");
					}
					if (!itemInstan.getISPARENT().equals("0")) {

						subTreeGridJson.setState("closed");
						if (subTreeGridJson.getItemLevel() - parentLevel < 3) {
							subTreeGridJson
									.setChildren(setChildren(ctOperationId, lederId, itemInstan.getCTITEMID(), subTreeGridJson.getItemLevel(), sequence));

						}
					}

					subTreeGridJson.setIconCls("icon-ok");
					children.add(subTreeGridJson);
				}

			}
			aliasToBeanItemjsonList.clear();
		} catch (Exception ee) {
			return null;
		}
		return children;
	}

	/**
	 * 提交交发布新的版本
	 * @param ctLedgerId 台账ID
	 * @param recordVerison 当前版本
	 * @param isPubNewVersion 是否发布
	 *@return 返回JSON数据 status:true成功,false失败,errorMsg：异常信息
	 */
	public void doPublishNewVersion() {

		String lederId = Struts2Utils.getParameter("ctLedgerId");
		String recordVerison = Struts2Utils.getParameter("recordVerison");
		String isPubNewVersion = Struts2Utils.getParameter("isPubNewVersion");
		StringBuffer strBuffer = new StringBuffer("{status:");

		try {
			if (StringUtils.isBlank(recordVerison)) {
				recordVerison = "0";
			}
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("lederId", lederId);
			values.put("recordVerison", Long.valueOf(recordVerison));
			values.put("newRecordVersion", Long.valueOf(recordVerison) + 1);
			values.put("ledgerStatus", "1");
			StringBuffer hql = new StringBuffer("");
			int count = 0;
			if (StringUtils.isNotBlank(isPubNewVersion) && isPubNewVersion.equals("true")) {
				// 如果提交时需要生成新的版本到历史记录中
				hql = new StringBuffer("INSERT INTO ct_item_history");
				hql.append("(CT_ITEM_HISTORY_ID,");
				hql.append("CT_ITEM_ID,CT_DIME_CD,SEQUENCE_NO,ITEM_LEVEL,");
				hql.append("ITEM_NAME,PARENT_ID,TARGET_AMT,CT_ITEM_TEMPLATE_ID,");
				hql.append("CT_ITEM_TEMPLATE_PID,REMARK,CREATOR,");
				hql.append("CREATED_CENTER_CD,CREATED_POSITION_CD,");
				hql.append("CREATED_DATE,UPDATOR,UPDATED_CENTER_CD,");
				hql.append("UPDATED_POSITION_CD,UPDATED_DATE,RECORD_VERSION,");
				hql.append("CT_LEDGER_ID,CT_CONT_PLAN_ID");
				hql.append(")(");
				hql.append("select ");
				hql.append("CONCAT(CT_ITEM_ID,to_char(sysdate, 'yymmddhhmmss')),");
				hql.append("CT_ITEM_ID,CT_DIME_CD,SEQUENCE_NO,ITEM_LEVEL,");
				hql.append("ITEM_NAME,PARENT_ID,TARGET_AMT,CT_ITEM_TEMPLATE_ID,");
				hql.append("CT_ITEM_TEMPLATE_PID,REMARK,CREATOR,");
				hql.append("CREATED_CENTER_CD,CREATED_POSITION_CD,");
				hql.append("CREATED_DATE,UPDATOR,UPDATED_CENTER_CD,");
				hql.append("UPDATED_POSITION_CD,UPDATED_DATE,RECORD_VERSION,");
				hql.append("CT_LEDGER_ID,CT_CONT_PLAN_ID");
				hql.append(" from CT_ITEM t");
				hql.append(" WHERE t.CT_LEDGER_ID=:lederId");
				hql.append(" and t.RECORD_VERSION=:recordVerison");
				hql.append(")");
				Query query = itemManager.getDao().createSQLQuery(hql.toString(), values);
				count = query.executeUpdate();
				hql = new StringBuffer("UPDATE");

				hql.append("  CtItem t");
				hql.append(" SET t.recordVersion=:newRecordVersion");
				hql.append(" WHERE t.ctLedger.ctLedgerId=:lederId");
				hql.append(" and t.recordVersion=:recordVerison");
				hql.append(")");
				query = itemManager.getDao().createQuery(hql.toString(), values);
				query.executeUpdate();
			}
          	CtLedger ctLedger=ledgerManager.getEntity(lederId);
         // 更新CtLedger的状态1.提交，2.审核，3.驳回
			ctLedger.setLedgerStatus("1");
			
			ledgerManager.saveCtLedger(ctLedger);
			/*
			hql = new StringBuffer("UPDATE");

			hql.append("  CtLedger t");
			hql.append(" SET t.ledgerStatus=:ledgerStatus");
			hql.append(" WHERE t.ctLedgerId=:lederId");

			hql.append(")");
			Query query = itemManager.getDao().createQuery(hql.toString(), values);
			query.executeUpdate();*/
			strBuffer.append("true,result:" + count + "}");

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"出现异常：%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	/**
	 * 更新科目成本
	 * @param ctItemId 科目ID
	 * @param targetAmt	目标成本
	 * @return 返回JSON数据 status:true成功,false失败,errorMsg：异常信息
	 */
	public void updateCostItem() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			String ctItemId = Struts2Utils.getParameter("ctItemId");
			String targetAmt = Struts2Utils.getParameter("targetAmt");
			BigDecimal targetAmtB = null;

			if (targetAmt.indexOf('.') > -1) {
				targetAmtB = BigDecimal.valueOf(Double.valueOf(targetAmt));
			} else {
				targetAmtB = BigDecimal.valueOf(Long.valueOf(targetAmt));
			}
			if (StringUtils.isNotBlank(ctItemId)) {
				CtItem itemInstan = itemManager.getEntity(ctItemId);
				itemInstan.setTargetAmt(targetAmtB);
				// 更新成本科目
				itemManager.saveCtItem(itemInstan);				
				strBuffer.append("true}");

			} else {
				strBuffer.append(String.format("false,errorMsg:\"请确认更新的科目编号是否正确！\""));
			}

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
	}

	/**
	 * 增加科目
	 * @param ctLedgerId 台账ID
	 * @param ctLandId 地块
	 * @param parentId 父级科目ID
	 * @param itemName 科目名称
	 * @param itemLevel 科目级别 (1,2,3,4,5..n 级科目)
	 * @param ctDimeCd 维度(这个字段原台需求上要求，之后版本暂时未用到)
	 * @param sequenceNo 序号
	 * @param ctRecordVersion 记录版本号
	 * @return 返回JSON数据 status:true成功,false失败,errorMsg：异常信息
	 */
	public void addCostItem() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {
			entity = new CtItem();

			CtLedger ledgerInstance = new CtLedger();
			String ctLedgerId = Struts2Utils.getParameter("ctLedgerId");
			String ctLandId = Struts2Utils.getParameter("ctLandId");
			String parentId = Struts2Utils.getParameter("parentId");
			String ctOperationId = Struts2Utils.getParameter("ctOperationId");			
			String itemName = Struts2Utils.getParameter("itemName");
			String itemLevel = Struts2Utils.getParameter("itemLevel");
			String ctDimeCd = Struts2Utils.getParameter("ctDimeCd");
			String sequenceNo = Struts2Utils.getParameter("sequenceNo");
			String ctRecordVersion = Struts2Utils.getParameter("ctRecordVersion");
			
			ledgerInstance.setCtLedgerId(ctLedgerId);
			if (StringUtils.isBlank(ctRecordVersion)) {
				ctRecordVersion = "0";
			}
			entity.setCtDimeCd(ctDimeCd);
			entity.setSequenceNo(Long.parseLong(sequenceNo));
			entity.setItemLevel(Long.valueOf(itemLevel));
			entity.setItemName(itemName);
			entity.setParentId(parentId);
			entity.setTargetAmt(null);

			entity.setCtLedger(ledgerInstance);
			entity.setRecordVersion(Long.valueOf(ctRecordVersion));
			itemManager.saveCtItem(entity);

			CtOperation operationInstance = new CtOperation();
			operationInstance.setCtOperationId(ctOperationId);

			// 增加地块与业态、目标成本之间的关系
			CtOperItemRel operItemRelInstance = new CtOperItemRel();
			operItemRelInstance.setCtItem(entity);
			operItemRelInstance.setCtLandId(ctLandId);
			operItemRelInstance.setCtLedgerId(ctLedgerId);
			operItemRelInstance.setCtOperation(operationInstance);
			ctOperItemRelManager.saveCtOperItemRel(operItemRelInstance);

			strBuffer.append("true}");

		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));
			ee.printStackTrace();
		}
		Struts2Utils.renderText(strBuffer.toString());
	}

	@SuppressWarnings("unchecked")
	/**
	 * 获取未与合约规划关系的科目和未被关联的合约规划
	 * @param ctLedgerId 台账ID
	 * @param recordVerison  记录版本号
     * @return 返回未联的合约规划JSON数据
	 */
	public void getItemAndConNoRelationInfo() {

		String ctLedgerId = Struts2Utils.getParameter("ctLedgerId");
		String recordVersion = Struts2Utils.getParameter("recordVerison");
		StringBuffer strBuffer = new StringBuffer("{status:");

		try {
			if (StringUtils.isBlank(recordVersion)) {

				recordVersion = "0";
			}
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("ctLedgerId", ctLedgerId);
			values.put("recordVersion", Long.valueOf(recordVersion));
			StringBuffer hql = new StringBuffer("select cl.landName as parentId,");
			hql.append("co.operTypeName as itemLevel, t.ctItemId,");
			hql.append("t.itemName ");
			hql.append("from CtItem t,CtOperItemRel coir,CtOperation co,");
			hql.append("CtLand cl");
			hql.append(" where t.ctLedger.ctLedgerId =:ctLedgerId");
			hql.append(" and t.ctItemId =coir.ctItem.ctItemId");
			hql.append(" and coir.ctOperation.ctOperationId =co.ctOperationId");
			hql.append(" and co.ctLand.ctLandId =cl.ctLandId");
			hql.append(" and t.recordVersion=:recordVersion and t.ctContPlan.ctContPlanId is null");
			hql.append(" order by t.parentId asc,t.itemLevel asc");
			List<CtItem> itemList = itemManager.find(hql.toString(), values);
			StringBuffer jsonData = new StringBuffer("[{items:[");
			jsonData.append(JSONArray.fromObject(itemList).toString());
			jsonData.append("],contPlans:[");

			hql = new StringBuffer("select distinct t.ctContPlanId,t.ledgerTypeCd from CtContPlan t,CtPlan cp");
			hql.append(" where t.ctPlan.ctPlanId = cp.ctPlanId");
			hql.append(" and cp.ctLedger.ctLedgerId=:ctLedgerId");

			hql.append(" and t.ctContPlanId  not in (select ct.ctContPlan.ctContPlanId  ");
			hql.append(" from CtItem ct  ");
			hql.append(" where ct.ctLedger.ctLedgerId =:ctLedgerId");
			hql.append(" and ct.recordVersion=:recordVersion and ct.ctContPlan.ctContPlanId is not null )");
			Query query = itemManager.getDao().createQuery(hql.toString(), values);
			List<CtContPlan> contPlanList = query.list();
			jsonData.append(JSONArray.fromObject(contPlanList).toString());
			jsonData.append("]}]");
			Struts2Utils.renderText(jsonData.toString());
			return;
		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());

	}

	public String modificationInput() throws Exception {
		// TODO Auto-generated method stub
		return "modification-input";
	}
}

/**
 * 
 */
package com.hhz.ump.web.ct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.ct.CtItemManager;
import com.hhz.ump.dao.ct.CtLandManager;
import com.hhz.ump.dao.ct.CtOperItemRelManager;
import com.hhz.ump.dao.ct.CtOperationManager;
import com.hhz.ump.entity.ct.CtAliasToBeanLandAndOperationJson;
import com.hhz.ump.entity.ct.CtLand;
import com.hhz.ump.entity.ct.CtLandAndOperationParas;
import com.hhz.ump.entity.ct.CtLandJson;
import com.hhz.ump.entity.ct.CtLedger;
import com.hhz.ump.entity.ct.CtOperItemRel;

/**
 * @author qlb 12/09/2011
 * @version 1.0
 * 
 * 
 */
public class CtLandAction extends CrudActionSupport<CtLand> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3029666883240261366L;

	private CtLand entity;

	// 科目表 sevivce
	@Autowired
	CtItemManager itemManager;

	@Autowired
	private CtLandManager ctLandManager;
	// 业态科目表serivce
	@Autowired
	private CtOperItemRelManager ctOperItemRelManager;
	@Autowired
	private CtOperationManager ctOperationManager;

	/**
	 * @return the entity
	 */
	public CtLand getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(CtLand entity) {
		this.entity = entity;
	}

	/**
	 * 删除地块
	 * @param ctLandid 地块ID
	 * @param ctLederid 台账ID
	 * @return 返回JSON数据 status:true成功,false失败,errorMsg：异常信息
	 */
	@Override
	public String delete() throws Exception {

		String ctLandId = Struts2Utils.getParameter("ctLandid");
		String ctLederid = Struts2Utils.getParameter("ctLederid");
		StringBuffer strBuffer = new StringBuffer("{status:");

		StringBuffer itemIds = new StringBuffer("");// 业态所对应的科目
		try {
			StringBuffer hqlBuffer = new StringBuffer("from CtOperation t where t.ctLand.ctLandId=:landId");// new
		

			Map<String, Object> values = new HashMap<String, Object>();
			values.put("ctLederId", ctLederid);
			values.put("landId", ctLandId);
			hqlBuffer = new StringBuffer("from CtOperItemRel t");
			hqlBuffer.append(" where t.ctLandId=:landId");
			List<CtOperItemRel> operItemRelList = ctOperItemRelManager.find(hqlBuffer.toString(), values);

			// 获取地块业态所对应的所有科目
			for (int i = 0; i < operItemRelList.size(); i++) {

				if (i > 0) {
					itemIds.append(",");

				}
				itemIds.append("'" + operItemRelList.get(i).getCtItem().getCtItemId() + "'");
			}
			operItemRelList.clear();

			// 第一步
			hqlBuffer = new StringBuffer("delete from CtOperItemRel t");
			hqlBuffer.append(" where t.ctLandId=:landId");
			Query query = ctOperItemRelManager.getDao().createQuery(hqlBuffer.toString(), values);
			query.executeUpdate();// 删除业态与科目关系数据
			// 第二步
			if (StringUtils.isNotEmpty(itemIds.toString())) {
				hqlBuffer = new StringBuffer("delete from CtItem t where t.ctItemId");
				hqlBuffer.append(" in(");
				hqlBuffer.append(itemIds);
				hqlBuffer.append(")");

				query = itemManager.getDao().createQuery(hqlBuffer.toString(), values);
				query.executeUpdate();// 删除业态所对应的科目表
			}

			// 第三步
			hqlBuffer = new StringBuffer("delete from CtOperation t where t.ctLand.ctLandId=:landId");
			query = ctOperationManager.getDao().createQuery(hqlBuffer.toString(), values);
			query.executeUpdate(); // 删除业态表数据
			// 第四步
			hqlBuffer = new StringBuffer("delete from CtLand t where t.ctLandId=:landId");
			query = ctLandManager.getDao().createQuery(hqlBuffer.toString(), values);
			query.executeUpdate(); // 删除地块表数据
			strBuffer.append("true}");

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}

		Struts2Utils.renderText(strBuffer.toString());
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

	// 首面载入时显示地块号
	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub

		String ctLederId = Struts2Utils.getParameter("ctLederId");
		getLandsInfo(ctLederId);
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = ctLandManager.getEntity(getId());

		} else {
			entity = new CtLand();
		}
	}

	/**
	 * 添加、修改地块
	 * @param actType  操作类别  mod修改  add增加
	 * @param ctLandid 地块ID
	 * @param ctLederid 台账ID
	 * @return 返回JSON数据 status:true成功,false失败,errorMsg：异常信息
	 */
	@Override
	public String save() throws Exception {
		StringBuffer strBuffer = new StringBuffer("{status:");
		String actType = Struts2Utils.getParameter("actType");
		String landId = Struts2Utils.getParameter("landId");
		try {
			CtLand landInstance = new CtLand();
			CtLedger ctLeder = new CtLedger();

			String landName = Struts2Utils.getParameter("landName");
			String ctLederId = Struts2Utils.getParameter("ctLederId");
			if (StringUtils.isNotBlank(actType) && actType.equals("mod") && StringUtils.isNotBlank(landId)) {
				landInstance=ctLandManager.getEntity(landId);
			} 
		landInstance.setLandName(landName);
		ctLeder.setCtLedgerId(ctLederId);
		landInstance.setCtLedger(ctLeder);

		ctLandManager.saveCtLand(landInstance);
			
			getLandsInfo(ctLederId);

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));
			Struts2Utils.renderText(strBuffer.toString());
		}

		// TODO Auto-generated method stub
		return null;
	}
 /**
  * 根据总况表台账ID获取对应的地块
  * @param ctLederId 台账ID
  * @return 返回 地块JSON数据
  */
	public void getLandsInfo(String ctLederId) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ctLederId", ctLederId);

		StringBuffer hqlBuffer = new StringBuffer(" from CtLand t where t.ctLedger.ctLedgerId =:ctLederId order by landName");

		List<CtLandJson> landJsonList = new ArrayList<CtLandJson>();
		try {
			List<CtLand> list = ctLandManager.getDao().find(hqlBuffer.toString(), values);

			for (int i = 0; i < list.size(); i++) {

				CtLand ctLand = list.get(i);
				CtLandJson ctLandJson = new CtLandJson();
				ctLandJson.setCtLandId(ctLand.getCtLandId());
				ctLandJson.setCtLedgerId(ctLand.getCtLedger().getCtLedgerId());
				ctLandJson.setLandName(ctLand.getLandName());
				ctLandJson.setRecordVersion(ctLand.getRecordVersion());
				landJsonList.add(ctLandJson);

			}
			list.clear();
		} catch (Exception ee) {

		}
		Struts2Utils.renderJson(landJsonList);
		landJsonList.clear();

	}

	/**
	 * 获取总况表中对应的地块和业态信息
	 * @param ctLederid 台账ID
	 * @return 返回总况表中的地块和为业态JSON信息
	 */
	@SuppressWarnings("unchecked")
	public void getLandAndOperationInfo() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {

			String ctLederId = Struts2Utils.getParameter("ctLederid");
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("ctLederId", ctLederId);

			StringBuffer sqlBuffer = new StringBuffer("select ");
			sqlBuffer.append("l.land_name as landName");
			sqlBuffer.append(",l.ct_land_id as ctLandId");
			sqlBuffer.append(",l.ct_ledger_id as ctLedgerId ");
			sqlBuffer.append(",o.ct_operation_id as ctOperationId");
			sqlBuffer.append(",o.oper_type_name as operName ");
			sqlBuffer.append(" from ct_land l left join ct_operation o ");
			sqlBuffer.append("on o.ct_land_id = l.ct_land_id");
			sqlBuffer.append(" where l.ct_ledger_id ='" + ctLederId + "'");
			sqlBuffer.append(" and o.ct_operation_id in ");
			sqlBuffer.append("( select distinct coi.ct_operation_id from ct_oper_item_rel coi ");
			sqlBuffer.append(" where coi.ct_ledger_id='" + ctLederId + "'");
			sqlBuffer.append(")");
			// sqlBuffer.append(" and o.ct_land_id = l.ct_land_id");
			// sqlBuffer.append(" and o.ct_operation_id in (select t.ct_operation_id from ct_oper_item_rel t )");
			sqlBuffer.append(" order by l.land_name");

			Query query = ctLandManager.getDao().createSQLQuery(sqlBuffer.toString(), values);
			query.setResultTransformer(new AliasToBeanResultTransformer(CtAliasToBeanLandAndOperationJson.class));
			List<CtAliasToBeanLandAndOperationJson> aliastList = query.list();
			List<CtLandAndOperationParas> landAndOperationList = new ArrayList<CtLandAndOperationParas>();

			CtLandAndOperationParas landAndOperationParam;
			for (int i = 0; i < aliastList.size(); i++) {
				CtAliasToBeanLandAndOperationJson aliasJson = aliastList.get(i);

				landAndOperationParam = new CtLandAndOperationParas();

				landAndOperationParam.setCtLandId(aliasJson.getCTLANDID());
				landAndOperationParam.setCtLedgerId(aliasJson.getCTLEDGERID());
				landAndOperationParam.setCtOperationId(aliasJson.getCTOPERATIONID());
				landAndOperationParam.setLandName(aliasJson.getLANDNAME());
				landAndOperationParam.setOperName(aliasJson.getOPERNAME());

				landAndOperationList.add(landAndOperationParam);

			}
			Struts2Utils.renderJson(landAndOperationList);

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));
			Struts2Utils.renderText(strBuffer.toString());
		}
	}

	/**
	 * 获取成本科目状态 (是否已生成本科目)
	 * @param ctLederid 台账ID
	 * @return 返回JSON格式数据{status:true,isHas:""}或{status:false,errorMsg:""}
	 *         ,status:true操作成功false:操作失败 errorMsg:出现异常信息 ,isHas：true已生成，false未生成
	 */
	public void getCostItemSatus() {
		StringBuffer strBuffer = new StringBuffer("{status:");
		try {

			String ctLederId = Struts2Utils.getParameter("ctLederid");
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("ctLederId", ctLederId);

			StringBuffer sqlBuffer = new StringBuffer("select ");
			sqlBuffer.append("l.land_name as landName");
			sqlBuffer.append(",l.ct_land_id as ctLandId");
			sqlBuffer.append(",l.ct_ledger_id as ctLedgerId ");
			sqlBuffer.append(",o.ct_operation_id as ctOperationId");
			sqlBuffer.append(",o.oper_type_name as operName ");
			sqlBuffer.append("from ct_land l,ct_operation o ");
			sqlBuffer.append("where l.ct_ledger_id ='" + ctLederId + "'");
			sqlBuffer.append(" and o.ct_land_id = l.ct_land_id");
			sqlBuffer.append(" and o.ct_operation_id in (select t.ct_operation_id from ct_oper_item_rel t )");
			sqlBuffer.append("order by l.land_name");

			Query query = ctLandManager.getDao().createSQLQuery(sqlBuffer.toString(), values);
			if (query.executeUpdate() > 0) {
				strBuffer.append(String.format("true,isHas:\"%s\"}", "1"));

			} else {

				strBuffer.append(String.format("true,isHas:\"%s\"}", "0"));
			}
			Struts2Utils.renderText(strBuffer.toString());

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));
			Struts2Utils.renderText(strBuffer.toString());
		}
	}

	@Override
	public CtLand getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

}

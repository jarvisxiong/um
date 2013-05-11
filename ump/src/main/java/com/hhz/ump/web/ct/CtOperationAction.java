package com.hhz.ump.web.ct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.ct.CtBaseOperationManager;
import com.hhz.ump.dao.ct.CtItemManager;
import com.hhz.ump.dao.ct.CtLedgerManager;
import com.hhz.ump.dao.ct.CtOperItemRelManager;
import com.hhz.ump.dao.ct.CtOperationManager;
import com.hhz.ump.entity.ct.CtBaseOperation;
import com.hhz.ump.entity.ct.CtLand;
import com.hhz.ump.entity.ct.CtOperItemRel;
import com.hhz.ump.entity.ct.CtOperation;
import com.hhz.ump.entity.ct.CtOperationJson;

@Namespace("/ct")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "ct-operation!list.action", type = "redirect") })
public class CtOperationAction extends CrudActionSupport<CtOperation> {

	private static final long serialVersionUID = -849240083339798922L;

	@Autowired
	private CtLedgerManager ctLedgerManager;
	// 科目表 sevivce
	@Autowired
	CtItemManager itemManager;

	// 业态科目表serivce
	@Autowired
	private CtOperItemRelManager ctOperItemRelManager;

	@Autowired
	private CtOperationManager ctOperationManager;
	@Autowired
	private CtBaseOperationManager ctBaseOperationManager;

	// 分页搜索
	private CtOperation entity;
	private String queOperTypeName;
	private String queCtLedgerId;

	@Override
	public CtOperation getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 初始化网批通过的项目期数列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapCtLedger() {
		Map<String, String> mapCtLedger = ctLedgerManager.getCtLedger();
		return mapCtLedger;
	}

	@Override
	public String list() throws Exception {
		queryOperation();
		return "list";
	}

	/**
	 * 业态搜索
	 * 
	 * @throws Exception
	 */
	public void queryOperation() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		StringBuffer hql = new StringBuffer();
		hql.append(" from CtOperation where 1=1 ");
		if (StringUtils.isNotBlank(queOperTypeName)) {
			hql.append(" and operTypeName like :queOperTypeName");
			param.put("queOperTypeName", "%" + queOperTypeName + "%");
		}
		if (StringUtils.isNotBlank(queCtLedgerId)) {
			hql.append(" and ctLedger.ctLedgerId =:queCtLedgerId");
			param.put("queCtLedgerId", queCtLedgerId);
		}
		hql.append(" order by operTypeName,ctOperationId ");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField + ",createdDate");
			page.setOrder(order + "," + Page.DESC);
		}
		page = ctOperationManager.findPage(page, hql.toString(), param);
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isBlank(entity.getOperTypeCd())) {
			entity.setOperTypeCd(RandomUtils.generateTmpEntityId());
		}
		ctOperationManager.saveCtOperation(entity);
		// queryOperation();
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		if (getId() != null) {
			entity = ctOperationManager.getEntity(getId());
			ctOperationManager.delete(entity);
			Struts2Utils.renderHtml("success");
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (getId() != null) {
			entity = ctOperationManager.getEntity(getId());
		} else {
			entity = new CtOperation();
		}
	}

	public String getQueOperTypeName() {
		return queOperTypeName;
	}

	public String getQueCtLedgerId() {
		return queCtLedgerId;
	}

	public void setQueOperTypeName(String queOperTypeName) {
		this.queOperTypeName = queOperTypeName;
	}

	public void setQueCtLedgerId(String queCtLedgerId) {
		this.queCtLedgerId = queCtLedgerId;
	}

	/**
	 * 增加地块所对应的业态
	 */
	public void addOper() {
		// data:"operTypeName="+operTypeName+"&ctLandid="+ctLandid,
		StringBuffer strBuffer = new StringBuffer("{status:");
		String operTypeName = Struts2Utils.getParameter("operTypeName");
		String ctLandid = Struts2Utils.getParameter("ctLandid");
		String actType = Struts2Utils.getParameter("actType");
		String operationId = Struts2Utils.getParameter("operationId");
		try {

			if (StringUtils.isNotBlank(actType) && actType.equals("mod")) {// 修改
				Map<String, Object> values = new HashMap<String, Object>();
				values.put("operTypeName", operTypeName);
				values.put("ctOperationId", operationId);
				StringBuffer hqlBuffer = new StringBuffer(" update CtOperation t set t.operTypeName=:operTypeName ");
				hqlBuffer.append(" where t.ctOperationId=:ctOperationId");

				Query query = ctOperationManager.getDao().createQuery(hqlBuffer.toString(), values);
				query.executeUpdate();
				strBuffer.append(String.format("true,operId:\"%s\"}", operationId));
				Struts2Utils.renderText(strBuffer.toString());
				return;
			} else { // 增加
				entity = new CtOperation();
				entity.setOperTypeName(operTypeName);
				CtLand ctLand = new CtLand();
				ctLand.setCtLandId(ctLandid);
				entity.setCtLand(ctLand);
				ctOperationManager.saveCtOperation(entity);
				if (StringUtils.isNotBlank(Struts2Utils.getParameter("isCallOperId")) && Struts2Utils.getParameter("isCallOperId").equals("true")) {

					strBuffer.append(String.format("true,operId:\"%s\"}", entity.getCtOperationId()));
					Struts2Utils.renderText(strBuffer.toString());
					return;

				} else {
					getOpersInfo(ctLandid);// 添加成功则返回当前地块所对应的业态
				}
			}
		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));
			Struts2Utils.renderText(strBuffer.toString());
		}
	}

	/**
	 * 业态删除
	 */
	@SuppressWarnings("unchecked")
	public void doDelete() {
		String ctOperationId = Struts2Utils.getParameter("ctOperationId");
		String landId = Struts2Utils.getParameter("ctLandid");
		StringBuffer strBuffer = new StringBuffer("{status:");

		try {
			Map<String, Object> values = new HashMap<String, Object>();

			values.put("ctOperationId", ctOperationId);

			StringBuffer itemIds = new StringBuffer("");

			StringBuffer hqlBuffer = new StringBuffer("from CtOperItemRel t");
			hqlBuffer.append(" where t.ctOperation.ctOperationId=:ctOperationId");
			Query query = itemManager.getDao().createQuery(hqlBuffer.toString(), values);
			List<CtOperItemRel> operItemRelList = query.list();

			for (int i = 0; i < operItemRelList.size(); i++) {

				if (i > 0) {
					itemIds.append(",");

				}
				itemIds.append("'" + operItemRelList.get(i).getCtItem().getCtItemId() + "'");
			}
			operItemRelList.clear();

			hqlBuffer = new StringBuffer("delete from CtOperItemRel t");
			hqlBuffer.append(" where t.ctOperation.ctOperationId=:ctOperationId");

			query = ctOperItemRelManager.getDao().createQuery(hqlBuffer.toString(), values);
			query.executeUpdate();// 删除业态与科目关系数据

			if (StringUtils.isNotBlank(itemIds.toString())) {
				hqlBuffer = new StringBuffer("delete from CtItem t where t.ctItemId");
				hqlBuffer.append(" in(");
				hqlBuffer.append(itemIds);
				hqlBuffer.append(")");

				query = itemManager.getDao().createQuery(hqlBuffer.toString(), values);
				query.executeUpdate();// 删除业态所对应的科目表
			}

			values.put("landId", landId);
			hqlBuffer = new StringBuffer("delete from CtOperation t ");
			hqlBuffer.append(" where t.ctLand.ctLandId=:landId");
			hqlBuffer.append(" and t.ctOperationId=:ctOperationId ");

			query = ctOperationManager.getDao().createQuery(hqlBuffer.toString(), values);
			query.executeUpdate(); // 删除业态表数据
			strBuffer.append("true}");

		} catch (Exception ee) {
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}

		Struts2Utils.renderText(strBuffer.toString());

	}

	public void getLandToOpers() {
		String ctLandid = Struts2Utils.getParameter("ctLandid");
		getOpersInfo(ctLandid);// 添加成功则返回当前地块所对应的业态

	}

	/**
	 * 获取地块所对应的业态信息
	 * 
	 * @param ctLandId
	 *            地块ID号
	 */
	public void getOpersInfo(String ctLandId) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("ctLandId", ctLandId);

		StringBuffer hqlBuffer = new StringBuffer(" from CtOperation t where t.ctLand.ctLandId =:ctLandId order by operTypeName");
		StringBuffer hasExsitOperations = new StringBuffer("");
		List<CtOperationJson> operJsonList = new ArrayList<CtOperationJson>();
		List<CtOperation> list = ctOperationManager.getDao().find(hqlBuffer.toString(), values);

		for (int i = 0; i < list.size(); i++) {

			CtOperation ctOperation = list.get(i);
			CtOperationJson ctOperationJson = new CtOperationJson();
			ctOperationJson.setCtOperationId(ctOperation.getCtOperationId());
			ctOperationJson.setCtLandId(ctOperation.getCtLand().getCtLandId());
			ctOperationJson.setOperTypeName(ctOperation.getOperTypeName());
			ctOperationJson.setRecordVersion(ctOperation.getRecordVersion());
			ctOperationJson.setBaseOperation(false);
			operJsonList.add(ctOperationJson);

			hasExsitOperations.append(String.format(",%s,", ctOperation.getOperTypeName().trim()));

		}

		hqlBuffer = new StringBuffer(" from CtBaseOperation t order by  operName");
		values = new HashMap<String, Object>();
		List<CtBaseOperation> baseList = ctBaseOperationManager.getDao().find(hqlBuffer.toString(), values);
		String temp = "";
		// 搜索默认的业态信息
		for (int i = 0; i < baseList.size(); i++) {
			CtBaseOperation baseOperation = baseList.get(i);
			if (StringUtils.isBlank(baseOperation.getOperName())) {
				continue;

			}

			temp = String.format(",%s,", baseOperation.getOperName().trim());

			if (hasExsitOperations.indexOf(temp) < 0) {
				CtOperationJson ctOperationJson = new CtOperationJson();
				ctOperationJson.setCtOperationId(baseOperation.getCtBaseOperationId());
				ctOperationJson.setOperTypeName(baseOperation.getOperName());
				ctOperationJson.setRecordVersion(baseOperation.getRecordVersion());
				ctOperationJson.setCtLandId(ctLandId);
				ctOperationJson.setBaseOperation(true);
				operJsonList.add(ctOperationJson);
				hasExsitOperations.append(temp);
			}
		}

		Struts2Utils.renderJson(operJsonList);
		operJsonList.clear();
		list.clear();

	}

}

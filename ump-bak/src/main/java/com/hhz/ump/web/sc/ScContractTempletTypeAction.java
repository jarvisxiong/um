/**
 * 
 */
package com.hhz.ump.web.sc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.dao.sc.ScContractInfoAttachManager;
import com.hhz.ump.dao.sc.ScContractInfoFlowManager;
import com.hhz.ump.dao.sc.ScContractInfoSayManager;
import com.hhz.ump.dao.sc.ScContractMarkManager;
import com.hhz.ump.dao.sc.ScContractPrintManager;
import com.hhz.ump.dao.sc.ScContractTempletFillManager;
import com.hhz.ump.dao.sc.ScContractTempletHisManager;
import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.dao.sc.ScContractTempletManager;
import com.hhz.ump.dao.sc.ScContractTempletTypeManager;
import com.hhz.ump.dao.sc.ScContractTempletUserManager;
import com.hhz.ump.entity.sc.ScContractTemplet;
import com.hhz.ump.entity.sc.ScContractTempletHis;
import com.hhz.ump.entity.sc.ScContractTempletInfo;
import com.hhz.ump.entity.sc.ScContractTempletType;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.TreePanelNode;

/**
 * 标准合同模板
 * 
 * @author qlb 2012/2/6 version 1.0.1
 * 
 */
public class ScContractTempletTypeAction extends CrudActionSupport<ScContractTempletType> {

	@Autowired
	private ScContractTempletTypeManager contractTempletTypeManager;
	@Autowired
	private ScContractTempletManager contractTempletManager;

	@Autowired
	ScContractTempletInfoManager scContractTempletInfoManager;
	@Autowired
	ScContractTempletFillManager scContractTempletFillManager;
	@Autowired
	ScContractTempletHisManager scContractTempletHisManager;

	@Autowired
	ScContractMarkManager scContractTepletMarkManager;

	@Autowired
	ScContractMarkManager scContractMarkManager;
	@Autowired
	ScContractPrintManager scContractPrintManager;
	@Autowired
	ScContractInfoFlowManager scContractInfoFlowManager;
	@Autowired
	ResApproveInfoManager resApproveInfoManager;
	@Autowired
	ScContractTempletUserManager contractTempletUserManager;

	@Autowired
	ScContractTempletManager ScContractTempletManager;
	@Autowired
	ScContractInfoSayManager scContractInfoSayManager;
	@Autowired
	ScContractInfoAttachManager scContractInfoAttachManager;

	private ScContractTempletType entity;

	private ScContractTemplet scContractTemplet;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	// 搜索表单
	private List<ScContractTempletType> searchModuleList = new ArrayList<ScContractTempletType>();
	private Long maxSequence = Long.valueOf("3");

	/**
	 * @return the maxSequence
	 */
	public Long getMaxSequence() {
		return maxSequence;
	}

	/**
	 * @param maxSequence
	 *            the maxSequence to set
	 */
	public void setMaxSequence(Long maxSequence) {
		this.maxSequence = maxSequence;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		String scId = this.getId();
		// 需要删除的合同模板
		List<ScContractTempletType> delContractTempletList = new ArrayList<ScContractTempletType>();
		StringBuffer delScIds = new StringBuffer("'" + scId + "'");
		List<String> idsList = new ArrayList<String>();
		List<ScContractTempletType> modules = contractTempletTypeManager.getAll();
		List<ScContractTempletInfo> delContInfoList = new ArrayList<ScContractTempletInfo>();
		for (int i = 0; i < modules.size(); i++) {
			ScContractTempletType conTempType = modules.get(i);
			// 设置状态为删除

			if (delScIds.indexOf("'" + conTempType.getContractTempletTypeId() + "'") > -1 || delScIds.indexOf("'" + conTempType.getParentId() + "'") > -1) {
				conTempType.setIsDel(Byte.valueOf("1"));
				delContractTempletList.add(conTempType);
				delScIds.append(",'" + conTempType.getContractTempletTypeId() + "'");
				idsList.add(conTempType.getContractTempletTypeId());
			}
			modules.remove(i);
			i--;
		}
		Map<String, Object> param = new HashMap<String, Object>();

		// param.put("templetTypeIds", delScIds.toString());
		param.put("templetTypeIds", idsList);
		List<ScContractTemplet> contractTempletList = new ArrayList<ScContractTemplet>();
		if (StringUtils.isNotEmpty(delScIds.toString())) {
			StringBuffer hql = new StringBuffer();
			hql.append("from ScContractTemplet t1");
			hql.append(" where t1.scContractTempletType.contractTempletTypeId in(:templetTypeIds)");

			Query query = contractTempletManager.getDao().createQuery(hql.toString(), param);
			// 删除模板类型对应的所有模板
			contractTempletList = query.list();
		}

		StringBuffer strBuffer = new StringBuffer("{status:");

		try {
			for (int i = 0; i < contractTempletList.size(); i++) {

				ScContractTemplet scContemplet = contractTempletList.get(i);
				if (scContemplet != null && scContemplet.getScContractTempletInfos() != null && scContemplet.getScContractTempletInfos().size() > 0) {

					delContInfoList.addAll(scContemplet.getScContractTempletInfos());
					/*
					 * for (int j = 0; j <
					 * scContemplet.getScContractTempletInfos().size(); j++) {
					 * //
					 * delRelationInfo(scContemplet.getScContractTempletInfos(
					 * ).get(j).getContractTempletInfoId()); }
					 */
				}
			}

			Session session = contractTempletTypeManager.getDao().getSession();
			int count = delContractTempletList.size();
			int submitTime = count % 100;// 获取插入记录个数
			for (int i = 0; i < count; i++) {
				session.saveOrUpdate(delContractTempletList.get(i));
				if (i % 100 == 0) { // 每100条提交一次数据
					session.flush();
					session.clear();

				} else if (submitTime > 0 && i == count - 1) {
					// 如果余数不为0则说明需要多提交一次数据，否则数据则会少生成数据
					session.flush();
					session.clear();

				}
			}

			count = delContInfoList.size();

			for (int i = 0; i < count; i++) {
				ScContractTempletInfo scConTempletInfo = delContInfoList.get(i);
				// 设置状态为逻辑删除
				scConTempletInfo.setIsDel(Byte.valueOf("1"));
				session.saveOrUpdate(delContInfoList.get(i));
				if (i % 100 == 0) { // 每100条提交一次数据
					session.flush();
					session.clear();

				} else if (submitTime > 0 && i == count - 1) {
					// 如果余数不为0则说明需要多提交一次数据，否则数据则会少生成数据
					session.flush();
					session.clear();

				}
			}

			// contractTempletManager.delete(contractTempletList);
			// 删除模板类型
			// contractTempletTypeManager.delete(delContractList);

			strBuffer.append("true}");
		} catch (Exception ee) {
			// ee.printStackTrace();
			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
		// contractTempletTypeManager.deleteContractTempletType(getId());
		return null;
	}

	/**
	 * 刪除合同的相關信息
	 * 
	 * @param contInfoId
	 * @return
	 */
	public String delRelationInfo(String contInfoId) {

		// TODO Auto-generated method stub
		StringBuffer strBuffer = new StringBuffer("");

		ScContractTempletInfo scontractTempletInfo = scContractTempletInfoManager.getEntity(contInfoId);
		// 删除打印信息
		scContractPrintManager.delete(scontractTempletInfo.getScContractPrints());
		// 删除留言
		scContractInfoSayManager.delete(scontractTempletInfo.getScContractInfoSaies());
		// 删除附件
		scContractInfoAttachManager.delete(scontractTempletInfo.getScContractInfoAttachs());
		if (scontractTempletInfo.getScContractTempletHises() != null) {

			for (int i = 0; i < scontractTempletInfo.getScContractTempletHises().size(); i++) {

				ScContractTempletHis scContractTempletHis = scontractTempletInfo.getScContractTempletHises().get(i);
				if (scContractTempletHis != null && scContractTempletHis.getScContractMarks() != null) {
					// 删除所有批注信息
					scContractTepletMarkManager.delete(scContractTempletHis.getScContractMarks());
				}

			}
			// 删除所有历史版本

			scContractTempletHisManager.delete(scontractTempletInfo.getScContractTempletHises());
		}
		// 删除填空
		scContractTempletFillManager.delete(scontractTempletInfo.getScContractTempletFills());
		// 删除责任人
		contractTempletUserManager.delete(scontractTempletInfo.getScContractTempletUser());
		// 删除流程
		scContractInfoFlowManager.delete(scontractTempletInfo.getScContractInfoFlow());
		/**
		 * 删除合同
		 */
		scContractTempletInfoManager.delete(scontractTempletInfo);

		strBuffer.append("true}");

		return strBuffer.toString();

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	public void prepareEdit() throws Exception {
		String id = Struts2Utils.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			entity = contractTempletTypeManager.getEntity(id);
		} else {
			entity = new ScContractTempletType();

			// 获取记录最大序号
			getRecordMaxSequence();

			entity.setSequenceNo(maxSequence);
		}
	}

	@Override
	public String save() throws Exception {

		StringBuffer strBuffer = new StringBuffer("{status:");
		String isstandard = Struts2Utils.getParameter("isstandard");
		String sequenceNo = Struts2Utils.getParameter("sequenceNo");
		String typeName = Struts2Utils.getParameter("typeName");
		String moduleTypeCd = Struts2Utils.getParameter("moduleTypeCd");
		String remark = Struts2Utils.getParameter("remark");
		String sn=Struts2Utils.getParameter("sn");
		String parentId = Struts2Utils.getParameter("parentId");
		String ifNotNeedRes = Struts2Utils.getParameter("ifNotNeedRes");
		try {
			if (StringUtils.isNotBlank(getId())) {
				entity = contractTempletTypeManager.getEntity(getId());
			} else {

				entity = new ScContractTempletType();
				if (StringUtils.isBlank(sequenceNo)) {

					sequenceNo = "1";
				}
				entity.setSequenceNo(Long.valueOf(sequenceNo));
				entity.setParentId(parentId);
			}
			if (StringUtils.isNotBlank(isstandard) && isstandard.equals("true")) {
				isstandard = "1";
			} else {

				isstandard = "0";
			}
			entity.setTypeName(typeName);
			entity.setModuleTypeCd(moduleTypeCd);
			entity.setRemark(remark);
			entity.setSn(sn);
			entity.setIsstandard(Byte.valueOf(isstandard));
			try{
				entity.setIfNotNeedRes(Boolean.parseBoolean(ifNotNeedRes));
			}catch(Exception e){}

			entity.setIsstandard(Byte.valueOf(isstandard));
			if (StringUtils.isNotBlank(parentId)) {
				contractTempletTypeManager.saveScContractTempletType(entity);
			}
			strBuffer.append("true}");
		} catch (Exception ee) {

			strBuffer.append(String.format("false,errorMsg:\"%s\"}", ee.getMessage().replace("\"", "”")));

		}
		Struts2Utils.renderText(strBuffer.toString());
		return null;
	}

	@Override
	public ScContractTempletType getModel() {
		// TODO Auto-generated method stub
		return entity;
	}
/**
 * 合同文本模板库入口
 */
	@Override
	public String execute() throws Exception {
		// 获取记录最大序号
		getRecordMaxSequence();
		// 默认执行的方法
		return SUCCESS;
	}

	/**
	 * 获取记录中最大序号
	 */
	public void getRecordMaxSequence() {

		Map<String, Object> param = new HashMap<String, Object>();

		StringBuffer hql = new StringBuffer();
		hql.append("select to_char(max(sequenceNo))from ScContractTempletType t1");
		List maxSequenceList = contractTempletManager.find(hql.toString(), param);
		String value = maxSequenceList.toString();
		if (maxSequenceList != null && value.indexOf("null") < 0 && maxSequenceList.size() > 0) {

			// 获取最大序号
			maxSequence = Long.valueOf((String) maxSequenceList.get(0));
		}

		maxSequence++;

	}

	public void buildTree() {
		String moduleName = Struts2Utils.getParameter("moduleName");
		String conTypePid = Struts2Utils.getParameter("conTypePid");
		String 	isStandard= Struts2Utils.getParameter("isStandard");
		//合同分类：QQ设计类,YX:营销类
		String sn=Struts2Utils.getParameter("sn");
		Map<String, Object> param = new HashMap<String, Object>();

		StringBuffer hql = new StringBuffer();
		hql.append("from ScContractTempletType t1");
		hql.append(" where 1=1 and t1.isDel is null ");
		// 如果类型节点不为空则搜索其子节点数据
		if (StringUtils.isNotBlank(conTypePid)) {
		//	hql.append("  and t1.parentId =:conTypePid ");
			//param.put("conTypePid", conTypePid);
		}
		if(StringUtils.isNotBlank(isStandard) && isStandard.toLowerCase().equals("true")){
			
	       hql.append("  and t1.isstandard =:isStandard ");
			param.put("isStandard", Byte.valueOf("1"));
		}
		if(StringUtils.isNotBlank(sn)){
			
			   hql.append("  and t1.sn =:sn ");
				param.put("sn",sn);
			
		}
		hql.append(" order by t1.sequenceNo asc ");

		List<ScContractTempletType> modules = contractTempletTypeManager.find(hql.toString(), param);
		if (StringUtils.isBlank(moduleName)) {
			moduleName = "合同模板库";
		}

		TreePanelNode node = null;
		// 如果类型节点不为空则搜索其子节点数据
		if (StringUtils.isNotBlank(conTypePid)) {

			node = buildProjectModuleTree(modules, moduleName, conTypePid, null);

		} else {
			node = buildProjectModuleTree(modules, moduleName);

		}
		Struts2Utils.renderJson(node);
	}

	public TreePanelNode buildProjectModuleTree(List<ScContractTempletType> moduleList, String rootTitle) {
		return buildProjectModuleTree(moduleList, rootTitle, "0", null);
	}

	public TreePanelNode buildProjectModuleTree(List<ScContractTempletType> moduleList, String rootTitle, String pid, Map<String, String> mapCd2Id) {

		TreePanelNode rootNode = new TreePanelNode();
		if (StringUtils.isNotBlank(pid) && pid.equals("0")) {
			rootNode.setId("0");
			rootNode.setText(rootTitle);
			rootNode.setEntityId("0");// 特殊处理
			rootNode.setEntityCd("0");// 特殊处理
			rootNode.setNodeType("root");
		} else {

			ScContractTempletType scBaseTempletType = contractTempletTypeManager.getEntity(pid);

			rootNode.setText(scBaseTempletType.getTypeName());
			rootNode.setTitle(scBaseTempletType.getTypeName());
			rootNode.setEntityId(scBaseTempletType.getParentId());
			rootNode.setEntityStatusCd(scBaseTempletType.getSequenceNo() != null ? scBaseTempletType.getSequenceNo().toString() : "");
			rootNode.setEntityName(scBaseTempletType.getRemark());
			rootNode.setExtParam(scBaseTempletType.getModuleTypeCd());
			rootNode.setSexCd(scBaseTempletType.getIsstandard() != null ? scBaseTempletType.getIsstandard().toString() : "");
			
			if (null !=moduleList && 0== moduleList.size()) {

				rootNode.setNodeType("scBaseTemplet");
			} else {

				rootNode.setNodeType("scBaseParentTemplet");
			}
		}
		rootNode.setChildren(setScTemplet(pid, moduleList));

		return rootNode;
	}

	private List<TreePanelNode> setScTemplet(String parentId, List<ScContractTempletType> moduleList) {

		List<TreePanelNode> childrens = new ArrayList<TreePanelNode>();

		for (int i = 0; i < moduleList.size(); i++) {

			ScContractTempletType scBaseTempletType = moduleList.get(i);

			if (StringUtils.isNotBlank(parentId) && !parentId.equals(scBaseTempletType.getParentId())) {
				continue;
			}

			TreePanelNode treePanelItemNode = new TreePanelNode();
			treePanelItemNode.setId(scBaseTempletType.getContractTempletTypeId());
			treePanelItemNode.setText(scBaseTempletType.getTypeName());
			treePanelItemNode.setTitle(scBaseTempletType.getTypeName());
			treePanelItemNode.setEntityId(scBaseTempletType.getParentId());
			treePanelItemNode.setEntityStatusCd(scBaseTempletType.getSequenceNo() != null ? scBaseTempletType.getSequenceNo().toString() : "");
			treePanelItemNode.setEntityName(scBaseTempletType.getRemark());
			treePanelItemNode.setExtParam(scBaseTempletType.getModuleTypeCd());
			treePanelItemNode.setSexCd(scBaseTempletType.getIsstandard() != null ? scBaseTempletType.getIsstandard().toString() : "");
			if (!isParentNode(scBaseTempletType.getContractTempletTypeId(), moduleList)) {

				treePanelItemNode.setNodeType("scBaseTemplet");
			} else {

				treePanelItemNode.setNodeType("scBaseParentTemplet");
			}

			childrens.add(treePanelItemNode);

			treePanelItemNode.setChildren(setScTemplet(scBaseTempletType.getContractTempletTypeId(), moduleList));
			moduleList.remove(scBaseTempletType);
			i--;

		}

		return childrens;
	}

	public Map<String, String> getMapmoduleTypeCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SC_TEMPLET_TYPE);
	}

	/**
	 * 编辑模块
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		// prepareModel();

		return "edit";
	}

	/**
	 * 功能: 模糊搜索表单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public String searchTreeList() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String sn=Struts2Utils.getParameter("sn");
		Map<String, Object> param = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(value)) {
			StringBuffer hql = new StringBuffer();
			hql.append("from ScContractTempletType t1");
			hql.append(" where lower(t1.typeName) like :typeName  ");
			if(StringUtils.isNotBlank(sn)){
				//合同类型，QQ：设计类，YX：营销类
				hql.append(" and t1.sn=:sn");
				param.put("sn", sn);
				
			}
			hql.append(" order by t1.sequenceNo desc");

			
			param.put("typeName", "%" + value.toLowerCase() + "%");
			List<ScContractTempletType> conTempletTypeList = contractTempletTypeManager.find(hql.toString(), param);
			int count = conTempletTypeList == null ? 0 : conTempletTypeList.size();
			List<ScContractTempletType> contractTempletAll = contractTempletTypeManager.getAll();
			for (int i = count - 1; i >= 0; i--) {
				ScContractTempletType conTempletType = conTempletTypeList.get(i);
				// 只取叶子节点
				if (!isParentNode(conTempletType.getContractTempletTypeId(), conTempletTypeList)) {
					String tempName = SetSearchTempletInfo(conTempletType.getParentId(), contractTempletAll);
					conTempletType.setTypeName(tempName + conTempletType.getTypeName());
					searchModuleList.add(conTempletType);
				} else {

					String tempName = SetSearchTempletInfo(conTempletType.getParentId(), contractTempletAll);
					conTempletType.setTypeName(tempName + conTempletType.getTypeName());
					searchModuleList.add(conTempletType);

				}

			}

		}

		return "search";
	}

	/**
	 * 
	 * @param conTempletTypeList
	 * @return
	 */
	public String SetSearchTempletInfo(String pId, List<ScContractTempletType> conTempletTypeList) {
		StringBuffer tempName = new StringBuffer("");
		for (int i = 0; pId != null && i < conTempletTypeList.size(); i++) {
			if (pId.equals(conTempletTypeList.get(i).getContractTempletTypeId())) {
				tempName.append(conTempletTypeList.get(i).getTypeName() + "/");
				ScContractTempletType conTempletType = conTempletTypeList.get(i);
				return SetSearchTempletInfo(conTempletType.getParentId(), conTempletTypeList) + tempName.toString();

			}

		}
		return "";
	}

	/**
	 * 是否为父节点
	 * 
	 * @param conTempletTypeList
	 * @return
	 */
	public boolean isParentNode(String templetId, List<ScContractTempletType> conTempletTypeList) {

		for (int i = 0; templetId != null && i < conTempletTypeList.size(); i++) {
			if (templetId.equals(conTempletTypeList.get(i).getParentId()))
				return true;

		}
		return false;
	}

	/**
	 * @return the searchModuleList
	 */
	public List<ScContractTempletType> getSearchModuleList() {
		return searchModuleList;
	}

	/**
	 * @param searchModuleList
	 *            the searchModuleList to set
	 */
	public void setSearchModuleList(List<ScContractTempletType> searchModuleList) {
		this.searchModuleList = searchModuleList;
	}

	/**
	 * @return the scContractTemplet
	 */
	public ScContractTemplet getScContractTemplet() {
		return scContractTemplet;
	}

	/**
	 * @param scContractTemplet
	 *            the scContractTemplet to set
	 */
	public void setScContractTemplet(ScContractTemplet scContractTemplet) {
		this.scContractTemplet = scContractTemplet;
	}

	/**
	 * @return the entity
	 */
	public ScContractTempletType getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(ScContractTempletType entity) {
		this.entity = entity;
	}

}

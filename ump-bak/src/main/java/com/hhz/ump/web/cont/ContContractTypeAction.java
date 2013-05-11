package com.hhz.ump.web.cont;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cont.ContContractTypeManager;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.entity.cont.ContContractType;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.util.TreePanelUtil;

@Namespace("/cont")
public class ContContractTypeAction extends CrudActionSupport<ContContractType> {
	
	private static final long serialVersionUID = 8071052318603147332L;
	@Autowired
	private ContContractTypeManager contContractTypeManager;
	@Autowired
	private ContProjectCodeManager contProjectCodeManager;

	private ContContractType entity;
	
	private String batchName;
	private Long batchOrder;
	private String ledgerType;
	public ContContractType getModel() {
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
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}
	
	public String getItemTree() throws Exception {
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setFinType("");
		node.setText("合同类型");
		node.setOrderNo(new Long(0));
		node.setChildren(contContractTypeManager.getChildrenNode(node, false,ledgerType));
		Struts2Utils.renderJson(node);

		return null;
	}
	/**
	 * 得到工程项目的所有节点
	 * @return
	 * @throws Exception
	 */
	public String getParentTree() throws Exception {
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setFinType("");
		node.setText("合同类型");
		node.setOrderNo(new Long(0));
		//List<WsPlasOrg> orgList =contContractTypeManager.getAllProjectNode();
		//得到所有cont_project_code表的所有节点
		List<ContProjectCode> projCodeList =contProjectCodeManager.getAll();
		node.setChildren(contContractTypeManager.getChildrenNodeByAll(projCodeList));
		Struts2Utils.renderJson(node);
		return null;
	}
	public String getContTypeTree() throws Exception {
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setFinType("");
		node.setText("请选择合同类别");
		node.setOrderNo(new Long(0));
		node.setChecked(TreePanelUtil.NODE_CHECKED_NONE);//选择框
		node.setChildren(contContractTypeManager.getChildrenNode(node, true,ledgerType));
		Struts2Utils.renderJson(node);
		return null;
	}
	
	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = contContractTypeManager.getContContractType(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new ContContractType();
		}
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (entity.getTypeCd() == null || "".equals(entity.getTypeCd())) {
			entity.setTypeCd(RandomUtils.generateTmpEntityId());
		}
		//若父类为自己，则不允许保存
		if(entity.getTypeParentCd()!=null&&entity.getTypeCd().equals(entity.getTypeParentCd()))
			return null;	
		contContractTypeManager.saveContContractType(entity);
		//若是新的projectCd,则在projectCd表上添加
		List<ContProjectCode> projectList =contProjectCodeManager.getProjectCode(entity.getProjectCd());
		if(projectList==null||projectList.size()<=0){
			ContProjectCode code =new ContProjectCode();
			code.setProjectCd(entity.getProjectCd());
			contProjectCodeManager.saveContProjectCode(code);
		}
		return null;
	}
	public String batchSave() throws Exception{
		if(StringUtils.isNotBlank(batchName)&&batchOrder!=null){
			contContractTypeManager.batchContContractType(batchName,batchOrder);
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			//entity = contContractTypeManager.getContContractType(getId());
			contContractTypeManager.deleteContContractType(getId());
			addActionMessage(getText("common.success"));
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public Long getBatchOrder() {
		return batchOrder;
	}

	public void setBatchOrder(Long batchOrder) {
		this.batchOrder = batchOrder;
	}

	public String getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(String ledgerType) {
		this.ledgerType = ledgerType;
	}

}

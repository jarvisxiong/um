package com.hhz.ump.web.assm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.assm.AssmAccountManager;
import com.hhz.ump.dao.assm.AssmModelManager;
import com.hhz.ump.entity.assm.AssmAccount;
import com.hhz.ump.entity.assm.AssmModel;
import com.hhz.ump.util.TreePanelNode;
@Namespace("/assm")
public class AssmManageAction  extends CrudActionSupport<AssmAccount> {
	
	private static final long serialVersionUID = -8818821430740848524L;
	
	@Autowired
	protected AssmModelManager assmModelManager;
	@Autowired
	protected AssmAccountManager assmAccountManager;
	
	private Page<AssmAccount> page = new Page<AssmAccount>(10);

	private TreePanelNode showTree;
	
	
	/**
	 * 构建固定资产台帐设备型号树
	 * @return
	 */
	public String getAssmModelTree(){
		
		List<AssmModel> list=assmModelManager.findAll();	
		
		List<TreePanelNode> nodeList=new ArrayList<TreePanelNode>();
		
		//根节点
		TreePanelNode root=null;
		TreePanelNode node=null;
		for(AssmModel mode:list){
			if(StringUtils.isBlank(mode.getPratentId())){
				mode.getPratentId();
				root=getRootOrg(mode);
				break;
			}			
		}
		for(AssmModel mode:list){
			if(StringUtils.isBlank(mode.getPratentId())){
				mode.getPratentId();
				root=getRootOrg(mode);
			}else{
				String nodeType="assm";
				if("4".equals(mode.getAssmLevel())){
					nodeType="leaf";
				}
				node=makeNode(mode.getAssmModelId(),mode.getAssmName(),nodeType,root.getEntityName(),mode.getAssmLevel().toString(),mode.getPratentId());
				nodeList.add(node);
			}		
		}
		 //迭代出树结构
		 showTree=getTreeNode(nodeList,root);
		 //调整排序
		 //sortTreeNode(showTree);
		 Struts2Utils.renderJson(showTree);
		return null;
	}
	
	/**
	 * 递归列表转换为树
	 * @param nodeList
	 * @param parentNode
	 * @return
	 */
	public TreePanelNode getTreeNode(List<TreePanelNode> nodeList,TreePanelNode parentNode){		
		for(TreePanelNode m:nodeList){
			//找到自己的儿子
			if(parentNode.getEntityId().equals(m.getExtParam())){				
				//如果是个叶子节点,则不再继续，不现实第三级
				if("3".equals(m.getLevelCd())){
					//将sexCd用作父节点名称
					m.setSexCd(parentNode.getText());
					parentNode.addChild(m);
					continue;
				}					
				getTreeNode(nodeList,m);
				parentNode.addChild(m);
			}
		}
		 
		return parentNode;
	}
	
	/**
	 * 对树节点排序
	 * @param treeNode
	 */
	public void sortTreeNode(TreePanelNode treeNode){
		List l = treeNode.getChildren();
		TreePanelNode fn = (TreePanelNode) l.get(0);
		l.add(fn);
		l.remove(0);
		treeNode.setChildren(l);
	}
	
	/**
	 * 构造相应节点
	 * @param nodeCd
	 * @param nodeName
	 * @param typeCd
	 * @param checkedType
	 * @param rootName
	 * @return
	 */
	private  TreePanelNode makeNode(String nodeCd, String nodeName, String typeCd,String rootName,String levelCd,String parentId){
		TreePanelNode tmp = new TreePanelNode();
		tmp.setEntityId(nodeCd);
		tmp.setEntityCd(nodeCd);
		tmp.setEntityBizCd(nodeCd);
		tmp.setEntityName(nodeName);
		tmp.setNodeType(typeCd);
		//tmp.setChecked("0");		
		tmp.setLevelCd(levelCd);
		tmp.setId(nodeCd);
		tmp.setText(nodeName);
		tmp.setExtParam(parentId);//父节点ID	
		return tmp;
	}
	
	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		 
		return null;
	}

	/**
	 * 资产台帐管理首页入口
	 */
	public String main() throws Exception {
		return "main";
	}
	
	@Override
	public String input() throws Exception {
		
		return "input";
	}
	
	/**
	 * 根节点
	 * @param num
	 * @return
	 */
	private  TreePanelNode getRootOrg(AssmModel mode) {
		String DEFAULT_ROOT_ORG_ID_ONE = mode.getAssmModelId();
		String DEFAULT_ROOT_ORG_CD_ONE = "0";
		String DEFAULT_ROOT_ORG_BIZ_CD_ONE = "0";
		String DEFAULT_ROOT_ORG_NAME_ONE = "固定资产台帐";
		String NODE_TYPE_ORG_ONE = "1";
		TreePanelNode node = new TreePanelNode();
		node.setId(DEFAULT_ROOT_ORG_ID_ONE);
		node.setText(DEFAULT_ROOT_ORG_NAME_ONE);
		node.setEntityId(DEFAULT_ROOT_ORG_ID_ONE);
		node.setEntityCd(DEFAULT_ROOT_ORG_CD_ONE);
		node.setEntityBizCd(DEFAULT_ROOT_ORG_BIZ_CD_ONE);
		node.setEntityName(DEFAULT_ROOT_ORG_NAME_ONE);
		node.setNodeType(NODE_TYPE_ORG_ONE);
		node.setOrgOrUser(NODE_TYPE_ORG_ONE);

		return node;
	}

	
	public String loadList(){
		String assmModelId=Struts2Utils.getParameter("assmModelId");
		Map<String,Object> values=new HashMap<String,Object> ();
		StringBuffer hql=new StringBuffer();
		hql.append(" from AssmAccount t where 1=1 ");
		if(StringUtils.isNotBlank(assmModelId)){
			values.put("assmModelId", assmModelId);
			hql.append(" and t.assmModel.assmModelId = :assmModelId");
			page = assmAccountManager.findPage(page, hql.toString(), values);
		}				
		return "loadList";
	}
	
	public Map<String,String> getMapUseStatus(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		map.put("1", "正常使用");
		map.put("2", "库存");
		map.put("3", "报废");
		map.put("4", "经营租出");
		map.put("5", "公用");
		return map;
	}
	
	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public AssmAccount getModel() {
		return null;
	}

	public TreePanelNode getShowTree() {
		return showTree;
	}

	public void setShowTree(TreePanelNode showTree) {
		this.showTree = showTree;
	}
	
}

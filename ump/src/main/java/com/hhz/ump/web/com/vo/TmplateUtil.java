package com.hhz.ump.web.com.vo;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.util.TreePanelNode;

public class TmplateUtil {

	/**
	 * @param node 例如:TreePanelUtil2.buildPhysicalOrgTree(rootNode, orgList);
	 * 根节点的level: 1级
	 * @return
	 */
	public static List<VoTreeRow> getOrgUserList(){
		
		TreePanelNode node = PlasCache.getPhysicalOrgTree();
//		TreePanelUtil2.buildPhysicalOrgTree(rootNode, orgList);
		int level = 1;//当前层级
		List<VoTreeRow> rtnList = new ArrayList<VoTreeRow>();
		rtnList.add(getVoTreeRow(node,"none", level));
		rtnList.add(createStart("none", level));
		for (TreePanelNode tmpNode : node.getChildren()) {
			rtnList.addAll(processNode(tmpNode, node.getId(), (level+1)));
		}
		rtnList.add(createEnd("none", level));
		return rtnList;
	}

	private static List<VoTreeRow> processNode(TreePanelNode node, String parentId, long level){
		
		List<VoTreeRow> list = new ArrayList<VoTreeRow>();
		list.add(getVoTreeRow(node, parentId, level));
		list.add(createStart(parentId, level));
		for (TreePanelNode tmpNode : node.getChildren()) {
			list.addAll(processNode(tmpNode, tmpNode.getId(), (level+1)));
		}
		list.add(createEnd(parentId, level));
		return list;
	}
	
	private static VoTreeRow getVoTreeRow(TreePanelNode node, String parentId, long level){
		
		VoTreeRow row = new VoTreeRow();
		row.setId(node.getId());
		row.setText(node.getText());
		row.setNodeTypeCd(node.getNodeType());
		row.setNodeId(node.getId());
		row.setNodeCd(node.getEntityCd());
		row.setNodeName(node.getEntityName());
		row.setParentId(parentId);
		row.setStatusCd(node.getEntityStatusCd());
		row.setLevel(level);
		
		return row;
	}
	private static VoTreeRow createStart(String parentId, long level){
		VoTreeRow row = new VoTreeRow();
		row.setNodeTypeCd("start");
		row.setParentId(parentId);
		row.setLevel(level);
		return row;
	}
	private static VoTreeRow createEnd(String parentId, long level){
		VoTreeRow row = new VoTreeRow();
		row.setNodeTypeCd("end");
		row.setParentId(parentId);
		row.setLevel(level);
		return row;
	}
}

package com.hhz.ump.dao.cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostProvCondRef;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;

@Service
@Transactional
public class CostProvCondRefManager extends BaseService<CostProvCondRef, String> {
	@Autowired
	private CostProvCondRefDao costProvCondRefDao;
	/**
	 * 保存对象
	 * @param costProvCondRef
	 */
	public void saveCostProvCondRef(CostProvCondRef costProvCondRef) {
		PowerUtils.setEmptyStr2Null(costProvCondRef);
		costProvCondRefDao.save(costProvCondRef);
	}
	/**
	 * 删除对象
	 * @param id
	 */
	public void deleteCostProvCondRef(String id) {
		costProvCondRefDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostProvCondRef, String> getDao() {
		return costProvCondRefDao;
	}
	
	public CostProvCondRef getCostProvCondRefByField(){
		return null;
	}
	/**
	 * 验证同一分类下不能存在同一供方内容
	 * @param typeName , provDesc
	 * @return true(有记录) false(无记录)
	 */
	public boolean valiExistProvDesc(String typeName, String provDesc){
		Map<String, Object> param =  new HashMap<String, Object>();
		param.put("typeName", typeName.trim());
		param.put("provDesc", provDesc.trim());
		String hql = " from CostProvCondRef t where t.typeName = :typeName and t.provDesc = :provDesc";
		List<CostProvCondRef> tlist = find(hql, param);
		if(tlist.size()>0)
			return true;
		else
			return false;
	}
	/***
	 * 构造左侧的树
	 * @return
	 */
	public TreePanelNode getProvTree(){

		String hql = " from CostProvCondRef ";
		List<CostProvCondRef> list = find(hql, new HashMap<String, Object>());
		Map<String, List<CostProvCondRef>> provcondMap = new HashMap<String, List<CostProvCondRef>>();
		Map<String,String> provcondNameMap = new HashMap<String, String>();
		
		List<String> costProvCondRefIdList = new ArrayList<String>();
		
		String typename = null;
		CostProvCondRef provcond = null;
		if(list!= null){
			for (int i=0; i<list.size(); i++) {
				provcond = list.get(i);
				if(provcond != null){
					typename = provcond.getTypeName();
					if(!costProvCondRefIdList.contains(typename)){
						costProvCondRefIdList.add(provcond.getTypeName());
						provcondNameMap.put(provcond.getTypeName(), provcond.getTypeName());
					}
					if(provcondMap.keySet().contains(typename)){
						provcondMap.get(typename).add(provcond);
					}else{
						List<CostProvCondRef> tmpList = new ArrayList<CostProvCondRef>();
						tmpList.add(provcond);
						provcondMap.put(typename, tmpList);
					}
				}
			}
		}
		//构造根节点
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("请选择项目");
		rootNode.setNodeType("0");//0-根节点
		rootNode.setEntityId("0");
		rootNode.setEntityCd("0");
		rootNode.setEntityName("根节点");
		rootNode.setChecked(TreePanelUtil.NODE_CHECKED_NONE);
		
		//遍历项目
		TreePanelNode tProjectNode = null;
		TreePanelNode tSectionNode = null;
		
		if(costProvCondRefIdList != null){
			for (String cfid : costProvCondRefIdList) {
				//构造项目
				tProjectNode = new TreePanelNode();
				tProjectNode.setId(cfid);
				tProjectNode.setNodeType("1");//1-项目公司
				tProjectNode.setEntityId(cfid);
				tProjectNode.setEntityCd(cfid);
				tProjectNode.setChecked(TreePanelUtil.NODE_CHECKED_NONE);
				
				tProjectNode.setText(provcondNameMap.get(cfid));
				tProjectNode.setEntityName(provcondNameMap.get(cfid));
				
				//构造子项目
				List<CostProvCondRef> tmpList = null;
				if(provcondMap.keySet().contains(cfid)){
					tmpList = provcondMap.get(cfid);
					if(tmpList != null){
						for (CostProvCondRef cpcr : tmpList) {
							if( cpcr!= null ){
								//构造项目
								tSectionNode = new TreePanelNode();
								tSectionNode.setId(cpcr.getCostProvCondRefId());
								tSectionNode.setText(cpcr.getProvDesc());
								tSectionNode.setNodeType("2");//2-标段
								tSectionNode.setEntityId(cpcr.getCostProvCondRefId());
								tSectionNode.setEntityBizCd(cpcr.getProfQualDesc());
								tSectionNode.setEntityName(cpcr.getProvDesc());
	
								tSectionNode.setChecked(TreePanelUtil.NODE_CHECKED_NONE);
								
								tProjectNode.getChildren().add(tSectionNode);
							}
						}
					}
				}
				//追加公司
				rootNode.getChildren().add(tProjectNode);
			}
		}
		return rootNode;
	}
}


package com.hhz.ump.dao.mate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.entity.mate.MateOwnerType;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.TreeNode;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class MateOwnerTypeManager extends BaseService<MateOwnerType, String> {
	@Autowired
	private MateOwnerTypeDao mateOwnerTypeDao;
	@Autowired
	private ContProjectCodeManager contProjectCodeManager;
	public void saveMateOwnerType(MateOwnerType mateOwnerType) {
		PowerUtils.setEmptyStr2Null(mateOwnerType);
		mateOwnerTypeDao.save(mateOwnerType);
	}

	public void deleteMateOwnerType(String id) {
		mateOwnerTypeDao.delete(id);
	}
	@Transactional(readOnly = true)
	public MateOwnerType getMateOwnerType(String id) {
		return mateOwnerTypeDao.get(id);
	}	
	@Override
	public HibernateDao<MateOwnerType, String> getDao() {
		return mateOwnerTypeDao;
	}
	
	/**
	 * 获取所有甲供料类型
	 * @return
	 */
	public List<MateOwnerType> getAllMateOwnerTypeList(){
		Map<String,Object> values = new HashMap<String,Object>();
		
		//注意,这里必须带若这里加上  projectCd != t.ownerParMaterialType 只能查出非"项目公司“下属的节点
		String hql = "from MateOwnerType t order by t.projectCd asc, t.createdDate asc ";
		List<MateOwnerType> result = mateOwnerTypeDao.find(hql, values);
		if(result == null)
			return new ArrayList<MateOwnerType>();
		return result;
	}
	/**
	 * 得到甲供料类型树
	 * @param treeNode
	 * @param haveChecked
	 * @return
	 */
	public List<TreeNode> getChildrenNode(TreeNode treeNode,boolean haveChecked) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		//得到地产公司projectCd,在跟节点下
//		List<String> projects =getDictProjectCd();
		List<HashMap<String,String>> projects = contProjectCodeManager.queryMateAuthority();
		//若进入人没有分配具体的项目权限，则取商业或地产台账所有项目
		if(projects==null||projects.size()<=0){
			List<ContProjectCode> codeList;
				codeList=contProjectCodeManager.getAll();
			for(ContProjectCode code:codeList){
				if(code.getProjectCd()!=null) {
					HashMap<String,String> map =new HashMap<String,String>();
					map.put(code.getProjectCd(), code.getProjectName());
					projects.add(map);
				}
			}
		}
		List<MateOwnerType> mateOwnerType = mateOwnerTypeDao.getAll();
		
		Iterator iter = projects.iterator(); 
		while (iter.hasNext()) { 
		    Map map = (Map) iter.next(); 
		    TreeNode proItem = new TreeNode();
		    String key = map.keySet().iterator().next().toString();
		    String value = (String)map.get(key);
		    //类型CD
		    proItem.setId(key);
			proItem.setFinType(key);
			proItem.setText(value);
			proItem.setChildren(getProjectChildrenNode(proItem, haveChecked,mateOwnerType));
			if (haveChecked) {
				proItem.setChecked("0");
			}
			children.add(proItem);
		    
		} 
		return children;
	}
	/**
	 * 得到所有项目节点，目前是地产公司节点
	 */
	public List<WsPlasOrg> getAllProjectNode(){
		
		//135为地产公司节点
//		return PlasCache.getLogicalDirectOrgListByOrgCd("135");
		
		//获取天津子项目公司采用获取中心机构形式；
		WsPlasOrg tmpOrg =  PlasCache.getOrgByCd(Constants.ORG_CD_DCGS);
		if(tmpOrg == null)
			return new ArrayList<WsPlasOrg>();
		else
			return PlasCache.getDecsantOrgList(PlasCacheUtil.TREE_TYPE_PHYSICAL, tmpOrg.getPlasOrgId(), Constants.ORG_TYPE_CD_ZX);
	}	
	/**
	 * 得到项目节点的相关甲供材料类型
	 * @param treeNode
	 * @param haveChecked
	 * @return
	 */
	public List<TreeNode> getProjectChildrenNode(TreeNode treeNode, boolean haveChecked,List<MateOwnerType> typeList) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		if (treeNode != null) {
			//这里的finType为地产公司的projectCd
			List<MateOwnerType> result = getParentType(treeNode.getId(),treeNode.getFinType());
			for(Iterator<MateOwnerType> it = typeList.iterator(); it.hasNext();){
				MateOwnerType type=it.next();
				if(treeNode.getId().equals(type.getOwnerParMaterialType())&&treeNode.getFinType().equals(type.getProjectCd())){
					result.add(type);
					it.remove();
				}
			}
			for (MateOwnerType item : result) {
				TreeNode childItem = new TreeNode();
				//类型CD
				childItem.setId(item.getOwnerMaterialType());
				childItem.setText(item.getTypeName());
				childItem.setParentId(treeNode.getId());
				childItem.setParentName(treeNode.getText());
				childItem.setFinItemCd(item.getOwnerParMaterialType());
				childItem.setFinType(treeNode.getFinType());
				childItem.setTrueId(item.getMateOwnerTypeId());
				if (haveChecked) {
					childItem.setChecked("0");
				}
				childItem.setChildren(this.getProjectChildrenNode(childItem,
						haveChecked,typeList));
				children.add(childItem);
			}
		}
		return children;
	}
	/**
	 * 得到甲供材料类型的地产公司
	 */
	public List<String> getDictProjectCd(){
		WsPlasOrg org = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
		String hql = "select distinct projectCd  from MateOwnerType";
		Map<String, Object> param = new HashMap<String, Object>();
		List<String> result = mateOwnerTypeDao.find(hql.toString(), param);
		for(String str:result){
			if(org!=null&&org.getOrgCd()!=null)
				if(str.equals(org.getOrgCd())){
					List<String> realResult =new ArrayList<String>();
					realResult.add(str);
					return realResult;
				}
		}
		return result;
	}
	/**
	 * 搜索父类ID=parentTypeCd的甲供料
	 * 
	 * @param parentItemCd
	 * @return
	 */
	public List<MateOwnerType> getParentType(String parentTypeCd,String projectCd) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from MateOwnerType a where 1=1"
				+" and a.ownerParMaterialType=:parentTypeCd "
				);
		if(StringUtils.isNotBlank(projectCd)){
			hql.append(" and a.projectCd=:projectCd");
			param.put("projectCd", projectCd);
		}
		param.put("parentTypeCd", parentTypeCd);
		hql.append(" order by a.projectCd asc");
		List<MateOwnerType> result = mateOwnerTypeDao.find(hql.toString(), param);
		return result;
	}
	public MateOwnerType getTypeVo(String typeCd){
		String hql ="select a from "+MateOwnerType.class.getSimpleName()+" a where a.MateOwnerType ='"+typeCd+"'";
		List<MateOwnerType> result = mateOwnerTypeDao.find(hql.toString());
		if(result!=null&&result.size()>0)
			return result.get(0);
		else return null;
	}
	/**
	 * 获得甲供材料类型的项目并子项目
	 */
	public List<String> getTypeList(String typeCd,List<String> typeNameList){
		if(typeCd!=null&&!"".equals(typeCd)){
			String hql=" from MateOwnerType a where a.ownerMaterialType ='"+typeCd+"'";
			List<MateOwnerType> result = mateOwnerTypeDao.find(hql.toString());
			if(result==null||result.size()<=0)
				return typeNameList;
			else{
				MateOwnerType type=result.get(0);
				typeNameList.add(type.getTypeName());
				if(!type.getOwnerParMaterialType().equals(type.getProjectCd()) ){
					getTypeList(type.getOwnerParMaterialType(),typeNameList);
				}
			}
		}
		return typeNameList;
	}

	/**
	 * 获取甲供料名称
	 * @param typeCd
	 * @return
	 */
	public String getMateNameByCd(String typeCd) {

		Map<String,Object> values = new HashMap<String, Object>();
		values.put("typeCd", typeCd);
		String hql=" select type_name from mate_owner_type a where a.owner_material_type = :typeCd ";
		List list = mateOwnerTypeDao.findBySql(hql, values);
		if(list == null || list.size() == 0)
			return "";
		else
			return (String)list.get(0);
	}	
}

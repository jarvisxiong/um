package com.hhz.ump.dao.cont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.cont.ContContractType;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.TreeNode;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class ContContractTypeManager extends BaseService<ContContractType, String> {
	@Autowired
	private ContContractTypeDao contContractTypeDao;
	@Autowired
	private ContProjectCodeManager contProjectCodeManager;
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<ContContractType, String> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(readOnly = true)
	public ContContractType getContContractType(String id) {
		return contContractTypeDao.get(id);
	}
	public void saveContContractType(ContContractType contContractType) {
		PowerUtils.setEmptyStr2Null(contContractType);
		contContractTypeDao.save(contContractType);
	}
	public void batchContContractType(String batchName,Long batchOrder){
		List<WsPlasOrg> orgList =getAllProjectNode();
		for(WsPlasOrg wsOrg:orgList){
			ContContractType typeVo = new ContContractType();
			typeVo.setProjectCd(wsOrg.getOrgCd());
			typeVo.setTypeName(batchName);
			typeVo.setTypeParentCd(wsOrg.getOrgCd());
			typeVo.setTypeCd(RandomUtils.generateTmpEntityId());
			typeVo.setDispOrderNum(batchOrder);
			saveContContractType(typeVo);
		}
	}
	public void deleteContContractType(String id) {
		contContractTypeDao.delete(id);
	}
	/**
	 * 得到所有项目节点，目前是地产公司节点
	 */
	public List<WsPlasOrg> getAllProjectNode(){
		
		//135为地产公司节点
		return PlasCache.getLogicalDirectOrgListByOrgCd("135");
	}
	/**
	 * 得到合同类型树
	 * @param treeNode
	 * @param haveChecked
	 * @return
	 */
	public List<TreeNode> getChildrenNode(TreeNode treeNode,
			 boolean haveChecked,String ledgerType) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		//得到地产公司projectCd,在跟节点下
		//List<String> projects =getDictProjectCd();
		List<HashMap<String,String>> projects = contProjectCodeManager.queryAuthority(ledgerType);
		//若进入人没有分配具体的项目权限，则取商业或地产台账所有项目
		if(projects==null||projects.size()<=0){
			List<ContProjectCode> codeList;
			if(StringUtils.isNotBlank(ledgerType)){
				String hql =" from ContProjectCode where codeType='"+ledgerType+"'";
				codeList=contProjectCodeManager.find(hql);
			}else{
				codeList=contProjectCodeManager.getAll();
			}
			for(ContProjectCode code:codeList){
				if(code.getProjectCd()!=null) {
					HashMap<String,String> map =new HashMap<String,String>();
					map.put(code.getProjectCd(), code.getProjectName());
					projects.add(map);
				}
			}
		}
		List<ContContractType> contractType = contContractTypeDao.getAll();
		
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
			proItem.setChildren(getProjectChildrenNode(proItem, haveChecked,contractType));
			if (haveChecked) {
				proItem.setChecked("0");
			}
			children.add(proItem);
		    
		} 
		/*for(HashMap<String,String> project:projects){
			TreeNode proItem = new TreeNode();
			//类型CD
			proItem.setId(project.keySet() );
			proItem.setFinType(projectCd);
			proItem.setText(CodeNameUtil.getDeptNameByCd(projectCd));
			proItem.setChildren(getProjectChildrenNode(proItem, haveChecked,contractType));
			if (haveChecked) {
				proItem.setChecked("0");
			}
			children.add(proItem);
		}*/
		return children;
	}
	/**
	 * 得到项目节点的相关合同
	 * @param treeNode
	 * @param haveChecked
	 * @return
	 */
	public List<TreeNode> getProjectChildrenNode(TreeNode treeNode,
			 boolean haveChecked,List<ContContractType> typeList) {
					List<TreeNode> children = new ArrayList<TreeNode>();
					if (treeNode != null) {
						//这里的finType为地产公司的projectCd
						//List<ContContractType> result = getParentType(treeNode.getId(),treeNode.getFinType());
						List<ContContractType> result = new ArrayList<ContContractType>();
						//对typeList遍历，等到满足ID、type条件的数据，并将其在typeList删除
						for(Iterator<ContContractType> it = typeList.iterator(); it.hasNext();){
							ContContractType type=it.next();
							if(treeNode.getId().equals(type.getTypeParentCd())&&treeNode.getFinType().equals(type.getProjectCd())){
								result.add(type);
								it.remove();
							}
						}
						for (ContContractType item : result) {
							TreeNode childItem = new TreeNode();
							//类型CD
							childItem.setId(item.getTypeCd());
							childItem.setText(item.getTypeName());
							childItem.setParentId(treeNode.getId());
							childItem.setParentName(treeNode.getText());
							childItem.setFinItemCd(item.getTypeCd());
							childItem.setFinType(treeNode.getFinType());
							childItem.setOrderNo(item.getDispOrderNum());
							childItem.setTrueId(item.getContContractTypeId());
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
	public List<TreeNode> getChildrenNodeByAll(List<ContProjectCode> projCodeList) { 
		//得到工程节点
		List<TreeNode> projectNode =new ArrayList<TreeNode>();
		List<ContContractType> contractType = contContractTypeDao.getAll();
		for (ContProjectCode org : projCodeList) {
			TreeNode proItem = new TreeNode();
			//类型CD
			proItem.setId(org.getProjectCd());
			proItem.setFinType(org.getProjectCd());
			proItem.setText(org.getProjectName());
			proItem.setChildren(getProjectChildrenNode(proItem, false,contractType));
			projectNode.add(proItem);
		}
		return projectNode;
	}
	/**
	 * 得到合同类型的地产公司
	 */
	public List<String> getDictProjectCd(String aa){
		WsPlasOrg org = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
		String hql = "select distinct projectCd  from ContContractType";
		Map<String, Object> param = new HashMap<String, Object>();
		List<String> result = contContractTypeDao.find(hql.toString(), param);
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
	 * 搜索父类ID=parentTypeCd的合同项目
	 * 
	 * @param parentItemCd
	 * @return
	 */
	public List<ContContractType> getParentType(String parentTypeCd,String projectCd) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("select a from "+ ContContractType.class.getSimpleName() + " a where 1=1"
				+" and a.typeParentCd=:parentTypeCd "
				);
		if(StringUtils.isNotBlank(projectCd)){
			hql.append(" and a.projectCd=:projectCd");
			param.put("projectCd", projectCd);
		}
		param.put("parentTypeCd", parentTypeCd);
		hql.append(" order by a.projectCd asc,a.dispOrderNum asc");
		List<ContContractType> result = contContractTypeDao.find(hql.toString(), param);
		return result;
	}
	public ContContractType getTypeVo(String typeCd){
		String hql ="select a from "+ContContractType.class.getSimpleName()+" a where a.typeCd ='"+typeCd+"'";
		List<ContContractType> result = contContractTypeDao.find(hql.toString());
		if(result!=null&&result.size()>0)
			return result.get(0);
		else return null;
	}
	/**
	 * 得到合同类型的项目并子项目
	 */
	public List<String> getTypeList(String typeCd,List<String> typeNameList){
		if(typeCd!=null&&!"".equals(typeCd)){
			String hql="select a from "+ContContractType.class.getSimpleName()+" a where a.typeCd ='"+typeCd+"'";
			List<ContContractType> result = contContractTypeDao.find(hql.toString());
			if(result==null||result.size()<=0)
				return typeNameList;
			else{
				ContContractType type=result.get(0);
				typeNameList.add(type.getTypeName());
				if(!type.getTypeParentCd().equals(type.getProjectCd()) ){
					getTypeList(type.getTypeParentCd(),typeNameList);
				}
			}
		}
		return typeNameList;
	}
}

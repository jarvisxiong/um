package com.hhz.ump.dao.cost;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.hhz.ump.entity.cost.CostBudgetAuth;
import com.hhz.ump.entity.cost.CostProjectSection;
import com.hhz.ump.uaap.util.RoleUtil;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;

@Service
@Transactional
public class CostProjectSectionManager extends BaseService<CostProjectSection, String> {
	@Autowired
	private CostProjectSectionDao costProjectSectionDao;
	@Autowired
	private CostBudgetAuthManager costBudgetAuthManager;
	

	public void saveCostProjectSection(CostProjectSection costProjectSection) {
		PowerUtils.setEmptyStr2Null(costProjectSection);
		costProjectSectionDao.save(costProjectSection);
	}

	public void deleteCostProjectSection(String id) {
		costProjectSectionDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostProjectSection, String> getDao() {
		return costProjectSectionDao;
	}
	
	
	/**
	 * 获取用户有权限的项目
	 * 1.若有授权记录,则允许访问授权项目；
	 * 2.若未授权，允许访问本中心项目；
	 * 3.若是预算管理员，允许访问所有项目
	 * @param
	 * @return
	 */
	public List<CostProjectSection> getPermSectionList(){
		
		String uiid = SpringSecurityUtils.getCurrentPlasUser().getUiid();
//		String acctId = SpringSecurityUtils.getCurrentAcctId();
		
		//根据用户所在的职位，冒泡获取相关的机构列表
//		List<WsPlasOrg> bubbleOrgList = PlasCache.getRelPosBubbleOrgCdList(acctId);
//		List<String> bubbleOrgCdList = new ArrayList<String>();
//		for (WsPlasOrg tOrg : bubbleOrgList) {
//			bubbleOrgCdList.add(tOrg.getOrgCd());
//			System.out.println(">>>>>>>>>>>>>" + tOrg.getOrgCd() + "," + tOrg.getOrgName());
//		}

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("authUiid", uiid);
//		values.put("bubbleList", bubbleOrgCdList);
		
		StringBuffer hql = new StringBuffer()
			.append(" select distinct t1 ")
			.append("   from CostProjectSection t1 ")
			//1.授权机构
			.append("  where exists(select 1 from CostBudgetAuth t2 where t2.costProjectSectionId = t1.costProjectSectionId and t2.authUiid = :authUiid )");
			//2.用户所在机构(中心)
//			.append("     or projectCd in (:bubbleList) ");
		
		if(RoleUtil.validateRole(Constants.A_COST_BUD_ADMIN)){
			//预算管理员
			hql.append("  or 1=1 ");
		}
			hql.append(" order by  t1.projectName asc, t1.sequenceNo asc ");//t1.projectCd,
			
		List<CostProjectSection> rtnList = costProjectSectionDao.find(hql.toString(), values);
		if(rtnList == null){
			rtnList = new ArrayList<CostProjectSection>();
		}
		return rtnList;
	}
	
	/**
	 * 获取有有权限的项目
	 * @return
	 */
	public List<String> getSectionIdList(){
		List<String> sectionIdList = new ArrayList<String>();
		for (CostProjectSection tSection : getPermSectionList()) {
			sectionIdList.add(tSection.getCostProjectSectionId());
		}
		
		//若无项目，则默认"none",防止搜索语句报错。
		if(sectionIdList.size() == 0){
			sectionIdList.add("none");
		}
		return sectionIdList;
	}
	
	public CostProjectSection getProjectById(String costProjectSectionId){
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("costProjectSectionId", costProjectSectionId);

		String hql = " from CostProjectSection p where p.costProjectSectionId = :costProjectSectionId";
		List<CostProjectSection> projects = costProjectSectionDao.find(hql, params);

		if (projects != null && projects.size() > 0)
			return projects.get(0);
		
		return null;
	}
	
	/**
	 * 模糊搜索
	 * @param sectionName
	 * @return
	 */
	public List<CostProjectSection> quickSearchSection(String sectionBizCd,String sectionName,String projectName){
		StringBuffer hql = new StringBuffer();
		hql.append(" from CostProjectSection p where ");
		Map<String,Object> params = new HashMap<String,Object>();
		if (StringUtils.isNotBlank(sectionBizCd)) {
			hql.append(" p.sectionBizCd like :sectionBizCd");
			params.put("sectionBizCd", "%"+sectionBizCd.trim()+"%");
		}
		if (StringUtils.isNotBlank(sectionName)) {
			hql.append(" or p.sectionName like :sectionName");
			params.put("sectionName", "%"+sectionName.trim()+"%");
		}
		if (StringUtils.isNotBlank(projectName)) {
			hql.append(" or p.projectName like :projectName");
			params.put("projectName", "%"+projectName.trim()+"%");
		}
		List<CostProjectSection> list = costProjectSectionDao.find(hql.toString(), params);
		if(list != null && list.size() > 0)
			return list;
		return null;
	} 
	 
	public TreePanelNode getSectionTreePanelNode(List<CostProjectSection> sectionList){
		
		//项目公司与项目列表关系
		Map<String, List<CostProjectSection>> projectSectionMap = new HashMap<String, List<CostProjectSection>>();
		//项目公司cd与名称关系
		Map<String,String> projectCdNameMap = new HashMap<String, String>();
		
		List<String> projectCdList = new ArrayList<String>();
		
		String projectCd = null;
		CostProjectSection section = null;
//		System.out.println(" >>>>>>>>>>>>>> 搜索结果: "+sectionList.size());
		if(sectionList!= null){
			for (int i=0; i<sectionList.size(); i++) {
				section = sectionList.get(i);
				if(section != null){
//					System.out.println(">>>>>>>>>>>>>>  "+ i + " -- " + section.getSectionName());
					projectCd = section.getProjectCd();
					//收集項目cd
					if(!projectCdList.contains(projectCd)){
						projectCdList.add(section.getProjectCd());
						projectCdNameMap.put(section.getProjectCd(), section.getProjectName());
					}
					
					//收集項目与标段关系
					if(projectSectionMap.keySet().contains(projectCd)){
						projectSectionMap.get(projectCd).add(section);
					}else{
						List<CostProjectSection> tmpList = new ArrayList<CostProjectSection>();
						tmpList.add(section);
						projectSectionMap.put(projectCd, tmpList);
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
		
		if(projectCdList != null){
			for (String tProjectCd : projectCdList) {
				//构造项目
				tProjectNode = new TreePanelNode();
				tProjectNode.setId(tProjectCd);
				tProjectNode.setNodeType("1");//1-项目公司
				tProjectNode.setEntityId(tProjectCd);
				tProjectNode.setEntityCd(tProjectCd);
				tProjectNode.setChecked(TreePanelUtil.NODE_CHECKED_NONE);
				
				tProjectNode.setText(projectCdNameMap.get(tProjectCd));
				tProjectNode.setEntityName(projectCdNameMap.get(tProjectCd));
				
				//构造子项目
				List<CostProjectSection> tmpList = null;
				if(projectSectionMap.keySet().contains(tProjectCd)){
					tmpList = projectSectionMap.get(tProjectCd);
					if(tmpList != null){
						for (CostProjectSection tSection : tmpList) {
							if( tSection!= null ){
								//构造项目
								tSectionNode = new TreePanelNode();
								tSectionNode.setId(tSection.getCostProjectSectionId());
								tSectionNode.setText(tSection.getBriefName());
//								tSectionNode.setText(tSection.getSectionName());
								tSectionNode.setNodeType("2");//2-标段
								tSectionNode.setEntityId(tSection.getCostProjectSectionId());
								tSectionNode.setEntityBizCd(tSection.getSectionBizCd());
//								tSectionNode.setEntityName(tSection.getSectionName());
								tSectionNode.setEntityName(tSection.getBriefName());
	
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

	/**
	 * 将合同台账（仅地产)的项目期数导入到”月度资金计划项目"中。
	 * 初始化项目
	 * 1.导入一级项目（即没项有分期的目,默认) 不分页, 仅导入有合同。
	 * 2.导入二级项目（即关在一级项目下的项目，如 一期、二期) 不分页 ，不管有没有合同
	 * @throws Exception 
	 */
	public String importSection() throws Exception {


//		--一级:地产(含项目) --code_type: 1-地产 
//		select * from cont_project_code t where t.code_type = 1;
//		--二级:项目
//		select count(*) from cont_contract_type t where t.project_cd in(
//		 select t.project_cd from cont_project_code t where t.code_type = '1'  --1-地产
//		);
//		--合同 (关联字段:cont_type_cd)
//		select a.* from cont_ledger a where a.cont_type_cd in(
//		  select t.type_cd from cont_contract_type t where t.project_cd in(
//		  select t.project_cd from cont_project_code t where t.code_type = '1'  --1-地产
//		     and and not exists( select 1 from cost_project_section t3 where t3.section_cd = t.type_cd)
//		   and not exists( select 1 from Cost_Project_Section t2 where t2.project_Cd  = t.project_Cd )
//		  )
//		);
//		
		
		//检查是否有数据
		//若已存在项目,则不执行，返回提示"已存在项目，不推荐执行!"
//		String hql = " from CostProjectSection t ";
//		long sectionCount = countHqlResult(hql, new HashMap<String,Object>());
//		if(sectionCount  > 0)
//			throw new Exception("已存在项目，不推荐执行");

		//临时变量
		CostProjectSection tmpSection = null;
		
		//1.导入一级项目（即没项有分期的目,默认) 不分页, 仅导入有合同。
		String hql1 = new StringBuffer()
			.append(" select t.project_cd, t.project_name ")
			.append("   from cont_project_code t ")
			.append("  where t.code_type = :codeTypeCd ")
			.append("    and not exists( select 1 from cost_project_section t2 where t2.project_cd  = t.project_cd )")//排除已导入的项目
			.append("    and exists( select 1 from cont_ledger t3 where t3.project_cd = t.project_cd and t3.cont_type_cd is null )")//存在合同
			.toString();
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("codeTypeCd", "1");//1-地产项目
		List list1 = findBySql(hql1, map1);

		String tmpProjectCd = null;
		String tmpProjectName = null;
		String tmpSectionCd = null;
		String tmpBriefName = null;
		
		Object[] tmpObj = null;
		for (int i = 0; i < list1.size(); i++) {
			tmpObj = (Object[])list1.get(i);
			
			tmpProjectCd = getFormat((String)tmpObj[0]);
			tmpProjectName = getFormat((String)tmpObj[1]);
			
			tmpSection = new CostProjectSection();
			tmpSection.setProjectCd(tmpProjectCd);
			tmpSection.setProjectName(tmpProjectName);
			tmpSection.setSectionCd("DEFAULT");
			tmpSection.setSectionBizCd("DEFAULT");
			tmpSection.setBriefName("共用");
			tmpSection.setSectionName(tmpProjectName + "/共用");
			tmpSection.setDefaultFlg("1");//1-默认
			
			System.out.println(
					"projectCd: " + tmpSection.getProjectCd() 
					+ ", projectName: " + tmpSection.getProjectName() 
					+ ", sectionCd: " + tmpSection.getSectionCd() 
					+ ", briefName:" + tmpSection.getBriefName() 
					+ ",sectionName:" + tmpSection.getSectionName()
			);
			saveCostProjectSection(tmpSection);
		}
		String result1 = ("导入一级项目一共有： " + list1.size() + " 个!");
		
		
		//2.导入二级项目（即关在一级项目下的项目，如 一期、二期) 不分页 ，不管有没有合同
		
		String hql2 = new StringBuffer()
		.append("select t.project_cd, t.type_cd, t.type_name")
		.append("  from Cont_Contract_Type t ")
		.append(" where t.project_Cd in( select t2.project_Cd from Cont_Project_Code t2 where t2.code_Type = :codeTypeCd) ")
		.append("   and not exists( select 1 from cost_project_section t3 where t3.section_cd = t.type_cd) ")//排除已导入的数据
		.toString();
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("codeTypeCd", "1");//1-地产项目
		List list2 = findBySql(hql2, map2);
 
		for (int i = 0; i < list2.size(); i++) {
			tmpObj =  (Object[])list2.get(i);
			tmpSection = new CostProjectSection();
			tmpProjectCd = getFormat((String)tmpObj[0]);
			tmpProjectName = getFormat(CodeNameUtil.getDeptNameByCd(tmpProjectCd));
			tmpSectionCd = getFormat((String)tmpObj[1]);
			tmpBriefName = getFormat((String)tmpObj[2]);
			 
			tmpSection.setProjectCd(tmpProjectCd);//project_cd
			tmpSection.setProjectName(tmpProjectName);//项目名称
			tmpSection.setSectionCd(tmpSectionCd);//type_cd
			tmpSection.setSectionBizCd(tmpSectionCd);//type_cd
			tmpSection.setBriefName(tmpBriefName);//type_name
			tmpSection.setSectionName(tmpProjectName + "/" + tmpBriefName);//type_name
			tmpSection.setDefaultFlg("0");//0-非默认
			System.out.println(
					"projectCd: " + tmpSection.getProjectCd() 
					+ ", projectName: " + tmpSection.getProjectName() 
					+ ", sectionCd: " + tmpSection.getSectionCd() 
					+ ", briefName:" + tmpSection.getBriefName() 
					+ ",sectionName:" + tmpSection.getSectionName()
			);
			saveCostProjectSection(tmpSection);
		}
		String result2 = (" 导入二级项目一共有： " + list2.size() + " 个!");
		return result1 + result2;
	}
	private String getFormat(String t){
		return StringUtils.isBlank(t)?"":(t.trim());
	}

	/**
	 * 保存授权
	 * @param sectionId
	 * @param authUiid
	 * @param authUiidOld
	 * @param tRemark
	 */
	public void saveCostProjectSection(String sectionId, String authUiid, String authUiidOld, String tRemark) {
		
		CostProjectSection tSection = getEntity(sectionId);
		tSection.setRemark(tRemark);
		saveCostProjectSection(tSection);
		 
		//删除相关项目的授权
		costBudgetAuthManager.deleteBySection(sectionId);				
		if (StringUtils.isNotBlank(authUiid)) {
			String[] uiids = authUiid.split(";");
			CostBudgetAuth auth = null;
			for (int i = 0; i < uiids.length; i++) {
				auth = new CostBudgetAuth();
				auth.setCostProjectSectionId(sectionId);
				auth.setAuthUiid(uiids[i]);
				auth.setSequenceNo(Long.valueOf(i+1));//序号
				costBudgetAuthManager.saveCostBudgetAuth(auth);
			}
		}
	}
	
}


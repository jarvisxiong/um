/**
 * 
 */
package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasAppManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasOrgMgrRelManager;
import com.hhz.uums.dao.plas.PlasRealPositionManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasApp;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasRoleGroup;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.EasyTree;
import com.hhz.uums.utils.EasyTreeUtil;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.AcctUserVo;
import com.hhz.uums.vo.vw.ConvertVoUtil;
import com.hhz.uums.vo.vw.TreePanelNode;

/**
 * @author huangbj 2009-12-25
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "plas-search-user.action", type = "redirect") })
public class PlasSearchUserAction extends CrudActionSupport<PlasAcct> {

	private static final long serialVersionUID = -5426765202592947483L;

	@Autowired
	private PlasAcctManager plasAcctManager;
	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasAppManager plasAppManager;
	@Autowired
	private PlasRoleManager plasRoleManager;
	 

	@Autowired
	private PlasOrgMgrRelManager plasOrgMgrRelManager;
 
	@Autowired
	private PlasRealPositionManager plasRealPositionManager;
	

	// 这里不能定义page,否则显示不出查询结果;若要默认记录数,必须加上getPage()方法重写.
	private Page<PlasAcct> page = new Page<PlasAcct>(10);
	
	// 前台展示账号+人员列表
	private Page<AcctUserVo> pageVo;
	

	private PlasAcct entity;
	//职位
	private String realPosName;
	//物理机构名称
	private String logicalOrgName;
	private String physicalOrgName;
	private String centerOrgName;
	

 	private String parentOrgNameLogical;
	private String parentOrgNamePhysical;
	
	private Map<String,String> bubblePhysOrgsAndUser;
	private Map<String,String> bubbleLogiOrgsAndUser;


	// 物理机构
	private List<PlasOrg> bubblePhysicalOrgs;

	// 逻辑机构
	private List<PlasOrg> bubbleLogicalOrgs;

	// 机构与管理员映射关系表
	private Map<String, String> orgMangerMap;

	// 机构负责人
	private List<PlasOrg> responseOrgs;

	// 机构管理员
	private List<PlasOrg> managerOrgs;
	
	@Override
	public Page<PlasAcct> getPage() {
		return page;
	}
	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = plasAcctManager.getEntity(getId());
		} else {
			entity = new PlasAcct();
		}
	}

	@Override
	public String list() throws Exception {
	
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("sequenceNo");
			page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.DESC);
		}
		
		page = plasAcctManager.findPage(page, filters);
		//将plas_app的子属性plasRoles置为空。由于hibernate设置为延迟加载，故为空。
		
		pageVo = new Page<AcctUserVo>(page.getPageSize());
		pageVo.setPageNo(page.getPageNo());
		pageVo.setTotalCount(page.getTotalCount());
		pageVo.setOrderBy(page.getOrderBy());
		pageVo.setOrder(page.getOrder());
		
		List<AcctUserVo> tmpList = new ArrayList<AcctUserVo>(page.getResult().size());
		AcctUserVo tmpVo = null;
		List<PlasAcct> tmpAcctList = page.getResult();
		for (int i = 0; i < tmpAcctList.size(); i++) {
			tmpVo = transferUaapUser(tmpAcctList.get(i));
			if(tmpVo != null){
				tmpList.add(tmpVo);
			}
		}
		pageVo.setResult(tmpList);
		JsonUtil.renderJson(pageVo,new String[]{"plasSysPositions","plasUser","plasAccts","plasOrg"});

		return null;
	}
	

	/**
	 * 完成uaapUser对象转换
	 */
	private AcctUserVo transferUaapUser(PlasAcct acct) {
		if (acct == null)
			return null;
		AcctUserVo tmpVo = new AcctUserVo();
		PlasUser tmpUser = acct.getPlasUser(); 
		tmpVo.setUiid(acct.getUiid());
		tmpVo.setAuthenticTypeCd(acct.getAuthenticTypeCd());
		tmpVo.setCustLoginName(acct.getCustLoginName());
		tmpVo.setEasFlg(acct.getEasFlg());
		tmpVo.setEasPasswordSetFlg(acct.getEasPasswordSetFlg());
		tmpVo.setEffectDate(acct.getEffectDate());
		tmpVo.setEmail(acct.getEmail());
		tmpVo.setEmailFlg(acct.getEmailFlg());
		tmpVo.setEmailPasswordSetFlg(acct.getEmailPasswordSetFlg());
		tmpVo.setFailureTimes(acct.getFailureTimes());
		tmpVo.setInvalidDate(acct.getInvalidDate());
		tmpVo.setLastLoginDate(acct.getLastLoginDate());
		tmpVo.setLastLoginIp(acct.getLastLoginIp());
		tmpVo.setLastLogoutDate(acct.getLastLogoutDate());
		tmpVo.setLockedDate(acct.getLockedDate());
		tmpVo.setLoginInPassword(acct.getLoginInPassword());
		tmpVo.setMacAddress(acct.getMacAddress());
		tmpVo.setMacLockedFlg(acct.getMacLockedFlg());
		try{
			if(tmpUser.getPlasOrg() != null){
				tmpVo.setLogicalOrgName(tmpUser.getPlasOrg().getOrgName());
				tmpVo.setPhysicalOrgName(tmpUser.getPlasOrg().getOrgName());
			}
		}catch(Exception e){
			//e.printStackTrace();
		}
		tmpVo.setPlasAcctId(acct.getPlasAcctId());
		tmpVo.setRealPosCd(tmpUser.getRealPosCd());
		tmpVo.setServiceStatusCd(tmpUser.getServiceStatusCd());
		tmpVo.setSexCd(tmpUser.getSexCd());
		tmpVo.setStatusCd(acct.getStatusCd());
		tmpVo.setUpdateDate(tmpUser.getUpdatedDate());
		tmpVo.setUserBizCd(tmpUser.getUserBizCd());
		tmpVo.setUserName(tmpUser.getUserName());
		return tmpVo;
	}


	


	/**
	 * java加载teepanel树
	 */
	public String loadLogicalTreeData() {

		Struts2Utils.renderJson(TreePanelUtil.getTreeNodePanelUserLogicalAll(SpringSecurityUtils.getLoginCode()));
		return null;
	}

	public String loadPhysicalTreeData() {

		Struts2Utils.renderJson(TreePanelUtil.getTreeNodePanelUserPhysicalAll(SpringSecurityUtils.getLoginCode()));
		return null;
	}

 
	public void prepareDetail() throws Exception {
		prepareModel();
	}

	// 查看人员明细(包括基本信息,邮箱信息,角色清单,机构管理员,权限信息)
	public String detail() throws Exception {
		
		//账号信息+基本  entity
		
		//实际职位
		realPosName = plasRealPositionManager.getPositionName(entity.getPlasUser().getRealPosCd());
	 
		//物理机构
		bubblePhysicalOrgs = plasOrgManager.getBubbleOrgsPhysical(entity.getPlasUser().getPlasOrg().getPlasOrgId());
		//逻辑机构
		bubbleLogicalOrgs = plasOrgManager.getBubbleOrgsLogical(entity.getPlasUser().getPlasOrg().getPlasOrgId());
		
		//机构与管理员映射关系表
		orgMangerMap = new HashMap<String, String>();
		buildOrgManagerMap(bubblePhysicalOrgs, orgMangerMap);
		buildOrgManagerMap(bubbleLogicalOrgs, orgMangerMap);

		//负责机构
		responseOrgs = plasOrgManager.getResponseOrgs(entity.getUiid());

		//管理机构
		managerOrgs = plasOrgManager.getMgrOrgList(DictContants.TREE_DIME_LOGICAL,entity.getUiid());
		 
		
		physicalOrgName = entity.getPlasUser().getPlasOrg().getOrgName();
		logicalOrgName  = physicalOrgName;
		for (PlasOrg tmpOrg : bubbleLogicalOrgs) {
			if(DictContants.PLAS_ORG_TYPE_CENTER.equals(tmpOrg.getOrgTypeCd())){
				centerOrgName = tmpOrg.getOrgName();
			}
		}
		
		return "detail";
	}



	/**
	 * 遍历机构,查询对应的管理员列表,存放到映射表中.
	 * 
	 * @param orgList
	 * @param map{orgId:{姓名1(uiid1),姓名2(uiid2),姓名3(uiid3)}}
	 */
	private void buildOrgManagerMap(List<PlasOrg> orgList, Map<String, String> map) {
		
		PlasAcct tmpAcct = null;
		List<PlasAcct> tmpAcctList = null;
		StringBuffer tmpStringBuf = null;
		
		for (int i = 0; i < orgList.size();i++) {
			PlasOrg org = orgList.get(i);
			String tmpOrgId = org.getPlasOrgId();
			
			if(map.containsKey(tmpOrgId)){
				continue;
			}
			tmpAcctList = plasOrgMgrRelManager.getPlasAcctsByOrgId(tmpOrgId);
			tmpStringBuf = new StringBuffer();
			if(tmpAcctList != null){
				for (int j = 0; j < tmpAcctList.size(); j++) {
					tmpAcct = tmpAcctList.get(j);
					if(j!= 0){
						tmpStringBuf.append(",");
					}
					tmpStringBuf.append(tmpAcct.getPlasUser().getUserName()+"("+tmpAcct.getUiid()+")");
				}
			}
			if(StringUtils.isBlank(tmpStringBuf.toString())){
				map.put(tmpOrgId, "-");
			}else{
				map.put(tmpOrgId, tmpStringBuf.toString());
			}
		}
	}


	/**
	 * 快速定位
	 * @throws Exception 
	 */
	public void prepareDetailUiid() throws Exception {
		prepareModel();
	}

	public String detailUiid() {
		return "detail";
	}
	public String toSearch(){
		return SUCCESS;
	}
	public void prepareLoadUserTreeRole() throws Exception{
		prepareModel();
	}
	public void loadUserTreeRole(){
		
		if(null == entity)
			return ;
		
		PlasUser tmpUser = entity.getPlasUser();
		List<PlasRole> roleList = null;
		//加载有效账号
		try{
			if(null!=tmpUser.getPlasAccts()&&tmpUser.getPlasAccts().size()>0){
				roleList = plasAcctManager.getRoles(tmpUser.getPlasAccts().get(0));
				JsonUtil.renderTreeJson(EasyTreeUtil.createRoleGroupTree(roleList));
			}
		}catch(Exception e){
			JsonUtil.renderTreeJson(new EasyTree());
		}
	}
	
	//加载人员角色树
	public String ajaxLoadUserRoleTree(){
		 
		String uiid = Struts2Utils.getParameter("uiid");
		
		List<PlasApp> appList = new ArrayList<PlasApp>();
		List<PlasRoleGroup> groupList = new ArrayList<PlasRoleGroup>();
		
		List<PlasRole> roleList = plasRoleManager.getUserRoles(uiid);
		PlasApp tmpApp = null;
		PlasRoleGroup tmpGroup = null;
		for (PlasRole role : roleList) {
			tmpApp = role.getPlasApp();
			tmpGroup = role.getPlasRoleGroup();
			
			if(!appList.contains(tmpApp)){
				appList.add(tmpApp);
			}
			
			if(!groupList.contains(tmpGroup)){
				groupList.add(tmpGroup);
			}
		}
		
		TreePanelNode node = TreePanelUtil2.buildAppRoleTree(ConvertVoUtil.getVoAppList(appList), ConvertVoUtil.getVoGroupList(groupList), ConvertVoUtil.getVoRoleList(roleList));
		Struts2Utils.renderJson(node);
		return null;
	}
	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	public String getRealPosName() {
		return realPosName;
	}

	public void setRealPosName(String realPosName) {
		this.realPosName = realPosName;
	}

	public Map<String, String> getBubblePhysOrgsAndUser() {
		return bubblePhysOrgsAndUser;
	}

	public void setBubblePhysOrgsAndUser(Map<String, String> bubblePhysOrgsAndUser) {
		this.bubblePhysOrgsAndUser = bubblePhysOrgsAndUser;
	}

	public Map<String, String> getBubbleLogiOrgsAndUser() {
		return bubbleLogiOrgsAndUser;
	}

	public void setBubbleLogiOrgsAndUser(Map<String, String> bubbleLogiOrgsAndUser) {
		this.bubbleLogiOrgsAndUser = bubbleLogiOrgsAndUser;
	}


	public void setParentOrgNameLogical(String parentOrgNameLogical) {
		this.parentOrgNameLogical = parentOrgNameLogical;
	}

	public String getParentOrgNamePhysical() {
		return parentOrgNamePhysical;
	}

	public void setParentOrgNamePhysical(String parentOrgNamePhysical) {
		this.parentOrgNamePhysical = parentOrgNamePhysical;
	}
	public PlasAcct getModel() {
		return entity;
	}

	public PlasAcct getEntity() {
		return entity;
	}
 

	public Map<String, String> getOrgMangerMap() {
		return orgMangerMap;
	}

	public void setOrgMangerMap(Map<String, String> orgMangerMap) {
		this.orgMangerMap = orgMangerMap;
	}

	public List<PlasOrg> getManagerOrgs() {
		return managerOrgs;
	}

	public void setManagerOrgs(List<PlasOrg> managerOrgs) {
		this.managerOrgs = managerOrgs;
	}

	public List<PlasOrg> getResponseOrgs() {
		return responseOrgs;
	}

	public void setResponseOrgs(List<PlasOrg> responseOrgs) {
		this.responseOrgs = responseOrgs;
	}
	public String getParentOrgNameLogical() {
		return parentOrgNameLogical;
	}

	public List<PlasOrg> getBubblePhysicalOrgs() {
		return bubblePhysicalOrgs;
	}
	public void setBubblePhysicalOrgs(List<PlasOrg> bubblePhysicalOrgs) {
		this.bubblePhysicalOrgs = bubblePhysicalOrgs;
	}
	public List<PlasOrg> getBubbleLogicalOrgs() {
		return bubbleLogicalOrgs;
	}
	public void setBubbleLogicalOrgs(List<PlasOrg> bubbleLogicalOrgs) {
		this.bubbleLogicalOrgs = bubbleLogicalOrgs;
	}
	public String getPhysicalOrgName() {
		return physicalOrgName;
	}
	public void setPhysicalOrgName(String physicalOrgName) {
		this.physicalOrgName = physicalOrgName;
	}
	public String getCenterOrgName() {
		return centerOrgName;
	}
	public void setCenterOrgName(String centerOrgName) {
		this.centerOrgName = centerOrgName;
	}
	public String getLogicalOrgName() {
		return logicalOrgName;
	}
	public void setLogicalOrgName(String logicalOrgName) {
		this.logicalOrgName = logicalOrgName;
	}
	
	//导出记录
	public String export(){
//		
//		  SELECT *
//		  FROM (SELECT fn_get_org_path (U.plas_ORG_ID) "机构",U.UIID, U.USER_NAME,U.MOBILE_PHONE
//		          FROM plas.plas_user u, plas.plas_org o, PLAS.PLAS_ACCT a
//		         WHERE     a.STATUS_CD <> '4'
//		               AND U.plas_ORG_ID = O.plas_ORG_ID
//		               AND U.PLAS_USER_ID = A.PLAS_USER_ID
//		               and U.FIXED_PHONE is null
//		               AND U.user_name NOT LIKE '%文件%'
//		               AND U.user_name NOT LIKE '%通讯%'
//		               AND U.uiid NOT IN ('pd_team', 'email_admin')
//		               AND O.ORG_NAME <> '通讯管理员'
//		               AND O.ORG_NAME <> '认证管理')
//		ORDER BY "机构";
		return null;
	}
}

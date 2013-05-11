/**
 * 
 */
package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.dao.app.AppSeqManager;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasDimeOrgRelManager;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasOrgDimeManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasOrgMgrRelManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.plas.PlasDimeOrgRel;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasOrgDime;
import com.hhz.uums.entity.plas.PlasOrgMgrRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.EasyTreeUtil;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.log.LogPlasOrg;
import com.hhz.uums.vo.log.LogUtil;
import com.hhz.uums.vo.vw.OrgDimeRelVo;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.vw.VoOrgMgr;
import com.hhz.uums.web.CrudActionSupport;

@Results({ @Result(name = CrudActionSupport.RELOAD, location = "plas-org.action", type = "redirect") })
public class PlasOrgAction extends CrudActionSupport<PlasOrg> {

	private static final long serialVersionUID = -3445152342227169047L;

	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasRoleManager plasRoleManager;

	@Autowired
	private PlasUserManager plasUserManager;
	@Autowired
	private PlasAcctManager plasAcctManager;
	

	@Autowired
	private PlasDimeOrgRelManager plasOrgDimeRelManager;
	@Autowired
	private PlasOrgDimeManager plasOrgDimeManager;
	
	@Autowired
	private AppParamManager appParamManager;

	@Autowired
	private AppSeqManager appSeqManager;

	@Autowired
	private PlasOrgMgrRelManager plasOrgMgrRelManager;
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	
	@Autowired
	private PlasDimeOrgRelManager plasDimeOrgRel;

	private PlasOrg entity;

	//子孙机构，用户大小
	private Integer iDescCountUser;

	private List<OrgDimeRelVo> voList;

	@Autowired
	private PlasOperateLogManager plasOperateLogManager;

	// 操作日志
	private LogPlasOrg oldOrg;

	// 机构查询条件
	private String filter_LIKES_orgBizCd;
	private String filter_LIKES_orgName;
	private String filter_LIKES_orgShortName;
	private Boolean filter_EQB_activeBl;

	// 机构负责人
	private String orgMgrName;
	private String dimeTypeCd;
	
	
	// 上级机构ID(新增机构) 
	private String appendParentOrgId;
	private String appendParentOrgName;
	
	//系统职位名称
	private String sysPositionName;
	//系统只为机构名
	private String sysPosOrgName;
	private String sysPosOrgMgrId;
	
	
	// 查看不可用机构
	private List<PlasOrg> disableOrgList;

	public PlasOrgAction() {

	}

	public PlasOrg getModel() {
		return entity;
	}

	/**
	 * @param id
	 * @param dimeTypeCd
	 * 
	 */
	@Override
	protected void prepareModel() throws Exception {

		if (StringUtils.isNotBlank(getId())) {
			entity = plasOrgManager.getEntity(getId());
		 
			appendParentOrgId = plasOrgDimeRelManager.getParentOrgId(getId(), dimeTypeCd);
			appendParentOrgName = getParentOrgName(appendParentOrgId);
			
			//列表
			voList = getRelList(getId());
			
			//机构负责人
			if(StringUtils.isNotBlank(entity.getOrgMgrId())){
				PlasUser tmpUser = plasUserManager.getPlasUserByUiid(entity.getOrgMgrId());
				if(tmpUser != null) {
					orgMgrName = tmpUser.getUserName();
				}else{
					orgMgrName = "";
				}
			}
			
			//系统职位名称和系统职位机构名称  add by liuzhihui - 2012-02-06
			if(StringUtils.isNotBlank(entity.getOrgMgrSysPosId())){
				PlasSysPosition tmpSysPos = plasSysPositionManager.getEntity(entity.getOrgMgrSysPosId());
				if(tmpSysPos != null){
					if(tmpSysPos.getPlasAcct() != null){
						if(tmpSysPos.getPlasAcct().getPlasUser() != null){
							sysPosOrgMgrId = tmpSysPos.getPlasAcct().getPlasUser().getUiid();
							//职位名称
							sysPositionName = tmpSysPos.getSysPosName()+"("+tmpSysPos.getPlasAcct().getPlasUser().getUiid()+","+tmpSysPos.getPlasAcct().getPlasUser().getUserName()+")";
						}
					}
					if(tmpSysPos.getPlasOrg() != null){
						//逐级显示系统职位机构名称,以'－'隔开
						if(StringUtils.isNotBlank(tmpSysPos.getPlasOrg().getPlasOrgId()) && !TreePanelUtil2.DEFAULT_ROOT_ORG_ID.equals(tmpSysPos.getPlasOrg().getPlasOrgId())){
							sysPosOrgName = PlasOrgManager.getOrgPath2(plasOrgManager.getBubbleOrgsLogical(tmpSysPos.getPlasOrg().getPlasOrgId()));
						}else{
							sysPosOrgName = "";
						}
					}
				}else{
					sysPositionName = "";
					sysPosOrgName = "";
				}
			}
		} else {
			entity = new PlasOrg();
			entity.setActiveBl(true);// 默认可用
			entity.setVisableFlg(true);// 默认可用
		}
	}
	
	// 查询各个维度对应的机构\上级机构关系
	private List<OrgDimeRelVo> getRelList(String orgId){
		List<OrgDimeRelVo> list = new ArrayList<OrgDimeRelVo>();
		List<Object> tmpList = plasOrgDimeRelManager.getDimeRelList(orgId);
		OrgDimeRelVo tVo = null;
		Object[] tmpArr = null;
		for (Object tmpObj : tmpList) {
			tmpArr = (Object[])tmpObj;
			tVo = new OrgDimeRelVo();
			tVo.setDimeId((String)tmpArr[0]);
			tVo.setDimeCd((String)tmpArr[1]);
			tVo.setDimeName((String)tmpArr[2]);
			tVo.setParentId((String)tmpArr[3]);
			tVo.setParentOrgName((String)tmpArr[4]);
			tVo.setRelId((String)tmpArr[5]);
			tVo.setRelFlag(StringUtils.isNotBlank(tVo.getParentId()));
			list.add(tVo);
		}
		return list;
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
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}

		page = plasOrgManager.findPage(page, filters);
		JsonUtil.renderJson(page, new String[] { "plasSysPositions", "plasUsers", "plasOrgMgrRels" });

		return null;
	}

	/**
	 * 功能:编辑关系
	 * @param id
	 * @param 
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	}

	/**
	 * @param id
	 * @param dimeTypeCd
	 * 
	 */
	@Override
	public String input() {
		return INPUT;
	}
 
	@Override
	public void prepareSave() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			// 记录修改日志使用
			oldOrg = LogUtil.transfer(entity);
		} else {
			// 记录修改日志使用
			oldOrg = new LogPlasOrg();
		}
		prepareModel();
	}

	@Override
	public String save() throws Exception {
		String tmpOldBizCd = Struts2Utils.getParameter("oldOrgBizCd").trim();
		if (!plasOrgManager.isPropertyUnique("orgBizCd", entity.getOrgBizCd(), tmpOldBizCd)) {
			Struts2Utils.renderText("机构业务编号已存在");
			return null;
		}
		boolean isNew = false;
		String orgCd = entity.getOrgCd();
		if (StringUtils.isBlank(orgCd)) {
			isNew = true;
			orgCd = appSeqManager.createNextValue(GlobalConstants.SEQ_ORG_CD).toString();
			entity.setOrgCd(orgCd);
		}
		
		//保存关系
		String tmpParentOrgId = Struts2Utils.getParameter("appendParentOrgId");
		String tmpDimeTypeCd = Struts2Utils.getParameter("dimeTypeCd");
		
		if(StringUtils.isBlank(entity.getPlasOrgId()) && StringUtils.isNotBlank(tmpParentOrgId)){
			plasOrgManager.savePlasOrg(entity);
			PlasDimeOrgRel tmpRel = new PlasDimeOrgRel();
			tmpRel.setPlasOrg(entity);
			tmpRel.setPlasOrgDime(plasOrgDimeManager.getEntityByDimeCd(tmpDimeTypeCd));
			tmpRel.setParentId(tmpParentOrgId);
			tmpRel.setTreeLevel(Long.valueOf(0));
			plasOrgDimeRelManager.savePlasOrgDimeRel(tmpRel);
			//若新增物理机构，则同时新增同样的逻辑机构
			plasOrgDimeRelManager.saveNewDimeOrgRel1(tmpRel);
		}else{
			plasOrgManager.savePlasOrg(entity);
		}
		
		
//		//只处理物理
//		if(TreePanelUtil2.TREE_TYPE_PHYSICAL.equals(dimeTypeCd)){
//			if(isNew){
//				VoOrg org = new VoOrg();
//				CoremailServiceClient.savePlasOrg(entity,tmpParentOrgId);
//			}else{
//				
//			}
//		}
		
		
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
		String desc = LogUtil.getOperateOrg(isNew, oldOrg, entity);
		if (StringUtils.isNotBlank(desc)) {
			plasOperateLogManager.savePlasOperateLog(
					operUiid,
					operUserName,
					OperConst.ORG,
					isNew?OperConst.ADD:OperConst.EDT,
					new StringBuffer("[").append(entity.getOrgCd()).append(",").append(entity.getOrgName())
							.append("]保存机构信息成功!").append(desc).toString());
		}
		
		
		
		Struts2Utils.renderText("success,"+ entity.getPlasOrgId());
		return null;
	}

	/**
	 * ajax查询下属员工数
	 * @param orgId  plasOrgId
	 * @param dimeCd 维度cd
	 * 
	 * 
	 * @return
	 */
	public String getDescendantUsers() {
		String tmpOrgId = Struts2Utils.getParameter("orgId").trim();
		String tmpDimeCd = Struts2Utils.getParameter("dimeCd").trim();

		String tmpUiid = SpringSecurityUtils.getCurUiid();
		boolean bAllUserFlg = plasRoleManager.validateViewDisableObj(tmpUiid);
		if(StringUtils.isNotBlank(tmpOrgId)){
			if(TreePanelUtil2.DEFAULT_ROOT_ORG_ID.equals(tmpOrgId)){
				iDescCountUser = plasOrgManager.getDescendantUsers(TreePanelUtil2.DEFAULT_ROOT_ORG_ID, tmpDimeCd, bAllUserFlg).size();
				Struts2Utils.renderText(String.valueOf(iDescCountUser));
			}else{
				PlasOrg tmpOrg = plasOrgManager.getEntity(tmpOrgId);
				if(tmpOrg == null){
					Struts2Utils.renderText("机构不存在!id["+ tmpOrgId +"]");
				}else{
					List<PlasUser> list = plasOrgManager.getDescendantUsers(tmpOrg.getPlasOrgId(), tmpDimeCd, bAllUserFlg);
					iDescCountUser = list.size();
					Struts2Utils.renderText(String.valueOf(iDescCountUser));
				}
			}
		}else{
			Struts2Utils.renderText("");
		}
		return null;
	}
 
	@Override
	public String delete() throws Exception {

		String tmpOrgId = Struts2Utils.getParameter("orgId").trim();
 
		if(StringUtils.isBlank(tmpOrgId) || StringUtils.isNotBlank(validateDeleteOrg(tmpOrgId))){
			Struts2Utils.renderText("failure");
			return null;
		}
		// 失效机构
		plasOrgManager.disableOrg(tmpOrgId);

		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	/**
	 * java加载teepanel树 1.获取当前用户有权限的机构 2.若有,都挂在根节点下;否则,默认全部机构
	 */

	// 逻辑视图
	public String loadLogicalTreeData() {
		String uiid = SpringSecurityUtils.getLoginCode();
		Struts2Utils.renderJson(TreePanelUtil.getTreeNodePanelOrgLogical(uiid));
		return null;
	}


	/**
	 * 支持使用Jquery.validate Ajax
	 * 
	 * 检验[机构业务编号]不能重复
	 */
	public String isOrgBizCdExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String newOrgBizCd = request.getParameter("orgBizCd").trim();
		String oldOrgBizCd = request.getParameter("oldOrgBizCd").trim();

		if (plasOrgManager.isPropertyUnique("orgBizCd", newOrgBizCd, oldOrgBizCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}
 
	// 根据机构代码获取机构名称
	public String loadParentOrgName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String orgCd = request.getParameter("orgCd").trim();

		// 注意:根节点在机构表中不存在
		String rootOrgCd = appParamManager.getAppOrgTreeRootCd();
		if (rootOrgCd.equals(orgCd)) {
			Struts2Utils.renderText(appParamManager.getAppOrgTreeRootName());
			return null;
		}

		if (StringUtils.isBlank(orgCd)) {
			Struts2Utils.renderText("");
		} else {
			PlasOrg org = plasOrgManager.getPlasOrgByCd(orgCd);
			Struts2Utils.renderText(org.getOrgName());
		}
		return null;
	}

	// 根据用户代码(UIID)获取用户名称
	public String loadUserName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uiid = request.getParameter("uiid").trim();
		if (StringUtils.isBlank(uiid)) {
			Struts2Utils.renderText("");
		} else {
			PlasUser user = plasUserManager.getPlasUserByUiid(uiid);
			if (user != null) {
				Struts2Utils.renderText(user.getUserName());
			} else {
				Struts2Utils.renderText(uiid + "[无该用户]");
			}
		}
		return null;
	} 

	public LogPlasOrg getOldOrg() {
		return oldOrg;
	}

	public void setOldOrg(LogPlasOrg oldOrg) {
		this.oldOrg = oldOrg;
	}

	/**
	 * 物理机构树
	 */
	public void loadOrgTreePhysical() {
		Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanelOrgPhysical(SpringSecurityUtils.getCurUiid()));
	}

	/**
	 * 逻辑机构树
	 */
	public void loadOrgTreeLogical() {
		Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanelOrgLogical(SpringSecurityUtils.getCurUiid()));
	}
	
	/**
	 * 加载系统职位树
	 * @return
	 */
	public String loadPositionTree() throws Exception{
		TreePanelNode node = TreePanelUtil2.getPosTreeNodePanel(
				TreePanelUtil2.TREE_TYPE_LOGICAL, 
				SpringSecurityUtils.getCurUiid(), 
				true,
				null, 
				false, 
				false,false);
		Struts2Utils.renderJson(node);
		return null;
	}
	
	/**
	 * 根据系统职位ID得到
	 * 职位负责人的uiid+userName和
	 * 系统职位机构名称(sysPosOrgName)
	 * @return
	 * @throws Exception
	 */
	public String getSysPosition() throws Exception{
		PlasSysPosition tmpSysPos = plasSysPositionManager.getEntity(getId());
		String sysPosOrgName = "";
		String uiid = "";
		String userName = "";
		if(tmpSysPos != null){
			//逐级显示系统职位机构名称,以'－'隔开
			if(StringUtils.isNotBlank(tmpSysPos.getPlasOrg().getPlasOrgId()) && !TreePanelUtil2.DEFAULT_ROOT_ORG_ID.equals(tmpSysPos.getPlasOrg().getPlasOrgId())){
				sysPosOrgName = PlasOrgManager.getOrgPath2(plasOrgManager.getBubbleOrgsLogical(tmpSysPos.getPlasOrg().getPlasOrgId()));
			}
			if(tmpSysPos.getPlasAcct()!=null){
				if(tmpSysPos.getPlasAcct().getPlasUser()!=null){
					uiid = tmpSysPos.getPlasAcct().getPlasUser().getUiid();
					userName = tmpSysPos.getPlasAcct().getPlasUser().getUserName();
				}
			}
			String str = uiid+","+userName+","+sysPosOrgName;
			Struts2Utils.renderHtml(str);
		}
		return null;
	}
	
	/**
	 * 加载机构json视图
	 * 
	 * @param dictTypeCd
	 * @return
	 */
	public String loadOrgTreeRootJson() {
		String dictTypeCd = Struts2Utils.getParameter("dimeTypeCd");
		if (StringUtils.isBlank(dictTypeCd)) {
			JsonUtil.renderFailure("请选择视图类型!");
		} else {
			JsonUtil.renderTreeJson(EasyTreeUtil.createOrgTree(dictTypeCd));
		}
		return null;
	}
	
	/**
	 * ajax 加载特定类型的机构树
	 * @return
	 */
	public String loadOrgTree(){
		String tmpTypeCd = Struts2Utils.getParameter("dimeTypeCd").trim();
		if(StringUtils.isBlank(tmpTypeCd))
			return null;
		else{
			if(DictContants.TREE_DIME_PHYSICAL.equals(tmpTypeCd)){
				loadOrgTreePhysical();
			}
			else if(DictContants.TREE_DIME_LOGICAL.equals(tmpTypeCd)){
				loadOrgTreeLogical();
			}
		}
		return null;
	}

	/**
	 * 查询某个机构名称
	 * 
	 * @param orgCd
	 * 
	 * @return
	 */
	public String loadOrgNameByOrgCd() {
		String orgCd = Struts2Utils.getParameter("orgCd").trim();
		if (StringUtils.isBlank(orgCd)) {
			JsonUtil.renderSuccess("");
		} else {
			PlasOrg org = plasOrgManager.getPlasOrgByCd(orgCd);
			JsonUtil.renderSuccess(org.getOrgName() + "[" + org.getOrgBizCd() + "]");
		}

		// 注意:根节点在机构表中不存在
		String rootOrgCd = appParamManager.getAppOrgTreeRootCd();
		if (rootOrgCd.equals(orgCd)) {
			JsonUtil.renderSuccess(appParamManager.getAppOrgTreeRootName());
			return null;
		}
		return null;
	}

	/**
	 * 
	 * Description:加载机构用户树 author:jiaoxiaofeng 2011-4-12 void
	 */
	public void getUserTree() {

		JsonUtil.renderTreeJson(EasyTreeUtil.createOrgUserTree(DictContants.TREE_DIME_PHYSICAL, null));
	}

	public String getFilter_LIKES_orgBizCd() {
		return filter_LIKES_orgBizCd;
	}

	public void setFilter_LIKES_orgBizCd(String filter_LIKES_orgBizCd) {
		this.filter_LIKES_orgBizCd = filter_LIKES_orgBizCd;
	}

	public String getFilter_LIKES_orgName() {
		return filter_LIKES_orgName;
	}

	public void setFilter_LIKES_orgName(String filter_LIKES_orgName) {
		this.filter_LIKES_orgName = filter_LIKES_orgName;
	}

	public String getFilter_LIKES_orgShortName() {
		return filter_LIKES_orgShortName;
	}

	public void setFilter_LIKES_orgShortName(String filter_LIKES_orgShortName) {
		this.filter_LIKES_orgShortName = filter_LIKES_orgShortName;
	}

	public Boolean getFilter_EQB_activeBl() {
		return filter_EQB_activeBl;
	}

	public void setFilter_EQB_activeBl(Boolean filter_EQB_activeBl) {
		this.filter_EQB_activeBl = filter_EQB_activeBl;
	}
 
	public String getOrgMgrName() {
		return orgMgrName;
	}

	public void setOrgMgrName(String orgMgrName) {
		this.orgMgrName = orgMgrName;
	}

	public String getDimeTypeCd() {
		return dimeTypeCd;
	}

	public void setDimeTypeCd(String dimeTypeCd) {
		this.dimeTypeCd = dimeTypeCd;
	}

	public List<OrgDimeRelVo> getVoList() {
		return voList;
	}

	public void setVoList(List<OrgDimeRelVo> voList) {
		this.voList = voList;
	}

	public String getFindOrgList() throws Exception {
		String value = Struts2Utils.getParameter("value");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("LIKES_orgBizCd_OR_orgName", value));
		page.setPageSize(15);
		page = plasOrgManager.findPage(page, filters);
		List<Object> data = new ArrayList<Object>();
		Map<String, String> map = null;
		for (PlasOrg tmp : page.getResult()) {
			map = new HashMap<String, String>();
			map.put("plasOrgId", tmp.getPlasOrgId());
			map.put("orgBizCd", tmp.getOrgBizCd());
			map.put("orgName", tmp.getOrgName());
			data.add(map);
		}
		Struts2Utils.renderJson(data);
		return null;
	}

	/**
	 * 生效机构
	 * 
	 * @param uaapOrgId
	 * @return
	 */
	public String enableOrg() {
		String uaapOrgId = Struts2Utils.getParameter("plasOrgId");
		if (StringUtils.isBlank(uaapOrgId)) {
			Struts2Utils.renderText(" 机构为空");
			return null;
		}

		PlasOrg tmpOrg = plasOrgManager.getEntity(uaapOrgId);
		if (tmpOrg == null) {
			Struts2Utils.renderText(" 机构未找到(" + uaapOrgId + ")!");
			return null;
		}

		tmpOrg.setActiveBl(new Boolean(true));
		plasOrgManager.savePlasOrg(tmpOrg);
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 校验机构是否允许删除
	 * 
	 * @param uaapOrgId
	 * @return
	 */
	public String validateDeleteOrg() {

		String tmpOrgId = Struts2Utils.getParameter("orgId");
		String tip = validateDeleteOrg(tmpOrgId);
		if (StringUtils.isBlank(tip)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText(tip);
		}
		return null;
	}

	/**
	 * 校验是否允许删除机构
	 * 
	 * @param orgId
	 * @return 空,则允许删除;不空,则为提示
	 */
	public String validateDeleteOrg(String orgId) {
		if (StringUtils.isBlank(orgId))
			return "机构为空";
		
		List<String> orgIdList = new ArrayList<String>();
		orgIdList.add(orgId);
		
		List<PlasOrg> tmpOrgList = plasOrgManager.getDirectOrgs(orgIdList, DictContants.TREE_DIME_PHYSICAL);
		if (tmpOrgList.size() == 0) {
			List<PlasUser> list = plasOrgManager.getDirectUsers(StringUtils.isBlank(orgId)? TreePanelUtil2.DEFAULT_ROOT_ORG_ID:orgId,false);
			if (list.size() == 0)
				return null;
			else{
				StringBuffer sb = new StringBuffer();
				for (PlasUser t : list) {
					sb.append(t.getUserName()).append("(").append(t.getUiid()).append("),");
				}
				return "存在下属未注销用户有:"+sb.toString();
			}
		} else
			return "存在下属未注销机构";
	}
	/**
	 * 
	 * Description:快速查询机构 
	 * @param value 模糊匹配值
	 * @param dimeTypeCd 维度
	 * @param isAllFlag 是否所有节点
	 * 
	 */
	public void quickSearchOrgList() {

		String tmpName = Struts2Utils.getParameter("value").trim().toLowerCase();
		String tmpDimeTypeCd = Struts2Utils.getParameter("dimeCd");
		String tmpAllFlg = Struts2Utils.getParameter("isAllFlag");
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		List<PlasOrg> result = plasOrgManager.getFindOrgList(tmpName,tmpDimeTypeCd, "10",SpringSecurityUtils.getCurUiid(),"on".equals(tmpAllFlg));
		// 增加根节点
		Map<String, String> map = null;
		for (PlasOrg org : result) {
			map = new HashMap<String, String>();
			map.put("plasOrgId", org.getPlasOrgId());
			map.put("orgBizCd", StringUtils.isBlank(org.getOrgBizCd())?"":org.getOrgBizCd());
			map.put("orgName", StringUtils.isBlank(org.getOrgName())?"":org.getOrgName());
			map.put("orgMgrId", StringUtils.isBlank(org.getOrgMgrId())?"":org.getOrgMgrId());
			map.put("activeFlgName", (org.getActiveBl()!= null && org.getActiveBl().booleanValue())?"在用":"弃用");
			map.put("centerOrgName", plasOrgManager.getCenterOrgNameByOrgId(org.getPlasOrgId()));
			tmpList.add(map);
		}
 
		Struts2Utils.renderJson(tmpList);
	}
 
	
	public String getAppendParentOrgId() {
		return appendParentOrgId;
	}

	public void setAppendParentOrgId(String appendParentOrgId) {
		this.appendParentOrgId = appendParentOrgId;
	}
 
	public String getAppendParentOrgName() {
		return appendParentOrgName;
	}

	public void setAppendParentOrgName(String appendParentOrgName) {
		this.appendParentOrgName = appendParentOrgName;
	}

	public String getSysPositionName() {
		return sysPositionName;
	}

	public void setSysPositionName(String sysPositionName) {
		this.sysPositionName = sysPositionName;
	}

	/**
	 * 新增机构
	 * @param appendParentOrgId
	 * 
	 * @return
	 * @throws Exception 
	 */
	public void prepareAppendOrg() throws Exception{
		prepareModel();
	}
	public String appendOrg(){
		appendParentOrgName = getParentOrgName(appendParentOrgId);
		return INPUT;
	}
	
	/**
	 * 获取上级机构名称
	 * @param orgId
	 * @return
	 */
	private String getParentOrgName(String orgId){
		
		if(StringUtils.isNotBlank(orgId)){
			if(TreePanelUtil2.DEFAULT_ROOT_ORG_ID.equals(orgId)
					|| StringUtils.isBlank(orgId))
				return TreePanelUtil2.DEFAULT_ROOT_ORG_NAME;
			else{
				PlasOrg org = plasOrgManager.getEntity(orgId);
				if(org != null)
					return org.getOrgName();
				return TreePanelUtil2.DEFAULT_ROOT_ORG_NAME;
			}
		} else
			return TreePanelUtil2.DEFAULT_ROOT_ORG_NAME;
	}

	/**
	 * ajax 获取特定维度机构关联
	 * @param orgId
	 * @param relParentId
	 * @param dimeId
	 * @return
	 */
	public String saveDimeOrgRel(){

		String tmpOrgId = Struts2Utils.getParameter("orgId").trim();
		String tmpParentId = Struts2Utils.getParameter("relParentId").trim();
		String tmpDimeId = Struts2Utils.getParameter("dimeId").trim();
		
		PlasOrg tmpOrg = plasOrgManager.getEntity(tmpOrgId);
		PlasOrgDime tmpDime = plasOrgDimeManager.getEntity(tmpDimeId);
		//保存新关系
		PlasDimeOrgRel rel = new PlasDimeOrgRel();
		rel.setPlasOrg(tmpOrg);
		rel.setPlasOrgDime(tmpDime);
		rel.setParentId(tmpParentId);
		
		plasOrgDimeRelManager.saveNewDimeOrgRel(rel);
		
//		//1-物理视图
//		if("1".equals(tmpDime.getDimeCd())){
//			CoremailServiceClient.editUnit(orgCd)
//		}
		
		Struts2Utils.renderText("success");
		
		return null;
	}

	/**
	 * ajax 删除特定维度机构关联
	 * @param orgId
	 * @param relParentId
	 * @param dimeId
	 * @param relId
	 * @return
	 */
	public String removeOrgRel(){

		String tmpOrgId = Struts2Utils.getParameter("orgId").trim();
		String tmpParentId = Struts2Utils.getParameter("relParentId").trim();
		String tmpDimeId = Struts2Utils.getParameter("dimeId").trim();
		String relId = Struts2Utils.getParameter("relId").trim();

		plasOrgDimeRelManager.deleteOrgRel(tmpOrgId, tmpParentId, tmpDimeId, relId);
		Struts2Utils.renderText("success");
		
		return null;
	}
	/**
	 * 设置机构管理员
	 */
	public String toOrgMgr(){
		return "mgr";
	}
	/**
	 * 保存授权机构管理员信息
	 * @param id 机构ID
	 * @param addPosAOrgIds
	 * @param delPosAOrgIds
	 */
	public void saveBatchOrgMgr(){
		PlasOrg tmpOrg = plasOrgManager.getEntity(getId());
		String addPosAOrgIds = Struts2Utils.getParameter("addPosAOrgIds").trim();
		String delPosAOrgIds = Struts2Utils.getParameter("delPosAOrgIds").trim();
		plasOrgMgrRelManager.saveBatchOrgMgrs(tmpOrg, addPosAOrgIds, delPosAOrgIds);
		Struts2Utils.renderText("success");
	}
	/**
	 * 获取机构与系统职位树
	 * @param id 机构ID
	 */
	public void loadSysPosPhysicalTree(){
		List<PlasSysPosition> sysPosList = plasOrgMgrRelManager.getPlasSysPositionByOrgId(getId());
		Struts2Utils.renderJson(TreePanelUtil.createOrgPosition(DictContants.TREE_DIME_PHYSICAL,sysPosList,true));

//		List<VoSysPosition> voPosList = plasSysPositionManager.searchPositionListByFlg(true);
//		Struts2Utils.renderJson(TreePanelUtil2.getPosTreeNodePanel(
//				TreePanelUtil2.TREE_TYPE_PHYSICAL, 
//				SpringSecurityUtils.getCurUiid(), 
//				true,
//				voPosList, 
//				false, true));
	} 
	public void loadSysPosLogicalTree(){
		List<PlasSysPosition> sysPosList = plasOrgMgrRelManager.getPlasSysPositionByOrgId(getId());
		Struts2Utils.renderJson(TreePanelUtil.createOrgPosition(DictContants.TREE_DIME_LOGICAL,sysPosList,true));
		
//		List<VoSysPosition> voPosList = plasSysPositionManager.searchPositionListByFlg(true);
//		Struts2Utils.renderJson(TreePanelUtil2.getPosTreeNodePanel(
//				TreePanelUtil2.TREE_TYPE_PHYSICAL, 
//				SpringSecurityUtils.getCurUiid(), 
//				true,
//				voPosList, 
//				false, true));
	} 
	/**
	 * 获取机构管理员列表
	 */
	public void loadSysPosList(){
		Page<VoOrgMgr> page = new Page<VoOrgMgr>(30);
		if (StringUtils.isNotBlank(getId())) {
			entity = plasOrgManager.getEntity(getId());
		}else {
			JsonUtil.renderJson(page, new String[] {});
			return ;
		}
		List<VoOrgMgr> result = new ArrayList<VoOrgMgr>();
		VoOrgMgr tmp = null;
		PlasOrg tmpOrg = null;
		PlasSysPosition tmpPos = null;
		
		for(PlasOrgMgrRel orgMgrRel : entity.getPlasOrgMgrRels()){
			tmp = new VoOrgMgr();
			tmpOrg = orgMgrRel.getPlasOrg();
			tmpPos = orgMgrRel.getPlasSysPosition();
			
			tmp.setOrgBizCd(tmpOrg.getOrgBizCd());
			tmp.setOrgName(tmpOrg.getOrgName());
			tmp.setPlasOrgId(tmpOrg.getPlasOrgId());
			
			tmp.setPlasSysPositionId(tmpPos.getPlasSysPositionId());
			tmp.setSysPosName(tmpPos.getSysPosName());
			tmp.setSysPosCd(tmpPos.getSysPosCd());
			
			//这里可能nullpointer
			if(tmpPos.getPlasAcct() != null){
				tmp.setUiid(tmpPos.getPlasAcct().getUiid());
			}
			result.add(tmp);
		}
		
		
		
		page.setResult(result);
		page.setTotalCount(result.size());
		JsonUtil.renderJson(page, new String[] {});
	}
	/**
	 * 增加机构管理员
	 */
	public void saveOrgMgr(){
		String sysPosCd = Struts2Utils.getParameter("sysPosCd");
		entity = plasOrgManager.getEntity(getId());
		PlasSysPosition sysPos = plasSysPositionManager.getEntityBySysPosCd(sysPosCd);
		plasOrgMgrRelManager.saveBatchOrgMgrs(entity,sysPos.getPlasSysPositionId(),"");
		Struts2Utils.renderText("success");
		
	}
	/**
	 * 删除机构管理员
	 */
	public void deleteOrgMgr(){
		
		String orgId = Struts2Utils.getParameter("orgId");
		String posId = Struts2Utils.getParameter("posId");
		

		PlasOrg org = plasOrgManager.getEntity(orgId);
		PlasSysPosition pos = plasSysPositionManager.getEntity(posId);
		
		plasOrgMgrRelManager.deleteRel(org, pos);
//		
//		entity = plasOrgManager.getPlasOrgByCd(orgCd);
//		PlasSysPosition sysPos = plasSysPositionManager.getEntityBySysPosCd(orgId);
//		plasOrgMgrRelManager.saveBatchOrgMgrs(entity,"",sysPos.getPlasSysPositionId());
		Struts2Utils.renderText("success");
	}
	
	/**
	 * 查看不可用机构列表
	 * @return
	 */
	public String disableOrgList(){
		disableOrgList = plasOrgManager.getDisableOrgList();
		return "disableOrgList";
	}

	public List<PlasOrg> getDisableOrgList() {
		return disableOrgList;
	}

	public void setDisableOrgList(List<PlasOrg> disableOrgList) {
		this.disableOrgList = disableOrgList;
	}

	public String getSysPosOrgName() {
		return sysPosOrgName;
	}

	public void setSysPosOrgName(String sysPosOrgName) {
		this.sysPosOrgName = sysPosOrgName;
	}

	public String getSysPosOrgMgrId() {
		return sysPosOrgMgrId;
	}

	public void setSysPosOrgMgrId(String sysPosOrgMgrId) {
		this.sysPosOrgMgrId = sysPosOrgMgrId;
	}

	/**
	 * 保存是否显示,若有占职位的人员,则不通过。
	 * @param positionId
	 * @param visableFlg
	 * @return
	 */
	public String saveVisableFlg(){
		
		String plasOrgId = Struts2Utils.getParameter("plasOrgId");
		String visableFlg = Struts2Utils.getParameter("visableFlg");
		
		if(StringUtils.isBlank(plasOrgId)){
			Struts2Utils.renderText("plasOrgId is null! ");
			return null;
		}
		
		Boolean tmpVisableFlg = new Boolean("true".equals(visableFlg));
		if(!tmpVisableFlg){
			
			List<String> orgIds = new ArrayList<String>();
			orgIds.add(plasOrgId);
			List<PlasOrg> orgList = plasOrgManager.getDescendantOrgs(orgIds, DictContants.TREE_DIME_PHYSICAL);
			for (PlasOrg t : orgList) {
				orgIds.add(t.getPlasOrgId());
			}
			//检查下属是否有正常账号
			long enableCount =  plasAcctManager.getEnableAcctCount(orgIds);
			if(enableCount > 0){
				Struts2Utils.renderText("failure: 存在"+enableCount+"个 占有效职位的账号!");
				return null;
			}
		}
		
		entity = plasOrgManager.getEntity(plasOrgId);
		entity.setVisableFlg(tmpVisableFlg);//checkbox 
		plasOrgManager.savePlasOrg(entity);
		
		Struts2Utils.renderText("success");
		
		return null;
	}
	
}


package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.app.AppSeqManager;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasRolePackManager;
import com.hhz.uums.dao.plas.PlasRolePackPosRelManager;
import com.hhz.uums.dao.plas.PlasSysPosRoleRelManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasRolePack;
import com.hhz.uums.entity.plas.PlasRolePackPosRel;
import com.hhz.uums.entity.plas.PlasSysPosRoleRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.utils.Util;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.vw.VoRole;
import com.hhz.uums.vo.vw.VoRolePack;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.web.CrudActionSupport;

/**
 * ------------------------------------------------------- date || author ||
 * Description || 2011-2-19 || jiaoxiaofeng || create
 * ||PlasSysPositionAction.java Description::系统职位管理
 * -------------------------------------------------------
 **/
public class PlasSysPositionAction extends CrudActionSupport<PlasSysPosition> {

	private static final long serialVersionUID = -8284864844072321760L;
	private static Log log = LogFactory.getLog(PlasSysPositionAction.class);

	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	@Autowired
	private PlasRoleManager plasRoleManager;
	@Autowired
	private PlasSysPosRoleRelManager plasSysPosRoleRelManager;

	
	
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	
	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasAcctManager acctManager;
	@Autowired
	private PlasRoleManager roleManager;
	@Autowired
	private PlasUserManager userManager;

	@Autowired
	private PlasSysPosRoleRelManager sysPosRoleRelManager;

	@Autowired
	private AppSeqManager appSeqManager;

	//打包角色
	@Autowired
	private PlasRolePackManager plasRolePackManager;
	@Autowired
	private PlasRolePackPosRelManager plasRolePackPosRelManager;
	private List<VoRolePack> plasRolePackList = new ArrayList<VoRolePack>();
	private String isAllRoleFlg;
	private String[] oldIds;
	
	private PlasSysPosition entity;
	private String plasOrgId;
	private String plasSysPositionId;
	
	private String quickSearchFieldId;//plasAcctId
	private String quickSearchField;//userName(状态)
	private String quickSearchPosId;//plasSysPositionId
	private String quickSearchPos;//sysPosNameName
	
	private String roleNames;
	private String orgNames;
	
	//职位所属机构名称
	private String sysPosOrgName;
	
	
	//某职位对应的角色列表
	private List<VoRole> relRoleList;
	
	
	@Override
	public PlasSysPosition getModel() {
		return entity;
	}

	public String getPlasOrgId() {
		return plasOrgId;
	}

	public void setPlasOrgId(String plasOrgId) {
		this.plasOrgId = plasOrgId;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public void prepareInput() throws Exception{
		prepareModel();
	}
	@Override
	public String input() throws Exception {
		return INPUT;
	}

	public void prepareView() throws Exception{
		prepareModel();
	}
	public String view() throws Exception {
		return "view";
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = plasSysPositionManager.getEntity(getId());

			//根据职位查询角色列表
			isAllRoleFlg = "true"; //查看角色-默认勾选(查询所有，包含角色包下的角色)
			relRoleList = sysPosRoleRelManager.getAllRoleListByPosId(getId(),"true".equals(isAllRoleFlg));
			
			//初始化
			PlasAcct tmpAcct = entity.getPlasAcct();
			if(tmpAcct != null){
				quickSearchFieldId = tmpAcct.getPlasAcctId();
				quickSearchField = new StringBuffer()
									.append(tmpAcct.getPlasUser().getUserName())
									.append("[").append(tmpAcct.getUiid()).append("]")
									.append("(").append(tmpAcct.getStatusCd()).append("-").append(appDictTypeManager.getDictNameByCd(DictContants.PLAS_USER_STATUS, tmpAcct.getStatusCd())).append(")")
									.toString();
			}
			
			//逐级显示机构名称,以'/'隔开
			try{
				PlasOrg tmpOrg = entity.getPlasOrg();
				if(tmpOrg!= null){
					String curOrgId = tmpOrg.getPlasOrgId();
					if(tmpOrg != null && StringUtils.isNotBlank(curOrgId)){
						sysPosOrgName = tmpOrg.getOrgName()+"(" + tmpOrg.getOrgCd()+")";
						if(StringUtils.isNotBlank(curOrgId) && !TreePanelUtil2.DEFAULT_ROOT_ORG_ID.equals(curOrgId)){
							sysPosOrgName = PlasOrgManager.getOrgPath(plasOrgManager.getBubbleOrgsLogical(curOrgId));
						}
					}
				}
			}catch(Exception e){
				log.error("获取职位对应的机构名称异常!", e);
			}
			

//			StringBuffer sb1 = new StringBuffer();
//			for (PlasSysPosRoleRel tmpRoleRel : entity.getPlasSysPosRoleRels()) {
//				sb1.append("[" +tmpRoleRel.getPlasRole().getRoleCd()  + "] " +tmpRoleRel.getPlasRole().getRoleName() + "<br/>");
//			}
//			roleNames = sb1.toString();
//			
//			StringBuffer sb2 = new StringBuffer();
//			for (PlasOrgMgrRel tmpOrgRel : entity.getPlasOrgMgrRels()) {
//				sb2.append("[" +tmpOrgRel.getPlasOrg().getOrgCd()  + "] " +tmpOrgRel.getPlasOrg().getOrgName() + "<br/>");
//			}
//			orgNames = sb2.toString();
		} else {
			String orgId = Struts2Utils.getParameter("plasOrgId");
			PlasOrg org = plasOrgManager.getEntity(orgId);
			entity = new PlasSysPosition();
			entity.setPlasOrg(org);
			entity.setActiveBl(true);//是否可用
			entity.setVisableFlg(true);//是否可见
			
			sysPosOrgName = org.getOrgName();
		}
	}
	
	/**
	 * 根据职位查询角色列表
	 * @param plasSysPositionId
	 * @param isAllRoleFlg
	 * @return
	 */
	public String viewRelRoleList(){
		if(StringUtils.isNotBlank(plasSysPositionId) && StringUtils.isNotBlank(isAllRoleFlg)){
			// isAllRoleFlg==true角色包下的角色也查询
			relRoleList = sysPosRoleRelManager.getAllRoleListByPosId(plasSysPositionId,"true".equals(isAllRoleFlg));
		}
		return "relRoleList";
	}
	
	
	/**
	 * 根据职位系统职位ID获得打包角色列表
	 * 
	 * @param plasSysPositionId
	 * @return
	 */
	public String viewRolePackList(){
		if(StringUtils.isNotBlank(plasSysPositionId)){
			List<String> tmpList = new ArrayList<String>();
			List<PlasRolePackPosRel> plasRolePackPosRelList = plasRolePackPosRelManager.getRolePackPosRelBySysPositionId(plasSysPositionId);
			List<PlasRolePack> rolePackList = plasRolePackManager.getPlasRolePack();
			VoRolePack voRolePack = null;
			if(plasRolePackPosRelList != null && plasRolePackPosRelList.size()>0){
				for (PlasRolePackPosRel plasRolePackPosRel : plasRolePackPosRelList) {
					voRolePack = new VoRolePack();
					voRolePack.setPlasRolePackId(plasRolePackPosRel.getPlasRolePack().getPlasRolePackId());
					voRolePack.setPackName(plasRolePackPosRel.getPlasRolePack().getPackName());
					voRolePack.setCheck(true);//checkbox 默认勾选
					tmpList.add(plasRolePackPosRel.getPlasRolePack().getPlasRolePackId());
					plasRolePackList.add(voRolePack);
				}
			}
			if(rolePackList != null && rolePackList.size()>0){
				for (PlasRolePack plasRolePack : rolePackList) {
					if(!tmpList.contains(plasRolePack.getPlasRolePackId())){
						voRolePack = new VoRolePack();
						voRolePack.setPlasRolePackId(plasRolePack.getPlasRolePackId());
						voRolePack.setPackName(plasRolePack.getPackName());
						voRolePack.setCheck(false);//checkbox 不勾选
						plasRolePackList.add(voRolePack);
					}
				}
			}
		}
		return "relRolePackList";
	}
	
	/**
	 * 配置角色包与职位关系
	 * 
	 * @param plasSysPositionId 系统职位id
	 * @param addPackIds 增加的角色包id
	 * @param delPackIds 删除的角色包id
	 * @return
	 */
	public String saveRolePackPosRel(){
		if(StringUtils.isBlank(plasSysPositionId)){
			Struts2Utils.renderText("plasSysPositionId is blank!");
		}else{
			String addPackIds = Struts2Utils.getParameter("addPackIds");
			String delPackIds = Struts2Utils.getParameter("delPackIds");
			plasRolePackPosRelManager.saveBatch(plasSysPositionId, addPackIds, delPackIds);
			Struts2Utils.renderText("success");
		}
		return null;
	}

	/**
	 * 加载逻辑机构系统职位树
	 */
	public void loadOrgSysPosTree() {
		String tmpAllFlg = Struts2Utils.getParameter("sAllFlag");
		//1-物理 2-逻辑
		String sViewFlg = Struts2Utils.getParameter("sViewFlg");
//		Struts2Utils.renderJson(TreePanelUtil.createOrgPositionEnable(isAllFlag));
		
//		List<VoOrg> voOrgList = plasDimeOrgRel.getVoOrgList(TreePanelUtil2.TREE_TYPE_LOGICAL, "true".equals(tmpFlag));
//		List<VoSysPosition> voPosList = plasSysPositionManager.searchPositionListByFlg("true".equals(tmpFlag));
//		Struts2Utils.renderJson(TreePanelUtil2.buildOrgPosTreeNoCheck(voOrgList, voPosList, new ArrayList<String>()));
		
		String treeTypeCd = null;
		if("true".equals(sViewFlg)){
			treeTypeCd = TreePanelUtil2.TREE_TYPE_PHYSICAL;
		}else{
			treeTypeCd = TreePanelUtil2.TREE_TYPE_LOGICAL;
		}
		
		TreePanelNode node = TreePanelUtil2.getPosTreeNodePanel(
				treeTypeCd, 
				SpringSecurityUtils.getCurUiid(), 
				true,
				null, 
				false, 
				"true".equals(tmpAllFlg),false);
		//移除其他邮箱
		TreePanelUtil2.refreshMoveOrgOtherEmailGroup(node);
		//正常编制/有效编制
		TreePanelUtil2.refreshPosOrg(node);
		//是显示
		TreePanelUtil2.refreshVisable(node,true);
		
		Struts2Utils.renderJson(node);
	}

	public void prepareGetPlasAcctByUserName() throws Exception {
		prepareModel();
	}

	/**
	 * 获取用户账号：页面通过combogrid控件输入用户名 注：combogrid控件请求参数通过 q 获取
	 */
	public void getPlasAcctByUserName() {
		List<Map<String, Object>> rtnList = new ArrayList<Map<String, Object>>();
		String p = Struts2Utils.getParameter("q");
		if (null == p) {
			if (entity.getPlasAcct() == null)
				return;
			else {
				p = entity.getPlasAcct().getUiid();
			}
		} else {
			p = p.trim();
		}
		if (null != p && !"".equals(p)) {
			String likeName = "%" + p + "%";
			List<PlasAcct> list = acctManager.getAllsByUiid(likeName);
			Map<String, Object> codeValMap = null;
			for (PlasAcct tmpUser : list) {
				codeValMap = new HashMap<String, Object>();
				codeValMap.put("plasAcctId", tmpUser.getPlasAcctId());
				codeValMap.put("uiid", tmpUser.getUiid());
				codeValMap.put("userName", tmpUser.getPlasUser().getUserName());
				codeValMap.put("orgName", tmpUser.getPlasUser().getPlasOrg().getOrgName());
				rtnList.add(codeValMap);
			}
		}
		JsonUtil.renderListJson(rtnList);
	}

	/**
	 * 停用系统职位：将删除与人员的关系，保留与机构、角色的关系
	 */
	@Override
	public String delete() throws Exception {
		String reUserName = "";
		String reAcct = "";
		if (null != entity.getPlasAcct() && null != entity.getPlasAcct().getPlasUser()) {
			reAcct = entity.getPlasAcct().getCustLoginName();
			reUserName = entity.getPlasAcct().getPlasUser().getUserName();
		}
		entity.setActiveBl(false);
		// 停用职位与人员的关联
		PlasAcct acct = entity.getPlasAcct();
		acct.setPlasUser(null);
		entity.setPlasAcct(acct);

		plasSysPositionManager.savePlasSysPosition(entity);

		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();

		plasOperateLogManager.savePlasOperateLog(
				operUiid,
				operUserName,
				OperConst.SYSPOS,
				OperConst.HALT,
				new StringBuffer("[").append(entity.getSysPosName()).append(",").append(entity.getSysPosCd())
						.append("]停用系统职位成功!").append("删除关联账号：" + reAcct).append("关联用户：" + reUserName).toString());

		return null;
	}


	/**
	 * Descrption：复制职位：复制系统职位所包含的角色
	 */
	public void prepareCopySysPosition() throws Exception {
		prepareModel();
	}
	public void copySysPosition() {
		
		PlasSysPosition sysPos = new PlasSysPosition();
		sysPos.setPlasAcct(null);
		String plasSysPositionId = entity.getPlasSysPositionId();
		String sysPosCd = entity.getSysPosCd();
		String sysPosName = entity.getSysPosName();
		if (sysPosCd.indexOf("_CP") > 0) {
			sysPosCd = sysPosCd.substring(0, sysPosCd.length() - 6);
		}
		sysPosCd += "_CP" + Util.makeRandom(3);
		if (sysPosName.indexOf("_CP") > 0) {
			sysPosName = sysPosName.substring(0, sysPosName.length() - 6);
		}
		sysPosName += "_CP" + Util.makeRandom(3);
		PlasOrg org = new PlasOrg();
		org.setPlasOrgId(entity.getPlasOrg().getPlasOrgId());
		sysPos.setPlasOrg(org);
		sysPos.setSysPosCd(sysPosCd);
		sysPos.setSysPosName(sysPosName);
		sysPos.setSequenceNo(entity.getSequenceNo());
		sysPos.setActiveBl(entity.getActiveBl());
		plasSysPositionManager.savePlasSysPosition(sysPos);
		
		//职位与角色关系
		List<PlasSysPosRoleRel> sysPosRoleRel = sysPosRoleRelManager.getCurSysPosRoleRelList(plasSysPositionId);
		if (sysPosRoleRel != null && sysPosRoleRel.size() > 0) {
			PlasSysPosRoleRel tmpRoleRel = null;
			for (PlasSysPosRoleRel SysPosRoleRel : sysPosRoleRel) {
				tmpRoleRel = new PlasSysPosRoleRel();
				tmpRoleRel.setPlasSysPosition(sysPos);
				tmpRoleRel.setPlasRole(SysPosRoleRel.getPlasRole());
				sysPosRoleRelManager.savePlasSysPosRoleRel(tmpRoleRel);
			}
		}
		//职位与机构关系(机构管理员)
		
		
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();

		plasOperateLogManager.savePlasOperateLog(
				operUiid,
				operUserName,
				OperConst.SYSPOS,
				OperConst.CPY,
				new StringBuffer("[").append(entity.getSysPosName()).append(",").append(entity.getSysPosCd())
						.append("]复制系统职位成功!").toString());

		Struts2Utils.renderText("success");
	}

	@Override
	public void prepareSave() throws Exception {
		prepareModel(); 
	}

	@Override
	public String save() throws Exception {
		
		if (StringUtils.isBlank(entity.getSysPosCd())) {
			String sysPosCd = appSeqManager.createNextValue(GlobalConstants.SEQ_SYSPOS_CD).toString();
			entity.setSysPosCd(sysPosCd);
		}
		
		boolean isNewFlg = false;
		if(StringUtils.isBlank(getId())){
			isNewFlg = true;
		}
		plasSysPositionManager.savePlasSysPosition(entity);

		// 若新建职位,则默认授权职位（普通用户A_USER, 帮助用户A_HELP_USER)
		if(isNewFlg){
			String[] arr = new String[]{GlobalConstants.A_USER,GlobalConstants.A_HELP_USER};
			
			PlasSysPosRoleRel rel = null;
			for(int i=0; i<arr.length; i++){
				rel = new PlasSysPosRoleRel();
				rel.setPlasSysPosition(entity);
				rel.setPlasRole(plasRoleManager.getPlasRoleByRoleCd(arr[i]));
				plasSysPosRoleRelManager.savePlasSysPosRoleRel(rel);
			}
		}

		// 保存操作日志
		String desc = new StringBuffer()
					.append("[").append(entity.getSysPosCd()).append(",").append(entity.getSysPosName()).append("] ")
					.append(isNewFlg?"新建系统职位,同时与普通用户,帮助用户关联":"保存系统职位").append("成功!")
					.toString();
		plasOperateLogManager.savePlasOperateLog(
				SpringSecurityUtils.getCurUiid(), 
				SpringSecurityUtils.getCurUserName(), 
				OperConst.SYSPOS, 
				(isNewFlg)?OperConst.ADD: OperConst.EDT,
				desc);
		
		//返回ID
		Struts2Utils.renderText("success,"+entity.getPlasSysPositionId());
		
		return null;
	}
	
	
	/**
	 * 保存职位与账号关系(n:1)
	 * @param positionId
	 * @param acctId
	 * 
	 * @return
	 */
	public String savePositionRelAcct(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		String acctId = Struts2Utils.getParameter("acctId");

		if(StringUtils.isBlank(positionId) || StringUtils.isBlank(acctId)){
			Struts2Utils.renderText("positionId or positionId is null! ");
			return null;
		}
		PlasAcct newAcct = acctManager.getEntity(acctId);
		plasSysPositionManager.savePositionRelAcct(positionId, newAcct);
		
		Struts2Utils.renderText("success");
		
		return null;
	}
	/**
	 * 清空关联职位
	 * @param positionId
	 * @param acctId
	 * 
	 * @return
	 */
	public String cleanPositionRelAcct(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		
		if(StringUtils.isBlank(positionId)){
			Struts2Utils.renderText("positionId is null! ");
			return null;
		}
		plasSysPositionManager.cleanPositionRelAcct(positionId);
		
		Struts2Utils.renderText("success");
		
		return null;
	}
	/**
	 * 是否有效
	 * @param positionId
	 * @param acctId
	 * 
	 * @return
	 */
	public String savePositionFlg(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		String activeBl = Struts2Utils.getParameter("activeBl");
		
		if(StringUtils.isBlank(positionId)){
			Struts2Utils.renderText("positionId is null! ");
			return null;
		}
		entity = plasSysPositionManager.getEntity(positionId);
		entity.setActiveBl(new Boolean("true".equals(activeBl)));//checkbox 
		plasSysPositionManager.savePlasSysPosition(entity);
		
		Struts2Utils.renderText("success");
		
		return null;
	}

	/**
	 * 临时职位
	 * @param positionId
	 * @param tmpPosFlg
	 * @return
	 */
	public String savePosTmp(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		String tmpPosFlg = Struts2Utils.getParameter("tmpPosFlg");
		
		if(StringUtils.isBlank(positionId)){
			Struts2Utils.renderText("positionId is null! ");
			return null;
		}
		entity = plasSysPositionManager.getEntity(positionId);
		entity.setTmpPosFlg(new Boolean("true".equals(tmpPosFlg)));//checkbox 
		plasSysPositionManager.savePlasSysPosition(entity);
		
		Struts2Utils.renderText("success");
		
		return null;
	}
	/**
	 * 超编
	 * @param positionId
	 * @param beyondFlg
	 * @return
	 */
	public String savePosBeyond(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		String beyondFlg = Struts2Utils.getParameter("beyondFlg");
		
		if(StringUtils.isBlank(positionId)){
			Struts2Utils.renderText("positionId is null! ");
			return null;
		}
		entity = plasSysPositionManager.getEntity(positionId);
		entity.setBeyondFlg(new Boolean("true".equals(beyondFlg)));//checkbox 
		plasSysPositionManager.savePlasSysPosition(entity);
		
		Struts2Utils.renderText("success");
		
		return null;
	}
	/**
	 *是否不在统计内
	 * @param positionId
	 * @param outStatFlg
	 * @return
	 */
	public String saveOutStat(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		String outStatFlg = Struts2Utils.getParameter("outStatFlg");
		
		if(StringUtils.isBlank(positionId)){
			Struts2Utils.renderText("positionId is null! ");
			return null;
		}
		entity = plasSysPositionManager.getEntity(positionId);
		entity.setOutStatFlg(new Boolean("true".equals(outStatFlg)));//checkbox 
		plasSysPositionManager.savePlasSysPosition(entity);
		
		Struts2Utils.renderText("success");
		
		return null;
	}
	/**
	 *是否显示
	 * @param positionId
	 * @param visableFlg
	 * @return
	 */
	public String saveVisableFlg(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		String visableFlg = Struts2Utils.getParameter("visableFlg");
		
		if(StringUtils.isBlank(positionId)){
			Struts2Utils.renderText("positionId is null! ");
			return null;
		}
		entity = plasSysPositionManager.getEntity(positionId);
		entity.setVisableFlg(new Boolean("true".equals(visableFlg)));//checkbox 
		plasSysPositionManager.savePlasSysPosition(entity);
		
		Struts2Utils.renderText("success");
		
		return null;
	}
	public String getQuickSearchFieldId() {
		return quickSearchFieldId;
	}

	public void setQuickSearchFieldId(String quickSearchFieldId) {
		this.quickSearchFieldId = quickSearchFieldId;
	}

	public String getQuickSearchPosId() {
		return quickSearchPosId;
	}

	public void setQuickSearchPosId(String quickSearchPosId) {
		this.quickSearchPosId = quickSearchPosId;
	}

	public String getQuickSearchPos() {
		return quickSearchPos;
	}

	public void setQuickSearchPos(String quickSearchPos) {
		this.quickSearchPos = quickSearchPos;
	}

	public String getQuickSearchField() {
		return quickSearchField;
	}

	public void setQuickSearchField(String quickSearchField) {
		this.quickSearchField = quickSearchField;
	}

	//停用职位
	public String disablePos(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		plasSysPositionManager.disablePos(positionId);
		Struts2Utils.renderText("success");
		
		return null;
	}
	//启用职位
	public String enablePos(){
		
		String positionId = Struts2Utils.getParameter("positionId");
		plasSysPositionManager.enablePos(positionId);
		Struts2Utils.renderText("success");
		
		return null;
	}
	
	public int getRoleSize(){
		return entity.getPlasSysPosRoleRels().size();
	}
	public int getOrgRelSize(){
		return entity.getPlasOrgMgrRels().size();
	} 
	
	//角色列表
	public String getRoleNames() {
		 return roleNames;
	}
	
	//机构列表
	public String getOrgNames() {
		return orgNames;
	}
	/**
	 * 模糊查询系统职位
	 */
	public void quickSearchSysPosList() {
		
		String tmpSysPosName = Struts2Utils.getParameter("value").trim().toLowerCase();
		String tmpSysPosCd = Struts2Utils.getParameter("sysPosCd");
		String tmpUiid = Struts2Utils.getParameter("uiid");
		String tmpDimeCd = Struts2Utils.getParameter("dimeCd");
		String tmpPlasUserId = Struts2Utils.getParameter("plasUserId");
		
		String tmpNeedCenterFlg = Struts2Utils.getParameter("needCenterFlg");
		String tmpEmptyPosFlg = Struts2Utils.getParameter("emptyPosFlg");
		
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();
		
		String tmpOrgId = null;
		if(StringUtils.isNotBlank(tmpPlasUserId)){
			PlasUser tmpUser = userManager.getEntity(tmpPlasUserId);
			if(tmpUser != null){
				tmpOrgId = tmpUser.getPlasOrg().getPlasOrgId();
			}
		}
		boolean isAdmin = RoleUtil.isAdmin(roleManager.getUserRoles( SpringSecurityUtils.getCurUiid()));
		List<VoSysPosition> result = plasSysPositionManager.getFindSysPositionList(tmpSysPosName, tmpSysPosCd, tmpUiid, tmpOrgId, tmpDimeCd, "20", isAdmin,"1".equals(tmpEmptyPosFlg));
		// 增加根节点
		Map<String, String> map = null;
		for (VoSysPosition sysPos : result) {
			map = new HashMap<String, String>();
			map.put("plasSysPostionId", sysPos.getPlasSysPositionId());
			map.put("sysPosCd", sysPos.getSysPosCd());
			map.put("sysPosName", StringUtils.isBlank(sysPos.getSysPosName())?"":sysPos.getSysPosName());
			map.put("orgName", StringUtils.isBlank(sysPos.getOrgName())?"":sysPos.getOrgName());
			map.put("orgCd", StringUtils.isBlank(sysPos.getOrgCd())?"":sysPos.getOrgCd());
			map.put("activeFlgName", (sysPos.getActiveBl()!= null && sysPos.getActiveBl().booleanValue())?"在用":"弃用");
			if("1".equals(tmpNeedCenterFlg)){
				map.put("centerOrgName", plasOrgManager.getCenterOrgNameByOrgId(sysPos.getOrgId()));//速度很慢
			}else{
				map.put("centerOrgName", ""/*plasOrgManager.getCenterOrgNameByOrgId(sysPos.getOrgId())*/);//速度很慢
			}
			map.put("userName", ((StringUtils.isBlank(sysPos.getUserName()))?"(空缺)":(sysPos.getUserName())+"["+sysPos.getUiid()+"]"));
			tmpList.add(map);
		}
		
		Struts2Utils.renderJson(tmpList);
	}
	public void prepareSaveSysPosRedeploy() throws Exception{
		if (StringUtils.isNotBlank(getId())) {
			entity = plasSysPositionManager.getEntity(getId());
		} else
			throw new Exception("sysPos no exist! restart");
	}
	//系统职位调动
	public void saveSysPosRedeploy(){
		PlasOrg org = plasOrgManager.getEntity(plasOrgId);
		entity.setPlasOrg(org);
		plasSysPositionManager.redeployPos(entity);
		Struts2Utils.renderText("success");
	}

	public String getSysPosOrgName() {
		return sysPosOrgName;
	}

	public void setSysPosOrgName(String sysPosOrgName) {
		this.sysPosOrgName = sysPosOrgName;
	}

	public List<VoRole> getRelRoleList() {
		return relRoleList;
	}

	public void setRelRoleList(List<VoRole> relRoleList) {
		this.relRoleList = relRoleList;
	}

	public List<VoRolePack> getPlasRolePackList() {
		return plasRolePackList;
	}

	public void setPlasRolePackList(List<VoRolePack> plasRolePackList) {
		this.plasRolePackList = plasRolePackList;
	}

	public String getPlasSysPositionId() {
		return plasSysPositionId;
	}

	public void setPlasSysPositionId(String plasSysPositionId) {
		this.plasSysPositionId = plasSysPositionId;
	}

	public String getIsAllRoleFlg() {
		return isAllRoleFlg;
	}

	public void setIsAllRoleFlg(String isAllRoleFlg) {
		this.isAllRoleFlg = isAllRoleFlg;
	}

	public String[] getOldIds() {
		return oldIds;
	}

	public void setOldIds(String[] oldIds) {
		this.oldIds = oldIds;
	}
	
}

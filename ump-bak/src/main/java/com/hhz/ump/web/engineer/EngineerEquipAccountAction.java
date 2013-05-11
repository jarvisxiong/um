package com.hhz.ump.web.engineer;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.engineer.EngineerEquipAccountManager;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.engineer.EngineerEquipAccount;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.TreePanelNode;

/**
 * 工程设备平台台账
 * @author Aspenn
 *
 */
/**
 * @author Aspenn
 *
 */
@Namespace("/engineer")
@Results( {@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "excelFile", "contentDisposition",
				"attachment;filename=${excelFileName}.xls" }) 
})
public class EngineerEquipAccountAction extends CrudActionSupport<EngineerEquipAccount>{
	private static final long serialVersionUID = 6265825544313251081L;
	@Autowired
	protected EngineerEquipAccountManager engineerEquipAccountManager;
	@Autowired
	protected BisProjectManager bisProjectManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	/***
	 * 集合对象
	 */
	private List<EngineerEquipAccount> engineerEquips ;
	/**
	 * 页面对象
	 */
	private Page<EngineerEquipAccount> page = new Page<EngineerEquipAccount>(10);
	/**
	 * 实体对象
	 */
	private EngineerEquipAccount entity;
	private TreePanelNode showTree;
	/**所有属性全改为string类型*/
	private String engineerEquipAccountId;
	private String bisProjectId;
	private String layoutCd;
	private String equipTypeCd;
	private String equipBelongtoCd;
	private String equipCd;
	private String equipName;
	private String equipModels;
	private String equipMaker;
	private String equipSerialNum;
	private String equipSerialDate;
	private String equipAmount;
	private String equipPosition;
	private String equipException;
	private String attachFlg;
	private String equipStatus;
	private String entityTmpId;
	/***
	 * 查询位置 1：普通查询、2：快速查询
	 */
	private String searchPosition;
	
    private InputStream excelFile;//导出Excel需要的参数
    private String excelFileName;
	
	/**
	 * 验证是否已经存在编码
	 * @return
	 */
	public String valiEngineerEquipCd(){
		equipCd = Struts2Utils.getParameter("equipCd");
		boolean text = engineerEquipAccountManager.ifExistEquipCd(equipCd);
		Struts2Utils.renderText(text==true?"0":"1");
		return null;
	}
	/**
	 * 获取工程台账项目树形
	 * @return
	 */
	public String getEquipProjectTree(){
		List<BisProject> proList = null;
		if(bisProjectManager.ifHeadquartered()){//如果是总部的就查询出全部
			proList = bisProjectManager.filterOrgCd153();
		}else{//当前项目公司
			proList = new ArrayList<BisProject>();
			proList.add(bisProjectManager.getCurrProject());
		}
		List<TreePanelNode> voList = new ArrayList<TreePanelNode>();
		TreePanelNode root = null;//根节点
		TreePanelNode vo = null;
		root =  getRootOrg("0");
		for (BisProject bisProject : proList) {
			if(!StringUtils.isBlank(bisProject.getBisProjectId())){
				vo = makeNode(bisProject.getBisProjectId(),bisProject.getProjectName(),"1",bisProject.getCity(),"1","0");
			}
			voList.add(vo);
		}
		root.setChildren(voList);
		//迭代出树结构
		//showTree=getTreeNode(voList,root);
		Struts2Utils.renderJson(root);
		return null;
	}
	/***
	 * 构造根节点
	 * @param bisProjec
	 * @return
	 */
	private  TreePanelNode getRootOrg(String orgId){
		String DEFAULT_ROOT_ORG_ID_ONE = orgId;
		String DEFAULT_ROOT_ORG_CD_ONE = "0";
		String DEFAULT_ROOT_ORG_BIZ_CD_ONE = "0";
		String DEFAULT_ROOT_ORG_NAME_ONE = "工程资产台帐";
		String NODE_TYPE_ORG_ONE = "0";
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
		String tname[]={"Mall","商业街","住宅/底商"};
		String type[]={"1","2","3"};
		List<TreePanelNode> chlist = new ArrayList<TreePanelNode>();
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
		for (int i = 0; i < tname.length; i++) {
			chlist.add(createStaticTreeNode(tname[i],type[i],nodeCd));
		}
		tmp.setChildren(chlist);//添加固定的子节点
		return tmp;
	}
	/***
	 * 构造固定的子节点
	 * @return
	 */
	private TreePanelNode createStaticTreeNode(String name,String types,String nodeId){
		TreePanelNode node1 = new TreePanelNode();
		node1.setEntityId(types);
		node1.setEntityCd(types);
		node1.setEntityBizCd(types);
		node1.setEntityName(name);
		node1.setNodeType("2");
		node1.setLevelCd("2");
		node1.setId(nodeId+types+"EntityId");
		node1.setText(name);
		node1.setExtParam(nodeId);//父nodeId
		return node1;
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
	 * 高级搜索
	 * @return
	 */
	public String loadList(){
		page = engineerEquipAccountManager.advanceSearch(bisProjectManager.ifHeadquartered(),page, equipCd, equipName, equipModels, equipMaker, equipSerialNum, equipSerialDate,bisProjectId,equipBelongtoCd,layoutCd);
		engineerEquips = page.getResult();
		Struts2Utils.getRequest().setAttribute("haselist", engineerEquips.size()>0?true:false);
		return "list";
	}
	/**
	 * 获取项目id
	 * 向前台打印出两个id
	 * @return
	 */
	public String getBisProjectIpByEquipId(){
		String id = Struts2Utils.getParameter("equipAccountId");
		EngineerEquipAccount tmpEquip = engineerEquipAccountManager.getEntity(id);
		//id打到前台,第一个是项目id，第二个是业态id
		if(tmpEquip!=null) {
//			Struts2Utils.renderText(tmpEquip.getBisProjectId()+","+tmpEquip.getEquipTypeCd());
			Struts2Utils.renderText(tmpEquip.getBisProjectId()+","+tmpEquip.getBisProjectId()+tmpEquip.getLayoutCd()+"EntityId");
		}else{
			Struts2Utils.renderText("0,1");
		}
		return null;
	}
	/**
	 * 快速查询
	 * @return
	 */
	public String quickSearch(){
		String words =  Struts2Utils.getParameter("value");
		page = engineerEquipAccountManager.quickSearch(bisProjectManager.ifHeadquartered(),page, words);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (EngineerEquipAccount tmp : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("engineerEquipAccountId", tmp.getEngineerEquipAccountId());
			String tequipName = "";
			String tequipCd = "";
			if (StringUtils.isNotBlank(tmp.getEquipName())) {
				tequipName = tmp.getEquipName();
			}
			if (StringUtils.isNotBlank(tmp.getEquipCd())) {
				tequipCd = tmp.getEquipCd();
			}
			map.put("equipName", tequipName);
			map.put("equipCd", tequipCd);
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}
	/**
	 * 导出报表
	 * @return
	 */
	public String doExportAccount(){
        page.setPageSize(99999999);
		if(StringUtils.isNotBlank(searchPosition)&&"1".equals(searchPosition)){
			engineerEquips = engineerEquipAccountManager.advanceSearch(bisProjectManager.ifHeadquartered(),page, equipCd, equipName, equipModels, equipMaker, equipSerialNum, equipSerialDate,bisProjectId,equipBelongtoCd,layoutCd).getResult();
		}else if(StringUtils.isNotBlank(searchPosition)&&"0".equals(searchPosition)){
			engineerEquips = engineerEquipAccountManager.getPageById(page,engineerEquipAccountId).getResult();
		} else
			return null;
		/**指定模板名称*/
    	String templateName = "engineerEquipAccount.xls";
        Map resultMap = new HashMap();
        //转换包含数据字典的内容
        List<EngineerEquipAccount> tlist = new ArrayList<EngineerEquipAccount>();
        EngineerEquipAccount tmp = null;
        int index = 1;
        Map<String, String> dataMap = getMapEquipTypeCd();
        for (EngineerEquipAccount eqp : engineerEquips) {
        	tmp = new EngineerEquipAccount(eqp.getEngineerEquipAccountId(),eqp.getBisProjectId(),eqp.getLayoutCd(),eqp.getEquipTypeCd(),
        			eqp.getEquipBelongtoCd(),eqp.getEquipCd(),eqp.getEquipName(),eqp.getEquipModels(),eqp.getEquipMaker(),
        			eqp.getEquipSerialNum(),eqp.getEquipSerialDate(),eqp.getEquipAmount(),eqp.getEquipPosition(),
        			eqp.getEquipException(),index+"",eqp.getEquipStatus());
        	tmp.setBisProjectId(CodeNameUtil.getProjectName(eqp.getBisProjectId()));
        	tmp.setLayoutCd(CodeNameUtil.getEquipLayoutName(eqp.getLayoutCd()));
        	tmp.setEquipTypeCd(dataMap.get(eqp.getEquipTypeCd()));
        	tmp.setEquipBelongtoCd(CodeNameUtil.getEquipBelongToName(eqp.getEquipBelongtoCd()));
			tlist.add(tmp);
			index++;
		}
    	resultMap.put("result", tlist);
        try {
			excelFile = JXLExcelUtil.initJxlsInputStream(resultMap, templateName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileName = "工程平台-" + DateOperator.formatDate(new Date(), "MMddHHmm");
		try {
			excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return "export";
	}
	/**
	 * 工程台帐管理首页入口
	 */
	public String main() throws Exception {
		if(!bisProjectManager.ifHeadquartered()){
			String currCentCd = SpringSecurityUtils.getCurrentCenterCd();
			BisProject project = bisProjectManager.getBisProjectByOrgCd(currCentCd);
			this.setBisProjectId(project.getBisProjectId());
		}
		return "main";
	}
	/**
	 * 固定业态
	 * @return
	 */
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
	/**
	 * 设备状态
	 * @return
	 */
	public Map<String, String> getMapequipStatus(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "正常");
		map.put("2", "异常");
		map.put("3", "报废");
		return map;
	}
	/***
	 * 固定所属系统
	 * @return
	 */
	public Map<String,String> getMapEquipBelongTo(){
		Map<String, String> map = DictMapUtil.getMapEquipBelongTo();
		return map;
	}
	/***
	 * 设备分类
	 * @return
	 */
	public Map<String,String> getMapEquipTypeCd(){
		Map<String, String> map = DictMapUtil.getMapEquipTypeCd();
		return map;
	}
	/***
	 * 增加工程设备
	 * @return
	 * @throws Exception
	 */
	public String add (){
		entity = new EngineerEquipAccount();
		entity.setBisProjectId(Struts2Utils.getParameter("bisProjectId"));
		entity.setLayoutCd(Struts2Utils.getParameter("layoutCd"));
		entity.setEquipBelongtoCd(Struts2Utils.getParameter("equipBelongtoCd"));
		if(StringUtils.isBlank(entityTmpId)) {
			entityTmpId = "enquip_"+RandomUtils.generateTmpEntityId();
		}
		return "input";
	}
	@Override
	public String list() throws Exception {
		String projectId = Struts2Utils.getParameter("proId");
		String belongTo = Struts2Utils.getParameter("belongTo");
		String typeCd = Struts2Utils.getParameter("typeCd");
//		engineerEquips = engineerEquipAccountManager.findListByTree(projectId, belongTo, typeCd);
		page = engineerEquipAccountManager.findPageList(page, projectId, belongTo, typeCd);
		Struts2Utils.getRequest().setAttribute("haselist", page.getResult().size()>0?true:false);
		return "list";
	}
	/**
	 * 根据Id查询对象
	 * @return
	 */
	public String listByEquipId(){
		String equipAccountId = Struts2Utils.getParameter("equipAccountId");
		page = engineerEquipAccountManager.getPageById(page, equipAccountId);
		Struts2Utils.getRequest().setAttribute("haselist", page.getResult().size()>0?true:false);
		return "list";
	}
	@Override
	public String delete() throws Exception {
		
		return null;
	}
	@Override
	public String deleteBatch() throws Exception {
		//需要删除的台账IDS
		String accountIds=Struts2Utils.getParameter("accountIds");
		//如果不为空，则删除相应的台账信息
		if(StringUtils.isNotBlank(accountIds)){
			String [] accIds=accountIds.split(",");
			engineerEquipAccountManager.deleteBatch(accIds);
			Struts2Utils.renderText("success");
		}else{
			Struts2Utils.renderText("删除出错，请核实！");
		}
		return null;
	}
	@Override
	public String input() throws Exception {
		String method = Struts2Utils.getParameter("method");
		if(StringUtils.isNotBlank(method)&&"update".equals(method)){
			Struts2Utils.getRequest().setAttribute("method", "update");
		}
		return "input";
	}
	@Override
	protected void prepareModel() throws Exception {
		String equipId  =Struts2Utils.getParameter("engineerEquipAccountId");
		if(StringUtils.isBlank(equipId)){
			getEngineerEquip();
		}else{
			entity = engineerEquipAccountManager.getEntity(equipId);
		}
	}
	/***
	 * 初始化对象
	 */
	private void getEngineerEquip(){
		entity =  new EngineerEquipAccount();
		entity.setBisProjectId(this.getBisProjectId());
		entity.setLayoutCd(this.getLayoutCd());
		entity.setEquipTypeCd(this.getEquipTypeCd());
		entity.setEquipBelongtoCd(this.getEquipBelongtoCd());
		entity.setEquipCd(this.getEquipCd());
		entity.setEquipName(this.getEquipName());
		entity.setEquipModels(this.getEquipModels());
		entity.setEquipMaker(this.getEquipMaker());
		entity.setEquipSerialNum(this.getEquipSerialNum());
		entity.setEquipSerialDate(this.getEquipSerialDate());
		entity.setEquipAmount(this.getEquipAmount());
		entity.setEquipPosition(this.getEquipPosition());
		entity.setAttachFlg(this.getAttachFlg());
		entity.setEquipStatus(this.getEquipStatus());
		entity.setEquipException(this.getEquipException());
	}
	@Override
	public String save() throws Exception {
		String method = Struts2Utils.getParameter("method");
		if(StringUtils.isNotBlank(method)&&"update".equals(method)){
			entity.setEquipTypeCd(this.getEquipTypeCd());
			entity.setEquipCd(this.getEquipCd());
			entity.setEquipName(this.getEquipName());
			entity.setEquipModels(this.getEquipModels());
			entity.setEquipMaker(this.getEquipMaker());
			entity.setEquipSerialNum(this.getEquipSerialNum());
			entity.setEquipSerialDate(this.getEquipSerialDate());
			entity.setEquipAmount(this.getEquipAmount());
			entity.setEquipPosition(this.getEquipPosition());
			entity.setAttachFlg(this.getAttachFlg());
			entity.setEquipStatus(this.getEquipStatus());
			entity.setEquipException(this.getEquipException());
		}
		engineerEquipAccountManager.saveEngineerEquipAccount(entity);
		//关联附件
		if (StringUtils.isNotEmpty(entityTmpId)) {
			appAttachFileManager.updateTmpFile(entityTmpId, EngineerEquipAccount.class.getSimpleName(), entity.getEngineerEquipAccountId());
		}
		/**将id打印到前台*/
		Struts2Utils.renderText(entity.getEngineerEquipAccountId());
		return null;
	}
	@Override
	public EngineerEquipAccount getModel() {
		
		return null;
	}
	public EngineerEquipAccount getEntity() {
		return entity;
	}
	public void setEntity(EngineerEquipAccount entity) {
		this.entity = entity;
	}

	public TreePanelNode getShowTree() {
		return showTree;
	}

	public void setShowTree(TreePanelNode showTree) {
		this.showTree = showTree;
	}
	@Override
	public Page<EngineerEquipAccount> getPage() {
		return page;
	}
	public void setPage(Page<EngineerEquipAccount> page) {
		this.page = page;
	}
	public List<EngineerEquipAccount> getEngineerEquips() {
		return engineerEquips;
	}
	public void setEngineerEquips(List<EngineerEquipAccount> engineerEquips) {
		this.engineerEquips = engineerEquips;
	}
	public EngineerEquipAccountManager getEngineerEquipAccountManager() {
		return engineerEquipAccountManager;
	}
	public void setEngineerEquipAccountManager(
			EngineerEquipAccountManager engineerEquipAccountManager) {
		this.engineerEquipAccountManager = engineerEquipAccountManager;
	}
	public BisProjectManager getBisProjectManager() {
		return bisProjectManager;
	}
	public void setBisProjectManager(BisProjectManager bisProjectManager) {
		this.bisProjectManager = bisProjectManager;
	}
	public String getEngineerEquipAccountId() {
		return engineerEquipAccountId;
	}
	public void setEngineerEquipAccountId(String engineerEquipAccountId) {
		this.engineerEquipAccountId = engineerEquipAccountId;
	}
	public String getBisProjectId() {
		return bisProjectId;
	}
	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}
	public String getLayoutCd() {
		return layoutCd;
	}
	public void setLayoutCd(String layoutCd) {
		this.layoutCd = layoutCd;
	}
	public String getEquipTypeCd() {
		return equipTypeCd;
	}
	public void setEquipTypeCd(String equipTypeCd) {
		this.equipTypeCd = equipTypeCd;
	}
	public String getEquipBelongtoCd() {
		return equipBelongtoCd;
	}
	public void setEquipBelongtoCd(String equipBelongtoCd) {
		this.equipBelongtoCd = equipBelongtoCd;
	}
	public String getEquipCd() {
		return equipCd;
	}
	public void setEquipCd(String equipCd) {
		this.equipCd = equipCd;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getEquipModels() {
		return equipModels;
	}
	public void setEquipModels(String equipModels) {
		this.equipModels = equipModels;
	}
	public String getEquipMaker() {
		return equipMaker;
	}
	public void setEquipMaker(String equipMaker) {
		this.equipMaker = equipMaker;
	}
	public String getEquipSerialNum() {
		return equipSerialNum;
	}
	public void setEquipSerialNum(String equipSerialNum) {
		this.equipSerialNum = equipSerialNum;
	}
	public String getEquipSerialDate() {
		return equipSerialDate;
	}
	public void setEquipSerialDate(String equipSerialDate) {
		this.equipSerialDate = equipSerialDate;
	}
	public String getEquipAmount() {
		return equipAmount;
	}
	public void setEquipAmount(String equipAmount) {
		this.equipAmount = equipAmount;
	}
	public String getEquipPosition() {
		return equipPosition;
	}
	public void setEquipPosition(String equipPosition) {
		this.equipPosition = equipPosition;
	}
	public String getEquipException() {
		return equipException;
	}
	public void setEquipException(String equipException) {
		this.equipException = equipException;
	}
	public String getEquipStatus() {
		return equipStatus;
	}
	public void setEquipStatus(String equipStatus) {
		this.equipStatus = equipStatus;
	}
	public String getAttachFlg() {
		return attachFlg;
	}
	public void setAttachFlg(String attachFlg) {
		this.attachFlg = attachFlg;
	}
	public String getEntityTmpId() {
		return entityTmpId;
	}
	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public String getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	public AppAttachFileManager getAppAttachFileManager() {
		return appAttachFileManager;
	}
	public void setAppAttachFileManager(AppAttachFileManager appAttachFileManager) {
		this.appAttachFileManager = appAttachFileManager;
	}
	public String getSearchPosition() {
		return searchPosition;
	}
	public void setSearchPosition(String searchPosition) {
		this.searchPosition = searchPosition;
	}
	
}

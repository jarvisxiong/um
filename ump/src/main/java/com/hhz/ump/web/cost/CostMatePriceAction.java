package com.hhz.ump.web.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostMateColManager;
import com.hhz.ump.dao.cost.CostMateManager;
import com.hhz.ump.dao.cost.CostMateModuleManager;
import com.hhz.ump.dao.cost.CostMatePriceManager;
import com.hhz.ump.entity.cost.CostMate;
import com.hhz.ump.entity.cost.CostMateCol;
import com.hhz.ump.entity.cost.CostMateModule;
import com.hhz.ump.entity.cost.CostMatePrice;
import com.hhz.ump.web.res.bean.StrategyOnlineOrder;

@Namespace("/cost")
@Results( { @Result(name = CostMatePriceAction.RELOAD, location = "cost-mate!input.action", type = "redirect", params = {
		"id", "${costMateId}" })})
public class CostMatePriceAction extends CrudActionSupport<CostMatePrice> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CostMateManager costMateManager;
	@Autowired
	private CostMateModuleManager costMateModuleManager;
	@Autowired
	private CostMatePriceManager costMatePriceManager;
	@Autowired
	private CostMateColManager costMateColManager;

	private List<CostMateCol> costMateColList;
	private List<CostMate> costMateList;
	private List<CostMatePrice> costMatePriceList;
	
	private CostMateModule costMateModule;
	private CostMatePrice entity;
	private CostMate costMate;//网批使用到 resAppend.jsp,resCreate.jsp使用
	private String costMateName;
	private String costMateId;
	private String inputFlag;
	private String id;
	//网上下单实体
	private StrategyOnlineOrder templateBean;
	private String specTypeCd;
	
	//网批专用  resAppend.jsp,resCreate.jsp
	private String resCostMatePriceId;
	private String groupTypeName;//材料,设备,材料或设备
	
	@Override
	public String list() throws Exception {
		
		String specName = Struts2Utils.getParameter("specName");
		String modelName = Struts2Utils.getParameter("modelName");
		String stratPrice = Struts2Utils.getParameter("stratPrice");
		String endPrice = Struts2Utils.getParameter("endPrice");
		String enableFlg = Struts2Utils.getParameter("enableFlg");
		if(StringUtils.isNotBlank(costMateId)){

			
			//获取类型名称
			costMate = costMateManager.getEntity(costMateId);
			initGroupTypeName(costMate.getParentModuleId());
			
			
			Map<String, Object> values = new HashMap<String, Object>();
			StringBuffer hql = new StringBuffer();
			hql.append("from CostMatePrice t where t.costMate.costMateId = :costMateId");
			values.put("costMateId", costMateId);
			if(StringUtils.isNotBlank(specName)){
				hql.append(" and t.specName like :specName");
				values.put("specName", "%"+specName.trim()+"%");
			}
			if(StringUtils.isNotBlank(modelName)){
				hql.append(" and t.modelName like :modelName");
				values.put("modelName","%"+modelName.trim()+"%");
			}
			if(StringUtils.isNotBlank(enableFlg)){
				hql.append(" and t.enableFlg = :enableFlg");
				values.put("enableFlg",Long.valueOf(enableFlg));
			}
			if(StringUtils.isNotBlank(stratPrice) && StringUtils.isNotBlank(endPrice)){
				hql.append(" and (t.price >= :startPrice and t.price <= :endPrice)");
				values.put("startPrice", BigDecimal.valueOf((Double.valueOf(stratPrice))));
				values.put("endPrice", BigDecimal.valueOf((Double.valueOf(endPrice))));
			}else{
				if(StringUtils.isNotBlank(stratPrice)){
					hql.append(" and t.price >= :price");
					values.put("price", BigDecimal.valueOf((Double.valueOf(stratPrice))));
				}
				if(StringUtils.isNotBlank(endPrice)){
					hql.append(" and t.price <= :price");
					values.put("price", BigDecimal.valueOf((Double.valueOf(endPrice))));
				}
			}
			hql.append(" order by t.sequenceNo asc");//按照序号排序
			page.setPageSize(50);
			page = costMatePriceManager.findPage(page, hql.toString(),values);
			costMatePriceList = page.getResult();
			
			//搜索列头名称
			queryCostMateColList(costMateId);
		}
		return "list";
	}

	@Override
	public String save() throws Exception {
		queryCostMateColList(costMateId);
		//规格型号为空则默认设值为"-"
		if (StringUtils.isBlank(entity.getModelName())) {
			entity.setModelName("-");
		}
		if (StringUtils.isBlank(entity.getSpecName())) {
			entity.setSpecName("-");
		}
		costMatePriceManager.saveCostMatePrice(entity);
		return RELOAD;
	}
	
	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = costMatePriceManager.getEntity(getId());
			if(entity != null){
				costMatePriceManager.deleteCostMatePrice(getId());
				Struts2Utils.renderHtml("success");
			}
		}
		return null;
	}
	
	/**
	 * 检测设备型号重复
	 * @param costMateId
	 * @param specName
	 * @param modelName
	 * @return
	 * @throws Exception
	 */
	public String checkMatePrice() throws Exception {
		String mateId = Struts2Utils.getParameter("costMateId");
		String specName = Struts2Utils.getParameter("specName");
		String modelName = Struts2Utils.getParameter("modelName");
		String message = null;
		//若为空则以”-“代替
		if (StringUtils.isBlank(specName)) {
			specName = "-";
		}
		if (StringUtils.isBlank(modelName)) {
			modelName = "-";
		}
		if (StringUtils.isNotBlank(mateId)) {
			List<CostMatePrice> matePrice = costMatePriceManager.checkMatePrice(mateId, specName, modelName);
			if (matePrice != null && matePrice.size() > 0) {
				CostMatePrice price = null;
				if (StringUtils.isNotBlank(getId())) {
					price = costMatePriceManager.getEntity(getId());
				}
				if (price != null && specName.trim().equals(price.getSpecName()) 
						&& modelName.trim().equals(price.getModelName())) {
					message = "success";
				}else{
					message = "fail";
				}
			}else{
				message = "success";
			}
		}
		Struts2Utils.renderText(message);
		return null;
	}
	
	/**
	 * 快速搜索(下单)
	 * strategy-online-order.jsp
	 * @param mateModuleId
	 * @param specName 规格
	 * @param modelName 型号
	 * @param costMateId 设备ID
	 * @param excludePriceIds 排除的priceIds
	 * @return
	 * @throws Exception
	 */
	public String quickSearch() throws Exception{
		
		String value = Struts2Utils.getParameter("value");
		String mateId = Struts2Utils.getParameter("costMateId");
		String parentModuleId = Struts2Utils.getParameter("costMateModuleId");
		String excludePriceIds = Struts2Utils.getParameter("excludePriceIds");
		
		if(StringUtils.isBlank(costMateId) && StringUtils.isBlank(parentModuleId))
			return null;
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("parentModuleId", format(parentModuleId));
		param.put("costMateId", format(mateId));
		param.put("enableFlg", Long.valueOf(1));
		param.put("likeVal", "%"+ format(value) +"%");
		
		if(StringUtils.isNotBlank(excludePriceIds)){
			param.put("excludePriceIds", StringUtils.split(format(excludePriceIds),","));
		}
		
		String like1 = null;
		String like2= null;
		
		String[] arr = StringUtils.split(value, " ");
		boolean andFlg = false;
		if(arr.length == 0){
			like1 = "";
			like2 = "";
		}
		else if( arr.length == 1){
			like1 = value;
			like2 = value;
		}else{
			like1 = arr[0];
			like2 = arr[1];
		}
		param.put("like1", "%"+ format(like1) +"%");
		param.put("like2", "%"+ format(like2) +"%");
		
		StringBuffer hql = new StringBuffer();
		hql.append("  from CostMatePrice p ");
		hql.append(" where p.enableFlg = :enableFlg");

		//材料设备类型
		if (StringUtils.isNotBlank(parentModuleId)) {
			hql.append(" and p.costMate.parentModuleId = :parentModuleId");
		}
		
		//材料设备ID
		if (StringUtils.isNotBlank(costMateId)) {
			hql.append(" and p.costMate.costMateId = :costMateId");
		}
		
		hql.append(" and ( 1=1 ");
		//规格型号,材料名称
		if (StringUtils.isNotBlank(like1)) {
			hql.append(" and ( p.groupName like :like1 or p.groupName2 like :like1 or p.groupName3 like :like1 or p.materialName like :like1 )");
		}
		if (StringUtils.isNotBlank(like2)){
			if(andFlg){
				hql.append(" and ");
			}else{
				hql.append(" or " );
			}
			hql.append(" ( p.specName like :likeVal or p.modelName like :likeVal )");
		}
		hql.append(" ) ");
		
		//排除的priceids
		if(StringUtils.isNotBlank(excludePriceIds)){
			hql.append(" and p.costMatePriceId not in (:excludePriceIds)");
		}
		
		hql.append(" order by p.sequenceNo asc");
		page.setPageSize(20);
		page = costMatePriceManager.findPage(page, hql.toString(),param);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		
		
		Map<String, String> map = null;

		map = new HashMap<String, String>();
		map.put("headFlg", "1");//1-标题 
		map.put("id", ""); 
		map.put("groupName", ""); 
		map.put("groupName2", ""); 
		map.put("groupName3", ""); 
		map.put("materialName", "设备名称"); 
		map.put("specName", "规格"); 
		map.put("modelName", "型号");
		map.put("price", "单价");
		
		list.add(map);
		
		for (CostMatePrice tMatePrice : page.getResult()) {
			map = new HashMap<String, String>();
			map.put("headFlg", "0");//0-数值
			map.put("id", format(tMatePrice.getCostMatePriceId())); 
			map.put("costMatePriceId", format(tMatePrice.getCostMatePriceId()));//jsp回调函数使用 
			map.put("costMateId", format(tMatePrice.getCostMate().getCostMateId())); //jsp回调函数使用 
			map.put("costModuleId", format(tMatePrice.getCostMate().getParentModuleId()));//模块ID
			map.put("groupName", format(tMatePrice.getGroupName())); 
			map.put("groupName2", format(tMatePrice.getGroupName2())); 
			map.put("groupName3", format(tMatePrice.getGroupName3())); 
			map.put("materialName", format(tMatePrice.getMaterialName())); 
			map.put("specName", format(tMatePrice.getSpecName())); 
			map.put("modelName", format(tMatePrice.getModelName()));
			map.put("price", format(String.valueOf(tMatePrice.getPrice())));
			//map.put("memoDesc", format(tMatePrice.getMemoDesc()));
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}
	
	private String format(String str){
		return StringUtils.isBlank(str)?"":("-".equals(str.trim())?"":str);
	}
	
	//得到设备属性列
	public String getMateCol() throws Exception{
		String mateId = Struts2Utils.getParameter("costMateId");
		if(StringUtils.isNotBlank(mateId)){
			List<CostMateCol> mateColList = queryCostMateColList(mateId);
			StringBuffer sb = new StringBuffer();
			if (mateColList != null && mateColList.size() > 0) {
				for (CostMateCol tCol : mateColList) {
					sb.append(tCol.getColName()+",");
				}
				Struts2Utils.renderText(sb.toString());
			}
		}
		return null;
	}
	
	/**
	 * 计算战略内(外)总价(网上下单)
	 * @param prices 价格
	 * @param numbers 数量
	 * @return
	 * @throws Exception
	 */
	public String totalAmt() throws Exception{
		String[] prices = Struts2Utils.getRequest().getParameterValues("prices");
		String[] numbers = Struts2Utils.getRequest().getParameterValues("numbers");
		String totalFloatRate = Struts2Utils.getParameter("totalFloatRate");
		BigDecimal totalAmt = new BigDecimal(0);
		if (prices.length > 0 && numbers.length > 0) {
			BigDecimal tmpTotalAmt = new BigDecimal(0);
			for (int i = 0; i < numbers.length; i++) {
				BigDecimal total = new BigDecimal(0);
				BigDecimal price = new BigDecimal(0);
				if (StringUtils.isNotBlank(numbers[i])) {
					total = BigDecimal.valueOf(Long.valueOf(numbers[i]));
				}
				if (StringUtils.isNotBlank(prices[i]) && !"null".equals(prices[i])) {
					price = BigDecimal.valueOf(Double.valueOf(prices[i]));
				}
				tmpTotalAmt = tmpTotalAmt.add(price.multiply(total));
			}
			if(StringUtils.isNotBlank(totalFloatRate)){
				//若totalFloatRate不为空，则总价格 = 原价格 *(1+totalFloatRate)   （适用于战略内型号计算）
				totalAmt = tmpTotalAmt.multiply(new BigDecimal(1).add(BigDecimal.valueOf(Double.valueOf(totalFloatRate)))).setScale(2,BigDecimal.ROUND_HALF_UP);
			}else{
				//（适用于战略外型号计算）
				totalAmt = tmpTotalAmt;
			}
		}
		Struts2Utils.renderText(totalAmt+"");
		return null;
	}

	/**
	 * 计算采购总价(网上下单)
	 * @param inPrice 战略内总价
	 * @param outPrice 战略外总价
	 * @return
	 */
	public String getTotalAmt() throws Exception{
		String inPrice = Struts2Utils.getParameter("inPrice");
		String outPrice = Struts2Utils.getParameter("outPrice");
		BigDecimal totalAmt = new BigDecimal(0);
		if(StringUtils.isNotBlank(inPrice) && StringUtils.isNotBlank(outPrice)){
			BigDecimal price1 = new BigDecimal(0);
			BigDecimal price2 = new BigDecimal(0);
			price1 = BigDecimal.valueOf(Double.valueOf(inPrice));
			price2 = BigDecimal.valueOf(Double.valueOf(outPrice));
			totalAmt = price1.add(price2).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		Struts2Utils.renderText(totalAmt+"");
		return null;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(costMateId)){
			costMate = costMateManager.getEntity(costMateId);
			costMateName = costMate.getMateName();
		}else{
			costMate = new CostMate();
		}
		if (getId() != null) {
			entity = costMatePriceManager.getEntity(getId());
		} else {
			entity = new CostMatePrice();
		}
		entity.setCostMate(costMate);
	}
	
	/**
	 * 获得设备类型Map
	 * @return
	 */
	public Map<String, String> getMapCostMateModule() {
		Map<String, String> mapCostMateModule = new LinkedHashMap<String, String>();
		List<CostMateModule> costMateModuleList = costMateModuleManager.getAll();
		for (CostMateModule t : costMateModuleList) {
			mapCostMateModule.put(t.getCostMateModuleId(), t.getModuleName());
		}
		return mapCostMateModule;
	}
	
	/**
	 * 搜索列名称非空的属性
	 * @return
	 */
	public List<CostMateCol> queryCostMateColList(String mateId){
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from CostMateCol t where t.costMate.costMateId = :costMateId and t.colName is not null");
		values.put("costMateId", mateId);
		hql.append(" order by t.sequenceNo asc,t.colField asc");
		costMateColList = costMateColManager.find(hql.toString(), values);
		return costMateColList;
	}
	
	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		queryCostMateColList(costMateId);
		return INPUT;
	}

	@Override
	public CostMatePrice getModel() {
		return entity;
	}
	
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public CostMatePrice getEntity() {
		return entity;
	}
	public void setEntity(CostMatePrice entity) {
		this.entity = entity;
	}
	public CostMateModule getCostMateModule() {
		return costMateModule;
	}
	public void setCostMateModule(CostMateModule costMateModule) {
		this.costMateModule = costMateModule;
	}

	public String getInputFlag() {
		return inputFlag;
	}

	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}

	public List<CostMate> getCostMateList() {
		return costMateList;
	}

	public void setCostMateList(List<CostMate> costMateList) {
		this.costMateList = costMateList;
	}

	public String getCostMateId() {
		return costMateId;
	}

	public void setCostMateId(String costMateId) {
		this.costMateId = costMateId;
	}

	public List<CostMateCol> getCostMateColList() {
		return costMateColList;
	}

	public void setCostMateColList(List<CostMateCol> costMateColList) {
		this.costMateColList = costMateColList;
	}

	public String getCostMateName() {
		return costMateName;
	}

	public void setCostMateName(String costMateName) {
		this.costMateName = costMateName;
	}

	public CostMate getCostMate() {
		return costMate;
	}

	public void setCostMate(CostMate costMate) {
		this.costMate = costMate;
	}

	public List<CostMatePrice> getCostMatePriceList() {
		return costMatePriceList;
	}

	public void setCostMatePriceList(List<CostMatePrice> costMatePriceList) {
		this.costMatePriceList = costMatePriceList;
	}

	public StrategyOnlineOrder getTemplateBean() {
		return templateBean;
	}

	public void setTemplateBean(StrategyOnlineOrder templateBean) {
		this.templateBean = templateBean;
	}

	public String getSpecTypeCd() {
		return specTypeCd;
	}

	public void setSpecTypeCd(String specTypeCd) {
		this.specTypeCd = specTypeCd;
	}

	/**
	 * 战略下单网批调用
	 * @param resCostMatePriceId 选中的材料设备型号ID
	 * @return
	 */
	
	public String resInCreate(){
		
		if(StringUtils.isBlank(resCostMatePriceId))
			return null;
		
		CostMatePrice price =  costMatePriceManager.getEntity(resCostMatePriceId);
		costMate = price.getCostMate();//很重要
				
		//查询：自定义列头名称
		queryCostMateColList(price.getCostMate().getCostMateId());
		
		//查询：材料设备型号的列表(一条记录)
		getPriceRow(resCostMatePriceId);
		
		//获取类型名称
		initGroupTypeName(costMate.getParentModuleId());
		
		return "resInCreate";
	}
	
	//材料或者设备
	private void initGroupTypeName(String moduleId){

		String fullPath = costMateModuleManager.getBubblePath(moduleId);
		if(StringUtils.isNotBlank(fullPath)){
			if(fullPath.indexOf("材料") != -1){
				groupTypeName = "材料名称";
			}else if(fullPath.indexOf("设备") != -1){
				groupTypeName = "设备名称";
			}else{
				groupTypeName = "材料或设备名称";
			}
		}
	}

	/**
	 * 查询行数据
	 * @param resCostMatePriceId 选中的材料设备型号ID
	 * @return
	 */
	public String resInAppend(){
		
		if(StringUtils.isBlank(resCostMatePriceId))
			return null;
		CostMatePrice price =  costMatePriceManager.getEntity(resCostMatePriceId);
		costMate = price.getCostMate();//很重要
				
		//查询：自定义列头名称
		queryCostMateColList(price.getCostMate().getCostMateId());
		
		//查询：材料设备型号的列表(一条记录)
		getPriceRow(resCostMatePriceId);
		
		return "resInAppend";
	}

	//查询：材料设备型号的列表(一条记录)
	private void getPriceRow(String priceId){
		 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("costMatePriceId", priceId);
		String hql = "from CostMatePrice t where  t.costMatePriceId = :costMatePriceId ";
		page = costMatePriceManager.findPage(page, hql, map);
		costMatePriceList = page.getResult();
	}
	
	private void getPriceTemplate(String moduleId){

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("parentModuleId", moduleId);
		String hql = "from CostMatePrice t where  t.costMate.parentModuleId = :parentModuleId ";
		page = new Page<CostMatePrice>(1);
		page = costMatePriceManager.findPage(page, hql, map);
 	}
	
	/**
	 * (战略外)创建
	 * @return
	 */
	public String resOutCreate(){
		String tmpModuleId = Struts2Utils.getParameter("moduleId");
		setGroupName(tmpModuleId);
		

		costMate = costMateManager.getCostMate(tmpModuleId);//很重要
		if(costMate != null){
				
			//查询：自定义列头名称
			queryCostMateColList(costMate.getCostMateId());
			
			//查询：材料设备型号的列表(一条记录)
			getPriceTemplate(tmpModuleId);
			
			//获取类型名称
			initGroupTypeName(costMate.getParentModuleId());
		}
		
		
		return "resOutCreate";
	}
	/**
	 * (战略外)追加
	 * @return
	 */
	public String resOutAppend(){
		String tmpModuleId = Struts2Utils.getParameter("moduleId");
		setGroupName(tmpModuleId);
		

		costMate = costMateManager.getCostMate(tmpModuleId);//很重要
		if(costMate != null){
				
			//查询：自定义列头名称
			queryCostMateColList(costMate.getCostMateId());
			
			//查询：材料设备型号的列表(一条记录)
			getPriceTemplate(tmpModuleId);
			
			//获取类型名称
			initGroupTypeName(costMate.getParentModuleId());
		}
		
		
		
		return "resOutAppend";
	}

	private void setGroupName(String tmpModuleId){

		//材料或者设备
		String fullPath = costMateModuleManager.getBubblePath(tmpModuleId);
		if(StringUtils.isNotBlank(fullPath)){
			if(fullPath.indexOf("材料") != -1){
				groupTypeName = "材料名称";
			}else if(fullPath.indexOf("设备") != -1){
				groupTypeName = "设备名称";
			}else{
				groupTypeName = "材料或设备名称";
			}
		}
		
	}
	public String getResCostMatePriceId() {
		return resCostMatePriceId;
	}

	public void setResCostMatePriceId(String resCostMatePriceId) {
		this.resCostMatePriceId = resCostMatePriceId;
	}

	public String getGroupTypeName() {
		return groupTypeName;
	}

	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
	} 

	/**
	 * 计算当前行的总价
	 * @param priceId 材料设备的型号ID
	 * @param buyNum  数量
	 * @return
	 */
	public String calcRowData(){
		//型号ID
		String priceId = Struts2Utils.getParameter("costMatePriceId");
		//数量
		String buyNum  = Struts2Utils.getParameter("buyNum");
		//时价
		String cprice  = Struts2Utils.getParameter("currentPrice");
		
		BigDecimal rtnTotal = new BigDecimal(0);
		
		BigDecimal num = parseDefault(buyNum);

		if(StringUtils.isBlank(priceId) || StringUtils.isBlank(buyNum))
			return null;
		
		CostMatePrice costPrice = costMatePriceManager.getEntity(priceId);
		BigDecimal dbPrice = parse(costPrice.getPrice());
		CostMate mate = costPrice.getCostMate();
		if (mate != null) {
			
			//价格计算方式
			String calcTypeCd = mate.getCalcTypeCd();
			
			//特殊类型
			String tmpSpecTypeCd = mate.getSpecTypeCd();
			BigDecimal totalFloatRate = parse(mate.getTotalFloatRate());

			//时价
			BigDecimal currentPrice = new BigDecimal(StringUtils.isBlank(cprice)?"0":cprice);
			//均单价
			BigDecimal basePrice = parse(mate.getBasePrice());
			//上下浮动比例
			BigDecimal floatRate = parse(mate.getFloatRate());
			
			//最低价
			BigDecimal lowPrice = parse(mate.getLowPrice());
			//最高价
			BigDecimal highPrice = parse(mate.getHighPrice());
			//实际单价
			BigDecimal realPrice = null;
			 
			//定量 : calcTypeCd =1
			if("1".equals(calcTypeCd)){
				realPrice = dbPrice;
				

				//总价
				rtnTotal = num.multiply(realPrice);
			}
			//公式: calctypeCd =2
			else if("2".equals(calcTypeCd)){
				//电缆: specTypeCd = 1
				if("1".equals(tmpSpecTypeCd)){
					// 比较价格: 最低价 <= 时价 <= 最高价之间  , 1-小 0-等于 1-大于
					if(currentPrice.compareTo(lowPrice) == -1 || currentPrice.compareTo(highPrice) == 1) {
						//Struts2Utils.renderText("error, 您输入的 铜价 必须介于" + lowPrice.toString() + "和" + highPrice.toString() + "之间。");
						Struts2Utils.renderText("price_range");//价格区间不对
						return null;
					}

					//计算公式 ：实时价格=(1+(55555-55000)/1000* (1.5/100))*6.52
					//计算公式 ：实时价格=(1+(时价-均单价)/1000*上下浮动比列)*型号的录入价格
					realPrice = (new BigDecimal(1).add(((currentPrice.subtract(basePrice)).divide(new BigDecimal(1000))).multiply(floatRate.divide(new BigDecimal(100))))).multiply(dbPrice);
					realPrice = realPrice.setScale(00, BigDecimal.ROUND_HALF_UP); //结果四舍五入(不要保留小数点)
				}
				//其他： specTypeCd = 1 (默认定量)
				else{
					realPrice = dbPrice;	
				}
				

				//总价
				rtnTotal = num.multiply(realPrice);
			}
			//总价浮动： calcTypeCd = 3
			else if("3".equals(calcTypeCd)){

				realPrice = dbPrice;
				//总价
				rtnTotal = num.multiply(realPrice).multiply((new BigDecimal(1).add(totalFloatRate.divide(new BigDecimal(100)))));
			}
			else{
				//默认按单价计算
				rtnTotal = num.multiply(realPrice);
			}

			//返回结果
			Struts2Utils.renderText("success,"+String.valueOf(realPrice)+","+String.valueOf(rtnTotal)+","+String.valueOf(lowPrice)+","+String.valueOf(highPrice));
		}else{
			//返回结果
			Struts2Utils.renderText("failure");
		}
		
		return null;
	}
	
	private BigDecimal parseDefault(String val){
		if(StringUtils.isBlank(val))
			return new BigDecimal(0);
		else
			return new BigDecimal(val.trim());
	}
	
	private BigDecimal parse(BigDecimal val){
		if(val == null)
			return new BigDecimal(0);
		else
			return val;
	}
}

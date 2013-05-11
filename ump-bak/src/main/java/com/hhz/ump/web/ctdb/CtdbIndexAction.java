/**  
 * CtdbIndex.java  
 * com.hhz.ump.web.ctdb  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-19        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.ctdb;  

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.prod.ProdBasicVersion;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.web.bid.PoiStyle;
import com.hhz.uums.entity.ws.WsAppDictType;

/**  
 * ClassName:CtdbIndex  
 * Function: 成本数据库首页
 * Reason:  成本数据库首页 
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-12-19        下午04:00:17  
 *  
 */
@Namespace("/ctdb")
@Results( { @Result(name = "exportExcel", type = "stream", params = { "contentType", "application/vnd.ms-excel","inputName", "excelFile", "contentDisposition", "attachment;filename=${excelFileName}.xls" }) })
public class CtdbIndexAction   extends CrudActionSupport{
	
	private static final long serialVersionUID = -4835446695834765582L;
	
	/**
	 * 日志工具
	 */
	private static final Log log = LogFactory.getLog(CtdbIndexAction.class);	
	/**
	 * 导出Excel需要的文件
	 */
	private InputStream excelFile;
	/**
	 * Excel文件文件名
	 */
	private String excelFileName;

	
	
	/**
	 * 定标数据库关系树
	 * 
	 * @param singleFlg 若为'true',单选;否则默认多选
	 * @return
	 */
	public String firstTree() {

	/*	TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("定标数据库");
		node.setOrderNo(new Long(0));
		node.setFinType("1");
		List<TreeNode> children=null;
		node=makeChildren(node);
		Struts2Utils.renderJson(node);*/
		//String singleFlg = Struts2Utils.getParameter("singleFlg");
		Struts2Utils.renderJson(getAreaProjectTree("定标数据库",0,"定标数据库"));
		return null;
	}
	
	
	/**
	 * 
	 *构造南北区树
	 */
	private TreeNode makeChildren(TreeNode treeNode) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		children.add(makeChildrenByArea(treeNode,DictContants.AREA_ORG_NORTH));
		treeNode.setChildren(children);
		treeNode.getChildren().add(makeChildrenByArea(treeNode,DictContants.AREA_ORG_SOUTH));		
		return treeNode;  
		
	}
	
	
	/**
	 * 
	 * 根据区域构造树
	 */
	public TreeNode makeChildrenByArea(TreeNode treeNode,String area) {
		TreeNode secNode=new TreeNode();
		secNode.setId(area);
		secNode.setChecked("0");
		if(DictContants.AREA_ORG_NORTH.equals(area)) {
			secNode.setText("北区项目");
		}else{
			secNode.setText("南区项目");
		}
		
		List<TreeNode> children = new ArrayList<TreeNode>();
		
		Map<String,String> map = PlasCache.getDictDataMap(area);	
		if(!map.isEmpty()){
			TreeNode node=null;
			Iterator<String> it = map.keySet().iterator();
			int i=0;
			while(it.hasNext()){
				String key=it.next();
				
				node=new TreeNode();
				node.setId(key);
				node.setText(map.get(key));
				node.setParentId(area);
				node.setParentName(area);
				node.setChecked("0");
				//node.setFinType(sort.getSortType());
				//node.setOrderNo(String.valueOf(i));
				i++;
				children.add(node);
			}
		}
		
		secNode.setChildren(children);
		return secNode;  
	}
	

	/**
	 * 定标数据库关系树
	 * 
	 * @param singleFlg 若为'true',单选;否则默认多选
	 * @return
	 */
	public String secondTree() {
		Struts2Utils.renderJson(getAreaProjectTree("结算数据库",1,"结算数据库"));
		return null;
	}
	
	
	/**
	 * 清单关系树
	 * 
	 * @param singleFlg 若为'true',单选;否则默认多选
	 * @return
	 */
	public String thirdTree() {
		Struts2Utils.renderJson(getListTree("清单数据库",2,"清单数据库"));
		return null;
	}
	
	
	/**
	 * 获取区域节点
	 * @param appDictTypeCd 区域类型 
	 * @param multiFlg 复选
	 * @return
	 */
	public  TreePanelNode getDictNode(String appDictTypeCd,boolean multiFlg,String rootName){

		WsAppDictType type = PlasCache.getDictType(appDictTypeCd);
		if(type == null)
			return null;

		String nodeCd = type.getDictTypeCd();
		String nodeName = type.getDictTypeName();
		int count = 1;
		if(StringUtils.isBlank(nodeCd)){
			nodeCd = "node"+ String.valueOf(count++);
		}
		TreePanelNode rootNode = getNode(nodeCd,nodeName, NODE_TYPE_ROLE_AREA, multiFlg? NODE_CHECKED_NONE: NODE_CHECKED_UNDEFINED,rootName);//区域
		Map<String,String> map = PlasCache.getDictDataMap(appDictTypeCd);
		if( map != null && map.keySet().size() > 0){
			for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
				nodeCd = iter.next();
				if(StringUtils.isBlank(nodeCd)){
					continue;
				}
				nodeName = map.get(nodeCd);
				rootNode.getChildren().add(getNode(nodeCd,nodeName, NODE_TYPE_ROLE_PROJECT, multiFlg? NODE_CHECKED_NONE: NODE_CHECKED_UNDEFINED,rootName));//项目
			}
		}
		return rootNode;
	}
	public  List<TreePanelNode> getDictNodeList(String appDictTypeCd,boolean multiFlg,String rootName){

		List<TreePanelNode> rtnNodeList = new ArrayList<TreePanelNode>();
		
		WsAppDictType type = PlasCache.getDictType(appDictTypeCd);
		if(type != null){
	
			String nodeCd = type.getDictTypeCd();
			String nodeName = type.getDictTypeName();
			int count = 1;
			if(StringUtils.isBlank(nodeCd)){
				nodeCd = "node"+ String.valueOf(count++);
			}
			Map<String,String> map = PlasCache.getDictDataMap(appDictTypeCd);
			if( map != null && map.keySet().size() > 0){
				for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
					nodeCd = iter.next();
					if(StringUtils.isBlank(nodeCd)){
						continue;
					}
					nodeName = map.get(nodeCd);
					rtnNodeList.add(getNode(nodeCd,nodeName, NODE_TYPE_ROLE_PROJECT, multiFlg? NODE_CHECKED_NONE: NODE_CHECKED_UNDEFINED,rootName));//项目
				}
			}
		}
		return rtnNodeList;
	}
	
	
	/**
	 * 获取区域节点
	 * @param appDictTypeCd 区域类型 
	 * @param multiFlg 复选
	 * @return
	 */
	public  TreePanelNode getDictNode2(String appDictTypeCd,boolean multiFlg,String rootName){
		String nodeCd="";
		String nodeName="";
		if(appDictTypeCd.equals(DictContants.CTDB_CONSTRUCTION_CATE)){
			  nodeCd = "jiesuan-tujian-01";
			  nodeName = "土建";
		}else{
			  nodeCd = "jiesuan-anzhuang-01";
			  nodeName = "安裝";
		}
		
		int count = 1;
		if(StringUtils.isBlank(nodeCd)){
			nodeCd = "node"+ String.valueOf(count++);
		}
		TreePanelNode rootNode = getNode(nodeCd,nodeName, NODE_TYPE_ROLE_AREA, multiFlg? NODE_CHECKED_NONE: NODE_CHECKED_UNDEFINED,rootName);//区域
		Map<String,String> map = DictMapUtil.getMapCtdb(appDictTypeCd);
		if( map != null && map.keySet().size() > 0){
			for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
				nodeCd = iter.next();
				if(StringUtils.isBlank(nodeCd)){
					continue;
				}
				nodeName = map.get(nodeCd);
				rootNode.getChildren().add(getNode(nodeCd,nodeName, NODE_TYPE_ROLE_PROJECT, multiFlg? NODE_CHECKED_NONE: NODE_CHECKED_UNDEFINED,rootName));//项目
			}
		}
		return rootNode;
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
	private  TreePanelNode getNode(String nodeCd, String nodeName, String typeCd, String checkedType,String rootName){
		TreePanelNode tmp = new TreePanelNode();
		tmp.setEntityId(nodeCd);
		tmp.setEntityCd(nodeCd);
		tmp.setEntityBizCd(nodeCd);
		tmp.setEntityName(nodeName);
		tmp.setNodeType(typeCd);
		tmp.setChecked(checkedType);
		tmp.setExtParam(rootName);
		tmp.setId(nodeCd);
		tmp.setText(nodeName);
		return tmp;
	}
	
	
	/**
	 * 构造区域机构树(根-区域-项目)
	 * @return
	 */
	public  TreePanelNode getAreaProjectTree(String rootNodeName,int num,String rootName){
		
		TreePanelNode rootNode = getRootOrg(num); 

		rootNode.getChildren().addAll(getDictNodeList(DictContants.AREA_ORG_NORTH, true,rootName));
		rootNode.getChildren().addAll(getDictNodeList(DictContants.AREA_ORG_SOUTH, true,rootName));
		
//		rootNode.getChildren().add(getDictNode(DictContants.AREA_ORG_NORTH, true,rootName));
//		rootNode.getChildren().add(getDictNode(DictContants.AREA_ORG_SOUTH, true,rootName));
		
		return rootNode;
	}
	
	
	/**
	 * 构造清单树(根-区域-项目)
	 * @return
	 */
	public  TreePanelNode getListTree(String rootNodeName,int num,String rootName){
		
		TreePanelNode rootNode = getRootOrg(num); 
		rootNode.getChildren().add(getDictNode2(DictContants.CTDB_CONSTRUCTION_CATE, true,rootName));
		rootNode.getChildren().add(getDictNode2(DictContants.CTDB_INSTALLATION_CATE, true,rootName));
		
		return rootNode;
	}
	
	public  String DEFAULT_ROOT_ORG_ID_ONE = "0"; 
	public  String DEFAULT_ROOT_ORG_CD_ONE = "0"; 
	public  String DEFAULT_ROOT_ORG_BIZ_CD_ONE = "0"; 
	public  String DEFAULT_ROOT_ORG_NAME_ONE = "定标数据库"; 
	public  String NODE_TYPE_ORG_ONE = "1";
	
	public  String DEFAULT_ROOT_ORG_ID_TWO = "1"; 
	public  String DEFAULT_ROOT_ORG_CD_TWO = "1"; 
	public  String DEFAULT_ROOT_ORG_BIZ_CD_TWO = "1"; 
	public  String DEFAULT_ROOT_ORG_NAME_TWO = "结算数据库"; 
	public  String NODE_TYPE_ORG_TWO = "1";
	
	public  String DEFAULT_ROOT_ORG_ID_THREE = "2"; 
	public  String DEFAULT_ROOT_ORG_CD_THREE = "2"; 
	public  String DEFAULT_ROOT_ORG_BIZ_CD_THREE = "2"; 
	public  String DEFAULT_ROOT_ORG_NAME_THREE = "清单数据库"; 
	public  String NODE_TYPE_ORG_THREE = "1";

	public  String NODE_TYPE_ROLE_ALL = "root";//总部
	public  String NODE_TYPE_ROLE_AREA = "area";//区域
	public  String NODE_TYPE_ROLE_PROJECT = "project";//项目	
	// 选择状态 : 0-不选中 1-选中 2-半选
	public  String NODE_CHECKED_UNDEFINED = "undefined";
	public  String NODE_CHECKED_NONE = "0";
	public  String NODE_CHECKED_CHECKED = "1";
	
	
	/**
	 * 根节点
	 * @param num
	 * @return
	 */
	private  TreePanelNode getRootOrg(int num) {
		TreePanelNode node = new TreePanelNode();
		if(num==0){
			node.setId(DEFAULT_ROOT_ORG_ID_ONE);
			node.setText(DEFAULT_ROOT_ORG_NAME_ONE);
			node.setEntityId(DEFAULT_ROOT_ORG_ID_ONE);
			node.setEntityCd(DEFAULT_ROOT_ORG_CD_ONE);
			node.setEntityBizCd(DEFAULT_ROOT_ORG_BIZ_CD_ONE);
			node.setEntityName(DEFAULT_ROOT_ORG_NAME_ONE);
			
			node.setNodeType(NODE_TYPE_ORG_ONE);
			node.setOrgOrUser(NODE_TYPE_ORG_ONE);
		}else if(num==1){
			node.setId(DEFAULT_ROOT_ORG_ID_TWO);
			node.setText(DEFAULT_ROOT_ORG_NAME_TWO);
			node.setEntityId(DEFAULT_ROOT_ORG_ID_TWO);
			node.setEntityCd(DEFAULT_ROOT_ORG_CD_TWO);
			node.setEntityBizCd(DEFAULT_ROOT_ORG_BIZ_CD_TWO);
			node.setEntityName(DEFAULT_ROOT_ORG_NAME_TWO);
			
			node.setNodeType(NODE_TYPE_ORG_TWO);
			node.setOrgOrUser(NODE_TYPE_ORG_TWO);
		}else{
			node.setId(DEFAULT_ROOT_ORG_ID_THREE);
			node.setText(DEFAULT_ROOT_ORG_NAME_THREE);
			node.setEntityId(DEFAULT_ROOT_ORG_ID_THREE);
			node.setEntityCd(DEFAULT_ROOT_ORG_CD_THREE);
			node.setEntityBizCd(DEFAULT_ROOT_ORG_BIZ_CD_THREE);
			node.setEntityName(DEFAULT_ROOT_ORG_NAME_THREE);
			
			node.setNodeType(NODE_TYPE_ORG_THREE);
			node.setOrgOrUser(NODE_TYPE_ORG_THREE);
		}
		return node;
	}
	
	/**
	 * 获取搜索表单类型及内容
	 * 
	 */
	public String getForm(){
		 String formType=Struts2Utils.getParameter("type");
		//清单数据库搜索表单
		//String formType = "listContent";
		//结算数据库搜索表单
		 //formType = "billingApp";
		//定标数据库搜索表单
		 //formType = "fixedBidForm";
		 
		return formType;
	}
	
	/**
	 * 导出EXCEL模板
	 * 
	 */
	public String exportExcel(){
		String firstCatName="";
		String secCatName="";
		String firstCat=Struts2Utils.getParameter("firstCat");
		String secCat=Struts2Utils.getParameter("secCat");
		if(StringUtils.isNotBlank(firstCat)){
			//大类
			if("CTDB_CONSTRUCTION_CATE".equals(firstCat)){
				firstCatName="土建";
				
			}else if("CTDB_INSTALLATION_CATE".equals(firstCat)){
				firstCatName="安装";
				
			}
			//小类
			if(StringUtils.isNotBlank(secCat)){
				secCatName=DictMapUtil.getMapCtdb(firstCat.trim()).get(secCat);
			}
		}
		
		
		buildExcel("清单数据库模板",firstCatName,secCatName);		
		return "exportExcel";
	}
	
	/**
	 *建立导出文件
	 */
	private  void buildExcel(String sheetName,String firstCat,String secCat){
		ByteArrayOutputStream outExcelFile=null;
		try {
			excelFileName=sheetName;
			//输出文件流
			outExcelFile= new ByteArrayOutputStream();
			//将分析数据填入excel
			fillInExcel(sheetName,outExcelFile,firstCat,secCat);	
			//将数据读出到数组
			byte[] data = outExcelFile.toByteArray();
			//将数据写入文件，执行导出
			excelFile=new ByteArrayInputStream(data);
			//文件名编码转换，防止乱码
			excelFileName=new String(excelFileName.getBytes("GBK"), "ISO8859-1");
		} catch (Exception e) {
			e.printStackTrace();  
			
		}finally{
			try {
				if (outExcelFile != null) {
					outExcelFile.close();
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
	
	
	/**
	 * 
	 *填充Excel文件
	 */
	private void fillInExcel(String sheetName, ByteArrayOutputStream outExcelFile,String firstCat,String secCat) {
		// 声明一个工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(sheetName);
	      // 设置表格默认列宽度为15个字节
	      sheet.setDefaultColumnWidth((short) 15);
	      sheet.setDefaultRowHeight((short)25);
	      //冻结
	      //sheet.createFreezePane(6, 2);
	      // 生成一个样式
	      HSSFCellStyle style = PoiStyle.buildStyle(workbook);	      
	      // 生成并设置另一个样式
	      HSSFCellStyle style2 = PoiStyle.buildStyle2(workbook);	      
	      // 生成并设置另一个样式
	      HSSFCellStyle style3 = PoiStyle.buildStyle3(workbook);
	      // 生成并设置另一个样式
	      HSSFCellStyle style4 = PoiStyle.buildStyle4(workbook);    
	     
	      //单元格
	      HSSFCell cell=null;
	      //单元格内容
	      HSSFRichTextString text=null;
	      //合并单元区域
	      CellRangeAddress address=null;
	      //mergeCell(sheet,address,0,1,0,0);
	      //第1行
	      HSSFRow row = sheet.createRow(0);
	      //数据库大类
	      HSSFCellStyle style5=workbook.createCellStyle();
	      //克隆以个样式
	      style5.cloneStyleFrom(style4);
	      style5.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	      //s生成一个字体
	      HSSFFont font = workbook.createFont();
	      font.setColor(HSSFColor.ORANGE.index);
	      font.setFontHeightInPoints((short) 10);
	      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	      style5.setFont(font);	      
	      addCell(row,cell,text,0,style5,"1","数据库大类:",null);
	      addCell(row,cell,text,1,style4,"1",firstCat,null);
	      addCell(row,cell,text,2,style4,"1","",null);
	      mergeCell(sheet,address,0,0,1,2);
	      //小类
	      addCell(row,cell,text,3,style5,"1","小类:",null);
	      addCell(row,cell,text,4,style4,"1",secCat,null);
	      addCell(row,cell,text,5,style4,"1","",null);
	      mergeCell(sheet,address,0,0,4,5);
	      //第2行
	      row = sheet.createRow(1);	 
	      //序号		  		  
	      //addCell(row,cell,text,0,style4,"1","序号",null);
	      //清单编号
	      addCell(row,cell,text,0,style4,"1","清单编号",null);
	      //清单名称
	      addCell(row,cell,text,1,style4,"1","清单名称",null);
	      //说明
	      addCell(row,cell,text,2,style4,"1","说明",null);
	      //数量
	      addCell(row,cell,text,3,style4,"1","数量",null);
	      //单位
	      addCell(row,cell,text,4,style4,"1","单位",null);
	      //单价（元）
	      addCell(row,cell,text,5,style4,"1","单价（元）",null);
	      //单价组价明细					
	      addCell(row,cell,text,6,style4,"1","单价组价明细",null);
	      //新建5列空表格
	      addCell(row,cell,text,7,style4,"1","",null);
	      addCell(row,cell,text,8,style4,"1","",null);
	      addCell(row,cell,text,9,style4,"1","",null);
	      addCell(row,cell,text,10,style4,"1","",null);
	      addCell(row,cell,text,11,style4,"1","",null);
	      mergeCell(sheet,address,1,1,6,11);
	      //主材耗损率
	      addCell(row,cell,text,12,style4,"1","主材耗损率",null);
	      //价格来源
	      addCell(row,cell,text,13,style4,"1","价格来源",null);
	      //第3行
	      row = sheet.createRow(2);	
	      //先建6个空表格
	      addCell(row,cell,text,0,style4,"1","",null);
	      mergeCell(sheet,address,1,2,0,0);
	      addCell(row,cell,text,1,style4,"1","",null);
	      mergeCell(sheet,address,1,2,1,1);
	      addCell(row,cell,text,2,style4,"1","",null);
	      mergeCell(sheet,address,1,2,2,2);
	      addCell(row,cell,text,3,style4,"1","",null);
	      mergeCell(sheet,address,1,2,3,3);
	      addCell(row,cell,text,4,style4,"1","",null);
	      mergeCell(sheet,address,1,2,4,4);
	      addCell(row,cell,text,5,style4,"1","",null);
	      mergeCell(sheet,address,1,2,5,5);
	      //人工费
	      addCell(row,cell,text,6,style4,"1","人工费",null);
	      //主材费
	      addCell(row,cell,text,7,style4,"1","主材费",null);
	      //辅材费
	      addCell(row,cell,text,8,style4,"1","辅材费",null);
	      //机械费
	      addCell(row,cell,text,9,style4,"1","机械费",null);
	      //管理费及利润
	      addCell(row,cell,text,10,style4,"1","管理费及利润",null);
	      //税金
	      addCell(row,cell,text,11,style4,"1","税金",null);
	      //主材耗损率
	      addCell(row,cell,text,12,style4,"1","",null);
	      mergeCell(sheet,address,1,2,12,12);
	      //价格来源
	      addCell(row,cell,text,13,style4,"1","",null);
	      mergeCell(sheet,address,1,2,13,13);
	      try {
			workbook.write(outExcelFile);
		} catch (IOException e) {
			e.printStackTrace();			
		}finally{
			if(workbook!=null){
				workbook=null;
			}
		}

	}
	
	/**
	 * 
	 *插入单元格
	 */
	private void addCell(HSSFRow row,HSSFCell cell,HSSFRichTextString text,int colIndex,HSSFCellStyle style,String contentType,String strContent,Double douContent){
		//根据下标创建单元格
		cell= row.createCell(colIndex);
		//设置单元格格式
		cell.setCellStyle(style);
		//设置单行号
		row.setHeightInPoints(25);
		//如果为字符类型单元格,则创建字符串型单元格格式
		if("1".equals(contentType)){
			text= new HSSFRichTextString(strContent);
			cell.setCellValue(text);
		}else{
			cell.setCellValue(douContent);
		}
	}
	
	/**
	 * 
	 *合并单元格
	 */
	private void mergeCell(HSSFSheet sheet,CellRangeAddress address,int col1,int col2,int col3,int col4){
		//合并区域
		address=new CellRangeAddress(col1,col2,col3,col4);
		//执行合并
        sheet.addMergedRegion(address);
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
	public String list() throws Exception {
		return "list";  
		
	}

	@Override
	protected void prepareModel() throws Exception {
	}

	@Override
	public String save() throws Exception {
		return null;  
		
	}

	@Override
	public ProdBasicVersion getModel() {
		return null;  
		
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
	
	

}
  

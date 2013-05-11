package com.hhz.ump.web.cost;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostMateManager;
import com.hhz.ump.dao.cost.CostMateModuleManager;
import com.hhz.ump.entity.cost.CostMate;
import com.hhz.ump.entity.cost.CostMateModule;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;

@Namespace("/cost")
@Results( { @Result(name = CostMateModuleAction.RELOAD, location = "cost-mate-module!main.action", type = "redirect", params = {
		"parentId", "${parentId}" })})
public class CostMateModuleAction extends CrudActionSupport<CostMateModule> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CostMateModuleManager costMateModuleManager;
	@Autowired
	private CostMateManager costMateManager;
	private List<CostMateModule> costMateModuleList;
	private CostMateModule entity;
	private String id;
	private int count;
	//父类ID
	private String parentId;
	private String moduleName;
	private String enableFlg;
	//导入excle文件
	private File upload;
	private String uploadFileName;
	
	
	public String main() throws Exception {
		return "main";
	}
	
	@Override
	public String list() throws Exception {
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from CostMateModule t where 1=1 ");
		if(StringUtils.isNotBlank(parentId)){
			hql.append(" and t.parentId = :parentId");
			values.put("parentId",parentId);
		}else{
			//默认搜索小类
			hql.append(" and t.parentId != :parentId");
			values.put("parentId","0");
		}
		if(StringUtils.isNotBlank(moduleName)){
			hql.append(" and t.moduleName like :moduleName");
			values.put("moduleName","%" + moduleName.trim() + "%");
		}
		if(StringUtils.isNotBlank(enableFlg)){
			hql.append(" and t.enableFlg = :enableFlg");
			values.put("enableFlg", Long.valueOf(enableFlg));
		}
		hql.append(" order by t.parentId asc, t.sequenceNo asc,t.createdDate asc");
		
		//展示全部
		page.setPageSize(100);
		
		costMateModuleList = costMateModuleManager.findPage(page, hql.toString(),values).getResult();
		if(StringUtils.isNotBlank(parentId) && "0".equals(parentId))
			return "listType"; //大类列表页
		else
			return "list"; //小类列表页
	}

	@Override
	public String save() throws Exception {
		String moduleType = Struts2Utils.getParameter("moduleType");
		try {
			if("0".equals(parentId)){
				entity.setParentId("0");
			}else{
				if(StringUtils.isNotBlank(moduleType)){
					entity.setParentId(moduleType);
				}else{
					entity.setParentId(parentId);
				}
			}
			costMateModuleManager.saveCostMateModule(entity);
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderText("fail");
		}
		return null;
	}
	
	@Override
	public String delete() throws Exception {
		String msg = "";
		if (StringUtils.isNotBlank(getId())) {
			entity = costMateModuleManager.getEntity(getId());
			if(entity != null){
				List<CostMate> mateList = this.getCostMateListByParentId(entity.getCostMateModuleId());
				int counts = getDelMateModuleCount(entity.getCostMateModuleId(),0);
				if((mateList != null && mateList.size()>0) || counts > 0){
					msg = "nodel";
				}else{
					deleteMateModule(entity.getCostMateModuleId());
					costMateModuleManager.delete(entity);
					msg = "success";
				}
			}
		}
		Struts2Utils.renderText(msg);
		return null;
	}
	
	//得到子类有设备的类型总数
	public int getDelMateModuleCount(String moduleId,int i){
		count = i;
		List<CostMateModule> list = costMateModuleManager.getCostMateModuleByParentId(moduleId);
		if(list != null && list.size() > 0){
			List<CostMate> mList = null;
			for(CostMateModule module : list) {
				mList = this.getCostMateListByParentId(module.getCostMateModuleId());
				if(mList != null && mList.size() > 0){
					count++;
				}
				getDelMateModuleCount(module.getCostMateModuleId(),count);
			}
		}
		return count;
	}
	//删除子类
	public String deleteMateModule(String costMateModuleId){
		List<CostMateModule> list = costMateModuleManager.getCostMateModuleByParentId(costMateModuleId);
		if(list != null && list.size() > 0){
			List<CostMate> mList = null;
			for (CostMateModule module : list) {
				mList = this.getCostMateListByParentId(module.getCostMateModuleId());
				if(mList == null || mList.size() <= 0){
					costMateModuleManager.delete(module);
				}
				deleteMateModule(module.getCostMateModuleId());
			}
		}
		return null;
	}
	
	/**
	 * 检测设备类型名称是否唯一
	 * @param moduleName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String checkMoudleName() throws Exception{
		String name = Struts2Utils.getParameter("moduleName");
		String moduleId = Struts2Utils.getParameter("id");
		if(StringUtils.isNotBlank(name)){
			List<CostMateModule> moduleList = costMateModuleManager.checkMoudleName(name);
			if (moduleList != null && moduleList.size() > 0) {
				if(StringUtils.isNotBlank(moduleId)){
					CostMateModule module = costMateModuleManager.getEntity(moduleId);
					if (module != null && name.trim().equals(module.getModuleName())) {
						Struts2Utils.renderText("fail");
						return null;
					}
				}
				Struts2Utils.renderText("success");
			}else{
				Struts2Utils.renderText("fail");
			}
		}
		return null;
	}
	
	/**
	 * 根据大类ID得到小类
	 * @param pId 大类ID
	 * @return
	 */
	public List<CostMate> getCostMateListByParentId(String pId){
		List<CostMate> mateList = new ArrayList<CostMate>();
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from CostMate t where t.parentModuleId = :parentId");
		values.put("parentId", pId);
		mateList = costMateManager.find(hql.toString(),values);
		return mateList;
	}
	
	//导入类型
	public String costMateModuleImport(){
		return "import";
	}
	
	/**
	 * 导入Excel数据：类型、供应商等
	 * 
	 * @param parentId 类型ID
	 * @return
	 */
	public String importExlPoi() throws Exception{
		if(upload !=null && StringUtils.isNotBlank(uploadFileName)){
			long begin = new Date().getTime();
			Workbook excelWorkbook = null;
			Sheet excleSheet = null;
			try {
				//读取excl 
				int index = uploadFileName.lastIndexOf(".");
				String str = uploadFileName.substring(index);
				if(".xlsx".equals(str)){//支持excel 2007的读取
					excelWorkbook = new XSSFWorkbook(new FileInputStream(upload));
				}else if(".xls".equals(str)){ //支持excel 2003的读取
					excelWorkbook = new HSSFWorkbook(new FileInputStream(upload));
				}else{
					Struts2Utils.renderText(",error,您导入的不是Excel文件,");
					return null;
				};
				excleSheet = excelWorkbook.getSheetAt(0);
				// 读取excl
				TreePanelNode rootNode = readCostMateModule(excleSheet);
				// 保存excl
				saveCostMateTree(rootNode);
				long end = new Date().getTime();
				System.out.println("导入用时-POI：" + (end - begin) + "毫秒");
				Struts2Utils.renderText(",success," + (end - begin) / 1000 + ",");
			}catch (Exception e) {
				e.printStackTrace();
				Struts2Utils.renderText(",error," + "未导入成功!" + ", " + e + ",");
			} finally{
				upload.delete();
			}
		}
		return null;
	}
	
	private TreePanelNode readCostMateModule(Sheet sheet){
		int countRow = sheet.getPhysicalNumberOfRows(); 

		TreePanelNode rootNode = new TreePanelNode();
		TreePanelNode firstNode = new TreePanelNode();
		TreePanelNode secondNode = new TreePanelNode();
		TreePanelNode thirdNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("战略材料设备库");
		Row currRow = null;
		Row nextRow = null;
		Cell currCell = null;
		Cell nextCell = null;
		for(int row = 1; row < countRow; row++) {
			currRow = sheet.getRow(row);
			if (row < countRow-1) {
			nextRow = sheet.getRow(row+1);
//			if (nextRow != null) {
				int countCol = currRow.getLastCellNum();
				for (int col = 0; col < countCol; col++) {
					currCell = currRow.getCell(col);
					nextCell = nextRow.getCell(col);
					if (currCell != null){
						String currValue = currCell.getStringCellValue();
						if(currCell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(currValue)){
							if (col == 0) {
								firstNode.setText(currValue);
//								rootNode.getChildren().add(firstNode);
								//addSonNode(rootNode, currValue);
								if (nextCell != null){
									String nextValue = nextCell.getStringCellValue();
									if(nextCell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(nextValue)){
										//addSonNode(rootNode, nextValue);
										firstNode.setText(nextValue);
									}
								}
								rootNode.getChildren().add(firstNode);
								//rootNode.addChild(firstNode);
							}
							if (col == 1) {
								secondNode.setText(currValue);
//								firstNode.getChildren().add(secondNode);
								//addSonNode(secondNode, currValue);
								if (nextCell != null){
									String nextValue = nextCell.getStringCellValue();
									if(nextCell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(nextValue)){
										//addSonNode(secondNode, nextValue);
										secondNode.setText(nextValue);
									}
								}
								firstNode.getChildren().add(secondNode);
							}
							if (col == 2) {
								thirdNode.setText(currValue);
//								secondNode.getChildren().add(thirdNode);
								//addSonNode(thirdNode, currValue);
								if (nextCell != null){
									String nextValue = nextCell.getStringCellValue();
									if(nextCell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(nextValue)){
										//addSonNode(thirdNode, nextValue);
										thirdNode.setText(nextValue);
									}
								}
								secondNode.getChildren().add(thirdNode);
							}
						}
					}
				}
			}
		}
			//addSonNode(rootNode, rowIndex, 0, sheet);
//		}
		//secondNode.getChildren().add(thirdNode);
		//firstNode.getChildren().add(secondNode);
//		rootNode.getChildren().add(firstNode);
		return rootNode;
	}
	
	
	private void addSonNode(TreePanelNode node,String cellValue){
		TreePanelNode tmpNode = new TreePanelNode();
		tmpNode.setText(cellValue);
		node.getChildren().add(tmpNode);
	}
	
	private void addSonNode2(TreePanelNode node, int rowIndex, int colIndex, Sheet sheet){
		
		if(rowIndex >= (sheet.getLastRowNum()-1))
			return;
		
		Row tNextRow = sheet.getRow(rowIndex+1);
		
		int nextRowColNum = tNextRow.getLastCellNum();
		
		int focusColIndex = (nextRowColNum-1);
		
		if(colIndex < focusColIndex){
			Cell tCell = tNextRow.getCell(colIndex + 1);
			if (tCell != null) {
				if(tCell.getCellType() == Cell.CELL_TYPE_STRING){
					TreePanelNode tmpNode = new TreePanelNode();
					tmpNode.setText(tCell.getStringCellValue());
					node.getChildren().add(tmpNode);
					addSonNode2(tmpNode, (rowIndex+1), (colIndex+1), sheet);
				}
			}
		}
	}
	
	/*private TreePanelNode readCostMateModule(Sheet sheet){
		int countRow = sheet.getPhysicalNumberOfRows(); //总行数

		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText("战略材料设备库");
		
		//上下行数据关系规则:错行，列+1
		 
		int iStartRow = 1;
		int iStartCol = 1;
		
		int totalRow = 100;
		int totalCol = 5;
		
		Map<Long, List<TreePanelNode>> map = new HashMap<Long, List<TreePanelNode>>();
		
		Row startRow = sheet.getRow(iStartRow);
		Cell tCell = startRow.getCell(iStartCol);//取下一行，列加1
		if (tCell != null) {
			if(tCell.getCellType() == Cell.CELL_TYPE_STRING){
				TreePanelNode tmpNode = new TreePanelNode();
				tmpNode.setText(tCell.getStringCellValue());
				rootNode.getChildren().add(tmpNode);
				//逐行读取数据
				List<TreePanelNode> list = new ArrayList<TreePanelNode>();
				list.add(rootNode);
				map.put(1L, list);
				appendNextNode(map, (1L+1), rootNode, tmpNode, sheet, (iStartRow + 1), iStartCol);
				//saveCostMateTree(rootNode);
			}
		}
		 
		return rootNode;
	}*/
	
	private int appendNextNode(Map<Long, List<TreePanelNode>> map,Long curRowLevel, TreePanelNode ppNode,TreePanelNode pNode,Sheet sheet, int iCurrentRow, int iStartCol){

		Row currentRow = sheet.getRow(iCurrentRow);
		
		//+1列 儿子
		int col = iStartCol + 1;
		Cell tCell = currentRow.getCell(col);
		if (tCell != null && tCell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(tCell.getStringCellValue())){
			TreePanelNode tmpNode = new TreePanelNode();
			tmpNode.setText(tCell.getStringCellValue());
			//确认父子关系
			pNode.getChildren().add(tmpNode);
			
			//追加到第curRowLevel节点
			(map.get(curRowLevel)).add(tmpNode);
			
			return appendNextNode(map, (curRowLevel+1), pNode, tmpNode, sheet, (iCurrentRow+1), (col+1));
		}
		
		//同列
		Cell tCell2 = currentRow.getCell(iStartCol);
		if (tCell2 != null && tCell2.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(tCell2.getStringCellValue())){
			TreePanelNode tmpNode = new TreePanelNode();
			tmpNode.setText(tCell2.getStringCellValue());
			ppNode.getChildren().add(tmpNode);

			//追加到第curRowLevel节点
			(map.get(curRowLevel-1)).add(tmpNode);
			
			return appendNextNode(map,(curRowLevel), pNode, tmpNode, sheet, (iCurrentRow+1), col);
		}
		
		//-1列
		Cell tCell3 = currentRow.getCell(iStartCol-1);
		if (tCell3 != null && tCell3.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isNotBlank(tCell3.getStringCellValue())){
			TreePanelNode tmpNode = new TreePanelNode();
			tmpNode.setText(tCell3.getStringCellValue());

			List<TreePanelNode> tmpList = (map.get(curRowLevel-3));
			if(tmpList == null)
				return iCurrentRow;
			TreePanelNode tmp = null;
			for (TreePanelNode tNode : tmpList) {
				tmp = tNode;
			}
			if(tmp == null)
				return iCurrentRow;
			tmp.getChildren().add(tmpNode);

			//追加到第curRowLevel节点
			(map.get(curRowLevel-2)).add(tmpNode);
			
			return appendNextNode(map,(curRowLevel-1), pNode, tmpNode, sheet, (iCurrentRow+1), col-1);
		}
		return 0;
	} 
	
	private void saveCostMateTree(TreePanelNode node){
		if(node == null)
			return;
		List<TreePanelNode> childList = node.getChildren();
		if(childList == null)
			return;
		CostMateModule tModule = null;
		for (TreePanelNode tNode : childList) {
			tModule = new CostMateModule();
			tModule.setModuleName(tNode.getText());
			tModule.setEnableFlg(new Long(1));
			tModule.setParentId(StringUtils.isNotBlank(node.getId())?node.getId():"0");//若空,默认挂在根底下
			costMateModuleManager.saveCostMateModule(tModule);
			//回填ID
			tNode.setId(tModule.getCostMateModuleId());
			//递推算法
			saveCostMateTree(tNode);
		}
	}
	
	//获取单元格的值
	public String getCellValue(Cell cell){
		if(cell == null) 
			return "";
		if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
			return cell.getStringCellValue();
		else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN)
			return String.valueOf(cell.getBooleanCellValue());
		else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA)
			return cell.getCellFormula();
		else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
			return String.valueOf(cell.getNumericCellValue());
		return "";
	}

	
	
	
	/**
	 * 带checkbox的设备类型树
	 * @return
	 * @throws Exception
	 */
	public String getCostMateModuleTreeCheck() throws Exception{
		this.getCostMateModuleTree(TreePanelUtil.NODE_CHECKED_NONE);
		return null;
	}
	
	/**
	 * 不带checkbox的设备类型树(弹出框用)
	 * @return
	 * @throws Exception
	 */
	public String getCostMateModuleTreeNoCheck() throws Exception{
		this.getCostMateModuleTree(TreePanelUtil.NODE_CHECKED_UNDEFINED);
		return null;
	}
	
	/**
	 * 得到设备类型树
	 * @param nodeCheckType
	 * @return
	 * @throws Exception
	 */
	public String getCostMateModuleTree(String nodeCheckType) throws Exception{
		TreePanelNode node = new TreePanelNode();
		node.setChecked(nodeCheckType);
		node.setId("0");
		node.setNodeType("0");
		node.setText("战略材料设备库");
		node.setChildren(costMateModuleManager.getChildrenNode(node, true,nodeCheckType));
		Struts2Utils.renderJson(node);
		return null;
	}
	
	/**
	 * 得到大类Map
	 * @return
	 */
	public Map<String, String> getMapCostMateModule() {
		Map<String, String> mapCostMateModule = new LinkedHashMap<String, String>();
		mapCostMateModule.put("", "--请选择--");
		List<CostMateModule> mateModuleList = costMateModuleManager.getAll();
		for (CostMateModule t : mateModuleList) {
			mapCostMateModule.put(t.getCostMateModuleId(), t.getModuleName());
		}
		if (StringUtils.isNotBlank(getId())) {
			CostMateModule module = costMateModuleManager.getEntity(getId());
			if (module != null) {
				//移除包含自己在内的元素
				mapCostMateModule.remove(module.getCostMateModuleId());
				this.removeChilren(module.getCostMateModuleId(), mapCostMateModule);
			}
		}
		return mapCostMateModule;
	}
	
	public Map<String, String> removeChilren(String costMateModuleId,Map<String, String> mapCostMateModule){
		List<CostMateModule> moduleList = costMateModuleManager.getCostMateModuleByParentId(costMateModuleId);
		if (moduleList != null && moduleList.size() >0) {
			for (CostMateModule tMoudle : moduleList) {
				//移除子节点(子类)
				mapCostMateModule.remove(tMoudle.getCostMateModuleId());
				removeChilren(tMoudle.getCostMateModuleId(), mapCostMateModule);
			}
		}
		return mapCostMateModule;
	}
	
	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		
		return "input";
	}
	@Override
	public CostMateModule getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = costMateModuleManager.getEntity(getId());
		} else {
			entity = new CostMateModule();
		}
	}

	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public CostMateModule getEntity() {
		return entity;
	}
	public void setEntity(CostMateModule entity) {
		this.entity = entity;
	}

	public List<CostMateModule> getCostMateModuleList() {
		return costMateModuleList;
	}

	public void setCostMateModuleList(List<CostMateModule> costMateModuleList) {
		this.costMateModuleList = costMateModuleList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getEnableFlg() {
		return enableFlg;
	}

	public void setEnableFlg(String enableFlg) {
		this.enableFlg = enableFlg;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}

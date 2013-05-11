package com.hhz.uums.web.plas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.jxls.util.Util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.uums.dao.plas.PlasDimeOrgRelManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.vw.VoOrg;
import com.hhz.uums.vo.vw.VoUser;
import com.hhz.uums.web.CrudActionSupport;

/**
 * <p>
 * 通讯类导出类
 * </p>
 * <p>
 * 继承PlasOrgAction组织类
 * </p>
 * 
 * @author hy
 * @version 1.00 2011-9-16
 */
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "plas-address-book-export.action", type = "redirect"),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
				"is", "contentDisposition", "attachment;filename=${downFileName}.xls" }) })
public class PlasAddressBookExportAction extends PlasOrgAction {

	private static Log log = LogFactory.getLog(PlasAddressBookExportAction.class);

	private static final long serialVersionUID = -3396901116980707378L;

	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasSysPositionManager plasSysManager;
	@Autowired
	private PlasDimeOrgRelManager plasDimeOrgRelManager;
	

	// excel 输出流
	private InputStream is;

	// 记录当前所在行，初始化为第一行
	private int currRow = 1;

	// 用来标记组织机构下是否有数据存在
	private boolean isTopFlag = false;

	// 导出文件名
	private String downFileName;
	
	// 建立新HSSFWorkbook对象
	private HSSFWorkbook wb = new HSSFWorkbook();
	private HSSFSheet sheet = wb.createSheet("通讯录");
	
	// 设置字体
	private HSSFFont font = wb.createFont(); //内容
	private HSSFFont fontTopTitle = wb.createFont(); //顶级组织
	private HSSFFont fontTitle = wb.createFont();	 //标题
	
	//创建全局样式以减少对象生成
	private HSSFCellStyle normal = wb.createCellStyle(); 		//内容样式
	private HSSFCellStyle setBorder = wb.createCellStyle(); 	//边框样式
	private HSSFCellStyle titleStyle = wb.createCellStyle();	//标题样式 
	private HSSFCellStyle titleTopStyle = wb.createCellStyle();	//顶级组织样式
	private HSSFCellStyle centerStyle = wb.createCellStyle();   //中心样式\
	
	//是否导出邮箱
	private String enableEmailFlg;
	
	

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	/**
	 * ajax 加载特定类型的机构树 带复选框
	 * 
	 * @return
	 */
	@Override
	public String loadOrgTree() {
		String tmpTypeCd = Struts2Utils.getParameter("dimeTypeCd").trim();
		if (StringUtils.isBlank(tmpTypeCd))
			return null;
		else {
			if (DictContants.TREE_DIME_PHYSICAL.equals(tmpTypeCd)) {
				loadOrgTreePhysical();
			} else if (DictContants.TREE_DIME_LOGICAL.equals(tmpTypeCd)) {
				loadOrgTreeLogical();
			}
		}
		return null;
	}

	/**
	 * 物理机构树
	 */
	@Override
	public void loadOrgTreePhysical() {
		// Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanelOrgPhysical(SpringSecurityUtils.getCurUiid()));
		Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanel(TreePanelUtil2.TREE_TYPE_PHYSICAL, SpringSecurityUtils
				.getCurUiid(), false, true, null, false, false));
	}

	/**
	 * 导出选中组织下所有人员通讯录
	 */
	public String exportAddressBook() {
		
		// 选定组织机构id集
		String tmpOrgIds = Struts2Utils.getParameter("orgids");
		
		// 保存宝龙集团直接下属组织机构 例(宝龙地产、宝龙商业、宝龙行业、事业管理中心、宝龙华康)
		List<PlasOrg> listPlasOrgTop = new ArrayList<PlasOrg>();
		if (StringUtils.isNotBlank(tmpOrgIds)) {
			
			TreePanelNode rootNode = new TreePanelNode();
			rootNode.setId(TreePanelUtil2.DEFAULT_ROOT_ORG_ID);
			rootNode.setText(TreePanelUtil2.DEFAULT_ROOT_ORG_NAME);
			rootNode.setOrgOrUser(TreePanelUtil2.NODE_TYPE_APP);
			rootNode.setNodeType(TreePanelUtil2.NODE_TYPE_APP);
			rootNode.setEntityId(TreePanelUtil2.DEFAULT_ROOT_ORG_ID);
			rootNode.setEntityName(TreePanelUtil2.DEFAULT_ROOT_ORG_NAME);
			
			// 获取"宝龙集团"下属组织机构
			List<String> topOrgIdList = new ArrayList<String>();
			topOrgIdList.add(TreePanelUtil2.DEFAULT_ROOT_ORG_ID);
			listPlasOrgTop = plasOrgManager.getDirectOrgsOrder(topOrgIdList, TreePanelUtil2.TREE_TYPE_PHYSICAL, false);

			// 获取已选中的"机构"列表
			List<VoOrg> voOrgList = plasDimeOrgRelManager.getVoOrgList(TreePanelUtil2.TREE_TYPE_PHYSICAL, false);
					
			// 获取已选中"机构"对应的"系统职位"列表
			List<VoUser> voUserList = plasSysManager.searchUsingPosUserList(tmpOrgIds);

			
			//初始化字体
			fontTopTitle();
			fontTitle();
			font();
			//初始化样式
			titleStyle();
			titleTopStyle();
			normal();
			setBorder();
			centerStyle();
			
			for (PlasOrg plasOrg : listPlasOrgTop) {
				rootNode.setId(plasOrg.getPlasOrgId());
				rootNode.setText(plasOrg.getOrgName());
				rootNode.setOrgOrUser(TreePanelUtil2.NODE_TYPE_APP);
				rootNode.setNodeType(TreePanelUtil2.NODE_TYPE_APP);
				rootNode.setEntityId(plasOrg.getPlasOrgId());
				rootNode.setEntityName(plasOrg.getOrgName());
//				generateSheetLayout(wb, sheet, rootNode, orgList, voUserList);
				generateSheetLayout(wb, sheet, rootNode, voOrgList, voUserList);
			}

			// 合并单元格
			mergedRegion(wb, sheet, normal);

			// 设置单元格宽度
			sheet.setDefaultColumnWidth(15);
			// sheet.autoSizeColumn((short)0); //自动调整第一列宽度
			sheet.setColumnWidth((short) 0, 512 * 10); // 调整第一列宽度
			sheet.setColumnWidth((short) 1, 512 * 10); // 调整第二列宽度
			sheet.setColumnWidth((short) 2, 512 * 15); // 调整第三列宽度
			sheet.setColumnWidth((short) 3, 512 * 10); // 调整第四列宽度
			sheet.setColumnWidth((short) 4, 512 * 10); // 调整第五列宽度
			sheet.setColumnWidth((short) 5, 512 * 10); // 调整第六列宽度
			sheet.setColumnWidth((short) 6, 512 * 20); // 调整第七列宽度

			// 输出文件
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				wb.write(bos);
				byte[] data = bos.toByteArray();
				is = new ByteArrayInputStream(data);
				String fileName = "通讯录" + DateOperator.formatDate(new Date(), "yyyyMMddHHmm");
				HttpServletRequest request = Struts2Utils.getRequest();
				String agentStr = request.getHeader("User-Agent");
				if (agentStr == null) {
					agentStr = "MSIE";
				}
				logger.info("enter file download....agent=" + agentStr);

				agentStr = agentStr.toUpperCase();
				if (agentStr != null && agentStr.indexOf("MSIE") != -1) {
					downFileName = new String(java.net.URLEncoder.encode(fileName, "UTF-8"));
				} else {
					downFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.debug("通讯录导出失败", e.getCause());
			} finally {
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						log.debug("关闭失败", e.getCause());
					}
				}
			}
		}
		return "export";

	}

	/**
	 * <p>
	 * 创建HSSFSheet工作簿
	 * </p>
	 * 
	 * @param wb
	 *            HSSFSeet
	 * @param sheetName
	 *            String
	 * @return HSSFSheet
	 */
	public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(12);
		sheet.setGridsPrinted(false);
		sheet.setDisplayGridlines(false);
		return sheet;
	}

	/**
	 * 将PlasUser集合转换为VoUser集合
	 * 
	 * @param plasUserList
	 * @return List<VoUser>
	 */
	public List<VoUser> transVoUserList(List<PlasUser> plasUserList) {
		List<VoUser> rtnList = new ArrayList<VoUser>();
		for (PlasUser plasUser : plasUserList) {
			VoUser voUser = new VoUser();
			voUser.setParentOrgId(plasUser.getPlasOrg().getPlasOrgId());
			voUser.setSequenceNo(plasUser.getSequenceNo());
			voUser.setServiceCd(plasUser.getServiceStatusCd());
			voUser.setSexCd(plasUser.getSexCd());
			voUser.setUiid(plasUser.getUiid());
			voUser.setUserBizCd(plasUser.getUserBizCd());
			voUser.setUserCd(plasUser.getUserCd());
			voUser.setUserId(plasUser.getPlasUserId());
			voUser.setUserName(plasUser.getUserName());
			voUser.setWorkDutyDesc(plasUser.getWorkDutyDesc());
			voUser.setPhone(plasUser.getFixedPhone());
			voUser.setMobilePhone(plasUser.getMobilePhone());
			voUser.setMobilePhone2(plasUser.getMobilePhone2());
			rtnList.add(voUser);
		}

		return rtnList;
	}

	/**
	 * 遍历并生成Excel格式
	 * 
	 * @param treePanelNodeList
	 *            //组织节点集合
	 * @param centerName
	 *            //中心名
	 * @param deptName
	 *            //部门名
	 * @param sheet
	 *            //表格
	 * @param style
	 *            //样式
	 * @return
	 */
	public boolean outNodeList(HSSFWorkbook wb, List<TreePanelNode> treePanelNodeList, String centerName,
			String deptName, HSSFSheet sheet, HSSFCellStyle style) {
		boolean rtnFlag = false;
		if (treePanelNodeList == null)
			return rtnFlag;

		

		// 开始遍历
		for (TreePanelNode treeNode : treePanelNodeList) {
			// 用户
			if ("0".equals(treeNode.getOrgOrUser())) {
				isTopFlag = true;
				// 如果是用户则增加新一行
				int nRow = (sheet != null ? sheet.getLastRowNum() + 1 : 1);
				// 创建新一行
				HSSFRow crow = sheet.createRow(nRow);
				crow.setHeightInPoints(25);
				// 中心
				HSSFCell cellValue0 = crow.createCell(0);
				cellValue0.setCellValue(centerName); 
				cellValue0.setCellStyle(centerStyle);
				// 部门
				HSSFCell cellValue1 = crow.createCell(1);
				cellValue1.setCellValue(deptName); 
				cellValue1.setCellStyle(style);
				// 职位
				HSSFCell cellValue5 = crow.createCell(2); 
				cellValue5.setCellStyle(style);
				// 姓名
				HSSFCell cellValue2 = crow.createCell(3);
				cellValue2.setCellValue(treeNode.getEntityName()); 
				cellValue2.setCellStyle(style);
				// 固定电话
				HSSFCell cellValue3 = crow.createCell(4); 
				cellValue3.setCellStyle(style);
				// 手机
				HSSFCell cellValue4 = crow.createCell(5); 
				cellValue4.setCellStyle(style);
				
				// 邮箱地址
				if(StringUtils.isNotBlank(enableEmailFlg)){
					HSSFCell cellValue6 = crow.createCell(6); 
					cellValue6.setCellValue(treeNode.getExtParam()+"@powerlong.com"); 
					cellValue6.setCellStyle(style);
				}
				

				// 从extParam获取
				String extParam = treeNode.getMobile();
				if (extParam != null) {
					String[] strArrays = StringUtils.split(extParam, "|||");
					cellValue3.setCellValue(strArrays.length > 0 ? strArrays[0].replaceAll("null", "") : "");
					cellValue4.setCellValue(strArrays.length > 1 ? strArrays[1].replaceAll("null", "") : "");
					cellValue5.setCellValue(strArrays.length > 2 ? strArrays[2].replaceAll("null", "") : "");
				}
				currRow++;

			}
			// 如果是部门则不新增
			else if ("1".equals(treeNode.getOrgOrUser())) {
				try {
					// 获取组织id并判断是否是中心
					PlasOrg plasOrg = plasOrgManager.getLatestSuperOrg(DictContants.PLAS_ORG_TYPE_CENTER, treeNode
							.getEntityId(), TreePanelUtil2.TREE_TYPE_PHYSICAL);
					// 判断是还否是否是中心
					if (plasOrg != null && DictContants.PLAS_ORG_TYPE_CENTER.equals(plasOrg.getOrgTypeCd())) {
						centerName = plasOrg.getOrgName();
					}
				} catch (Exception e) {
					log.debug("该" + treeNode.getEntityName() + "没有获取到上级组织中心 ", e.fillInStackTrace());
				}
				deptName = treeNode.getEntityName();
			}

			List<TreePanelNode> tmpNodeList = treeNode.getChildren();
			if (tmpNodeList != null && tmpNodeList.size() > 0) {
				// 递归
				outNodeList(wb, tmpNodeList, centerName, deptName, sheet, style);
			}
		}
		return false;

	}

	/**
	 * <p>
	 * 合并单元格
	 * </p>
	 * 
	 * @param sheet
	 * @param style
	 */
	public void mergedRegion(HSSFWorkbook wb, HSSFSheet sheet, HSSFCellStyle style) {
		// 合并单元格
		int nRowFrom = 0; // 开始行
		int nRowTo = 0; // 结束行
		int nColumnFrom = 0; // 开始列
		int nColumnTo = 0; // 结束列 现默认第一列
		String strOldValue = "";
		String strNewValue = "";
		int rowTotal = sheet.getLastRowNum();
		// 循环全并合同单元格（第一行除外）
		for (int i = 1; i <= rowTotal; i++) {
			HSSFRow rowTmp = sheet.getRow(i);
			// 初始化
			if (i == 1 && rowTmp != null) {
				strOldValue = rowTmp.getCell(0).getStringCellValue();
			} else if (rowTmp != null) {
				strNewValue = rowTmp.getCell(0).getStringCellValue();
			}
			// 最后一行特殊处理，并且值必须相同
			if (nRowTo == (rowTotal - 1) && (strOldValue.equals(strNewValue))) {
				CellRangeAddress region = new CellRangeAddress(nRowFrom, nRowTo + 1, nColumnFrom, nColumnTo);
				sheet.addMergedRegion(region);
			}
			if (strOldValue.equals(strNewValue) && !"".equals(strOldValue)) {
				nRowTo = i;
			}
			// 如果前后值不同，则开始合并
			else {
				CellRangeAddress region = new CellRangeAddress(nRowFrom, nRowTo, nColumnFrom, nColumnTo);
				sheet.addMergedRegion(region);
				// 合并结束后起始行重新定位到结束行
				nRowFrom = i;
				strOldValue = rowTmp != null && rowTmp.getLastCellNum() > 0 ? rowTmp.getCell(0).getStringCellValue()
						: "";
				strNewValue = rowTmp != null && rowTmp.getLastCellNum() > 0 ? rowTmp.getCell(0).getStringCellValue()
						: "";
				nRowTo = i;
			}
		}
	}

	/**
	 * <p>
	 * 通过rootNode遍历成树
	 * </p>
	 * 
	 * @param wb
	 *            //poi HSSFWorkbook
	 * @param sheet
	 *            //poi HSSFSheet
	 * @param rootNode
	 *            //TreePanelNode
	 * @param voOrgList
	 *            //List<VoOrg>
	 * @param voUserList
	 *            //List<voUser>
	 */
	public void generateSheetLayout(HSSFWorkbook wb, HSSFSheet sheet, TreePanelNode rootNode, List<VoOrg> voOrgList, List<VoUser> voUserList) {
		
		// 定义是否生成顶级组织行
		isTopFlag = false;
		
		// 遍历成树
		TreePanelNode treePanelNode = TreePanelUtil2.buildOrgUserTree(TreePanelUtil2.TREE_TYPE_PHYSICAL, rootNode,
				voOrgList, voUserList, TreePanelUtil2.NODE_CHECKED_UNDEFINED);
		
//		TreePanelUtil2.buildOrgUserTree(TreePanelUtil2.TREE_TYPE_PHYSICAL, rootNode, voOrgList, voUserList, TreePanelUtil2.NODE_CHECKED_UNDEFINED);

		if (treePanelNode.getChildren() != null && treePanelNode.getChildren().size() > 0) {

			// 暂存插入行
			int nRow = (sheet != null ? sheet.getLastRowNum() + 1 : 0);

			String deptName = "宝龙集团";
			String centerName = "宝龙集团";
			// 遍历组织机构
			if (treePanelNode != null) {
				// 0-用户
				if ("0".equals(treePanelNode.getOrgOrUser())) {
					// 创建新一行
					HSSFRow crow = sheet.createRow(currRow);
					crow.setHeightInPoints(25);
					HSSFCell cellValue0 = crow.createCell(0);
					cellValue0.setCellValue(deptName);
					HSSFCell cellValue1 = crow.createCell(1);
					cellValue1.setCellValue(deptName);
					HSSFCell cellValue5 = crow.createCell(5);
					cellValue5.setCellValue(treePanelNode.getEntityName());
					isTopFlag = true;
				} 
				// 1-机构
				else if ("1".equals(treePanelNode.getOrgOrUser())) {
					deptName = treePanelNode.getEntityName();
				}
				outNodeList(wb, treePanelNode.getChildren(), centerName, treePanelNode.getEntityName(), sheet, normal);
				if (isTopFlag) {
					// 插入一行顶级组织名称
					HSSFRow topRow = insertRow(wb, sheet, nRow);
					if (topRow != null) {
						// 生成顶级组织
						topRow.setHeightInPoints(30);
						HSSFCell centerDept0 = topRow.createCell(0);
						centerDept0.setCellValue(treePanelNode.getEntityName());
						centerDept0.setCellStyle(titleTopStyle);
						HSSFCell centerDept1 = topRow.createCell(1);
						centerDept1.setCellStyle(titleTopStyle);
						HSSFCell centerDept2 = topRow.createCell(2);
						centerDept2.setCellStyle(titleTopStyle);
						HSSFCell centerDept3 = topRow.createCell(3);
						centerDept3.setCellStyle(titleTopStyle);
						HSSFCell centerDept4 = topRow.createCell(4);
						centerDept4.setCellStyle(titleTopStyle);
						HSSFCell centerDept5 = topRow.createCell(5);
						centerDept5.setCellStyle(titleTopStyle);
						

						CellRangeAddress region = null;
						
						if(StringUtils.isNotBlank(enableEmailFlg)){
							HSSFCell centerDept6 = topRow.createCell(6);
							centerDept6.setCellStyle(titleTopStyle);
							region = new CellRangeAddress(nRow, nRow, 0, 6);
						}else{
							region = new CellRangeAddress(nRow, nRow, 0, 5);
						}
						sheet.addMergedRegion(region);
						nRow++;
					}

					// 插入一新行
					HSSFRow row = insertRow(wb, sheet, nRow);
					if (row != null) {
						row.setHeightInPoints(30);
						HSSFCell centerDept = row.createCell(0);
						centerDept.setCellValue("中心");
						centerDept.setCellStyle(titleStyle);
						HSSFCell dept = row.createCell(1);
						dept.setCellValue("部门");
						dept.setCellStyle(titleStyle);
						HSSFCell job = row.createCell(2);
						job.setCellValue("职位");
						job.setCellStyle(titleStyle);
						HSSFCell name = row.createCell(3);
						name.setCellValue("姓名");
						name.setCellStyle(titleStyle);
						HSSFCell phone = row.createCell(4);
						phone.setCellValue("分机号");
						phone.setCellStyle(titleStyle);
						HSSFCell mobilePhone = row.createCell(5);
						mobilePhone.setCellValue("手机号");
						mobilePhone.setCellStyle(titleStyle);
						// 邮箱地址
						if(StringUtils.isNotBlank(enableEmailFlg)){
							HSSFCell cellEmail = row.createCell(6);
							cellEmail.setCellValue("邮箱地址");
							cellEmail.setCellStyle(titleStyle);
						}
					}

				}

			}

		}
	}


	/**
	 * 插入一行
	 * 
	 * @param wb
	 * @param sheet
	 * @param starRow
	 */
	public HSSFRow insertRow(HSSFWorkbook wb, HSSFSheet sheet, int startRow) {

		sheet.shiftRows(startRow + 1, sheet.getLastRowNum(), 1, true, false);
		HSSFRow sourceRow = null;
		HSSFRow targetRow = null;
		sourceRow = sheet.getRow(startRow);
		targetRow = sheet.createRow(++startRow);
		if (sourceRow != null) {
			Util.copyRow(sheet, sourceRow, targetRow);
			return sourceRow;

		}
		return null;
	}

	/**
	 * <p>
	 * 创建顶级单元格样式
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @param fontTitle
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle titleTopStyle() {
		titleTopStyle.setFont(fontTopTitle);
		titleTopStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 水平居中
		titleTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		titleTopStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleTopStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleTopStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleTopStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleTopStyle.setWrapText(true);
		/*
		 * titleTopStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		 * titleTopStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
		 * titleTopStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		 */
		return titleTopStyle;
	}

	/**
	 * <p>
	 * 标题样式
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @param fontTitle
	 * @return
	 */
	public HSSFCellStyle titleStyle() {
		titleStyle.setFont(fontTitle);
		titleStyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER); // 水平居中
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setWrapText(true);
		titleStyle.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		titleStyle.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return titleStyle;
	}

	/**
	 * <p>
	 * 内容样式
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @param fontTitle
	 * @return
	 */
	public HSSFCellStyle normal() {
		normal.setFont(font);
		normal.setAlignment(HSSFCellStyle.VERTICAL_CENTER); // 水平居中
		normal.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		normal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		normal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		normal.setBorderRight(HSSFCellStyle.BORDER_THIN);
		normal.setBorderTop(HSSFCellStyle.BORDER_THIN);
		normal.setWrapText(true);
		return normal;
	}

	/**
	 * <p>
	 * 边框样式
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @param fontTitle
	 * @return
	 */
	public HSSFCellStyle setBorder() {
		setBorder.setAlignment(HSSFCellStyle.VERTICAL_CENTER); // 水平居中
		setBorder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		setBorder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		setBorder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		setBorder.setBorderRight(HSSFCellStyle.BORDER_THIN);
		setBorder.setBorderTop(HSSFCellStyle.BORDER_THIN);
		setBorder.setWrapText(true);
		return setBorder;
	}
	
	/**
	 * <p>
	 * 边框样式
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @param fontTitle
	 * @return
	 */
	public HSSFCellStyle centerStyle() {
		centerStyle.setFont(fontTitle);
		centerStyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER); // 水平居中
		centerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直居中
		centerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		centerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		centerStyle.setWrapText(true);
		return centerStyle;
	}
	
	

	/**
	 * <p>
	 * 内容字体样式
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @return HSSFFont
	 */
	public HSSFFont font() {
		font.setColor(HSSFFont.COLOR_NORMAL);
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);
		return font;
	}

	/**
	 * <p>
	 * 顶级组织字体样式
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @return HSSFFont
	 */
	public HSSFFont fontTopTitle() {
		fontTopTitle.setColor(HSSFFont.COLOR_NORMAL);
		fontTopTitle.setFontName("宋体");
		fontTopTitle.setFontHeightInPoints((short) 16);
		fontTopTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		return fontTopTitle;
	}

	/**
	 * <p>
	 * 标题字体样式
	 * </p>
	 * 
	 * @param wb
	 * @param sheet
	 * @return HSSFFont
	 */
	public HSSFFont fontTitle() {
		fontTitle.setColor(HSSFFont.COLOR_NORMAL);
		fontTitle.setFontName("宋体");
		fontTitle.setFontHeightInPoints((short) 12);
		fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		return fontTitle;
	}

	public String getEnableEmailFlg() {
		return enableEmailFlg;
	}

	public void setEnableEmailFlg(String enableEmailFlg) {
		this.enableEmailFlg = enableEmailFlg;
	}

}

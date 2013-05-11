package com.hhz.ump.web.assm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.assm.AssmAccountManager;
import com.hhz.ump.dao.assm.AssmModelManager;
import com.hhz.ump.dao.assm.AssmModelStandardManager;
import com.hhz.ump.dao.assm.AssmModelStandardVo;
import com.hhz.ump.dao.assm.ResApproveInfoVo;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.assm.AssmAccount;
import com.hhz.ump.entity.assm.AssmModel;
import com.hhz.ump.entity.assm.AssmModelStandard;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Namespace("/assm")
@Results( {@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "excelFile", "contentDisposition",
				"attachment;filename=${downFileName}.xls" }) 
})
public class AssmAccountAction extends CrudActionSupport<AssmAccount> {

	private static final long serialVersionUID = -8818821430740848524L;
	@Autowired
	protected AssmModelManager assmModelManager;
	@Autowired
	protected AssmAccountManager assmAccountManager;
	@Autowired
	protected AssmModelStandardManager assmModelStandardManager;
	@Autowired
	protected BisProjectManager bisProjectManager;

	private Page<AssmAccount> page = new Page<AssmAccount>(10);
	private Page<BisProject> bisPage = new Page<BisProject>(20);
	private Map<String,String> mapAssmModel = new LinkedHashMap<String, String>();
	private Map<String,String> mapNextAssmModel = new LinkedHashMap<String, String>();
	private List<ResApproveInfoVo> res;
	private AssmAccount entity;
	private String id;
	private String assmModelStandardId;
	
	private String deptName;//商业公司名称
	private String computerModelName;//电脑类名称
	private String stanNum; //标准配置
	private String currNum; //当前配置

	//导出Excel文件参数
	private InputStream excelFile;	
	private String downFileName;

	@Override
	public String input() throws Exception {
		String isLook = Struts2Utils.getParameter("isLook");
		if (StringUtils.isNotBlank(isLook))
			return "detail"; //查看页面
		else
			return "input"; //编辑页面
	}

	@Override
	public String list() throws Exception {
		Map<String,Object> values=new HashMap<String,Object>();
		page = assmAccountManager.findPage(page, accountQueryHql(values), values);
		return "loadList";
	}

	/**
	 * 导出资产excel数据
	 * @return
	 * @throws Exception
	 */
	public String doExportAccount() throws Exception{
		String projectName = Struts2Utils.getParameter("s_projectName");
		excelFile = buildTemplateExcel(projectName);
		downFileName = new String(downFileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}
	
	

	public String accountQueryHql(Map<String,Object> values) throws Exception{
		//搜索条件
		String assmAccountId = Struts2Utils.getParameter("assmAccountId");
		String projectCd = Struts2Utils.getParameter("s_projectCd");
		String assmName = Struts2Utils.getParameter("s_account_assmName");
		String code = Struts2Utils.getParameter("s_code");
		String creator = Struts2Utils.getParameter("s_creator");
		String useDate = Struts2Utils.getParameter("s_useDate");
		String keeperCd = Struts2Utils.getParameter("s_keeperCd");
		String useDepartment = Struts2Utils.getParameter("s_useDepartment");
		String useStatus = Struts2Utils.getParameter("s_useStatus");
		String parentId = Struts2Utils.getParameter("parentId");
		
		StringBuffer hql = new StringBuffer();
		hql.append(" from AssmAccount t where 1=1 ");
		if (StringUtils.isNotBlank(assmAccountId)) {
			hql.append(" and t.assmAccountId = :assmAccountId");
			values.put("assmAccountId", assmAccountId);
		}

		boolean flag1 = false;
		boolean flag2 = false;
		if (SpringSecurityUtils.hasRole("A_ASSM_ACC_VIEW_ALL")) {
			flag1 = true;
		}
		if (SpringSecurityUtils.hasRole("A_ASSM_ACC_VIEW_PRO")) {
			flag2 = true;
		}
		if(!flag1){
			if(flag2){
				//项目公司只查对应该公司的资产
				hql.append(" and t.projectCd = :projectCd");
				prepareModel();
				values.put("projectCd", entity.getProjectCd());
			}
		}
		if(StringUtils.isNotBlank(parentId)){
			String[] assmModelIds = null;
			List<String> list = new ArrayList<String>();  
			AssmModel model = assmModelManager.getEntity(parentId);
			if(model != null){
				List<AssmModel> modelList = assmModelManager.getAssmModelByPratentId(parentId);
				if (model.getAssmLevel() == 2) {
					if (modelList != null && modelList.size() > 0) {
						List<AssmModel> thridList = null;
						for (AssmModel tmp : modelList) {
							thridList = assmModelManager.getAssmModelByPratentId(tmp.getAssmModelId());
							if (thridList != null && thridList.size() > 0) {
								for (AssmModel tmp2 : thridList) {
									list.add(tmp2.getAssmModelId());
								}
							}
						}
					}
				}
				if (model.getAssmLevel() == 3) {
					if (modelList != null && modelList.size() > 0) {
						for (AssmModel tmp : modelList) {
							list.add(tmp.getAssmModelId());
						}
					}
				}
				hql.append(" and t.assmModel.assmModelId in(:assmModelIds)");
				if (list.size()>0) {
					assmModelIds = list.toArray(new String[list.size()]); 
					values.put("assmModelIds", assmModelIds);
				}else{
					values.put("assmModelIds", "");
				}
			}
		}
		if(StringUtils.isNotBlank(projectCd)){
			hql.append(" and t.projectCd = :projectCd");
			values.put("projectCd", projectCd.trim());
		}
		if(StringUtils.isNotBlank(assmName)){
			hql.append(" and t.assmName like :assmName");
			values.put("assmName", "%"+assmName.trim()+"%");
		}
		if(StringUtils.isNotBlank(code)){
			hql.append(" and t.code like :code");
			values.put("code", "%"+code.trim()+"%");
		}
		if(StringUtils.isNotBlank(creator)){
			hql.append(" and t.creator = :creator");
			values.put("creator", creator.trim());
		}
		if(StringUtils.isNotBlank(useDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(useDate);
			hql.append(" and t.useDate = :useDate");
			values.put("useDate", date);
		}
		if(StringUtils.isNotBlank(keeperCd)){
			hql.append(" and t.keeperCd = :keeperCd");
			values.put("keeperCd", keeperCd.trim());
		}
		if(StringUtils.isNotBlank(useDepartment)){
			hql.append(" and t.useDepartment = :useDepartment");
			values.put("useDepartment", useDepartment.trim());
		}
		if(StringUtils.isNotBlank(useStatus)){
			hql.append(" and t.useStatus = :useStatus");
			values.put("useStatus", useStatus.trim());
		}
		hql.append(" order by t.code asc");
		return hql.toString();
	}

	
	/**
	 * 构建导出excel文件流
	 * @return
	 */
	private InputStream buildTemplateExcel(String projectName) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableWorkbook wbook = JXLExcelUtil.createWorkBook(bos);
		if (wbook != null) {
			try {
				initTemplateData(wbook,projectName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			JXLExcelUtil.closeWorkBook(wbook);
		}
		byte[] data = bos.toByteArray();
		try {
			bos.close();
		} catch (IOException e) {
			throw new RuntimeException("关闭输出流失败", e);
		}
		InputStream inputStream = new ByteArrayInputStream(data);
		return inputStream;
	}
	/**
	 * 制定模板样式等
	 * @param wbook
	 * @throws Exception
	 */
	private void initTemplateData(WritableWorkbook wbook,String projectName) throws Exception {
		//模板名称
		if(StringUtils.isNotBlank(projectName)){
			downFileName = projectName+"-资产台账";
		}else{
			projectName = "所有项目公司";
			downFileName = "资产台账";
		}
		//设置工作表名称
		WritableSheet wSheet = wbook.createSheet(projectName, -1);
		//冻结第几行第几列
		wSheet.getSettings().setVerticalFreeze(3); //冻结行
		initAccountExcelSheet(wSheet,projectName);
	}
	
	public void initAccountExcelSheet(WritableSheet wsheet,String projectName) throws Exception {
		WritableFont font_Bold_10 = new WritableFont(WritableFont.createFont("Times New Roman"), 10);
		font_Bold_10.setBoldStyle(WritableFont.BOLD);
		WritableFont font_Bold_14 = new WritableFont(WritableFont.createFont("Times New Roman"), 14);
		font_Bold_14.setBoldStyle(WritableFont.BOLD);
		
		// 一级标题样式
		WritableCellFormat format_head1 = new WritableCellFormat(font_Bold_14);
		format_head1.setAlignment(Alignment.CENTRE); // 水平对齐
		format_head1.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		format_head1.setBorder(Border.ALL, BorderLineStyle.THIN);
		
		// 二级标题样式
		WritableCellFormat format_head2 = new WritableCellFormat(font_Bold_10);
		format_head2.setAlignment(Alignment.CENTRE);
		format_head2.setBackground(Colour.YELLOW);
		format_head2.setVerticalAlignment(VerticalAlignment.CENTRE);
		format_head2.setBorder(Border.ALL, BorderLineStyle.THIN);//边框为实体线
		format_head2.setWrap(true);//自动换行
		
		// 普通框样式
		WritableCellFormat format_label = new WritableCellFormat(NumberFormats.TEXT);
		format_label.setAlignment(Alignment.CENTRE);
		format_label.setBorder(Border.ALL, BorderLineStyle.THIN);
		format_label.setWrap(true); 
		
		String firstColStr = null;
		if(StringUtils.isNotBlank(projectName)){
			firstColStr = projectName+"-资产台账";
		}else{
			firstColStr = "资产台账";
		}
		//添加第一行数据
		wsheet.addCell(new Label(0, 0, firstColStr, format_head1));
		//添加第二行列头数据
		wsheet.addCell(new Label(0, 1, "序号", format_head2));
		wsheet.addCell(new Label(1, 1, "商业公司/总部", format_head2));
		wsheet.addCell(new Label(2, 1, "设备型号", format_head2));
		wsheet.addCell(new Label(3, 1, "型号编码", format_head2));
		wsheet.addCell(new Label(4, 1, "资产名称", format_head2));
		wsheet.addCell(new Label(5, 1, "资产编码", format_head2));
		wsheet.addCell(new Label(6, 1, "使用部门", format_head2));
		wsheet.addCell(new Label(7, 1, "保管人员(PD)", format_head2));
		wsheet.addCell(new Label(8, 1, "保管人员(非PD)", format_head2));
		wsheet.addCell(new Label(9, 1, "使用情况", format_head2));
		wsheet.addCell(new Label(10, 1, "购入时间", format_head2));
		wsheet.addCell(new Label(11, 1, "存放地点", format_head2));
		wsheet.addCell(new Label(12, 1, "增加方式", format_head2));
		wsheet.addCell(new Label(13, 1, "原值(元)", format_head2));
		wsheet.addCell(new Label(14, 1, "净值(元)", format_head2));
		wsheet.addCell(new Label(15, 1, "残值(元)", format_head2));
		wsheet.addCell(new Label(16, 1, "折旧年限", format_head2));
		wsheet.addCell(new Label(17, 1, "折旧方式", format_head2));
		wsheet.addCell(new Label(18, 1, "当前配置", format_head2));
		wsheet.addCell(new Label(19, 1, "标准配置", format_head2));

		// 每一列宽度
		wsheet.setColumnView(0, 8);
		wsheet.setColumnView(1, 20);
		wsheet.setColumnView(2, 25);
		wsheet.setColumnView(3, 20);
		wsheet.setColumnView(4, 15);
		wsheet.setColumnView(5, 15);
		wsheet.setColumnView(6, 15);
		wsheet.setColumnView(7, 15);
		wsheet.setColumnView(8, 15);
		wsheet.setColumnView(9, 15);
		wsheet.setColumnView(10, 15);
		wsheet.setColumnView(11, 15);
		wsheet.setColumnView(12, 15);
		wsheet.setColumnView(13, 15);
		wsheet.setColumnView(14, 15);
		wsheet.setColumnView(15, 15);
		wsheet.setColumnView(16, 15);
		wsheet.setColumnView(17, 15);
		wsheet.setColumnView(18, 15);
		wsheet.setColumnView(19, 15);

		//设置行高
		wsheet.setRowView(0, 700);
		wsheet.setRowView(1, 600);
		wsheet.setRowView(2, 1);
		//设置第一行合并列
		wsheet.mergeCells(0, 0, 19, 0);
		
		// 查询资产数据
		Map<String,Object> values=new HashMap<String,Object>();
		List<AssmAccount> accountList = assmAccountManager.find(accountQueryHql(values), values);
		int row = 3;
		for (int i = 0; i < accountList.size(); i++) {
			AssmAccount assmAccount = accountList.get(i);
			if(assmAccount != null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//查询当前配置及标准配置
				String value = assmAccountManager.getValues(assmAccount.getAssmModel().getAssmModelId(), assmAccount.getProjectCd());
				String[] staAndHasNum = value.split(",");
				//项目公司
				BisProject bisProject = bisProjectManager.getBisProjectByOrgCd(assmAccount.getProjectCd());
				String pName = null;
				if(bisProject!=null){
					pName = bisProject.getProjectName();
				}else{
					pName = "";
				}
				wsheet.addCell(new Label(0, row, (i+1)+"", format_label)); //序号
				wsheet.addCell(new Label(1, row, pName, format_label)); 
				wsheet.addCell(new Label(2, row, assmAccount.getAssmModel().getAssmName(), format_label)); 
				wsheet.addCell(new Label(3, row, assmAccount.getAssmModel().getAssmCode(), format_label));
				wsheet.addCell(new Label(4, row, assmAccount.getAssmName(), format_label));
				wsheet.addCell(new Label(5, row, assmAccount.getCode(), format_label));
				wsheet.addCell(new Label(6, row, CodeNameUtil.getDeptNameByCd(assmAccount.getUseDepartment()), format_label));
				wsheet.addCell(new Label(7, row, assmAccount.getKeeperName(), format_label));
				wsheet.addCell(new Label(8, row, assmAccount.getKeeperName2(), format_label));
				wsheet.addCell(new Label(9, row, this.getUseStatus(assmAccount.getUseStatus()), format_label));
				wsheet.addCell(new Label(10, row, sdf.format(assmAccount.getUseDate()), format_label));
				wsheet.addCell(new Label(11, row, this.getStorageSites(assmAccount.getStorageSites()), format_label));
				wsheet.addCell(new Label(12, row, this.getAddWays(assmAccount.getAddWay()), format_label));
				if(assmAccount.getSrcValue() == null){
					wsheet.addCell(new Label(13, row, "", format_label));
				}else{
					wsheet.addCell(new Label(13, row, assmAccount.getSrcValue()+"", format_label));
				}
				if(assmAccount.getRemainVal() == null){
					wsheet.addCell(new Label(14, row, "", format_label));
				}else{
					wsheet.addCell(new Label(14, row, assmAccount.getRemainVal()+"", format_label));
				}
				if(assmAccount.getNetValue() == null){
					wsheet.addCell(new Label(15, row, "", format_label));
				}else{
					wsheet.addCell(new Label(15, row, assmAccount.getNetValue()+"", format_label));
				}
				if(assmAccount.getDepreYeadNum() == null){
					wsheet.addCell(new Label(16, row, "", format_label));
				}else{
					wsheet.addCell(new Label(16, row, assmAccount.getDepreYeadNum()+"", format_label));
				}
				wsheet.addCell(new Label(17, row, assmAccount.getDepreWay(), format_label));
				wsheet.addCell(new Label(18, row, staAndHasNum[3], format_label));
				wsheet.addCell(new Label(19, row, staAndHasNum[2], format_label));
				wsheet.setRowView(row++, 600);
			}
		}
	}
	
	@Override
	public String save() throws Exception {
		String assmModelId2 = Struts2Utils.getParameter("assmModelId2");
		try {
			if (StringUtils.isNotBlank(assmModelId2)) {
				AssmModel assmModel = assmModelManager.getEntity(assmModelId2);
				if (assmModel != null) {
					entity.setAssmModel(assmModel);
				}
			}
			if (StringUtils.isNotBlank(assmModelStandardId)) {
				AssmModelStandard modelStandard = assmModelStandardManager.getEntity(assmModelStandardId);
				if (modelStandard != null) {
					entity.setAssmModelStandard(modelStandard);
				}
			}
			assmAccountManager.saveAssmAccount(entity);
			//使用情况为报废的不折旧 useStatus='3'
			if(StringUtils.isNotBlank(entity.getUseStatus()) && !"3".equals(entity.getUseStatus())){
				//保存完成后自动折旧
				assmAccountManager.updateDepreYeadNum(entity);
			}
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderText("fail");
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = assmAccountManager.getEntity(getId());
			if (entity != null) {
				assmAccountManager.delete(entity);
				Struts2Utils.renderText("success");
			}
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = assmAccountManager.getEntity(getId());
			String modelId = entity.getAssmModel().getAssmModelId();
			//初始化当前配置和标准配置
			String retValue = assmAccountManager.getValues(modelId, entity.getProjectCd());
			if (StringUtils.isNotBlank(retValue) && !"error".equals(retValue)) {
				String[] value = retValue.split(",");
				stanNum = value[2];
				currNum = value[3];
			}
			//得到该台账对应设备的父类的名称
			AssmModel model = assmModelManager.getEntity(entity.getAssmModel().getPratentId());
			if (model != null) {
				computerModelName = model.getAssmName();
			}
			res=assmAccountManager.loadResInfoByIds(entity.getResIds());
		} else {
			gerProject();
		}
	}
	
	
	public void gerProject() throws Exception{
		entity = new AssmAccount();
		String orgCd = null;
		String uiid = SpringSecurityUtils.getCurrentUiid();
		WsPlasAcct acct = SpringSecurityUtils.getCurrentPlasUser();
		//冒泡搜索当前用户的机构
		List<WsPlasOrg> orgList = PlasCache.getLogicalBubbleOrgListByUiid(uiid);
		for (WsPlasOrg tOrg : orgList) {
			if ("各商业公司".equals(tOrg.getOrgName())) {
				orgCd = tOrg.getOrgCd();
				break;
			}
		}
		//商业公司的人则取出当前用户所属的商业公司
		if (StringUtils.isNotBlank(orgCd)) {
			//搜索‘各商业公司’的下级机构
			List<WsPlasOrg> logicalOrgList = PlasCache.getLogicalDirectOrgListByOrgCd(orgCd);
			for (WsPlasOrg tmp : logicalOrgList) {
				//用‘各商业公司’的下级机构匹配当前用户部门
				if (acct.getOrgCd().equals(tmp.getOrgCd())) {
					entity.setProjectCd(tmp.getOrgCd());
					deptName = tmp.getOrgName();
					break;
				}else{
					//如果‘各商业公司’的下级机构的部门与当前用户的部门匹配不上继续往下级机构找
					List<WsPlasOrg> logicalOrgList2 = PlasCache.getLogicalDirectOrgListByOrgCd(tmp.getOrgCd());
					for (WsPlasOrg tmp2 : logicalOrgList2) {
						if (acct.getOrgCd().equals(tmp2.getOrgCd())) {
							entity.setProjectCd(tmp.getOrgCd());
							deptName = tmp.getOrgName();
							break;
						}
					}
				}
			}
		}else{
			//非商业公司的人默认商业公司为‘宝龙商业’
			List<WsPlasOrg> allOrgList = PlasCache.getOrgAllList();
			for (WsPlasOrg tOrg : allOrgList) {
				if ("宝龙商业".equals(tOrg.getOrgName())) {
					entity.setProjectCd(tOrg.getOrgCd());
					deptName = tOrg.getOrgName();
					break;
				}
			}
		}
	}
	
	
	public String getProjectCd() throws Exception{
		this.gerProject();
		if (StringUtils.isNotBlank(entity.getProjectCd()) && StringUtils.isNotBlank(deptName)) {
			Struts2Utils.renderText(deptName+","+entity.getProjectCd());
		}
		return null;
	}
	/**
	 * 根据资产ID得到该资产对应型号的二、三级设备ID
	 * @return
	 * @throws Exception
	 */
	public String getAssmModelParentId() throws Exception {
		String accountId = Struts2Utils.getParameter("assmAccountId");
		if(StringUtils.isNotBlank(accountId)){
			entity = assmAccountManager.getEntity(accountId);
			AssmModel model = assmModelManager.getEntity(entity.getAssmModel().getPratentId());
			String parentId = model.getPratentId();
			Struts2Utils.renderText(parentId+","+model.getAssmModelId());
		}
		return null;
	}
	
	/**
	 * 设备型号选择时搜索二级设备
	 * @return
	 * @throws Exception
	 */
	public String getAssmModel() throws Exception {
		//搜索二级设备
		mapAssmModel = assmModelManager.getMapAssmModel("2");
		return "firstModel";
		
	}
	/**
	 * 根据上一级别的设备ID搜索其子类列表
	 * @return
	 * @throws Exception
	 */
	public String loadNextModel() throws Exception{
		String pratentId = Struts2Utils.getParameter("assmModelId");
		String level = Struts2Utils.getParameter("level");
		List<AssmModel> list = assmModelManager.getAssmModelByPratentId(pratentId);
		if(list != null && list.size() > 0){
			for (AssmModel tmp : list) {
				mapNextAssmModel.put(tmp.getAssmModelId(), tmp.getAssmName());
			}
		}
		Struts2Utils.getRequest().setAttribute("level", level);
		if ("2".equals(level))
			return "secondModel";
		else if("3".equals(level))
			return "thirdModel";
		return null;
	}
	
	/**
	 * 根据设备ID和项目CD(商业公司)得到型号编码、残值、当前配置、标准配置等
	 * @param modelId 
	 * @param projectCd 
	 * @return
	 * @throws Exception
	 */
	public String getValues() throws Exception{
		String modelId = Struts2Utils.getParameter("assmModelId");
		String projectCd = Struts2Utils.getParameter("projectCd");
		String value = assmAccountManager.getValues(modelId, projectCd);
		if(StringUtils.isNotBlank(value)){
			Struts2Utils.renderText(value);
		}
		return null;
	}
	
	/**
	 * 校验资产编号是否唯一
	 * @param code 资产编号
	 * @return
	 * @throws Exception
	 */
	public String checkCode() throws Exception{
		String code = Struts2Utils.getParameter("code");
		if (StringUtils.isNotBlank(code)) {
			boolean flag = false;
			if (StringUtils.isNotBlank(getId())) {
				AssmAccount account = assmAccountManager.getEntity(getId());
				if (!code.equals(account.getCode())) {
					flag = assmAccountManager.checkAccountByCode(code);
				}
			}else{
				flag = assmAccountManager.checkAccountByCode(code);
			}
			Struts2Utils.renderText(flag+"");
		}
		return null;
	}
	/**
	 * 校验同一“保管人员”不能拥有两个或两个以上的设备
	 * @return
	 * @throws Exception
	 */
	public String checkKeeper() throws Exception{
		String keeperCd = Struts2Utils.getParameter("keeperCd");
		if (StringUtils.isNotBlank(keeperCd)) {
			boolean flag = false;
			if (StringUtils.isNotBlank(getId())) {
				AssmAccount account = assmAccountManager.getEntity(getId());
				if (!keeperCd.equals(account.getKeeperCd())) {
					flag = assmAccountManager.checkAccountByKeepCd(keeperCd);
				}
			}else{
				flag = assmAccountManager.checkAccountByKeepCd(keeperCd);
			}
			Struts2Utils.renderText(flag+"");
		}
		return null;
	}
	
	
	public String getModelStandard() throws Exception {
		String projectCd = Struts2Utils.getParameter("projectCd");
		String modelId = Struts2Utils.getParameter("assmModelId");
		if (StringUtils.isNotBlank(projectCd) && StringUtils.isNotBlank(modelId)) {
			AssmModelStandardVo modelStandard = assmModelStandardManager.getAssmModelStandardByCont(projectCd, modelId);
			if (modelStandard != null) {
				//String 
				//Struts2Utils.renderText(totalHasNum.setScale(2, RoundingMode.HALF_UP).toString()+
				//","+totalStandNum.setScale(2, RoundingMode.HALF_UP).toString());
			}
		}
		return null;
	}
	
	/**
	 * 快速搜索台账
	 * @param value 输入值
	 * @return
	 */
	public String quickSearch() throws Exception {
		String value =Struts2Utils.getParameter("value");
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from AssmAccount t where 1=1");
		if(StringUtils.isNotBlank(value)){
			hql.append(" and( t.assmName like :accountValue");
			hql.append(" or t.code like :accountValue)");
			param.put("accountValue", "%"+value.trim()+"%");
		}
		String[] role = SpringSecurityUtils.getCurrentRoleCds();
		boolean flag1 = false;
		boolean flag2 = false;
		for (int i = 0; i < role.length; i++) {
			if ("A_ASSM_ACC_VIEW_ALL".equals(role[i])) {
				flag1 = true;
			}
			if ("A_ASSM_ACC_VIEW_PRO".equals(role[i])) {
				flag2 = true;
			}
		}
		if(!flag1){
			if(flag2){
				//项目公司只查对应该公司的资产
				hql.append(" and t.projectCd = :projectCd");
				prepareModel();
				param.put("projectCd", entity.getProjectCd());
			}
		}
		hql.append(" order by t.createdDate asc");
		page.setPageSize(20);
		page = assmAccountManager.findPage(page, hql.toString(),param);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (AssmAccount tmp : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("assmAccountId", tmp.getAssmAccountId());
			String assmName = "";
			String code = "";
			if (StringUtils.isNotBlank(tmp.getAssmName())) {
				assmName = tmp.getAssmName();
			}
			if (StringUtils.isNotBlank(tmp.getCode())) {
				code = tmp.getCode();
			}
			map.put("assmName", assmName);
			map.put("code", code);
			AssmModel assmModel= tmp.getAssmModel();
			if (assmModel!=null){
			map.put("assmModelName", StringUtils.trimToEmpty(assmModel.getAssmName()));
			}
			map.put("outDeptName",CodeNameUtil.getDeptNameByCd(tmp.getUseDepartment()));//使用部门
			map.put("srcValue",tmp.getSrcValue()==null?"":tmp.getSrcValue().toString());//原值
			map.put("remainVal",tmp.getRemainVal()==null?"":tmp.getRemainVal().toString());//净值
			map.put("useDate",DateOperator.formatDate(tmp.getUseDate()) );//购置日期
			map.put("assmCode", StringUtils.trimToEmpty(tmp.getAssmCode()) );//型号编码
			
			//初始化当前配置和标准配置
			String retValue = assmAccountManager.getValues(tmp.getAssmModel().getAssmModelId(), tmp.getProjectCd());
			if (StringUtils.isNotBlank(retValue) && !"error".equals(retValue)) {
				String[] retValues = retValue.split(",");
				map.put("stanNum", StringUtils.trimToEmpty(retValues[2]));
				map.put("currNum", StringUtils.trimToEmpty(retValues[3]));
			}
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}
	
	public Map<String,String> getMapBisProject(){
		List<BisProject> bisList = bisProjectManager.getAll();
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (bisList != null && bisList.size() > 0) {
			for (BisProject tmp : bisList) {
				map.put(tmp.getOrgCd(), tmp.getProjectName());
			}
		}
		return map;
	}
	public Map<String,String> getMapAssmModelAll(){
		List<AssmModel> modelList = assmModelManager.getAll();
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (modelList != null && modelList.size() > 0) {
			for (AssmModel tmp : modelList) {
				map.put(tmp.getAssmModelId(), tmp.getAssmName());
			}
		}
		return map;
	}
	
	public Map<String,String> getMapUseStatus(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		map.put("1", "正常使用");
		map.put("2", "库存");
		map.put("3", "报废");
		map.put("4", "经营租出");
		map.put("5", "公用");
		map.put("6", "出售");
		map.put("7", "遗失");
		return map;
	}
	
	public String getUseStatus(String useStatusCd){
		String useStatus = null;
		if("1".equals(useStatusCd)){
			useStatus = "正常使用";
		}else if("2".equals(useStatusCd)){
			useStatus = "库存";
		}else if("3".equals(useStatusCd)){
			useStatus = "报废";
		}else if("4".equals(useStatusCd)){
			useStatus = "经营租出";
		}else if("5".equals(useStatusCd)){
			useStatus = "公用";
		}else{
			useStatus = "";
		}
		return useStatus;
	}
	
	public Map<String,String> getMapStorageSites(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		map.put("1", "办公区");
		map.put("2", "仓库");
		map.put("3", "城市广场");
		return map;
	}
	
	public String getStorageSites(String storageSitesCd){
		String storageSites = null;
		if("1".equals(storageSitesCd)){
			storageSites = "办公区";
		}else if("2".equals(storageSitesCd)){
			storageSites = "仓库";
		}else if("3".equals(storageSitesCd)){
			storageSites = "城市广场";
		}else{
			storageSites = "";
		}
		return storageSites;
	}
	
	public Map<String,String> getMapAddWays(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		map.put("1", "购入");
		map.put("2", "地产转入");
		map.put("3", "其他商业公司转入");
		map.put("4", "租入");
		map.put("5", "捐赠");
		map.put("6", "地产借入");
		map.put("7", "商业集团调拨");
		return map;
	}
	
	public String getAddWays(String addWaysCd){
		String addWay = null;
		if("1".equals(addWaysCd)){
			addWay = "购入";
		}else if("2".equals(addWaysCd)){
			addWay = "地产转入";
		}else if("3".equals(addWaysCd)){
			addWay = "其他商业公司转入";
		}else if("4".equals(addWaysCd)){
			addWay = "租入";
		}else if("5".equals(addWaysCd)){
			addWay = "捐赠";
		}else if("6".equals(addWaysCd)){
			addWay = "地产借入";
		}else if("7".equals(addWaysCd)){
			addWay = "商业集团调拨";
		}else{
			addWay = "";
		}
		return addWay;
	}
	
	@Override
	public AssmAccount getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		//需要删除的台账IDS
		String accountIds=Struts2Utils.getParameter("accountIds");
		//如果不为空，则删除相应的台账信息
		if(StringUtils.isNotBlank(accountIds)){
			String [] accIds=accountIds.split(",");
			assmAccountManager.deleteBatch(accIds);
			Struts2Utils.renderText("success");
		}else{
			Struts2Utils.renderText("删除出错，请核实！");
		}
		return null;
	}

	public AssmAccount getEntity() {
		return entity;
	}

	public void setEntity(AssmAccount entity) {
		this.entity = entity;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Map<String, String> getMapAssmModel() {
		return mapAssmModel;
	}

	public void setMapAssmModel(Map<String, String> mapAssmModel) {
		this.mapAssmModel = mapAssmModel;
	}

	public Map<String, String> getMapNextAssmModel() {
		return mapNextAssmModel;
	}

	public void setMapNextAssmModel(Map<String, String> mapNextAssmModel) {
		this.mapNextAssmModel = mapNextAssmModel;
	}

	public String getAssmModelStandardId() {
		return assmModelStandardId;
	}

	public void setAssmModelStandardId(String assmModelStandardId) {
		this.assmModelStandardId = assmModelStandardId;
	}

	@Override
	public Page<AssmAccount> getPage() {
		return page;
	}

	public void setPage(Page<AssmAccount> page) {
		this.page = page;
	}

	public Page<BisProject> getBisPage() {
		return bisPage;
	}

	public void setBisPage(Page<BisProject> bisPage) {
		this.bisPage = bisPage;
	}

	public String getStanNum() {
		return stanNum;
	}

	public void setStanNum(String stanNum) {
		this.stanNum = stanNum;
	}

	public String getCurrNum() {
		return currNum;
	}

	public void setCurrNum(String currNum) {
		this.currNum = currNum;
	}

	public String getComputerModelName() {
		return computerModelName;
	}

	public void setComputerModelName(String computerModelName) {
		this.computerModelName = computerModelName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public List<ResApproveInfoVo> getRes() {
		return res;
	}

	public void setRes(List<ResApproveInfoVo> res) {
		this.res = res;
	}

}

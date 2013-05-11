package com.hhz.ump.web.biz;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppDictDataManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.biz.BizRelaAuthManager;
import com.hhz.ump.dao.biz.BizRelaLibDelManager;
import com.hhz.ump.dao.biz.BizRelaLibManager;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.entity.biz.BizRelaAuth;
import com.hhz.ump.entity.biz.BizRelaLib;
import com.hhz.ump.entity.biz.BizRelaLibDel;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
@Namespace("/biz")
@Results( { 
		@Result(name = "export", type = "stream",params = { "contentType", "application/vnd.ms-excel", 
				"inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
		})
public class BizRelaLibAction extends CrudActionSupport<BizRelaLib> {

	@Autowired
	private BizRelaLibManager bizRelaLibManager;
	@Autowired
	private BizRelaLibDelManager bizRelaLibDelManager;
	@Autowired
	private AppDictDataManager appDictDataManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private BizRelaAuthManager bizRelaAuthManager;
	
	 
	private BizRelaLib entity;
	private BizRelaAuth bizRelaAuth;
	private BizRelaLibDel bizRelaLibDel;
	private AppDictData appDictData;
	private Page<AppDictData> pageAppData = new Page<AppDictData>(15);
	private Page<BizRelaLib> page = new Page<BizRelaLib>(10);
	private Page<BizRelaLibDel> pageDel = new Page<BizRelaLibDel>(10);
	private List<BizRelaLibDel> bizRelaDelLis;
	private AppDictType appDictType;
	private String queRelaTypeCd;
	private String queRelaProvince;
	private String relaProvinceName;
	private String queRelaArea;
	private String queRelaName;
	private String queRelaUnit;
	private String relaTypeCd;
	private String relaLevelCd;
	private String relaProvince;
	private String relaMobile;
	private String relaArea;
	private String relaName;
	private String relaUnit;
	private String downFileName;
	private InputStream is;
	/**
	 * 上传文件名
	 */
	private File[] upload;

	private String[] uploadFileName;

	private String[] uploadContentType;
	String fileName ;
	//指定记录导入失败的列
	private int sheetLine = 2;
	
	
	//数据字典
	private static String RELA_LIB_DICT_CD = "BIZ_AUTH_ORG";
	//机构列表
	private List orgList;
	
	//当前选中的用户uiid
	private String curUserCd;
	//已分配的机构列表
	private List selOrgList;
	
	
	@Override
	public BizRelaLib getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 导出公共关系库
//	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		//根据搜索条件查找到符合条件的记录
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = buildHql(param);
		List<BizRelaLib> results =bizRelaLibManager.find(hql.toString(), param);
		for (BizRelaLib bizRela : results) {
			bizRelaLibManager.getDao().evict(bizRela);
			bizRela.setRelaTypeCd(CodeNameUtil.getDictNameByCd(DictContants.BIZ_RELA_TYPE , bizRela.getRelaTypeCd().toString()));
		}
//		List<BizRelaLibVo> totalResults =new ArrayList<BizRelaLibVo>();
		Map beans = new HashMap();
		beans.put("result", results);
//		beans.put("total", totalResults);
		beans.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));
		is = JXLExcelUtil.initJxlsInputStream(beans, "relaResult.xls");
		String fileName = "公共关系库" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}
	/**
	 * 用于机构字典选择
	 * @param value
	 * @throws Exception
	 */
	public void quickSearchOrg() throws Exception {
		String userCd=  SpringSecurityUtils.getCurrentUiid();
		BizRelaAuth relaAuth=bizRelaAuthManager.getBizRelaAuth(userCd);

		Map<String, Object> param = new HashMap<String, Object>();
		String value = Struts2Utils.getParameter("value");
		StringBuffer hql = new StringBuffer();
		if(!SpringSecurityUtils.hasRole("A_BIZ_RELA_SUP") && null == relaAuth){
			hql.append(" from AppDictData where 1=2 ");
		}else{
			hql.append(" from AppDictData where  1=1 ");
			hql.append(" and appDictType.appDictTypeId =");
			hql.append("(select appDictTypeId from AppDictType t where t.dictTypeCd=:dictTypeCd )");
			param.put("dictTypeCd",RELA_LIB_DICT_CD);
			if(!SpringSecurityUtils.hasRole("A_BIZ_RELA_SUP")){
				String submitCenterCd=relaAuth.getSubmitCenterCd().substring(0, relaAuth.getSubmitCenterCd().length()-1);
				hql.append(" and dictCd in (:submitCenterCd)");
				param.put("submitCenterCd", submitCenterCd.split(","));
			}
			hql.append(" and dictName like :dictName");
			param.put("dictName", "%"+value+"%");
		}
		pageAppData = appDictDataManager.findPage(pageAppData, hql.toString(), param);
		List<Map<String, String> > list = new ArrayList<Map<String, String> >();
		for(AppDictData cl : pageAppData.getResult()){
			Map<String, String> map=new HashMap<String, String>();
			map.put("dictName", cl.getDictName());
			map.put("dictCd", cl.getDictCd());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}
/*	private List<BizRelaLibVo> relaLibExcel(List list){
		List<BizRelaLibVo> bizRelaLibList =new ArrayList<BizRelaLibVo>();
		if(list!=null&&list.size()>0){
			//合同
			for(int i=0;i<list.size();i++){
				Object[] obj =(Object[])list.get(i);
				BizRelaLibVo bizRelaLib=new BizRelaLibVo();
				bizRelaLib.setRelaProvince((String)obj[0]);
				bizRelaLib.setRelaArea((String)obj[1]);
				bizRelaLib.setRelaName((String)obj[2]);
				bizRelaLib.setRelaUnit((String)obj[3]);
				bizRelaLib.setRelaPos((String)obj[4]);
				bizRelaLib.setRelaAddress((String)obj[5]);
				bizRelaLib.setRelaPhone((String)obj[6]);
				bizRelaLib.setRelaMobile((String)obj[7]);
				bizRelaLib.setRelaFax((String)obj[8]);
				bizRelaLib.setSubmitCenterCd((String)obj[9]);
				bizRelaLib.setRelaTypeCd((String)obj[10]);
				bizRelaLibList.add(bizRelaLib);
			}
		}
		return bizRelaLibList;
	}	*/
	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		String hql=buildHql(param);
		
		page=bizRelaLibManager.findPage(page, hql, param);
		return "list";
	}
	public String delList() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String hql=buildDelHql(param);
		pageDel=bizRelaLibDelManager.findPage(pageDel, hql, param);
		bizRelaDelLis = pageDel.getResult();
		return "delList";
	}
	private String buildDelHql(Map<String, Object> param) { 
		StringBuffer hql=new StringBuffer().append(" from  BizRelaLibDel biz where 1=1 ");
		if(StringUtils.isNotBlank(relaTypeCd)){
			hql.append(" and biz.relaTypeCd =:relaTypeCd");
			param.put("relaTypeCd", relaTypeCd);
		}
		if (StringUtils.isNotBlank(relaProvinceName)) {
			hql.append(" and biz.relaProvinceName  like :relaProvinceName");
			param.put("relaProvinceName", "%"+relaProvinceName+"%");
		}
		if (StringUtils.isNotBlank(relaArea)) {
			hql.append(" and biz.relaArea  like :relaArea ");
			param.put("relaArea", "%"+relaArea+"%");
		}
		if (StringUtils.isNotBlank(relaName)) {
			hql.append(" and biz.relaName  like :relaName ");
			param.put("relaName", "%"+relaName+"%");
		}
		if (StringUtils.isNotBlank(relaUnit)) {
			hql.append(" and biz.relaUnit  like :relaUnit");
			param.put("relaUnit", "%"+relaUnit+"%");
		}
		if(relaTypeCd.equals("1")){
			hql.append(" order by biz.sequenceNo");
		}else{
			hql.append(" order by biz.relaTypeCd, biz.relaProvince, biz.sequenceNo");
		}
		return hql.toString();
	}

	private String buildHql(Map<String, Object> param) {
		String userCd = SpringSecurityUtils.getCurrentUiid();
		BizRelaAuth relaAuth = bizRelaAuthManager.getBizRelaAuth(userCd);
		if ((!SpringSecurityUtils.hasRole("A_BIZ_RELA_SUP") &&!SpringSecurityUtils.hasRole("A_BIZ_RELA_QUERYALL")) && null == relaAuth) {
			String hqls = " from  BizRelaLib where 1=2 ";
			return hqls;
		} else {
			StringBuffer hql = new StringBuffer().append(" from  BizRelaLib biz where 1=1 ");

			if ((!SpringSecurityUtils.hasRole("A_BIZ_RELA_SUP") &&!SpringSecurityUtils.hasRole("A_BIZ_RELA_QUERYALL")) && relaAuth != null) {
				String submitCenterCd = relaAuth.getSubmitCenterCd().substring(0, relaAuth.getSubmitCenterCd().length() - 1);
				hql.append(" and submitCenterCd in (:submitCenterCd)");
				param.put("submitCenterCd", submitCenterCd.split(","));
			}
			if (StringUtils.isNotBlank(relaTypeCd)) {
				hql.append(" and biz.relaTypeCd =:relaTypeCd");
				param.put("relaTypeCd", relaTypeCd);
			}
			if (StringUtils.isNotBlank(relaProvinceName)) {
				hql.append(" and biz.relaProvinceName  like :relaProvinceName");
				param.put("relaProvinceName", "%"+relaProvinceName+"%");
			}
			if (StringUtils.isNotBlank(relaArea)) {
				hql.append(" and biz.relaArea  like :relaArea ");
				param.put("relaArea", "%"+relaArea+"%");
			}
			if (StringUtils.isNotBlank(relaName)) {
				hql.append(" and biz.relaName  like :relaName ");
				param.put("relaName", "%"+relaName+"%");
			}
			if (StringUtils.isNotBlank(relaUnit)) {
				hql.append(" and biz.relaUnit  like :relaUnit");
				param.put("relaUnit", "%"+relaUnit+"%");
			}
			if (StringUtils.isNotBlank(relaLevelCd) && !(relaLevelCd.equals("4"))) {
				hql.append(" and biz.relaLevelCd  =:relaLevelCd");
				param.put("relaLevelCd", relaLevelCd);
			}
			if (relaTypeCd.equals("1")) {
				hql.append(" order by biz.sequenceNo");
			} else {
				hql.append(" order by biz.relaTypeCd, biz.relaProvince, biz.sequenceNo");
			}
			return hql.toString();
		}
	}
//	private String relaLibQuery(boolean isExport){
// 		StringBuffer hql = new StringBuffer();
//		hql.append(" from BizRelaLib a where 1=1 ");
//		return hql.toString();
//	}
	public String relaLibReduction () throws Exception{
		if(StringUtils.isNotBlank(getId())){
			bizRelaLibDel=bizRelaLibDelManager.getEntity(getId());
			entity=new BizRelaLib();
			entity.setCreatedCenterCd(bizRelaLibDel.getCreatedCenterCd());
			entity.setCreatedDate(bizRelaLibDel.getCreatedDate());
			entity.setCreatedDeptCd(bizRelaLibDel.getCreatedDeptCd());
			entity.setCreatedPositionCd(bizRelaLibDel.getCreatedPositionCd());
			entity.setCreator(bizRelaLibDel.getCreator());
			entity.setRecordVersion(0);
			entity.setRelaAddress(bizRelaLibDel.getRelaAddress());
			entity.setRelaArea(bizRelaLibDel.getRelaArea());
			entity.setRelaFax(bizRelaLibDel.getRelaFax());
			entity.setRelaMobile(bizRelaLibDel.getRelaMobile());
			entity.setRelaName(bizRelaLibDel.getRelaName());
			entity.setRelaPhone(bizRelaLibDel.getRelaPhone());
			entity.setRelaPos(bizRelaLibDel.getRelaPos());
			entity.setRelaProvince(bizRelaLibDel.getRelaProvince());
			entity.setRelaTypeCd(bizRelaLibDel.getRelaTypeCd());
			entity.setRelaUnit(bizRelaLibDel.getRelaUnit());
			entity.setRemark(bizRelaLibDel.getRemark());
			entity.setSequenceNo(bizRelaLibDel.getSequenceNo());
			entity.setSubmitCenterCd(bizRelaLibDel.getSubmitCenterCd());
			entity.setUpdatedCenterCd(bizRelaLibDel.getUpdatedCenterCd());
			entity.setUpdatedDate(bizRelaLibDel.getUpdatedDate());
			entity.setUpdatedDeptCd(bizRelaLibDel.getUpdatedDeptCd());
			entity.setUpdatedPositionCd(bizRelaLibDel.getUpdatedPositionCd());
			entity.setUpdator(bizRelaLibDel.getUpdator());
			bizRelaLibManager.saveBizRelaLib(entity);
			bizRelaLibDelManager.deleteBizRelaLibDel(getId());
			Struts2Utils.renderHtml("success");
			return null;
		}
		return "reduction";
	}
	
	public String bizRelaDetail() throws Exception{
			if(StringUtils.isNotBlank(getId())){
				entity=bizRelaLibManager.getEntity(getId());				
		}
			return "bizRelaDetail";
	}
	public String bizRelaImport() throws Exception{
		return "import";
	}
	public String relaAuth() throws Exception{
		
		return "relaAuth";
	}
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		bizRelaLibManager.saveBizRelaLib(entity);
		return "input";
	}
	public String relaAuthSave() throws Exception {
		// TODO Auto-generated method stub
		bizRelaAuthManager.saveBizRelaAuth(bizRelaAuth);
		Struts2Utils.renderHtml("success");
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity=bizRelaLibManager.getEntity(getId());
			bizRelaLibDel=new BizRelaLibDel();
//			bizRelaLibDel.setBizRelaLibDelId(entity.getBizRelaLibId());
			bizRelaLibDel.setCreatedCenterCd(entity.getCreatedCenterCd());
			bizRelaLibDel.setCreatedDate(entity.getCreatedDate());
			bizRelaLibDel.setCreatedDeptCd(entity.getCreatedDeptCd());
			bizRelaLibDel.setCreatedPositionCd(entity.getCreatedPositionCd());
			bizRelaLibDel.setCreator(entity.getCreator());
			bizRelaLibDel.setRecordVersion(0);
			bizRelaLibDel.setRelaAddress(entity.getRelaAddress());
			bizRelaLibDel.setRelaArea(entity.getRelaArea());
			bizRelaLibDel.setRelaFax(entity.getRelaFax());
			bizRelaLibDel.setRelaMobile(entity.getRelaMobile());
			bizRelaLibDel.setRelaName(entity.getRelaName());
			bizRelaLibDel.setRelaPhone(entity.getRelaPhone());
			bizRelaLibDel.setRelaPos(entity.getRelaPos());
			bizRelaLibDel.setRelaProvince(entity.getRelaProvince());
			bizRelaLibDel.setRelaTypeCd(entity.getRelaTypeCd());
			bizRelaLibDel.setRelaUnit(entity.getRelaUnit());
			bizRelaLibDel.setRemark(entity.getRemark());
			bizRelaLibDel.setSequenceNo(entity.getSequenceNo());
			bizRelaLibDel.setSubmitCenterCd(entity.getSubmitCenterCd());
			bizRelaLibDel.setUpdatedCenterCd(entity.getUpdatedCenterCd());
			bizRelaLibDel.setUpdatedDate(entity.getUpdatedDate());
			bizRelaLibDel.setUpdatedDeptCd(entity.getUpdatedDeptCd());
			bizRelaLibDel.setUpdatedPositionCd(entity.getUpdatedPositionCd());
			bizRelaLibDel.setUpdator(entity.getUpdator());
			bizRelaLibDelManager.saveBizRelaLibDel(bizRelaLibDel);
			bizRelaLibManager.deleteBizRelaLib(getId());
//				addActionMessage(getText("success"));
				Struts2Utils.renderHtml("success");
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (getId() != null) {
			entity = bizRelaLibManager.getEntity(getId());
		} else {
			entity = new BizRelaLib();
		}
	}
	private String importExl(){
		StringBuffer error = new StringBuffer().append("");
		Workbook book = null;
		Sheet se = null;
		Cell[] cells = null;
		BizRelaLib info = null;
		int begin = 0;
		int end = 0;
		//指定开始解析行号
		int overlookno = 1;
		//指定解析的工作薄
		Integer sheetNow = 2;
		//记录失败
		List<ResultVO> errorList = new ArrayList<ResultVO>();
		ResultVO result = null;
		try {
			book = Workbook.getWorkbook(upload[0]);
			if(!(relaTypeCd.equals(""))&&null!=relaTypeCd){
				sheetNow=Integer.parseInt(relaTypeCd);
			}
			String sheetName=CodeNameUtil.getDictNameByCd(DictContants.BIZ_RELA_TYPE, relaTypeCd);
			se = book.getSheet(sheetName);//得到第1个sheet(Excel通常会有3个Sheet)  
			int rownum = se.getRows();//得到总行数  
			//解析合并的单元gre
//			List<String> mergeRow = new ArrayList<String>();
//			List<String> rows = new ArrayList<String>();
//			for(int j=0;j<=sheetNow;j++){
//				se = book.getSheet(j);
//				relaTypeCd=String.valueOf(j);
				for (int i = overlookno; i < rownum; i++) {
					 cells = se.getRow(i);
					 entity = new BizRelaLib();
					 entity.setRelaProvince(CodeNameUtil.getDictCdByName(DictContants.CITY_PROVINCE_TYPE, cells[0].getContents()));
					 entity.setRelaProvinceName(cells[0].getContents());
					 entity.setRelaArea(cells[1].getContents());
					 entity.setRelaName((cells[2].getContents()));
					 entity.setRelaUnit((cells[3].getContents()));
					 entity.setRelaPos((cells[4].getContents()));
					 entity.setRelaAddress((cells[5].getContents()));
					 entity.setRelaPhone((cells[6].getContents()));
					 entity.setRelaMobile((cells[7].getContents()));
					 entity.setRelaFax((cells[8].getContents()));
					 String submitCenter=(cells[9].getContents());
					 if(submitCenter.startsWith("许")){
						 entity.setSubmitPersion(submitCenter);
						 entity.setSubmitCenterCd("168");
						 entity.setSubmitCenterName("决策层");
					 }else{
						 entity.setSubmitCenterName(submitCenter);
						 entity.setSubmitCenterCd(CodeNameUtil.getDictCdByName(RELA_LIB_DICT_CD, submitCenter));
					 }
					 long j=i;
					 entity.setRelaTypeCd(relaTypeCd);
					 entity.setSequenceNo(j);
					 result = new ResultVO(i);
					 if(StringUtils.isBlank(entity.getRelaProvince())&&StringUtils.isBlank(entity.getRelaArea())&&StringUtils.isBlank(entity.getRelaName())&&StringUtils.isBlank(entity.getRelaMobile())&&StringUtils.isBlank(entity.getRelaUnit())){
						 break;
					 }else{
						 bizRelaLibManager.saveBizRelaLib(entity);
					 }
//						if(!result.flag){
//							error.append(" 行号：").append(i).append(" ：").append("").append(";");
//							continue;
//						}
				//	}
//				}
			}
		} catch (Exception e) {  
			e.printStackTrace();
			Struts2Utils.renderText("导入失败");
			return null;
		} finally{
			if(book != null){
				book.close();
			}
			upload[0].delete();
		}
		if(error.equals("")){
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("导入成功;请重新进入");
		}
		return null;
	}
//	public void saveImport(){
//		int n= 
//		if(n==1){
//			result.flag = true;
//		}else{
//			result.flag = false;
//		}
//	}	
	private class ResultVO{
		public boolean flag;
		public String mess;
		public int no;
		public ResultVO(int no){
			this.no = no;
		}
		public ResultVO(boolean flag,String mess){
			this.flag = flag ;this.mess = mess;
		}
		public ResultVO(boolean flag,String mess,int no){
			this.flag = flag ;this.mess = mess;this.no = no;
		}
	}
	public String uniqueExist()throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer().append(" from BizRelaLib where 1=1 ");
		if(StringUtils.isNotBlank(queRelaName)){
			hql.append(" and relaName  = '").append(queRelaName).append("'");
		}
		if(StringUtils.isNotBlank(queRelaUnit)){
			hql.append(" and relaUnit  = '").append(queRelaUnit).append("'");
		}
		if(StringUtils.isNotBlank(relaMobile)){
			hql.append(" and relaMobile  = '").append(relaMobile).append("'");
		}
		List<BizRelaLib> result = bizRelaLibManager.find(hql.toString(), param);
		if (result.size()>0) {
			Struts2Utils.renderHtml("failure");
		} else {
			Struts2Utils.renderHtml("success");
		}
		return null;
	}
	public BizRelaLib getEntity() {
		return entity;
	}

	public void setEntity(BizRelaLib entity) {
		this.entity = entity;
	}

	public String getRelaTypeCd() {
		return relaTypeCd;
	}

	public void setRelaTypeCd(String relaTypeCd) {
		this.relaTypeCd = relaTypeCd;
	}

	public String getRelaProvince() {
		return relaProvince;
	}

	public void setRelaProvince(String relaProvince) {
		this.relaProvince = relaProvince;
	}

	public String getRelaArea() {
		return relaArea;
	}

	public void setRelaArea(String relaArea) {
		this.relaArea = relaArea;
	}

	public String getRelaName() {
		return relaName;
	}

	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}

	public String getRelaUnit() {
		return relaUnit;
	}

	public void setRelaUnit(String relaUnit) {
		this.relaUnit = relaUnit;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

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

	public String getQueRelaTypeCd() {
		return queRelaTypeCd;
	}

	public void setQueRelaTypeCd(String queRelaTypeCd) {
		this.queRelaTypeCd = queRelaTypeCd;
	}

	public String getQueRelaProvince() {
		return queRelaProvince;
	}

	public void setQueRelaProvince(String queRelaProvince) {
		this.queRelaProvince = queRelaProvince;
	}

	public String getQueRelaArea() {
		return queRelaArea;
	}

	public void setQueRelaArea(String queRelaArea) {
		this.queRelaArea = queRelaArea;
	}

	public String getQueRelaName() {
		return queRelaName;
	}

	public void setQueRelaName(String queRelaName) {
		this.queRelaName = queRelaName;
	}

	public String getQueRelaUnit() {
		return queRelaUnit;
	}

	public void setQueRelaUnit(String queRelaUnit) {
		this.queRelaUnit = queRelaUnit;
	}

	public String getRelaMobile() {
		return relaMobile;
	}

	public void setRelaMobile(String relaMobile) {
		this.relaMobile = relaMobile;
	}

	@Override
	public Page<BizRelaLib> getPage() {
		return page;
	}

	public void setPage(Page<BizRelaLib> page) {
		this.page = page;
	}

	public List<BizRelaLibDel> getBizRelaDelLis() {
		return bizRelaDelLis;
	}

	public void setBizRelaDelLis(List<BizRelaLibDel> bizRelaDelLis) {
		this.bizRelaDelLis = bizRelaDelLis;
	}

	public String getRelaLevelCd() {
		return relaLevelCd;
	}

	public void setRelaLevelCd(String relaLevelCd) {
		this.relaLevelCd = relaLevelCd;
	}

	public String getRelaProvinceName() {
		return relaProvinceName;
	}

	public void setRelaProvinceName(String relaProvinceName) {
		this.relaProvinceName = relaProvinceName;
	}

	public BizRelaAuth getBizRelaAuth() {
		return bizRelaAuth;
	}

	public void setBizRelaAuth(BizRelaAuth bizRelaAuth) {
		this.bizRelaAuth = bizRelaAuth;
	}

	//获取多选人员树
	public String buildUserTree() {
		TreePanelNode node = TreePanelUtil.buildPhysicalOrgUserTreeNoCheck(TreePanelUtil.getRootOrg(), PlasCache.getOrgAllList(), PlasCache.getUserActiveList());
		Struts2Utils.renderJson(node);
		return null;
	}
	
	//获取机构列表
	public String bizRelaOrgList(){
		
		orgList = getDictOrgList();
		
		return "orgList";
	}
	//获取选择机构列表
	public String bizRelaOrgSelList(){
		
		orgList = getDictOrgList();
		
		return "orgListSel";
	}
	
	private List getDictOrgList(){
		Map<String,Object> values= new HashMap<String, Object>();
		values.put("appDictTypeCd", RELA_LIB_DICT_CD);
		String hql = " from AppDictData t where t.appDictType.dictTypeCd = :appDictTypeCd ";
		List list = appDictTypeManager.getDao().createQuery(hql, values).list();
		if(list == null){
			list = new ArrayList<AppDictData>();
		}
		return list;
	}

	public List getOrgList() {
		return orgList;
	}

	public void setOrgList(List orgList) {
		this.orgList = orgList;
	}
	
	/**
	 * 保存分配的管理用户和机构关系
	 * @param orgDictCds
	 * @param addUserIds
	 * @param delUserIds
	 * 
	 * @return
	 */
	public String saveBatchUserOrgRel(){
		
		String orgDictCds = Struts2Utils.getParameter("orgDictCds");
		String addUserIds = Struts2Utils.getParameter("addUserIds");
		String delUserIds = Struts2Utils.getParameter("delUserIds");

		if(StringUtils.isBlank(orgDictCds ) ||(StringUtils.isBlank(addUserIds) && StringUtils.isBlank(delUserIds))){
			Struts2Utils.renderText("failure: error params!");
			return null;
		}

		bizRelaAuthManager.saveBatchUserOrgRel(orgDictCds, addUserIds, delUserIds);
		Struts2Utils.renderText("success");
		return null;
	}
	
	/**
	 * 搜索用户的机构列表
	 * @param userId
	 * 
	 * @return
	 */
	public String dispatchedOrgList(){
		
		String userId = Struts2Utils.getParameter("userId");
		curUserCd = PlasCache.getUserById(userId).getUiid();
		BizRelaAuth auth = bizRelaAuthManager.getBizRelaAuth(curUserCd);
		if(auth == null)
			return "dispatchedOrgList";
		String centerCd = auth.getSubmitCenterCd();
		
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(RELA_LIB_DICT_CD);
		
		selOrgList = new ArrayList<AppDictData>();
		for (String tCd : centerCd.split(",")) {
			if(StringUtils.isBlank(tCd)){
				continue;
			}
			AppDictData dictData= appDictTypeManager.getDictDataByCd(tCd,RELA_LIB_DICT_CD);
			if (dictData!=null){
				selOrgList.add(dictData);
			}
		}
		CollectionHelper.sortList(selOrgList, "dispOrderNo", false);
		
		return "dispatchedOrgList";
	}

	
	/**
	 * 搜索用户的机构列表
	 * @param userId
	 * @param orgCd
	 * 
	 * @return
	 */
		
	public String removeRel(){
		String userCd = Struts2Utils.getParameter("userCd");
		String orgCd = Struts2Utils.getParameter("orgCd");
		bizRelaAuthManager.removeRel(userCd, orgCd);

		Struts2Utils.renderText("success");
		return null;
	}

	public List getSelOrgList() {
		return selOrgList;
	}

	public void setSelOrgList(List selOrgList) {
		this.selOrgList = selOrgList;
	}

	public String getCurUserCd() {
		return curUserCd;
	}

	public void setCurUserCd(String curUserCd) {
		this.curUserCd = curUserCd;
	}
	
}

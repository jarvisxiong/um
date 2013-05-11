package com.hhz.ump.web.mate;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.mate.MateCountBasicManager;
import com.hhz.ump.dao.mate.MateCountDetailManager;
import com.hhz.ump.dao.mate.MateOwnerTypeManager;
import com.hhz.ump.entity.mate.MateCountBasic;
import com.hhz.ump.entity.mate.MateCountDetailVo;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.JXLExcelUtil;

@Namespace("/mate")
@Results( { 
		@Result(name = "export", type = "stream",params = { "contentType", "application/vnd.ms-excel", 
				"inputName","is", "contentDisposition", "attachment;filename=${downFileName}.xls" })
		})
	public class MateBasicAction extends CrudActionSupport<MateCountBasic> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9101447562674833448L;
	private MateCountBasic entity;
	private String contNo;
	private String suppUnitName;
	private String downFileName;
    private String clearAudit;//已清算总额
    private String useUnitName;//领货单位名称
    private String clearStatus;//清算状态
    private String useStatus;//是否已提交
	private Date insDate;
	private Date insDateBetween;
	private InputStream is;
	private Map<String, String> mateBasicMap = new HashMap<String, String>();
	private List<MateCountBasic> mateCountBasic;
	private String contTypes;
    private String inputFlag;
    private String contName ;
    private String exeContNo;
    private String exeUnitName;
	private Map<String, String> mateTypeMap = new HashMap<String, String>();
	@Autowired
	private MateOwnerTypeManager mateTypeManager;
	@Autowired
	private MateCountBasicManager mateCountBasicManager;	
	@Autowired
	private MateCountDetailManager mateCountDetailManager;
	@Override
	public MateCountBasic getModel() {
		// TODO Auto-generated method stub
		return entity;
	}


	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 拼装甲供料基本信息搜索条件
	 * @return
	 */
	private String materialBasicQuery(boolean isExport){
 		StringBuffer hql = new StringBuffer();
		hql.append("select a from MateCountBasic a ");
		if (contTypes != null && !"".equals(contTypes)) {
			String[] types = contTypes.replaceAll(" ", "").split(",");
			//对types遍历，分出搜索条件为公司的和类别的
			List<String> typeProject = new ArrayList<String>();
			List<String> typeCode = new ArrayList<String>();
			for(int i=0;i<types.length;i++){
				//由类别为10位随机数，则若长度为10则为类别
				if(types[i].length()!=10){
					typeProject.add(types[i]);
				}else{
					typeCode.add(types[i]);
				}
			}
			hql.append(",ContProjectCode c where a.projectCd=c.projectCd");
			hql.append(" and (");
			if((typeProject!=null&&typeProject.size()>0)&&(typeCode.size()<=0)){
				for(int i=0;i<typeProject.size();i++){
					hql.append(" c.projectCd='").append(typeProject.get(i)).append("'");
					if(i!=(typeProject.size()-1)){
						hql.append(" or ");
					}
				}
			}else{
				if(typeProject!=null&&typeProject.size()>0){
					for(int i=0;i<typeProject.size();i++){
						hql.append(" c.projectCd='").append(typeProject.get(i)).append("'");
						hql.append(" or ");
					}
				}
				for(int i=0;i<typeCode.size();i++){
					hql.append(" a.ownerMaterialType like '%").append(types[i])
							.append("%'");
					if (i != (typeCode.size() - 1)) {
						hql.append(" or ");
					}
				}
			}
			hql.append(" )");
		}else{
			hql.append(" where 1!= 1");
			/*
			hql.append(",ContProjectCode c where a.projectCd=c.projectCd ");
			//如果是地产公司员工，则寻找它所拥有的其它公司权限值，得到相关数据操作权限
			List<HashMap<String,String>> centerList =mateProjectCodeManager.queryMateAuthority();
			
			Iterator iter = centerList.iterator(); 
			int i=0;
			if(centerList!=null&&centerList.size()>0){
				hql.append(" and (");
				while (iter.hasNext()) { 
				    Map map = (Map) iter.next(); 
				    String key = map.keySet().iterator().next().toString();
						if(i!=0){
							hql.append(" or ");
						}
						hql.append(" a.projectCd='"+key+"'");
					i++;
				} 
				hql.append(")");
			}
			*/
		}
		if((insDate!=null&&!"".equals(insDate))||(insDateBetween!=null&&!"".equals(insDateBetween))||StringUtils.isNotBlank(useUnitName)){
			hql.append(" and a.mateCountBasicId in ( select mateCountBasic.mateCountBasicId from MateCountDetail b where 1=1 ");
			if(StringUtils.isNotBlank(useUnitName)){
				hql.append(" and b.useUnitName like '%").append(useUnitName).append("%'");
			}
			if(insDate!=null&&!"".equals(insDate)){
				String toInsDate=DateOperator.formatDate((Date)insDate, "yyyy-MM-dd");
				hql.append(" and b.insDate >= to_date('").append(toInsDate).append("','yyyy-mm-dd')");
			}
			if(insDateBetween!=null&&!"".equals(insDateBetween)){
				String toInsDateBetween=DateOperator.formatDate((Date)insDateBetween, "yyyy-MM-dd");
				hql.append(" and b.insDate <= to_date('").append(toInsDateBetween).append("','yyyy-mm-dd')");
			}
			hql.append(" ) ");
		}
		if(contName!=null && !"".equals(contName)){
			hql.append(" and  a.contName like '%").append(contName).append("%'");
		}
		if(exeContNo!=null && !"".equals(exeContNo)){
			hql.append(" and  a.exeContNo like '%").append(exeContNo).append("%'");
		}
		if(exeUnitName!=null && !"".equals(exeUnitName)){
			hql.append(" and  a.exeUnitName like '%").append(exeUnitName).append("%'");
		}
		if(contNo!=null && !"".equals(contNo)){
			hql.append(" and  a.contNo like '%").append(contNo).append("%'");
		}
		if(useStatus!=null && !"".equals(useStatus)){
			if(useStatus.equals("1")){
				hql.append(" and  a.useStatus = '").append(useStatus).append("'");
			}else{
				hql.append(" and ( a.useStatus = '").append(useStatus).append("'");
				hql.append(" or  a.useStatus is null )");
			}
		}
		if(clearStatus!=null && !"".equals(clearStatus)&&!"null".equals(clearStatus)){
			hql.append(" and  a.clearStatus = '").append(clearStatus).append("'");
		}
		if(suppUnitName!=null && !"".equals(suppUnitName)){
			hql.append(" and  a.suppUnitName like '%").append(suppUnitName).append("%'");
		}
		
		return hql.toString();
	}
	/**
	 * 拼装甲供料详细信息搜索条件
	 * @return
	 */
	private String materialDetailQuery(boolean isExport){
		StringBuffer sql = new StringBuffer()
		.append(" select c.mate_Tasname,c.use_Unit_Name,c.use_Space,c.ins_Date,c.mate_Brand,c.mate_Char,c.mate_Stan,c.mate_Type,c.unit,");
		sql.append(" c.buy_Cout_Single,c.real_Supply,c.exe_Unit_Appl_Bud,c.exe_Unit_Real_Use,c.exe_Cout_single,c.stock_All,c.ins_Register_Number,");
		sql.append(" a.cont_No,a.cont_name,a.supp_Unit_Name,a.bud_Supply_All,a.real_Supply_All,a.end_Supply_All,a.no_Clear_Audit,a.clear_Audit,c.remark,c.clear_audit");
		sql.append("  from  Mate_Count_Basic a, Mate_Count_Detail c where 1=1 ");
		if(StringUtils.isNotBlank(useUnitName)){
			sql.append(" and  c.use_Unit_Name like '%").append(useUnitName).append("%'");
		}
//		if(clearAudit!=null && !"".equals(clearAudit)){
//			sql.append(" and  c.clear_Audit = '").append(clearAudit).append("'");
//		}
		if(insDate!=null&&!"".equals(insDate)){
			String toInsDate=DateOperator.formatDate((Date)insDate, "yyyy-MM-dd");
			sql.append(" and c.ins_Date >= to_date('").append(toInsDate).append("','yyyy-mm-dd')");
		}
		if(insDateBetween!=null&&!"".equals(insDateBetween)){
			String toInsDateBetween=DateOperator.formatDate((Date)insDateBetween, "yyyy-MM-dd");
			sql.append(" and c.ins_Date <= to_date('").append(toInsDateBetween).append("','yyyy-mm-dd')");
		}
//		if(contNo!=null && !"".equals(contNo)){
//			sql.append(" and  a.cont_No like '%").append(contNo).append("%'");
//		}
		if(clearStatus!=null && !"".equals(clearStatus)){
			sql.append(" and  (c.clear_audit = '").append(clearStatus).append("'");
			sql.append(" or c.clear_audit is null) ");
		}
//		if(suppUnitName!=null && !"".equals(suppUnitName)){
//			sql.append(" and  a.supp_Unit_Name like '%").append(suppUnitName).append("%'");
//		}
		if(StringUtils.isNotBlank(getId())){
			sql.append(" and  c.mate_Count_Basic_Id = '").append(getId()).append("'");
		}
		if (contTypes != null && !"".equals(contTypes)) {
			String[] types = contTypes.replaceAll(" ", "").split(",");
			sql.append(" and (");
			for(int i=0;i<types.length;i++){
				sql.append(" a.owner_Material_Type like '%").append(types[i])
				.append("%'");
				if (i != (types.length - 1)) {
					sql.append(" or ");
				}
			}
			sql.append(" )");
		}
		sql.append("and c.mate_Count_Basic_Id = a.mate_Count_Basic_Id ");
		
		//add by huangbijin 2012-01-29 排序
		sql.append(" order by c.ins_register_number asc ");
		return sql.toString();
	}
	@Override
	public String list() throws Exception {
		
		
		Map<String, Object> values = new HashMap<String, Object>();
		
		page = mateCountBasicManager.findPage(page, materialBasicQuery(false),values);
		//显示公司名称
		mateCountBasic=page.getResult();
		for(MateCountBasic mateBasic:mateCountBasic){
			mateBasicMap.put(mateBasic.getProjectCd(), CodeNameUtil.getDeptNameByCd(mateBasic.getProjectCd()));
		}
		return "list";
	}

	@Override
	public String input() throws Exception {
			mateBasicMap.put(entity.getProjectCd(), CodeNameUtil.getDeptNameByCd(entity.getProjectCd()));
			buildToTypeMap(entity);
		return "input";
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		mateBasicMap.put(entity.getProjectCd(), CodeNameUtil.getDeptNameByCd(entity.getProjectCd()));
		buildToTypeMap(entity);
		mateCountBasicManager.saveMateCountBasic(entity);
		inputFlag="保存成功！";
		return "input";
	}
	private void buildToTypeMap(MateCountBasic materialCountBasic){
		if(StringUtils.isNotBlank(getId())||StringUtils.isNotBlank(materialCountBasic.getOwnerMaterialType())){
			List<String> typeNameList =mateTypeManager.getTypeList(materialCountBasic.getOwnerMaterialType(),new ArrayList<String>());
			StringBuffer typeName =new StringBuffer("");
			if(typeNameList!=null&&typeNameList.size()>0){
				for(int i=typeNameList.size();i>0;i--){
					typeName.append(typeNameList.get(i-1));
					typeName.append(" ");
				}
			}
			mateTypeMap.put(materialCountBasic.getOwnerMaterialType(), typeName.toString());
		}
		
	}
	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity=mateCountBasicManager.getEntity(getId());
			if(null!=entity.getUseStatus()&&entity.getUseStatus().equals("1")){
				addActionMessage(getText("failures"));
//				Struts2Utils.renderHtml("failure");
			}else{
				mateCountBasicManager.deleteMateCountBasic(getId());
//				addActionMessage(getText("success"));
				Struts2Utils.renderHtml("success");
			}
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (getId() != null) {
			entity = mateCountBasicManager.getEntity(getId());
		} else {
			entity = new MateCountBasic();
		}
		
	}

	/**
	 * 导出甲供料报表
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		//根据搜索条件查找到符合条件的记录
		Map<String, Object> param = new HashMap<String, Object>();
		List<MateCountDetailVo> results =mateCountDetailExcel(mateCountDetailManager.findBySql(materialDetailQuery(true),param));
		List<MateCountDetailVo> totalResults =new ArrayList<MateCountDetailVo>();
		if(results!=null&&results.size()>0){
			MateCountDetailVo totalVo =new MateCountDetailVo();
			totalVo.setContNo(results.get(results.size()-1).getContNo());
			totalVo.setContName(results.get(results.size()-1).getContName());
			totalVo.setSuppUnitName(results.get(results.size()-1).getSuppUnitName());
			totalVo.setBudSupplyAll(results.get(results.size()-1).getBudSupplyAll());
			totalVo.setRealSupplyAll(results.get(results.size()-1).getRealSupplyAll());
			totalVo.setEndSupplyAll(results.get(results.size()-1).getEndSupplyAll());//已供货总额
			totalVo.setNoClearAudit(results.get(results.size()-1).getNoClearAudit());
			totalVo.setClearAudit(results.get(results.size()-1).getClearAudit());
			totalResults.add(totalVo);
		}
		Map beans = new HashMap();
		beans.put("result", results);
		beans.put("total", totalResults);
		beans.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));
		is = JXLExcelUtil.initJxlsInputStream(beans, "mateResult.xls");
		String fileName = "甲供料台账" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}
	private List<MateCountDetailVo> mateCountDetailExcel(List list){
		List<MateCountDetailVo> mateDetailList =new ArrayList<MateCountDetailVo>();
		if(list!=null&&list.size()>0){
			//合同
			for(int i=0;i<list.size();i++){
				Object[] obj =(Object[])list.get(i);
				MateCountDetailVo mateDetail=new MateCountDetailVo();
				mateDetail.setMateTasname((String)obj[0]);
				mateDetail.setUseUnitName((String)obj[1]);
				mateDetail.setUseSpace((String)obj[2]);
				mateDetail.setToInsDate(DateOperator.formatDate((Date)obj[3], "yyyy-MM-dd"));
				mateDetail.setMateBrand((String)obj[4]);
				mateDetail.setMateChar((String)obj[5]);
				mateDetail.setMateStan((String)obj[6]);
				mateDetail.setMateType((String)obj[7]);
				mateDetail.setUnit((String)obj[8]);
				mateDetail.setBuyCoutSingle(obj[9]==null?new BigDecimal(0):(BigDecimal)obj[9]);
				mateDetail.setRealSupply(obj[10]==null?new BigDecimal(0):(BigDecimal)obj[10]);
				mateDetail.setAddAllPrice(mateDetail.getBuyCoutSingle().multiply(mateDetail.getRealSupply()));
				mateDetail.setExeUnitApplBud(obj[11]==null?new BigDecimal(0):(BigDecimal)obj[11]);
				mateDetail.setExeUnitRealUse(obj[12]==null?new BigDecimal(0):(BigDecimal)obj[12]);
				mateDetail.setExeCoutSingle(obj[13]==null?new BigDecimal(0):(BigDecimal)obj[13]);
				mateDetail.setExeCountAllPrice(mateDetail.getExeUnitRealUse().multiply(mateDetail.getExeCoutSingle()));
				mateDetail.setStockAll(obj[14]==null?new BigDecimal(0):(BigDecimal)obj[14]);
				mateDetail.setInsRegisterNumber((String)obj[15]);
				mateDetail.setContNo((String)obj[16]);
				mateDetail.setContName((String)obj[17]);
				mateDetail.setSuppUnitName((String)obj[18]);//
				mateDetail.setBudSupplyAll(obj[19]==null?new BigDecimal(0):(BigDecimal)obj[19]);//
				mateDetail.setRealSupplyAll(obj[20]==null?new BigDecimal(0):(BigDecimal)obj[20]);//
				mateDetail.setEndSupplyAll(obj[21]==null?new BigDecimal(0):(BigDecimal)obj[21]);//已供货总额
				mateDetail.setNoClearAudit(obj[22]==null?new BigDecimal(0):(BigDecimal)obj[22]);
				mateDetail.setClearAudit(obj[23]==null?new BigDecimal(0):(BigDecimal)obj[23]);
				mateDetail.setRemark((String)obj[24]);
				if(null!=(String)obj[24]&&!("0".equals((String)obj[24]))){
					mateDetail.setIfClearAudit("是");
				}else{
					mateDetail.setIfClearAudit("否");
				}
				mateDetailList.add(mateDetail);
			}
		}
		return mateDetailList;
	}
	
	public List<MateCountBasic> getMateCountBasic() {
		return mateCountBasic;
	}

	public void setMaterialCountBasic(List<MateCountBasic> mateCountBasic) {
		this.mateCountBasic = mateCountBasic;
	}
	public Map<String, String> getMateBasicMap() {
		return mateBasicMap;
	}

	public void setMateBasicMap(Map<String, String> mateBasicMap) {
		this.mateBasicMap = mateBasicMap;
	}

	public String getContTypes() {
		return contTypes;
	}

	public void setContTypes(String contTypes) {
		this.contTypes = contTypes;
	}
	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getInputFlag() {
		return inputFlag;
	}

	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}

	public Map<String, String> getMateTypeMap() {
		return mateTypeMap;
	}

	public void setMateTypeMap(Map<String, String> mateTypeMap) {
		this.mateTypeMap = mateTypeMap;
	}
	public String getSuppUnitName() {
		return suppUnitName;
	}
	
	
	public void setSuppUnitName(String suppUnitName) {
		this.suppUnitName = suppUnitName;
	}
	public MateCountBasic getEntity() {
		return entity;
	}
	
	public void setEntity(MateCountBasic entity) {
		this.entity = entity;
	}


	public String getDownFileName() {
		return downFileName;
	}


	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}


	public InputStream getIs() {
		return is;
	}


	public void setIs(InputStream is) {
		this.is = is;
	}


	public String getUseUnitName() {
		return useUnitName;
	}


	public Date getInsDate() {
		return insDate;
	}


	public void setUseUnitName(String useUnitName) {
		this.useUnitName = useUnitName;
	}


	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}


	public Date getInsDateBetween() {
		return insDateBetween;
	}


	public void setInsDateBetween(Date insDateBetween) {
		this.insDateBetween = insDateBetween;
	}


	public String getClearAudit() {
		return clearAudit;
	}


	public void setClearAudit(String clearAudit) {
		this.clearAudit = clearAudit;
	}


	public String getClearStatus() {
		return clearStatus;
	}


	public void setClearStatus(String clearStatus) {
		this.clearStatus = clearStatus;
	}
	public String getUseStatus() {
		return useStatus;
	}


	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}


	public String getExeUnitName() {
		return exeUnitName;
	}


	public void setExeUnitName(String exeUnitName) {
		this.exeUnitName = exeUnitName;
	}


	public String getExeContNo() {
		return exeContNo;
	}


	public void setExeContNo(String exeContNo) {
		this.exeContNo = exeContNo;
	}


	public String getContName() {
		return contName;
	}


	public void setContName(String contName) {
		this.contName = contName;
	}
}

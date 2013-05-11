/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.util.Constants;
import com.hhz.ump.web.res.baseBean.BaseSupTemplate;

/**
 * 供方评级表（ 新单位级别评定）
 * 
 * @author huangjian
 * 
 *         2011-3-16
 */
public class SupplierApplyBill extends BaseSupTemplate {
	private String suitArea;// 适用地区
	private String strengthAss;// 实力评价
	private String supName;// 供方名称
	private String supType;// 供方类别
	private String infoSource;// 信息来源
	private String updatedDate;// 更新时间
	private String webSite;// 官网地址

	private String registerFund;// 注册资金
	private String mainBusiness;// 主营
	private String qualiGrade;// 企业资质等级
	private String honour;// 企业或产品主要荣誉
	private String yeji;// 类似工程业绩

	private String registerFundFileId;// 注册资金-附件
	private String mainBusinessFileId;// 主营-附件
	private String qualiGradeFileId;// 企业资质等级-附件
	private String honourFileId;// 企业或产品主要荣誉-附件
	private String yejiFileId;// 类似工程业绩-附件

	private String examUser;// 考察人员
	private String examResult;// 现场考察结果
	private String examFileId;// 考察附件
	private String examType1;// 已考察(合格)
	private String examType2;// 免考察
	private String examType3;// 已考察(不合格)
	private String examType4;// 未考察
	private String grade1;// 优秀
	private String grade2;// 合格
	private String grade3;// 试用
	private String grade4;// 停用
	private String grade5;// 禁用
	private String grade6;// 合格★
	private String grade7;// 合格★★
	private String grade8;// 待定
	private String grade9;// 试用未考察
	private String supExamResu;// 供应商带来的考察状态
     //供方类型
	private String supplierTypeMapCd;
	////供方类型ID
	private String costProvCondRefId;
	
	//入库试用条件审核
	//体系认证
	private String importLibCond0;
	private String assessSuppilerStatus0;
	
	//行业排名
	private String importLibCond1;	
	private String assessSuppilerStatus1;
	
	//企业业绩
	private String importLibCond2;
	private String assessSuppilerStatus2;
	
	//体系认证
	private String importLibCond3;
	private String assessSuppilerStatus3;
	
	private List<SupplierImportLabCond>	supplierImportLabConds=new ArrayList<SupplierImportLabCond>();
    //符合条件
	private String matchCondition;
	//  //符合条件
	private String noMatchCondition;
	

	//备注
	private String remark;
	
	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getSupType() {
		return supType;
	}

	public void setSupType(String supType) {
		this.supType = supType;
	}

	public String getInfoSource() {
		return infoSource;
	}

	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}

	public String getRegisterFund() {
		return registerFund;
	}

	public void setRegisterFund(String registerFund) {
		this.registerFund = registerFund;
	}

	public String getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public String getQualiGrade() {
		return qualiGrade;
	}

	public void setQualiGrade(String qualiGrade) {
		this.qualiGrade = qualiGrade;
	}

	public String getHonour() {
		return honour;
	}

	public void setHonour(String honour) {
		this.honour = honour;
	}

	public String getYeji() {
		return yeji;
	}

	public void setYeji(String yeji) {
		this.yeji = yeji;
	}

	public String getExamResult() {
		return examResult;
	}

	public void setExamResult(String examResult) {
		this.examResult = examResult;
	}

	public String getGrade1() {
		return grade1;
	}

	public void setGrade1(String grade1) {
		this.grade1 = grade1;
	}

	public String getGrade2() {
		return grade2;
	}

	public void setGrade2(String grade2) {
		this.grade2 = grade2;
	}

	public String getGrade3() {
		return grade3;
	}

	public void setGrade3(String grade3) {
		this.grade3 = grade3;
	}

	public String getGrade4() {
		return grade4;
	}

	public void setGrade4(String grade4) {
		this.grade4 = grade4;
	}

	public String getGrade5() {
		return grade5;
	}

	public void setGrade5(String grade5) {
		this.grade5 = grade5;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return supName;
	}

	public String getRegisterFundFileId() {
		return registerFundFileId;
	}

	public void setRegisterFundFileId(String registerFundFileId) {
		this.registerFundFileId = registerFundFileId;
	}

	public String getMainBusinessFileId() {
		return mainBusinessFileId;
	}

	public void setMainBusinessFileId(String mainBusinessFileId) {
		this.mainBusinessFileId = mainBusinessFileId;
	}

	public String getQualiGradeFileId() {
		return qualiGradeFileId;
	}

	public void setQualiGradeFileId(String qualiGradeFileId) {
		this.qualiGradeFileId = qualiGradeFileId;
	}

	public String getHonourFileId() {
		return honourFileId;
	}

	public void setHonourFileId(String honourFileId) {
		this.honourFileId = honourFileId;
	}

	public String getYejiFileId() {
		return yejiFileId;
	}

	public void setYejiFileId(String yejiFileId) {
		this.yejiFileId = yejiFileId;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public void doImport() {
		SupBasicManager supBasicManager = (SupBasicManager) SpringContextHolder.getBean("supBasicManager");
		String supManaEval = null;
		if (grade1.equals(Constants.TRUE)) {// 优秀
			supManaEval = "1";
		} else if (grade2.equals(Constants.TRUE)) {// 合格
			supManaEval = "4";
		} else if (grade6.equals(Constants.TRUE)) {// 合格★
			supManaEval = "6";
		} else if (grade7.equals(Constants.TRUE)) {// 合格★★
			supManaEval = "7";
		} else if (grade3.equals(Constants.TRUE)) {// 试用
			supManaEval = "2";
		} else if (grade9.equals(Constants.TRUE)) {// 试用未考察
			supManaEval = "8";
		} else if (grade4.equals(Constants.TRUE)) {// 停用
			supManaEval = "3";
		} else if (grade5.equals(Constants.TRUE)) {// 禁用
			supManaEval = "5";
		} else if (grade8.equals(Constants.TRUE)) {// 待定
			supManaEval = "0";
		}
		// 现场考察结果examType1:已考察合格，examType3:已考察不合格，examType2：免考察，examType4：未考察
		String supExamType = null;
		if (examType1.equals(Constants.TRUE)) {
			supExamType = "1";
		} else if (examType3.equals(Constants.TRUE)) {
			supExamType = "2";
		} else if (examType2.equals(Constants.TRUE)) {
			supExamType = "4";
		} else if (examType4.equals(Constants.TRUE)) {
			supExamType = "3";
		}
		// 传参分别为：id,供方评级、现场考察结果、考察人员、
		supBasicManager.updateSupLevel(getSupBasicId(), supManaEval, supExamType, examUser, examResult);
	}

	@Override
	public boolean isAutoImport() {
		return true;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getGrade6() {
		return grade6;
	}

	public void setGrade6(String grade6) {
		this.grade6 = grade6;
	}

	public String getGrade7() {
		return grade7;
	}

	public void setGrade7(String grade7) {
		this.grade7 = grade7;
	}

	public String getExamUser() {
		return examUser;
	}

	public void setExamUser(String examUser) {
		this.examUser = examUser;
	}

	public String getExamFileId() {
		return examFileId;
	}

	public void setExamFileId(String examFileId) {
		this.examFileId = examFileId;
	}

	public String getExamType1() {
		return examType1;
	}

	public void setExamType1(String examType1) {
		this.examType1 = examType1;
	}

	public String getExamType2() {
		return examType2;
	}

	public void setExamType2(String examType2) {
		this.examType2 = examType2;
	}

	public String getExamType3() {
		return examType3;
	}

	public void setExamType3(String examType3) {
		this.examType3 = examType3;
	}

	public String getExamType4() {
		return examType4;
	}

	public void setExamType4(String examType4) {
		this.examType4 = examType4;
	}

	public String getGrade8() {
		return grade8;
	}

	public void setGrade8(String grade8) {
		this.grade8 = grade8;
	}

	public String getGrade9() {
		return grade9;
	}

	public void setGrade9(String grade9) {
		this.grade9 = grade9;
	}

	public String getSupExamResu() {
		return supExamResu;
	}

	public void setSupExamResu(String supExamResu) {
		this.supExamResu = supExamResu;
	}


	
	/**
	 * @return the costProvCondRefId
	 */
	public String getCostProvCondRefId() {
		return costProvCondRefId;
	}

	/**
	 * @param costProvCondRefId the costProvCondRefId to set
	 */
	public void setCostProvCondRefId(String costProvCondRefId) {
		this.costProvCondRefId = costProvCondRefId;
	}

	/**
	 * @return the supplierImportLabConds
	 */
	public List<SupplierImportLabCond> getSupplierImportLabConds() {
		return supplierImportLabConds;
	}

	/**
	 * @param supplierImportLabConds the supplierImportLabConds to set
	 */
	public void setSupplierImportLabConds(List<SupplierImportLabCond> supplierImportLabConds) {
		this.supplierImportLabConds = supplierImportLabConds;
	}

	/**
	 * @return the matchCondition
	 */
	public String getMatchCondition() {
		return matchCondition;
	}

	/**
	 * @param matchCondition the matchCondition to set
	 */
	public void setMatchCondition(String matchCondition) {
		this.matchCondition = matchCondition;
	}

	/**
	 * @return the noMatchCondition
	 */
	public String getNoMatchCondition() {
		return noMatchCondition;
	}

	/**
	 * @param noMatchCondition the noMatchCondition to set
	 */
	public void setNoMatchCondition(String noMatchCondition) {
		this.noMatchCondition = noMatchCondition;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @param supplierTypeMapCd the supplierTypeMapCd to set
	 */
	public void setSupplierTypeMapCd(String supplierTypeMapCd) {
		this.supplierTypeMapCd = supplierTypeMapCd;
	}
	/**
	 * @return the importLibCond0
	 */
	public String getImportLibCond0() {
		return importLibCond0;
	}

	/**
	 * @param importLibCond0 the importLibCond0 to set
	 */
	public void setImportLibCond0(String importLibCond0) {
		this.importLibCond0 = importLibCond0;
	}

	/**
	 * @return the supplierTypeMapCd
	 */
	public String getSupplierTypeMapCd() {
		return supplierTypeMapCd;
	}

	/**
	 * @return the assessSuppilerStatus0
	 */
	public String getAssessSuppilerStatus0() {
		return assessSuppilerStatus0;
	}

	/**
	 * @param assessSuppilerStatus0 the assessSuppilerStatus0 to set
	 */
	public void setAssessSuppilerStatus0(String assessSuppilerStatus0) {
		this.assessSuppilerStatus0 = assessSuppilerStatus0;
	}

	/**
	 * @return the importLibCond1
	 */
	public String getImportLibCond1() {
		return importLibCond1;
	}

	/**
	 * @param importLibCond1 the importLibCond1 to set
	 */
	public void setImportLibCond1(String importLibCond1) {
		this.importLibCond1 = importLibCond1;
	}

	/**
	 * @return the assessSuppilerStatus1
	 */
	public String getAssessSuppilerStatus1() {
		return assessSuppilerStatus1;
	}

	/**
	 * @param assessSuppilerStatus1 the assessSuppilerStatus1 to set
	 */
	public void setAssessSuppilerStatus1(String assessSuppilerStatus1) {
		this.assessSuppilerStatus1 = assessSuppilerStatus1;
	}

	/**
	 * @return the importLibCond2
	 */
	public String getImportLibCond2() {
		return importLibCond2;
	}

	/**
	 * @param importLibCond2 the importLibCond2 to set
	 */
	public void setImportLibCond2(String importLibCond2) {
		this.importLibCond2 = importLibCond2;
	}

	/**
	 * @return the assessSuppilerStatus2
	 */
	public String getAssessSuppilerStatus2() {
		return assessSuppilerStatus2;
	}

	/**
	 * @param assessSuppilerStatus2 the assessSuppilerStatus2 to set
	 */
	public void setAssessSuppilerStatus2(String assessSuppilerStatus2) {
		this.assessSuppilerStatus2 = assessSuppilerStatus2;
	}

	/**
	 * @return the importLibCond3
	 */
	public String getImportLibCond3() {
		return importLibCond3;
	}

	/**
	 * @param importLibCond3 the importLibCond3 to set
	 */
	public void setImportLibCond3(String importLibCond3) {
		this.importLibCond3 = importLibCond3;
	}

	/**
	 * @return the assessSuppilerStatus3
	 */
	public String getAssessSuppilerStatus3() {
		return assessSuppilerStatus3;
	}

	/**
	 * @param assessSuppilerStatus3 the assessSuppilerStatus3 to set
	 */
	public void setAssessSuppilerStatus3(String assessSuppilerStatus3) {
		this.assessSuppilerStatus3 = assessSuppilerStatus3;
	}

	public String getSuitArea() {
		return suitArea;
	}

	public void setSuitArea(String suitArea) {
		this.suitArea = suitArea;
	}

	public String getStrengthAss() {
		return strengthAss;
	}

	public void setStrengthAss(String strengthAss) {
		this.strengthAss = strengthAss;
	}

}

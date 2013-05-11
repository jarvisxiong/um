/**
 * 
 */
package com.hhz.ump.web.res.bean;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.sup.SupBasicManager;
import com.hhz.ump.util.Constants;
import com.hhz.ump.web.res.baseBean.BaseSupTemplate;


/**供方评级表（原供方级别调整）
 * @author huangjian
 *
 * 2011-3-16
 */
public class SupplierAdjustApplyBill  extends BaseSupTemplate {
	private String supName;//供方名称
	private String supType;//供方类别
	private String oriGrade;//原供方级别
	private String infoSource;//信息来源
	private String updatedDate;//更新时间

	private String registerFund;//注册资金(万)
	private String mainBusiness;//主营
	private String qualiGrade;//企业资质等级
	private String honour;//企业或产品主要荣誉
	private String yeji;//类似工程业绩
	private String supEvaluate;//履约评估
	private String baolongYeji;//宝龙业绩
	private String examResult;//现场考察结果
	
	private String registerFundFileId;//注册资金-附件
	private String mainBusinessFileId;//主营-附件
	private String qualiGradeFileId;//企业资质等级-附件
	private String honourFileId;//企业或产品主要荣誉-附件
	private String yejiFileId;//类似工程业绩-附件
	private String evalFileId;//履约评估-附件
	private String baolongYejiFileId;//宝龙业绩-附件
	private String examResultFileId;//现场考察结果-附件
	private String oriGradeFileId;//原供方级别-附件
	
	private String grade1;//优秀
	private String grade2;//合格
	private String grade3;//试用
	private String grade4;//停用
	private String grade5;// 禁用
	private String grade6;// 合格★
	private String grade7;// 合格★★
	private String grade8;// 待定
	private String grade9;//试用未考察
	
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
	public String getOriGrade() {
		return oriGrade;
	}
	public void setOriGrade(String oriGrade) {
		this.oriGrade = oriGrade;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getSupEvaluate() {
		return supEvaluate;
	}
	public void setSupEvaluate(String supEvaluate) {
		this.supEvaluate = supEvaluate;
	}
	public String getBaolongYeji() {
		return baolongYeji;
	}
	public void setBaolongYeji(String supChecView) {
		this.baolongYeji = supChecView;
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
	public String getEvalFileId() {
		return evalFileId;
	}
	public void setEvalFileId(String evalFileId) {
		this.evalFileId = evalFileId;
	}
	public String getBaolongYejiFileId() {
		return baolongYejiFileId;
	}
	public void setBaolongYejiFileId(String cooperatedFileId) {
		this.baolongYejiFileId = cooperatedFileId;
	}
	public String getExamResultFileId() {
		return examResultFileId;
	}
	public void setExamResultFileId(String examResultFileId) {
		this.examResultFileId = examResultFileId;
	}
	public String getOriGradeFileId() {
		return oriGradeFileId;
	}
	public void setOriGradeFileId(String oriGradeFileId) {
		this.oriGradeFileId = oriGradeFileId;
	}
	@Override
	public void doImport() {
		SupBasicManager supBasicManager = (SupBasicManager) SpringContextHolder.getBean("supBasicManager");
		String supManaEval = null;
		if (grade1.equals(Constants.TRUE)) {// 优秀
			supManaEval = "1";
		}else if (grade2.equals(Constants.TRUE)) {// 合格
			supManaEval = "4";
		}else if (grade6.equals(Constants.TRUE)) {// 合格★
			supManaEval = "6";
		}else if (grade7.equals(Constants.TRUE)) {// 合格★★
			supManaEval = "7";
		} else if (grade3.equals(Constants.TRUE)) {// 试用
			supManaEval = "2";
		} else if(grade9.equals(Constants.TRUE)){//试用未考察
			supManaEval="8";
		}else if (grade4.equals(Constants.TRUE)) {// 停用
			supManaEval = "3";
		} else if (grade5.equals(Constants.TRUE)) {// 禁用
			supManaEval = "5";
		} else if (grade8.equals(Constants.TRUE)) {// 待定
			supManaEval = "0";
		}
		supBasicManager.updateSupLevel(getSupBasicId(), supManaEval);
	}
	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
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
}

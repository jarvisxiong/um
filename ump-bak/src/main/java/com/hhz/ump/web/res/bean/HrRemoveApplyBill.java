/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**人事变动表
 * @author huangj
 * 2010-12-21
 */
public class HrRemoveApplyBill  extends BaseTemplate {
	private String applyOfficeName;//申请公司
	private String userName;//姓名
	private String positionBefore;//职位调整前
	private String positionAfter;//职位调整后
	private String levelBefore;//职级调整前
	private String levelAfter;//职级调整后
	private String salaryBefore;//工资调整前
	private String salaryAfter;//工资调整前
	private String enterDate;//入职日期
	private String effectDate;//生效日期
	private String remark;//事由
	//**********集团与子公司***********//
	private String gUp;//晋升晋级
	private String gDown;//降职降级
	private String gFlat;//岗位平调
	private String gReturn;//工作轮岗
	private String gTemp;//临时借调
	private String gOther;//其它
	private String gOtherContent;//其它内容
	//**********子公司与子公司***********//
	private String sUp;//晋升晋级
	private String sDown;//降职降级
	private String sFlat;//岗位平调
	private String sReturn;//工作轮岗
	private String sTemp;//临时借调
	private String sOther;//其它
	private String sOtherContent;//其它内容
	public String getApplyOfficeName() {
		return applyOfficeName;
	}
	public void setApplyOfficeName(String applyOfficeName) {
		this.applyOfficeName = applyOfficeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPositionBefore() {
		return positionBefore;
	}
	public void setPositionBefore(String positionBefore) {
		this.positionBefore = positionBefore;
	}
	public String getPositionAfter() {
		return positionAfter;
	}
	public void setPositionAfter(String positionAfter) {
		this.positionAfter = positionAfter;
	}
	public String getLevelBefore() {
		return levelBefore;
	}
	public void setLevelBefore(String levelBefore) {
		this.levelBefore = levelBefore;
	}
	public String getLevelAfter() {
		return levelAfter;
	}
	public void setLevelAfter(String levelAfter) {
		this.levelAfter = levelAfter;
	}
	public String getSalaryBefore() {
		return salaryBefore;
	}
	public void setSalaryBefore(String salaryBefore) {
		this.salaryBefore = salaryBefore;
	}
	public String getEnterDate() {
		return enterDate;
	}
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getgUp() {
		return gUp;
	}
	public void setgUp(String gUp) {
		this.gUp = gUp;
	}
	public String getgDown() {
		return gDown;
	}
	public void setgDown(String gDown) {
		this.gDown = gDown;
	}
	public String getgFlat() {
		return gFlat;
	}
	public void setgFlat(String gFlat) {
		this.gFlat = gFlat;
	}
	public String getgReturn() {
		return gReturn;
	}
	public void setgReturn(String gReturn) {
		this.gReturn = gReturn;
	}
	public String getgTemp() {
		return gTemp;
	}
	public void setgTemp(String gTemp) {
		this.gTemp = gTemp;
	}
	public String getgOther() {
		return gOther;
	}
	public void setgOther(String gOther) {
		this.gOther = gOther;
	}
	public String getgOtherContent() {
		return gOtherContent;
	}
	public void setgOtherContent(String gOtherContent) {
		this.gOtherContent = gOtherContent;
	}
	public String getsUp() {
		return sUp;
	}
	public void setsUp(String sUp) {
		this.sUp = sUp;
	}
	public String getsDown() {
		return sDown;
	}
	public void setsDown(String sDown) {
		this.sDown = sDown;
	}
	public String getsFlat() {
		return sFlat;
	}
	public void setsFlat(String sFlat) {
		this.sFlat = sFlat;
	}
	public String getsReturn() {
		return sReturn;
	}
	public void setsReturn(String sReturn) {
		this.sReturn = sReturn;
	}
	public String getsTemp() {
		return sTemp;
	}
	public void setsTemp(String sTemp) {
		this.sTemp = sTemp;
	}
	public String getsOther() {
		return sOther;
	}
	public void setsOther(String sOther) {
		this.sOther = sOther;
	}
	public String getsOtherContent() {
		return sOtherContent;
	}
	public void setsOtherContent(String sOtherContent) {
		this.sOtherContent = sOtherContent;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return applyOfficeName;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return userName;
	}
	public String getSalaryAfter() {
		return salaryAfter;
	}
	public void setSalaryAfter(String salaryAfter) {
		this.salaryAfter = salaryAfter;
	}
}

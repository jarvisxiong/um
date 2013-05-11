package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//土地标准审批表
public class LandStandardAudit extends BaseTemplate{

	private String name;
	private String useArea;
	private String buildScale;
	private String possiblyAnalyse;
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUseArea() {
		return useArea;
	}

	public void setUseArea(String useArea) {
		this.useArea = useArea;
	}

	public String getBuildScale() {
		return buildScale;
	}

	public void setBuildScale(String buildScale) {
		this.buildScale = buildScale;
	}

	public String getPossiblyAnalyse() {
		return possiblyAnalyse;
	}

	public void setPossiblyAnalyse(String possiblyAnalyse) {
		this.possiblyAnalyse = possiblyAnalyse;
	}

}

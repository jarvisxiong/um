package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * <p>项目交底信息 </p>
 * @author huangjian
 * @create 2011-9-14
 */
public class ProjectBackInfo extends BaseTemplate {

	private String projectName;// 项目名称
	private String projectPosition;// 项目地理位置
	private String totalArea;// 总用地面积
	private String volumeRate;// 容积率
	private String placeNature1;// 用地性质：居住
	private String placeNature2;// 用地性质：商业
	private String placeNature3;// 用地性质：办公
	private String buildingDensity;// 建筑密度
	private String otherRequire;// 建筑使用性质或功能要求
	private String buildingHeight;// 建筑高度
	private String otherCondition;// 其他规划限制条件
	private String siteConf;// 地段要求：符合
	private String siteUnconf;// 地段要求：不符合
	private String siteRemark;// 地段要求：不符合 说明

	private String busConf;// 商业要求：符合
	private String busUnconf;// 商业要求：不符合
	private String busRemark;// 商业要求：不符合
	private String finConf;// 财务要求：符合
	private String finUnconf;// 财务要求：不符合
	private String finRemark;// 财务要求：不符合
	private String otherRemark;// 其他情况

	private String mapFileId;// 城市地图和地块位置标示
	private String landFileId;// 项目用地地形和红线图（比例或电子文件）
	private String otherInfoFileId;// 有关规划文件和资料（列明文件名称）
	public String getSiteRemark() {
		return siteRemark;
	}

	public void setSiteRemark(String siteRemark) {
		this.siteRemark = siteRemark;
	}

	public String getBusRemark() {
		return busRemark;
	}

	public void setBusRemark(String busRemark) {
		this.busRemark = busRemark;
	}

	public String getFinRemark() {
		return finRemark;
	}

	public void setFinRemark(String finRemark) {
		this.finRemark = finRemark;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPosition() {
		return projectPosition;
	}

	public void setProjectPosition(String projectPosition) {
		this.projectPosition = projectPosition;
	}

	public String getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(String totalArea) {
		this.totalArea = totalArea;
	}

	public String getVolumeRate() {
		return volumeRate;
	}

	public void setVolumeRate(String volumeRate) {
		this.volumeRate = volumeRate;
	}

	public String getPlaceNature1() {
		return placeNature1;
	}

	public void setPlaceNature1(String placeNature1) {
		this.placeNature1 = placeNature1;
	}

	public String getPlaceNature2() {
		return placeNature2;
	}

	public void setPlaceNature2(String placeNature2) {
		this.placeNature2 = placeNature2;
	}

	public String getPlaceNature3() {
		return placeNature3;
	}

	public void setPlaceNature3(String placeNature3) {
		this.placeNature3 = placeNature3;
	}

	public String getBuildingDensity() {
		return buildingDensity;
	}

	public void setBuildingDensity(String buildingDensity) {
		this.buildingDensity = buildingDensity;
	}

	public String getOtherRequire() {
		return otherRequire;
	}

	public void setOtherRequire(String otherRequire) {
		this.otherRequire = otherRequire;
	}

	public String getBuildingHeight() {
		return buildingHeight;
	}

	public void setBuildingHeight(String buildingHeight) {
		this.buildingHeight = buildingHeight;
	}

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
	}

	public String getSiteConf() {
		return siteConf;
	}

	public void setSiteConf(String siteConf) {
		this.siteConf = siteConf;
	}

	public String getSiteUnconf() {
		return siteUnconf;
	}

	public void setSiteUnconf(String siteUnconf) {
		this.siteUnconf = siteUnconf;
	}

	public String getBusConf() {
		return busConf;
	}

	public void setBusConf(String busConf) {
		this.busConf = busConf;
	}

	public String getBusUnconf() {
		return busUnconf;
	}

	public void setBusUnconf(String busUnconf) {
		this.busUnconf = busUnconf;
	}

	public String getFinConf() {
		return finConf;
	}

	public void setFinConf(String finConf) {
		this.finConf = finConf;
	}

	public String getFinUnconf() {
		return finUnconf;
	}

	public void setFinUnconf(String finUnconf) {
		this.finUnconf = finUnconf;
	}

	public String getOtherRemark() {
		return otherRemark;
	}

	public void setOtherRemark(String otherRemark) {
		this.otherRemark = otherRemark;
	}

	public String getMapFileId() {
		return mapFileId;
	}

	public void setMapFileId(String mapFileId) {
		this.mapFileId = mapFileId;
	}

	public String getLandFileId() {
		return landFileId;
	}

	public void setLandFileId(String landFileId) {
		this.landFileId = landFileId;
	}

	public String getOtherInfoFileId() {
		return otherInfoFileId;
	}

	public void setOtherInfoFileId(String otherInfoFileId) {
		this.otherInfoFileId = otherInfoFileId;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return projectName;
	}

}

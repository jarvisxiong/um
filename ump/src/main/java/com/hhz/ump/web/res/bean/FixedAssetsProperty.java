package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 固定资产属性
 * 
 * @author liuzhihui 2012-05-15
 * 
 */
public class FixedAssetsProperty extends BaseTemplate implements IAutoImport {

	private String projectName;         // 商业公司名称
	private String projectCd;           // 商业公司CD
	private String assmName;            // 资产名称
	private String code;                // 资产编码
	private String assmModelId;         // 资产型号Id
	private String assmModelName;       // 资产型号名称
	private String assmCode;            // 资产型号编码
	private String useDepartName;       // 使用部门名称
	private String useDepartCd;         // 使用部门CD
	private String useStatus1;           // 使用情况1
	private String useStatus2;           // 使用情况2
	private String useStatus3;           // 使用情况3
	private String useStatus4;           // 使用情况4
	private String useStatus5;           // 使用情况5
	private String storageSites1;       // 保存地点1
	private String storageSites2;       // 保存地点2
	private String storageSites3;       // 保存地点3
	private String addWay1;             // 增加方式1
	private String addWay2;             // 增加方式2
	private String addWay3;             // 增加方式3
	private String addWay4;             // 增加方式4
	private String addWay5;             // 增加方式5
	private String addWay6;             // 增加方式6
	private String addWay7;             // 增加方式7
	private String keeperName;          // 保管人员(PD)
	private String keeperCd;            // 保管人员CD
	private String keeperName2;         // 保管人员(非PD)
	private String useDate;             // 使用时间
	private String srcValue;            // 原值
	private String remainVal;           // 净值
	private String netValue;            // 残值
	private String depreYeadNum;        // 折旧年限
	private String depreWay;            // 折旧方式
	private String currHasNum;          // 当前配置数
	private String currStanNum;         // 标准配置数
	
	private String computerType;         // 电脑类
	private String selectThridModelId;  // 选中的3级节点设备ID

	@Override
	public void doImport() {

	}

	@Override
	public boolean isAutoImport() {
		return false;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getAssmName() {
		return assmName;
	}

	public void setAssmName(String assmName) {
		this.assmName = assmName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAssmModelId() {
		return assmModelId;
	}

	public void setAssmModelId(String assmModelId) {
		this.assmModelId = assmModelId;
	}

	public String getAssmModelName() {
		return assmModelName;
	}

	public void setAssmModelName(String assmModelName) {
		this.assmModelName = assmModelName;
	}

	public String getAssmCode() {
		return assmCode;
	}

	public void setAssmCode(String assmCode) {
		this.assmCode = assmCode;
	}

	public String getUseDepartName() {
		return useDepartName;
	}

	public void setUseDepartName(String useDepartName) {
		this.useDepartName = useDepartName;
	}

	public String getUseDepartCd() {
		return useDepartCd;
	}

	public void setUseDepartCd(String useDepartCd) {
		this.useDepartCd = useDepartCd;
	}

	public String getUseStatus1() {
		return useStatus1;
	}

	public void setUseStatus1(String useStatus1) {
		this.useStatus1 = useStatus1;
	}

	public String getUseStatus2() {
		return useStatus2;
	}

	public void setUseStatus2(String useStatus2) {
		this.useStatus2 = useStatus2;
	}

	public String getUseStatus3() {
		return useStatus3;
	}

	public void setUseStatus3(String useStatus3) {
		this.useStatus3 = useStatus3;
	}

	public String getUseStatus4() {
		return useStatus4;
	}

	public void setUseStatus4(String useStatus4) {
		this.useStatus4 = useStatus4;
	}

	public String getUseStatus5() {
		return useStatus5;
	}

	public void setUseStatus5(String useStatus5) {
		this.useStatus5 = useStatus5;
	}

	public String getStorageSites1() {
		return storageSites1;
	}

	public void setStorageSites1(String storageSites1) {
		this.storageSites1 = storageSites1;
	}

	public String getStorageSites2() {
		return storageSites2;
	}

	public void setStorageSites2(String storageSites2) {
		this.storageSites2 = storageSites2;
	}

	public String getStorageSites3() {
		return storageSites3;
	}

	public void setStorageSites3(String storageSites3) {
		this.storageSites3 = storageSites3;
	}

	public String getAddWay1() {
		return addWay1;
	}

	public void setAddWay1(String addWay1) {
		this.addWay1 = addWay1;
	}

	public String getAddWay2() {
		return addWay2;
	}

	public void setAddWay2(String addWay2) {
		this.addWay2 = addWay2;
	}

	public String getAddWay3() {
		return addWay3;
	}

	public void setAddWay3(String addWay3) {
		this.addWay3 = addWay3;
	}

	public String getAddWay4() {
		return addWay4;
	}

	public void setAddWay4(String addWay4) {
		this.addWay4 = addWay4;
	}

	public String getAddWay5() {
		return addWay5;
	}

	public void setAddWay5(String addWay5) {
		this.addWay5 = addWay5;
	}

	public String getAddWay6() {
		return addWay6;
	}

	public void setAddWay6(String addWay6) {
		this.addWay6 = addWay6;
	}

	public String getKeeperName() {
		return keeperName;
	}

	public void setKeeperName(String keeperName) {
		this.keeperName = keeperName;
	}

	public String getKeeperCd() {
		return keeperCd;
	}

	public void setKeeperCd(String keeperCd) {
		this.keeperCd = keeperCd;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public String getSrcValue() {
		return srcValue;
	}

	public void setSrcValue(String srcValue) {
		this.srcValue = srcValue;
	}

	public String getNetValue() {
		return netValue;
	}

	public void setNetValue(String netValue) {
		this.netValue = netValue;
	}

	public String getRemainVal() {
		return remainVal;
	}

	public void setRemainVal(String remainVal) {
		this.remainVal = remainVal;
	}

	public String getDepreYeadNum() {
		return depreYeadNum;
	}

	public void setDepreYeadNum(String depreYeadNum) {
		this.depreYeadNum = depreYeadNum;
	}

	public String getDepreWay() {
		return depreWay;
	}

	public void setDepreWay(String depreWay) {
		this.depreWay = depreWay;
	}

	public String getCurrHasNum() {
		return currHasNum;
	}

	public void setCurrHasNum(String currHasNum) {
		this.currHasNum = currHasNum;
	}

	public String getCurrStanNum() {
		return currStanNum;
	}

	public void setCurrStanNum(String currStanNum) {
		this.currStanNum = currStanNum;
	}

	public String getComputerType() {
		return computerType;
	}

	public void setComputerType(String computerType) {
		this.computerType = computerType;
	}

	public String getKeeperName2() {
		return keeperName2;
	}

	public void setKeeperName2(String keeperName2) {
		this.keeperName2 = keeperName2;
	}

	public String getAddWay7() {
		return addWay7;
	}

	public void setAddWay7(String addWay7) {
		this.addWay7 = addWay7;
	}

	public String getSelectThridModelId() {
		return selectThridModelId;
	}

	public void setSelectThridModelId(String selectThridModelId) {
		this.selectThridModelId = selectThridModelId;
	}
	
}

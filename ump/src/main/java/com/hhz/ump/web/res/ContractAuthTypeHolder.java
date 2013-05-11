package com.hhz.ump.web.res;

import java.util.HashMap;
import java.util.Map;

public class ContractAuthTypeHolder {

	private static Map<String,ContractAuthTypeHolder> authTypeMap ;
	static{
		authTypeMap = new HashMap<String,ContractAuthTypeHolder>();
		
		ContractAuthTypeHolder authType1 = new ContractAuthTypeHolder("ZB_ZBWJ_01","HT_GCCB_01","HT_GCCB_02");
		ContractAuthTypeHolder authType2 = new ContractAuthTypeHolder("ZB_ZBWJ_02","HT_JL_01","HT_JL_02");
		ContractAuthTypeHolder authType3 = new ContractAuthTypeHolder("ZB_ZBWJ_03","HT_CLCB_01","HT_CLCB_02");
		ContractAuthTypeHolder authType4 = new ContractAuthTypeHolder("ZB_ZBWJ_04","HT_CHGS_01","HT_CHGS_02");
		ContractAuthTypeHolder authType5 = new ContractAuthTypeHolder("ZB_ZBWJ_05","HT_XSDL_01","HT_XSDL_02");
		ContractAuthTypeHolder authType6 = new ContractAuthTypeHolder("ZB_ZBWJ_06","HT_SDJG_01","HT_SDJG_02");
		
		authTypeMap.put(authType1.getZbAuthTypeCd(), authType1); //工程承包单位
		authTypeMap.put(authType2.getZbAuthTypeCd(), authType2); //监理单位
		authTypeMap.put(authType3.getZbAuthTypeCd(), authType3); //材料设备供应商
		authTypeMap.put(authType4.getZbAuthTypeCd(), authType4); // 营销策划公司
		authTypeMap.put(authType5.getZbAuthTypeCd(), authType5); // 销售代理公司
		authTypeMap.put(authType6.getZbAuthTypeCd(), authType6); // 市调机构
	}
	// 招标类型
	private String zbAuthTypeCd;
	// 转入的标准合同类型
	private String stdContractAuthTypeCd;
	// 转入的非标准合同类型
	private String unStdContractAuthTypeCd;
	
	public String getZbAuthTypeCd() {
		return zbAuthTypeCd;
	}
	public void setZbAuthTypeCd(String zbAuthTypeCd) {
		this.zbAuthTypeCd = zbAuthTypeCd;
	}
	public String getStdContractAuthTypeCd() {
		return stdContractAuthTypeCd;
	}
	public void setStdContractAuthTypeCd(String stdContractAuthTypeCd) {
		this.stdContractAuthTypeCd = stdContractAuthTypeCd;
	}
	public String getUnStdContractAuthTypeCd() {
		return unStdContractAuthTypeCd;
	}
	public void setUnStdContractAuthTypeCd(String unStdContractAuthTypeCd) {
		this.unStdContractAuthTypeCd = unStdContractAuthTypeCd;
	}
	private ContractAuthTypeHolder(String zbAuthTypeCd,
			String stdContractAuthTypeCd, String unStdContractAuthTypeCd) {
		super();
		this.zbAuthTypeCd = zbAuthTypeCd;
		this.stdContractAuthTypeCd = stdContractAuthTypeCd;
		this.unStdContractAuthTypeCd = unStdContractAuthTypeCd;
	}
	
	
	public static String getStdContractAuthTypeCd(String zbAuthTypeCd){
		ContractAuthTypeHolder holder = ContractAuthTypeHolder.authTypeMap.get(zbAuthTypeCd);
		return null==holder?null:holder.getStdContractAuthTypeCd();
	}
	public static String getUnStdContractAuthTypeCd(String zbAuthTypeCd){
		ContractAuthTypeHolder holder = ContractAuthTypeHolder.authTypeMap.get(zbAuthTypeCd);
		return null==holder?null:holder.getUnStdContractAuthTypeCd();
	}
	
	
	
}

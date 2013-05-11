/**
 * 
 */
package com.hhz.ump.web.res.bean;


/**
 * <p>
 * 设备型号
 * </p>
 * 
 * @author liuzhihui
 * @version 1.00 2012-03-14
 */
public class StrategyMatePrice {
	
	//战略内外：１－内　２－外
	private String strageTypeCd;
	
	//行类型: 1-标题  0-数值
	//若rowtype=1: 显示字段名称/自定义字段名称
	private String headFlg;
	
	//材料或设备名称
	private String groupTypeName;
	
	//设备名称
	private String materialName;

	//材料设备类型
	private String costMateModuleId;
	
	//材料设备
	private String costMateId;
	
	// 型号ID
	private String costMatePriceId;

	//关键字1,2,3
	private String groupName;
	private String groupName2;
	private String groupName3;
	
	// 规格
	private String specName;
	
	// 型号
	private String modelName;
	
	// 总价
	private String totalPrice;
	
	// 原价
	private String originalValue;
	
	// 价格(这个价格可能根据’时价‘而改变)
	private String price;
	
	// 采购单价
	private String cgPrice; 
	
	// 时价(暂时针对电缆类)即:铜价
	private String currentPrices;
	
	// 价格计算方式(1-定量 2-公式 3-浮动)
	private String calcTypeCd;
	
	// 数量
	private String buyNum;
	
	// f01-f15(存放值)
	private String f01;
	private String f02;
	private String f03;
	private String f04;
	private String f05;
	private String f06;
	private String f07;
	private String f08;
	private String f09;
	private String f10;
	private String f11;
	private String f12;
	private String f13;
	private String f14;
	private String f15;

    private String f16;
    private String f17;
    private String f18;
    private String f19;
    private String f20;
    private String f21;
    private String f22;
    private String f23;
    private String f24;
    private String f25;
    private String f26;
    private String f27;
    private String f28;
    private String f29;
    private String f30;

    private String memoDesc;

    //存放列头名称
	private String h01;
	private String h02;
	private String h03;
	private String h04;
	private String h05;
	private String h06;
	private String h07;
	private String h08;
	private String h09;
	private String h10;
	private String h11;
	private String h12;
	private String h13;
	private String h14;
	private String h15;
    private String h16;
    private String h17;
    private String h18;
    private String h19;
    private String h20;
    private String h21;
    private String h22;
    private String h23;
    private String h24;
    private String h25;
    private String h26;
    private String h27;
    private String h28;
    private String h29;
    private String h30;

    
    //是否扩展字段
	private String e01;
	private String e02;
	private String e03;
	private String e04;
	private String e05;
	private String e06;
	private String e07;
	private String e08;
	private String e09;
	private String e10;
	private String e11;
	private String e12;
	private String e13;
	private String e14;
	private String e15;
    private String e16;
    private String e17;
    private String e18;
    private String e19;
    private String e20;
    private String e21;
    private String e22;
    private String e23;
    private String e24;
    private String e25;
    private String e26;
    private String e27;
    private String e28;
    private String e29;
    private String e30;
    

    //是否扩展价格
	private String p01;
	private String p02;
	private String p03;
	private String p04;
	private String p05;
	private String p06;
	private String p07;
	private String p08;
	private String p09;
	private String p10;
	private String p11;
	private String p12;
	private String p13;
	private String p14;
	private String p15;
    private String p16;
    private String p17;
    private String p18;
    private String p19;
    private String p20;
    private String p21;
    private String p22;
    private String p23;
    private String p24;
    private String p25;
    private String p26;
    private String p27;
    private String p28;
    private String p29;
    private String p30;

	public String getCostMatePriceId() {
		return costMatePriceId;
	}

	public void setCostMatePriceId(String costMatePriceId) {
		this.costMatePriceId = costMatePriceId;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCalcTypeCd() {
		return calcTypeCd;
	}

	public void setCalcTypeCd(String calcTypeCd) {
		this.calcTypeCd = calcTypeCd;
	}
 
	

	public String getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}

	public String getF01() {
		return f01;
	}

	public void setF01(String f01) {
		this.f01 = f01;
	}

	public String getF02() {
		return f02;
	}

	public void setF02(String f02) {
		this.f02 = f02;
	}

	public String getF03() {
		return f03;
	}

	public void setF03(String f03) {
		this.f03 = f03;
	}

	public String getF04() {
		return f04;
	}

	public void setF04(String f04) {
		this.f04 = f04;
	}

	public String getF05() {
		return f05;
	}

	public void setF05(String f05) {
		this.f05 = f05;
	}

	public String getF06() {
		return f06;
	}

	public void setF06(String f06) {
		this.f06 = f06;
	}

	public String getF07() {
		return f07;
	}

	public void setF07(String f07) {
		this.f07 = f07;
	}

	public String getF08() {
		return f08;
	}

	public void setF08(String f08) {
		this.f08 = f08;
	}

	public String getF09() {
		return f09;
	}

	public void setF09(String f09) {
		this.f09 = f09;
	}

	public String getF10() {
		return f10;
	}

	public void setF10(String f10) {
		this.f10 = f10;
	}

	public String getF11() {
		return f11;
	}

	public void setF11(String f11) {
		this.f11 = f11;
	}

	public String getF12() {
		return f12;
	}

	public void setF12(String f12) {
		this.f12 = f12;
	}

	public String getF13() {
		return f13;
	}

	public void setF13(String f13) {
		this.f13 = f13;
	}

	public String getF14() {
		return f14;
	}

	public void setF14(String f14) {
		this.f14 = f14;
	}

	public String getF15() {
		return f15;
	}

	public void setF15(String f15) {
		this.f15 = f15;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCurrentPrices() {
		return currentPrices;
	}

	public void setCurrentPrices(String currentPrices) {
		this.currentPrices = currentPrices;
	}

	public String getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}

	public String getCgPrice() {
		return cgPrice;
	}

	public void setCgPrice(String cgPrice) {
		this.cgPrice = cgPrice;
	}

	public String getCostMateModuleId() {
		return costMateModuleId;
	}

	public void setCostMateModuleId(String costMateModuleId) {
		this.costMateModuleId = costMateModuleId;
	}

	public String getCostMateId() {
		return costMateId;
	}

	public void setCostMateId(String costMateId) {
		this.costMateId = costMateId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName2() {
		return groupName2;
	}

	public void setGroupName2(String groupName2) {
		this.groupName2 = groupName2;
	}

	public String getGroupName3() {
		return groupName3;
	}

	public void setGroupName3(String groupName3) {
		this.groupName3 = groupName3;
	}

	public String getF16() {
		return f16;
	}

	public void setF16(String f16) {
		this.f16 = f16;
	}

	public String getF17() {
		return f17;
	}

	public void setF17(String f17) {
		this.f17 = f17;
	}

	public String getF18() {
		return f18;
	}

	public void setF18(String f18) {
		this.f18 = f18;
	}

	public String getF19() {
		return f19;
	}

	public void setF19(String f19) {
		this.f19 = f19;
	}

	public String getF20() {
		return f20;
	}

	public void setF20(String f20) {
		this.f20 = f20;
	}

	public String getF21() {
		return f21;
	}

	public void setF21(String f21) {
		this.f21 = f21;
	}

	public String getF22() {
		return f22;
	}

	public void setF22(String f22) {
		this.f22 = f22;
	}

	public String getF23() {
		return f23;
	}

	public void setF23(String f23) {
		this.f23 = f23;
	}

	public String getF24() {
		return f24;
	}

	public void setF24(String f24) {
		this.f24 = f24;
	}

	public String getF25() {
		return f25;
	}

	public void setF25(String f25) {
		this.f25 = f25;
	}

	public String getF26() {
		return f26;
	}

	public void setF26(String f26) {
		this.f26 = f26;
	}

	public String getF27() {
		return f27;
	}

	public void setF27(String f27) {
		this.f27 = f27;
	}

	public String getF28() {
		return f28;
	}

	public void setF28(String f28) {
		this.f28 = f28;
	}

	public String getF29() {
		return f29;
	}

	public void setF29(String f29) {
		this.f29 = f29;
	}

	public String getF30() {
		return f30;
	}

	public void setF30(String f30) {
		this.f30 = f30;
	}

	public String getMemoDesc() {
		return memoDesc;
	}

	public void setMemoDesc(String memoDesc) {
		this.memoDesc = memoDesc;
	}
 
	public String getHeadFlg() {
		return headFlg;
	}

	public void setHeadFlg(String headFlg) {
		this.headFlg = headFlg;
	}

	public String getH01() {
		return h01;
	}

	public void setH01(String h01) {
		this.h01 = h01;
	}

	public String getH02() {
		return h02;
	}

	public void setH02(String h02) {
		this.h02 = h02;
	}

	public String getH03() {
		return h03;
	}

	public void setH03(String h03) {
		this.h03 = h03;
	}

	public String getH04() {
		return h04;
	}

	public void setH04(String h04) {
		this.h04 = h04;
	}

	public String getH05() {
		return h05;
	}

	public void setH05(String h05) {
		this.h05 = h05;
	}

	public String getH06() {
		return h06;
	}

	public void setH06(String h06) {
		this.h06 = h06;
	}

	public String getH07() {
		return h07;
	}

	public void setH07(String h07) {
		this.h07 = h07;
	}

	public String getH08() {
		return h08;
	}

	public void setH08(String h08) {
		this.h08 = h08;
	}

	public String getH09() {
		return h09;
	}

	public void setH09(String h09) {
		this.h09 = h09;
	}

	public String getH10() {
		return h10;
	}

	public void setH10(String h10) {
		this.h10 = h10;
	}

	public String getH11() {
		return h11;
	}

	public void setH11(String h11) {
		this.h11 = h11;
	}

	public String getH12() {
		return h12;
	}

	public void setH12(String h12) {
		this.h12 = h12;
	}

	public String getH13() {
		return h13;
	}

	public void setH13(String h13) {
		this.h13 = h13;
	}

	public String getH14() {
		return h14;
	}

	public void setH14(String h14) {
		this.h14 = h14;
	}

	public String getH15() {
		return h15;
	}

	public void setH15(String h15) {
		this.h15 = h15;
	}

	public String getH16() {
		return h16;
	}

	public void setH16(String h16) {
		this.h16 = h16;
	}

	public String getH17() {
		return h17;
	}

	public void setH17(String h17) {
		this.h17 = h17;
	}

	public String getH18() {
		return h18;
	}

	public void setH18(String h18) {
		this.h18 = h18;
	}

	public String getH19() {
		return h19;
	}

	public void setH19(String h19) {
		this.h19 = h19;
	}

	public String getH20() {
		return h20;
	}

	public void setH20(String h20) {
		this.h20 = h20;
	}

	public String getH21() {
		return h21;
	}

	public void setH21(String h21) {
		this.h21 = h21;
	}

	public String getH22() {
		return h22;
	}

	public void setH22(String h22) {
		this.h22 = h22;
	}

	public String getH23() {
		return h23;
	}

	public void setH23(String h23) {
		this.h23 = h23;
	}

	public String getH24() {
		return h24;
	}

	public void setH24(String h24) {
		this.h24 = h24;
	}

	public String getH25() {
		return h25;
	}

	public void setH25(String h25) {
		this.h25 = h25;
	}

	public String getH26() {
		return h26;
	}

	public void setH26(String h26) {
		this.h26 = h26;
	}

	public String getH27() {
		return h27;
	}

	public void setH27(String h27) {
		this.h27 = h27;
	}

	public String getH28() {
		return h28;
	}

	public void setH28(String h28) {
		this.h28 = h28;
	}

	public String getH29() {
		return h29;
	}

	public void setH29(String h29) {
		this.h29 = h29;
	}

	public String getH30() {
		return h30;
	}

	public void setH30(String h30) {
		this.h30 = h30;
	}

	public String getE01() {
		return e01;
	}

	public void setE01(String e01) {
		this.e01 = e01;
	}

	public String getE02() {
		return e02;
	}

	public void setE02(String e02) {
		this.e02 = e02;
	}

	public String getE03() {
		return e03;
	}

	public void setE03(String e03) {
		this.e03 = e03;
	}

	public String getE04() {
		return e04;
	}

	public void setE04(String e04) {
		this.e04 = e04;
	}

	public String getE05() {
		return e05;
	}

	public void setE05(String e05) {
		this.e05 = e05;
	}

	public String getE06() {
		return e06;
	}

	public void setE06(String e06) {
		this.e06 = e06;
	}

	public String getE07() {
		return e07;
	}

	public void setE07(String e07) {
		this.e07 = e07;
	}

	public String getE08() {
		return e08;
	}

	public void setE08(String e08) {
		this.e08 = e08;
	}

	public String getE09() {
		return e09;
	}

	public void setE09(String e09) {
		this.e09 = e09;
	}

	public String getE10() {
		return e10;
	}

	public void setE10(String e10) {
		this.e10 = e10;
	}

	public String getE11() {
		return e11;
	}

	public void setE11(String e11) {
		this.e11 = e11;
	}

	public String getE12() {
		return e12;
	}

	public void setE12(String e12) {
		this.e12 = e12;
	}

	public String getE13() {
		return e13;
	}

	public void setE13(String e13) {
		this.e13 = e13;
	}

	public String getE14() {
		return e14;
	}

	public void setE14(String e14) {
		this.e14 = e14;
	}

	public String getE15() {
		return e15;
	}

	public void setE15(String e15) {
		this.e15 = e15;
	}

	public String getE16() {
		return e16;
	}

	public void setE16(String e16) {
		this.e16 = e16;
	}

	public String getE17() {
		return e17;
	}

	public void setE17(String e17) {
		this.e17 = e17;
	}

	public String getE18() {
		return e18;
	}

	public void setE18(String e18) {
		this.e18 = e18;
	}

	public String getE19() {
		return e19;
	}

	public void setE19(String e19) {
		this.e19 = e19;
	}

	public String getE20() {
		return e20;
	}

	public void setE20(String e20) {
		this.e20 = e20;
	}

	public String getE21() {
		return e21;
	}

	public void setE21(String e21) {
		this.e21 = e21;
	}

	public String getE22() {
		return e22;
	}

	public void setE22(String e22) {
		this.e22 = e22;
	}

	public String getE23() {
		return e23;
	}

	public void setE23(String e23) {
		this.e23 = e23;
	}

	public String getE24() {
		return e24;
	}

	public void setE24(String e24) {
		this.e24 = e24;
	}

	public String getE25() {
		return e25;
	}

	public void setE25(String e25) {
		this.e25 = e25;
	}

	public String getE26() {
		return e26;
	}

	public void setE26(String e26) {
		this.e26 = e26;
	}

	public String getE27() {
		return e27;
	}

	public void setE27(String e27) {
		this.e27 = e27;
	}

	public String getE28() {
		return e28;
	}

	public void setE28(String e28) {
		this.e28 = e28;
	}

	public String getE29() {
		return e29;
	}

	public void setE29(String e29) {
		this.e29 = e29;
	}

	public String getE30() {
		return e30;
	}

	public void setE30(String e30) {
		this.e30 = e30;
	}

	public String getP01() {
		return p01;
	}

	public void setP01(String p01) {
		this.p01 = p01;
	}

	public String getP02() {
		return p02;
	}

	public void setP02(String p02) {
		this.p02 = p02;
	}

	public String getP03() {
		return p03;
	}

	public void setP03(String p03) {
		this.p03 = p03;
	}

	public String getP04() {
		return p04;
	}

	public void setP04(String p04) {
		this.p04 = p04;
	}

	public String getP05() {
		return p05;
	}

	public void setP05(String p05) {
		this.p05 = p05;
	}

	public String getP06() {
		return p06;
	}

	public void setP06(String p06) {
		this.p06 = p06;
	}

	public String getP07() {
		return p07;
	}

	public void setP07(String p07) {
		this.p07 = p07;
	}

	public String getP08() {
		return p08;
	}

	public void setP08(String p08) {
		this.p08 = p08;
	}

	public String getP09() {
		return p09;
	}

	public void setP09(String p09) {
		this.p09 = p09;
	}

	public String getP10() {
		return p10;
	}

	public void setP10(String p10) {
		this.p10 = p10;
	}

	public String getP11() {
		return p11;
	}

	public void setP11(String p11) {
		this.p11 = p11;
	}

	public String getP12() {
		return p12;
	}

	public void setP12(String p12) {
		this.p12 = p12;
	}

	public String getP13() {
		return p13;
	}

	public void setP13(String p13) {
		this.p13 = p13;
	}

	public String getP14() {
		return p14;
	}

	public void setP14(String p14) {
		this.p14 = p14;
	}

	public String getP15() {
		return p15;
	}

	public void setP15(String p15) {
		this.p15 = p15;
	}

	public String getP16() {
		return p16;
	}

	public void setP16(String p16) {
		this.p16 = p16;
	}

	public String getP17() {
		return p17;
	}

	public void setP17(String p17) {
		this.p17 = p17;
	}

	public String getP18() {
		return p18;
	}

	public void setP18(String p18) {
		this.p18 = p18;
	}

	public String getP19() {
		return p19;
	}

	public void setP19(String p19) {
		this.p19 = p19;
	}

	public String getP20() {
		return p20;
	}

	public void setP20(String p20) {
		this.p20 = p20;
	}

	public String getP21() {
		return p21;
	}

	public void setP21(String p21) {
		this.p21 = p21;
	}

	public String getP22() {
		return p22;
	}

	public void setP22(String p22) {
		this.p22 = p22;
	}

	public String getP23() {
		return p23;
	}

	public void setP23(String p23) {
		this.p23 = p23;
	}

	public String getP24() {
		return p24;
	}

	public void setP24(String p24) {
		this.p24 = p24;
	}

	public String getP25() {
		return p25;
	}

	public void setP25(String p25) {
		this.p25 = p25;
	}

	public String getP26() {
		return p26;
	}

	public void setP26(String p26) {
		this.p26 = p26;
	}

	public String getP27() {
		return p27;
	}

	public void setP27(String p27) {
		this.p27 = p27;
	}

	public String getP28() {
		return p28;
	}

	public void setP28(String p28) {
		this.p28 = p28;
	}

	public String getP29() {
		return p29;
	}

	public void setP29(String p29) {
		this.p29 = p29;
	}

	public String getP30() {
		return p30;
	}

	public void setP30(String p30) {
		this.p30 = p30;
	}

	public String getGroupTypeName() {
		return groupTypeName;
	}

	public void setGroupTypeName(String groupTypeName) {
		this.groupTypeName = groupTypeName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getStrageTypeCd() {
		return strageTypeCd;
	}

	public void setStrageTypeCd(String strageTypeCd) {
		this.strageTypeCd = strageTypeCd;
	}

}

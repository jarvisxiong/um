package com.hhz.ump.web.vo;

public class VoFactOpera {
	/**
	 * 满足项目、租户或公寓或多经、费用类别、年、月条件的合同冲突
	 */
	/**
	 * 不冲突：保证金：无年月，不存在合同及应收记录，
	 *  公寓项目，合同和应收可有可无
	 */
	public static final int BIS_CONT_CONFLICT_NO_00 = 0;
	/**
	 * 不冲突：广告收入、停车费等，可以录入年月，不存在合同及应收记录，
	 */
	public static final int BIS_CONT_CONFLICT_NO_01 = 1;
	/**
	 * 不冲突：存在一条合同，
	 */
	public static final int BIS_CONT_CONFLICT_NO_02 = 2;
	/**
	 * 不冲突：存在多个合同及应收记录，合同时间合理，
	 */
	public static final int BIS_CONT_CONFLICT_NO_03 =3 ;
	/**
	 *冲突: 不存在合同及应收记录，
	 */
	public static final int BIS_CONT_CONFLICT_04 =4 ;
	/**
	 * 冲突:存在多个合同及应收记录，合同时间不合理，
	 */
	public static final int BIS_CONT_CONFLICT_05 =5 ;
	public VoFactOpera(int type,String desc){
		this.type=type;
		this.desc =desc;
	}
	private boolean success;
	private String desc;
	private int type;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}


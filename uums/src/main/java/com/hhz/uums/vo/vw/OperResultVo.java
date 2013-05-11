package com.hhz.uums.vo.vw;

public class OperResultVo {

	//0-失败  1-成功2-部分成功 9-其他
	public static String TYPE_FAILURE = "0";
	public static String TYPE_SUCCESS = "1";
	public static String TYPE_SUCPART = "2";
	public static String TYPE_OTHER   = "9";

	private boolean success;
	private String type;
	private String desc;
	
	public OperResultVo(String type, String desc){
		this.type = type;
		this.desc = desc;

		if(OperResultVo.TYPE_SUCCESS.equals(type) || OperResultVo.TYPE_SUCPART.equals(type)) {
			this.success = true;
		} else {
			this.success = false;
		}
	}
	
	/**
	 * 构造失败
	 * @param desc
	 * @return
	 */
	public static OperResultVo getErrorVo(String desc){
		return new OperResultVo(OperResultVo.TYPE_FAILURE, desc);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean getSuccess() {
		return this.success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}

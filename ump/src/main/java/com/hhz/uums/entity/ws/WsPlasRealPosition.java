// 
package com.hhz.uums.entity.ws;

public class WsPlasRealPosition implements java.io.Serializable {

	private static final long serialVersionUID = -6396857936543917691L;

    private String plasRealPositionId;
    private String realPosCd;
    private String realPosName;
    private String realPosNameShow;
    private Long sequenceNo;
    private String remark;

	public String getPlasRealPositionId() {
		return plasRealPositionId;
	}

	public void setPlasRealPositionId(String plasRealPositionId) {
		this.plasRealPositionId = plasRealPositionId;
	}

	public String getRealPosCd() {
		return realPosCd;
	}

	public void setRealPosCd(String realPosCd) {
		this.realPosCd = realPosCd;
	}

	public String getRealPosName() {
		return realPosName;
	}

	public void setRealPosName(String realPosName) {
		this.realPosName = realPosName;
	}

	public String getRealPosNameShow() {
		return realPosNameShow;
	}

	public void setRealPosNameShow(String realPosNameShow) {
		this.realPosNameShow = realPosNameShow;
	}

	public Long getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(Long sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

// 
package com.hhz.uums.vo.vw;

import java.math.BigDecimal;

import com.hhz.uums.entity.bis.BisEmailStat;

public class VoBisEmailStat extends BisEmailStat {

//	private String noReadCountSizeDiv; // 未读邮件占用空间(M)
//	private String totalCountSizeDiv; // 总占用空间(M)

	public String getNoReadCountSizeDivi() {
//		return noReadCountSizeDiv;
		
		Long t = getNoReadCountSize();
		if( t == null)
			return "0";
		else
			return String.valueOf(formatScale(formatScale(getNoReadCountSize())/formatScale(1024.0)/formatScale(1024.0)));
	}

//	public void setNoReadCountSizeDiv(String noReadCountSizeDiv) {
//		this.noReadCountSizeDiv = noReadCountSizeDiv;
//	}

	public String getTotalCountSizeDivi() {
//		return totalCountSizeDiv;

		Long t = getTotalCountSize();
		if( t == null)
			return "0";
		else
			return String.valueOf(formatScale(formatScale(getTotalCountSize())/formatScale(1024.0)/formatScale(1024.0)));
	}

//	public void setTotalCountSizeDiv(String totalCountSizeDiv) {
//		this.totalCountSizeDiv = totalCountSizeDiv;
//	}
	
	//邮件占比
	public String getCountDivi(){
		if(getNoReadCount() == 0 ||getTotalCount() == 0)
			return "0";
		else
			return String.valueOf(formatScale(formatScale(getNoReadCount())/formatScale(getTotalCount()) * 100));
		
	}
	
	//空间占比
	public String getSizeDivi(){
		if(getNoReadCountSize() == 0 ||getTotalCountSize() == 0)
			return "0";
		else
			return String.valueOf(formatScale(formatScale(getNoReadCountSize())/formatScale(getTotalCountSize()) * 100));
		
		
	}

	public static double formatScale(Double b){
		BigDecimal tt = new BigDecimal(b);
		return tt.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static double formatScale(Long b){
		BigDecimal tt = new BigDecimal(b);
		 return tt.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}

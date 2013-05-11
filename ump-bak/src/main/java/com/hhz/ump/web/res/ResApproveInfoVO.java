package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hhz.ump.entity.res.ResApproveInfo;

public class ResApproveInfoVO implements Comparable {
	
	String statusCd ;
	List<ResApproveInfo> resApproveInfos = new ArrayList<ResApproveInfo>();
	
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public List<ResApproveInfo> getResApproveInfos() {
		return resApproveInfos;
	}
	public void setResApproveInfos(List<ResApproveInfo> resApproveInfos) {
		this.resApproveInfos = resApproveInfos;
	}
	
	public void add(ResApproveInfo rai){
		this.resApproveInfos.add(rai);
	}
	public ResApproveInfoVO(String statusCd) {
		super();
		this.statusCd = statusCd;
	}

	public int compareTo(Object obj) {
		if (this == obj)
			return 0;
		if (obj == null)
			return 1;
		if (getClass() != obj.getClass())
			return 1;
		ResApproveInfoVO other = (ResApproveInfoVO) obj;
		if(statusCd != null && other.statusCd!=null){
			int res = statusCd.compareTo(other.statusCd);
			return res;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		ResApproveInfoVO v0 = new ResApproveInfoVO("0");
		ResApproveInfoVO v1 = new ResApproveInfoVO("1");
		List<ResApproveInfoVO> al = new ArrayList<ResApproveInfoVO>();
		al.add(v1);
		al.add(v0);
		
		for(ResApproveInfoVO v : al){
			System.out.println(v.getStatusCd());
		}
		
		Collections.sort(al);
		
		System.out.println("sort");
		
		for(ResApproveInfoVO v : al){
			System.out.println(v.getStatusCd());
		}
		
		Collections.reverse(al);
		
		System.out.println("reverse");
		
		for(ResApproveInfoVO v : al){
			System.out.println(v.getStatusCd());
		}
	}
	

	
	
}

package com.hhz.ump.web.vo;

import java.util.ArrayList;
import java.util.List;

public class BisManageVo {
	private String projectid;
	private List<BisManageProjectVo> list = new ArrayList<BisManageProjectVo>();
	private int count;
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public List<BisManageProjectVo> getList() {
		return list;
	}
	public void setList(List<BisManageProjectVo> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}


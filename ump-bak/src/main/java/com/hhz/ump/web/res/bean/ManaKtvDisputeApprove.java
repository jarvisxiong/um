package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * KTV门店一般争端处理方案审批表
 * @author baolm
 *
 * 2011-05-31
 */
public class ManaKtvDisputeApprove extends BaseTemplate {
	
	private String name; // 名称
	private String desc; // 内容简述(详细内容附后)
	private String less; // 单次金额≤2000元，且月度累计金额≤5000元
	private String more; // 单次2000元＜金额≤1万元，且月度累计5000元＜金额≤2万元
	private String other; // 其他

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLess() {
		return less;
	}

	public void setLess(String less) {
		this.less = less;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}

}

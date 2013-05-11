/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 招聘广告审批表(总部/项目公司)
 * 
 */
public class JoinUsAdvertSheet extends BaseTemplatePay {
	
	//	名称adverName
	//	发布方式及范围postScopDesc
	//	内容简述(详细内容附后)adverDesc

	private String adverName;
	private String postScopDesc;
	private String adverDesc;
	public String getAdverName() {
		return adverName;
	}
	public void setAdverName(String adverName) {
		this.adverName = adverName;
	}
	public String getPostScopDesc() {
		return postScopDesc;
	}
	public void setPostScopDesc(String postScopDesc) {
		this.postScopDesc = postScopDesc;
	}
	public String getAdverDesc() {
		return adverDesc;
	}
	public void setAdverDesc(String adverDesc) {
		this.adverDesc = adverDesc;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return adverName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return adverName;
	}
	
}

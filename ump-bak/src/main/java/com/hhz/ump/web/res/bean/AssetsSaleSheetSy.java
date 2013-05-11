package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.assm.AssmAccountManager;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 办公资产出售/报废/遗失申请单(商业公司/总部)
 *
 * 2011-1-13
 */
public class AssetsSaleSheetSy extends BaseTemplate implements IAutoImport {

	private List<AssetsSaleSheetSyProp> subProperties= new ArrayList<AssetsSaleSheetSyProp>();
	@Override
	public void doImport() throws Exception {
		// TODO Auto-generated method stub
		AssmAccountManager accountManager=SpringContextHolder.getBean("assmAccountManager"); 
		accountManager.updateResRef2(getSubProperties(), getResApproveInfoId());
	}
	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}
	public List<AssetsSaleSheetSyProp> getSubProperties() {
		return subProperties;
	}
	public void setSubProperties(List<AssetsSaleSheetSyProp> subProperties) {
		this.subProperties = subProperties;
	}
	
	
}

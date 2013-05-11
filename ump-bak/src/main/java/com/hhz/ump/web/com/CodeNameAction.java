package com.hhz.ump.web.com;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictMapUtil;
import com.opensymphony.xwork2.ActionSupport;

public class CodeNameAction extends ActionSupport {

	private static final long serialVersionUID = 2052008574600849454L;

	private static final String BIS_FACT_STATUS   ="BIS_FACT_STATUS";//用户代码
	private static final String BIS_CHARGE_TYPE   ="BIS_CHARGE_TYPE";//用户代码
	private static final String BIS_PROJECT_ID   ="BIS_PROJECT_ID";//用户代码
	private String field;

	private String value;
	@Override
	public String execute() throws Exception {
		String result = value;
		if(StringUtils.equals(field, BIS_FACT_STATUS)){
			result = DictMapUtil.getMapBisFactStatus(value);
		} else	if(StringUtils.equals(field, BIS_CHARGE_TYPE)){
			result = DictMapUtil.getMapChargeType(value);
		} else if(StringUtils.equals(field,BIS_PROJECT_ID)){
			result = CodeNameUtil.getProjectName(value);
			
		}
		Struts2Utils.renderText(StringUtils.trimToEmpty(result));
		return null;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

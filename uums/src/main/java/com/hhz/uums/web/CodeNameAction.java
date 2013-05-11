/**
 * 
 */
package com.hhz.uums.web;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.utils.CodeNameUtil;
import com.hhz.uums.utils.Constants;
import com.hhz.uums.utils.DictMapUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangj 2010-12-20
 */
@Namespace("/")
public class CodeNameAction extends ActionSupport {

	private static final long serialVersionUID = 2052008574600849454L;

	private static final String USER_CODE   ="userCode";//用户代码
	private static final String APP_USER_ID="appUserId";//用户ID
	private static final String CURRENCY   ="currency";
	private static final String APP_TYPE = "appType";//应用类型
	private String field;

	private String value;
	@Override
	public String execute() throws Exception {
		String result = value;
		if(StringUtils.equals(field, USER_CODE)){
			result =  CodeNameUtil.getUserNameByCode(value);
		}else if(StringUtils.equals(field, APP_USER_ID)){
			result =  CodeNameUtil.getUserNameById(value);
		}else if(StringUtils.equals(field, CURRENCY)){
			result =  CodeNameUtil.getDictNameByCd(Constants.DICT_TYPE_CURRENCY, value);
		}else if(StringUtils.equals(field, APP_TYPE)){
			result = DictMapUtil.getMapAppType().get(value);
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

package com.hhz.uums.web.ssl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.entity.plas.PlasUser;

/**
 * 本类供外部系统校验用户有效性使用
 * 
 * @author huangbijin
 * 
 */
@Results( {
		@Result(name = "sslsuccess", location = "success.jsp", type = "redirect", params = { "validateResult",
				"${validateResult}", "validateResultDesc", "${validateResultDesc}" }),
		@Result(name = "sslfailue", location = "failure.jsp", type = "redirect", params = { "validateResult",
				"${validateResult}", "validateResultDesc", "${validateResultDesc}" }),
		@Result(name = "sslexception", location = "failure.jsp", type = "redirect", params = { "validateResult",
				"${validateResult}", "validateResultDesc", "${validateResultDesc}" }) })
				
public class SslAction extends CrudActionSupport<PlasUser> {

	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(SslAction.class);

	@Autowired
	private PlasAcctManager acctManager;

	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;

	// 校验类型:
	// 1.elem-返回页面元素(默认)
	// 2.url-返回sucess.jsp/failure.jsp/exception.jsp
	// 3.返回response参数(eg:head/session/request)
	private String validateType;

	// 用户名
	private String name;

	// 密码
	private String password;

	// 校验结果
	private String validateResult;
	// 校验结果描述
	private String validateResultDesc;

	private final static String VALIDATE_TYPE_RESULT_ELEM = "elem";// 默认
	private final static String VALIDATE_TYPE_RESLUT_URL = "url";
	private final static String VALIDATE_TYPE_RESLUT_RESPONSE = "response";

	private final static String VALIDATE_RESULT_SUCCESS = "1";
	private final static String VALIDATE_RESULT_FAILURE = "0";
	private final static String VALIDATE_RESULT_EXCEPTION = "2";

	private final static String VALIDATE_RESULT_SUCCESS_DESC = "sslsuccess";
	private final static String VALIDATE_RESULT_FAILURE_DESC = "sslfailure";
	private final static String VALIDATE_RESULT_EXCEPTION_DESC = "sslexception";

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// 默认执行校验
		// return result();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlasUser getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidateResult() {
		return validateResult;
	}

	public void setValidateResult(String validateResult) {
		this.validateResult = validateResult;
	}

	public String getValidateResultDesc() {
		return validateResultDesc;
	}

	public void setValidateResultDesc(String validateResultDesc) {
		this.validateResultDesc = validateResultDesc;
	}

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

	/**
	 * 功能: 校验结果 输入:
	 * 
	 * @return
	 */
	public String result() {

		try{
			String md5Password = md5PasswordEncoder.encodePassword(getPassword(), "");
			boolean bFlag = acctManager.validateUser(getName(), md5Password);
			if(bFlag){
				setValidateResult(VALIDATE_RESULT_SUCCESS);
				setValidateResultDesc(VALIDATE_RESULT_SUCCESS_DESC);
			}else{
				setValidateResult(VALIDATE_RESULT_FAILURE);
				setValidateResultDesc(VALIDATE_RESULT_FAILURE_DESC);
			}
		}catch (Exception e) {
			//e.printStackTrace();
			setValidateResult(VALIDATE_RESULT_EXCEPTION);
			setValidateResultDesc(VALIDATE_RESULT_EXCEPTION_DESC + "\n getMessage{" + e.getMessage() + "} \n ");
		}

		String tResult = null;
		if (StringUtils.isBlank(validateType) || VALIDATE_TYPE_RESULT_ELEM.equalsIgnoreCase(validateType)) {
			tResult = "result";
		} else if (VALIDATE_TYPE_RESLUT_URL.equalsIgnoreCase(validateType))
			return getValidateResultDesc();
		else if(VALIDATE_TYPE_RESLUT_RESPONSE.equalsIgnoreCase(validateType)) {
			Struts2Utils.getRequest().setAttribute("validateResult", getValidateResult());
			Struts2Utils.getRequest().setAttribute("validateResultDesc", getValidateResultDesc());
			Struts2Utils.getRequest().getSession().setAttribute("validateResult", getValidateResult());
			Struts2Utils.getRequest().getSession().setAttribute("validateResultDesc", getValidateResultDesc());

			Struts2Utils.getResponse().addHeader("validateResult", getValidateResult());
			Struts2Utils.getResponse().addHeader("validateResultDesc", getValidateResultDesc());
			tResult = "result";
		} else {
			tResult = null;
		}
		log.warn("----------------------------------------------------------------------------------------------------");
		log.warn("username:" + getName() + ",password:" + (("$PIN$".equals(getPassword()))?"$PIN$":"******") + " --> " + tResult + "| validate type:"+ getValidateType()+",Result:" + getValidateResult() + ",desc:" + getValidateResultDesc());
		log.warn("----------------------------------------------------------------------------------------------------");
		return tResult;
	}
}

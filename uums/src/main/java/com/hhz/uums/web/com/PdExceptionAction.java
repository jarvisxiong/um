/**
 * 
 */
package com.hhz.uums.web.com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>
 * </p>
 * 
 * @author huangjian
 * @create 2012-2-9
 */
@Results( { @Result(name = Action.SUCCESS, location = "/common/500.jsp") })
public class PdExceptionAction extends ActionSupport {
	private static final long serialVersionUID = 5689552298460351667L;
	private static Log log = LogFactory.getLog(PdExceptionAction.class);
	@Override
	public String execute() throws Exception {
		Exception ex = (Exception)Struts2Utils.getRequest().getAttribute("exception");
//		for(StackTraceElement element: ex.getStackTrace()){
//			log.error("className:"+element.getClassName());
//			log.error("FileName:"+element.getFileName());
//			log.error("LineNumber:"+element.getLineNumber());
//			log.error("MethodName:"+element.getMethodName());
//		}
		log.error(ex,ex);
		return SUCCESS; 
	}
}

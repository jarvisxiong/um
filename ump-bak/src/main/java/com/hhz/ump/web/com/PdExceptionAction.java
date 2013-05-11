/**
 * 
 */
package com.hhz.ump.web.com;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.dao.app.AppAttachFileManager;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>
 * PD统一异常处理类
 * </p>
 * 
 * @author huangjian
 * @create 2012-2-9
 */
@Results({ @Result(name = Action.SUCCESS, location = "/common/500.jsp") })
public class PdExceptionAction extends ActionSupport {
	private static final long serialVersionUID = 5689552298460351667L;
	private static Log log = LogFactory.getLog(PdExceptionAction.class);
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Override
	public String execute() throws Exception {
		Exception ex = (Exception) Struts2Utils.getRequest().getAttribute("exception");
//		 for(StackTraceElement element: ex.getStackTrace()){
//			 log.info(element.getClassName()+"."+element.getMethodName()+"("+element.getFileName()+":"+element.getLineNumber()+")");
//		 }
//		Enumeration<String> enumTmp = Struts2Utils.getRequest().getAttributeNames();
//		while (enumTmp.hasMoreElements()) {
//			String attrName = (String) enumTmp.nextElement();
//			log.info(attrName);
//		}
		if (ex.toString() != null && ex.toString().indexOf("ClientAbortException") != -1) {
			String attachFileId =(String)Struts2Utils.getRequest().getAttribute("error_attachFileId");
			if (StringUtils.isNotBlank(attachFileId)){
				appAttachFileManager.reduceDownloadTime(attachFileId);
				log.error(ex.getMessage());
				return null;
			}else{
				log.error("otherException:"+ex.getMessage());
				return SUCCESS;
			}
		} else {
			log.error(ex.getMessage(), ex);
			return SUCCESS;
		}
	}
}

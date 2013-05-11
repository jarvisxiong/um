/**
 * 
 */
package com.hhz.ump.tags;

import java.io.Writer;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.app.AppRoleMenuRelManager;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author huangj 2010-3-20
 */
@StrutsTag(name = "hasMenu", tldTagClass = "com.hhz.core.tags.HasMenu", description = "Permission tag")
public class Permission extends Component {
	public static final String ANSWER = "struts.if.answer";

	Boolean answer;

	private AppRoleMenuRelManager appRoleMenuRelManager = SpringContextHolder.getBean("appRoleMenuRelManager");

	/**
	 * menuCd,functionCd
	 */
	private String key;

	/**
	 * menu,function
	 */
	private String type;

	public Permission(ValueStack stack) {
		super(stack);
		// TODO Auto-generated constructor stub
	}

	@StrutsTagAttribute(description = "Expression to determine if body of tag is to be displayed", type = "String", required = true)
	public void setKey(String key) {
		this.key = key;
	}

	@StrutsTagAttribute(description = "Expression to determine if body of tag is to be displayed", type = "String", required = true)
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean start(Writer writer) {
		if (type.equals("menu")) {
			answer = appRoleMenuRelManager.hasMenuPermission(key);
		} else if (type.equals("function")) {
			answer = appRoleMenuRelManager.hasFuncPermission(key);
		}
		if (answer == null) {
			answer = Boolean.FALSE;
		}
		stack.getContext().put(ANSWER, answer);
		return answer.booleanValue();
	}

	@Override
	public boolean end(Writer writer, String body) {
		stack.getContext().put(ANSWER, answer);
		return super.end(writer, body);
	}
}

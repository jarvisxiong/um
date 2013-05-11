/**
 * 
 */
package com.hhz.core.tags;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

import org.apache.commons.lang.xwork.StringEscapeUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author huangj 2009-12-8
 */
@StrutsTag(name = "code2Name", tldBodyContent = "empty", tldTagClass = "com.hhz.core.tags.Code2NameTag", description = "Code to name")
public class ContentI18N extends Component {
	private static final Logger LOG = LoggerFactory.getLogger(ContentI18N.class);

	public ContentI18N(ValueStack stack) {
		super(stack);
	}



	private boolean escape = true;

	private boolean escapeJavaScript = false;


	private String name;


	@StrutsTagAttribute(description = " Whether to escape HTML", type = "Boolean", defaultValue = "true")
	public void setEscape(boolean escape) {
		this.escape = escape;
	}

	@StrutsTagAttribute(description = "Whether to escape Javascript", type = "Boolean", defaultValue = "false")
	public void setEscapeJavaScript(boolean escapeJavaScript) {
		this.escapeJavaScript = escapeJavaScript;
	}


	@StrutsTagAttribute(description = "Name Key", type = "Object")
	public void setName(String nameKey) {
		this.name = nameKey;
	}

	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);

		try {
			writer.write(getValue());
		} catch (IOException e) {
			LOG.info("Could not print out value '" + name + "'", e);
		}

		return result;
	}

	private String getValue() {
		String rtnVal = null;
		String actualName = null;
		Locale locale = (Locale) ActionContext.getContext().getSession().get("WW_TRANS_I18N_LOCALE");
		if (locale == null) {
			locale = ActionContext.getContext().getLocale();
		}
		if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
			actualName = name + "Chi";
		} else if (locale.toString().startsWith("zh")) {
			actualName = name + "Gbx";
		} else {
			actualName = name + "Eng";
		}
		rtnVal = (String) getStack().findValue(actualName, String.class, throwExceptionOnELFailure);

		if (rtnVal != null) {
			String tmpVal = rtnVal.toLowerCase().trim();
			if (tmpVal.equals("no news"))
				return "false";
			rtnVal = prepare(rtnVal);
		} else
			return "false";
		return rtnVal;
	}

	private String prepare(String value) {
		String result = value;
		if (escape) {
			result = StringEscapeUtils.escapeHtml(result);
		}
		if (escapeJavaScript) {
			result = StringEscapeUtils.escapeJavaScript(result);
		}
		return result;
	}

}

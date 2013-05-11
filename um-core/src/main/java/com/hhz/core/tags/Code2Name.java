/**
 * 
 */
package com.hhz.core.tags;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.xwork.StringEscapeUtils;
import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.hhz.core.utils.CoreContants;
import com.hhz.core.utils.PowerUtils;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @author huangj 2009-12-8
 */
@StrutsTag(name = "code2Name", tldBodyContent = "empty", tldTagClass = "com.hhz.core.tags.Code2NameTag", description = "Code to name")
public class Code2Name extends Component {
	private static final Logger LOG = LoggerFactory.getLogger(Code2Name.class);

	public Code2Name(ValueStack stack) {
		super(stack);
	}

	private String defaultValue;

	private String value;

	private boolean escape = true;

	private boolean escapeJavaScript = false;

	private String codeKey;

	private String nameKey;

	private String mapCodeName;

	@StrutsTagAttribute(description = "The default value to be used if <u>value</u> attribute is null")
	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	@StrutsTagAttribute(description = " Whether to escape HTML", type = "Boolean", defaultValue = "true")
	public void setEscape(boolean escape) {
		this.escape = escape;
	}

	@StrutsTagAttribute(description = "Whether to escape Javascript", type = "Boolean", defaultValue = "false")
	public void setEscapeJavaScript(boolean escapeJavaScript) {
		this.escapeJavaScript = escapeJavaScript;
	}

	@StrutsTagAttribute(description = "Value to be displayed", type = "Object", defaultValue = "&lt;top of stack&gt;")
	public void setValue(String value) {
		this.value = value;
	}

	@StrutsTagAttribute(description = "Code Key", type = "Object")
	public void setCodeKey(String codeKey) {
		this.codeKey = codeKey;
	}

	@StrutsTagAttribute(description = "Name Key", type = "Object")
	public void setNameKey(String nameKey) {
		this.nameKey = nameKey;
	}

	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);

		try {
			writer.write(getValue());
		} catch (IOException e) {
			LOG.info("Could not print out value '" + value + "'", e);
		}

		return result;
	}

	private String getValue() {

		String rtnVal = null;
		String actualValue = null;
		actualValue = (String) getStack().findValue(value, String.class, throwExceptionOnELFailure);
		if (value == null) {
			value = "top";
		} else {
			value = stripExpressionIfAltSyntax(value);
		}

		if (actualValue != null) {
			rtnVal = prepare(actualValue);
		} else if (defaultValue != null) {
			rtnVal = prepare(defaultValue);
		}
		return getNameByCode(rtnVal);

	}

	/**
	 * Code 转name
	 * 
	 * @param keyVal
	 * @return
	 */
	private String getNameByCode(String keyVal) {
		String rtnVal = null;
		if (keyVal != null) {
			if (codeKey != null && nameKey != null) {
				try {
					Field fieldCode = CoreContants.class.getField(codeKey);
					Field fieldName = CoreContants.class.getField(nameKey);
					String[] codeAry = (String[]) fieldCode.get(null);
					String[] nameAry = (String[]) fieldName.get(null);
					rtnVal = PowerUtils.getSamPlaceValue(nameAry, codeAry, keyVal);
				} catch (Exception e) {
					LOG.info("Code To Name error '" + value + "'", e);
				}
			} else if (mapCodeName != null) {
				Map<String, String> mapValue = (Map<String, String>) findValue(mapCodeName);
				if (mapValue != null) {
					rtnVal = mapValue.get(keyVal);
					// rtnVal = MessageUtils.getText(rtnVal);
				}
			}
			if (StringUtils.isEmpty(rtnVal)) {
				rtnVal = keyVal;
			}
		}
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

	public void setMapCodeName(String mapCodeName) {
		this.mapCodeName = mapCodeName;
	}

}

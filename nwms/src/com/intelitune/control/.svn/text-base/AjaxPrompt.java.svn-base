package com.intelitune.control;

import net.sf.click.control.Field;

public class AjaxPrompt {
	public StringBuffer sb = new StringBuffer();

	public void registerRequest(Field f, String procedure, String paramName,
			String url) {
		sb.append("ajaxEngine.registerRequest('" + procedure + "'," + "'" + url
				+ "');");
		sb.append("ajaxEngine.registerAjaxElement('" + f.getId()
				+ "_ErrorMessage');");

		f.setAttribute("onblur", "send(this,'" + procedure + "','" + paramName
				+ "');");
	}

	public String toString() {
		StringBuffer s = new StringBuffer();
		s
				.append("<script type='text/javascript' src='../assets/js/prototype.js'></script>");
		s
				.append("<script type='text/javascript' src='../assets/js/rico.js'></script>");
		s.append("<script type='text/javascript'>");
		s.append("function send(select, procedure, field) {");
		s.append("var re = select.value;");
		s.append("if(re==null||trim(re)==\"\"){");
		s
				.append("var se = document.getElementById(select.id+\"_ErrorMessage\");");
		s.append("var s = \"不能为空\";");
		s.append("se.innerHTML= s ;");
		s.append("return;}");
		s
				.append("ajaxEngine.sendRequest(procedure , field +\"=\"+ encodeURIComponent(select.value));}");
		s.append(sb.toString());
		s.append("</script>");

		return s.toString();
	}
}

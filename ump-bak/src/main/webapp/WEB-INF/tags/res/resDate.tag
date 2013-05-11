<%--
网批，Date控件
 --%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="name" description="字段名" required="true"%>
<%@ attribute name="value" description="字段值" required="false"%>
<%@ attribute name="isRequired" description="是否必输条件,默认true" required="false"%>
<%@ attribute name="index" description="列表序号" required="false"%>
<%@ attribute name="subProp" description="子列表属性" required="false"%>
<%@ attribute name="style" description="css属性" required="false"%>
<% if (index!=null){ %>
<input style="${style}" class="Wdate inputBorder" <% if (isRequired==null || isRequired.equals("true")){ %>validate="required" <%} %> type="text" name="templateBean.${subProp}[${index}].${name}" value="${value}" onfocus="WdatePicker()"/>
<%}else{ %>
<input style="${style}" class="Wdate inputBorder" <% if (isRequired==null || isRequired.equals("true")){ %>validate="required" <%} %> type="text" name="templateBean.${name}" value="${value}" onfocus="WdatePicker()"/>
<%} %>
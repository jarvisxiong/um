<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.uums.entity.ws.WsPlasOrg"%>
<%@page import="com.hhz.ump.cache.PlasCache"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<style>
	.tree_module_body {
	    font-size:12px;
		margin-left: 5px;
		cursor:pointer;
		border-bottom: 1px solid #DDDBDC;
	}
	.tree_module_body:hover{
		color:#0167A2;
	}
	</style>
 <s:iterator value="searchModuleList">
	 	<div class="tree_module_body" id="${contractTempletTypeId}" onclick="searchScTemplet('${contractTempletTypeId}','${typeName}');" style="margin-bottom:5px;">
		
			${typeName }
			
			
		</div>
</s:iterator>

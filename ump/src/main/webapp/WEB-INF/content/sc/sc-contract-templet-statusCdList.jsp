<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<s:iterator value="coOpinions">
	<li id="btn_nav1_${key}" <s:if test='%{key==selectOptions}'>class="cost-nav-click" isFirst="1"</s:if> onclick="seleConTypeOp('${key}');">
	&nbsp;${value}&nbsp;
	</li>
</s:iterator>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<s:iterator value="recoOpinions" status="recoOpinions">
	<input type="button" id="btn_nav1_<s:property value="#recoOpinions.index"/>" <s:if test='%{key==selectRecoOpinion}'>class="btn_nav1 btn_nav1_click" isFirst="1"</s:if><s:else>class="btn_nav1"</s:else> onclick="seleRecoOp('${key}','<s:property value="#recoOpinions.index"/>');" value="${value}"/>
</s:iterator>

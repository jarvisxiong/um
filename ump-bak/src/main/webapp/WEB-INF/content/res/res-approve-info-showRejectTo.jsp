<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<div style="margin:2px;width:90%;text-align: center;">
驳回到：
<s:select cssClass='input' list='mapReturnToNode' listKey='key' listValue='value' id='idRejectTo' name='rejectTo' emptyOption="true" ></s:select>
</div>
<div style="width:90%;text-align:center;color:red;">(默认空,表示驳回到发起人)</div>
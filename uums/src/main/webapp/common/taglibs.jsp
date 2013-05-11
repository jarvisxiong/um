<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pa" uri="/plas-tags" %>
<%@ taglib prefix="p" uri="/power-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page import="com.hhz.uums.utils.JspUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% request.setAttribute("siteTitle",JspUtil.getSiteTitle()); %>

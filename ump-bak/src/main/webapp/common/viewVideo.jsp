<%@include file="/common/taglibs.jsp"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.utils.EncodeUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/meta.jsp"%>
<title>网上审批管理规定培训视频</title>
</head>
<body>
	<s:url id="down" action="download" namespace="/app"  includeParams="none"  >
  	  <s:param name="fileName">resTrain.wmv</s:param>
  	  <s:param name="realFileName">网批培训录像.wmv</s:param>
  	  <s:param name="bizModuleCd">public</s:param>
	</s:url>
	<div>
	<object align="middle" classid="CLSID:22d6f312-b0f6-11d0-94ab-0080c74c7e95" class="OBJECT" width="600" height="500" >
	    <param name="ShowStatusBar" value="-1" />
	    <param name="Filename" value="${down}" />
	    <param name="autostart" value="0" />
	    <param name="autoplay" value="0" />
	    <embed type="application/x-oleobject" 
	    	codebase="http://w/activex/controls/mplayer/en/nsmp2inf.cab#Version=5,1,52,701" 
	        flename="mp" src="${down}" width="400" height="360" autostart="true" autoplay="true" controls="playbutton">
	    </embed>
	</object>
	</div>
</body>
</html>

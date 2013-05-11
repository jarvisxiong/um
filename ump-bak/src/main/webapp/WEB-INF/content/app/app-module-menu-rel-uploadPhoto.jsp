<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
	response.setHeader("Expires","0");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragrma", "no-cache");
	response.setDateHeader("Expires", 0);
%>

<s:form id="photoForm" action="/app/app-attachment!upload.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bizModuleCd" value="appMenu" />
    <input type="hidden" name="bizEntityId" value="${bizEntityId}" />
    <input type="hidden" name="filterType" value="image" />
    <input type="hidden" name="id" value="${userPhotoId}" />
   	<div style="margin-top: 20px;">请选择一张要上传的照片：<input type="file" name="upload" id="photoFile" /></div>
</s:form>
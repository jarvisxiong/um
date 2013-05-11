<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<s:form id="attachSingleForm" action="/bid/bid-app-attachment!uploadSingleFile.action" enctype="multipart/form-data">
    <div style="padding:10px;">
	    <input type="hidden" id="attachSingleForm_bizModuleCd" name="bizModuleCd" value="${bizModuleCd}" />
	    <input type="hidden" id="attachSingleForm_bizEntityId" name="bizEntityId" value="${bizEntityId}" />
	    <input type="hidden" name="bizEntityName" value="${bizEntityName}" />
	    <input type="hidden" name="fieldName" value="${fieldName}" />
	    <input type="hidden" name="bizFieldName" value="${fieldName}" />
	    <input type="hidden" name="id" value="${id}" />
	    <input type="hidden" id="domId" value="${domId}"/>
	    
	    <div style="height: 50px;overflow: hidden;cursor: pointer;" title="请选择上传文件">
	    	<input type="file" id="attachSingleForm_uploadInput" name="upload" size="50" style="float: left; height: 25px; margin-left: 30px; width: 200px;"/>
   	 	</div>
   	 	
	</div>
</s:form>
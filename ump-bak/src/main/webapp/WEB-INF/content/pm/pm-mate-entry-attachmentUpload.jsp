<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<div id="attachSingleUpload" style="padding:10px;">
	<div style="height: 50px;overflow: hidden;cursor: pointer;" title="请选择上传文件">
		<s:if test="uploadSn=='_pic'">
			<input type="file" id="attachSingleForm_uploadInput" name='upload_pic' size="50" style="float: left; height: 25px; margin-left: 30px; width: 200px;"/>
		</s:if>
		<s:else>
			<input type="file" id="attachSingleForm_uploadInput" name='upload_prj' size="50" style="float: left; height: 25px; margin-left: 30px; width: 200px;"/>
		</s:else>
	</div>
</div>

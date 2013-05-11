<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<s:form id="attachmentForm" action="/app/app-attachment!upload.action" enctype="multipart/form-data">
    <div>
	    <input type="hidden" name="bizModuleCd" value="oaNotify" />
	    <input type="hidden" name="bizEntityId" value="${bizEntityId}" />
	    <input type="hidden" name="filterType" value="pdf" />
	    <input type="hidden" name="id" value="${notifyAttachMent.appAttachFileId}" />
	    <input type="hidden" name="isUpdate" value="1" />
	    
	    <ul style="margin: 0; padding: 0; list-style: none;">
	    	<li style="height: 30px; line-height: 30px;">
	    		<input type="file" id="uploadInput" name="upload"/>
	    		<button class="anniu_lan_2" id="saveBtn" onclick="uploadAttachment(); return false;">
	    			<s:text name="common.upload"/>
	    		</button>
	    		<strong>（仅支持上传PDF附件）</strong>
	    	</li>
	    	<c:if test="${notifyAttachMent != null}">
		    	<li style="height: 30px; line-height: 30px;" id="attachment_element">
		    		<s:url id="preview" action="show" namespace="/app">
						<s:param name="fileName">${notifyAttachMent.fileName}</s:param>
						<s:param name="realFileName">${notifyAttachMent.fileName}</s:param>
						<s:param name="bizModuleCd">oaNotify</s:param>
						<s:param name="filterType">pdf</s:param>
						<s:param name="operator">inline;</s:param>
						<s:param name="id">${notifyAttachMent.appAttachFileId}</s:param>
					</s:url>
					<strong>已有附件：${notifyAttachMent.realFileName}</strong>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="anniu_lan_2" id="saveBtn" onclick="window.open('${preview}'); return false;">预览</button>
				</li>
	    	</c:if>
	    </ul>
    </div>
    <script type="text/javascript">
    	$("#notifyAttachmentId").val('${notifyAttachMent.appAttachFileId}');
    	$(".anniu_lan_2").each(function() {
			$(this).mouseover(function() {
				$(this).removeClass("anniu_lan_2").addClass("anniu_lan_2_an");
			});
			$(this).mouseout(function() {
				$(this).removeClass("anniu_lan_2_an").addClass("anniu_lan_2");
			});
		});
    </script>
</s:form>

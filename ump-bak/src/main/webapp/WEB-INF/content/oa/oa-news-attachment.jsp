<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<s:form id="attachmentForm" action="/app/app-attachment!upload.action" enctype="multipart/form-data">
    <div>
	    <input type="hidden" name="bizModuleCd" value="oaNews" />
	    <input type="hidden" name="bizEntityId" value="${bizEntityId}" />
	    
	    <ul style="margin: 0; padding: 0; list-style: none;">
	    	<li style="height: 30px; line-height: 30px;">
	    		<input type="file" id="uploadInput" name="upload"/>
	    		<button class="anniu_lan_2" onclick="uploadAttachment(); return false;"><s:text name="common.upload"/></button>
	    	</li>
	    	
	    	<c:if test="${attachMentPage.totalCount > 0}">
	    		<li>
		    		<strong>已有 ${attachMentPage.totalCount} 个附件：</strong>
		    	</li>
	    	</c:if>
	    	<s:iterator value="attachMentPage.result">
	    		<li style="height: 30px; line-height: 30px; padding-left: 20px;" id="attachment_element">
		    		<s:url id="preview" action="show" namespace="/app">
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${fileName}</s:param>
						<s:param name="bizModuleCd">oaNews</s:param>
						<s:param name="operator">inline;</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			    	<a href="#" style="text-decoration: none; color: gray;" onclick="window.open('${preview}'); return false;"><strong>${realFileName}</strong></a>
			    	&nbsp;&nbsp;
			    	<button class="anniu_lan_2" onclick="deleteAttachment('${appAttachFileId}', 'oaNews', '${bizEntityId}'); return false;"><s:text name="common.delete"/></button>
				</li>
	    	</s:iterator>
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

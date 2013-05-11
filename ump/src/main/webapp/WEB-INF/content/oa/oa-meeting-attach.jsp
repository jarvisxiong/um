<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>


<%@page import="com.hhz.ump.util.JspUtil"%>
<s:form id="attachForm" action="/app/app-attachment!upload.action" enctype="multipart/form-data">
    <div style="padding:15px;">
	    <input type="hidden" name="bizModuleCd" value="oaMeeting" />
	    <input type="hidden" name="bizEntityId" value="${bizEntityId}" />
	    
	    <div style="height: 30px;">
	    	<input type="file" id="uploadInput" name="upload" style="float: left; height: 25px; margin-left: 10px; width: 200px;"/>
	    	<div class="buttonStyle" onmousemove="$(this).addClass('buttonStyle_hover');" onmouseout="$(this).removeClass('buttonStyle_hover');" onclick="uploadAttachment(); return false;" style="float: left; margin-left: 20px;">上传</div>
   	 	</div>
	    <s:actionmessage/>
	    <c:if test="${attachPage.totalCount > 0}">
		    <div style="clear: both; margin-top: 10px;">
		    	<div style="text-align: center; height: 20px; color: #FFF; line-height: 20px; background-color: #5a6a83;">
		    		当前共有  ${attachPage.totalCount} 个附件
		    	</div>
		    	
		    	<div style="color: #FFF; background-color: #eee;">
		    		<ul style="margin: 0; padding: 0; list-style: none;">
				    	<s:iterator value="attachPage.result">
				    		<li style="height: 30px; line-height: 30px; padding-left: 20px; border-bottom: 1px solid #FFF;" id="attachment_element">
								<div style="width : 250px; float: left; height: 30px; line-height: 30px; color: #000;">
						    		${realFileName}
								</div>
								
								<s:url id="preview" action="show" namespace="/app">
									<s:param name="fileName">${fileName}</s:param>
									<s:param name="realFileName">${fileName}</s:param>
									<s:param name="bizModuleCd">oaMeeting</s:param>
									<s:param name="operator">inline;</s:param>
									<s:param name="id">${appAttachFileId}</s:param>
								</s:url>
								<span style="width:100px; height: 30px; line-height: 30px;">
									<a href="#" onclick="window.open('${preview}'); return false;"><img style="margin-top: 5px;" src="${ctx}/pics/email/atta_down.gif" />&nbsp;下载</a>
						    	</span>
								<s:if test="oaMeetingAdmin == true || oaMeetingGenren==true">
								
									<span style="width:100px; height: 30px; line-height: 30px; cursor: pointer; margin-left: 10px;">
							    		<a onclick="deleteAttachment('${appAttachFileId}', 'oaMeeting', '${bizEntityId}'); return false;">删除</a>
							    	</span>
								</s:if>
							</li>
				    	</s:iterator>
				    </ul>
		    	</div>
		    </div>
	   	</c:if>
    </div>
</s:form>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


<%@page import="com.hhz.ump.util.Util"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%><s:form id="attachForm" action="/app/app-attachment!upload.action" enctype="multipart/form-data">
    <div style="padding:10px;">
	    <input type="hidden" name="bizModuleCd" value="fileTracking" />
	    <input type="hidden" name="bizEntityId" value="${bizEntityId}" />
	    
	    <div style="height: 30px;">
	    	<input type="file" id="uploadInput" name="upload" style="float: left; height: 25px; margin-left: 10px; width: 250px;"/>
	    	<div class="buttonStyle" onclick="uploadAttachment();" style="float: left; margin-left: 20px;">上传</div>
   	 	</div>
   	 	
	    <s:if test="attachList.size > 0">
		    <div style="clear: both; margin-top: 10px;">
		    	<div style="text-align: center; height: 20px; color: #FFF; line-height: 20px; background-color: #5a6a83;">
		    		当前共有  <s:text name="attachList.size"></s:text> 个附件
		    	</div>
		    	
		    	<div style="background-color: #eee;">
		    		<table style="table-layout: fixed; width: 100%;">
		    			<s:iterator value="attachList">
		    				<tr style="border-bottom: 1px solid #FFF;">
		    					<td style="line-height: 20px;">
		    						${realFileName}
		    					</td>
		    					<td width="80" align="left">
		    						上传者:<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>
		    					</td>
	    						<td width="90" align="center">
	    							<s:url id="preview" action="show" namespace="/app">
										<s:param name="fileName">${fileName}</s:param>
										<s:param name="realFileName">${realFileName}</s:param>
										<s:param name="bizModuleCd">fileTracking</s:param>
										<s:param name="operator">inline;</s:param>
										<s:param name="id">${appAttachFileId}</s:param>
									</s:url>
	    							<a href="#" onclick="window.open('${preview}'); return false;" title="下载附件">
										<img style="margin-top: 5px;" src="${ctx}/pics/email/atta_down.gif" />
									</a>
									<img style="cursor: pointer;" title="删除附件" src="${ctx}/pics/note/note_del.gif" onclick="deleteAttachment('${appAttachFileId}', '${bizEntityId}');" />
								</td>
		    				</tr>
		    			</s:iterator>
		    		</table>
		    	</div>
		    </div>
	   	</s:if>
    </div>
</s:form>
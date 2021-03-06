<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<div style="padding: 0 10px; margin-top: 20px;">
	<s:form id="inputFileForm" action="company-meeting!saveFile.action" method="post">
		<div align="center">
			<s:hidden name="folderId" />
			<s:hidden name="fileId" />
			<s:hidden name="tempEntityId" />
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td valign="top" style="width:60px;text-align: right;">文件名称：</td>
					<td valign="top" ><input style="width:230px;" type="text" id="fileName" name="fileName" value="${fileEntity.fileName}" /></td>
				</tr>
				<!-- 
				<tr>
					<td style="width:60px;text-align: right;">主持人：</td>
					<td ><input style="width:130px;" type="text" id="compere" name="compere" value="${fileEntity.compere}" /></td>
				</tr>
				<tr>
					<td style="width:60px;text-align: right;">记录人：</td>
					<td><input style="width:130px;" type="text" id="recorder" name="recorder" value="${fileEntity.recorder}" /></td>
				</tr>
				<tr>
					<td style="width:60px;text-align: right;">会议时间：</td>
					<td><input style="width:133px;" type="text" id="beginDate" name="beginDate" value="${fileEntity.beginDate}"  class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/></td>
				</tr>
				 -->
			</table>
		</div>
	</s:form>
	
	<div style="text-align: center; color: #FFF; height: 20px; line-height: 20px; margin-top: 10px; background-color: #5A6A83;">附件列表</div>
	<div id="inputAttachList" style="height: 170px; background-color: #eee; overflow: auto; overflow-x: hidden;">
		<table style="table-layout: fixed; width: 100%">
			<s:iterator value="attachList">
				<tr>
					<td style="padding-left: 10px;border-bottom:1px solid #DDDBDC;">
						<s:url id="preview" action="show" namespace="/app">
							<s:param name="fileName">${fileName}</s:param>
							<s:param name="realFileName">${fileName}</s:param>
							<s:param name="bizModuleCd">companyMeeting</s:param>
							<s:param name="operator">inline;</s:param>
							<s:param name="id">${appAttachFileId}</s:param>
						</s:url>
						
						<a style="color: #5A5A5A;" title="${realFileName}" onclick="window.open('${preview}'); return false;" href="#">${realFileName}</a> &nbsp;&nbsp;
					</td>
					<td width="50" align="center" style="border-bottom:1px solid #DDDBDC;">
						<div class="btn_del_file" title="删除附件" onclick="deleteAttachment('${appAttachFileId}', '${bizEntityId}');" ></div>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div style="height: 2px; background-color: #FFF;overflow: hidden; font-size: 0;"></div>
	
	<div style="height: 35px; line-height: 35px; text-align: center; background: #EEE;">
		<s:form id="inputUploadAttForm" action="/app/app-attachment!upload.action" enctype="multipart/form-data">
			<input type="hidden" name="bizModuleCd" value="companyMeeting" />
			<input type="hidden" name="bizEntityId" value="${bizEntityId}" />
			<input type="file" id="uploadInput" name="upload" style="cursor: pointer;height: 22px;line-height: 22px;"/>
			<input type="button" value="上传附件" onclick="uploadAttach('${bizEntityId}');" style="cursor: pointer;height: 22px;line-height: 22px;"/>
		</s:form>
	</div>
</div>
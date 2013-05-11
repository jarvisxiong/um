<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="attachFileMainDiv" class="easyui-layout" fit="true" region="center" style="text-align: center;">
	<!-- hidden clone area -->
	<table style="display: none;">
		 <tr id="trUploadTemplate" >
		 	<td style="float:right;">
				<input class="easyui-validatebox" required="true" type="file" validType="length[1,50]" id="file" name="upload"/>
			</td>
		 </tr>
	</table>
	
	<div class="easyui-panel" region="south" style="padding:10px;">
	<form id="mainForm" method="post" enctype="multipart/form-data" action="${ctx}/app/app-attachment!upload.action">
		<input type="hidden" name="bizModuleCd" value="${bizModuleCd}" />
		<input type="hidden" name="bizEntityId" value="${bizEntityId}" />
		<input type="hidden" name="filterType" value="${filterType}" />
		<input type="hidden" name="bizEntityName" value="${bizEntityName}" />
		<input type="hidden" name="attachModelType" value="${attachModelType}" />
		<input type="hidden" name="bizMainEnableFlg" value="${bizMainEnableFlg}" />
		
		<table id="downTable" class="easyui-table" style="width:100%;">
			<col style="width: 70%;">
			<col style="width: 30%;">
			 <tr id="trUpload" >
			 	<td style="float:right;">
					<input class="easyui-validatebox" required="true" type="file"  id="file" name="upload"/>
				</td>
			 </tr>
		</table>
		
	 	<!-- 默认允许多个上传 -->
		<s:if test="attachModelType == null || attachModelType == ''">
		<a href="#" class="easyui-linkbutton" onclick="attach_AddNewFile()">增加附件</a>
		</s:if>
		<a href="#" class="easyui-linkbutton" onclick="attach_doUpload();" ><s:text name="common.upload"/></a>
	</form>
	</div>
	
	
	<div style="width:100%;">
		<c:if test="#page.list() == 0">
			<div style="padding:5px;">没有附件,赶快上传吧!</div>
		</c:if>
		<s:else>
			<%--
			<div><s:text name="common.download" /></div>
			 --%>
		</s:else>
		
		<div class="datagrid-header" style="height:22px;">
			<table>
			 	<tbody>
			 	<tr>
			 		<td style="width: 30px"><div class="datagrid-cell">序号</div></td>
			 		<td style="width:200px"><div class="datagrid-cell">文件名称</div></td>
			 		<td style="width: 50px"><div class="datagrid-cell">文件大小</div></td>
			 		<td style="width: 50px"><div class="datagrid-cell">上传人</div></td>
			 		<td style="width: 80px"><div class="datagrid-cell">上传日期</div></td>
			 		<s:if test="bizMainEnableFlg == 1">
			 		<td style="width: 50px"><div class="datagrid-cell">主图</div></td>
			 		</s:if>
			 		<td style="width: 80px"><div class="datagrid-cell"></div></td>
			 	</tr>
		 	</tbody>
			</table>
		</div>
		<div class="datagrid-body">
			<table>
		 		<tbody>
				<s:iterator value="page.result" status="stat">
				<tr
					<s:if test="mainFlg == 1">
						class = "datagrid-row-selected"
					</s:if>
				>
					<td style="width:33px">
						<div class="datagrid-cell">
							<s:property value="#stat.count"/>
						</div>
					</td>
					<td style="width:200px;">
						<div class="datagrid-cell">
						<s:url id="down" action="download" namespace="/app">
							<s:param name="fileName">${fileName}</s:param>
							<s:param name="realFileName">${realFileName}</s:param>
							<s:param name="bizModuleCd">${bizModuleCd}</s:param>
							<s:param name="filterType">${filterType}</s:param>
						</s:url> 
						<a href="${down}">${realFileName}</a>
						</div>
					</td>
					<td style="width:55px">
						<div class="datagrid-cell">
							<s:property value="fileSize"/>
						</div>
					</td>
					<td style="width:50px;">
						<div class="datagrid-cell">
						<s:property value="creator" />
						</div>
					</td>
					<td style="width:80px">
						<div class="datagrid-cell">
						<s:property value="createdDate" />
						</div>
					</td>
					<s:if test="bizMainEnableFlg == 1">
					<td style="width:50px;">
						<div class="datagrid-cell">
						<input  title="选中设置为主要图片" 
								type="checkbox" 
								<s:if test="mainFlg==true">checked="checked"</s:if> 
								onclick="attach_changeMainFlg(this,'${appAttachFileId}','${bizEntityId}','${bizEntityName}','${mainFlg}','${bizMainEnableFlg}');" />
						</div>
					</td>
					</s:if>
					<td style="width:80px">
						<div class="datagrid-cell">
						<a  title="删除附件"
							class="easyui-linkbutton" 
							onclick="attach_deleteAttach('${appAttachFileId}','${bizModuleCd}','${bizEntityId}','${bizEntityName}','${filterType}','${attachModelType}')"
							href="#">
							<font style='color: red; font-weight: bold; cursor: pointer;'>×</font>
						</a>
						</div>
					</td>
				</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</div> 
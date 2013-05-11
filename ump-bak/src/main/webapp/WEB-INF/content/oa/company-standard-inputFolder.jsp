<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<s:form id="inputFolderForm" action="company-standard!saveFolder.action" method="post">
	<input type="hidden" id="folderId" name="folderId" value="${folderEntity.oaFilingFolderId}" />
	<input type="hidden" id="parentFolderId" name="parentFolderId" value="${folderEntity.parentFolderId}" />
	
	<input type="hidden" id="selParentFolderId" name="selParentFolderId" value="" />
	<input type="hidden" id="selParentFolderName" name="selparentFolderName" value="" />
	
	<table style="width:90%; margin-top: 50px; text-align: center;" cellpadding="0" cellspacing="0">
		<tr>
			<td align="center" height="40">
				目录名称：
			</td>
			<td align="left"><input id="folderNameInput" name="folderName" value="${folderEntity.folderName}" type="text" style="width: 200px;" />
			</td>
		</tr>
		<tr>
			<td align="center" height="40" valign="middle">
				上级目录：
			</td>
			<td align="left"><input type="text" id="parentFolderNameInput" value="${parentFolderName}" style="width: 160px; cursor: pointer;" onfocus="getParent();" />
				<a onfocus="this.blur();" style="width: 30px; margin-left: 10px; text-decoration: underline; " href="javascript: clearParent();">清空</a>
			</td>
		</tr>
		<tr>
			<td align="center" height="40" valign="middle">
				可见人员：
			</td>
			<td align="left">
				<%-- 注意：以下两个ID有用 --%>
				<input type="text" id="visibleToUserNames" style="width: 160px; cursor: pointer;"  class="singleSelect" value="${visibleToPersonNames}"/>
				<input type="hidden" name="visibleToPersonCds" value="${visibleToPersonCds}" id="visibleToPersonCds"/>
				<a onfocus="this.blur();" style="width: 30px; margin-left: 10px; text-decoration: underline; " href="javascript: enableAllPerson();">全部</a>
				<a onfocus="this.blur();" style="width: 30px; margin-left: 10px; text-decoration: underline; " href="javascript: cleanInputPerson()">清空</a>
			</td>
		</tr>
		<tr>
			<td align="center" height="40" valign="middle">
				排序：
			</td>
			<td align="left"><input type="text" id="sequenceNo" name="sequenceNo" value="${folderEntity.sequenceNo}" style="width: 60px;"/>
			</td>
		</tr>
		<tr>
			<td align="center" height="40" valign="bottom" colspan="2">
				<div style="width: 150px;">
					<input type="button" class="fLeft btn_new btn_blue_new" onclick="saveFolder();" value="保存" />
					<s:if test="folderEntity.oaFilingFolderId != ''">
					<input type="button" class="fLeft btn_new btn_red_new" onclick="delFolder();" value="删除" />
					</s:if>
					<input type="button" class="fLeft btn_new btn_green_new" onclick="back();" value="返回" />
				</div>
			</td>
		</tr>
	</table>
	
	<div id="visibleToPersonDataDiv" style="display: none;">${visibleToPersonData}</div>
	<script type="text/javascript">
		function initVisibleToPersons() {
			visibleToPersons = [];
			var data = jQuery.trim($("#visibleToPersonDataDiv").html());
			if (data.length > 0) {
				var strAr = data.split("|");
				for (var i = 0; i < strAr.length; i ++) {
					var j = eval("(" + strAr[i] + ")");
					visibleToPersons.push(j);
				}
			} else {
				enableAllPerson();
			}
		}

		//initVisibleToPersons();
	</script>
	<script type="text/javascript">
		$(function(){ 
			$('.singleSelect').userSelect({
				muti:true
			});
		});
	</script>
</s:form>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<form action="${ctx}/oa/oa-email!save.action" id="mailForm" method="post" enctype="multipart/form-data">
	<s:hidden name="entityTmpId"></s:hidden>
	<input type="hidden" name="id" id="entityId" value="${oaEmailBody.oaEmailBodyId}"/>
	<input type="hidden" id="entityIdAlias" value="${oaEmailBody.oaEmailBodyId}"/>
	<input type="hidden" name="recordVersion" value="${oaEmailBody.recordVersion}"/>
	<div style="padding-left:3px;">
		<table class="inputTable" style="width:98%;_width:90%;">
			<tr>
				<td width="60" >收件人</td>
				<td colspan="2" limit="30">
					<s:property value='oaEmailBody.toUserNames' />
				</td>
			</tr>
			<tr>
				<td >发送时间</td>
				<td colspan="2">
					<s:property value='oaEmailBody.sendTime' />
				</td>
			</tr>
			<tr>
				<td >主题</td>
				<td><input name="subject" id="subject" style="width:100%" value="<s:property value='oaEmailBody.subject' />"/>
				</td>
				<td onmouseover="showImportDiv(this);" style="padding-left: 10px">
					<s:if test="oaEmailBody.importLevelCd == 2">		
						<span id="importLevelText"><span style="color:#FF6600">重要</span></span>
						<input type="hidden" id="importLevelCd" name="importLevelCd" value="3"/>
					</s:if>
					<s:elseif test="oaEmailBody.importLevelCd == 3">
						<span id="importLevelText"><span style="color:#FF0000">非常重要</span></span>
						<input type="hidden" id="importLevelCd" name="importLevelCd" value="2"/>
					</s:elseif>
					<s:else>
						<span id="importLevelText"><span style="color:#0066CC">一般</span></span>
						<input type="hidden" id="importLevelCd" name="importLevelCd" value="1"/>
					</s:else>
			</tr>
		</table>
	</div>
	<div style="padding-left:3px;">
		<textarea name="content" id="mailContent" style="width:98%;height:260px;" ><s:property value='oaEmailBody.content' /></textarea>
	</div>
	<div id="uploadAttaContainer" style="padding-left:3px;">
		<s:iterator value="appAttachFiles">
			<div class="attachment">
				<s:url id="down" action="download" namespace="/app"  includeParams="none"  >
		  	  <s:param name="fileName">${fileName}</s:param>
		  	  <s:param name="realFileName">${realFileName}</s:param>
		  	  <s:param name="bizModuleCd">${bizModuleCd}</s:param>
		  	  <s:param name="uiid">${creator}</s:param>
		  	  <s:param name="id">${appAttachFileId}</s:param>
			</s:url>
			<a href="${down}">${realFileName}</a><font onclick="removeFile(this)" class=hand style=color:red;font-weight:bold>×</font>|
			<input type="hidden" name="attaFileIds" value="${appAttachFileId}"/>
			</div>
		</s:iterator>
	</div>
	<div style="padding-left:3px;height:50px">
		<div style="float:left;">
			<div id="addAttaFile"><input class="mailFile" type="file" onchange="fileChange(this)" name="upload" hidefocus="true" size="1" /></div>
			<img src="${ctx}/pics/email/atta_upload.gif" align="absmiddle" style="cursor: pointer;" /><a href="javascript:void(0)" class="mailBlueText">添加附件</a>
		</div>
		<div id="attaContainer" style="float:left">
		</div>
	</div>
	<div align="center" style="padding-left:3px;width:150px;margin:0 auto;">
		<div class="btn_green_55_20" style="margin-right:20px;float:left;" title="保存" onclick="editEmail('${oaEmailBody.oaEmailBodyId}');">保存</div>
		<s:if test="searchFlg == 'true'">
			<div class="btn_green_55_20" style="float:left" title="返回到搜索界面" onclick="return2SearchDiv();">返回</div>
		</s:if>
		<s:else>
			<div class="btn_green_55_20" style="float:left" title="返回到发件箱" onclick="getMailList(3);">返回</div>
		</s:else>
		
	</div>
	<input type="hidden" id="uploadFlg" name="uploadFlg" value="0" />
	<input type="hidden" name="bizModuleCd" value="email" />
    <input type="hidden" name="bizEntityId" value="${entityTmpId}" />
    <input type="hidden" name="uiid" value="<%=SpringSecurityUtils.getCurrentUiid() %>" />
    <input type="hidden" name="filterType" value="all" />
</form>

<div id="importTipDiv" class="floatDiv">
	<div>
		<span><span style="color:#0066CC" value="1">一般</span></span>
		<span><span style="color:#FF6600" value="2">重要</span></span>
		<span><span style="color:#FF0000" value="3">非常重要</span></span>
	</div>
</div>
<script type="text/javascript">

function editEmail(){
	TB_showMaskLayer(".");
	if($("#attaContainer input").length>0){
		editAtta();
	}else{
		editEmail2();
	}
}
function editAtta(){
	$("#TB_tip").text("正在上传附件..");
	$("#mailForm").attr("action","${ctx}/app/app-attachment!upload.action");
	$("#uploadFlg").val("1");
	$("#entityId").val("");
	$("#mailForm").ajaxSubmit(function(){
		editEmail2();
	})
}
function editEmail2(){
	$("#TB_tip").text("正在发送..");
	$("#mailForm").attr("action","${ctx}/oa/oa-email!edit.action");
	if($("#uploadAttaContainer").children().length>0){$("#uploadFlg").val("1");};
	$("#entityId").val($("#entityIdAlias").val());
	var content = mailContentEditor.getSource();
	$("#mailContent").val(content);
	$("#mailForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#mailRight").html(result);
		bindTdEvents();
		setNum2LeftBar();
	});
}

	function showImportDiv(dom){
		var off = $(dom).offset();
		$("#importLevelText").mouseleave(function(){
			setTimeout(function(){$("#importTipDiv").hide()}, 3000);
		})
	    $("#importTipDiv")
	    	.mouseleave(function(){$(this).hide()})
	    	.css({left:off.left+10+"px",top:off.top+$(dom).height()+"px"}).show();
	    $("#importTipDiv div span").click(function(){
			$("#importLevelCd").val($(this).attr("value"));
			$("#importLevelText").html($(this).html());
			$("#importTipDiv").hide();
		})
	}
	
	function createFile(){
		$("#addAttaFile").append("<input onchange=fileChange(this) name='upload' hidefocus=true type=file size=1 class=mailFile>");
	}
	function removeFile(dom){
		$(dom).parent().remove();
	}
	function fileChange(dom){
		var sName,o;
	    sName=dom.value.replace(/\\/g,"/").replace(/(.*\/)(.*)/,"$2");
		if(!checkUploadFile(sName))return;
	    o=document.createElement("nobr");
	    o.style.cssText="float:left;margin-right:6;padding-top:3;color:darkgreen";
	    o.innerHTML="|"+sName+"<font onclick=removeFile(this) class=hand style=color:red;font-weight:bold>"+unescape("×")+"</font>"; 
	    dom.style.display="none";
	    o.appendChild(dom);
	    $("#attaContainer").append(o);
	    if($("#subject").val() == ""){
	    	$("#subject").val(sName.substring(0,sName.lastIndexOf('.')));
		}
	    createFile();
	}
</script>


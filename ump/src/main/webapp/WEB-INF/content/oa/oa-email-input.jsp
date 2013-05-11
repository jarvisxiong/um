<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<input type="hidden" id="input_email_flag"/>
<form action="${ctx}/oa/oa-email!save.action" id="mailForm" method="post" enctype="multipart/form-data">
<input type="hidden" name="reply_id" value="${reply_id}" />
<s:if test="boxId != 2">
<div style="padding:5px 0;">
	<security:authorize ifNotGranted="A_EMAIL_READ_ONLY">
		<div style="float:left;width:80px;" align="left">
			<div class="btn_blue_55_55" style="margin:0px 0 5px 10px" onclick="sendMail();">发送</div>
			<%--
			<div class="btn_green_55_20" style="margin-left:10px;" onclick="draftMail();">保存</div>
			 --%>
		</div>
	</security:authorize>
	<security:authorize ifAnyGranted="A_EMAIL_READ_ONLY">
		<div style="float:left;width:80px;" align="left">
			<div class="btn_green_55_55" style="margin:2px 0 5px 10px">禁用</div>
			<div class="btn_green_55_20" style="margin-left:10px;" >禁用</div>
		</div>
	</security:authorize>
	<div style="margin-left: 100px;">
		<table class="inputTable" style="width:95%;_width:90%;">
			<s:hidden name="entityTmpId"></s:hidden>
			<tr>
				<td width="12%" >收件人</td>
				<td >
					<input  name="toUserNames" 
							<%--
							onclick="getMember('to');" 
							--%>
							id="toUserNames" style="width:70%;cursor: pointer;" value="${toUserNames }" readonly="readonly" 
					/>
							
					<%--
					&nbsp;<a onclick="clearMember('to');" class="mailBlueText">清空</a>
					--%>
					<input type="hidden" id="toUserCds" name="toUserCds" value="${toUserCds}"/>
				</td>
			</tr>
			<c:if test="${copyUserCds == null||copyUserCds == ''}">
				<tr><td colspan="2" class="mailBlueText">
					<span onclick="$(this).hide().next().show();$('#copyerText').show();">添加抄送人</span>
					<span onclick="$(this).hide().prev().show();$('#copyerText').hide();" style="display: none;">隐藏抄送人</span>
					<span onclick="$(this).hide().next().show();$('#bccText').show();">添加密送人</span>
					<span onclick="$(this).hide().prev().show();$('#bccText').hide();" style="display: none;">隐藏密送人</span>
				</td></tr>
				<tr id="copyerText" style="display:none">
					<td >抄送人</td>
					<td>
						<input  name="copyUserNames" 
								<%--
								onclick="getMember('copy');" 
								--%>
								id="copyUserNames" style="width:70%;cursor: pointer;" value="${copyUserNames}" readonly="readonly"
						/>
						<%--
						&nbsp;<a onclick="clearMember('copy');" class="mailBlueText">清空</a>
						--%>
						<input type="hidden" name="copyUserCds" id="copyUserCds" value="${copyUserCds}"/>
					</td>
				</tr>
				<tr id="bccText" style="display:none">
					<td >密送人</td>
					<td>
						<input 	name="bccUserNames" 
								<%--
								onclick="getMember('bcc');" 
								--%>
								id="bccUserNames" style="width:70%;cursor: pointer;" value="${bccUserNames}" readonly="readonly"
						/>
						<%--
						&nbsp;<a onclick="clearMember('bcc');" class="mailBlueText">清空</a>
						--%>
						<input type="hidden" name="bccUserCds" id="bccUserCds" value="${bccUserCds}"/>
					</td>
				</tr>
			</c:if>
			<c:if test="${copyUserCds != null && copyUserCds != ''}">
				<tr style="display:none">
					<td colspan="2" class="mailBlueText">
						<span  id="addCopyUser" onclick="$(this).parent().parent().hide();$('#copyerText').show();">添加抄送人</span>
						<span onclick="$(this).hide().prev().show();$('#copyerText').hide();" style="display: none;">隐藏抄送人</span>
					</td>
				</tr>
				<tr id="copyerText" >
					<td >抄送人</td>
					<td>
						<input  name="copyUserNames" 
								<%--
								onclick="getMember('copy');" 
								--%>
								id="copyUserNames" style="width:70%;cursor: pointer;" value="${copyUserNames}" readonly="readonly"
						/>
						<%--
						&nbsp;<a onclick="clearMember('copy');" class="mailBlueText">清空</a>
						--%>
						<input type="hidden" name="copyUserCds" id="copyUserCds" value="${copyUserCds}"/>
					</td>
				</tr>
				<tr id="bccText" style="display:none">
					<td >密送人</td>
					<td>
						<input  name="bccUserNames" 
								<%--
								onclick="getMember('bcc');" 
								--%>
								id="bccUserNames" style="width:70%;cursor: pointer;" value="${bccUserNames}" readonly="readonly"
						/>
						<%--
						&nbsp;<a onclick="clearMember('bcc');" class="mailBlueText">清空</a>
						--%>
						<input type="hidden" name="bccUserCds" id="bccUserCds" value="${bccUserCds}"/>
					</td>
				</tr>
			</c:if>
			<tr>
				<td >主题</td>
				<td>
					<input name="subject" id="subject" style="width:70%" value="${subject}"/>
					<span onmouseover="showImportDiv(this);" style="padding-left: 10px">
						<span  id="importLevelText"><span style="color:#0066CC">一般</span></span>
						<input type="hidden" id="importLevelCd" name="importLevelCd" value="1"/>
					</span>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="2">
					<div style="float:left;" id="addAttaFile" title="文件大小不能超过20M">
						<input class="mailFile" type="file" onchange="fileChange(this)" name="upload" hidefocus="true" size="1" />
					</div>
					<div style="float:left;">
						<a style="text-decoration: underline" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
						<%--
						&nbsp;&nbsp;&nbsp;
						<a style="text-decoration: underline" href="javascript:pasteFromCopy()" class="mailBlueText" title="每次发信前系统会自动保存内容，如果发送失败可重新进入点击此按钮">粘贴剪贴板内容</a>
						 --%>
					</div>
					<div style="float:left;" id="uploadAttaContainer">
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
					<div style="float:left;" id="attaContainer">
					</div>
				</td>
			</tr>
			<% if("1".equals(SpringSecurityUtils.getCurrentPlasUser().getCmailFlg())){ %>
			<tr style="display: none;">
				<td colspan="2">
					<input type="checkbox" checked="checked" name="send2OutEmail" value="1" />
					<span style="font-weight: bolder;color: #0167a2;">同时抄送到外部箱</span>
				</td>
			</tr>
			<%} %>
		</table>
	</div>
</div>
<div style="padding-left: 3px;">
	<textarea name="content" id="mailContent" style="width:98%;height:300px;" >${content}</textarea>
	<div id="divOriContent">${content}</div>
	<script language="javascript">
	 if(!isPhone()){
		$('#divOriContent').remove();
	 }else{
		 $('#mailContent').val('');
	 }
	</script>
</div>

<input type="hidden" id="uploadFlg" name="uploadFlg" value="0" />
<input type="hidden" name="bizModuleCd" value="email" />
<input type="hidden" name="bizEntityId" value="${entityTmpId}" />
<input type="hidden" name="uiid" value="<%=SpringSecurityUtils.getCurrentUiid() %>" />
<input type="hidden" name="ajaxFlg" value="true" />
<input type="hidden" name="filterType" value="all" />
<input type="hidden" id="mailFormPageSize" name="pageEmail.pageSize"/>
</s:if>
<!-- ***********************************************草稿箱************************************************ -->
<s:if test="boxId == 2">
<s:hidden name="entityTmpId"></s:hidden>
<input type="hidden" name="id" id="entityId" value="${oaEmailBody.oaEmailBodyId}"/>
<input type="hidden" id="entityIdAlias" value="${oaEmailBody.oaEmailBodyId}"/>
<input type="hidden" name="recordVersion" value="${oaEmailBody.recordVersion}"/>
<div style="padding:5px 0;">
	<security:authorize ifNotGranted="A_EMAIL_READ_ONLY">
		<div style="float:left;width:80px;">
			<div class="btn_blue_55_55" style="margin:0 0 5px 10px" onclick="sendMail();">发送</div>
			<div class="btn_green_55_20" style="margin-left:10px;" onclick="draftMail();">保存</div>
		</div>
	</security:authorize>
	<security:authorize ifAnyGranted="A_EMAIL_READ_ONLY">
		<div style="float:left;width:80px;">
			<div class="btn_blue_55_55" style="margin:2px 0 5px 10px" >发送(<font color="red">无效</font>)</div>
			<div class="btn_green_55_20" style="margin-left:10px;" >保存(<font color="red">无效</font>)</div>
		</div>
	</security:authorize>
	
	<div style="margin-left: 100px;">
		<table class="commonTable" style="width:98%;_width:90%;">
			<tr>
				<td width="12%" >收件人</td>
				<td >
					<input name="toUserNames" onclick="getMember('to');" id="toUserNames" style="width:70%;cursor: pointer;" value="<s:property value='oaEmailBody.toUserNames' />" readonly="readonly">
					&nbsp;<a onclick="clearMember('to');" class="mailBlueText">清空</a>
					<input type="hidden" id="toUserCds" name="toUserCds" value="<s:property value='oaEmailBody.toUserCds' />"/>
				</td>
			</tr>
			<tr><td colspan="2" class="mailBlueText">
					<span onclick="$(this).hide().next().show();$('#copyerText').show();">添加抄送人</span>
					<span onclick="$(this).hide().prev().show();$('#copyerText').hide();" style="display: none;">隐藏抄送人</span>
					<span onclick="$(this).hide().next().show();$('#bccText').show();">添加密送人</span>
					<span onclick="$(this).hide().prev().show();$('#bccText').hide();" style="display: none;">隐藏密送人</span>
				</td></tr>
				<tr id="copyerText" style="display:none">
					<td >抄送人</td>
					<td>
						<input name="copyUserNames" onclick="getMember('copy');" id="copyUserNames" style="width:70%;cursor: pointer;" value="<s:property value='oaEmailBody.copyUserNames' />" readonly="readonly"/>
						&nbsp;<a onclick="clearMember('copy');" class="mailBlueText">清空</a>
						<input type="hidden" name="copyUserCds" id="copyUserCds" value="<s:property value='oaEmailBody.copyUserCds' />"/>
					</td>
				</tr>
				<tr id="bccText" style="display:none">
					<td >密送人</td>
					<td>
						<input name="bccUserNames" onclick="getMember('bcc');" id="bccUserNames" style="width:70%;cursor: pointer;" value="<s:property value='oaEmailBody.bccUserNames' />" readonly="readonly"/>
						&nbsp;<a onclick="clearMember('bcc');" class="mailBlueText">清空</a>
						<input type="hidden" name="bccUserCds" id="bccUserCds" value="<s:property value='oaEmailBody.bccUserCds' />"/>
					</td>
				</tr>
			<tr>
				<td >主题</td>
				<td>
					<input name="subject" id="subject" style="width:70%" value="<s:property value='oaEmailBody.subject' />"/>
					<span onmouseover="showImportDiv(this);" style="padding-left: 10px">
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
					</span>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="2">
					<div style="float:left;" id="addAttaFile" title="文件大小不能超过20M">
						<input class="mailFile" type="file" onchange="fileChange(this)" name="upload" hidefocus="true" size="1" />
					</div>
					<div style="float:left;">
						<a style="text-decoration: underline" href="javascript:void(0)" class="mailBlueText" title="文件大小不能超过20M">添加附件</a>
						&nbsp;&nbsp;&nbsp;
						<a style="text-decoration: underline" href="javascript:pasteFromCopy()" class="mailBlueText" title="每次发信前系统会自动保存内容，如果发送失败可重新进入点击此按钮">粘贴剪贴板内容</a>
					</div>
					<div style="float:left;" id="uploadAttaContainer">
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
					<div style="float:left;" id="attaContainer">
					</div>
				</td>
			</tr>
			<% if("1".equals(SpringSecurityUtils.getCurrentPlasUser().getCmailFlg())){ %>
			<tr>
				<td colspan="2">
					<input type="checkbox" checked="checked" name="send2OutEmail" value="1" />
					<span style="font-weight: bolder;color: #0167a2;">同时抄送到外部箱</span>
				</td>
			</tr>
			<%} %>
			</table>
		</div>
</div>
<div style="padding-left: 3px;">
	<textarea name="content"  id="mailContent" style="width:98%;height:300px;" ><s:property value='oaEmailBody.content' /></textarea>
</div>

<input type="hidden" id="uploadFlg" name="uploadFlg" value="0" />
<input type="hidden" name="bizModuleCd" value="email" />
<input type="hidden" name="bizEntityId" value="${entityTmpId}" />
<input type="hidden" name="uiid" value="<%=SpringSecurityUtils.getCurrentUiid() %>" />
<input type="hidden" name="ajaxFlg" value="true" />
<input type="hidden" name="filterType" value="all" />
<input type="hidden" id="mailFormPageSize" name="pageEmail.pageSize"/>
</s:if>
</form>

<div id="importTipDiv" class="floatDiv">
	<div>
		<span style="color:#0066CC" value="1">一般</span>
		<span style="color:#FF6600" value="2">重要</span>
		<span style="color:#FF0000" value="3">非常重要</span>
	</div>
</div>
<script type="text/javascript">
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
<%--
<%if (SpringSecurityUtils.getCurrentUiid().equals("huangjian")){ %>
<script type="text/javascript">
	window.location.href=_ctx+'/login!logout.action';
</script>
<%}
--%>

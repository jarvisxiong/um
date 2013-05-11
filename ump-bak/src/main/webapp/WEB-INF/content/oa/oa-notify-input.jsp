<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<title>新增公告</title>
		<meta http-equiv="Content-Type" content="text/html" />
		<link rel="stylesheet" href="${ctx}/css/desk/deskCommon.css" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
		<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
		<script src="${ctx}/js/jquery.form.pack.js" type="text/javascript"></script>
		<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${ctx}/js/EasyWindow.js" type="text/javascript"></script>
		<script src="${ctx}/js/common.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
		<script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
		<script src="${ctx}/js/table.js" type="text/javascript"></script>
		<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/js/prompt/skin/qq/ymPrompt.css" />
		<script language="javascript">
			var tree;
			function buildDepts(){
				$("#notifyToDeptsDiv").empty();
				var selectedCds = $("#toDeptCds").val();
				$.post("${ctx}/oa/oa-notify!buildDepts.action?selectedDeptCds=" + selectedCds,
				 function(result) {
					if (result) {
						tree = new TreePanel({
							renderTo:'notifyToDeptsDiv',
							'root' : eval('('+result+')'),
							'ctx':'${ctx}'
						});
						tree.render();
					}
				});
			}
		
			function modContent(my_id){
			    var oFCKeditor = new FCKeditor(my_id) ;
			    oFCKeditor.BasePath = "${ctx}/js/FCKEditor/";
				oFCKeditor.Height = 300;
			    oFCKeditor.ReplaceTextarea();
			}
			function getToDeptCds() {
				ymPrompt.confirmInfo({
					icoCls:"",
					title:"选择发布范围",
					message:"<div id='deptDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
					useSlide:true,
					winPos:"t",
					width:350,
					height:400,
					maxBtn:true,
					allowRightMenu:true,
					handler: getSelectedDepts,
					afterShow:function(){
						$.post("${ctx}/oa/oa-notify!toDepts.action",function(result){
							$("#deptDiv").html(result);
						});
					}
				});
			}
			function getSelectedDepts(tp) {
				if (tp == "ok") {
					var value = "" + tree.getChecked("id");
					if (value.trim().length > 0) {
						// 如果选中了全部机构，则发布范围为all，不需要把所有的机构都列出来
						if (value.substring(0, 2) == '0,') {
							$("#toDeptCds").val("all");
							$("#toDeptNames").val("全部");
						} else {
							$("#toDeptCds").val("," + value + ",");
							$("#toDeptNames").val(tree.getChecked("text"));
						}
					} else {
						$("#toDeptNames").val("");
						$("#toDeptCds").val("");
					}
				}
			}
			function clearToDeptCds() {
				$("#toDeptNames").val("");
				$("#toDeptCds").val("");
			}
			// 上传附件，上传完毕后更新附件区域信息
			function uploadAttachment() {
				var uploadedAttach = $("#uploadInput").val();
				if (jQuery.trim(uploadedAttach).length == 0) {
					return false;
				}
				$("#attachmentForm").ajaxSubmit(function(result) {
					if (result) {
						refreshAttachment();
					}
				});
			}
			// 删除附件，删除完毕后更新附件区域内容
			function deleteAttachment(fileId, bizModuleCd, bizEntityId, filterType) {
				var url = "${ctx}/app/app-attachment!delete.action";
				$.post(url,
					{
						id:				fileId,
						bizModuleCd: 	"oaNotify",
						bizEntityId:	bizEntityId,
						filterType:		"pdf"
					},
					function(result) {
						if (result) {
							refreshAttachment();
						}
					}
				);
				return false;
			}
			// 更新附件区域信息
			function refreshAttachment() {
				$.get($("#attach_files_div").attr("href"),
					function(result) {
						$("#attach_files_div").html(result);
					}
				);
			}

			// 提交表单时进行校验，主题和附件是必输项
			function validateInput(handleId) {
				var subject = $("#subject").val();
				var entityId = $("#inputFrom input:hidden[name='id']").val();
				switch (handleId) {
					case "saveBtn":
						if (jQuery.trim(subject).length == 0) {
							alert("请输入主题内容!");
							$("#subject").focus();
							return false;
						}
						if (jQuery.trim($("#notifyAttachmentId").val()).length == 0) {
							alert("请先上传附件然后保存!");
							return false;
						}
						break;
					case "backBtn":
						if (jQuery.trim(entityId).length > 0) {
							if (jQuery.trim($("#notifyAttachmentId").val()).length == 0) {
								alert("请先上传附件然后返回!");
								return false;
							}
						}
						break;
					default: break;
				}
				
				return true;
			}

			$(function() {
				refreshAttachment();
			});
		</script>
	</head>

	<body>
		<div class="main">
			<div id="input_div">
				<div>
					<div class="corner_top_left"></div>
					<div class="corner_top_right"></div>
					<div class="corner_top_middle"></div>
				</div>
			
				<div class="container" style="border: thin solid #F6F6F6;">
						<div id="note_panel">
							<s:if test="%{oaNotifyId == null}">
								<s:text name="common.create" />
							</s:if>
							<s:else>
								<s:text name="common.edit" />
							</s:else>
						</div>
						
						<table cellpadding="0" cellspacing="0" style="margin: 0px auto; width: 100%; height: 400px; table-layout: fixed;" >
							<s:form id="inputFrom" action="oa-notify!save.action" method="post" cssClass="inputFrom" >
								<input type="hidden" name="id" value="${oaNotifyId}" />
								<s:hidden name="recordVersion"/>
								<s:hidden name="entityTmpId"/>
								<input type="hidden" id="notifyAttachmentId" name="notifyAttachmentId" value="" />
								
								<tr>
									<td width="20%" class="label" height="12%" valign="middle" align="right"><s:text name="oaOaNotify.subject"/>：&nbsp;&nbsp;</td>
									<td width="80%" height="12%" valign="middle"><s:textfield id="subject" name="subject" size="60"/></td>
								</tr>
								<tr>
									<td class="label" height="12%" valign="middle" align="right"><s:text name="oaOaNotify.toDeptCd"/>：&nbsp;&nbsp;</td>
									<td height="12%" valign="middle">
										<s:textfield  id="toDeptNames" name="toDeptNames" size="60" readonly="true" />
										<button class="anniu_lan_2" onclick="getToDeptCds(); return false;">选择</button>
										<button class="anniu_lan_2" onclick="clearToDeptCds(); return false;">清空</button>
										<input type="hidden" name="toDeptCds" id="toDeptCds" value="${toDeptCds}"/>
									</td>
								</tr>
								<tr>
									<td class="label" height="12%" valign="middle" align="right"><s:text name="oaOaNotify.sendTime"/>：&nbsp;&nbsp;</td>
									<td height="12%" valign="middle">
										<s:textfield  id="sendTime" name="sendTime" onfocus="WdatePicker()" cssClass="Wdate" />
									</td>
								</tr>
								<tr>
									<td align="right" class="label" height="12%">有效时间：&nbsp;&nbsp;</td>
									<td height="12%">
										<s:textfield  id="startDate" name="startDate" onfocus="WdatePicker()" cssClass="Wdate" />
										<span class="label">至</span>
										<s:textfield  id="endDate" name="endDate" onfocus="WdatePicker()" cssClass="Wdate" />
									</td>
								</tr>
								<tr>
									<td align="right" class="label" height="12%"><s:text name="oaOaNotify.topFlg" />：&nbsp;&nbsp;</td>
									<td class="label" height="12%">
										<input type="radio" value="1" name="topFlg" <c:if test="${topFlg == '1'}">checked="checked"</c:if>>是</input>
										<input type="radio" value="0" name="topFlg" <c:if test="${topFlg == '0'}">checked="checked"</c:if>>否</input>
									</td>
								</tr>
								
								<tr>
									<td align="right" class="label" height="12%">是否可用：&nbsp;&nbsp;</td>
									<td class="label" height="12%">
										<s:radio list="mapEnabledFlg" name="enabledFlg" listKey="key" listValue="value" value="enabledFlg==null?0:enabledFlg" />
									</td>
								</tr>
							</s:form>
							
							<tr  align="left">
								<td class="label" align="right"><s:text name="appAttachment.title"/>：&nbsp;&nbsp;</td>
								<td>
									<s:url id="notifyAttachment" value="oa-notify!attachment.action">
										<s:param name="bizEntityId">${oaNotifyId==null ? entityTmpId : oaNotifyId}</s:param>
									</s:url>
									<div id="attach_files_div" href="${notifyAttachment}">
									</div>
								</td>
							</tr>
							
							<tr>
						   		<td colspan="2" align="center" height="10%">
							        <hr style="border:1px solid #FFF; margin: 0px 0px 5px 0px;"  />
							        <button class="anniu_lan_2" id="saveBtn" onclick="if(validateInput(this.id)){$('#inputFrom').submit();} return false;"><s:text name="common.save"/></button>
							        <button class="anniu_lan_2" onclick="document.getElementById('inputFrom').reset(); return false;"><s:text name="common.reset"/></button>
							        <button class="anniu_lan_2" id="backBtn" onclick="if(validateInput(this.id)){window.location.href='oa-notify.action';} return false;"><s:text name="common.return"/></button>
						      	</td>
					       </tr>
						</table>
				</div>
			
				<div>
					<div class="corner_bottom_left"></div>
					<div class="corner_bottom_right"></div>
					<div class="corner_bottom_middle"></div>
				</div>
			</div>
		</div>
	</body>
</html>

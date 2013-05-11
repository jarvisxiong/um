<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>管理区域</title>
		<meta http-equiv="Content-Type" content="text/html" />
		<link rel="stylesheet" href="${ctx}/css/desk/deskCommon.css" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
		<script src="${ctx}/js/jquery.form.pack.js" type="text/javascript"></script>
		<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
		<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/js/xheditor/xheditor-zh-cn.js"></script>
		<script src="${ctx}/js/common.js" type="text/javascript"></script>
		<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
		<script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
		<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/js/prompt/skin/qq/ymPrompt.css" />
		<script language="javascript">
			var tree;
			function buildDepts(){
				$("#newsToDeptsDiv").empty();
				var selectedCds = $("#toDeptCds").val();
				$.post("${ctx}/oa/oa-news!buildDepts.action?selectedDeptCds=" + selectedCds,
				 function(result) {
					if (result) {
						tree = new TreePanel({
							renderTo:'newsToDeptsDiv',
							'root' : eval('('+result+')'),
							'ctx':'${ctx}'
						});
						tree.render();
					}
				});
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
						$.post("${ctx}/oa/oa-news!toDepts.action",function(result){
							$("#deptDiv").html(result);
						});
					}
				});
			}
			function getSelectedDepts(tp) {
				if (tp == "ok") {
					$("#toDeptNames").val(tree.getChecked("text"));
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
				$("#toDeptCds").val(" ");
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
			function deleteAttachment(fileId, bizModuleCd, bizEntityId) {
				var url = "${ctx}/app/app-attachment!delete.action";
				$.post(url,
					{
						id:				fileId,
						bizModuleCd: 	"oaNews",
						bizEntityId:	bizEntityId
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
			function validateInput() {
				var s = $("#subject");
				if (jQuery.trim(s.val()).length == 0) {
					alert("请输入主题");
					s.focus();
					return false;
				}

				var typeCd = $("#typecd_select").children("option:selected").val();
				if (jQuery.trim(typeCd).length == 0) {
					alert("请选择新闻类型");
					return false;
				}
				
				return true;
			}
			$(function() {
				refreshAttachment();
				$("#content").xheditor({});
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
						<s:if test="%{oaNewsId == null}">
							<s:text name="common.create" />
						</s:if>
						<s:else>
							<s:text name="common.edit" />
						</s:else>
					</div>
					<table style="margin: 0px auto; padding: 0px; width: 100%;">
						<s:form id="inputFrom" action="oa-news!save.action" method="post" cssClass="inputFrom" >
							<input type="hidden" name="id" value="${oaNewsId}" />
							<s:hidden name="recordVersion"/>
							<s:hidden name="entityTmpId"/>
							
							<tr>
								<td width="80" class="label"><s:text name="oaOaNews.typeCd" />:</td>
								<td><s:select id="typecd_select" list="mapNewsType" listKey="key" listValue="value" name="typeCd" cssStyle="width:100px;"/></td>
								<td width="80" class="label"><s:text name="oaOaNews.newsTime"/>:</td>
								<td ><s:textfield  id="newsTime" name="newsTime" onfocus="WdatePicker()" cssClass="Wdate" /></td>
							</tr>
							<tr align="left">
								<td width="80" class="label"><s:text name="oaOaNews.toDeptCd"/>:</td>
								<td colspan="3">
									<s:textfield  id="toDeptNames" name="toDeptNames" size="60" readonly="true" />
									<button class="anniu_lan_2" onclick="getToDeptCds(); return false;">选择</button>
									<button class="anniu_lan_2" onclick="clearToDeptCds(); return false;">清空</button>
									<input type="hidden" name="toDeptCds" id="toDeptCds" value="${toDeptCds}"/>
								</td>
							</tr>
							<tr align="left">
								<td class="label"><s:text name="oaOaNews.subject"/>:</td>
								<td colspan="3"><s:textfield id="subject" name="subject" size="80"/></td>
							</tr>
							<tr align="left">
								<td class="label">是否可用:</td>
								<td colspan="3">
									<span class="label">
										<s:radio list="mapEnabledFlg" name="enabledFlg" listKey="key" listValue="value" value="enabledFlg==null?0:enabledFlg" />
									</span>
								</td>
							</tr>
							<tr  align="left">
								<td class="label"><s:text name="oaOaNews.content"/>:</td>
								<td  colspan="3" >
									<s:textarea id="content" name="content"  cssStyle="width:100%;height:200px;"/>
								</td>
							</tr>
						</s:form>
						
						<tr  align="left">
							<td class="label"><s:text name="appAttachment.title"/>：</td>
							<td colspan="3">
								<s:url id="newsAttachment" value="oa-news!attachment.action">
									<s:param name="bizEntityId">${oaNewsId == null ? entityTmpId : oaNewsId}</s:param>
								</s:url>
								<div id="attach_files_div" href="${newsAttachment}">
								</div>
							</td>
						</tr>
						
						<tr>
							<td colspan="3" align="right" >
								<hr style="border:1px solid #FFF; margin: 0px 0px 5px 0px;"  />
								<button class="anniu_lan_2" onclick="if(validateInput()){$('#inputFrom').submit();} return false;"><s:text name="common.save"/></button>
								<button class="anniu_lan_2" onclick="document.getElementById('inputFrom').reset(); return false;"><s:text name="common.reset"/></button>
								<button class="anniu_lan_2" onclick="window.location.href='${ctx}/oa/oa-news.action'; return false;"><s:text name="common.return"/></button>
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
		<script type="text/javascript">
			$(".anniu_lan_2").each(function() {
				$(this).mouseover(function() {
					$(this).removeClass("anniu_lan_2").addClass("anniu_lan_2_an");
				});
				$(this).mouseout(function() {
					$(this).removeClass("anniu_lan_2_an").addClass("anniu_lan_2");
				});
			});
		</script>
	</body>
</html>

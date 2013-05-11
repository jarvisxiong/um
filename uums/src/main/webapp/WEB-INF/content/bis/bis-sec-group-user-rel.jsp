<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>配置加密组</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link   type="text/css"        rel="stylesheet" href="${ctx}/css/common.css" />
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	
	<!-- TreePanel -->
	<link   type="text/css"        rel="stylesheet" href="${ctx}/css/TreePanel.css" />
	<script type="text/javascript" src="${ctx}/js/TreePanel.js"></script>
 
	<link   type="text/css"        rel="stylesheet" id='skin' href="${ctx}/js/prompt/skin/qq/ymPrompt.css" />
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js" ></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js" ></script>

	<link   type="text/css"        rel="stylesheet" href="${ctx}/css/shadeLayer.css" />
	<script type="text/javascript" src="${ctx}/js/shadeLayer.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/plValidate.js"></script>
	
</head>

<body>
	<table style="table-layout: fixed;width: 100%;margin:5px;" >
		<tr>
			<td style="width:280px;height: 430px;border: 1px;" valign="top">
				<table style="margin: 5px;">
					<tr>
						<td valign="top">
							<div id="content" style="overflow-x:hidden; overflow-y: auto;height:430px;">
						 		<div id="group_list_panel" style="float:left;">
									
						 		</div>
							</div>
							<input type="button" class="buttom"  onclick="addGroup();" style="font-weight: bold;width:60px;" value="添加新组"/>
						</td>
					</tr>			
				</table>
			</td>
			<td valign="top" style="background-color: white;">
				<fieldset>
				<table align="left" style="widht:100%;">
				<tr>
					<td colspan="2" style="text-align: left;">
						<!-- 选择Group -->
						<div style="display: inline;font-weight: bold;" id="currentSelectGroupName"></div>
						<div style="display: inline;font-weight: bold;display:none;" id="currentSelectGroupId"></div>
						
						<!-- 操作提示-->
						<div style="display: inline;font-weight: bold;float:right;" id="operate_result_tip"></div>
					</td>
				</tr>
				<tr>
					<td valign="top"  style="padding: 10px;">
						<div id="group_detail_panel"></div>
					</td> 
					<td valign="top">
						<div id="group_user_panel"  style="width:300px;padding: 10px;height: 430px; overflow-x:auto; overflow-y: auto;"></div>
					</td>
				</tr>
				</table>
				</fieldset>
			</td> 
	</tr>
	<tr>
		<td colspan="2" style="text-align: right;padding-right: 20px;">
			<input id="btnSaveBatchGroupUsers" type="button" class="buttom" onclick="saveBatchGroupUsers()" value="保存批量组用户"></input>
		</td>
	</tr>
	</table>
		
	<script language="javascript">

		loadGroupPanel();
		$("#btnSaveBatchGroupUsers").attr("disabled",true);

		
	
		//机构用户树
		var treeUser;
		var addUserIds = "";
		var delUserIds = "";
		
		function setGroupId(secGroupId,secGroupName){
			$("#currentSelectGroupId").html(secGroupId);
			//$("#currentSelectGroupName").html("<b color='red'>" + secGroupName + "</b>");
		}
		
		function getGroupId(){
			return $("#currentSelectGroupId").html();
		}

		//查看机构
		function viewGroup(secGroupId,secGroupName){
			setGroupId(secGroupId,secGroupName);
			loadTreeUser(secGroupId);
		}
	   
		//新建组
		function addGroup(){
			$("#group_detail_panel").html('<span><image src="${ctx}/images/loading.gif"/>新建组 中...</span>');
			var url = "${ctx}/sec/sec-group!input.action";
			$.post(url, function(result) {
				if(result){
					$("#group_detail_panel").html(result).show();
					$("#group_user_panel").html('');
				}
			});
		}

		//加载组列表
		function loadGroupPanel(tagTypeCd){
			$("#group_list_panel").html('<span><image src="${ctx}/images/loading.gif"/>刷新组列表 中...</span>');
			var url = "${ctx}/sec/sec-group!list.action";

			if(typeof(tagTypeCd)!="undefined" && $.trim(tagTypeCd) != ''){
				url = url+ "?tmpTagTypeCd="+tagTypeCd;
			}
			$.post(url, function(result) {
				if(result){
					$("#group_list_panel").html(result).show();

					//清空右边区域
					$("#group_detail_panel").html('');
					$("#group_user_panel").html('');
				}
			});
		}
 
		//加载机构人员树
		function loadTreeUser(secGroupId){

			$("#btnSaveBatchGroupUsers").attr("disabled",true);
			$("#operate_result_tip").html("");
			
			//组明细
			$("#group_detail_panel").html('<span><image src="${ctx}/images/loading.gif"/>查询 组明细 中...</span>');
			var url = "${ctx}/sec/sec-group-user-rel!getGroupDetail.action?secGroupId="+secGroupId;
			$.post(url, function(result) {
				if(result){
					$("#group_detail_panel").html(result).show();
				}
			});

			//机构人员树
			$("#group_user_panel").html('<span><image src="${ctx}/images/loading.gif"/>查询 分配组员情况...</span>');
			var url2 = "${ctx}/sec/sec-group-user-rel!getDispatchedOrgUserNode.action?secGroupId="+secGroupId;
			$.post(url2, function(result) {
				$("#group_user_panel").html('');
				if(result){
					treeUser = new TreePanel({
						renderTo: "group_user_panel",
						'root'  : eval('('+result+')'),
						'ctx'	:'${ctx}'
					}); 
					treeUser.render();  
					treeUser.on(function(node){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					});
					$("#btnSaveBatchGroupUsers").attr("disabled",false);
				}
			});
		}

		// 保存授权情况
		function saveBatchGroupUsers(){

			//检查是否选择角色
			var secGroupId = getGroupId();
			if(!secGroupId){
				return;
			}

			//确认保存
			ymPrompt.confirmInfo({
				icoCls:"",
				title:"确认保存?",
				message:"<div id='modifyNodeInfo' style='padding:10px;'></div>",
				useSlide:true,
				winPos:"t",
				width:360,
				height:400,
				maxBtn: false,
				allowRightMenu:true,
				handler:confirmSave,
				afterShow:function(){ 
					$("#modifyNodeInfo").html(getPopDiv(treeUser));
				}
			});
			
		}

		//确认保存授权
		function confirmSave(tp){
			if("ok" == tp){
				
				var secGroupId = getGroupId();
				var addDels = getAddDeleteIds()
				
				var addUserIds = addDels[0];
				var delUserIds = addDels[2];

				if((addUserIds == '') && (delUserIds == '') ){
					alert("请选择新增或移除人员");
					return;
				}

				$("#btnSaveBatchGroupUsers").attr("disabled",true);
				$("#operate_result_tip").html('<span><image src="${ctx}/images/loading.gif"/>保存设置中...</span>');
				var url  = "${ctx}/sec/sec-group-user-rel!saveBatchGroupUsers.action";
				var params = {
						secGroupId:secGroupId,
						addUserIds:addDels[0],
						addUserTexts:addDels[1],
						delUserIds:addDels[2],
						delUserTexts:addDels[3],
						addExtParams:addDels[4],
						delExtParams:addDels[5]
				};
				$.post(url,params, function(result) {
					if(result == 'true'){
						$("#operate_result_tip").html("<font color='green'>保存成功!</font>").show();
						loadTreeUser(secGroupId);
					}else{
						$("#operate_result_tip").html("<font color='red'>保存失败!</font>").show();
					}
				});
			}
		}


		//返回html片段
		function getPopDiv(treeUser){
			var modifyNodes = treeUser.getModifiedLeafNodes('0');//0-用户
			var addIds   = modifyNodes[0];
			var addTexts = modifyNodes[1];
			var delIds 	 = modifyNodes[2];
			var delTexts = modifyNodes[3];

			//新增部分
			var addDiv   = new Array();
				addDiv.push("<table style='width:80px;'>");
			for(var i=0; i<addIds.length; i++){
				addDiv.push("<tr><td valign='top' id='"+addIds[i]+"'>"+addTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
			}
				addDiv.push("</table>");

			//删除部分
			var delDiv  = new Array();
				delDiv.push("<table style='width:80px;'>");
			for(var i=0; i<delIds.length; i++){
				delDiv.push("<tr><td valign='top' id='"+delIds[i]+"'>"+delTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
			}
				delDiv.push("</table>");

			//弹出框
			var popDiv = new Array();
			popDiv.push("<table style='width:300px;' >");
			popDiv.push("	<tr><th style='text-align:center;'>本次新增人员如下</th><th style='text-align:center;'>移除人员如下</th></tr>" );
			popDiv.push("	<tr><td valign='top'><div>"+ addDiv.join("")+"</div></td><td><div>"+ delDiv.join("")+"</div></td></tr>");
			popDiv.push("</table>");

			return popDiv.join("");
		}

		//获取授权或收回的用户IDs
		function getAddDeleteIds(){
			var modifyNodes = treeUser.getModifiedLeafNodes('0');//0-用户
			var addIds   = modifyNodes[0];
			var addTexts = modifyNodes[1];
			var delIds 	 = modifyNodes[2];
			var delTexts = modifyNodes[3];
			var addExtParams = modifyNodes[4];
			var delExtParams = modifyNodes[5];
 
			
			return [addIds.join(","),addTexts.join(","),delIds.join(","),delTexts.join(","),addExtParams.join(","),delExtParams.join(",")];
		}

		//保存组
		function mysubmit(){
			var pdv = new PlValidate("inputForm");
			if (pdv.validate()) {
				TB_showMaskLayer('正在执行...');
				$("#inputForm").ajaxSubmit(function(result) {
					if (result) {
						TB_removeMaskLayer();
						if(result.split(",").length == 3){
							var idNames = result.split(",");
							if('success' == idNames[0]){
								//alert(result);
								//viewGroup(idNames[1],idNames[2]);
								document.location="${ctx}/sec/sec-group-user-rel.action";
							}else{
								alert("failure:"+result);
							}
						}else{
							alert("failure:"+result);
						}
					}
				});
			}else{
				alert("请确认输入项!");
			}
			TB_removeMaskLayer();
		}

		//编辑组
		function editGroup(secGroupId){
			var url  = "${ctx}/sec/sec-group!input.action?id="+secGroupId;
			$.post(url, function(result) {
				if(result){
					$("#group_detail_panel").html(result).show();
				}
			});
		}
		
		//删除组,同时解除组与成员关系
		function deleteGroup(secGroupId){
			if(window.confirm("此操作不可恢复,您确认要删除组?")){
				var url  = "${ctx}/sec/sec-group!delete.action?id="+secGroupId;
				$.post(url, function(result) {
					if(result == 'true'){
						$("#operate_result_tip").html("<font color='green'>删除组成功!</font>").show();
						loadGroupPanel();
					}else{
						alert("删除组失败:" + result);
					}
				});
			}
		}

		//切换组
		function changeTagType(tagTypeCd){
			loadGroupPanel(tagTypeCd);
		}
		//返回
		function gotoHome(){
			loadGroupPanel();
		}
	</script>
</body>
</html>

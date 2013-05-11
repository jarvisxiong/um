<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type=text/javascript src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript">
getUsersTree();
function getUsersTree(){
	$.post("${ctx}/fin/fin-project!getUsersTree.action", function(result){
		var tree = new TreePanel({
			renderTo:'userDiv',
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.on(function(node){
			$("#ProUserCd").val(node.attributes.entityCd);
			$("#ProUserName").val(node.attributes.text);
			$.post("${ctx}/fin/fin-project!getUserProject.action", {userCd:node.attributes.entityCd}, function(result){
				if(result!=""){
					var obj=eval(result);
					for(var i=0;i<obj.length;i++){
						var chk =obj[i];
						if(chk[1]=="1"){
							$("#chk_all_"+chk[0]).attr("checked","checked"); 
						}else{
							$("#chk_all_"+chk[0]).attr("checked",""); 
						}
					}
				}
			});
		});
		tree.render();
	});
}
//点击全选按钮
function checkedAll(flag){
	$(".chkClass").attr("checked",flag);
}
//保存所設置的用戶權限
function saveProjectUser(){
	var checkbox_ids = new Array();
	$("input[type=checkbox][class='chkClass']:checked").each(function(i, dom) {
		checkbox_ids.push($(dom).val());
	});
	if($("#ProUserCd").val()!=""){
		var userCd =$("#ProUserCd").val();
		param={userProject:checkbox_ids,userCd:userCd,isUser:"isUser"};
		$.post("${ctx}/fin/fin-project!projectUsersSave.action", param, function(result){
			$("#main_div").html(result);
		});
		//self.location ="${ctx}/fin/fin-project!projectUsersSave.action?userProject="+checkbox_ids+"&userCd="+userCd; 
	}
	
}
</script>
</head>
<body>
<div style="float:left;height:100%;width:200px;margin-left:2px;">
	<div id="userDiv">
	</div>
</div>
<div id="main_div" class="mailRight" style="margin-left:200px;margin-right:10px;height:100%;padding: 0 3px;">
<%@include file="fin-project-user.jsp" %>
</div>
</body>
</html>
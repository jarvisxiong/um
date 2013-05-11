<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link href="${ctx}/resources/css/common/TreePanel.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/css/desk/oa-meeting.css" type="text/css" />
<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script> 
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script language="javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/tab.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type=text/javascript src="${ctx}/js/common.js"></script>
<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
<script type="text/javascript">
function getUserTree(){
	var selectId ="";
	var selectName ="";
	var selectType ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"用户",
		message:"<div id='userDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		afterShow:function(){
			$.post("${ctx}/fin/fin-project!getUserTree.action", function(result){
				var tree = new TreePanel({
					renderTo:"parentDiv",
					'root' : eval('('+result+')'),
					'ctx':'${ctx}'
				});
				tree.on(function(node){
					selectId =node.attributes.id;
					selectName =node.attributes.text;
					selectType =node.attributes.finType;
				});
				tree.render();
			});
	},
	handler:function(e){
		if("ok"==e){
			$("#parentCd").val(selectId);
			$("#parentName").val(selectName);
			$("#itemType").val(selectType);
		}
	}
	});
}
function saveProject(){
	if($("#sequenceNo").val()==""){
		alert("请输入序号");
		return false;
	}
	if($("#projectCd").val()!=""&&$("#projectName").val()!="")
	   $("#inputForm").submit(); 
}
</script>
</head>
<body>
<div>
<table>
<tbody>
<s:form id="inputForm" action="fin-project!save.action" method="post">
<s:hidden id="id" name="id" />
<tr>
<td>编号</td>
<td><s:textfield  id="projectCd" name="projectCd"/></td>
</tr>
<tr>
<td>项目名称</td>
<td><s:textfield  id="projectName" name="projectName" cssStyle="width:250px"/></td>
</tr>
<tr>
<td>序号</td>
<td><s:textfield  id="sequenceNo" name="sequenceNo"/></td>
</tr>
<tr>
<td colspan="2"> <s:checkbox name="publicFlg">是否是上市</s:checkbox> </td>
</tr>
</s:form>
<tr>
<td><button type="button" id="SaveBtn" class="btn_list_add" onclick="saveProject();">保存</button></td>
<td><button type="button" id="ReturnBtn" class="btn_list_add" onclick="self.location='${ctx}/fin/fin-project!list.action'">返回</button>
</td>
</tr>
</tbody>
</table>
</div>
</body>
</html>
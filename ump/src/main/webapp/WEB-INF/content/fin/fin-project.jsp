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
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript">
function addProject(){
	self.location="${ctx}/fin/fin-project!input.action";
}
function addUsers(){
	self.location ="${ctx}/fin/fin-project!addProjectUsers.action";
}
function copyUsers(){
	self.location ="${ctx}/fin/fin-project!copyUserPermission.action";
}
function doAttent(id){
	if(id!=""){
		self.location="${ctx}/fin/fin-project!input.action?id="+id;
	}else{
		alert("no id");
	}
}
</script>
</head>
<body>
<div>
<button type="button" id="AddBtn" class="btn_list_add" onclick="addProject();">新增项目</button>
&nbsp;
<button type="button" id="AddUserBtn" class="btn_list_add" onclick="addUsers();">用户授权</button>
&nbsp;
<button type="button" id="AddUser2Btn" class="btn_list_add" onclick="copyUsers();">复制用户</button>
</div>
<div id="TableDiv">
 <table class="content_table">
	<thead>
			<th align="left">编号</th>
			<th align="left" >项目名称</th>
			<th align="left">用户</th>
	</thead>
	<tbody>
	<s:iterator value="page.result">
	  <tr class="mainTr" style="cursor:pointer;" onclick="doAttent('${finProjectId}');">
	   <td>${projectCd}</td>
	   <td>${projectName}</td>
	   <td><p:code2name mapCodeName="mapUserCds" value="userCds" /></td>
	  </tr>
	</s:iterator>
	</tbody>
 </table>
 </div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/fin/fin-project-copyUser.css"/>
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript">
function saveCopy(){
	if($("#copyUser").val()==""){
		alert("请选择原用户");
		return false;
	}
	if($("#targetUser").val()==""){
		alert("请选择目标用户");
		return false;
	}
	$("#inputForm").ajaxSubmit(function(result){
		if("ok"==result){
			alert("用户授权复制成功");
		} else {
			alert("用户授权复制失败!");
		}
	});
}
</script>
</head>
<body>
<div>
<table style="width: 350px; height: 160px;">
<tbody class="tbody">
<s:form id="inputForm" action="fin-project!saveCopy.action" method="post">
<tr>
<td>原用户</td>
<td><s:textfield id="copyUser" name="copyUser" readonly="true"/>
<s:hidden name="copyUserCd"></s:hidden> </td>
<script language="javascript">$('#copyUser').ouSelect({muti: false, showGroupFlg : true});</script>
</tr>
<tr>
<td>目标用户</td>
<td><s:textfield id="targetUser" name="targetUser" readonly="true"/>
<s:hidden name="targetUserCd"></s:hidden></td>
<script language="javascript">$('#targetUser').ouSelect({muti: false, showGroupFlg : true});</script>
</tr>
<tr>
<td>&nbsp;</td>
<td><s:radio name="copyType" list="#{'0':'新增','1':'覆盖'}" value="%{'0'}"></s:radio></td>
</tr>
</s:form>
<tr>
<td>&nbsp;</td>
<td><button type="button" id="SaveBtn" class="btn_copy_save" onclick="saveCopy();">保 存</button>
<button type="button" id="ReturnBtn" class="btn_copy_cancel" onclick="self.location='${ctx}/fin/fin-project!list.action'">返 回</button>
</td>
</tr>
</tbody>
</table>
</div>
</body>
</html>
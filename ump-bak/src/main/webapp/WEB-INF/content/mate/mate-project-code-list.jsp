<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>权限分配</title>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
</head>
<body>

<div class="title_bar">
	<div class="banTitle" style="display:inline;margin-left:10px;">甲供料权限分配</div>
	<div class="fRight" style="display:inline;">
		<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
		<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
	</div>
</div>
<div id="TableDiv">
 <table class="content_table" style="width:100%;">
	<thead>
	  <tr>
		<th width="40px" style="background:none">编号</th>
		<th width="30%">项目名称</th>
		<th width="50%">用户</th>
		<th>操作</th>
	 </tr>
	</thead>
	<tbody>
	<s:iterator value="page.result">
	  <tr class="mainTr" style="cursor:pointer;">
	   <td>${projectCd}</td>
	   <td>${projectName}</td>
	   <td>
	       <input type="hidden"  id="id_${projectCd}" name="id" value="${contProjectCodeId}"/> 
		   <input id="${projectCd}UserNames" type="text" class="member" style="width:85%;" readonly="readonly" value="<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("mateAuthority"),";") %>"/>
		   <input id="${projectCd}UserCds" type="hidden" name="mateAuthority" value="${mateAuthority}"/>
	   </td>
	   <td><input type="button" class="btn_new btn_green_new" onclick="doProjectSave('${projectCd}');" value="保存" /></td>
	  </tr>
	</s:iterator>
	</tbody>
 </table>
 </div>
 
<script type="text/javascript">
function doProjectSave(projectCd){
	var id=$("#id_"+projectCd).val();
	var userCds =$("#"+projectCd+"UserCds").val();
	$.post("${ctx}/mate/mate-project-code!save.action",{id:id,userCds:userCds},
		function(result){
	      if("ok"==result){
	    	  alert("保存成功");
	      }else{
	    	  alert("保存失败");
	      }
	});
}
$(function(){
	$(".member").ouSelect();
});
</script>
</body>
</html>
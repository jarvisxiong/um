<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis-shop.js"></script>
</head>
<body>
<style type="text/css">
.btn_red_50_20{
	width: 50px;
	height: 20px;
	line-height: 22px;
	background-color:rgb(172, 39, 39);
	color: #FFF;
	cursor:pointer;
	text-align: center;
}
</style>
<div style=" height:10%;">
 <table width="100%">
   <tr style="padding: 10px;">
						<td align="left" height="10" style="padding: 5px;">
							<button name="add" title="新增" class="btn_blue" onclick="showSortAdd();">新增</button>&nbsp;				
							<button name="save" title="保存" class="btn_green_55_20" onclick="doSortSave();">保存</button>&nbsp;
							<button name="del" title="删除" class="btn_red_35_20" onclick="doSortDelete();">删除</button>
						</td>
						<td align="center"><font color="red">${msg}</font>
						</td>
					</tr>
 </table>
</div>
<div style="width:200px; height:85%; border:1px solid #80AB73; position:absolute;">
<div id="sortDiv" style="height: 100%; width: 200px; overflow-y: auto;overflow-x:hidden"></div>
</div>
<div id="itemRight" style="margin-left:230px;margin-right:10px;padding: 0 3px;">
<form id="sortForm" method="post">
<table cellpadding="1" cellspacing="1">
<tr style="height:30px;"><td>类型名称：</td>
<td><s:textfield  id="sortName" name="sortName" /> <s:hidden id="id" name="id" /> </td></tr>
<tr style="height:30px;"><td>上级类型：</td>
<td><input type="text" id="parentName" name="parentName" onclick="doParentSort();" readonly="readonly"/> 
    <s:hidden id="parentId" name="parentId" /> 
</td></tr>
<tr style="height:30px;"><td>序号：</td><td><s:textfield  id="sequenceNo" name="sequenceNo" /></td></tr>
<tr style="height:30px;display:none;"><td>类别：</td><td><s:textfield  id="sortType" name="sortType" />
（是否能删除类别，若类别为1，则不允许删除）
</td>
</tr>
</table>
</form>
</div>
<script type="text/javascript">
$(function(){
	loadSortTree(false,false,false,"sortDiv");
});
</script>
</body>
</html>
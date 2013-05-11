<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<title>网批选项</title>

<link href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/resources/js/jquery-easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/css/email/email.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"  ></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/res/res-option.js"></script>
</head>

<body style="background-color: #E4E7EC;">
   <div class="mailTop" style="height:40px;">
	<form id="searchForm" action="res-option" method="post">
     	选项的名称:<input name="optionName" id="optionName" value="${optionName}" />
     	选项的属性名:<input name="optionIdName" id="optionIdName" value="${optionIdName}" />
     	选项的类型:<select name="optionType" id="optionType" value="${optionType}">
	     				<option value="" selected="selected">--全部--</option>
	     				<option value="1">单行文本框</option>
	     				<option value="2">多行文本框</option>
	     				<option value="3">HTML编辑器</option>
	     				<option value="4">单选</option>
	     				<option value="5">多选</option>
     				</select>
 	  	<a href="javascript:void(0);" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">搜索</a> 
 	  	<a href="javascript:void(0);" id="btn" iconCls="icon-add" class="easyui-linkbutton" onclick="editrow('');">新增</a> 
	 </form>
	</div>
	
	<div id="mailBottom" style="height:400px;background-color: #ffffff;">
		<table id="tt" fit="true"
				 singleSelect="true" rownumbers="true"
				idField="resOptionId" url="${ctx}/res/res-option!list.action">
			
		</table>
	</div>
	
	<div id="w" class="easyui-window" style="top:10px;width:650px;height:450px;">
	</div>
</body>
</html>

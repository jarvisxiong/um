<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" href="${ctx}/resources/css/biz/biz-rela.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
</head>
<body>
<form action="${ctx}/biz/biz-rela-lib!delList.action" method="post" id="bizRelaDelForm">
<input type="hidden" id="recordVersion" name="recordVersion" value="${recordVersion}"/>
<div id="div_main_cont" style="margin-left: 0px;">
	 <fieldset style="padding-left: 0px;padding-right: 0px; ">
	 <legend>&nbsp;&nbsp;
	 类别：<s:select style="width:65px;" list="@com.hhz.ump.util.DictMapUtil@getMapRelaType()" listKey="key" listValue="value" id="relaTypeCd" name="queRelaTypeCd" ></s:select>
	 &nbsp;&nbsp;省份：<input style="width: 50px;" type="text"  id="relaProvince" name="queRelaProvince"/>
	 &nbsp;&nbsp;地区：<input style="width: 50px;" type="text" id="relaArea" name="queRelaArea"/>
	 &nbsp;&nbsp;姓名：<input style="width: 50px;" type="text" id="relaName" name="queRelaName"/>
	 &nbsp;&nbsp;单位：<input style="width: 50px;" type="text" id="relaUnit" name="queRelaUnit"/>
	 <security:authorize ifAnyGranted="A_BIZ_RELA_QUERY,A_BIZ_RELA_SUP,A_BIZ_RELA_ADMIN">
	 <input  class="btn_green" type="button" onclick="bizRelaSearchDel();" value="搜索"/>
	 </security:authorize>
	 </legend>
	 </fieldset>
</div>
	 <div id="bizRelaDelList" style="padding: 10px 8px 0px;"></div>
</form>
<script type="text/javascript">
$(function(){
	bizRelaSearchDel();
});

function bizRelaSearchDel(){
	var relaTypeCd= $("#relaTypeCd").val();
	var relaProvince= $("#relaProvince").val();
	var relaArea= $("#relaArea").val();
	var relaName= $("#relaName").val();
	var relaUnit= $("#relaUnit").val();
	TB_showMaskLayer("正在搜索请稍后...");
	$.post(_ctx+"/biz/biz-rela-lib!delList.action",
			{
				relaTypeCd:relaTypeCd,
				relaProvince:relaProvince,
				relaArea:relaArea,
				relaName:relaName,
				relaUnit:relaUnit
		    },
			function(result) {
				if (result) {
					$("#bizRelaDelList").html(result);
				}
				TB_removeMaskLayer();
			}
		);
}

</script>
</body>
</html>
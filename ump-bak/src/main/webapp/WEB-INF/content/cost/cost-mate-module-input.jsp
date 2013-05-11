<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
</head>
<body>
	<div id="mailMianContainer" >
	<%-- 添加 --%> 
	<fieldset id="addDiv" style="margin-left: 10px;margin-right: 10px;">
	<legend>编辑设备类型<img src="${ctx}/images/meetingRoom/pic_down_blue.gif"/></legend>
	<form action="${ctx}/cost/cost-mate-module!save.action" method="post" id="moduleForm">
		<input type="hidden" id="costMateModuleId" name ="costMateModuleId" value="${costMateModuleId}"/>
		<input type="hidden" id="recordVersion" name ="recordVersion" value="${recordVersion}"/>
	  	<table width="90%" class="content_table" id="addTab">
	 		<col style="width:100px;"/>
			<col />
			<col style="width:100px;"/>
			<col style="width: 200px;"/>
			<col style="width:100px;"/>
			<col />
		  <tr style="height: 35px;">
		  	<td align="right">*类型名称：</td>
			<td>
				<input type="text" id="moduleName" name ="moduleName" value="${moduleName}" maxlength='40' />
			</td>
			<c:if test="${parentId == '0'}">
				<td align="right">是否大类：</td>
				<td>是<input type="hidden" id="parentId" name="parentId" value="${parentId}"/></td>
			</c:if>
			<c:if test="${parentId != '0'}">
				<td align="right">*选择类型：</td>
				<td><s:select list="mapCostMateModule" listKey="key" listValue="value" id="parentId" name="parentId"/></td>
			</c:if>
			<td align="right">*是否启动：</td>
			<td>
				<s:radio list="#{'1':'是','0':'否'}" name="enableFlg" id="enableFlg"></s:radio>
			</td>
		  </tr>
		  <tr style="height: 35px;" align="left">
			<td valign="top" align="right">备注：</td>
			<td valign="top" colspan="5"> 
				<textarea id="remark" name="remark" style="width:80%;height:50px;border:1px solid #ccc;font-size: 13px;" onpropertychange="if(value.length>100) value=value.substr(0,100)">${remark}</textarea>
			</td>
		  </tr>
		  <tr style="height: 35px;">
		   	<td>&nbsp;</td>
		   	<td align="left" colspan="5">
				<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
					<input type="button" class="btn_green" onclick="mateModuleSave();" value="保存" />
				</security:authorize>
	       	  	<input type="button" class="btn_green" onclick="back();" value="返回" />
			</td>
		  </tr>
		 </table>
		</form>
	</fieldset>
	<input type="hidden" id="parId"  value="${parentId}"/>
</div>

<script type="text/javascript">
function mateModuleSave(){
	var parentId = $("#parentId").val();
	var moduleName = $("#moduleName").val();
	var enableFlg = $("input[type=radio][name='enableFlg']:checked").val();
	if(moduleName == null || $.trim(moduleName)==""){
		alert("请填写类型名称!");
		return false;
	}
	if(parentId == null || parentId==""){
		alert("请选择类型");
		return false;
	}
	if(enableFlg == null || enableFlg==""){
		alert("请选择是否启用");
		return false;
	}
	var url = "${ctx}/cost/cost-mate-module!checkMoudleName.action";
	$.post(url,{moduleName:$.trim(moduleName), id:$("#costMateModuleId").val()},function(result) {
		if("success"==result){
			alert("类型名成已使用，请更换!");
			$('#moduleName').focus();
			return false;
		}else{
			$("#moduleForm").ajaxSubmit(function(result) {
				if("success" == result){
					back();
				}else{
					alert("保存失败!");
					return false;
				}
			});
		}
	});
}

function showParent(value){
	if(value=="1"){
		$("#selctP").show();
	}else{
		$("#selctP").hide();
	}
}
function back(){
	var pId = $("#parId").val();
	if(pId == '0'){
		location.href="${ctx}/cost/cost-mate-module!main.action?parentId="+pId;
	}else{
		location.href="${ctx}/cost/cost-mate-module!main.action";
	}
}
</script>
</body>
</html>

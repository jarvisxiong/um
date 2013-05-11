<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
 	 
</head>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle" style="padding-left:10px;">
			(战略材料设备)类型设置
		</div>
		
		<div class="fRight">
			<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
				<input type="button"  class="btn_new btn_add_new" style="width:80px;" onclick="doAddModule();" value="新增类型" />
			</security:authorize>
			<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN,A_COST_STRAGE_VIEW">
				<input type="button" id="bigDiv" class="btn_new btn_green_new" style="width:80px;" onclick="queryModuleType('0');" value="显示大类" />
				<input type="button" id="smallDiv" class="btn_new btn_green_new" style="width:80px;display: none;" onclick="queryModuleType('');" value="显示分类"/>
				<input type="button" class="btn_new btn_refresh_new" onclick="doCostMateModule('');" value="刷新" />
			</security:authorize>
		</div>
	</div>
	<!-- 搜索 -->
   	<div class="clearBoth">
		<table style="height:30px;margin-left: 15px;" cellspacing="0" cellpadding="0" id="searchTab">
			<tr>
				<td>设备类型名称:</td>
				<td><input type="text" id="sModuleName" style="width:120px" /></td>
				<td style="padding-left: 20px;">是否大类:</td>
				<td>
					<select style="width: 60px;" id="sParentId" onchange="showParent('selctSP');">
						 <option value="1">否</option>
						 <option value="0">是</option>
					</select>
					<span id="selctSP">--&nbsp;<s:select list="mapCostMateModule" listKey="key" listValue="value" id="sParentId2" name="parentId"/></span>
				</td>
				<td style="padding-left: 20px;">是否启动:</td>
				<td>
					<select style="width: 80px;" id="sEnableFlg">
						 <option value="">请选择</option>
						 <option value="1">是</option>
						 <option value="0">否</option>
					</select>
				</td>
				<td style="padding-left:10px;"><input type="button" id="ModBtn" class="btn_new btn_query_new" onclick="doQueryModule();" value="搜索" /></td>
			</tr>
		</table>
	</div>
	
	<%-- 添加类型 --%> 
	<div id="addDiv" style="display: none;margin-left: 10px;margin-right: 10px;border: 1px solid #cbcbcb;">
		<form action="${ctx}/cost/cost-mate-module!save.action" method="post" id="moduleForm">
	  	<table width="90%" class="content_table" id="addTab">
	  		<col style="width:80px;"/>
			<col style="width: 150px;"/>
			<col style="width:100px;"/>
			<col style="width: 180px;"/>
			<col style="width:100px;"/>
			<col />
		 	<tr style="height: 35px;">
			  	<td align="right">*类型名称：</td>
				<td><input type="text" id="aModuleName" name="moduleName" maxlength='40' onblur="checkMoudleName(this.value);"/></td>
				<td align="right">*是否大类：</td>
				<td>
					<s:radio list="#{'0':'是','1':'否'}" name="parentId" id="parentId" value="0" onclick="showParentAdd(this.value);"></s:radio>
					<span style="display: none;" id="selctP"><s:select list="mapCostMateModule" listKey="key" listValue="value" id="parentId2" name="moduleType"/></span>
				</td>
				<td align="right">*是否启动：</td>
				<td><s:radio list="#{'1':'是','0':'否'}" name="enableFlg" id="enableFlg" value="1"></s:radio></td>
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
						<input type="button" class="btn_new btn_save_new" class="btn_green" onclick="mateModuleSave();" value="保存" />
		       	  	</security:authorize>
		       	  	<input type="button" class="btn_new btn_cancel_new" onclick="doAddModule();" value="取消" />
				</td>
		 	 </tr>
		 </table>
		</form>
	</div>
	
	<%-- 列表 --%>
	<form action="${ctx}/cost/cost-mate-module!list.action" method="post" id="searchForm">
	<%--
		<div style="font-weight: bolder;line-height: 30px;margin-left:12px;"> 设备类型列表</div>
	 --%>
		<div id="costMateModuleList">	  
 			<%-- 搜索结果列表 --%>
		</div>
 	</form>
 	<input type="hidden" id="parId" value="${parentId}"/>

<script type="text/javascript">
$(function(){
	var pId = $("#parId").val();
	if(pId=='0'){
		$("#sParentId").val(pId);
		$("#selctSP").hide();
		$("#smallDiv").show();
		$("#bigDiv").hide();
	}else{
		$("#smallDiv").hide();
		$("#bigDiv").show();
	}
	doQueryModule(pId);
});


//搜索类型
function doQueryModule(pId){
	var parentId = $("#sParentId").val();
	var enableFlg = $("#sEnableFlg").val();
	var moduleName = $("#sModuleName").val();
	if(parentId == '1'){ //parentId = 1 为否
		parentId = $("#sParentId2").val();
	}
	if(pId != null && pId !=""){
		parentId = pId;
	}
	$("#addDiv").hide();
	TB_showMaskLayer("正在搜索,请稍候...");
	var data = {parentId:parentId, moduleName:moduleName, enableFlg:enableFlg};
	$.post("${ctx}/cost/cost-mate-module!list.action",data,function(result) {
		TB_removeMaskLayer();
		if (result) {
			$("#costMateModuleList").html(result);
		}
	});
}

//删除类型
function doMateDelete(id,parentId){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url="${ctx}/cost/cost-mate-module!delete.action";
			$.post(url,{id:id},function(result) {
				if('success' == result){
					if(parentId=="0"){
						doCostMateModule('0');
					}else{
						doCostMateModule('');
					}
				}else if('nodel'==result){
					alert("该类型或子类有关联的设备数据，不能删除");
					return false;
				}else{
					alert("删除失败");
					return false;
				}
			});
		}
	}
}

//保存类型
function mateModuleSave(){
	var parentId = $("#addTab input[type=radio][name='parentId']:checked").val();
	var moduleName = $("#aModuleName").val();
	var enableFlg = $("#addTab input[type=radio][name='enableFlg']:checked").val();
	if(moduleName == null || $.trim(moduleName)==""){
		alert("请填写类型名称!");
		return false;
	}
	if(parentId == null || parentId==""){
		alert("请选择是否是大类");
		return false;
	}else{
		if(parentId=="1"){
			var parentId2 = $("#addTab #parentId2").val();
			if(parentId2 == null || parentId2==""){
				alert("请选择大类!");
				return false;
			}
		}
	}
	if(enableFlg == null || enableFlg==""){
		alert("请选择是否启用");
		return false;
	}
	var url = "${ctx}/cost/cost-mate-module!checkMoudleName.action";
	$.post(url,{moduleName:$.trim(moduleName)},function(result) {
		if("success"==result){
			alert("类型名称已使用，请更换!");
			return false;
		}else{
			$("#moduleForm").ajaxSubmit(function(result) {
				if("success" == result){
					$("#addDiv").hide();
					if(parentId=="0"){
						doCostMateModule('0');
					}else{
						doCostMateModule('');
					}
				}else{
					alert("保存失败!");
					return false;
				}
			});
		}
	});
}

//搜索大小类(切换大小类)
function queryModuleType(pId){
	$("#addDiv").hide();
	if(pId != null && pId =='0'){
		$("#sParentId").val('0');
		$("#selctSP").hide();
	}else{
		$("#sParentId").val('1');
		$("#selctSP").show();
	}
	TB_showMaskLayer("正在搜索,请稍候...");
	var url = "${ctx}/cost/cost-mate-module!list.action";
	$.post(url,{parentId:pId},function(result) {
		TB_removeMaskLayer();
		if (result){
			if(pId=='0'){
				$("#smallDiv").show();
				$("#bigDiv").hide();
			}else{
				$("#smallDiv").hide();
				$("#bigDiv").show();
			}
			$("#costMateModuleList").html(result);
		}
	});
}

//检测设备类型名称
function checkMoudleName(value){
	if(value != null && $.trim(value) !=""){
		var url = "${ctx}/cost/cost-mate-module!checkMoudleName.action";
		$.post(url,{moduleName:value},function(result) {
			if("success"==result){
				alert("类型名成已使用，请更换!");
				return false;
			}
		});
	}
}

//查看类型
function getDetail(id){
	location.href="${ctx}/cost/cost-mate-module!input.action?id="+id;
}

//刷新
function doCostMateModule(parentId){
	var url="${ctx}/cost/cost-mate-module!main.action?parentId="+parentId;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-mate-module-main","设备类型维护",url,true);
}

//显示隐藏添加div
function doAddModule(){
	if($("#addDiv").is(":hidden")){
		$("#aModuleName").val('');
		$("input:radio[value='0']").eq(0).attr('checked','true');
		$("input:radio[value='1']").eq(1).attr('checked','true');
		$("#remark").val('');
		$("#selctP").hide();
		$("#parentId2").val('');
		$("#addDiv").show();
	}else{
		$("#addDiv").hide();
	}
}
function showParent(value){
	if($("#"+value).is(":hidden")){
		$("#"+value).show();
	}else{
		$('#'+value).hide();
	}
}
function showParentAdd(value){
	if(value=="1"){
		$("#selctP").show();
	}else{
		$("#selctP").hide();
	}
}

function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#searchForm").ajaxSubmit(function(result) {
		$("#costMateModuleList").html(result);
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
</script>
</body>
</html>

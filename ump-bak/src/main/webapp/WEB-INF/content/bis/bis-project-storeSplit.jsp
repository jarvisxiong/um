<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis-project.js"></script>
<style>
.main_content input{
width:110px !important;
}
.main_content .div30{
float:left;
width:33%;text-align:right;
margin:auto;
}
.main_content .div40{
float:left;
width:33%;
text-align:right;
margin:auto;
}

.main_content .div25{
float:left;
width:30%;text-align:right;
margin:auto;
}
</style>
<title>项目基础信息</title>
</head>
<body>
<form action="${ctx}/bis/bis-project!storeSplitSave.action" method="post" id="storeSplitForm">
<input type="hidden" name="recordVersion" id="recordVersion" value="${recordVersion}" />
<input type="hidden" name="bisStore.parentStoreId" id="bisStoreId" value="${bisStore.bisStoreId}" />
<input type="hidden" name="bisStore.bisFloor.bisFloorId" id="bisFloorId" value="${bisStore.bisFloor.bisFloorId}"/>
<input type="hidden" name="bisStore.bisProjectId" id="bisProjectId" value="${bisStore.bisProjectId}"/>
<input type="hidden" name="bisStore.sellPrice" value="${bisStore.sellPrice}"/>
<input type="hidden" name="bisStore.tenemStandard" value="${bisStore.tenemStandard}"/>
<input type="hidden" name="bisStore.rentStandard" value="${bisStore.rentStandard}"/>
<input type="hidden" name="bisStore.equityNature" value="${bisStore.equityNature}" />
<input type="hidden" name="bisStore.managementStatus" value="${bisStore.managementStatus}"/>
<input type="hidden" name="bisStore.ifPractice" value="${bisStore.ifPractice}"/>
<input type="hidden" name="bisStore.shopPosition" value="${bisStore.shopPosition}"/>
<input type="hidden" name="bisStore.owner" value="${bisStore.owner}"/>
<input type="hidden" name="bisStore.storeRemark" value="${bisStore.storeRemark}"/>
<input type="hidden" name="bisStore.squareReal1" value="${bisStore.squareReal}"/><input type="hidden" name="bisStore.innerSquareReal1" value="${bisStore.innerSquareReal}"/>
<input type="hidden" name="bisStore.layoutCd" value="${bisStore.layoutCd}"/>
<input type="hidden" name="bisStore.ifChild" value="1"/>
<input type="hidden" name="bisStore.ifSubmit" id="ifSubmit" value="0"/>
<input type="hidden" name="bisStore.splitStatus" value="0"/>
		<div style="height:100%;margin-top:8px;padding:0px 0px; width:100%" id="titleAdd">
		<div style="border: 0px solid ; margin:10px 8px;">
		<div  style="padding-left: 8px; height: 20px;padding-top: 10px;border: 1px solid #eeeeee;font-size: 14px">
		<font><font color="red">正在拆分：</font> 
		${bisProjectName}&nbsp;>&nbsp;<s:if test="buildingNum != ''&&buildingNum != null">${buildingNum}&nbsp;>&nbsp;</s:if>
		${floorNum}层&nbsp;>&nbsp;
		<font color="#0693e3">${bisStore.storeNo}号商铺&nbsp;</font>
		(建筑面积${bisStore.square}，
		<s:if test="bisStore.squareReal != ''&&bisStore.squareReal != null">建筑面积(实测)${bisStore.squareReal}，</s:if>
		套内面积${bisStore.innerSquare}，
	    <s:if test="bisStore.innerSquareReal != ''&&bisStore.innerSquareReal != null">套内面积(实测)${bisStore.innerSquareReal}，</s:if>
		计租面积${bisStore.rentSquare})
		</font>
		</div>
		</div>
		<fieldset style="background-color:#F7F7F6;border-top:0px;border-left:0px;border-right: 0px;margin-right: 8px;margin-left: 8px;">
		<!--<legend><font color="#ffa613">商铺拆分</font></legend>-->
		<div id="new_Store" style="background-color:#F7F7F7 ;float:left;font-size:12px;padding-right:20px;line-height:30px;">
		<table class="main_content" id="splitTable" border="0" cellpadding="0" cellspacing="0"  style="width:800px;height:100%;table-layout:fixed" align="left">
		
			<tr>
			<td>
				<div class="div25">
				编号：<input type="text" id="storeNo"  class="requiredStore" name="bisStore.storeNo"  onkeyup="storeValidate(this);"/>
				</div>
				<div class="div30">
					建筑面积㎡(图纸)：<input type="text" id="square" alt="amount" name="bisStore.square"  maxlength="8" />
				</div>
				<div class="div40">
				     建筑面积㎡(实测)：<input type="text" id="squareReal"  name="bisStore.squareReal"   alt="amount"  maxlength="10"/>
			     </div>
			</td>
			</tr>
			<tr>
			<td>
				<div class="div25">
					套内面积㎡(图纸)：<input type="text" id="innerSquare" alt="amount" name="bisStore.innerSquare"  maxlength="8"/>
				</div>
				<div class="div30">
					套内面积㎡(实测)：<input type="text"  id="innerSquareReal" alt="amount" name="bisStore.innerSquareReal"  maxlength="10"/>
				</div>
				<div class="div40">
				    计租面积㎡：<input type="text" class="requiredStore" id="rentSquare" alt="amount" name="bisStore.rentSquare"   maxlength="10"/>
				</div>
			</td>
			</tr>
			<tr>
			<td>
				<div class="div25">
					拆分日期：<s:textfield name="bisStore.splitDate" id="splitDate" onfocus="WdatePicker()"/></div>
				<div class="div30">
					开业日期：<s:textfield name="bisStore.openDate" id="openDate" onfocus="WdatePicker()"/>
				</div>
				<div class="div40">
				&nbsp;
				</div>
			</td>
			</tr>
		</table>
		<div style="float:left;font-size:12px;padding-right:20px;margin-top:10px;line-height:30px;width:100%;">
		<table  class="main_content" border="0" cellpadding="0" cellspacing="0"  style="width:800px;height:100%;table-layout:fixed">
	
			<tr>
				<td>
					<div class="div25" >
						<s:if test="bisStore.ifSubmit==0||bisStore.ifSubmit==2||bisStore.ifSubmit==null">
							<input  class="btn_green"  type="button" onclick="storeSplitSave();" value="保存"  style="margin-right:80px !important;"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</s:if>
					</div>
					<div class="div30">
					&nbsp;
					</div>
					<div class="div30">
						&nbsp;
					</div>
				</td>			
			</tr>
		</table>
		</div>
	</div>
	</fieldset>
	</div>
	<div id="storeSplit" style="margin-left: 8px;margin-right: 8px;"></div>
	<div style="float:left;font-size:12px;padding-right:20px;line-height:30px;width:100%;margin-top: 10px;">
	<table  class="main_content" border="0" cellpadding="0" cellspacing="0"  style="width:100%;height:100%;table-layout:fixed">
		<tr>
			<td style="padding-left: 8px">
			<s:if test="bisStore.ifSubmit==0||bisStore.ifSubmit==2||bisStore.ifSubmit==null">
				<input  class="btn_blue"  type="button" onclick="splitSubmit();" value="提交" />
	
			</s:if>
			<s:if test="bisStore.ifSubmit==1">
			 <input  class="btn_blue"  type="button" onclick="splitRevarte();" value="还原"/>
			</s:if>
			</td>
		</tr>
	</table>
	</div>
</form>
<script type="text/javascript">
<!--
$(function(){
	storeSplitSecrch();
	$('input[alt="amount"]').live('keyup',function(){
		clearNoNum_1(this);
		if($('.percent').val()>100){
			this.value=0;
		}
	});
});
//校验编号不允许输入特殊字符
function storeValidate(obj) {
	obj.value = obj.value.replace(/[^\a-zA-Z\d\_\-]/g, "");
	} 
function storeSplitClear(){



	$("#splitTable").find("input").val("");
	//$('#storeNo').val('');
	//$('#square').val('');
	///$('#innerSquare').val('');
	//$('#publicSquare').val('');
	//$('#rentSquare').val('');
}
function storeSplitSave(){
	var storeNo=$('#storeNo').val();
	var rentSquare=$('#rentSquare').val();
	if(storeNo==""){
		alert("请填写商铺编号");
		$('#storeNo').focus();
		return false;
	}else if(rentSquare==""){
		alert("请填写计租面积");
		$('#rentSquare').focus();
		return false;
	}else{
		$('#storeSplitForm').ajaxSubmit(function(result) {
			if(result!=null){
				alert("保存成功!");
				storeSplitClear();
				storeSplitSecrch();
			}
		});
	}
}
function storeSplitSecrch(){
	var bisStoreId=$('#bisStoreId').val();
	$.post(_ctx+"/bis/bis-project!storeSplitSecrch.action",
			{
				bisStoreId:bisStoreId
		    },
			function(result) {
				if (result) {
					$("#storeSplit").html(result);
				}
			}
		);
}
function deleteSplitStore(id){
	if(id!=null){
		if(confirm("确定要删除该条记录吗？")){
			var url=_ctx+"/bis/bis-project!delete.action";
			$.post(url,{
						id:id
					   },
					function(result) {
						if('success' == result){
							storeSplitSecrch();
						}else{
							alert("删除失败");
							return false;
						}
					});
		}
	}
}
function splitSubmit(){
	var ifSubmit='1';
	var bisStoreId=$('#bisStoreId').val();
	if(bisStoreId!==null){
		var url=_ctx+"/bis/bis-project!splitSubmit.action";
		$.post(url,{
					ifSubmit:ifSubmit,
					bisStoreId:bisStoreId
				   },
				function(result) {
					if('success' == result){
						alert("提交成功");
						var urlMain=_ctx+"/bis/bis-project!main.action?bisStoreId="+bisStoreId;

						parent.TabUtils.newTab("197","项目基础资料维护",urlMain,true);
						var cfg = {};
						cfg.data = {tabId:'bis-project-storeSplit'};
						parent.TabUtils.closeTab(cfg);
					}else{
						alert(result);
						return false;
					}
				});
	}
}
function splitRevarte(){
	var bisStoreId=$('#bisStoreId').val();
	
	$.post(_ctx+"/bis/bis-project!splitRevarte.action",
			{
				bisStoreId:bisStoreId
		    },
			function(result) {
				if (result=='success') {
					alert("还原成功");
			//		storeSplitSecrch();
					var urlMain=_ctx+"/bis/bis-project!main.action?bisStoreId="+bisStoreId;
					parent.TabUtils.newTab("197","项目基础资料维护",urlMain,true);
					//var cfg = {};
					//cfg.data = {tabId:'bis-project-storeSplit'};
					//parent.TabUtils.closeTab(cfg);
				}else if(result.indexOf('notrevarte')>-1){

					alert(result.split("&")[1]+"商铺已被使用无法还原!");
					}
			}
		);
}
/**
function bisFloorSearch(){
	var floorType ='1';
	var bisProjectId = $('#bisProjectId').val();
	var bisFloorId =$("#bisFloorId").val();
	var num =$("#num").val();
	$.post(_ctx+"/bis/bis-project!main.action",
			{
			  bisProjectId:bisProjectId,
			  floorType:floorType,
			  bisFloorId:bisFloorId
		    },
			function(result) {
				if (result) {
					$("#bisProjectFloor").html(result);
				}
			}
		);
}
*/
-->
</script>
</body>
</html>

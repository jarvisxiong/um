<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>材料设备属性信息</title>
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.example.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/customInput/customInput.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	
	<style>
		.cont-show-table{
			margin:5px 10px;
		}
		.cont-show-table thead{
			background-color:#E4E7EC;
			table-layout:none;
		}
		.cont-show-table tr:hover {
			cursor: pointer;
			background-color:#E4E7EC;
		}
		.cont-show-table tr td.del input{
			display: none;
		}
		.cont-show-table tr:hover td.del input{
			display: block;
		}
	</style>
</head>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle">正在查看<font color="red">[${costMateName}]</font>的标题栏</div>
		
		<s:if test="inputFlag != null && inputFlag != ''"><font color="red">保存成功!</font></s:if>
		<input type="button" class="fRight btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
		<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
	    <input type="button" class="fRight btn_new btn_save_new" onclick="costMateSave();" value="保存" />
		</security:authorize>
	</div>
  
	<div id="cont_basic">
	<form action="${ctx}/cost/cost-mate-col!save.action" method="post" id="costMateColForm">
		<input type="hidden" id="costMateId" name="costMateId" value="${costMateId}"/>
		<input type="hidden" name="id" id="costMateColId" value="${costMateColId}" />
		<input type="hidden" id="listCount" value='<s:property value="costMateColList.size()"/>'/>
		
		<table class="cont-show-table" id="addTab">
			<thead>
			<tr align="center" style="font-weight: bolder;">
			    <td width="60px" style="padding-left: 5px;">属性名称</td>
			    <td width="15%">列名称 </td>
			    <td width="10%">列数据类型</td>
			    <td width="10%">属性级别</td>
			    <td width="10%">是否扩展字段</td>
			    <td width="10%">是否价格字段</td>
			    <td width="12%">显示序号 </td>
			    <td width="">&nbsp;</td>
			</tr>
			</thead>
			<s:if test="costMateColList.size()>0">
				<s:iterator value="costMateColList" var="voItem" status="sta">
					<tr align="center">
						<td>${colField}</td>
					    <td>
					    	<input type="hidden" id="costMateColId" name="costMateColId" value="${costMateColId}"/>
					    	<input type="hidden" name ="colField" value="${colField}"/>
					    	<input type="text" id="colName" name="colName" value="${colName}" maxlength='40'/>
					    </td>
					    <td><s:select list="mapColDataTypeCd" listKey="key" listValue="value" id="colDataTypeCd" name="colDataTypeCd"></s:select></td>
					    <td><s:select list="mapLevelCd" listKey="key" listValue="value" id="colDataLevelCd" name="levelCd"></s:select></td>
					    
					    <td><s:select list="mapExtFlg" listKey="key" listValue="value" id="extFlg" name="extFlg"></s:select></td>
					    <td><s:select list="mapExtPrice" listKey="key" listValue="value" id="extPrice" name="extPrice"></s:select></td>
					    
					    <td><input type="text" value="${sequenceNo}" id="sequenceNo" name="sequenceNo" maxlength='10' onkeyup="clearNoNum_1(this);" style="width: 70px;text-align: center;" title="值越小越靠前"/></td>
						<td align="left" class="del">
							<s:if test="colName != null && colName != ''">
								<input type="button" onclick="delCol('${costMateColId}');" class="btn_new btn_red_new" tle="删除" value="删除"/>
							</s:if>
						</td>
					</tr>
				</s:iterator>
				<s:if test="costMateColList.size() < 30">
					<tr align="center" id="addTr">
						<td><span id="colFieldDiv">f01</span></td>
					    <td>
						    <input type="hidden" id="costMateColId" name="costMateColId"/>
					    	<input type="hidden" id="colField" name ="colField"/>
					    	<input type="text" id="colName" id="colName" name ="colName" maxlength='40'/>
					    </td>
					    <td><s:select list="mapColDataTypeCd" listKey="key" listValue="value" id="colDataTypeCd" name="colDataTypeCd"></s:select></td>
					    <td><s:select list="mapLevelCd" listKey="key" listValue="value" id="levelCd" name="levelCd"></s:select></td>
					    
					    <td><s:select list="mapExtFlg" listKey="key" listValue="value" id="extFlg" name="extFlg"></s:select></td>
					    <td><s:select list="mapExtPrice" listKey="key" listValue="value" id="extPrice" name="extPrice"></s:select></td>
					    
					    <td><input type="text" id="sequenceNo" name ="sequenceNo" maxlength='10' onkeyup="clearNoNum_1(this);" style="width: 70px;text-align: center;" title="值越大越靠前"/></td>
					</tr>
				</s:if>
			</s:if>
			<s:else>
				<tr align="center" id="addTr">
					<td><span id="colFieldDiv"></span></td>
				    <td>
					    <input type="hidden" id="costMateColId" name="costMateColId"/>
				    	<input type="hidden" id="colField" name ="colField"/>
				    	<input type="text" id="colName" id="colName" name ="colName" maxlength='40'/>
				    </td>
				    <td><s:select list="mapColDataTypeCd" listKey="key" listValue="value" id="colDataTypeCd" name="colDataTypeCd"></s:select></td>
				    <td><s:select list="mapLevelCd" listKey="key" listValue="value" id="levelCd" name="levelCd"></s:select></td>
				    
				    <td><s:select list="mapExtFlg" listKey="key" listValue="value" id="extFlg" name="extFlg"></s:select></td>
				    <td><s:select list="mapExtPrice" listKey="key" listValue="value" id="extPrice" name="extPrice"></s:select></td>
				    
				    <td><input type="text" id="sequenceNo" name ="sequenceNo" maxlength='10' onkeyup="clearNoNum_1(this);" style="width: 70px;text-align: center;" title="值越大越靠前"/></td>
				</tr>
		</s:else>
		</table>
	</form>
</div>
	
<script type="text/javascript">

var DB_FIELD_COUNT = 30;
var i = 1;
$(function(){
	var listCount = $("#listCount").val();
	var row = DB_FIELD_COUNT - listCount;
	var count = parseInt(listCount)+1;
	if(listCount < 10){
		$("#colField").val("f0"+count);
		$("#colFieldDiv").html("f0"+count);
	}else{
		$("#colField").val("f"+count);
		$("#colFieldDiv").html("f"+count);
	}
	for ( var i = 1; i < row; i++) {
		addNewCol();
	}
});
//添加tr
function addNewCol() {
	var listCount = $("#listCount").val();
	var row = parseInt(i)+parseInt(listCount)+1;
	if(row <= DB_FIELD_COUNT){
		i++;
		var addTr = $("#addTr").clone();
		addTr.attr("id","addTr"+row);
		addTr.appendTo("#addTab");
		$("#addTr"+row+" #colFieldDiv").attr("id","colFieldDiv"+row);
		$("#addTr"+row+" #colField").attr("id","colField"+row);
		if(row < 10){
			$("#colField"+row).val("f0"+row);
			$("#colFieldDiv"+row).html("f0"+row);
		}else{
			$("#addTr"+row+" #colField"+row).val("f"+row);
			$("#addTr"+row+" #colFieldDiv"+row).html("f"+row);
		}
		//addTr.append("<td onclick='remove(this);' style='cursor: pointer;' align='left' width='42%'><font style='color:red;font-weight:bold;cursor:pointer;' title='移除'>&nbsp;&nbsp;×&nbsp;&nbsp;</font></td>");
	}
}

//保存
function costMateSave(){
	var costMateColId = new Array();
	var colField = new Array();
	var colName = new Array();
	var colDataTypeCd = new Array();
	var levelCd = new Array();
	var sequenceNo = new Array();
	$("input[name='costMateColId']").each(function(){
		costMateColId.push($(this).val());
	});
	$("input[name='colField']").each(function(){
		colField.push($(this).val());
	});
	$("input[name='colName']").each(function(){
		colName.push($.trim($(this).val()));
	});
	$("select[name='colDataTypeCd']").each(function(){
		colDataTypeCd.push($.trim($(this).val()));
	});
	$("select[name='levelCd']").each(function(){
		levelCd.push($.trim($(this).val()));
	});
	$("input[name='sequenceNo']").each(function(){
		sequenceNo.push($.trim($(this).val()));
	});
	var costMateId = $("#costMateId").val();
	$.post("${ctx}/cost/cost-mate-col!save.action",
		{
			costMateId : costMateId,
			costMateColId : costMateColId,
			colField : colField,
			colName : colName,
			colDataTypeCd : colDataTypeCd,
			levelCd : levelCd,
			sequenceNo : sequenceNo
		},
		function(result) {
			if(result.indexOf('success') >= 0) {
				refresh(costMateId,'input');
			}else if(result.indexOf('realy') >= 0){
				alert("有列名相同");
				return false;
			}else{
				alert("保存失败");
				return false;
			}
		});
}

function delCol(id){
	var costMateId = $("#costMateId").val();
	var data = {id:id,costMateId:costMateId};
	if(id!=null){
		$.post("${ctx}/cost/cost-mate-col!checkColValue.action",data,function(result){
			var tips = "";
			if('realy' == result){
				tips = "该属性已在型号中设置了值,删除属性将清空型号中对应属性的值,";
			}
			if(confirm(tips+"确定要删除该条记录吗?")){
				var url="${ctx}/cost/cost-mate-col!delete.action";
				$.post(url,data,function(result) {
					if('success' == result){
						refresh($("#costMateId").val());
					}else{
						alert("删除失败");
						return false;
					}
				});
			}
		});
	}
}
//刷新
function refresh(id,input){
	var url = "";
	if(input != null && input!=''){
		url = "${ctx}/cost/cost-mate-col!list.action?costMateId="+id+"&inputFlag=inputFlag";
	}else{
		url ="${ctx}/cost/cost-mate-col!list.action?costMateId="+id;
	}
	location.href=url;
}
</script>
</body>
</html>

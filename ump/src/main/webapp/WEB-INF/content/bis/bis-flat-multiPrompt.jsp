<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<style type="text/css">
<!--
.addFirst {
	background-color: #61becf;
	color:#ffffff;
}
table.currtr{
	color:#000;
	table-layout: fixed;
	width: 100%;
	text-align: left;
}
table.currtr tr td{
	padding-left: 4px;
	height: 20px;
	line-height: 20px;
	vertical-align: middle;
	border-bottom: 1px solid #aaabb0;
	background-position: left center;
	background-repeat: no-repeat;
	font-weight:normal;
	white-space: pre-wrap;
}
-->
</style>
</head>
<body>
<input type="hidden" id="bisMultiId" >
<input type="hidden" id="bisContId" >
<input type="hidden" id="multiName" >
<div style="padding: 3px;">
 <span style="padding-left:5px;font-weight:bold;">多经编号</span>
 <span style="padding-left:60px;font-weight:bold;">合同编号</span>
</div>
<div id="selectFloorDiv"  style="margin:5px;overflow: auto;height:280px;border:1px solid #DBDBDB">
 <table id='mainTable' class='currtr'> 
 <col width='120px'/><col/>
   <s:iterator value="vos">
    <tr id="${id}_TR" onclick="setMulti('${id}','${text}','${key}');">
     <td>${text}</td>
     <td>${TextAttach}</td>
    </tr>
   </s:iterator>
 </table>
</div>
<script type="text/javascript">
function setMulti(bisId,multiName,key){
    if("cont"==key){
    	$("#bisContId").val(bisId);
    	$("#bisMultiId").val("");
    }else{
    	$("#bisContId").val("");
    	$('#bisMultiId').val(bisId);
    }
    $('#multiName').val(multiName);
	$('#mainTable  tr').removeClass("addFirst");
	$('#'+bisId+'_TR').addClass("addFirst");
}
function getMultiName(){
	 return $('#multiName').val();
}
function getContId(){
	return $('#bisContId').val();
}
function  getMultiId(){
	 return $('#bisMultiId').val();
}
</script>
</body>
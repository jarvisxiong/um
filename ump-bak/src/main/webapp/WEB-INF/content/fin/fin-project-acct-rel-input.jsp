<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script> 
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script language="javascript" src="${ctx}/js/jquery.form.pack.js"></script>
</head>
<body>
<div style="padding-left: 5px;padding-top: 5px;">
<s:form id="inputForm" action="fin-project-acct-rel!save.action" method="post">
	  <s:hidden id="id" name="id" />
	  <input type="hidden" id="projectCd" value="${projectCd}"/>
	  <s:hidden name="recordVersion"/>
  <table>
    <tbody>
	 <tr style="width:100%" height="30px;">
	   <td width="30%">项目名称</td>
	   <td  width="70%"><s:textfield  id="bankName" name="bankName" /></td>
	 </tr>
	 <tr height="30px;">
	  <td>账号</td>
	  <td><s:textfield  id="accountNo" name="accountNo" /></td>
	 </tr>
	 <tr height="30px;">
	 <td>账户类型</td>
	 <td>
	 <select id="accountTypeCd" name="accountTypeCd" >
		<option value="1">银行</option>
		<option value="2">现金</option>
	 </select>
	 </td>
	 </tr>
	 <tr height="30px;">
	 <td>是否可用</td>
	 <td>
	 <select id="enableFlg" name="enableFlg" >
		<option value="1">可动用</option>
		<option value="2">不可动用</option>
	 </select>
	 </td>
	 </tr>
	 <tr height="30px;">
	 <td>币种</td>
	 <td><s:textfield  id="currencyCd" name="currencyCd" /></td>
	 </tr>
	 <tr height="30px;">
	 <td>金额</td>
	 <td colspan="4"><s:textfield  id="totalAmount" name="totalAmount" disabled="true" /> </td>
	 </tr>
	</tbody>
  </table>
</s:form>
</div>
<div style="padding-top: 10px;">&nbsp;&nbsp;
<button type="button" id="ModBtn" class="btn_green_55_20"  onclick="doRelSave('${finProjectAcctRelId}');">保存</button>&nbsp;&nbsp;&nbsp;&nbsp;
<button type="button" id="ReturnBtn" class="btn_green_55_20"  onclick="window.location.href='${ctx}/fin/fin-project-acct-rel!list.action'">返回</button>
</div>
<script type="text/javascript">
function doRelSave(){
	//$("#inputForm").submit(); 
	if($("#bankName").val()==""){
		alert("请输入项目名称");
		return;
	}
	if($("#accountTypeCd").val()==""){
		alert("请输入账户类型");
		return;
	}
	if($("#enableFlg").val()==""){
		alert("请选择是否可用");
		return;
	}
	if($("#currencyCd").val()==""){
		alert("请输入币种");
		return;
	}
	if($("#totalAmount").val()==""){
		alert("请输入金额");
		return;
	}
	$("#inputForm").attr("action","${ctx}/fin/fin-project-acct-rel!save.action");
	$("#inputForm").submit(); 
}
</script>
</body>
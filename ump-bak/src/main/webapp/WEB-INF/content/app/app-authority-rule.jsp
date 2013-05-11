<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/js/jquery.form.pack.js"  type="text/javascript"></script>
<title>功能规则</title>

</head>

<body>
<fieldset>
	    <legend><s:text name="appAppRule"/></legend>
<div   class="divContent" >
	<div id="tableDiv">
	<table class="commonTable" id="editTable" width="99%"  style="table-layout: fixed;">
	<thead>
		<tr>
		<th width="90"><s:text name="appAppRule.ruleCd"/></th>
		<th width="90"><s:text name="appAppRule.ruleName"></s:text></th>
		<th width="120"><s:text name="appAppRule.content"/></th>
		<th width="90"><s:text name="appAppRoelResourceRel.roleCd"/></th>
		<th width="120"><s:text name="appAppRule.remark"/></th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="result" var="obj">
		<tr id="main_${obj[0].appRuleId}" class="mainTr">
			<td id="ruleCd">${obj[0].ruleCd}</td>
			<td id="ruleName">${obj[0].ruleName}</td>
			<td id="content" title="${obj[0].content}" class="split">${obj[0].content}</td>
			<td id="roleCd">
			<c:if test="${obj[1] > 0}"><img src="${ctx}/images/gou.gif"/></c:if>
			</td>
			<td id="remark" title="${obj[0].remark}" class="split" >${obj[0].remark}</td>
		</tr>
		<tr  id="detail_${obj[0].appRuleId}" class="detailTr"  style="display:none">
			<td colspan="5">
				<fieldset>
				    <legend><s:text name="common.detail"/></legend>
				    <form  id="editFormRule_${obj[0].appRuleId}" action="app-authority-rule!save.action" >
				    <table class="innerTable" >
				    	<tr >
				    	<td width="60"><s:text name="appAppRule.ruleCd"/>
				    	</td>
				    	<td  width="120"><input type="text" name="ruleCd" id="ruleCd" value="${obj[0].ruleCd}" />
				    	<s:hidden name="appFunctionId"/>
				    	<input type="hidden" name="id" value="${obj[0].appRuleId}" />
				      	<input type="hidden" name="recordVersion" value="${obj[0].recordVersion}" />
				    	</td>
						<td width="60">
						<s:text name="appAppRule.ruleName"/>
				      	</td>
				      	<td  width="120"><input type="text" name="ruleName" id="ruleName" value="${obj[0].ruleName}" />
						</td>
						</tr>
						<tr><td width="60">
						<s:text name="appAppRule.content"/>
				     	</td>
				     	<td colspan="3"><textarea name="content" id="content" >${obj[0].content}</textarea>
						</td>
						</tr>
						<tr><td width="60">
						<s:text name="appAppRule.remark"/>
				      	</td>
				      	<td colspan="3"><textarea name="remark" id="remark" >${obj[0].remark}</textarea>
						</td >
						</tr>
				    	<tr><td colspan="3">
				    	<input type="button" class="buttom" id="btnEdit" onclick="editRule('${obj[0].appRuleId}');" value="<s:text name="common.save"/>" />
				    	<input type="button" class="buttom" id="btnEdit" onclick="deleteRule('${obj[0].appRuleId}');" value="<s:text name="common.delete"/>" />
				    	<input type="button" class="buttom" id="btnEdit" onclick="add2Role('${obj[0].appRuleId}','${appFunctionId}');" value="<s:text name="appAppRoelResourceRel.roleCd"/>" />
				    	<input type="button" class="buttom" id="btnCancel" onclick="cancelEdit();" value="<s:text name="common.cancel"/>" />
				    	</td></tr>
				    </table>
				    </form>
				 </fieldset>
			</td>
		</tr>
	</s:iterator>
	</tbody>
</table>	
	</div>
	
<div id="divParent">
	<div id="divNew"  >
	<fieldset>
	    <legend><s:text name="common.create"/></legend>
	<div>
	<s:form id="createFormRule" action="app-authority-rule!save.action" >
		<table class="mainTable">
			<s:hidden name="appFunctionId"/>
			<tr><td width="60"><s:text name="appAppRule.ruleCd"/></td>
			<td width="120"><s:textfield name="ruleCd"/></td>
			<td width="60"><s:text name="appAppRule.ruleName"/></td>
			<td width="120"><s:textfield name="ruleName"/></td>
			</tr>
			<tr><td width="60"><s:text name="appAppRule.content"/></td>
			<td colspan="3" ><s:textarea  id="content" name="content" /></td>
			</tr>
			<tr><td width="60"><s:text name="appAppRule.remark"/></td>
			<td colspan="3" ><s:textarea  id="remark" name="remark" /></td>
			</tr>
			<tr><td colspan="4" >
			<input type="button" class="buttom" id="btnSave" onclick="createRule();" value="<s:text name="common.save"/>" />
			<input type="button" class="buttom" id="btnCancel" onclick="cancelNew();" value="<s:text name="common.cancel"/>" />
			</td></tr>
		</table>	
	</s:form>	
	</div>
	</fieldset>
</div>
</div>
<div id="divToolBar">
<input type="button"  class="buttom" id="btnNew" type="button" value="<s:text name="common.create"/>" />
</div>
</div>
</fieldset>
<script language="javascript">
	var divNew=$("#divNew").clone();
	$("#divParent").empty();
	
	$("#btnNew").bind("click", function(){
		divNew.appendTo("#divParent");
		$("#divToolBar").hide();
		cancelEdit();
	});
	$("#editTable tbody tr.mainTr").toggle(function(){
		$(".foldTr").trigger("click");
		$(this).addClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif");
		$(this).next().show();
		cancelNew();
	},function(){
		$(this).removeClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/down.gif");
		$(this).next().hide();
	});
	$("#editTable tbody tr.detailTr :input").keyup(function(){
		var id = this.id;
		$(this).parents(".detailTr").prev().children("[id="+id+"]").text($(this).val());
	});
	function cancelNew(){
		$("#divParent").empty();
		$("#divToolBar").show();
		
	}
	function cancelEdit(){
		$(".foldTr").trigger("click");
		$(this).addClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif");
		$(this).next().show();
	}
</script>
</body>
</html>

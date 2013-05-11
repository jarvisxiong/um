<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<div >
	<form action="${ctx}/res/res-module!saveModule.action" id="popModuleForm" method="post">
		<s:hidden name="id"></s:hidden>
		<s:hidden name="resModule.recordVersion"></s:hidden>
		<table style="width:100%;">
			<tr>
				<td align="right" style="width:80px;">序号：</td>
				<td><s:textfield cssStyle="100%;" name="resModule.sequenceNo"></s:textfield></td>
			</tr>
			<tr>
				<td align="right">模块代码：</td>
				<td><input cssStyle="100%;" type="text" name="resModule.moduleCd" value="${resModule.moduleCd}" onkeyup="validateCd('${resModule.moduleCd}',this,'module')"/></td>
			</tr>
			<tr>
				<td align="right">模块名称：</td>
				<td><s:textfield cssStyle="100%;" name="resModule.moduleName" id="popModuleName"></s:textfield></td>
			</tr>
			<tr>
				<td align="right">模块类别：</td>
				<td><s:select list="mapModuleType" listKey="key" listValue="value" name="resModule.moduleTypeCd"></s:select></td>
			</tr>
			<tr>
				<td align="right">是否有效：</td>
				<td><s:checkbox name="resModule.active" ></s:checkbox> </td>
			</tr>
			<tr>
				<td align="right">上级模块：</td>
				<td>
					<input id="parent_module_edit" style="width:100px;" type="text" value="${resModule.parentModuleCd}" />
					<input id="parentModuleCd" type="hidden" value="${resModule.parentModuleCd}" name="resModule.parentModuleCd" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td align="left">
					<button type="button" class="buttom" onclick="saveModulePop();" style="width:60px;">保存</button>
					<button type="button" class="buttom" onclick='delModule("${id}");' style="width:60px;">删除</button>
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
$('#parent_module_edit').quickSearch('${ctx}/res/res-module!searchModule.action?type=cd',['moduleName'],{moduleName:'parent_module_edit',moduleCd:'parentModuleCd'});
</script>


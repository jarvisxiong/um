<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

<div>
<form action="${ctx}/sc/sc-contract-templet-type!save.action" id="popModuleForm" method="post" >
<s:hidden name="id"></s:hidden>

<table style="width: 100%;">
	<tr style="display:none">
		<td align="right" style="width: 100px;">序号：</td>
		<td><input   type="text" name="sequenceNo" id="eSequenceNo" maxlength="18" value="${entity.sequenceNo}" style="width:190px"></input></td>
	</tr>
	<tr>
		<td align="right" style="width: 100px;">类别名称：</td>
		<td><input type="text" name="typeName" id="eTypeName" style="width:190px"  class="input" maxlength="50" value="${entity.typeName}"></input></td>
	</tr>
	<tr>
		<td width="100" style="text-align:right">模块类型：</td>
		<td><s:select list="mapmoduleTypeCd" listKey="key" listValue="value" id="eModuleTypeCd" name="moduleTypeCd" cssStyle="width:190px" value="entity.moduleTypeCd"></s:select></td>
	</tr>
	<tr>
		<td width="100" style="text-align:right">是否标准合同：</td>
		<td><s:checkbox name="isstandard" id="isstandard" cssClass="group"></s:checkbox><label for="isstandard"></label> &nbsp;&nbsp;<span style="color: #0167A2;" title="编号如:工程类(JA)|营销类(YX)|设计类(QQ)|战略(ZL)">编号如:工程类(JA)|营销类(YX)</span></td>
	</tr>
	<tr>
		<td width="100" style="text-align:right">是否不需要合同审批表即可打印：</td>
		<td><s:checkbox name="ifNotNeedRes" id="ifNotNeedRes" cssClass="group"></s:checkbox><label for="ifNotNeedRes"></label> &nbsp;&nbsp;</td>
	</tr>
	<tr>
		<td width="100" style="text-align:right"><span class="link" style="text-decoration:none;" title="编号如:工程类(JA)|营销类(YX)|设计类(QQ)|战略(ZL)">编号：</span></td>
		<td><input type="text" name="sn" id="sn" style="width:190px" class="input" maxlength="20" value="${entity.sn}"></input></td>
	
	</tr>
	<tr>
		<td width="100" style="padding: 5px 0 0 5px; text-align: right;">描述:</td>
		<td style="padding: 5px 0 0 0;">
		<textarea rows="5" cols="6" maxlength="200" style="margin-left: 0px; margin-right: 0px; width: 190px;" name="remark" id="eRemark">${entity.remark}</textarea>
		<input type="hidden" name="parentId" id="eParentId" value="${entity.parentId}" /></td>


	</tr>
	<tr style="height: 40px;">
		<td align="center" colspan="2">
		
		<input type="button" value="保存 " class="btnStyle handler" style="cursor:pointer" onclick="saveModulePop()" />

	
		&nbsp;&nbsp;
		<input type="button" value="取消" class="btnStyle handler" style="cursor:pointer" onclick="javascript:ymPrompt.close();" />

		&nbsp;&nbsp;
		<input type="button" value="删除 " class="btnStyle handler" style="cursor:pointer" onclick="delModule('${id}');" id="btTypeDel"/></td>
		</tr>
</table>
</form>
</div>




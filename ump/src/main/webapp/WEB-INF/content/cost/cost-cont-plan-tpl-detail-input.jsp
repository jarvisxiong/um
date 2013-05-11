<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<div>
<form action="${ctx}/cost/cost-cont-plan-tpl-detail!save.action" id="ccPlanTplDetailForm" method="post" >
<s:hidden name="id"></s:hidden>
<input type="hidden" name="costContPlanTplId" value="${entity.costContPlanTpl.costContPlanTplId}"/>

<table style="width: 100%;" cellspacing="2" cellpadding="3">

	<tr style="display:none">
		<td  class="caption">序号显示：</td>
		<td><input  type="text" name="contSequNo" id="contSequNo" maxlength="18" value="${entity.contSequNo}" style="width:190px"></input></td>
	</tr>
		<tr style="display:none">
		<td class="caption">行类型：</td>
		<td><input  type="text" name="rowTypeCd" id="rowTypeCd" maxlength="18" value="${entity.rowTypeCd}" style="width:190px"></input></td>
	</tr>
	<tr style="display:none">
		<td class="caption">序号显示：</td>
		<td><input  type="text" name="dispOrderDesc" id="dispOrderDesc" maxlength="18" value="${entity.dispOrderDesc}" style="width:190px"></input></td>
	</tr>
	<tr>
		<td class="caption">科目类型：</td>
        <td><s:select list="mapsubjectCd" listKey="key" listValue="value" id="subjectCd" name="subjectCd" cssStyle="width:190px"></s:select></td>
	</tr>
	<tr>
		<td class="caption">合同分类：</td>
		<td><s:select list="mapcontTypeCd" listKey="key" listValue="value" id="contTypeCd" name="contTypeCd" cssStyle="width:190px"></s:select></td>
	</tr>
	<tr>
		<td class="caption" >标段/合同名称：</td>
			<td><input  type="text" name="contName" id="contName" maxlength="18" value="${entity.contName}" style="width:190px"></input></td>
	</tr>
	<tr>
		<td class="caption">合同性质：</td>
		<td><input type="text" name="contCharTypeCd" id="contCharTypeCd" style="width:190px" class="input" maxlength="20" value="${entity.contCharTypeCd}"></input></td>
	</tr>
		<tr>
		<td class="caption">合约子目标成本1：</td>
		<td><input type="text" name="contSubTargetAmt" id="contSubTargetAmt" style="width:190px" class="input" maxlength="20" value="${entity.contSubTargetAmt}"></input></td>
	</tr>
		<tr>
		<td class="caption">地块、业态范围：</td>
		<td><input type="text" name="landDesc" id="landDesc" style="width:190px" class="input" maxlength="20" value="${entity.landDesc}"></input></td>
	</tr>
	<tr>
		<td class="caption"> 单位定义 ：</td>
		<td><input type="text" name="unitDefineDesc" id="unitDefineDesc" style="width:190px" class="input" maxlength="20" value="${entity.unitDefineDesc}"></input></td>
	</tr>
		<tr>
		<td class="caption">数量 ：</td>
		<td><input type="text" name="amount " id="amount " style="width:190px" class="input" maxlength="20" value="${entity.amount}"></input></td>
	</tr>
    <tr>
		<td class="caption">工作内容、边界条件、技术要求 ：</td>
		<td><input type="text" name="workReqDesc" id="workReqDesc" style="width:190px" class="input" maxlength="20" value="${entity.workReqDesc}"></input></td>
	</tr>
	<tr>
		<td class="caption">甲供材料、设备 ：</td>
		<td>		
		    <input type="hidden" name="partMateTypeIds" value="${entity.partMateTypeIds}"/>
		    <input type="hidden" name="partMateTypeCds" value="${entity.partMateTypeCds}"/>
	
		       <%-- JS中获取combobox 多选值var i = $("#cc").combobox("getValues");getValue:获取一个--%>
			<input class="easyui-combobox"  id="partMateTypeNames"  name="partMateTypeNames"  url="${ctx}/cost/cost-cont-plan-tpl-detail!getPartMateType.action" valueField="id" textField="name"  multiple="true"  panelHeight="auto"  />
		    <input type="hidden" name="relParentContId"/>
		</td>
	</tr>
	   <tr>
		<td class="caption">工 招标前置条件 ：</td>
		<td>
	    <input type="hidden" name="inviPreCondCds"   value="${entity.inviPreCondCds}"/>
	    <input type="hidden" name="inviPreCondIds" value="${entity.inviPreCondIds}"/>
		<input type="text" name="inviPreCondNames" id="inviPreCondNames" style="width:190px" class="input" maxlength="20" value="${entity.inviPreCondNames}"></input></td>
	</tr>
	 <tr>
		<td class="caption">出图时间 ：</td>
		<td><input type="text" name="outDrawDate" id="outDrawDate" style="width:190px" class="input" maxlength="20" value="${entity.outDrawDate}"></input></td>
	</tr>
	   <tr>
		<td class="caption"> 计划开工时间 ：</td>
		<td><input type="text" name="planStartDate" id="planStartDate" style="width:190px" class="input" maxlength="20" value="${entity.planStartDate}"></input></td>
	</tr>
	  <tr>
		<td class="caption"> 计划竣工时间 ：</td>
		<td><input type="text" name="planEndDate" id="planEndDate" style="width:190px" class="input" maxlength="20" value="${entity.planEndDate}"></input></td>
	</tr>
		  <tr>
		<td class="caption">招标开始时间 ：</td>
		<td><input type="text" name="tendStartDate" id="tendStartDate" style="width:190px" class="input" maxlength="20" value="${entity.tendStartDate}"></input></td>
	</tr>
	<tr>
		<td class="caption">描述:</td>
		<td style="padding: 5px 0 0 0;">
		<textarea rows="5" cols="6" maxlength="200" style="margin-left: 0px; margin-right: 0px; width: 190px;" name="memoDesc" id="memoDesc">${entity.memoDesc}</textarea>
	</td>
	</tr>
	<tr style="height: 40px;">
		<td align="center" colspan="2">
		<input type="button" value="保存 " class="btnStyle handler" style="cursor:pointer" onclick="saveModulePop()" />
		&nbsp;&nbsp;
		<input type="button" value="取消" class="btnStyle handler" style="cursor:pointer" onclick="javascript:ymPrompt.close();" />
	
</td>
			</tr>
</table>
</form>
</div>
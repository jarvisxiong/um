<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>

	<div style="margin:10px 2px 0px 2px;padding-left: 10px;">
		<table class="showTable">
			<tr class="header">
				<th style="width: 10%;" width="120px">序号</th>
				<th>审批节点</th>
				<th width="200px">级别</th>
				<th width="100px">操作</th>
			</tr>
			<s:if test="resApproveSteps.size == 0">
				<tr>
					<td colspan="4">
					<div class="noResult" style="padding: 10px 0;">尚未录入数据！</div>
					</td>
				</tr>
			</s:if>
			<tbody>
				<s:iterator value="resConditonType.resApproveSteps" status="status">
					<tr id="main_${resApproveStepId}">
						<td><s:property value="#status.index+1" /></td>
						<td name="nodeCd"><p:code2name mapCodeName="nodeMap" value="nodeCd"/></td>
						<td name="approveLevel"><s:property value="approveLevel"/></td>
						<td style="text-decoration: underline; cursor: pointer;">
							<span style="margin-right: 2px;" onclick="editRow('${resApproveStepId}')">编辑</span>
							<span onclick="delStep('${resApproveStepId}')">删除</span>
						</td>
					</tr>
					<tr id="edit_${resApproveStepId}" style="display: none">
						<td><s:property value="#status.index+1" /></td>
						<td>
							<s:select list="nodeMap" emptyOption="true" name="nodeCd" listKey="key" listValue="value"></s:select>
						</td>
						<td><input type="text" name="approveLevel"  value="${approveLevel}" /></td>
						<td style="text-decoration: underline; cursor: pointer;">
							<span style="margin-right: 2px;" onclick="saveStep('${resApproveStepId}')">保存</span>
							<span onclick="cancelRow('${resApproveStepId}')">取消</span>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
	<div>
		<button class="buttom" id="newStepBtnId" style="height: 25px; width: 80px;" onclick="showNew('newApproveStepDiv',125)" >新增步骤</button>
	</div>
	<div id="newApproveStepDiv" style="display: none">
				<form action="${ctx}/res/res-module!saveStep.action" id="newApproveStepForm" method="post">
					<input type="hidden" id="resConditionIdHidden" name="resApproveStep.resConditonType.resConditonTypeId" value="${id}"/>
					<table class="editTable">
						<tr>
							<td align="right">审批节点:</td>
							<td><s:select list="nodeMap" cssStyle="width:120px" emptyOption="true" name="resApproveStep.nodeCd" listKey="key" listValue="value"></s:select></td>
						</tr>
						<tr>
							<td align="right">级别:</td>
							<td><input style="width:120px;" type="text" name="resApproveStep.approveLevel"  /></td>
						</tr>
						<tr>
							<td colspan="2" align="center"><button class="buttom" type="button" onclick="save(this)">保存</button></td>
						</tr>
					</table>
				</form>
		</div>



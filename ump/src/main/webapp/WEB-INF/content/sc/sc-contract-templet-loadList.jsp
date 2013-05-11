<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%--合同模板数据列表 --%>
<table width="100%" id="result_table" class="showTable" cellpadding="0" cellpadding="0" border="0">
	<thead>
		<tr class="header">
			<th style="text-align: center;" width="30px;">序号</th>
			<th width="130px;" class="pd-chill-tip" title="模板名称" id="scloadTempletName"">模板名称</th>

			<th width="100px;">合同类别</th>
			<th style="width: 50px; ">状态</th>
			<th style="width: 80px; ">当前版本</th>
			<th style="width: 120px; text-align: center" id="operation">操作</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="voContractTempletList" status="stat">
			<s:if test="isView==1 && iscurversion==1">
				<%
					/*  用户预览合同模板并创建合同 */
				%>
				<tr class="mainTr"
					onclick="btnTempletClick('${contractTempletId}','${templetName}','${isvalid}');$('tr[otype=templet]').removeClass('clsSelected');$(this).addClass('clsSelected');">

					<td style="text-align: center"><s:property value="#stat.index+1" /></td>
					<%-- 模板名称 --%>
					<td class="clswrap" title="${templetName}">${templetName}</td>

					<%-- 合同类别--%>
					<td class="clswrap" title="${contractTempletTypeName}">${contractTempletTypeName}</td>

					<%-- 状态  --%>
					<td style=""><s:if test="isvalid==1">
						<font class="abled">启用</font>
					</s:if> <s:if test="isvalid==0">
						<font class="disabled">未启用</font>
					</s:if></td>
					<td style="text-align: center"><font class="abled">是</font></td>


				</tr>
			</s:if>
			<s:elseif test="isView!=1">
				<%
					/*  管理员后台维护合同模板*/
				%>
				<tr class="mainTr"
					onclick="btnTempletClick('${contractTempletId}','${templetName}','${isvalid}');$('tr[otype=templet]').removeClass('clsSelected');$(this).addClass('clsSelected');"
					style="cursor: pointer;"  isstandard="${isstandard}">

					<td style="text-align: center"><s:property value="#stat.index+1" /></td>
					<%-- 模板名称 --%>
					<td class="clswrap" title="${templetName}">${templetName}</td>

					<%-- 合同类别--%>
					<td class="clswrap" title="${contractTempletTypeName}">${contractTempletTypeName}</td>

					<%-- 状态  --%>
					<td >
					<s:if test="isvalid==1">
						<font class="abled">启用</font>
					</s:if> 
					<s:if test="isvalid==0">
						<font class="disabled">未启用</font>
					</s:if>
					</td>
					<td >
					<s:if test="iscurversion==1">
						<font class="abled">是</font>
					</s:if> 
					<s:if test="iscurversion==0">
						<font class="disabled">否</font>
					</s:if>
					</td>
					<s:if test="isView!=1">
						<td style="text-align: center">
						<input type="button" class="btn_green" style="width: 50px;" value="修改"
						onclick="showUploadSingleAttachDialog('标准合同模板编辑','mod','${contractTempletId}'); $('tr[otype=templet]').attr('isSel','false'); $(this).parent().parent().attr('isSel','true');"
						/>
						&nbsp;
						<input type="button" class="btn_red" style="width: 50px;" value="删除" onclick="deleteTemplet('${contractTempletId}')"/>
						</td>
					</s:if>

				</tr>
			</s:elseif>
		</s:iterator>

	</tbody>
</table>
		<input type="hidden" id="templetMaxSeq" name="templetMaxSeq" value="${templetMaxSeq}" />
	
<div class="pagerRight" id="sorucePager"><p:page pageInfo="voPage" /></div>

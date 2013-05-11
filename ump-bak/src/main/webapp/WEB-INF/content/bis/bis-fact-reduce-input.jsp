<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<style>
</style>
<%@page import="com.hhz.ump.util.JspUtil"%>
<form action="${ctx}/bis/bis-fact-reduce!batchSave.action" id="inputForm">
<legend>
	<s:if test="inputStatus==0">
		<font style="margin-left: 10px;color:#ffa613;font-size:14px ;font-weight:bold;">新增</font>
	</s:if>
	<s:if test="inputStatus==1">
		<span style="height:10px;width:90%;display:block"></span>
	</s:if>
</legend>
	<div style="border-bottom: 1px solid #8C8F94;line-height: 30px; padding: 5px;">
		<button type="button" class="btn_green_55_20" onclick="save('inputForm');" >保存</button>
		<button type="button" class="btn_red_55_20" onclick="cance();">取消</button>
	</div>
	<table  class="sup_table" style="width:100%;line-height:30px;margin-top:10px;">
	<input type="hidden" id="id" name="id" value="${bisFactId}"/>
	<col width="100" style="margin-right:10px">
	<col>
				<input type="hidden" id="bisProjectIdInput" name="bisProjectId"  value="${bisProjectId}" readonly="readonly"/>
		<tr  >
			<td align="right" style="padding-right:10px">
				<font style="color:red;">*</font>合同编号:
			</td>
			<td>
				<input id="selectConts" value="选择合同" type="text" style="width:90%"/>
				<input id="selectContIds" name="bisContId" type="hidden" type="text" />
				<input id="bigTypeId" type="hidden" type="text" />
				<input id="smallTypeId" type="hidden" type="text" />
			</td>
		</tr>
		<tr>
			<td align="right" style="padding-right:10px">
				<font style="color:red;">*</font>网批编号:
			</td>
			<td><input id="selectResApprove" value="搜索网批"  type="text"  onfocus="clearInput(this,'搜索网批')" style="width:90%"/>
			<input id="resApproveInfoId"  type="hidden" name="resApproveInfoId" />
			</td>
		</tr>
		<tr>
			<td align="right">
				说明：
			</td>
			<td><textarea rows="4" cols="3" name="remark" id="remark"></textarea>
			</td>
		</tr>
	</table>

</form>


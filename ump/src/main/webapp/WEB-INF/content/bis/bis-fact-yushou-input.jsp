<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<form action="${ctx}/bis/bis-fact-yu-s!save.action" id="frmAddAdvance" method="post">
    <input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}" />
	<div style="background-color:#F7F7F7">
		<legend> 
			<font style="margin-left: 10px; color: #ffa613; font-size: 14px; font-weight: bold;">新增</font>
		</legend>
		<table class="main_content"	border="0"  >
			<input type="hidden" id="id" name="id" value="${bisFactId}" />
			<input type="hidden" id="bisTenantId" name="bisTenantId" value="bisTenantId"/>
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<col width="15%">
			<tr>
				<td align="right">
					<div id="currDetailLabel">租户/商铺：</div>
				</td>
				<td>
					<input name="bisTenant" class="required" id="bisTenant4Ys" type="text" value=""/>
				</td>
				<td align="right">实收日期：</td>
				<td><input type="text" onfocus="WdatePicker()"value="${factDate}" class="required" name="factDate" id="factDate"validate="required" /></td>
				<td align="right">实收金额：</td>
				<td><input type='text' id='money' class="required"  name='money' value="${money }"onblur="formatNumber1($(this));" /></td>
			
			</tr>
			<tr >
				<td align="right">备注：</td>
				<td colspan="3" ><textarea rows="2" name="remark" id="remark" style="font-size: 12px;"></textarea></td>
			</tr>
			<tr>
				<td align="center"  colspan="2">
					<button type="button" class="btn_green" style="margin-right:4px;" onclick="return saveAdvancesDetail();">保存</button>
					<button type="button" class="btn_red" onclick="cance();">取消</button>
				</td>
				<td colspan="4">
				</td>
			</tr>
		</table>
	</div>
</form>



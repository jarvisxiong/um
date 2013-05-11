<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<input id="rentYear" type="hidden" value="${rentYear}"/>
<table class="tb_noborder" style="margin-top:10px; line-height:35px;">
	<col width="70"/>
	<col width="50"/>
	<col width="50"/>
	<col width="90"/>
	<col />
	<tr>
		<td style="text-align: right;">增长方式</td>
		<td colspan="3"><s:select id="incMode" cssStyle="width:100%" list="#{'1':'百分比','2':'固定值'}" listKey="key" listValue="value" onchange="changeIncMode(this.value);"></s:select></td>
		<td></td>
	</tr>
	<tr>
		<td style="text-align: right;">第</td>
		<td><s:select id="incStartYear" cssStyle="width:100%" list="#{'2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" listKey="key" listValue="value" ></s:select></td>
		<td colspan="2">年开始</td>
		<td></td>
	</tr>
	<tr>
		<td style="text-align: right;">每</td>
		<td><s:select id="incStep" cssStyle="width:100%" list="#{'1':'1','2':'2','3':'3','4':'4','5':'5'}" listKey="key" listValue="value" ></s:select></td>
		<td>年增长</td>
		<td><input id="incNum" style="width:80%;" type="text" onblur="formatVal($(this));" />&nbsp;<span id="spPercent">%</span></td>
		<td></td>
	</tr>
</table>

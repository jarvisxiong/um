<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css" />

<input type="hidden" id="contSDate" name="contStartDate" value="${contStartDate}"/>
<input type="hidden" id="contEDate" name="contEndDate" value="${contEndDate}"/>
<table class="tb_noborder" style="margin-top:10px; line-height:26px;">
	<col width="110"/>
	<col/>
	<col width="20"/>
	<tr>
		<td style="text-align: right;">开始时间：</td>
		<td><input type="text" id="startDate" name="startDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
		<td></td>
	</tr>
	<tr>
		<td style="text-align: right;">结束时间：</td>
		<td><input type="text" id="endDate" name="endDate" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/></td>
		<td></td>
	</tr>
	<tr>
		<td style="text-align: right;">第一个收款日：</td>
		<td><input type="text" id="firstDay" name="firstDay" class="Wdate" onfocus="WdatePicker()"/></td>
		<td></td>
	</tr>
	<tr>
		<td style="text-align: right;">付款方式：</td>
		<td><s:select cssStyle="width:100%" list="#{'1':'月付','2':'季付','3':'半年付','4':'年付'}" listKey="key" listValue="value" id="payMode" name="payMode"></s:select></td>
		<td></td>
	</tr>
	<tr>
		<td style="text-align: right;">月应收金额：</td>
		<td><input type="text" id="money" name="money" /></td>
		<td></td>
	</tr>
	<%-- 
	<tr>
		<td style="text-align: right;">增长方式：</td>
		<td><s:select cssStyle="width:100%" list="#{'1':'递增'}" listKey="key" listValue="value" id="increaseMode" name="increaseMode"></s:select></td>
		<td></td>
	</tr>
	<tr>
		<td style="text-align: right;">增长率(%)：</td>
		<td><input type="text" id="increaseRate" name="increaseRate" /></td>
		<td></td>
	</tr>
	--%>
</table>

<script type="text/javascript">
$(function(){
	var sd = $("#contSDate").val();
	var ed = $("#contEDate").val();
	$("#startDate").val(sd.substring(0,7));
	$("#endDate").val(ed.substring(0,7));
	$("#firstDay").val(sd);
});
</script>

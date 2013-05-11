<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css" />

<table class="tb_noborder" style="margin-top: 10px;">
	<col width="100"/>
	<col/>
	<tr>
		<td style="text-align: right;">费用类别</td>
		<td><s:select cssStyle="width:120px;" list="mapChargeType" listKey="key" listValue="value" id="sele_chargeType"></s:select></td>
	</tr>
</table>

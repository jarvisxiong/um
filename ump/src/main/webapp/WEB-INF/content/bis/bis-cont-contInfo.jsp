<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css" />

<input type="hidden" value="${bisContId}">
<table class="tb_noborder">
	<col width="100"/>
	<col />
	<tr>
		<td style="padding-top: 10px;">合同编号：</td>
		<td style="text-align: left; padding-top: 10px;">${contNo}</td>
	</tr>
	<tr>
		<td>合同类型：</td>
		<td style="text-align: left;"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" value="contBigTypeCd"/>-<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapContSmallType()" value="contSmallTypeCd"/></td>
	</tr>
	<s:if test="contBigTypeCd==1">
	<tr>
		<td>商铺编号：</td>
		<td style="text-align: left;">${bisStoreNos}</td>
	</tr>
	</s:if>
	<s:if test="contBigTypeCd==2">
	<tr>
		<td>公寓编号：</td>
		<td style="text-align: left;">${bisFlatNos}</td>
	</tr>
	</s:if>
	<tr>
		<td>商家名称：</td>
		<td style="text-align: left;">${bisShopName}</td>
	</tr>
	<tr>
		<td>开始时间：</td>
		<td style="text-align: left;"><s:date name="contStartDate" format="yyyy-MM-dd"/></td>
	</tr>
	<tr>
		<td>结束时间：</td>
		<td style="text-align: left;"><s:date name="contEndDate" format="yyyy-MM-dd"/></td>
	</tr>
	<s:if test="!activeBl">
	<tr>
		<td>失效时间：</td>
		<td style="text-align: left;"><s:date name="contToFailDate" format="yyyy-MM-dd"/></td>
	</tr>
	</s:if>
</table>

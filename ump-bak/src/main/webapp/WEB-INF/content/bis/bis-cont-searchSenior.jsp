<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css" />
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>

<table class="tb_noborder">
	<col width="130"/>
	<col width="120"/>
	<col width="100"/>
	<col width="120"/>
	<tr>
		<td style="text-align: right;">合同名称：</td>
		<td><input type="text" id="search_LIKES_contName" value="${filter_LIKES_contName}"/></td>
		<td style="text-align: right;">合同编号：</td>
		<td><input type="text" id="search_LIKES_contNo" value="${filter_LIKES_contNo}"/></td>
	</tr>
	<tr>
		<td style="text-align: right;">合同大类：</td>
		<td>
		<s:select cssStyle="width:100%" list="mapContBigType" listKey="key" listValue="value" id="search_EQ_contBigTypeCd" name="contBigTypeCd" onchange="getSmallType('search_EQ_contBigTypeCd','search_EQ_contSmallTypeCd',true);" ></s:select>
		</td>
		<td style="text-align: right;">合同小类：</td>
		<td>
		<s:select cssStyle="width:100%" list="mapContSmallType" listKey="key" listValue="value" id="search_EQ_contSmallTypeCd" name="contSmallTypeCd" emptyOption="true"></s:select>
		</td>
	</tr>
	<tr>
		<td style="text-align: right;">合同开始时间：</td>
		<td colspan="3">
		<input type="text" class="Wdate" id="search_GED_contStartDate" onfocus="WdatePicker()" value='${filter_GED_contStartDate}'/>
		~
		<input type="text" class="Wdate" id="search_LTD_contStartDate" onfocus="WdatePicker()" value='${filter_LTD_contStartDate}'/>
		</td>
	</tr>
	<tr>
		<td style="text-align: right;">合同结束时间：</td>
		<td colspan="3">
		<input type="text" class="Wdate" id="search_GED_contEndDate" onfocus="WdatePicker()" value='${filter_GED_contEndDate}'/>
		~
		<input type="text" class="Wdate" id="search_LTD_contEndDate" onfocus="WdatePicker()" value='${filter_LTD_contEndDate}'/>
		</td>
	</tr>
	<tr>
		<td style="text-align: right;">合同状态：</td>
		<td>
		<s:select cssStyle="width:100%" list="@com.hhz.ump.util.DictMapUtil@getMapBisContStatus()" listKey="key" listValue="value" id="search_EQ_statusCd" name="filter_EQ_statusCd"></s:select>
		</td>
		<td colspan="2"></td>
	</tr>
</table>

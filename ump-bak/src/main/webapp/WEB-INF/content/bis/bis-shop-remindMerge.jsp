<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<div id="result_div" style="padding: 8px; height: 490px;">
<table class="content_table" id="result_table">
	<col />
	<col />
	<thead>
		<tr class="header">
			<th align="left" style="background: none;">品牌名(中)</th>
			<th align="left">品牌名(英)</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="compareMap" var="map">
			<tr class="mainTr" >
				<td colspan="2" style="font-weight: bold; background-color: #E4E7EC; color: #40A4DE">${map.key}</td>
			</tr>
			<s:iterator value="#map.value" var="bisShop">
			<tr class="mainTr" id="main_${bisShop.bisShopId}" >
				<td title="${bisShop.nameCn}" >
					<div class="ellipsisDiv_full" >
					${bisShop.nameCn}
					</div>
				</td>
				<td title="${bisShop.nameEn}" >
					<div class="ellipsisDiv_full" >
					${bisShop.nameEn}
					</div>
				</td>
			</tr>
			</s:iterator>
		</s:iterator>
	</tbody>
</table>
</div>

<script type="text/javascript">

</script>
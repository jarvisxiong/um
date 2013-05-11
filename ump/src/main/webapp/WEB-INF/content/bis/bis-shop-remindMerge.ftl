<#setting number_format="#">

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
		<#list compareMap?keys as mapkey>
			<tr class="mainTr" >
				<td colspan="2" style="font-weight: bold; background-color: #E4E7EC; color: #40A4DE"> ${mapkey}</td>
			</tr>
			<#assign mapValue=compareMap[mapkey] >
			<#list mapValue as bisShop>
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
			</#list>
		</#list>
	</tbody>
</table>
</div>

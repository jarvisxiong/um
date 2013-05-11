<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:if test="priceIndexs!=null&&priceIndexs.size()>1">
<div style="overflow-x: auto; " >

	<table class="tab1" class="tab1" cellspacing="0" cellpadding="0" bordercolor="#83B3DE" border="1" align="center" width="800px;" >
	
		<thead>
			<tr >
			<th rowspan="2" style="background-image: url('');min-width: 160px;" bgcolor="#F1F7FC" width="160" >产品业态</th>
			<th rowspan="2" bgcolor="#F1F7FC" width="80" style="min-width: 80px;">权重</th>
			<s:iterator var="w" value="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" status="status">
				<s:if test="#status.index==0">
				</s:if>
				<s:else>
				<th colspan="3" align="center" style="text-align: center;" bgcolor="#F1F7FC" width="240">${value}</th>
				</s:else>	
			</s:iterator>
			<th rowspan="2" title="初始基准工料价格小计" bgcolor="#F1F7FC" width="100" style="min-width: 100px;">初始小计</th>
			<th rowspan="2" title="当月工料价格小计" bgcolor="#F1F7FC" width="100" style="min-width: 100px;">当月小计</th>
			<th rowspan="2" title="当月工料价格指数" bgcolor="#F1F7FC" width="100" style="min-width: 100px;">价格指数</th>
			</tr>
			<tr style="background-color: #E4E7EC;">
			<s:iterator var="w" value="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" status="status">
			<s:if test="#status.index==0">
			</s:if>
			<s:else>
				<th title="基准工料每m2消耗量" bgcolor="#F1F7FC" width="80" style="min-width: 80px;">Qi工日/㎡</th>
				<th title="基准工料价格" bgcolor="#F1F7FC" width="80" style="min-width: 80px;">C0i元/工日</th>
				<th title="当月工料价格" bgcolor="#F1F7FC" width="80" style="min-width: 80px;">Ci元/工日</th>
			</s:else>	
			
			</s:iterator>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="w" value="priceIndexs" status="status">
				<tr prodMaterialPriceId="${bussinessCd}">
				<%-- <td><s:property value="#status.index+1"></s:property></td>--%>
				
				<td bgcolor="#F1F7FC"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" value="bussinessCd"/></td>
				<td >${weight}</td>
					<s:iterator var="priceIndexCell" value="cells" status="status2">
					<td style="min-width: 80px;"><div  class="ellipsisDiv" style="margin-left: 2px;">${value}</div></td>			
					</s:iterator>
				<td >${initTotal}</td>
				<td >${currentMonthTotal}</td>
				<td >${priceIndex}</td>
				</tr>
			</s:iterator>
			
		</tbody>	
	</table>
	<form action="${ctx}/prod/prod-material-price!loadList.action" id="pageForm" method="post" accept-charset="UTF-8">
	<input type="hidden" name="bussinessCd" id="pmp_input_bussinessCd"></input>	
	</form>
</div>
</s:if>
<s:else>
<div style="margin-left: 10px;">没有符合搜索的结果</div>
</s:else>
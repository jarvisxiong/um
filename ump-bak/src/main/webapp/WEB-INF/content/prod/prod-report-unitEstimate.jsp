<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:if test="unitEstimateVos!=null&&unitEstimateVos.size()>1">
<table  class="tab1" class="tab1" cellspacing="0" cellpadding="0" bordercolor="#83B3DE" border="1" align="center">
	
	<thead>
		<tr >
			<th  style="background-image: url('');"  bgcolor="#F1F7FC">产品业态</th>
			<th   bgcolor="#F1F7FC">权重</th>
			<th  bgcolor="#F1F7FC" title="初始基准工料价格小计">初始基准工料价格小计</th>
			<th  bgcolor="#F1F7FC" title="当月工料价格指数Pi">当月工料价格指数Pi</th>
			<th   bgcolor="#F1F7FC" title="基准单位估算指标（W0）">基准单位估算指标（W0）</th>
			<th   bgcolor="#F1F7FC" title="指标增加指标ΔW i">指标增加指标ΔW i</th>
			<th   bgcolor="#F1F7FC" title="单位估算指标">单位估算指标</th>	
		</tr>
	</thead>
	<tbody>
		<s:iterator var="w" value="unitEstimateVos" status="status">
			<tr prodMaterialPriceId="${bussinessCd}">			
			<td  bgcolor="#F1F7FC"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" value="bussinessCd"/></td>
			<td >${weight}</td>				
			<td >${initTotal}</td>
			<td >${priceIndex}</td>
			<td >${basicEstimate}</td>
			<td >${addPart}</td>
			<td >${estimatePrice}</td>
			</tr>
		</s:iterator>
		
	</tbody>	
</table>
</s:if>	
<s:else>
<div style="margin-left: 10px;">没有符合搜索的结果</div>
</s:else>
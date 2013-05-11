<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table class="prodTable">
	<colgroup>
		<col width="5%"></col>
		<col width="10%"></col>
		<col width="12%"></col>
		<col width="15%"></col>
		<col width="15%"></col>
		<col width="15%"></col>
		<%--<col width="8%"></col> --%>
	</colgroup>
	<thead>
		<tr>
			<th style="background-image: url('');">序号</th>
			<th>时间	</th>
			<th>区域 </th>
			<th>产品业态</th>
			<th>工料范围 	</th>
			<th>工料价格 	</th>			
			<%--<th>创建人</th> --%>
		</tr>
	</thead>
	<tbody>
		<s:iterator var="w" value="page.result" status="status">
			<tr prodmaterialpriceId="${prodMaterialPriceId}">
			<td><s:property value="#status.index+1"></s:property></td>
			<td title="${yearCd}年${monthCd}月" ym="${yearCd}-${monthCd}">${yearCd}-${monthCd}</td>
			<td areacd="<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" value="areaCd"/>"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" value="areaCd"/></td>			
			<td bussinesscd="<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" value="bussinessCd"/>"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" value="bussinessCd"/></td>		
			<td materialzonecd="<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" value="materialZoneCd"/>"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" value="materialZoneCd"/></td>	
			<td currentmonthprice="${currentMonthPrice}">${currentMonthPrice}</td>
			<%--<td >
				${creator }
			</td> --%>
			</tr>
		</s:iterator>
		
	</tbody>	
</table>	
	<div class="pageFooter">
		<input type="hidden" id="pageNums" value="${page.totalPages}"></input>
		<p:page/>
	</div>
	
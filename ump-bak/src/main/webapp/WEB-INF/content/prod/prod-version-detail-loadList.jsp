<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table class="prodTable">
	<colgroup>
		<col width="5%"></col>
		<col width="8%"></col>
		<%--<col width="8%"></col> --%>
		<col width="15%"></col>
		<col width="15%"></col>
		<col width="15%"></col>
		<col width="15%"></col>		
		<%--<col width="15%"></col>	 --%>
	</colgroup>
	<thead>
		<tr>
			<th style="background-image: url('');">序号</th>			
			<th >版本号 </th>
			<%--<th >地区 </th> --%>
			<th class="sortField">产品业态</th>
			<th>工料范围 	</th>
			<th title="人工(工日)、砼(m3)、钢筋(kg)、砌块(m3)">消耗量</th>
			<th>工料价格 	</th>
			<%--<th title="基准单位估算价格" nowrap="nowrap">基准单位估算价格</th> --%>
			
		</tr>
	</thead>
	<tbody>
		<s:iterator var="w" value="page.result" status="status">
			<tr detailId="${prodVersionDetailId}" basicverdionid="${prodBasicVersion.versionNo}">
			<td><s:property value="#status.index+1"></s:property></td>
			<td title="${prodBasicVersion.yearCd}年${prodBasicVersion.monthCd}月">${prodBasicVersion.versionNo}</td>
			<%--<td title="<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" value="areaCd"/>"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdAreaCd()" value="areaCd"/></td> --%>
			<td bussinesscd="<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" value="bussinessCd"/>"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdBussinessCd()" value="bussinessCd"/></td>
			<td materialzonecd="<p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" value="materialZoneCd"/>"><p:code2name mapCodeName="@com.hhz.ump.util.DictMapUtil@getMapProdMaterialZoneCd()" value="materialZoneCd"/></td>
			<td permeterQuantity="${permeterQuantity}">${permeterQuantity}</td>
			<td price="${price}">${price}</td>
			<%--<td estimatePrice="${estimatePrice}">${estimatePrice}</td> --%>			
			</tr>
		</s:iterator>
		
	</tbody>	
</table>	
	<input type="hidden" name="bussinessCd" id="pvd_input_bussinessCd"></input>
	<div class="pageFooter">
	<input type="hidden" id="pageNums" value="${page.totalPages}"></input>
	<input type="hidden" id="sort" name="sort" />
	<input type="hidden" id="order" name="order" value="asc"/>
		<p:page/>
	</div>
	</form>
<#setting number_format="#">

<#if (volist.size()>0)>
<div id="totalDiv" style="overflow: scroll;">
	<table id="mainMatrix" class="innerTable" style="width:1220px;" cellpadding="0" cellspacing="0">
		<col width="35"/>
		<#if floorType==1>
		<col width="100"/>
		<col width="100"/>
		<col width="60"/>
		<col width="140"/>
		<#elseif floorType==2>
		<col width="100"/>
		<col width="100"/>
		<#elseif floorType==3>
		<col width="100"/>
		<col width="100"/>
		</#if>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<col width="60"/>
		<tr>
			<th style="text-align:center; height:50px;">序号</th>
			<#if floorType==1>
			<th style="text-align:center;">商家</th>
			<th style="text-align:center;">合同编号</th>
			<th style="text-align:center;">合同类型</th>
			<th style="text-align:center;">商铺</th>
			<#elseif  floorType==2>
			<th style="text-align:center;">公寓</th>
			<th style="text-align:center;">合同编号</th>
			<#elseif  floorType==3>
			<th style="text-align:center;">多经</th>
			<th style="text-align:center;">合同编号</th>
			</#if>
			<th style="text-align:center;">水费</th>
			<th style="text-align:center;">电费</th>
			<th style="text-align:center;">水电公摊</th>
			<th style="text-align:center;">空调费</th>
			<th style="text-align:center;">电梯费</th>
			<th style="text-align:center;">燃气费</th>
			<th style="text-align:center;">热力费</th>
			<th style="text-align:center;">其他押金</th>
			<th style="text-align:center;">停车费</th>
			<th style="text-align:center;">车位管理费</th>
			<th style="text-align:center;">服务费</th>
			<th style="text-align:center;">维修服务费</th>
			<th style="text-align:center;">清洁服务费</th>
		</tr>
		<#list volist as incomeOtherVo>
		<tr>
			<td class="leftTd" style="text-align:center;">${incomeOtherVo_index+1}</td>
			<td class="leftTd pd-chill-tip" title="${incomeOtherVo.name}">
			<div class="ellipsisDiv_full" style="width:100px;">
			${incomeOtherVo.name}
			</div>
			</td>
			<td class="leftTd pd-chill-tip" title="${incomeOtherVo.contNo}">
			<div class="ellipsisDiv_full" style="width:100px;">
			${incomeOtherVo.contNo}
			</div>
			</td>
			<#if floorType==1>
			<td class="leftTd pd-chill-tip" title="${incomeOtherVo.contType}">
			<div class="ellipsisDiv_full" style="width:60px;">
			${incomeOtherVo.contType}
			</div>
			</td>
			<td class="leftTd pd-chill-tip" title="${incomeOtherVo.storeNos}">
			<div class="ellipsisDiv_full" style="width:140px;">
			${incomeOtherVo.storeNos}
			</div>
			</td>
			</#if>
			<td>${incomeOtherVo.must1}</td>
			<td>${incomeOtherVo.must2}</td>
			<td>${incomeOtherVo.must3}</td>
			<td>${incomeOtherVo.must4}</td>
			<td>${incomeOtherVo.must5}</td>
			<td>${incomeOtherVo.must6}</td>
			<td>${incomeOtherVo.must7}</td>
			<td>${incomeOtherVo.must8}</td>
			<td>${incomeOtherVo.must9}</td>
			<td>${incomeOtherVo.must10}</td>
			<td>${incomeOtherVo.must11}</td>
			<td>${incomeOtherVo.must12}</td>
			<td>${incomeOtherVo.must13}</td>
		</tr>
		</#list>
	</table>
</div>
<#else>
	<div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 100px;">暂无记录</div>
</#if>

<script type="text/javascript">
	$(function(){
		//alert($(window).height());
		//alert($("#totalDiv").height());
		if($("#totalDiv").height() > $(window).height()) {
			$("#totalDiv").height($(window).height()-27-60-2);
		}
	});
</script>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="resultList"  style="width:100%;clear: both;overflow-x:auto; " >
<table  class="commonTable" style="border: 0px none; border-collapse: separate;" cellspacing="0" cellpadding="0">
	<thead>
		<tr>
			<th rowspan="2"  width="35"  align="center"  style="background-image:url('');vertical-align: middle;" nowrap="nowrap">序号</th>
			<th nowrap="nowrap"  style="width:150px;" >投标单位</th>
			<th nowrap="nowrap" style="min-width: 120px;" >标底价</th>
			<th nowrap="nowrap" style="min-width: 120px;" >总报价</th>
			<th nowrap="nowrap" title="平米指标(元/平方米)" style="width:100px;" ><div  class='ellipsisDiv' style="width:100px;overflow:hidden;" >平米指标(元/平方米)</div></th>
			<th nowrap="nowrap" title="分部分项清单报价" ><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">分部分项清单报价</div></th>
			<th nowrap="nowrap"  title="平米指标(元/平方米)"><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">平米指标(元/平方米)</div></th>
			<th nowrap="nowrap"  title="措施项目清单报价"><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">措施项目清单报价</div></th>
			<th nowrap="nowrap" title="平米指标(元/平方米)" ><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">平米指标(元/平方米)</div></th>
			<th nowrap="nowrap" title="其他项目清单报价" ><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">其他项目清单报价</div></th>
			<th nowrap="nowrap"  title="平米指标(元/平方米)" ><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">平米指标(元/平方米)</div></th>			
		</tr>
	</thead>
	<tbody>
	 	<s:iterator  value="tpVoList"  var="totalPriceVo"  status="index" >
	 		<tr>
	 		<td  align="center"  style="text-align: center;"><s:property value="#index.index+1"/></td>
	 			<td nowrap="nowrap" style="width:150px;overflow:hidden;" title="${supName}"><div class='ellipsisDiv' style="width:150px;overflow:hidden;margin-left: 5px;">${supName}</div></td>
	 			<td nowrap="nowrap"  style="width:120px;overflow:hidden;" title="${refPrice}"><div  class='ellipsisDiv' style="width:120px;overflow:hidden;margin-left: 5px;">${refPrice}</div></td>
	 			<td nowrap="nowrap"  style="width:120px;overflow:hidden;" title="${supTotalPrice}"><div  class='ellipsisDiv' style="width:120px;overflow:hidden;margin-left: 5px;">${supTotalPrice}</div></td>
	 			<td nowrap="nowrap"  style="width:80px;overflow:hidden;" title="${perSupTotalPrice}"><div  class='ellipsisDiv' style="width:80px;overflow:hidden;margin-left: 5px;">${perSupTotalPrice}</div></td>
	 			<td nowrap="nowrap" style="width:100px;overflow:hidden;" title="${divisionTotalPrice}"><div  class='ellipsisDiv' style="width:120px;overflow:hidden;margin-left: 5px;">${divisionTotalPrice}</div></td>
	 			<td nowrap="nowrap"  style="width:80px;overflow:hidden;" title="${perMeterdivisionPrice}"><div  class='ellipsisDiv' style="width:80px;overflow:hidden;margin-left: 5px;">${perMeterdivisionPrice}</div></td>
	 			<td nowrap="nowrap"  style="width:100px;overflow:hidden;" title="${measureOneTotalPrice}"><div  class='ellipsisDiv' style="width:120px;overflow:hidden;margin-left: 5px;">${measureOneTotalPrice}</div></td>
	 			<td nowrap="nowrap"  style="width:80px;overflow:hidden;" title="${perMeterMeasureOnePrice}"><div  class='ellipsisDiv' style="width:80px;overflow:hidden;margin-left: 5px;">${perMeterMeasureOnePrice}</div></td>
	 			<td nowrap="nowrap"  style="width:100px;overflow:hidden;" title="${measureTwoTotalPrice}"><div  class='ellipsisDiv' style="width:120px;overflow:hidden;margin-left: 5px;">${measureTwoTotalPrice}</div></td>
	 			<td nowrap="nowrap" style="width:80px;overflow:hidden;" title="${perMetermeasureTwoPrice}"><div  class='ellipsisDiv' style="width:80px;overflow:hidden;margin-left: 5px;">${perMetermeasureTwoPrice}</div></td>	 			
	 		</tr>
	 	</s:iterator> 	
	</tbody>
</table>
</div>
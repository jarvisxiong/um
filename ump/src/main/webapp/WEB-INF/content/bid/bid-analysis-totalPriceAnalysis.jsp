<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div id="resultList"  style="width:100%;clear: both;overflow-x:auto;" >
<table  class="bidTable" style="border: 0px none; border-collapse: separate;" cellspacing="0" cellpadding="0">
	<thead>
	
		<tr>
			<th  rowspan="2"  width="35"  align="center"  style="background-image:url('');vertical-align: middle;" nowrap="nowrap">序号</th>
			<th nowrap="nowrap" style="width:120px;">投标单位</th>
			<th nowrap="nowrap" style="min-width: 80px;">标底价</th>
			<th nowrap="nowrap" style="min-width: 80px;">总报价</th>
			<th nowrap="nowrap" >比例</th>
			<th nowrap="nowrap" title="分部分项清单报价" style="min-width: 80px;"><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">分部分项清单报价</div></th>
			<th nowrap="nowrap" style="width: 20px;">所占比例</th>
			<th nowrap="nowrap"  title="措施项目清单报价" style="min-width: 80px;"><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">措施项目清单报价</div></th>
			<th nowrap="nowrap" style="width: 20px;">所占比例</th>
			<th nowrap="nowrap" title="其他项目清单报价" style="min-width: 80px;"><div  class='ellipsisDiv' style="width:100px;overflow:hidden;">其他项目清单报价</div></th>
			<th nowrap="nowrap" style="width: 20px;">所占比例</th>			
		</tr>
	</thead>
	<tbody>
	 	<s:iterator  value="tpVoList"  var="totalPriceVo"  status="index" >
	 		<tr>
	 		<td align="center"  style="text-align: center;"><s:property value="#index.index+1"/></td>
	 			<td nowrap="nowrap" title="${supName}"><div class='ellipsisDiv analysisTd' >${supName}</div></td>
	 			<td nowrap="nowrap" title="${refPrice}"><div  class='ellipsisDiv analysisTd'>${refPrice}</div></td>
	 			<td nowrap="nowrap" title="${totalPriceVo.supTotalPrice}"><div  class='ellipsisDiv analysisTd' >${totalPriceVo.supTotalPrice}</div></td>
	 			<td nowrap="nowrap" title="${percent}">${percent}%</td>
	 			<td nowrap="nowrap" title="${divisionTotalPrice}">${divisionTotalPrice}</td>
	 			<td nowrap="nowrap" title="${divisionPercent}">${divisionPercent}%</td>
	 			<td nowrap="nowrap" title="${measureOneTotalPrice}">${measureOneTotalPrice}</td>
	 			<td nowrap="nowrap" title="${measureOnePercent}">${measureOnePercent}%</td>
	 			<td nowrap="nowrap" title="${measureTwoTotalPrice}">${measureTwoTotalPrice}</td>
	 			<td nowrap="nowrap" title="${measureTwoPercent}">${measureTwoPercent}%</td>	 			
	 		</tr>
	 	</s:iterator> 	
	</tbody>
</table>
</div>
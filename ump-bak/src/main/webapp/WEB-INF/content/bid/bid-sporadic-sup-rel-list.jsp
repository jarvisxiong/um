<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<table class="bidTable" style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
	<thead>
	<tr class="header">
		<th width="35"  class="thNoLine">序号</th>
		<th>项目名称 </th>
		<th>单位</th>
		<th>暂定数量</th>
		<th>综合单价(元)</th>
		<th>合价(元)</th>			
	</tr>
	</thead>
	<tbody>
	<s:iterator value="bidSporadicSupRelRs.result"  var="bidSporadicSupRel"  status="index">
	<tr  class="commonTr">
		<td  align="center" style="text-align: center;" ><s:property value="#index.index+1"/></td>
		<td title='${itemName}'><div class="ellipsisDiv"  >${itemName}</div></td>
		<td title='${basicDesc}'><div  class="ellipsisDiv"  >${basicDesc}</div></td>
		<td title='${tempQuantitie}'><div  class="ellipsisDiv"  >${tempQuantitie}</div></td>
		<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">
			<td title='***'><div  class="ellipsisDiv" >***</div></td>
			<td title='***'><div  class="ellipsisDiv"  >***</div></td>
		</s:if>
		<s:else>
			<td title='${compUnitAmt}'><div  class="ellipsisDiv" >${compUnitAmt}</div></td>
			<td title='${totalAmt}'><div  class="ellipsisDiv"  >${totalAmt}</div></td>
		</s:else>
		
		
	</tr>
	</s:iterator>
	</tbody>
</table>


<div>
<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-sporadic-sup-rel!list.action">
  	<p:page />
</div>
</div>
				
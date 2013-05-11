<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 
<%--标段其他费用 --%>
<table class="bidTable"  style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
	<thead>
	<tr class="header">
		<th width="35"   style="background-image:url('');">序号</th>				
		<th>费用名称 </th>
		<th>费用（元）</th>
		<th>备注</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="bidSupOtherRelRs.result"  var="bidSupOtherRel"  status="index">
	<tr class="commonTr">
		<td  align="center" style="text-align: center;" ><s:property value="#index.index+1"/></td>		
		<td title='${feeName}'><div class="ellipsisDiv  exceltableContent">${feeName}</div></td>
		<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">
			<td title='***'><div class="ellipsisDiv exceltableContent">***</div></td>
			<td title='***'><div class="ellipsisDiv exceltableContent">***</div></td>		
		</s:if>
		<s:else>
			<td title='${feeAmt}'><div class="ellipsisDiv exceltableContent">${feeAmt}</div></td>
			<td title='${remark}'><div class="ellipsisDiv exceltableContent">${remark}</div></td>		
		</s:else>	
	</tr>
	</s:iterator>
		</tbody>
</table>


<div>
	<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-sup-other-rel!list.action">
		<p:page />
	</div>
</div>
				
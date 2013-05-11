<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
 

<table class="bidTable" style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
	<thead>
	<tr class="header">
		<th width="45" class="thNoLine">序号</th>				
		<th>(措)项目名称 </th>
		<th>计算基础 </th>
		<th>费率</th>
		<th>金额(元)</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="bidTaxsSupRelRs.result"  var="bidTaxsSupRel"  status="index">
	<tr class="commonTr">
		<td  style="text-align: center;" ><s:property value="#index.index+1"/></td>		
		<td title='${itemName}'><div class="decorate" >${itemName}</div></td>
		<td title='${calcBasicDesc}'><div class="decorate" >${calcBasicDesc}</div></td>
		<td title='${rate}'><div class="decorate" >${rate}</div></td>
		<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">
			<td  title='***'><div class="decorate">***</div></td>
		</s:if>
		<s:else>
			<td  title='${amt}'><div class="decorate">${amt}</div></td>
		</s:else>
		
	</tr>
	</s:iterator>
	</tbody>
</table>

<div>
	<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-taxs-sup-rel!list.action">
		<p:page />
	</div>
</div>
				
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--措施项目清单与计价表(二) --%>
<table class="bidTable" style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
	<thead>
	<tr class="header">
		<th width="45"  class="thNoLine">序号</th>			
		<th>项目编号</th>
		<th>项目名称 </th>
		<th style="text-align: center;">计量单位</th>
		<th>工程量</th>
		<th>综合单价(元)</th>
		<th>合价(元)</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="bidMeasureSupRel2Rs.result"  var="bidMeasureSupRel2"  status="index">
	<tr>
		<td  align="center" style="text-align: center;"><s:property value="#index.index+1"/></td>			
		<td  title='${itemNo}'><div  class="ellipsisDiv  exceltableContent" >${itemNo}</div></td>
		<td  title='${itemName}'><div   class="decorate">${itemName}</div></td>
		<td  title='${calcBasicDesc}' style="text-align: center;"><div  class="decorate">${calcBasicDesc}</div></td>
		<td  title='${quantitie}'><div  class="decorate" >${quantitie}</div></td>
		<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">
			<td  title='***'><div  class="decorate">***</div></td>
			<td  title='***'><div  class="decorate">***</div></td>
		</s:if>
		<s:else>				
			<td  title='${compUnitAmt}'><div  class="decorate">${compUnitAmt}</div></td>
			<td  title='${totalAmt}'><div  class="decorate">${totalAmt}</div></td>
		</s:else>
	</tr>
	</s:iterator>
	</tbody>
</table>

<div>
	<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-measure-sup-rel2!list.action">
		<p:page />
	</div>
</div>
				
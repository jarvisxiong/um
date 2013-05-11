<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--措施项目清单与计价表(一) --%>
<table class="bidTable" style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
	<colgroup>
		<col style="width:45px"/>
		<col/>
		<col style="width:100px"/>
		<col style="width:100px"/>
		<col style="width:100px"/>
	</colgroup>
	<thead>
		<tr class="header">
			<th class="thNoLine">序号</th>
			<th>项目名称 </th>
			<th>计算基础</th>
			<th>费率</th>
			<th>金额(元)</th>
		</tr>
	</thead>
	<tbody>
		<s:iterator value="bidMeasureSupRelRs.result" var="bidMeasureSupRel"  status="index">
		<tr>
			<td  align="center" style="text-align: center;" ><s:property value="#index.index+1"/></td>
			<td  title='${itemName}'><div class="decorate">${itemName}</div></td>
			<td  title='${calcBasicDesc}'><div  class="decorate" >${calcBasicDesc}</div></td>
			<td  title='${rate}' ><div class="decorate" >${rate}</div></td>
			<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">					
				<td  title='***'><div  class="decorate">***</div></td>
			</s:if>
			<s:else>					
				<td  title='${amt}'><div  class="decorate">${amt}</div></td>
			</s:else>
		</tr>
		</s:iterator>
	</tbody>
</table>

<div>
	<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-measure-sup-rel!list.action">
		<p:page />
	</div>
</div>
				
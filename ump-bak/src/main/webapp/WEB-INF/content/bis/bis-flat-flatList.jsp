<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/css/bis/bis-shop.css" type="text/css" />
<table>
<tr>
 <td width="250px;" valign="top">
   <div id="leftTopDiv" style="width:250px;">
	<table style="width:100%;" class="report_table">
	 <thead>
		 <tr class="top_thead" style="height:60px;">
		  <th rowspan="2">公寓编号</th>
		  <th rowspan="2">面积</th>
		  <th rowspan="2">套内面积</th>
		  <th rowspan="2">公摊面积</th>
		 </tr>
	 </thead>
	</table>
	</div>
	<div id="leftDiv" style="width:250px;overflow:hidden;height:300px;"> 
	 <table id="leftTable" style="width:100%;" class="report_table">
	  <s:iterator value="flatUtils">
		 <tr>
		  <td>${flatNo}</td>
		  <td>${square}</td>
		  <td>${innerSquare}</td>
		  <td>${PublicSquare}</td>
		  </tr>
	 </s:iterator>
	 </table>
	</div>
 </td>
 <td valign="top">
  <div id="rightTopDiv" style="border-bottom: 1px solid rgb(170, 171, 176); overflow: hidden;width:800px;height:60px;">
	 <table id="rightTopTable" class="report_table">
	  <thead  style="height:60px;">
		 <tr class="top_thead">
		 <c:if test='${fn:contains(chargeTypeCd, "32")}'>
		  <th colspan="5">物业费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "33")}'>
		  <th colspan="5">水费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "34")}'>
		  <th colspan="5">电费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "35")}'>
		  <th colspan="5">水电公摊</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "36")}'>
		  <th colspan="5">空调费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "37")}'>
		  <th colspan="5">电梯费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "38")}'>
		  <th colspan="5">物业履约保证费</th>
		 </c:if>
		 </tr>
		 <tr class="top_thead">
		   <c:if test='${fn:contains(chargeTypeCd, "32")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "33")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		  <c:if test='${fn:contains(chargeTypeCd, "34")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		  <c:if test='${fn:contains(chargeTypeCd, "35")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		  <c:if test='${fn:contains(chargeTypeCd, "36")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		  <c:if test='${fn:contains(chargeTypeCd, "37")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		  <c:if test='${fn:contains(chargeTypeCd, "38")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		 </tr>
	 </thead>
	</table>
 </div>
 <div id="rightDiv" style="overflow: auto;width:800px;height:300px;" onscroll="resetLayout($(this));">
  <table id="rightTable" class="report_table">
   <s:iterator value="flatUtils">
	 <tr>
	  <c:if test='${fn:contains(chargeTypeCd, "32")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='32'">
		     <c:set var="haveVal" value="true"></c:set>
			  <td>${yearMoneyRel}</td>
			  <td>${monthMoneyRel}</td>
			  <td>${yearMoneySub}</td>
			  <td>${monthMoneySub}</td>
			  <td>${befYearMoney}</td>
			 </s:if>
			</s:iterator>
			<c:if test="${haveVal==false}">
			      <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
			</c:if>
		</c:if>
	 <c:if test='${fn:contains(chargeTypeCd, "33")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='33'">
		     <c:set var="haveVal" value="true"></c:set>
			  <td>${yearMoneyRel}</td>
			  <td>${monthMoneyRel}</td>
			  <td>${yearMoneySub}</td>
			  <td>${monthMoneySub}</td>
			  <td>${befYearMoney}</td>
			 </s:if>
			</s:iterator>
			<c:if test="${haveVal==false}">
			      <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
			</c:if>
		</c:if>
		<c:if test='${fn:contains(chargeTypeCd, "34")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='34'">
		     <c:set var="haveVal" value="true"></c:set>
			  <td>${yearMoneyRel}</td>
			  <td>${monthMoneyRel}</td>
			  <td>${yearMoneySub}</td>
			  <td>${monthMoneySub}</td>
			  <td>${befYearMoney}</td>
			 </s:if>
			</s:iterator>
			<c:if test="${haveVal==false}">
			      <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
			</c:if>
		</c:if>
		<c:if test='${fn:contains(chargeTypeCd, "35")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='35'">
		     <c:set var="haveVal" value="true"></c:set>
			  <td>${yearMoneyRel}</td>
			  <td>${monthMoneyRel}</td>
			  <td>${yearMoneySub}</td>
			  <td>${monthMoneySub}</td>
			  <td>${befYearMoney}</td>
			 </s:if>
			</s:iterator>
			<c:if test="${haveVal==false}">
			      <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
			</c:if>
		</c:if>
		<c:if test='${fn:contains(chargeTypeCd, "36")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='36'">
		     <c:set var="haveVal" value="true"></c:set>
			  <td>${yearMoneyRel}</td>
			  <td>${monthMoneyRel}</td>
			  <td>${yearMoneySub}</td>
			  <td>${monthMoneySub}</td>
			  <td>${befYearMoney}</td>
			 </s:if>
			</s:iterator>
			<c:if test="${haveVal==false}">
			      <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
			</c:if>
		</c:if>
		<c:if test='${fn:contains(chargeTypeCd, "37")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='37'">
		     <c:set var="haveVal" value="true"></c:set>
			  <td>${yearMoneyRel}</td>
			  <td>${monthMoneyRel}</td>
			  <td>${yearMoneySub}</td>
			  <td>${monthMoneySub}</td>
			  <td>${befYearMoney}</td>
			 </s:if>
			</s:iterator>
			<c:if test="${haveVal==false}">
			      <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
			</c:if>
		</c:if>
		<c:if test='${fn:contains(chargeTypeCd, "38")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='38'">
		     <c:set var="haveVal" value="true"></c:set>
			  <td>${yearMoneyRel}</td>
			  <td>${monthMoneyRel}</td>
			  <td>${yearMoneySub}</td>
			  <td>${monthMoneySub}</td>
			  <td>${befYearMoney}</td>
			 </s:if>
			</s:iterator>
			<c:if test="${haveVal==false}">
			      <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
				  <td></td>
			</c:if>
		</c:if>
		</tr>
	 </s:iterator>
  </table>
 </div>
 </td>
</tr>
</table>
<script type="text/javascript">
//滚动时,自动保持左下/右上/右下布局对齐.
function resetLayout(obj) {
	var t = parseInt(obj.scrollTop());
	var l = parseInt(obj.scrollLeft());
	$("#leftTable").css("margin-top", -t + "px");
	$("#rightTopTable").css("margin-left", -l + "px");
} 
</script>
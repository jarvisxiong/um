<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-shop.css" />
<table>
<tr>
 <td width="250px;" valign="top">
   <div id="leftTopDiv" style="width:250px;">
	<table style="width:100%;" class="report_table">
	 <thead>
		 <tr class="top_thead" style="height:30px;">
		  <th colspan="2">租户名称</th>
		  <th rowspan="2">产权面积(建筑)</th>
		 </tr>
		 <tr style="height:30px;">
		   <th>商家</th>
		   <th>商铺</th>
		 </tr>
	 </thead>
	</table>
	</div>
	<div id="leftDiv" style="width:250px;overflow:hidden;height:300px;"> 
	<table id="leftTable" class="report_table">
		<s:iterator value="shopTenantUtils">
		<tr onclick="newTenant('${tentantId}');">
			<td class="pd-chill-tip" title="${tentantName}">
			<div class="ellipsisDiv" >
			${tentantName}
			</div>
			</td>
			<td class="pd-chill-tip" title="${storeName}">
			<div class="ellipsisDiv" >
			${storeName}
			</div>
			</td>
			<td>${shopArea}</td>
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
		 <c:if test='${fn:contains(chargeTypeCd, "1")}'>
		  <th colspan="5">租金</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "11")}'>
		  <th colspan="5">佣金</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "31")}'>
		  <th colspan="5">综合管理费</th>
		 </c:if>
		 
		 <c:if test='${fn:contains(chargeTypeCd, "32")}'>
		  <th colspan="5">物业管理费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "35")}'>
		  <th colspan="5">水电公摊</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "33")}'>
		  <th colspan="5">水费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "34")}'>
		  <th colspan="5">电费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "36")}'>
		  <th colspan="5">空调费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "02")}'>
		  <th colspan="5">租金履约保证金</th>
		 </c:if>
		 </tr>
		 <tr class="top_thead">
		   <c:if test='${fn:contains(chargeTypeCd, "1")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		 </c:if>
		 <c:if test='${fn:contains(chargeTypeCd, "11")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		  <c:if test='${fn:contains(chargeTypeCd, "31")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		  <c:if test='${fn:contains(chargeTypeCd, "32")}'>
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
		  <c:if test='${fn:contains(chargeTypeCd, "36")}'>
			 <th>本年累计比较数</th>
			  <th>本月比较数</th>
			  <th>本年累计欠费</th>
			  <th>本月欠费</th>
			  <th>以前年度欠费</th>
		  </c:if>
		  <c:if test='${fn:contains(chargeTypeCd, "02")}'>
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
  <s:iterator value="shopTenantUtils">
	 <tr onclick="newTenant('${tentantId}');">
	  <c:if test='${fn:contains(chargeTypeCd, "1")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='1'">
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
	 <c:if test='${fn:contains(chargeTypeCd, "11")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='11'">
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
		<c:if test='${fn:contains(chargeTypeCd, "31")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='31'">
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
		<c:if test='${fn:contains(chargeTypeCd, "02")}'>
	   <c:set var="haveVal" value="false"></c:set>
		  <s:iterator value="tenantTypeReportUtils">
		    <s:if test="chargeTypeCdRel=='02'">
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
function searchReport(){
	
	if(chargeTypeCd==""){
		chargeTypeCd="01,02";
	}
	$.post("${ctx}/bis/bis-tenant!shopList.action",
			{
		      yearQuery:yearQuery,
		      monthQuery:monthQuery,
		      bisProjectId:bisProjectId,
		      chargeTypeCd:chargeTypeCd
		    },
			function(result) {
				if (result) {
					$("#resultDiv").html(result);
					var typeStr =(chargeTypeCd.split(",")).length;
					$("#rightTable").css("width",400*typeStr+"px");
				}
			}
		);
}
function newTenant(tenantId){
	if(tenantId=='') {
		return;
	}
	var url="${ctx}/bis/bis-tenant!main.action?bisTenantId="+tenantId+"&bisProjectId="+$("#bisProjectId").val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisTenant","商业租户",url,true);
}
//滚动时,自动保持左下/右上/右下布局对齐.
function resetLayout(obj) {
	var t = parseInt(obj.scrollTop());
	var l = parseInt(obj.scrollLeft());
	$("#leftTable").css("margin-top", -t + "px");
	$("#rightTopTable").css("margin-left", -l + "px");
} 
</script>
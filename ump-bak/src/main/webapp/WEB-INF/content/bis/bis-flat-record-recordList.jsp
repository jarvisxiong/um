<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!--
<div style="width:100%;height:100%;padding-top: 10px;padding-bottom:5px;background-color: #E4E7EC;overflow-x: hidden;">
-->
<div style="width:100%;height:100%;">
	<%--公寓基本信息 --%>
	<div style="padding-left: 10px;">
		<table cellpadding="0" cellspacing="0">
			<col width="100"/>
			<col/>
			<col width="120"/>
			<col/>
			<col width="120"/>
			<col/>
			<col width="120"/>
			<col/>
			<tr style="height: 25px;"><td colspan="6" style="font-weight: bolder;font-size: 15px;" align="left">公寓信息：</td></tr>
			<tr style="height: 25px;">
				<td align="right">客户名称：</td>
				<td style="font-weight: bolder;">${bisFlat.customerName}</td>
				<td align="right">公寓编号：</td>
				<td style="font-weight: bolder;">${bisFlat.flatNo}</td>
				<td align="right">楼栋名称：</td>
				<td style="font-weight: bolder;"><p:code2name mapCodeName="mapBuilding" value="bisFlat.bisFloor.bisFloorId"/></td>
				<td align="right">建筑面积㎡：</td>
				<td style="font-weight: bolder;">${bisFlat.square}</td>
			</tr>
			<tr style="height: 25px;">
				<td align="right">套内面积㎡：</td>
				<td style="font-weight: bolder;">${bisFlat.innerSquare}</td>
				<td align="right">实测建面㎡：</td>
	            <td style="font-weight: bolder;">${bisFlat.actualSquare}</td>
	            <td align="right">实测套面㎡：</td>
				<td style="font-weight: bolder;">${bisFlat.actualInnerSquare}</td>
				<td align="right">物管费(元/月)：</td>
				<td style="font-weight: bolder;">${bisFlat.monPromanfeePrice}</td>
			</tr>
			<s:if test="costType == 4">
				<tr style="height: 25px;">
					<td align="right">累计应收：</td>
		            <td style="font-weight: bolder;">${bisFlat.annualYsMan}</td>
		            <td align="right">累计实收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualSsMan}</td>
					<td align="right">累计预收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualYusMan}</td>
					<td align="right">累计欠收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualQsMan}</td>
				</tr>
			</s:if>
			<s:if test="costType == 5">
				<tr style="height: 25px;">
					<td align="right">累计应收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualYsWater}</td>
					<td align="right">累计实收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualSsWater}</td>
					<td align="right">累计预收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualYusWater}</td>
					<td align="right">累计欠收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualQsWater}</td>
				</tr>
			</s:if>
			<s:if test="costType == 6">
				<tr style="height: 25px;">
					<td align="right">累计应收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualYsElec}</td>
					<td align="right">累计实收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualSsElec}</td>
					<td align="right">累计预收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualYusElec}</td>
					<td align="right">累计欠收：</td>
					<td style="font-weight: bolder;">${bisFlat.annualQsElec}</td>
				</tr>
			</s:if>
		</table>
	</div>
	
	<%--公寓收费记录信息 --%>
	<div align="right" style="margin-top: 10px;"><span style="font-weight: bolder;font-size: 13px;">单位：元</span></div>
	<div style="width:100%;height:285px;margin-top: 5px;overflow-x: auto;">
		<table width="100%" class="content_table" cellpadding="0" cellspacing="0">
		  <thead>
		        <tr class="header">
		            <th scope="col" align="center" style="width: 30px;">序号</th>
		            <th scope="col" align="left" style="width: 80px;">年月</th>
		            <th scope="col" align="left" style="width: 80px;">本月实收</th>
	            	<th scope="col" align="left" style="width: 90px;">缴费跨期</th>
		            <th scope="col" align="left" style="width: 80px;">本月是否起收</th>
		            <s:if test="costType==5">
		            	<th scope="col" align="left" style="width: 100px;">本月水费吨数</th>
	            	</s:if>
	            	<s:if test="costType==6">
		            	<th scope="col" align="left" style="width: 100px;">本月电费度数</th>
	            	</s:if>
	            	<th scope="col" align="left" style="width: 80px;">本月应收</th>
	            	<th scope="col" align="left" style="width: 80px;">本月已收</th>
	            	<th scope="col" align="left" style="width: 80px;">本月收回本月</th>
	            	<th scope="col" align="left" style="width: 80px;">本月预收</th>
	            	<th scope="col" align="left" style="width: 120px;">本月收回本年度欠款</th>
	            	<th scope="col" align="left" style="width: 130px;">本月收回以前年度欠款</th>
	            	<s:if test="costType==4">
		            	<th scope="col" align="left" style="width: 100px;">本月欠收物管费</th>
	            	</s:if>
	            	<s:if test="costType==5">
		            	<th scope="col" align="left" style="width: 90px;">本月欠收水费</th>
	            	</s:if>
	            	<s:if test="costType==6">
		            	<th scope="col" align="left" style="width: 90px;">本月欠收电费</th>
	            	</s:if>
	            	<th scope="col" align="left" style="width: 80px;">本年累计应收</th>
	            	<th scope="col" align="left" style="width: 80px;">累计应收</th>
	            	<th scope="col" align="left" style="width: 80px;">本年累计实收</th>
	            	<th scope="col" align="left" style="width: 80px;">累计实收</th>
		            <%--物管费 --%>
		            <s:if test="costType==4">
		            	<th scope="col" align="left" style="width: 130px;">本年收回本年物管费</th>
		            	<th scope="col" align="left" style="width: 120px;">本年度预收物管费</th>
	            	</s:if>
	            	<%--水费 --%>
	            	<s:if test="costType==5">
		            	<th scope="col" align="left" style="width: 120px;">本年收回本年水费</th>
		            	<th scope="col" align="left" style="width: 100px;">本年度预收水费</th>
	            	</s:if>
	            	<%--电费 --%>
	            	<s:if test="costType==6">
		            	<th scope="col" align="left" style="width: 130px;">本年收回本年电费</th>
		            	<th scope="col" align="left" style="width: 100px;">本年度预收电费</th>
	            	</s:if>
	            	
	            	<th scope="col" align="left" style="width: 130px;">累计收回以前年度欠费</th>
	            	<th scope="col" align="left" style="width: 90px;">累计预收</th>
	            	<th scope="col" align="left" style="width: 90px;">本年累计欠收</th>
	            	<th scope="col" align="left" style="width: 90px;">累计欠收</th>
		        </tr>
		  </thead>
		  <tbody>
			  <s:iterator value="page.result" status="sta" var="fl">
				    <tr class="mainTr" style="height: 35px;">
		  	    		<td align="center" class="pd-chill-tip">
		  	    			<s:property value="#sta.index+1"/>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip" title='${recordYear}年${recordMonth}月'>
		  	    			<div class="ellipsisDiv_full" >${recordYear}年${recordMonth}月</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip" title='${monthSs}'>
		  	    			<div class="ellipsisDiv_full" >${monthSs}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${paymentIntertemporal}'>
		  	    			<div class="ellipsisDiv_full" >${paymentIntertemporal}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${chargeFlg}'>
		  	    			<div class="ellipsisDiv_full" >${chargeFlg}</div>
		  	    		</td>
		  	    		
		  	    		<s:if test="costType==5">
			            	<td align="center" class="pd-chill-tip"  title='${waterTonnage}'>
			  	    			<div class="ellipsisDiv_full" >${waterTonnage}</div>
			  	    		</td>
		            	</s:if>
		            	<s:if test="costType==6">
			            	<td align="center" class="pd-chill-tip"  title='${elecDegree}'>
			  	    			<div class="ellipsisDiv_full" >${elecDegree}</div>
			  	    		</td>
		            	</s:if>
		            	
		  	    		<td align="center" class="pd-chill-tip" title='${monthYs}'>
		  	    			<div class="ellipsisDiv_full" >${monthYs}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip" title='${monthYis}'>
		  	    			<div class="ellipsisDiv_full" >${monthYis}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${monthMonth}'>
		  	    			<div class="ellipsisDiv_full" >${monthMonth}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${monthYus}'>
		  	    			<div class="ellipsisDiv_full" >${monthYus}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${currYearDebt}'>
		  	    			<div class="ellipsisDiv_full" >${currYearDebt}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${agoYearDebt}'>
		  	    			<div class="ellipsisDiv_full" >${agoYearDebt}</div>
		  	    		</td>
		  	    		
		  	    		<s:if test="costType==4">
		  	    			<td align="center" class="pd-chill-tip"  title='${owePropRate}'>
			  	    			<div class="ellipsisDiv_full" >${owePropRate}</div>
			  	    		</td>
		            	</s:if>
		  	    		<s:if test="costType==5">
			            	<td align="center" class="pd-chill-tip"  title='${oweWaterRate}'>
		  	    				<div class="ellipsisDiv_full" >${oweWaterRate}</div>
		  	    			</td>
		            	</s:if>
		            	<s:if test="costType==6">
			            	<td align="center" class="pd-chill-tip"  title='${oweElecRate}'>
		  	    				<div class="ellipsisDiv_full" >${oweElecRate}</div>
		  	    			</td>
		            	</s:if>
		            	
		            	<td align="center" class="pd-chill-tip"  title='${currYearYsTotal}'>
		  	    			<div class="ellipsisDiv_full" >${currYearYsTotal}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${ysTotal}'>
		  	    			<div class="ellipsisDiv_full" >${ysTotal}</div>
		  	    		</td>
		            	<td align="center" class="pd-chill-tip"  title='${currYearSsTotal}'>
		  	    			<div class="ellipsisDiv_full" >${currYearSsTotal}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${ssTotal}'>
		  	    			<div class="ellipsisDiv_full" >${ssTotal}</div>
		  	    		</td>
	            		<%-- 物管费 --%>
		  	    		<s:if test="costType==4">
		  	    			<td align="center" class="pd-chill-tip"  title='${currYearPropRate}'>
			  	    			<div class="ellipsisDiv_full" >${currYearPropRate}</div>
			  	    		</td>
		  	    			<td align="center" class="pd-chill-tip"  title='${currYearYusPropRate}'>
			  	    			<div class="ellipsisDiv_full" >${currYearYusPropRate}</div>
			  	    		</td>
		            	</s:if>
		            	<%--水费 --%>
		            	<s:if test="costType==5">
		            		<td align="center" class="pd-chill-tip"  title='${currYearWaterRate}'>
			  	    			<div class="ellipsisDiv_full" >${currYearWaterRate}</div>
			  	    		</td>
		            		<td align="center" class="pd-chill-tip"  title='${currYearYusWaterRate}'>
			  	    			<div class="ellipsisDiv_full" >${currYearYusWaterRate}</div>
			  	    		</td>
		            	</s:if>
		            	<%--电费 --%>
		            	<s:if test="costType==6">
		            		<td align="center" class="pd-chill-tip"  title='${currYearElecRate}'>
			  	    			<div class="ellipsisDiv_full" >${currYearElecRate}</div>
			  	    		</td>
		            		<td align="center" class="pd-chill-tip"  title='${currYearYusElecRate}'>
			  	    			<div class="ellipsisDiv_full" >${currYearYusElecRate}</div>
			  	    		</td>
		            	</s:if>
		            	<td align="center" class="pd-chill-tip"  title='${agoYearOwnfeeTotal}'>
		  	    			<div class="ellipsisDiv_full" >${agoYearOwnfeeTotal}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${yusTotal}'>
		  	    			<div class="ellipsisDiv_full" >${yusTotal}</div>
		  	    		</td>
		            	<td align="center" class="pd-chill-tip"  title='${currYearQsTotal}'>
		  	    			<div class="ellipsisDiv_full" >${currYearQsTotal}</div>
		  	    		</td>
		  	    		<td align="center" class="pd-chill-tip"  title='${qsTotal}'>
		  	    			<div class="ellipsisDiv_full" >${qsTotal}</div>
		  	    		</td>
			    	</tr>
			  </s:iterator>
	  		<s:if test="page.result==null||page.result.size()<1">
				<tr class="mainTr">
					<td class="pd-chill-tip" <s:if test="costType==4"> colspan="22"</s:if><s:else> colspan="23"</s:else>>
						<div align="center">无记录！</div>
					</td>
				</tr>
			</s:if>
		</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
function loadRecordList(dom){
	var index = $("#index").val();
	var bisFlatId = $("#bisFlatId").val();
	var costType = dom.value;
	var data = {bisFlatId:bisFlatId, costType:costType, index:index};
	var url = "${ctx}/bis/bis-flat-record!loadRecordByFlatId.action";
    TB_showMaskLayer("正在搜索...");
	$.post(url,data,function(result){
		TB_removeMaskLayer();
		//$("#recordList"+index).html('');
		$("#recordList"+index).html(result);
	});
}

function recrodQuery(){
	var index = $("#index").val();
	TB_showMaskLayer("正在搜索...");
	$("#recordListFormPage").ajaxSubmit(function(result){
		TB_removeMaskLayer();
		$("#recordList"+index).html(result);
	});
}

/*
function jumpPageTo() {
    var index = $("#pageTo").val();
    index = parseInt(index);
    if (index > 0) {
        jumpPage(index);
    }
}
//跳转到相应的页
function jumpPage(pageNo){
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索...");
	$("#recordListFormPage").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		var index = $("#index").val();
		$("#recordList"+index).html(result);
	});
}
*/
</script>
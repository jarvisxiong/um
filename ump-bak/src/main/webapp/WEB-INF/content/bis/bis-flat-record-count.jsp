<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div style="width:100%;height:100%;">
	<%--查询信息 --%>
	<div style="padding-left: 10px;">
		<table class="tb_search" style="width: 100%" cellpadding="0" cellspacing="0">
			<col width="50px"/>
			<col/>
			<col width="80px"/>
			<col/>
			<col width="60px"/>
			<col/>
			<tr style="height: 35px;">
			 	<td>项目：</td>
			 	<td>
					<input type="text" id="bisProjectName3" value="${bisProjectName}" style="width:120px;" readonly="readonly"/>
					<input type="hidden" id="bisProjectId3" value="${bisProjectId}"/>
				</td>
				<td>费用类别：</td>
			 	<td>
					<s:select cssStyle="width:125px;"
							  list="@com.hhz.ump.util.DictMapUtil@getMapGyCostType()" 
							  listKey="key" 
							  listValue="value" 
							  name="costType" 
							  id="costType3"></s:select>
				</td>
				<td>公寓编号：</td>
			 	<td>
					<input type="text" id="flatNoCount" value="${flatNo}" style="width:120px;"/>
				</td>
			</tr>
			<tr style="height: 35px;">
				<td>楼号：</td>
			 	<td>
					<input type="hidden" id="bisFloorId3" value="${bisFloorId}"/>
					<input type="text" class="pd-chill-tip" id="bisFloorName3" value="${bisFloorName}" title="${bisFloorName}" readonly="readonly" style="width:120px;" onclick="showDiv(true,3);" onmouseout="bindInputBlur('out',3);"/>
					<div id="selectFloor3" style="display: none;z-index: 1000;" onmouseover="bindInputBlur('over',3);" onmouseout="bindInputBlur('out',3);">
					</div>
				</td>
				<td><font color="red">*</font>年&nbsp;&nbsp;&nbsp;月：</td>
			 	<td>
					<input class="Wdate" type="text" id="yearMonthCount" value="${yearMonth}" style="width:120px;" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
				</td>
				<td colspan="2" align="left">
					<input type="button" class="btn_blue" style="width:65px;margin-left: 10px;" onclick="queryCount();" value="查询"/>
				</td>
			</tr>
		</table>
	</div>
	<%--公寓收费记录总计信息 --%>
	<div align="right" style="margin-top: 10px;"><span style="font-weight: bolder;font-size: 13px;">单位：元</span></div>
	
	<div style="width:100%;height:120px;margin-top: 5px;overflow-x: auto;">
		<table style="width: 100%;" class="content_table" cellpadding="0" cellspacing="0">
			<thead>
		        <tr class="header">
		            <th scope="col" align="center" style="width: 30px;">&nbsp;</th>
		            <th scope="col" align="left" style="width: 80px;">年月</th>
		            <th scope="col" align="left" style="width: 80px;">本月实收</th>
		            <%--
	            	<th scope="col" align="left" style="width: 90px;">缴费跨期</th>
		            <th scope="col" align="left" style="width: 80px;">本月是否起收</th>
		             --%>
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
		 	 	<s:if test="voBisFlatRecord.recordYear !=null && voBisFlatRecord.recordMonth != null && voBisFlatRecord.recordYear !='' && voBisFlatRecord.recordMonth != ''">
			  		<tr class="mainTr" style="height: 35px;">
	 	    			<td align="center" class="pd-chill-tip">
	 	    				<span style="font-weight: bolder;">合计</span>
	 	    			</td>
		 	    		<td align="center" class="pd-chill-tip" title='${voBisFlatRecord.recordYear}年${voBisFlatRecord.recordMonth}月'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.recordYear}年${voBisFlatRecord.recordMonth}月</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip" title='${voBisFlatRecord.monthSs}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.monthSs}</div>
		 	    		</td>
		 	    		<%--
		 	    		<td align="center" class="pd-chill-tip"  title=''>
		 	    			<div class="ellipsisDiv_full" >-</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title=''>
		 	    			<div class="ellipsisDiv_full" >-</div>
		 	    		</td>
		 	    		 --%>
		 	    		<s:if test="costType==5">
			            	<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.waterTonnage}'>
			  	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.waterTonnage}</div>
			  	    		</td>
		           		</s:if>
		           		<s:if test="costType==6">
			            	<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.elecDegree}'>
			  	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.elecDegree}</div>
			  	    		</td>
		           		</s:if>
		 	    		<td align="center" class="pd-chill-tip" title='${voBisFlatRecord.monthYs}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.monthYs}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip" title='${voBisFlatRecord.monthYis}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.monthYis}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.monthMonth}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.monthMonth}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.monthYus}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.monthYus}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearDebt}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearDebt}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.agoYearDebt}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.agoYearDebt}</div>
		 	    		</td>
		 	    		<s:if test="costType==4">
		 	    			<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.owePropRate}'>
		  	    				<div class="ellipsisDiv_full" >${voBisFlatRecord.owePropRate}</div>
		  	    			</td>
		           		</s:if>
		 	    		<s:if test="costType==5">
		            		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.oweWaterRate}'>
		 	    				<div class="ellipsisDiv_full" >${voBisFlatRecord.oweWaterRate}</div>
		 	    			</td>
		           		</s:if>
		           		<s:if test="costType==6">
		            		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.oweElecRate}'>
		 	    				<div class="ellipsisDiv_full" >${voBisFlatRecord.oweElecRate}</div>
		 	    			</td>
		           		</s:if>
		           	
		           		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearYsTotal}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearYsTotal}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.ysTotal}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.ysTotal}</div>
		 	    		</td>
		           		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearSsTotal}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearSsTotal}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.ssTotal}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.ssTotal}</div>
		 	    		</td>
		          		<%-- 物管费 --%>
		 	    		<s:if test="costType==4">
		 	    			<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearPropRate}'>
		  	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearPropRate}</div>
		  	    		</td>
		 	    			<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearYusPropRate}'>
		  	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearYusPropRate}</div>
		  	    		</td>
			           	</s:if>
			           	<%--水费 --%>
			           	<s:if test="costType==5">
		           			<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearWaterRate}'>
		  	    				<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearWaterRate}</div>
		  	    			</td>
		           			<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearYusWaterRate}'>
		  	    				<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearYusWaterRate}</div>
		  	    			</td>
		           		</s:if>
			           	<%--电费 --%>
			           	<s:if test="costType==6">
			           		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearElecRate}'>
			  	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearElecRate}</div>
			  	    		</td>
			           		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearYusElecRate}'>
			  	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearYusElecRate}</div>
			  	    		</td>
			           	</s:if>
			           	<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.agoYearOwnfeeTotal}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.agoYearOwnfeeTotal}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.yusTotal}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.yusTotal}</div>
		 	    		</td>
			           	<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.currYearQsTotal}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.currYearQsTotal}</div>
		 	    		</td>
		 	    		<td align="center" class="pd-chill-tip"  title='${voBisFlatRecord.qsTotal}'>
		 	    			<div class="ellipsisDiv_full" >${voBisFlatRecord.qsTotal}</div>
		 	    		</td>
		    		</tr>
	    		</s:if>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
$(function(){
    initFloor('3');
});

function queryCount(){
	var bisProjectId = $("#bisProjectId3").val();
	var bisProjectName = $("#bisProjectName3").val();
	var bisFloorId = $("#bisFloorId3").val();
	var costType = $("#costType3").val();
	var yearMonth = $("#yearMonthCount").val();
    var bisFloorName = $("#bisFloorName3").val();
    var flatNo = $("#flatNoCount").val();
	if(yearMonth == null || "" == $.trim(yearMonth)){
		alert("请选择年月");
		$("#yearMonthCount").focus();
		return false;
	}
	var data = {
		bisProjectId:bisProjectId,
		bisProjectName:bisProjectName,
		bisFloorId:bisFloorId, 
		bisFloorName:bisFloorName,
		costType:costType,
		yearMonth:yearMonth,
		flatNo:flatNo
	};
	var url = "${ctx}/bis/bis-flat-record!loadRecordCount.action";
	$.post(url,data,function(result){
		$("#flatRecordCountDiv").html(result);
	});
}

</script>
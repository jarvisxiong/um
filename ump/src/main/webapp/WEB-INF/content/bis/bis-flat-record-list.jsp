<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div id="TableDiv" style="width:100%;overflow-x: hidden;">
	<table width="100%" id="result_table" class="content_table" >
	  <thead>
	        <tr class="header">
	            <th scope="col" align="center" style="width: 30px;">序号</th>
	            <th scope="col" align="left" >客户名称</th>
	            <th scope="col" align="left" >楼栋名称</th>
	            <th scope="col" align="left" >公寓编号</th>
	            <th scope="col" align="left" >建筑面积㎡</th>
	            <th scope="col" align="left" >累计应收</th>
            	<th scope="col" align="left" >累计实收</th>
            	<th scope="col" align="left" >累计预收</th>
            	<th scope="col" align="left" >累计欠收</th>
	        </tr>
	  </thead>
	  <tbody>
		  <s:set var="flatCount"><s:property value="pageFlat.result.size()"/></s:set>
		  <s:iterator value="pageFlat.result" status="sta" var="fl">
			    <tr class="mainTr" onclick="getRecordList('${bisFlatId}');">
			    	<td align="center" class="pd-chill-tip">
  	    				<s:property value="#sta.index+1"/>
  	    			</td>
	  	    		<td align="left" class="pd-chill-tip" title='${customerName}'>
	  	    			<div class="ellipsisDiv_full" >${customerName}</div>
	  	    		</td>
	  	    		<td align="left" class="pd-chill-tip" title='<p:code2name mapCodeName="mapBuilding" value="bisFloor.bisFloorId"/>'>
	  	    			<div class="ellipsisDiv_full" ><p:code2name mapCodeName="mapBuilding" value="bisFloor.bisFloorId"/></div>
	  	    		</td>
	  	    		<td align="left" class="pd-chill-tip" title='${flatNo}'>
	  	    			<div class="ellipsisDiv_full" >${flatNo}</div>
	  	    		</td>
	  	    		<td align="left" class="pd-chill-tip" title='${square}'>
	  	    			<div class="ellipsisDiv_full" >${square}</div>
	  	    		</td>
	  	    		<%--物管费 --%>
	  	    		<s:if test="costType == 4">
		  	    		<td align="left" class="pd-chill-tip" title='${annualYsMan}'>
		  	    			<div class="ellipsisDiv_full" >${annualYsMan}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualSsMan}'>
		  	    			<div class="ellipsisDiv_full" >${annualSsMan}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualYusMan}'>
		  	    			<div class="ellipsisDiv_full" >${annualYusMan}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualQsMan}'>
		  	    			<div class="ellipsisDiv_full" >${annualQsMan}</div>
		  	    		</td>
	  	    		</s:if>
	  	    		<%--水费 --%>
	  	    		<s:if test="costType == 5">
	  	    			<td align="left" class="pd-chill-tip" title='${annualYsWater}'>
	  	    				<div class="ellipsisDiv_full" >${annualYsWater}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualSsWater}'>
		  	    			<div class="ellipsisDiv_full" >${annualSsWater}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualYusWater}'>
		  	    			<div class="ellipsisDiv_full" >${annualYusWater}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualQsWater}'>
		  	    			<div class="ellipsisDiv_full" >${annualQsWater}</div>
		  	    		</td>
	  	    		</s:if>
	  	    		<%--电费 --%>
	  	    		<s:if test="costType == 6">
		  	    		<td align="left" class="pd-chill-tip" title='${annualYsElec}'>
		  	    			<div class="ellipsisDiv_full" >${annualYsElec}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualSsElec}'>
		  	    			<div class="ellipsisDiv_full" >${annualSsElec}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualYusElec}'>
		  	    			<div class="ellipsisDiv_full" >${annualYusElec}</div>
		  	    		</td>
		  	    		<td align="left" class="pd-chill-tip" title='${annualQsElec}'>
		  	    			<div class="ellipsisDiv_full" >${annualQsElec}</div>
		  	    		</td>
	  	    		</s:if>
		    	</tr>
		  </s:iterator>
		  <tr class="mainTr" style="font-weight: bolder;">
		  		<td colspan="4">&nbsp;</td>
		  		<td align="right">合计：</td>
		  		<td align="left" title='${voFlatCount.annualYsCount}'>${voFlatCount.annualYsCount}</td>
		  		<td align="left" title='${voFlatCount.annualSsCount}'>${voFlatCount.annualSsCount}</td>
		  		<td align="left" title='${voFlatCount.annualYusCount}'>${voFlatCount.annualYusCount}</td>
		  		<td align="left" title='${voFlatCount.annualQsCount}'>${voFlatCount.annualQsCount}</td>
		  </tr>
		</tbody>
	</table>
	
	<form action="${ctx}/bis/bis-flat-record!list.action" method="post" id="recordFormPage">
		<input type="hidden" id="bisFloorId_s" name="bisFloorId" value="${bisFloorId}"/>
		<input type="hidden" name="bisProjectId" value="${bisProjectId}"/>
		<input type="hidden" id="flatNo_s" name="flatNo" value="${flatNo}"/>
		<input type="hidden" id="costType" name="costType" value="${costType}"/>
		<div class="pageRight" style="float: right;margin-top: 5px;">
			<p:page pageInfo="pageFlat"/>
		</div>
	</form>
</div>

<script type="text/javascript">

function getRecordList(bisFlatId){
	var costType = $("#costType").val();
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div style='padding:5px;padding-right:15px;' id='flatRecordDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 850,
		height : 500,
		title : "查看公寓收费记录",
		closeBtn:true,
		afterShow : function() {
			var data = {bisFlatId:bisFlatId, costType:costType};
			var url = "${ctx}/bis/bis-flat-record!loadRecordByFlatId.action";
		   	//TB_showMaskLayer("正在搜索...");
			$.post(url,data,function(result){
				//TB_removeMaskLayer();
				$("#flatRecordDiv").html(result);
			});
		},
		handler : function(btn){
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}

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
	$("#bisFloorId_s").val($("#bisFloorId").val());
	$("#flatNo_s").val($("#flatNo").val());
	TB_showMaskLayer("正在搜索...");
	$("#recordFormPage").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#welcom").html(result);
	});
}
</script>
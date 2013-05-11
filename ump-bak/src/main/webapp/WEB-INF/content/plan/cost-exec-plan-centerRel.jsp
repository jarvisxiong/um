<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div>
 <table class="content_table" cellpadding="0" cellspacing="0" width="100%">
		<tr class="header">
		    <th style="width:5%;background: none;">
		    </th>
			<th style="width:20%;" align="left">计划</th>
			<th style="width:30%;" align="left">事项</th>
			<th style="width:20%;" align="left">计划完成时间</th>
			<th style="width:10%;" align="left">进度</th>
			<th style="width:10%;" align="left">状态</th>
		</tr>
 </table>
</div>
<div  style="overflow-y: hidden;width:100%;">
 <div id="yearPlanDiv" style="overflow: auto;">
	<table class="content_table" cellpadding="0" cellspacing="0" width="100%">
		<col style="width:5%;"></col>
		<col style="width:20%;"></col>
		<col style="width:30%;"></col>
		<col style="width:20%;"></col>
		<col style="width:10%;"></col>
		<col style="width:10%;"></col>
		<s:iterator value="costCtrlPlanRelList" var="details">
		<tr onclick="trOnclick('${costCd}');" style="border-bottom:1px solid #AAABB0;">
			<td colspan="6" class="big_drop_down project_row_item" id="detail_tr_${costCd}">
			  ${costName}(共${costSize}条记录)
			</td>
		</tr>
		<s:set var="month">""</s:set>
        
		<s:iterator value="#details.costDetailList" var="detail">
		  <s:if test="#month != monthDesc">
			   <tr class="tr_${costCd} group-title" style="border-bottom:0px solid #AAABB0;height:24px;">
			    <td colspan="6" style="padding-left:10px;">${monthDesc}
			     <s:if test="monthDesc=='本月'">(${currentMonth})</s:if>
			     <s:elseif test="monthDesc=='下月'">(${nextMonth})</s:elseif>
			    </td>
			   </tr>
			   <s:set var="month">${monthDesc}</s:set>	
		   </s:if>	
		   <tr class="tr_${costCd}" style="border-bottom:1px solid #AAABB0;width: 100%;height:24px;"
		   >
			    <td style="padding-left:10px;">
			    <security:authorize ifAnyGranted="A_COST_YTRAN">
				    <s:if test="costCtrlBidPurcId==null||costCtrlBidPurcId==''">
				     <input type="checkbox" name="allFilesCheckBox" id="${detailId}_checkBox" value="${detailId}"  
				      detailId = "${detailId}" 
				      planType="${planType}" 
				      content="${content}"  
				      costName = "${costName}" 
				      projectCd ="${projectCd}" 
				     onclick="transmitJsonData('${detailId}','${planType}','${content}','${costName}','${projectCd}');"/>
				    </s:if>
			    </security:authorize>
			    </td>
				<td>
				<s:if test="planType==2">半年计划</s:if>
				<s:else>执行计划</s:else>
				</td>
				<td>${content}</td>
				<td><s:date name="planCompleteTime" format="yy-MM-dd" /></td>
				<td><s:if test="delayDesc==0"><span style="color:#FF0000;">过期</span></s:if> <s:elseif test="delayDesc==1">近期</s:elseif></td>
				<td><s:if test="costCtrlBidPurcId==null||costCtrlBidPurcId==''">未推送
				<security:authorize ifAnyGranted="A_COST_YTRAN">
				 <button type="button" id="transmitCostCtrl" class="btn_blue_35_20" onclick="doTransmitCostCtrl('${detailId}','${planType}','${content}','${costName}','${projectCd}');">推送</button>
				</security:authorize>
				</s:if>
				    <s:else>已推送</s:else>
				  </td>
			</tr>
		</s:iterator>
		</s:iterator>
	</table>
  </div>
</div>
 <security:authorize ifAnyGranted="A_COST_YTRAN">
<div style="width: 100%;background-color:#EEEEEE;">
  <table>
   <tr>
    <td>
      <div style="padding-top:5px;background-color: #6FB0D0;text-align:center;width:30px;">
		<input type="checkbox" title="全选/不选" style="cursor: pointer;" onclick="checkedAll($(this).attr('checked'));">
	  </div>
    </td>
    <td>
    <div style="height:24px;">
       <div class="btn_cutline_3_26" style="float:left;"></div>
	   <div style="float:left;padding-top:8px;"><a title="批量推送" onclick="batchTransmit()">批量推送</a></div>
	   <div class="btn_cutline_3_26" style="float:left;"></div>
     </div>
    </td>
   </tr>
  </table>
</div>
</security:authorize>
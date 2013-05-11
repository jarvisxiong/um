<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 
	Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@ page language="java" import="java.text.SimpleDateFormat"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.hhz.ump.entity.plan.ExecPlanProcessingVO"%>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
%>
<table class="meeting_list">
	<thead>
	<%
	  List<ExecPlanProcessingVO> epvoLists = (List)request.getAttribute("epvoList");
	  if (epvoLists.size()!=0){%>
		<tr class="header">
			<th width="8"  style="background-image: none;"></th>
			<th width="40">时间</th>
			<th width="120">地产</th>
			<th width="30">本周未回复</th>
			<th width="30">近期</th>
			<th width="30">未确认</th>
			<th width="30">过期</th>
			<th width="30">完成</th>
		</tr>
		<%}else{%>
		  <tr class="header">
			&nbsp;&nbsp;&nbsp;没有对应的采集数据!
		</tr>
		<%}
	%>
	</thead>
	
	<tbody>
		  <s:iterator value="epvoList" var="entity">
			<tr class="item" id="${entity.execPlanProcessingId}item" myid="${entity.execPlanProcessingId}">
				<td align="center" title="点击打开详情">
					<img class="down_arrow" src="${ctx}/images/plan/right_grey.gif" />
					<img class="up_arrow" src="${ctx}/images/plan/down_blue.gif" />
				</td>
				<td title="创建时间">
					<div class="splitWord  pd-chill-tip" style="width: 95%;"><%=DateOperator.formatDate((Date)JspUtil.findValue("createdDate"))%></div>
				</td>
				<td title="地产" >
					<span id="dssequenceNo">${entity.projectName}</span>
				</td> 
				
				<td title="未回复">
					<div class="splitWord  pd-chill-tip" style="width: 95%;">${entity.noReplyStatus}</div>
				</td> 
				
				<td title="进行中">
					<div class="splitWord  pd-chill-tip" style="width: 95%;">${entity.goingStatus}</div>
				</td>
				
				<td title="未确认">
					<div class="splitWord  pd-chill-tip" style="width: 95%;">${entity.noConfirmStatus}</div>
				</td> 
				
				<td title="过期">
					<div class="splitWord  pd-chill-tip" style="width: 95%;">${entity.suspendStatus}</div>
				</td>
				
				<td title="完成">
					<div class="splitWord  pd-chill-tip" style="width: 95%;">${entity.completeStatus}</div>
				</td>
			</tr>
			
			<tr class="detail">
					<td>
						<table class="tableStyle"  cellpadding="0" cellspacing="0" style="background-color: #E4E7EC;border: solid 1px #ffffff;" >
							<tr class="header">
								<th style="background-image: none;" width="120px" rowspan="2">主责方</th>
								<th width="130px" rowspan="2">本周未回复</th>
								<th width="130px" rowspan="2">近期</th>
								<th width="130px" rowspan="2">未确认</th>
								<th width="130px" rowspan="2">过期</th>
								<th width="130px" rowspan="2">完成</th>
								<th width="130px" rowspan="2" style="display: none"></th>
								<th width="130px" rowspan="2" style="display: none"></th>
								<th width="130px" rowspan="2" style="display: none"></th>
							</tr>

							<tbody>
								<s:iterator value="execPlanProcessing">
							<tr class="mainTr">
								<td>
									<div class="splitWord  pd-chill-tip" style="width: 95%;"><s:property value="resOrgName" escape="true"/></div>
								</td>
								<td>
									<div class="splitWord  pd-chill-tip" style="width: 95%;"><s:property value="noReplyStatus" escape="true"/></div>
								</td>
								<td>
									<div class="splitWord  pd-chill-tip" style="width: 95%;"><s:property value="goingStatus" escape="true"/></div>
								</td>
								<td>
									<div class="splitWord  pd-chill-tip" style="width: 95%;"><s:property value="noConfirmStatus" escape="true"/></div>
								</td>
								<td>
									<div class="splitWord  pd-chill-tip" style="width: 95%;"><s:property value="suspendStatus" escape="true"/></div>
								</td>
								<td>
									<div class="splitWord  pd-chill-tip" style="width: 95%;"><s:property value="completeStatus" escape="true"/></div>
								</td>
								<td style="display: none">
									<div  style="width: 100%;"></div>
								</td>
								<td style="display: none">
									<div  style="width: 100%;"></div>
								</td>
								<td style="display: none">
									<div  style="width: 100%;"></div>
								</td>
							</tr>
							</s:iterator>
							</tbody>
						</table>
				</td>
			</tr>
		</s:iterator>
	</tbody>
</table>
<script language="javascript">
//$("#myMeetingRoomRes").addClass("waiting");

$("tr.item").click(function() {
	try{
		setAttentionRead('dataMissing',$(this).attr("myid"));
	}catch(e){}
	var detail = $(this).next();
	if (detail.css("display") == "none") {
		if (openItem != null) {
			openItem.next().hide();
			openItem.removeClass("selected");
			openItem.find("div").addClass("splitWord");
			openItem.find("td").css("color", "");
			switchArrow(openItem, "down");
		}
		$(this).addClass("selected");
		$(this).find("td").css("color", "#40a3de");
		$(this).find("div").removeClass("splitWord");
		detail.show();
		switchArrow($(this), "up");
		openItem = $(this);
	} else {
		$(this).removeClass("selected");
		$(this).find("div").addClass("splitWord");
		$(this).find("td").css("color", "");
		switchArrow($(this), "down");
		detail.hide();
		openItem.next().hide();
		openItem = null;
	}
});

</script>

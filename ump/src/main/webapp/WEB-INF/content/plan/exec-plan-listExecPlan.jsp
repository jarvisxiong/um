<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 
	Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.Date"%>
<%@page import="com.hhz.core.utils.DateOperator"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<table class="meeting_list">
	<thead>
		<tr class="header">
			<th width="22">序号</th>
			<th width="70">地产</th>
			<th width="90">主责方</th>
			<th width="80">节点名称</th>
			<th width="190">业态</th>
			<th width="50">计划完成时间</th>
			<th width="40">进度</th>
			<th width="150">留言</th>
			<% 
						if(request.getAttribute("flag")!=null && request.getAttribute("flag").equals("1")){%>
							
						<%}else{%>
							<th width="30" style="background-image: none;"></th>
						<%}
					%>
		</tr>
	</thead>
	
	<tbody>
		
		<s:iterator value="page.result">
			<tr class="item" id="${parentId15}item" myid="${execPlanNodeId}">
				<td title="序号" >
					<span id="${execPlanNodeId}sequenceNo"><s:property value="sequenceNo"/></span>
				</td> 
				
				<td title="地产"  onclick="showPlanDetail($(this),'${execPlanNodeId}','${treeParentNodeId}');" label="${parentId15}";>
					<div class="splitWord  pd-chill-tip" style="width: 95%;" title="<p:code2name mapCodeName="mapExecPlanTips" value="nodeCd" />"><span id="${execPlanNodeId}corOrgCd"><s:property value="corOrgCd"/></span></div>
				</td>
				
				<td title="主责方"  onclick="showPlanDetail($(this),'${execPlanNodeId}','${treeParentNodeId}');" label="${parentId15}";>
					<div class="splitWord  pd-chill-tip" style="width: 95%;" title="<p:code2name mapCodeName="mapExecPlanTips" value="nodeCd" />"><span id="${execPlanNodeId}resOrgName"><s:property value="resOrgName"/></span></div>
				</td> 
				
				<td title="节点" onclick="showPlanDetail($(this),'${execPlanNodeId}','${treeParentNodeId}');" label="${parentId15}";>
					<div class="splitWord  pd-chill-tip" style="width: 95%;" title="<p:code2name mapCodeName="mapExecPlanTips" value="nodeCd" />"><s:property value="nodeName"/></div>
				</td> 
				
				<td title="业态"  onclick="showPlanDetail($(this),'${execPlanNodeId}','${treeParentNodeId}');" label="${parentId15}";>
					<div class="splitWord  pd-chill-tip" style="width: 95%;" title="<p:code2name mapCodeName="mapExecPlanTips" value="nodeCd" />"><span id="${execPlanNodeId}corOrgName"><s:property value="corOrgName"/></span></div>
				</td> 
				
				<td align="center" title="计划完成时间">
					<div class="splitWord  pd-chill-tip" style="width: 95%;" title="<p:code2name mapCodeName="mapExecPlanTips" value="nodeCd" />"><s:date name="createdDate" format="MM-dd" /></div>
				</td>
				
				<td align="center" title="进度">
				<s:if test="activeFlg == 0">
					<span class="color_red" id="${execPlanNodeId}corOrgName"><s:property value="planTypeCd"/></span>
				</s:if>
				<s:elseif test="activeFlg==1">
					<span class="color_going" id="${execPlanNodeId}corOrgName"><s:property value="planTypeCd"/></span>
				</s:elseif>
				</td>
				<% 
						if(request.getAttribute("flag")!=null && request.getAttribute("flag").equals("1")){%>
							<td style="padding-bottom: 2px;" >
								<div>
									<s:property value="remark"/>
								</div>
							</td>
						<%}else{%>
							<td style="padding-bottom: 2px;" >
								<div>
									<textarea id="${parentId15}commentArea" rows="2" style="width: 99%;"></textarea>
								</div>
							</td>
							<td valign="bottom" style="padding-bottom: 8px;">
								<div class="func_icon" style="float: left; margin-left: px;" onclick="saveMessage1('${parentId15}');">
									发表
								</div>
							</td>
						<%}
					%>

			</tr>
		</s:iterator>
	</tbody>
</table>

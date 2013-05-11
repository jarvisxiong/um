<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" import="com.hhz.uums.utils.DictContants" %>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.uums.utils.CodeNameUtil"%>
<%@page import="com.hhz.uums.utils.JspUtil"%>

<%-- 本页面不使用 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>审核</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
</head>
<body >
<s:form id="mainFormSearch" action="approve!list.action" method="post">
	<input type="hidden" name="qsCondition" id="qsCondition" value="${qsCondition}"/>
	<input type="hidden" name="selectedOrderBy" id="selectedOrderBy" value="${selectedOrderBy}"/>
	<input type="hidden" name="selectedOrder" id="selectedOrder" value="${selectedOrder}"/>
	<div id="rightHeadQuick" style="background-color: #E4E7EC;border-bottom: 1px solid #AAABB0;">
		<table style="width:100%;background-color:white;" id="quickSearchPanel">
			<tr>
				<td style="width:100px;">
					<div class="quickSenior" id="btnQuickSenior" onclick="searchSenior()">&nbsp;</div>
				</td>
				<td>
					<div class="quickItem" <s:if test="3==qsCondition"> style="color:#ff0012;"</s:if> id="btnMyDeal"  onclick="searchMyDeal()">我的历史</div>
					<div class="quickItem" <s:if test="2==qsCondition"> style="color:#ff0012;"</s:if> id="btnMyDuty"  onclick="searchMyDuty()">待审批</div>
					<div class="quickItem" <s:if test="1==qsCondition"> style="color:#ff0012;"</s:if> id="btnMyReco"  onclick="searchMyReco()">我的申请</div>
					<div class="quickTitle" >快速搜索:</div>
				</td>
			</tr>
		</table>
	</div>
	<!-- 高级搜索 -->
	<div id="rightHeadTool" style="width:100%;background-color: #E4E7EC;display:none;border-bottom: 1px solid #AAABB0;">
		<table class="search"> 
		<col width="60">
		<col>
		<col width="70">
		<col>
		<col width="70">
		<col>
			<tr>
				<td>申请类别</td>  
				<td colspan="3"><s:textfield cssClass="input" name="filter_LIKES_type" id="filter_LIKES_type" /></td>
				<td>姓名</td>  
				<td colspan="3"><s:textfield cssClass="input" name="filter_LIKES_newName" id="filter_LIKES_newName" /></td>
				<td>项目</td>  
				<td>
				<input type="text" name="filter_LIKES_landproject" id="filter_LIKES_landproject" style="cursor: pointer;" value="${filter_LIKES_landproject}"/>
				<input type="hidden" id="centerCd"/>
				</td>
				<td>职级</td>  
				<td colspan="3"><s:textfield cssClass="input" name="filter_LIKES_level" id="filter_LIKES_level" /></td>
				<td>审批人</td>
				<td>
					<input id="auditorUserNames" type="text" class="singleSelect" name="filter_LIKES_approveUserName" value="${filter_LIKES_approveUserName}"/>
					<input id="auditorUserCds" type="hidden" name="filter_LIKES_approveUserUiid"  value="${filter_LIKES_approveUserCd}" />
				</td>
				<td>发起人</td>
				<td>
					<input id="creatorUserNames" type="text" class="singleSelect" name="filter_LIKES_userName" value="${filter_LIKES_userName}"/>
					<input id="creatorUserCds" type="hidden" name="filter_LIKES_userUiidd"  value="${filter_LIKES_userCd}" />
				</td>
				<td>审批状态</td>
				<td><s:select cssClass="input" list="@com.powerlong.plas.utils.DictMapUtil@getMapApproveStatus()" listKey="key" listValue="value" id="filter_LIKES_applyStatusCd" name="filter_LIKES_applyStatusCd" ></s:select>
				</td>
				<td>发起时间从</td>
				<td><s:textfield name="filter_GED_startDate"  id="filter_GED_startDate" onfocus="WdatePicker()" cssClass="Wdate"/>
				</td>
				<td>到</td>   
				<td>
					<s:textfield name="filter_LTD_startDate" id="filter_LTD_startDate" onfocus="WdatePicker()" cssClass="Wdate"/>
				</td>
			</tr>	
		</table>
	</div>
	
	<s:set var="curUserCd"><%=SpringSecurityUtils.getCurUiid()%></s:set>
		<!-- 搜素结果 -->
		<div id="tableDiv" style="overflow:auto;border-bottom:1px solid #dddbdc; border-right:1px solid #dddbdc;">
			<table class="content_table" id="editTable" style="width: expression(this.parentNode.offsetHeight >this.parentNode.scrollHeight ? '100%' :parseInt(this.parentNode.offsetWidth - 18) + 'px');">
				<col>
				<col width="58">
				<col>
				<col>
				<col width="58">
				<col width="60">
				<col width="60">
					<tr>
						<th nowrap="nowrap" onclick="sortBy(this,'applyTypeCd','${selectedOrder}')">
							<div style="float:left;cursor: pointer;">申请类型</div>
							<s:if test="selectedOrderBy=='applyTypeCd'">
								<s:if test="selectedOrder=='desc'">
									<div class="sort_desc"></div>
								</s:if>
								<s:if test="selectedOrder=='asc'">
									<div class="sort_asc"></div>
								</s:if>
							</s:if>
						</th>
						<th nowrap="nowrap" onclick="sortBy(this,'newName','${selectedOrder}')">
							<div style="float:left;cursor: pointer;">姓名</div>
							<s:if test="selectedOrderBy=='newName'">
								<s:if test="selectedOrder=='desc'">
									<div class="sort_desc"></div>
								</s:if>
								<s:if test="selectedOrder=='asc'">
									<div class="sort_asc"></div>
								</s:if>
							</s:if>
						</th>
						<th nowrap="nowrap" style="background: none;cursor: pointer;" onclick="sortBy(this,'landProject','${selectedOrder}')">
							<div style="float:left;">项目/中心</div>
							<s:if test="selectedOrderBy=='landProject'">
								<s:if test="selectedOrder=='desc'">
									<div class="sort_desc"></div>
								</s:if>
								<s:if test="selectedOrder=='asc'">
									<div class="sort_asc"></div>
								</s:if>
							</s:if>
						</th>
						<th nowrap="nowrap" style="cursor: pointer;" onclick="sortBy(this,'userCd','${selectedOrder}')">
							<div style="float:left;">发起人</div>
							<s:if test="selectedOrderBy=='userCd'">
								<s:if test="selectedOrder=='desc'">
									<div class="sort_desc"></div>
								</s:if>
								<s:if test="selectedOrder=='asc'">
									<div class="sort_asc"></div>
								</s:if>
							</s:if>
						</th>
						
						<th nowrap="nowrap">状态</th>
						<th nowrap="nowrap">
							<s:if test="filter_LIKES_applyStatusCd == 2">
								完成时间
							</s:if>
							<s:else>
								到达时间
							</s:else>
						</th>
					</tr>
					<s:iterator value="page.result">
					</s:iterator>
			</table>
			<div class="table_pager" style="margin-top:5px;">
				<p:page />
			</div>
		</div>
</s:form>
<script type="text/javascript">
</script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<!-- 搜素结果 -->
<div class="div_data_content">
	<div class="content_header">
	<span class="td_sort_tip">点击排序：</span>
	<div class="sort_div" onclick="sortBy(this,'land_Project','${selectedOrder}')">
	<span>项目/中心</span>
	<s:if test="selectedOrderBy=='land_Project'">
		<s:if test="selectedOrder=='desc'">
			<div class="sort_desc"></div>
		</s:if>
		<s:if test="selectedOrder=='asc'">
			<div class="sort_asc"></div>
		</s:if>
	</s:if>
	</div>
	<div class="sort_div"  onclick="sortBy(this,'user_Cd','${selectedOrder}')"><span>发起人</span>
		<s:if test="selectedOrderBy=='user_Cd'">
			<s:if test="selectedOrder=='desc'">
				<div class="sort_desc"></div>
			</s:if>
			<s:if test="selectedOrder=='asc'">
				<div class="sort_asc"></div>
			</s:if>
		</s:if>
	</div>
	
	<div class="sort_div" onclick="sortBy(this,'auth_Type_Cd','${selectedOrder}')"><span>类型</span>
		<s:if test="selectedOrderBy=='auth_Type_Cd'">
			<s:if test="selectedOrder=='desc'">
				<div class="sort_desc"></div>
			</s:if>
			<s:if test="selectedOrder=='asc'">
				<div class="sort_asc"></div>
			</s:if>
		</s:if>
	</div>
	<div class="sort_div" onclick="sortBy(this,'title_Name','${selectedOrder}')"><span>标题</span>
		<s:if test="selectedOrderBy=='title_Name'">
			<s:if test="selectedOrder=='desc'">
				<div class="sort_desc"></div>
			</s:if>
			<s:if test="selectedOrder=='asc'">
				<div class="sort_asc"></div>
			</s:if>
		</s:if>
	</div>
	<div class="sort_div" onclick="sortBy(this,'approve_User_Cd','${selectedOrder}')"><span>审批人</span>
		<s:if test="selectedOrderBy=='approve_User_Cd'">
			<s:if test="selectedOrder=='desc'">
				<div class="sort_desc"></div>
			</s:if>
			<s:if test="selectedOrder=='asc'">
				<div class="sort_asc"></div>
			</s:if>
		</s:if>
	</div>
	
	<s:if test="filter_LIKES_statusCd == 2">
		<div class="sort_div" onclick="sortBy(this,'complete_date','${selectedOrder}')"><span>完成时间</span>
		<s:if test="selectedOrderBy=='complete_date'">
		<s:if test="selectedOrder=='desc'">
			<div class="sort_desc"></div>
		</s:if>
		<s:if test="selectedOrder=='asc'">
			<div class="sort_asc"></div>
		</s:if>
		</s:if>
		</div>
	</s:if>
	<s:else>
		<div class="sort_div" onclick="sortBy(this,'last_approve_date','${selectedOrder}')"><span>到达时间</span>
		<s:if test="selectedOrderBy=='last_approve_date'">
		<s:if test="selectedOrder=='desc'">
			<div class="sort_desc"></div>
		</s:if>
		<s:if test="selectedOrder=='asc'">
			<div class="sort_asc"></div>
		</s:if>
		</s:if>
		</div>
	</s:else>
	
	<div class="sort_div" onclick="sortBy(this,'TIME_LIMIT - (SYSDATE - LAST_APPROVE_DATE) * 24','${selectedOrder}')"><span>剩余时间</span>
		<s:if test="selectedOrderBy=='TIME_LIMIT - (SYSDATE - LAST_APPROVE_DATE) * 24'">
		<s:if test="selectedOrder=='desc'">
			<div class="sort_desc"></div>
		</s:if>
		<s:if test="selectedOrder=='asc'">
			<div class="sort_asc"></div>
		</s:if>
		</s:if>
	</div>
	<div class="table_header_reset" onclick="sortBy(this,'','')">重置</div>
	<div style="float:right;margin-right: 0;">
	<p:page createAttr="false"/>
	</div>
	</div>
	<div class="content_data">
	<s:iterator value="page.result">
		<jsp:include page="res-approve-info-data.jsp">
			<jsp:param value="0" name="listMode"/>
		</jsp:include>
	</s:iterator>
	</div>
	<div class="table_pager">
		<p:page />
	</div>
</div>
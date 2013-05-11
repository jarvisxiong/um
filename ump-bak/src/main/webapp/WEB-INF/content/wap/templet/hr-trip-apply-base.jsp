<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 出差申请计划单 --%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
		<s:set var="sendCenterName">${templateBean.centerOrgName}</s:set>
		<span class="wap_title">中心/公司:</span>
		<span class="wap_value">${sendCenterName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">申请人:</span>
		<span class="wap_value">${templateBean.userName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">部门:</span>
		<span class="wap_value">${templateBean.dept}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">职位:</span>
		<span class="wap_value">${templateBean.position}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">开始日期:</span>
		<span class="wap_value">${templateBean.tripTimeBegin}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">结束日期:</span>
		<span class="wap_value">${templateBean.tripaTimeEnd}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">合计出差时间:</span>
		<span class="wap_value">${templateBean.tripDay}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">出差地点:</span>
		<span class="wap_value">${templateBean.tripPlace}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">随行人员:</span>
		<span class="wap_value">${templateBean.entourageUserName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">交通方式:</span>
		<span class="wap_value"><%=CodeNameUtil.getDictNameByCd(DictContants.JBPM_TRAVEL_WAY,JspUtil.findString("templateBean.travelWay")) %></span>
		</div>
		<div class="div_row">
		<span class="wap_title">出差事由:</span>
		<span class="wap_value">${templateBean.tripReason}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">预计差旅借款:</span>
		<span class="wap_value">${templateBean.lendMoneyAmt}元</span>
		</div>
		<div class="div_row">
		<span class="wap_title">行程计划安排:</span>
		<span class="wap_value">${templateBean.tripDesc}</span>
		</div>
		<<s:if test="templateBean.tripDayPlan.size()>0">
		<div class="div_row">
		<span class="wap_title">日程计划安排:</span>
		<s:iterator value="templateBean.tripDayPlan" var="dayPlan" status="st">
		<div><span class="wap_value">第${st.index+1}天:上午:${dayPlan.forenoonPlan};下午:${dayPlan.afternoonPlan}。</span></div>
		</s:iterator>
		</div>
		</s:if>
		<s:set var="travelWay"><%=CodeNameUtil.getDictNameByCd(DictContants.JBPM_TRAVEL_WAY,JspUtil.findString("templateBean.travelWay")) %></s:set>
		<s:if test="#travelWay=='飞机'">
		<div class=div_table_row>
		<span>序号/出行人员 /起迄地 /身份证号 /建议航班时间 /费用承担部门 </span>	
		<s:iterator value="templateBean.airTicketsBookingSheet" var="item" status="s">
		<div class="orgDiv">
			<span class="wap_value"><s:property value="#s.index+1"/>./${item.userName}/${item.beginEndPlace}/${item.idCardNo}/${item.tripDate}/${item.costDept}</span>
		</div>
		</s:iterator>
		</div>
		</s:if>

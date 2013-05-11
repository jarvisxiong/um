<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<!--战略采购订单审批表-->
<s:set var="canEdit">false</s:set>
<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">职级:</span>
			<span class="wap_value">${templateBean.positionGrade}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">发起公司:</span>
			<span class="wap_value">${templateBean.companyName}</span>
			<span><s:checkbox name="templateBean.isUrgency" cssClass="group"></s:checkbox>急</span>
			<span class="wap_title">总金额:</span>
			<span class="wap_value">${templateBean.totalAmount}</span>
		</div>
		<div class="div_label">
			<span class="wap_title">出差补贴</span>
			<s:iterator value="templateBean.onBusiness" var="onBis">
			<div class="div_row">
				<span class="wap_title">开始日期:</span>
				<span class="wap_value">${onBis.startDate}</span>
				<span class="wap_title">结束日期:</span>
				<span class="wap_value">${onBis.endDate}</span>
				<span class="wap_title">共(天):</span>
				<span class="wap_value">${onBis.sumDays}</span>
				<span class="wap_title">出差地点:</span>
				<span class="wap_value">${onBis.place}</span>
				<span class="wap_title">日补助(元):</span>
				<span class="wap_value">${onBis.daySubsidy}</span>
				<span class="wap_title">共计(元):</span>
				<span class="wap_value">${onBis.sumSubsidy}</span>
			</div>
			</s:iterator>
		</div>
		<div class="div_label">
			<span class="wap_title">住宿费</span>
			<s:iterator value="templateBean.stopAt" var="stopAt" status="st">
			<div class="div_row">
				<span class="wap_title">开始日期:</span>
				<span class="wap_value">${stopAt.startDate}</span>
				<span class="wap_title">结束日期:</span>
				<span class="wap_value">${stopAt.endDate}</span>
				<span class="wap_title">共(天):</span>
				<span class="wap_value">${stopAt.sumDays}</span>
				<span class="wap_title">住宿地点:</span>
				<span class="wap_value">${stopAt.place}</span>
				<span class="wap_title">平均每日(元):</span>
				<span class="wap_value">${stopAt.dayMoney}</span>
				<span class="wap_title">共计(元):</span>
				<span class="wap_value">${stopAt.sumMoney}</span>
				<r:wapFile fileId="stopAtAttach" title="附件" fileValue="${stopAt.stopAtAttach}" index="${st.index}"/>
			</div>
			</s:iterator>
		</div>
		<div class="div_label">
			<span class="wap_title">差旅费</span>
			<s:iterator value="templateBean.travel" var="travel" status="st">
			<div class="div_row">
				<span class="wap_title">从:</span>
				<span class="wap_value">${travel.travelFrom}</span>
				<span class="wap_title">到:</span>
				<span class="wap_value">${travel.travelTo}</span>
				<span class="wap_title">交通方式:</span>
				<span class="wap_value"><%=CodeNameUtil.getDictNameByCd(DictContants.JBPM_TRAVEL_WAY,JspUtil.findString("trafficWay")) %></span>
				<span class="wap_title">费用:</span>
				<span class="wap_value">${travel.costAmount}</span>
				<r:wapFile fileId="travelAttach" title="附件" fileValue="${travel.travelAttach}" index="${st.index}"/>
			</div>
			</s:iterator>
		</div>
		<div class="div_label">
			<span class="wap_title">其它费用</span>
			<s:iterator value="templateBean.other" var="other" status="st">
			<div class="div_row">
				<span class="wap_title">费用说明:</span>
				<span class="wap_value">${other.costDirection}</span>
				<span class="wap_title">费用金额:</span>
				<span class="wap_value">${other.costAmount}</span>
				<r:wapFile fileId="otherAttach" title="附件" fileValue="${other.otherAttach}" index="${st.index}"/>
			</div>
			</s:iterator>
		</div>
</div>

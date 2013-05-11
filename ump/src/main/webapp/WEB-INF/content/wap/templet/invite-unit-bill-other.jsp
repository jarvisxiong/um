<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--邀标单位审批表-->
<div id="billContent">
			<c:set var="isSy">false</c:set>
			<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
			<c:set var="isSy">true</c:set>
			</c:if>
			<%@ include file="template-var.jsp"%>
			<c:if test="${isSy}">
			<div class="div_row">
				<div><s:checkbox name="templateBean.businessCompany" cssClass="group"></s:checkbox><span>商业公司发起</span></div>
				<div><s:checkbox name="templateBean.businessGroup" cssClass="group"></s:checkbox><span>商业集团发起</span></div>
			</div>				
			</c:if>
			<div class="div_row">
				<span class="wap_title">项目:</span>
				<span class="wap_value">${templateBean.projectName}</span>
			</div>				
			<div class="div_row">
				<span class="wap_title">标书编号:</span>
				<span class="wap_value">${templateBean.biaoShuNo}</span>
			</div>				
			<div class="div_row">
				<span class="wap_title">服务内容:</span>
				<span class="wap_value">${templateBean.biaoDuan}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">战略:</span>
				<div><s:checkbox name="templateBean.overAllYes" cssClass="group"></s:checkbox><span>战略</span></div>
				<div><s:checkbox name="templateBean.overAllNo" cssClass="group"></s:checkbox><span>非战略</span></div>
			</div>	
			<div class="div_row">
				<span class="wap_title">邀请类别:</span>
				<div><s:checkbox name="templateBean.showFlag" cssClass="group"></s:checkbox><span>明标</span></div>
				<div><s:checkbox name="templateBean.hideFlag" cssClass="group"></s:checkbox><span>暗标</span></div>
			</div>		
			<div class="div_row">
				<span class="wap_title">预计金额(元):</span>
				<span class="wap_value">${templateBean.planMoney}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">预算:</span>
				<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
				<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
			</div>
			<div class="div_row">
				<span class="wap_title">备注:</span>
				<span class="wap_value">${templateBean.remark}</span>
			</div>
			<div class=div_table_row>
			<span>序号/单位名称 /供方级别 </span>	
			<s:iterator value="templateBean.otherProperties" var="item" status="s">
			<div class="orgDiv">
				<s:property value="#s.index+1"/>./${item.unitName}/${item.unitLevel}
			</div>
			</s:iterator>
			</div>
</div>
	
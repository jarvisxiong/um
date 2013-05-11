<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!--战略邀标单位审批表-->
<div id="billContent">
			<c:set var="isSy">false</c:set>
			<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
			<c:set var="isSy">true</c:set>
			</c:if>
			<%@ include file="template-var.jsp"%>
		 
			<div class="div_row">
				<span class="wap_title">战略名称:</span>
				<span class="wap_value">${templateBean.strageName}</span>
			</div>				
			<div class="div_row">
				<span class="wap_title">项目名称:</span>
				<span class="wap_value"><span>${templateBean.projectName}</span><span>(${templateBean.periodNum })期</span></span>
			</div>				
			<div class="div_row">
				<span class="wap_title">工程:</span>
				<span class="wap_value">${templateBean.construction}</span>
			</div>
			
			<div class="div_row">
				<span class="wap_title">邀请类别:</span> 
				<div><s:checkbox name="templateBean.showFlag" cssClass="group"></s:checkbox><span>明标</span></div>
				<div><s:checkbox name="templateBean.hideFlag" cssClass="group"></s:checkbox><span>暗标</span></div>
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
	
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--合同标准库--%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<s:if test="templateBean.contlib == 'true'">
		<div class="div_row">
			<span class="wap_title">合同库:</span>
			<div class="div_row">
				<div><s:checkbox name="templateBean.contlib" cssClass="group"></s:checkbox><span>使用</span></div>
				<div><s:checkbox name="templateBean.noncontlib" cssClass="group"></s:checkbox><span>不使用</span></div>
			</div>
		</div>
		<div class="div_row">
			<span class="wap_title">合同文本库编号:</span>
			<span class="wap_value scContractLink">${templateBean.contractNo}</span>
			<input type="hidden" id="contractTempletInfoId" name="templateBean.contractTempletInfoId" value="${templateBean.contractTempletInfoId}" />
			<input type="hidden" id="contractTempletHisId" name="templateBean.contractTempletHisId" value="${templateBean.contractTempletHisId}" />
			
		</div>
</s:if>

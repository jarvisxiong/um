<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 招标文件/合同评审表（招标文件） --%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>

		<div class="div_row">
			<span class="wap_title">评审类别:</span>
			<div class="div_row">

				<div><s:checkbox name="templateBean.noncontlib" id="noncontlib"  cssClass="group"></s:checkbox><span class="useless">招标文件</span></div>
				<div><s:checkbox name="templateBean.contlib"  id="contlib"  cssClass="group"></s:checkbox><span class="use">合同</span></div>
			</div>
		</div>
		<s:if test="templateBean.contlib == 'true'">
		<div class="div_row">
			<span class="wap_title">合同库编号:</span>
			<span class="wap_value scContractLink" >${templateBean.contractNo}</span>
		</div>
    </s:if>

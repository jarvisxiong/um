<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--人员申请表-->

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
		<span class="wap_title">中心:</span>
		<span class="wap_value">${templateBean.centerName}</span>
		</div>	
		<div class="div_row">
		<span class="wap_title">职位:</span>
		<span class="wap_value">${templateBean.positionName}</span>
		</div>	
		<div class="div_row">
		<span class="wap_title">级别:</span>
		<span class="wap_value">${templateBean.positionLevel}</span>
		</div>	
		<div class="div_row">
		<span class="wap_title">薪金:</span>
		<span class="wap_value">${templateBean.salary}</span>
		</div>	
		<div class="div_row">
		<span class="wap_title">性别:</span>
		<span class="wap_value">${templateBean.sex}</span>
		</div>	
		<div class="div_row">
		<span class="wap_title">年龄:</span>
		<span class="wap_value">${templateBean.age}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">婚否:</span>
			<div><s:checkbox name="templateBean.selectMarriageYes" cssClass="group"></s:checkbox><span>是</span></div>
			<div><s:checkbox name="templateBean.selectMarriageNo" cssClass="group"></s:checkbox><span>否</span></div>
		</div>
		<div class="div_row">
		<span class="wap_title">到岗日期:</span>
		<span class="wap_value">${templateBean.checkDate}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">申请人数:</span>
		<span class="wap_value">${templateBean.applyNum}</span>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.isAdd" cssClass="group"></s:checkbox><span>增加</span></div>
			<div><s:checkbox name="templateBean.isRepair" cssClass="group"></s:checkbox><span>补缺</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">员工类别:</span>
			<div><s:checkbox name="templateBean.userKind1" cssClass="group"></s:checkbox><span>正式工</span></div>
			<div><s:checkbox name="templateBean.userKind2" cssClass="group"></s:checkbox><span>临时工</span></div>
		</div>
		<div class="div_row">
		<span class="wap_title">工作职责简述:</span>
		<span class="wap_value">${templateBean.workDuty}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">任职要求:</span>
		<span class="wap_value">${templateBean.jobRequire}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">人员申请理由:</span>
		<span class="wap_value">${templateBean.applyReason}</span>
		</div>
</div>

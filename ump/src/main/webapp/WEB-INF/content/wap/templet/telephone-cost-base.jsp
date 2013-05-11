<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		<%--电话费用报销单--%>
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
		<span class="wap_title">申请人姓名:</span>
		<span class="wap_value">${templateBean.appName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">中心:</span>
		<span class="wap_value">${templateBean.appCenterName}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">部门:</span>
		<span class="wap_value">${templateBean.appDept}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">级别:</span>
		<span class="wap_value">${templateBean.appGrade}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">报销电话费类别:</span>
		<span class="wap_value">${templateBean.appTeleType}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">报销电话费号码:</span>
		<span class="wap_value">${templateBean.appTelephone}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">总经理审核额度(元):</span>
		<s:if test="nodeCd==22 && isMyApprove==1">
		<s:hidden id="isEdit" value="true"/>
		<input class="inputBorder" edit='true' validate="required" type="text" name="templateBean.approveLimit" value="${templateBean.approveLimit}"/>
		</s:if>
		<s:else>
		<span class="wap_value">${templateBean.approveLimit}</span>
		</s:else>
		</div>
		<div class="div_row">
		<span class="wap_title">入职时间:</span>
		<span class="wap_value">${templateBean.entrantTime}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">报销起始时间:</span>
		<span class="wap_value">${templateBean.fromTime}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">申请理由:</span>
		<span class="wap_value">${templateBean.appReason}</span>
		</div>
		<div class="div_row">
		<span class="wap_title">入职时间:</span>
		<span class="wap_value">${templateBean.entrantTime}</span>
		</div>
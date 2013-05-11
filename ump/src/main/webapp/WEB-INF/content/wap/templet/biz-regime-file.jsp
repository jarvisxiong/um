<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--宝龙商业总部组织管理文件审批表--%>

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">制度文件名称:</span>
			<span class="wap_value">${templateBean.regimeName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">制度类型:</span>
			<div><s:checkbox name="templateBean.baseRegime" cssClass="group"></s:checkbox><span>基本制度</span></div>
			<div><s:checkbox name="templateBean.commonRegime" cssClass="group"></s:checkbox><span>一般制度</span></div>
			<div><s:checkbox name="templateBean.innerRegime" cssClass="group"></s:checkbox><span>内部制度</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">是否需要相关专业口审阅:</span>
			<div><s:checkbox name="templateBean.isAudit" cssClass="group"></s:checkbox><span>是</span></div>
			<div><s:checkbox name="templateBean.isNotAudit" cssClass="group"></s:checkbox><span>否</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">审批类型:</span>
			<div><s:checkbox name="templateBean.auditType" cssClass="group"></s:checkbox><span>新增</span></div>
			<div><s:checkbox name="templateBean.auditModify" cssClass="group"></s:checkbox><span>修订</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">内容简述(详细内容附后):</span>
			<span class="wap_value">${templateBean.contentDesc}</span>
		</div>
</div>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--标准合同版本审批表--%>

<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">合同版本名称:</span>
			<span class="wap_value">${templateBean.contractFileName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">文本类型:</span>
			<div><s:checkbox name="templateBean.fileType1" cssClass="group"></s:checkbox><span>租赁意向书、租赁合同</span></div>
			<div><s:checkbox name="templateBean.fileType2" cssClass="group"></s:checkbox><span>其他各类协议</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">审批类型:</span>
			<div><s:checkbox name="templateBean.approveType1" cssClass="group"></s:checkbox><span>新增</span></div>
			<div><s:checkbox name="templateBean.approveType2" cssClass="group"></s:checkbox><span>修订</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">合同适用范围:</span>
			<span class="wap_value">${templateBean.remark}</span>
		</div>
</div>

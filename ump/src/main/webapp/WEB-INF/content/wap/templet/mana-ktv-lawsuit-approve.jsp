<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- KTV门店诉讼处理方案审批表 --%>

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">名称:</span>
			<span class="wap_value">${templateBean.name}</span>
			</div>
		<div class="div_row">	
			<span class="wap_title">内容简述(详细内容附后):</span>
			<span class="wap_value">${templateBean.desc}</span>
		</div>
</div>

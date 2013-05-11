<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<%-- 航程变更申请单 --%>
		<div id="billContent">
				<%@ include file="template-var.jsp"%>
				<div class="div_row">
					<span class="wap_title">姓名:</span>
					<span class="wap_value">${templateBean.userName}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">原订航程:</span>
					<span class="wap_value">${templateBean.originalVoya}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">需要更改和预订的航程:</span>
					<span class="wap_value">${templateBean.changeVoya}</span>
				</div>
				<div class="div_row">
					<span class="wap_title">机票航程更改原因:</span>
					<span class="wap_value">${templateBean.cause}</span>
				</div>
		</div>

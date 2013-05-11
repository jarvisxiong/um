<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	物业服务合同审批表(eg:  物业服务合同审批表（主办店/次主力店）)	
--%>

<div class="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_label">
			<span class="wap_label">【合同双方】</span>
			<div class="div_row">
				<span class="wap_title">甲方:</span>
				<span class="wap_value">${templateBean.companyName1}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">签约人:</span>
				<span class="wap_value">${templateBean.signerName1}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">乙方:</span>
				<span class="wap_value">${templateBean.companyName2}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">签约人:</span>
				<span class="wap_value">${templateBean.signerName2}</span>
			</div>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<div class="div_row">
				<span class="wap_title">丙方:</span>
				<span class="wap_value">${templateBean.companyName3}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">签约人:</span>
				<span class="wap_value">${templateBean.signerName3}</span>
			</div>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
		</div>
		<div class="div_row">
			<span class="wap_title">物业管理费标准:</span>
			<span class="wap_value">${templateBean.contentDesc}</span>
		</div>
</div>

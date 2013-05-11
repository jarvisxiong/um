<%@ page contentType="text/html;charset=UTF-8"%>
<!--投资类合同审批表-->
<%@ include file="/common/taglibs.jsp"%>
		<div class="billContent">
			<%@ include file="template-var.jsp"%>
			<div class="div_row">
				<span class="wap_title">标题:</span>
				<span class="wap_value">${templateBean.titleName}</span>
			</div>
			<div class="div_label">
				<span class="wap_label">【合同双方】</span>
				<div class="div_row">
				<span class="wap_title">甲方:</span>
				<span class="wap_value">${templateBean.partyA}</span>
				</div>
				<div class="div_row">
				<span class="wap_title">签约人:</span>
				<span class="wap_value">${templateBean.partyASigning}</span>
				</div>
				<div class="div_row">
				<span class="wap_title">乙方:</span>
				<span class="wap_value">${templateBean.partyB}</span>
				</div>
				<div class="div_row">
				<span class="wap_title">签约人:</span>
				<span class="wap_value">${templateBean.partyBSigning}</span>
				</div>
				<!-- Start Add for part C by zhuxj on 2012.3.31 -->
				<div class="div_row">
				<span class="wap_title">丙方:</span>
				<span class="wap_value">${templateBean.partyC}</span>
				</div>
				<div class="div_row">
				<span class="wap_title">签约人:</span>
				<span class="wap_value">${templateBean.partyCSigning}</span>
				</div>
				<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			</div>
			<div class="div_label">
				<span class="wap_label">【合同主要内容】</span>
				<div class="div_row">
				<span class="wap_title">主要内容:</span>
				<span class="wap_value">${templateBean.mainContents}</span>
				</div>
				<div class="div_row">
				<span class="wap_title">合同价款(元):</span>
				<span class="wap_value">${templateBean.contractPrice}</span>
				</div>
				<div class="div_row">
				<span class="wap_title">付款方式:</span>
				<span class="wap_value">${templateBean.priceWay}</span>
				</div>
			</div>
		</div>

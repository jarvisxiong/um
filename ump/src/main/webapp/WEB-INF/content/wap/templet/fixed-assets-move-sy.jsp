<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!--办公资产调拨单-商业-->
<div class="billContent" >
	
	<%@ include file="template-var.jsp"%>
	<div class="div_label">
		<span class="wap_label">【调拨部门】</span>
		<div class="div_row">
			<span class="wap_title">调拨设备申请部门:</span>
			<span class="wap_value">${templateBean.applyDeptName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">申请人:</span>
			<span class="wap_value">${templateBean.applyUser}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">总价(元):</span>
			<span class="wap_value">${templateBean.totalPrice}</span>
		</div>
	</div>
	<div class="div_row">
		<span class="wap_title">调拨原因:</span>
		<span class="wap_value">${templateBean.reason}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">资产编码/设备名称/型号/调出部门/调入部门/原值(元)/调拨日期</span>
	</div>
	<s:iterator value="templateBean.fixedAssetsMove1Assets" var="item" status="s">
	<div class="div_row">
		<div class="orgDiv">
			${item.assetCode}/${item.assetName}/${item.model}/${item.outDeptName}/${item.inDeptName}/${item.unitPrice}/${item.moveDate}
		</div>
	</div>
	</s:iterator>

</div>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	租金减免审批表(eg:租金减免审批表) 
--%>
	<!-- 
	注：1、此单审批完成后，应向宝龙商业财务部、宝龙地产财务管理中心备案。
	 -->
<div id="billContent">
	<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">商户名称:</span>
			<span class="wap_value">${templateBean.storeName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">联系人:</span>
			<span class="wap_value">${templateBean.storeLinkman}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">联系地址:</span>
			<span class="wap_value">${templateBean.storeLinkAddr}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">联系电话:</span>
			<span class="wap_value">${templateBean.storeLinkPhone}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【商户分类】</span>
			<div class="div_row">
				<span class="wap_title"><s:checkbox name="templateBean.storeTypeCd1"  cssClass="group"></s:checkbox>主力店</span>
			</div>			
			<div class="div_row">
				<span class="wap_title"><s:checkbox name="templateBean.storeTypeCd2"  cssClass="group"></s:checkbox>次主力店</span>
			</div>
			<div class="div_row">
				<span class="wap_title"><s:checkbox name="templateBean.storeTypeCd3"  cssClass="group"></s:checkbox>中型店</span>
			</div>
			<div class="div_row">
				<span class="wap_title"><s:checkbox name="templateBean.storeTypeCd4"  cssClass="group"></s:checkbox>品牌店</span>
			</div>
	</div>
	<div class="div_row">
			<span class="wap_title">租金减免金额:</span>
			<span class="wap_value">${templateBean.remissionAmt}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">租金减免 起止期:</span>
			<span class="wap_value">${templateBean.startDate}至${templateBean.endDate}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">租金减免内容:</span>
			<span class="wap_value">${templateBean.rentDesc}</span>
	</div>
</div>

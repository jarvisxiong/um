<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%--	立项申请单-自由步骤	--%>
		
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<div class="div_row">
	<span class="wap_title">费用名称:</span>
	<span class="wap_value">${templateBean.feeName}</span>
	</div>
	<div class="div_label">
		<span class="wap_title">预算类别:</span>
		<div class="div_row">
			<div><s:checkbox name="templateBean.budgetType1" cssClass="group"></s:checkbox><span>初步预算</span></div>
			<div><s:checkbox name="templateBean.budgetType2" cssClass="group"></s:checkbox><span>精确费用(立即付款)</span></div>
		</div>
	</div>
	<div class="div_label">
		<span class="wap_title">预算处理<span style="font-size: 10px;">(此栏由财务填写)</span>:</span>
		<div class="div_row">
			<div><s:checkbox name="templateBean.budgetDeal1" cssClass="group"></s:checkbox><span>年度预算内调剂</span></div>
			<div><s:checkbox name="templateBean.budgetDeal2" cssClass="group"></s:checkbox><span>新增预算</span></div>
		</div>
	</div>
	<div class="div_row">
	<span class="wap_title">预算金额(元):</span>
	<span class="wap_value">${templateBean.budgetMoney}</span>
	</div>
	<div class="div_label">
		<span class="wap_title">立项类别:</span>
		<div class="div_row">
			<div><s:checkbox name="templateBean.approvalType1" cssClass="group"></s:checkbox><span>法律事务</span></div>
			<div><s:checkbox name="templateBean.approvalType2" cssClass="group"></s:checkbox><span>品牌推广</span></div>
			<div><s:checkbox name="templateBean.approvalType3" cssClass="group"></s:checkbox><span>培训活动</span></div>
			<div><s:checkbox name="templateBean.approvalType4" cssClass="group"></s:checkbox><span>会务组织</span></div>
			<div><s:checkbox name="templateBean.approvalType5" cssClass="group"></s:checkbox><span>奖惩</span></div>
			<div><s:checkbox name="templateBean.approvalType6" cssClass="group"></s:checkbox><span>计划方案变更</span></div>
			<div><s:checkbox name="templateBean.approvalType7" cssClass="group"></s:checkbox><span>其他</span></div>
			<div ><span class="wap_value">${templateBean.otherInfo}</span></div>
		</div>
	</div>
	<div class="div_row">
	<span class="wap_title">申请事由:</span>
	<div class="orgDiv"><pre style="white-space:pre-wrap;">${templateBean.remark}</pre></div>
	</div>
</div>

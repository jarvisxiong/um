<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- KTV门店一般争端处理方案审批表 --%>
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
		<div class="div_row">
			<div><s:checkbox name="templateBean.less" cssClass="group"></s:checkbox><span>单次金额≤2000元，且月度累计金额≤5000元</span></div>
			<div><s:checkbox name="templateBean.more" cssClass="group"></s:checkbox><span>单次2000元＜金额≤1万元，且月度累计5000元＜金额≤2万元</span></div>
			<div><s:checkbox name="templateBean.other" cssClass="group"></s:checkbox><span>其他</span></div>
		</div>
</div>

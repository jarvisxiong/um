<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--成本月战略计划(集团)-->
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<!-- 列表 -->
	<s:iterator value="templateBean.planProperties" var="item" status="s">
	<div class="div_label">
	<div class="div_row">
		<span class="wap_title">商业公司:</span>
		<span class="wap_label">【${item.projectName}】</span>
	</div>
	<div class="div_row">
		<span class="wap_title">类别:</span>
		<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd1" cssClass="group"></s:checkbox><span>工程</span></div>
		<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd2" cssClass="group"></s:checkbox><span>企划</span></div>
		<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd3" cssClass="group"></s:checkbox><span>营运</span></div>
		<div><s:checkbox name="templateBean.planProperties[%{#s.index}].typeCd4" cssClass="group"></s:checkbox><span>行政</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">名称:</span>
		<span class="wap_value">${item.projectDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">招标范围/采购内容:</span>
		<span class="wap_value">${item.invPurDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">预计金额(元):</span>
		<span class="wap_value">${item.totalPrice}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">工期要求:</span>
		<span class="wap_value">${item.timeLimitDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">进场时间:</span>
		<span class="wap_value">${item.enterDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">预计上单时间:</span>
		<span class="wap_value">${item.preUploadDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">定标完成时间:</span>
		<span class="wap_value">${item.bidCompleteDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">备注:</span>
		<span class="wap_value">${item.remark}</span>
	</div>
	</div>
	</s:iterator>
</div>


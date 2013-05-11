<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 续签合同申请表base --%>
<%@ include file="template-var.jsp"%>
<div class="div_row">
<span class="wap_title">姓名:</span>
<span class="wap_value">${templateBean.userName}</span>
</div>
<div class="div_row">
<span class="wap_title">中心/公司:</span>
<span class="wap_value">${templateBean.centerName}</span>
</div>
<div class="div_row">
<span class="wap_title">职位:</span>
<span class="wap_value">${templateBean.position}</span>
</div>
<div class="div_row">
<span class="wap_title">入职日期:</span>
<span class="wap_value">${templateBean.enterDate}</span>
</div>
<div class="div_row">
<span class="wap_title">最近一次合同类型:</span>
<div><s:checkbox name="templateBean.conFixedExpires" cssClass="group"></s:checkbox>固定期限：<s:if test="templateBean.conFixedExpires=='true'">从 ${templateBean.conDateFrom} 至 ${templateBean.conDateTo}</s:if></div>
<div><s:checkbox name="templateBean.conNonFixedExpires" cssClass="group"></s:checkbox>无固定期限：<s:if test="templateBean.conNonFixedExpires=='true'">从 ${templateBean.conNonDateFrom} 起</s:if></div>
<div><s:checkbox name="templateBean.conCompleteTask" cssClass="group"></s:checkbox>以完成一定工作任务：<s:if test="templateBean.conCompleteTask=='true'">${templateBean.conCompleteTaskInput}</s:if></div>		
</div>
<div class="div_row">
<span class="wap_title">申请次数:</span>
<span class="wap_value">本次为申请第 ${templateBean.applyRenewTime} 次申请续签劳动合同</span>
</div>
<div class="div_row">
<span class="wap_title">本项目竣工备案时间:</span>
<span class="wap_value">${templateBean.filingDate}</span>
</div>
<div class="div_row">
<span class="wap_title">续签申请:</span>
<div><s:checkbox name="templateBean.reDisAgreeRenew" cssClass="group"></s:checkbox>不同意续签</div>
<div><s:checkbox name="templateBean.reApplyFixedExpires" cssClass="group"></s:checkbox>申请固定期限：<s:if test="templateBean.reApplyFixedExpires=='true'">${templateBean.reMonth} 个月，从 ${templateBean.reDateFrom} 至 ${templateBean.reDateTo}</s:if></div>
<div><s:checkbox name="templateBean.reCompleteTask" cssClass="group"></s:checkbox>以完成一定工作任务：<s:if test="templateBean.reCompleteTask=='true'">${templateBean.reCompleteTaskInput}</s:if></div>	
<div><s:checkbox name="templateBean.reNonFixedExpires" cssClass="group"></s:checkbox>无固定期限</div>		
</div>

		<div class="div_row">
			<span class="wap_title">附件:</span>
			<div id="show_curPicId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.attachStr}','attachStr','false');
				</script>
		</div>

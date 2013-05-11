<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
		商业工程改造审批单
 --%>
<div class="billContent">
	
	<%@ include file="template-var.jsp"%>
	
	<div class="div_row">
		<s:if test="authTypeCd!='BLSY_WYGC_GCGZ_30'">
			<s:if test="authTypeCd!='BLSY_WYGC_GCGZ_20'">
			<div><s:checkbox name="templateBean.checkCd1" cssClass="group"></s:checkbox><span>零星改造（单项金额≤5000元，月度总额≤2万元）</span></div>
			</s:if>
			<div><s:checkbox name="templateBean.checkCd2" cssClass="group"></s:checkbox><span>涉及使用功能性改造或其他方面改造</span></div>
			<div><s:checkbox name="templateBean.checkCd3" cssClass="group"></s:checkbox><span>涉及外观、效果改造</span></div>
		</s:if>
		<s:if test="authTypeCd=='BLSY_WYGC_GCGZ_20'">
		<div><s:checkbox name="templateBean.hasLandCompany" id="inFlag" cssClass="group"></s:checkbox><span>有地产公司</span></div>
		</s:if>
	</div>
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">工程名称:</span>
		<span class="wap_value">${templateBean.programName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">预估工程费用（元）:</span>
		<span class="wap_value">${templateBean.planFeeAmt}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">工程改造范围:</span>
		<span class="wap_value">${templateBean.areaDesc}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">内容简述（详细内容附后）:</span>
		<span class="wap_value">${templateBean.reasonDesc}</span>
	</div>
	
</div>

<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<%-- 合同评审表 --%> 
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<%--合同库引用 start --%>
			<%@ include file="bid-contract-base.jsp"%>
			<%--合同库引用 end --%>
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	<c:set var="isSy">false</c:set>
	<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
	<c:set var="isSy">true</c:set>
	</c:if>
 
	<div class="div_row">
		<span class="wap_title">项目名称:</span>
		<span class="wap_value">${templateBean.projectName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">工程名称:</span>
		<span class="wap_value">${templateBean.engineeringName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">定标审批表编号:</span>
		<span class="wap_value">${templateBean.resApproveCd}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">定标审批表名称:</span>
		<span class="wap_value">${templateBean.resApproveTitleName}</span>
	</div>
	 
	<s:if test="authTypeCd=='JD_ZCGL_HTQS_40'||authTypeCd=='JD_ZCGL_HTQS_70'||authTypeCd=='JD_ZCGL_HTQS_90'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		</div>
	</s:if>
	

	<div class="div_row">
		<span class="wap_title">评审内容:</span>
		<div><s:checkbox name="templateBean.approveContent2" cssClass="group"></s:checkbox><span>合同文件</span></div>
	</div>
	 
	 
		
	<div class="div_label">
		<span class="wap_title">【合作方式】</span>
		<div class="div_row"><s:checkbox name="templateBean.cooperationWays1" cssClass="group"></s:checkbox><span>续标(原合同文件编号：${templateBean.oriContractFileCd})</span></div>
		<div class="div_row"><s:checkbox name="templateBean.cooperationWays2" cssClass="group"></s:checkbox><span>直接委托(理由：${templateBean.delegateReason})</span></div>
		<div class="div_row"><s:checkbox name="templateBean.cooperationWays3" cssClass="group"></s:checkbox><span>竞争性谈判(理由：${templateBean.negotiationReason})</span></div>
	</div>
		 
	
		
	<div class="div_label">
		<span class="wap_title">【合同主要内容】</span>
		
		<div class="div_row">
			<span class="wap_title">合同范围:</span>
			<span class="wap_value">${templateBean.bidRange}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">工期:</span>
			<span class="wap_value">${templateBean.fromDate}</span>
			<span class="wap_title">至</span>
			<span class="wap_value">${templateBean.toDate}</span>
			<span class="wap_title">共</span>
			<span class="wap_value">${templateBean.totalDay}</span>
			<span class="wap_title">天</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">质量要求:</span>
			<span class="wap_value">${templateBean.qualityRequirement}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">计价模式:</span>
			
			<div><s:checkbox name="templateBean.pricingModel1" cssClass="group"></s:checkbox><span>总价包干</span></div>
			<div><s:checkbox name="templateBean.pricingModel2" cssClass="group"></s:checkbox><span>单价包干</span></div>
			<div><s:checkbox name="templateBean.pricingModel3" cssClass="group"></s:checkbox><span>按实结算</span></div>
		</div>
		
		<div class="div_row">
			<span class="wap_title">预算金额(元):</span>
			<span class="wap_value">${templateBean.budgetAmount}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">付款方式:</span>
			<span class="wap_value">${templateBean.paymentType}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">评审附件</span>
			<div id="show_bidContractFileId"></div>
			<script type="text/javascript">
			showUploadedFile('${templateBean.bidContractFileId}','bidContractFileId','${canEdit}');
			</script>
		</div>
	</div>     
  </div>
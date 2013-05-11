<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<ul id="nav_btns_operations"  class="nav_btns">

	<c:if test="${fn:length(operations)==0 }">
		<div style="color: red;font-size: 12px; padding-left:5px; line-height: 24px; height:24px;">当前项目尚未配置有效业态</div>
	</c:if>
	
	<s:iterator value="operations">
		<li>
			<div style="cursor:pointer;" onclick="switchOperation($(this), '${projectCd}','${planExecutionPlanId}')" id="operation_${planExecutionPlanId}" <c:if test="${operationId == planExecutionPlanId}">class="active"</c:if>>${executionPlanName}</div>
		</li>
		<li>
			<div class="spliter"></div>
		</li>
	</s:iterator>
</ul>
<script language="javascript">
	//默认当前业态
	var operationId = "${operationId}";
	//alert("operationId="+operationId);
	if(operationId != ''){
		isLoading = false;
		$("#operation_" + operationId).trigger("click");
	}	
</script>
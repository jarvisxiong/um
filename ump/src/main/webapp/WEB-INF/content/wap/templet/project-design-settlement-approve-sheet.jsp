<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程预算审批表 --%>
<%@ include file="template-var.jsp"%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div id="billContent">
   <div>
  <span class="wap_title">合同编号:</span>
	  <span class="wap_value">${templateBean.contNo}</span>
    </div>
    <div>
     <span class="wap_title">编号:</span>
	  <span class="wap_value">${templateBean.seriNo}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">合同名称:</span>
	  <span class="wap_value">${templateBean.contName}</span>
    </div>
  
    <div class="div_row">
	  <span class="wap_title">乙方:</span>
	  <span class="wap_value">${templateBean.partB}</span>
    </div>
      <div class="div_row">
	  <span class="wap_title">项目名称:</span>
	  <span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
    </div>
    
    <div class="div_row">
	  <span class="wap_title">原合同总价:</span>
	  <span class="wap_value">${templateBean.totalPrice}</span>
    </div>
    <div class="div_row">
	  <span class="wap_title">合同性质:</span>
	  <span class="wap_value">${templateBean.contProperty}</span>
    </div>
    
      <div class="div_row">
	  <span class="wap_title">乙方报审金额（元）:</span>
	  <span class="wap_value">${templateBean.partBReviewAmount}</span>
    </div>
    
    <div class="div_row">
	  <span class="wap_title">地产公司审核费用（元）:</span>
	  <span class="wap_value">${templateBean.landEstateCorpApproCost}</span>
    </div>
    
     <div class="div_row">
	  <span class="wap_title">设计原因引起的变更造价:</span>
	  <span class="wap_value">${templateBean.designChangeCost}</span>
    </div>
    
     <div class="div_row">
	  <span class="wap_title">对应工程造价:</span>
	  <span class="wap_value">${templateBean.projectCost}</span>
    </div>
    
     <div class="div_row">
	  <span class="wap_title">设计变更率:</span>
	  <span class="wap_value">${templateBean.designChangeRate}</span>
    </div>
    
      <div class="div_row">
	  <span class="wap_title">违约扣款:</span>
	  <span class="wap_value">${templateBean.defaultDeductCost}</span>
    </div>
    
    <div class="div_row">
	  <span class="wap_title">设计成果验收、现场服务说明:</span>
	  <span class="wap_value">${templateBean.designResultCheck}</span>
    </div>
    
    
    
    <div class="div_label">
      <span class="wap_label">【附件】</span>
    
	  <div class="div_row">
	     <span class="wap_title">设计成果审批表:</span>
	     <div id="show_designResultApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.designResultApproveId}','designResultApproveId','${canEdit}');
				</script>
	  </div>
	  <div class="div_row">
	     <span class="wap_title">设计变更统计表:</span>
	     <div id="show_designChangeTotalId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.designChangeTotalId}','designChangeTotalId','${canEdit}');
				</script>
	  </div>
	  
    </div>
</div>
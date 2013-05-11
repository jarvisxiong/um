<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page import="com.hhz.ump.util.JspUtil"%>
<%@ page import="java.util.Map"%>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page import="com.hhz.ump.util.LoginUtil"%>


<%-- 定标审批表（地产营销类） --%>
<div id="billContent">
	<%@ include file="template-var.jsp"%>
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>

		<s:if test="authTypeCd == 'ZCGL_HTQS_105' || authTypeCd == 'ZCGL_HTQS_111'">
			<div class="div_row">
				<div><s:checkbox name="templateBean.businessCompany" cssClass="group"></s:checkbox><span>总部发起</span></div>
				<div><s:checkbox name="templateBean.businessGroup" cssClass="group"></s:checkbox><span>地产公司发起</span></div>
			</div>
		</s:if>
			 
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
			(<span class="wap_value">${templateBean.projectPeriod}</span>)期
		</div> 
		
		
		<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value">${templateBean.engineeringName}</span>
		</div> 

		<s:if test="authTypeCd=='ZCGL_HTQS_53'||authTypeCd=='ZCGL_HTQS_55'||authTypeCd=='ZCGL_HTQS_101'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		</div>
		</s:if>
			
		<div class="div_label">
			<span class="wap_title">【招  标/主  要/内  容】</span>
			
			<div class="div_row">
				<span class="wap_title">服务内容:</span>
				<span class="wap_value">${templateBean.bidRange}</span>
			</div>
			
				
			<div class="div_row">
				<span class="wap_title">服务工期:</span>
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
			</div>
			
			<div class="div_row">
				<span class="wap_title">付款方式:</span>
				<span class="wap_value">${templateBean.paymentType}</span>
			</div>
		</div>
		
		<div class="div_row">
			<span class="wap_title">是否项目权限内:</span>
			<div><s:checkbox name="templateBean.isProjectAuth" cssClass="group"></s:checkbox><span></span></div>
		</div>
		
		<div class="div_row">
			<span class="wap_title">是否垄断:</span>
			<div><s:checkbox name="templateBean.isMonopoly" cssClass="group"></s:checkbox><span></span></div>
		</div>
		
		<div class="div_row">
			<span class="wap_title">中标单位:</span>
			<span class="wap_value">${templateBean.bidUnit}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.remark}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">推荐中标理由:</span>
			<span class="wap_value">${templateBean.successfulBidReason}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">预算费用(元):</span>
			<span class="wap_value">${templateBean.targetCost}</span>
		</div>
		
		
		<div class="div_row">
			<span class="wap_title">中标价(元):</span>
			<span class="wap_value">${templateBean.successfulBidPrice}</span>
		</div>
		
		
		
		
		<div class="div_label">
			<span class="wap_title">【定标资料附件】</span>
			<div class="div_row">
				<span class="wap_title">1、中标单位报价清单</span>
				<div id="show_bidPriceListId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidPriceListId}','bidPriceListId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">2、预算审批表</span>
				<div id="show_bidAnswerAndBidQueryId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidAnswerAndBidQueryId}','bidAnswerAndBidQueryId','${canEdit}');
				</script>
			</div>
		</div>
</div>
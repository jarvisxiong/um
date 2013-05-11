<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 扣减费用核定单 --%>
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
		<div class="div_row">
			<span class="wap_title">合同编号:</span>
			<span class="wap_value">${templateBean.contNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<span class="wap_value">${templateBean.contName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
		</div>
		<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.partB}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">扣减内容:</span>
			<span class="wap_value">${templateBean.reduceContent}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">扣减原因:</span>
				<div><s:checkbox name="templateBean.reduceReason1" cssClass="group"></s:checkbox><span>另行委托</span></div>
				<div><s:checkbox name="templateBean.reduceReason2" cssClass="group"></s:checkbox><span>设计变更减少</span></div>
				<div><s:checkbox name="templateBean.reduceReason3" cssClass="group"></s:checkbox><span>未施工甩项，且经同意</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">地产公司扣减费用(元):</span>
			<span class="wap_value">${templateBean.reduceMoney}</span>
		</div>
		
		<div class="div_row">
			<span class="wap_title">核价编制说明:</span>
			<span class="wap_value">1、变更费用按合同${templateBean.itemNo}条款  第${templateBean.functionNo}其中工程量按${templateBean.projectQuantity}计量，</span>
			<span class="wap_value">单价按:</span>
				<div><s:checkbox name="templateBean.unitPriceType1" cssClass="group"></s:checkbox><span>合同综合单价</span></div>
				<div><s:checkbox name="templateBean.unitPriceType2" cssClass="group"></s:checkbox><span>参考合同综合单价</span></div>
				<div><s:checkbox name="templateBean.unitPriceType3" cssClass="group"></s:checkbox><span>定额计价</span></div>
		    <span class="wap_value">确定，合同约定下浮率${templateBean.reduceRate}%</span>
		    
		</div>
		<div class="div_label">
			<span class="wap_label">【附件】</span>
			<div class="div_row">
				<span class="wap_title">核减的预算:</span>
				<div id="show_reduceBudgetId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.reduceBudgetId}','reduceBudgetId','${canEdit}');
				</script>				
			</div>
			<div class="div_row">
				<span class="wap_title">图纸或现场签证:</span>
					<div id="show_liveVisaId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.liveVisaId}','liveVisaId','${canEdit}');
					</script>
				
			</div>
			<div class="div_row">
				<span class="wap_title">工程量确认单:</span>				
					<div id="show_quantityConformId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.quantityConformId}','quantityConformId','${canEdit}');
					</script>
				
			</div>
			
	    </div>
		
		
</div>


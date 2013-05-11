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
	<s:if test="aboutHotel">
		<div class="div_row">
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
	</s:if>
	<s:if test="authTypeCd=='JD_ZCGL_GCYS_10'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		</div>
	</s:if>
	
	<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
	</div>
	<div class="div_row">
			<span class="wap_title">编号:</span>
			<span class="wap_value">${templateBean.serialNo}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">拟招标工程名称:</span>
			<span class="wap_value">${templateBean.planBidProjectName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">工程范围/内容:</span>
			<span class="wap_value">${templateBean.projectContent}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">计划开工时间:</span>
			<span class="wap_value">${templateBean.planBeginDate}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">计划竣工时间:</span>
			<span class="wap_value">${templateBean.planEndDate}</span>
	</div>
	<div class="div_row">
			<span class="wap_title">工期:</span>
			<span class="wap_value">${templateBean.planDays}</span>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【拟招标方式】</span>
		<div class="div_row">
			<div><s:checkbox name="templateBean.planBidMode1" cssClass="group"></s:checkbox><span>邀请招标</span></div>
			<div><s:checkbox name="templateBean.planBidMode2" cssClass="group"></s:checkbox><span>竞争性议标</span></div>
			<div><s:checkbox name="templateBean.planBidMode3" cssClass="group"></s:checkbox><span>直接委托议价/垄断议价</span></div>
			<div><s:checkbox name="templateBean.planBidMode4" cssClass="group"></s:checkbox><span>定向续标</span></div>
			<div >
				<span class="wap_title">主合同编号:</span>
				<span class="wap_value">${templateBean.contractCd}</span>
			</div>
			<div>
				<span class="wap_title">和合同名称:</span>
				<span class="wap_value">${templateBean.contractName}</span>
			</div>
		</div>
	</div>
	<div class="div_label">
		<span class="wap_label">【审批用途】</span>
		<div class="div_row">
			<div><s:checkbox name="templateBean.approveUsage1" cssClass="group"></s:checkbox><span>标底限价</span></div>
			<div><s:checkbox name="templateBean.approveUsage2" cssClass="group"></s:checkbox><span>谈判指导价</span></div>
			<div><s:checkbox name="templateBean.approveUsage3" cssClass="group"></s:checkbox><span>立项预算</span></div>
			<div><s:checkbox name="templateBean.approveUsage4" cssClass="group"></s:checkbox><span>其他</span></div>
		</div>
	</div>
	
	<div class="div_label">
		<span class="wap_label">【地产公司申报预算金额(元)】</span>
		<div class="div_row">
			<div><s:checkbox name="templateBean.budgetAccordingTo1" cssClass="group"></s:checkbox><span>施工图(经批准的)</span></div>
			<div><s:checkbox name="templateBean.budgetAccordingTo2" cssClass="group"></s:checkbox><span>方案(经批准的)</span></div>
		</div>
	</div>
	
	<div class="div_row">
			<s:if test="authTypeCd==BLSY_ZCGL_GCYS_10"><span class="wap_title">商业公司申报预算金额(元):</span></s:if>
			<s:elseif test="authTypeCd=='JD_ZCGL_GCYS_10'" ><span class="wap_title">酒店公司申报预算金额(元):</span></s:elseif>
			<s:else><span class="wap_title">地产公司申报预算金额(元):</span></s:else>
			<span class="wap_value">${templateBean.declareBudgetAmount}</span>
	</div>	
	
	<div class="div_row">
			<span class="wap_title">指标:</span>
			<span class="wap_value">${templateBean.indicate}</span>
	</div>
	
	<div class="div_row">
			<span class="wap_title">预算编制说明:</span>
			<span class="wap_value">${templateBean.budgetDesc}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【附件】</span>
		<div class="div_row">
				<span class="wap_title">立项审批或图纸审批文件:</span>
				<div id="show_approveFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.approveFileId}','approveFileId','${canEdit}');
				</script>
		</div>		
		<div class="div_row">
				<span class="wap_title">图纸:</span>
				<div id="show_pictureId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.pictureId}','pictureId','${canEdit}');
				</script>
		</div>		
	</div>
	
</div>
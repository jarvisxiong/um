<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 招标文件/合同评审表（合同文件） --%>

<c:set var="isSy">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
<c:set var="isSy">true</c:set>
</c:if>

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<%--合同库引用 start --%>
		<%@ include file="bid-contract-base.jsp"%>
		<%--合同库引用 end --%>
		<c:if test="${isSy}">
		<div class="div_row">
			<div><s:checkbox name="templateBean.businessCompany" cssClass="group"></s:checkbox><span>商业公司发起</span></div>
			<div><s:checkbox name="templateBean.businessGroup" cssClass="group"></s:checkbox><span>商业集团发起</span></div>
		</div>
		</c:if>
		<s:if test="aboutHotel">
		<div class="div_row">
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
		</s:if>
		<s:if test="authTypeCd=='JD_ZCGL_HTQS_20'||authTypeCd=='JD_ZCGL_HTQS_40'||authTypeCd=='JD_ZCGL_HTQS_50'||authTypeCd=='JD_ZCGL_HTQS_60'||authTypeCd=='JD_ZCGL_HTQS_70'||authTypeCd=='JD_ZCGL_HTQS_80'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		</div>
		</s:if>
		<div class="div_row">
			<span class="wap_title">项目名称:</span>
			<span class="wap_value">${templateBean.projectName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">工程名称:</span>
			<span class="wap_value">${templateBean.engineeringName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">评审内容:</span>
			<div><s:checkbox name="templateBean.approveContent2" cssClass="group"></s:checkbox><span>合同文件</span></div>
		</div>
		<div class="div_row">
			<span class="wap_title">单位名称:</span>
			<span class="wap_value">${templateBean.unitName}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.remark}</span>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.isMonopoly" cssClass="group"></s:checkbox><span>是垄断</span></div>
		</div>
		<div class="div_label">
			<span class="wap_label">【合作方式】</span>
			<div class="div_row">
			<div>
			<s:checkbox name="templateBean.cooperationWays1" cssClass="group"></s:checkbox><span>招标(招标文件编号:</span>
			<span class="wap_value">${templateBean.oriBidFileCd})</span>
			</div>
			<div>
			<s:checkbox name="templateBean.cooperationWays2" cssClass="group"></s:checkbox><span>续标(原合同文件编号:</span>
			<span class="wap_value">${templateBean.oriContractFileCd})</span>
			</div>
			<div>
			<s:checkbox name="templateBean.cooperationWays3" cssClass="group"></s:checkbox><span>直接委托(理由:</span>
			<span class="wap_value">${templateBean.delegateReason})</span>
			</div>
			<div>
			<s:checkbox name="templateBean.cooperationWays4" cssClass="group"></s:checkbox><span>竞争性谈判(理由:</span>
			<span class="wap_value">${templateBean.negotiationReason})</span>
			</div>
			</div>
		</div>
		<div class="div_label">
			<span class="wap_label">【招标主要内容】</span>
			<div class="div_row">
				<span class="wap_title">制作/发布内容:</span>
				<span class="wap_value">${templateBean.bidRange}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">服务工期:</span>
				<span class="wap_value">${templateBean.fromDate} 至  ${templateBean.toDate} 共 ${templateBean.totalDay}天</span>
			</div>
			<div class="div_row">
				<span class="wap_title">质量要求:</span>
				<span class="wap_value">${templateBean.qualityRequirement}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">招标模式:</span>
				<div><s:checkbox name="templateBean.inviteBidModel1" cssClass="group"></s:checkbox><span>工程量清单</span></div>
				<div><s:checkbox name="templateBean.inviteBidModel2" cssClass="group"></s:checkbox><span>费率</span></div>
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
		</div>
		<div class="div_label">
			<span class="wap_label">【评审附件】</span>
			<div class="div_row">
				<span class="wap_title">招标合同文件</span>
				<div id="show_bidContractFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidContractFileId}','bidContractFileId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">定标审批表</span>
				<div id="show_bidApproveBillId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidApproveBillId}','bidApproveBillId','${canEdit}');
				</script>
			</div>
			<div class="div_row">
				<span class="wap_title">合同条款审批表</span>
				<div id="show_contractItemApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.contractItemApproveId}','contractItemApproveId','${canEdit}');
				</script>
			</div>
		</div>
		
</div>

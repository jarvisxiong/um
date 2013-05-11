<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 合同评审表（地产战略） --%>

<c:set var="isSy">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
<c:set var="isSy">true</c:set>
</c:if>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">合同编号:</span>
			<span class="wap_value">${templateBean.contNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">合同名称:</span>
			<span class="wap_value">${templateBean.engineeringName}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【合作双方】</span>
			<div class="div_row">
			<span class="wap_title">甲方:</span>
			<span class="wap_value">${templateBean.approveContent1}</span>
			</div>
			<div class="div_row">
			<span class="wap_title">乙方:</span>
			<span class="wap_value">${templateBean.approveContent2}</span>
			</div>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<div class="div_row">
			<span class="wap_title">丙方:</span>
			<span class="wap_value">${templateBean.approveContent3}</span>
			</div>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
		</div>
		<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.remark}</span>
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
				<span class="wap_title">招标范围:</span>
				<span class="wap_value">${templateBean.bidRange}</span>
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
		</div>
		
</div>

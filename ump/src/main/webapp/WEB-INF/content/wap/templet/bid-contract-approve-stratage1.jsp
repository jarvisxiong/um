<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 招标文件/合同评审表（招标文件） --%>

<c:set var="isSy">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
<c:set var="isSy">true</c:set>
</c:if>

<div id="billContent">

		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<div><s:checkbox name="templateBean.businessCompany" cssClass="group"></s:checkbox><span>标准</span></div>
			<div><s:checkbox name="templateBean.businessGroup" cssClass="group"></s:checkbox><span>非标准</span></div>
		</div>
		<s:if test="aboutHotel">
		<div class="div_row">
			<span class="wap_title">审批权限:</span>
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
		</s:if>
		<s:if test="authTypeCd=='JD_ZCGL_ZB_10'||authTypeCd=='JD_ZCGL_ZB_20'||authTypeCd=='JD_ZCGL_ZB_30'||authTypeCd=='JD_ZCGL_ZB_40'">
		<div class="div_row">
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
		</div>
		</s:if>
		<%@ include file="bid-contract-base.jsp"%>
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
			<div><s:checkbox name="templateBean.approveContent1" cssClass="group"></s:checkbox>
			<span>招标文件(招标文件编号：</span>
			<span class="wap_value">${templateBean.oriBidFileCd}</span>
			</div>
		</div>
		<s:if test="authTypeCd == 'ZB_ZBWJ_17'">
			<span class="wap_title">招采计划编号:</span>
			<span class="wap_value">${templateBean.ccbpNo}</span>
		</s:if>
				
				
		<div class="div_label">
			<span class="wap_label">【招标主要内容】</span>
			<div class="div_row">
				<span class="wap_title">招标范围:</span>
				<span class="wap_value">${templateBean.bidRange}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">施工工期:</span>
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
				<div><s:checkbox name="templateBean.inviteBidModel3" cssClass="group"></s:checkbox><span>模拟清单(出图后1个月完成工程量核对)</span></div>
			</div>
			<div class="div_row">
				<span class="wap_title">计价模式:</span>
				<div><s:checkbox name="templateBean.inviteBidModel1" cssClass="group"></s:checkbox><span>总价包干</span></div>
				<div><s:checkbox name="templateBean.inviteBidModel2" cssClass="group"></s:checkbox><span>单价包干（出图一个月完成总价包干）</span></div>
				<div><s:checkbox name="templateBean.inviteBidModel3" cssClass="group"></s:checkbox><span>按实结算</span></div>
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
			
		</div>
		
</div>

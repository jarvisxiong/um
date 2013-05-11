<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 材料设备款付款审批表 --%>
<div id="billContent">
	
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">审批权限:</span>
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
		</div>
		<div class="div_row">
			<div><s:checkbox name="templateBean.inFlag" cssClass="group"></s:checkbox><span>预算内</span></div>
			<div><s:checkbox name="templateBean.outFlag" cssClass="group"></s:checkbox><span>预算外</span></div>
		</div>
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
			<span class="wap_value">${templateBean.projectName} (${templateBean.projectPeriod})期</span>
		</div>
		<div class="div_row">
			<span class="wap_title">付款单位:</span>
			<span class="wap_value">${templateBean.paymentUnit}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【收款人(乙方)信息】</span>
			<div class="div_row">
				<span class="wap_title">收款人名称:</span>
				<span class="wap_value">${templateBean.partB}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">收款人开户行:</span>
				<span class="wap_value">${templateBean.payeeOpenBankNo}</span>
			</div>
			<div class="div_row">
				<span class="wap_title">收款人账号:</span>
				<span class="wap_value">${templateBean.payeeAccountNo}</span>
			</div>
		</div>
		<div class="div_row">
				<span class="wap_title">付款方式:</span>
				<span class="wap_value">${templateBean.payWay}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">合同总价:</span>
				<span class="wap_value">${templateBean.totalPrice}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">已确认合同总价:</span>
				<span class="wap_value">${templateBean.updateTotal}</span>
		</div>
		<div class="div_row">
				<span class="wap_title">本次付款申请金额(元):</span>
				<span class="wap_value">${templateBean.applyAmount}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【付款计算】</span>
			<div class="div_scroll">
			<table>
					<tr>
						<td></td>
						<td><span class="wap_title">已累计</span></td>
						<td><span class="wap_title">本次</span></td>
						<td><span class="wap_title">小计</span></td>
					</tr>
					<tr>
						<td><span class="wap_title">已供货合价</span></td>
						<td>
						<span class="wap_value">${templateBean.completeNumBefore}</span>
						</td>
						<td>
						<span class="wap_value">${templateBean.completeNumThis}</span>
						</td>
						<td >
						<span class="wap_value">${templateBean.completeNumTotal}</span>
						</td>
					</tr>
					<tr>
						<td><span class="wap_title">付款</span></td>
						<td>
						<span class="wap_value">${templateBean.currentPayBefore}</span>
						</td>
						<td>
						<span class="wap_value">${templateBean.currentPayThis}</span>
						</td>
						<td >
						<span class="wap_value">${templateBean.currentPayTotal}</span>
						</td>
					</tr>
					<tr>
						<td><span class="wap_title">付款比例%</span></td>
						<td>
						<span class="wap_value">${templateBean.payRateBefore}</span>
						</td>
						<td>
						<span class="wap_value">${templateBean.payRateThis}</span>
						</td>
						<td >
						<span class="wap_value">${templateBean.payRateTotal}</span>
						</td>
					</tr>
					<s:iterator value="templateBean.otherProperties" var="item" status="s">
					<tr>
						<td><span class="wap_title"><s:if test="#item.constructionUnit!=''">(${item.constructionUnit})</s:if>施工单位领货合价</span></td>
						<td>
						<span class="wap_value">${item.receiveAmountBefore}</span>
						</td>
						<td>
						<span class="wap_value">${item.receiveAmountThis}</span>
						</td>
						<td >
						<span class="wap_value">${item.receiveAmountTotal}</span>
						</td>
					</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<div class="div_row">
				<span class="wap_title">需说明事项:</span>
				<span class="wap_value">${templateBean.description}</span>
		</div>
		<div class="div_label">
				<span class="wap_label">【附件】</span>
				<div class="div_row">
					<span class="wap_title">供货、领货明细表:</span>
					<div id="show_provideDetailId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.provideDetailId}','provideDetailId','${canEdit}');
					</script>
				</div>
				<div class="div_row">
					<span class="wap_title">三方验收凭证:</span>
					<div id="show_threeSideAcceptCertificateId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.threeSideAcceptCertificateId}','threeSideAcceptCertificateId','${canEdit}');
					</script>
				</div>
		</div>
			
</div>

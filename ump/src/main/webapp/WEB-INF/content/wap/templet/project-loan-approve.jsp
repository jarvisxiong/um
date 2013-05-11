<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程预付(借)款审批表 --%>
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
			<span class="wap_title"> 审批权限:</span>
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
	</div>
	<div class="div_row">
			<span class="wap_title"> 款项性质:</span>
			<div><s:checkbox name="templateBean.checkType1" cssClass="group"></s:checkbox><span>预付款</span></div>
			<div><s:checkbox name="templateBean.checkType2" cssClass="group"></s:checkbox><span>借款</span></div>
	</div>
	<div class="div_row">
			<span class="wap_title"> 合同编号:</span>
			<span class="wap_value">${templateBean.contNo}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 合同名称:</span>
			<span class="wap_value">${templateBean.contName}</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 项目名称:</span>
			<span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})期</span>
	</div>
	<div class="div_row">
			<span class="wap_title"> 付款单位:</span>
			<span class="wap_value">${templateBean.paymentUnit}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【收款人(乙方)信息】</span>
		<div class="div_row">
			<span class="wap_title"> 收款人名称:</span>
			<span class="wap_value">${templateBean.partB}</span>
		</div>
		<div class="div_row">
			<span class="wap_title"> 收款人开户行:</span>
			<span class="wap_value">${templateBean.payeeOpenBankNo}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">收款人账号 :</span>
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
			<span class="wap_title">借款原因:</span>
			<span class="wap_value">${templateBean.loanReason}</span>
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
							<td><span class="wap_title">已确认产值(含甲供料)</span></td>
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
							<td><span class="wap_title">扣：甲供料款(按暂定价)</span></td>
							<td>
							<span class="wap_value">${templateBean.matieralNumBefore}</span>
							</td>
							<td>
							<span class="wap_value">${templateBean.matieralNumThis}</span>
							</td>
							<td >
							<span class="wap_value">${templateBean.matieralNumTotal}</span>
							</td>
						</tr>
						<tr>
							<td><span class="wap_title">扣：其他扣款或代扣款</span></td>
							<td>
							<span class="wap_value">${templateBean.currentAddBefore}</span>
							</td>
							<td>
							<span class="wap_value">${templateBean.currentAddThis}</span>
							</td>
							<td >
							<span class="wap_value">${templateBean.currentAddTotal}</span>
							</td>
						</tr>
						<tr>
							<td><span class="wap_title">直接支付</span></td>
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
							<td><span class="wap_title">产值付款比例</span></td>
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
						
					</table>
			</div>
	</div>
	<div class="div_row">
			<span class="wap_title">备注:</span>
			<span class="wap_value">${templateBean.description}</span>
	</div>
	<div class="div_label">
			<span class="wap_label">【附件】</span>
			<div class="div_row">
				<span class="wap_title">借款理由的相应附件</span>
				<div id="show_loanReasonId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.loanReasonId}','loanReasonId','${canEdit}');
				</script>
			</div>
	</div>
</div>

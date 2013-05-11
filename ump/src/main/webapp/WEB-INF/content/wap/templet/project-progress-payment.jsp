<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程进度款付款审批表 --%>

<div id="billContent">
		<%@ include file="template-var.jsp"%>
		<div class="div_row">
			<span class="wap_title">审批权限</span>
			<div><s:checkbox name="templateBean.hotel" cssClass="group"></s:checkbox><span>与酒店有关</span></div>
			<div><s:checkbox name="templateBean.otherCondition" cssClass="group"></s:checkbox><span>景观、装修、外立面</span></div>
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
			<span class="wap_value">${templateBean.projectName}(${templateBean.projectPeriod})</span>
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
			<span class="wap_title">合同总价:</span>
			<span class="wap_value">${templateBean.totalPrice}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">已确认合同总价:</span>
			<span class="wap_value">${templateBean.payWay}</span>
		</div>
		<div class="div_row">
			<span class="wap_title">本次付款申请金额(元):</span>
			<span class="wap_value">${templateBean.applyAmount}</span>
		</div>
		<div class="div_label">
			<span class="wap_label">【付款计算】<red>(可左右拖动查看)</red></span>
			<div class="div_scroll">
						<table>
							<col width="200"/>
							<col/>
							<col/>
							<col/>
							<tr>
								<td ></td>
								<td class="wap_title">已累计</td>
								<td class="wap_title">本次</td>
								<td class="wap_title">小计</td>
							</tr>
							<tr>
								<td class="wap_title">已确认产值(含甲供料)</td>
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
								<td class="wap_title">扣：甲供料款(按暂定价)</td>
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
								<td class="wap_title">扣：其他扣款或代扣款</td>
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
								<td class="wap_title">直接支付</td>
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
								<td class="wap_title">产值付款比例</td>
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
							<tr>
								<td class="wap_title">备注</td>
								<td colspan="3">
									<span class="wap_value">${templateBean.description}</span>
								</td>
							</tr>
						</table>
			
			</div>
		</div>
		<div class="div_label">
		<span class="wap_label">【资料附件】</span>
		<div class="div_row">
			<span class="wap_title">月度工程造价产值确认表:</span>
			<div id="show_clearApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.clearApproveId}','clearApproveId','${canEdit}');
				</script>
		</div>
		<div class="div_row">
			<span class="wap_title">现场进度照片(不少于5张，否则退单):</span>
			<div id="show_curPicId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.curPicId}','curPicId','${canEdit}');
				</script>
		</div>
		</div>
</div>

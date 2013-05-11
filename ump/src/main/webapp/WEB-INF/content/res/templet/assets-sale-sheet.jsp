<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<%-- 办公资产出售/报废/遗失申请单 --%>
		<div align="center" class="billContent">
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
					<col width="30%"/>
					<tr>
						<td class="td_title">设备名称</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.assetName" value="${templateBean.assetName}"  />
						</td>
						<td class="td_title">分类编号</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.typeNo" value="${templateBean.typeNo}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">使用部门</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.useDept" value="${templateBean.useDept}"  />
						</td>
						<td class="td_title">使用责任人</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.useUser" value="${templateBean.useUser}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">规格型号</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.modelNo" value="${templateBean.modelNo}"  />
						</td>
						<td class="td_title">数量</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.assetNum" value="${templateBean.assetNum}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">购置时间</td>
						<td>
							<input class="Wdate inputBorder" validate="required" type="text" name="templateBean.buyDate" value="${templateBean.buyDate}" onfocus="WdatePicker()" />
						</td>
						<td class="td_title">使用年限</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.userYear" value="${templateBean.userYear}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">金额(元)</td>
						<td colspan="3">
							<input class="inputBorder" validate="required"  onblur="formatVal($(this));" type="text" name="templateBean.totalMoney" value="${templateBean.totalMoney}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">出售/报废/遗失原因</td>
						<td colspan="3">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.saleCause">${templateBean.saleCause}</textarea>
						</td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>

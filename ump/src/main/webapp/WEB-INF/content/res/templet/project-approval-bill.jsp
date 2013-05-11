<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%--	立项申请单-自由步骤	--%>
<div align="center" class="billContent">
	<%--财务管理中心地产财务部，财务管理中心财务部负责人， 宝龙商业财务部负责人，宝龙集团财务总监--%>
	<s:if test="(nodeCd == 151 || nodeCd==91 || nodeCd==170 || nodeCd==257|| nodeCd==285) && isMyApprove==1">
		<s:hidden id="isEdit" value="true"/>
		<s:set var="isEdit" value="true" />
	</s:if> 
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px"/>
			<col/>
			<tr>
				<td class="td_title">费用名称</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.feeName" value="${templateBean.feeName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">预算类别</td>
				<td class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.budgetType1" cssClass="group"></s:checkbox>初步预算</td>
					<td><s:checkbox name="templateBean.budgetType2" cssClass="group"></s:checkbox>精确费用(立即付款)</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">预算处理<br/><span style="font-size: 10px;">(此栏由财务填写)</span></td>
				<s:if test="#isEdit==true">
				<td class="chkGroup" align="left" edit="true" validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.budgetDeal1" edit="true" cssClass="group"></s:checkbox>年度预算内调剂</td>
					<td><s:checkbox name="templateBean.budgetDeal2" edit="true" cssClass="group"></s:checkbox>新增预算</td>
					</tr>
				</table>
				</td>
				</s:if>
				<s:else>
				<td class="chkGroup" align="left">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.budgetDeal1"  cssClass="group"></s:checkbox>年度预算内调剂</td>
					<td><s:checkbox name="templateBean.budgetDeal2"  cssClass="group"></s:checkbox>新增预算</td>
					</tr>
				</table>
				</td>
				</s:else>
			</tr>
			
			<tr>
				<td class="td_title">预算金额(元)</td>
				<td>
				<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));" name="templateBean.budgetMoney" value="${templateBean.budgetMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">立项类别</td>
				<td class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
					<col width="120">
					<col width="120">
					<col width="120">
					<col width="120">
					<tr>
					<td><s:checkbox name="templateBean.approvalType1" cssClass="group"></s:checkbox>法律事务</td>
					<td><s:checkbox name="templateBean.approvalType2" cssClass="group"></s:checkbox>品牌推广</td>
					<td><s:checkbox name="templateBean.approvalType3" cssClass="group"></s:checkbox>培训活动</td>
					<td><s:checkbox name="templateBean.approvalType4" cssClass="group"></s:checkbox>会务组织</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.approvalType5" cssClass="group"></s:checkbox>奖惩</td>
					<td><s:checkbox name="templateBean.approvalType6" cssClass="group"></s:checkbox>计划方案变更</td>
					<td colspan="2"><s:checkbox name="templateBean.approvalType7" id="approvalType7" cssClass="group" onchange="checkOhter();"></s:checkbox>其他
					<input class="inputBorder" id="otherInfo" type="text" name="templateBean.otherInfo" value="${templateBean.otherInfo}" style="width:100px;"/>
					</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">申请事由</td>
				<td>
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-steps.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function checkOhter(){
	var checkOther=$("#approvalType7").attr("checked");
	if (checkOther){
		$('#otherInfo').attr("validate", "required").addClass('required');
	}else{
		$("#otherInfo").removeAttr("validate").removeClass('required');
	}
}
</script>

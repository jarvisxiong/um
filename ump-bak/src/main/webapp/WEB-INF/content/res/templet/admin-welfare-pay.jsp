<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%--员工行政、福利支出申请表	--%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px"/>
			<col/>
			<col width="90px"/>
			<col/>
			<tr>
				<td class="td_title">标题</td>
				<td colspan="3">
				<r:resInput name="titleName" value="${templateBean.titleName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">公司名称</td>
				<td><r:resInput name="companyName" value="${templateBean.companyName}" /></td>
				<td colspan="2" validate="required">
					<s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外
					<s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内
				</td>
			</tr>
			<tr>
				<td class="td_title">类别</td>
				<td colspan="3" class="chkGroup" validate="required">
				<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.feeType1" cssClass="group"></s:checkbox><span>员工活动</span></td>
					<td><s:checkbox name="templateBean.feeType2" cssClass="group"></s:checkbox><span>工装</span></td>
					<td><s:checkbox name="templateBean.feeType3" cssClass="group"></s:checkbox><span>非法定类保险</span></td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.feeType4" cssClass="group"></s:checkbox><span>体检</span></td>
					<td colspan="2"><s:checkbox name="templateBean.feeType5" id="feeType5" cssClass="group" onchange="checkOhter();"></s:checkbox><span>其他</span>
					<input class="inputBorder" type="text" id="otherInfo" name="templateBean.other" style="width:100px;" value="${templateBean.other}"/>
					</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">提审事项</td>
				<td><r:resInput name="bringItem" value="${templateBean.bringItem}"/></td>
				<td class="td_title">预计费用(元)</td>
				<td><r:resNumber name="estimatedCost" value="${templateBean.estimatedCost}"/></td>
			</tr>
			<tr>
				<td class="td_title">相关说明(附件)</td>
       			<td colspan="3">
       			<r:resTextArea name="contentTextArea" value="${templateBean.contentTextArea}"></r:resTextArea>
       			</td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function checkOhter(){
	var checkOther=$("#feeType5").attr("checked");
	if (checkOther){
		$('#otherInfo').attr("validate", "required").addClass('required');
	}else{
		$("#otherInfo").removeAttr("validate").removeClass('required');
	}
}
</script>
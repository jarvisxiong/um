<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%--有偿服务收费标准审批表	--%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="80px"/>
			<col/>
			<col width="80px"/>
			<col/>
			<tr>
				<td class="td_title">标题</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.titleName" value="${templateBean.titleName}"/></td>
			</tr>
			<tr>
				<td class="td_title">公司名称</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}"/></td>
			</tr>
			<tr>
				<td class="td_title">类别</td>
				<td colspan="3" class="chkGroup" validate="required">
				<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.feeType1" cssClass="group"></s:checkbox><span>物业服务</span></td>
					<td><s:checkbox name="templateBean.feeType2" cssClass="group"></s:checkbox><span>商户服务</span></td>
					<td><s:checkbox name="templateBean.feeType3" id="feeType3" cssClass="group" onchange="checkOhter();"></s:checkbox><span>其他</span>
					<input class="inputBorder" type="text" id="otherInfo" name="templateBean.other" style="width:100px;" value="${templateBean.other}"/>
					</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">服务项目</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.serviceItem" value="${templateBean.serviceItem}"/></td>
				<td class="td_title">收费标准</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.feeStandard" value="${templateBean.feeStandard}"/></td>
			</tr>
			<tr>
				<td  class="td_title">相关说明</td>
       			<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark" style="height:200px;">${templateBean.remark}</textarea></td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function checkOhter(){
	var checkOther=$("#feeType3").attr("checked");
	if (checkOther){
		$('#otherInfo').attr("validate", "required").addClass('required');
	}else{
		$("#otherInfo").removeAttr("validate").removeClass('required');
	}
}
</script>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%--商业公司奖金申报表--%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px"/>
			<col/>
			<tr>
				<td class="td_title">标题</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.titleName" value="${templateBean.titleName}"/></td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/></td>
			</tr>
			<tr>
				<td class="td_title">类别</td>
				<td class="chkGroup" validate="required">
				<table class="tb_checkbox">
					<col width="100">
					<col width="100">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.feeType1" cssClass="group"></s:checkbox><span>招商奖金</span></td>
					<td><s:checkbox name="templateBean.feeType2" cssClass="group"></s:checkbox><span>开业奖</span></td>
					<td><s:checkbox name="templateBean.feeType3" cssClass="group"></s:checkbox><span>目标责任书完成奖</span></td>
					</tr>
					<tr>
					<td colspan="3"><s:checkbox name="templateBean.feeType4" id="feeType4" cssClass="group" onchange="checkOhter();"></s:checkbox><span>其他</span>
					<input class="inputBorder" type="text" id="otherInfo" name="templateBean.other" style="width:100px;" value="${templateBean.other}"/>
					</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">奖金总额(元)</td>
				<td><input class="inputBorder" validate="required" onblur="formatVal($(this));" type="text" name="templateBean.totalFee" value="${templateBean.totalFee}"/></td>
			</tr>
			<tr>
				<td  class="td_title">发放理由及说明(附件)</td>
       			<td><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark" style="height:200px;">${templateBean.remark}</textarea></td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function checkOhter(){
	var checkOther=$("#feeType4").attr("checked");
	if (checkOther){
		$('#otherInfo').attr("validate", "required").addClass('required');
	}else{
		$("#otherInfo").removeAttr("validate").removeClass('required');
	}
}
</script>
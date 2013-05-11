<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<%--财务双签人变动审批表	--%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="50px"/>
			<col width="80px"/>
			<col/>
			<tr>
				<td colspan="2" class="td_title">公司名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}"/></td>
			</tr>
			<tr>
				<td colspan="2" class="td_title">变动原因说明<br/><span style="font-size: 12px;">(可带附件)</span></td>
				<td >
				<table class="tb_noborder">
				<tr><td class="td_title_left">1、第一双签人（法定双签人）变动原因（附任命通知）</td></tr>
				<tr><td><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason1" style="height:200px;">${templateBean.reason1}</textarea></td></tr>
				<tr><td class="td_title_left">2、第二双签人（指定双签人）变动理由（简明扼要）</td></tr>
				<tr><td><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason2" style="height:200px;">${templateBean.reason2}</textarea></td></tr>
				</table>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2" >变动前</td>
				<td class="td_title" >第一双签人</td>
       			<td><input class="inputBorder" validate="required" type="text" name="templateBean.oriSigner1" value="${templateBean.oriSigner1}"/></td>
			</tr>
			<tr>
				<td class="td_title" >第二双签人</td>
       			<td><input class="inputBorder" validate="required" type="text" name="templateBean.oriSigner2" value="${templateBean.oriSigner2}"/></td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2" >变动后</td>
				<td class="td_title" >第一双签人</td>
       			<td><input class="inputBorder" validate="required" type="text" name="templateBean.newSigner1" value="${templateBean.newSigner1}"/></td>
			</tr>
			<tr>
				<td class="td_title" >第二双签人</td>
       			<td><input class="inputBorder" validate="required" type="text" name="templateBean.newSigner2" value="${templateBean.newSigner2}"/></td>
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
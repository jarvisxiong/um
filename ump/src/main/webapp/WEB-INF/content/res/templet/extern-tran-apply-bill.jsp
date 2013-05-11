<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--赴外培训申请表-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	<s:set var="canEdit">
			<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
			true
			</s:if>
			<s:else>
			false
			</s:else>
	</s:set>
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col style="width:100px;">
			<col style="width:100px;">
			<col>
			<col style="width:100px;">
			<col>
			<tr>
				<td class="td_title"> 申请中心</td>
				<td colspan="2">
					<s:if test="resApproveInfoId==null">
					<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
					<s:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
					</s:if>
					<s:else>
					<s:set var="sendCenterName">${templateBean.centerOrgName}</s:set>
					<s:set var="sendCenterCd">${templateBean.centerOrgCd}</s:set>
					</s:else>
					<input validate="required" type="text" name="templateBean.centerOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.centerOrgCd" value="${sendCenterCd}"  />
				</td>
				<td class="td_title"> 培训对象</td>
				<td><input validate="required" class="inputBorder" type="text" name="templateBean.tranObjectName" value="${templateBean.tranObjectName}"/></td>
			</tr>
			<tr>
				<td class="td_title"> 培训课程</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" name="templateBean.tranClassName" value="${templateBean.tranClassName}"/>
				</td>
				<td class="td_title"> 培训日期</td>
				<td><input validate="required" class="inputBorder Wdate" onfocus="WdatePicker()" name="templateBean.tranDate" value="${templateBean.tranDate}"/></td>
			</tr>
			<tr>
				<td class="td_title"> 培训地点</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" name="templateBean.tranAddr" value="${templateBean.tranAddr}"/></td>
				<td class="td_title"> 培训机构</td>
				<td><input validate="required" class="inputBorder" type="text" name="templateBean.tranOrgName" value="${templateBean.tranOrgName}"/></td>
			</tr>
			<tr>
				<td class="td_title" rowspan="6" style="font-weight:bolder;"> 培训预算</td>
			</tr>
			<tr>
				<td class="td_title"> 1.培训费</td>
				<td colspan="3"><input validate="required" class="inputBorder" type="text" id="tranCostAmt" name="templateBean.tranCostAmt" onblur="calculateTotal();" value="${templateBean.tranCostAmt}"/></td>
			</tr>
			<tr>
				<td class="td_title"> 2.交通费</td>
				<td colspan="3"><input class="inputBorder" type="text" id="vehicleCostAmt" name="templateBean.vehicleCostAmt" onblur="calculateTotal();" value="${templateBean.vehicleCostAmt}"/></td>
			</tr>
			<tr>
				<td class="td_title"> 3.住宿费</td>
				<td colspan="3"><input class="inputBorder" type="text" id="settleCostAmt" name="templateBean.settleCostAmt" onblur="calculateTotal();" value="${templateBean.settleCostAmt}"/></td>
			</tr>
			<tr>
				<td class="td_title"> 4.其他费</td>
				<td colspan="3"><input class="inputBorder" type="text" id="otherCostAmt" name="templateBean.otherCostAmt" onblur="calculateTotal();" value="${templateBean.otherCostAmt}"/></td>
			</tr>
			<tr>
				<td class="td_title" style="font-weight: bolder;"> 总计费用</td>
				<td colspan="3"><input validate="required" class="inputBorder" readonly="readonly" type="text" id="totoalCostAmt" name="templateBean.totoalCostAmt" onblur="calculateTotal();" value="${templateBean.totoalCostAmt}"/></td>
			</tr>
			<tr>
				<td class="td_title">申请培训原因</td>
				<td colspan="4"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reasonDesc">${templateBean.reasonDesc}</textarea></td>
			</tr>
			<tr>
				<td class="td_title">附件上传</td>
						<td align="center" style="height:40px;" validate="required" value="${templateBean.attachmentId}">“邀请函”、“课程内容介绍”等资料</td>
						<td align="center">
						<s:if test="#canEdit=='true'">
						<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('请上传“邀请函”、“课程内容介绍”等资料','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','attachmentId',event);"/>
						<input type="hidden" name="templateBean.attachmentId" id="attachmentId" value="${templateBean.attachmentId}"/>
						</s:if>
						</td>
						<td colspan="2">
						<div id="show_attachmentId"></div>
						<script type="text/javascript">
						showUploadedFile('${templateBean.attachmentId}','attachmentId','${canEdit}');
						</script>
						</td>
					</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<script type="text/javascript">
	function calculateTotal(){
		
		formatVal($('#tranBudgetAmt'));
		formatVal($('#tranCostAmt'));
		formatVal($('#vehicleCostAmt'));
		formatVal($('#settleCostAmt'));
		formatVal($('#otherCostAmt'));
		formatVal($('#totoalCostAmt'));
		

		var total = getVal('tranBudgetAmt')+getVal('tranCostAmt')+getVal('vehicleCostAmt')+getVal('settleCostAmt')+getVal('otherCostAmt');
		$('#totoalCostAmt').val(formatMoney(total));
	}

</script>

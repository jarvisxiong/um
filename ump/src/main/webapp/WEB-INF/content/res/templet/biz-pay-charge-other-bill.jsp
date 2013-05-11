<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--商业公司其它费用审批表 -->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
</s:set>
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px"/>
			<col />
			<col width="120px"/>
			<col />
			<tr>
				<td class="td_title"></td>
				<td align="left" colspan="3" validate="required"  class="chkGroup" style="text-align:left;">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外</td>
						<td><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内</td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">申请中心</td>
				<td colspan="3">
					<s:if test="resApproveInfoId==null">
					<s:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </s:set>
					<s:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
					</s:if>
					<s:else>
					<s:set var="sendCenterName">${templateBean.sendOrgName}</s:set>
					<s:set var="sendCenterCd">${templateBean.sendOrgCd}</s:set>
					</s:else>
					
					<input validate="required" type="text" name="templateBean.sendOrgName" value="${sendCenterName}" id="centerName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="centerCd" name="templateBean.sendOrgCd" value="${sendCenterCd}"  />
				</td>
			</tr>
			
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
					<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractNo}');">${templateBean.contractNo}</span>
						<input id="contractNo" type="hidden" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" id="contractNo" type="text" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:else>
				</td>
				<td class="td_title">合同名称</td>
				<td>
					<input class="inputBorder" validate="required" readonly="readonly" id="contactNameId" type="text" name="templateBean.contactName" value="${templateBean.contactName}" />
				</td>
			</tr>
			
			<tr>
				<td style="border-top:0 none;" class="td_title" rowspan="4">收款人（乙方）信息</td>
				<td style="border-top:0 none;" class="td_title">收款人名称</td>
				<td style="border-top: none;" colspan="2">
					<input class="inputBorder" readonly="readonly" type="text" id="receName" name="templateBean.receName" value="${templateBean.receName}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">收款人开户行</td>
				<td style="border-top: none;" colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.receOpenBankName" value="${templateBean.receOpenBankName}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">收款人账号</td>
				<td style="border-top: none;" colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.receAcctNo" value="${templateBean.receAcctNo}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">付款方式</td>
				<td style="border-top: none;" colspan="2">
					<input class="inputBorder" readonly="readonly" type="text" id="payTypeDesc" name="templateBean.payTypeDesc" value="${templateBean.payTypeDesc}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">合同总价（元）</td>
				<td style="border-top:0 none;">
					<input class="inputBorder" readonly="readonly" type="text" id="contractTotalAmt" name="templateBean.contractTotalAmt" value="${templateBean.contractTotalAmt}"  onblur="formatVal($(this));" />
				</td>
				<td style="border-top:0 none;" class="td_title">已确认合同总价（元）</td>
				<td style="border-top:0 none;">
					<input class="inputBorder" readonly="readonly" type="text" id="confirmTotalAmt" name="templateBean.confirmTotalAmt" value="${templateBean.confirmTotalAmt}"  onblur="formatVal($(this));" />
				</td> 
			</tr>  
			<tr>
				<td class="td_title">已付合同款(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="contractPaiedAmt" name="templateBean.contractPaiedAmt" value="${templateBean.contractPaiedAmt}"/></td>
			</tr>
			<tr>
				<td class="td_title">本次支付金额(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.curPaymentAmt" value="${templateBean.curPaymentAmt}"/></td>
				<td class="td_title">本次付款时间</td>
				<td><input class="inputBorder Wdate" validate="required" type="text" name="templateBean.currentPayDate" value="${templateBean.currentPayDate}"   onfocus="WdatePicker()"/></td>
			</tr> 			
			 			
			<tr>
				<td class="td_title">收款单位</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.orgName" value="${templateBean.orgName}"  /></td>
			</tr>
			<tr>
				<td class="td_title">需说明事项</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getContByno(contNo){
	var data = {contNo:contNo};
    var getContUrl="${ctx}/cont/cont-ledger!detail.action";
	var url;
	$.post(getContUrl,data, function(result){
		$("#contLedgerId").val(result);
		url="${ctx}/cont/cont-ledger!input.action?id="+$("#contLedgerId").val();
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
	});
	
}

var mapPrama={
		contNo:'contractNo',
		contName:'contactNameId',
		partB:'receName',
		payWay:'payTypeDesc',
		totalPrice:'contractTotalAmt',
		updateTotal:'confirmTotalAmt'
	};
$("#contractNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{codeType:'2'});



</script>

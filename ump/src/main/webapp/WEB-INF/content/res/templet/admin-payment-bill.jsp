<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--行政费用付款审批表 -->
<%@ include file="template-header.jsp"%>
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px"/>
			<col />
			<col width="120px"/>
			<col />
			<tr>
				<td class="td_title">申请中心</td>
				<td colspan="2">
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
				<td style="text-align:left;">
					<s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外
					<s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内
				</td>
			</tr>
			<tr>
				<td class="td_title">收款单位</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.orgName" value="${templateBean.orgName}"  /></td>
				<td class="td_title">合同编号</td>
				<td>
					<input class="inputBorder" type="text" validate="required" id="contractNo"  name="templateBean.contractNo" value="${templateBean.contractNo}"  />
					<input class="inputBorder" type="hidden" id="contactNameId"/>
					
					
				</td>
			</tr>
			<tr>
				<td class="td_title">本次支付金额(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.curPaymentAmt" value="${templateBean.curPaymentAmt}"/></td>
				<td class="td_title">本次付款时间</td>
				<td><input class="inputBorder Wdate" validate="required" type="text" name="templateBean.currentPayDate" value="${templateBean.currentPayDate}"   onfocus="WdatePicker()"/></td>
			</tr> 			
			<tr>
				<td class="td_title">合同总金额(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="contractTotalAmt" name="templateBean.contractTotalAmt" value="${templateBean.contractTotalAmt}"/></td>
				<td class="td_title">已付合同款(元)</td>
				<td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" id="contractPaiedAmt" name="templateBean.contractPaiedAmt" value="${templateBean.contractPaiedAmt}"/></td>
			</tr> 			
			<tr>
				<td class="td_title">需说明事项</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea></td>
			</tr>
			<s:if test="authTypeCd=='JD_CWGL_80' || authTypeCd=='JD_CWGL_90'">
			<tr>
				<td class="td_title"  rowspan="1">附件上传</td>
				<td style="height:40px;"  validate="required" value="${templateBean.payDependenceId}">付款依据</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('付款依据','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','payDependenceId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.payDependenceId" id="payDependenceId" value="${templateBean.payDependenceId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_payDependenceId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.payDependenceId}','payDependenceId','${canEdit}');
				</script>
				</td>
			</tr>
			</s:if>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<s:if test="authTypeCd=='BLSY_CWGL_FKGL_125'">
<script type="text/javascript">
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id+"&ledgerType=1";
	var showName;
	if("1"==ledgerType){
		showName="合同台账查看/新增";
	}
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input",showName,url,true);
}
var mapPrama={
		contNo:'contractNo',
		contName:'contactNameId',
		totalPrice:'contractTotalAmt'
	};
  $("#contractTotalAmt").removeAttr("validate");
  $("#contractTotalAmt").attr("readonly","readonly");
	$("#contractNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{codeType:'2'});
</script>
</s:if>
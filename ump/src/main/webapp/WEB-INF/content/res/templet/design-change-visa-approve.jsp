<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--设计变更签证审批表 --%>

<div class="billContent" align="center">
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
		<table class="mainTable">
			<col width="90">
			<col>
			<col width="70">
			<col>
			<tr>
				<td class="td_title">工程名称</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">施工单位</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.constructionCompany" value="${templateBean.constructionCompany}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">设计变更单号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.changeSN" value="${templateBean.changeSN}"/>
				</td>
				<td class="td_title">工程代号</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.engineeringSN" value="${templateBean.engineeringSN}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">拟签证号</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.visaNumber" value="${templateBean.visaNumber}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">工程地点</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.engineeringPlace" value="${templateBean.engineeringPlace}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
					
					<input class="inputBorder" type="hidden" id="contactNameId"/>
					<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractSn.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractSn}');">${templateBean.contractSn}</span>
						<input id="contractNoId" type="hidden" name="templateBean.contractSn" value="${templateBean.contractSn}" />
					</s:if>
					<s:else>
					<input validate="required" class="inputBorder" type="text" id="contractNoId" name="templateBean.contractSn" value="${templateBean.contractSn}"/>
					</s:else>
				</td>
				<td class="td_title">合同金额(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.contractMoney" value="${templateBean.contractMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">签证占合同<br/>金额比例</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.visaRate" value="${templateBean.visaRate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">现场签证<br/>内容及原因</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentAndReason" rows="6" cols="30">${templateBean.contentAndReason}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">增加或减少的<br/>费用(元)</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.changeMoney" value="${templateBean.changeMoney}"/>
				</td>
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
	$("#contractNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:"contractNoId",contName:"contactNameId"});
</script>

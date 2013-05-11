<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--工程变更签证审批表-->
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
			<col width="120"/>
			<col/>
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">工程名称</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/></td>
			</tr>
			<tr>
			  <td class="td_title">施工单位</td>
				<td  colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.builder" value="${templateBean.builder}"/></td>
			</tr>
			<tr>
		   	<td class="td_title">工程地点</td>
			<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.projectAddress" value="${templateBean.projectAddress}"/></td>
			
			</tr>
			<tr>
				<td class="td_title">工程代号</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.projectNo" value="${templateBean.projectNo}"/></td>
				<td class="td_title">拟签证号</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.signCard" value="${templateBean.signCard}"/></td>
				</tr>
			<tr>
			   <td class="td_title">合同编号</td>
			   <td align="left">
			   
			   <input class="inputBorder" type="hidden" id="contactNameId"/>
			   
			   <input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractNo}');">${templateBean.contractNo}</span>
						<input id="contractNoId" type="hidden" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" id="contractNoId" name="templateBean.contractNo" value="${templateBean.contractNo}"/>
					</s:else>
			   </td>
			   <td class="td_title">合同金额(元)</td>
			   <td><input class="inputBorder" validate="required" type="text"onblur="formatVal($(this));"  name="templateBean.contractAmount" value="${templateBean.contractAmount}"/></td>
			</tr>
			<tr>
			   <td class="td_title">预计增加<br/>或减少的费用(元)</td>
			   <td colspan="3"><input class="inputBorder" validate="required" type="text"onblur="formatVal($(this));"  name="templateBean.addOrMinusFee" value="${templateBean.addOrMinusFee}"/></td>
			</tr>
			<tr>
			<td class="td_title">现场签证内容及原因</td>
			<td  colspan="3">
			<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.updateMoney">${templateBean.updateMoney}</textarea></td>
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
	$("#contractNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",["contNo","contName"],{contNo:"contactNoId",contName:"contactNameId"});
</script>


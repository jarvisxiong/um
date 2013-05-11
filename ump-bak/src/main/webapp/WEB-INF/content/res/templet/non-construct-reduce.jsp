<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--另行委托/未施工扣减审批表-->
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
			<col width="100px">
			<col width="80px">
			<col>
			<col width="100px">
			<col width="140px">
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="4">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">项目地点</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" name="templateBean.projectPlace" value="${templateBean.projectPlace}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">工程名称</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">扣减单位名称</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" name="templateBean.reduceCompany" value="${templateBean.reduceCompany}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">扣减合同编号</td>
				<td colspan="2" align="left">
					
					<input class="inputBorder" type="hidden" id="reduceContractNameId" />
					
					<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.reduceContractSN.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.reduceContractSN}');">${templateBean.reduceContractSN}</span>
						<input id="reduceContractSNId" type="hidden" name="templateBean.reduceContractSN" value="${templateBean.reduceContractSN}" />
					</s:if>
					<s:else>
					<input validate="required" class="inputBorder" type="text" id="reduceContractSNId" name="templateBean.reduceContractSN" value="${templateBean.reduceContractSN}"/>
					</s:else>
				</td>
				<td class="td_title">扣减费用(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.reduceMoney" value="${templateBean.reduceMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">另行委托单位</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" name="templateBean.otherTrustCompany" value="${templateBean.otherTrustCompany}"/>
				</td>
			</tr>
			<tr>
				
				<td class="td_title">另行委托单位合同编号</td>
				<td colspan="2" align="left">
					
					<input class="inputBorder" type="hidden" id="otherTrustNameId" />
					
					<s:if test="!templateBean.otherTrustSN.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.otherTrustSN}');">${templateBean.otherTrustSN}</span>
						<input id="otherTrustSNId" type="hidden" name="templateBean.otherTrustSN" value="${templateBean.otherTrustSN}" />
					</s:if>
					<s:else>
					<input validate="required" class="inputBorder" type="text" id="otherTrustSNId" name="templateBean.otherTrustSN" value="${templateBean.otherTrustSN}"/>
					</s:else>
				</td>
				<td class="td_title">费用支出(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));"  name="templateBean.costMoney" value="${templateBean.costMoney}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">扣减内容、原因</td>
				<td colspan="4">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reduceReason" rows="6" cols="30">${templateBean.reduceReason}</textarea>
					<div style="float:left;">注：附图纸，编号<input validate="required" class="inputBorder" style="width:300px;" type="text" name="templateBean.drawingSN" value="${templateBean.drawingSN}"/></div>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2">核价编制说明</td>
				<td align="right">1、核价条款</td>
				<td colspan="3" align="left">
					变更合同价款按合同 <input validate="required" class="inputBorder" type="text" style="width:30px;" name="templateBean.clauseNumber" value="${templateBean.clauseNumber}"/>条款
					 第<input validate="required" class="inputBorder" type="text" style="width:30px;" name="templateBean.mothodNumber" value="${templateBean.mothodNumber}"/>种方法
					 <br/>
					 其中工程量按<input validate="required" class="inputBorder" type="text" style="width:30px;" name="templateBean.calculate" value="${templateBean.calculate}"/> 计量
					 <br/>
					 单价按<input validate="required" class="inputBorder" type="text" style="width:30px;" name="templateBean.calculatePrice" value="${templateBean.calculatePrice}"/>确定
					<br/>
				</td>
			</tr>
			<tr>
				<td align="right">2、说明</td>
				<td colspan="3" align="left">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.instruction" rows="6" cols="30">${templateBean.instruction}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">变更内容及原因</td>
				<td colspan="4">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentAndReason" rows="6" cols="30">${templateBean.contentAndReason}</textarea>
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
	$("#reduceContractSNId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:"reduceContractSNId",contName:"reduceContractNameId"});
	$("#otherTrustSNId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:"otherTrustSNId",contName:"otherTrustNameId"});
</script>
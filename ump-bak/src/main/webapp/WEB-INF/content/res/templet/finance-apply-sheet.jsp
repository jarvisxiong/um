<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 资金申请表 --%>
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
<%--财务管理中心资金部 --%>
<s:if test="(nodeCd == 283) && isMyApprove==1">
	<s:hidden id="isEdit" value="true"/>
	<s:set var="isEdit" value="true" />
</s:if> 
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120"/>
			<col/>
			<col width="120"/>
			<col/>
			<s:if test="authTypeCd=='FSS_CW_FK_60'">
			<tr>
			<td class="td_title">
			</td>
			<td colspan="3" class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="150">
				<col>
				<tr>
				<td><s:checkbox name="templateBean.type1" cssClass="group"></s:checkbox>全资公司</td>
				<td><s:checkbox name="templateBean.type2" cssClass="group"></s:checkbox>合资公司(宝龙拍卖、聚龙轩)</td>
				</tr>
			</table>
			</td>
			</tr>
			</s:if>
			<s:if test="authTypeCd=='FKGL_250'">
			<tr>
				<td class="td_title">宝龙集团资金申请表</td>
				<td colspan="3" align="left">
					<input type="hidden" name="templateBean.resApproveId" id="resApproveId" value="${templateBean.resApproveId}"/>
					<s:if test="!templateBean.resApproveId.isEmpty() && #canEdit == 'false'">
						<span class="link"  onclick="openSheet('${templateBean.resApproveId}');">${templateBean.resApproveCd}</span>
						<input class="inputBorder" name="templateBean.resApproveCd"  type="hidden" id="resApproveCd" value="${templateBean.resApproveCd}"/>
					</s:if>
					<s:else>
						<input class="inputBorder" name="templateBean.resApproveCd"  type="text" id="resApproveCd" value="${templateBean.resApproveCd}"/>
					</s:else>
				</td>
			</tr>
			</s:if>
			<tr>
				<td class="td_title">申请单位</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.unit" value="${templateBean.unit}" />
				</td>
				<td class="td_title">开户行账户信息</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.openAccount" value="${templateBean.openAccount}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">资金用途</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.usage" value="${templateBean.usage}" />
				</td>
				<td class="td_title">列支科目</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.subject" value="${templateBean.subject}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">资金数额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.amount" value="${templateBean.amount}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">用款时间</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" validate="required" type="text" name="templateBean.useDate" value="${templateBean.useDate}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">申请事由</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason">${templateBean.reason}</textarea>
				</td>
			</tr>
			<s:if test="(statusCd!=0&&statusCd!=3)">
			<tr>
				<td class="td_title">调拨路径描述</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" <s:if test="#isEdit==true"> edit="true" validate="required" </s:if> name="templateBean.remark">${templateBean.remark}</textarea>
				</td>
			</tr>
			</s:if>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
var mapPrama={
		approveCd:'resApproveCd',
		id:'resApproveId'
	};
	$("#resApproveCd").quickSearch("${ctx}/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama,{authTypeCd:'HY_CWGL_70',statusCd:'2'});
	function openSheet(id){
		var url="${ctx}/res/res-approve-info!detail.action?id="+id;
		window.open(url);
	}
</script>
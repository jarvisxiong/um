<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 商业招待费付款(eg: 宝龙商业招待费付款审批表) --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px"/>
			<col />
			<col width="100px"/>
			<col />
			<tr>
				<td style="border-top:0 none;" class="td_title"></td>
				<td style="border-top: none;" colspan="3"  validate="required" class="chkGroup">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>月度预算内</td>
						<td><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>月度预算外</td>
					</tr>
					</table>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">
				<s:if test="authTypeCd=='FSS_CW_FK_40'">下属公司</s:if>
				<s:else>商业公司</s:else>
				</td>
				<td style="border-top: none;">
					<input id="companyName" validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.companyCd" value="${templateBean.companyCd}" />
				</td>
				<td style="border-top:0 none;" class="td_title">申请人</td>
				<td style="border-top: none;">
					<input id="applyName" validate="required" type="text" name="templateBean.applyName" value="${templateBean.applyName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip singleSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.applyCd" value="${templateBean.applyCd}" />
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">招待对象</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.entertainObjectDesc" value="${templateBean.entertainObjectDesc}" />
				</td>
				<td style="border-top:0 none;" class="td_title">招待地点</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.entertainPlaceDesc" value="${templateBean.entertainPlaceDesc}"/>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">本次支付金额(元)</td>
				<td style="border-top:0 none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.payAmt" value="${templateBean.payAmt}"  onblur="formatVal($(this));" />
				</td>
				<td style="border-top:0 none;" class="td_title">本次付款时间</td>
				<td style="border-top: none;">
					<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" name="templateBean.payDate" value="${templateBean.payDate}" />
				</td>
			</tr>  
			<tr>
				<td style="border-top:0 none;" class="td_title">付款原由</td>
				<td style="border-top:0 none;" colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.payReasonDesc">${templateBean.payReasonDesc}</textarea>
				</td>
			</tr>   
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

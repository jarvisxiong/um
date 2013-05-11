<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
		商业工程改造审批单
 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	
	<!-- 
	
	注：1、此项工作必须上会决策，请在上会前三天，将上会资料发至总裁办企管部陈美馨处
	 -->
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120px"/>
			<col />
			<col width="120px"/>
			<col />
			<s:if test="authTypeCd!='SYGS_WYGC_GCGZ_30'">
			<tr>
				<td style="border-top:0 none;" class="td_title"></td>
				<td colspan="3" validate="required"  class="chkGroup">
					<table class="tb_checkbox">
					<col>
					<s:if test="authTypeCd!='SYGS_WYGC_GCGZ_20'">
					<tr>
						<td><s:checkbox name="templateBean.checkCd1" cssClass="group"></s:checkbox>零星改造（单项金额≤5000元，月度总额≤2万元）</td>
					</tr>
					</s:if>
					<tr>
						<td><s:checkbox name="templateBean.checkCd2" cssClass="group"></s:checkbox>涉及使用功能性改造或其他方面改造</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.checkCd3" cssClass="group"></s:checkbox>涉及外观、效果改造</td>
					</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<s:if test="authTypeCd=='SYGS_WYGC_GCGZ_20'">
			<tr>
				<td class="td_title"></td>
				<td colspan="3" class="chkGroup" align="left">
					<table class="tb_checkbox">
					<col width="150">
					<tr>
						<td><s:checkbox name="templateBean.hasLandCompany" id="inFlag" cssClass="group"></s:checkbox>有地产公司</td>
					</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<tr>
				<td style="border-top:0 none;" class="td_title">项目名称</td>
				<td style="border-top: none;" colspan="3">
					<input id="projectName" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">工程名称</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.programName" value="${templateBean.programName}" />
					<input type="hidden" name="templateBean.programCd" value="${templateBean.programCd}" />
				</td>
				<td style="border-top:0 none;" class="td_title">预估工程费用（元）</td>
				<td style="border-top: none;">
					<input class="inputBorder" validate="required" type="text" name="templateBean.planFeeAmt" value="${templateBean.planFeeAmt}" onblur="formatVal($(this));"/>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">工程改造范围</td>
				<td style="border-top: none;" colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.areaDesc">${templateBean.areaDesc}</textarea>
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">内容简述<br/>（详细内容附后）</td>
				<td style="border-top: none;" colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reasonDesc">${templateBean.reasonDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>



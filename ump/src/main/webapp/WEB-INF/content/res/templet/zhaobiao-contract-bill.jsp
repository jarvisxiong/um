<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--招标合同-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80">
			<col width="90">
			<col>
			<col width="60">
			<col>
			<tr>
				<td style="padding-left:5px;font-size: 14px;line-height: 26px;height: 26px;font-weight: bolder;">标题</td>
				<td colspan="4">
				<table class="tb_noborder">
					<col>
					<col width="40">
					<col>
					<col width="40">
					<tr>
					<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
					</td>
					<td>项目</td>
					<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.contactName" size="100"  value="${templateBean.contactName}"/>
					</td>
					<td>合同</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td rowspan="3" style="padding-left:5px;">合同双方</td>
				<td  class="td_title">甲方</td>
				<td><input  class="inputBorder" type="text" name="templateBean.sideA" value="${templateBean.sideA}"/></td>
				<td  class="td_title">签约人</td>
				<td><input class="inputBorder"  type="text" name="templateBean.signerA" value="${templateBean.signerA}"  /></td>
			</tr>	
			<tr>
				<td  class="td_title" style="padding-left:5px;">乙方</td>
				<td><input  class="inputBorder" type="text" name="templateBean.sideB" value="${templateBean.sideB}"  /></td>
				<td  class="td_title">签约人</td>
				<td><input class="inputBorder"  type="text" name="templateBean.signerB" value="${templateBean.signerB}"/></td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td  class="td_title" style="padding-left:5px;">丙方</td>
				<td><input  class="inputBorder" type="text" name="templateBean.sideC" value="${templateBean.sideC}"  /></td>
				<td  class="td_title">签约人</td>
				<td><input class="inputBorder"  type="text" name="templateBean.signerC" value="${templateBean.signerC}"/></td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td style="padding-left:5px;">选择合作<br/>单位方式</td>
				<td colspan="4" class="chkGroup">
					<table class="tb_noborder">
					<col width="110">
					<col>
					<tr>
					<td align="left"><s:checkbox name="templateBean.isInvite" cssClass="group" ></s:checkbox>招标(标书编号)</td>
					<td><input class="inputBorder" type="text" name="templateBean.inviteNo" value="${templateBean.inviteNo}"  /></td>
					</tr>
					<tr>
					<td align="left"><s:checkbox name="templateBean.isDirect" cssClass="group" ></s:checkbox>直接委托(理由)</td>
					<td><input class="inputBorder"  type="text" name="templateBean.directReason" value="${templateBean.directReason}"  /></td>
					</tr>
					<tr>
					<td align="left"><s:checkbox name="templateBean.isCompetitive" cssClass="group" ></s:checkbox>竞争性谈判</td>
					<td></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td rowspan="5" style="padding-left:5px;">合同主要内容</td>
				<td  class="td_title">标段</td>
				<td colspan="3"><input class="inputBorder"  type="text" name="templateBean.section" value="${templateBean.section}"  /></td>
			</tr>
			<tr>
				<td  class="td_title" style="padding-left:5px;">工期</td>
				<td colspan="3"><input class="inputBorder"  type="text" name="templateBean.timeLimit" value="${templateBean.timeLimit}"  /></td>
			</tr>
			<tr>
				<td  class="td_title" style="padding-left:5px;">范围/数量</td>
				<td colspan="3"><input class="inputBorder"  type="text" name="templateBean.scope" value="${templateBean.scope}"  /></td>
			</tr>
			<tr>
				<td  class="td_title" style="padding-left:5px;">预估合同价款(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.conPrice" value="${templateBean.conPrice}"  /></td>
			</tr>
			<tr>
				<td  class="td_title" style="padding-left:5px;">付款方式</td>
				<td colspan="3"><input class="inputBorder"  type="text" name="templateBean.conPayMode" value="${templateBean.conPayMode}"  /></td>
			</tr>
			<tr>
				<td style="padding-left:5px;">备注</td>
				<td colspan="4"><textarea class="inputBorder contentTextArea" name="templateBean.conContent">${templateBean.conContent}</textarea></td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

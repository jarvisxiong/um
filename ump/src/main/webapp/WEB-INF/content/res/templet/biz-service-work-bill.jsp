<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	商业公司营运工作审批表(eg: 商业公司营运工作审批表) 
 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="80px">
			<col >
			<tr>
				<td style="border-top:0 none;" class="td_title">项目名称</td>
				<td style="border-top: none;">
					<input id="projectName" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">审批内容</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required">
				<table class="tb_checkbox">
					<col width="100">
					<col width="130">
					<col width="130">
					<col width="100">
					<tr>
						<td><s:checkbox name="templateBean.typeCd1"  cssClass="group"></s:checkbox>进退场</td>
						<td><s:checkbox name="templateBean.typeCd2"  cssClass="group"></s:checkbox>停开业</td>
						<td><s:checkbox name="templateBean.typeCd3"  cssClass="group"></s:checkbox>装修</td>
						<td><s:checkbox name="templateBean.typeCd4"  cssClass="group"></s:checkbox>验收</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.typeCd5"  cssClass="group"></s:checkbox>商户营业时间</td>
						<td><s:checkbox name="templateBean.typeCd6"  cssClass="group"></s:checkbox>商户营业人员聘用</td>
						<td><s:checkbox name="templateBean.typeCd7"  cssClass="group"></s:checkbox>商户货品价格</td>
						<td></td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.typeCd8"  cssClass="group"></s:checkbox>商户进出货</td>
						<td><s:checkbox name="templateBean.typeCd9"  cssClass="group"></s:checkbox>商户经营规范责任书</td>
						<td><s:checkbox name="templateBean.typeCd10"  cssClass="group"></s:checkbox>其他</td>
						<td></td>
					</tr>
				</table>
				</td> 
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">内容简述（详细内容附后）</td>
				<td style="border-top: none;">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc">${templateBean.contentDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!-- 证照办理需求单 -->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="90px">
			<col>
			<col width="90px">
			<col width="100">
			<tr>
				<td class="td_title">标题</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">需求提出中心</td>
				<td>
					<input validate="required" type="text" name="templateBean.reqCenterOrgName" value="${templateBean.reqCenterOrgName}" id="reqCenterOrgName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="reqCenterOrgCd" name="templateBean.reqCenterOrgCd" value="${templateBean.reqCenterOrgCd}"  />
				</td>
				<td class="td_title">申请办理日期</td>
				<td><input class="inputBorder Wdate" onfocus="WdatePicker()" type="text" name="templateBean.applyDate" value="${templateBean.applyDate}"/></td>
			</tr>
			<tr>
				<td class="td_title">类别选择</td>
				<td colspan="3" style="text-align:left;" class="chkGroup" validate="required">
					<table class="tb_checkbox">
					<tr>
					<td><s:checkbox name="templateBean.selectSetUp" cssClass="group"/>设立公司</td>
					</tr>
					<tr>
					<td><s:checkbox name="templateBean.selectRevoke" cssClass="group"/>注销公司</td>
					</tr>
					<tr><td><s:checkbox name="templateBean.selectAptitude" cssClass="group"/>资质（新办、年检、升级）</td>
					</tr>
					<tr><td><s:checkbox name="templateBean.selectBaseChange" cssClass="group"/>基本信息变更（名称、地址、法人或负责人、类型、注册资金、经营范围、其他）</td>
					</tr>
					<tr><td><s:checkbox name="templateBean.selectPositionChange" cssClass="group"/>任职变更（董事长、总经理、股东、董事、监事）</td>
					</tr>
					<tr><td><s:checkbox name="templateBean.selectProjectManual" cssClass="group"/>项目手册（新办、年检）</td>
					</tr>
					</table>
				</td>
			</tr>	
			<tr>
				<td class="td_title">
					证照办理需求说明（需列明相关会议决议、领导指示/批复等，可复印后作为附件提供）
				</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" name="templateBean.reqDesc" style="width:100%;height:200px;">${templateBean.reqDesc}</textarea>
				</td>
			</tr> 
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

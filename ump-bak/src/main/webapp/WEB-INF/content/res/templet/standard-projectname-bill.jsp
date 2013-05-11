<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--	标准审批表-项目名称	-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="100px"/>
			<col/>
			<s:if test="authTypeCd == 'CPDW_CBCP_10'||authTypeCd =='SJGL_CLSBXX_10' ">
			<tr>
				<td class="td_title">审批权限</td>
				<td align="left">
					<table class="tb_checkbox" style="width:100%;">
					    <col width="100">
					    <col width="100">
						<col width="100">
						<col width="100">
						<tr>
						<td><s:checkbox name="templateBean.estate"  cssClass="group"></s:checkbox>与商业有关</td>
						<td><s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关</td>
						</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<s:if test="authTypeCd == 'SJGL_CLSBXX_10'||authTypeCd =='SJGL_CLSBXX_20'||authTypeCd =='SJGL_CLSBXX_30'">
			<tr>
				<td class="td_title"></td>
				<td align="left">
					<table class="tb_checkbox" style="width:100%;">
						<tr>
						<td><s:checkbox name="templateBean.isOutFace"  cssClass="group"></s:checkbox>涉及外立面</td>
						</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<tr>
				<td class="td_title">标题</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
				</td>
			</tr>
			<tr>
				<td  class="td_title">项目名称</td>
				<td>
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" />
				</td>
			</tr>
			<tr>
				<td  class="td_title">内容简述<br/>(详细内容附后)</td>
				<td><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentBriefly" style="height:200px;">${templateBean.contentBriefly}</textarea></td>
			</tr>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

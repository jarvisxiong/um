<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--营销方案审批表-->
		<%@ include file="template-header.jsp"%>
<div align="center" class="billContent">
	
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="120"/>
					<col/>
					<tr>
						<td class="td_title">标题</td>
						<td>
							<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
						</td>
					</tr>
					<tr>
						<td class="td_title">项目名称</td>
						<td>
							<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
					</tr>
					<tr>
					  <td rowspan="2" class="td_title">方案类别</td>
					  <td  class="chkGroup" validate="required"  align="left">
					    <s:checkbox name="templateBean.totalMarketProgram" cssClass="group"></s:checkbox>整体营销方案
					    <s:checkbox name="templateBean.stageMarketProgram" cssClass="group"></s:checkbox>阶段营销方案
					  </td>
					</tr>
					<tr>
						<td  class="chkGroup" validate="required" align="left">
						<s:checkbox name="templateBean.subscriptionProgram" cssClass="group"></s:checkbox>认购方案
						<s:checkbox name="templateBean.openProgram" cssClass="group"></s:checkbox>开盘方案
						<s:checkbox name="templateBean.additionPush" cssClass="group"></s:checkbox>房源加推<br/>
						<s:checkbox name="templateBean.extendProgram" cssClass="group"></s:checkbox>推广方案
						<s:checkbox name="templateBean.sellPlan" cssClass="group"></s:checkbox>销售方案
						<s:checkbox name="templateBean.planProgram" cssClass="group"></s:checkbox>策划方案
						</td>
					</tr>
					<tr>
						<td class="td_title">营销内容及限额说明</td>
						<td ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.programContent">${templateBean.programContent}</textarea></td>
					</tr>
					
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>

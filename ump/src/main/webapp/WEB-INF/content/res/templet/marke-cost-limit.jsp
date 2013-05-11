<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!-- 营销费用限额审批表-->
		<%@ include file="template-header.jsp"%>
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="80"/>
					<col/>
					<col width="80"/>
					<col/>
					<tr>
						<td class="td_title">标题</td>
						<td colspan="3">
							<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
						</td>
					</tr>
					<tr>
						<td class="td_title">项目名称</td>
						<td>
							<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td class="td_title">费用限额(元)</td>
						<td>
						   <input class="inputBorder" type="text" onblur="formatVal($(this));"  id="costLimit"  name="templateBean.costLimit" value="${templateBean.costLimit}"  />
						</td>
					</tr>
					<tr>
					  <td class="td_title">费用用途</td>
					  <td colspan="3" class="chkGroup" validate="required" align="left">
					    <s:checkbox name="templateBean.extend" cssClass="group"></s:checkbox>推广
					    <s:checkbox name="templateBean.market" cssClass="group"></s:checkbox>销售
					    <s:checkbox name="templateBean.other" cssClass="group"></s:checkbox>其它
					  </td>
					</tr>
					<tr>
						<td class="td_title">营销内容及限额说明</td>
						<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.marketContent">${templateBean.marketContent}</textarea></td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>

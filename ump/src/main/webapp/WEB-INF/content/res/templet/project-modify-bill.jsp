<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--商业工程改造审批表-->
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="90"/>
					<col/>
					<col width="90"/>
					<col width="90"/>
					<tr>
						<td class="td_title">项目名称</td>
						<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.itemName" value="${templateBean.itemName}"  /></td>
					</tr>
					<tr>
					  <td class="td_title">工程名称</td>	
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/>
					  </td>
					  <td class="td_title">预估工程费用(元)</td>
					  <td><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.preProjectFee" value="${templateBean.preProjectFee}"/>
					  </td>
					</tr>
					<tr>
					 <td class="td_title">工程改造内容及原因</td>
					 <td colspan="3">
					   <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.transformCause">${templateBean.transformCause}</textarea>
					 </td>
					</tr>
					<s:if test="#isJcw">
					<tr class="noprint">
					<td class="td_title">决策委员会名单</td>
					<td colspan="3">
						<input id="receiveUserNames" type="text"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip mutiSelect" style="cursor: pointer;" </s:if><s:else> class="inputBorderNoTip" </s:else>/>
						<input type="hidden" id="receiveUserCds"/>
					</td>
					</tr>
					</s:if>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>

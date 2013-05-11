<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>
<!--工程质量巡查报告审批表-->
<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="80"/>
					<col/>
					<col width="80"/>
					<col width="100"/>
					<tr>
						<td class="td_title">项目名称</td>
						<td colspan="3">
							<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">巡查工程</td>
						<td colspan="3"><input class="inputBorder" validate="required" type="text" name="templateBean.trackProject" value="${templateBean.trackProject}"  /></td>
					</tr>
					<tr>
						<td class="td_title">施工单位</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.builder" value="${templateBean.builder}"  /></td>
						<td class="td_title">巡查日期</td>
						<td><input class="inputBorder Wdate" onfocus="WdatePicker()" class="Wdate" validate="required" type="text" name="templateBean.trackDate" value="${templateBean.trackDate}"  /></td>
					</tr>
					<tr>
						<td class="td_title">巡查主要结论(附报告)</td>
						<td  colspan="3"><textarea class="inputBorder contentTextArea" validate="required" type="text" name="templateBean.trackResult">${templateBean.trackResult}</textarea></td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>

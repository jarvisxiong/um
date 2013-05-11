<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<!--概念规划设计成果审批表-->
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="100"/>
					<col/>
					<col width="100"/>
					<col/>
					<tr>
						<td class="td_title">项目名称</td>
						<td>
							<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td class="td_title">设计单位</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.designUnit" value="${templateBean.designUnit}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">方案编号</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.schemeNo" value="${templateBean.schemeNo}"  />
						</td>
						<td class="td_title">论证次数</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.argTime" value="${templateBean.argTime}"  />
						</td>
					</tr>
					<tr>
						<td class="td_title">论证结果</td>
						<td colspan="3">
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
						</td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>

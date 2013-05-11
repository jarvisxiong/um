<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--土地标准审批表-->
		<%@ include file="template-header.jsp"%>
	<div align="center" class="billContent">
	  
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="100"/>
					<col width="100"/>
					<col/>
					<col width="100"/>
					<tr>
					<td class="td_title">审批权限</td>
					<td align="left" colspan="3">
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
					<tr>
						<td class="td_title">名称</td>
						<td colspan="3">
							<input class="inputBorder" validate="required" type="text" name="templateBean.name" value="${templateBean.name}"  />
						</td>
					</tr>
					<tr>
						<td rowspan="3" class="td_title">内容简述<br>(详细内容附后)</td>
						<td class="td_title">用地面积</td>
						<td  colspan="2">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.useArea">${templateBean.useArea}</textarea>
						</td>
					</tr>
					<tr>
						<td class="td_title">建筑规模</td>
						<td  colspan="2">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.buildScale">${templateBean.buildScale}</textarea>
						</td>
					</tr>
					<tr>
					 	<td class="td_title">营运可能性分析</td>
						<td  colspan="2">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.possiblyAnalyse">${templateBean.possiblyAnalyse}</textarea>
						</td>
					</tr>
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>

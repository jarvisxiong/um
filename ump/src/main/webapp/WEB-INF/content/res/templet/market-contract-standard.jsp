<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--营销合同杨标准版本审批表-->
		<%@ include file="template-header.jsp"%>
		<div align="center" class="billContent">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="100"/>
					<col/>
					<col width="80"/>
					<col/>
					<tr>
						<td class="td_title">项目名称</td>
						<td colspan="3">
							<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
					</tr>
					<tr>
					   <td class="td_title">文件名称</td>
					   <td><input class="inputBorder" type="text" id="fileName"  name="templateBean.fileName" value="${templateBean.fileName}"  /></td>
					   <td class="td_title">文件编号</td>
					   <td><input class="inputBorder" type="text" id="fileNum"  name="templateBean.fileNum" value="${templateBean.fileNum}"  /></td>
					</tr>
					<tr>
					  <td class="td_title">文件类别</td>
					  <td colspan="3" class="chkGroup" align="left" validate="required" >
					  <table class="tb_checkbox" cellpadding="0" cellspacing="0">
					   <col width="150"/>
					   <col width="150"/>
					   <col />
					   <tr>
					    <td><s:checkbox name="templateBean.subscribeBook" cssClass="group"></s:checkbox>预定书</td>
					    <td><s:checkbox name="templateBean.modelContract" cssClass="group"></s:checkbox>商品房买卖合同范本</td>
					    <td><s:checkbox name="templateBean.modelContractRide" cssClass="group"></s:checkbox>合同范本补充条款</td>
					   </tr>
					   <tr>
					    <td><s:checkbox name="templateBean.makeRoomStandard" cssClass="group"></s:checkbox>交房装修标准</td>
					    <td><s:checkbox name="templateBean.marketContract" cssClass="group"></s:checkbox>营销合同范本</td>
					    <td></td>
					   </tr>
					   </table>
					 </td>
					</tr>
					<tr>
					    <td class="td_title">编制或修订</td>
						<td colspan="3" align="left"  class="chkGroup" validate="required" >
						<s:checkbox name="templateBean.plait" cssClass="group"></s:checkbox>编制
						<s:checkbox name="templateBean.revise" cssClass="group"></s:checkbox>开盘方案
						(第<input class="inputBorder" style="width: 30px;" type="text" id="reviseNum"  name="templateBean.reviseNum" value="${templateBean.reviseNum}"  />次修订)
						</td>
					</tr>    
					<tr>
						<td class="td_title">标准版本合同主要条款</td>
						<td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.mainContract">${templateBean.mainContract}</textarea></td>
					</tr>
					
				</table>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>

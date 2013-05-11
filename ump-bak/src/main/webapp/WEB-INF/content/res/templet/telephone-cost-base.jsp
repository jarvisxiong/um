<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
		<%--电话费用报销单--%>
	
		<div align="center" class="billContent">
				<%@ include file="template-var.jsp"%>
				<table  class="mainTable">
					<col width="100"/>
					<col width="200"/>
					<col width="100"/>
					<col />
					<tr>
						<td  class="td_title">申请人姓名</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.appName" value="${templateBean.appName}"  /></td>
					    <td  class="td_title">中心</td>
						<td>
							<input validate="required" type="text" name="templateBean.appCenterName" value="${templateBean.appCenterName}" id="appCenterName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
							<input type="hidden" id="appCenterCd"  name="templateBean.appCenterCd" value="${templateBean.appCenterCd}"/>	
						</td>
					</tr>
					<tr>
					  <td class="td_title">部门</td>
						<td ><input class="inputBorder" validate="required" type="text" name="templateBean.appDept" value="${templateBean.appDept}"/></td>
						<td class="td_title">级别</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.appGrade" value="${templateBean.appGrade}"/></td>
					</tr>
					<tr>
					  <td class="td_title">报销电话费类别</td>
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.appTeleType" value="${templateBean.appTeleType}"/></td>
					  <td class="td_title">报销电话费号码</td>
					  <td><input class="inputBorder" validate="required" type="text" name="templateBean.appTelephone" value="${templateBean.appTelephone}"/></td>
					</tr>
					
					<tr>
					  <td class="td_title">总经理审核额度(元)</td>
					  <td colspan="3">
					  	<s:if test="nodeCd==22 && isMyApprove==1">
						<s:hidden id="isEdit" value="true"/>
						<input class="inputBorder" edit='true' validate="required" onblur="formatVal($(this));" type="text" name="templateBean.approveLimit" value="${templateBean.approveLimit}"/>
						</s:if>
						<s:else>
						<input class="inputBorder" readonly="readonly" onblur="formatVal($(this));" type="text" name="templateBean.approveLimit" value="${templateBean.approveLimit}"/>
						</s:else>
					  </td>
					</tr>
					<tr>
					  <td class="td_title">入职时间</td>
					  <td><input class="Wdate inputBorder" onfocus="WdatePicker()" class="Wdate" validate="required" type="text" name="templateBean.entrantTime" value="${templateBean.entrantTime}"/></td>
					  <td class="td_title">报销起始时间</td>
					  <td><input class="Wdate inputBorder"  onfocus="WdatePicker()" class="Wdate" validate="required" type="text" name="templateBean.fromTime" value="${templateBean.fromTime}"/></td>
					</tr>
					<tr>
					  <td class="td_title">申请理由</td>
					  <td colspan="3"><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.appReason">${templateBean.appReason}</textarea></td>
					</tr>
				</table>
		</div>

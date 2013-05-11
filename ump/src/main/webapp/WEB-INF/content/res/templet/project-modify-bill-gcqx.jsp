<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<!--工程缺陷改造-->
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="90"/>
			<col/>
			<col width="110"/>
			<col width="90"/>
			<tr>
			<td class="td_title" rowspan="2">
			选择
			</td>
			<td class="chkGroup" align="left" validate="required" colspan="3">
			<table class="tb_checkbox">
				<col>
				<tr>
				<td><s:checkbox name="templateBean.feeType1"  cssClass="group"></s:checkbox>维保期，地产公司承担费用</td>
				</tr>
				<tr>
				<td><s:checkbox name="templateBean.feeType2"  cssClass="group"></s:checkbox>营运期，资本性支出</td>
				</tr>
				<s:if test="authTypeCd == 'JD_JSGL_10'">
				<tr>
				<td><s:checkbox name="templateBean.modifyType3"  cssClass="group"></s:checkbox>施工单位未能按照维保协议履行维保义务,由酒店公司自行安排</td>
				</tr>
				</s:if>
				<tr>
				<td><s:checkbox name="templateBean.modifyType2"  cssClass="group"></s:checkbox>其他</td>
				</tr>
			</table>
			</td>
			</tr>
			<tr>
			<td class="chkGroup" align="left" validate="required" colspan="3">
			<table class="tb_checkbox">
				<col width="200">
				<col width="200">
				<col>
				
				<tr>
				<td><s:checkbox name="templateBean.modifyType1"  cssClass="group"></s:checkbox>设计外观、效果改造</td>
				</tr>
			</table>
			</td>
			</tr>
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

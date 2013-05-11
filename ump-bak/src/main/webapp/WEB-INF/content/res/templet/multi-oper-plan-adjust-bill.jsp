<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 
	多经调整审批表(eg:多经点位规划及调整审批表)	
 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">

	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="100px">
			<col >
			<tr>
				<td style="border-top:0 none;" class="td_title">文件标题</td>
				<td style="border-top: none;">
					关于
					<input id="projectName" style="width:300px;" validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" class="inputBorder" />
					多经点位规划及调整的审批
				</td>
			</tr> 
			<tr>
				<td style="border-top:0 none;" class="td_title">密级</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.securityCd1"  cssClass="group"></s:checkbox>绝密
					<s:checkbox name="templateBean.securityCd2"  cssClass="group"></s:checkbox>机密
					<s:checkbox name="templateBean.securityCd3"  cssClass="group"></s:checkbox>保密
					<s:checkbox name="templateBean.securityCd4"  cssClass="group"></s:checkbox>内部公开
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">多经类别</td>
				<td style="border-top:0 none;" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.multiTypeCd1"  cssClass="group"></s:checkbox>路演
					<s:checkbox name="templateBean.multiTypeCd2"  cssClass="group"></s:checkbox>促销
					<s:checkbox name="templateBean.multiTypeCd3"  cssClass="group"></s:checkbox>其他
					(
					<input class="inputBorder" style="width:150px;" type="text" name="templateBean.multiTypeCd3Desc" value="${templateBean.multiTypeCd3Desc}" />
					)
					
				</td> 
			</tr>
			<tr>　
				<td style="border-top:0 none;" class="td_title">紧急</td>
				<td style="border-top:0 none;" class="chkGroup" align="left" >
					<s:checkbox name="templateBean.urgencyCd1"  cssClass="group"></s:checkbox>是
					<s:checkbox name="templateBean.urgencyCd2"  cssClass="group"></s:checkbox>否
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">中心</td>
				<td style="border-top: none;">
					<input id="centerName" validate="required" type="text" name="templateBean.centerName" value="${templateBean.centerName}"  readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" name="templateBean.centerCd" value="${templateBean.centerCd}" />
				</td> 
			</tr>
			<tr>
				<td style="border-top:0 none;" class="td_title">多经点位规划及调整说明<br/>（多经点位规划图附后）</td>
				<td style="border-top: none;">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.adjustDesc">${templateBean.adjustDesc}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

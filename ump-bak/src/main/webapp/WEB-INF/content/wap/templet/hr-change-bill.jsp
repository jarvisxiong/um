<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<!--人事变动表-->
<div class="billContent">

	<%@ include file="template-var.jsp"%>
	<div class="div_row">
		<span class="wap_title">中心:</span>
		<span class="wap_value">${templateBean.centerName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">姓名:</span>
		<span class="wap_value">${templateBean.userName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">部门:</span>
		<span class="wap_value">${templateBean.deptName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">职位:</span>
		<span class="wap_value">${templateBean.positionName}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">入职日期:</span>
		<span class="wap_value">${templateBean.enterDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">生效日期:</span>
		<span class="wap_value">${templateBean.effectDate}</span>
	</div>
	<div class="div_row">
		<span class="wap_title">用工类别:</span>
		<div><s:checkbox name="templateBean.userKind1" cssClass="group"></s:checkbox><span>正式工</span></div>
		<div><s:checkbox name="templateBean.userKind2" cssClass="group"></s:checkbox><span>临时工</span></div>
		<div><s:checkbox name="templateBean.userKind3" cssClass="group"></s:checkbox><span>实习生</span></div>
		<div><s:checkbox name="templateBean.userKind4" cssClass="group"></s:checkbox><span>外聘</span></div>
		<div><s:checkbox name="templateBean.userKind5" cssClass="group"></s:checkbox><span>外派</span></div>
		<div><s:checkbox name="templateBean.userKind6" cssClass="group"></s:checkbox><span>外籍</span></div>
	</div>
	<div class="div_row">
		<span class="wap_title">调整项目:</span>
		<div><s:checkbox name="templateBean.adjustItem4" cssClass="group"></s:checkbox><span>降职</span></div>
		<div><s:checkbox name="templateBean.adjustItem2" cssClass="group"></s:checkbox><span>通过试用期</span></div>
		<div><s:checkbox name="templateBean.adjustItem3" cssClass="group"></s:checkbox><span>升职</span></div>
		<div><s:checkbox name="templateBean.adjustItem5" cssClass="group"></s:checkbox><span>内部调动</span></div>
		<div><s:checkbox name="templateBean.adjustItem6" cssClass="group"></s:checkbox><span>工资变动</span></div>
		<div><s:checkbox name="templateBean.adjustItem8" cssClass="group"></s:checkbox><span>临时借调</span></div>
		<div><s:checkbox name="templateBean.adjustItem7" cssClass="group"></s:checkbox><span>其他</span></div>
		<span class="wap_value">${templateBean.adjustOther}</span>
	</div>
	<div class="div_label">
		<span class="wap_label">【调整内容明细】<red>(可左右拖动查看)</red></span>
		<div class="div_scroll">
		<table>
			<tr>
				<td><span class="wap_title">调整内容</span></td>
				<td><span class="wap_title">调整前</span></td>
				<td><span class="wap_title">调整后</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">中心/公司</span></td>
				<td><span class="wap_value">${templateBean.centerBefore}</span></td>
				<td><span class="wap_value">${templateBean.centerAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">部门</span></td>
				<td><span class="wap_value">${templateBean.deptBefore}</span></td>
				<td><span class="wap_value">${templateBean.deptAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">职位</span></td>
				<td><span class="wap_value">${templateBean.positionBefore}</span></td>
				<td><span class="wap_value">${templateBean.positionAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">职级</span></td>
				<td><span class="wap_value">${templateBean.levelBefore}</span></td>
				<td><span class="wap_value">${templateBean.levelAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">工资</span></td>
				<td><span class="wap_value">${templateBean.salaryBefore}</span></td>
				<td><span class="wap_value">${templateBean.salaryAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">外派补贴(元)</span></td>
				<td><span class="wap_value">${templateBean.foreSubsidyBefore}</span></td>
				<td><span class="wap_value">${templateBean.foreSubsidyAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">住房补贴(元)</span></td>
				<td><span class="wap_value">${templateBean.houseSubsidyBefore}</span></td>
				<td><span class="wap_value">${templateBean.houseSubsidyAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">交通补贴(元)</span></td>
				<td><span class="wap_value">${templateBean.trafficSubsidyBefore}</span></td>
				<td><span class="wap_value">${templateBean.trafficSubsidyBefore}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">通讯补贴(元)</span></td>
				<td><span class="wap_value">${templateBean.commuSubsidyBefore}</span></td>
				<td><span class="wap_value">${templateBean.commuSubsidyAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">特殊补贴(元)</span></td>
				<td><span class="wap_value">${templateBean.specialSubsidyBefore}</span></td>
				<td><span class="wap_value">${templateBean.specialSubsidyAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">其他</span></td>
				<td><span class="wap_value">${templateBean.otherBefore}</span></td>
				<td><span class="wap_value">${templateBean.otherAfter}</span></td>
			</tr> 
			<tr>
				<td><span class="wap_title">备注</span></td>
				<td colspan="2"><span class="wap_value">${templateBean.remark}</span></td>
			</tr> 
		</table>
		</div>
	</div>

</div>

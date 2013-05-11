<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="template-header.jsp"%>

<%--宝龙商业总部组织管理文件审批表--%>

<div class="billContent" align="center">
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
		<col width="120px">
		<col/>
		<tr>
			<td class="td_title">制度文件名称</td>
			<td>
			 <input validate="required" class="inputBorder" type="text" name="templateBean.regimeName" value="${templateBean.regimeName}"/>
			</td>
		</tr>
		<tr>
		 <td class="td_title">制度类型</td>
		 <td class="chkGroup"  validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<col/>
					<tr>
					<td><s:checkbox name="templateBean.baseRegime"  cssClass="group"></s:checkbox>基本制度</td>
					<td><s:checkbox name="templateBean.commonRegime"  cssClass="group"></s:checkbox>一般制度</td>
					<td><s:checkbox name="templateBean.innerRegime"  cssClass="group"></s:checkbox>内部制度</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
		 <td class="td_title">是否需要相关专业口审阅</td>
		 <td  class="chkGroup" validate="required"> 
		  <table class="tb_checkbox">
				<col width="150">
				<col/>
				<tr>
				<td><s:checkbox name="templateBean.isAudit"  cssClass="group"></s:checkbox>是</td>
				<td><s:checkbox name="templateBean.isNotAudit"  cssClass="group"></s:checkbox>否</td>
				</tr>
			</table>
		 </td>
		</tr>
		<tr>
		 <td class="td_title">审批类型</td>
		 <td  class="chkGroup" validate="required">
		  <table class="tb_checkbox">
				<col width="150">
				<col/>
				<tr>
				<td><s:checkbox name="templateBean.auditType"  cssClass="group"></s:checkbox>新增</td>
				<td><s:checkbox name="templateBean.auditModify"  cssClass="group"></s:checkbox>修订</td>
				</tr>
			</table>
		 </td>
		</tr>
		<tr>
		 <td class="td_title">内容简述<br></br>(详细内容附后)</td>
		<td>
		  <textarea class="inputBorder contentTextArea" validate="required" name="templateBean.contentDesc" rows="6" cols="30">${templateBean.contentDesc}</textarea>
		</td>
		</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
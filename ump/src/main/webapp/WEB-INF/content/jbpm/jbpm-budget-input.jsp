<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html" />
		<title>新增/修改预算</title>
		<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script src="${ctx}/js/table.js" type="text/javascript"></script>
		<style type="text/css">
			.overlay {
				-moz-background-clip:border;
				-moz-background-inline-policy:continuous;
				-moz-background-origin:padding;
				background-color: #5C5C5C;
				filter:alpha(opacity=30);
				position: absolute;
				top: 0px;
				bottom: 0px;
				width: 100%;
				height: 100%;
				display: none;
			}
			
			.popup_dialog {
				position: absolute;
				height: auto;
				width:300px;
				top:140px;
				left:379px;
				background-color: #FFF;
				display: none;
				padding: 0px;
				margin: 0px;
			}
			
			.popup_dialog .popup_dialog_header {
				margin: 5px 0px 0px 0px;
				padding: 2px;
			}
			
			.popup_dialog .popup_dialog_header span {
				font-weight: bold;
				font-size: 15px;
			}
			
			.popup_dialog .popup_dialog_content {
				padding: 5px 2px;
				text-align: left;
				display: block;
			}
			
			.popup_dialog .popup_dialog_navigation {
				padding:1px 1px;
				margin-botton: 4px;
			}
		</style>
		<script language="javascript">
			$(function() {
				$("#budgetDetailEditSaveBtn").click(function() {
					$("#editBudgetDetailForm").submit();
					return false;
				});

				$("#budgetDetailCancelBtn").click(function() {
					$("#editedBudgetDetailId").val('');
					$("#popup_dialog").fadeOut(100).css("z-index", "-1");
					$("#mask").hide().css("z-index", "-1");
					return false;
				});
			});
			
			function deleteBudgetDetail(id, subid){
				location.href="jbpm-budget!deleteSub.action?subId="+subid+"&id="+id;
			}
			
			function editBudgetDetail(subId, budgetFee, remark) {
				$("#editedBudgetDetailId").val(subId);
				$("#edited_budgetDetail_budgetFee").val(budgetFee);
				$("#edited_budgetDetail_remark").text(remark);
				$("#mask").show().css("z-index", "2001");
				$("#popup_dialog").fadeIn(100).css("z-index", "2002");
			}
		</script>
	</head>

	<body>
		<div align="center" style="width:100%;">
			<s:if test="id == null">
				<h1><s:text name="common.create" /><s:text name="jbpmJbpmBudget" /></h1>
				<s:form id="inputBudgetForm" action="jbpm-budget!save.action" method="post" theme="simple">
					<table>
						<tr align="left">
							<td nowrap="nowrap"><s:text name="jbpmJbpmBudget.deptCd"/>*:</td>
							<td><s:textfield label="Name" id="deptCd" name="deptCd" size="40" /></td>
						</tr>
						<tr align="left">
							<td nowrap="nowrap"><s:text name="jbpmJbpmBudget.budgetYear"/>*:</td>
							<td><s:textfield id="budgetYear" name="budgetYear" value="" size="40" /></td>
						</tr>
						<tr align="left">
							<td nowrap="nowrap"><s:text name="jbpmJbpmBudget.budgetMonth"/>*:</td>
							<td><s:select list="monthMap" name="budgetMonth" value="1"/></td>
						</tr>
						<tr align="left">
							<td nowrap="nowrap"><s:text name="jbpmJbpmBudget.budgetAmount"/>*:</td>
							<td><s:textfield id="budgetAmount" name="budgetAmount" size="40"/></td>
						</tr>
						<tr align="left">
							<td nowrap="nowrap"><s:text name="jbpmJbpmBudget.remark"/>:</td>
							<td><s:textarea key="remark" id="remark" name="remark" cssStyle="width: 260px; height: 50px;" /></td>
						</tr>
						<tr>
							<td colspan="2">
								<input class="buttom" type="button" name=""  onclick="window.location.href = 'jbpm-budget.action'" value="<s:text name="common.return"/>" />
								<s:reset name="" cssClass="buttom" key="common.reset"/>
								<security:authorize ifAnyGranted="A_ADMIN">
									<s:submit cssClass="buttom" name="" key="common.submit"/>
								</security:authorize>
							</td>
						</tr>
					</table>
				</s:form>
			</s:if>
			<s:else>
				<s:form id="inputBudgetForm" action="jbpm-budget!save.action" method="post" theme="simple">
					<div id="budgetInfo">
						<s:hidden name="id" value="%{jbpmBudgetId}" />
						<fieldset>
							<legend><s:text name="jbpmJbpmBudget" /></legend>
							<table width="90%">
								<tr>
									<td nowrap="nowrap" width="15%"><s:text name="jbpmJbpmBudget.deptCd"/>:</td>
									<td width="35%" align="left"><s:text name="deptCd" /></td>
									<td nowrap="nowrap" width="15%"><s:text name="jbpmJbpmBudget.budgetYear"/>:</td>
									<td width="35%" align="left"><s:text name="budgetYear" /></td>
								</tr>
								<tr>
									<td nowrap="nowrap"><s:text name="jbpmJbpmBudget.budgetMonth"/>:</td>
									<td align="left"><s:text name="budgetMonth" /></td>
									<td nowrap="nowrap"><s:text name="jbpmJbpmBudget.budgetAmount"/>:</td>
									<td align="left"><s:text name="budgetAmount" /></td>
								</tr>
								<tr>
									<td nowrap="nowrap"><s:text name="jbpmJbpmBudget.remark"/>:</td>
									<td align="left" colspan="3">
										<s:textarea key="remark" id="remark" name="remark" cssStyle="width: 350px; height: 50px;" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<input class="buttom" type="button" name=""  onclick="window.location.href = 'jbpm-budget.action'" value="<s:text name="common.return"/>" />
										<s:submit cssClass="buttom" name="" key="common.save" />
										<s:reset cssClass="buttom" name="" key="common.reset"/>
									</td>
								</tr>							
						</div>
							</table>
						</fieldset>
					</div>
					<div id="tableDiv">
						<div align="left">
							<fieldset>
								<legend><s:text name="common.create"/><s:text name="jbpmJbpmBudgetDetail"/></legend>
								<s:text name="jbpmJbpmBudgetDetail.budgetFee"/>*:<s:textfield id="budgetFee" name="jbpmJbpmBudgetDetail_budgetFee" size="20" />
								<s:text name="jbpmJbpmBudgetDetail.remark"/>:<s:textfield name="jbpmJbpmBudgetDetail_remark" id="remark" size="50" maxlength="200"  />
								<s:submit name="" cssClass="buttom" key="common.create"/>
							</fieldset>
						</div>

						<div>
							<table class="commonTable" id="editTable" align="left" width="99%" style="table-layout: fixed;">
								<caption><span style="font-size: 18px;font-weight: bold;"><s:text name="jbpmJbpmBudgetDetail"/></span></caption>
								<thead>
									<tr>
										<th><s:text name="jbpmJbpmBudgetDetail.budgetFee" /></th>
										<th><s:text name="jbpmJbpmBudgetDetail.creator" /></th>
										<th><s:text name="jbpmJbpmBudgetDetail.createdDeptCd" /></th>
										<th><s:text name="jbpmJbpmBudgetDetail.createdDate" /></th>
										<th><s:text name="jbpmJbpmBudgetDetail.updator" /></th>
										<th><s:text name="jbpmJbpmBudgetDetail.updatedDeptCd" /></th>
										<th><s:text name="jbpmJbpmBudgetDetail.updatedDate" /></th>
										<th><s:text name="jbpmJbpmBudgetDetail.remark" /></th>
										<th width="90"><s:text name="common.operate"/></th>
									</tr>
								</thead>
								
								<tbody>
									<s:iterator value="jbpmBudgetDetails" status="status">
										<tr>
											<td>${budgetFee}</td>
											<td>${creator}</td>
											<td>${createdDeptCd}</td>
											<td><s:date name="createdDate" format="yyyy-MM-dd"/></td>
											<td>${updator}</td>
											<td>${updatedDeptCd}</td>
											<td><s:date name="updatedDate" format="yyyy-MM-dd"/></td>
											<td>${remark}</td>
											<td>
												<a href="#" onclick="deleteBudgetDetail('${jbpmBudgetId}','${jbpmBudgetDetailId}');" ><s:text name="common.delete"/></a>
												<a href="#" onclick="editBudgetDetail('${jbpmBudgetDetailId}', '${budgetFee}', '${remark}' );" ><s:text name="common.modify"/></a>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</s:form>
				
				<div id="mask" class="overlay"></div>
				
				<div id="popup_dialog" class="popup_dialog">
					<div class="popup_dialog_header">
						<span><s:text name="common.modify"/><s:text name="jbpmJbpmBudgetDetail"/></span>
					</div>
					
					<div class="popup_dialog_content">
						<s:form id="editBudgetDetailForm" action="jbpm-budget!editSub.action" method="POST" theme="simple">
							<s:hidden id="budgetId" name="id" value="%{jbpmBudgetId}" />
							<input type="hidden" id="editedBudgetDetailId" name="subId" value="" />
							
							<fieldset>
								<table>
									<tr>
										<td align="left"><s:text name="jbpmJbpmBudgetDetail.budgetFee"/>*:</td>
									</tr>
									<tr>
										<td align="left"><s:textfield id="edited_budgetDetail_budgetFee" name="jbpmJbpmBudgetDetail_budgetFee" size="20" /></td>
									</tr>
									<tr>
										<td align="left"><s:text name="jbpmJbpmBudgetDetail.remark"/>:</td>
									</tr>
									<tr>
										<td align="left"><s:textarea id="edited_budgetDetail_remark" name="jbpmJbpmBudgetDetail_remark" /></td>
									</tr>
								</table>							
							</fieldset>
						</s:form>
					</div>
					
					<div class="popup_dialog_navigation">
						<input id="budgetDetailEditSaveBtn" class="buttom" type="button" name=""  value="<s:text name="common.save"/>" />
						<input id="budgetDetailCancelBtn" class="buttom" type="button" name=""  value="<s:text name="common.cancel"/>" />
					</div>
				</div>
			</s:else>
		</div>
	</body>
</html>
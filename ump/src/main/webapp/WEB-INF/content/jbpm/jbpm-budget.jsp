<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html" />
		<title>预算管理</title>
		<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script src="${ctx}/js/table.js" type="text/javascript"></script>
		<script language="javascript">
			
		</script>
	</head>

	<body>
		<div class="search">
			<s:form id="mainForm" action="jbpm-budget" method="POST">
				<fieldset>
					<legend><s:text name="common.search" /></legend>
					<div style="text-align: center;">
						<s:text name="jbpmJbpmBudget.deptCd" />:<s:textfield name="filter_LIKES_deptCd" id="filter_LIKES_deptCd" size="18" maxlength="30" />
						<input type="submit" class="buttom" value="<s:text name="common.search" />" />
						<input type="button" class="buttom" value="<s:text name="common.exportExcel" />" />
					</div>
				</fieldset>
			</s:form>
		</div>
	
		<div id="tableDiv">
			<table class="commonTable" id="editTable" align="left" width="99%" style="table-layout: fixed;">
				<thead>
					<tr>
						<th><s:text name="jbpmJbpmBudget.deptCd"></s:text></th>
						<th><s:text name="jbpmJbpmBudget.budgetYear" /></th>
						<th><s:text name="jbpmJbpmBudget.budgetMonth" /></th>
						<th><s:text name="jbpmJbpmBudget.budgetAmount" /></th>
						<th><s:text name="jbpmJbpmBudget.creator" /></th>
						<th><s:text name="jbpmJbpmBudget.createdDeptCd" /></th>
						<th><s:text name="jbpmJbpmBudget.createdDate" /></th>
						<th><s:text name="jbpmJbpmBudget.updator" /></th>
						<th><s:text name="jbpmJbpmBudget.updatedDeptCd" /></th>
						<th><s:text name="jbpmJbpmBudget.updatedDate" /></th>
						<th><s:text name="jbpmJbpmBudget.remark" /></th>
						<th><s:text name="common.operate" /></th>
					</tr>
				</thead>
				
				<tbody>
					<s:iterator value="page.result">
					<tr>
						<td>${deptCd}</td>
						<td>${budgetYear}</td>
						<td>${budgetMonth}</td>
						<td>${budgetAmount}</td>
						<td>${creator}</td>
						<td>${createdDeptCd}</td>
						<td><s:property value="createdDate" /></td>
						<td>${updator}</td>
						<td>${updatedDeptCd}</td>
						<td><s:date name="updatedDate" format="yyyy-MM-dd"/></td>
						<td>${remark}</td>
						<td>
							<security:authorize ifAnyGranted="A_ADMIN">
								<p:operate idName="jbpmBudgetId" action="jbpm-budget" enableName="" />
							</security:authorize>
						</td>
					</tr>
					</s:iterator>
					
					<tr>
						<td align="center" colspan="12">
							<a href="jbpm-budget!input.action"> 
								<font color="red">
									<s:text name="common.create" /><s:text name="jbpmJbpmBudget" /> 
								</font> 
							</a>&nbsp; 
							<p:page />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
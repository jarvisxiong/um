<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<div>
	<table style="width:100%;height:100%;" cellpadding="0px" cellspacing="0px">
		<thead>
			<tr class="headTR">
				<th class="th_first" style="width: 40%;">开户公司</th>
				<th class="th" style="width: 30%;">银行名称</th>
				<th class="th" style="width: 25%;">银行账号</th>
				<th class="th" style="width: 5%;">操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="page.result" status="s">
			<tr class="bodyTR" onmouseover="" onmouseout="">
				<td class="td" id="td_companyName_${bizBankAccountId}" onclick="showEditPanel('${bizBankAccountId}');">${companyName}</td>
				<td class="td" id="td_bankName_${bizBankAccountId}" onclick="showEditPanel('${bizBankAccountId}');">${bankName}</td>
				<td class="td" id="td_bankNo_${bizBankAccountId}" onclick="showEditPanel('${bizBankAccountId}');">${bankNo}</td>
				<td class="td">
				<security:authorize ifAnyGranted="A_BANK_ADMIN">
				<input type="button" onclick="deleteAccount('${bizBankAccountId}');" class="btn btn_red" value="删除" title="删除"/>
				</security:authorize>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="edit_account_panel_${bizBankAccountId}" class="edit_account_panel">
						<form action="${ctx}/biz/biz-bank-account!save.action" method="post" id="edit_account_form_${bizBankAccountId}">
						<input type="hidden" name="id" value="${bizBankAccountId}"/>
						<input type="hidden" name="bizBankAccountId" value="${bizBankAccountId}"/>
						<input type="hidden" id="recordVersion" name="recordVersion" value="${recordVersion}"/>
						<table class="edit_table" id="edit_table_${bizBankAccountId}">
							<tr>
								<td class="edit_td" style="width: 40%;"><input type="text" id="companyName_edit" class="input" name="companyName" value="${companyName}" required="required"/></td>
								<td class="edit_td" style="width: 30%;"><input type="text" id="bankName_edit" class="input" name="bankName" value="${bankName}" required="required"/></td>
								<td class="edit_td" style="width: 25%;"><input type="text" id="bankNo_edit" class="input" name="bankNo" value="${bankNo}" required="required"/></td>
								<td class="edit_td" style="width: 5%;padding-right: 0px;"><div style="width: 70px !important;"></div></td>
							</tr>
							<tr>
								<td colspan="4" style="padding-top:5px;">
									<security:authorize ifAnyGranted="A_BANK_ADMIN">
									<input type="button" class="btn btn_green" value="保存" onclick="editAccount('${bizBankAccountId}');" title="保存编辑" style="float:left;margin-right:5px;">
									</security:authorize>
									<input type="button" class="btn btn_green" value="取消" onclick="showEditPanel('${bizBankAccountId}');" title="取消编辑" style="float:left;">
								</td>
							</tr>
						</table>
						</form>
					</div>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	
	<div class="table_pager" style="margin-top:5px;">
		<p:page pageInfo="page"/>
	</div>
</div>
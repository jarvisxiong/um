<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<title>银行账号</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/biz/biz-bank-account.css"/>

<%@ include file="/common/global.jsp" %>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/biz/biz-bank-account.js"></script>
</head>
<body style="background-color: white !important;">
<div>
	<!-- 头部区域 -->
	<div id="head_panel">
		<div class="act_div">
			<form action="${ctx}/biz/biz-bank-account!list.action" method="post" id="search_account_form">
			<input type="hidden" id="pageNo" name="page.pageNo">
			<span class="cond_span">开户公司：<input id="companyName_search" name="companyName" class="input"/></span>
			<span class="cond_span">银行名称：<input id="bankName_search" name="bankName" class="input"/></span>
			<span class="cond_span">银行账号：<input id="bankNo_search" name="bankNo" class="input"/></span>
			<input type="button" class="btn btn_blue cond_btn" value="搜索" onclick="jumpPage();" title="搜索"/>
			<security:authorize ifAnyGranted="A_BANK_ADMIN">
			<input type="button" class="btn btn_blue cond_btn" value="新增" onclick="showNewPanel();" title="新增"/>
			<div style="clear: left;margin-left:5px;padding-top:5px;">
			选择文件：<input type="file" id="importFile" name="importFile" style="line-height:20px;height:20px;"/>
			<input type="button" class="btn btn_green" value="导入数据" onclick="importAccount();" title="导入数据"/>
			<input type="button" class="btn btn_blue" value="导出模板" onclick="exportAccount();" title="导出模板"/>
			</div>
			</security:authorize>
			</form>
		</div>
	</div>
	
	<!-- 新增银行账户区域 -->
	<div id="new_account_panel">
		<div class="title">
			<div>银行账户新增</div>
		</div>
		<form action="${ctx}/biz/biz-bank-account!save.action" method="post" id="new_account_form">
			<table class="new_table" cellpadding="0px" cellspacing="0px">
				<tr>
					<td class="new_td" align="right" style="width: 90px;">开户公司：</td>
					<td class="new_td" style="padding-left:5px;padding-right:5px;"><input type="text" id="companyName_new" name="companyName" required="required" class="input"/></td>
					<td class="new_td" align="right" style="width: 90px;">银行名称：</td>
					<td class="new_td" style="padding-left:5px;padding-right:5px;"><input type="text" id="bankName_new" name="bankName" required="required" class="input"/></td>
					<td class="new_td" align="right" style="width: 90px;">银行账号：</td>
					<td class="new_td" style="padding-left:5px;padding-right:5px;"><input type="text" id="bankNo_new" name="bankNo" required="required" class="input"/></td>
				</tr>
				<tr>
					<td colspan="6" style="padding-top:5px;">
						<input type="button" id="btn_save_account" class="btn btn_green" value="保存" onclick="saveAccount();" title="保存" style="float:left;">
						<input type="button" id="btn_cancel_account" class="btn btn_green" value="取消" onclick="showNewPanel();" title="取消" style="float:left; margin-left:5px;">
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<form action="${ctx}/biz/biz-bank-account!delete.action" method="post" id="del_account_form">
		<input type="hidden" id="bizBankAccountId" name="bizBankAccountId">
	</form>
	<!-- 内容列表显示区域 -->
	<div id="content_panel">
	</div>
</div>
</body>
</html>
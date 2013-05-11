<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div id="emailSearchDiv" class="emailSearchDiv">
<form action="${ctx}/oa/oa-email!list.action" method="post" id="mailSearchForm">
	<table cellpadding="1" border="0" cellspacing="0" >
		<tr>
			<td align="left" style="padding-left:8px;"  width="40px">邮箱</td>
			<td width="100px">
				<select name="searchBoxId" style="width:100%" onchange="changeUserFilter(this);">
					<option value="1">收件箱</option>
					<option value="2">草稿箱</option>
					<option value="3">发件箱</option>
					<option value="4">垃圾箱</option>
				</select>
			</td>
			
			<td userType="send" align="center" width="50px">发件人</td>
			<td userType="send">
				<s:textfield onkeyup="showSearchUser(this)" name="filter_LIKES_sender" id="creatorNameId" ></s:textfield>
			</td>
			<td userType="receive" style="display:none" align="center" width="50px">收件人</td>
			<td userType="receive" style="display:none">
				<s:textfield onkeyup="showSearchUser(this)" name="filter_LIKEC_toUserNames" id="toUserNameId" ></s:textfield>
			</td>
			<td width="50px" align="center">主题</td>
			<td><s:textfield name="filter_LIKES_subject"></s:textfield></td>
			
		</tr>
		<tr>
			<td align="left" style="padding-left:8px;">日期</td>
			<td><s:textfield id="filter_GED_sendTime" name="filter_GED_sendTime" onfocus="WdatePicker()" size="20" /></td>
			<td align="center">到</td>
			<td><s:textfield id="filter_LED_sendTime" name="filter_LED_sendTime" onfocus="WdatePicker()" size="20" /></td>
			
			<td align="center">类型</td>
			<td width="100px">
				<select name="filter_EQS_emailType" style="width:100%">
					<option value="">全部</option>
					<option value="0">普通</option>
					<option value="1">审批</option>
				</select>
				<input type="hidden" name="pageEmail.pageNo" id="searchPageNo" value="1"/>
			</td>
			<td colspan="2" align="right" style="padding-left: 10px;">
				<div class="btn_green_55_20" id="emailSearchBtn" onclick="getEmailList();">搜索</div>
			</td>
		</tr>
	</table>
</form>
</div>
<div id="searchResult">
	<!-- 显示搜索结果 -->
</div>
<div id="searchReadMailDiv">
	<!-- 显示阅读结果 -->
</div>

<script language="javascript">
	function getEmailList(){
		$("#searchResult").empty().addClass("waiting");
		$("#mailSearchForm").ajaxSubmit(function(result){
			$("#searchResult").html(result).removeClass("waiting");
			$(".mailTableDiv").height("390px");
			bindTdEvents();
		});
	}
	function resetSearchForm(){
		$("#mailSearchForm").reset();
	}

</script>
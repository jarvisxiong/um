<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>宝龙管理平台</title>
<%@ include file="/common/global.jsp" %>
<meta name="HandHeldFriendly" content="true">
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<link rel="stylesheet" href="${ctx}/resources/css/wap/desk_main.css" />
<link rel="stylesheet" href="${ctx}/resources/css/wap/wapTreePanel.css" />


<script src="${ctx}/resources/js/common/common.js"></script>
<script src="${ctx}/resources/js/jquery/jquery.js"></script>
<script src="${ctx}/resources/js/wap/desk_main.js"></script>
<script src="${ctx}/resources/js/desk/wab/wabTreePanel.js"></script>
</head>
<body>
	<div id="content">
		<div id="div_logo">
		<span style="margin:auto 5px;font-size:16px;color: #333;">宝龙管理系统</span>
		</div>
		<div id="div_main">
			<span class="m_title_page">通讯录</span>
			<div>
				<input type="text" onblur="resetSearchInput(this,'wabTree','searchResult');" onkeyup="searchUserResult(this);" onclick="clearSearchInput(this);" class="search_input" value="搜索联系人..." id="searched_user">
				<ul class="ul_none" id="wap_orgList">
				<s:iterator value="rootNoodes">
					<li class="ul_li_right" onclick="expandNode('${id}')">${text}</li>
					<div id="div_tree_${id}" style="display: none;"></div>
				</s:iterator>
				</ul>
		    </div>
		    <div id="searchResult" class="search_user_result" ></div>
		    
		    <div class="div_btn" style="background-color:#a4c45f;">
				<input class="div_font" type="button" value="内部邮件">
			</div>
		    <div class="div_btn" style="background-color:#1ba0e1;" id="web_approve">
				<input class="div_font"  type="button" value="网上审批">
			</div>
			<div style="clear: both;margin: 0;padding: 0;"></div>
		    <div class="div_btn" style="background-color:#fb9032;">
				<input class="div_font" type="button" value="商业系统">
			</div>
		    <div class="div_btn" style="background-color:#5a5a5a;">
				<input class="div_font" type="button" value="计划管理">
			</div>
			<div style="clear: both;margin: 0;padding: 5px 0;"></div>
		    <div class="div_btn2" style="background-color:#909090;" onclick="logout();">
				<input class="div_font" type="button" id="div_logout" value="退出">
			</div>
		    <div class="div_btn2" style="background-color:#909090;" onclick="gotoWeb();">
				<input class="div_font" type="button" id="div_gotoweb" value="转至网页版">
			</div>
		</div>
	</div>
	<script language="javascript">
		$(function(){
			$('#wap_orgList li').toggle(
				function(){
					$(this).next().show();
					$(this).removeClass('ul_li_right').addClass('ul_li_drop');
				},
				function(){
					$(this).next().hide();
					$(this).removeClass('ul_li_drop').addClass('ul_li_right');
				}
			);
		});
		$("#web_approve").click(function(){
			window.location.href=_ctx+'/wap/wap-approve-info.action';
			});
	</script>
</body>
</html>

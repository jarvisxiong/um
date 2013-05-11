<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<div>
	<div class="toolBar">
	<input type="button" value="确定" class="btn_blue" onclick="doApproveDelay();" />
	<input type="button" value="返回" class="btn_green" onclick="doApproveDelay();" />
	</div>
	<div class="list_header">人员选择</div>
	
	<div style="margin:5px;">
	<span>已选中:</span>
	<div id="resChoosed"></div>
	<div style="margin:10px auto;">
	</div>
	<div>
	<input type="text" onblur="resetSearchInput(this,'wabTree','searchResult');" onkeyup="searchUserResult(this);" onclick="clearSearchInput(this);" class="search_input" value="搜索联系人..." id="searched_user">
	</div>
	<ul class="ul_none" id="wap_orgList">
	<s:iterator value="rootNoodes">
		<li id="li_${id}" class="ul_li_right" onclick="expandNode('${id}')">${text}</li>
		<div id="div_tree_${id}" style="display: none;"></div>
	</s:iterator>
	</ul>
   	<div id="searchResult" class="search_user_result" ></div>
   	</div>
   </div>
   <link rel="stylesheet" href="${ctx}/resources/css/wap/wap_approve_share.css" />
   <script src="${ctx}/resources/js/wap/wap_approve_user.js"></script>

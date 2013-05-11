<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
</head>

<body>

	<div class="title_bar">
		<div class="fLeft banTitle">
			项目设置
		</div>
		<div class="fRight">
			<input type="button" class="btn_new btn_full_new"  onclick="window.open(location.href);" title="全屏" value="全屏"/>
			<input type="button" class="btn_new btn_refresh_new"  onclick="window.location.href= location.href;"  title="刷新" value="刷新"/>
		</div>
	</div>
	
	
	<div id="TableDiv">
		<form action="${ctx}/cont/cont-project-code!list.action" method="post" id="searchForm">
			<%--翻页：很重要 --%>
			<input type="hidden" id="pageNo" name="pageNo" />
			<table style="margin:5px 10px;">
			<tr>
				<td style="padding-left: 10px;">机构类型：</td>
				<td>
					<select name="contTypeCd" id="contTypeCd" onchange="doQueryProjectList();">
						<option value="">全部</option>
						<option value="1">地产</option>
						<option value="2">商业</option>
						<option value="3">酒店</option>
					</select>
				</td>
				<%--
				<td style="padding-left: 10px;">项目名称：</td>
				<td>
					<input type="text" id="projectName" name="projectName" class="text" title="支持多个，用英文逗号隔开" style="width:80px;"/>
				</td>
				 --%>
				<td style="padding-left: 10px;">关键字：</td>
				<td>
					<input type="text" id="remark" name="remark" class="text" title="例如北区等" style="width:100px;"/>
				</td>
				<td style="padding-left: 10px;">授权用户：</td>
				<td>
					<input type="text" name="curUserName" id="curUserName" readonly="readonly" class="text" style="cursor: pointer;width:120px;" title="点击选择要用户"/> 
					<input type="hidden" id="authUiid" name="authUiid"/>
				</td>
				<td style="padding-left: 10px;">
					<input type="button" class="btn_new btn_query_new" onclick="doQueryProjectList();" value="搜索" style="width:40px;"/>
					<input type="button" class="btn_new btn_clean_new" onclick="doClear();" value="清空条件" style="width: 70px;"/>
				</td>
			</tr>
			</table> 	
  		</form>
	  		
		<%-- 搜索结果列表 --%>
		<div id="contProjectList" style="margin-top: 10px;">	  
			<%@include file="cont-project-code-list.jsp" %>
		</div>
 </div>
 
<script type="text/javascript">  
	$(function(){ 
		//选择人
		$("#curUserName").userSelect({
	        muti:true,
	        nameField:'curUserName',
	        cdField:'authUiid'
		});
	});
	
	function doQueryProjectList(){
		jumpPage(1);
	}
	 
	//翻页搜索
	function jumpPage(pageNo) {
		if(!pageNo){
			pageNo = 1;
		}
		$("#pageNo").val(pageNo);
		TB_showMaskLayer("正在搜索,请稍候...");
		$("#searchForm").ajaxSubmit(function(result) {
			TB_removeMaskLayer();
			$("#contProjectList").html(result);
		});
	}
	function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
	}

	//清除控件的值
	function doClear(){
		var	_userMap = {};
		var o = {userName:'',uiid:''};
		_userMap[$("#curUserName").val()] = o;
		var data = $.extend(true,{},null);
		$("#curUserName").data('userMap',data);
		
		$("#contTypeCd").val('');
		$("#projectName").val('');
		$("#authUiid").val('');
		$("#curUserName").val('');
		$("#remark").val('');
	}
	//刷新项目
	function refreshMain(){
		window.location.href = _ctx + "/cont/cont-project-code!main.action";
	}
	//全屏
	function openFullMain(){
		window.open(_ctx + "/cont/cont-project-code!main.action");
	}
	
</script>
</body>
</html>
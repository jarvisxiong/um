<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>授权项目管理</title>
	
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/global.jsp" %>
    <!-- Le styles -->
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/loadMask/jquery.loadmask.css" />
    <%--
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap/bootstrap-responsive.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap/docs.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bootstrap/prettify.css">
     --%>
    <link type="text/css" rel="stylesheet" href="${ctx}/resources/js/loadMask/jquery.loadmask.css">
    

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
    <%--
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/prettify.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-transition.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-alert.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-modal.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-dropdown.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-scrollspy.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-tab.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-popover.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-button.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-collapse.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-carousel.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/bootstrap-typeahead.js"></script>
     --%>
    <%--
    <script type="text/javascript" src="${ctx}/resources/js/bootstrap/application.js"></script>
     --%>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
    <script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
    
	<style type="text/css">
		.search_panel th{
			text-align: right;
			line-height: 25px;
			padding-left: 5px;
			width:60px;
			padding:3px;
		}
		.search_panel td{
			text-align: left;
			line-height: 25px;
			padding-left: 5px;
			width:150px;
			padding:3px;
		}
		.form-search{
		  margin:5px 0;
		}
	</style>
</head>
<body> 
	
	<div class="title_bar">
		<div class="fLeft banTitle">项目权限管理</div>
		<div class="fRight">
		  	<input type="button" class="btn_new btn_full_new " onclick="window.open(location.href)" value="全屏"/>
			<input type="button" class="btn_new btn_refresh_new" style="margin-left:2px" onclick="window.location.href=location.href" value="刷新"/>
		</div>
  	</div>
  		
	<div style="padding:10px;clear: left;"> 
		<form class="form-search" action="${ctx}/bid/bid-project-role!list.action" method="post" id="searchForm">
			<input type="hidden" id="pageNo" name="pageNo" value="${pageNo}"/>
	   		<table class="search_panel" cellpadding="0" cellspacing="0">
	   			<colgroup>
	   				<col width="90px;"/>
	   				<col />
	   				<col width="80px;"/>
	   				<col />
	   
	   				<col width="200px"/>
	   			</colgroup>
	   			<tbody>
				<tr>
					<%-- 
					<th>项目名称：</th>
					<td>
						<input type="text" id="searchProjectName" name="searchProjectName" class="input-medium " title="支持多个，用英文逗号隔开" value="${searchProjectName}"/>
					</td>
					--%>
					<th>关键字：</th>
					<td>
						<input type="text" id="searchRemark" name="searchRemark" class="input-medium " title="例如南区等" value="${searchRemark}"/>
					</td>
					<th>授权用户：</th>
					<td>
						<input 	type="text" 
								name="curUserName" 
								id="curUserName" 
								class="input-medium " 
								style="cursor: pointer;" 
								value="${curUserName}"
								placeholder="点击选择要用户"
								/> 
						<input type="hidden" id="authUiid" name="authUiid" value="${authUiid}"/>
					</td>
					<td>
						<input type="button" class="btn_new btn_query_new fLeft" onclick="doQueryAuth();" value="搜索" />
						<input type="button" class="btn_new btn_clean_new fLeft" onclick="doClear();" value="清空条件" style="width: 80px;"/>
					</td>
				</tr>
				</tbody>
			</table> 
		</form>
	
 		<div style="width:100%;" id="searchResultList">
 			<%@include file="bid-project-role-list.jsp" %>
 		</div>
	</div>
	  
<script type="text/javascript">
	$(function(){
		
		$("#curUserName").userSelect({
	        muti:false,
	        nameField:'curUserName',
	        cdField:'authUiid'
		});
		
	});
	//保存项目权限
	function doProjectSave(dom,projectCd){
  	   
		var pcd = $("#id_"+projectCd).val();
		var userCds =$("#"+projectCd+"_UserCds").val();
		var remark =$("#"+projectCd+"_remark").val();
		
	   $('.tip').hide();
		$.post("${ctx}/bid/bid-project-role!save.action",{projectCd:pcd,userCds:userCds, remark: remark},function(result){
			var rs = result.split(',');
			if("success"==rs[0]){
			 	$(dom).parent().find('.tip').html("<font color='red'>保存成功!</font>").show();
			}else{
			 	$(dom).parent().find('.tip').html("<font color='red'>保存失败!</font>").show();
			}
		});
	}

	//搜索授权列表
	function doQueryAuth(){ 
		jumpPage(1);
	}

	function jumpPage(pageNo) {
		if(!pageNo){
			pageNo = 1;
		}
		$("#pageNo").val(pageNo);
		$('#searchResultList').mask('正在搜索,请稍候...');
		$('#searchForm').ajaxSubmit(function(result){
			$('#searchResultList').unmask();
			$('#searchResultList').html(result);
		});
	}
	function jumpPageTo() {
		var index = $("#pageTo").val();
		index = parseInt(index);
		if (index > 0) {
			jumpPage(index);
		}
	}
	function doClear(){
		//清除控件的值
		var	_userMap = {};
		var o = {userName:'',uiid:''};
		_userMap[$("#curUserName").val()] = o;
		var data = $.extend(true,{},null);
		$("#curUserName").data('userMap',data);
		
		$("#authUiid").val('');
		$("#curUserName").val('');
		$("#searchRemark").val('');
	}
	
	//刷新
	function refreshMain(){
		window.location.href = '${ctx}/bid/bid-project-role!main.action';
	}
	//打开
	function openMain(){
		window.open('${ctx}/bid/bid-project-role!main.action');
	}
</script>

</body>
</html>
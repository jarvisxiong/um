<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>缓存管理</title>
	<%@ include file="/common/global.jsp" %>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
</head>
<body class="easyui-layout" style="overflow:auto;background-color: white;"> 
	<div region="center" style="text-align: left;padding:20px;">
	 
		<div id="div_plas" style="width:100%;">
			<%--
			<div class="panel-header">PD缓存管理</div>
			<table>
				<col width="100px"/>
				<col />
				<tr>
					<td><input type="button" class="buttom" onclick="cleanCache('org')" value="刷新机构用户"/></td>
					<td><div id="processLoading_org"></div></td>
				</tr>
			</table>
			 --%>
			
			<div class="panel-header">PLAS缓存管理</div>
			<table>
				<col width="100px"/>
				<col />
				<tr>
					<td><input type="button" class="buttom" onclick="cleanCache('all')" value="刷新全部缓存(包括字典和参数)"/></td>
					<td><div id="processLoading_all"></div></td>
				</tr>
				<tr>
					<td><input type="button" class="buttom" onclick="cleanCache('dict')" value="刷新数据字典"/></td>
					<td><div id="processLoading_dict"></div></td>
				</tr>
				<tr>
					<td><input type="button" class="buttom" onclick="cleanCache('param')" value="刷新全局参数"/></td>
					<td><div id="processLoading_param"></div></td>
				</tr>
				<tr>
					<td><input type="button" class="buttom" onclick="setUserCd()" value="设置userCd"/></td>
					<td><div id="processLoading_usercd"></div></td>
				</tr>
				
			</table>
		</div>
		 
	</div>
		 
	
	<script type="text/javascript"> 
		function cleanCache(typeCd){
			if(window.confirm('您确定要刷新吗?')){
				$('body').mask('刷新中,请稍候...');
				var url = '${ctx}/bis/bis-center!cleanCache.action';
				var data = {typeCd: typeCd};
				$.post(url,data,function(result){
					$('body').unmask();
					$('#processLoading_'+typeCd).html('<div style="color:red;">'+ result +'</div>');
				});
			}
		}
		function setUserCd(typeCd){
			var typeCd = 'usercd';
			if(window.confirm('您确定要刷新吗?')){
				$('body').mask('刷新中,请稍候...');
				var url = '${ctx}/bis/bis-center!processNullUserCd.action';
				var data = {typeCd: typeCd};
				$.post(url,data,function(result){
					alert(result);
					$('body').unmask();
					$('#processLoading_'+typeCd).html('<div style="color:red;">'+ result +'</div>');
				});
			}
		}
	</script> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EAS用户管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>

</head>
<body class="easyui-layout">
	<div region="west" title='EAS人事管理<span align="right"><a href="${ctx}/plas/plas-append.action">返回树</a></span>
	<span align="right"><a href="${ctx}/plas/plas-append!load.action?status=1">刷新</a></span>' split="true"  style="width:220px;">
		<div id="leftPanel" style="height:100%;">
			<ul id="tt1" class="easyui-tree" animate="true" dnd="fasle">
			<li>
			<span>人事事务处理</span>
			<ul>
				<li>
					<span>入职管理</span>
					<ul>
						<li><span><a href="#" onclick="javascript:loadData('1');">新员工入职</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('2');">离职回岗</a></span></li>
					</ul>
				</li>
				<li state="closed">
					<span>转正管理</span>
					<ul>
						<li><span><a href="#" onclick="javascript:loadData('3');">新员工转正</a></span></li>
					</ul>
				</li>
				<li state="closed">
					<span>调配管理</span>
					<ul>
						<li><span><a href="#" onclick="javascript:loadData('4');">内部异动</a></span></li>
					</ul>
				</li>
				<li state="closed">
					<span>兼职管理</span>
					<ul>
						<li><span><a href="#" onclick="javascript:loadData('5');">兼职入职</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('6');">兼职免职</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('7');">兼职变动</a></span></li>
					</ul>
				</li>
				<li state="closed">
					<span>借调管理</span>
					<ul>
						<li><span><a href="#" onclick="javascript:loadData('8');">借调调出</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('9');">借调调入</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('10');">借调调回</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('11');">借调处理</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('12');">借调调回延时处理</a></span></li>
					</ul>
				</li>
				<li state="closed">
					<span>离职管理</span>
					<ul>
						<li><span><a href="#" onclick="javascript:loadData('13');">离职处理</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('14');">不在职处理</a></span></li>
					</ul>
				</li>
				<li state="closed">
					<span>离退休管理</span>
					<ul>
						<li><span><a href="#" onclick="javascript:loadData('15');">离退休处理</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('16');">返聘处理</a></span></li>
						<li><span><a href="#" onclick="javascript:loadData('17');">返聘解聘处理</a></span></li>
					</ul>
				</li>
			</ul>
			</li>
			</ul>
	</div>
	</div>
	<div region="center" split="true" style="+position: relative;overflow-x:hidden; padding:0 5px;">
		<!--<div style="padding:5px 0;">
			快捷搜索账号: 
			<input style="width:150px;" type="text" id="quickSearchField" name="quickSearchField" value="${quickSearchField}" title="支持账号或姓名或自定义名称搜索账号列表"/>
			<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
			<input type="button" onclick="reloadSearch()" value="搜索"></input>
			<%--
			<input type="button" onclick="cleanSearchField()" value="清空"></input>
			 --%>
		</div>
		--><div id="rightContainer" >
		
		</div>
	</div>
<script type="text/javascript">
function loadData(index){
	$('#rightContainer').html('<div style="height:100px;width:100%;">&nbsp;</div>').mask('正在载入信息,请稍候...');
	var url = "/plas/plas/plas-append!list.action";
	$.post(url, {status: "0",pages:index},function(result) {
		//if(result){
			$("#rightContainer").html(result);
			//很重要：json加载的页面，需要经过下列方法渲染页面
			$.parser.parse('#rightContainer');	
		//}
	});	
}
</script>
</body>
</html>
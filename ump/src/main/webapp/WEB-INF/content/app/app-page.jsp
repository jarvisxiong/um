<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
	<meta http-equiv="Content-Type" content="text/html" />
	<%@ include file="/common/global.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common.css" />	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/jquery-easyui/themes/icon.css"   />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
	
	<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/jquery/jquery-1.4.4.min.js"  ></script>
	<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/jquery-easyui/jquery.easyui.min.js"  type="text/javascript" ></script>
	<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	<!-- 遮罩 -->
	<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js" ></script>

</head>

<body class="easyui-layout" style="background-color:#F9F9F9;font-family:'宋体';font-size:12px;line-height:20px;maring:0;padding:0px 0px 30px;">
	<div region="west"  split="true"  style="width:200px;background-color:#F9F9F9;">
		<div id="leftPanel" >
			<ul id="leftTree"></ul>
		</div>
	</div>
	<div region="center" split="true" style="padding:5px;+position: relative;overflow-x:hidden;background-color:#F9F9F9; ">
		<div class="toolbar" style="height:30px;padding:5px;">
		
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="appendPage();" id="btnAppendPage">新增页面</a>
			<span id="userNumId" style="font-weight: bold;color:red;"></span>
		</div>
		<div id="rightContainer" >
		
		</div>
	</div>


<script language="javascript">
	$(function(){
		$('#btnAppendPage').hide();
		$('#leftTree').tree({
			url:'${ctx}/app/app-page!loadModuleMenuPageTree.action',
			onClick:function(node){
				var type = node.attributes.nodeType;
				var parentNode = $('leftTree').tree('getParent',node.target);
				if(type=='page'){
					loadPage(node.id,parentNode.id,parentNode.text);
				}else if(type=='menu'){
					var isLeaf = $('#leftTree').tree('isLeaf',node.target);
					if(isLeaf){$('#btnAppendPage').linkbutton('enable');}
				} 
				$('#leftTree').tree('toggle', node.target);
			},
			onLoadSuccess:function(){
				$('#leftTree').tree("collapseAll");
				var root = $('#leftTree').tree("getRoot");
				if(root){
					$('#leftTree').tree('expand',root.target);
				}
		
				
			}
		});
		loadPage('');
		
	});
	function appendPage(){
		var node =$('#leftTree').tree('getSelected');
		if(node)
		loadPage('',node.id,node.text);
		else{
		loadPage();
		}
	}
	function loadPage(id,menuId,menuName){
		var url = '${ctx}/app/app-page!input.action';
		var mId ;
		var mName;
		if(menuId){
			if(menuId!='0'){
				mId=menuId;
				if(menuName){
					mName=menuName;
				}
			}
		}
		$.post(url,{id:id,menuId:mId,menuName:mName},function(result){
			$('#rightContainer').show().html(result);
			//	$.parser.parse('#rightContainer');
			$('#btnAppendPage').show();
		
		});
	}
	function delPage(pageId){
		var url = '${ctx}/app/app-page!delete.action'
		$.post(url ,{id:pageId},function(result){
			if(result=='success'){
				$('#rightContainer').html('删除成功!').show().fadeOut(2000);
				$('#leftTree').tree('reload');
			}
		});
	}
</script>
</body>
</html>

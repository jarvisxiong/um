<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>网批节点和系统职务对应关系</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>

	<%--注意：jquery1.4以上，不适用eval构造json,直接(result)即可 --%>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/validate/jquery.validate.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
</head>
<body class="easyui-layout">

	<div region="west" title='网批节点列表' split="true" style="width:250px;">
		<div>
			<ul id="leftTree">
			<s:iterator value="resNodes" >
				<li style="line-height: 22px; cursor: pointer;" id="${nodeCd}">${nodeName}</li>
			</s:iterator>
			</ul>
		</div>
	</div>
	<div region="center" split="true" title="系统职位树" >
		<div style="width:100%;line-height: 30px;height: 30px;">
			<a id="btnSaveCenterTree" href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="saveBatchSysPos()">保存</a>
		</div>
		<div id="rightTree" style="min-height:450px;"></div>
	</div>
	<div region="east" split="true" title="详细信息" style="width:400px;">
	</div>
	<div id="wSaveConfirm"
		 class="easyui-window" closed="true" style="min-width:400px;height:500px;padding:5px;background: #fafafa;"
		 title="授权职位变化如下:"
		>
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopConfirm()" value="确定" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopConfirm()" value="取消" />
		</div>
		<div id="confirmDetailDiv">
			
		</div>
	</div>
<script type="text/javascript">
$(function(){
	$("#leftTree li").bind("click",function(){
		$(this).css("background","#FBEC88");
		$(this).siblings().css("background","none");
		var nodeCd=$(this).attr("id");
		loadRolePosRelRel(nodeCd);
	});
	$("#wSaveConfirm").window({
		onOpen:function(){
			$('body').mask();
		},
		onClose:function(){
			$('body').unmask();
		}
	});
});

var gOrgPosTree;
//最近选中nodeCd
var gNodeCd;

function loadRolePosRelRel(nodeCd){
	gNodeCd = nodeCd;
	
	$("#rightTree").html('<div style="height:100px;width:100%;">&nbsp;</div>').mask('正在载入节点职位关系树,请稍候...');
	
	var url = '${ctx}/plas/plas-node-sys-pos-rel!loadNodeSysPosRelTree.action';
	var data = {nodeCd: nodeCd};
	$.post(url, data, function(result) {
		$("#rightTree").html('').unmask();
		if (result) {
			gOrgPosTree = new TreePanel({
				renderTo: "rightTree",
				'root'  : ( result ),
				'ctx'	:'${ctx}'
			}); 
			//自定义
			gOrgPosTree.isDelegateIcon = true;
			gOrgPosTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};

			//修饰所有节点
			for(var k in gOrgPosTree.nodeHash){
				var node = gOrgPosTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				//系统职位
				if( nodeType== "3"){
					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
				}
				//机构
				else if( nodeType== "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
				else{
					node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
				}
			}
			gOrgPosTree.render(); 
			
			//单击事件
			gOrgPosTree.on(function(node){
				var id  = node.id;
				if( $.trim(id) == '' || $.trim(id)=='0'){
					return;
				}
				
				var nodeType = node.attributes.nodeType;
				//系统职位
				if( nodeType == "1"){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
			});
		}
	});
}
//批量保存授权系统职位信息
function saveBatchSysPos(){
	var modifyNodes = gOrgPosTree.getModifiedLeafNodes('3');//0-系统职位
	var addDels = getAddDeleteIds(modifyNodes);
	var addPosARoleIds = addDels[0];
	var delPosARoleIds = addDels[1];
	
	if((addPosARoleIds == '') && (delPosARoleIds == '') ){
		$.messager.alert('提示',"未发生变化!");
		return;
	}

	$("#confirmDetailDiv").html(getPopDiv());
	$("#wSaveConfirm").window('open');
}

//确认保存
function savePopConfirm(){
	var modifyNodes = gOrgPosTree.getModifiedLeafNodes('3');//菜单
	var addDels    = getAddDeleteIds(modifyNodes);
	var addPosARoleIds = addDels[0];
	var delPosARoleIds = addDels[1];

	$("body").mask('正在保存,请稍候...');
	var url='${ctx}/plas/plas-node-sys-pos-rel!saveBatchRoleSysPosRel.action';
	var data = {nodeCd : gNodeCd, addPosARoleIds: addPosARoleIds,delPosARoleIds:delPosARoleIds}
	$.post(url, data, function(result) {
		$("body").unmask();
		if(result == 'success'){
			//注意:必须重新加载菜单树,否则点击保存方法,仍可以保存.
			loadRolePosRelRel(gNodeCd);
			closePopConfirm();
			//$.messager.alert('提示','保存授权成功');
		}else{
			$.messager.alert('提示','保存授权失败');
		}
	});
	
}
//关闭
function closePopConfirm(){
	$('#wSaveConfirm').window('close');
}

//返回html片段
function getPopDiv(){
	var modifyNodes = gOrgPosTree.getModifiedLeafNodes('3');
	var addIds   = modifyNodes[0];
	var addTexts = modifyNodes[1];
	var delIds 	 = modifyNodes[2];
	var delTexts = modifyNodes[3];

	//新增部分
	var addDiv   = new Array();
		addDiv.push("<table style='min-width:150px;'>");
	for(var i=0; i<addIds.length; i++){
		addDiv.push("<tr><td valign='top' id='"+addIds[i]+"'>"+addTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
	}
		addDiv.push("</table>");

	//删除部分
	var delDiv  = new Array();
		delDiv.push("<table style='min-width:150px;'>");
	for(var i=0; i<delIds.length; i++){
		delDiv.push("<tr><td valign='top' id='"+delIds[i]+"'>"+delTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
	}
		delDiv.push("</table>");

	//弹出框
	var popDiv = new Array();
	popDiv.push("<table style='min-width:300px;border:1px solid #D2E0F2;' >");
	popDiv.push("	<tr class='panel-header'><th style='text-align:center;'>新增清单</th><th style='text-align:center;'>收回清单</th></tr>" );
	popDiv.push("	<tr><td valign='top'><div>"+ addDiv.join("")+"</div></td><td valign='top'><div>"+ delDiv.join("")+"</div></td></tr>");
	popDiv.push("</table>");

	return popDiv.join("");
}
//获取授权或收回的用户IDs
function getAddDeleteIds(modifyNodes){
	var addIds   = modifyNodes[0];
	var delIds 	 = modifyNodes[2];

	var strAddIds = "";
	var strDelIds = "";
	
	for(var i=0; i<addIds.length; i++){
		if(i>0){
			strAddIds +=',';
		}
		strAddIds += addIds[i];
	}

	for(var i=0; i<delIds.length; i++){
		if(i>0){
			strDelIds +=',';
		}
		strDelIds += delIds[i];
	}
	return [strAddIds,strDelIds];
}
</script>
</body>
</html>

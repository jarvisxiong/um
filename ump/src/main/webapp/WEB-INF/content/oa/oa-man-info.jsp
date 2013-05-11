<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/userChoose.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/res.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/oa/oa-man-info.css" />
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/table.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/userChoose.js"></script>
	<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/oa/oaManInfo.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
    <script type="text/javascript" src="${ctx}/js/validate/PdValidate.js" ></script>
	
	<title>合理化建议</title>
</head>

<body>
   <div id="idDivTitle" class="title_bar">
		
		<div style="float: left; font-size:14px;">
			<span>合理化建议列表</span> <span style="padding-left:10px;color:red;">当前选择：<span id="idCurAuthType"></span></span>
		</div>
		<div style="float:right;">
		    <div style="float:right;color:white;" class="full_screen" onmousemove="underline(this);" onmouseout="removeUnderline(this);" onclick="window.open('${ctx}/oa/oa-man-info.action')">
				全屏
			</div>
			<div style="float:right;" class="btn_cutline_3_26"></div>
			<div style="float:right;" class="btn_mylist" onclick="window.location.href='${ctx}/oa/oa-man-info.action'">
				<div style="margin-left:18px;" onmousemove="underline(this);" onmouseout="removeUnderline(this);">返回列表</div>
			</div>
			<div style="float:right;" class="btn_cutline_3_26"></div>
			
		</div>
	</div>
	
	
	
	<div id="idDivParent" class="divParent" style="width:100%;">
		<table style="width:100%;">
			<tr>
				<td id="leftPanel" style="width:180px;" valign="top">
					<div id="divTreeP" class="divTreeP"  style="overflow-x:auto;width:179px;border-width:0px 1px 0px 0px;border-style:solid;border-color:#8c8f94;background-color:#e4e7ec;">
						<div id ="tree-div">
						</div>
					</div>
				</td>
				<td id="rightPanel" valign="top">
					<div id="idDivContentPanel" style="width:100%;"> 
					<div id="contentList" style="border:0px;width:100%;">
					<div id="content" style="overflow-y:auto; width:100%;"> 
						<%@ include file="oa-man-info-load.jsp"%>
						<div id="tmpLoading" style="width:100%;border-top: 1px solid #AAABB0;"></div>
					</div>
					</div>
					</div>
				</td>
			</tr>
		
		</table>
	</div>
	<!-- 搜索框 -->
	<div id="searchUserDiv" class="searchUserDiv"></div>
	
	<script language="javascript">
	    resetLeftPanel();
		var dictCdVal="";
		var dictNameVal="";
		$("#dictCd").val("");
		$("#dictName").val("");	

		//加载页面功能树
		var curAuthTypeCd='';
	    var entityCd = '';
		var treePanel;
		
		$("#tree-div").html('<div><image src="${ctx}/resources/images/common/loading.gif"/>加载数据...</div>');
		$.post("${ctx}/oa/oa-man-info!buildTree.action",{active:'1'}, function(result) {
			$("#tree-div").empty();
			if (result) {
				var arr=eval('('+result+')');
				root=arr;
				treePanel = new TreePanel({
					renderTo:'tree-div',
					'root' : root,
					'ctx':'${ctx}'
				});
				treePanel.render();
				treePanel.isExpendSelect = true;
				treePanel.on(function(node){
					var tmp = node.attributes.entityCd;
					if("0" == tmp){
						$("#btnNewApply").attr("disabled","disabled");
						$("#dictCd").val("");
						curAuthTypeCd='';
						$("#idCurAuthType").html('');
						return;
					}
					
					//模块
					if(node.attributes.nodeType == "module"){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
					
					//分类
					if(node.attributes.nodeType == "authType"){
						var itemCd=node.attributes.entityCd;
						curAuthTypeCd=itemCd;
						entityCd = node.parentNode.attributes.entityCd;
						$("#proposalCatCd").val(entityCd);
						$("#dictCd").val(curAuthTypeCd);

						$.post("${ctx}/oa/oa-man-info!load.action",
								{
									dictCd:curAuthTypeCd,
									proposalCatCd:entityCd
								},
							 function(result) {
								$("#content").html(result);
								$("#idCurAuthType").html(node.attributes.text);
								resetLeftPanel();
						});
					}else{
						$("#btnNewApply").attr("disabled","disabled");
						$("#resAuthTypeCd").val("");
						curAuthTypeCd='';
						$("#idCurAuthType").html('');
					}
				});

				var tmpRoot = treePanel.getRootNode();
				if(tmpRoot.getChildNodes().length > 0){
					(tmpRoot.getChildNodes()[0]).expand();
				}
			
				$("#tmpLoading").html('<div><image src="${ctx}/resources/images/common/loading.gif"/>加载数据...</div>');
			}
		});
		function getValidValue(value){
			if(typeof(value)=='undefined'){
				value='';
			}
			return value;
		} 

		
		$(function(){
			var id='${id}';
			if(!isEmpty(id)){
				openDetail('${dictCd}','${proposalCatCd}',id,'myApprove','1','');
			}else{
				listMy(1);
			}
		});
		function underline(dom){
			$(dom).css('text-decoration','underline');
		}

		function removeUnderline(dom){
			$(dom).css('text-decoration','none');
		}
	</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<%@ include file="/common/global.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 	
	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cost/cost.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/css/common/select.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/js/jqueryplugin/jqModal/jqModal.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
</head>
<body>
	<div id="mailMianContainer" >
	<div class="title_bar">
		<div style="float:left;margin-right: 5px;">
			<img src="${ctx}/images/fin/pic_Supplier.gif" style="margin-top:3px;"/>
		</div>
		<div style="float:left;">
			项目授权维护
		</div>
		<div style="float:right;width:50px;" class="btn_refresh" onclick="refreshProject();">刷新</div>
	</div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:100%;">
		<table style="width:100%;">
		<tr>
		<td id="leftPanel" style="width:100px;border-bottom:1px solid #8C8F94; border-right:1px solid #8c8f94;background-color: #E4E7EC;" valign="top">
			<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
				<tr>
					<td>
			  			<div id="itemDiv" style="padding-top:5px;min-width:200px;height:450px; width: 100%; overflow-x: hidden; overflow-y:auto;">加载中...</div>
					</td>
					<td style="width:5px;">
						<div id="noteResizeHandler" class="pd-chill-tip" title="您可以拖动,调整宽度" style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;</div>
					</td>
				</tr>
			</table>
		</td>
		<td id="rightPanel" valign="top" rowspan="2">
			<form action="${ctx}/cost/cost-budget-auth!list.action" method="post" id="searchForm">
				<%--翻页：很重要 --%>
				<input type="hidden" id="pageNo" name="pageNo" />
				<%--搜索条件 --%>
	    		<table id="search_condtion">
					<tr class="line">
						<td style="padding-left: 10px;">项目名称：</td>
						<td>
							<input type="text" id="sectionName" name="sectionName" class="text" title="支持多个，用英文逗号隔开" style="width:80px;"/>
							<%-- 
							<input type="text" id="quickSearchSection" name="quickSearchSection" class="text" title="支持项目公司、项目名称、项目编号搜索"/>
							<input type="hidden" id="costProjectSectionId" name="costProjectSectionId"/>
							--%>
						</td>
						<td style="padding-left: 10px;">关键字：</td>
						<td>
							<input type="text" id="remark" name="remark" class="text" title="例如南区等" style="width:80px;"/>
							<%-- 
							<input type="text" id="quickSearchSection" name="quickSearchSection" class="text" title="支持项目公司、项目名称、项目编号搜索"/>
							<input type="hidden" id="costProjectSectionId" name="costProjectSectionId"/>
							--%>
						</td>
						<td style="padding-left: 10px;">授权用户：</td>
						<td>
							<input type="text" name="curUserName" id="curUserName" readonly="readonly" class="text" style="cursor: pointer;width:80px;" title="点击选择要用户"/> 
							<input type="hidden" id="authUiid" name="authUiid"/>
						</td>
						<td style="padding-left: 10px;">
							<input type="button" class="fLeft btn_new btn_query_new" onclick="doQueryAuth();" value="搜索" style="width:40px"/>
							<input type="button" class="fLeft btn_new btn_clean_new" onclick="doClear();" value="清空条件" style="width:60px"/>
						</td>
						<!--
						<td style="padding-left: 10px;">项目编号：</td>
						<td>
							<input type="text" id="sectionBizCd" name="sectionBizCd" class="text"/>
							<input type="button" id="ModBtn" class="searchBtn" onclick="doQuerySection();" value="搜索" style="cursor: pointer;margin-left: 5px;"/>
						</td>
						-->
					</tr>
				</table>
			 	<%-- 搜索结果列表 --%>
			 	<div id="costBudgetAuthList" style="margin-top: 10px;">
			 	  	
				</div>
			</form>
	    </td>
	    </tr>
	    </table>
	</div>
</div>

<script type="text/javascript">
var arrCheck ="";//存储树形结构节点值
$(function(){
	//初始化树形结构
	getBudgetTree("itemDiv");
	//左右拖拉
    $('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 200, height: ($('#leftPanel').height()) },
        max: { width: 400, height: ($('#leftPanel').height()) },
        onResize: function(e) {
        	//$('#divTreeP').width($('#leftPanel').width()-5);
        }
    });
  	//注册快速搜索(模糊匹配:projectName,sectionName,sectionBizCd)
	/*$('#quickSearchSection').quickSearch(
		'${ctx}/cost/cost-project-section!quickSearchSection.action',
		['projectName','sectionName','sectionBizCd'],
		{costProjectSectionId:'costProjectSectionId',sectionName:'quickSearchSection'},'',
		function(result){},
		{'sectionBizCd':'quickSearchSection','sectionName':'quickSearchSection','projectName':'quickSearchSection'}
	);*/
	//选择人
	$("#curUserName").userSelect({
	        muti:false,
	        nameField:'curUserName',
	        cdField:'authUiid'
	});
	
	//默认搜索
	doQueryAuth();
});
function getBudgetTree(itemDiv){
	$.post("${ctx}/cost/cost-project-section!getSectionTree.action", function(result){
		$('#'+itemDiv).empty();
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		//alert(tree.getId());
		tree.addListener("check",function(node){
			arrCheck = tree.getCheckedId();
		});
		tree.render();
		tree.on(function(node){
			var nodeType = node.attributes.nodeType;
			if(nodeType == '1'){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}
			}
		});
	});
}

//搜索授权列表
function doQueryAuth(){
	var costProjectSectionId = $("#costProjectSectionId").val();
	if(costProjectSectionId == null || costProjectSectionId == ''){
		costProjectSectionId = arrCheck;
	}
	var authUiid = $("#authUiid").val();
	var sectionName = $("#sectionName").val();
	var remark = $("#remark").val();
	$('#searchUserDiv').hide();
	$('#costBudgetAuthList').empty();
	TB_showMaskLayer("正在搜索,请稍候...");
	$.post("${ctx}/cost/cost-budget-auth!list.action",
		{
			costProjectSectionId:costProjectSectionId, 
			sectionName:sectionName,
			authUiid:authUiid,
			remark: remark
		},function(result) {
			TB_removeMaskLayer();
			if (result) {
				$("#costBudgetAuthList").html(result);
				//$('#itemDiv').height($('#rightPanel').height()-$('#btnLeftQuery').height());
			}
		});
}

function jumpPage(pageNo) {
	if(!pageNo){
		pageNo = 1;
	}
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索,请稍候...");
	$("#searchForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#costBudgetAuthList").html(result);
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
	
	$("#sectionName").val('');
	$("#authUiid").val('');
	$("#curUserName").val('');
	$("#remark").val('');
}


function refreshProject(){
	window.location.href = _ctx + "/cost/cost-budget-auth!main.action";
}
</script>
</body>
</html>

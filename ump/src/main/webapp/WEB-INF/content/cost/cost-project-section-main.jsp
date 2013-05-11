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
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/css/common/select.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="/PowerDesk/resources/js/jqueryplugin/jqModal/jqModal.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
</head>
<body>
	<div id="mailMianContainer" >
	<div class="title_bar">
		<div style="float:left;margin-right: 5px;">
			<img src="${ctx}/images/fin/pic_Supplier.gif" style="margin-top:3px;"/>
		</div>
		<div style="float:left;">
			项目设置
		</div>
		<div style="float:right;">
			<div class="btn_cutline_3_26"></div>
			<div style="float:left;width:50px;" class="btn_refresh" onclick="refreshProject();">刷新</div>
			<div style="float:left;width:50px;" class="btn_refresh" onclick="doQuery();">搜索</div>
			<security:authorize ifAnyGranted="A_COST_BUD_ADMIN">
		  	<div style="float:left;width:70px;" class="btn_refresh" onclick="doInitSection();" title="从合同台账同步项目公司和项目期数">同步项目</div>
			</security:authorize>
		</div>
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
			<%--搜索条件 --%>
			<form action="${ctx}/cost/cost-project-section!list.action" method="post" id="searchForm">
	    		<table id="search_condtion" id="searchTab">
				<tr class="line">
					<td style="padding-left: 10px;">项目名称：</td>
					<td>
						<input type="text" id="secName" name="sectionName" style="width: 120px;" class="text" title="支持模糊匹配"/> 
						<%--
						<input type="text" id="proName" name="proName" readonly="readonly" style="cursor: pointer;width: 150px;" class="text" title="点击选择项目公司"/> 
						<input type="hidden" id="proCd" name="projectCd" value=""/>(请选择)
						--%>
						
					</td>
					<td style="padding-left: 10px;">备注(关键字)：</td>
					<td>
						<input type="text" id="inp_remark" name="remark" style="width: 120px;" class="text" title="支持模糊匹配"/> 
						<%--
						<input type="text" id="proName" name="proName" readonly="readonly" style="cursor: pointer;width: 150px;" class="text" title="点击选择项目公司"/> 
						<input type="hidden" id="proCd" name="projectCd" value=""/>(请选择)
						--%>
						
					</td>
					<td style="padding-left: 10px;">
						<input type="button" class="btn_new btn_query_new" onclick="doQuerySection();" value="搜索"/>
						<input type="button" class="btn_new btn_clean_new" onclick="doClear();" value="清空条件" style="width:70px;"/>
					</td>
				</tr>
				</table>
				<%-- 搜索结果列表 --%>
			 	<div id="costProjectSectionList" style="margin-top: 10px;">	  
			 		
				</div>
			</form>
			<%-- 新增div --%>
			<div id="addDiv" style="display: none;">
				
			</div>
	    </td>
	    </tr>
	    </table>
	</div>
</div>


<script type="text/javascript">
//存储树形结构节点值
var arrCheck ="";

$(function(){
	//初始化树形结构
	getSectionTree("itemDiv");
	//左右拖拉
    $('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 200, height: ($('#leftPanel').height()) },
        max: { width: 400, height: ($('#leftPanel').height()) },
        onResize: function(e) {
        }
    });
  	//选择项目公司-搜索
	/*$('#proName').orgSelect({
		muti:true,
		showProjectOrg : true,
		orgTreeType : '2'
	});*/
	
	//搜索项目
    doQuerySection();
});

//刷新项目
function refreshProject(){
	window.location.href = _ctx + "/cost/cost-project-section!main.action";
}
//加载项目树
function getSectionTree(itemDiv){
	$.post("${ctx}/cost/cost-project-section!getSectionTree.action", function(result){
		$('#'+itemDiv).empty();
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
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

//搜索项目
function doQuerySection(){
	$("#addDiv").hide();
	$("#costProjectSectionList").show();
	var sectionName = $("#secName").val();
	var remark = $("#inp_remark").val();
	$('#costProjectSectionList').empty();
	TB_showMaskLayer("正在搜索,请稍候...");
	$.post("${ctx}/cost/cost-project-section!list.action",
		{
			ids:arrCheck,
			sectionName:sectionName,
			remark:remark
		},function(result) {
			TB_removeMaskLayer();	
			if (result) {
				$("#costProjectSectionList").html(result);
				//$('#itemDiv').height($('#rightPanel').height()-$('#btnLeftQuery').height());
			}
		});
}

//新增项目
function doAddSection(id){
	var param = {};
	if(id != null && id !=''){
		param = {id:id};
	}
	$.post("${ctx}/cost/cost-project-section!input.action",param,function(result) {
		if (result) {
			$("#addDiv").html(result);
			if($("#addDiv").is(":hidden")){
				$("#addDiv").show();
				$("#costProjectSectionList").hide();
			}else{
				$("#addDiv").hide();
				doQuerySection();
				$("#costProjectSectionList").show();
			}
		}
	});
}
//初始化项目(从合同台账导入，仅执行一次)
function doInitSection(){
	if(!window.confirm('确认执行?')){
		return;
	}
	$.post("${ctx}/cost/cost-project-section!importSection.action",{},function(result) {
		alert( result);	
		refreshProject();
	});
}
function doQuery(){
	if(!$("#addDiv").is(":hidden")){
		$("#addDiv").hide();
	}
	if($("#search_condtion").css("display")=="none"){
		$("#search_condtion").show();
	}else{
		$('#search_condtion').hide();
	}
}
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索,请稍候...");
	$("#searchForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();	
		$("#costProjectSectionList").html(result);
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
	$("#secName").val('');
	$("#inp_remark").val('');
}

</script>
</body>
</html>

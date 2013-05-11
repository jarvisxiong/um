<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	
	<style type="text/css">
		#search_condtion{
			padding:5px 10px;
			font-weight: normal;
			width:100%;
			padding:5px;
			border-bottom:1px solid #cbcbcb;
		}
		#search_condtion th{
			padding:2px 5px;
			text-align: right;
			font-weight: normal;
		}
	</style>
</head>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle">	
			战略价格平台
			<security:authorize ifAnyGranted="A_COST_STRAGE_VIEW,A_COST_STRAGE_ADMIN">
				<input type="button" class="btn_new btn_blue_new" style="width:80px;" onclick="doCostMateModule();" value="设置" />
			</security:authorize>
		</div>
		<div class="fRight">
			<security:authorize ifAnyGranted="A_COST_STRAGE_VIEW,A_COST_STRAGE_ADMIN">
				<input type="button" class="btn_new btn_senior_new" style="width:70px;" onclick="doQuery();" value='高级搜索' />
			</security:authorize>	
			<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
				<input type="button" class="btn_new btn_add_new" style="width:70px;" onclick="doAddCostMate();" value="新增设备"/>
			</security:authorize>
			<security:authorize ifAnyGranted="A_COST_STRAGE_ADMIN">
				<input type="button" class="btn_new btn_blue_new" style="width:70px;" title="请选择左侧某一类型导入" onclick="doImportCostMate();" value="手工导入"/>
			</security:authorize>
			<security:authorize ifAnyGranted="A_COST_STRAGE_VIEW,A_COST_STRAGE_ADMIN">
				<input type="button" class="btn_new btn_blue_new" style="width:90px;" onclick="caigou();" value="查看战略下单" />
			</security:authorize>
			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
		</div>
	</div>
	
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:100%;border-bottom:1px solid #cbcbcb; ">
		<table style="width:100%;">
		<tr>
		<td id="leftPanel" style="width:200px;border-right:1px solid #cbcbcb;" valign="top">
			<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
				<tr>
					<td>
			  			<div id="itemDiv" style="padding-top:5px;min-height:450px; width: 100%; overflow-x: hidden; overflow-y:auto;">加载中...</div>
					</td>
					<td style="width:5px;">
						<div id="noteResizeHandler" class="pd-chill-tip btn_drag" title="您可以拖动,调整宽度">&nbsp;</div>
					</td>
				</tr>
			</table>
		</td>
		<td id="rightPanel" valign="top" rowspan="2">
			<form action="${ctx}/cost/cost-mate!list.action" method="post" id="searchForm">
	    	<div style="height: 100%;" id="search_condtion">
				<table cellpadding="0" cellspacing="0" border="0" style="line-height: 22px;">
				<tr>
					<th>设备名称</th>
					<td><input type="text" id="mateName" name="mateName" style="width:120px"/></td>
					<th>设备编号</th>
					<td><input type="text" id="mateBizCd" name="mateBizCd" style="width:120px" /></td>
				</tr>
				<tr>
					<th>价格计算方式</th>
					<td><select style="width: 120px;" id="calcTypeCd" name="calcTypeCd" >
							 <option value=""></option>
							 <option value="1">定量</option>
							 <option value="2">公式</option>
							 <option value="3">浮动</option>
						</select>
					</td>
					<th>是否启用</th>
					<td><select style="width: 120px;" id="enableFlg" name="enableFlg" >
							 <option value=""></option>
							 <option value="0">否</option>
							 <option value="1">是</option>
						</select>
					</td>
					<td>
						<input type="button" class="btn_new btn_query_new" onclick="doQueryMate();" value="搜索" />
						<input type="button" class="btn_new btn_clean_new" onclick="doClear();" value="清空条件" style="width:70px;"/>
					</td>
				</tr>
				</table>
			</div>
			 	<div id="costMateList" style="padding-left: 5px;">	  
			 		<%-- 搜索结果列表 --%>
				</div>
			</form>
			<div id="searchUserDiv" class="searchUserDiv">
				<div class="inform_div" >请选择左侧目录搜索</div>
			</div>
	    </td>
	    </tr>
	    </table>
	</div>


<script type="text/javascript">
var arrCheck ="";//存储树形结构节点值
$(function(){
	getMateTree("itemDiv");//初始化树形结构
	//左右拖拉
    $('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 200, height: ($('#leftPanel').height()) },
        max: { width: 400, height: ($('#leftPanel').height()) },
        onResize: function(e) {
        	//$('#divTreeP').width($('#leftPanel').width()-5);
        }
    });
	
	//默认查询
    jumpPage(1);
});

/**
 *getMateTree
 *初始化材料设备分类树
 * itemDiv 所在页面位置
 */
function getMateTree(itemDiv){
	var url = "${ctx}/cost/cost-mate-module!getCostMateModuleTreeCheck.action";
	$.post(url, function(result){
		$('#'+itemDiv).empty();
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.addListener("check",function(node){
			arrCheck = tree.getAllCheckedId();
		});
		tree.render();
		tree.on(function(node){
			var nodeId = node.attributes.id;
			var nodeType = node.attributes.nodeType;
			if(nodeType != '0'){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}
			}
			//点击根节点不去搜索 nodeId=0为根节点
			if(nodeId != "0"){
				doQueryMateById(nodeId);
			}
		});
		
		//默认展开1级节点
		tree.getRootNode().expandDeeps(1); 
	});
}

//搜索材料设备
function doQueryMate(pId){
	var mateBizCd = $("#mateBizCd").val();
	var mateName = $("#mateName").val();
	var enableFlg = $("#enableFlg").val();
	var calcTypeCd = $("#calcTypeCd").val();
	$('#searchUserDiv').hide();
	$('#costMateList').empty();
	TB_showMaskLayer("正在搜索,请稍候...");
	var data = {
				parentModuleId:arrCheck, 
				mateName:mateName, 
				mateBizCd:mateBizCd,
				enableFlg:enableFlg,
				calcTypeCd:calcTypeCd
			};
	$.post("${ctx}/cost/cost-mate!list.action",data,function(result) {
			TB_removeMaskLayer();
			if (result) {
				$("#costMateList").html(result);
			}
		});
}
//单个搜索
function doQueryMateById(pId){
	$('#searchUserDiv').hide();
	TB_showMaskLayer("正在搜索,请稍候...");
	$.post("${ctx}/cost/cost-mate!list.action",{parentModuleId:pId},function(result) {
		TB_removeMaskLayer();
		if (result) {
			$("#costMateList").html(result);
		}
	});
}

//新增材料设备信息
function doAddCostMate(){
	var url="${ctx}/cost/cost-mate!input.action";
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-mate-input","新增设备信息",url,true);
}
function doOrder(){
	var url="${ctx}/cost/cost-mate!doOrder.action";
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-mate-order","网上下单",url,true);
}

//设备类型维护
function doCostMateModule(){
	var url="${ctx}/cost/cost-mate-module!main.action";
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cost-mate-module-main","设备类型维护",url,true);
}

//导入excel设备数据
function doImportCostMate(){
	//parentId在cost-mate-list.jsp
	var parentId = new Array();
	$("input[id='parentId']").each(function(){
		parentId.push($(this).val());
	});
	if(parentId.length != 1){
		alert("请选择左侧的某一类型导入");
		return false;
	}
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='costMateImport'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
		width : 330,
		height : 135,
		title : "导入设备信息",
		closeBtn:true,
		afterShow : function() {
			var url = "${ctx}/cost/cost-mate!costMateImport.action";
			$.post(url,{parentId : parentId.toString()}, function(result) {
				$("#costMateImport").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				if($("#file").val()=="") {
					alert("请先选择要导入的文件");
					$("#file").focus();
					return false;
				}
				TB_showMaskLayer("正在导入...");
				$("#costMatetUplodForm").ajaxSubmit(function(result){
					TB_removeMaskLayer();
					var msg = result.split(",");
					if(msg[1] == "success") {
					    alert("导入成功,耗时"+msg[2]+"秒");
					    $("#costMateUplodForm").val("");
					    doQueryMateById(parentId); //刷新当前页
					} else {
						alert(msg[2]);
					}
				});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}

//导入excel 类型数据
function doImportModule(){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='costMateModuleImport'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
		width : 330,
		height : 110,
		title : "导入设备类型信息",
		closeBtn:true,
		afterShow : function() {
			var url = "${ctx}/cost/cost-mate-module!costMateModuleImport.action";
			$.post(url,{}, function(result) {
				$("#costMateModuleImport").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				if($("#file").val()=="") {
					alert("请先选择要导入的文件");
					$("#file").focus();
					return false;
				}
				TB_showMaskLayer("正在导入...");
				$("#costMateModuleUplodForm").ajaxSubmit(function(result){
					TB_removeMaskLayer();
					var msg = result.split(",");
					if(msg[1] == "success") {
					    //alert("导入成功,耗时"+msg[2]+"秒");
					    $("#costMateModuleImport").val("");
					    refresh();//刷新当前页
					} else {
						alert(msg[2]);
					}
				});
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["取消",'cancel']]
	});
}

function doQuery(){
	if($("#search_condtion").is(":hidden")){
		$("#search_condtion").show();
	}else{
		$('#search_condtion').hide();
	}
}
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#searchForm").ajaxSubmit(function(result) {
		$("#costMateList").html(result);
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
	$("#mateBizCd").val('');
	$("#mateName").val('');
	$("#enableFlg").val('');
	$("#calcTypeCd").val('');
}
function refresh(){
	window.location.href="${ctx}/cost/cost-mate!main.action";
}

function caigou(){
	var url="${ctx}/cost/cost-strage-mate!main.action";
	if(parent && parent.TabUtils){
	  	parent.TabUtils.newTab("cost-strage-mate-main","战略材料采购库",url,true);
	}
	else{
		window.open(url);
	}
}
</script>
</body>
</html>

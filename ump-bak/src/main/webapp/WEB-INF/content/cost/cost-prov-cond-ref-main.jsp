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
	<link type="text/css" rel="stylesheet" media="screen" href="/PowerDesk/resources/css/common/select.css"/>
	<link type="text/css" rel="stylesheet" media="screen" href="/PowerDesk/resources/js/jqueryplugin/jqModal/jqModal.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cost/cost.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/customInput/customInput.css"  />
	
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
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
	<style type="text/css">
		#search_condtion{
		
		}
		#search_condtion th{
			text-align: right;
			width:80px;
			font-weight: normal;
		}
	</style>
</head>
<body>
<input type="hidden"  id="toRefleshLeftTreeBtn" onclick='getSectionTree("itemDiv");' title="本表单仅作绑定click事件使用"/>
	<div id="mailMianContainer" >
	<div class="title_bar">
		<div class="fLeft banTitle">
			入库供方试用评级资格
		</div>
		<div class="fRight">
			<input type="button" class="btn_new btn_senior_new" onclick="doQuery();" value="高级搜索" style="width:70px;"/>
			<security:authorize ifAnyGranted="A_CONT_COND_EDIT">
			<input type="button" class="btn_new btn_add_new" onclick="doAddCostProvcondRef();" value="新增"/>
			</security:authorize>
  			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
  			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />	
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
			  			<div id="itemDiv" style="padding-top:5px;min-width:150px;height:450px; width: 100%; overflow-x: hidden; overflow-y:auto;">加载中...</div>
					</td>
					<td style="width:5px;">
						<div id="noteResizeHandler" class="pd-chill-tip btn_drag" title="您可以拖动,调整宽度">&nbsp;</div>
					</td>
				</tr>
			</table>
		</td>
		<td id="rightPanel" valign="top" rowspan="2">
			<%--搜索条件 --%>
			<form action="${ctx}/cost/cost-prov-cond-ref!list.action" method="post" id="searchForm">
	    		<table id="search_condtion" >
				<tr class="line">
					<th style="width:80px;">分类：</th>
					<td>
						<input type="text" id="type_nameId" name="type_name" style="width: 120px;" class="text" title="支持模糊匹配"/> 
					</td>
					<th>供方内容：</th>
					<td>
						<input type="text" id="prov_descId" name="prov_desc" style="width: 120px;" class="text" title="支持模糊匹配"/> 
					</td>
				  	<th>专业资质：</th>
				  	<td><input type="text" id="prof_qual_desc" name="prof_qual_desc" style="width: 120px;" class="text" title="支持模糊匹配"/></td>
				</tr>
				<tr class="line">
				  <th>行业排名：</th>
				  <td><input type="text" id="indu_rank_desc" name="indu_rank_desc" style="width: 120px;" class="text" title="支持模糊匹配"/></td>
				  <th>企业业绩：</th>
				  <td><input type="text" id="ente_perf_desc" name="ente_perf_desc" style="width: 120px;" class="text" title="支持模糊匹配"/></td>
				  <th>体系认证：</th>
				  <td><input type="text" id="syst_cert_desc" name="syst_cert_desc" style="width: 120px;" class="text" title="支持模糊匹配"/></td>
			     </tr>
			     <tr>
			     	<td></td>
			     	<td colspan="5">
						<input type="button" id="btnSearchcostInfo" class="btn_new btn_query_new" onclick="doQuerySection();" value="搜索" />
						<input type="reset" id="ModBtn" class="btn_new btn_clean_new" value="清空条件" style="width:70px;"/>				
			     	</td>
			     </tr>
				</table>
				<%-- 搜索结果列表 --%>
			 	<div id="queryResult" style="margin-top: 5px;">	  
			 		<div class="inform_div">当前没有数据显示</div>
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
});

//刷新项目
function refreshProject(){
	window.location.href = _ctx + "/cost/cost-prov-cond-ref!main.action";
}
//加载项目树
function getSectionTree(itemDiv){
	$.post("${ctx}/cost/cost-prov-cond-ref!getCostProvCondrefTree.action", function(result){
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
	$("#queryResult").show();
	var typename = $("#type_nameId").val();
	var provdesc = $("#prov_descId").val();
	var profqualdesc = $("#prof_qual_desc").val();
	var indurankdesc = $("#indu_rank_desc").val();
	var enteperfdesc = $("#ente_perf_desc").val();
	var systcertdesc = $("#syst_cert_desc").val();
	$('#queryResult').empty();
	TB_showMaskLayer("正在搜索,请稍候...");
	$.post("${ctx}/cost/cost-prov-cond-ref!list.action",
		{
			ids:arrCheck,
			typename:typename,
			provdesc:provdesc,
			profqualdesc:profqualdesc,
			indurankdesc:indurankdesc,
			enteperfdesc:enteperfdesc,
			systcertdesc:systcertdesc
		},function(result) {
			TB_removeMaskLayer();	
			if (result) {
				$("#queryResult").html(result);
			}
		});
}

//新增项目
function doAddCostProvcondRef(id){
	var param = {};
	if(id != null && id !=''){
		param = {id:id};
	}
	$.post("${ctx}/cost/cost-prov-cond-ref!input.action",param,function(result) {
		if (result) {
			if($("#addDiv").is(":hidden")){
				$("#search_condtion").hide();
				$("#queryResult").html("");
				$("#addDiv").show();
				$("#addDiv").html(result);
			}else{
				$("#addDiv").hide();
				$("#search_condtion").show();
				$("#queryResult").html("");
			}
		}
	});
}
//初始化项目(从合同台账导入，仅执行一次)
function doInitSection(){
	if(!window.confirm('确认执行?')){
		return;
	}
	$.post("${ctx}/cost/cost-prov-cond-ref!importSection.action",{},function(result) {
		refreshProject();
	});
}
function doQuery(){
	$("#addDiv").hide();
	$("#search_condtion").toggle();
/**	if($("#search_condtion").is(":hidden")){
		$("#search_condtion").show();
	}else{
		$("#search_condtion").hide();
	}
	**/
}
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	TB_showMaskLayer("正在搜索,请稍候...");
	$("#searchForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();	
		$("#queryResult").html(result);
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

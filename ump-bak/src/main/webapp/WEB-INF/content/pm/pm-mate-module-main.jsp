<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	
	<script>
		var _ctx = '${ctx}';
	</script>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/base.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/assm/assm.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/assm/style.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"/>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js" ></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/pm/pm-mate-main.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/pm/pmdetail.js"></script>
	<style>
	#header .condition_panel ul li.buttons button{
		float: left;
    	margin-right: 5px;
    	width: 65px;
	}
	</style>
</head>
<body>
	<div id="header">
		<div class="title1 clearfix">
			<h2 style="width: auto;float: left;">商业企划案例库</h2>
			<div class="btns">
				<input id="mateEntry_quick" class="text" name="mateEntry_quick" onblur="resetQuickSearch(this);" onclick="clearQuickSearch(this);" onkeyup="quickSearchModel();" value="快速搜索..." title="支持主题活动搜索" style="float: left;margin-top:3px; padding: 2px;width: 150px;background:#FFF url(/PowerDesk/resources/images/desk2/search.png) no-repeat 136px 1px;color:#cccccc;"/>
				<button id="entrySearchBtu" class="gray" style="margin-left: 5px;" onclick="showEntrySearchDiv();" type="button">高级搜索</button>
			</div>
		</div>
	</div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="position: absolute;bottom:0;top:39px;width:100%;overflow-x:hidden;overflow-y:auto;">
		<table style="width:100%;height: 100%;">
			<tr>
				<td id="leftPanel" style="width:160px;border-right: 1px solid #8c8f94;background-color:#e4e7ec;" valign="top">
					<%-- 左边企划案例库树  --%>
					<table cellpadding="0" cellspacing="0" border="0" style="width:100%;">
						<tr>
							<td>
					  			<div id="itemDiv" style="height:100%;padding-top: 5px; float: left; overflow-y: auto; overflow-x: hidden;border: none;">加载中...</div>
							</td>
							<td style="width:5px;">
								<div id="noteResizeHandler" class="pd-chill-tip btn_drag" title="您可以拖动,调整宽度" style="float:right; width:5px;height:100%;background: #eee url('${ctx}/resources/images/common/width_resize.gif') left center no-repeat;cursor: w-resize;">&nbsp;</div>
							</td>
						</tr>
					</table>
				</td>
				
				<%--右边顶部显示div --%>
				<td id="rightPanel" valign="top" style="margin: 10px 5px 0px 10px;" rowspan="2">
				   <div id="mainDiv">
						<form action="${ctx}/pm/pm-mate-entry!list.action" method="post" id="searchForm">
							<div id="header">
								<div id="searchDiv" class="form_body condition_panel none">
									<%--企划案例搜索 --%>
				          			<ul class="clearfix" id="searchEntryDiv" style="display: none;">
					                    <li>
											<label style="width: 60px;">活动主题：</label>
											<input type="text" 
												   class="text max"
												   style="cursor:pointer;width: 315px;"
											       id="pm_activeTitle" 
											       name="pm_activeTitle"
											       />
											<input type="hidden" id="entry_quick_id"/>
					                       	<label>时间周期：</label><input type="text" class="text" id="pm_activePeriod" name="pm_activePeriod"/>
										</li>
										<li>
											<label style="width: 60px;">预期效果：</label><input type="text" class="text" id="pm_expectedResults" name="pm_expectedResults"/>
											<label>费用预算：</label><input type="text" class="text" id="pm_expensesBudget_min" name="pm_expensesBudget_min"/>
											<label>到：</label><input type="text" class="text" name="pm_expensesBudget_max" id="pm_expensesBudget_max" style="cursor: pointer;"/>
										</li>
										<li class="buttons clearfix">
					                        <button type="button" style="float:left;" class="blue min" onclick="doQuery();">搜索</button>
					                        <button type="button" style="float:left;" class="green min" onclick="doClear();">清空</button>
											<button type="button" class="red min" onclick="showEntrySearchDiv();">取消</button>
				                  		</li>
					           		</ul>
								</div>
							</div>	
							<div class="res_tip" style="margin:10px 10px 0px 10px;">
								<span id="titleSpan" style="margin-left:10px;font-size: 16px;font-weight: bold;">企划案例库</span>
								<span id="treeV" style="display:none;margin-left: 10px;">
									
								</span>
							</div>
							<%--企划案例新增、删除按钮 --%>
							<div style="padding: 10px 0;margin-left:10px;" id="pmMateBtu">
								<div class="btns clearfix">
									<input type="hidden" name="hasChildNodes" id="hasChildNodes"/>
									<input type="hidden" name="pmMateModuId" id="pmMateModuId"/>
									<security:authorize ifAnyGranted="A_PM_ENTRY_NEW">
										<button id="add" type="button" class="blue min" onclick="doAddPmMateEntry('0');" style="float:left;padding-left:15px;padding-top: 1px;background: #0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center;">新增</button>
									</security:authorize>
									<security:authorize ifAnyGranted="A_PM_ENTRY_DEL">
										<button id="del" type="button" class="red min" style="padding-top: 1px;float:left;" onclick="deleteBatchMateEntry();">删除</button>
									</security:authorize>
									<span id="operateResultTip" style="color:red;margin-left:30px;line-height:2"></span>
								</div>
							</div>
							<div id="pmMateEntryList">	  
							 		<%-- 搜索结果列表 --%>
							</div>
						</form>
						<div id="searchUserDiv" class="searchUserDiv">
							<div class="inform_div" ></div>
						</div>
					</div>
					<%--企划案例库增加DIV --%>
					<div id="pmMateEntryDiv" class="default">&nbsp;</div>
			    </td>
		    </tr>
	    </table>
	</div>


<script type="text/javascript">
var arrCheck ="";//存储树形结构节点值
$(function(){
	$("#pmMateEntryDiv").html('');
	getMateTree("itemDiv");//初始化树形结构
	//第一次加载时,控制新增或删除按钮.(background:#dedede;)
	//新增按钮变灰（不可操作）
	$("#add").attr("disabled","disabled");
	$("#add").css("background","#dedede url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center");

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
 *初始化企划案例库分类树
 * itemDiv 所在页面位置
 */
function getMateTree(itemDiv){
	var selectId = "";
	var selectName = "";
	var url = "${ctx}/pm/pm-mate-module!getPmMateModuleTreeCheck.action";
	$.post(url, function(result){
		$('#'+itemDiv).empty();
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.render();
		tree.isExpendSelect = true;
		tree.on(function(node){
			var nodeId = node.attributes.id;
			var nodeType = node.attributes.nodeType;
			if('0' != nodeType){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
					//$("#treeV").text(node.attributes.text);
					//$("#treeV").show();
				}
			}
			//点击根节点,如果有子节点则只处理子节点
			if(node.hasChildNodes()==false){
				//新增按钮正常显示（可操作）
				$("#add").removeAttr("disabled");
				$("#add").css("background","#0072bb url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center");

				//对子节点查询操作
				$("#pmMateModuId").val(nodeId);
				doQueryMateByIdQ(nodeId);
				
			}else{
				//新增按钮变灰（不可操作）
				$("#add").attr("disabled","disabled");
				$("#add").css("background","#dedede url('${ctx}/resources/images/res/res_add.png') no-repeat scroll 5px center");
				//0表示父子节点
				$("#hasChildNodes").val("0");
			}
		});
	});
}


function openAttachmentByModel(title,bizModuleCd,uiid) {
	ymPrompt.params={};
	ymPrompt.params.uiid=uiid;
	//ymPrompt.params.attachFlag=attachFlag;
	var popWinUrl;
	var bizEntityTempId = $("#bizEntityTempId").val();
	if(bizEntityTempId==''){
		bizEntityTempId = 'pm_'+uiid+'_'+String((new Date().getTime()) ^ Math.random());
		$("#bizEntityTempId").val(bizEntityTempId);
		popWinUrl="${ctx}/app/app-attachment!list.action?bizEntityId="+ bizEntityTempId 
		+ "&bizModuleCd="+ bizModuleCd
		+ "&uiid="+uiid
		//+ "&bizFieldName="+attachFlag
		+ "&filterType=image|office";
	}else{
		popWinUrl="${ctx}/app/app-attachment!list.action?bizEntityId="+ bizEntityTempId
		+ "&bizModuleCd="+ bizModuleCd
		+ "&uiid="+uiid
		//+ "&bizFieldName="+attachFlag
		+ "&filterType=image|office";
	}
	//alert("bizEntityTempId: "+$("#bizEntityTempId").val()+" , bizEntityId: "+entityId);
	
	ymPrompt.win( {
				message :popWinUrl,
				width : 500,
				height : 300,
				winPos : [300,100],
				title : title,
				iframe : true,
				afterShow : function(){},
				handler : showAttach
			});
}

function showAttach() {
	//var attachFiles = ymPrompt.params.attachFlag;
	var data = {
			bizEntityId : $("#bizEntityTempId").val(),
			//bizFieldName :attachFiles,
			userCd : ymPrompt.params.uiid  //创建用户
		};
	$.post("${ctx}/app/app-attachment!resShow.action",data,
			function(data) {
				$("#attaFilesContainer").html(data);
			});
}

//修改企划案例信息
function updateMateEntry(id){
	var url = "${ctx}/pm/pm-mate-entry!input.action";
	TB_showMaskLayer("正在加载...");
	$.post(url,{pmMateEntryId:id}, function(result) {
		TB_removeMaskLayer();
		doQueryMateById($("#pmMateModuId").val());
		$("#mainDiv").hide();
		$("#pmMateEntryDiv").html(result);
		$("#pmMateEntryDiv").show();
		showAttachPm();
		$("#flag").val("1");
	});
}

//当前登陆用户修改操作中，加载相应的上传附件
function showAttachPm() {
	var curr_user_Cd = '<%= SpringSecurityUtils.getCurrentUiid()%>';
	var data = {
			bizEntityId : $("#bizEntityTempId").val(),
			userCd : curr_user_Cd  //登录用户
		};
	$.post("${ctx}/app/app-attachment!resShow.action",data,
			function(data) {
				$("#attaFilesContainer").html(data);
			});
}
//新增企划案例库信息显示页
function doAddPmMateEntry(addFlag){
	var url="${ctx}/pm/pm-mate-entry!input.action";
	//获取选中树的模型Id
	var pmMateModuId = $("#pmMateModuId").val();
	var hasChildNodes = $("#hasChildNodes").val();
	if(""==pmMateModuId){
		if(hasChildNodes=="0"){
			alert("请先选择左侧子目录!");
			return;
		}
	}
	var data = {pmMateModuId:pmMateModuId,flag:addFlag};
	TB_showMaskLayer("正在加载...");
	$.post(url,data,function(result){
		TB_removeMaskLayer();
		$("#mainDiv").hide();
		$("#pmMateEntryDiv").html(result);
		$("#pmMateEntryDiv").show();
	});
}

//企划案例库“返回”
function returnPmMateEntry(){
	doQueryMateById($("#pmMateModuId").val());
	$("#pmMateEntryDiv").hide();
	$("#pmMateEntryDiv").html('');
	$("#mainDiv").show();
}

//判断是否为空
function hasEmpty(){
	//企划案例编号
	var pmMateEntryNo = $("#pmMateEntryNo");
	//活动主题
	var activeTitle = $("#activeTitle");
	//活动内容
	var activeContent = $("#activeContent");
	//时间周期
	var activePeriod = $("#activePeriod");
	//费用预期
	var expensesBudget = $("#expensesBudget");
	//道具
	var properties = $("#properties");
	//美陈
	var meiChen = $("#meiChen");
	//赠品
	var present = $("#present");
	//媒体
	var medium = $("#medium");
	//操作指引
	var operatorGuide = $("#operatorGuide");
	//预期效果
	var expectedResults = $("#expectedResults");
	//参考图片及方案
	var attaFilesContainer = $("#attaFilesContainer").find(".mainTr");
	if(""==pmMateEntryNo.val()&&""==activeTitle.val()&&""==activeContent.val()&&""==activePeriod.val()&&""==expensesBudget.val()&&""==properties.val()&&""==meiChen.val()&&""==present.val()&&""==medium.val()&&""==operatorGuide.val()&&""==expectedResults.val()&&""==attaFilesContainer.text()){
		return true;
	}else if(""==pmMateEntryNo.val()||""==activeTitle.val()||""==activeContent.val()||""==activePeriod.val()||""==expensesBudget.val()||""==properties.val()||""==meiChen.val()||""==present.val()||""==medium.val()||""==operatorGuide.val()||""==expectedResults.val()||""==attaFilesContainer.text()){
		return false;
	}
	return false;
}

//单个搜索（企划案例库）
function doQueryMateById(pId){
	$('#searchUserDiv').hide();
	TB_showMaskLayer("正在搜索,请稍候...");
	$.post("${ctx}/pm/pm-mate-entry!list.action",{pmMateModuId:pId},function(result) {
		TB_removeMaskLayer();
		if (result) {
			$("#pmMateEntryList").html(result);
		}
	});
	$("#pmMateEntryDiv").hide();
	$("#pmMateEntryDiv").html('');
	$("#mainDiv").show();
}
//保存数据的提示操作[切换到树形结构操作，避免填写了数据后，忘记保存]
function doQueryMateByIdQ(pId){
	$('#searchUserDiv').hide();
	
	if($("#pmMateEntryDiv").text().trim() == ""){
		doQueryMateById(pId);
	}else{
		if(hasEmpty()==false){
			if(confirm("当前数据尚未保存，确定退出？")==true){
				doQueryMateById(pId);
			}
		}else{
			doQueryMateById(pId);
		}
	}
}

function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#searchForm").ajaxSubmit(function(result) {
		$("#pmMateEntryList").html(result);
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}


//批量删除企划案例库信息
function deleteBatchMateEntry(){
	var mateEntryIds="";
	$("input[type='checkbox'][mateEntryId]").each(function(){
		if($(this).attr('checked')==true){
			var mateEntryId = $(this).attr('mateEntryId');
			mateEntryIds=mateEntryIds+","+mateEntryId;
		}			
	});	
	//需删除的所有台账
	mateEntryIds=mateEntryIds.substring(1,mateEntryIds.length);
	if(mateEntryIds.length < 1){
		alert('请选择需要删除企划案例的信息！');
		return ;
	}else{
		if(window.confirm('确认删除？')){
			//alert("ok: "+mateEntryIds);
			var data={mateEntryIds:mateEntryIds};
			var url="${ctx}/pm/pm-mate-entry!deleteBatch.action";
			TB_showMaskLayer("正在删除...");
			$.post(url,data,function(result){
				TB_removeMaskLayer();
				if(result=='success'){
					openSuccess("删除成功");
					doQueryMateById($("#pmMateModuId").val());
				}else{
					openFailure("删除失败");
				}
			});
		}	
	}		
}

/**
 * 显示提示成功
 * @param result
 */
function openSuccess(result){
	var myDate = new Date();
	var hour = myDate.getHours();      //获取当前小时数(0-23)
	var minu = myDate.getMinutes();    //获取当前分钟数(0-59)
	var sec  = myDate.getSeconds();    //获取当前秒数(0-59)
	$('#operateResultTip').html(hour +':' + minu + ':' + sec +' ' + result).css({'color':'green'}).show().fadeOut(5000);
}
/**
 * 显示提示不成功
 * @param result
 */
function openFailure(result){
	$('#operateResultTip').css({'color':'red'}).html(result).show().fadeOut(5000);
}
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/desk/res-common.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/css/resApprove.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/table.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>	
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/js/gt-grid/gt_grid.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/gt-grid/skin/vista/skinstyle.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/gt-grid/skin/china/skinstyle.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/gt-grid/skin/mac/skinstyle.css" />
	
	<script type="text/javascript" src="${ctx}/js/gt-grid/gt_msg_cn.js"></script>
	<script type="text/javascript" src="${ctx}/js/gt-grid/gt_grid_all.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	
	<title>权责信息配置</title>
</head>

<body>

	<table border="0" cellpadding="0" style="width:100%;">
	<tr>
	<td style="width:300px;" valign="top">
		<div style="margin:10px;">
		<input type="button" value="网上审批" onclick="changeModuleType('0');"/>
		<input type="button" value="合理化建议" onclick="changeModuleType('1');"/>
		<input type="button" value="特别审批" onclick="changeModuleType('2');"/>
		</div>
		<div id="searchApproveFix" style="border-style:solid;border-width:1px; border-color:#BFBFBF;margin:10px;">
			<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
				<tr>
					<td>
						<input  value="搜索表单..." 
								type="text" 
								style="padding:2px;border:0;font-size: 12px;color: #CCCCCC;width:100%;"
								onkeyup="searchTreeNode(this)"
								onblur="resetSearchApproveInput(this);"
								onclick="clearSearchApproveInput(this);"
						/>
					</td>
					<td style="width:56px;">
						<div id="inputSearchOperate" class="searchNextNoActive">&nbsp;</div>
					</td>
				</tr>
			</table>
		</div>
		<div class="divTree"  id ="tree-div" style="overflow-y:auto;margin-left:10px;border:1px solid #BFBFBF;"></div>
	</td>
	<td style="padding:10px" valign="top">
		<fieldset>
			<div style="margin: 10px 0px;width:100%;">
				<button class="buttom" style="height: 25px; width: 80px;" onclick="refreshResModuleTree()" class="buttom">刷新表单</button>
				<button class="buttom" style="height: 25px; width: 80px;" onclick="showNew('newModuleDiv',300,300);">新增模块</button>
				<button class="buttom" id="newTypeBtnId" style="height: 25px; width: 120px;" onclick="showNew('newAuthTypeDiv',300,300);" disabled="disabled">新增分类(表单)</button>
				
				<span style="padding-left:20px;font-weight: bold; color:red;" id="succesedInfo"></span>
			</div>
		</fieldset>
		<div >
		<div id="content" style="line-height: 25px;height: 25px;">友情提示: 双击模块可以直接对模块进行编辑</div>
		<div>
			<div id="newModuleDiv" style="display: none">
				<form action="${ctx}/res/res-module!saveModule.action" id="newModuleForm" method="post">
					<table style="width:95%;">
						<tr>
							<td align="right" style="width:80px;" valign="top">上级模块：</td>
							<td valign="top" style="line-height: 18px;">
								<div id="parentModuleNameId"></div>
								<input type="hidden" id="parentModuleCdId" name="resModule.parentModuleCd" value="0"/>
							</td>
						</tr>
						<tr>
							<td align="right">序号：</td>
							<td><input type="text" name="resModule.sequenceNo" /></td>
						</tr>
						<tr>
							<td align="right">模块代码：</td>
							<td>
								<input type="text" name="resModule.moduleCd"  onkeyup="validateCd('',this,'module')"/>
							</td>
						</tr>
						<tr>
							<td align="right">模块名称：</td>
							<td><input type="text" name="resModule.moduleName" /></td>
						</tr>
						<tr>
							<td align="right">模块类别：</td>
							<td><s:select list="mapModuleType" listKey="key" listValue="value" name="resModule.moduleTypeCd"></s:select></td>
						</tr>
						<tr>
							<td align="right">是否有效：</td>
							<td align="left" ><s:checkbox cssStyle="width:20px;" name="resModule.active" ></s:checkbox> </td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<button class="buttom" type="button" onclick="save(this)">保存</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div id="newAuthTypeDiv" style="display: none">
				<form action="${ctx}/res/res-module!save.action" id="newAuthTypeForm" method="post">
					<s:hidden name="resModule.resModuleId" id="resModuleIdHidden"></s:hidden>
					<table style="width:95%;">
						<tr>
							<td align="right" style="width:80px">序号：</td>
							<td><input style="width:100%;" type="text" name="sequenceNo" /></td>
						</tr>
						<tr>
							<td align="right">分类代码：</td>
							<td>
								<input style="width:100%;" type="text" name="authTypeCd"  onblur="validateCd('',this,'type')"/>
							</td>
						</tr>
						<tr>
							<td align="right">分类名称：</td>
							<td><input style="width:100%;" type="text" name="authTypeName" /></td>
						</tr>
						<tr>
							<td align="right">显示名称：</td>
							<td><input style="width:100%;" type="text" name="displayName" /></td>
						</tr>
						<tr>
							<td align="right">模板：</td>
							<td><s:select cssStyle="width:100%;" list="templetMap" emptyOption="true" name="templetCd" listKey="key" listValue="value"></s:select></td>
						</tr>
						<tr>
							<td align="right">是否发布：</td>
							<td><s:checkbox name="publish" ></s:checkbox> </td>
						</tr>
						<tr>
							<td colspan="2" align="center"><button class="buttom" type="button" onclick="save(this)">保存</button></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</td>
</tr>
</table>
<script type="text/javascript">
	//加载页面功能树
	var treePanel;
	var curNode;
	function changeModuleType(moduleTypeCd){
		//0-网上审批，1-合理化建议，2-特别申请
		refreshResModuleTree(moduleTypeCd);
	}
	changeModuleType('0');
	//刷新树
	function refreshResModuleTree(moduleTypeCd){
		$("#tree-div").html('<div><image src="${ctx}/images/loading.gif"/>加载数据...</div>');
		$.post("${ctx}/res/res-approve-info!buildTree.action", {moduleTypeCd:moduleTypeCd},function(result) {
			$("#tree-div").empty();
			if (result) {
				var arr=eval('('+result+')');
				root=arr;
				treePanel = new TreePanel({
					renderTo:'tree-div',
					'root' : root,
					'ctx':'${ctx}'
				});

				treePanel.delegateOnDblClick = function(node){
					if(node.attributes.nodeType == "module"){
						//curNode = node;
						var id = node.attributes.id;
						ymPrompt.win({
							title:'编辑模块',
							fixPosition:true,
							width:250,
							height:280,
							allowRightMenu:true,
							showMask : false,
							message:"<div id='popEditDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
							afterShow:function(){
								$.post("${ctx}/res/res-module!edit.action",{id:id},function(result){
									$("#popEditDiv").html(result);
								});
							}
						});
					}
				};
				treePanel.render();
				treePanel.on(function(node){
					//模块
					if(node.attributes.nodeType == "module"){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
						$("#parentModuleNameId").text(node.attributes.text);
						$("#parentModuleCdId").val(node.attributes.entityCd);
						$("#newTypeBtnId").attr("disabled",false);
						$("#resModuleIdHidden").val(node.attributes.id);
						$("#content").html("<div style='color:red;'>友情提示:双击模块可以直接对模块进行编辑<div>");
					}else if(node.attributes.nodeType == "authType"){ //分类
						var itemId=node.attributes.id;
						$("#newTypeBtnId").attr("disabled",false);
						$("#resModuleIdHidden").val(node.parentNode.attributes.id);
						$("#content").addClass("waiting");
						$.post("${ctx}/res/res-module!input.action",{id:itemId},function(result) {
							 $("#content").empty().html(result).removeClass("waiting");
							 msgGrid.render();
							 conditionGrid.render();
							 stepGrid.render();
							 centerGrid.render();
						});
					}else{
						return;
					}
					curNode = node;
				});
			}
		});
	}
	/**
	* 弹出新增框
	**/
	function showNew(divId,height,width){
		ymPrompt.win({
			title:'新增',
			fixPosition:true,
			allowRightMenu:true,
			width:width||200,
			height:height,
			showMask : false,
			message:$("#"+divId).html()
		});
	}
	/**
	* 新增保存 - 模块|分类 |步骤|权限
	**/
	function save(dom){
		var $form = $(dom).parents("form");
		if($(".error",$form).length>0){
			$(".error",$form).focus();
			return;
		}
		var formId = $form.attr("id");
		$form.ajaxSubmit(function(result){
			if(formId == "newContionForm"){
				var idValue = $('#resAuthTypeIdHidden').val();
				$.post("${ctx}/res/res-module!input.action",{id:idValue},function(result) {
					ymPrompt.close();
					$("#content").html(result);
					 $("#conditionTable tbody tr:first td:first").trigger("click");
					showSuccesedInfo("保存成功!");
				});
			}else if(formId == "newApproveStepForm"){
				var idValue = $('#resConditionIdHidden').val();
				ymPrompt.close();
				showSteps(idValue);
			}else{
				location.href="${ctx}/res/res-module.action";
			}
		});
	}
	/**
	* 编辑模块-保存
	**/
	function saveModulePop(){
		if($(".error","#popModuleForm").length>0)return;
		$("#popModuleForm").ajaxSubmit(function(){
			//curNode.attributes.text = $("#popModuleName").val();
			//curNode.paintText();
			//ymPrompt.close();
			window.location.href=window.location.href;
		});
	}
	/**
	* 编辑分类-保存
	**/
	function saveType(dom){
		var $form = $(dom).parents("form");
		if($(".error",$form).length>0){
			$(".error",$form).focus();
			return;
		}
		$form.ajaxSubmit(function(result){
			//curNode.attributes.text = $("#authTypeEditId").val();
			//curNode.paintText();
			showSuccesedInfo("保存成功!");
			//window.location.href=window.location.href;
		});
	}

	function editRow(id){
		$("#main_"+id).hide();
		$("#edit_"+id).show();
	}
	function cancelRow(id){
		$("#main_"+id).show();
		$("#edit_"+id).hide();
	}

	/**
	*  编辑权限-保存
	**/
	function saveCondition(id){
		var conditionCd = $("#edit_"+id+" input[name='conditionCd']").val();
		var conditionName = $("#edit_"+id+" input[name='conditionName']").val();
		var conditionValue = $("#edit_"+id+" input[name='conditionValue']").val();
		var param = {
			'id':id,
			'resConditonType.conditionCd':conditionCd,
			'resConditonType.conditionName':conditionName,
			'resConditonType.conditionValue':conditionValue
			
		};
		$.post("${ctx}/res/res-module!saveCondition.action",param,function(result){
			$("#main_"+id+" td[name='conditionCd']").text(conditionCd);
			$("#main_"+id+" td[name='conditionName']").text(conditionName);
			$("#main_"+id+" td[name='conditionValue']").text(conditionValue);
			$("#main_"+id).show();
			$("#edit_"+id).hide();
			showSuccesedInfo("保存成功");
		});
	}
	/**
	*  编辑步骤-保存
	**/
	function saveStep(id){
		var $nodeCd = $("#edit_"+id+" select[name='nodeCd']");
		var $approveLevel = $("#edit_"+id+" input[name='approveLevel']");
		var nodeCd = $nodeCd.val();
		var approveLevel = $approveLevel.val();
		var nodeText = $nodeCd.find("option:selected").text();
		var levelText = $approveLevel.val();
		
		var param = {
			'id':id,
			'resApproveStep.nodeCd':nodeCd,
			'resApproveStep.approveLevel':approveLevel
			
		};
		$.post("${ctx}/res/res-module!saveStep.action",param,function(result){
			$("#main_"+id+" td[name='nodeCd']").text(nodeText);
			$("#main_"+id+" td[name='approveLevel']").text(levelText);
			$("#main_"+id).show();
			$("#edit_"+id).hide();
			showSuccesedInfo("保存成功");
		});
	}
	
	/**
	* 删除模块
	**/
	function delModule(moduleId){
		if(!confirm("确认要删除该模块吗？该操作同时会删除当前模块下所有的分类？"))return;
		$.post("${ctx}/res/res-module!delModule.action",{id:moduleId},function(result){
			location.href="${ctx}/res/res-module.action";
		});
	}
	/**
	* 删除分类
	**/
	function delType(typeId){
		if(!confirm("确认要删除该分类吗？该操作同时会删除当前分类下所有的权限"))return;
		$.post("${ctx}/res/res-module!delete.action",{id:typeId},function(result){
			location.href="${ctx}/res/res-module.action";
		});
	}
	/**
	* 删除权限
	**/
	function delCondition(conditionId){
		if(!confirm("确认要删除该权限吗？该操作同时会删除当前权限下所有的步骤"))return;
		$.post("${ctx}/res/res-module!delCondition.action",{id:conditionId},function(result){
			$("#main_"+conditionId).remove();
			$("#edit_"+conditionId).remove();
			$("#resStepGridId").html("请点击一条权限记录");
			showSuccesedInfo("删除成功!");
		});
	}
	/**
	* 删除步骤
	**/
	function delStep(stepId){
		if(!confirm("确认要删除该步骤吗？"))return;
		$.post("${ctx}/res/res-module!delStep.action",{id:stepId},function(result){
			$("#main_"+stepId).remove();
			$("#edit_"+stepId).remove();
			showSuccesedInfo("删除成功!");
		});
	}

	function showSteps(id){
		var $grid = $("#resStepGridId");
		$grid.addClass("waiting");
		$.post("${ctx}/res/res-module!showSteps.action",{id:id},function(result){
			$("#main_"+id).addClass("trHilight").siblings().removeClass("trHilight");
			$grid.html(result).removeClass("waiting");
			showSuccesedInfo("加载成功!");
			$("#newStepBtnId").attr("disabled",false);
		});
	}
	/**
	* 显示成功信息
	**/
	function showSuccesedInfo(text){
		$("#succesedInfo").text(text);
		setTimeout(function(){
			$("#succesedInfo").text("");
		}, 3000);
	}
	/**
	* 验证模块代码或者分类代码是否已经存在
	**/
	var timeOut;
	function validateCd(oldCd,dom,type){
		if(timeOut)clearTimeout(timeOut);
		timeOut = setTimeout(function(){
			var newCd = $(dom).val();
			var $form = $(dom).parents("form"); 
			var action = $form.attr("action");
			$.post("${ctx}/res/res-module!checkCd.action",{oldCd:oldCd,newCd:newCd,type:type},function(result){
				if(eval(result)){
					$(dom).removeClass("error").removeAttr("title");
				}else{
					$(dom).addClass("error").attr("title","已经存在");
				}
			});
		}, 500);
	}

	$(function(){
		//
		//$("#item_3_8_frame",window.parent.document).css("height","90%");
	});

	
	//搜索定位表单
	var curVal = null;
	var curNode = null;

	var searchTreeManager;
	function searchTreeNode(dom){
		if(searchTreeManager)clearTimeout(searchTreeManager);
		searchTreeManager = setTimeout(function(){
			processSearch(dom);
		}, 300);
	}
	
	function processSearch(dom){
		if($(dom).val().trim() == ''){
			$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
		}else{
			$('#inputSearchOperate').removeClass('searchNextNoActive').addClass('searchNextActive');
		}
		
		$(dom).css({color:"#5A5A5A"});
 		if($(dom).val().trim() == curVal){
			//NONE
		}else{
			curVal = $(dom).val().trim();
			curNode = null;
		}
 		curNode = treePanel.searchNode(curVal, curNode);
 		if(curNode){
			var nodes = curNode.getPathNodes();
			for(var i= 0; i < nodes.length; i++){
				nodes[i].expand();
			}
			treePanel.setFocusNode(curNode);
			var nodeDom = curNode['html-element']['text'];
			var toh = $('#tree-div').height();
			var top = $('#tree-div')[0].scrollTop;
			var noh = $(nodeDom).offset().top;
			$('#tree-div').animate({ scrollTop : top+noh-toh }, "normal");
		}else{
			$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
		}
	}
	function resetSearchApproveInput(dom){
		if($(dom).val().trim() == ''){
			$(dom).val('搜索表单...');
			$(dom).css({color:"#E6E6E6"});
		}else{
			$(dom).css({color:"#5A5A5A"});
		}
	}
	function clearSearchApproveInput(dom){
		if( $(dom).val() == '搜索表单...'){
			$(dom).val('');
			$(dom).css({color:"#5A5A5A"});
		}
	}
</script>
</body>
</html>

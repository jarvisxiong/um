var gTreePanel="";
var clickProjectId="";
var clickTypeCd="";
var belongToCd = "";
/**
 * 左侧可拖拉移动
 */
function dragbar(){
	var bodyHeight = Number($(document).height()-80);
	$("#leftTreePanel").css("height",bodyHeight+"px");
	//左右拖拉
	$('#leftPanel').resizable({
        handler: '#noteResizeHandler',
        min: { width: 160, height: ($('#mainDiv').height()) },
        max: { width: 300, height: ($('#mainDiv').height()) },
        onResize: function(e) {
        	$('#leftTreePanel').width($('#leftPanel').width()-7);
        }
    });
}
/***
 * 加载工程项目树
 * @return
 */
function loadEngineerProjectTree(parentId,equipId){
	$("div.TreePanel").remove();
	var url = _ctx + '/engineer/engineer-equip-account!getEquipProjectTree.action';
	$.post(url, {}, function(result){
		$('#leftTreePanel').empty();
		gTreePanel = new TreePanel({
			renderTo:'leftTreePanel',
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		//选中后展开节点
		gTreePanel.render();
		gTreePanel.isExpendSelect = true;
		gTreePanel.on(function(node){
			var nodeType =node.attributes.nodeType;
			if("0" != nodeType){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}
				if("1"==nodeType){
					clickProjectId = node.attributes['entityId'];
					$("#bisProjectId").val(clickProjectId);
					clickTypeCd = "";
					$("#layoutCd").val(clickTypeCd);
					$("#equipBelongtoCd").val("");
					$("#navigation ul li").removeClass("selected");
					//加载所有数据
					loadAccount();
				}
				if("2"==nodeType){//点击自己点的时候，还需要将该自己节点的父节点设置好
					clickProjectId = node.parentNode.attributes['entityId'];
					clickTypeCd = node.attributes['entityId'];
					$("#bisProjectId").val(clickProjectId);
					$("#layoutCd").val(clickTypeCd);
					belongTo = $("#equipBelongtoCd").val();
					searchEquip(clickProjectId,belongTo,clickTypeCd);
				}
			}
		});
		if(parentId){
			gTreePanel.getNodeById(parentId).expand();
			gTreePanel.setFocusNode(gTreePanel.getNodeById(equipId));
		}
	});
}
/***
 * 增加工程设备
 * @return
 */
function addEngineerEquip(){
	if(clickProjectId!=undefined&&clickProjectId!=""&&clickTypeCd!=undefined&&clickTypeCd!=""&&belongToCd!=""){
		var data = {"bisProjectId":clickProjectId,"layoutCd":clickTypeCd,"equipBelongtoCd":$("#equipBelongtoCd").val()};
		var url=_ctx+"/engineer/engineer-equip-account!add.action";
		TB_showMaskLayer("正在加载...");
		$.post(url,data,function(result){
			TB_removeMaskLayer();
			$("#mainDiv").hide();
			$("#searchAccountDiv").hide();
			$("#accountAddDiv").html(result);
			$("#accountAddDiv").show();
		});
	}else{
		alert("请选中左侧项目和业态和上面的所属系统之后再进行操作");
	}
}
//批量删除工程台账
function deleteBatchAccount(){
	var accountIds="";
	$("input[type='checkbox'][enquipId]").each(function(){
		if($(this).attr('checked')==true){
			var accountId=$(this).attr('enquipId');
			accountIds=accountIds+","+accountId;
		}			
	});	
	//需删除的所有台账
	accountIds=accountIds.substring(1,accountIds.length);
	if(accountIds.length < 1){
		alert('请选择需要删除的工程台账信息！');
		return ;
	}else{
		if(window.confirm('确认删除？')){
			var data={accountIds:accountIds};
			var url=_ctx+"/engineer/engineer-equip-account!deleteBatch.action";
			TB_showMaskLayer("正在删除...");
			$.post(url,data,function(result){
				TB_removeMaskLayer();
				if(result=='success'){
					$("#accountDelTip").html("<font color='red' style='vertical-align: middle;'>删除成功</font>").fadeIn(100).fadeOut(2000);
					loadAccount();
				}else{
					$("#accountDelTip").html("<font color='red' style='vertical-align: middle;'>"+result+"</font>").fadeIn(100).fadeOut(2000);
				}
			});
		}	
	}		
}

/**工程台账“取消”按钮*/
function canleAccount(){
	$("#accountAddDiv").hide();
	$("#accountAddDiv").html('');
	$("#mainDiv").show();
}

/**工程台账“返回”按钮*/
function backAccount(){
	$("#accountAddDiv").hide();
	$("#accountAddDiv").html('');
	loadAccount();
	$("#mainDiv").show();
}
/***
 * 更新台账
 * @param equipId
 * @return
 */
function updateAccount(equipId){
	var url=_ctx+"/engineer/engineer-equip-account!input.action";
	TB_showMaskLayer("正在加载...");
	$.post(url,{'engineerEquipAccountId':equipId,'method':"update"}, function(result) {
		TB_removeMaskLayer();
		$("#mainDiv").hide();
		$("#accountAddDiv").html(result);
		$("#accountAddDiv").show();
	});
}
/**保存工程台账**/
function saveAccount(){
	var flag = false;
	$.each($(".required"),function(){
		if($(this).attr("name")!=undefined&&$(this).attr("name")!=""){
			if($.trim($(this).val()).length<1){
				flag = false;
				alert($(this).prev().html()+" 不能为空");
				return false;
			}else{
				if($(this).attr("name")=="equipAmount"){
					if(isNaN($(this).val())){
						alert($(this).prev().html()+"必须填入数字");
						flag = false;
						return false;
					}
				}
				flag = true;
			}
		}
	});
	if(flag){
		var url = $("#saveFormId").attr("action");
		var param = $("#saveFormId").serialize();
		$.post(url,param,function(data){
			if(data.length==32){
				alert("操作成功");
				$("#btnCancelId").click();
				/**
				if($.trim($("#methodId").val())=="update"){
					$("#btnCancelId").click();
				}else{
					$("#btnEquipAdd").hide();
					$("#btnEquipEdit").show();
				}
				$("#newengineerEquipAccountId").val(data);
				$("#methodId").val("update");
				**/
			}else{
				alert("操作失败\n"+data);
				return false;
			}
		});
	}
}
/**
 * 查询资产信息
 * proId:项目编号
 * belongTo:所属系统
 * typeCd:业态
 **/
function searchEquip(proId,belongTo,typeCd){
		$("#searchPosition").val(1);
		proId=proId==undefined?"":proId;
		belongTo=belongTo==undefined?"":belongTo;
		typeCd=typeCd==undefined?"":typeCd;
		/**将值存起来*/
		$("#equipBelongtoCd").val(belongTo);
		$("#bisProjectId").val(proId);
		$("#layoutCd").val(typeCd);
		var param = {"proId":proId,"belongTo":belongTo,"typeCd":typeCd,"ver":new Date()};
		var url =  _ctx + "/engineer/engineer-equip-account!list.action";
		$.post(url,param,function(data){
			$("#resultTable").empty();
			$("#resultTable").html(data);
		});
}
/**加载台账*/
function loadAccount(){
	searchEquip($("#bisProjectId").val(),$("#equipBelongtoCd").val(),$("#layoutCd").val());
}
/**滚动显示所属系统*/
function scl(){
	t1=setTimeout("scl()",50);
	$("#navigation").css("margin-left",(parseInt($("#navigation").css("margin-left"))-10)+"px");
	if(parseInt($("#navigation").css("margin-left"))>650){
		clearTimeout(t1);
	}
}
/**滚动显示所属系统*/
function scr(){
	t2=setTimeout("scr()",50);
	$("#navigation").css("margin-left",(parseInt($("#navigation").css("margin-left"))+10)+"px");
	if(parseInt($("#navigation").css("margin-left"))>200){
		clearTimeout(t2);
	}
}
/**停止滚动显示所属系统*/
function stopSc(demo){
	if(demo==1){
		clearTimeout(t1);
	}else{
		clearTimeout(t2);
	}
}
/**弹出附件框*/
function openAttachment(entityId){
	var tmp = $.trim($("#onlyViewId").val());
	var text = "";
	if("notOnlyView"!=tmp){
		text = "&onlyShow=true";
	}
	 ymPrompt.win({
		 message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=engineerEquip&bizEntityName=EngineerEquipAccount&filterType=image|office"+text,
		 width:500,
		 height:300,
		 title:"附件管理",
		 iframe:true,
		 afterShow : function() {},
		 handler : function(btn) {
			if(btn=='close') {
				showAttach(entityId,'attachFlgImgId','attachFlgId');
			}
		 },
		 btn:[["完成",'close']]
	 });
}
/**
 * 判断是否有附件
 */
function showAttach(entityId,domId,attachFlgId) {
	$.post(_ctx+"/app/app-attachment!hasAttachment.action",
		{bizEntityId:entityId,bizModuleCd:'engineerEquip'},
		function(result){
			if(result == "true") {
				$("#"+domId).attr("src",_ctx+"/resources/images/common/atta_y.gif");
				$("#"+attachFlgId).val("1");
			} else {
				$("#"+domId).attr("src",_ctx+"/resources/images/common/atta.gif");
				$("#"+attachFlgId).val("0");
			}
	});
}
/**全选，反选**/
function checkAll(dom){
	var jdom=$(dom);
	if(jdom.attr('checked')==true){
		$("input[type='checkbox'][enquipId]").each(function(){
			$(this).attr('checked',true);
		});
	}else{
		$("input[type='checkbox'][enquipId]").each(function(){
			$(this).attr('checked',false);
		});
	}
}
/** 验证设备型号不能重复*/
function valiEquipCdOnBlur(){
	if($.trim($("#methodId").val())!="update"){
		var url =   _ctx + "/engineer/engineer-equip-account!valiEngineerEquipCd.action";
		var param = {"equipCd":$.trim($("#equipCdId").val())};
		$.get(url,param,function(data){
			if(data=="1"){
				alert("设备编号不能重复，请修改！");
				$("#equipCdId").select();
			}
		});
	}
}
/**显示高级查询*/
function showAccountSearchDiv(){
	$('#searchModelDiv').hide();
	if($("#searchAccountDiv").is(":hidden")){
		$("#searchDiv").show();
		$("#searchAccountDiv").show();
	}else{
		$("#searchDiv").hide();
		$('#searchAccountDiv').hide();
		//doClear();
	}
}
/**
 * 高级查询清空
 * @return
 */
function doClear(){
	document.forms["mainForm"].reset();
	
}
/**
 * 高级查询
 * @return
 */
function doQuery(){
	$("#searchPosition").val(1);
	TB_showMaskLayer("正在搜索...");
	$("#mainForm").attr("action",_ctx+"/engineer/engineer-equip-account!loadList.action");
	$("#mainForm").ajaxSubmit(function(result){
		TB_removeMaskLayer();
		$("#resultTable").html(result);
		$("#accountAddDiv").hide();
		$("#mainDiv").show();
	});
}
/**
 * 导出excel报表
 * @return
 */
function doExportAccount(){
	TB_showMaskLayer("正在导出...",5000);
	$("#mainForm").attr("action",_ctx+"/engineer/engineer-equip-account!doExportAccount.action");
	TB_removeMaskLayer();
	document.forms["mainForm"].submit();
}
var g_quicksearch='快速搜索...';
/**
 * 重置字段
 * @param dom
 * @return
 */
function resetQuickSearch(dom){
	if($(dom).val().trim() == ''){
		$(dom).val(g_quicksearch);
		$(dom).css({color:"#cccccc"});
	}else{
		$(dom).css({color:"#5A5A5A"});
	}
}
/**
 * 清空字段
 * @param dom
 * @return
 */
function clearQuickSearch(dom){
	if($(dom).val() == g_quicksearch){
		$(dom).val('');
		$(dom).css({color:"#5A5A5A"});
	}
} 
/**
 * 快速搜索
 * @return
 */
function quickSearchAccount(){
	$("#searchPosition").val(0);
	$("#words").quickSearch(
			_ctx+"/engineer/engineer-equip-account!quickSearch.action",
	   		['equipCd','equipName'],
	   		{engineerEquipAccountId:'account_quick_id',equipName:'words'},'',
	   		function(result){
	   			var id = $("#account_quick_id").val();
	   			$("#engineerEquipAccountId").val(id);//导出时使用的值
	   			var url=_ctx+"/engineer/engineer-equip-account!getBisProjectIpByEquipId.action";
	   			TB_showMaskLayer("正在搜索...");
	   			$.post(url,{equipAccountId:id},function(result){
	   				//ids目的是定位到第二层节点
	   	   			var ids = result.split(',');
	   	   			if("" != ids[0] && "" != ids[1]){
		   				var url2 = _ctx+"/engineer/engineer-equip-account!listByEquipId.action";
		   	   			TB_showMaskLayer("正在搜索...");
		   	   			$.post(url2,{equipAccountId:id},function(result){
		   	   				TB_removeMaskLayer();
		   	   				$("#accountAddDiv").hide();
		   	   				$("#resultTable").html(result);
		   	   				$("#mainDiv").show();
		   	   				//定位到树
		   	   				loadEngineerProjectTree(ids[0],ids[1]);
		   	   			});
	   	   	   		}
	   	   		});
	       	}
	   	);
}
/***
 * 类型选择移到位置
 * @return
 */
function typeCdChoiseMouseOver(){
	$("#equipTypeCd").unbind("blur");
}
function typeCdChoiseMouseOut(){
	$("#equipTypeCd").bind("blur",function(){
		$("#typeCdChoiseId").hide();
	});
}
/**
 * 状态选择失去焦点
 * @return
 */
function statusChoiceMouseOver(){
	$("#equipStatus").unbind("blur");
}
function statusChoiceMouseOut(){
	$("#equipStatus").bind("blur",function(){
		$("#statusChoiceId").hide();
	});
}
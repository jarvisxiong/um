$(function(){
	$("div.tabs a:first").addClass("current"); //第一个元素选中     
	$("div.tab:not(:first)").hide();
	$("div.tab:first").show(); //第一个内容显示       
	$("div.tabs a").click(function () {    
		$("div.tabs a").removeClass("current"); //将所有的tab去掉current样式          
		$(this).addClass("current");
		var index=$("div.tabs a").index(this);
		if(index==0){
			//合约规划模版
			contractTemplateOne();
		}
		if(index==1){
			//成本汇总模版
			contractTemplateTwo();
			//加载iframe中的方法
			$("#costContPlanTplStatIframe")[0].contentWindow.iframeInit();
		}   
	});
});

function contractTemplateOne(){
	var costContPlanTplId = $("#costContPlanTplId").val();
	$("#tabs_1").show();
	$("#tabs_2").hide();
	var url = _ctx + '/cost/cost-cont-plan-tpl-detail!list.action';
	$.post(url,{id:costContPlanTplId},function(data){
	});
}

function contractTemplateTwo(){
	$("#tabs_2").show();
	$("#tabs_1").hide();
}

/**
 * 判断是否为纯数字
 * @param dom
 * @return
 */
function isNum(dom){
	var tplVersion=$(dom).val();
	if(!/^\d+$/.test(tplVersion)){
		alert("版本号必须为纯数字");
		$(dom).focus();
		return;
	}
}

/**
 * 保存合约模版
 * @return
 */
function saveTemplate(){
	//获取合约模板号
	var costContPlanTplId = $("#costContPlanTplId").val();
	//模版名称
	var tplName = $("#tplName").val();
	//版本
	var tplVersion = $("#tplVersion").val();
	var data={};
	if($.trim(tplName)==''){
		alert("模版名称为必填");
		return;
	}
	if($.trim(tplVersion)==''){
		alert("版本为必填");
		return;
	}else{
		isNum($("#tplVersion"));
	}
	data.tplName = tplName;
	data.tplVersion = tplVersion;
	data.costContPlanTplId = costContPlanTplId;
    var url = _ctx + '/cost/cost-cont-plan-tpl-detail!saveTemplate.action';
    
    TB_showMaskLayer("正在保存...");
    
	$.post(url,data,function(responseText){
		var resultJson=toJson(responseText);
		if(resultJson.status=='success'){
			openSuccess('模板保存成功!');
			//模板保存完后，将重新加载新建的模板数据
			if(resultJson.costContPlanTplId!=null){
				var url = _ctx + '/cost/cost-cont-plan-tpl-detail!hasExist.action';
				$.post(url,{costContPlanTplId:resultJson.costContPlanTplId},function(data){
					//判断新增的模板是否在对应的模板中有对应的数据（如果没有则返回;如果有，则刷新）
					if(data=='yes'){
						window.location.href=_ctx+"/cost/cost-cont-plan-tpl-detail!buildTabs.action?id='"+costContPlanTplId+"'";
					}else{
						openFailure('对不起，模板库中无数据，请先选择有效模板!');
					}
				});
			}else{
				
			}
		}else{	
			openFailure('模板保存失败!');
		}
	});
	TB_removeMaskLayer();
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
	$('#isSuccess').html(hour +':' + minu + ':' + sec +' ' + result).css({'color':'green'}).show().fadeOut(5000);
}
/**
 * 显示提示不成功
 * @param result
 */
function openFailure(result){
	$('#isSuccess').css({'color':'red'}).html(result).show().fadeOut(5000);
}


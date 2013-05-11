
/**
* 步骤 筛选点击效果
* @param demo操作对象
* @param step步骤编号
* @param option1 resRightsId
* @param option2 resAuthTypeId 
* @param option3 中文权责名称
* @param option4 resAuthTypeCd
*/
function stepItemCheck(demo,step,option1,option2,option3,option4){
	//有文本选项
	var optionFlag = false;
	if($(demo).attr("flag")=="1"){
		$(demo).parent().siblings().children().removeClass("bg_blue");
		$(demo).parent().siblings().children().removeAttr("cked");//移除其他选中
		$(demo).addClass("bg_blue");
		$(demo).attr("cked","on");//表示已经被选中
		$("#stepItemCancel"+step).hide();
		//点击不可用
		$("#step"+(step)).children().children().find(".btn_step_item").attr("flag","0");
		//将点选的值存入表单中
		$("#stp"+step).val(option1);
		//判断是否有选项
		if(option3){
			var url =  _ctx+"/resources/js/res/data.js?v="+new Date();
			$.get(url,function(datas){
				var data = eval("("+datas+")");
				$.each(data.data,function(i,j){
					if(option3==j['resRightId']||option4==j['authTypeCd']){
						//添加步骤
						addItemStepTr();
						optionFlag = true;
						$.each(j['items'],function(k,v){
							eval(v['func']+'()');
						});
						//添加确定按钮
						addItemOK(option1,option2,(parseInt(step)+1));
						//确定模板值
						$("#resAuthTypeCd").val(option4);
					}
				});
				if(optionFlag){
					addEvent();
					return ;
				}
				doNextStep(option1,option2,step);
			});
		}
		
	}
}
/**
 * 确定是否有下一级，并且执行操作
 * @param option1 resRightsId
 * @param option2 resAuthTypeId
 * @param step 步骤
 */

function doNextStep(option1,option2,step){
	if(option2!=""&&option2.length>5){//能确定表单模板
		var pdv = new Validate("templetForm");
		if (pdv.validate()) {//验证通过则赋值
			//如果authtypeId有值
			if(confirm("是否确认以上选择?")){
				readonlyForm();
				$("#approverDiv").show();
			}else{
				$("#stepItemCancel"+step).show();
				return false;
			}
		}else{
			alert("验证失败，请仔细检查后再操作");
			$("#step"+step).find("table tr:last").show();
		}
		
	}else{//还有下一级
		//调用点击事件
		getNextStepData(step,option1);
		$("#step"+(step+1)).fadeIn("fast");
		$("#point"+(step)).fadeIn("fast");
		$("#step"+step).find("table tr:last").hide();
	}
}
/**
 * 获取下一步数据 
 * @param step步骤
 * @param id 父ID(权责表Id)
 */
function getNextStepData(step,id){
	var url = _ctx+"/res/res-rights!getTreeDataById.action";
	var param = {"id":id,"step":step};
	$.get(url,param,function(data){
		createItems(data,(parseInt(step)+1));
	});
}
/**
 * 动态创建表单选项 按钮
 * @param json数据
 */
function createItems(data,step){
	addItemStep();
	var obj = eval("("+data+")");
	$("#step"+step+" ul").empty();
	for(var i=0,j=obj.length;i<j;i++){
		var items = '<li><input type="button" value="'+obj[i].rightsName+'" flag="1" resCd="'+obj[i].resAuthTypeCd+'" class="btn_step_item" onclick="stepItemCheck(this,'+step+',\''+obj[i].resRightsId+'\',\''+obj[i].resAuthTypeId+'\',\''+obj[i].rightsName+'\',\''+obj[i].resAuthTypeCd+'\')"/></li>';
		$("#step"+step+" ul").append(items);
	}
}

/**
 * 点击取消
 */
function stepItemCancel(step){
	$("#step"+step).fadeOut("fast");
	$("#point"+(step-1)).fadeOut("fast");
	$("#step"+(step-1)+" input").removeClass("bg_blue");
	alert(step);
	if(step!=2){
		$("#stepItemCancel"+(step-1)).show();
	}
	//加点击事件
	$("#step"+(step-1)).children().children().find(".btn_step_item").attr("flag","1");
}
/**
 * 跳到模板
 */
function jumpToTemplate(authTypeCd){
	curAuthTypeCd=authTypeCd;
	openDetail('','1');
	return false;
}
/**
 * 增加步骤step
 * @return
 */
function addItemStep(){
	var step = $(".step").length+1;
	var divStr = '';
	if(step==1){
		divStr += '<div class="step" id="step'+step+'">';
		divStr += '<div class="stepBody" style="width:100%">';
		divStr += '	<ul>';
		divStr += '	</ul>';
		divStr += '	<div class="clear"></div>';
		divStr += '</div>';
		divStr += '	<div class="clear"></div>';
		divStr += '</div>';
	}else{
		divStr += '<div class="step" id="step'+step+'">';
		divStr += '<div class="stepBody" >';
		divStr += '	<ul>';
		divStr += '	</ul>';
		divStr += '	<div class="clear"></div>';
		divStr += '</div>';
		divStr += '<div class="cancelDiv">';
		divStr += '		<input type="button" class="btn_red" id="btn_cancel'+step+'" value="取消" onclick="removeStep(\''+step+'\')"/>';
		divStr += '</div>'
		divStr += '	<div class="clear"></div>';
		divStr += '</div>';
	}
	$("#maincontent").append(divStr);
	$("#btn_cancel"+(step-1)).addClass("dis_none");
}
/**
 * 增加step元素 按钮
 * @param step步骤
 * @param text按钮显示文字
 * @param 值1
 * @param 值2
 * @return
 */
function addItemBtn(step,text,val1,val2){
	var obj = $(".stepBody:last ul");
	var items = "<li><input type='button' value='"+text+"' class='btn_step_item' /><li>";
	$(obj).append(items);	
}
/**
 * 增加step元素 按钮
 * @param step步骤
 * @param text按钮显示文字
 * @param 值1
 * @param 值2
 * @return
 */
function addItemText(step,text,val1,val2){
	var obj = $(".stepBody:last ul");
	var items = "<li><label class='lab_step_item'>"+text+":&nbsp;<input type='text' name='"+name+"' value='' class='txt_step_item' /></label><li>";
	$(obj).append(items);
}
/**
 * 增加step元素 文本框
 * @param step步骤
 * @param tip提示文字
 * @param text按钮显示文字
 * @param 值1
 * @param 值2
 * @return
 */
function addItemCheckBox(step,tip,text,val1,val2){
	var obj = $(".stepBody:last ul");
	var items = "<li><input type='button' value='点击我' class='btn_step_item' /><li>";
	$(obj).append(items);
}
/**
 * 增加步骤元素 单选框
 * @param step
 * @param tip
 * @param text
 * @param val1
 * @param val2
 * @return
 */
function addItemRadio(step,tip,name,val1,val2){
	var obj = $(".stepBody:last ul");
	var items = "<li><label class='lab_step_ckbx'>"+tip+":&nbsp;<input name='"+name+"' type='radio' /></label><li>";
	$(obj).append(items);
}

/**移除步骤*/
function removeStep(step){
	if(step!=2){
		$("#btn_cancel"+(step-1)).removeClass("dis_none");
	}
	$("#step"+(step-1)).children().children().find(".btn_step_item").attr("flag","1");
	$("#step"+step).remove();
	$("#step"+(step-1)).find("table tr:last").show();
	$("#approverDiv").hide();
}
/**清空*/
function resetBody(){
	$("#maincontent").empty();
}
/**
 * 增加步骤step(Table的方式)
 */
function addItemStepTr(){

	var step = $(".step").length+1;
	var divStr = '';
		divStr += '<div class="step" id="step'+step+'">';
		divStr += '<div class="stepBody" style="width:100%">';
		divStr += '	<table class="mainTable margin_5px">';
		divStr += '	</table>';
		divStr += '</div>';
		divStr += '	<div class="clear"></div>';
		divStr += '</div>';
	$("#maincontent").append(divStr);
	$("#btn_cancel"+(step-1)).addClass("dis_none");
}
/**
 * 获取所有被选中的按钮的值，并且创建隐藏表单
 * 
 */
function getAllCkedBtn(){
	var itms = $(":input[cked=on]");
	$.each(itms,function(i,j){
		alert($(j).val());
	});
}
/***
 * 给表单加上只读效果
 * @return
 */
function readonlyForm(){
	$("#templetForm :input").filter(".inputBorder").attr("readonly","readonly");
	$("#templetForm :input").filter(".inputBorderNoTip").attr("readonly","readonly");
	$("#templetForm :input").filter(".inputBorder").addClass("inputBorder_readOnly");
	$("#templetForm :input").filter(".inputBorderNoTip").addClass("inputBorder_readOnly");
	$("#templetForm .Wdate").filter("[edit!=true]").attr("onclick","");
	$("#templetForm .Wdate").filter("[edit!=true]").attr("onfocus","");
	$("#templetForm .Wdate").filter("[edit!=true]").attr("onblur","");
	$("#ccbpNo").focus(function(){$("#ccbpNo").blur()});
	$("#ccbpNo").click(function(){$("#ccbpNo").blur()});
	$("input[type='checkbox']").filter("[edit!=true]").unbind('click').removeAttr("validate");
	$("input[type='checkbox']").filter("[edit!=true]").click(function(){
		return false;
	});
	$("#uploadBtnId").attr("disabled","disabled");

	$('.singleSelect').unbind('click').css('cursor','');
	$('.mutiSelect').unbind('click').css('cursor','');
	$('.orgSelect').unbind('click').css('cursor','');
	$('.projSelect').unbind('click').css('cursor','');
	
}
/***
 * 恢复表单可编辑属性
 * @return
 */
function backspringForm(){
	$("#templetForm :input").filter(".inputBorder").removeAttr("readonly");
	$("#templetForm :input").filter(".inputBorderNoTip").removeAttr("readonly");
	$("#templetForm .Wdate").focus(function(){WdatePicker()});
	$("input[type='checkbox']").unbind("click");
	$("#uploadBtnId").removeAttr("disabled").removeClass("inputBorder");
	$("#ccbpNo").unbind("click");
	$("#ccbpNo").unbind("focus");
	addEvent();
}
/**
 * 日期计算
 * @return
 */
function onchange_DesignTotalDay() {
	var fromDate = $("#fromDate").val();
	var toDate = $("#toDate").val();
	if(isEmpty(fromDate) || isEmpty(toDate)) {
		return;
	}
	var fArray = fromDate.split("-");
	var tArray = toDate.split("-");
	var fDate = new Date(fArray[0],fArray[1]-1,fArray[2]);
	var tDate = new Date(tArray[0],tArray[1]-1,tArray[2]);
	if(tDate.getTime()<fDate.getTime()) {
		$("#totalDay").val("");
		return;
	}
	var day = Math.abs(tDate.getTime()-fDate.getTime())/1000/60/60/24+1;
	$("#totalDay").val(day);
}
/***
 * 点击合同文本库按钮操作事件
 * @return
 */
function doContTemplet(){
	//sn=QQ
	var getContUrl= _ctx + "/sc/sc-contract-templet-info!conSelect.action?frameId=156&sn=";
	if(parent.TabUtils==null){
		window.open(getContUrl);
	}else{
		parent.TabUtils.newTab("scconInfo_Select","合同文本库",getContUrl,true);
	}
}
/**
 * 选择项目名称
 * @return
 * TODO
 */
function addEvent(){
	$('.projSelect').orgSelect({
		orgMuti:false
	});
	addClickAction('',true);
	quickSearch_ccbpNo();
	quickSearch_bidSerialTypeNo();
	quickSearch_contractNo();
}
/**
 * 编辑
 * @return
 */
function doDeleteUser(){
	$("#div_approve_user").html("");
}
/**
 * 招标计划编号 的quickSearch
 * @return
 */
function quickSearch_ccbpNo(){
	$('#ccbpNo').attr('title','请选择【成本系统-招标平台（工程）】中的‘招标’计划编号');
	$('#ccbpNo').quickSearch(
		_ctx + '/plan/cost-ctrl-bid-purc!quickSearchCcbp.action',
		['ccbpProjectName','ccbpDataTypeName','ccbpNo','ccbpPlanContentDesc'],
		{ccbpId:'ccbpId',ccbpNo:'ccbpNo'},
		'',
		function(jdom){
			var t = jdom.attr('ccbpPlanContentDesc');
			$('#ccbpPlanContentDesc').val("工程："+t);
			$('#ccbpPlanContentDescSpan').attr("title",t);
			$('#ccbpPlanContentDescSpan').html("工程："+t);
		},
		{projectCd:'projectCd',dataTypeCd:'dataTypeCd'}
	);
}

function quickSearch_bidSerialTypeNo(){
	$('#bidSerialTypeNo').attr('title','请选择【成本系统-招标平台（战略）】中的‘战略’计划编号');
	$('#bidSerialTypeNo').quickSearch(
		_ctx + '/bid/bid-ledger!quickSearchZlzb.action',
		['bidProjectName','bidSerialTypeNo','bidSectionName'],
		{bidLedgerId:'bidLedgerId',bidSerialTypeNo:'bidSerialTypeNo'},
		'',
		function(jdom){
			var arr = new Array(); 
			arr.push(jdom.attr('bidSectionName'));
			var t = arr.join('');
			$('#bidSectionName').val("工程："+t);
		
			$('#bidSectionNameSpan').attr("title",t);
			$('#bidSectionNameSpan').html("工程："+t);
		
		},
		{projectCd:'projectCd'}
	);
}
function leaveBidSerialTypeNo(){
	var bidSerialTypeNo = $('#bidSerialTypeNo').val();
	if( bidSerialTypeNo == ''){
		$('#bidSectionNameSpan').html('');
	}
}

function quickSearch_contractNo(){
	var ids={
		contractNo:'contractNo',
		contractTempletInfoId:'contractTempletInfoId',
		contractTempletHisId:'contractTempletHisId'
	};
	$("#contractNo").quickSearch(_ctx+"/sc/sc-contract-templet-info!quickSearch.action",['contractName','contractNo'],ids,{sn:""},function(e){
		var $s = $(e);
		if($s.attr("isStandard") == '1'){
			$("#nonstandard").val("0");
			$("#standard").val("1");
		}else{
			$("#nonstandard").val("1");
			$("#standard").val("0");
		}
			//reloadContAttachToRes();
	});
}

function reloadContAttachToRes() {
	//清空app-attachment-resShow.jsp页面中#attachList DIV中合同附件列表 
	//这是由于当网批发布提交后进入页面会先执行reloadContAttachToRes() 加载一次合同文库中附件  页面加完时会调用 app-attachment-resShow.jsp reloadScAttachs() 再加载一次合同文库库附件
	$("#attachList").html("");
	//清空当前页面附件区域
	$("#scattachList").html("");
	var scContractId = $("#contractTempletInfoId").val();
	alert(scContractId);
	if (isNotEmpty(scContractId)) {
		var data = {
			id : scContractId
		};
		data.scContractId = $("#contractTempletInfoId").val();
		data.hisContId = $("#contractTempletHisId").val();
	     //是否从合同文本库中抓取附件
     //   if($("#isGraspAttach").val() == "1"){
		$.post(_ctx + "/sc/sc-contract-templet-info!loadResAttachList.action",
				data, function(result) {
			        //加载合同文本中对应的附件
					$("#scattachList").html(result);
					if($("#scattachList").find("#attachSize").val() == "0"){	
						//无附件不抓取			
						return;
					}
				
            	   //抓取的附件个数
                   var graspCount=0;
					//附件
					$.each($("td[resattachname]"),function(i,td){
					  var resAttachName= $(td).attr("resattachname");
					 //如果当前附件不显示，则直不需要抓取
					 if($(td).parent().css("display") != 'none' && resAttachName){
					  //清空附件中的值
					  $(td).val("");
					  $(td).next().next().html('');	
					  $(td).next().find(":hidden").val("");
					      var resAttachName= $(td).attr("resattachname");
					  	$.each($("#scattachList").find("td[attachHdName="+resAttachName+"]"),function(){
					   	//设置值，为空则不允许提交
					  		$(td).val(resAttachName);
					  		$(td).next().find(":hidden").val(resAttachName);
					  		$(td).next().next().append($(this).html());
					  		graspCount++;
								});
					  	if($(td).val()==""){
									  //还原始数据，否则不使用合同库,直接使用页面上传文件成功附件不显示
							   $(td).next().next().html('<div id="show_bidContractFileId"></div>');
										}
                       //移除合同文本库附件区域中对应附件
					  	$("tr[attachHdName="+resAttachName+"]").remove();
				     	$("div[attachHdName="+resAttachName+"]").remove();
					 }
					});
					
					   //附件 处理网批中DIV[resattachname] 
					  $.each($("div[resattachname]"),function(i,div){								
						   var resAttachName= $(div).attr("resattachname");
						     if(resAttachName.indexOf("采购数量及技术参数确认单")>-1){
                              resAttachName="采购数量及技术参数确认单";
						      }
						   //如果当前附件不显示，则直不需要抓取
						   if($(div).parent().css("display") != 'none' && resAttachName){
							   //清空当前附件列表
							   $(div).html('');
							   //清空附件中的值
							  $(div).prev().prev().val("");	
							  //清空隐藏域中的值	
							   $(div).prev().find(":hidden").val("");			
							     // 查找					    
						    	$.each($("#scattachList").find("td[attachHdName="+resAttachName+"]"),function(){ 
										    	//设置值，为空则不允许提交 （发）
									    		$(div).prev().prev().val(resAttachName);
									    		$(div).prev().find(":hidden").val(resAttachName);
									    		$(div).append($(this).html());
									    		graspCount++;
											});
						    		if($(div).prev().prev().val()==""){
												  //还原始数据，否则不使用合同库,直接使用页面上传文件成功附件不显示
										   $(div).html('<div id="show_bidContractFileId"></div>');
											}
						        //移除合同文本库附件区域中对应附件
						    	$("tr[attachHdName="+resAttachName+"]").remove();
						       	$("div[attachHdName="+resAttachName+"]").remove();
						   }
					  });
				});

        }
	//}
}
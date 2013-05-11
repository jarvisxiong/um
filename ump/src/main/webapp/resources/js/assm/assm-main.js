var gTreePanel;
var arrCheck;
var parentassmName;

var gTreePanel2;
var arrCheck2;
var parentassmName2;
function loadAssmTree(paId,asId,isAccount){
	$("div.TreePanel").remove();
	var url = _ctx + '/assm/assm-manage!getAssmModelTree.action';
	$.post(url, {}, function(result){
		$('#leftTreePanel').empty();
		gTreePanel = new TreePanel({
			renderTo:'leftTreePanel',
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		gTreePanel.addListener("click",function(node){
			var levelCd =node.attributes['levelCd'];
			if('2'==levelCd || '3'==levelCd){
				arrCheck=node.attributes['entityId'];
				parentassmName=node.attributes['text'];
				try{
					$("#hide_pratentId").val(arrCheck);
					$("#gloab_hide_pratentId").val(arrCheck);
					$("#inp_parentassmName").val(parentassmName);
					$("#modelNameHolder").html(parentassmName);
					$("#currentModelId").attr('modelId',arrCheck);
					if('3'==levelCd){
						//加载总体情况信息(当前配置)
						modelDetail(arrCheck);
						$("#currentModelId").show();
						$("#modelDetail").show();
					}else if('2'==levelCd){
						$("#modelDetail").hide();
					}
				}catch(e){			
				}
				if (isAccount!=null && isAccount!='') {
					if('3'==levelCd){
						//搜索权限判断标示
						var accountView = $("#accountView").val();
						if(accountView !=null && accountView=="accountView"){
							loadAccount();//搜索资产台账
						}
					}
				}else{
					//搜索权限判断标示
					var modelView = $("#modelView").val();
					if(modelView !=null && modelView=="modelView"){
						loadModel(); //搜索设备型号列表
					}
				}
				$("#parentId").val(arrCheck); //搜索表单型号ID
			}else{
				arrCheck='';
				try{
					$("#parentId").val('');
					$("#hide_pratentId").val('');
					$("#gloab_hide_pratentId").val('');
					$("#inp_parentassmName").val('');
					//$("#currentModelId").attr('modelId','');
				}catch(e){					
				}
			}				
		});
		gTreePanel.render();
		gTreePanel.isExpendSelect = true;
		gTreePanel.on(function(node){
			var nodeType =node.attributes.nodeType;				
			//若非根节点 
			if("0" != nodeType){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}
			}
		}); 
		if(paId){
			gTreePanel.getNodeById(paId).expand();
			gTreePanel.setFocusNode(gTreePanel.getNodeById(asId));
		}
	});
}

function loadAssmTree2(paId,asId){
	var url = _ctx + '/assm/assm-manage!getAssmModelTree.action';
	$.post(url, {}, function(result){
		gTreePanel2 = new TreePanel({
			renderTo:'leftTreePanel2',
			'root' : eval('('+result+')'),
			'ctx':_ctx
		});
		gTreePanel2.addListener("click",function(node){	
			if('3'==node.attributes['levelCd']){
				arrCheck2=node.attributes['entityId'];
				parentassmName2=node.attributes['text'];
				try{
					$("#pop_cur_model").html(parentassmName2);
					loadStandardList(arrCheck2);
				}catch(e){					
				}
			}else{
				arrCheck2='';
				try{
				}catch(e){					
				}
			}	
		});
		gTreePanel2.render();
		gTreePanel2.isExpendSelect = true;
		gTreePanel2.on(function(node){
			var nodeType =node.attributes.nodeType;				
			//若非根节点 
			if("0" != nodeType){
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}
			}			
		}); 
		gTreePanel2.getNodeById(paId).expand();
		gTreePanel2.setFocusNode(gTreePanel2.getNodeById(asId));
	});
}

//设备型号管理
function modelMaint(dom){
	arrCheck="";
	$("#parentId").val('');
	$("#gloab_hide_pratentId").val('');
	//快速搜索
	$("#account_quick").hide();
	$("#model_quick").val('');
	$("#model_quick").show();
	//高级搜索div
	resetQuickSearch($("#model_quick"));
	$("#accountSearchBtu").hide();
	$("#searchAccountDiv").hide();
	$("#modelSearchBtu").show();
	$("#sbBtu").removeClass('btn_blue').addClass('btn_blue_select');
	$("#zcBtu").removeClass('btn_blue_select').addClass('btn_blue');
	$("#zczjBtu").removeClass('btn_blue_select').addClass('btn_blue');
	//资产折旧div
	$("#depreciationDiv").hide();
	//资产台账增加div
	$("#accountAddDiv").hide();
	//增加、删除按钮
	$("#assmAccountBtu").hide();
	$("#assmModelBtu").show();
	$("#modeFormDiv").hide();
	
	$("#titleSpan").html('设备型号管理');
	$("#modelDetail").hide();
	$("#mainDiv").show();
	loadAssmTree('', '', '');
	//加载列表
	loadModel();
}

//资产管理台帐
function accountMaint(dom){
	arrCheck="";
	$("#parentId").val('');
	$("#gloab_hide_pratentId").val('');
	//快速搜索
	$("#model_quick").hide();
	$("#account_quick").val('');
	$("#account_quick").show();
	//高级搜索div
	resetQuickSearch($("#account_quick"));
	$("#modelSearchBtu").hide();
	$("#searchModelDiv").hide();
	$("#accountSearchBtu").show();
	$("#zcBtu").removeClass('btn_blue').addClass('btn_blue_select');
	$("#sbBtu").removeClass('btn_blue_select').addClass('btn_blue');
	$("#zczjBtu").removeClass('btn_blue_select').addClass('btn_blue');
	//资产折旧div
	$("#depreciationDiv").hide();
	//资产台账增加div
	$("#accountAddDiv").hide();
	//增加、删除按钮
	$("#assmModelBtu").hide();
	$("#assmAccountBtu").show();
	$("#modeFormDiv").hide();

	$("#modelDetail").hide();
	$("#titleSpan").html('资产管理台帐');
	$("#mainDiv").show();
	loadAssmTree('', '', 'true');
	loadAccount();
}

//资产折旧
function depreMaint(dom){
	$("#parentId").val('');
	$("#quickAccSpan").hide();
	$("#quickModSpan").hide();
	$("#model_quick").hide();
	$("#account_quick").hide();
	$("#modelSearchBtu").hide();
	$("#accountSearchBtu").hide();
	$("#searchAccountDiv").hide();
	$("#searchModelDiv").hide();
	$("#accountAddDiv").hide();
	$('#mainDiv').hide();
	$("#zczjBtu").removeClass('btn_blue').addClass('btn_blue_select');
	$("#sbBtu").removeClass('btn_blue_select').addClass('btn_blue');
	$("#zcBtu").removeClass('btn_blue_select').addClass('btn_blue');
	loadDepre();
}

//高级搜索
function doQuery(searchForm){
	TB_showMaskLayer("正在搜索...");
	if("assmAccount"==searchForm){
		$("#assmAccountId").val('');
		//资产台账
		$("#mainForm").attr("action",_ctx+"/assm/assm-account!list.action");
	}else{
		//设备型号
		$("#mainForm").attr("action",_ctx+"/assm/assm-model!loadList.action");
	}
	$("#mainForm").ajaxSubmit(function(result){
		TB_removeMaskLayer();
		$("#resultTable").html(result);
		$("#accountAddDiv").hide();
		$("#mainDiv").show();
	});
}

//清空高级搜索的值
function doClear(){
	$("#mainForm")[0].reset();
	//清空人员选择框已选择的人员
	var	_userMap = {};
	var o = {userName:'',uiid:''};
	_userMap[$("#s_keeperName").val()] = o;
	var data = $.extend(true,{},null);
	$("#s_keeperName").data('userMap',data);
	_userMap[$("#s_creatorName").val()] = o;
	$("#s_creatorName").data('userMap',data);
	$("#parentId").val(arrCheck);
	$("#s_projectCd").val('');
}

//高级搜索-选择设备型号
function doChooseModel(){
	ymPrompt.confirmInfo( {
		icoCls : "",
		autoClose:false,
		message : "<div id='selectTypeDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 580,
		height : 500,
		title : "设备型号选择",
		afterShow : function() {
			var url = _ctx+"/assm/assm-account!getAssmModel.action";
			$.post(url, {}, function(result) {
				$("#selectTypeDiv").html(result);
			});
		},
		handler : function(btn){
			if(btn=='ok'){
				//选中的第四级设备的ID
				var selectAssmModelId = $('#selectAssmModelId').val();
				//选中的第四级设备的名称
				var selectAssmModelName = $('#selectAssmModelName').val();
				if(isEmpty(selectAssmModelId)){
					alert('请选择四级设备');
					return false;
				}
				$("assmModelId").val(selectAssmModelId);
				$("#s_assmModelName").val(selectAssmModelName);
				
			}
			ymPrompt.close();
		},
		btn:[["确定",'ok'],["退出",'cancel']]
	});
}

//加载设备型号列表
function loadModel(){
	var parentId = "";
	//父节点Id
	if(arrCheck){
		parentId = arrCheck;
	}
	var s_assmName = $("#s_assmName").val();
	var s_assmCode = $("#s_assmCode").val();
	var s_proCode = $("#s_proCode").val();
	var s_fullCode = $("#s_fullCode").val();
	var data={
			parentId:parentId,
			s_assmName:s_assmName,
			s_assmCode:s_assmCode,
			s_proCode:s_proCode,
			s_fullCode:s_fullCode
	};
	TB_showMaskLayer("正在搜索...");
	var url=_ctx+"/assm/assm-model!loadList.action";
	$.post(url,data,function(result){
		TB_removeMaskLayer();
		$("#resultTable").html(result);
		$("#depreciationDiv").hide();
		$("#mainDiv").show();
	});
}

//显示/影藏设备维护新增DIV
function showModeFormDiv(){
	if($("#modeFormDiv").is(":hidden")){
		$("#modeFormDiv").show();
	}else{
		$("#inp_assmName").val('');
		$("#inp_assmCode").val('');	
		$("#inp_proCode").val('');	
		$("#inp_fullCode").val('');
		$('#modeFormDiv').hide();
	}
}

//保存设备型号维护
function saveNewModel(dom){
	if(validateModelForm()){
		var url1 = _ctx+"/assm/assm-model!checkAssmName.action";
		$.post(url1,{assmName:$("#inp_assmName").val()},function(result){
			if ("exsit" == result) {
				alert("该设备名称已经存在");
				$("#inp_assmName").focus();
				return false;
			}else if("success" == result){
				TB_showMaskLayer("正在保存...");
				var inp_pratentId = $("#hide_pratentId").val();
				var inp_assmName = $("#inp_assmName").val();
				var inp_assmCode = $("#inp_assmCode").val();	
				var inp_proCode = $("#inp_proCode").val();	
				var inp_fullCode = $("#inp_fullCode").val();
				var data ={
					inp_pratentId:inp_pratentId,
					inp_assmName:inp_assmName,
					inp_assmCode:inp_assmCode,
					inp_proCode:inp_proCode,
					inp_fullCode:inp_fullCode
				};
				var url = _ctx+"/assm/assm-model!newMode.action";
				$.post(url,data,function(result){
					TB_removeMaskLayer();
					var rs=result.split(",");
					//清理表单
					if("success"==rs[0]){
						$("#inp_assmName").val('');
						$("#inp_assmCode").val('');	
						$("#inp_proCode").val('');	
						$("#inp_fullCode").val('');	
						$("#rsTip").html("<font color='red'>保存成功</font>").fadeIn(100).fadeOut(2000);
						loadModel();
						if(rs[1]=='1'){
							loadAssmTree(rs[2],rs[3]);					
						}
					}else{
						$("#rsTip").html("<font color='red'>本设备型号已经存在</font>").fadeIn(100).fadeOut(2000);
					}		
				});
			}else{
				alert("保存失败");
				return false;
			}
		});
	}
}

//验证设备型号维护新增表单
function validateModelForm(){
	var pratentId=$("#hide_pratentId").val();
	if(pratentId==null||pratentId==''){
		alert('请选择一种设备类型');
		return false;
	}
	var assmName=$("#inp_assmName").val();
	if(assmName==null||assmName==''){
		alert('设备名称不能为空');
		return false;
	}
	if(!validateLenth(assmName,50,'设备名称')){
		return false;
	}
	var assmCode=$("#inp_assmCode").val();
	if(assmCode==null||assmCode==''){
		alert('编码不能为空');
		return false;
	}
	if(!validateLenth(assmCode,50,'编码')){
		return false;
	}
	return true;
}

//判断最长，包括中文
function validateLenth(orData,max,alertName){
	//判断最长
	var length = orData.replace(/[^\x00-\xff]/g,"**").length; 	
	if(length>max){
		alert(alertName+' 不能超出'+max+'个字符！');
		return false;
		}
	return true;
}

//取消新增设备型号
function cancelNewModel(dom){
	$("#inp_assmName").val('');
	$("#inp_assmCode").val('');	
	$("#rsTip").html('');
	$("#addModelBtn").trigger('click');
}

//标准配置
function standModel(dom){
	var assmModelId=$("#currentModelId").attr('modelId');
	if(assmModelId){
		var data={assmModelId:assmModelId};
		var url = _ctx+'/assm/assm-model-standard!loadList.action';
		ymPrompt.confirmInfo( {
			icoCls : "",
			autoClose:false,
			message : "<div id='selectTypeDiv' style='padding-right:10px;'><img align='absMiddle' src='"+ _ctx + "/images/loading.gif'></div>",
			width : 660,
			height :345,
			title : "标准配置",
			afterShow : function() {
				$.post(url, data, function(result) {
					$("#selectTypeDiv").html(result);
				});
			},
			handler : function(btn){
				modelDetail(assmModelId);
				ymPrompt.close();
			},
			closebtn:true,
			btn:[]
		});
	}
}


//加载项目标准配置列表
function loadStandardList(assmModelId){
	var url=_ctx+'/assm/assm-model-standard!loadList.action?listType=onlyTable';
	var data={assmModelId:assmModelId};
	$.post(url, data, function(result) {
		$("#popStandTable").html(result);
	});
	
}

//获取设备类型详情
function modelDetail(assmModelId){
	var url=_ctx+'/assm/assm-model-standard!modelDetail.action';
	var data={assmModelId:assmModelId};	
	$.post(url, data, function(result) {
		var rs=result.split(',');
		$("#hasNum_detail").html(rs[0]);
		$("#stanNum_detail").html(rs[1]);		
	});
}

//删除设备类型
function deleteModel(){
	var modelIds="";
	$("input[type='checkbox'][assmId]").each(function(){
		if($(this).attr('checked')==true){
			var modelId=$(this).attr('assmId');
			modelIds = modelIds+","+modelId;
		}			
	});	
	//需删除的所有设备型号
	modelIds = modelIds.substring(1,modelIds.length);
	if(modelIds.length<1){
		alert('请选择需要删除的设备信息！');
		return ;
	}else{
		if(window.confirm('确认删除？')){
			var data={modelIds:modelIds};
			var url=_ctx+"/assm/assm-model!deleteBatch.action";
			TB_showMaskLayer("正在删除...");
			$.post(url,data,function(result){
				TB_removeMaskLayer();
				if("success"==result){
					$("#modelDelTip").html("<font color='red' style='vertical-align: middle;'>删除成功</font>").fadeIn(100).fadeOut(2000);
					loadAssmTree('','');
					jumpPageTo();
					loadModel();
				}else if("error"==result){
					$("#modelDelTip").html("<font color='red' style='vertical-align: middle;'>删除出错，请核实！</font>").fadeIn(100).fadeOut(2000);
				}else{
					var str = result.split("|");
					var tips = "";
					if(str[0]!=null&&""!=str[0]){
						tips = str[0]+"无法删除，请先删除挂钩的设备型号";
					}
					if(str[1] !=null&&""!=str[1]){
						var s;
						if(tips == ""){
							s = tips;
						}else{
							s =tips+"；";
						}
						tips = s+str[1]+"无法删除，请先删除挂钩的资产";
					}
					alert(tips);
					loadAssmTree('','');
					jumpPageTo();
					loadModel();
				}
			});
		}
	}		
}

//编辑设备型号
function updateModel(){
	var url=_ctx+"/assm/assm-model!save.action";
	var data={};
	var assmName=$("#upd_assmName").val();
	var assmCode=$("#upd_assmCode").val();
	var proCode=$("#upd_proCode").val();
	var fullCode=$("#upd_fullCode").val();
	var assmModelId=$("#upd_assmModelId").val();
	data['entity.assmName']=assmName;
	data['entity.assmCode']=assmCode;
	data['entity.proCode']=proCode;
	data['entity.fullCode']=fullCode;
	data['assmModelId']=assmModelId;	
	$.post(url,data,function(result){
		var rs=result.split(",");
		//关闭修改框
		ymPromptclose();
		//重新加载数据
		loadModel();
		loadAssmTree(rs[0],rs[1]);
	});
}

//-----------资产折旧---------------------//
function loadDepre(){
	var url=_ctx+"/assm/assm-depreciation!list.action";
	TB_showMaskLayer("正在搜索...");
	$.post(url,{},function(result){
		TB_removeMaskLayer();
		$("#depreciationDiv").html(result);
		$("#depreciationDiv").show();
	});
}

//-----------资产台账---------------------//

//加载资产台账列表
function loadAccount(tips){
	$("#assmAccountId").val('');
	var s_useStatus = $("#s_useStatus").val();
	var s_account_assmName = $("#s_account_assmName").val();
	var s_code = $("#s_code").val();
	var s_creator = $("#s_creator").val();
	var s_useDepartment = $("#s_useDepartment").val();
	var s_useDate = $("#s_useDate").val();
	var s_keeperCd = $("#s_keeperCd").val();
	var s_projectCd = $("#s_projectCd").val();
	if(tips==null||tips==''){
		TB_showMaskLayer("正在搜索...");
	}
	var parentId = "";
	if(arrCheck){
		parentId = arrCheck;
	}
	var data={
			parentId:parentId,
			s_useStatus:s_useStatus,
			s_account_assmName:s_account_assmName,
			s_code:s_code,
			s_creator:s_creator,
			s_useDepartment:s_useDepartment,
			s_useDate:s_useDate,
			s_keeperCd:s_keeperCd,
			s_projectCd:s_projectCd
	};
	var url=_ctx+"/assm/assm-account!list.action";
	$.post(url,data,function(result){
		TB_removeMaskLayer();
		$("#accountAddDiv").hide();
		$("#depreciationDiv").hide();
		$("#resultTable").html(result);
		$("#mainDiv").show();
	});
}

//查看单条资产台账信息
function getAccountDetail(id){
	var url=_ctx+"/assm/assm-account!input.action";
	TB_showMaskLayer("正在查看...");
	$.post(url,{id:id,isLook:"true"},function(result){
		TB_removeMaskLayer();
		$("#mainDiv").hide();
		$("#accountAddDiv").html(result);
		$("#accountAddDiv").show();
	});
}

//“增加”资产台账
function addAssmAccount(){
	var pratentId = $("#gloab_hide_pratentId").val();
	var data = {assmModelId:pratentId};
	var url=_ctx+"/assm/assm-account!input.action";
	TB_showMaskLayer("正在加载...");
	$.post(url,data,function(result){
		TB_removeMaskLayer();
		$("#mainDiv").hide();
		$("#searchAccountDiv").hide();
		$("#accountAddDiv").html(result);
		$("#accountAddDiv").show();
	});
}

//保存资产台账
function saveAccount(){
	var assmAccountId = $("#assmAccountId").val();
	var code = $("#code").val();
	if(validateAccountForm()){
		//验证资产编码
		var url = _ctx+"/assm/assm-account!checkCode.action";
		$.post(url,{code:$.trim(code),id:assmAccountId}, function(result) {
			if(result=='true'){
				alert("资产编码已经存在");
				$("#code").focus();
				return false;
			}else{
				var currHasNum = parseInt($("#currHasNum").val());//当前配置
				var currStanNum = parseInt($("#currStanNum").val());//标准配置
				if(assmAccountId==null || ''==assmAccountId){
					currHasNum = currHasNum+1;
				}
				//电脑类特殊判断
				var computerType = $("#computerType").val();
				if("电脑" == computerType){
					var keeperCd = $("#keeperCd").val();
					var url = _ctx+"/assm/assm-account!checkKeeper.action";
					$.post(url,{keeperCd:$.trim(keeperCd),id:assmAccountId}, function(result) {
						if(result=='true'){
							if(confirm("您选中的保管人员已经保管有电脑设备，确认提交？")){
								numTips(currHasNum,currStanNum,assmAccountId);
							}
						}else{
							numTips(currHasNum,currStanNum,assmAccountId);
						}
					});
				}else{
					numTips(currHasNum,currStanNum,assmAccountId);
				}
			}
		});
	}
}

function doSaveAccount(assmAccountId){
	var selectThridModelId = $("#selectThridModelId").val();
	TB_showMaskLayer("正在保存...");
	$("#accountFrom").ajaxSubmit(function(result){
		if("success"==result){
			if(selectThridModelId != null && selectThridModelId!=''){
				arrCheck = selectThridModelId;
			}
			loadAccount('noTips');
		}else{
			TB_removeMaskLayer();
			alert("保存失败");
			return false;
		}	
	});
}

function numTips(currHasNum,currStanNum,assmAccountId){
	if(currHasNum > currStanNum){
		if(confirm("当前配置数大于标准配置数，确认提交？")){
			doSaveAccount(assmAccountId);
		}
	}else{
		doSaveAccount(assmAccountId);
	}
}

//修改资产台账
function updateAccount(id){
	var url = _ctx+"/assm/assm-account!input.action";
	TB_showMaskLayer("正在加载...");
	$.post(url,{id:id}, function(result) {
		TB_removeMaskLayer();
		$("#mainDiv").hide();
		$("#accountAddDiv").html(result);
		$("#accountAddDiv").show();
	});
}

//删除单个资产台账
function deleteAccount(id){
	var assmModelId = $("#gloab_hide_pratentId").val();
	var url = _ctx+"/assm/assm-account!delete.action";
	if(confirm("确认删除？")){
		TB_showMaskLayer("正在删除...");
		$.post(url,{id:id}, function(result) {
			TB_removeMaskLayer();
			if("success"==result){
				loadAccount();
			}else{
				alert("删除失败");
				return false;
			}
		});
	}
}

//批量删除资产台账
function deleteBatchAccount(){
	var accountIds="";
	$("input[type='checkbox'][assmId]").each(function(){
		if($(this).attr('checked')==true){
			var accountId=$(this).attr('assmId');
			accountIds=accountIds+","+accountId;
		}			
	});	
	//需删除的所有台账
	accountIds=accountIds.substring(1,accountIds.length);
	if(accountIds.length < 1){
		alert('请选择需要删除的资产台账信息！');
		return ;
	}else{
		if(window.confirm('确认删除？')){
			var data={accountIds:accountIds};
			var url=_ctx+"/assm/assm-account!deleteBatch.action";
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

//验证资产台账信息
function validateAccountForm(){
	//设备型号
	var assmModelName=$("#assmModelName").val();
	if(assmModelName==null||$.trim(assmModelName)==''){
		alert('设备型号不能为空');
		$("#assmModelName").focus();
		return false;
	}
	//资产名称
	var assmName=$("#assmName").val();
	if(assmName==null||$.trim(assmName)==''){
		alert('资产名称不能为空');
		$("#assmName").focus();
		return false;
	}
	if(!validateLenth(assmName,50,'资产名称')){
		$("#assmName").focus();
		return false;
	}
	//资产编码
	var code = $("#code").val();
	if(code==null||$.trim(code)==''){
		alert('资产编码不能为空');
		$("#code").focus();
		return false;
	}
	if(!validateLenth(code,50,'资产编码')){
		$("#code").focus();
		return false;
	}
	//使用使用部门
	var useDepartmentName=$("#useDepartmentName").val();
	if(useDepartmentName==null||$.trim(useDepartmentName)==''){
		alert('请选择使用部门');
		$("#useDepartmentName").focus();
		return false;
	}
	//保管人员
	var keeperName=$("#keeperName").val();
	var keeperName2=$("#keeperName2").val();
	if($.trim(keeperName)=='' && $.trim(keeperName2)==''){
		alert('保管人员不能为空');
		$("#keeperName").focus();
		return false;
	}
	//使用情况
	var useStatus=$("#useStatus").val();
	if(useStatus==null||$.trim(useStatus)==''){
		alert('请选择使用情况');
		$("#useStatus").focus();
		return false;
	}
	//使用时间
	var useDate=$("#useDate").val();
	if(useDate==null||$.trim(useDate)==''){
		alert('购入时间不能为空');
		$("#useDate").focus();
		return false;
	}
	//原值
	var srcValue=$("#srcValue").val();
	if(srcValue==null||$.trim(srcValue)==''){
		alert('原值不能为空');
		$("#srcValue").focus();
		return false;
	}else{
		if(srcValue<500){
			alert('原值必须大于等于500');
			$("#srcValue").focus();
			return false;
		}
	}
	return true;
}

//得到标准配置和当前配置数(台账)
function getModelStandard(){
	var projectCd = $("#projectCd").val();
	var assmModelId = $("#assmModelId").val();
	var data = {projectCd:projectCd,assmModelId:assmModelId};
	var url=_ctx+"/assm/assm-account!getModelStandard.action";
	$.post(url,data,function(result){
		var rs=result.split(',');
		$("#currNum").val(rs[0]);
		$("#stanNum").val(rs[1]);
	});
}

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
function showModelSearchDiv(){
	$('#searchAccountDiv').hide();
	if($("#searchModelDiv").is(":hidden")){
		$("#searchDiv").show();
		$("#searchModelDiv").show();
	}else{
		$("#searchDiv").hide();
		$('#searchModelDiv').hide();
		//doClear();
	}
}

//资产台账“取消”
function canleAccount(){
	$("#accountAddDiv").hide();
	$("#accountAddDiv").html('');
	$("#mainDiv").show();
}

//全选，反选
function checkAll(dom){
	var jdom=$(dom);
	if(jdom.attr('checked')==true){
		$("input[type='checkbox'][assmId]").each(function(){
			$(this).attr('checked',true);
		});
	}else{
		$("input[type='checkbox'][assmId]").each(function(){
			$(this).attr('checked',false);
		});
	}
}

var g_quicksearch='快速搜索...';
function resetQuickSearch(dom){
	if($(dom).val().trim() == ''){
		$(dom).val(g_quicksearch);
		$(dom).css({color:"#cccccc"});
	}else{
		$(dom).css({color:"#5A5A5A"});
	}
}
function clearQuickSearch(dom){
	if($(dom).val() == g_quicksearch){
		$(dom).val('');
		$(dom).css({color:"#5A5A5A"});
	}
} 


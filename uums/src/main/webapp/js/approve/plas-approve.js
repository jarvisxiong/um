
//选择上级机构
var parentTree;

//加载职位树
var gPosTree;

//调入机构
var gTranInTree;

//左边机构树
var gLeftTree;//user
var gLeftPosTree;//pos
 
function reloadUserTree(dom){
	if(typeof dom != 'undefined'){
		$(dom).addClass('nav_tree_click').siblings().removeClass('nav_tree_click');
	}
	
	$('#leftTree').show();
	$('#leftTreePos').hide();
	$('#leftPanel').attr("reltype","user");
	$('#btn_toggle_view').html('切换视角:职位树');

	var url = _ctx + '/plas/plas-approve!loadHrOrgUser.action';
	var isPosFlg = $('#load_pos_flag').attr('checked');
	$('#leftTree').html('<div style="height:80px;">&nbsp;</div>').mask('正在载入职务树,请稍候...');
	$.post(url, {isPosFlg: isPosFlg}, function(result) {
		$("#leftTree").unmask();
		$('#leftTree').html('');
		if (result) {

			gLeftTree = null;
			gLeftTree = new TreePanel({
				renderTo: "leftTree",
				'root'  : (result),
				'ctx'	:_ctx + ''
			}); 
			//自定义
			gLeftTree.isDelegateIcon = true;
			gLeftTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			gLeftTree.delegateOnMouseOverNode = function(node){
				return '';
			};
			
			//修饰所有节点
			for(var k in gLeftTree.nodeHash){
				var node = gLeftTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				if( nodeType == "0"){
					var sexCd = node.attributes.sexCd;
					switch(sexCd){
						case '1': node.iconPath = _ctx + "/images/webim/male_online.jpg"; break;
						case '2': node.iconPath = _ctx + "/images/webim/female_online.jpg"; break;
						default: node.iconPath = _ctx + "/images/webim/none_online.jpg"; break;
					}
				}else if( nodeType == "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}else{
					node.iconPath = _ctx + "/images/webim/none_online.jpg";
				}
			}
			gLeftTree.render(); 
			//单击事件
			gLeftTree.on(function(node){
				var userId  = node.attributes.entityId;
				if( $.trim(userId) == '' || $.trim(userId)=='0'){
					return;
				}
				
				var nodeType = node.attributes.nodeType;
				//机构
				if( nodeType == "1"){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
				//用户
				else if( nodeType == "0"){
					refreshUserArea(userId);
				}
			});
		}
	});
}

function reloadPosTree(dom){
	if(typeof dom != 'undefined'){
		$(dom).addClass('nav_tree_click').siblings().removeClass('nav_tree_click');
	}
	
	$('#leftTree').hide();
	$('#leftTreePos').show();
	$('#leftPanel').attr("reltype","pos");

	var url = _ctx + '/plas/plas-approve!loadHrPosTree.action';
	$('#leftTreePos').html('<div style="height:80px;">&nbsp;</div>').mask('正在载入职位树,请稍候...');
	$.post(url, {}, function(result) {

		$("#leftTreePos").unmask();
		$('#leftTreePos').html('');
		if (result) {

			gLeftPosTree = null;
			gLeftPosTree = new TreePanel({
				renderTo: "leftTreePos",
				'root'  : (result),
				'ctx'	:_ctx + ''
			}); 
			//自定义
			gLeftPosTree.isDelegateIcon = true;
			gLeftPosTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			gLeftPosTree.delegateOnMouseOverNode = function(node){
				return '';
			};
			
			//修饰所有节点
			for(var k in gLeftPosTree.nodeHash){
				var node = gLeftPosTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				if( nodeType == "3"){
					var sexCd = node.attributes.sexCd;
					switch(sexCd){
						case '1': node.iconPath = _ctx + "/images/webim/male_online.jpg"; break;
						case '2': node.iconPath = _ctx + "/images/webim/female_online.jpg"; break;
						default: node.iconPath = _ctx + "/images/webim/none_online.jpg"; break;
					}
				}else if( nodeType == "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}else{
					node.iconPath = _ctx + "/images/webim/none_online.jpg";
				}
			}
			gLeftPosTree.render(); 
			//单击事件
			gLeftPosTree.on(function(node){
				var posId  = node.attributes.entityId;
				if( $.trim(posId) == '' || $.trim(posId)=='0'){
					return;
				}
				
				var nodeType = node.attributes.nodeType;
				//机构
				if( nodeType == "1"){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
				//职位
				else if( nodeType == "3"){
					refreshPosArea(posId);
				}
			});
		}
	});
}


function refreshPosArea(posId){
	closeAllPosWin();
	$('#userDetailPanel').show().mask('加载信息...');
	$('#userDetailPanel').siblings().hide();
	var url = _ctx + "/plas/plas-approve!posMove.action";
	$.post(url,{posId: posId}, function(result) {
		$('#userDetailPanel').unmask();
		refreshComArea(result);
	});
}
/**
 * 刷新用户信息区域
 * @param userId  用户ID
 * @param parentId 机构ID
 *
 */
function refreshUserArea(userId){
	closeAllPosWin();
	$('#userDetailPanel').show().mask('加载信息...');
	$('#userDetailPanel').siblings().hide();
	var url = _ctx + "/plas/plas-approve!userMove.action";
	$.post(url,{plasUserId: userId}, function(result) {
		$('#userDetailPanel').unmask();
		refreshComArea(result);
	});
}

function refreshAcctArea(acctId){
	closeAllPosWin();
	$('#userDetailPanel').show().mask('加载信息...');
	$('#userDetailPanel').siblings().hide();
	var url = _ctx + "/plas/plas-approve!userMove.action";
	$.post(url,{plasAcctId: acctId}, function(result) {
		$('#userDetailPanel').unmask();
		refreshComArea(result);
	});
}

function refreshApproveArea(approveInfoId){
	closeAllPosWin();
	$('#userDetailPanel').show().mask('加载信息...');
	$('#userDetailPanel').siblings().hide();
	var url = _ctx + "/plas/plas-approve!input.action";
	$.post(url,{id: approveInfoId}, function(result) {
		$('#userDetailPanel').unmask();
		refreshComArea(result);
	});
}
//private
function refreshComArea(result){
	$("#userDetailPanel").html(result).show();

	try{
			
		//生效日期,必须在渲染之前
		$('#effectDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"-"))); 
			}
		});  
	}catch(e){
		
	}
	try{
		$('#birthday').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"-"))); 
			}
		});  
	}catch(e){
		
	}
	try{
		$('#attendWorkDate').datebox({
			formatter: function(date){
				return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
			},   
			parser: function(date){
				return new Date(Date.parse(date.replace(/-/g,"-"))); 
			}
		}); 
	}catch(e){
		
	}
	
	//$('#effectDate').next().find('input:first-child').attr('readonly','readonly');
	//$('#attendWorkDate').next().find('input:first-child').attr('readonly','readonly');
	
	//很重要：json加载的页面，需要经过下列方法渲染页面
	$.parser.parse('#userDetailPanel');
}


//调入用户
function tranInUser(){
	refreshApproveArea('');
}

//新增用户
function addUser(){
	refreshApproveArea('');
}

//离职人员
function leaveUser(){
	$('#leavePanel').show().siblings().hide();
}

//检查uiid
function validateUiid(){

	var uiid = $.trim($('#openAcct_uiid').val());
	//非空检查
	if( uiid == ''){
		$('#openAcct_uiid_tip').html('请输入').css({'color':'red'}).show();
		initTipList();
		return;
	}
	

	var pattern=/^[a-zA-Z0-9]{4,20}$/;
	if(!pattern.exec(uiid)){
		$('#openAcct_uiid_tip').html("账号格式不对!").css({'color':'red'}).show();
		return false;
	}
	
	

	var tId = $.trim($('#plasApproveInfoId').val());
	var url = _ctx + '/plas/plas-approve!isUiidExists.action';
	var data = {uiid:uiid,plasApproveInfoId:tId};
	$('#openAcct_uiid_tip').hide();
	$('#acctListDiv').hide();
	$.post(url,data,function(result){
		if(result=='true'){
			$('#openAcct_uiid_tip').html('已占用. ').css({'color':'red'}).show();
		}else{
			$('#openAcct_uiid_tip').html('可用').css({'color':'green'}).show();
		}
	});
}
function initTipList(){
	//$('#acctListDiv').html('提示:账号以首字全拼加其他字首字符.<br/>例如:<br/>若张三,则zhangsan,zhansan1...<br/>若欧阳振华,则ouyzh,ouyzh1...').show();
}

//查询照片清单
function reloadPics(id){
	$('#pics_area').mask('载入照片中...');
	var url = _ctx + "/plas/plas-approve!pics.action";
	var data = {id: id};
	$.post(url,data,function(result){
		$('#pics_area').unmask();
		$("#pics_area").html(result); 
	});
}

function showParentOrg(){
	$('#parentOrgTree').html('<div style="height:60px;">&nbsp;</div>').mask('正在载入机构,请稍候...');
	var url = _ctx + '/plas/plas-approve!loadHrOrg.action';
	$.post(url, function(result) {
		$('#parentOrgTree').empty().unmask();
		if (result) {
			parentTree = new TreePanel({
				renderTo : "parentOrgTree",
				'root' : (result),
				'ctx' : _ctx + ''
			});
			//自定义
			parentTree.isDelegateIcon = true;
			parentTree.delegateGetDelegateIcon = function(node) {
				return node.iconPath;
			};
			parentTree.delegateOnMouseOverNode = function(node) {
				return '';
			};
			parentTree.render();
			//修饰所有节点
			var tmpNode = null;
			var tmpNodeType = null;
			for ( var k in parentTree.nodeHash) {
				tmpNode = parentTree.nodeHash[k];
				tmpNodeType = tmpNode.attributes.nodeType;
				if( tmpNodeType == "1" && tmpNode.isLeaf()){
					tmpNode.iconPath = _ctx + "/images/imgTree/page.gif";
				}
			}
			//单击事件
			parentTree.on(function(node) {
				var nodeType = node.attributes.nodeType;
				if(nodeType == '1') {//机构
					$('#parentOrgTree').attr("orgId", node.id);
					$('#parentOrgTree').attr("orgName", node.attributes.entityName);
					$('#parentOrgTree').attr("orgPath", node.getPath('entityName').replace(new RegExp('/',"gm"),' - '));
					
					if (node.isExpand) {
						node.collapse();
					} else {
						node.expand();
					}
				}
			});
		}
	});
	$('#wParentOrg').window('open');
}

//保存上级机构ID
function saveParentOrg(){
	var orgId 	= $('#parentOrgTree').attr('orgId');
	var orgName = $('#parentOrgTree').attr('orgName');
	var orgPath = $('#parentOrgTree').attr('orgPath');
	
	if( orgId == '' || orgId == '0'){
		alert('请选择机构!');
		return;
	}
	$('#newParentId').val(orgId);
	$('#newParentName').val(orgName);
	$('#span_parentOrg').html(orgPath.substr(2));
	//关闭窗口
	closeParentOrg();
}

//关闭机构
function closeParentOrg(){
	$('#wParentOrg').window('close');
}

//变化提示窗口
function savePopChange(){

	closePopChange();
	$('#tip_sel_position').hide();
	
	//先检查部门
	var oldOrgId = $('#oldParentId').val();
	var newOrgId = $('#newParentId').val();
	var newPosIds = getNewPosIds(); 
	if(oldOrgId == newOrgId){
		saveAdjust();
	}else{
		var url3 = _ctx + '/plas/plas-approve!validatePosPosRel.action';
		var data3 = {newOrgId: newOrgId, posIds: newPosIds};
		$.post(url3, data3, function(result3){
			if('success' == result3){
				saveAdjust();
			}else{
				$.messager.alert('提示', '操作不成功!'+result3);
				$('#tip_sel_position').html(result3).show();//.fadeOut(5000);
			}
		});	
	}
}
function closePopChange(){
	$('#wConfigChange').window('close');
}

function saveAdjust(){
	$('#tip_apply').hide();
	$('body').mask('保存中...');
	var url = _ctx + '/plas/plas-approve!saveKeyFields.action';
	var data = {};
	var id = $('#plasApproveInfoId').val();
	data['id'] = id;
	$('input[keyfield=true],select[keyfield=true]').each(function(){
		data[$(this).attr("name")] = $(this).val();
	});
	$.post(url, data, function(result){
		$('body').unmask();
		var arr = result.split(',');
		if('success' == arr[0]){
			//$("#userDetailPanel").html('').hide();
			//跳转至审批列表：我的申请维度
			var url2 = _ctx + '/plas/plas-approve!result.action';
			$.post(url2,{id: arr[1]}, function(result){
				$("#userDetailPanel").html(result).show().fadeOut(1000,function(){
					turn2MyReco();//approve.js
				});
			});
		}else{
			$.messager.alert('提示','保存不成功!'+result);
			//$('#tip_apply').html('保存不成功!'+result).show();
		}
	});
}

//保存关键信息
function saveKeyFields(approveStatusCd){
	
	//若是调入人员,则必须变更机构
	var isUnderCtrlFlg = $('#isUnderCtrlFlg').val();
	if('0' == isUnderCtrlFlg){
		if($('#oldParentId').val() == $('#newParentId').val()){
			alert('请选择调入部门!');
			return;
		};
	}

	$('#approveStatusCd').val(approveStatusCd);
	if(!valdateKeyFields()){
		return;
	}
	//比较字段变化
	if(isChange()){
		$('#changePanelDiv').html(getFieldCompare());
		$('#wConfigChange').window('open');
	}else{
		//alert('关键信息无变化!');
		$.messager.alert('提示','关键信息无变化!');
		//$('#tip_operate_result').html('关键信息无变化').fadeOut(2000);//无响应
	}
}
//保存关键信息
function saveComFields(){
 
	if(!validateOtherFields()){
		return;
	}
	$('#tip_save_com').hide();
	var url = _ctx + '/plas/plas-approve!saveComFields.action';
	var data = {};
	var plasUserId = $('#plasUserId').val();
	data['id'] = plasUserId;//很重要
	$('input[comfield=true],select[comfield=true]').each(function(){
		//特殊处理easyui input 日前选择框 add by huangbijin 2011-02-08
		var tName = $(this).attr("name");
		if(tName){
			data[tName] = $(this).val();
		}else{
			var comboname = $(this).attr("comboname");
			if(comboname){
				data[comboname] = $('input[name='+comboname+']').val();
			}
		}
	});
	$.post(url, data, function(result){
		if(result == 'success'){
			$.messager.alert('提示','保存成功!');
			//$('#tip_save_com').html('保存成功!').show().fadeOut('10000');
		}else{
			$.messager.alert('提示','保存不成功!'+result);
			//$('#tip_save_com').html('保存不成功!'+result);
		}
	});
}
//private
function isChange(){
	if($('#oldParentId').val() != $('#newParentId').val()){
		return true;
	}
	if($('#oldLevelCd').val() != $('#newLevelCd').val()){
		return true;
	}
	if($('#oldPosNames').val() != getNewPosNames()){
		return true;
	}
	if($('#oldWorkDuty').val() != $('#newWorkDuty').val()){
		return true;
	}
	return false;
}

//private
function getFieldCompare(){

	var t11 = $('#oldParentName').val();
	var t12 = $('#newParentName').val();
	var t21 = $("#oldLevelName").val();
	var t22 = $("#newLevelCd option:selected").text();
	var t31 = $('#oldPosNames').val();
	var t32 = getNewPosNames();
	var t41 = $('#oldWorkDuty').val();
	var t42 = $('#newWorkDuty').val();

	var flag1= (t11 == t12);
	var flag2= (t21 == t22);
	var flag3= (t31 == t32);
	var flag4= (t41 == t42);
	
	var tDiv = new Array();
		tDiv.push("<table style='width:100%;line-height:24px;' class='content_table' cellpadding='0' cellspace='0'>");
		tDiv.push("<col width='60px'/>");
		tDiv.push("<col />");
		tDiv.push("<col />");
		tDiv.push("<thead><th style='text-align:left;'>调整内容</th><th style='text-align:left;'>调整前</th><th style='text-align:left;'>调整后</th></thead>");
		
		if(!flag1){
			tDiv.push("<tr><td valign='top'>部门</td><td valign='top'>"+t11+"</td><td valign='top' class='changeVal'>"+t12+"</td></tr>");
		}
		if(!flag2){
			tDiv.push("<tr><td valign='top'>职级</td><td valign='top'>"+t21+"</td><td valign='top' class='changeVal'>"+t22+"</td></tr>");
		}
		if(!flag3){
			tDiv.push("<tr><td valign='top'>编制落位</td><td valign='top'>"+t31+"</td><td valign='top' class='changeVal'>"+t32+"</td></tr>");
		}
		if(!flag4){
			tDiv.push("<tr><td valign='top'>职位</td><td valign='top'>"+t41+"</td><td valign='top' class='changeVal'>"+t42+"</td></tr>");
		}
		tDiv.push("</table>");
		return tDiv.join("");
}

function valdateKeyFields(){

	var userName = $.trim($('#userName').val());
	if(userName == ''){
		alert('请填写姓名!');
		$('#userName').focus();
		return false;
	}
	
	var uiid = $.trim($('#openAcct_uiid').val());
	if(uiid == ''){
		$('#openAcct_uiid_tip').html('请输入').css({'color':'red'}).show();
		initTipList();
		alert('请填写账号!');
		$('#openAcct_uiid').focus();
		return false;
	}
	
	var newParentId = $.trim($('#newParentId').val());
	if(newParentId == ''){
		alert('请选择所属机构!');
		$('#newParentId').focus();
		return false;
	}
	
	//选择职职位
	var strPosIds = getNewPosIds();
	if($.trim(strPosIds) == ''){
		alert('请选择系统职位!');
		return;
	}
	var newLevelCd = $.trim($('#newLevelCd').val());
	if( newLevelCd == ''){
		alert('请选择职级!');
		$('#newLevelCd').focus();
		return false;
	}
	
	var newWorkDuty = $.trim($('#newWorkDuty').val());
	if( newWorkDuty == ''){
		alert('请填写岗位描述!');
		$('#newWorkDuty').focus();
		return false;
	}

	var contentDesc = $.trim($('#contentDesc').val());
	if( contentDesc == ''){
		alert('请填写说明信息!');
		$('#contentDesc').focus();
		return false;
	}

	if($('#effectDate').length == 1){
		//日期格式
		$('#span_openAcct_effectDate').hide();
		var tDate = $.trim($('input[name=effectDate]').val());
		if(tDate == '' || !validateCNDate(tDate)){
			$('#span_openAcct_effectDate').fadeIn('2000');
			return false;
		}
	}
	return true;
}

//员工资料
function validateOtherFields(){
	var sexCd = $.trim($('#sexCd').val());
	if( sexCd == ''){
		alert('请选择性别!');
		$('#sexCd').focus();
		return false;
	}
	var mobilePhone = $.trim($('#mobilePhone').val());
	if( mobilePhone == ''){
		alert('请填写手机号码!');
		$('#mobilePhone').focus();
		return false;
	}

	var userTypeCd = $.trim($('#userTypeCd').val());
	if( userTypeCd == ''){
		alert('请选择用户类型!');
		$('#userTypeCd').focus();
		return false;
	}
	
	var idno = $.trim($('#idno').val());
	if( idno == ''){
		alert('请填写证件号码!');
		$('#idno').focus();
		return false;
	}	
	return true;
}

//保存用户明细
function saveAllFields(approveStatusCd, operName){
	$('#tip_sel_position').hide();
	$('#openAcct_uiid_tip').hide();
	
	if(!valdateKeyFields()){
		return;
	}

	if(!validateOtherFields()){
		return;
	}
	
	$('#approveStatusCd').val(approveStatusCd);
	var uiid = $.trim($('#openAcct_uiid').val());
	var url = _ctx +'/plas/plas-approve!isUiidExists.action';
	var tId = $.trim($('#plasApproveInfoId').val());
	
	var acctStatusCd = $('#acctStatusCd').val();
	
	var data = {uiid: uiid, plasApproveInfoId: tId, acctStatusCd: acctStatusCd};
	$.post(url,data,function(result){
		if('false' == result){
			//先检查部门
			var newOrgId = $('#newParentId').val();
			var posIds = getNewPosIds(); 
			var url3 = _ctx + '/plas/plas-approve!validatePosPosRel.action';
			var data3 = {newOrgId: newOrgId, posIds: posIds};
			$.post(url3, data3, function(result3){
				if('success' == result3){
					saveApproveForm(operName);
				}else{
					$('#tip_sel_position').html(result3).show();
				}
			});
		}else{
			$('#openAcct_uiid_tip').html('已占用. ').css({'color':'red'}).show();
			$('#openAcct_uiid').focus();
		}
	});
} 
function saveUserKey(){
	//否则,检查是否修改关键项,并提示，确认后提交
	//所属机构名称/职级/系统职位/岗位描述/说明
	if($('#newParentId').val() != $('#oldParentId').val()){
		changeFlg = true;
	}
	if($('#newParentId').val() != $('#oldParentId').val()){
		changeFlg = true;
	}
	if($('#newParentId').val() != $('#oldParentId').val()){
		changeFlg = true;
	}
} 
function saveApproveForm(operName){
	getNewPosIds();
	$('#userForm').form('submit',{
		url:_ctx + '/plas/plas-approve!save.action',
		onSubmit: function(){
			$('body').mask('正在校验用户信息..');
			
			var flagForm = $('#userForm').form('validate');
			if(!flagForm){
				$('body').unmask();
				return false;
			} 
			$('body').mask('正在提交用户信息,请稍候..');
			return true;
		},
		success:function(result){
			processResult(result, operName);
		}
	});
}

function processResult(result, operName){
	$('#tip_save_com').hide();
	var arr = result.split(',');
	$('body').unmask();
	if('success' == arr[0]){
		$("#userDetailPanel").hide();
		var url2 = _ctx + '/plas/plas-approve!result.action';
		$.post(url2,{id: arr[1]}, function(result){
			$("#userDetailPanel").html(result).show().fadeOut(3000,function(){
				$("#userDetailPanel").html('');
				loadMyApplyList();
			});
		});
 	}else{
		//alert('保存不成功! \n ' + result);
 		//$('#tip_save_com').html(operName +'不成功! ' + result).show();
		$.messager.alert('提示',operName +'不成功!'+result);
	}
}

//yyyy-MM-dd
function validateCNDate( strValue ) { 
	var objRegExp = /^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$/ ;

	if(!objRegExp.test(strValue)) 
		return false; 
	else{ 
		var arrayDate = strValue.split('-'); 
		if(arrayDate.length != 3){
			return false;
		}
		var intDay = parseInt(arrayDate[2],10); 
		var intYear = parseInt(arrayDate[0],10); 
		var intMonth = parseInt(arrayDate[1],10); 

		if(intMonth > 12 || intMonth < 1) { 
			return false; 
		} 

		var arrayLookup = { '1' : 31,'3' : 31, '4' : 30,'5' : 31,'6' : 30,'7' : 31, '8' : 31,'9' : 30,'10' : 31,'11' : 30,'12' : 31};

		//除2月
		if(arrayLookup[parseInt(arrayDate[1])] != null) { 
			if(intDay <= arrayLookup[parseInt(arrayDate[1])] && intDay != 0) 
				return true; 
			else 
				return false;
		} 
		//2月份
		if (intMonth == 2) { 
			var booLeapYear = (intYear % 4 == 0 && (intYear % 100 != 0 || intYear % 400 == 0)); 
			if( ((booLeapYear && intDay <= 29) || (!booLeapYear && intDay <=28)) && intDay !=0) 
				return true; 
			else{
				return false;
			}
		}
		return true; 
	} 
}


function userMove(acctId){
	
	var url = _ctx + '/plas/plas-approve!userMove.action';
	var data = {plasAcctId: acctId};
	$.post(url, data ,function(result){

		$("#userDetailPanel").html(result).show().siblings().hide();
		//很重要：json加载的页面，需要经过下列方法渲染页面
		$.parser.parse('#userDetailPanel');
	});
}

function processAcct(plasAcctId,funcName,desc){
	commonProcess(plasAcctId, funcName, desc);
}
//private 
function commonProcess(plasAcctId,funcName,operateName){
	var otherTip = '';
	$('#tip_operate_result').hide();
	$.messager.confirm('确认', operateName + otherTip + ',您确定继续吗?',function(r){
		if(r){
			var popFlg = ('acctResetPassword' == funcName);
			var contentDesc = '';
			if(!popFlg){
				$.messager.prompt("操作提示", "请输入操作事由:", function (data) {
		            if (data) {
		                contentDesc = data;
		                proc(popFlg, funcName, plasAcctId, contentDesc);
		            }
		        });
			}else{
                proc(popFlg, funcName, plasAcctId, operateName);
			}
		}
	});
}

function proc(popFlg, funcName, plasAcctId, contentDesc){
	$('body').mask('处理中,请稍候...');
	var params = {funcName: funcName, plasAcctId: plasAcctId, contentDesc: contentDesc};
	$.post( _ctx + '/plas/plas-approve!commonProcess.action', params, function(result){
		$('body').unmask();
		
		if(popFlg){
			if('success' == result){
				//$.messager.alert('提示','密码重置成功!<br/>');
				$('#tip_operate_result').html('密码重置成功!');
			}else{
				$.messager.alert('提示',contentDesc + '操作不成功! '+result);
			}
		}else{
			var arr = result.split(',');
			if('success' == arr[0]){
				var url2 = _ctx + '/plas/plas-approve!result.action';
				$.post(url2,{id: arr[1]}, function(result){
					$("#userDetailPanel").html(result).show().fadeOut(1000,function(){
						$("#userDetailPanel").html('');
						loadMyApplyList();
					});
				});
			}else{
				$.messager.alert('提示',contentDesc + '操作不成功! ' + result);
			}
		}
	});
}

function refrshTranIn(){
	showTranIn();
}
//选择调入人员
function showTranIn(){
	
	$('#wTranIn').window('open');
	$("#tranInTreePanel").mask('加载中...');
	//全部人员
	var url = _ctx +'/plas/plas-approve!loadOrgUserEnableTreePhysical.action';
	$.post(url, function(result) {
		$("#tranInTreePanel").html('').unmask();
		if (result) {
			gTranInTree = new TreePanel({
				renderTo: "tranInTreePanel",
				'root'  : (result),
				'ctx'	: _ctx
			}); 
			//自定义
			gTranInTree.isDelegateIcon = true;
			gTranInTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			gTranInTree.delegateOnMouseOverNode = function(node){
				return '';
			};
			
			//修饰所有节点
			for(var k in gTranInTree.nodeHash){
				var node = gTranInTree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				node.iconPath = _ctx + "/images/webim/none_online.jpg"; 
				if( nodeType == "0"){
					var sexCd = node.attributes.sexCd;
					//var userStatusCd = node.attributes.extParam.split(",")[1];
					var userStatusCd = node.attributes.entityStatusCd;
					switch(sexCd){
						case '1': switch(userStatusCd){
									case '0': node.iconPath = _ctx + "/images/webim/male_locked.gif"; break;
									case '1': node.iconPath = _ctx + "/images/webim/male_online.jpg"; break;
									case '2': node.iconPath = _ctx + "/images/webim/male_offline.jpg"; break;
									default: ;
								  }
						  		  break;
						case '2': switch(userStatusCd){
									case '0': node.iconPath = _ctx + "/images/webim/female_locked.gif"; break;
									case '1': node.iconPath = _ctx + "/images/webim/female_online.jpg"; break;
									case '2': node.iconPath = _ctx + "/images/webim/female_offline.jpg"; break;										
									default: ;
								  }
				  		  		  break; 
						default: switch(userStatusCd){
									case '0': node.iconPath = _ctx + "/images/webim/none_locked.gif"; break;
									case '1': node.iconPath = _ctx + "/images/webim/none_online.jpg"; break;
									case '2': node.iconPath = _ctx + "/images/webim/none_offline.jpg"; break;
									default: ;
								  }
				  		  		  break; 
					}
				}else if( nodeType == "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
			}
			gTranInTree.render(); 
			//单击事件
			gTranInTree.on(function(node){
				var id = node.attributes.entityId;  
				var nodeType = node.attributes.nodeType;
				if(id == '0' || id == ''){
					return;
				}
				if (nodeType == "1") {//机构
					if (node.isExpand) {
						node.collapse();
					} else {
						node.expand();
					}
				}else if (nodeType == "0") {
					closePopTranIn();
					refreshUserArea(id);
				}
			});
		}
	});
} 
function savePopTranIn(){
	
}
function closePopTranIn(){
	$('#wTranIn').window('close');
}

//选择系统职位
function showEmptyPosTree(acctId){

	var tOrgId = $('#newParentId').val(); 
	if(tOrgId == ''){
		$('#saveUserParentOrgTip').html('请选择部门!');
		return;
	}
	
	$('#wConfigPos').window('open');

	/*
	if( $('#wConfigPos').attr('acctId') ==acctId){
		//$('#posTreePanel').show();
		//alert('已存在'+$('#wConfigPos').attr('acctId'));
		//return;
	}	
	else{
		$('#wConfigPos').attr('acctId', acctId);
	}
	*/
	
	$('#wConfigPos').mask('正在查询职位...');
	$('#posTreePanel').html('');
	
	var url = _ctx + '/plas/plas-approve!loadEmptyPositionTree.action';
	var data = {acctId: acctId,orgId: $('#newParentId').val()};
	$.post(url,data,function(result){
		$('#posTreePanel').html('');
		$('#wConfigPos').unmask();
		gPosTree = new TreePanel({
			renderTo : "posTreePanel",
			'root' : (result),
			'ctx' : _ctx
		});
		//自定义
		gPosTree.isDelegateIcon = true;
		gPosTree.delegateGetDelegateIcon = function(node) {
			return node.iconPath;
		};
		gPosTree.delegateOnMouseOverNode = function(node) {
			return '';
		};
		
		//修饰所有节点
		for ( var k in gPosTree.nodeHash) {
			var node = gPosTree.nodeHash[k];
			var nodeType = node.attributes.nodeType;
			if( nodeType == '3'){//pos
				var statusCd = node.attributes.entityStatusCd;//是否有效 true-有效 false/null-无效
				if(statusCd == '1'){
					node.iconPath = _ctx+"/images/webim/male_online.jpg";
				}else{
					node.iconPath = _ctx+"/images/webim/male_offline.jpg";
				}
			}
			else if( nodeType == "1" && node.isLeaf()){
				node.iconPath = _ctx + "/images/imgTree/page.gif";
			}
		}
		gPosTree.render(); 
		//单击事件
		gPosTree.on(function(node) {
			var nodeType = node.attributes.nodeType;
			if (nodeType == "1") {//机构
				if (node.isExpand) {
					node.collapse();
				} else {
					node.expand();
				}
			} 
		});
 		//treePanel动态生成,只能在这里赋值height:400px;overflow-y:auto;+position: relative;
 		//$('#posTreePanel').find('.TreePanel').css({"height":"400px", "overflow-y": "auto", "+position":"relative"});
 		 
	});
} 
function savePopPos(){
	var addDels = gPosTree.getAddDeleteIds('3');//职位
 	var addIds = addDels[0];
 	var delIds = addDels[1];
 	if((addIds == '') && (delIds == '') ){
 		alert("未发生变化!");
 		return;
 	}
 	$("#posRangePanelDiv").html(gPosTree.getPopDiv('3','授权职位','收回职位'));
	$('#wConfigPos').window('close');
 	$("#wConfigPosConfirm").window('open');
}
function closePopPos(){
	$('#wConfigPos').window('close');
}

//确认保存
function savePopPosConfirm(){
	
	var nodes = gPosTree.getCheckedNodes(true);
	var node;
	for(var i=0; i<nodes.length; i++){
		node = nodes[i];
		appendToPosList(node.parentNode.attributes['text'],node.attributes['text'], node.attributes['id']);
	}
 	
	$('#wConfigPos').window('close');
	$('#wConfigPosConfirm').window('close');
}
function closePopPosConfirm(){
	$('#wConfigPos').window('open');
	$('#wConfigPosConfirm').window('close');
}

function getNewPosIds(){
	var arrPos = new Array();
	//若已存在,返回
	$('#position_list_ul tr td[class=posId]').each(function(){
		arrPos.push($(this).attr('posId'));
		arrPos.push(',');
	});
	var strPosIds = arrPos.join('');
	$('#newSysPosIds').val(strPosIds);
	return strPosIds;
}

function getNewPosNames(){
	var arr = new Array();
	$('#position_list_ul tbody tr td.posName').each(function(){
		arr.push($(this).html());
		arr.push(',');
	});
 	return arr.join('');
}


function getEntityId(plasUserId,plasApproveInfoId,entityTmpId){
	var entityId = '';
	if('' != plasUserId){
		entityId = plasUserId;
	}
	else if('' != plasApproveInfoId){
		entityId = plasApproveInfoId;
	}
	else if('' != entityTmpId){
		entityId = entityTmpId;
	}
	return entityId;
}

function appendToPosList(tOrgName, tPosName, tPosId){
	//若已存在,返回
	if($('#position_list_ul tr td[posId='+tPosId+'] ').length > 0){
		return;
	}
	var arr = new Array();
	arr.push('<td style="text-align: center;padding-left:5px;" class="posId" posId="' + tPosId + '"></td>');
	arr.push('<td style="text-align: center;">'+ tOrgName +'</td>');
	arr.push('<td style="text-align: center;" class="posName">'+ tPosName +'</td>');
	arr.push('<td style="text-align: center;"><input type="button" class="plbtn plred" value="删除" onclick="$(this).parent().parent().remove();refreshIndex();"/></td>');
	var str = arr.join('');
	$('#position_list_ul').append('<tr>' + str +'</tr>');
	refreshIndex();
}
function removeToPosList(tOrgName, tPosName, tPosId){
	//若已存在,返回
	$('#position_list_ul tr td[posId='+tPosId+'] ').each(function(){
		$(this).parent().remove();
	});
	refreshIndex();
}


function refreshIndex(){
	var i = 1;
	$('#position_list_ul tbody tr td:first-child').each(function(){
		$(this).html(i++);
	});
	getNewPosIds();
}


//清空关系账号
function cleanPositionRelAcct(jDom,positionId){
	var url = '${ctx}/plas/plas-sys-position!cleanPositionRelAcct.action';
	$('#tip_operatePos').hide();
	$.post(url, {positionId: positionId}, function(result){
		if('success' == result){
			$('#tip_operatePos').html('收回成功!').show().fadeOut(2000);
			jDom.parent().parent().remove();
			refreshIndex();
		}else{
			$.messager.alert('提示','清空关联账号失败! \n' + result);
		}
	});
}


function showKeyButtons(){
	 $('input[keybutton=true]').each(function(){
		 $(this).show();
	 });
}
function hideKeyButtons(){
	 $('input[keybutton=true]').each(function(){
		 $(this).hide();
	 });
}

function closeAllPosWin(){
	$('#wUploadFile').window('close');
	$('#wParentOrg').window('close');
	$('#wConfigPos').window('close');
	$('#wConfigPosConfirm').window('close');
	$('#wConfigChange').window('close');
	$('#wTranIn').window('close');
}
//查看我的申请
function loadMyApplyList(){
	
	closeAllPosWin();
	
//	var url  = _ctx + '/plas/approve!list.action';
//	var data = {qsCondition: '1'};
//	$.post(url, data, function(result){
//		$("#userDetailPanel").hide();
//		$('#approvePanel').html(result).show();
//	});
	
	$('#rightPanel'). mask('正在加载,请稍后...');
	var url  = _ctx + '/plas/approve!panel.action';
	var data = {searchTypeCd: '1'};
	$.post(url, data, function(result){
		$('#rightPanel').unmask();
		$('#approvePanel').html(result).show().siblings().hide();
	});
}
//审批列表
function loadApproveList(){
	
	closeAllPosWin();
	
	
//	var url  = _ctx + '/plas/approve!list.action';
//	var data = {};
//	$.post(url, data, function(result){
//		$("#userDetailPanel").hide();
//		$('#approvePanel').html(result).show();
//	});

	$('#rightPanel'). mask('正在加载,请稍候...');
	var url  = _ctx + '/plas/approve!panel.action';
	var data = {searchTypeCd: '2'};
	$.post(url, data, function(result){
		$('#rightPanel').unmask();
		$('#approvePanel').html(result).show().siblings().hide();
	});
}

var focusTreeMgr;
function focusTree(jdom, panelId){
	if(focusTreeMgr)clearTimeout(focusTreeMgr);
	focusTreeMgr = setTimeout(function(){
		processFocuTree(jdom, panelId);
	}, 300);
}

//搜索定位表单
var curVal = null;
var curNode = null;
function processFocuTree(jdom, panelId){
	jdom.next().hide();
	var tmpTree;
	if(panelId == 'leftScrollPanel' ){
		if('user' == $('#leftPanel').attr("reltype")){
			tmpTree = gLeftTree;
		}else{
			tmpTree = gLeftPosTree;
		}
	}
	else if(panelId == 'tranInTreePanel' ){
		tmpTree = gTranInTree;
	}
	else{
		return;
	}
	
	if(typeof(tmpTree) == "undefined"){
		return;
	}
	
	jdom.css({color:"#5A5A5A"});
	if($.trim(jdom.val()) == curVal){
		//NONE
	}else{
		curVal = $.trim(jdom.val());
		curNode = null;
	}
	curNode = tmpTree.searchNode(curVal, curNode);
	if(curNode){
		var nodes = curNode.getPathNodes();
		for(var i= 0; i < nodes.length; i++){
			try{
				nodes[i].expand();
			}catch(e){}
		}
		tmpTree.setFocusNode(curNode);
		var nodeDom = curNode['html-element']['text'];
		var toh = $('#'+ panelId +'').height();
		var top = $('#'+ panelId +'')[0].scrollTop;
		var noh = $(nodeDom).offset().top;
		$('#'+ panelId).animate({ scrollTop : top+noh-toh }, "normal");
		if(curVal != ''){
			jdom.next().show();
		}
	}else{
		//$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
	}
}

function gotoHis(userName){
	var url = _ctx + '/plas/approve!panel.action';
	var data = {searchTypeCd: 3, searchMul: userName, condApproveStatusCd: '1,2,3,4'};
	$('#rightPanel').mask('正在查询...');
	$.post(url, data, function(result){
		$('#rightPanel').unmask();
		$('#approvePanel').html(result).show().siblings().hide();
	});
}

function processCondOver(dom){
	var val = $(dom).val();
	if('搜索账号或姓名...' == $.trim(val)){
		$(dom).val('');
	}
	$(dom).removeClass('auto_search_init').addClass('auto_search');
}
function processCondOut(dom){
	var val = $(dom).val();
	if('' == $.trim(val)){
		$(dom).val('搜索账号或姓名...');
		$(dom).removeClass('auto_search').addClass('auto_search_init');
	}else{
		$(dom).removeClass('auto_search_init');
	}
}
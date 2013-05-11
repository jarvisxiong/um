<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>用户管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	
	<%--注意：jquery1.4以上，不适用eval构造json,直接(result)即可 --%>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/xheditor/xheditor-src-zh-cn.js"></script>
	<script type="text/javascript" src="${ctx}/js/biz/app/app-attachment.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.select.js"></script>
</head>
<body class="easyui-layout">
	<div region="west" title='<a href="#" id="btnChangeTree">切换至逻辑机构</a>
			<span><input type=checkbox id="load_pos_flag"  onchange="reloadUserTree()"/>含职位</span>
			<span><input type=checkbox id="load_auto_flag" checked="checked" onchange="reloadUserTree()"/>刷新</span>
		' split="true"  style="width:240px;">
		<div id="leftPanel" style="height:100%;">
			<div id="leftTree" ></div>
		</div>
	</div>
	<div region="center" split="true" style="padding:5px;+position: relative;overflow:auto;height:100%;">
		<!-- 类型:1-物理机构 -->
		<input id="gbCurType" type="hidden" value="1"/>
		<div style="padding:5px 0;">
			快捷搜索职员: 
			<input style="width:150px;" type="text" id="quickSearchField" name="quickSearchField" value="${quickSearchField}" title="支持账号或姓名搜索人员列表"/>
			<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
			<input type="button" onclick="reloadSearch()" value="搜索"></input>
			<%-- hidden by huangbijin 2011-11-25 chenjj1要求
			<input type="button" onclick="cleanSearchField()" value="清空"></input>
			 --%>
			&nbsp;&nbsp;&nbsp;
			<a href="#" id="addBtn"  iconCls="icon-add"  class="easyui-linkbutton" onclick="addUser();">新增</a>
			
 			<security:authorize ifAnyGranted="A_ADMIN">
			<a href="#" id="btnBatchAdjust"  iconCls="icon-edit"  class="easyui-linkbutton" onclick="batchAdjust();">调动</a>
			</security:authorize>
		</div>
		
		<!-- 记忆当前选中的机构 -->
		<input type="hidden" id="nodeOrgId"/>
		 
		<!-- 明细 -->
		<div id="userDetailPanel" >
			
		</div>
	</div>
<div id="wUploadFile" title="上传图片" class="easyui-window" minimizable="false" maximizable="false" closed="true" style="width:600px;height:500px;padding:5px;background: #fafafa;">
</div>
<div id="wOpenAcct"   title="开通账号" class="easyui-window" minimizable="false" maximizable="false" closed="true" style="width:600px;height:250px;padding:5px;background: #fafafa;">
</div>
<div id="wParentOrg"  title="请选择上级机构" class="easyui-window" minimizable="false" maximizable="false" closed="true" style="width:300px;height:500px;padding:5px;background: #fafafa;">
	<div style="width: 100%;text-align: left;">
		<input type="button" style="cursor:pointer" class="buttom" onclick="savePopOrg()" value="保存" />
		<input type="button" style="cursor:pointer" class="buttom" onclick="closePopOrg()" value="取消" />
		<input type="hidden" id="wParentOrg_orgId"/>
		<input type="hidden" id="wParentOrg_orgCd"/>
		<input type="hidden" id="wParentOrg_orgBizCd"/>
		<input type="hidden" id="wParentOrg_orgName"/>
	</div>
	<div id="orgTreePanel" style="margin-top:10px;">
		<!-- 这里加载物理机构视图 -->
	</div>
</div>

<div id="wDestOrg" title="请选择目标机构" class="easyui-window" minimizable="false" maximizable="false" closed="true" style="width:300px;height:500px;padding:5px;background: #fafafa;">
	<div style="width: 100%;text-align: left;">
		<input type="button" style="cursor:pointer" class="buttom" onclick="confirmPopDestOrg()" value="确定" />
		<input type="button" style="cursor:pointer" class="buttom" onclick="closePopDestOrg()" value="取消" />
		<input type="hidden" id="wDestOrg_orgId"/>
		<input type="hidden" id="wDestOrg_orgCd"/>
		<input type="hidden" id="wDestOrg_orgBizCd"/>
		<input type="hidden" id="wDestOrg_orgName"/>
	</div>
	<div id="destOrgPanel" style="margin-top:10px;">
		<!-- 这里加载物理机构视图 -->
	</div>
</div>

<script type="text/javascript">

	function setCurSelOrg(nodeOrgId){
		$('#nodeOrgId').val(nodeOrgId);
	}
	function getCurSelOrg(nodeOrgId){
		return $('#nodeOrgId').val();
	}

	var fckeditor;
	//邮件签名
	function showEditor(){
		fckeditor = $("#detailShow").xheditor(true,{
			emots:{'qq':{'name':'QQ','count':55,'width':25,'height':25,'line':11}}, 
			skin:'o2007blue'
			/*,
			upLinkUrl:_ctx+"/com/upload!upload.action?bizTmpId=${bizTmpId}&fileTypeCd=1&bizTypeCd=11",upLinkExt:"xlsx,docx,doc,xls,pdf,zip,rar,txt",
			upImgUrl :_ctx+"/com/upload!upload.action?bizTmpId=${bizTmpId}&fileTypeCd=2&bizTypeCd=11",upImgExt:"jpg,jpeg,gif,png"
			*/
		});
	}

	function getSignContentForSubmit(){
		if(fckeditor){
			$("#detailShow").val(fckeditor.getSource());	
		}
	}

	//显示树：1 显示物理机构 2 显示逻辑机构
	var CONST_PHYSICAL = '1';
	var CONST_LOGICAL  = '2';

	$(function() {
		
		$('#btnChangeTree').toggle(
			function (){
				$(this).html('切换至逻辑机构');
				loadUserTree(CONST_LOGICAL);
			},
			function (){
				$(this).html('切换至逻辑机构');
				loadUserTree(CONST_PHYSICAL);
			}
		); 

		$('#addBtn').linkbutton('disable');
		$('#saveBtn').linkbutton('disable');
		
		loadUserTree(CONST_PHYSICAL);
		//快速搜索职员(模糊匹配:uiid,userName)
		$('#quickSearchField').quickSearch(
			'${ctx}/plas/plas-user!quickSearchUserList.action',
			['uiid','userName','orgName','centerOrgName','serviceStatusCd'],
			{plasUserId:'quickSearchFieldId',userName:'quickSearchField'},
			'',
			function(result){
				refreshUserArea(result.attr('plasUserId'));
			},
			{'dimeCd':'gbCurType'}
		);
	});

	var gbCurType = '';
	function getGbCurType(){
		return gbCurType;
	}
	function setGbCurType(curType){
		$('#gbCurType').val(curType);
		gbCurType = curType;
	}

	function reloadUserTree(){
		loadUserTree(getGbCurType());
	}

	function loadUserTree(type) {
		
		//若不勾选,不刷新
		if(!$('#load_auto_flag').attr('checked')){
			return;
		}

		$('#leftTree').html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入机构职员树,请稍候...');
		var url = '';
		switch(type){
			case CONST_PHYSICAL: url = '${ctx}/plas/plas-user!loadOrgUserTreePhysical.action';break;
			case CONST_LOGICAL : url = '${ctx}/plas/plas-user!loadOrgUserTreeLogical.action';break;
			default: return;
		}
		setGbCurType(type);
		
		var isPosFlg = $('#load_pos_flag').attr('checked');
		$.post(url, {isPosFlg: isPosFlg}, function(result) {
			$("#leftTree").html('').unmask();
			if (result) {
				var mytree = new TreePanel({
					renderTo: "leftTree",
					'root'  : (result),
					'ctx'	:'${ctx}'
				}); 
				//自定义
				mytree.isDelegateIcon = true;
				mytree.delegateGetDelegateIcon = function(node){
					return node.iconPath;
				};
				mytree.delegateOnMouseOverNode = function(node){
					return '';
				};
				
				//修饰所有节点
				for(var k in mytree.nodeHash){
					var node = mytree.nodeHash[k];
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
				mytree.render(); 
				//单击事件
				mytree.on(function(node){
					var id  = node.attributes.entityId;
					var parentId = node.parentNode.id;
					if( $.trim(id) == '' || $.trim(id)=='0'){
						return;
					}
					
					var nodeType = node.attributes.nodeType;
					//机构
					if( nodeType == "1"){
						$('#addBtn').linkbutton('enable');
						$('#saveBtn').linkbutton('disable');
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
						//机构id
						setCurSelOrg(node.id);
					}
					if( nodeType == "0"){//用户
						$('#addBtn').linkbutton('disable');
						$('#saveBtn').linkbutton('enable');
						refreshUserArea(id,parentId);
					}
				});
			}
		});
	}
	 
	/**
	 * 刷新用户信息区域
	 * @param id  用户ID
	 * @param parentId 机构ID
	 *
	 */
	function refreshUserArea(id,parentId){

		$('#userDetailPanel').html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入职员信息,请稍候...');
		var url = "${ctx}/plas/plas-user!input.action";
		$.post(url,{id: id, plasOrgId: parentId}, function(result) {
			$("#userDetailPanel").html(result);

			try{
				//在parser之前
				$('#birthday').datebox({
					formatter: function(date){ 
						return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
					},   
					parser: function(date){ 
						return new Date(Date.parse(date.replace(/-/g,"/"))); 
					}
				});  

				
				/*
				$('#attendWorkDate').datebox({
					formatter: function(date){ 
						return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(); 
					},   
					parser: function(date){ 
						return new Date(Date.parse(date.replace(/-/g,"/"))); 
					}
				});  
				*/
			}catch(e){
				
			}
			//很重要：json加载的页面，需要经过下列方法渲染页面
			$.parser.parse('#userDetailPanel');
		});
	}
		
	//新增用户
	function addUser(){
		var plasOrgId = getCurSelOrg();
		if(plasOrgId){
			refreshUserArea('', plasOrgId);
		}else{
			$.messager.alert('提示','请选择所在机构');
		}
	}
	
	//批量调整用户
	function batchAdjust(){
		$('#userDetailPanel').html('<div style="height:60px;">&nbsp;</div>').mask('正在载入人员树,请稍候...');
		var url = '${ctx}/plas/plas-user!batchAdjust.action';
		$.post(url, function(result) {
			$('#userDetailPanel').empty().unmask();
			$('#userDetailPanel').html(result);
			loadSrcUserTree();
		});
	}

	/*****************************************************************************************************/
	//复用部分 plas-user-my.jsp
	/*****************************************************************************************************/

	//选择上级机构
	function showPopOrg(){
		$('#orgTreePanel').html('<div style="height:60px;">&nbsp;</div>').mask('正在载入机构关系树,请稍候...');
		var url = '${ctx}/plas/plas-org!loadOrgTreePhysical.action';
		$.post(url, function(result) {
			$('#orgTreePanel').empty().unmask();
			if (result) {
				var mytree = new TreePanel({
					renderTo : "orgTreePanel",
					'root' : (result),
					'ctx' : '${ctx}'
				});
				//自定义
				mytree.isDelegateIcon = true;
				mytree.delegateGetDelegateIcon = function(node) {
					return node.iconPath;
				};
				mytree.delegateOnMouseOverNode = function(node) {
					return '';
				};
				mytree.render();
				//修饰所有节点
				for ( var k in mytree.nodeHash) {
					var node = mytree.nodeHash[k];
					var nodeType = node.attributes.nodeType;
					if( nodeType == "1" && node.isLeaf()){
						node.iconPath = _ctx + "/images/imgTree/page.gif";
					}
				}
				//单击事件
				mytree.on(function(node) {
					var nodeId = node.id;
					var nodeType = node.attributes.nodeType;
					if(nodeType == '1') {//机构
						$('#wParentOrg_orgId').val(nodeId);
						$('#wParentOrg_orgCd').val(node.attributes.entityCd);
						$('#wParentOrg_orgBizCd').val(node.attributes.entityBizCd);
						$('#wParentOrg_orgName').val(node.attributes.entityName);
						
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
	function savePopOrg(){
		var userId  = $('#plasUserId').val();
		var orgId 	= $('#wParentOrg_orgId').val();
		var orgCd 	= $('#wParentOrg_orgCd').val();
		var orgBizCd= $('#wParentOrg_orgBizCd').val();
		var orgName = $('#wParentOrg_orgName').val();
		
		if(orgId == '' || orgId == '0'){
			alert('请选择机构!');
			return;
		}
	
		var url = '${ctx}/plas/plas-user!saveParentPhysicalOrgId.action';
		$('#saveUserParentOrgTip').hide();
		$.post(url, {orgId: orgId, userId: userId}, function(result) {
			if('success' == result){
				var title = '机构业务编号:['+orgBizCd+'],机构内码:['+ orgCd+']';
				$('#span_parentOrg').attr("title",title);
				$('#span_parentOrg').html(orgName);
				$('#saveUserParentOrgTip').html('保存上级成功!').show();
				//重新载入
				reloadUserTree();
				//关闭窗口
				closePopOrg();
			}else{
				$.messager.alert('异常',result);
			}
		});
	}
	
	//关闭机构
	function closePopOrg(){
		$('#wParentOrg').window('close');
	}

	
	//重新搜索账号
	function reloadSearch(){
		var userId = $.trim($('#quickSearchFieldId').val());
		if(userId){
			refreshUserArea(userId,'')
		}
		
	}
	//清空输入框
	function cleanSearchField(){
		$('#quickSearchField').val('');
		$('#quickSearchFieldId').val('');
	}
	
	var gSecUserTree;
	//载入可多选的人员树
	function loadSrcUserTree(){
		$('#srcUserTree').html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入机构职员树,请稍候...');
		
		var url = '';
		switch(getGbCurType()){
			case CONST_PHYSICAL: url = '${ctx}/plas/plas-user!loadOrgUserTreePhysicalM.action';break;
			case CONST_LOGICAL : url = '${ctx}/plas/plas-user!loadOrgUserTreeLogicalM.action';break;
			default: return;
		} 
		$.post(url, function(result) {
			$("#srcUserTree").html('').unmask();
			if (result) {
				gSecUserTree = new TreePanel({
					renderTo: "srcUserTree",
					'root'  : (result),
					'ctx'	:'${ctx}'
				}); 
				//自定义
				gSecUserTree.isDelegateIcon = true;
				gSecUserTree.delegateGetDelegateIcon = function(node){
					return node.iconPath;
				};
				gSecUserTree.delegateOnMouseOverNode = function(node){
					return '';
				};
				
				//修饰所有节点
				for(var k in gSecUserTree.nodeHash){
					var node = gSecUserTree.nodeHash[k];
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
				gSecUserTree.render(); 
				//单击事件
				gSecUserTree.on(function(node){
					var id  = node.attributes.entityId;
					var parentId = node.parentNode.id;
					if( $.trim(id) == '' || $.trim(id)=='0'){
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
					else if( nodeType == "0"){ 
						refreshSrcUserDiv();
					}
				});
				$('#destOrgBtnsPanel').show();
			}
		});
	}
	
	function refreshSrcUserDiv(){
		$('#srcUserDiv').html(gSecUserTree.getPopDiv('0','选择职员',''));
		var modifyNodes = gSecUserTree.getModifiedLeafNodes('0');
		var addIds = modifyNodes[0];
		if( addIds.length == 0 || $('#wDestOrg_orgId').val() == ''){
			$('#btnSavePopDestOrg').attr("disabled","disabled");
		}else{
			$('#btnSavePopDestOrg').attr("disabled","");
		}
	}
	
	//选择目标机构
	var gDestOrgTree;
	function loadDestOrgTree(){
		$('#destOrgPanel').html('<div style="height:60px;">&nbsp;</div>').mask('正在载入机构关系树,请稍候...');
		var url = '${ctx}/plas/plas-org!loadOrgTreePhysical.action';
		$.post(url, function(result) {
			$('#destOrgPanel').empty().unmask();
			if (result) {
				gDestOrgTree = new TreePanel({
					renderTo : "destOrgPanel",
					'root' : (result),
					'ctx' : '${ctx}'
				});
				//自定义
				gDestOrgTree.isDelegateIcon = true;
				gDestOrgTree.delegateGetDelegateIcon = function(node) {
					return node.iconPath;
				};
				gDestOrgTree.delegateOnMouseOverNode = function(node) {
					return '';
				};
				gDestOrgTree.render();
				//修饰所有节点
				var node;
				var nodeType;
				for ( var k in gDestOrgTree.nodeHash) {
					node = gDestOrgTree.nodeHash[k];
					nodeType = node.attributes.nodeType;
					if( nodeType == "1" && node.isLeaf()){
						node.iconPath = _ctx + "/images/imgTree/page.gif";
					}
				}
				//单击事件
				gDestOrgTree.on(function(node) {
					var nodeId = node.id;
					var nodeType = node.attributes.nodeType;
					var path = node.getPath();
					if(nodeType == '1') {//机构
						$('#wDestOrg_orgId').val(nodeId);
						$('#wDestOrg_orgCd').val(node.attributes.entityCd);
						$('#wDestOrg_orgBizCd').val(node.attributes.entityBizCd);
						$('#wDestOrg_orgName').val(node.attributes.entityName);

						if (node.isExpand) {
							node.collapse();
						} else {
							node.expand();
						}
						if(orgId == '' || orgId == '0'){
							return;
						} 
					} 
				});
			}
		});
		$('#wDestOrg').window('open');
	}
	
	//确定
	function confirmPopDestOrg(){
		var orgId = $('#wDestOrg_orgId').val(); 
		var orgName = $('#wDestOrg_orgName').val(); 
		if( orgId == '' || orgId == '0'){
			alert('请选择机构!');
			return;
		} 
		$('#span_destOrg').html(orgName);
		
		//关闭窗口
		closePopDestOrg();
		
		refreshSrcUserDiv();
	}
	//关闭机构
	function closePopDestOrg(){
		$('#wDestOrg').window('close');
	}
	//保存调动职员成功
	function savePopDestOrg(){
		var orgId = $('#wDestOrg_orgId').val();
		$('#saveDestOrgTip').hide();
		var url = '${ctx}/plas/plas-user!saveBatchAdjust.action';
		var modifyNodes = gSecUserTree.getModifiedLeafNodes('0');
		var addIds   = modifyNodes[0];

		if(addIds.length == 0){
			alert('请选择人员!');
			return;
		}
		
		var addIdList = new Array();
		$.each( addIds, function(i, n){
			addIdList.push(n);
			addIdList.push(',');
		});
		
		$.post(url, {orgId: orgId, addIds: addIdList.join('')}, function(result) {
			if('success' == result){
				$('#saveDestOrgTip').html('调动职员成功!').show().fadeIn(2000);
				loadSrcUserTree();
				refreshSrcUserDiv();
			}else{
				$.messager.alert('异常',result);
			}
		});
	}
	
	//立即更改密码
	function saveChgPwdStrategy(){
		
		var pwdStrategyCd = $('#pwdStrategyCd').val();
		/*
		if( '' == $.trim(pwdStrategyCd)){
			alert('选择策略!');
			return;
		}
		*/

		$('#saveDestStraTip').hide();
		var url = '${ctx}/plas/plas-user!saveChgPwdStrategy.action';
		var modifyNodes = gSecUserTree.getModifiedLeafNodes('0');
		var addIds   = modifyNodes[0];

		if(addIds.length == 0){
			alert('请选择人员!');
			return;
		}
		var addIdList = new Array();
		$.each( addIds, function(i, n){
			addIdList.push(n);
			addIdList.push(',');
		});

		var url = '${ctx}/plas/plas-user!saveChgPwdStrategy.action';
		$.post(url, {pwdStrategyCd: pwdStrategyCd, addIds: addIdList.join('')}, function(result) {
			if('success' == result){
				$('#saveDestStraTip').html('设置策略成功!').show().fadeIn(2000);
				loadSrcUserTree();
				refreshSrcUserDiv();
			}else{
				$.messager.alert('异常',result);
			}
		});
	}
	
	//yyyy-MM-dd
	function validateCNDate( strValue ) { 
		var objRegExp = /^\d{4}(\-|\/|\.)\d{2}\1\d{2}$/ 

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
	
			var arrayLookup = { '1' : 31,'3' : 31, '4' : 30,'5' : 31,'6' : 30,'7' : 31, 
			'8' : 31,'9' : 30,'10' : 31,'11' : 30,'12' : 31} 
	
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

</script>
</body>
</html>
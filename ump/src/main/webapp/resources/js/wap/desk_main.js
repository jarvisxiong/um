var mapTree = {}; // 加载通讯录
var ID_SEARCH_RESULT = 'searchResult';// 搜索面板ID
var ID_WAB_TREE = 'wap_orgList';	//机构树ID
var DEFAULT_SEARCH_TIP = '搜索联系人...';
var userSearchMgr;
function expandNode(orgId) {
	var wabTree = mapTree[orgId];
	if (!wabTree) {
		loadWabTree(orgId);
	}
}

function loadWabTree(orgId) {
	var wabTreeId = "div_tree_" + orgId;
	$("#" + wabTreeId).html('<div style="padding-left:8px;"><image src="' + _ctx + '/images/loading.gif"></div>');
	$.post(_ctx + "/desk/desk-wab!getWabTreeByOrgId.action", {
		'orgId' : orgId
	}, function(result) {
		$("#" + wabTreeId).html('');
		if (result) {
			var wabTree = new TreePanel( {
				renderTo : wabTreeId,
				'root' : eval('(' + result + ')'),
				'ctx' : _ctx
			});
			wabTree.icon = {
				root : 'empty.gif',// root.gif
				folder : 'triangle_right.gif',// folder.gif
				folderOpen : 'triangle_down.gif',// folderopen.gif
				node : 'male_online.gif',
				empty : 'empty.gif',
				line : 'empty.gif',// line.gif
				join : 'empty.gif',// join.gif
				joinBottom : 'empty.gif',// joinbottom.gif
				plus : 'empty.gif',// plus.gif
				plusBottom : 'empty.gif',// plusbottom.gif
				minus : 'empty.gif',// minus.gif
				minusBottom : 'empty.gif',// minusbottom.gif
				nlPlus : 'empty.gif',// nolines_plus.gif
				nlMinus : 'empty.gif',// nolines_minus.gif
				checkbox0 : 'checkbox_0.gif',
				checkbox1 : 'checkbox_1.gif',
				checkbox2 : 'checkbox_2.gif'
			// org : 'org.gif',
			// edp : 'edp.gif',
			// emp : 'emp.gif'
			};
			// refreshNodeIcon(true, []);
			wabTree.render();
			wabTree.on(function(node) {
				var nodeType = node.attributes.nodeType;
				// 机构
					if (nodeType == "1") {
						var iconPathRight = _ctx + "/resources/images/treePanel/triangle_right.gif";
						var iconPathDown = _ctx + "/resources/images/treePanel/triangle_down.gif";
						if (node.iconPath == iconPathRight) {
							node.iconPath = iconPathDown;
						} else {
							node.iconPath = iconPathRight;
						}
						if (node.isExpand) {
							node.collapse();
						} else {
							node.expand();
						}
					} else if (nodeType == "0") {
						// VisitingCardUtil.sendEmail(node.attributes.extParam);
					}
				});
			// 屏蔽第一个节点
			$(".TreePanel").css( {
				"margin-left" : "-18px",
				"padding-top" : "5px",
				"padding-bottom" : "5px"
			});// .addClass('TreePanelInit');
			var span = $(".TreePanel").find("span:first");
			span.hide();
			span.next().hide();
			span.next().next().hide();
			span.next().next().next().hide();
			span.next().next().next().next().hide();
			// 调整高度
			// resetWabTreeHeight(wabTreeId);

			// 注册定时刷新在线人员图标
			// timerOnlineImg();
			// 注册定时刷新在线人数
			// timerOnlineCount();
			$("#" + wabTreeId).show();
			if (wabTree != null) {
				mapTree[orgId] = true;
			}
		}
	});
}

function searchUserResult(srcElem) {
	var wabTreeElemId = ID_WAB_TREE;
	var resultElemId = ID_SEARCH_RESULT;
	if (userSearchMgr)
		clearTimeout(userSearchMgr);
	userSearchMgr = setTimeout(function() {
		var val = $.trim($(srcElem).val());
		if (val == "") {
			$("#" + wabTreeElemId).show();
			$("#" + resultElemId).hide();
			return;
		} else {
			$("#" + wabTreeElemId).hide();
			$("#" + resultElemId).show();
			$(srcElem).css( {
				color : "#5A5A5A"
			});
			$("#" + resultElemId).addClass("waiting");
			$.post(_ctx + "/desk/desk-wab!searchUserList.action", {
				value : val
			}, function(result) {
				$("#" + resultElemId).html(result).removeClass("waiting").height(320).show();// 300:计算排除的高度
				$(".module:odd").addClass("module_odd");
				$(".module_body").each(function(i){
					$(this).children(":first").children().slice(1, 2).each(function(i){
						var tel=$(this).html();
						var length=tel.length;
						tel=tel.substr(3,length);
						tel=$.trim(tel);
						var tels=tel.split("&nbsp;");
						$(this).html('手机:');
						for(var i=0;i< tels.length;i++){
							var telTmp=$.trim(tels[i]);
							if (telTmp!='' && telTmp!='&nbsp;'){
								$(this).append('<a href="tel:'+telTmp+'">'+telTmp+'</a>');
							}
						}
					});
				});
			});
		}
	}, 300);
}
function clearSearchInput(srcElem) {

	var wabTreeElemId = ID_WAB_TREE;
	var resultElemId = ID_SEARCH_RESULT;

	var val = $.trim($(srcElem).val());
	if (val == DEFAULT_SEARCH_TIP) {
		$(srcElem).val("");
		$(srcElem).css( {
			color : "#5A5A5A"
		});

		$("#" + wabTreeElemId).show();
		$("#" + resultElemId).hide();
	}
};
function resetSearchInput(srcElem){

	var wabTreeElemId = ID_WAB_TREE;
	var resultElemId = ID_SEARCH_RESULT;
	
	var val = $.trim($(srcElem).val());
	if( val == '' || val == DEFAULT_SEARCH_TIP){
		$(srcElem).val(DEFAULT_SEARCH_TIP);
		$(srcElem).css({color:"#909090"});
		$("#"+wabTreeElemId).show();
		$("#"+resultElemId).hide();
	}
}
function logout(){
	logoff();
	window.location.href=_ctx+'/login!logout.action';
}
function logoff(){
	// 注销PD
	$.post(_ctx+"/login!reduceUser.action", function(result) {
		// $("#divXialaMenu").html(result);;
	});
}
function gotoWeb(){
	window.location.href=_ctx;
}
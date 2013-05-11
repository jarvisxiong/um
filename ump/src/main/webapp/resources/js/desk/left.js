var IF_ADDRESS_DOWN = false;	//通讯录是否下拉显示的判断参数
var ID_DIRECT_WAB = 'btn_direct_wab';//方向标
var ID_WAB_TREE = 'wabTree';	//机构树ID
var ID_SEARCH_RESULT = 'searchResult';//搜索面板ID
var wabTree; //加载通讯录
function addressClick(direction){
	if(IF_ADDRESS_DOWN || "up"==direction){
		$("#div_address").animate({"height":"164px"},300);
		$("#btn_address_down").show();
		$("#btn_address_up").hide();
		IF_ADDRESS_DOWN = false;
	}else if(!IF_ADDRESS_DOWN || "down"==direction){
		$("#div_address").animate({"height":"500px"},300);
		$("#btn_address_down").hide();
		$("#btn_address_up").show();
		IF_ADDRESS_DOWN = true;
	}
}
var NOW_GGWX_MODULE = "notify";	//当前是在公告还是在新闻
//公告新闻的切换动作
function clickGgxwChange(to_module){
	doGgxwChange(to_module);
	window.clearInterval(ggxw_interval);
	ggxw_interval = setInterval(autoChangeGgxw, 10*1000);
}
function doGgxwChange(to_module){
	if(NOW_GGWX_MODULE!=to_module){
		var other_module = "";
		if("notify"==to_module){
			other_module = "news";
		}else{
			other_module = "notify";
		}
		$("#desk_front_"+to_module+"_div").show();
		$("#desk_front_"+other_module+"_div").hide();
		$("#div_btn_"+to_module).removeClass("ggxw_title_inactive").addClass("ggxw_title_active");
		$("#div_btn_"+other_module).removeClass("ggxw_title_active").addClass("ggxw_title_inactive");
		NOW_GGWX_MODULE=to_module;
	}
}
//打开公告新闻的更多的按钮
function openGgxwMore(){
	if("notify"==NOW_GGWX_MODULE){
		TabUtils.newTab('177', '公告', _ctx+'/desk/desk-notify.action', true);
	}else{
		TabUtils.newTab('142', '新闻', _ctx+'/desk/desk-news.action', true);
	}
}

/**
* 功能:搜索,搜索用户列表
* @param val: 模糊匹配 登录名,姓名,公司电话和手机号码
* @return 更新搜索结果
*/

var DEFAULT_SEARCH_TIP = '搜索联系人...';
//按键时若存在默认字符,清空
function clearSearchInput(srcElem){
	
	var wabTreeElemId = ID_WAB_TREE;
	var resultElemId = ID_SEARCH_RESULT;
	
	var val = $.trim($(srcElem).val());
	if( val == DEFAULT_SEARCH_TIP){
		$(srcElem).val("");
		$(srcElem).css({color:"#5A5A5A"});
		
		$("#"+wabTreeElemId).show();
		$("#"+resultElemId).hide();
	}
};
function resetSearchInput(srcElem){

	var wabTreeElemId = ID_WAB_TREE;
	var resultElemId = ID_SEARCH_RESULT;
	
	var val = $.trim($(srcElem).val());
	if( val == '' || val == DEFAULT_SEARCH_TIP){
		$(srcElem).val(DEFAULT_SEARCH_TIP);
		$(srcElem).css({color:"#E6E6E6"});
		$("#"+wabTreeElemId).show();
		$("#"+resultElemId).hide();
	}
}
var userSearchMgr;
function searchUserResult(srcElem){
	
	var wabTreeElemId = ID_WAB_TREE;
	var resultElemId = ID_SEARCH_RESULT;
	
	if(userSearchMgr)clearTimeout(userSearchMgr);
	userSearchMgr = setTimeout(function(){
		var val = $.trim($(srcElem).val());
		if( val == ""){
			$("#"+wabTreeElemId).show();
			$("#"+resultElemId).hide();
			return;
		}else{
			$("#"+wabTreeElemId).hide();
			$("#"+resultElemId).show();
			$(srcElem).css({color:"#5A5A5A"});
			$("#"+resultElemId).addClass("waiting");
			$.post(_ctx+"/desk/desk-wab!searchUserList.action",{value:val},function(result){
				$("#"+resultElemId).html(result).removeClass("waiting").height($(document).height()-300).show();//300:计算排除的高度
			});
		}
	}, 300);
}
function loadWabTree(){
	var wabTreeId = ID_WAB_TREE;
	$("#"+wabTreeId).html('<div style="padding-left:8px;"><image src="'+_ctx+'/images/loading.gif"></div>');
	//机构人员树
	//$.post(_ctx+"/desk/desk-wab!getWabTree.action", function(result) {
	//机构职位树
	$.post(_ctx+"/desk/desk-wab!getWabTreePos.action", function(result) {
		$("#"+wabTreeId).html('');
		if (result) {
			wabTree = new TreePanel({
				renderTo : wabTreeId,
				'root'   : eval('('+result+')'),
				'ctx'    : _ctx
			});
			wabTree.icon = {
				root		: 'empty.gif',//root.gif
				folder		: 'triangle_right.gif',//folder.gif
				folderOpen	: 'triangle_down.gif',//folderopen.gif
				node		: 'page.gif',
				empty		: 'empty.gif',
				line		: 'empty.gif',//line.gif
				join		: 'empty.gif',//join.gif
				joinBottom	: 'empty.gif',//joinbottom.gif
				plus		: 'empty.gif',//plus.gif
				plusBottom	: 'empty.gif',//plusbottom.gif
				minus		: 'empty.gif',//minus.gif
				minusBottom	: 'empty.gif',//minusbottom.gif
				nlPlus		: 'empty.gif',//nolines_plus.gif
				nlMinus		: 'empty.gif',//nolines_minus.gif
				checkbox0	: 'checkbox_0.gif',
				checkbox1	: 'checkbox_1.gif',
				checkbox2	: 'checkbox_2.gif',
				org			: 'org.gif',
				edp			: 'edp.gif',
				emp			: 'emp.gif' 
			};
			//自定义
			wabTree.isDelegateIcon = true;
			wabTree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			wabTree.delegateOnMouseOverNode = function(dom, node){
				var nodeType = node.attributes.nodeType;
				if( nodeType == "1"){
					VisitingCardUtil.hide();
				}else if(nodeType == "0"){
					var tLeft  = $(dom).find("span:last").offset().left;
					var tWidth = $(dom).find("span:last").width();
					var tLeftPad = tLeft + tWidth;
					var tUiid = node.attributes.extParam;
					var tNodeId = node.attributes.id;
					openCardManager(tLeftPad, "1", dom, tUiid, tNodeId);
					$(dom).mouseleave(function(){
						closeCardManager();
						VisitingCardUtil.hide();
					});
				}
			}; 
			refreshNodeIcon(true,[]);
			wabTree.render();
			wabTree.on(function(node){
				var nodeType = node.attributes.nodeType;
				//机构
				if( nodeType == "1"){
					var iconPathRight = _ctx + "/resources/images/treePanel/triangle_right.gif";
					var iconPathDown  = _ctx + "/resources/images/treePanel/triangle_down.gif";
					if( node.iconPath== iconPathRight){
						node.iconPath = iconPathDown;
					}else{
						node.iconPath = iconPathRight;
					}
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					} 
				}
				else if(nodeType == "0"){
					VisitingCardUtil.sendEmail(node.attributes.extParam);
				}
			});
			//屏蔽第一个节点
			$(".TreePanel").css({"margin-left":"-18px","padding-top":"5px","padding-bottom":"5px"});//.addClass('TreePanelInit');
			var span = $(".TreePanel").find("span:first");
			span.hide();
			span.next().hide();
			span.next().next().hide();
			span.next().next().next().hide();
			span.next().next().next().next().hide();
			//调整高度
			resetWabTreeHeight(wabTreeId);
			

			//展开特定节点(地产控股)
			var exp1 = wabTree.getNodeById('402834ea37700b2c0137842951097e0a');
			exp1.expand();
			//注册定时刷新在线人员图标
			//timerOnlineImg();
			//注册定时刷新在线人数
			//timerOnlineCount();
		}
	});
}
// 查看搜索用户的名片
function showSearchUserCard(dom, uiid, entityId) {
	var tLeft = $(dom).offset().left;
	var tWidth = $(dom).width();
	var tLeftPad = tLeft + tWidth;
	openCardManager(tLeftPad, "2", dom, uiid, entityId);
}
//此方法供desk_wab_user.jsp的单击会话用户使用
function chatWithSomebody(uiid,userCd,userName){
	VisitingCardUtil.sendEmail(uiid);
} 
//鼠标移到人员节点时显示"名片",停留0.3 second显示名片
var cardTimeMgr;
function openCardManager(leftPad, picType, dom, uiid, nodeId) {
	cardTimeMgr = setTimeout(function() {
		VisitingCardUtil.show(leftPad, picType, dom, uiid, nodeId, '1');
	}, 300);
}
function closeCardManager(){
	if(cardTimeMgr){
		clearTimeout(cardTimeMgr);
	}
}

//刷新在线人员图标
function refreshOnlineImg(){
	$.post(_ctx + "/desk/desk-wab!getCurrentOnlineUsers.action", function(result) {
		if(result){
			refreshNodeIcon(false,result.split(","));
		}
	});
}
//定时更新在线人图标(默认10分钟)
var userOnlineImgMgr;
function timerOnlineImg(){
	userOnlineImgMgr= setInterval(function(){
		refreshOnlineImg();
	}, 600000);
}
//刷新在线人数
function refreshOnlineCount(){
	$.post(_ctx + "/desk/desk-wab!getCurrentOnlineCount.action", function(result) {
		if(result){
			if( result.indexOf("success") == 0){
				var arr = result.split(",");
				if(arr.length == 2){
					$("#count_online").html(arr[1]);
				}
			}
		}
	});
}
//定时更新在线人数(默认10分钟)
var userCountMgr;
function timerOnlineCount(){
	userCountMgr= setInterval(function(){
		refreshOnlineCount();
	}, 600000);
}

//重新调整树的高度
function resetWabTreeHeight(){

	var wabTreeId = ID_WAB_TREE;
	
	$("#"+wabTreeId).show();
	//搜索面板ID:searchResult
	$("#"+ID_SEARCH_RESULT).hide();
	
	var docHeight = $(document).height();
	var maxHeight = docHeight-280;//280:除TreePanel外,其他部分的高度总和
	var wabTreePanelHeight = $(".TreePanel").height();
	var wabHeight = $("#"+wabTreeId).height();
	var minHeight = 110;//默认高度110
	 
	//alert("docHeight =" + docHeight +", maxHeight=" + maxHeight + ",minHeight="+minHeight+",wabHeight="+wabHeight);
	
	//最大值(展开)
	if( wabTreePanelHeight>= maxHeight){
		$("#"+wabTreeId).height(maxHeight);
		$(this).removeClass().addClass("address_up");
	}
	//最小值(初始)
	else if(wabTreePanelHeight <= minHeight){
		$("#"+wabTreeId).height(minHeight);
		$(this).removeClass().addClass("address_down");
	}
	//自适应(展开)
	else{
		$("#"+wabTreeId).height(wabTreePanelHeight+10);
		$(this).removeClass().addClass("address_up");
	}
}
//初始化方向标
function initDirectWab(){
	var btnId = ID_DIRECT_WAB;
	var wabTreeId = ID_WAB_TREE;
	$("#"+btnId).toggle(
		function () {
			$(this).removeClass().addClass("address_down");
			$("#"+wabTreeId).height(110);
		},
		function () {
			$(this).removeClass().addClass("address_up");
			resetWabTreeHeight();
		}
	);
}
//调整通知栏
function initBroad(broadId){
	var docHeight = $(document).height();
	$("#"+broadId).css({"top":(docHeight-24-33-15)});
}

//刷新节点图标
function refreshNodeIcon(firstFlg,userCds){
	for(var k in wabTree.nodeHash){
		var node = wabTree.nodeHash[k];
		var nodeType = node.attributes.nodeType;
		var entityCd = node.attributes.entityCd;
		
		
		//人员
		var path ='';
		if( nodeType== "0"){
			if(firstFlg){
				onlineFlg = ("1" == node.attributes.entityStatusCd)?true:false;
			}else{
				onlineFlg = contain(userCds, entityCd);
			}
			switch(node.attributes.sexCd){
				case '1': if(onlineFlg){
							path = _ctx+"/resources/images/desk/wab/male_online.gif";
						  }else{
						    path = _ctx+"/resources/images/desk/wab/male_offline.gif"; 
						  }
						  break;
				case '2': if(onlineFlg){
						    path = _ctx+"/resources/images/desk/wab/female_online.gif"; 
						  }else{
							path = _ctx+"/resources/images/desk/wab/female_offline.gif"; 
						  }
						  break;
				default : if(onlineFlg){
						    path = _ctx+"/resources/images/desk/wab/none_online.gif";
						  }else{
							path =_ctx+"/resources/images/desk/wab/none_offline.gif"; 
						  }
			}
			$("#usertreenode_"+entityCd).attr("src",path);
			node.iconPath = path;
		}
		//机构
		else if( nodeType == "1" && node.isLeaf()){
			node.iconPath = _ctx + "/resources/images/treePanel/triangle_right.gif";
		}
	}
}
//功能: 判断userCd是否存在userCds里
function contain(userCds, userCd){
	for(var i=0; i<userCds.length; i++){
		if(userCd == userCds[i]){
			return true;
		}
	}
	return false;
}


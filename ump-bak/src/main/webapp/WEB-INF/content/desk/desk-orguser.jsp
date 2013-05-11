<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%-- 提供comet使用 --%>

<%@page import="java.util.HashSet"%><script type="text/javascript" src="${ctx}/js/cometd/cometd.js"></script>
<script type="text/javascript" src="${ctx}/js/cometd/jquery.json-2.2.js"></script>
<script type="text/javascript" src="${ctx}/js/cometd/jquery.cometd.js"></script>
<script type="text/javascript" src="${ctx}/js/cometd/ReloadExtension.js"></script>
<script type="text/javascript" src="${ctx}/js/cometd/jquery.cometd-reload.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/WebimClientEngineer.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/WebimUIEngineer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/xheditor/xheditor-zh-cn.min.js"></script>

<%-- 加载机构树 --%>
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>

<div>
	<div id="left_address_talk_panel" class="left_address_talk_panel" >
		<%-- 通讯录/对话 --%>
		<div class="left_top_tabs">
			<div id="address_nav" title="查看通讯录" class="active">
				&nbsp;<span style="font-weight:normal;">通讯录</span>
			</div>
			<div style="float:left; background-color: transparent;"></div>
			<div id="talk_nav" title="查看会话列表" class="inactive">
				&nbsp;<span style="font-weight:normal;">对话</span>
			</div>
		</div>
		<div style="height:4px; position:relative;">
			<div id="imTabSub" style="position:absolute; top:0px; left:0px;">
	     	  <div style="background-color:#4984ae; height:4px; width:101px;"></div>
	        </div>
        </div>
		<div id="search_nav" style="display:none;"></div>
		<div class="search_area">
			<input class="search_input" value="搜索用户..." id="searched_user" type="text" onkeydown="clearSearchInput()" onkeyup ="searchUser();" onclick ="searchUser();" onmouseout="resetSearchInput();" onmouseover="clearSearchInput();"></input>
		</div>
		<div class="common_area">
			<div class="common_area_head"><img src="${ctx}/images/webim/tongxunlu_bai_xia.gif" align="absmiddle" style="margin-left:5px;"/> 宝龙集团发展有限公司</div>
			<%-- 通讯录 --%>
			<div id="left_address_panel_id" class="address_org_user">
				<%-- 下面区域: 构造 动态创建机构人员树 --%>
				<div id="org_tree_content" class="org_tree_content"> </div>
			</div>
			<%-- 对话(正在对话/最近对话) --%>
			<div id="left_talk_options_id" style="display: none;">
				<div class="left_talk_options">
					<ul>
						<li title="正在对话" id="talk_talking_nav" class="active">正在对话</li>
						<li title="最近联系" id="talk_latest_nav">最近联系</li>
					</ul>
				</div>
				<div style="display:inline;" id="communication_panel">
					<%-- 正在对话 --%>
					<div id="talk_talking_div" style="display:inline;text-align: left;">
						<ul class="talk_member_ul_cls">
							<%-- 参见 webim.css ： Li的id是userCd
							<li id="userno_8" alt="A" title="A" class="selected"><span class="newmsg_online"></span><span>A</span><span class="x_close" style="display: none;"></span></li>
							<li id="userno_9" alt="B" title="B" class=""        ><span class="newmsg_online"></span><span>b</span><span class="x_close" style="display: none;"></span></li>
							 --%>
						</ul>
					</div>
					<%-- 最近对话 --%>
					<div id="talk_latest_div" style="display:none;text-align: left;">
						<ul class="talk_member_ul_cls">
							<%-- 参见 webim.css
							<li id="userno_133" class="selected"><span class="online"></span><span>C</span><span class="x_close"></span></li>
							<li id="userno_134" class=""        ><span class="online"></span><span>D</span><span class="x_close"></span></li>
							 --%>
						</ul>
					</div>
				</div>
			</div>
			<%-- 搜索 --%>
			<div id="left_user_result" class="search_user_result" ></div>
		</div>
	</div>
	<div style="height:5px; width:100%;"></div>

	<%-- 在线统计 --%>	
	<div style="text-align: left;">
		<div id="chatting_min_container" class="chatting_min" >
		</div>
		<div id="statistic_panel" style="padding: 1px 0 2px 5px;height:23px;line-height: 23px;margin-right: 30px;">
			在线人数/总人数:
			<span style="line-height: 14px;height: 14px;" id="count_online">${onlineCount}</span>
			/<span style="line-height: 14px;height: 14px;" id="count_all">加载中...</span>
		</div>
	</div>
</div>

<!-- 
<div id="chatting_panel" class="chatting_panel" >
	<div style="margin:3px;">
		<div align="left" style="height:25px;line-height:25px;color:#000;font-size:14px;font-weight:bold;padding-left:10px;;background-color: #EEEEEE;">
			<div class="chatting_window_close" title="关闭" ></div>
			<div class="chatting_window_min" align="right" title="最小化" ></div>
			<div id="chatting_titlebar" class="titlebar">&nbsp;</div>
		</div>
		<div class="chatting_content" id="chatting_content" align="left">聊天记录区域</div>
		<div align="left">
			<textarea id="chatRichEditor" style="height:85px;width:495px;"></textarea>
			<div class="chatting_operate">
					<div class="chatting_clean" style="float:left;" title="清除当前窗口聊天记录ALT+T">清屏(T)</div>
					<div class="chatting_send"  title="快捷键：ENTER或者ALT+S">发送(S)</div>
					<div class="chatting_close"  title="快捷键：ALT+C">关闭(C)</div>
					<div class="chatting_history"  title="查看会话记录">历史记录</div>
					<input id="userMessageUrl" style="display:none" value="${ctx}/webim/user-message.action"/> 
				</div>
		</div>
	</div>
</div>
 -->
<script language="javascript"> 

<%--步骤
	1.加载所有人员
	2.加载机构数
	3.更新所有人员中的在线人员和最近联系人清单
	1.展开机构节点,刷新人员在线状态
	2.选择谈话对象,更新对话框/正在对话/最近联系人清单
--%>
<%--加载机构树--%>
	var tree;

<%--会话--%>
	var strCometUrl = '${webimPreUrl}${webimContext}/cometd';
	var strUiid 	= '${uiid}';
	var strUserCd 	= '${currentUserCd}';

<%--实例->注册控件/事件->在线人数->创建连接->--%>
	var webimUI = new WebimUIEngineer();
	    webimUI.registerOperate();

	$("#org_tree_content").html('<div><image src="${ctx}/images/loading.gif"></div>');
	$.post("${ctx}/desk/desk!getOrgUserTree.action", function(result) {
		$("#org_tree_content").html('');
		if (result) {
			tree = new TreePanel({
				renderTo:"org_tree_content",
				'root' : eval('('+result+')'),
				'ctx':'${ctx}'
			});
			tree.icon = {
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
			tree.isDelegateIcon = true;
			tree.delegateGetDelegateIcon = function(node){
				return node.iconPath;
			};
			tree.delegateOnMouseOverNode = function(elem, node){
				if(node.attributes.orgOrUser == "1"){
					VisitingCardUtil.hide();
				}else if(node.attributes.orgOrUser == "0"){
					var strUiid = node.attributes.extParam;
					var strUserCd = node.attributes.id.substr("usertreenode_".length);
					var elemLeft  = $(elem).find("span:last").offset().left;
					var elemWidth = $(elem).find("span:last").width();
					var leftPad = elemLeft + elemWidth;
					openCardManager(leftPad,"1",elem,strUiid,strUserCd);
					$(elem).mouseleave(function(){
						closeCardManager();
						VisitingCardUtil.hide();
					});
				}
			}; 
			tree.render();
			var iCountAll = 0;
			//修饰所有节点
			for(var k in tree.nodeHash){
				var node = tree.nodeHash[k];
				var orgOrUser = node.attributes.orgOrUser;
				//人员
				if( orgOrUser== "0"){
					var tmpOnline = node.attributes.online;
					switch(node.attributes.sexCd){
						case '1':
							 if(tmpOnline == '1'){
								node.iconPath = _ctx+"/images/webim/male_online.jpg";
							 }else{
								node.iconPath = _ctx+"/images/webim/male_offline.jpg";
							 }
						 	 break;
						case '2':
							 if(tmpOnline == '1'){
								node.iconPath = _ctx+"/images/webim/female_online.jpg";
							 }else{
								node.iconPath = _ctx+"/images/webim/female_offline.jpg";
							 }
							 break;
						default:
							 if(tmpOnline == '1'){
								node.iconPath = _ctx+"/images/webim/none_online.jpg";
							 }else{
								node.iconPath = _ctx+"/images/webim/none_offline.jpg";
							 }
							 break;
					}
					iCountAll++;
				}
				//机构
				else if( orgOrUser == "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/triangle_right.gif";
				}
			}
			webimUI.setCountAll(iCountAll);
			tree.on(function(node){
				var orgOrUser = node.attributes.orgOrUser;
				//机构
				if( orgOrUser == "1"){
					var iconPathRight = _ctx + "/images/imgTree/triangle_right.gif";
					var iconPathDown  = _ctx + "/images/imgTree/triangle_down.gif";
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
				else if(orgOrUser == "0"){
					var strId 	 	 = node.attributes.id;
					var strUserCd 	 = strId.substr("usertreenode_".length);
					var strUiid	 	 = node.attributes.extParam;
					var strUserName  = node.attributes.text;
					var strSexCd  	 = node.attributes.sexCd;
					//webimUI.setTalkToUser(strUserCd,strUserName,strSexCd,strUiid);
					VisitingCardUtil.sendEmail(strUiid);
				}
			});
			//屏蔽第一个节点
			var span = $(".TreePanel").find("span:first")
			span.hide();
			span.next().hide();
			span.next().next().hide();
			span.next().next().next().hide();
			span.next().next().next().next().hide();
			//创建连接
			webimUI.init(strCometUrl,strUiid,strUserCd,tree);
			webimUI.login();
		}
	});

	//查看搜索用户的名片
	function showSearchUserCard(dom,uiid,userCd){
		var elemLeft  = $(dom).offset().left;
		var elemWidth = $(dom).width();
		var leftPad   = elemLeft + elemWidth;
		openCardManager(leftPad,"2",dom,uiid,userCd);
	}
	
	//此方法供desk_user.jsp的单击会话用户使用
	function chatWithSomebody(uiid,userCd,userName){
		webimUI.setTalkToUser(userCd,userName);
	}
	function closeChattingWindow(){
		webimUI.closeChat();
	}

	//退出webim
	function logoutWebim(){
		webimUI.logout();
	}

	refreshCount();

	//每隔10分钟,更新在线人数
	var userOnlineCountMgr = setInterval(function(){
		refreshCount();
	}, 600000);
	function refreshCount(){
		$.post("${ctx}/desk/desk!getCurrentOnlineCount.action", function(result) {
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
		
	//注册:鼠标移到人员节点时显示"名片"
	var vistingCardTimeMgr;
	function openCardManager(leftPad,picType,docDom,strUiid,strUserCd){ 
		var strIsOnline = webimUI.getOnlineStatus(strUserCd);
		vistingCardTimeMgr = setTimeout(function(){
			VisitingCardUtil.show(leftPad,picType,docDom,strUiid,strUserCd,strIsOnline);
		}, 300);//鼠标停留0.3 second显示名片
	}
	function closeCardManager(){
		if(vistingCardTimeMgr){
			clearTimeout(vistingCardTimeMgr);
		}
	}
	
</script>

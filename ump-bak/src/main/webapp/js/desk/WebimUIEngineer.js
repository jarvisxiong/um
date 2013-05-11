
/**
 * 1.注册与HTML对象(id或class) JQuery操作对象仅与只对应
 * 2.定义常量或配置(如最近联系人数)
 * 3.调用webimClientEngineer.js发送消息
 * 4.调用webimClientEngineer.js接收信息,后生成html
 * 5.采用swing的监听机制实现联动更新(Observer设计模式)
 * 用户类型: 男/女on
在线状态: 共2种[在线/不在线 ]

用户图标: 共8种[男/女(在线/不在线/来信)]

1.	进入页面，初始化机构人员树,默认男女都上线
2.	调用搜索在线用户清单,更新机构人员树（设置男女员工在线或不在线）
3.	调用搜索最近联系人,初始化最近联系人清单,
	根据机构树人员在线状态,更新最近联系人清单的男女员工在线状态
*/
//发送消息
function WebimUIEngineer() {
	//注册常量
	this.config = {
		// 定义左边
		clsLeftContainer		: "#container_left",
		// 定义窗口
		clsChatDialog 			: "#chatting_panel",
		// 定义标题
		clsChatDialogTitleBar	: "#chatting_titlebar" ,
		// 离线提示
		clsChatOffLineNotify	: "#chatting_panel .offline_notify",
		// 聊天记录
		clsChatDialogContent 	: "#chatting_panel .chatting_content",//iD:chatting_content
		// 滚动最后
		clsChatDialogEnd	 	: "#chatting_panel .chatting_end",
		// 定义文本框
		clsChatDialogTextarea	: "#chatting_panel .chatting_textarea",
		// 发送
		clsChatDialogSend		: "#chatting_panel .chatting_send",
		// 清空
		clsChatDialogClean 		: "#chatting_panel .chatting_clean",
		// 查看历史
		clsChatDialogHistory	: "#chatting_panel .chatting_history",
		// 正在聊天人员清单
		clsTalkingContact		: "#talk_talking_div .talk_member_ul_cls",
		// 最近联系人员清单
		clsRecentContacts		: "#talk_latest_div  .talk_member_ul_cls",
		//聊天窗口关闭按钮
		clsChattingWindowClose  : "#chatting_panel .chatting_window_close",
		//聊天窗口最小化后区域
		clsChattingMin  : "#chatting_min_container",
		// 关闭按钮样式
		clsListItemFloatClose	: ".x_close",
		// 针对“离线提示面板”,定义显示到自动隐藏的时间间隔(默认3000ms)
		dataTimesNotifyOffLine  : 3000,
		// 校验字符串长度 (例如:480个字符(或240个汉字))
		dataMsgMinLength 		: 1,
		dataMsgMaxLength 		: 480,
		// 是否回显(0-否 1-是)
		dataEchoBackFlgSelf		: '1',
		dataEchoBackFlgOther	: '0',
		// 用户状态 
		dataUserOffLine			: '0',
		dataUserOnLine			: '1',
		dataUserNewMsg			: '2',
		dataUserNewMsgDone		: '3',
		// 图标状态 0-离线 1-上线 2-离线闪烁 3-上线闪烁
		clsRenIconOffLine		: 'offline',
		clsRenIconOnLine		: 'online',
		clsRenIconOffLineFlash	: 'newmsg_offline',
		clsRenIconOnLineFlash	: 'newmsg_online',
		// 聊天对象 1-人员 2-群组
		dataObjectTypeUser		: '1',
		dataObjectTypeRoom		: '2',
		//1-男/2-女
		dataSexNone				: '0',
		dataSexMale				: '1', 
		dataSexFemale			: '2',
		// 显示来信图标
		clsMessageIconTipNew	: "#messageIconTipNew",
		// 友情提示
		tip_message_content		: "友情提示:选择聊天对象后可以在这里进行对话...",
		tip_message_content_here: "这里进行对话...",
		// 针对"离线提示面板",定义显示到自动隐藏的时间间隔(3000ms)
		OFF_LINE_NOFITY_HIDDEN_TIME : 3000,
		//针对标题新消息走马灯
		TIP_TITLE_CONTENT_SPEED : 300,
		tip_title_content		: '欢迎使用宝龙管理平台',
		tip_title_content_newmsg: '您有新消息',
		//在线统计面板
		idStatisticPanel		: 'statistic_panel',
		idCountOnline			: 'count_online',
		idCountAll				: 'count_all'
	};
	//男女各个状态的图标(注:树形机构的图标采用路径控制,对话窗口/正在对话/最近联系人采用样式控制)
	this.icon ={
		noneNewmsgOfflinePath	: _ctx+"/images/webim/none_newmsg_offline.gif",
		noneNewmsgOnlinePath 	: _ctx+"/images/webim/none_newmsg_online.gif",
		noneOfflinePath	 	 	: _ctx+"/images/webim/none_offline.jpg",
		noneOnlinePath		 	: _ctx+"/images/webim/none_online.jpg", 
		maleNewmsgOfflinePath	: _ctx+"/images/webim/male_newmsg_offline.gif",
		maleNewmsgOnlinePath 	: _ctx+"/images/webim/male_newmsg_online.gif",
		maleOfflinePath	 	 	: _ctx+"/images/webim/male_offline.jpg",
		maleOnlinePath		 	: _ctx+"/images/webim/male_online.jpg", 
		femaleNewmsgOfflinePath	: _ctx+"/images/webim/female_newmsg_offline.gif",
		femaleNewmsgOnlinePath	: _ctx+"/images/webim/female_newmsg_online.gif",
		femaleOfflinePath		: _ctx+"/images/webim/female_offline.jpg",
		femaleOnlinePath		: _ctx+"/images/webim/female_online.jpg"
	};
	this.iconClass ={
		noneNewmsgOffline		: "none_newmsg_offline",
		noneNewmsgOnline 		: "none_newmsg_online",
		noneOffline	 	 		: "none_offline",
		noneOnline	 			: "none_online", 
		maleNewmsgOffline		: "male_newmsg_offline",
		maleNewmsgOnline 		: "male_newmsg_online",
		maleOffline	 	 		: "male_offline",
		maleOnline		 		: "male_online", 
		femaleNewmsgOffline		: "female_newmsg_offline",
		femaleNewmsgOnline		: "female_newmsg_online",
		femaleOffline			: "female_offline",
		femaleOnline			: "female_online"
	};
};
WebimUIEngineer.prototype.init = function(strCometUrl,strUiid,strClientUserCd,tree){
	//变量
	this.strCometUrl = strCometUrl;
	this.strUiid = strUiid;
	this.strClientUserCd = strClientUserCd;
	this.tree = tree;
	
	var oThis = this;
	//缓存聊天记录,刷新立即清空 {strUserCd:strMsgHTML},
	this.messageCache = {};
	//当前聊天对象  {objectCd:'',objectName:'',objectType:'',extParam:''}
	this.talkToObject = null;
	//是否需要提示输入 true-是 false-否
	this.bNeedTipText = true; 
	//默认未加载最近联系人
	this.bRecentContacts = false;

	//***************************************************************/
	//定义webimClient实例
	this.webimClient = new WebimClientEngineer(this.strCometUrl,this.strUiid,this);

	//定义回调方法,在initConnect()方法前
	this.webimClient.delegateEchoOnlineStatus = function(data){
		//alert("delegateEchoOnlineStatus : \n " + data);
		var strUserCd = data['userCd'];
        var strOnline = data['online']+'';//因为服务端返回online是int类型,这里强制转成字符串
        var ui = this.observer;
        if( strUserCd == ui.strClientUserCd && strOnline == '0'){
        	//alert("我下线了!");
        	return;
        }else{
        	//alert("strUserCd :" + strUserCd + "\n  strClientUserCd" + ui.strClientUserCd );
        }
        //ui.notifyOnline(strOnline);
        ui.updateIcon(strUserCd,strOnline);//刷新全部相关图标志
        
        var talkObject = ui.getTalkToObject();
        if(!talkObject){
        	return;
        }else{
	        var talkUserCd = talkObject.userCd;
	        if((talkUserCd == strUserCd) && (strOnline == ui.config.dataUserOffLine)){
	        	// 先显示再定时隐藏
	        	//$(ui.config.clsChatOffLineNotify).show();
	        	//setTimeout($(ui.config.clsChatOffLineNotify).hide(),ui.config.OFF_LINE_NOFITY_HIDDEN_TIME);
	        }
        }
	};
	//接收登录用户CD
	this.webimClient.delegateLoginUserCd = function(data){
		//alert("delegateLoginUserCd : \n " + data);
		var strLoginUserCd = data.userCd;
		var ui = this.observer;
		ui.updateIcon(strLoginUserCd, ui.config.dataUserOnLine);
	};
	//接收到在线人员列表
	this.webimClient.delegateEchoOnlineUserList = function(data){
		/*
		alert("delegateEchoOnlineUserList : \n " + data);
		var ui = this.observer;
		var len = 0;
		if( data){
			len = data.length;
		}
		ui.setCountOnline(len);
		ui.updateIcons(data);
		*/
	};

	//最近联系人 -> requestOnlineUserCds
	this.webimClient.delegateEchoRecentContacts = function(data){
		//alert("delegateEchoRecentContacts : \n " + data);
		var ui = this.observer;
		if( ui.bRecentContacts){
			//alert("再次加载最近联系人,抛弃!");
			return;
		}
		ui.bRecentContacts = true;
		
		var responseHtml = data;
		$(ui.config.clsRecentContacts).append(responseHtml);
			
		//替换人员图标
		$(ui.config.clsRecentContacts).find("li").each(function() {
			var userId = $(this).attr("id");
			if(!userId || $.trim(userId) == ''){
				//alert(" userid is null!");
				return;
			}
			var strUserCd = userId.substr("userno_".length);//服务端返回userno_109等 userno_app_admin
			var jsonNode  = ui.findTreeUserNodeJson(strUserCd);
			if(!jsonNode) return;//TODO:
			var imgPath   = jsonNode.src;
			var imgClass  = ui.getNewIconClass(imgPath);
			$(this).find("span:first").removeClass().addClass(imgClass);
			
			//增加事件(同AddListItem)
			$(this).click(function(){
				ui.setTalkToUserCd(strUserCd);
			});
			$(this).mouseover(function(){
				$(this).siblings().removeClass();
				$(this).addClass("selected");
			});
			$(this).mouseout(function(){
				$(this).removeClass();
			});
		}); 
		
		//很重要:初始化在线人员状态(只触发1次)
		//this.requestOnlineUserCds();
	};

	//显示聊天消息
	this.webimClient.delegateEchoPrivateChat = function(data){
		//alert("delegateEchoPrivateChat : \n " + data);
		//alert(" call delegateEchoPrivateChat!");
		var ui = this.observer;
		var strObjectType   = ui.config.dataObjectTypeUser;
		var strFromUserCd   = data['senderNo']; 
		var strToUserCd 	= data['receiverNo'];
		var strEchoBackFlag = data['echoback']+'';//强制转成string
		var strMsgHTML 		= data['msg'];
		var strObjectCd  	= (ui.config.dataEchoBackFlgSelf == strEchoBackFlag)?strToUserCd:strFromUserCd;
		
		//设置已处理(注意：不用updateIcon()方法,以免造成重复刷新)
		ui.updateIconDataTree(strObjectCd,ui.config.dataUserNewMsgDone);
		//保存聊天消息
		ui.saveToMsgCache(strObjectCd, strMsgHTML);
		//设置对话对象
		var node  = ui.findTreeUserNodeJson(strObjectCd);
		if(!node) return;//TODO
		var strUserCd   = node.userCd;
		var strUserName = node.userName;
		var strSexCd    = node.sexCd;
		ui.setTalkToUser(strUserCd,strUserName,strSexCd);

		//加入最近联系清单(要在setTalkToUser()方法前执行)
		ui.addListItem(ui.config.clsRecentContacts, ui.talkToObject, false, true);
	};
	/*
	this.webimClient.delegateEchoRoomChat = function(data){
		//alert("delegateEchoRoomChat : \n " + data);
		//alert(" call delegateEchoRoomChat!");
	};
	*/
	this.webimClient.delegateEchoNewPriateMsg = function(data){
		//alert("delegateEchoNewPriateMsg : \n " + data);
		//alert(" call delegateEchoNewPriateMsg!");

		var ui = this.observer;
		var bTalkingNow= false;
		var talkObject = ui.getTalkToObject();

		//提示
		if(data.length >0){
			$(ui.config.clsMessageIconTipNew).show();
			ui.openDocTitleManager();
		}
		var newData = new Array();
		for(var i=0;i<data.length;i++){
			var tSenderUserCd = data[i].senderNo;
			var tSenderUserName = data[i].senderName;
			var tSenderMsgType = data[i].msgtype;//TODO:服务端返回,暂不用

			//更新数状态
			ui.updateIconDataTree(tSenderUserCd,ui.config.dataUserNewMsg);
			
			var senderObject = {objectCd:tSenderUserCd,objectName:tSenderUserName,objectType:ui.config.dataObjectTypeUser};
			
			//更新正在对话列表
			ui.addListItem(ui.config.clsTalkingContact, senderObject, true, true);
			//更新最近联系清单
			ui.addListItem(ui.config.clsRecentContacts, senderObject, false, false);
			//刷新各个区域的图标
			ui.updateIconDialogList(tSenderUserCd);

			if( talkObject){
				var talkObjectCd = talkObject.objectCd;
				if( talkObjectCd == tSenderUserCd){
					bTalkingNow = true;
				} 
			}
		}
		//若当前用户来信消息,则获取并显示消息
		if( bTalkingNow && talkObject){
			this.asyncGetNewMsg(talkObject.objectCd);
		}
	};
	/*
	this.webimClient.delegateEchoNewRoomMsg = function(data){
		//alert("delegateEchoNewRoomMsg : \n " + data);
		//alert(" call delegateEchoNewRoomMsg!");
	};
	this.webimClient.delegateEchoRoomChange = function(data){
		//alert("delegateEchoRoomChange : \n " + data);
		//alert(" call delegateEchoRoomChange!");
	};
	this.webimClient.delegateEchoRoomMembers = function(data){
		//alert("delegateEchoRoomMembers : \n " + data);
		//alert(" call delegateEchoRoomMembers!");
	};
	*/
	this.webimClient.delegateEchoUserOnline = function(data){
		//alert("delegateEchoUserOnline : \n " + data);
		var strUserNo = data.userNo;
		var strOnline = data.online+'';

		//alert(" call delegateEchoUserOnLine!");
		var ui = this.observer;
		ui.updateIcon(strUserNo,strOnline);
	};
};
WebimUIEngineer.prototype.login = function(){
	this.webimClient.initConnect();
	this.webimClient.login();
};
//*********************************************************************/
//注册事件(发送/清空/历史)
//*********************************************************************/
WebimUIEngineer.prototype.registerOperate = function(){
	this.registerSend();
	this.registerClean();
	this.delegateRegisterHistory();
	this.registerTextArea();
};
WebimUIEngineer.prototype.registerSend = function(){
	var oThis = this;
	$(oThis.config.clsChatDialogSend).click(function() {
		//oThis.sendMessage(oThis.getTalkToObject(), $(oThis.config.clsChatDialogTextarea).val());
		oThis.sendMessage(oThis.getTalkToObject(), chatRichEditor.getSource());
	});
};
WebimUIEngineer.prototype.registerClean = function(){
	var oThis = this;
	$(oThis.config.clsChatDialogClean).click(function() {
		var strObjectCd = oThis.getTalkToObject().objectCd;
		oThis.messageCache[strObjectCd] = null;
		$(oThis.config.clsChatDialogContent).empty();
	});
};
WebimUIEngineer.prototype.delegateRegisterHistory = function(){
	var oThis = this;
	$(oThis.config.clsChatDialogHistory).click(function() {
		var talkObject = oThis.getTalkToObject();
		var objectCd = '';
		if( talkObject && talkObject.objectCd){
			objectCd = talkObject.objectCd;
		}
		var url = _ctx +"/webim/user-message.action?chattorCd="+objectCd;
		//alert(url);
		TabUtils.newTab("user_message_history","聊天记录",url,true);
	});
};
WebimUIEngineer.prototype.registerTextArea = function(){
	var oThis = this;
	//默认提示
	$(oThis.config.clsChatDialogTextarea).val(oThis.config.tip_message_content);
	//监听键盘,快捷键ENTER/ALT+S(IE)
	$(oThis.config.clsChatDialogTextarea).keyup(function(event) {
		//alert(event.keyCode);
        if((!event.ctrlKey && event.keyCode == 13 ) ||(event.altKey && event.keyCode==83)) {
        	oThis.sendMessage(oThis.getTalkToObject(), $(oThis.config.clsChatDialogTextarea).val());
        	event.returnValue = false;
        }
        if( event.ctrlKey && event.keyCode == 13){
        	$(this).val($(this).val()+"\n ");//需要空格,否则光标仍然在行后,未能换行
        	//自适应滚动到底部
        	var msgTextArea = document.getElementById("chatting_textarea");
        	msgTextArea.scrollTop = msgTextArea.scrollHeight;
        }
    }); 
	//鼠标移入
	$(oThis.config.clsChatDialogTextarea).mouseover(function(event) {
		var strMsgText = $(oThis.config.clsChatDialogTextarea).val();
        if(($.trim(strMsgText) == oThis.config.tip_message_content) || ($.trim(strMsgText) == oThis.config.tip_message_content_here)){
        	$(this).val(" ");//默认空
        }
        //聚焦
        //$(oThis.config.clsChatDialogTextarea).focus();
    });
	//鼠标移出
	$(oThis.config.clsChatDialogTextarea).mouseout(function(event) {
		var strMsgText = $(oThis.config.clsChatDialogTextarea).val();
		if( oThis.bNeedTipText){
	        if( $.trim(strMsgText) == ""){
	        	if(!oThis.getTalkToObject()){
	        		$(this).val(oThis.config.tip_message_content);
	        	}else{
	        		$(this).val(oThis.config.tip_message_content_here);
	        	}
	        	//移除焦点,否则会有bug,可以发送"提示信息"
	    		//$(this).blur();
	        }
		}
     });
	//防止在提示后追加字符
	$(oThis.config.clsChatDialogTextarea).keydown(function(event) {
		var strMsgText = $(oThis.config.clsChatDialogTextarea).val();
		if( oThis.bNeedTipText){
	        if(($.trim(strMsgText) == oThis.config.tip_message_content) || ($.trim(strMsgText) == oThis.config.tip_message_content_here)){
	        	$(this).val("");
	        }
		}
     });
};
//*********************************************************************/
//当前聊天对象 UI部分
//*********************************************************************/
WebimUIEngineer.prototype.getTalkToObject = function(){
	return this.talkToObject;
};
WebimUIEngineer.prototype.setTalkParams = function(strObjectCd,strObjectName,strObjectType,strExtParam){
	this.setTalkToObject({
		objectCd	:strObjectCd,
		objectName	:strObjectName,
		objectType	:strObjectType,
		extParam	:((!strExtParam)?'':strExtParam)
	});
};
WebimUIEngineer.prototype.setTalkToUserCd = function(strObjectCd){
	// {userCd:tUserCd,userName:tUserName,sexCd:tSexCd,src:tImgSrc,allParam:extParam};
	var obj = this.findTreeUserNodeJson(strObjectCd);
	if(!obj){
		//alert(" WebimUIEngineer.prototype.setTalkToUserCd param :strObjectCd is illegal! please contact developer thank u! ");
		return;
	}
	this.setTalkToUser(obj.userCd,obj.userName,(obj.sexCd + "," + obj.src  + "," +  obj.allParam));
};
WebimUIEngineer.prototype.closeChat = function(){
	// {userCd:tUserCd,userName:tUserName,sexCd:tSexCd,src:tImgSrc,allParam:extParam};
	this.talkToObject = null;
	$(this.config.clsChatDialogTitleBar).empty();
	$(this.config.clsChatDialogTitleBar).append('请选择会话对象');
};
WebimUIEngineer.prototype.isHasNewmsg = function(strUserCd){
	var imgClass = this.getIconClass(strUserCd);
	if(".none_newmsg_offline,.none_newmsg_online,.male_newmsg_offline,.male_newmsg_online,.female_newmsg_offline,.female_newmsg_online".indexOf(imgClass)!= -1){
		return true;
	}else{
		return false;
	}
};
WebimUIEngineer.prototype.setTalkToUser = function(strObjectCd,strObjectName,strExtParam){
	if(strObjectCd && !strObjectName){
		objectName = this.getUserNode(strObjectCd);
	}
	this.setTalkParams(strObjectCd, strObjectName, this.config.dataObjectTypeUser, strExtParam);
};
WebimUIEngineer.prototype.setTalkToRoom = function(strObjectCd,strObjectName,strObjectType,strExtParam){
	this.setTalkParams(strObjectCd, strObjectName, this.config.dataObjectTypeRoom, strExtParam);
};

WebimUIEngineer.prototype.setTalkToObject = function(talkObject){
	
	/*ymPrompt.win({
		message:"<div style='line-height:20px;padding:2px 8px;'>尊敬的各位同仁：<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;非常抱歉，出于对财务系统接入期间的安全问题考虑，及系统整体升级调整的需要，在线对话模块将暂时停止服务。"+
			"待调整完毕，升级后的在线对话模块将提供更佳的使用体验。感谢大家的理解和支持！<br/><div align=right>PD开发团队</div></div>",
		title:'提示',
		height:'200px'
	});*/
};

/**
 * 2010.7.7 屏蔽聊天功能
 * @param strObjectCd
 * @return
 */
//打开对话窗口(人员或群组)
WebimUIEngineer.prototype.setTalkToObject1 = function(talkObject){
	if(!talkObject){
		//alert(" talkObject is illegal! please contact developer thank u!");
		return;
	}
	var strObjectCd   = talkObject.objectCd;
	var strObjectName = talkObject.objectName;
	var strObjectType = talkObject.objectType;
	
	if(this.isBlank(strObjectCd) || this.isBlank(strObjectName) || this.isBlank(strObjectType)){
		//alert(" construct TalkToObject ：param is illegal! please contact developer thank u! \n strObjectCd["+strObjectCd+"] objectName["+strObjectName+"] objectType["+strObjectType+"]");
		return;
	}

	//下面是更新聊天面板/正在联系/最近联系人列表/群聊(待扩展)
	//************************************更新聊天窗口************************************
	var isCurObject = false;
	if( this.talkToObject && (this.talkToObject.objectCd ==strObjectCd) ){
		isCurObject = true;
	}

	if(!isCurObject){
		//设置当前聊天对象
		this.talkToObject = talkObject;
		//设置对话人员
		$(this.config.clsChatDialogTitleBar).empty();
		$(this.config.clsChatDialogTitleBar).append('<span class=""></span><span>与【' + strObjectName + '】聊天</span><input type="hidden" value="' + strObjectCd + '">');
	}
	//若有新消息,则接收消息
	var isNewmsg = this.isHasNewmsg(strObjectCd);
	if( isNewmsg){
		this.webimClient.asyncGetNewMsg(strObjectCd);
		return;
	}				

	//读取缓存消息+最新消息 
	var strMsgHTML = this.getFromMsgCache(strObjectCd);
	$(this.config.clsChatDialogContent).empty();
	$(this.config.clsChatDialogContent).append(strMsgHTML);

	//注意:新消息在delegateEchoNewPriateMsg()读取.

	//更新正在对话列表
	this.addListItem(this.config.clsTalkingContact, this.talkToObject, true, true);

	//显示最近消息
	//$(this.config.clsChatDialogEnd).focus().blur();
	//$(this.config.clsChatDialogTextarea).focus();
	//$(this.config.clsChatDialogContent).scrollTop($(this.config.clsChatDialogContent).scrollHeight);
	var content = document.getElementById("chatting_content");
	content.scrollTop = content.scrollHeight;

	//刷新各个区域的图标
	this.updateIconDialogList(this.talkToObject.objectCd);

	//处理来消息提醒
	this.dealTipNewMsg();

	var oThis = this;
	var iWidth = $(document).width(); 
	var iHeight = $(document).height();
	var h = 350;
	var w = 500;
	$(this.config.clsChatDialog).css({'zIndex':'99','width':w+'px','height':h+'px','top':(iHeight-h)/2+'px','left':(iWidth-w)/2+'px'}).show();
};
//功能:检查是否还有未处理的来信 (1-有 0-无)
WebimUIEngineer.prototype.dealTipNewMsg = function(){
	var len =  $(this.config.clsLeftContainer)
			.find(".none_newmsg_offline,.none_newmsg_online,.male_newmsg_offline,.male_newmsg_online,.female_newmsg_offline,.female_newmsg_online")
			.length;
	//alert("检查是否还有未处理的来信:"+len);
	if(len > 0){
		$(this.config.clsMessageIconTipNew).show();
		this.openDocTitleManager();
	}else{
		$(this.config.clsMessageIconTipNew).hide();
		this.closeDocTitleManager();
	}
};
//功能:搜索指定userCd的用户节点
WebimUIEngineer.prototype.findTreeUserNodeJson = function (strUserCd){
	
	var node = tree.getNodeById("usertreenode_"+strUserCd);
	if(!node){
		//alert("WebimUIEngineer.prototype.findTreeUserNodeJson param strUserCd["+strUserCd+"] is not found! please contact developer thank u!");
		return;
	}
	var strUserName = node.attributes.text;
	var strSexCd = node.attributes.sexCd;
	var strIconPath = node.iconPath; 
	return rtnObj = {userCd:strUserCd,userName:strUserName,sexCd:strSexCd,src:strIconPath};
};

//功能:更新用户
WebimUIEngineer.prototype.updateIcons = function (data){
 	if(!data){
	 	return ;
	} 
   	for(var i=0; i< data.length; i++){
   		var record = data[i];
   		var strUserCd = record.userCd;
   		var strOnline = record.online;
   		this.updateIcon(strUserCd,strOnline,true);
   	}
};

WebimUIEngineer.prototype.updateIconsRever = function(){
	$(this.config.clsTalkingContact+" li").each(function(){
		var strUserCd = $(this).attr("id");
	});
	//3.最近联系人
	$(this.config.clsRecentContacts).find(" li[id='userno_"+ strUserCd+ "'] span:first").removeClass().addClass(newIconClass);
}

//功能:人员图标(机构树+更新聊天/正在联系/最近联系清单)
WebimUIEngineer.prototype.updateIcon  = function (strUserCd,strOnline,initFlg){
	strUserCd += '';//强制转字符串
	strOnline += '';
	this.updateIconDataTree(strUserCd,strOnline);
	if(!initFlg){
		this.updateIconDialogList(strUserCd);
	}
};

//功能: 更新机构树的用户在线图标
WebimUIEngineer.prototype.updateIconDataTree  = function (strUserCd,strNewOnline){
	
	var node = this.tree.getNodeById("usertreenode_"+strUserCd);
	if(!node){
		//alert("WebimUIEngineer.prototype.findTreeUserNodeJson param strUserCd["+strUserCd+"] is not found! please contact developer thank u!");
		return;
	}
	var strOldPath = node.iconPath;
	var strSexCd = node.attributes.sexCd;
	var strNewPath = this.getNewIconPath(strOldPath,strNewOnline,strSexCd);//userCd,online,src
	node.iconPath = strNewPath;
	$("#usertreenode_"+strUserCd).attr("src",strNewPath);
	var strNewClass= this.getNewIconClass(strNewPath); 
	return strNewClass;
};
  
//获取树结构新图标路径
WebimUIEngineer.prototype.getNewIconPath = function (strOldPath,strNewOnline,strSexCd){
	var newImgPath = "";
	switch(strSexCd){
		case this.config.dataSexNone:{//未知
			switch(strNewOnline){ 
				 case this.config.dataUserNewMsgDone:
					 switch(strOldPath){
						 case this.icon.noneNewmsgOfflinePath: newImgPath = this.icon.noneOnlinePath ; break;
						 case this.icon.noneNewmsgOnlinePath : newImgPath = this.icon.noneOnlinePath ; break;
						 case this.icon.noneOfflinePath	 	 : newImgPath = this.icon.noneOfflinePath; break;
						 case this.icon.noneOnlinePath		 : newImgPath = this.icon.noneOnlinePath ; break;
						 default					 		 : newImgPath = this.icon.noneOnlinePath ; break;
					 }
					 break;
				 case this.config.dataUserNewMsg:
					 switch(strOldPath){
						 case this.icon.noneNewmsgOfflinePath: newImgPath = this.icon.noneNewmsgOnlinePath ; break;
						 case this.icon.noneNewmsgOnlinePath : newImgPath = this.icon.noneNewmsgOnlinePath ; break;
						 case this.icon.noneOfflinePath	 	 : newImgPath = this.icon.noneNewmsgOfflinePath; break;
						 case this.icon.noneOnlinePath		 : newImgPath = this.icon.noneNewmsgOnlinePath ; break;
						 default					 		 : newImgPath = this.icon.noneNewmsgOnlinePath ; break;
					 }
					 break;
			 	 case this.config.dataUserOnLine://在线
					 switch(strOldPath){
						 case this.icon.noneNewmsgOfflinePath: newImgPath = this.icon.noneNewmsgOnlinePath; break;
						 case this.icon.noneNewmsgOnlinePath : newImgPath = this.icon.noneNewmsgOnlinePath; break;
						 case this.icon.noneOfflinePath	 	 : newImgPath = this.icon.noneOnlinePath	  ; break;
						 case this.icon.noneOnlinePath		 : newImgPath = this.icon.noneOnlinePath	  ; break;
						 default					 		 : newImgPath = this.icon.noneOnlinePath	  ; break;
					 }
					 break;
				 case this.config.dataUserOffLine:
					 switch(strOldPath){
						 case this.icon.noneNewmsgOfflinePath: newImgPath = this.icon.noneNewmsgOfflinePath; break;
						 case this.icon.noneNewmsgOnlinePath : newImgPath = this.icon.noneNewmsgOfflinePath; break;
						 case this.icon.noneOfflinePath	 	 : newImgPath = this.icon.noneOfflinePath      ; break;
						 case this.icon.noneOnlinePath		 : newImgPath = this.icon.noneOfflinePath      ; break;
						 default							 : newImgPath = this.icon.noneOfflinePath	   ; break;
					 }
					 break;
				 default: break;
			}
		}
		break;
		case this.config.dataSexMale:{//男
			switch(strNewOnline){ 
				 case this.config.dataUserNewMsgDone: 
						 switch(strOldPath){
						 case this.icon.maleNewmsgOfflinePath	: newImgPath = this.icon.maleOnlinePath ; break;
						 case this.icon.maleNewmsgOnlinePath 	: newImgPath = this.icon.maleOnlinePath ; break;
						 case this.icon.maleOfflinePath	 	 	: newImgPath = this.icon.maleOfflinePath; break;
						 case this.icon.maleOnlinePath		 	: newImgPath = this.icon.maleOnlinePath ; break;
						 default					 		 	: newImgPath = this.icon.maleOnlinePath ; break;
					 }
					 break;
				 case this.config.dataUserNewMsg:
					 switch(strOldPath){
						 case this.icon.maleNewmsgOfflinePath: newImgPath = this.icon.maleNewmsgOnlinePath ; break;
						 case this.icon.maleNewmsgOnlinePath : newImgPath = this.icon.maleNewmsgOnlinePath ; break;
						 case this.icon.maleOfflinePath	 	 : newImgPath = this.icon.maleNewmsgOfflinePath; break;
						 case this.icon.maleOnlinePath		 : newImgPath = this.icon.maleNewmsgOnlinePath ; break;
						 default					 		 : newImgPath = this.icon.maleNewmsgOnlinePath ; break;
					 }
					 break;
				 case this.config.dataUserOnLine://在线
					 switch(strOldPath){
						 case this.icon.maleNewmsgOfflinePath: newImgPath = this.icon.maleNewmsgOnlinePath ; break;
						 case this.icon.maleNewmsgOnlinePath : newImgPath = this.icon.maleNewmsgOnlinePath ; break;
						 case this.icon.maleOfflinePath	 	 : newImgPath = this.icon.maleOnlinePath	   ; break;
						 case this.icon.maleOnlinePath		 : newImgPath = this.icon.maleOnlinePath	   ; break;
						 default							 : newImgPath = this.icon.maleOnlinePath	   ; break;
					 }
					 break;
				 case this.config.dataUserOffLine:
					 switch(strOldPath){
						 case this.icon.maleNewmsgOfflinePath: newImgPath = this.icon.maleNewmsgOfflinePath; break;
						 case this.icon.maleNewmsgOnlinePath : newImgPath = this.icon.maleNewmsgOfflinePath; break;
						 case this.icon.maleOfflinePath	 	 : newImgPath = this.icon.maleOfflinePath      ; break;
						 case this.icon.maleOnlinePath		 : newImgPath = this.icon.maleOfflinePath      ; break;
						 default							 : newImgPath = this.icon.maleOfflinePath	   ; break;
					 }
					 break;
				 default: break;
			 }
		}
		break;
		case this.config.dataSexFemale:{//女
			switch(strNewOnline){
				 case this.config.dataUserNewMsgDone: 
					 switch(strOldPath){
						 case this.icon.femaleNewmsgOfflinePath  : newImgPath = this.icon.femaleOnlinePath ; break;
						 case this.icon.femaleNewmsgOnlinePath   : newImgPath = this.icon.femaleOnlinePath ; break;
						 case this.icon.femaleOfflinePath	 	 : newImgPath = this.icon.femaleOfflinePath; break;
						 case this.icon.femaleOnlinePath		 : newImgPath = this.icon.femaleOnlinePath ; break;
						 default					 			 : newImgPath = this.icon.femaleOnlinePath ; break;
					 }
					 break;
				 case this.config.dataUserNewMsg:
					 switch(strOldPath){
						 case this.icon.femaleNewmsgOfflinePath  : newImgPath = this.icon.femaleNewmsgOnlinePath ; break;
						 case this.icon.femaleNewmsgOnlinePath   : newImgPath = this.icon.femaleNewmsgOnlinePath ; break;
						 case this.icon.femaleOfflinePath	 	 : newImgPath = this.icon.femaleNewmsgOfflinePath; break;
						 case this.icon.femaleOnlinePath		 : newImgPath = this.icon.femaleNewmsgOnlinePath ; break;
						 default					 			 : newImgPath = this.icon.femaleNewmsgOnlinePath ; break;
					 }
					 break;
				 case this.config.dataUserOnLine:
					 switch(strOldPath){
						 case this.icon.femaleNewmsgOfflinePath  : newImgPath = this.icon.femaleNewmsgOnlinePath; break;
						 case this.icon.femaleNewmsgOnlinePath   : newImgPath = this.icon.femaleNewmsgOnlinePath; break;
						 case this.icon.femaleOfflinePath	 	 : newImgPath = this.icon.femaleOnlinePath	    ; break;
						 case this.icon.femaleOnlinePath		 : newImgPath = this.icon.femaleOnlinePath	    ; break;
						 default					 			 : newImgPath = this.icon.femaleOnlinePath	    ; break;
					 }
					 break;
				 case this.config.dataUserOffLine:
					 switch(strOldPath){
						 case this.icon.femaleNewmsgOfflinePath  : newImgPath = this.icon.femaleNewmsgOfflinePath; break;
						 case this.icon.femaleNewmsgOnlinePath   : newImgPath = this.icon.femaleNewmsgOfflinePath; break;
						 case this.icon.femaleOfflinePath	 	 : newImgPath = this.icon.femaleOfflinePath	     ; break;
						 case this.icon.femaleOnlinePath		 : newImgPath = this.icon.femaleOfflinePath	     ; break;
						 default					 			 : newImgPath = this.icon.femaleOfflinePath	     ; break;
					 }
					 break;
				 default: break;
			}
		}
		break;
		default: break;
	}
	return newImgPath;
};

WebimUIEngineer.prototype.updateIconDialogList = function (strUserCd){
	var newClass = this.getIconClass(strUserCd);
	this.refreshDialog(strUserCd,newClass);
	this.refreshUserList(strUserCd,newClass);
};

WebimUIEngineer.prototype.getIconClass = function(strUserCd){
	var strOldPath = '';
	var node = tree.getNodeById("usertreenode_"+strUserCd);
	if(!node){
		//alert("WebimUIEngineer.prototype.findTreeUserNodeJson param strUserCd["+strUserCd+"] is not found! please contact developer thank u!");
		return;
	}
	strOldPath = node.iconPath;
	var newIconClass = this.getNewIconClass(strOldPath);
	if(!newIconClass){
		newIconClass = '';
	} 
	return newIconClass;
};
//0-不在线 1-在线
WebimUIEngineer.prototype.getOnlineStatus = function(strUserCd){
	var iconClass = this.getIconClass(strUserCd);
	switch(iconClass){
		case this.iconClass.noneNewmsgOffline		: return '0';
		case this.iconClass.noneNewmsgOnline 		: return '1';
		case this.iconClass.noneOffline	 			: return '0';
		case this.iconClass.noneOnline	 			: return '1';
		case this.iconClass.maleNewmsgOffline		: return '0';
		case this.iconClass.maleNewmsgOnline 		: return '1';
		case this.iconClass.maleOffline	 			: return '0';
		case this.iconClass.maleOnline		 		: return '1';
		case this.iconClass.femaleNewmsgOffline		: return '0';
		case this.iconClass.femaleNewmsgOnline		: return '1';
		case this.iconClass.femaleOffline			: return '0';
		case this.iconClass.femaleOnline			: return '1';
		default: return '0';
	}
};

WebimUIEngineer.prototype.refreshDialog = function(strUserCd, newIconClass){
	//1.消息对话框
	var titleBar = $(this.config.clsChatDialogTitleBar);
	if(!titleBar){
		//alert(" titlebar is not found!");
		return;
	}
	var talkObject = this.getTalkToObject();
	if(!talkObject){
		return;
	}
	if( strUserCd == talkObject.objectCd){
		titleBar.find("span:first").removeClass().addClass(newIconClass);
	}
};
//列表
WebimUIEngineer.prototype.refreshUserList = function(strUserCd, newIconClass){
	//2.正在对话列表
	$(this.config.clsTalkingContact).find(" li[id='userno_"+ strUserCd+ "'] span:first").removeClass().addClass(newIconClass);
	//3.最近联系人
	$(this.config.clsRecentContacts).find(" li[id='userno_"+ strUserCd+ "'] span:first").removeClass().addClass(newIconClass);
};
WebimUIEngineer.prototype.updateListItemIcon = function(jqSpan){
	if(!jqSpan){
		//alert(" updateListItemIcon : jqSpan is null!");
		return;
	} 
	var strOldPath = '';
	$(".dtree img[nodetype='userNode']").each(function() {
		 var extParam = $(this).attr("nodeextparam");
		 var tmpUserCd = extParam.split(",")[2];
		 var strSexCd = extParam.split(",")[4];
		 if( strUserCd == tmpUserCd ){
			 strOldPath = $(this).attr("src");
		}
	}); 
	var newIconClass = this.getNewIconClass(strOldPath);
	if(!newIconClass){
		newIconClass = '';
	} 
	jqSpan.removeClass().addClass(newIconClass);
};

//获取对话框新图标样式
WebimUIEngineer.prototype.getNewIconClass = function (strIconPath){
	var newIconClass = "";
	     if(strIconPath.lastIndexOf(this.icon.noneNewmsgOfflinePath  )!= -1) newIconClass = this.iconClass.noneNewmsgOffline   ;
	else if(strIconPath.lastIndexOf(this.icon.noneNewmsgOnlinePath   )!= -1) newIconClass = this.iconClass.noneNewmsgOnline    ; 
	else if(strIconPath.lastIndexOf(this.icon.noneOfflinePath        )!= -1) newIconClass = this.iconClass.noneOffline         ; 
	else if(strIconPath.lastIndexOf(this.icon.noneOnlinePath         )!= -1) newIconClass = this.iconClass.noneOnline          ; 
	else if(strIconPath.lastIndexOf(this.icon.maleNewmsgOfflinePath  )!= -1) newIconClass = this.iconClass.maleNewmsgOffline   ; 
	else if(strIconPath.lastIndexOf(this.icon.maleNewmsgOnlinePath   )!= -1) newIconClass = this.iconClass.maleNewmsgOnline    ; 
	else if(strIconPath.lastIndexOf(this.icon.maleOfflinePath        )!= -1) newIconClass = this.iconClass.maleOffline         ; 
	else if(strIconPath.lastIndexOf(this.icon.maleOnlinePath         )!= -1) newIconClass = this.iconClass.maleOnline          ; 
	else if(strIconPath.lastIndexOf(this.icon.femaleNewmsgOfflinePath)!= -1) newIconClass = this.iconClass.femaleNewmsgOffline ;  
	else if(strIconPath.lastIndexOf(this.icon.femaleNewmsgOnlinePath )!= -1) newIconClass = this.iconClass.femaleNewmsgOnline  ; 
	else if(strIconPath.lastIndexOf(this.icon.femaleOfflinePath      )!= -1) newIconClass = this.iconClass.femaleOffline       ; 
	else if(strIconPath.lastIndexOf(this.icon.femaleOnlinePath       )!= -1) newIconClass = this.iconClass.femaleOnline        ;
	else {
	}
	return newIconClass;
};

//*********************************************************************/
//UI操作
//*********************************************************************/

//功能: 插入行
WebimUIEngineer.prototype.addListItem = function(jqListId,talkObject,bEnableCloseFlg,bEnableInsertNode){

	//判断是否存在?若是,移动到第一位;若否,插入到第一位.
	var strObjectCd   = talkObject.objectCd;
	var strObjectName = talkObject.objectName;

	var oThis = this;
	if(bEnableInsertNode){
		//先移除节点再插入
		//$(jqListId).find("li[id='userno_"+strObjectCd+"']").remove();
	
		//若存在,返回
		var len = $(jqListId).find("li[id='userno_"+strObjectCd+"']").length;
		if( len > 0){
			return;
		}
		
		var listItemHtml = this.getListItemHtml(strObjectCd, strObjectName, true);
		//前面插入
		//$(jqListId).prepend(listItemHtml);
		//后面插入
		$(jqListId).prepend(listItemHtml);

		//处理listitem事件
		//增加onclick(同AddListItem)
		$(jqListId).find("li:first").click(function(){
			var userId = $(this).attr("id");
			if(!userId){
				//alert("userid is null! please contact developer! thank u!");
				return ;
			}
			var strUserCd = userId.substr("userno_".length);
			oThis.setTalkToUserCd(strUserCd);
		}); 
		//增加mouseover(同contactRecents的listItem)
		$(jqListId).find("li:first").mouseover(function(){
			$(this).siblings().removeClass();
			$(this).addClass("selected");
			$(this).find(".x_close").show();
		});
		//增加mouseout
		$(jqListId).find("li:first").mouseout(function(){
			$(this).removeClass();
			$(this).find(".x_close").hide();
		});

		//处理关闭按钮
		$(jqListId).find("li:first").find(".x_close").click(function(){
			$(this).parent().remove();
		});
	}else{
		
		/*
		$(jqListId).find(".x_close").remove();
		//若存在,先移除节点再插入
		var listItem = $(jqListId).find("li[id='userno_"+strObjectCd+"']");
		if( listItem.length>0){
			$(jqListId).find("li[id='userno_"+strObjectCd+"']").remove();
			$(jqListId).prepend(listItem);
			
			//增加事件(同AddListItem)
			$(jqListId).find("li:first").click(function(){
				var userId = $(this).attr("id");
				if(!userId){
					//alert("userid is null! please contact developer! thank u!");
					return ;
				}
				var strUserCd = userId.substr("userno_".length);
				oThis.setTalkToUserCd(strUserCd); 
			}); 
			
			//增加事件(同contactRecents的listItem)
			$(jqListId).find("li:first").mouseover(function(){
				$(this).siblings().removeClass();
				$(this).addClass("selected");
			});
			$(jqListId).find("li:first").mouseout(function(){
				$(this).removeClass();
			});
		}
		*/
	}
};

/**功能:获取li的html结构
 * 参数: jqList - 存放li的jquery对象(即li的直接上级)
 *       	 icontype:iconNode 
 *       	 图标类型 :0-离线 1-上线 2-离线消息 3-上线消息 
 *       	 对应class分别是offline,online,newmsg_offline,newmsg_online
 *                      
 * ----------------------------------------------------------------------
 * 例子: 
 *       <ul>
 * 			 <li id="userno_8" alt="张三" title="张三" class="selected">
 * 			 	<span class="offline" icontype="iconNode" ></span>
 * 			 	<span class="userName">张三</span>
 * 			 	<span class="x_close"></span>
 * 			 </li>
 * 		</ul>
 * ----------------------------------------------------------------------
 */
WebimUIEngineer.prototype.getListItemHtml = function(strObjectCd,strObjectName,bEnableCloseFlg){
	if(!strObjectCd ||!strObjectName ||!bEnableCloseFlg  ){
		//alert('param error: strObjectCd=[' + strObjectCd  + '] objectName ['+strObjectName+'] bEnableCloseFlg['+bEnableCloseFlg+']' );
		return '';
	}
	var liHtml =' <li id="userno_' + strObjectCd +'" alt="'+strObjectName +'" title="'+strObjectName+'"> 	'+
			    ' <span class="" icontype="iconNode" onclick="" ></span> '+//用户设置用户在线状态的图标
			    ' <span class="userName">'+ strObjectName +'</span>';
	if(bEnableCloseFlg){
		liHtml+=' <span class="x_close"></span>';
	}
		liHtml+=' </li>';
				
	return liHtml;
};

//功能:鼠标移动到正在聊天人员,显示"关闭按钮"
WebimUIEngineer.prototype.onMouseOverListItem = function(jqListItem){
	if(!jqListItem){
		//alert("onMouseOverChatListItem param:jqListItem is " + jqListItem);
		return;
	}else{
		jqListItem.find(this.config.clsListItemFloatClose).show();
	}
};

//功能:鼠标移出正在聊天人员,隐藏"关闭按钮"
WebimUIEngineer.prototype.onMouseOutListItem = function(jqListItem){
	if(!jqListItem){
		//alert("onMouseOutChatListItem param:jqListItem is " + jqListItem);
		return;
	}else{
		jqListItem.find(this.config.clsListItemFloatClose).hide();
	} 
};

//功能:单击关闭按钮,移除选中人员
WebimUIEngineer.prototype.removeJqListItem = function(jqCloseButton){
	if(!jqCloseButton){
		//alert("removeJqListItem param:jqListItem is " + jqListItem);
		return;
	}else{
		try{
			var jqItemId = jqCloseButton.parent().attr("id");//attr:li
			//alert(" click item:" + jqItemId);
			if(!jqItemId){
				//alert("removeJqListItem param:jqListItem is " + jqItemId);
			}
			jqCloseButton.parent().remove("#" + jqItemId);
		}catch(e){
			alret(e);
		}
	}
};
 
//****************************************************************/
//调用WebimClientEngineer的请求
//****************************************************************/
//加载完毕后,定时1.5 s更新用户状态 very important!

//获取在线用户清单
WebimUIEngineer.prototype.requestOnLineUserCds = function(){
	this.webimClient.requestOnlineUserCds(); 	
};
//发送消息
WebimUIEngineer.prototype.sendMessage = function(talkToObject,strMsgText){
	if(!talkToObject){
		//alert("请选择会话对象...");
		return false;
	}
	//若无输入内容,返回
	//var strMsgText = $.trim($(this.config.clsChatDialogTextarea).val());
	var strMsgText = $.trim(chatRichEditor.getSource());
	if((strMsgText == this.config.tip_message_content) ||(strMsgText == this.config.tip_message_content_here ) || (strMsgText == '')){
		return false;
	}
	var result = this.validateMsgLength(strMsgText,this.config.dataMsgMinLength,this.config.dataMsgMaxLength);
	if(!result){
		return false;
	} 

	var strObjectCd   = talkToObject.objectCd;
	var strObjectName = talkToObject.objectName;
	var strObjectType = talkToObject.objectType;
	var strSelfMessage= '';
	
	if(this.strClientUserCd == strObjectCd){
		strSelfMessage= this.config.dataEchoBackFlgSelf;//发送给自己
	}
	
	/*
	if(!this.webimClient.isConnected()){
		//alert("sorry! client can not connect to webim server! \n please refresh web page or check the network! ");
		return false;
	}else{
		//alert("connected!");
	}
	*/
	
	switch(strObjectType){
		case this.config.dataObjectTypeUser:
			 this.webimClient.send(strObjectCd,strMsgText);
			 break;
		case this.config.dataObjectTypeRoom:
			 this.webimClient.sendToRoom(strObjectCd,strMsgText);
			 break;
		default: break;
	}
	//清除
	//$(this.config.clsChatDialogTextarea).val(" ");//空格
	//$(this.config.clsChatDialogTextarea).focus();
	chatRichEditor.setSource("");
	chatRichEditor.focus();
	
	return true;
};
//注销
WebimUIEngineer.prototype.logout = function(){
	if(!this.strClientUserCd) 
		return;
	this.webimClient.asyncChangeOnline(this.strClientUserCd,this.config.dataUserOffLine);
};
/********************************************************************************************/
//                        公用方法
/********************************************************************************************/
//标题走马灯控制器
var docTitleManager;
WebimUIEngineer.prototype.openDocTitleManager = function(title) {
	
	//先关闭
	this.closeDocTitleManager();
	
	var msgud = "       " + this.config.tip_title_content_newmsg; 
	var msg = this.config.tip_title_content_newmsg;
	
	docTitleManager = setInterval(function(){
		if (msgud.length <msg.length) msgud += " " + msg; 
		msgud = msgud.substring(1, msgud.length); 
		document.title = msgud.substring(0, msg.length);
	}, this.config.TIP_TITLE_CONTENT_SPEED);
};
WebimUIEngineer.prototype.closeDocTitleManager = function() {
	if(docTitleManager){
		clearInterval(docTitleManager);
		document.title = this.config.tip_title_content;
	}
};
//功能: 缓存消息
WebimUIEngineer.prototype.saveToMsgCache = function(strObjectCd, strMsgHTML) {
	if(!strMsgHTML) {
		return;
	}  
	var historyMsg = this.messageCache[strObjectCd];
	if(!historyMsg){
		historyMsg = '';	
	}
	this.messageCache[strObjectCd] = historyMsg + strMsgHTML;
};
WebimUIEngineer.prototype.getFromMsgCache = function(strObjectCd) {
	var strMsgHTML = this.messageCache[strObjectCd];
	if(!strMsgHTML){
		return '';
	}else{
		return strMsgHTML;
	}
};

/**
 * 功能: 校验字符串长度,不能超过数据库定义
 *       取得字符串的长度(汉字的长度为2，英文为1)
 * @param msg       - 发送消息
 * @param minLength - 最小长度
 * @param maxLength - 最大长度
 */
WebimUIEngineer.prototype.validateMsgLength = function(strMsgText,minLength,maxLength){
	var msgLength = strMsgText.replace(/[^\x00-\xff]/g,"**").length;
	if( minLength > msgLength){
		return false;
	}
	if( maxLength  < msgLength){
		alert("信息超长,最多允许"+this.config.dataMsgMaxLength+"个字符(或"+(this.config.dataMsgMaxLength/2)+"个汉字)");
		return false;
	}
	return true;
};

//判断是否为空
WebimUIEngineer.prototype.isBlank = function(val){
	if( typeof(val) == 'undefined'){
		return true;
	}
	//针对非字符串类型的数据,强制转成字符串
	val += '';
	
	if($.trim(val) == ''){
		return true;
	}else{
		return false;
	}
};
//设置在线人数
WebimUIEngineer.prototype.notifyOnline = function(strOnline){
	var iCountOnline = this.getCountOnline();
	if('0' == strOnline){
		--iCountOnline;
		this.setCountOnline((iCountOnline<0)?0:iCountOnline);
	}
	else if('1' == strOnline){
		var iCountAll = this.getCountAll();
		++iCountOnline;
		this.setCountOnline((iCountOnline>iCountAll)?iCountAll:iCountOnline);
	}
};
//在线人数
WebimUIEngineer.prototype.getCountOnline = function(){
	return parseInt($("#"+this.config.idCountOnline).html());
};
WebimUIEngineer.prototype.setCountOnline = function(len){
	$("#"+this.config.idCountOnline).html(len+'');
};
//总人数
WebimUIEngineer.prototype.getCountAll = function(){
	return parseInt($("#"+this.config.idCountAll).html());
};
WebimUIEngineer.prototype.setCountAll = function(len){
	$("#"+this.config.idCountAll).html(len+'');
};
//显示统计面板
WebimUIEngineer.prototype.showStatisPanel = function(len){
	$("#"+this.config.idStatisticPanel).show();
};

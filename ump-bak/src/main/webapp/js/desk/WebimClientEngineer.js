
/** 
 * @class    WebimClientEngineer.js
 * @author   huangbijin
 * @requires comet.js
 * 
 * 主要功能如下:
 * (1)创建连接
 * (2)注册服务
 *    1.获取用户状态变化(上线/离线)
 *    2.获取登录上线用户
 *    3.获取在线用户清单
 *    4.获取最近联系清单
 *    5.获取私人聊天信息
 *    6.获取群组聊天信息
 *    7.获取最新消息提醒
 *    8.获取群组属性变化
 *    9.获取群组成员变化
 *   10.获取接收方的状态
 * (3)注销服务
 * (4)释放连接
 * 
 * @class 初始化CometD服务监听器/.用于获取各种CometD服务传来的信息
 */


//全局唯一
var cometd = $.cometd;
//是否连接
var connected = false;

//参数: strCometUrl  {string}  初始化comet服务地址(例如http://127.0.0.1/webim/cometd) 
//      uiid         {string}
//      observer     {object}  注册监听对象,为了重写WebimClientEngineer的代理方法时,可以调用observer的属性/方法
function WebimClientEngineer(strCometUrl,strUiid,observer) {
	
	//定义变量
    this.strCometUrl = strCometUrl;
	this.strUiid 	 = strUiid;
	this.observer 	 = observer;
	
	//定义常量   
    this.config = {
    	//最近联系人的最大人数
    	DATA_CONTACT_NUM   : 20,
    	//用户在线状态 0-离线 1-在线
    	DATA_USER_ONLINE   : 1,
    	DATA_USER_OFFLINE  : 0
    }; 

	//用户状态发生变化(上线/下线)
    this.onlineStatusListener = null;
    
	//搜索当前uiid对应用户编号
    this.getLoginUserCdListener = null;
    //搜索在线人员清单
    this.echoOnlineUserListListener = null;
	//搜索最近联系人
    this.recentContactsListener = null;
    //搜索消息内容
    this.echoPrivateChatListener = null;
    //搜索聊天室消息内容
    this.echoRoomChatListener = null;
    //搜索新消息提醒
    this.echoNewPrivateMsgListener = null;
    //搜索聊天室新消息提醒
    this.echoNewRoomMsgListener = null;
    //搜索聊天室变化
    this.echoRoomChangeListener = null;
    //搜索群组成员变化
    this.echoRoomMembersListener = null;
    //检测发送信息接收方的在线状态
    this.echoUserOnlineListener = null;
};


//功能: 初始化CometD服务连接
WebimClientEngineer.prototype.initConnect = function() {
    var oThis = this;
    //Listen for error messages like handshake error!
	function metaConnect(message) {
		//log(" metaConnect + message" + message);

		//若不这么判断,会不断请求服务端
		var wasConnected = connected;
		connected = (message && message.successful);
		if (!wasConnected && connected) {
			oThis.connectionEstablished();
		}
		else if (wasConnected && !connected) {
			oThis.connectionBroken();
		}
		else{
			//log(" Do nothing...");
		}
	};
	
	//It is not possible to subscribe to meta channels: the server will reply with an error message.
	//However, it is possible to listen to meta channels.
	cometd.addListener('/meta/connect', metaConnect);
	cometd.addListener('/meta/subscribe', function(msg) {
		//log(" /meta/subscribe : msg[" + msg + "]");
	});
	
	//level:debug,info
	cometd.configure({
		url 	: this.strCometUrl,
		logLevel: 'info'
	});
	
	//首次握手
	//TODO: Another interesting usage of meta channels is when there is an authentication step during the handshake.
	//The JavaScript Cometd implementation performs automatic reconnect in case of network or Bayeux server failures 
	cometd.handshake();
	
	/*
	//security validate
	var username = "foo";
	var password = "bar";
	cometd.handshake({
		ext:{
			authentication:{
				user:username,
				credentials:passowrd
			}
		}
	});
	*/
	
}; 

//注意: 即使网络掉线,comet的connected仍然是true,不能作为发送的依据
WebimClientEngineer.prototype.isConnected = function(){
	alert("connected:" + connected);
	if(connected){
		return true;
	}else{
		return false;
	}
};
 
//建立连接
WebimClientEngineer.prototype.connectionEstablished = function(){
	//log("Connection Established...");
	var oThis = this;
	$.cometd.batch(function() {
		oThis.unsubscribe();
		oThis.subscribe();
	});
};

//释放连接
WebimClientEngineer.prototype.connectionBroken = function() {
	//log("Connection to Server Broken");
};

//订阅服务
//注意:startBatch与endBatch必须在同一个上下文中,
//    也就是说不能在funcA()中start,funcB()中end,或者用setTimeout中start,其他方法中End
WebimClientEngineer.prototype.subscribe = function() {
	//log("准备登录 CometD...");
    cometd.startBatch();
    
    // 订阅【其他人员在线状态】和【接收好友列表】、【验证登录】等频道
    this.subscribeChatChannel();

    cometd.endBatch();
    //log("完成登录 CometD!");
};

//释放订阅
WebimClientEngineer.prototype.unsubscribe = function() {
	//remove subscribe start
	this.comRemoveSubStribe(this.onlineStatusListener);
	//remove subscribe end
	
	//remove listeners start
	this.comRemoveListener(this.getLoginUserCdListener); 
	this.comRemoveListener(this.echoOnlineUserListListener); 
	this.comRemoveListener(this.recentContactsListener); 
	this.comRemoveListener(this.echoPrivateChatListener); 
	this.comRemoveListener(this.echoRoomChatListener); 
	this.comRemoveListener(this.echoNewPrivateMsgListener); 
	this.comRemoveListener(this.echoNewRoomMsgListener); 
	this.comRemoveListener(this.echoRoomChangeListener); 
	this.comRemoveListener(this.echoRoomMembersListener); 
	this.comRemoveListener(this.echoUserOnlineListener); 
	//remove listeners end
};

//注销订阅
WebimClientEngineer.prototype.comRemoveSubStribe = function(mySubstribe){
	if (mySubstribe) {
		cometd.unsubscribe(mySubstribe);
	}
	mySubstribe = null;
};

//注销监听
WebimClientEngineer.prototype.comRemoveListener = function(myListener){
	if (myListener) {
		cometd.removeListener(myListener);
	}
	myListener = null;
};

/***********************************************************************************/
/* 订阅服务清单如下: */
/***********************************************************************************/


/* 功能: 订阅请求公用方法 */
WebimClientEngineer.prototype.publish = function(channel, jsonParam) {
	//log(" Start publish channel [" + channel +"] jsonParam ["+ jsonParam +"]") ;
    cometd.startBatch();
    cometd.publish(channel, jsonParam);
    cometd.endBatch();
	//log(" End publish !") ;
};

/**
 * 功能: 
 * @param senderNo
 */
WebimClientEngineer.prototype.login = function(strSenderUserCd) {
    cometd.publish('/service/login', {
        uiid 		  : this.strUiid,
        online 		  : this.config.DATA_USER_ONLINE,
        maxContactNum : this.config.DATA_CONTACT_NUM
    });
};

/**
 * 功能: 搜索私聊信息
 * @param senderNo
 */
WebimClientEngineer.prototype.asyncGetNewMsg = function(strSenderUserCd) {
	this.publish('/service/getNewPrivateMsg', {
        senderNo : strSenderUserCd
    });
};

//功能 : 发送请求服务器提供在线人员列表  下面的方法合并使用(见服务端ChatCometService.java)
WebimClientEngineer.prototype.requestOnlineUserCds = function() {
	cometd.publish('/service/getOnlineUserList', {});
};


/**
 * 功能: 搜索私聊信息(聊天室)
 */
WebimClientEngineer.prototype.asyncGetNewMsgRoom = function(strSenderUserCd) {
	this.publish('/service/getNewRoomMsg', {
        roomNo : strSenderUserCd
    });
};
/**
 * 功能: 用户在线状态变化提醒
 * @param {String} userCd 用户编号
 * @param {int}    online 在线状态
 */
WebimClientEngineer.prototype.asyncChangeOnline = function(strChangedUserCd, strOnlineStatus) {
	this.publish('/service/changeOnlineStatus', {
        userCd : strChangedUserCd,
        online : strOnlineStatus
    });
};
/**
 * 功能: 发送消息<br>
 * @param {String} destUserCd 接收方编号
 * @param {String} msg        消息内容
 */
WebimClientEngineer.prototype.send = function(strDestUserCd, strMsgText) {
    this.publish('/service/privateChat', {
        receiverNo : strDestUserCd,
        msg 	   : strMsgText
    });
    return true;
};

/**
 * 待扩展
 * 功能 : 发送消息(群聊)<br>
 * 当前用户向群组发送消息
 * 
 * @param {int} roomNo 接收消息的群组编号
 * @param {String} msg 消息内容
 */
WebimClientEngineer.prototype.sendToRoom = function(strDestRoomCd, strMsgText) {
    this.publish('/service/roomChat', {
        roomNo : strDestRoomCd,
        msg : strMsgText
    });
};

/**
 * 功能 : 订阅这些服务<br>
 * 客户端监听服务端的频道<br>
 */
WebimClientEngineer.prototype.subscribeChatChannel = function() {
	// log(" Start subscribeChatChannel...");
	var oThis = this;
    
	//log(" Start onlineStatusListener...");
    /* 功能: 获取用户状态变化
	 * message.data {
	 *     userCd	[string]-用户编号
	 *     online	[int   ]-在线状态
	 *     }
	 */
    this.onlineStatusListener = cometd.subscribe('/chat/onlineStatus', function(message) {
    	//alert(" call delegateEchoOnlineStatus...");
        if (message != null && message.data != null) {
        	oThis.delegateEchoOnlineStatus(message.data);
        }
    });
	//log(" End onlineStatusListener!");
    
    /* 功能: 获取登录上线的用户信息
     * message.data{
     * 		userCd[string] - 用户编号
     * }
     */
	// log(" Start getLoginUserCdListener...");
	this.getLoginUserCdListener = cometd.addListener('/service/getLoginUserCd', function(message) {
        if (message != null && message.data != null) {
        	oThis.delegateLoginUserCd(message.data);
        }
    });
	// log(" End getLoginUserCdListener!");

	/* 功能: 获取在线用户清单 
	 * message.data[list]
	 * {
	 *  	userCd[string] - 用户编号
	 *  	online[int]	   - 在线状态 
	 * }
	 * */
	// log(" Start echoOnlineUserListListener...");
    this.echoOnlineUserListListener = cometd.addListener('/service/echoOnlineUserList', function(message) {
    	//alert(" call echoOnlineUserListListener...");
        if (message != null && message.data != null) {
        	oThis.delegateEchoOnlineUserList(message.data);
        }
    });
	//log(" End echoOnlineUserListListener!");
    
    /* 功能: 获取最近联系清单 
     * message.data{
     * 	   responseXML [string] - HTML结构
     * }
     */
	//log(" Start recentContactsListener...");
    this.recentContactsListener = cometd.addListener('/service/echoRecentContacts', function(message) {
        if (message != null && message.data != null) {
        	oThis.delegateEchoRecentContacts(message.data);
        }
    });
	//log(" End recentContactsListener!");

    /* 功能 : 接收回显消息
     * mesage.data
     * {
     *   senderNo	[string]: 发送人编号
     *   senderName	[string]: 发送人姓名
     *   receiverNo	[string]: 接收人编号
     *   msgtime	[string]: 发送时间
     *   msg		[string]: 消息内容HTML(即发送人姓名,消息,时间)
     *   echoback	[int   ]: 消息是否为当前用户发给别人的回显消息
     * }
     */
	//log(" Start echoPrivateChatListener...");
    this.echoPrivateChatListener = cometd.addListener('/service/echoPrivateChat', function(message) {
        if (message != null && message.data != null) {
        	oThis.delegateEchoPrivateChat(message.data);
        }
    });
	//log(" End echoPrivateChatListener!");

   /* TODO 
    * 功能 : 聊天室消息内容 
    * mesage.data
    * {
    *   senderNo	[string]: 发送人编号
    *   senderName	[string]: 发送人姓名
    *   receiverNo	[string]: 接收人编号
    *   msgtime		[string]: 发送时间
    *   msg			[string]: 消息内容HTML(即发送人姓名,消息,时间)
    *   echoback	[int   ]: 消息是否为当前用户发给别人的回显消息
    * }
    */
	//log(" Start echoRoomChatListener..."); 
    this.echoRoomChatListener = cometd.addListener('/service/echoRoomChat',function(message) {
        if (message != null && message.data != null) {
        	this.delegateEchoRoomChat(message.data);
        }
    });
	//log(" End echoRoomChatListener!");

    /*
     * 功能 : 私聊新消息提醒
     * message.data(list)
     * {
     * 	 senderNo	[string]: 发送人编号
     * 	 senderName	[string]: 发送人姓名
     * }
     */
	//log(" Start echoNewPrivateMsgListener...");
    this.echoNewPrivateMsgListener = cometd.addListener('/service/echoNewPrivateMsg',function(message) {
        if (message != null && message.data != null) {
        	oThis.delegateEchoNewPriateMsg(message.data);
        }
    });
	//log(" End echoNewPrivateMsgListener!");

    /* TODO
     * 功能 : 群组新消息提醒
     * message.data(list)
     * {
     * 	 roomNo		[string]: 群组编号
     * 	 roomName	[string]: 群组名称
     * }
     */
	//log(" Start echoNewRoomMsgListener...");
    /*
    this.echoNewRoomMsgListener = cometd.addListener('/service/echoNewRoomMsg', function(message) {
        if (message != null && message.data != null) {
        	oThis.delegateEchoNewRoomMsg(message.data);
        }
    });
	//log(" End echoNewRoomMsgListener!");

    /* TODO
     * 功能 : 获取群组的变化情况 - 群组的增加和删除
     * 如果当前人员从群组中被移除，则应该从当前人员的群组列表中将群组信息删除
     * 若当前人员被管理员加入到某个群组中，则应该将该群组添加到当前人员的群组列表中
     */
	//log(" Start echoRoomChangeListener...");
    /*
    this.echoRoomChangeListener = cometd.addListener('/service/echoRoomChange', function(message) {
        if (message != null && message.data != null) {
        	oThis.delegateEchoRoomChange(message.data);
        }
    });
    */
	//log(" End echoRoomChangeListener!");

    /* TODO
     * 功能 : 显示群员列表
     * 该频道返回的json结构： { roomNo:.., membersHTML:.. }
     * 其中 membersHTML 返回的HTML内容格式大致为：
     * <li>(<span id='orgCd_1'>企管部</span>)<span id='userCd_1'>Caesar</span></li>
     * <li>(<span id='orgCd_1'>企管部</span>)<span id='userCd_2'>Test</span></li>
     */
	//log(" Start echoRoomMembersListener...");
    /*
    this.echoRoomMembersListener = cometd.addListener('/service/echoRoomMembers', function(message) {
        if (message != null && message.data != null) {
    		oThis.delegateEchoRoomMembers(message.data);
    	}
    });
    */
	//log(" End echoRoomMembersListener!");

    /*  
     * 功能 : 用户状态变化
    * mesage.data
    * {
    *   userNo	[string]: 发送人编号
    *   online	[int   ]: 在线状态
    * }
     */
	//log(" Start echoUserOnlineListener...");
    
    this.echoUserOnlineListener = cometd.addListener('/service/echoUserOnline', function(message) {
        if (message != null && message.data != null) {
        	oThis.delegateEchoUserOnline(message.data);
        }
    });
    
	//log(" End echoUserOnlineListener!");
	
	
	//log(" End subscribeChatChannel!");
};
 

//***************************************************/
// 功能 : 提供用户自定义扩展  
//***************************************************/

WebimClientEngineer.prototype.delegateEchoOnlineStatus = function(data){
	//log(" call delegateEchoOnlineStatus!");
};
WebimClientEngineer.prototype.delegateLoginUserCd = function(data){
	//log(" call delegateLoginUserCd!");
};
WebimClientEngineer.prototype.delegateEchoOnlineUserList = function(data){
	//log(" call delegateEchoOnlineUserList!");
};
WebimClientEngineer.prototype.delegateEchoRecentContacts = function(data){
	//log(" call delegateEchoRecentContacts!");
};
WebimClientEngineer.prototype.delegateEchoPrivateChat = function(data){
	//alert(" call delegateEchoPrivateChat!");
};
WebimClientEngineer.prototype.delegateEchoRoomChat = function(data){
	//log(" call delegateEchoRoomChat!");
};
WebimClientEngineer.prototype.delegateEchoNewPriateMsg = function(data){
	//log(" call delegateEchoNewPriateMsg!");
};
WebimClientEngineer.prototype.delegateEchoNewRoomMsg = function(data){
	//log(" call delegateEchoNewRoomMsg!");
};
WebimClientEngineer.prototype.delegateEchoRoomChange = function(data){
	//log(" call delegateEchoRoomChange!");
};
WebimClientEngineer.prototype.delegateEchoRoomMembers = function(data){
	//log(" call delegateEchoRoomMembers!");
};
WebimClientEngineer.prototype.delegateEchoUserOnline = function(data){
	//log(" call delegateEchoUserOnLine!");
};
 
/********************************************************************************************/
//                        公用方法
/********************************************************************************************/

/*
var _logged = true;
function log(text) {
  	$('#log').append('<br/>' + text);
}
 */
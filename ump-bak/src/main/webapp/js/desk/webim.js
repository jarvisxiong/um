var connected = false;

var toUsernoMaptoMsg = {};

var loginUserno = null;
var loginUsername = null;
var MIN_MSG_LENGTH = 1;
var MAX_MSG_LENGTH = 2000;//2000个字符(或1000个汉字)

/**
 * 最近联系人的最大人数
 */
var MAX_CONTACT_NUM = 10;

/**
 * 聊天用户管理器<br>
 * 用于管理简易聊天、多人私聊、群聊
 * 
 * @class 创建聊天用户管理器，初始化CometD服务监听器，用于获取各种CometD服务传来的信息
 */
function chatuserbuilder() {
	var _echoOnlineUserListListener = null;
	var _onlineStatusListener = null;
    var _getLoginUserCdListener = null;
    var _recentContactsListener = null;
    var _echoPrivateChatListener = null;
    var _echoRoomChatListener = null;
    var _echoNewPrivateMsgListener = null;
    var _echoNewRoomMsgListener = null;
    var _echoRoomChangeListener = null;
    var _echoRoomMembersListener = null;
    var _echoUserOnlineListener = null;
		
    var _initURL = null;
	/**
	 * 群组聊天窗口中，当前用户正在发送消息的群组编号
	 */
    var _toRoomno = null;
    
    

	/**
	 * 获取当前用户正在参与讨论的群组编号
	 * @return 返回群组编号
	 */
    this.getToRoomno = function() {
        return _toRoomno;
    };
	/**
	 * 设置正在参与讨论的群组编号
	 * @param {int} toRoomno 群组编号
	 */
    this.setToRoomno = function(toRoomno) {
        _toRoomno = toRoomno;
    };
	
	/**
	 * 设置CometD服务连接到的应用服务地址
	 * @param {String} initURL 应用服务地址，比如http://127.0.0.1/webim/cometd
	 */
    this.setInitURL = function(initURL) {
        _initURL = initURL;
    };
    /**
     * 初始化CometD服务连接
     */
    this.initConnect = function() {
		cometd.addListener('/meta/connect', _metaConnect);
		cometd.addListener('/meta/subscribe', function(msg) {
			log("subscribe - msg: " + msg);
			outputMsg(msg);
		});
		cometd.configure({
			url: _initURL,
			logLevel: 'debug'
		});
		
		log("首次握手");
		cometd.handshake();
		
		
		function _unsubscribe() {
			log("_unsubscribe start");
			unsubscribeImpl();
			
			log("removeListener end");
		}
		
		function _subscribe() {
			log("_subscribe");
			subscribeImpl();
		}
		
		
		function _connectionEstablished() {
			log('Connection to Server Opened');
			$.cometd.batch(function() {
				_unsubscribe();
				_subscribe();
			});
		}
		
		function _connectionBroken() {
			log('Connection to Server Broken');
		}
		
		var _wasConnected = false;
		var _connected = false;
		var _disconnecting;
		function _metaConnect(message) {
			if (_disconnecting) {
				_connected = false;
				log("connection closed");
			}
			else {
				_wasConnected = _connected;
				_connected = message.successful === true;
				if (!_wasConnected && _connected) {
					_connectionEstablished();
				}
				else 
					if (_wasConnected && !_connected) {
						_connectionBroken();
					}
			}
		}
	};
    
	/**
	 * 变更用户状态时，需要调用该方法，向服务器发送状态变化的请求，以便通知其他用户
	 * @param {String} userCd 当前用户的编号
	 * @param {int} online 在线状态值，目前值为1-在线，0-离线
	 */
    this.asyncChangeOnline = function(userCd, online) {
        publish('/service/changeOnlineStatus', {
            userCd : userCd,
            online : online
        });
    };
	
    /**
     * 功能: 取得字符串的长度(汉字的长度设为2，英文设为1)
     * @param msg - 发送消息
     */
    this.getMsgLength = function(msg){
    	return msg.replace(/[^\x00-\xff]/g,"**").length;
    };
    
	/**
	 * 发送私聊消息<br>
	 * 当前用户向其他的用户发送消息
	 * 
	 * @param {String} receiverNo 接收消息的用户编号
	 * @param {String} msg 消息内容
	 */
    this.send = function(receiverNo, msg) {

    	// add by huangbijin 20100302 begin
    	// 校验消息长度 
    	var msgLength = this.getMsgLength(msg);
    	if(MIN_MSG_LENGTH > msgLength){
    		return false;
    	}
    	if(MAX_MSG_LENGTH < msgLength){
    		alert("信息超长,最多允许2000个字符(或1000个汉字)");
    		return false;
    	}
    	// add by huangbijin 20100302 end
    	
    	try{
	        publish('/service/privateChat', {
	            receiverNo : receiverNo,
	            msg : msg
	        });
    	}catch(e){
    		alert(e);
    		
        	$("#chatting_panel .chatting_textarea").val(msg);
    	}
        
        return true;
    };
    
    /**
     * 发送群聊消息<br>
     * 当前用户向群组发送消息
     * 
     * @param {int} roomNo 接收消息的群组编号
     * @param {String} msg 消息内容
     */
    this.sendToRoom = function(roomNo, msg) {
        publish('/service/roomChat', {
            roomNo : roomNo,
            msg : msg
        });
    };
    
    /**
     * 发送请求，要求服务器提供在线人员列表
     */
    this.requestOnlineUserCds = function() {
        cometd.publish('/service/getOnlineUserList', {});
    };
    
    /**
     * 在请求服务器提供在线人员名单之后，使用该方法获取在线人员名单列表
     * @param {Array} userCds 用户userCd数组，每个元素对应一个userCd
     * @see requestOnlineUserCds 该方法为请求获得名单的方法
     */
    this.dealWithOnlineUserCds = function(userCds) {
        if (this.delegateDealWithOnlineUserCds &&
              typeof(this.delegateDealWithOnlineUserCds) == 'function') {
            this.delegateDealWithOnlineUserCds(userCds);
        }
    };
	
	
    
    function unsubscribeImpl() {
		log("unsubscribe start");
		if (_onlineStatusListener) {
			cometd.unsubscribe(_onlineStatusListener);
		}
		_onlineStatusListener = null;
		log("unsubscribe end");
		
		log("remove listeners start");
        if (_echoOnlineUserListListener) {
            cometd.removeListener(_echoOnlineUserListListener);
        }
        _echoOnlineUserListListener = null;
		if (_getLoginUserCdListener) {
			cometd.removeListener(_getLoginUserCdListener);
		}
		_getLoginUserCdListener = null;
		
		if (_recentContactsListener) {
			cometd.removeListener(_recentContactsListener);
		}
		_recentContactsListener = null;
		
		if (_echoPrivateChatListener) {
			cometd.removeListener(_echoPrivateChatListener);
		}
		_echoPrivateChatListener = null;
		
		if (_echoRoomChatListener) {
			cometd.removeListener(_echoRoomChatListener);
		}
		_echoRoomChatListener = null;
		
		if (_echoNewPrivateMsgListener) {
			cometd.removeListener(_echoNewPrivateMsgListener);
		}
		_echoNewPrivateMsgListener = null;
		
		if (_echoNewRoomMsgListener) {
			cometd.removeListener(_echoNewRoomMsgListener);
		}
		_echoNewRoomMsgListener = null;
		
		if (_echoRoomChangeListener) {
			cometd.removeListener(_echoRoomChangeListener);
		}
		_echoRoomChangeListener = null;
		
		if (_echoRoomMembersListener) {
			cometd.removeListener(_echoRoomMembersListener);
		}
		_echoRoomMembersListener = null;
		
		if (_echoUserOnlineListener) {
			cometd.removeListener(_echoUserOnlineListener);
		}
		_echoUserOnlineListener = null;
		log("remove listeners end");
	}
    
    function subscribeImpl() {
        log("准备登录 CometD");
        cometd.startBatch();
        
        // 订阅【其他人员在线状态】和【接收好友列表】、【验证登录】等频道
        subscribeChatChannel();
		
        cometd.publish('/service/login', {
            uiid : globalUserLoginName,
            online : 1,
            maxContactNum : MAX_CONTACT_NUM
        });
		
        cometd.endBatch();
        log("完成登录 CometD");
    }
	
	/**
     * 订阅这些服务<br>
     * 为从服务端到客户端发送消息的频道增加监听器<br>
     * 监听的频道有：
     * 私聊 - 新消息提示/消息获取
     * 群聊 - 新消息提示/消息获取
     * 群组变化更新
     * 
     */
    function subscribeChatChannel() {
        log("subscribeChatChannel func start");
        
        // 用户状态变化 广播频道 "/chat/onlineStatus"
        _onlineStatusListener = cometd.subscribe('/chat/onlineStatus', function(message) {
                    if (message != null && message.data != null) {
                        var userCd = message.data['userCd'];
                        var online = message.data['online'];
                        log('-----------------------------------------------<br>\n/chat/onlineStatus: userCd=' + userCd + ', online=' + online+'<br>\n-----------------------------------------------');
                        chatLeftUserlist.dealWithUserOnline(userCd, online);
                    }
                });
        log("subscribe - /chat/onlineStatus : " + _onlineStatusListener);
        
        // 获取所有在线状态的用户userCd 的服务频道 "/service/echoOnlineUserList"
        _echoOnlineUserListListener = cometd.addListener('/service/echoOnlineUserList', function(message) {
                    if (message != null && message.data != null) {
                        var usercdMaptoOnline = message.data;
                        if (usercdMaptoOnline != null && usercdMaptoOnline.length != null) {
                            chatuser.dealWithOnlineUserCds(usercdMaptoOnline);
                        }
                    }
                });
        log("subscribe - /service/echoOnlineUserList : " + _echoOnlineUserListListener);
        
        _getLoginUserCdListener = cometd.addListener('/service/getLoginUserCd', function(msg) {
            loginUserno = msg.data.userCd;
        });
        log("addListener - /service/getLoginUserCd : " + _getLoginUserCdListener);
        
        // 用户状态变化 广播频道 "/service/echoRecentContacts"
        _recentContactsListener = cometd.addListener(
                '/service/echoRecentContacts', function(message) {
                    if (message != null && message.data != null) {
                        log("/service/echoRecentContacts - html:"+message.data);
                        chatLeftUserlist.setRecentContacts(message.data);
                    }
                });
        log("addListener - recentContactsListener : " + _recentContactsListener);

        /*
         * 接收回显消息的频道 "/service/echoPrivateChat"
         * JSON格式： 
         * {
         *   senderNo:...,   发送人员编号
         *   senderName:..., 发送人员名称
         *   receiverNo:..., 接收人员编号
         *   msgtime:...,    发送时间
         *   msg:...,         消息内容
         *   echoback:...     消息是否为当前用户发给别人的回显消息
         * }
         */
        _echoPrivateChatListener = cometd.addListener(
                '/service/echoPrivateChat', function(message) {
                    if (message != null && message.data != null) {
                        var senderNo = message.data['senderNo'];
                        var receiverNo = message.data['receiverNo'];
                        log('/service/echoPrivateChat: senderNo=' + senderNo
                                + ', receiverNo=' + receiverNo);
                        chatLeftUserlist.showMsgText(null, senderNo,
                                receiverNo, message.data['msg'],
                                message.data['echoback']);
                    }
                });
        log("addListener - echoPrivateChatListener : " + _echoPrivateChatListener);

        _echoRoomChatListener = cometd.addListener('/service/echoRoomChat',
                function(message) {
                    if (message != null && message.data != null) {
                        var roomNo = message.data['roomNo'];
                        log('/service/echoRoomChat: roomNo: ' + roomNo + ', msg: ' + message.data['msg']);
                        var senderNo = message.data['senderNo'];
                        var receiverNo = message.data['receiverNo'];
                        roomLeftUserlist.showMsgText(roomNo, senderNo,
                                receiverNo, message.data['msg'],
                                message.data['echoback']);
                        if($("#chatting_room .chatting_friends_panel .newmsg").length <= 0){
                            var taskB = $("#msn_nav_btn b.task").remove();
                            delete taskB;
                            taskB = $("#room_nav_btn b.task").remove();
                            delete taskB;
                        }
                    }
                });
        log("addListener - echoRoomChatListener : " + _echoRoomChatListener);

        /*
         * 接收新消息提醒的频道 "/service/echoNewPrivateMsg" 
         * JSON格式： 
         * {
         *   senderNo:...,
         *   senderName:...
         * }
         */
        _echoNewPrivateMsgListener = cometd
                .addListener(
                        '/service/echoNewPrivateMsg',
                        function(message) {
                            if (message != null && message.data != null) {
                                var cnt = message.data.length;
                                if(cnt > 0){
                                    for ( var i = 0; i < cnt; i++) {
                                        var senderNo = message.data[i]['senderNo'];
                                        var senderName = message.data[i]['senderName'];
                                        log("/service/echoNewPrivateMsg - senderNo: " + senderNo + ", senderName: " + senderName);
                                        chatLeftUserlist.dealWithNewPrivateMsg(senderNo, senderName);
                                    }
                                } // cnt > 0
                            }
                        });
        log("addListener - echoNewPrivateMsgListener : " + _echoNewPrivateMsgListener);

        /*
         * 接收群组的新消息提醒的频道 "/service/echoNewRoomMsg" 
         * JSON格式： 
         * {
         *   roomNo:...,
         *   roomName:...
         * }
         */
        _echoNewRoomMsgListener = cometd.addListener('/service/echoNewRoomMsg -', function(message) {
            if (message != null && message.data != null) {
                var cnt = message.data.length;
                if(cnt>0){
                    try {
                        for ( var i = 0; i < cnt; i++) {
                            var roomNo = message.data[i]['roomNo'];
                            var roomName = message.data[i]['roomName'];
                            roomLeftUserlist.dealWithNewPrivateMsg(roomNo, roomName);
                        }
                    }catch(e) {
                        log("/service/echoNewRoomMsg : exception - " + e);
                    }
                }
            }
        });
        log("addListener - echoNewRoomMsgListener : " + _echoNewRoomMsgListener);

        /*
         * 获取群组的变化情况 - 群组的增加和删除
         * 如果当前人员从群组中被移除，则应该从当前人员的群组列表中将群组信息删除
         * 若当前人员被管理员加入到某个群组中，则应该将该群组添加到当前人员的群组列表中
         */
        _echoRoomChangeListener = cometd.addListener('/service/echoRoomChange', function(message) {
            if (message != null && message.data != null) {
                if (message.data.action != null) {
                    var action = message.data.action;
                    var roomNo = message.data.roomNo;
                    log('/service/echoRoomChange - action='+action+'  roomNo='+roomNo);
                    if('add' == action){
                        var roomName = message.data.roomName;
                        log('/service/echoRoomChange - roomName='+roomName);
                        roomchatter.addRoom(roomNo, roomName);
                    } else if('remove' == action){
                        roomchatter.removeRoom(roomNo);
                    } else if('update' == action){
                        var roomName = message.data.roomName;
                        var membersHTML = message.data.membersHTML;
                        log('/service/echoRoomChange - roomName='+roomName+'  membersHTML='+membersHTML);
                        roomchatter.updateRoom(roomNo, roomName, membersHTML);
                        
                        // 修改成功，则关闭窗口
                        if(roomNo == room.getRoomNo()){
                            room.hideRoomDIV();
                        }
                    } else if('init' == action){
                        var roomsHTML = message.data.roomsHTML;
                        log('/service/echoRoomChange - roomsHTML='+roomsHTML);
                        roomchatter.setRoomList(roomsHTML);
                    } else if('error' == action){
                        log('/service/echoRoomChange - error msg='+message.data.extdata);
                        room.showErrorLog(message.data.extdata);
                        room.hideSubmittingAnimation();
                    }
                }
            }
        });
        log("addListener - echoRoomChangeListener : " + _echoRoomChangeListener);
        
        /*
         * 显示群员列表
         * 该频道返回的json结构：
         * {
         *   roomNo:..,
         *   membersHTML:..
         * }
         * 其中 membersHTML 返回的HTML内容格式大致为：
         * <li>(<span id='orgCd_1'>企管部</span>)<span id='userCd_1'>Caesar</span></li>
         * <li>(<span id='orgCd_1'>企管部</span>)<span id='userCd_2'>Test</span></li>
         */
        _echoRoomMembersListener = cometd.addListener('/service/echoRoomMembers', function(message) {
            if (message != null && message.data != null) {
                roomchatter.updateRoomMembers(message.data.roomNo, message.data.membersHTML);
            }
        });
        log("addListener - echoRoomMembersListener : " + _echoRoomMembersListener);

        
        // 用户状态变化 单独通知频道 "/service/echoUserOnline"
        _echoUserOnlineListener = cometd.addListener(
                '/service/echoUserOnline', function(message) {
                    if (message != null && message.data != null) {
                        var userNo = message.data['userNo'];
                        var online = message.data['online'];
                        log('/service/echoUserOnline: userNo=' + userNo + ', online=' + online);
                        chatLeftUserlist.dealWithUserOnline(userNo, online);
                    }
                });
        log("addListener - echoUserOnlineListener : " + _echoUserOnlineListener);
        
        log("subscribeChatChannel func end");
    } // subscribeChatChannel func end
}

/*
 * 创建聊天对象管理器
 */
var chatuser = new chatuserbuilder();

function publish(channel, jsonParam) {
    cometd.startBatch();
    cometd.publish(channel, jsonParam);
    cometd.endBatch();
}

function outputMsg(message){
    for(key in message){
        var logtxt = key+' ';
        if(typeof(message[key]) == 'object'){
            logtxt += '<br>';
            for (subkey in message[key]) {
                logtxt += ('　　'+subkey+' '+message[key][subkey]+'<br>');
            }
        }else{
            logtxt += message[key];
        }
        log(logtxt);
    }
    log("<br>");
}
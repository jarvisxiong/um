/*
 * 
 * #webim_chat ul.leftside 下的某一个li对应的JQuery对象
 */

var partJqpathMainBottom = 'div.new_msg_panel';
/*
 * 聊天窗口对象，类型为div
 */
var partJqpathMsgText = 'div.chatting_content';

// 针对“离线提示面板”,定义显示到自动隐藏的时间间隔
var OFF_LINE_NOFITY_HIDDEN_TIME = 3000;//3000ms

/**
 * 聊天标签页管理器
 * @class 创建聊天标签页管理器
 * @param maindialogId
 * @param chatDlgId
 * @param builderFunc
 * 
 * <pre>
 *            特殊定制对象，结构：
 *            function() {
 *              this.getLiId = function{}();
 *              this.getVO = function(){};  - 获取标签对象对应的VO对象
 *              this.getId = function(){};  - 获取标签对象中包含的唯一标识信息
 *              this.getVoName = 
 *              this.getNewMsgSpanObj = function(){}; - 获取新消息动画SPAN标签中对应的VO对象,
 *                                              SPAN标签的结构：
 *                                              &lt;span id='...'&gt;&lt;span class='newmsg'&gt;&lt;/span&gt;&lt;input value='...'&gt;&lt;/input&gt;&lt;/span&gt;
 *                                              
 *              this.pos = function(chatDlgJQObject){}; - （可选）在显示窗口的时候对窗口进行重新定位
 *            }
 *            
 *            VO对象结构：
 *            {
 *              voId:...,
 *              voName:...,
 *              isPrivateChat:true|false
 *            }
 *            对应个人消息
 *              voId - senderNo, voName - senderName:...
 *            对应群组消息
 *              voId - roomNo, voName - roomName:...
 * </pre>
 * 
 * @return
 */
function ChatLeftSide(/*str*/maindialogId,/*str*/chatDlgId,/*function*/builderFunc) {
    var mainDlgId = maindialogId;
	/**
	 * 简易聊天窗口的JQuery定位器 - 指向聊天内容的DOM容器
	 */
    var _chattingPanelJQpath = '#chatting_panel';
	
	/**
	 * 聊天对象用户的名称显示的面板
	 */
    var _titlebarJQPath = _chattingPanelJQpath + ' .titlebar';
	
    var _multiDlgIdJQpath = '#'+chatDlgId;
	/**
     * 多人私聊(或群组讨论)窗口的JQuery定位器 - 指向聊天内容的DOM容器
	 */
    var _multiChatJQpath = _multiDlgIdJQpath + ' .chatting_window';
    
    var builder = new builderFunc();
    var jqpathMainBottom = "#" + chatDlgId + " " + partJqpathMainBottom;
    var jqpathMsgText = _chattingPanelJQpath + " " + partJqpathMsgText;
    /**
     * 正在对话 &lt;UL>
     */
    var _chattingTabJQPath = "#"+chatDlgId+" .talk_member_ul_cls";
    /**
     * 最近联系人 &lt;UL>
     */
    var _recentTabJQPath = "#talk_latest_div .talk_member_ul_cls";

	/**
	 * 聊天对象的编号与聊天内容的映射对象<br>
     * 在多人私聊窗口中，userCd 与 聊天内容相映射<br>
     * 在群组讨论窗口中，roomNo 与 聊天内容相映射
	 */
    var idMaptoMsgtext = {};

    var _isShowedChatMessageInput = false;
	/**
	 * 新消息提示标记SPAN
	 */
    var newmsgSPAN = "<span class='newmsg'></span>";
	/**
	 * 正在载入信息的提示动画
	 */
	var loadingSPAN = "<span class='loading'></span>";
    var self = this;
    
	
	function getEventLiObj(event){
		var target = event.target;
        if(target.tagName == 'LI'){
           return $(target);
        }else if($(target).attr("tagName")=='SPAN'){
           var liObj = $(target).parent();
           if(liObj.attr("tagName")=='LI'){
            return liObj;
           }
        }
		return null;
	}
	
    function mouseEnter(event){
		var liObj = getEventLiObj(event);
		
		if(liObj != null){
            liObj.find(".x_close").css("display","inline-block");	
		}
    }
    function mouseExit(event){
        var liObj = getEventLiObj(event);
        if(liObj != null){
           liObj.find(".x_close").css("display","none");
        }
    }
	
	function closeUserTab(event){
        var target = event.target;
        if(target.tagName == 'SPAN'){
            if($(target).hasClass("x_close")){
				var liID = $(target).parent("li").attr("id");
                removeChattingUser(builder.getId(liID));
            }
        }
	}
    
    this.getId = builder.getId;

    // public methods start
    
    this.getChattingUserno = function(){
        return getChattingUserno();
    };
	
    /**
     * 点击进行简易聊天窗口
     * 
     * @param userinfo
     *            聊天的用户信息，js对象数据结构如下：
     * 
     * <pre>
     * {
     *     userCd:...,
     *     userName:...,
     * }
     * </pre>
     */
    this.clickSimpleChat = function(userLI){
		newMsgSpanClick(userLI);
    };

    this.isSimpleChatShow = function(){
        return $('#address_nav').hasClass('active');
    };
    
    this.isSimpleChatUser = function(userCd){
        return findSimpleChatUser(userCd).length>0;
    };
    
    this.isMultiChatShow = function(){
        return $('#talk_nav').hasClass('active');
    };
    
    this.isMultiChattingUser = function(userCd){
        return findMultiChatUser(userCd).hasClass("selected");
    };
	
	/**
	 * 判断某个对话用户是否为当前正在聊天的对象
	 * @param {String} userCd 用户编号
	 */
	this.isChattingUser = function(userCd){
		return $(_chattingPanelJQpath + " .titlebar").find("input[value='"+userCd+"']").length>0;
	};
	
	this.findMultiChatUser = function(userCd){
		return findMultiChatUser(userCd);
	};
    
    this.isRoomChatShow = function(){
        return $('#room_nav_btn').hasClass('selected');
    };
    
    /**
     * 接收其他用户通过服务发送过来的消息
     * 
     * @param roomNo
     *            聊天室编号，如果不是群聊消息，则该参数值为null
     * @param fromUserno
     *            消息发送者的编号
     * @param toUserno
     *            消息接收者的编号
     * @param msgtext
     *            消息内容
     * @param echoback
     *            是否为回显消息，如果值为1，表明该消息来自当前用户，否则如果值为空或者0，则表示消息来自其他用户
     *            
     */
    this.showMsgText = function(roomNo, fromUserno, toUserno, msgtext, echoback) {
        // 判定当前聊天窗口显示的消息接收人的编号
        var dlgUserno = (roomNo!=null?roomNo:(echoback==1?toUserno:fromUserno));
        log('showMsgText - dlgUserno: ' + dlgUserno);
		log("this.isChattingUser(dlgUserno): "+this.isChattingUser(dlgUserno));
			  
        // 显示消息
        if(this.isChattingUser(dlgUserno)){
            switchUserTabSelected(dlgUserno, null, msgtext);
            if(echoback==1) {
                clearChatTextarea(_multiChatJQpath);
            }
			
			var dlgUserLI = $(_chattingTabJQPath).find(">li[id='" + builder.getLiId(dlgUserno) + "']");
			if(! dlgUserLI.hasClass("selected")){
	            dlgUserLI.siblings().removeClass("selected");
	            dlgUserLI.addClass("selected");	
			}
			
			setNewmsgClass(dlgUserno, false);
        }
        
		clearGettingMsgAnimation();
        appendMsgtextToMsgMapper(dlgUserno, msgtext);
    };
	
	/**
	 * 切换用户标签页对象<br>
	 * 删除原有标签页的选中样式，为当前聊天的用户标签页增加选中样式
	 * 
	 * @param {Object} dlgUserno 当前聊天用户的USER_CD
     * @param {String} dlgUsername 当前聊天对象的名称，当用户直接点击用户标签来显示聊天内容时，该参数为null
	 * @param {String} msgtext 附加的聊天信息
	 */
	function switchUserTabSelected(dlgUserno, dlgUsername, msgtext){
		var tabs = [_chattingTabJQPath, _recentTabJQPath];
		for (var i = 0; i < tabs.length; i++) {
			var friendsSPANs = $(tabs[i]).find("li");
			friendsSPANs.removeClass("selected");
			
			var chattingFriendSPAN = $(tabs[i]).find(">li[id='" + builder.getLiId(dlgUserno) + "']");
			if (chattingFriendSPAN.length > 0) {
				chattingFriendSPAN.addClass("selected");
			}
		}
		
		// 用户如果直接点击标签页对象，则用户名称无需再次显示在聊天标题栏上，所以该参数就会为null
		if(dlgUsername!=null){
			updateTitlebar(dlgUsername);
		}
        
        showChattingContentBySwitchChattingUser(dlgUserno, msgtext);
	}
	
	/**
     * 切换聊天对象时，显示选中人员之前的聊天记录
	 * 
	 * @param {Object} dlgUserno 聊天对象的用户编号，对于用户信息表中的字段为 USER_CD
     * @param {String} msgtext 附加的聊天信息
	 */
	function showChattingContentBySwitchChattingUser(dlgUserno, msgtext){
        clearChatDlg();
        appendToChatDlg(idMaptoMsgtext[dlgUserno], _chattingPanelJQpath+' div.chatting_content');
		if(msgtext!=null){
            appendToChatDlg(msgtext, _chattingPanelJQpath+' div.chatting_content');	
		}
        clearChatTextarea(_chattingPanelJQpath);
	};

    this.getDlgUserno = function(fromUserno, toUserno) {
        var dlgUserno = fromUserno;
        if (fromUserno == loginUserno) {
            dlgUserno = toUserno;
        }
        return dlgUserno;
    };

    /**
     * 请求获取新消息内容
     * 向服务器中发送请求，获取某个用户发送过来的消息内容
     * @param {Object} senderNo 发送消息的用户的编号，如果是私聊，则为userCd，如果是群聊，则为roomNo
     */
    this.asyncGetNewMsg = function(senderNo) {
        asyncGetNewMsg(senderNo);
    };
	
	/**
	 * 关闭正在聊天的用户窗口
	 */
    function removeChattingUser(userNo){
        var chattingUserSpan = $(_chattingTabJQPath).find("li[id='"+builder.getLiId(userNo)+"']");
//		selectAnotherUserSpan(chattingUserSpan);
		
        var selectedUserSpan = chattingUserSpan.remove();
        delete selectedUserSpan;
		var vo = builder.getVO(selectedUserSpan);
		delete idMaptoMsgtext[vo.voId];
		
        clearTitlebar();
        clearChatTextarea();
        clearChatDlg();
		
		hideChattingPanel();
		
        log("chattingUserSpan removed");
    };
	function hideChattingPanel(){
		$(_chattingPanelJQpath).hide();
	}
	
	/**
	 * 在标签页中选中指定标签对象之外的一个标签<br>
	 * 在标签页面板中，如果用户关闭某个标签页，调用该方法可以切换到另外一个标签，即将这个标签页设置为选中状态
	 * 
	 * @param {Object} chattingUserSpan 指定的标签对象
	 */
	function selectAnotherUserSpan(chattingUserSpan){
        var nextUserSpan = chattingUserSpan.next();
        log("selectAnotherUserSpan - next user span: "+nextUserSpan.find("input").val());
        if(nextUserSpan == null || nextUserSpan.length == 0) {
            nextUserSpan = chattingUserSpan.prev();
            log("prev user span: "+nextUserSpan.find("input").val());
        }
        if(nextUserSpan != null) {
            nextUserSpan.addClass("selected");
            log("selectAnotherUserSpan - other user span addClass .selected");
        }
	}
	
	/**
	 * 切换用户标签页<br>
	 * 当点击某个用户列表时，如果需要将被点击的用户信息添加到正在聊天的用户标签面板中，则调用该方法即可<br>
	 * 如果这个被点击的用户信息不存在，则为其创建用户标签；若存在该用户标签，则切换到该用户标签
	 * 
	 * @param {Object} senderNo 发送消息的用户编号
	 * @param {Object} senderName 发送消息的用户名称
	 */
	this.switchUserTab = function(senderNo, senderName){
		var usertabs = findChatUserTab(senderNo);
        // 存在该人员消息标签页
        if (usertabs != null) {
			switchUserTabSelected(senderNo, senderName);
		}
        // 不存在该人员消息标签页
        else{
            createNewMsgSpan(senderNo, senderName, false);
            switchUserTabSelected(senderNo, senderName);
        }
	};

	/**
	 * 当服务端有新消息传递给用户时，对新消息的发送用户信息进行处理<br>
	 * 如果有需要，则从服务端下载消息内容<br>
	 * 如果暂不需要，则只是在界面上予以提示<br>
	 * 
	 * @param {Object} senderNo 发送用户编号
	 * @param {Object} senderName 发送用户的名称
	 */
    this.dealWithNewPrivateMsg = function(senderNo, senderName){
        var usertabs = findChatUserTab(senderNo);
        // 存在该人员消息标签页
        if(usertabs != null) {
            // 正好该人员为当前正在聊天的对象，则获取新消息内容
            if (this.isChattingUser(senderNo)) {
                log("dealWithNewPrivateMsg - asyncGetNewMsg - senderNo: " + senderNo);
                asyncGetNewMsg(senderNo);
            } else {
                log("dealWithNewPrivateMsg - senderNo: " + senderNo + " 未处于正在聊天状态，增加新消息提示");
            }
        }
        // 不存在该人员消息标签页
		else{
            createNewMsgLI(senderNo, senderName, true, true);
		}
		setNewmsgClass(senderNo, true);
    };
	
	this.createNewMsgLI = function(senderNo, senderName, hasNewMsgFlagSpan, online){
		createNewMsgLI(senderNo, senderName, hasNewMsgFlagSpan, online);
	};
	
	/**
	 * 处理用户状态变化
	 * @param {String} receiverNo 用户编号，对应userCd
	 * @param {int} online 用户状态，值为0，则表示用户离线，值为1，则表示用户在线
	 */
	
	this.hiddenOffLineNotify = function(){
		$(_chattingPanelJQpath+" .offline_notify").hide();
	};
	
	this.dealWithUserOnline = function(receiverNo, online){
        log("dealWithUserOnline - receiverNo= "+receiverNo+", online="+online);
        log("dealWithUserOnline - this.isChattingUser(receiverNo)= "+this.isChattingUser(receiverNo));
		if(online != '1' && online != 1){
			if(this.isChattingUser(receiverNo)){
	            $(_chattingPanelJQpath+" .offline_notify").show();
	            setTimeout(this.hiddenOffLineNotify,OFF_LINE_NOFITY_HIDDEN_TIME);
	        }
		}
		setOnlineClass(receiverNo, online);
		
		if(this.delegateDealWithUserOnline && typeof(this.delegateDealWithUserOnline) == 'function'){
			this.delegateDealWithUserOnline(receiverNo, online);
	    }
	};
	
    
    this.setOnlineClass = function(userCd, online){
        setOnlineClass(userCd, online);
    };
    
    this.setNewmsgClass = function(userCd, hasNewMsgFlagSpan){
        setNewmsgClass(userCd, hasNewMsgFlagSpan);
    };
    this.setStateClass = function(newMsgUserLIObj, hasNewMsgFlagSpan, online){
        setStateClass(newMsgUserLIObj, hasNewMsgFlagSpan, online);
    };
	
	this.setRecentContacts = function(recentContactsHTML){
        $(_recentTabJQPath).empty();
        $(_recentTabJQPath).append(recentContactsHTML);
		$(_recentTabJQPath).find("li").click(function(){
			newMsgSpanClick($(this));
		});
	};
    
	var _notifyNewMsgComing = null;
	var _notifyNoNewMsgRemained = null;
    this.setNotifyNewMsgComing = function(notifyNewMsgComing) {
		_notifyNewMsgComing = notifyNewMsgComing;
	};
	this.setNotifyNoNewMsgRemained = function(notifyNoNewMsgRemained) {
		_notifyNoNewMsgRemained = notifyNoNewMsgRemained;
	};
	
    // public methods end

    // private methods start
    /**
     * 为消息标签增加动画提示，表明有新消息到来
     * 
     * @param 消息标签
     *            span 对象
     */
    function appendNewMsgFlagSpan(usertabs) {
        if(usertabs.find('span.newmsg').length == 0) {
            $(newmsgSPAN).insertBefore(usertabs.find('input'));
        }
    }
    
    /**
     * 判断某个人员的消息标签页是否存在于简易聊天窗口或者多人聊天窗口中<br>
     * 如果找到对应的用户标签对象，则返回该标签对象，若没有找到，则返回null
     * 
     * @return js对象结构：{usertab:...,parenttype:...}<br>
     *         usertab 为搜索到的用户消息标签对象 <br>
     *         <pre>
     *         parenttype 可取值有
     *             multitab - 用户消息标签页存放在多人聊天标签面板中, 
     *             floattab - 用户消息标签页存放在多人聊天浮动面板中,
     *             simpletab - 用户消息标签页处于简易聊天窗口中
     *         </pre>
     */
    function findChatUserTab(userno) {
        var chatDlgTab = findMultiChatUser(userno);
        if(chatDlgTab.length > 0) {
            return {usertab: chatDlgTab, parenttype:'multitab'};
        }
        log("findMultiChatUser none");
        return null;
    };
    
    function findSimpleChatUser(userCd) {
        return $(_chattingPanelJQpath+' .titlebar').find('input[value="'+userCd+'"]');
    }
    
    function findMultiChatUser(userCd) {
        return $(_chattingTabJQPath).find(">li[id='"+builder.getLiId(userCd)+"']");
    }
    
	/**
	 * 向应用服务发送请求，获取指定用户发送者传递过来的新消息
	 * @param {Object} senderNo 发送消息的用户编号
	 */
    function asyncGetNewMsg(senderNo) {
        builder.asyncGetNewMsg(senderNo);
    }

	/**
	 * 创建带有新消息提示的用户标签对象<br>
     * 该标签中包含一个新消息动画以及用户的userCd和用户名称
     * 
	 * @param {Object} animationId 用户的含有前缀的userCd，内容格式为：userno_3
	 * @param {Object} animationText 用户名称
	 * @param {Object} hasNewMsgFlagSpan 增加新消息动画提示的标志位，如果设置为true，则增加动画提示，否则不会有动画提示
	 * @param {int} online 在线状态, 1 - 表示在线， 0 - 表示离线
     * @return
	 */
    function newMsgUserLI(animationId, animationText, hasNewMsgFlagSpan, online) {
		log("newMsgUserLI - animationId: "+animationId+", animationText: "+animationText+", hasNewMsgFlagSpan: "+hasNewMsgFlagSpan);
		// 创建含有用户信息的<LI>
        var newMsgUserLIObj = $(document.createElement('li'));
        newMsgUserLIObj.attr("id", animationId);
        newMsgUserLIObj.append("<span class='width:18px;'></span>");
        newMsgUserLIObj.append("<span style='width:100px;'>"+ animationText + "</span>");//TODO:&nbsp;&nbsp;&nbsp;&nbsp;
		newMsgUserLIObj.append("<span class='x_close' style='width:18px;'></span>");
		newMsgUserLIObj.hover(mouseEnter,  mouseExit);
		newMsgUserLIObj.find(".x_close").click(closeUserTab);

        log('newMsgUserSPAN - add to dialog tabs');
		// 正在对话
        $(_chattingTabJQPath).append(newMsgUserLIObj);
		// 添加到最近联系人
		if ($(_recentTabJQPath).find("li[id='" + animationId + "']").length == 0) {
			if ($(_recentTabJQPath).find("li").length == 0) {
				$(_recentTabJQPath).append(newMsgUserLIObj.clone());
			}
			else {
				newMsgUserLIObj.clone().insertBefore($(_recentTabJQPath).find("li:first"));
			}
		}
        
        setStateClass(newMsgUserLIObj, hasNewMsgFlagSpan, online);
		
        log('newMsgUserSPAN - finished creating the user span object');
        newMsgUserLIObj.attr("alt", animationText);
        newMsgUserLIObj.attr("title", animationText);
        return newMsgUserLIObj;
    }
    
    function setOnlineClass(userCd, online){
        var newMsgUserLIObj = $(_chattingTabJQPath).find("li[id='" + builder.getLiId(userCd) + "']");
		setOnlineClassForUserLI(newMsgUserLIObj, online);
    }
        
    function setOnlineClassForUserLI(userLI, online){
		var firstNotifySpan = userLI.find("span:first");
		if (online == 1) {
			if (firstNotifySpan.hasClass("newmsg_offline")) {
				firstNotifySpan.removeClass();
				firstNotifySpan.addClass("newmsg_online");
			}
			else {
				firstNotifySpan.removeClass();
				firstNotifySpan.addClass("online");
			}
		}
		else {
			if (firstNotifySpan.hasClass("newmsg_online")) {
				firstNotifySpan.removeClass();
				firstNotifySpan.addClass("newmsg_offline");
			}
			else {
				firstNotifySpan.removeClass();
				firstNotifySpan.addClass("offline");
			}
		}
        syncUserLIStateInDiffTabs(userLI, firstNotifySpan.attr("class"));
    }
    
    function setNewmsgClass(userCd, hasNewMsgFlagSpan){
        var newMsgUserLIObj = $(_chattingTabJQPath).find("li[id='" + builder.getLiId(userCd) + "']");
        setNewmsgClassForUserLI(newMsgUserLIObj, hasNewMsgFlagSpan);
    }
    
    //根据userCd搜索机构用户树,获取用户在线状态
	//默认离线
	function findOnLineStautsByUserCd(userCd){
		
		var strOnLineStatus = '0';
		$(".dtree img[userCd='"+userCd+"']").each(function() {
			strOnLineStatus = $(this).attr("onlinestatus");
		}); 
		//alert("findOnLineStautsByUserCd["+userCd+"]:" + onlineStatus);
		return strOnLineStatus;
	};
    
    function setNewmsgClassForUserLI(userLI, hasNewMsgFlagSpan) {
		var firstNotifySpan = userLI.find("span:first");
		if (firstNotifySpan != null) {
			 
			var userCd = builder.getVO(userLI).voId;
			//alert(userCd);
			var strOnLineStatus = findOnLineStautsByUserCd(userCd);
			
			// 有新消息
			if (hasNewMsgFlagSpan) {
				if('0'==strOnLineStatus){
					firstNotifySpan.removeClass().addClass("newmsg_offline");
				}
				if('1'==strOnLineStatus){
					firstNotifySpan.removeClass().addClass("newmsg_online");
				}
                syncUserLIStateInDiffTabs(userLI, firstNotifySpan.attr("class"));
				notifyNewMsgComing();
			}
			// 无新消息
			else {

				if('0'==strOnLineStatus){
					firstNotifySpan.removeClass().addClass("offline");
				}
				if('1'==strOnLineStatus){
					firstNotifySpan.removeClass().addClass("online");
				}
                syncUserLIStateInDiffTabs(userLI, firstNotifySpan.attr("class"));
                checkNewMsgRemained();
			}
		}
	}
	
	function notifyNewMsgComing(){
		if(_notifyNewMsgComing && typeof(_notifyNewMsgComing) == 'function'){
			_notifyNewMsgComing();
		}
	}
	
	/**
	 * 检查是否还有未读消息
	 */
	function checkNewMsgRemained() {
		if ($(_chattingTabJQPath).find("li[id] span.newmsg_online").length == 0 &&
		$(_chattingTabJQPath).find("li[id] span.newmsg_offline").length == 0 &&
		$(_recentTabJQPath).find("li[id] span.newmsg_online").length == 0 &&
		$(_recentTabJQPath).find("li[id] span.newmsg_offline").length == 0) {
			if (_notifyNoNewMsgRemained && typeof(_notifyNoNewMsgRemained) == "function") {
				_notifyNoNewMsgRemained();
			}
		}
	}
	
	function syncUserLIStateInDiffTabs(userLI, firstNotifySpanClass) {
		if (userLI == null) {
			return;
		}
		var liID = userLI;
		if (userLI.attr != null && userLI.attr('id') != null) {
			var userVO = builder.getVO(userLI);
			liID = builder.getLiId(userVO.voId);
		}
		var tabJQs = [_chattingTabJQPath, _recentTabJQPath];
		for (var i = 0; i < tabJQs.length; i++) {
			var recentUserLIObj = $(tabJQs[i]).find("li[id='" + liID + "']");
			if (recentUserLIObj != null && recentUserLIObj.length > 0) {
				var recentUserFirstNotifySpan = recentUserLIObj.find("span:first");
				recentUserFirstNotifySpan.removeClass();
				recentUserFirstNotifySpan.addClass(firstNotifySpanClass);
			}
		}
		
		var userVO = builder.getVO(userLI); 
		
		//设置简易聊天窗口
		var titlbarUserFirstNotifySpan = $(_titlebarJQPath).find("span:first");
		var curUserCd  = userVO.voId;
		var chatUserCd = $(_titlebarJQPath).find("input:first").val();
		//alert("curUserCd :" + curUserCd);
		//alert("chatUserCd:" + chatUserCd);
		
		if( curUserCd == chatUserCd){
			titlbarUserFirstNotifySpan.removeClass().addClass(firstNotifySpanClass);
		}
		
	}
	
	/**
	 * 根据新消息和在线状态，更新用户的第一个提示&lt;span>对象的class
	 * @param {Object} newMsgUserLIObj 含有用户信息的&lt;LI>对象
	 * @param {Object} hasNewMsgFlagSpan 新消息标志位，设置为true，则表示有新消息，否则表示没有新消息
	 * @param {Object} online 用户在线状态标志位，值为1，表示用户在线，值为0，表示用户离线
	 */
	function setStateClass(newMsgUserLIObj, hasNewMsgFlagSpan, online) {
		setOnlineClassForUserLI(newMsgUserLIObj, online);
		setNewmsgClassForUserLI(newMsgUserLIObj, hasNewMsgFlagSpan);
	}
    
	/**
	 * 清除与某某对话的标题栏
	 */
    function clearTitlebar(){
        $(_chattingPanelJQpath + " .titlebar b").empty();
	}
	
    /**
     * 设置与某某对话的标题栏
     * @param {String} voname 对话人员的姓名
     */
    function updateTitlebar(voname) {
		clearTitlebar();
		//$(_chattingPanelJQpath + " .titlebar b").append(builder.getTitle(voname));
        //$(_chattingPanelJQpath + " .titlebar ").append(builder.getTitle(voname));
    }
    
    /**
     * 设置聊天对象(和xxx聊天)
     * 显示简易聊天窗口，将该窗口的聊天对象名称显示在窗口标题栏
     * 
     * @param userinfo
     * <pre>
     *            userCd - 聊天对象的用户编号
     *            userName - 聊天对象的用户名称
     *            online - 聊天用户的在线状态，值为1-在线，值为0-离线，该值可能因为用户传递的问题，为null
     * </pre>
     */
    function showSimpleChatDlg(userinfo) {
		$(_chattingPanelJQpath).show();
		var titlebar = $(_titlebarJQPath);
		titlebar.empty();
		if(userinfo && (userinfo.online != null || userinfo.online!= '') ){
			switch(userinfo.online){
				case 1:
					titlebar.append("<span class='online'></span>");
					break;
				case 0: 
					titlebar.append("<span class='offline'></span>");
					break;
				default:
					break;
			}
		} 
		titlebar.append('<span>与【' + userinfo.userName + '】聊天</span><input type="hidden" value="' + userinfo.userCd + '">');
		if (findMultiChatUser(userinfo.userCd).length > 0) {
			return;
		}
		$(_chattingPanelJQpath + ' .chatting_content').empty();
		//TODO: 不清空输入框内容
		//$(_chattingPanelJQpath + ' .chatting_textarea').val('');
		$(_chattingPanelJQpath + " .offline_notify").hide();
	}
    
    function showChatMessageInput() {
        if (_isShowedChatMessageInput) {
            return;
        }
        _isShowedChatMessageInput = true;
    }

    function clearChatTextarea(dlgId) {
        if(dlgId==null) {
            //$("#"+chatDlgId+" .chatting_textarea").val("");
        } else {
            //$("#"+dlgId+" .chatting_textarea").val("");
        }
    }

    /*
     * 将对方用户编号和对方用户所发来的消息，保存到映射对象之中，以便未来查看时使用
     */
    function appendMsgtextToMsgMapper(dlgUserno, msgtext) {
        log("appendMsgtextToMsgMapper: dlgUserno=" + dlgUserno + " msgtext=" + msgtext);
        if (idMaptoMsgtext[dlgUserno] == null) {
            idMaptoMsgtext[dlgUserno] = msgtext;
        } else {
            if(msgtext==null||msgtext=='') {
                return;
            }
            idMaptoMsgtext[dlgUserno] += msgtext;
        }
    }
    function clearChatDlg() {
        $(jqpathMsgText).empty();
    }

    function appendToChatDlg(msgtext, dlgChatWindowId) {
        if(dlgChatWindowId==null) {
            $(jqpathMsgText).append(msgtext);
            $(jqpathMsgText).attr("scrollTop", 100000000);
        } else {
            $(dlgChatWindowId).append(msgtext);
            $(dlgChatWindowId).attr("scrollTop", 100000000);
        }
    }
    
	/**
	 * 创建新消息标签<br>
	 * 该消息标签可能创建在标签栏，也可能出现在浮动窗口
	 * 
	 * @param {Object} senderNo 发送信息者的编号
	 * @param {Object} senderName 发送信息者的名称
	 */
	/**
     * 创建新消息标签<br>
     * 该消息标签可能创建在标签栏，也可能出现在浮动窗口
	 * 
     * @param {Object} senderNo 发送信息者的编号
     * @param {Object} senderName 发送信息者的名称
	 * @param {boolean} hasNewMsgFlagSpan 增加新消息动画提示的标志位，如果设置为true，则增加动画提示，否则不会有动画提示
	 * @param {int} online 用户在线状态标志位，值为1，表示用户在线，值为0，表示用户不在线
	 */
    function createNewMsgLI(senderNo, senderName, hasNewMsgFlagSpan, online){
        var newMsgSpan = newMsgUserLI(	builder.getLiId(senderNo), 
						        		senderName, 
						        		hasNewMsgFlagSpan, 
						        		online);
            
        newMsgSpan.click(function() {
			newMsgSpanClick($(this));
        }); // newMsgSpan click event listener
    }
	
	/**
	 * 用户标签点击事件
	 * @param {JQuery Object} userLI 用户标签对象 或者 用户信息对象
	 */
	function newMsgSpanClick(userLI) {
		if (userLI == null) {
			return;
		}
		// 用户信息
		var hasNewMsg = false;
		var userinfo = {};
		if (userLI.userCd != null && userLI.userName != null) {
			userinfo = userLI;
		}
		else {
			var spanObj = builder.getNewMsgSpanObj(userLI);
			
			userinfo['userCd'] = spanObj.voId;
			userinfo['userName'] = spanObj.voName;
			userinfo['online'] = spanObj.online;
			
			hasNewMsg = (userLI.find(".newmsg_online").length > 0) || (userLI.find(".newmsg_offline").length > 0);
		}
		
		// 对话框中显示用户信息
		showSimpleChatDlg(userinfo);
		
		// 设置聊天对象userCd
		setChattingUserno(userinfo['userCd']);
		
		// 若为新消息，则从服务器读取新消息内容
		if (hasNewMsg) {
			showGettingMsgAnimation();
			asyncGetNewMsg(userinfo['userCd']);
		}
		// 若没有新消息，则显示之前的聊天记录
		else {
			switchUserTabSelected(userinfo['userCd'], userinfo['userName']);
		}
	}
	
	/**
	 * 获取正在聊天的用户编号
	 */
    function getChattingUserno(){
        return $(_chattingPanelJQpath + " .titlebar").find("input").val();
    }
    
	/**
	 * 设置正在聊天的用户编号
	 * @param {Object} userno 用户编号
	 */
    function setChattingUserno(userno){
        $(_chattingPanelJQpath + " .titlebar").find("input").val(userno);
    }
    
	/**
	 * 显示正在获取信息的动画
	 */
    function showGettingMsgAnimation(){
		// 标题栏
		if($(_chattingPanelJQpath+" .titlebar").find(".loading").length<=0){
			//TODO
			//$(loadingSPAN).insertBefore(_chattingPanelJQpath+" .titlebar span");
		}
    }
    
	/**
	 * 隐藏获取信息的动画
	 */
    function clearGettingMsgAnimation(){
        //var loadingGIF = $(_chattingPanelJQpath+" .titlebar .loading").remove();
		//delete loadingGIF;
    }
    // private methods end
}
var _logged = true;
function log(text) {
	
	/*
    try {
        if($('#log').length > 0) {
            $('#log').append('<br/>' + text);
            $('#log').attr('scrollTop', 10000000);
        }else if(console != null && console.log != null) {
            console.log(text);
        }
        if(_logged) {
            return;
        }
    }catch(e) {
//        alert(e);
    }finally {
        _logged = false;   
    }
    */
    
}
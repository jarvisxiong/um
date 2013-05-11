var cometd = $.cometd;
var room = null;
var roomchatter = null;
$(function() {
    chatuser.setInitURL(hostpath + context + "/cometd");
    chatuser.initConnect();
    // 群组依赖于CometD的初始化，所以将群组的创建置于CometD之后
    room = new RoomManager() ;
    room.init({filterjsppath:hostpath+context+'/servlet/userinfo'});
    initPrivateAndRoomTab();
	
	roomchatter = new RoomChatter("#chatting_room", room);
	roomchatter.initRoomChatUI();

    // 简易会话窗口 - 按钮 - 发送消息
    $("#chatting_panel .chatting_send").click(function() {
        var msgtext = $("#chatting_panel .chatting_textarea").val();
        var toUserno = chatLeftUserlist.getChattingUserno();
        if(!toUserno){
        	alert("请选择会话对象...");
        	return;
        }
        var resultFlag = chatuser.send(toUserno, msgtext);
        if(resultFlag){
        	$("#chatting_panel .chatting_textarea").val("");
        }
    });

    // 简易私聊窗口 - 按钮  - 历史记录
    $("#chatting_panel .chatting_history").click(function(event) {
    	
    	/*
        alert("会话对象的userCd: "+chatLeftUserlist.getChattingUserno()
              +"\n当前用户userCd在服务端通过session来获取"
              +"\n搜索语句为"
              +"\nselect send_date, from_user_name, msg_text from user_message"
              +"\nwhere (from_user_cd = {当前用户userCd} and to_user_cd={会话对象的userCd})"
              +"\n    or (to_user_cd = {当前用户userCd} and from_user_cd={会话对象的userCd})"
              +"\norder by send_date");
         */
    	
    	var curUserNo = chatLeftUserlist.getChattingUserno();
    	//alert(curUserNo);
    	if(!curUserNo){
    		return;
    	}
    	var url = $("#userMessageUrl").val();
    	alert(curUserNo);
    	TabUtils.newTab("user_message_history","历史消息",url,true);
    });

    // 简易会话窗口 - 录入框 - 回车发送消息
    $("#chatting_panel .chatting_textarea").keyup(function(event) {
        if (event.keyCode == 13 && event.ctrlKey) {
            var msgtext = $(this).val();
            var toUserno = chatLeftUserlist.getChattingUserno();
            if(!toUserno){
            	alert("请选择会话对象...");
            	return;
            }
            var resultFlag = chatuser.send(toUserno, msgtext);
            if(resultFlag){
            	$("#chatting_panel .chatting_textarea").val("");
            }
            $(this).focus();
        }
    });

    // 群聊窗口 - 按钮 - 发送消息
    $("#chatting_room .chatting_send").click(function() {
        var msgtext = $("#chatting_room .chatting_textarea").val();
        var toRoomno = chatuser.getToRoomno();
        chatuser.sendToRoom(toRoomno, msgtext);
    });

    // 群聊窗口 - 录入框 - 回车发送消息
    $("#chatting_room .chatting_textarea")
            .keyup(
                    function(event) {
                        if (event.keyCode == 13 && event.ctrlKey) {
                            var msgtext = $(this).val();
                            var toRoomno = chatuser.getToRoomno();
                            log("#chatting_room .chatting_textarea keyup - toRoomno: " + toRoomno);
                            chatuser.sendToRoom(toRoomno, msgtext);
                            $(this).focus();
                        }
                    });

    // 群聊窗口 - 按钮  - 历史记录
    $("#chatting_room .chatting_history").click(function(event) {
        alert("所在群组的编号: "+chatuser.getToRoomno()
              +"\n当前用户userCd在服务端通过session来获取"
              +"\n搜索语句为"
              +"\nselect send_date, from_user_name, msg_text from room_message"
              +"\nwhere (from_user_cd = {当前用户userCd} and room_no={群组编号})"
              +"\n    or (to_user_cd = {当前用户userCd} and room_no={群组编号})"
              +"\norder by send_date");
    });

    // 正在对话
    $("#talk_talking_div .talk_member_ul_cls").click(function(event){
        clickUserLI(event);
    });

    // 最近联系人
    $("#talk_latest_div .talk_member_ul_cls").click(function(event){
        clickUserLI(event);
    });
	
	/*
	 * 对话模式标签选择
	 */
	$(".left_talk_options li").click(function(event){
		var id = $(this).attr("id");
		id = id.replace(/_nav$/,'_div');
		var userTabPanel = $("#left_talk_options_id div#"+id);
		userTabPanel.siblings().hide();
		userTabPanel.show();
	});
    
    $("#userlisttree li[id='roomlist']").click(function(event) {
        var target = event.target;
        if (isNotUserLink(target)) {
            return;
        }
        roomLeftUserlist.clickUser($(target).parent('li'));
    });
});

function clickUserLI(event){
	var targetObj = $(event.target);
    if(targetObj.attr("tagName") != 'LI'){
        targetObj = targetObj.parent();
    }
    if (targetObj.attr("tagName") == 'LI') {
        var clickedLI = targetObj;
        clickedLI.siblings().removeClass("selected");
        clickedLI.addClass("selected");
        chatLeftUserlist.clickSimpleChat(clickedLI);
    }
}

/**
 * 判断dom元素是否为包含有用户信息
 * @param domobj dom元素对象
 * @return 如果包含有用户信息，则返回true；否则返回false
 */
function isNotUserLink(/*dom obj*/domobj) {
    return domobj == null || domobj.tagName == null
            || domobj.tagName != 'A'
            || $(domobj).hasClass("department_node_t");
}

/*
 * 处理用户关闭窗口
 */
function handleUnload() {
//    cometd.reload();
}

var chatLeftUserlist = new ChatLeftSide("communication_panel",
        "talk_talking_div", function() {
            this.getLiId = function(voId) {
                return 'userno_' + voId;
            };
            this.getVO = function(liObj) {
                var isPrivateChat = false;
                if (liObj.attr('id').indexOf('userno_') >= 0) {
                    isPrivateChat = true;
                }
                var idstr = this.getId(liObj.attr('id'));
                var namestr = liObj.text().replace(/^\s+/, '').replace(/\s+$/,'');
                return {
                    voId : idstr,
                    voName : namestr,
                    isPrivateChat : isPrivateChat
                };
            };
            this.getId = function(idStr) {
                return idStr.replace(/^userno_/, '');
            };
            this.getVoName = function() {
                return 'username';
            };
            this.getNewMsgSpanObj = function(newMsgSpan) {
				var userOnlineStatus = 0;
				if (newMsgSpan.find("span.online").length > 0 || newMsgSpan.find("span.newmsg_online").length > 0) {
					userOnlineStatus = 1;
				}
				return {
					voId: this.getId(newMsgSpan.attr("id")),
					voName: newMsgSpan.text().replace(/^\s+/, '').replace(/\s+$/, ''),
					online: userOnlineStatus
				};
			};
            this.getTitle = function(voName) {
                return '与 ' + voName + ' 对话';
            };
            this.asyncGetNewMsg = function(senderNo) {
                log('this.getNewMsg - senderNo: ' + senderNo);
                publish('/service/getNewPrivateMsg', {
                    senderNo : senderNo
                });
            };
        });

var roomLeftUserlist = new ChatLeftSide("communication_panel", "chatting_room",
        function() {
            var roomnoMaptoMemberhtml = {};
            this.getLiId = function(voId) {
                return 'roomno_' + voId;
            };
            this.getVO = function(spanObj) {
                return {
                    voId : this.getId(spanObj.attr('id')),
                    voName : jqLIObj.find("span").find('input').val()
                };
            };
            this.getId = function(idStr) {
                return idStr.replace(/^roomno_/, '');
            };
            this.getNewMsgSpanObj = function(newMsgSpan) {
                return {
                    voId : this.getId(newMsgSpan.attr("id")),
                    voName : newMsgSpan.text().replace(/^\s+/,'').replace(/\s+$/,'')
                };
            };
            this.getTitle = function(voName) {
                return '群组 ' + voName + ' 会话';
            };
            this.asyncGetNewMsg = function(senderNo) {
                publish('/service/getNewRoomMsg', {
                    roomNo : senderNo
                });
            };
        });

/**
 * 初始化私聊、群聊的标签页
 * @return
 */
function initPrivateAndRoomTab() {
    var tab_cur = $("#chatting_private");
    $("#chatting_tabs div.btn").click(function() {
        if ($(this).hasClass("white")) {
            tab_cur.hide();
            $(this).removeClass("white").addClass("green").siblings().removeClass("green").addClass("white");
            var divId = $(this).children().attr("href");
            tab_cur = $("#" + divId).show();
        }
        return false;
    });
}
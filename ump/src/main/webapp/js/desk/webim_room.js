/**
 * 群组管理面板
 * @class 创建一个群组管理器
 */
function RoomManager() {
    var _notifyroomcreation = false;
    /**
     * 聊天群组的成员列表
     */
    var _roommemberJQPath = '#group .roompane .searchresult';
    var _searchnameInputJQPath = "#group .searchpane input[name='searchname']";
    var _roomnameInputJQPath = "#group .roompane input[name='roomname']";

    /**
     * 出错信息提示窗口的宽度
     */
    var errorDivWidth = 200;
    /**
     * 出错信息提示窗口的高度
     */
    var errorDivHeight = 30;

    function init() {
        createRoomDIV();
        //        doFilter();
        registerTreeAndListMouseEvent();
        clearInputs();
        createErrorTipDiv();

        // 取消并关闭"群组"配置窗口
        $("#group .bottombtnpane input[name='cancel']").click(function() {
            resetRoomDIV();
            hideRoomDIV();
        });
        // 重置群组信息
        $("#group .bottombtnpane input[name='resetroom']").click(function() {
            showRoomDIV();
        });
        // 创建群组
        $("#group .bottombtnpane input[name='createroom']").click(
            function() {
                if (hasNoneRoomMember()) {
                    showErrorLog('无法创建聊天室，因为目前还没有成员！');
                    return;
                }
                var roomnameInput = $(_roomnameInputJQPath);
                var roomname = roomnameInput.val();
                if (roomname == null
                        || roomname.replace(/^\s+/g, '').replace(/\s+$/g,
                                '') == '') {
                    showErrorLog('无法创建聊天室，因为群组没有名称！');
                    roomnameInput.focus();
                    return;
                }
                var roomUserLIs = $(_roommemberJQPath).find('li');
                var members = [];
                var deptnos = [];
                for ( var i = 0; i < roomUserLIs.length; i++) {
                    var roomUserLI = $(roomUserLIs[i]);
                    var userCd = roomUserLI.find("input[name='userCd']").val();
                    var userName = roomUserLI.find("input[name='userName']").val();
                    var orgCd = roomUserLI.find("input[name='orgCd']").val();
                    var orgName = roomUserLI.find("input[name='orgName']").val();
					var roomMember = {
                        userCd : userCd,
                        userName : userName,
                        orgCd : orgCd,
                        orgName : orgName
					};
                    members[members.length] = roomMember;
                }
				
				$("#group .msgpane").append("<span class='loading'></span>操作提交中...");
				$("#group [name='createroom']").attr("disabled", true);
				var actiontype = $("#group [name='actiontype']").val();
				if(actiontype == 'createroom'){
                    createRoom(roomname, members);
				}else if(actiontype == 'updateroom'){
					var roomno = getRoomNo();
					updateRoom(roomno, roomname, members);
				}
            }
		);
		
        // 初始化 - 注册搜索群的录入框回车键盘事件
        $(_searchnameInputJQPath).keyup(function(event) {
            if (event.keyCode == 13) {
                doFilter($(this).val());
            }
        });
        registerCometdService();
    }
	
	/**
	 * 获取当前群组操作面板中正在编辑的群组编号
	 * @return 返回群组编号
	 */
	function getRoomNo(){
		return parseInt($("#group [name='roomno']").val());
	}

    function registerCometdService() {
        if (!_notifyroomcreation) {
			/*
			 * 显示过滤的用户信息
			 * 
			 * msg.data
			 * json对象
			 * {
			 *   userName: ... ,
			 *   userTree: ...
			 * }
			 */
            var echoFilteredUserTreeListener =
			  cometd.addListener('/service/echoFilteredUserTree', function(msg) {
                if (msg != null && msg.data != null) {
                    if (msg.data.error) {
                        alert('some exceptions occurred during get user list ，error msg：' + msg.data.error);
                    } else {
						log('/service/echoFilteredUserTree - userTree: '+msg.data.userTree);
                        clearResultTree();
                        appendResultTree(msg.data.userTree);
                    }
                }
            });
			log("echoFilteredUserTreeListener is registered!");
			
			/*
			 * 显示群组名称以及群员信息
			 */
			var echoRoomInfoListener =
              cometd.addListener('/service/echoRoomInfo', function(msg) {
                if (msg != null && msg.data != null) {
                    if (msg.data.error) {
                        alert('some exceptions occurred during get room info，error msg：' + msg.data.error);
                    } else {
						msg.data['actiontype']='updateroom';
						showRoomDIV(msg.data);
                    }
                }
            });
            log("echoRoomInfoListener is registered!");
            _notifyroomcreation = true;
        }
    }

    /**
     * 初始化 - 注册左侧的单位人员树与右侧的人员信息列表的鼠标点击事件
     */
    function registerTreeAndListMouseEvent() {
        $("#browser").click(function(event) {
            if (event.target) {
                var userSPAN = $(event.target);
                if (isClickMemberInfo(userSPAN)) {
                    var userinfo = getMemberInfo(userSPAN);
                    addMember(userinfo);
                }
            }
        });

        $("#roomuser").click(function(event) {
            if (event.target) {
                var userSPAN = $(event.target);
                removeMember(userSPAN);
            }
        });
    }

    function createRoomDIV() {
        var roomdivhtml = '<div id="group" style="display:none;">'
                + '<div class="searchpane" >'
                + '<div>搜索：<input type="text"  name="searchname" style="display:none;"></input></div>'
                + '<div id="browser" class="searchresult source demo">'
                //                + '<ul id="browser" class="filetree treeview-famfamfam">'
                + '</div>'
                // <!-- group tree end -->
                + '</div>'
                + '<div class="roompane">'
                + '<div>群组名：<input type="text" name="roomname" style="display:none;"></input></div>'
                + '<div class="searchresult">'
                + '<ul id="roomuser">'
                + '</ul>'
                + '</div>'
                + '</div>'
                // <!-- roompane div end -->
                // <!-- bottom button panel start -->
                + '<div class="bottombtnpane" align="center">'
                + '<div class="msgpane">'
                + '</div>'
                + '<div class="ctrlbtnpane">'
                + '<input name="createroom" type="button" value="保存"></input>'
				+ '<input name="resetroom" type="button" value="重置"></input>'
				+ '<input name="cancel" type="button" value="取消"></input>'
                + '<input name="actiontype" type="hidden" value="createroom"></input>'
                + '<input name="roomno" type="hidden" value=""></input>'
                + '</div>'
                + '</div>'
                //<!-- bottom button panel end -->
                + '</div>';
        $('body').append(roomdivhtml);
    }

    function clearInputs() {
        $(_searchnameInputJQPath).val('');
        $(_roomnameInputJQPath).val('');
    }


    function doFilter(filterStr) {
		cometd.publish("/service/getFilteredUserTree", {
			userName: (filterStr ? filterStr : '')
		});
    }

    function resetRoomDIV() {
        $(_searchnameInputJQPath).val('');
        $(_roomnameInputJQPath).val('');
        $(_roommemberJQPath).find('ul').empty();
    }

	/**
	 * 
	 * @param {String} roomname 聊天群组名称
	 * @param {Object} members 群组成员信息数组，每个元素的json结构为
	 * <pre>
	 * {
     *   userCd:..., 用户编号
     *   userName:..., 用户名称
     *   orgCd:..., 公司编号
     *   orgName:... 公司名称
	 * }
	 * </pre>
	 */
    function createRoom(/*str*/roomName, /*Object*/members) {
        publish('/service/createRoom', {
            roomName : roomName,
            members : members
        });
    }
	
	/**
	 * 向服务器请求更新指定编号的群组
	 * @param {int} roomNo 群组编号
	 * @param {String} roomName 群组名称
	 * @param {Object}  members 群员列表，每个元素对应一个群员信息，其json结构为:
	 * <pre>
	 * {
     *   userCd: 人员编号,
     *   userName: 人员名称,
     *   orgCd: 组织编号,
     *   orgName: 组织名称,
	 * }
	 * </pre>
	 */
    function updateRoom(roomNo, roomName, members) {
        publish('/service/updateRoom', {
            roomNo : roomNo,
            roomName : roomName,
            members : members
        });
    }

    /**
     * 初始化 - 创建出错提示信息窗口
     */
    function createErrorTipDiv() {
        /*
            <div id="error_tip"
                style="width: 150px; height: 30px; position: absolute; background-color: blue; color: white;">
                <p>出错提示信息</p>
            </div>
        */
        var errordiv = $("<DIV ID='error_tip' style='display:none;'></DIV>").css( {
            width : errorDivWidth + 'px',
            height : errorDivHeight + 'px',
            position : 'absolute',
            backgroundColor : 'blue',
            color : 'white',
            opacity : '0'
        });
        $('body').append(errordiv);
    }

    function getRemoteTreeHTML(/*str*/filterStr) {
        // ajax calling for tree html
    }
    function clearResultTree() {
        $('#browser').empty();
    }
	/**
	 * 搜索得到的人员信息显示在群组信息操作面板中
	 * @param {Object} treeHTML
	 */
    function appendResultTree(treeHTML) {
        $("#browser").append(treeHTML);
    }
    function hasNoneRoomMember() {
        var roomMemberLen = $(_roommemberJQPath).find('li').length;
        return (roomMemberLen == null) || roomMemberLen <= 0;
    }

    /**
     * 根据用户信息创建用户的dom对象
     * @param {Object} userinfo 用户信息对象
     */
    function createMemberLI(userinfo) {
        var memberLI = document.createElement('li');
        $(memberLI).append(getLIText(userinfo.orgName, userinfo.userName))
                .append(getHiddenInput('userCd', userinfo.userCd))
                .append(getHiddenInput('orgCd', userinfo.orgCd))
                .append(getHiddenInput('userName', userinfo.userName))
                .append(getHiddenInput('orgName', userinfo.orgName));
        return $(memberLI);
    }

    function getLIText(deptname, username) {
        return "<span>(" + deptname + ")" + username + "</span>";
    }

    function getHiddenInput(inputName, val) {
        return "<input name='" + inputName + "' value='" + val
                + "' type='hidden'></input>";
    }

    function isAdded(userno) {
        return $(_roommemberJQPath).find(
                "li input[name='userCd'][value='" + userno + "']").length > 0;
    }

    function appendToMemberList(liObj) {
        $(_roommemberJQPath).find('ul').append(liObj);
    }

    function getDlgWidth() {
        return parseInt($('#group').css('width').replace(/px/, ''));
    }

    function getDlgHeight() {
        return parseInt($('#group').css('height').replace(/px/, ''));
    }

    function setPosition(x, y) {
        $('#group').css('position', 'absolute');
        $('#group').css('top', y + 'px');
        $('#group').css('left', x + 'px');
    }

    function showErrorLog(errtext) {
        $("#error_tip").empty();
        $("#error_tip").append('<p>' + errtext + '</p>');
        var grpOffset = $('#group').offset();
        var grpWidth = getDlgWidth();
        var grpHeight = getDlgHeight();
        $("#error_tip").css( {
            opacity : '0',
            left : grpOffset.left + (grpWidth - errorDivWidth) / 2 + 'px',
            top : grpOffset.top + (grpHeight - errorDivHeight) / 2 + 'px'
        });
        $("#error_tip").animate( {
            opacity : "1"
        }, {
            duration : 1000,
            queue : false
        }).animate( {
            width : errorDivWidth + "px"
        }, {
            duration : 1000,
            queue : true
        }).animate( {
            opacity : "0"
        }, {
            duration : 1000,
            queue : true
        });

    }

    function hideRoomDIV() {
        $("#group").hide();
		hideSubmittingAnimation();
    }
	
	function hideSubmittingAnimation() {
        $("#group .msgpane").empty();
        $("#group [name='createroom']").attr("disabled", false);
	}

    /**
     * 显示群组操作面板<br>
     * 根据用户提供的设置信息，显示群组名称、群员列表等
     * 
     * @param {Object} cfg json结构为：<pre>
     * {
     *   actiontype: 操作类型标志位，可选值有updateroom - 表示更新群组操作，createroom - 表示创建群组操作,
     *   roomNo : 群组编号,
     *   roomName : 群组名称,
     *   membersHTML : 群组成员列表
     * }
     * </pre>
     */
    function showRoomDIV(cfg) {
		resetRoomDIV();
        $("#group").show();
		setPosition(300, 100);
		
		//默认 创建群组
		$("#group [name='actiontype']").val('createroom');
		
		if(cfg!=null){
			if(cfg.actiontype){
				if('updateroom' == cfg.actiontype){
                    $("#group [name='actiontype']").val('updateroom');
					showRoomInfo(cfg);
                }
			}
		}
    }
	
	/**
	 * 显示群组名称以及成员列表在群组的编辑面板中
	 * @param {Object} roominfo 群组信息对象，数据结构为
	 * {
     *   roomNo : 群组编号,
     *   roomName : 群组名称,
     *   membersHTML : 群组成员列表
	 * }
	 */
	function showRoomInfo(roominfo){
        $("#group [name='roomname']").val(roominfo.roomName);
        $("#group [name='roomuser']").empty();
		setRoomMembersHTML(roominfo.membersHTML);
        $("#group [name='roomno']").val(roominfo.roomNo);
	}
    
    /**
     * 设置群组编辑面板中的群员列表，用于用户进一步<b>修改</b>群组之用
     * @param {String} membersHTML 群员列表
     * 由多个<LI>标签组成
     * 每一个群员的信息表示一个<LI>，其格式为：
     * <li>
     *    <span>(企管部)吴志敏</span>
     *    <input name='userCd' type='hidden' value='7'>
     *    <input name='orgCd' type='hidden' value='1'>
     *    <input name='userName' type='hidden' value='吴志敏'>
     *    <input name='orgName' type='hidden' value='企管部'>
     * </li>
     */
    function setRoomMembersHTML(membersHTML){
        $(_roommemberJQPath).find('ul').empty();
        $(_roommemberJQPath).find('ul').append(membersHTML);
    }

    function isClickMemberInfo(userSPAN) {
        return userSPAN.attr('id') == 'username';
    }
    /**
     * 获取被点击的span对应的群员信息对象<br>
     * 
     * @param userSPAN
     * 发生点击事件的html结构如下：
     * 
     * <pre>
     * &lt;li>&lt;span>测试部门&lt;/span>&lt;input name='deptno' type='hidden' value='a01'/>
     *   &lt;ul>
     *     &lt;li>&lt;span>测试用户1&lt;/span>&lt;input name='userno' type='hidden' value='001'/>&lt;/li>
     *   &lt;/ul>
     * &lt;/li>
     * </pre>
     * 
     * 只有当点击事件发生在<span>测试用户1</span>时，才执行该方法
     * 参数：
     *   userSPAN - 表示"<span>测试用户1</span>"的JQuery对象
     *   
     * 原理：
     * 通过获取 span 中的 text 来得知用户的 username 获取邻居的 input 中的 userno 来获知用户的 userno
     * 通过上一级的上一级的 li 中的 span 和 input 中的信息来获得 deptname 和 deptno
     * 
     * 
     * @return 获得的用户信息json结构为：
     * <pre>
     * {
     *   userCd:...,
     *   userName:...,
     *   orgCd:...,
     *   orgName:...
     * }
     * </pre>
     */
    function getMemberInfo(userSPAN) {
        var username = userSPAN.text();
        var parentLI = userSPAN.parent('li');
        var userno = parentLI.attr('id').replace(/^userno_/, '');
        var deptALink = parentLI.parent("ul").prev("a");
        var deptname = deptALink.text().replace(/^\s+/, '').replace(/\s+$/, '');
        var deptno = deptALink.attr('id').replace(/^deptno_/, '');
        return {
            userCd : userno,
            userName : username,
            orgCd : deptno,
            orgName : deptname
        };
    }
    /*
     * 新增选中用户到聊天群组之中
     * 
     * 参数： 
     * userinfo -
     * {
     *   userCd:..,
     *   userName:..,
     *   orgCd:..,
     *   orgName:..
     * }
     */
    function addMember(userinfo) {
        if (isAdded(userinfo.userCd)) {
            showErrorLog('用户【' + userinfo.userName + '】已经存在于该群之中');
            return;
        }
        var memberLI = createMemberLI(userinfo);
        appendToMemberList(memberLI);
    }

    function removeMember(userSPAN) {
        var memberLI = userSPAN.parent('li');
        var removedLI = memberLI.remove();
        delete removedLI;
    }

    /**
     * 初始化群组管理器
     * <pre>
     * 执行逻辑有：
	 * 创建群组设置界面
	 * 注册面板左侧（群组过滤搜索人员树）和右侧（群组成员列表）的鼠标点击事件
	 * 清空群组设置面板中录入框
	 * 创建出错信息提示窗口（用于提示群员已存在）
	 * 注册功能按钮（如重置、执行创建/修改、关闭）的点击事件
	 * 创建搜索文本框中的回车事件监听器
	 * 注册CometD的服务类监听器
	 * </pre>
     */
    this.init = function() {
        init();
    };
    
    /**
     * 获取当前群组操作面板中正在编辑的群组编号
     * @return 返回群组编号
     */
    this.getRoomNo = function(){
        return getRoomNo();
    };
	
	/**
	 * 显示出错提示信息
	 * @param {String} errtext 出错信息内容
	 */
	this.showErrorLog = function(errtext){
		showErrorLog(errtext);
	};
	
	/**
	 * 获取群组管理面板的宽度
	 */
    this.getDlgWidth = function() {
        return getDlgWidth();
    };
	/**
     * 获取群组管理面板的高度
	 */
    this.getDlgHeight = function() {
        return getDlgHeight();
    };
	/**
	 * 设置群组面板在网页中显示的绝对位置
	 * @param {Object} x 绝对位置的x轴坐标
	 * @param {Object} y 绝对位置的y轴坐标
	 */
    this.pos = function(x, y) {
        setPosition(x, y);
    };
	/**
     * 显示群组管理面板
     * 将面板放置在默认位置(x=300, y=300)
	 * 
	 * @param {Object} cfg
	 */
    this.showRoomDIV = function(cfg) {
        showRoomDIV(cfg);
    };
	/**
	 * 显示需要修改的群组信息
	 * @param {int} roomNo 待修改的群组编号
	 */
	this.asyncShowUpdateRoomDIV = function(roomNo){
		cometd.publish("/service/getRoomInfo",{roomNo: roomNo});
	};
	/**
	 * 向服务申请删除群组
	 * @param {Object} roomNo 群组编号
	 */
	this.asyncRemoveRoom = function(roomNo){
		cometd.publish("/service/removeRoom",{roomNo: roomNo});
	};
    /**
     * 隐藏群组管理面板
     */
    this.hideRoomDIV = function() {
        hideRoomDIV();
    };
	/**
	 * 判断用户点击的span对象是否对应人员的信息
	 * @param {Object} userSPAN 用户点击事件对应的dom元素
	 * @return 如果对应的为人员信息，则返回true；否则返回false
	 */
    this.isClickMemberInfo = function(userSPAN) {
        isClickMemberInfo(userSPAN);
    };
	/**
	 * 隐藏提交请求的提示动画
	 */
	this.hideSubmittingAnimation = function(){
		hideSubmittingAnimation();
	};
}

/**
 * 群聊消息面板
 * @class 创建群聊消息面板管理器
 * 
 * @param {String} roomChatterJQPath 群聊消息面板的JQuery选择器路径，比如id为 chatting_room 的 div，则传入参数应该为 #chatting_room
 * @param {RoomManager} roomManager
 */
function RoomChatter(roomChatterJQPath, roommanager){
	/**
	 * 【群号】与【群员列表HTML】的映射关系
	 */
	var _roomnoMaptoMembersHTML = {};
    var _roomChatterJQPath = roomChatterJQPath;
	var _roommanager = roommanager;
	/**
     * JQuery定位器 - 群组列表的DOM容器
	 */
	var _roomlistJQPath = roomChatterJQPath + " #room_list";
    /**
     * JQuery定位器 - 群员列表的DOM容器
     */
    var _roomMembersJQPath = roomChatterJQPath + " #member_list";
	/**
	 * JQuery定位器 - 群员列表所在面板的DOM容器
	 */
    var _roomMembersPaneJQPath = roomChatterJQPath + " #member_panel";
	
	/**
	 * 初始化群组聊天面板中的UI事件监听器
	 */
	this.initRoomChatUI = function(){
//	    initRoomListScrollbar();
	    initRoomButtonEvent();
		initRoomListMouseEvent();
		initRoomList();
	};
    
	/**
	 * 向服务发送创建群组的请求，得到创建成功的回应后
	 * 在界面中，向群组列表中增加新创建的群组信息，并隐藏群组创建面板
	 * 
	 * @param {int} roomNo 群组编号
	 * @param {String} roomName 群组名称
	 */
    this.addRoom = function(roomNo, roomName){
        $(_roomlistJQPath).append("<li id='roomno_"+roomNo+"'>"+roomName+"</li>");
		roommanager.hideRoomDIV();
    };
    
	/**
	 * 从列表中删除某个群组
	 * @param {int} roomNo 群组编号
	 */
    this.removeRoom = function(roomNo){
        var removedRoom = $(_roomlistJQPath).find("li[id='roomno_"+roomNo+"']").remove();
		delete removedRoom;
		log("删除标签页中的群组信息, roomNo=" + roomNo);
    };
    
	/**
	 * 更新列表中某个群组信息（包含群组名称和群员列表）
	 * 
	 * @param {int} roomNo 群组编号
	 * @param {String} roomName 更新后的群组名称
	 * @param {String} membersHTML 更新后的群组成员信息
	 */
    this.updateRoom = function(roomNo, roomName, membersHTML){
        var updateRoom = $(_roomlistJQPath).find("li[id='roomno_"+roomNo+"']");
        if(updateRoom.length>0){
            updateRoom.text(roomName);
        }else{
            this.addRoom(roomNo, roomName);
        }
		
        this.updateRoomMembers(roomNo, membersHTML);
    };
	
	/**
	 * 更新群组的成员列表
	 * @param {int} roomNo 群组编号
	 * @param {String} membersHTML 群员列表HTML
	 */
    this.updateRoomMembers = function(roomNo, membersHTML){
        if(isRoomSelected(roomNo)){
			updateRoomMembersHTML(membersHTML);
        }
        if(_roomnoMaptoMembersHTML[roomNo]!=null){
            delete _roomnoMaptoMembersHTML[roomNo];
        }
        _roomnoMaptoMembersHTML[roomNo] = membersHTML;
    };
	
	/**
	 * 清空并重新设置群组列表
	 * @param {String} roomsHTML 群组列表的HTML
	 */
	this.setRoomList = function(roomsHTML){
		$(_roomlistJQPath).empty();
		$(_roomlistJQPath).append(roomsHTML);
	};
    
	/**
	 * 更新群聊窗口中的群员列表<br>
	 * 当用户点击某个群组时，应该相应的显示其对应的群员列表
	 * @param {String} membersHTML 群员列表HTML，其结构大致为：
	 * <pre>
     * <li id='userno_12'>(测试部)测试用户1</li>
     * <li id='userno_12'>(测试部)测试用户2</li>
	 * </pre>
	 */
    function updateRoomMembersHTML(membersHTML){
        $(_roomMembersJQPath).empty();
        $(_roomMembersJQPath).append(membersHTML);
    }
    
    /**
     * 单击群组列表中的某个群，向服务发送请求，获取该群组的群员列表
     * 
     * @param {int} roomNo 用户点击的群组编号
     */
    function asyncGetRoomMembers(roomNo){
        if (isRoomMembersLoaded(roomNo)) {
            updateRoomMembersHTML(_roomnoMaptoMembersHTML[roomNo]);
        }else{
			cometd.publish("/service/getRoomMembers", {
                roomNo: roomNo
            });
		}
    }
	
	/**
	 * 判断指定编号的群组成员列表是否已经载入
	 * @param {int} roomNo 群组编号
	 * @return 如果群组成员列表已经载入，则返回true；否则返回false
	 */
	function isRoomMembersLoaded(roomNo){
		return _roomnoMaptoMembersHTML[roomNo] != null && _roomnoMaptoMembersHTML[roomNo] != '';
	}

	/**
	 * 初始化群组列表的滚动条
	 */
	function initRoomListScrollbar() {
	    var slideSpeed = 300;
	    // 群组列表滚动
	    var membersItemHeight = parseInt($("#member_department_tree li:first a:first").height());
	    membersItemHeight = membersItemHeight==null||isNaN(membersItemHeight)?50:membersItemHeight;
	    var membersOffsetHeight = $("#member_list_panel").get(0).offsetHeight;
	    var membersScrollHeight = membersOffsetHeight;
	    $("#room_slide_bar_up").click(function(event) {
	        slideContent("up", $("#room_list li:first"), membersScrollHeight, membersOffsetHeight, membersItemHeight*3, slideSpeed);
	    });
	    $("#room_slide_bar_down").click(function(event) {
	        slideContent("down", $("#room_list li:first"), membersScrollHeight, membersOffsetHeight, membersItemHeight*3, slideSpeed);
	    });
	    // 群组成员列表滚动
	    $("#member_slide_bar_up").click(function(event) {
	        slideContent("up", $("#member_list li:first"), membersScrollHeight, membersOffsetHeight, membersItemHeight*3, slideSpeed);
	    });
	    $("#member_slide_bar_down").click(function(event) {
	        slideContent("down", $("#member_list li:first"), membersScrollHeight, membersOffsetHeight, membersItemHeight*3, slideSpeed);
	    });
	}
	
	/**
	 * 初始化群组相关的按钮事件
	 */
	function initRoomButtonEvent() {
	    $(_roomChatterJQPath+" [name='createroom']").click(function(){
	        _roommanager.showRoomDIV();
	    });
	    $(_roomChatterJQPath+" [name='removeroom']").click(function(){
	        if(isAnyRoomSelected()){
				if(confirm("确定要删除所选群组?")){
                    _roommanager.asyncRemoveRoom(getSelectedRoomNo());
				}
			}else{
				alert("没有选中需要删除的群组！");
			}
	    });
	    $(_roomChatterJQPath+" [name='updateroom']").click(function(){
            if(isAnyRoomSelected()){
                _roommanager.asyncShowUpdateRoomDIV(getSelectedRoomNo());
            }else{
                alert("没有选中需要修改的群组！");
            }
	    });
	}
	
	function initRoomListMouseEvent(){
        $(_roomlistJQPath).click(function(event){
            var target = $(event.target);
            // 选择的是群组项目
            if(target.attr('id').indexOf("roomno")>=0){
                target.siblings().removeClass("selected");
                target.addClass("selected");
                var roomNo = parseInt(target.attr('id').replace(/^roomno_/,""));
                var roomName = target.text();
                updateMemberListTitle(roomName);
                asyncGetRoomMembers(roomNo);
            }
        });
        $(_roomlistJQPath).dblclick(function(event){
            var target = $(event.target);
            // 选择的是群组项目
            if(target.attr('id').indexOf("roomno")>=0){
                var roomNo = parseInt(target.attr('id').replace(/^roomno_/,""));
                var roomName = target.text();
                roomLeftUserlist.switchUserTab(roomNo, roomName);
				roomLeftUserlist.findMultiChatUser(roomNo).click();
            }
        });
	}
	
	/**
	 * 更新群员列表顶部的标题栏 - 表示当前正在查看的群员所属的群组名称
	 * @param {String} roomName
	 */
	function updateMemberListTitle(roomName){
		$(_roomMembersPaneJQPath).find("#roomName").text(roomName);
	}
	
    function initRoomList(){
		cometd.publish("/service/getRoomList",{});
	}
	
	/**
	 * 判断是否有群组被选中
	 * @return {boolean} 如果存在被选中的群组，则返回true；否则返回false
	 */
    function isAnyRoomSelected(){
        return getSelectedRoomNo() != null;
    }
	
	/**
	 * 判断某个群组是否被选中
	 * @param {Object} roomNo 群组编号
	 */
    function isRoomSelected(roomNo){
        return getSelectedRoomNo() == roomNo;
    }
	
	/**
	 * 获取被选中的群组编号
	 * @return {int} 返回被选中的群组编号，若没有群组被选中，则返回null
	 */
    function getSelectedRoomNo(){
		var selectedRoomDom = $(_roomlistJQPath).find(".selected");
		if(selectedRoomDom != null && selectedRoomDom.length == 1){
			return parseInt(selectedRoomDom.attr('id').replace(/^roomno_/,''));
		}
        return null;
    }
}

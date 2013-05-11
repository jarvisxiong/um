var CAN_GGXW_CHANGE = true;	// 是否能自动切换公告新闻
var ADDRESS_BOOK = "#addressBookList";	//通讯录对像
function returnHome(){
	window.location.href = _ctx+'/desk/desk!mainContainer.action';
}


//************* 切换内外邮件列表 *********/
var gListHeight = 200;
var gMailPageRows = 20; //计算邮件行数
var gTipLoaded = false; //是否
var gMailLoaded = false;//是否
var gTabTypeCd = false; //true-邮件 false-提醒

//人员管理模块
function doShowHrApprove(){
	var src = _ctx + "/plaspd/plas-user!hrApprove.action";
	TabUtils.newTab('211','人员管理', src, true); 
}
//提醒列表
function showTabMailInner(){
	replaceInnerMailHead();
}
function openMailInner(){
	TabUtils.newTab('131', '提醒', _ctx + '/oa/oa-email!main.action', true); 
}
function replaceInnerMailHead(){
	
	$('#desk_front_email_div').height(gListHeight);
	$('#mailHeadPanel').html($('#desk_front_email_div .innerMailHead').html());
	$('#mailHeadPanel div').css({'display':'block'});
	$('#desk_front_email_div').show();
	$('#desk_front_emailOut_div').hide();
	
	//resetInnerUnReadNumOut();
}


function showTabMailOut(){
	replaceOutMail();
}
function replaceOutMail(){

	$('#desk_front_emailOut_div').height(gListHeight);
	$('#desk_front_email_div').hide();
	$('#desk_front_emailOut_div').show();
	
	var pageNo = 1;
	if($('#curCoremailPageNo') && $('#curCoremailPageNo').val()!=''){
		pageNo = $('#curCoremailPageNo').val();
	}
	jumpPageToCoremail(pageNo);
	
	//resetOutUnReadNum();
}
//邮件标题-更新未读提醒数
function resetOutUnReadNum(){
	var num = $('#desk_front_email_div .innerMailHead .numOfNoRead').html();
	$('.outMailHead .numOfNoRead').each(function(){
		$(this).html(num);
	}); 
}

//查看邮件列表
function openWinMailOut(src){
	if($("#atten").length<=0)
	TabUtils.newTab('206', '邮件', src, true); 
}
//查看邮件明细
function showCmailDetail(server, sid, mid, fid){
	var src = server + '/coremail/XJS/index.jsp?go=read&'+sid+'&mid='+mid+'&fid='+fid;
	openWinMailOut(src);
}
//***************************************/
var attenNo=0;
//更新邮件记录
function refreshMails(pageNo) {
	if(refreshMails)
	
	var data = {
		    "pageNo":pageNo
			};
		//邮件
		$.post(_ctx + "/desk2/desk-new!email.action",data, function(result) {
		    //$("#coremailResultTip").removeClass('loading');
			$("#desk_new_email").html(result);
			//给提醒附加数值
			//$("#oaEmailNum").html("("+$("#remindNoReadEmailNum").val()+")");
			//$("#oaEmailNum").css("color","red");
			//$("#email").addClass("notice_link_selected").removeClass("notice_link");
			$("#remind").addClass("notice_link").removeClass("notice_link_selected");

			var pn = $("#pageOutEmailNo").val() ;
			$("#emailPage_"+pn).addClass("content_left_page_selected").removeClass("content_left_page");
			$("#emailPage_"+pn).siblings(".content_left_page_selected").addClass("content_left_page").removeClass("content_left_page_selected");
			
			//重新给邮件绑定事件
			$("#email").click(function(){
				openWinMailOut($("#toMail").val());
			});
		    //关注记录
			$.post(_ctx+"/desk2/desk-new!countAttention.action",function(r){
				if("0"==r){
					$("#attenDiv").css("cursor","auto");
				}else if(r.length<4){
					$("#attenDiv").bind("click",function(){
						refreshOaAttention('1');
					});
					$("#attentionCount").text("("+r+")");
					attenNo=r;
				}
			});
			
			
			////$("#emailPage").html("...").addClass("content_left_page").click(function(){
			//	openWinMailOut($("#toMail").val());
			//});
		});
		$("#email").css("color","#2D8BEF");
		$("#attenDiv").css("color","");
}

//提醒
function loadRemindEmail(pageNo){
	var data = {
			"pageNo":pageNo
			};
	$.post(_ctx + "/desk2/desk-new!remind.action",data, function(result) {
		$("#desk_new_email").html(result);
		$("#remind").addClass("notice_link_selected").removeClass("notice_link");
		$("#email").addClass("notice_link").removeClass("notice_link_selected");
		var pn = $("#pageRemindNo").val() ;
		$("#pageRemindNo_"+pn).addClass("content_left_page_selected").removeClass("content_left_page");
		$("#pageRemindNo_"+pn).siblings(".content_left_page_selected").addClass("content_left_page").removeClass("content_left_page_selected");
		//重新给提醒绑定事件
		$("#remind").click(function(){
			parent.TabUtils.newTab('131', '提醒',_ctx + '/oa/oa-email!main.action');
		});
	});
	
}

//写信
function writeMail(){
	parent.TabUtils.newTab('206', '写信','http://mail.powerlong.com/coremail/XJS/compose/main.jsp?sid=BASRiMZZTlojLhqUtTZZYAHyxpqHFTUp');
	//http://mail.powerlong.com/coremail/XJS/compose/main.jsp?sid=BASRiMZZTlojLhqUtTZZYAHyxpqHFTUp
}

//更新首页的关注提醒列表
function refreshOaAttention(page) {
	$("#desk_new_email").html('<div style="padding-left:8px;"><image src="'+_ctx+'/images/loading.gif"></div>');
	$("#attenDiv").css("color","#2D8BEF");
	$("#email").css("color","");
	$.post(_ctx + "/desk2/desk-new-schedule!oaAttention.action",{pageNo:page}, function(result) {
		$("#desk_new_email").html(result);
		if(attenNo<=9){
			$("#atten_2").hide();
			$("#atten_3").hide();
		}else if(attenNo<=19){
			$("#atten_3").hide();
		}
		$("#desk_new_email .content_left_page_selected").removeClass("content_left_page_selected").addClass("content_left_page");
		$("#atten_"+page).removeClass("content_left_page").addClass("content_left_page_selected");
	});
}

// 更新待办
function refreshJbpm(moduleCd,pageNo,selectCd) {
	var data = {
		"moduleCd":moduleCd,
		"pageNo":pageNo
	};
	$("#desk-new-schedule").html('<div style="padding-left:8px;"><image src="'+_ctx+'/images/loading.gif"></div>');
	$.post(_ctx + "/desk2/desk-new-schedule.action",data,function(result){
		$("#desk-new-schedule").html('');
		$("#desk-new-schedule").html(result);
		var pn = $("#pageNo").val() ;
		$("#Page_"+pn).addClass("content_left_page_selected").removeClass("content_left_page");
		$("#Page_"+pn).siblings(".content_left_page_selected").addClass("content_left_page").removeClass("content_left_page_selected");
		
		$("#desk-new-schedule").find("div[isline]").each(function(){
			if(navigator.userAgent.indexOf('8') > -1 && navigator.userAgent.indexOf('Trident/4.0') > -1)
			 {
				$(this).css("margin-top","4px");
			}else{
				$(this).css("margin-top","6px");
				
			}
		});
		$("#desk-new-schedule").find("div[iswait]").each(function(){
			//为了兼容ie8模式写的奇怪代码
			if($(this).attr("class") == "notice_link_selected" && navigator.userAgent.indexOf('8') > -1 && navigator.userAgent.indexOf('Trident/4.0') > -1)
			 {
				$(this).css("line-height","26px");
			}
			else if($(this).attr("isNum") && navigator.userAgent.indexOf('8') > -1 && navigator.userAgent.indexOf('Trident/4.0') > -1){
				$(this).css("line-height","26px");
			}
			else if(navigator.userAgent.indexOf('8') > -1 && navigator.userAgent.indexOf('Trident/4.0') > -1){
				$(this).css("line-height","30px");
			}
			else if($(this).attr("class") == "notice_link_selected"){
				$(this).css("line-height","30px");
				
			}
			else{
				$(this).css("line-height","30px");
			}
			$(this).click(function(){
				if($(this).attr("class") == "notice_link_selected"){
					setTimeout($("#more").attr("ispop"), 300);
				}
			});
		});
		//网批
		/*
		$("#waitArea").find("div[iswait]").each(function(){
			var w = $(this);
			var waitName = $(this).attr("iswait");
			var tempValue = $(this).attr("title");
			var num = $("#desk-new-schedule").find("[id="+waitName+"]");
			if(typeof num.attr("isNum") != "undefined"){
				tempValue = tempValue + "(" + num.attr("isNum") + ")";
				$(this).html(tempValue);
				$(this).css({"cursor":"pointer"});
				if(selectCd == waitName){
					$(this).removeClass().addClass("notice_link_selected");
				}else{
					$(this).removeClass().addClass("notice_link");
				}
				$(this).click(function(){
					refreshJbpm(waitName,'1',waitName);
					//$(this).removeClass().addClass("notice_link_selected");
					//alert($(this).attr("iswait"));
				});
			}
		});
		*/
	});
}
// 更新首页的公告列表
function refreshHomeNotifyList() {
	$.post(_ctx + "/desk2/desk-new!notice.action",function(result){
		$("#desk-new-news").html(result);
		$("#div_notice_show").show();
		$("#div_news_show").hide();
		$("#div_news").attr("class","notice_link");
		
		//切换到公告后增加点击事件
		$("#div_news").unbind().click(function(){
			refreshHomeNewsList();
		});
		$("#div_notice").unbind().bind("click",function(){
			if($(this).attr("class") == "notice_link_selected"){
				TabUtils.newTab('177', '公告', _ctx + '/desk/desk-notify.action', true);
			}
		});
		$("#div_notice").attr("class","notice_link_selected");
	});
}
// 更新首页的新闻列表
function refreshHomeNewsList() {
	$.post(_ctx + "/desk2/desk-new!news.action",function(result){
		$("#desk-new-news").html(result);
		$("#div_news_show").show();
		$("#div_notice_show").hide();
		$("#div_notice").attr("class","notice_link");
		
		//切换到新闻后增加点击事件
		$("#div_notice").unbind().bind("click",function(){
			refreshHomeNotifyList();
		});
		$("#div_news").unbind().click(function(){
			if($(this).attr("class") == "notice_link_selected"){
				TabUtils.newTab('142', '新闻', _ctx + '/desk/desk-news.action', true);
			}
			
		});
		$("#div_news").attr("class","notice_link_selected");
	});
}

//打开公告新闻的更多的按钮
function showGgxwMore(){
	var className =$("#div_notice").attr("class");
	if("notice_link_selected"==className){
		TabUtils.newTab('177', '公告', _ctx + '/desk/desk-notify.action', true);
	}else{
		TabUtils.newTab('142', '新闻', _ctx + '/desk/desk-news.action', true);
	}
} 

//定时更新会议(默认10分钟)
var newMeetingMgr;
function timernewMeeting(){
	newMeetingMgr= setInterval(function(){
		refreshHomeAttention();
	}, 50000);
}
//更新首页的每日会议提醒
function refreshHomeAttention() {
	$.post(_ctx + "/desk2/desk-new!meeting.action",function(result){
		$("#desk_new_meeting").html(result);
	});

}

function toPd(userName,sessionId){
	$("#login_name").val(userName);
	$("#sessionId").val(sessionId);
	$("#oldPdForm").submit();
}
function logout(doj){
	doj.src=_ctx+'/images/desk/X_heng_an.png';
	//ymPrompt.confirmInfo({message:'确认退出系统？',title:'退出',handler:function (tp){
		//if (tp=='ok'){
		//	logoff();
		//	window.location.href=_ctx+'/login!logout.action';
		//}else{
			// doj.src=_ctx+'/images/desk2/top_quit_normal.jpg';
		//}
	//}});
	logoff();
	window.location.href=_ctx+'/login!logout.action';
}
function logoff(){
	// 注销webim
	$("#btn_logout").click();
	// 注销PD
	// window.location.href=_ctx+'/login!reduceUser.action';
	$.post(_ctx+"/login!reduceUser.action", function(result) {
		// $("#divXialaMenu").html(result);;
	});
}
function showHelpMessage(){
	var showStr = "右上角<img src=_ctx+'/pics/home/top_help_press.jpg' />图标是PD管理平台所有功能的<br/><br/>帮助说明书，点击进入可供参考！谢谢！";
	setTimeout(function(){$.messager.show('友情提示', showStr, 0);}, 2000);
	return;
}


// 如果字符串超过指定长度，自动截断并用...代替后面的文本
function cutoffStr(str, num) {
	str = jQuery.trim(str);
	if(str.length <= num) {
		return str;
	} else {
		return str.substring(0, num) + "...";
	}
}
// 打开一条公告
function openNotify(url, notifyId, isReaded) {
	TabUtils.newTab("122","查看公告",url,true);
	// 如果当前公告是未读，则往后台发一条消息设置为已读
	if (isReaded == "-1") {
		$.post(_ctx + "/oa/oa-notify!readNotify.action", {id : notifyId});
	}
}
// 打开一条新的新闻或者公告时把new图标删掉
function removeNewImg(srcEle) {
	$(srcEle).siblings(".new_img").hide();
}

// 关闭浏览器时注销
function runOnBeforeUnload() { 
	logoff();
} 
// 点击向上或向下按钮时滚动菜单区域
var direction = null;
function startScrolling(dir) {
	direction = dir;
	scrollMenu();
}
function stopScrolling() {
	direction = null;
}
function openHelp(){
	window.open(_ctx+"/sso/help/login.jsp");
}
function openEas(){
	window.open(_ctx+"/sso/eas/login.jsp");
}
function openExchange(){
	// window.open(_ctx+"/sso/exchange/login.jsp");
	var url = _ctx+"/sso/exchange/login.jsp";
	TabUtils.newTab("178","外部邮件",url,true);
}
function openCtrip(){
	window.open("http://corporatetravel.ctrip.com/crpTravel/loginIndex.asp");
}
function isMacOs(){
	var os = window.navigator.userAgent; 
	var osVersion = os.split(";")[2]; 
	if(osVersion.indexOf("Mac")>-1){
		return true;
	}else{
		return false;
	}
}
// 刷新主页
function refreshHome(){
	if("homepage"==ACTIVE_TAB_ID){
		// 如果是在首页的div，才自动刷新
		refreshJbpm("","1");
		refreshMails("1");
	}
}
// 刷新更新通知
function refreshUpdateAlert(){
	// 刷新是否弹窗提示更新信息
	try{
		$.post(_ctx + "/desk/desk-email!updateAlert.action", null, function(result) {
			if(null!=result && "1"==result){
				var alertStr = "<div style='font-size:20px; margin:8px; font-weight:bolder;' class='color_blue'>系统更新通知：<br/>"
					+"&nbsp;&nbsp;&nbsp;&nbsp;因系统维护更新需要，PD系统将于10分钟内暂停使用。<br/>"
					+"&nbsp;&nbsp;&nbsp;&nbsp;请您尽快完成现有操作，并保存。<br/>"
					+"&nbsp;&nbsp;&nbsp;&nbsp;系统将于20分钟后恢复使用，届时请您重新登入系统。<br/>"
					+"&nbsp;&nbsp;&nbsp;&nbsp;给您带来不便之处，请见谅。</div>";
				$.messager.lays(600,300);
				$.messager.show("<span class='color_red'>系统提示：</span>", alertStr, 0);
			}else{
				// $.messager.close();
			}
		});
	}catch(e){}
}
var refreshNews = refreshHomeNewsList;
var jbpmMoudel={"travel":"出差审批","reimburse":"报销审批","ceomeeting":"指令单跟踪","specialtask":"专项任务","resApprove":"网上审批","speApprove":"特别审批","planTarget":"工作计划","planWorkCenter":"中心内部任务","oaFileFollowed":"文件跟踪","projectAcc":"项目","oaManInfo":"合理化建议","costCtrlPurBid":"成本工作管理","execPlan":"项目开发计划","mesMeetingInfo":"纪要系统"};
var jbpmMoudelMenuCd={"travel":"123","reimburse":"124","ceomeeting":"148","specialtask":"148","resApprove":"156","speApprove":"232","planTarget":"153","planWorkCenter":"138","oaFileFollowed":"132","projectAcc":"项目","oaManInfo":"139","costCtrlPurBid":"140","execPlan":"141","mesMeetingInfo":"213"};
// 全屏填充整个中间区域
function showAll(url, type) {
	TabUtils.newTab(jbpmMoudelMenuCd[type],jbpmMoudel[type],url,true);
	return false;
}
function showEmail(dom,id,emailBodyId){
	var $parent = $(dom).parent();
	$parent.removeClass('unReadEmail').find('.new_img').remove();
	
	var url = _ctx+"/oa/oa-email!main.action?oaEmailId="+id+"&id="+emailBodyId+"&boxId=5";
	TabUtils.newTab("131","提醒",url,true);
	// refreshMails();
}

// 打开审批任务模块的条目
function openTask(statusCd,id,taskId,type,deptCd){
	var url;
	switch (type) {
	case "ceomeeting" :
		url = _ctx + "/oa/oa-meeting.action?moduleCd=zc&id=" + id;
		break;
	case "specialtask" :
		url = _ctx + "/oa/special-task.action?id=" + id;
		break;
	case "reimburse":
	case "travel":
		if (statusCd==2 || statusCd==-1){
			url=_ctx+'/jbpm/jbpm-'+type+'-flow!input.action?id='+id+'&taskId='+taskId+"&isDesk=1";
		}else{
			url=_ctx+'/jbpm/jbpm-'+type+'-flow!view.action?id='+id+'&taskId='+taskId+"&isDesk=1";
		}
		break;
	case "resApprove":
		url=_ctx+'/res/res-approve-info.action?id='+id+'&moduleTypeCdSrh='+deptCd;
		if (deptCd == '2')type='speApprove';
		break;
	case "planTarget":
		url=_ctx+'/plan/plan-target!monEnter.action?currentCenterCd='+deptCd+'&opened_entityid='+ id;
		break;
	case "planWorkCenter":
		url=_ctx+'/plan/plan-work-center!getAllPlan.action?centerCd='+deptCd+'&opened_entityid='+ id;
		break;
	case "oaFileFollowed":
		url=_ctx+'/oa/oa-file-followed.action?id='+ id;
		break;
	case "oaManInfo":
		url=_ctx+'/oa/oa-man-info.action?id='+ id+'&proposalCatCd='+taskId;
		break;
	case "costCtrlPurBid":
		url=_ctx+'/plan/cost-ctrl-bid-purc.action?targetId='+ taskId+'&targetPageType='+statusCd;
		break;
	case "execPlan":
		url = _ctx + "/plan/exec-plan!getAllSuspendExecPlan.action";
		break;
	case "mesMeetingInfo":
		url = _ctx + '/mes/mes-meeting-info!index.action?reload-id='+id;
		break;
	}
	
	showAll(url,type);
}
// 截取字符长度
 function getSubStr (src,n) {
	var r = /[^\x00-\xff]/g;
	if (src.replace(r, "mm").length <= n)
		return src;
	n = n - 3;
	var m = Math.floor(n / 2);
	for ( var i = m; i < src.length; i++) {
		if (src.substr(0, i).replace(r, "mm").length >= n) {
			return src.substr(0, i) + "...";
		}
	}
	return src;
}
// 自动切换公告和新闻
function autoChangeGgxw(){
	if(CAN_GGXW_CHANGE){
		if("notify"==NOW_GGWX_MODULE){
			doGgxwChange("news");
		}else{
			doGgxwChange("notify");
		}
	}
}



function openHomeAttentionSp(id,opened_entityid){
	var opened_id_str = "";
	if(null!=opened_entityid){
		opened_id_str = "&opened_entityid="+opened_entityid;
	}
	TabUtils.newTab("133","每日会议提醒",_ctx+"/oa/oa-meeting-room-res!main.action?addrType="+opened_entityid+'&flag=true&id='+id,true);
	//showMeetingInfos(id,opened_entityid);
}

function openHomeChairman(id){
	TabUtils.newTab("133","总裁预约",_ctx+"/oa/oa-chairman-reserve!main.action?flag=true&id="+id,true);
}

function showMeetingInfos(id,addrType){
	ymPrompt.win({
		title:'会议室预定信息',
		fixPosition:true,
		width:550,
		height:480,
		showMask:false,
		iframe:{id:'showMeetingRoomIframe',name:'showMeetingRoomIframe',src:_ctx+'/oa/oa-meeting-room-res!look.action?id='+id+'&addrType='+addrType+'&flag=true'}
	});
}
function showMsgDlg(data){
	ymPrompt.win({message:data.content,title:data.title,width:400,height:250});
}

//菜单鼠标移动事件

function menuMouseOver(obj,url){
	/*selectedObj = $(obj);
	var childObj = $("#child_"+selectedObj.attr("id"));
	//只加载一次
	if(selectedObj.attr("id") == 'book' && $(ADDRESS_BOOK).attr("addressShow") == "false"){
		//加载机构树
		loadWabTree();
		$(ADDRESS_BOOK).attr("addressShow","true");
	}
	
	//鼠标移动到菜单上则清除显示和隐藏timeout事件
	if(addressShowMgr){
		clearTimeout(addressShowMgr);
	}
	if(addressHideMrg){
		clearTimeout(addressHideMrg);
	}
	
	

	addressShowMgr = setTimeout(function(){
		$(ADDRESS_BOOK).show();
		},300);
	
	//限制只获取一次子菜单数据
	if(childObj.html() == '' && (selectedObj.attr("id") != 'book') ){
		$.post(url,{}, function(result) {
			childObj.html(result);
			childObj.show();
			childObj.siblings("[oi!='nohide']").hide();
			});
	}

	//除通讯录外改变框宽度
	if(selectedObj.attr("id") != 'book'){
		$(ADDRESS_BOOK).css("width","254px");
		$(ADDRESS_BOOK).css("background-position","250px center");
	}else{
		$(ADDRESS_BOOK).css("width","398px");
		$(ADDRESS_BOOK).css("background-position","344px center");
	}
	
	if(bookShowMgr){
		clearTimeout(bookShowMgr);
	}
	bookShowMgr = setTimeout(function(){
		childObj.show();
		childObj.siblings("[oi!='nohide']").hide();
		},1);


	selectedObj.mouseleave(function(){
		if(bookHideMgr){
			clearTimeout(bookHideMgr);
		}
		
		bookHideMgr = setTimeout(function(){
			childObj.hide();
			},300);
		if(addressHideMrg){
			clearTimeout(addressHideMrg);
		}
		addressHideMrg = setTimeout(function(){
			$(ADDRESS_BOOK).hide();
			},300);
		});

	selectedObj.mouseout(function(){
		$(this).css("background-color","");
	});
	*/
}

//切换便签通知
function showNote(){
	$("#note_show").hide();
	$("#pd_notice").show();
}
//鼠标离开子菜单时关闭
function closebookTimeout(obj){
	alert(121121);
}

//菜单延时by wubo
var Timer=null;
function doDelayTimeClick(obj,url){
	clearTimeout(Timer);
	Timer=setTimeout(function(){doClickBook(obj,url);},500);
}
//菜单点击事件
function doClickBook(obj,url){
	
	$("#bookClickMask").show();
	selectedObj = $(obj);

	//如果已经打开菜单则关闭
	if(selectedObj.attr("isCheck") == 'true'){
		closeBook();
		return;
	}
	var childObj = $("#child_"+selectedObj.attr("id"));
	//只加载一次
	if($(ADDRESS_BOOK).attr("addressShow") == "false"){
		//加载机构树
		loadWabTree();
		$(ADDRESS_BOOK).attr("addressShow","true");
	}
	//$('#addressBookList').toggle();
	selectedObj.css("background-color","#2d8bef").css("color","#FFFFFF").attr("isCheck","true");
	selectedObj.siblings().css("background-color","").attr("isCheck","false");
	
	if($(ADDRESS_BOOK).attr("display") == "none"){
		$(ADDRESS_BOOK).show();
	}else{
		$(ADDRESS_BOOK).show();
		
	}
	//限制只获取一次子菜单数据
	/*if(childObj.html() == '' && (selectedObj.attr("id") != 'book') ){
		$.post(url,{}, function(result) {
			childObj.html(result);
			childObj.show();
			childObj.siblings("[oi!='nohide']").hide();
			});
	}
	*/
	var offsetTop = $(obj).offset().top;
	
	var objId = selectedObj.attr("id");
	
	//除通讯录外改变框宽度
	if( objId == 'book'){
		$(ADDRESS_BOOK).css("background-position","344px center");
		$(ADDRESS_BOOK).css({"width":"450px", "max-width": "450px"});//这里留意通讯录的宽度比较大.
		$(ADDRESS_BOOK).css({position: "absolute",top:offsetTop});
		$(ADDRESS_BOOK).css("height","590px");
	}else{
		//重新定位
		var height = childObj.height();
		var hCenter = height/2;
		if(((height/2) - offsetTop) <0 ){
			hCenter = offsetTop - (height/2) + 20;
		}else{
			//alert((height/2)+ ":::" + offsetTop );
			hCenter = "0px";
		}
		$(ADDRESS_BOOK).css({"width":"254px", "max-width": "254px"});
		$(ADDRESS_BOOK).css("background-position","250px center");
		$(ADDRESS_BOOK).css({position: "absolute",top:hCenter, height:height});
	}
	
	childObj.show();
	childObj.siblings("[oi!='nohide']").hide();
	$("#searched_user").select(0, $("#searched_user").text().Length);
	//$("#searched_user").focus();
}

//关闭左边窗口
function closeBook(){
	$(ADDRESS_BOOK).hide();
	$("#divXialaMenu").find("[isCheck='true']").css("background-color","").attr("isCheck","false");
	$('#bookClickMask').hide();
	
}
function openPlanTarget(){
	TabUtils.newTab('153', '总部工作计划', _ctx+'/plan/plan-target!monEnter.action', true); 
}

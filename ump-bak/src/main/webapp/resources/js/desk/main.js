var HOME_EMAIL_PAGESIZE = 7;	// 首页邮件的pageSize
var HOME_JBPM_PAGESIZE = 7;	// 首页审批的pageSize
var HOME_XWGG_PAGESIZE = 5;	// 首页新闻公告的pageSize
var CAN_GGXW_CHANGE = true;	// 是否能自动切换公告新闻
var daiban_sort_num = Number(0);	// 待办事项的类数，用来控制待办事项div的高度和显示
var attention_sort_num = Number(0);	// 关注事项的类数，用来控制关注事项div的高度和显示
var now_in_daiban_or_attention = "";	// 现在在待办还是在关注
var before_in_daiban_or_attention = "";	// 之前s在待办还是在关注
var page_no_attention = 1;	// 现在在首页关注列表的第几页
var page_all_attention = 1;	// 首页关注列表的的总页数
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
//提醒标题-更新未读邮件数
function resetInnerUnReadNumOut(){
	var num = $('#desk_front_emailOut_div .outMailHead .numOfNoReadOut').html();
	$('.innerMailHead .numOfNoReadOut').each(function(){
		$(this).html(num);
	});
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

//@param {String} pageNo 第pageNo页(从1开始)
//@param {String} rows 每页记录数

function jumpPageToCoremail(pageNo){
	var rows = gMailPageRows;
	//$("#coremailResultTip").addClass('loading');
	$.post(_ctx + "/desk/coremail.action",{pageNo: pageNo, rows:rows}, function(result) {
		//$("#coremailResultTip").removeClass('loading');
		$("#desk_front_emailOut_div").html(result);
	});
}
//查看邮件列表
function openWinMailOut(src){
	TabUtils.newTab('206', '邮件', src, true);
}
//查看邮件明细
function showCmailDetail(server, sid, mid, fid){
	// liangwr 2011-08-18
	//生产
	//var src  =/coremail/XJS/index.jsp?go=read&sid=${sid}&mid=${mid}&fid=${fid}
	//var src = 'http://mail.powerlong.com/coremail/XJS/index.jsp?go=read&'+sid+'&mid='+mid+'&fid='+fid;
	var src = server + '/coremail/XJS/index.jsp?go=read&'+sid+'&mid='+mid+'&fid='+fid;
	//本地
	//var src = 'http://192.168.169.121:80/coremail/XJS/index.jsp?go=read&'+sid+'&mid='+mid+'&fid='+fid;
	//var src = 'http://192.168.169.139:80/coremail/XJS/index.jsp?go=read&'+sid+'&mid='+mid+'&fid='+fid;

	//var src = 'http://mail1.powerlong.com/coremail/XJS/mbox/viewmail.jsp?mid='+mid+'&'+sid;
	//var src = 'http://mail1.powerlong.com/coremail/XJS/index.jsp?go=VIEW&mid='+mid+'&'+sid;
	openWinMailOut(src);
}
//***************************************/

//更新邮件记录
function refreshMails() {

	gListHeight = $('#mailBodyPanel').height();
	//alert(gListHeight);

	//计算记录数
	gMailPageRows = parseInt(($('#mailBodyPanel').height() - 60)/24);


	var rows = gMailPageRows;
	$.post(_ctx + "/desk/coremail.action",{pageNo: 1, rows:rows}, function(result) {
		$("#desk_front_emailOut_div").html(result);
		$.post(_ctx + "/desk/desk-email!email.action",{"deskHomePager.pageSize":HOME_EMAIL_PAGESIZE}, function(result) {
			$("#desk_front_email_div").html(result);
		});
	});
}
// 更新审批记录
function refreshJbpm() {
	$.post(_ctx + "/desk/jbpm-task.action", {"deskHomePager.pageSize":HOME_JBPM_PAGESIZE}, function(result) {
		$("#desk_front_jbpm_div").html(result);
	});
}
// 更新首页的公告列表
function refreshHomeNotifyList() {
	$.post(_ctx + "/desk/desk-notify!frontPageNotify.action",{"deskHomePager.pageSize":HOME_XWGG_PAGESIZE}, function(result) {
		$("#desk_front_notify_div").html(result);
	});
}
// 更新首页的新闻列表
function refreshHomeNewsList() {
	$.post(_ctx + "/desk/desk-news!frontPageNews.action",{"deskHomePager.pageSize":HOME_XWGG_PAGESIZE}, function(result) {
		$("#desk_front_news_div").html(result);
	});
}
// 更新首页的关注提醒列表
//function refreshHomeAttention() {
//	$.post(_ctx + "/oa/oa-all-attention!forHome.action",null, function(result) {
//		$("#attention_ajax_div").html(result);
//	});
//}
//更新首页的每日会议提醒
function refreshHomeAttention() {
	$.post(_ctx + "/plan/exec-plan-extend!forHome.action",null, function(result) {
		$("#attention_ajax_div").html(result);
	});
}

function toPd(userName,sessionId){
	$("#login_name").val(userName);
	$("#sessionId").val(sessionId);
	$("#oldPdForm").submit();
}
function logout(doj){
	doj.src=_ctx+'/images/desk/X_heng_an.png';
	ymPrompt.confirmInfo({message:'确认退出系统？',title:'退出',handler:function (tp){
		if (tp=='ok'){
			logoff();
			window.location.href=_ctx+'/login!logout.action';
		}else{
			// doj.src=_ctx+'/images/desk2/top_quit_normal.jpg';
		}
	}});
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
function showLoginMsg(userName,lastLoginIp,lastLoginTime){
	try{
		$.post("http://int.dpool.sina.com.cn/iplookup/iplookup.php", {format:'js',ip:lastLoginIp}, function(result) {
			try{
				var showStr = '上次登录:'+lastLoginIp;
				var loginPlace = "";
				if(!remote_ip_info.country){
					loginPlace = '内网地址';
				}else{
					if(remote_ip_info.province == remote_ip_info.city){
						loginPlace = remote_ip_info.country+'&nbsp;'+remote_ip_info.province;
					}else{
						loginPlace = remote_ip_info.country+'&nbsp;'+remote_ip_info.province+'&nbsp;'+remote_ip_info.city;
					}
				}
				loginPlace = lastLoginIp?loginPlace:'';
				showStr =showStr+ '('+loginPlace+')&nbsp;'+lastLoginTime;
				$('#lastLoginInfoId').html(showStr);
			}catch(e){}
		});
	}catch(e){}
}
function showHelpMessage(){
	var showStr = "右上角<img src=_ctx+'/pics/home/top_help_press.jpg' />图标是PD管理平台所有功能的<br/><br/>帮助说明书，点击进入可供参考！谢谢！";
	setTimeout(function(){$.messager.show('友情提示', showStr, 0);}, 2000);
	return;
}
function ask(){
	var isAsked=getCookieValue("isAsked_YB");
	if (isAsked!='1'){
	ymPrompt.confirmInfo({message:'企业文化活动员工意见调查',title:'问卷调查',okTxt:"参与调查",cancelTxt:"以后再说",handler:function (tp){
		if (tp=='ok'){
			// 注销webim
			window.open('http://www.askform.cn/77233-97701.aspx');
			setCookie("isAsked_HR","1",24,"/");
			// window.location.href=_ctx+'/ask.html';
		}else{
			// doj.src=_ctx+'/images/desk2/top_quit_normal.jpg';
		}
	}});
	}
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
// window.open(url);
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
	// desk-org-user.jsp
	// $("#btn_logout").click();
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
// 从别的标签也切换到首页时更新整个首页的内容
// function refreshWholeHome() {
// if (ACTIVE_TAB_ID=="homepage_tab") {
// return;
// }
// var event = {data:{tabId:"homepage",src:""}};
// TabUtils.showTab(event);
// refreshHome();
// }
// 刷新主页
function refreshHome(){
	if("homepage"==ACTIVE_TAB_ID){
		// 如果是在首页的div，才自动刷新
		refreshJbpm();
		refreshMails();
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
var jbpmMoudel={"travel":"出差审批","reimburse":"报销审批","ceomeeting":"工作指令","specialtask":"专项任务","resApprove":"网批","planWork2":"总部计划","planWorkCenter":"中心内部任务","oaFileFollowed":"文件跟踪","projectAcc":"项目","oaManInfo":"合理化建议","costCtrlPurBid":"成本工作管理","execPlan":"项目计划","mesMeetingInfo":"纪要系统"};
var jbpmMoudelMenuCd={"travel":"123","reimburse":"124","ceomeeting":"148","specialtask":"148","resApprove":"156","planWork2":"153","planWorkCenter":"138","oaFileFollowed":"132","projectAcc":"项目","oaManInfo":"139","costCtrlPurBid":"140","execPlan":"141","mesMeetingInfo":"213"};
// 全屏填充整个中间区域
function showAll(url, type) {
	TabUtils.newTab(jbpmMoudelMenuCd[type],jbpmMoudel[type],url,true);
	return false;
}
function jumpPageTo(url, pageNo, targDivId, callback) {
	var tempPageSize = 5;
	if(targDivId == "desk_front_notify_div"||targDivId == "desk_front_news_div"){
		tempPageSize = 5;
	}else if(targDivId == "desk_front_email_div"){
		tempPageSize = HOME_EMAIL_PAGESIZE;
	}else if(targDivId == "desk_front_jbpm_div"){
		tempPageSize = HOME_JBPM_PAGESIZE;
	}
	if (parseInt(pageNo) > 0) {
		url = url + "?deskHomePager.pageNo=" + pageNo+"&deskHomePager.pageSize=" + tempPageSize;
	}
	$.post(url, function(result) {
		$("#" + targDivId).html(result);

		//例如,邮件刷新区域
		if(callback){
			callback.call();
		}
	});
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
		url=_ctx+'/res/res-approve-info.action?id='+id;
		break;
	case "planWork2":
		url=_ctx+'/plan/plan-work2!getAllPlan.action?ifFromDaiban=true&opened_entityid='+id+"&centerCd="+deptCd;
		break;
	case "planWorkCenter":
		url=_ctx+'/plan/plan-work-center!getAllPlan.action?ifFromDaiban=true&opened_entityid='+id+"&centerCd="+deptCd;
		break;
	case "oaManInfo":
		url=_ctx+'/oa/oa-man-info.action?id='+ id+'&proposalCatCd='+taskId;
		break;
	case "costCtrlPurBid":
		url=_ctx+'/plan/cost-ctrl-bid-purc.action?targetId='+ taskId+'&targetPageType='+statusCd;
		break;
	case "execPlan":
		url = _ctx + "/plan/exec-plan!getAllSuspendExecPlan.action?ifFromDaiban=true";
		break;
	case "mesMeetingInfo":
		url = _ctx + '/mes/mes-meeting-info!index.action?reload-id='+id;
		break;
	}

	showAll(url,type);
}
function attentionEmail(id,dom){
	var $img = $(dom).find("img");
	var attention = $img.attr("attentionFlg");
	if(attention == '1'){
		$img.attr("src",_ctx+"/pics/email/attention_cancel.gif").attr("title","点击关注,邮件将被置顶显示").attr("attentionFlg","0");
	}else{
		$img.attr("src",_ctx+"/pics/email/attention.gif").attr("title","点击取消关注,取消置顶").attr("attentionFlg","1");
	}
	$.post(_ctx+"/oa/oa-email!attention.action",{oaEmailId:id},function(result){
	});
}
// 处理首页右上角:用户名\职位超长情况
function resizeRightTitle(userDeptId,positionNameId){
	var t11 = $("#"+userDeptId).text();
	var t12 = getSubStr(t11,18);
	if( t11 != t12){
		$("#"+userDeptId).html(t12);
		$("#"+userDeptId).attr("title",t11);
	}
	var t21 = $("#"+positionNameId).text();
	var t22 = getSubStr(t21,18);
	if( t21 != t22){
		$("#"+positionNameId).html(t22);
		$("#"+positionNameId).attr("title",t21);
	}
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

// 点击右边的关注事项类别，刷新关注事项列表区域，只显示该类别的关注事项
function jbpmModuleClick(moduleCd){
	$.post(_ctx + "/desk/jbpm-task.action", {"deskHomePager.pageSize":HOME_JBPM_PAGESIZE,"moduleCd":moduleCd}, function(result) {
		$("#desk_front_jbpm_div").html(result);
	});
}

// 切换待办或者关注
function doChangeDaibanAttention(to_module){
	if(now_in_daiban_or_attention!=to_module){
		var other_module = "";
		if("daiban"==to_module){
			other_module = "attention";
		}else{
			other_module = "daiban";
		}
		$("#span_title_"+other_module+"_wenzi").css("fontWeight","normal");
		$("#span_title_"+to_module+"_wenzi").css("fontWeight","bolder");
		$("#span_title_"+other_module+"_wenzi").css("fontSize","14px");
		$("#span_title_"+to_module+"_wenzi").css("fontSize","16px");
		$("#span_page_"+other_module).hide();
		$("#span_page_"+to_module).show();
		$("#div_content_"+other_module).hide();
		$("#div_content_"+to_module).show();
		now_in_daiban_or_attention = to_module;
		before_in_daiban_or_attention = to_module;
	}
}

// 首页关注向上翻页动作
function pageUpAttention(){
	$(".js_attention_"+page_no_attention).hide();
	page_no_attention--;
	$(".js_attention_"+page_no_attention).show();
	activePageAttention();
}
// 首页关注向上翻页动作
function pageDownAttention(){
	$(".js_attention_"+page_no_attention).hide();
	page_no_attention++;
	$(".js_attention_"+page_no_attention).show();
	activePageAttention();
}
// 根据现在状态激活分页按钮，关注
function activePageAttention(){
	if(page_no_attention<page_all_attention){
		$("#attention_down_inactive").hide();
		$("#attention_down_active").show();
		if(page_no_attention<2){
			$("#attention_up_active").hide();
			$("#attention_up_inactive").show();
		}else{
			$("#attention_up_inactive").hide();
			$("#attention_up_active").show();
		}
	}else{
		$("#attention_down_active").hide();
		$("#attention_down_inactive").show();
		if(page_no_attention<2){
			$("#attention_up_active").hide();
			$("#attention_up_inactive").show();
		}else{
			$("#attention_up_inactive").hide();
			$("#attention_up_active").show();
		}
	}
	$("#page_no_attention").html(page_no_attention);
}

// 从首页的关注打开某个关注模块
function openHomeAttention(moduleCd,opened_entityid){
	var opened_id_str = "";
	if(null!=opened_entityid){
		opened_id_str = "&opened_entityid="+opened_entityid;
	}
	if("oaMeeting"==moduleCd){
		TabUtils.newTab("172","指令单跟踪",_ctx+"/oa/oa-meeting.action?moduleCd=zc&if_in_attention=true"+opened_id_str,true);
	}else if("planWork2"==moduleCd){
		TabUtils.newTab("153","中心月计划",_ctx+"/plan/plan-work2!getAllPlan.action?if_in_attention=true"+opened_id_str,true);
	}else if("planWorkCenter"==moduleCd){
		TabUtils.newTab("138","中心内部任务",_ctx+"/plan/plan-work-center!getAllPlan.action?if_in_attention=true"+opened_id_str,true);
	}else if("oaFileFollowed"==moduleCd){
		TabUtils.newTab("132","文件跟踪",_ctx+"/oa/oa-file-followed.action?if_in_attention=true"+opened_id_str,true);
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

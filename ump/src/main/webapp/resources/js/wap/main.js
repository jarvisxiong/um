var HOME_EMAIL_PAGESIZE = 7;	// 首页邮件的pageSize
var HOME_JBPM_PAGESIZE = 7;	// 首页审批的pageSize
var HOME_XWGG_PAGESIZE = 5;	// 首页新闻公告的pageSize
var daiban_sort_num = Number(0);	// 待办事项的类数，用来控制待办事项div的高度和显示
var attention_sort_num = Number(0);	// 关注事项的类数，用来控制关注事项div的高度和显示
var now_in_daiban_or_attention = "";	// 现在在待办还是在关注
var before_in_daiban_or_attention = "";	// 之前s在待办还是在关注
var page_no_attention = 1;	// 现在在首页关注列表的第几页
var page_all_attention = 1;	// 首页关注列表的的总页数
function returnHome(){
	window.location.href = _ctx+'/desk/desk!mainContainer.action';
}
// 更新邮件记录
function refreshMails() {
	$.post(_ctx + "/desk/desk-email!email.action",{"deskHomePager.pageSize":HOME_EMAIL_PAGESIZE}, function(result) {
		$("#desk_front_email_div").html(result);
	});
}
// 更新审批记录
function refreshJbpm() {
	$.post(_ctx + "/desk/jbpm-task.action", {"deskHomePager.pageSize":HOME_JBPM_PAGESIZE}, function(result) {
		$("#desk_front_jbpm_div").html(result);
	});
}
// 更新首页的关注提醒列表
function refreshHomeAttention() {
	$.post(_ctx + "/oa/oa-all-attention!forHome.action",null, function(result) {
		$("#attention_ajax_div").html(result);
	});
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
		refreshJbpm();
		refreshMails();
	}
}
var jbpmMoudel={"travel":"出差审批","reimburse":"报销审批","ceomeeting":"指令单跟踪","specialtask":"专项任务","resApprove":"网上审批","planWork2":"月计划","planWorkCenter":"中心内部任务","oaFileFollowed":"文件跟踪","projectAcc":"项目","oaManInfo":"合理化建议","costCtrlPurBid":"成本工作管理"};
var jbpmMoudelMenuCd={"travel":"123","reimburse":"124","ceomeeting":"148","specialtask":"148","resApprove":"156","planWork2":"153","planWorkCenter":"138","oaFileFollowed":"132","projectAcc":"项目","oaManInfo":"139","costCtrlPurBid":"140"};
// 全屏填充整个中间区域
function showAll(url, type) {
	TabUtils.newTab(jbpmMoudelMenuCd[type],jbpmMoudel[type],url,true);
	return false;
}
function jumpPageTo(url, pageNo, targDivId) {
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
	});
}
function showEmail(dom,id,emailBodyId){
	var $parent = $(dom).parent();
	$parent.removeClass('unReadEmail').find('.new_img').remove();
	
	var url = _ctx+"/oa/oa-email!main.action?oaEmailId="+id+"&id="+emailBodyId+"&boxId=5";
	TabUtils.newTab("131","内部邮件",url,true);
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
		url=_ctx+'/res/res-approve-info.action?id='+id+'&resAuthTypeCd='+taskId;
		break;
	case "planWork2":
		url=_ctx+'/plan/plan-work2!getAllPlan.action?centerCd='+deptCd+'&opened_entityid='+ id;
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

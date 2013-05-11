/**
 * 当前编辑便签的编辑状态
 * false 无变动;
 * true 有变动 
 */
var IS_NOTE_DIRTY = false;

/**
 * 用于存放定时提醒的便签相关信息
 */
var noteTipObj = {};
/**
 * 当前展开的便签的提醒日期
 */
var currentNoteTipDate;

var EVERY_DAY_TIP_UIIDS = ['huangbj','zhengym','shixy'];
/**
 *	点击便签按钮控制右边的区域是否显示或隐藏 
 */
var IF_RIGHT_SHOW = true;	//右边区域是否显示出来的判断参数
function rightControlBtnClick(){
	if(IF_RIGHT_SHOW){
		closeNote();
		$('#main_right').animate({'marginRight':'-118px'},300);
		$('#divFrame').animate({'marginRight':'0px'},300);
		//$('#divTab').animate({'marginRight':'113px'},30);
		$('#rightControlImg').attr('src',_ctx+'/resources/images/desk/note/left_greyback.gif');
		IF_RIGHT_SHOW = false;
	}else{
		$('#main_right').animate({'marginRight':'0px'},300);
		$('#divFrame').animate({'marginRight':'118px'},300);
		//$('#divTab').animate({'marginRight':'118px'},300);
		$('#rightControlImg').attr('src',_ctx+'/resources/images/desk/note/right_greyback.gif');
		IF_RIGHT_SHOW = true;
	}
}

/**
 * 绑定便签点击事件、点击区域外自动保存事件
 */
function bindNoteEvents(){
	$('.pd_notepad').click(function(){
		$('.note_active').removeClass('note_active');
		$(this).addClass('note_active').removeClass('note_empty');
		if($(this).attr("id") == $("#dlyNoteId").val()){
			//if($('#note_show').css("display") == 'none'){
				$('#note_show').show();
				$('#pd_notice').hide();
			//}else{
			//	$('#note_show').hide();
			//	$('#pd_notice').show();
			//}
		}else{
			$('#note_show').show();
			$('#pd_notice').hide();
		}
		
		//通知显示
		if($('#note_show').css("display") != 'none'){
			$('#note_page_btn_3').removeClass("content_left_page_selected");
			$('#note_page_btn_3').addClass("content_left_page");
			$('#note_page_btn_3').show();
		}else if($('#note_show').css("display") == 'none'){
			$('#note_page_btn_3').removeClass("content_left_page_selected");
			$('#note_page_btn_3').addClass("content_left_page");
			$('#note_page_btn_3').hide();
		}
		
		$('#noteClickMask').show();
		$('.memo_right_btns').width('273px');
		$.post(_ctx+'/dly/dly-note!load.action',{id:$(this).attr('id')},function(result){
			IS_NOTE_DIRTY = false;
			setNoteInfo2Div(eval(result));
		});
	});
	$('#noteClickMask').click(function(){
		saveNote();
	});
	$('#noteContentId,#noteRemindDate,#noteRemindTime').bind('propertychange',function(){IS_NOTE_DIRTY = true;});
	$('#noteRemindDate,#noteRemindTime').bind('blur',function(){IS_NOTE_DIRTY = true;});
	$('#noteContentId').change(function(){IS_NOTE_DIRTY = true;});
}
/**
 * 新增便签
 */
function addNote(){
	if(!IF_RIGHT_SHOW){
		$('#main_right').css({'marginRight':'0px'});
		$('#divFrame').css({'marginRight':'118px'});
		$('#divTab').css({'marginRight':'118px'});
		IF_RIGHT_SHOW = true;
	}
	var i = 0;
	$('.pd_notepad').each(function(){
		var $text = $.trim($(this).text());
		if($text == ''){
			i++;
			$(this).parent().show().siblings().hide();
			$(this).trigger('click');
			return false;
		}
	});
	if(i==0){
		alert('没有可用的格子！');
	}
}
/**
 * 收起便签显示区域
 */
function closeNote(){
	$('.memo_right_btns').width('112px');
	$('#note_show').hide();
	$('#pd_notice').show();
	$('#note_container').show();
	$('.note_active').removeClass('note_active');
}
/**
 * 保存便签
 */
function saveNote(){
	$('#noteClickMask').hide();
	var id = $('#dlyNoteId').val();
	var content = $('#noteContentId').val();
	var date = $.trim($('#noteRemindDate').val());
	var time = $.trim($('#noteRemindTime').val());
	var remindDate = $.trim(date+' '+time);
	if(date == '' ||time == ''){
		remindDate = '';
	}
	if(!IS_NOTE_DIRTY){
		if('' == content){
			$('#'+id).attr({'className':'pd_notepad note_empty'});
		}
		closeNote();
		return;
	}
	$.post(_ctx+'/dly/dly-note!save.action',{noteContent:content,tipStartDate:remindDate,id:id},function(result){
		IS_NOTE_DIRTY = false;
		var obj = eval(result);
		$this = $('#'+id);
		$this.attr('title',obj[2]);
		$('#'+id+' div').text(obj[2]);
		var tipDate = $.trim(obj[4]);
		$this.attr({'className':'pd_notepad remind'});
		delete noteTipObj[currentNoteTipDate];//删除当前提醒缓存
		if('' == tipDate){
			$this.attr({'className':'pd_notepad note_noremind'});
		}else{
			//添加到提醒缓存
			tipDate = tipDate.replace(/-/g,'').replace(' ','').replace(':','');
			noteTipObj[tipDate] = id;
		}
		if('' == obj[2]){
			$this.attr({'className':'pd_notepad note_empty'});
		}
		closeNote();
	});
}
/**
 *删除便签 
 */
function delNote(){
	$('#noteClickMask').hide();
	var id = $('#dlyNoteId').val();
	$.post(_ctx+'/dly/dly-note!save.action',{noteContent:'',tipStartDate:'',id:id},function(result){
		IS_NOTE_DIRTY = false;
		var obj = eval(result);
		$('#'+id).attr({'title':'','className':'pd_notepad note_empty'});
		$('#'+id+' div').text('');
		closeNote();
		delete noteTipObj[currentNoteTipDate];//删除当前提醒缓存
	});
}
/**
 * 显示第二页
 */
function showSecondPage(dom){
	$('#note_page_1').hide();
	$('#note_page_2').show();
	if($('#note_show').css("display") != 'none'){
		$('#note_page_btn_3').show();
	}else{
		$('#note_page_btn_3').hide();
	}
	$('#note_page_btn_1').removeClass("content_left_page_selected");
	$('#note_page_btn_1').addClass("content_left_page");
	$('#note_page_btn_3').removeClass("content_left_page_selected");
	$('#note_page_btn_3').addClass("content_left_page");
	$('#note_page_btn_2').addClass("content_left_page_selected");
	$('#note_page_btn_2').removeClass("content_left_page");
	//$(dom).hide().next().show();
}
/**
 * 显示第一页
 */
function showFirstPage(dom){
	$('#note_page_2').hide();
	$('#note_page_1').show();
	if($('#note_show').css("display") != 'none'){
		$('#note_page_btn_3').show();
	}else{
		$('#note_page_btn_3').hide();
	}
	$('#note_page_btn_1').removeClass("content_left_page");
	$('#note_page_btn_1').addClass("content_left_page_selected");
	$('#note_page_btn_2').removeClass("content_left_page_selected");
	$('#note_page_btn_2').addClass("content_left_page");
	$('#note_page_btn_3').removeClass("content_left_page_selected");
	$('#note_page_btn_3').addClass("content_left_page");
}
/*显示通知
 * */
function showNotice(dom){
	$('#note_show').hide();
	$('#pd_notice').show();
	$('#note_page_btn_3').hide();
	$('#note_page_btn_3').removeClass("content_left_page");
	$('#note_page_btn_3').addClass("content_left_page_selected");
	$('#note_page_btn_2').removeClass("content_left_page_selected");
	$('#note_page_btn_2').addClass("content_left_page");
	$('#note_page_btn_1').removeClass("content_left_page_selected");
	$('#note_page_btn_1').addClass("content_left_page");
	
	if($('#note_page_2').css("display") == "none"){
		$('#note_page_btn_1').removeClass("content_left_page");
		$('#note_page_btn_1').addClass("content_left_page_selected");
	}else if($('#note_page_1').css("display") == "none"){
		$('#note_page_btn_2').addClass("content_left_page_selected");
		$('#note_page_btn_2').removeClass("content_left_page");
	}
}
function setNoteInfo2Div(obj){
	$('#noteContentId').focus();
	$('#dlyNoteId').val(obj[0]);
	$('#noteContentId').val(obj[2]||'');
	$('#noteUpdatedDate').text(obj[3]);
	if($.trim(obj[4]) == ''){
		$('#noteRemindDate').val('');
		$('#noteRemindTime').val('');
	}else{
		var tipDateTime =  obj[4].split(' ');
		$('#noteRemindDate').val(tipDateTime[0]);
		$('#noteRemindTime').val(tipDateTime[1]);
	}
	$('#noteContent').focus();
	IS_NOTE_DIRTY = false;
	//将当前日期赋值给currentNoteTipDate
	currentNoteTipDate = obj[4].replace(/-/g,'').replace(' ','').replace(':','');
}
/**
 * 便签到时提醒
 * @return
 */
function showNoteRemind(){
	var date = new Date();
	var now = date.format('yyyyMMddhhmm');
	var now_f = date.format('yyyy-MM-dd hh:mm');
	var now_time = date.format('hhmm');
	var now_time_f = date.format('hh:mm');
	var noteId = noteTipObj[now];
	var noteId_time = noteTipObj[now_time];
	if(noteId){
		var content = $('#'+noteId+' div').text();
		content = '提醒时间:'+now_f+'<br/><br/>'+'&nbsp;&nbsp;&nbsp;'+content;
		y_message(content);
		delete noteTipObj[now];
	}else{
		if(noteId_time){
			var content = $('#'+noteId_time+' div').text();
			content = '每日提醒时间:'+now_time_f+'<br/><br/>'+'&nbsp;&nbsp;&nbsp;'+content;
			y_message(content);
			delete noteTipObj[now_time];
		}
	}
}

var y_message = function(content){
	if ($("#y_message_container").length == 0) {
		$(document.body).append('<div id="y_message_container"></div>');
	}
	$("#y_message_container").prepend(
			'<div class="y_message"><div class="y_message_title">'
			+'便签提醒'+'</div><div class="y_message_close"></div><div class="y_message_hengxian"></div><div class="y_message_content">'
			+content+'</div></div>'
	);
	$('.y_message_close').click(function(){
		$(this).parent().slideUp(function(){$(this).remove();});
	});
};

$(function(){
	bindNoteEvents();
	//便签编辑区域拖拉
	//便签编辑区域拖拉
   // $('#note_show').resizable({
   //     handler: '#noteResizeHandler',
   //     min: { width: 160, height: 258 },
   //     max: { width: 160, height: 550 },
    //    onResize: function(e) {
   //     	var height = $('#note_show').height();
   //     	$('#noteContentId').height(height-78);
  //      }
   // });
    setInterval(showNoteRemind, 1000*20);
   
});
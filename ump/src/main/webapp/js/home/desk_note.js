/**
 * 当前编辑便签的编辑状态
 * false 无变动;
 * true 有变动 
 */
var IS_NOTE_DIRTY = false;
function getNotePage(pageNo){
	$("#note_container").addClass("waiting");
	$.post(_ctx+"/dly/dly-note!desk.action",{"pageNote.pageNo":pageNo}, function(result) {
		$("#note_container").html(result).removeClass("waiting");
		bindNoteEvents();
		hideNote();
	});
}

function showNote(dom){
	initHeight();
	$(dom).addClass("click").siblings().removeClass("click");
	$("#rightPanel").width("258px");
	$("#note_data_container").show();
	$("#rightPanel").animate({width:"258"},100,function(){
		$("#note_data_container").show();
	});
	$("#noteClickMask").show();
	if($(dom).attr("id") == "new_note_li"){
		setNoteInfo2Div(null);
	}else{
		$.post(_ctx+"/dly/dly-note!load.action",{id:$(dom).attr("id")},function(result){
			if(result == "error"){
				alert("该便签已经被删除！");
				getNotePage(1);
				return;
			}
			setNoteInfo2Div(eval(result));
		});
	}
	
}

function saveNote(tag){
	if(!tag){
		hideNote();
	}
	if(!IS_NOTE_DIRTY)return;
	var id = $("#dlyNoteId").val();
	var content = $("#noteContent").val();
	if($("#dlyNoteId").val()==""){
		$("#noteContent").val("");
	}
	if($.trim(content) == "")return;
	$.post(_ctx+"/dly/dly-note!save.action",{noteContent:content,id:id},function(result){
		IS_NOTE_DIRTY = false;
		var obj = eval(result);
		if(id !=""){
			$("#"+id).attr("title",obj[1]).children(".note_title_center").text(obj[1]);
			$("#note_frame").attr("src",$("#note_frame").attr("src"));
		}else{
			if($("#notes_title_container li").length == 13){
				$("#notes_title_container li:last").remove();
			}
			var html = "<li id='"+obj[0]+"' title='"+obj[3]+"&#13;"+obj[2]+"'><div class='note_title_left'></div><div class='note_title_center'>" +obj[1]+
					"</div><div class='note_title_right' title='直接删除该便签'></div><div class=''clear'></div></li>";
			$("#new_note_li").after(html);
			$("#"+obj[0]).mouseover(function(){
				$(this).addClass("click").children(".note_title_right").show();
			}).mouseout(function(){
				$(this).removeClass("click").children(".note_title_right").hide();
			});
			$("#"+obj[0]).children("div.note_title_center").click(function(){
				showNote($(this).parent());
			});
			$("#"+obj[0]).children("div.note_title_right").click(function(){
				delNote($(this).parent());
			});
			//showNote($("#"+obj[0])[0]);
		}
	});
}
function delNote(dom){
	var id;
	if(dom){
		id = $(dom).attr("id");
	}else{
		id = $("#dlyNoteId").val();
	}
	if(id=="")return;
	$("#note_container").addClass("waiting");
	$.post(_ctx+"/dly/dly-note!delete.action",{id:id},function(result){
		$("#note_container").empty().html(result).removeClass("waiting");
		bindNoteEvents();
		hideNote();
		$("#note_frame").attr("src",$("#note_frame").attr("src"));
	});
}
function hideNote(){
	$("#note_data_container").hide();
	$("#rightPanel").animate({width:"90px"},100);
	$("#noteClickMask").hide();
	
}
function setNoteInfo2Div(obj){
	$("#noteContent").focus();
	if(obj){
		$("#dlyNoteId").val(obj[0]);
		$("#noteContent").val(obj[2]||"");
		$("#noteUpdateDate").text(obj[3]);
	}else{
		$("#dlyNoteId").val("");
		$("#noteContent").val("");
		var date = new Date(); 
		var now = date.format("yyyy-MM-dd hh:mm:ss");
		$("#noteUpdateDate").text(now);
		
	}
	$("#noteContent").focus();
	IS_NOTE_DIRTY = false;
}
/**
 * 将便签内容通过邮件发送
 */
function sendNote2Email(){
	var noteContent = $.trim($("#noteContent").val());
	if(noteContent == '')return false;
	var param="sendEmailFlag=true&content="+noteContent;
	if($.browser.msie){
		if($("#email_frame").length==0){
			if($.browser.version != "7.0"){
				param = param + "&charset=GBK";
			}
		}
	}
		
	TabUtils.newTab("email","邮件",_ctx+"/oa/oa-email!main.action?"+param,true);
}
function showAllNote(){
	if($("#notes_title_container li").length == 1)return;
	TabUtils.newTab("note","便签",_ctx+"/dly/dly-note!list.action",true);
}
function copyNote(){
	copyToClipboard($("#noteContent").text());
	if($.browser.msie){
		$("#noteContent").addClass("note_data_copy");
		setTimeout(function(){$("#noteContent").removeClass("note_data_copy")}, 500);
	}
}
/**
 *绑定便签事件 
 */
function bindNoteEvents(){
	$(".note_title").click(function(){
		saveNote("noHide");
	})
	
	$(".note_title ul li").mouseover(function(){
		$(this).addClass("click").children(".note_title_right").show();
	}).mouseout(function(){
		$(this).removeClass("click").children(".note_title_right").hide();
	});
	$(".note_title ul li div.note_title_center").click(function(){
		saveNote("noHide");
		showNote($(this).parent());
	});
	$(".note_title ul li div.note_title_right").click(function(){
		saveNote("noHide");
		delNote($(this).parent());
	})
	$("#noteContent").keyup(function(){IS_NOTE_DIRTY = true});
	$("#noteClickMask").click(function(){
		saveNote();
	})
	$(".note_title").click(function(){
//		if(note_data_container)
//		saveNote();
	})
}

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/desk-oa.css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/table.js"></script>
<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
<title>便签管理</title>
</head>
<body>
<script type="text/javascript">
var IS_NOTE_DIRTY = false;
function checkedAll(flag){
	var $notes = $(".note_title_right_top");
	if(flag){
		$notes.removeClass("note_title_right_top_c").addClass("note_title_right_top_c");
		$(".note_title_center").addClass("note_title_center_c");
	}else{
		$notes.removeClass("note_title_right_top_c");
		$(".note_title_center").removeClass("note_title_center_c");
	}
}
function resetForm(){
	$("#filter_LIKES_noteTitle").val("");
	$("#filter_GED_updatedDate").val("");
	$("#filter_LED_updatedDate").val("");
}
function move2DataContainer(dom){
	$("#flyDiv").stop(true,true);
	var $off = $(dom).offset();
	var $descOff = $("#contentContainer").offset();
	$("#flyDiv").css({"left":$off.left+10+"px","top":$off.top+10+"px","width":"10px","height":"10px"}).show();
	$("#flyDiv").animate({
		left:$descOff.left+"px",
		top:$descOff.top+"px",
		width:"142px",
		height:"150px"
	},400)
	.animate({
		height:"340px",
		opacity:"hide"
	},300);
}

function initHeight(){
	var h = $(document).height();
	var lh = h-108+"px";
	var rh = h-148+"px";
	$("#left_note").height(lh);
	$("#noteContent").height(rh);
}

$(function(){
	$(".note_title_right_top").toggle(
		function(){
			$(this).addClass("note_title_right_top_c");
		},
		function(){
			$(this).removeClass("note_title_right_top_c");
		});
	$(".note_title_center").click(function(event,noAnimate){
		if(!noAnimate){
			move2DataContainer(this);
		}
		$.post("${ctx}/dly/dly-note!load.action",{id:$(this).attr("noteId")},function(result){
			if(result == "error"){
				alert("该便签已经被删除！");
				window.location.href="${ctx}/dly/dly-note!list.action";
			}else{
				var obj = eval(result);
				$("#noteContent").focus();
				$("#dlyNoteId").val(obj[0]);
				$("#noteContent").val(obj[2]||"");
				$("#noteUpdatedDate").text(obj[3]);
				$("#noteContent").focus();
			}
		});
	});
	$(".note_title_right_bottom").click(function(){
		var id = $(this).parent().siblings(".note_title_center").attr("noteId");
		$.post("${ctx}/dly/dly-note!delete.action",{id:id},function(){
			window.parent.getNotePage(1);
			window.location.href="${ctx}/dly/dly-note!list.action";
		})
	});
	
	$("#noteDelBtn").click(function(){
		var noteIds = new Array();
		$(".note_title_right_top_c").each(function(i,dom){
			var id = $(this).parent().siblings(".note_title_center").attr("noteId");
			noteIds.push("ids=" + id);
		});
		if(noteIds.length == 0){
			alert("请勾选需要删除的便签");return;
		}
		if(!window.confirm("确定要删除选中的便签?"))return;
		var param = noteIds.join("&");
		$.post("${ctx}/dly/dly-note!deleteBatch.action",param,function(){
			window.parent.getNotePage(1);
			window.location.href="${ctx}/dly/dly-note!list.action";
		})
	})
	
	$("#noteContent").keyup(function(){IS_NOTE_DIRTY = true});
	
	
	$("#contentContainer").mouseleave(function(){
		if(!IS_NOTE_DIRTY)return;
		var id = $("#dlyNoteId").val();
		var content = $("#noteContent").val();
		if($.trim(content) == "")return;
		$.post("${ctx}/dly/dly-note!save.action",{noteContent:content,id:id},function(result){
			if(result == "error"){
				alert("该便签已经被删除！")
				window.location.href="${ctx}/dly/dly-note!list.action";
			}else{
				IS_NOTE_DIRTY = false;
				var obj = eval(result);
				$("#noteUpdatedDate").text(obj[3]);
				$("#note_"+obj[0]).text(obj[1]);
			}
		})
	});
	
	//进入页面时默认显示第一个便签
	$(".note_title_center:first").trigger("click",[true]);
	initHeight();
})
</script>
<div class="notePage">
<form action="${ctx}/dly/dly-note!list.action" id="mainForm" method="post">
<div class="searchContainer" style="width:100%;height: 25px;line-height: 25px;margin-bottom:5px;margin-top:5px; ">
	<div style="float:left;margin-left:10px;">
	<span>内容：<s:textfield cssStyle="width:100px" name="filter_LIKES_noteContent" id="filter_LIKES_noteContent"></s:textfield></span>
	<span>最后更新时间：<s:textfield id="filter_GED_updatedDate" cssStyle="width:100px;" name="filter_GED_updatedDate" onfocus="WdatePicker()" size="20" /></span>
	<span>至</span>
	<span><s:textfield id="filter_LED_updatedDate" cssStyle="width:100px;" name="filter_LED_updatedDate" onfocus="WdatePicker()" size="20" /></span>
	</div>
	<div class="search_zoom" style="margin-top:0px" onclick="$('#pageNo').val(1);$('#mainForm').submit();"></div>
	<div  class="mail_button2" style="margin-top:2px;float:left;margin-right:10px;" onclick="$('#pageNo').val(1);$('#mainForm').submit();">搜索</div>
	<div class="mail_button2" style="margin-top:2px;float:left;" onclick="resetForm();">重置</div>
</div>
<div class="hengxian" style="margin-bottom: 1px;"></div>
<div >
	<div style="width:150px;float:right;margin-right:5px;">
		<div id="contentContainer">
			<input type="hidden" id="dlyNoteId" />
			<div class="content_top"></div>
			<div class="content_middle">
				<div class="content_date" id="noteUpdatedDate"></div>
				<textarea  class="note_content" onpaste="IS_NOTE_DIRTY = true;" id="noteContent" name="noteContent"></textarea>
			</div>
		</div>
	</div>
	
	<div id="left_note" style="margin: 0 160px 0 10px;height:400px;">
		<table class="noteTable" cellpadding="0" cellspacing="0">
		<s:iterator value="pageNote.result" id='note' status='status'>
			<s:if test="#status.index ==0">
				<tr>
			</s:if>
			<s:if test="#status.index%4==0 && #status.index !=0">
				</tr>
				<tr>
			</s:if>
				<td valign="top">
				<div class="note_title_container">
					<div class="note_title_left"></div>
					<div class="note_title_right">
						<div class="note_title_right_top"></div>
						<div class="note_title_right_bottom"></div>
					</div>
					<div class="note_title_center" noteId="${note.dlyNoteId}" title="${note.noteContent}">
						<div id="note_${note.dlyNoteId}">${note.noteTitle}</div>
						<div><s:date name="updatedDate" format="yyyy-MM-dd"/></div>
					</div>
				</div>
				
				</td>
		</s:iterator>
			</tr>
		</table>
	</div>
	<div class="hengxian" style="margin-bottom: 0;"></div>
	<div>
		<div style="float:left;">
		<div style="float:left;margin: 8px 5px 0 20px;"><input id="selectAllNotes" onclick="checkedAll($(this).attr('checked'));" type="checkbox"  title="全选/不选　本页所有便签"/>
		</div>
		<div onclick="$('#selectAllNotes').trigger('click');" title="全选/不选　本页所有便签" class="mail_button2"  style="float:left;margin-right:10px;">全选</div>
		<div id="noteDelBtn" class="mail_button2" style="float:left;">删除</div>
		</div>
		<div style="float:right;margin-right: 10px;">
	     	<p:page pageInfo="pageNote"/>
		</div>
	</div>
</div>
</form>
</div>
<div id="flyDiv" style="position:absolute;z-index:99;width:10px;height:10px;top:0;left:0;border: 1px solid gray;display: none;background-color: #fff;"></div>
</body>
</html>

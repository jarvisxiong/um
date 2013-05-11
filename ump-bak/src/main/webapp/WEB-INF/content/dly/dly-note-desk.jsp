<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="note_img"><span style="margin-left:8px;">便签</span></div>
<div style="height:4px; width:100%; background-color: #bebdc2;"></div>
<div style="height:100%">	
	<div class="note_title">
		<div>
		<ul id="notes_title_container">
			<li id="new_note_li">
				<div class="note_title_center">
					<img style="width:16px; height:16px; margin-top:0px;" src="${ctx}/images/desk2/note_add.gif" align="absmiddle"/>
					新建
				</div>
				<div class="clear"></div>
			</li>
			<s:iterator value="pageNote.result">
			<li id="${dlyNoteId}" title="<s:date name="updatedDate" format="yyyy-MM-dd HH:mm:ss"/>&#13;${noteContent}">
				<div class="note_title_left"></div>
				<div class="note_title_center">${noteTitle}</div>
				<div class="note_title_right" title="直接删除该便签"></div>
				<div class="clear"></div>
			</li>
			</s:iterator>
		</ul>
		</div>
		<div class="note_btns" style="position: absolute;bottom:2px;">
			<div style="margin-left:0px;"><img alt="上一页" title="上一页" onclick="getNotePage(${pageNote.prePage});" src="${ctx}/images/desk2/menu_up_normal.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/menu_up_press.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/menu_up_normal.gif');"></div>
			<div style="margin-left:10px;"><img alt="最大化" title="最大化" onclick="showAllNote();" src="${ctx}/images/desk2/note_big_normal.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/note_big_press.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/note_big_normal.gif');"></div>
			<div style="margin-left:10px;"><img alt="下一页" title="下一页" onclick="getNotePage(${pageNote.nextPage});" src="${ctx}/images/desk2/menu_down_normal.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/menu_down_press.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/menu_down_normal.gif');"></div>
		</div>
	</div>
	<div id="note_data_container" class="note_data_container">
		<div class="note_data_container_top"></div>
		<input type="hidden" id="dlyNoteId" />
		<div class="note_data_bottom">
			<div class="note_btns" style="float:right;padding-left: 5px;">
				<div style="margin-right: 10px;" onclick="sendNote2Email()" title="通过邮件发送便签内容"><img src="${ctx}/pics/note/note_email.gif"/></div>
				<div style="margin-right: 10px;" onclick="copyNote()" title="复制内容到剪贴板"><img src="${ctx}/pics/note/note_copy.gif"/></div>
				<div style="margin-right: 10px;" onclick="delNote()" title="删除该便签">
					<img src="${ctx}/pics/note/note_del.gif" 
						onmouseover="this.src='${ctx}/pics/note/note_del_hover1.gif'"
						onmouseout="this.src='${ctx}/pics/note/note_del.gif'"
					/>
				</div>
			</div>
		</div>
		<div style="clear: both"></div>
		<div class="note_data_top">
			<div id="noteUpdateDate" class="note_data_top_left"></div>
		</div>
		<div style="height:85%">
			<textarea onpaste="IS_NOTE_DIRTY = true;"  id="noteContent" class="note_data_middle" name="noteContent">
			</textarea>
		</div>
	</div>
</div>


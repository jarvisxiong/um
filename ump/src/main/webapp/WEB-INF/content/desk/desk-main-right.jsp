<%@page contentType="text/html;charset=UTF-8"%>  
<div class="note_container">
	<div id="note_container">
		<div id="note_page_1">
			<s:iterator begin="0" end="7" value="pageNote.result">
				<s:if test="noteContent == null||noteContent == ''">
					<div class="note note_empty" id="${dlyNoteId}" title="${noteContent}">
						<div class="noteDisplay">${noteContent}</div>
					</div>
				</s:if>
				<s:elseif test="tipStartDate != null">
					<div class="note " id="${dlyNoteId}" title="${noteContent}">
						<div class="noteDisplay">${noteContent}</div>
						<script type="text/javascript">
							var tipDate = "<s:date name='tipStartDate' format='yyyyMMddHHmm'></s:date>";
							noteTipObj[tipDate]='${dlyNoteId}';
							if(EVERY_DAY_TIP_UIIDS.contains('${uiid}')){
								var tipTime = "<s:date name='tipStartDate' format='HHmm'></s:date>";
								noteTipObj[tipTime]='${dlyNoteId}';
							}
						</script>
					</div>
				</s:elseif>
				<s:else>
					<div class="note note_noremind" id="${dlyNoteId}" title="${noteContent}">
						<div class="noteDisplay">${noteContent}</div>
					</div>
				</s:else>
			</s:iterator>
			<div style="width:112px; height:33px;">
				<div class="note_page page_active" onclick="showFirstPage();" title="第一页">1</div>
				<div class="note_fanye_shu"></div>
				<div class="note_page" onclick="showSecondPage();"  title="第二页">2</div>
			</div>
		</div>
		<div id="note_page_2" style="display:none">
			<s:iterator begin="8" end="15" value="pageNote.result">
				<s:if test="noteContent == null||noteContent == ''">
					<div class="note note_empty" id="${dlyNoteId}" title="${noteContent}">
						<div class="noteDisplay">${noteContent}</div>
					</div>
				</s:if>
				<s:elseif test="tipStartDate != null">
					<div class="note " id="${dlyNoteId}" title="${noteContent}">
						<div class="noteDisplay">${noteContent}</div>
						<script type="text/javascript">
							var tipDate = "<s:date name='tipStartDate' format='yyyyMMddHHmm'></s:date>";
							noteTipObj[tipDate]='${dlyNoteId}';
							if(EVERY_DAY_TIP_UIIDS.contains('${uiid}')){
								var tipTime = "<s:date name='tipStartDate' format='HHmm'></s:date>";
								noteTipObj[tipTime]='${dlyNoteId}';
							}
					</script>
					</div>
				</s:elseif>
				<s:else>
					<div class="note note_noremind" id="${dlyNoteId}" title="${noteContent}">
						<div class="noteDisplay">${noteContent}</div>
					</div>
				</s:else>
			</s:iterator>
			<div style="width:112px; height:33px;">
				<div id="note_page_btn_1" class="note_page" onclick="showFirstPage();"  title="第一页">1</div>
				<div class="note_fanye_shu"></div>
				<div id="note_page_btn_2" class="note_page page_active" onclick="showSecondPage();"  title="第二页">2</div>
			</div>
		</div>
	</div>
</div>
<div id="note_show" class="note_show">
	<div class="note_btns">
		<div class="note_save" onclick="saveNote()">保存</div>
		<div class="note_remind">提醒</div>
		<div class="note_del" onclick="delNote()">删除</div>
	</div>
	<div class="note_remind_date">
		<input type="hidden" id="dlyNoteId">
		<input type="text" id="noteRemindDate" style="width:90px;margin-left: 3px;" onfocus="WdatePicker()"/>
		<input type="text" id="noteRemindTime" style="width:50px;" onfocus="WdatePicker({dateFmt:'HH:mm'})"/>
	</div>
	<div style="border-top: 1px solid #dddbdc;"><textarea id="noteContentId" onpaste="IS_NOTE_DIRTY = true;" class="note_content_div"></textarea></div>
	<div class="note_update_div">
		<span>更新时间:</span><span id="noteUpdatedDate"></span>
	</div>
	<div style="background-color:#8c8f94; height:1px;"></div>
	<div id="noteResizeHandler" class="note_resize"></div>
	<div style="background-color:#8c8f94; height:1px;"></div>
</div>

<%--
<div id="daiban_display_div" style="display:none;">
	<div style="height: 3px; border-top: 1px solid #8C8F94;  border-bottom: 1px solid #8C8F94;border-left: 1px solid #8C8F94;">
		<div style="background-color: #EFF3FB; height: 1px;"></div>
		<div style="background-color: #DEE6E8; height: 2px;"></div>
	</div>
	<div style="width:112px; height:22px;border-bottom: 1px solid #8C8F94;">
		<div class="daiban_title">待办事项</div>
	</div>
	<div id="daiban_height_div" style="height:189px; width:112px;">
		<table class="home_table" width="98%">
			<tr style="height:0px; display:none;" id="daiban_start_tr">
				<td id="daiban_start_td" colspan="2"></td>
			</tr>
		</table>
	</div>
</div>
 --%>

<div id="attention_display_div" style="display:block;">
	<div style="height: 3px; border-top: 1px solid #8C8F94;  border-bottom: 1px solid #8C8F94; border-left: 1px solid #8C8F94;">
		<div style="background-color: #EFF3FB; height: 1px;"></div>
		<div style="background-color: #DEE6E8; height: 2px;"></div>
	</div>
	<div style="width:112px; height:22px;border-bottom: 1px solid #8C8F94;">
		<div class="daiban_title">每日会议提醒</div>
	</div>
	<div id="attention_height_div" style="width:112px;">
		<table class="home_table" width="98%" id="attention_table">
			<tr style="height:0px; display:none;" id="attention_start_tr">
				<td id="attention_start_td" colspan="2"></td>
			</tr>
		</table>
	</div>
</div>
<div id="attention_ajax_div" style="display:none;"></div>

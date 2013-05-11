<%@ page contentType="text/html;charset=UTF-8" %>
	 				<div style="margin-top:4px;">
							<div class="content_left_page_selected" id="note_page_btn_1"  onclick="showFirstPage();" title="第一页">1</div>
							<div class="content_left_page" id="note_page_btn_2" onclick="showSecondPage();">2</div>
							<%--
							<div class="content_left_page" id="note_page_btn_3" style="width:52px;display:none;" onclick="showNote();">&nbsp;通&nbsp;&nbsp;知&nbsp;</div>
							 --%>
							<div class="div_clear"></div>
					</div>
					<div class="div_clear"></div>
 						
 						
 				<div class="note_container">
					<div id="note_container">
						<div id="note_page_1">
							<s:iterator begin="0" end="7" value="pageNote.result" status='st'>
								<s:if test="noteContent == null||noteContent == ''">
									<div class="pd_notepad note_empty" id="${dlyNoteId}" title="${noteContent}">
										<div class="noteDisplay">${noteContent}</div>
									</div>
								</s:if>
								<s:elseif test="tipStartDate != null">
									<div class="pd_notepad remind" id="${dlyNoteId}" title="${noteContent}">
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
									<div class="pd_notepad note_noremind" id="${dlyNoteId}" title="${noteContent}">
										<div class="noteDisplay">${noteContent}</div>
									</div>
								</s:else>
								<s:if test="#st.Odd">
									<div style="float:left;width:4px;">&nbsp;</div>
								</s:if>
								
							</s:iterator>
						</div>
						<div id="note_page_2" style="display:none">
							<s:iterator begin="8" end="15" value="pageNote.result" status='st'>
								<s:if test="noteContent == null||noteContent == ''">
									<div class="pd_notepad note_empty" id="${dlyNoteId}" title="${noteContent}">
										<div class="noteDisplay">${noteContent}</div>
									</div>
								</s:if>
								<s:elseif test="tipStartDate != null">
									<div class="pd_notepad remind" id="${dlyNoteId}" title="${noteContent}">
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
									<div class="pd_notepad note_noremind" id="${dlyNoteId}" title="${noteContent}">
										<div class="noteDisplay">${noteContent}</div>
									</div>
								</s:else>
								<s:if test="#st.Odd">
									<div style="float:left;width:4px;">&nbsp;</div>
								</s:if>
							</s:iterator>
						</div>
					</div>
				</div>
				<div class="div_clear"></div>
				<div  class="block_deepblue_fast" id="pd_notice"  style="width:100%;height:260px;margin-top: 4px;">
 						<!-- <div style="line-height: 16px;text-align: left;padding:12px 0px 0px 5px;font-size: 18px;font-weight:bold;"></div> -->
 						<div class="font_notice" style="margin-top: 8px;" >
 							<div style="padding:18px 5px 6px 5px; font-weight:bolder;" onclick="TabUtils.newTab('pptdownload2','下载', '${ctx}/common/pptdownload2.jsp', true);">下载半年会工作报告&nbsp;></div>
 						</div>
 						<div class="font_notice">
 							<div style="padding:6px 5px 6px 5px; font-weight:bolder;" onclick="TabUtils.newTab('pptdownload','下载', '${ctx}/common/pptdownload.jsp', true);">下载商业地产项目管理专项培训课件&nbsp;></div>
 						</div>
 						<div class="font_notice">
 							<!-- <div style="padding:6px 5px 6px 5px;" onclick="window.open('${ctx}/common/viewVideo.jsp');">观看网批培训视频&nbsp;></div> -->
 							<div style="padding:6px 5px 6px 5px; font-weight:bolder;" onclick="window.open('${ctx}/app/download.action?fileName=PD3.0.ppsx&amp;realFileName=PD3.0_intro.ppsx&amp;bizModuleCd=public');">下载PD3.0新特性说明&nbsp;></div>
 						</div>
						<div class="font_notice">
							<div style="padding:6px 5px 6px 5px;" onclick="window.open('${ctx}/app/download.action?fileName=seat_and_tel.xlsx&amp;realFileName=seat_and_tel.xlsx&amp;bizModuleCd=public');">总部人员座位图&nbsp;></div>
						</div>
						<div class="font_notice">
							<div style="padding:6px 5px 6px 5px;" onclick="window.open('${ctx}/app/download.action?fileName=IT_duty.xlsx&realFileName=IT_duty.xlsx&bizModuleCd=public');">IT值班表&nbsp;></div>
							</div>
						<div class="font_notice">
							<div style="padding:6px 5px 6px 5px;" onclick="window.open('${ctx}/app/download.action?fileName=adobe.rar&realFileName=adobe.rar&bizModuleCd=public');">人事系统和加密系统插件&nbsp;></div>
							</div>
						<div class="font_notice">
							<div style="padding:6px 5px 6px 5px;" onclick="window.open('${ctx}/app/download.action?fileName=print_set.docx&amp;realFileName=print_set.docx&amp;bizModuleCd=public');">下载打印机配置说明&nbsp;></div>
						</div>
 						<div class="div_clear"></div>
 				</div>
 				<div id="note_show" class="note_show" style="margin-top: 4px;">
							<div class="note_remind_date">
								<input type="hidden" id="dlyNoteId">
								<input type="text" id="noteRemindDate" style="width:80px;margin-left: 3px;" onfocus="WdatePicker()"/>
								<input type="text" id="noteRemindTime" style="width:50px;" onfocus="WdatePicker({dateFmt:'HH:mm'})"/>
							</div>
							<div style="border-top: 1px solid #dddbdc;">
							<textarea id="noteContentId"  onpaste="IS_NOTE_DIRTY = true;" class="note_content_div" style="width:158px;"></textarea>
							</div>
							<div class="pd_remind" style="border-top: 1px solid #dddbdc;">
							<div onclick="saveNote()" style="width:100px;float:left;font-weight: bold;cursor: pointer; background-color:#54b147; color:#fff;">保存</div> 
							<div onclick="delNote()"  style="width:53px;float:right;font-weight: bold;cursor: pointer; background-color:#AC2727; color:#fff;">删除</div>
							</div>
							<div class="note_update_div">
								<span id="noteUpdatedDate"></span>更新
							</div>
							<div style="background-color:#8c8f94; height:1px;"></div>
							<div id="noteResizeHandler" class="note_resize"></div>
							<div style="background-color:#8c8f94; height:1px;"></div>
				</div>
				
				
				<div id="attention_ajax_div" style="display:none;"></div>
					
					

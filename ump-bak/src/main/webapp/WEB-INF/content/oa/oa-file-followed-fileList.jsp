<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<table class="file_list" cellpadding="0" cellspacing="0">
	<tr style="height: 0;">
		<th width="50" style="height: 0; padding-left: 4px;"></th>
		<th width="80" style="height: 0; padding-left: 4px;"></th>
		<th width="90" style="height: 0; padding-left: 4px;"></th>
		<th style="height: 0; padding-left: 4px;"></th>
		<th width="70" style="height: 0; padding-left: 4px;"></th>
		<th width="150" style="height: 0; padding-left: 4px;"></th>
		<th width="50" style="height: 0; padding-left: 4px;"></th>
		<th width="60" style="height: 0; padding-left: 4px;"></th>
		<th width="40" style="height: 0; padding-left: 4px;"></th>
	</tr>

	<s:iterator value="fileResults" var="fileResult">
		<tr class="resulttype_title">
			<td colspan="9" style="font-size: 14px; cursor: default; font-weight: bold; background-color: #CCCCCC;">${typeName}(${totalNum})</td>
		</tr>
		<s:iterator value="#fileResult.fileList" var="fileItem">
			<tr class="item" myid="${oaFileFollowId}">
				<td style="overflow:visible;" nowrap="nowrap">
					<div style="display:none;" id="attention_recordVersion_${oaFileFollowId}"></div>
					<div style="display:none;" id="attention_status_${oaFileFollowId}">${status}</div>
					<div style="display:inline;"><input type="checkbox" name="allFilesCheckBox" recordVersion="${recordVersion}" value="${oaFileFollowId}" id="${oaFileFollowId}checkBox" onclick="checkFile(event);"/></div>
					<div style="display:inline;"><img id="attention_${oaFileFollowId}" title="点击取消关注" attentionFlg="1" src="${ctx}/pics/email/attention.gif" onclick='jQuery.Event(event).stopPropagation();doDeleteAttention("oaFileFollowed","${oaFileFollowId}");' style="display:none;"/><img id="attention_cancel_${oaFileFollowId}" title="点击关注,该条将在您的首页自动提示是否有更新" attentionFlg="0" src="${ctx}/pics/email/attention_cancel.gif" onclick='jQuery.Event(event).stopPropagation();doAddAttention("oaFileFollowed","${oaFileFollowId}","${recordVersion}");' style="display:none;"/></div>
					<div style="display:none; cursor:pointer;" id="attention_unread_img_${oaFileFollowId}" onClick="setAttentionRead('oaFileFollowed','${oaFileFollowId}');"><img class="new_img" src="${ctx}/resources/images/common/new.gif" /></div>
				</td>
				<td><span id="${oaFileFollowId}serialNumSpan">${serialNumber}${serialNumberNum}</span></td>
				<td>
				   <div id="${oaFileFollowId}ProjDiv" ><p:code2name mapCodeName="mapToProjectNames" value="oaFileFollowId" /></div>
					<input type="text" id="${oaFileFollowId}Proj" class="pd-chill-tip sendOrg" style="width:90%;display:none;" readonly="readonly"
					 title="<p:code2name mapCodeName="mapToProjectNames" value="oaFileFollowId" />"
					 value="<p:code2name mapCodeName="mapToProjectNames" value="oaFileFollowId" />"
					 />
					<input id="${oaFileFollowId}ProjectSn" name="projectSn" value="${projectSn}" style="display:none;"/>
				</td>
				<td
					<s:if test="status != 4">
						<security:authorize ifAnyGranted="A_FILE_TRACK_ADMIN">
							onclick="enableFileNameEdit('${oaFileFollowId}', event);"
							title="点击编辑文件名"
						</security:authorize>
					</s:if>
				>
					<div id="${oaFileFollowId}FileNameDiv" style="line-height: 35px;" class="splitWord pd-chill-tip" title="${content}">${content}</div>
					
					<s:if test="status != 4">
						<security:authorize ifAnyGranted="A_FILE_TRACK_ADMIN">
							<input type="text" id="${oaFileFollowId}FileNameInput" 
								name="${oaFileFollowId}FileNameInput"
								value="${content}"
								onblur="saveFileName('${oaFileFollowId}');"
								style="width: 99%; cursor: pointer; display: none; height: 25px; line-height: 25px;"/>
						</security:authorize>
					</s:if>
				</td>
				<td style="cursor: pointer;">
					<p:code2name mapCodeName="mapToCenterNames" value="oaFileFollowId" />
				</td>
				<td <s:if test="#fileResult.typeCd == 3" >style="color: red;"</s:if>>
					<p:code2name mapCodeName="mapLatestStatus" value="oaFileFollowId" />
				</td>
				<td><s:date name="createdDate" format="MM-dd" /></td>
				<td><s:date name="updatedDate" format="MM-dd" /></td>
				
				<s:if test="query==null||(query==1&&currentCd==centerDeptSn)">
				<td class="pd-chill-tip" style="background-image: none;" 
					onclick="attachManage('${oaFileFollowId}', event);" title="点击管理附件" >
					<s:if test="mapIfHasAttach[oaFileFollowId] == false">
						<img src="${ctx}/pics/email/atta.gif" />
					</s:if>
					<s:else>
						<img src="${ctx}/pics/email/atta_y.gif" />
					</s:else>
				</td>
				</s:if>
				<s:else>
				<td>
				</td>
				</s:else>
			</tr>
			<tr class="detail" id="detail_${oaFileFollowId}">
				<td colspan="2" align="center" style="font-size: 14px; font-weight: bold; color: red;">进度</td>
				<td colspan="2" style="padding-bottom: 10px;" valign="bottom">
					<div id="${oaFileFollowId}commentDiv" style="margin-bottom: 10px; line-height: 20px;">
						${scheduleDescribe}
					</div>
	
					<div class="func_icon_publish" style="float: right; margin-right: 20px; width: 50px; height: 50px;" onclick="saveComment('${oaFileFollowId}');">留言</div>
					<div style="margin-right: 75px; height: 50px;">
						<textarea id="${oaFileFollowId}commentArea" style="width: 98%; height: 48px; overflow: auto;"></textarea>
					</div>
				</td>
				<td colspan="2" style="padding-bottom: 10px;" valign="top">
					<div id="${oaFileFollowId}statusDesc" style="margin-bottom: 20px; line-height: 20px;">
						${scheduleDescribe2}
					</div>
					
					<s:if test="#fileItem.status != 4 && #fileResult.typeCd != 2 && curUser == #fileItem.currentUiid && #fileItem.receiver == null">
						<div style="height: 50px; margin: 0; padding: 0;">
							<div style="float: left; width: 80px; height: 50px;">
								<textarea readonly="readonly" id="${oaFileFollowId}receiverName" style="overflow: auto; line-height: 48px; width: 78px; height: 48px; cursor: pointer;"
									class="receiver" title="点击选择发送人" class="pd-chill-tip"></textarea>
							    <input type="hidden" name="${oaFileFollowId}receiver" id="${oaFileFollowId}receiver" />
							</div>
							<div class="func_icon_publish" style="margin-left: 90px; width: 50px; height: 50px;" onclick="sendPerson('${oaFileFollowId}');">发送</div>
						</div>
					</s:if>
				</td>
				<td colspan="3">
					<s:if test="(status == 2 || status == 3) && curUser == #fileItem.receiver">
						<div id="${oaFileFollowId}receiveBtn" class="func_icon" style="float: left; margin-left: 10px;" onclick="receive('${oaFileFollowId}');">
							确认
						</div>
					</s:if>
					<security:authorize ifNotGranted="A_FILE_TRACK_ADMIN">
						<s:if test="(status == 2 || status == 3) && curUser == #fileItem.receiver">
							<div class="func_icon" style="float: left; margin-left: 10px;" onclick="sendBack('${oaFileFollowId}');">
								退回
							</div>
						</s:if>
						<s:if test="status == 3 && curUser == #fileItem.currentUiid">
							<div class="func_icon" style="float: left; margin-left: 10px;" onclick="complete('${oaFileFollowId}');">
								完成
							</div>
						</s:if>
						<s:if test="status == 1 && curUser == #fileItem.creator">
							<div id="${oaFileFollowId}delBtn" class="func_icon_red" style="float: left; margin-left: 10px;" onclick="deleteFile('${oaFileFollowId}');">
								删除
							</div>
						</s:if>
					</security:authorize>
					<security:authorize ifAnyGranted="A_FILE_TRACK_ADMIN">
						<s:if test="status == 2 || status == 3">
							<s:if test="#fileItem.receiver != null && #fileItem.receiver != ''">
								<div class="func_icon" style="float: left; margin-left: 10px;" onclick="sendBack('${oaFileFollowId}');">
									退回
								</div>
							</s:if>
							<div class="func_icon" style="float: left; margin-left: 10px;" onclick="complete('${oaFileFollowId}');">
								完成
							</div>
						</s:if>
						<s:if test="status == 4">
							<div class="func_icon" style="float: left; margin-left: 10px;" onclick="contin('${oaFileFollowId}');">
								恢复
							</div>
						</s:if>
						<s:if test="status != 4">
							<div id="${oaFileFollowId}delBtn" class="func_icon_red" style="float: left; margin-left: 10px;" onclick="deleteFile('${oaFileFollowId}');">
								删除
							</div>
						</s:if>
					</security:authorize>
				</td>
			</tr>
			<script language="javascript">
				<s:if test="attentionMap[oaFileFollowId] == 'attention'">
					$("#attention_${oaFileFollowId}").show();
				</s:if>
				<s:if test="!(attentionMap[oaFileFollowId] == 'attention')">
					$("#attention_cancel_${oaFileFollowId}").show();
				</s:if>
				<s:if test="attentionMapUnread[oaFileFollowId] == 'unread'">
					$("#attention_recordVersion_${oaFileFollowId}").html("${recordVersion}");
					$("#attention_unread_img_${oaFileFollowId}").css("display","inline");
				</s:if>
			</script>
		</s:iterator>
	</s:iterator>
</table>

<div style="display: none;" id="hiddenPager">
	<div style="float: left;">
		<div class="func_icon" style="margin-right: 10px; float: left; margin-left: 5px;" onclick="batchSend();">送出</div>
		<div class="func_icon" style="margin-right: 10px; float: left;" onclick="batchConfirm();">确认</div>
		<s:url id="exportExl" action="oa-file-followed!exportExcel.action" namespace="/oa"  includeParams="none"  />
		<div class="func_icon_red" style="float: left; margin-right: 10px;" onclick="exportResult('${exportExl}');">导出</div>
		<div class="func_icon_red" style="margin-right: 10px; float: left;" onclick="batchDel();">删除</div>
	</div>
	<div style="float: right; padding-right: 10px;">
		共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
		 当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
		 <s:if test="page.hasPre">
			<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_up.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_up_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_up.gif');" onclick="jumpTo('${page.prePage}');"/>
		 </s:if>
		 <s:else>
		 	<img align="absmiddle" src="${ctx}/images/desk2/page_up_grey.gif" />
		 </s:else>
		 
		 <s:if test="page.hasNext">
			<img align="absmiddle" style="cursor:pointer;" src="${ctx}/images/desk2/page_down.gif" onmouseover="$(this).attr('src', '${ctx}/images/desk2/page_down_hover.gif');" onmouseout="$(this).attr('src', '${ctx}/images/desk2/page_down.gif');" onclick="jumpTo('${page.nextPage}');"/>
		 </s:if>
		 <s:else>
		 	<img align="absmiddle" src="${ctx}/images/desk2/page_down_grey.gif" />
		 </s:else>
		 
		 <input type="text" id="pageNo" style="height: 15px; width: 20px; text-align: center;" value="${page.pageNo}" />
		 <a href="#" onblur="this.blur();" onclick="jumpTo($(this).prev().val()); return false;">GO</a>
	 </div>
</div>

<script language="javascript">
	$("tr.item").click(function() {
		try{
			setAttentionRead('oaFileFollowed',$(this).attr("myid"));
		}catch(e){}
		var detail = $(this).next();
		if (detail.css("display") == "none") {
			if (openItem != null) {
				openItem.next().hide();
				openItem.removeClass("selected");
				openItem.find("div").addClass("splitWord");
			}
			$(this).addClass("selected");
			$(this).find("div").removeClass("splitWord");
			detail.show();
			openItem = $(this);
		} else {
			$(this).removeClass("selected");
			$(this).find("div").addClass("splitWord");
			detail.hide();
			openItem.next().hide();
			openItem = null;
		}
	});
	$("#totalPageSize").val("${page.totalPages}");
	var currentId ="${currentId}";
	if(currentId){
		$("#detail_"+currentId).show();
	}
</script>
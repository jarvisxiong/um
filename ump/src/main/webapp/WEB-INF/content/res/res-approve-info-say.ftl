<script type="text/javascript">
function say(){
	var content= $("#comment").val();
	if($.trim(content)==''){
		$("#comment").css("border","1px solid red");
	}else{
		reloadMsg(content);
	}
	$('#idReplyDiv').html('');
	$('#idReplyDiv').hide();
	$('#idReplyMsg').val('');
}

function replayPerson(person,content,date,replayMsgId){
	var msg = '===============引用===============';
	msg += '<br/> ' + person + ' ' + date;
	msg += '<br/> ' + content ;
	$('#idReplyDiv').html(msg);
	$('#idReplyDiv').show();
	$('#idReplyMsg').val(replayMsgId);
	$("#comment").focus();
}

</script>

<style type="text/css">
<!--
.shared_msg div{
	background-color:#e1f1f9;
}
.shared_msg{
	background-color:#e1f1f9;
}
-->
</style>
		<div style="padding:0px 10px;">
			<table class="msgTable">
				<#assign curUserCd=stack.findValue('@org.springside.modules.security.springsecurity.SpringSecurityUtils@getCurrentUiid()')>
				<input type="hidden" id="isShare" name="isShare" value="${isShare}" />
				<#list messages as item>
					<#if !(item.resApproveNodeId!=null)>
					<#assign canSee=true>
					<#assign isPrivateMsg=false>
					<#if item.toUserCds!=null&&item.toUserCds!=''>
						<#assign isPrivateMsg=true>
						<#assign canSee=false>
						<#if item.toUserCds?contains(curUserCd)>
							<#assign canSee=true>
							
						</#if>
						<#if item.creator==curUserCd>
							<#assign canSee=true>
						</#if>
						<#if permission.qzspAdmin||permission.qzspMsg>
						<#assign canSee=true>
						</#if>
					</#if>
					<#if canSee>
						<tr class="mainTr">
							<td style="padding:10px 5px;" <#if isPrivateMsg>class="shared_msg"</#if>>
								<div style="float:left;padding-left: 10px;">
									<#assign ceatorName=stack.findValue('@com.hhz.ump.util.CodeNameUtil@getUserNameByCd("${item.creator}")')>
									<#assign toUserNames=stack.findValue('@com.hhz.ump.util.CodeNameUtil@getUserNamesByUiids("${item.toUserCds}",";")')>
									<span style="color: #5a5a5a;font-weight: bold">${ceatorName}</span>
									<span>(${item.createdDate?string("yy-MM-dd HH:mm")})</span>
									<span><a style="color:#0693e3;text-decoration: underline;" href="javascript:replayPerson('${ceatorName}','${item.msgContent}','${item.createdDate?string("yy-MM-dd HH:mm")}','${item.resApproveMessageId}');">回复</a></span>
									<#if isPrivateMsg>
										<span style="color:#464646">发送给</span>  
										<span style="color:#0167a2">${toUserNames}</span>
									</#if>
								</div>
								
								<div style="clear:both;padding:5px;">
									<#if item.referMsgId!=null>
										<#assign tmpId=item.referMsgId>
										<div class="repDiv" >
											<#list messages as refMsg>
												<#assign tmpCreator=refMsg.creator >
												<#if refMsg.resApproveMessageId==tmpId>
													<div style="float:left;">
														${stack.findValue('@com.hhz.ump.util.CodeNameUtil@getUserNameByCd("${tmpCreator}")')}
													</div> 
													<div style="float:left;margin-left: 10px;">${refMsg.createdDate?string("yy-MM-dd HH:mm")}</div>
													<div style="clear:both;white-space:pre-wrap;">${refMsg.msgContent}</div>
												</#if>	
											</#list>
										</div>
									</#if>
									<div style="clear:both;padding-top:5px;padding-left:10px;white-space:pre-wrap;border-bottom: #BFC4C8 1px solid;color:#5a5a5a; ">${item.msgContent}</div>
								</div>
							</td>
						</tr>
					</#if>
					</#if>
				</#list>
			</table>
		</div>
		<div style="width:90%;padding:0px 10px;">
			<table class="showTable" id="showTable" style="width:100%;table-layout: auto;">
				<tr class="detailTr">
					<td>
						<div style="padding-left:10px;padding-top:10px;">发表留言<span style="color:red;">（这里填写的内容不具审批效力也不会被打印）</span></div>
					</td>
					<td style="width:130px;">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2">
						<div id="idReplyDiv" style="padding-left:10px;padding-top:10px;background-color:#ffffee;display:none;white-space:pre-wrap;"></div>
						<input type="hidden" id="idReplyMsg" name="replyMsgId"></input>
					</td>
				</tr>
				<tr>
					<td style="padding-left:10px;padding-top:10px;" colspan="2" id="applyReasonTip">
					</td>
				</tr>
				<tr class="detailTr">
					<td style="padding:10px;">
						<textarea style="width:100%;height:55px;" name="comment" id="comment"></textarea>	
					</td>
					<td>	
						<input type="button" id="say" value="留言" class="btn_green_55_55" onclick="say();" />
					</td>
				</tr>
			</table>
			
		</div>
		<script>
		var maxNumber = 300;
		$('#comment').bind('keyup change',function(){
			var btn = $("#say"),
			val = $(this).val().length;
			disabled = {
					on: function(){
						btn.removeAttr('disabled');
						btn.attr('class','btn_blue_55_55');
					},
					off: function(){
						btn.attr('disabled', 'disabled');
						btn.removeAttr('class');;
						btn.css("border","1px solid #8c8f94");
						btn.css("width","55px");
						btn.css("height","55px");
					}
			};
			if (val == 0) 
			{
			disabled.off();
			}
			if(val <= maxNumber){
				if (val > 0) disabled.on();
				$("#applyReasonTip").html('<span>\u8FD8\u80FD\u8F93\u5165 </span><span style="font-weight:bold;color:green;">' + (maxNumber - val) + '</span> \u4E2A\u5B57</span>');
			}else{
				disabled.off();
				$("#applyReasonTip").html('<span>\u5DF2\u7ECF\u8D85\u51FA </span><span style="font-weight:bold;color:red;">' + (val - maxNumber) + '</span><span> \u4E2A\u5B57\uff0c<span style="font-weight:bold;color:red;">\u5c06\u4e0d\u80fd\u53d1\u5e03\u7559\u8a00</span></span>');
			};
			
		});
		</script>

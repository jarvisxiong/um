<#assign ctx=request.contextPath>
<#assign curUserCd=stack.findValue('@org.springside.modules.security.springsecurity.SpringSecurityUtils@getCurrentUiid()')>
<div class="list_header_img"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
<div class="list_header2"><span>审批记录</span></div>
<div style="padding:0px 5px;margin:10px;">
	<table class="approveTable" id="showTableApproveRec">
				<col width="30px"/>
				<col width="50px"/>
				<col width="190px"/>
				<col width="40%"/>
				<col width="40%"/>
				<#assign index=0>
				<#assign preLevel=-1>
				<#assign nodeFrom=0>
		<#list resApproveNodes as ra>
			<#assign isCur=0>
			<#if ((nodeCd==ra.groupNodeCd || nodeCd==ra.nodeCd) && ra.approveOptionCd ==-1 && ra.approveLevel==approveLevel)>
				<#assign isCur=1>
			</#if>
			<#if (ra.approveRank==1)>
			<tr style="height: 30px;">
				<#if ra.approveLevel !=preLevel >
				<#assign index=index+1>
				</#if>
				<td colspan="5" id="tr_level1_${ra.resApproveNodeId}" ifShowLevel2="true" style="cursor:pointer;" onclick="showOrHideLevel2('${ra.resApproveNodeId}');">
					<div>
					<table class="approveTable" style="<#if ra.approveRank = 2>margin-right:-40px;</#if>float:right;">
					<tr class="
							<#if ra.remark==null>bottom </#if>
							<#if isCur==1>curTr<#elseif ra.approveOptionCd==1>passTr<#else>futureTr</#if> 
							<#if !(ra.isDefault!true)>trDefault</#if>
						">
						<td align="center" style="width:60px">${index}</td>
						<td class="approveName" <#if !(ra.isDefault!true)>style="color:red;"</#if>>${ra.userName}</td>
						<td <#if !(ra.isDefault!true)>style="color:red;"</#if>>
							<#if ra.approveDate!=null>
							<#assign approveDateStr=ra.approveDate?string("yy-MM-dd HH:mm") >
							<#else>
							<#assign approveDateStr="">
							</#if>
							(${approveDateStr}<#if (ra.groupNodeCd==39 || ra.nodeCd==97)>${mapOptionZyps[ra.approveOptionCd]}<#else>${mapOption[ra.approveOptionCd]}</#if>)
							<#if isMyApprove==1 && ra.userCd!=curUserCd && ra.approveDate!=null>
								<span class="noprint">
									<a class="approveLine" href="javascript:agreePerson('${ra.userName}');">同意</a>
								</span>
							</#if>
							<#if (ra.userCd==curUserCd && ra.approveOptionCd!=-1)>
							<span class="divApppendRemark noprint" id="${ra.resApproveNodeId}"><span><a class="approveLine" href="javascript:void(0);">追加</a></span></span>											
							</#if>
						</td>
						<#assign nodeName>${codeNameUtil.getResNodeNameByCd(ra.nodeCd)}<#if ra.groupNodeCd!=null>/${codeNameUtil.getResNodeNameByCd(ra.groupNodeCd)}</#if></#assign>
						<td <#if !(ra.isDefault!true)>style="color:red;"</#if> class="ellipsisDiv_full" title="${ra.workDutyDesc}">
							<#if templetCd!=9>${nodeName}.</#if>${ra.workDutyDesc}
						</td>
						<td <#if !(ra.isDefault!true)>style="color:red;"</#if> class="ellipsisDiv_full" title="${nodeName}">
							&nbsp;<span id="span_level1_${ra.resApproveNodeId}" style="display:none; padding:4px;background-color:#53b147; color:#FFF;"></span>
						</td>
						<#assign preLevel=ra.approveLevel>
					</tr>
					</table>
					</div>
				</td>
			</tr>
			<tr class="approveOptionTr <#if ra.remark!=null>bottom</#if>">
			<td></td>
			<td colspan="4">
				<#if ra.remark!=null>
					<div style="clear:both;white-space:pre-wrap;" class="approveOption">${ra.remark}</div>
				</#if>
		<div style="padding:0px 10px;">
			<table class="msgTable" style="width:100%;">
				<#list resApproveMessages as item>
					<#if (item.resApproveNodeId==ra.resApproveNodeId)>
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
			</td>
			</tr>
			<tr id="trAppendRemark_${ra.resApproveNodeId}" style="display:none;" class="noprint">
				<td colspan="5">
					<table style="border:none;" border="0" width="100%" >
					<tr>
					<td>
					<textarea style="width:99%;height:51px;" id="approveRemark_append_${ra.resApproveNodeId}"></textarea>
					</td>
					<td width="60">
					<input class="btn_blue_55_55" type="button" value="完成追加" style="border:none;" onclick='doCompleteAppend("${ra.resApproveNodeId}");' />
					</td>
					</tr>
					</table>
				</td>
			</tr>
			
			<!--这里遍历第二遍，读取二级审批人-->
			<#assign reachToNextLevel1=false>
			<#assign index2=0>
			<#assign parentId=ra.resApproveNodeId>
		<#list resApproveNodes as ra>
			<#assign isCur=0>
			<#if (ra.approveOptionCd ==-1 && ra.approveLevel>=approveLevel2 && ra.approveLevel<approveLevel)>
			<#assign isCur=1>
			</#if>
			<#if (ra.approveRank==1 && ra.approveLevel>nodeFrom && !reachToNextLevel1)>
				<#assign reachToNextLevel1=true>
			</#if>
			<#if (ra.approveRank==2 && ra.approveLevel>nodeFrom && !reachToNextLevel1)>
			<tr style="height: 30px;" class="js_${parentId}">
				<#if ra.approveLevel !=preLevel >
				<#assign index2=index2+1>
				</#if>
				<td colspan="5">
					<div>
					<table class="approveTable" style="<#if ra.approveRank = 2>margin-right:-40px;</#if>float:right;">
					<tr class="
							<#if ra.remark==null>bottom </#if>
							<#if isCur==1>curTr<#elseif ra.approveOptionCd==1>passTr<#else>futureTr</#if> 
							<#if !(ra.isDefault!true)>trDefault</#if>
						">
						<td align="center" style="width:60px">${index}-${index2}</td>
						<td class="approveName" <#if !(ra.isDefault!true)>style="color:red;"</#if>>${ra.userName}</td>
						<td <#if !(ra.isDefault!true)>style="color:red;"</#if>>
							<#if ra.approveDate!=null>
							<#assign approveDateStr=ra.approveDate?string("yy-MM-dd HH:mm") >
							<#else>
							<#assign approveDateStr="">
							</#if>
							(${approveDateStr}<#if (ra.groupNodeCd==39 || ra.nodeCd==97)>${mapOptionZyps[ra.approveOptionCd]}<#else>${mapOption[ra.approveOptionCd]}</#if>)
							<#if isMyApprove==1 && ra.userCd!=curUserCd && ra.approveDate!=null>
								<span class="noprint">
									<a class="approveLine" href="javascript:agreePerson('${ra.userName}');">同意</a>
								</span>
							</#if>
							<#if (ra.userCd==curUserCd && ra.approveOptionCd!=-1)>
							<span class="divApppendRemark noprint" id="${ra.resApproveNodeId}"><span><a class="approveLine" href="javascript:void(0);">追加</a></span></span>											
							</#if>
						</td>
						<#assign nodeName>${codeNameUtil.getResNodeNameByCd(ra.nodeCd)}<#if ra.groupNodeCd!=null>/${codeNameUtil.getResNodeNameByCd(ra.groupNodeCd)}</#if></#assign>
						<td <#if !(ra.isDefault!true)>style="color:red;"</#if> class="ellipsisDiv_full" title="${ra.workDutyDesc}">
							<#if templetCd!=9>${nodeName}.</#if>${ra.workDutyDesc}
						</td>
						<td <#if !(ra.isDefault!true)>style="color:red;"</#if> class="ellipsisDiv_full" title="${nodeName}">
							&nbsp;
						</td>
						<#assign preLevel=ra.approveLevel>
					</tr>
					</table>
					</div>
				</td>
			</tr>
			<tr class="approveOptionTr <#if ra.remark!=null>bottom</#if> js_${parentId}">
			<td></td>
			<td colspan="4" style="padding-left:60px;">
				<#if ra.remark!=null>
					<div style="clear:both;white-space:pre-wrap;" class="approveOption">${ra.remark}</div>
				</#if>
		<div style="padding:0px 10px;">
			<table class="msgTable" style="width:100%;">
				<#list resApproveMessages as item>
					<#if (item.resApproveNodeId==ra.resApproveNodeId)>
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
			</td>
			</tr>
			<tr id="trAppendRemark_${ra.resApproveNodeId}" style="display:none;" class="noprint js2_${parentId}">
				<td colspan="5">
					<table style="border:none;" border="0" width="100%" >
					<tr>
					<td>
					<textarea style="width:99%;height:51px;" id="approveRemark_append_${ra.resApproveNodeId}"></textarea>
					</td>
					<td width="60">
					<input class="btn_blue_55_55" type="button" value="完成追加" style="border:none;" onclick='doCompleteAppend("${ra.resApproveNodeId}");' />
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</#if>
		</#list>
			<#assign nodeFrom=ra.approveLevel>
			<#if (index2>0) >
				<script language="javascript">$("#span_level1_${ra.resApproveNodeId}").html("+${index2}个二级审批人").show();</script>
			</#if>
			</#if>
		</#list>
	</table>
</div>
<#if isMyApprove==1>
	<div style="margin-left: 5px;" class="noprint">
		<table width="100%" style="table-layout: auto;">
			<tr class="detailTr">
				<td style="color: #363636;" valign="top" colspan="2">
					<div style="padding-top:10px;padding-left:15px;">
					审批意见<span style="color:red;" >（请在这里填写有效的审核/审批意见）</span>:
					</div>
				</td>
			</tr>
			<tr class="detailTr">
				<td style="padding:10px;">
					<textarea style="width:100%;height:51px;" id="approveRemark"/>
				</td>
				<td style="width:370px;">
					<#if isMyApprove==1>
						<#if myMod=='HQ'||nodeCd=='101'>
							  <input class="btn_blue_55_55" type="button" value="同意"  class="btn_agree" style="border:none;" onclick="doComplete();" />
							  <#if backFlg==null || backFlg=='1'>
							  <input type="button" title="资料不齐/填写错误" value="驳回/资料不齐" style="border:1px solid #5b0c0c;width:100px;height:55px; background-color:#ac2727;color: #FFF;cursor:pointer; " onclick="doProcessError2();" />
							  </#if>
							
						<#elseif myMod=='publish'>
							<input class="btn_blue_55_55" type="button" value="发布完成"  class="btn_agree" style="border:none;" onclick="doComplete();" />
						<#else>
							<input type="hidden" name="signFile" id="signFile" value="${signFile}"/>
							<#assign agreeDisplay='完成'/>
							<input type="hidden" id="idAddNode"/>
						     <#if approveUserCd2 !=null && (approveUserCd2?index_of(curUserCd)!=-1)>
							        <input class="btn_blue_55_55" type="button" value="同意"  class="btn_agree" style="border:none;" onclick='doCd2Agree("0");' />
							        <input class="btn_red_55_55" type="button" value="不同意"  class="btn_reject" style="border:none;" onclick='doCd2Agree("-1");' />
							  <#else>
							      <input class="btn_blue_55_55" type="button" value="${agreeDisplay}"  class="btn_agree" style="border:none;" onclick='doAgree("${nodeCd}","${curUserCd}","${isJcw}");' />
							  </#if>
							<#if backFlg==null || backFlg=='1'>
							<input class="btn_red_55_55"  type="button" value="驳回"  class="btn_reject" style="border:none;" onclick="showRejectDialog('${resApproveInfoId}',event);" />
							</#if>
							<#if permission.resPerror>
							<input class="btn_red_55_55"  type="button" value="流程错误"  class="btn_reject" style="border:none;" onclick="doProcessError();" />
							</#if>
							<#if permission.resTomeeting>
							<input class="btn_blue_55_55" type="button" value="上会" title="直接发起决策会" class="btn_agree" style="border:none;" onclick='doProcessJcw();' />
							</#if>
							<#if templetCd==9>
							<#if permission.resToceo>
							<input class="btn_blue_55_55" type="button" value="执行总裁" title="直接送至执行总裁" class="btn_agree" style="border:none;" onclick='toCeo();' />
							</#if>
							<#if permission.resTopresident>
							<input class="btn_blue_55_55" type="button" value="总裁" title="直接送至总裁" class="btn_agree" style="border:none;" onclick='toPresident();' />
							</#if>
							</#if>
						</#if>
					</#if>	
				</td>
			</tr>
		</table>
	</div>
</#if>
<script type="text/javascript"> 
	$(".divApppendRemark").toggle(function(){
		var approveNodeId =$(this).attr("id");
		$(this).children().children().html("取消");
		$("#trAppendRemark_"+approveNodeId).show();
		$("#approveRemark_append_"+approveNodeId).focus();
	},function(){
		var approveNodeId =$(this).attr("id");
		$(this).children().children().html("追加");
		$("#trAppendRemark_"+approveNodeId).hide();
	});
	function doCompleteAppend(approveNodeId){
		$.post("${ctx}/res/res-approve-info!appendRemark.action",{
				approveRemark_append:$("#approveRemark_append_"+approveNodeId).val(),
				approveNodeId:approveNodeId,
				id:'${resApproveInfoId}'
			},
			 function(result) {
			 $("#divApprove").html(result);
		});
	}
	$(".approveOption").highlight(["不同意","拒绝"]);
	if($.browser.msie && $.browser.version>8.0||!$.browser.msie){
		$(".approveOption").each(function(i){
			ajustHeight(this,3);
		 });
	}
	//显示/隐藏二级审批人
	function showOrHideLevel2(parentId){
		var ifShowLevel2 = $("#tr_level1_"+parentId).attr("ifShowLevel2");
		if(false==ifShowLevel2){
			$(".js_"+parentId).show();
			$(".js_"+parentId).css("visibility","visible");
		}else{
			$(".js_"+parentId).hide();
			$(".js_"+parentId).css("visibility","hidden");
		}
		$("#tr_level1_"+parentId).attr("ifShowLevel2",!ifShowLevel2);
	}
</script>

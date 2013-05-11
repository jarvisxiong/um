<#assign ctx=request.contextPath>
<#assign curUserCd=stack.findValue('@org.springside.modules.security.springsecurity.SpringSecurityUtils@getCurrentUiid()')>
<div class="list_header2"><span>审批记录</span></div>
<div style="padding:0px 5px;margin:3px;font-size:16px;">
	<table class="approveTable" id="showTableApproveRec">
				<col width="20px"/>
				<col/>
				<#assign index=0>
				<#assign preLevel=-1>
		<#list resApproveNodes as ra>
			<#assign isCur=0>
			<#if ((nodeCd==ra.groupNodeCd || nodeCd==ra.nodeCd) && ra.approveOptionCd ==-1 && ra.approveLevel==approveLevel)>
			<#assign isCur=1>
			</#if>
			<tr style="height: 55px; " class="<#if ra.remark==null>bottom </#if><#if isCur==1>curTr<#elseif ra.approveOptionCd==1>passTr<#else>futureTr</#if> ">
				<#if ra.approveLevel !=preLevel >
				<#assign index=index+1>
				</#if>
				<td align="center">${index}</td>
				<td style="font-size:16px;">
					<div style="float:left;">
					<span class="chk_selected">${ra.userName}</span>
					<#if ra.approveDate!=null>
					<#assign approveDateStr=ra.approveDate?string("yy-MM-dd HH:mm") >
					<#else>
					<#assign approveDateStr="">
					</#if>
					(${approveDateStr}<#if (ra.groupNodeCd==39 || ra.nodeCd==97)>${mapOptionZyps[ra.approveOptionCd]}<#else>${mapOption[ra.approveOptionCd]}</#if>)
					<#if isMyApprove==1 && ra.userCd!=curUserCd && ra.approveDate!=null>
						<span class="noprint">
							<a class="approveLine" href="javascript:agreePerson('${ra.userName}');" style="font-size:16px;">同意</a>
						</span>
					</#if>
					<#if (ra.userCd==curUserCd && ra.approveOptionCd!=-1)>
					<span class="divApppendRemark noprint" id="${ra.resApproveNodeId}"><span><a class="approveLine" href="javascript:void(0);" style="font-size:16px;">追加</a></span></span>											
					</#if>
					&nbsp;
					</div>
					<div style="float:left;">
					<span style="font-size:16px;">
					${ra.workDutyDesc}
					<#assign nodeName>
					${mapNode[ra.nodeCd]}<#if ra.groupNodeCd!=null>/${mapNode[ra.groupNodeCd]}</#if>
					</#assign>
					</span>
					&nbsp;
					</div>
					<div style="float:left;">
					<span>${nodeName}</span>
					<div>
					
				</td>
				
				<#assign preLevel=ra.approveLevel>
			</tr>
			<tr class="approveOptionTr <#if ra.remark!=null>bottom</#if>">
			<td></td>
			<td>
				<#if ra.remark!=null>
				<pre style="white-space:pre-wrap;font-size:16px;" class="approveOption">${ra.remark}</pre>
				</#if>
			</td>
			</tr>
			<tr id="trAppendRemark_${ra.resApproveNodeId}" style="display:none;" class="noprint">
				<td colspan="2">
					<table style="border:none;" border="0" width="100%" >
					<tr>
					<td>
					<textarea style="width:99%;height:51px;" id="approveRemark_append_${ra.resApproveNodeId}"></textarea>
					</td>
					<td width="60">
					<input class="btn_blue_55_55" style="height: 52px!important;line-height: 52px!important;width:65px!important;" type="button" value="完成追加" style="border:none;" onclick='doCompleteAppend("${ra.resApproveNodeId}");' />
					</td>
					</tr>
					</table>
				</td>
			</tr>
		</#list>
	</table>
</div>
<#if isMyApprove==1>
	<div style="margin-left: 0px;" class="noprint">
		<table style="width=device-width;table-layout: auto;">
			<col width="200px"/>
			<col/>
			<tr class="detailTr">
				<td style="color: #363636;" valign="top" colspan="2">
					<div style="padding-top:10px;padding-left:0px;">
					【审批意见】<span style="color:red;" >(请在这里填写有效的审核/审批意见)</span>
					</div>
				</td>
			</tr>
			<tr class="detailTr">
				<td style="padding-left:5px;padding-top:5px;">
					<textarea style="width:200px;height:51px;color:#5A5A5A;font-size:14px;" id="approveRemark" />
				</td>
				<td style="align:left;padding-left:5px;padding-top:5px;padding-right: 5px;">
					<#if isMyApprove==1>
						<#if myMod=='HQ'||nodeCd=='101'>
						<input class="btn_blue_55_55" type="button" value="完成"  style="border:none;height: 52px;line-height: 52px;"   onclick="doComplete();" />
						<#else>
						<#assign agreeDisplay='完成'/>
						<input class="btn_blue_55_55" type="button" value="${agreeDisplay}"  style="border:none;height: 52px;line-height: 52px;"  onclick='doAgree("${nodeCd}","${curUserCd}","${isJcw}");' />
						</#if>
					</#if>
				</td>
			</tr>
			<tr>
				<td style="margin-right: auto;margin-left: auto;line-height:40px;padding-left:5px;" colspan="2">
					<#if isMyApprove==1>
						<#if myMod=='HQ'||nodeCd=='101'>
							
							<#if backFlg==null || backFlg=='1'>
							<input type="button" value="驳回/资料不齐" style="border:1px solid #5b0c0c;width:100px;height:35px; background-color:#ac2727;color: #FFF;cursor:pointer; " onclick="doProcessError2();" />
							</#if>
							
						<#elseif myMod=='publish'>
							<input class="btn_blue_55_55" type="button" value="发布完成"  class="btn_agree" style="border:none;" onclick="doComplete();" />
						<#else>
							<input type="hidden" name="signFile" id="signFile" value="${signFile}"/>
							
							<input type="hidden" id="idAddNode"/>
							<#if permission.resTomeeting>
							<input class="btn_blue_55_55" type="button" value="上会" class="btn_agree" style="border:none;" onclick='doProcessJcw();' />
							</#if>
							<#if test="templetCd==9">
							<#if permission.resToceo>
							<input class="btn_blue_55_55" type="button" value="执行总裁" class="btn_agree" style="border:none;" onclick='toCeo();' />
							</#if>
							<#if permission.resTopresident>
							<input class="btn_blue_55_55" type="button" value="总裁" class="btn_agree" style="border:none;" onclick='toPresident();' />
							</#if>
							</#if>
							
							<#if backFlg==null || backFlg=='1'>
							<input class="btn_red_55_55"  type="button" value="驳回"  class="btn_reject" style="border:none;" onclick="showReject();" />
							</#if>
							<#if permission.resPerror>
							<input class="btn_red_55_55"  type="button" value="流程错误"  class="btn_reject" style="border:none;" onclick="doProcessError();" />
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
</script>

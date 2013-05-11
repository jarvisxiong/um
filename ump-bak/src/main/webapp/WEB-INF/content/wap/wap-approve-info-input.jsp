<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE>
<%@page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/global.jsp"%>
<head>
<%@ include file="/common/meta.jsp"%>
</head>

<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<s:set var="isJcw">${nodeCd==30||nodeCd==31||nodeCd==32||nodeCd==33||nodeCd==34||nodeCd==35}</s:set>
<input type="hidden" id="nodeCd" value="${nodeCd}" />
<input type="hidden" id="curUserCd" value="${curUserCd}" />
<input type="hidden" id="userCd" value="${userCd}" />
<input type="hidden" id="isJcw" value="${isJcw}" />
<input type="hidden" id="isAcc" value="${isAcc}" />
<input type="hidden" name="signFile" id="signFile" value="${signFile}" />
<input type="hidden" name="signFileX" id="signFileX" value="${signFileX}" />
<s:hidden id="isModified" value="false" />

<div id="divAll" style="overflow: hidden;">

<div class="toolBar">
	<%--btn_blue 蓝色样式 start--%>
	<s:if test="resApproveInfoId!=null">
		<input id="btnApproveHis" type="button" class="btn_blue"
			style="width: 70px;" onclick="viewApproveHis()"
			value="审批历史" />
	</s:if>
	<s:if test="publish && templetCd!=9">
		<input type="button" class="btn_blue" style="width: 70px;" onclick="viewSteps('${authTypeCd}')" value="所有流程" />
	</s:if>
	<s:if test="timeLimit!=null&&timeLimit>0">
	<s:if test="isMyApprove==1">
		<s:set var="canSeeDelay">true</s:set>
	</s:if>
	<security:authorize ifAnyGranted="A_QZSP_ADMIN">
		<s:set var="canSeeDelay">true</s:set>
	</security:authorize>
	<s:if test="#canSeeDelay=='true'">
		<input id="btnDelayHistory" type="button" class="btn_blue"
			style="width:100px;display:<s:if test="resApproveDelaies.size>0">inline;</s:if><s:else>none;</s:else>"
			onclick="viewDelayApproveHistory()"
			value="查看延期申请" />
	</s:if>
	</s:if>
	<%--btn_blue 蓝色样式 end--%>
	
	<%--btn_green 绿色样式 start --%>
	<input type="button" value="返回" class="btn_green" onclick="doReturn();" />
	
	<s:if test="isAcc==1||isMyApprove==1||isShare==1">
		<input type="button" value="共享" class="btn_green" id="btnShare" onclick="doShare();" />
		<input type="hidden" id="shareMsg" name="shareMsg" />
	</s:if>
	
	<s:if test="publish || statusCd==1">
	<s:if test="isMyApprove==1&&nodeCd!='101'">
		<%--权限:跟踪人整理 --%>
		<s:if test="myMod=='FQ'">
			<input type="button" value="发往决策层" class="btn_green" style="width: 85px;" onclick="doComplete();" />
			<input type="button" value="发起决策会" class="btn_green" style="width: 85px;" onclick="doMeeting();" />
		</s:if>
	</s:if>
	</s:if>
	
	<c:if test="${ 'xiaoqp'==curUserCd || ((isMyApprove==1||curUserCd==contFollower)&&nodeCd == 21)}">
		<input type="button" value="肖总签字" class="btn_green"
			style="width: 70px;"
			onclick="showUploadChiefAttachDialog('${resApproveInfoId}','resChiefXiao',event);" />
		<span style="color: red; display: none;" id="chiefXiaoHasAnswer">肖总签字已上传</span>
	</c:if>
	
	<c:if test="${ 'xujk'==curUserCd || ((isMyApprove==1||curUserCd==contFollower)&&nodeCd == 21)}">
		<input type="button" value="总裁签字" class="btn_green"
			style="width: 70px;"
			onclick="showUploadChiefAttachDialog('${resApproveInfoId}','resChief',event);" />
		<span style="color: red; display: none;" id="chiefHasAnswer">总裁签字已上传</span>
	</c:if>
	<%--btn_green 绿色样式 end --%>
	
	
	<%--btn_red  红色样式 start --%>
	<s:if test="publish || statusCd==1">
	<s:if test="isCanRollback==1">
		<input type="button" value="追回" class="btn_red" onclick="doRollback();" />
	</s:if>
	<s:if test="statusCd==2 && completeUser == #curUserCd">
		<security:authorize ifAnyGranted="A_RES_BACK2LAST">
		<input type="button" value="追回完成" class="btn_red" style="width: 70px;" onclick="back2Last();" />
		</security:authorize>
	</s:if>
	
	</s:if>

	
	<!-- 功能区域:上传肖总意见扫描件" -->
	<s:if test="signFileX != null">
		<span style="color: red;" id="chiefXiaoHasAnswerEnter">肖总签字已上传</span>
	</s:if>
	
	<!-- 功能区域:上传"总裁"意见扫描件" -->
	<s:if test="signFile != null">
		<span style="color: red;" id="chiefHasAnswerEnter">总裁签字已上传</span>
	</s:if>
	
	<%-- --%>
	<s:if test="timeLimit!=null&&timeLimit>0">
	<s:if test="isMyApprove==1">
		<input id="btnDoApproveDelay" type="button" value="申请延期"
			class="btn_red" onclick="doApproveDelay();"
			style="width:70px;display:<s:if test="gbDelay == null">inline</s:if><s:else>none</s:else>;" />
		<input id="btnCancelApproveDelay" type="button" value="取消延期"
			class="btn_red" onclick="cancelApproveDelay();"
			style="width:70px;display:<s:if test="gbDelay == null">none</s:if><s:else>inline</s:else>;" />
	</s:if>
	<%-- --%>
	
	</s:if>
	<%--btn_red 红色样式 end --%>
	
</div>

<s:set var="curNodeCd">
	<s:if test="nodeCd==99">
	</s:if>
	<s:else>
	${nodeCd}
	</s:else>
</s:set>
<s:set var="curLevel">${approveLevel}</s:set>
<div style="padding-top: 0px; overflow-y: auto;" id="detailPanelDiv">
<div class="div_num">
			<div class="wap_title" style="float:left;">搜索号:
				<span class="chk_selected">${displayNo}</span>
				&nbsp;
			</div>
			<div class="wap_title" style="float:left;">发起人:
				<span class="chk_selected">${userName}</span>
				&nbsp;
			</div>
			<div class="wap_title" style="float:left;">发起日期:
				<span class="chk_selected"><s:property value="startDate" /></span>
				&nbsp;
			</div>
			<div class="wap_title" style="float:left;">
				<s:if test="isMyApprove==1">
				<span style="color: red;">${timeReduce}</span>
				</s:if>
			</div>
			<div style="clear: both;"></div>
			
</div>
<div class="main_input_content" id="templet">
	<div align="left" >
	<span class="subject">${resAuthType.displayName}</span>
	</div>
	<div class="div_hr"></div>
	<jsp:include page="/WEB-INF/content/wap/templet/${templetFileName}">
	<jsp:param value="${nodeCd}" name="nodeCd" />
	</jsp:include>
</div>
<!-- 若已创建申请 -->
<s:if test="resApproveInfoId!=null">
	<s:if test="delayApproveUser!=null">
	<%-- 申请延期描述 --%>
	<div id="resDelayProcess" class="noprint" style="clear: both; padding-top: 10px; padding-bottom: 10px;"></div>
	<div id="resDelayProcessTip" class="noprint" style="color: red; padding-left: 20px"></div>
	</s:if>
	<!-- 附件列表 -->
	
	<div class='noprint' id="attach_files_div"></div>

	<div style="clear: both; padding-bottom: 10px;" id="divApprove">
	</div>
	<div class="noprint" style="height: 4px;background-color: #CCCCCC;"></div>
	<div class="noprint divNoPrint">
		<s:if test="signFile != null">
		<div class="noprint" id="chiefCopyDiv" style="margin-bottom: 30px;">
			<script language="javascript">
				showChiefCopyDiv('${resApproveInfoId}','resChief');
			</script>
		</div>
		</s:if>
		<s:if test="signFileX != null">
		<div class="noprint" id="chiefXiaoCopyDiv" style="margin-bottom: 30px;">
			<script language="javascript">
				showChiefCopyDiv('${resApproveInfoId}','resChiefXiao');
			</script>
		</div>
		</s:if>
		<div class="noprint">
	
		<div style="height: 10px; line-height: 10px;">
		</div>
		</div>
		
		
		<div id="resSharedList" class="noprint"></div>
		
		<div id="resPushList" class="noprint" <s:if test="pushUserNames==null">style="display:none;"</s:if>>
			<div class="list_header"><span style="font-size:14px;">已推送给</span></div>
			<div id="divPushUsers" style="margin-left: 20px;line-height:24px;padding-bottom:8px;color:#0167a2;">
				<s:property value="pushUserNames"/>
			</div>
		</div>
		
		<div class="noprint">
		<div class="list_header">
			<span style="font-size:18px;">留言记录</span>
		</div>
		<div id="msgContent" style="paddig: 5px;">
		</div>
		</div>
	</div>
</s:if>
<s:else>
	<div class='noprint' id="attach_files_div" style="padding-left: 5px;">
	</div>
</s:else>

</div>
</div>


<div id="delayWindowDiv"
	style="position: absolute; width: 300px; height: 300px; top: 140px; left; 0 px; display: none; background-color: #fff; border: 1px solid #000; padding: 0;"></div>

<!-- 加载附件列表 -->
<script type="text/javascript">
	loadApprove();
	loadAttachment('${resApproveInfoId}', '${myMod}', '${lockUser}','${contFollower}');
	loadDelay();
	reloadMsg();
	$("#delayWindowDiv").css("left", ($(document).width() - 500) / 2);
	shareUserNames='${shareUserNames}';
	shareUserCds='${shareUserCds}';

	$("input[type='checkbox']").click(function(){
		return false;
	});
	$("input[type='checkbox']").each(function(i){
		if ($(this).attr('checked')){
			$(this).next().next().addClass('chk_selected');
		}
	});
	function loadAttachment(resApproveInfoId,myMod,lockUser,contFollower){
		var nodeCd=$('#nodeCd').val();
		var userCd=$('#userCd').val();// 发起人
		var isJcw=eval(nodeCd==30||nodeCd==31||nodeCd==32||nodeCd==33||nodeCd==34||nodeCd==35);
		var data = {
				bizEntityId : resApproveInfoId,
				myMod : myMod,
				lockUser : lockUser, // 当前锁定合同的用户
				resUserCd : contFollower, // 跟踪人
				nodeCd : nodeCd, // 当前节点
				userCd : userCd  // 发起人
			};
		$.post(_ctx+"/wap/wap-attachment!resShow.action",data,
				function(data) {
					$("#attach_files_div").html(data);
				});
	}
</script>


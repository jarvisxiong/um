<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<%@ include file="/common/global.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%><head>

<%@ include file="/common/meta.jsp"%>
</head>


<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<s:set var="isJcw">${nodeCd==30||nodeCd==31||nodeCd==32||nodeCd==33||nodeCd==34||nodeCd==35}</s:set>
<input type="hidden" id="nodeCd" value="${nodeCd}" />
<input type="hidden" id="curUserCd" value="${curUserCd}" />
<input type="hidden" id="userCd" value="${userCd}" />
<input type="hidden" id="isJcw" value="${isJcw}" />
<input type="hidden" id="isAcc" value="${isAcc}" />
<input type="hidden" id="templetCd" value="${templetCd}" />
<input type="hidden" name="signFile" id="signFile" value="${signFile}" />
<input type="hidden" name="signFileX" id="signFileX" value="${signFileX}" />
<s:hidden id="isModified" value="false" />

<div id="divAll">
<s:set var="isAdmin" value="0"/>
<security:authorize ifAnyGranted="A_ADMIN">
	<s:set var="isAdmin" value="1"/>
</security:authorize>
<s:set var="isResAdmin" value="0"/>
<security:authorize ifAnyGranted="A_QZSP_ADMIN">
	<s:set var="isResAdmin" value="1"/>
</security:authorize>
<div class='noprint' id="funcPanelDiv">

	<%--只有“确认付款”角色的用户，才允许操作 --%>
	<security:authorize ifAnyGranted="A_RES_SPEC_CON_PAY">
	<s:if test="statusCd == 2 && templateBean.specialApproveBillId != '' && templateBean.specialApproveBillId != null">
	<div style="float:left;display: inline;">
		<input type="button" id="btnConfirmPay" style="display:none;" class="btn_blue" onclick="confirmPayedMoney(this,'${resApproveInfoId}')" value="确认付款" />
		<span id="spanConfirmTip" style="display:none;">当前网批: 已确认付款!</span>
		<script type="text/javascript">
			$(function(){
				var url = '${ctx}/res/res-bean-special-record!isConfirmFlg.action';
				$.post(url, {payResId: '${resApproveInfoId}'}, function(result){
					if('1' == result){
						$("#btnConfirmPay").hide();
						$('#spanConfirmTip').show();
					}
					else if('0' == result){
						$("#btnConfirmPay").show();
					}
					else{
						alert(result);
					}
				});
			});
			function confirmPayedMoney(dom, payResId){
				if(!window.confirm('确认付款?')){
					return;
				}
				var url = '${ctx}/res/res-bean-special-record!confirmResPay.action';
				$.post(url, {payResId: payResId}, function(result){
					if('success' == result){
						alert('确认付款成功!');
						$(dom).hide();
						$('#spanConfirmTip').show();
					}else{
						alert(result);
						$('#spanConfirmTip').hide();
					}
				});
			}
		</script>
	</div>
	</s:if>
	</security:authorize>

	<%--btn_blue 蓝色样式 start--%>
	<s:if test="publish || statusCd==1 || statusCd==2">
	<%--admin或者发起人可以推送--%>
	<s:if test="statusCd==2 && (#curUserCd==userCd || #isAdmin==1)">
		<input type="button" value="推送" class="btn_blue" id="btnPush" />
		<input type="hidden" id="pushUserNames" name="pushUserNames" value="${pushUserNames}" />
		<input type="hidden" id="pushUserCds" name="pushUserCds" value="${pushUserCds}" />
	</s:if>
	</s:if>
	<%--特别申请不显示‘导入台账’‘审批历史’‘推送退回’按钮 --%>
	<s:if test="moduleTypeCd!=2">
	<!-- 招标流程结束后转入合同流程 -->
	<s:if test="(must2Cont && statusCd==2 && userCd==#curUserCd) || #isAdmin==1">
		<input type="button" id="btnImport2Cont" value="导入台账" class="btn_blue" onclick="doImport();" />
	</s:if>
	<s:if test="resApproveInfoId!=null">
		<input id="btnApproveHis" type="button" class="btn_blue" onclick="viewApproveHis()" value="审批历史" />
	</s:if>
	<s:if test="pushUserCds!=null&&pushUserCds!=''&&pushUserCds.contains(#curUserCd)&&userCd!=#curUserCd">
		<input id="btnPushBack" type="button" class="btn_blue"
			style="width: 70px;" onclick="backToPusher()"
			value="推送退回" />
	</s:if>
	</s:if>
	<%--btn_blue 蓝色样式 end--%>
	
	<%--btn_green 绿色样式 start --%>
	<input type="button" value="返回" class="btn_green" onclick="doReturn();" />
	<s:if test="publish || statusCd==1">
	<s:if test="userCd==#curUserCd && statusCd!=2">
		<s:if test="statusCd==0||statusCd==3">
			<input type="button" value="存草稿" class="btn_green" onclick="doSave();" />
			<input type="button" value="附件" isAttach="true" class="btn_green"
				onclick="openAttachmentByModel('<s:text name="appAttachment.title"/>','resApproveInfo',''); return false;" />
		</s:if>
		<s:else>
		</s:else>
	</s:if>
	<s:if test="statusCd==1 || #isAdmin==1">
	
		<s:if test="#isJcw">
			<input type="button" value="附件" isAttach="true" class="btn_green"
				onclick="openAttachmentByModel('附件管理','resJcw','','<%=SpringSecurityUtils.getCurrentUiid()%>'); return false;" />
		</s:if>
		<s:else>
			<input type="button" value="其他附件" isAttach="true" class="btn_green" onclick="openAttachmentByModel('其他附件管理','resOther','','<%=SpringSecurityUtils.getCurrentUiid()%>'); return false;" />
		</s:else>
	</s:if>
	<s:if test="publish && templetCd!=9 && moduleTypeCd!=2">
		<input type="button" class="btn_green" onclick="viewSteps('${authTypeCd}')" value="查看流程" />
	</s:if>
	<s:if test="isMyApprove==1&&nodeCd!='101'">
		<%--权限:跟踪人整理 --%>
		<s:if test="myMod=='FQ'">
			<input type="button" value="发往决策层" class="btn_green" style="width: 85px;" onclick="doComplete();" />
			<input type="button" value="发起决策会" class="btn_green" style="width: 85px;" onclick="doMeeting();" />
		</s:if>
	</s:if>
	<s:else>
		<s:if test="isCanGiveMe==1">
			<input type="button" value="给我" class="btn_green" onclick="giveMe();" />
		</s:if>
	</s:else>
	<s:if test="isAcc==1||isMyApprove==1||isShare==1">
		<input type="button" value="共享" class="btn_green" id="btnShare" />
		<input type="hidden" id="shareUserNames" name="shareUserNames" value="${shareUserNames}" />
		<input type="hidden" id="shareUserCds" name="shareUserCds" value="${shareUserCds}" />
		<input type="hidden" id="shareMsg" name="shareMsg" />
	</s:if>
	</s:if>
	<s:if test="resApproveInfoId!=null">
		<input type="button" value="打印" class="btn_green" onclick="openPrint();" />
	</s:if>
	<c:if test="${'xiaoqp'==curUserCd || ((isMyApprove==1)&&nodeCd == 104)}">
		<input type="button" value="肖总签字" class="btn_green"
			style="width: 65px;"
			onclick="showUploadChiefAttachDialog('resChiefXiao',event);" />
		<span style="color: red; display: none;" id="chiefXiaoHasAnswer">肖总签字已上传</span>
	</c:if>
	
	<c:if test="${'xujk'==curUserCd || ((isMyApprove==1)&&nodeCd == 21)}">
		<input type="button" value="总裁签字" class="btn_green"
			style="width: 65px;"
			onclick="showUploadChiefAttachDialog('resChief',event);" />
		<span style="color: red; display: none;" id="chiefHasAnswer">总裁签字已上传</span>
	</c:if>
	<%--btn_green 绿色样式 end --%>
	
	
	<%--btn_red  红色样式 start --%>
	<s:if test="publish || statusCd==1">
	<s:if test="userCd==#curUserCd && statusCd!=2">
		<s:if test="resApproveInfoId!=null ">
			<s:if test="statusCd==0 || statusCd==3">
				<input type="button" value="删除" class="btn_red"
					onclick="doDelete();" />
			</s:if>
			<s:if test="statusCBidContractApproved==5">
				<input type="button" value="收回" class="btn_red"
					onclick="doReceive();" />
			</s:if>
		</s:if>
	</s:if>
	<s:if test="isCanRollback==1">
		<input type="button" value="追回" class="btn_red" onclick="doRollback();" />
	</s:if>
	<s:if test="statusCd==2 && completeUser == #curUserCd">
		<security:authorize ifAnyGranted="A_RES_BACK2LAST">
		<input type="button" value="追回完成" class="btn_red" onclick="back2Last();" />
		</security:authorize>
	</s:if>
	<s:if test="#isAdmin==1">
		<input type="button" value="删除" class="btn_red" onclick="doDelete();" />
		<s:if test="statusCd==2">
		<input type="button" value="追回完成" class="btn_red" onclick="back2Last();" />
		</s:if>
	</s:if>
	</s:if>
	<s:if test="isMyApprove==1">
		<s:set var="canSeeDelay">true</s:set>
	</s:if>
	<security:authorize ifAnyGranted="A_QZSP_ADMIN">
		<s:set var="canSeeDelay">true</s:set>
	</security:authorize>
	<s:if test="#canSeeDelay=='true'">
		<input id="btnDelayHistory" type="button" class="btn_red"
			style="width:100px;display:<s:if test="resApproveDelaies.size>0">inline;</s:if><s:else>none;</s:else>"
			onclick="viewDelayApproveHistory()"
			value="查看延期申请" />
	</s:if>
	<s:if test="publish && statusCd==1">
	<s:if test="#isAdmin==1">
		<input type="button" value="驳回" class="btn_red" onclick="showRejectDialog(event,'admin');" />
	</s:if>
	<s:if test="#curUserCd=='qgb'">
		<!--	企管部帐号可以驳回任何网批	-->
		<input type="button" value="驳回" class="btn_red" onclick="showRejectDialog(event,'admin');" />
	</s:if>
	</s:if>
	<%--btn_red 红色样式 end --%>
	<!-- 功能区域:上传肖总意见扫描件" -->
	<s:if test="signFileX != null">
		<span style="color: red;" id="chiefXiaoHasAnswerEnter">肖总签字已上传</span>
	</s:if>
	
<!-- 功能区域:上传"总裁"意见扫描件" -->
	<s:if test="signFile != null">
		<span style="color: red;" id="chiefHasAnswerEnter">总裁签字已上传</span>
	</s:if>
	
	<%--特别申请不显示‘申请延期’按钮 --%>
	<s:if test="timeLimit!=null&&timeLimit>0">
	<s:if test="isMyApprove==1 && moduleTypeCd!=2">
		<s:set var="timeReduceFloat" value="%{getTimeReduce(timeLimit,lastApproveDate)}"></s:set>
		<s:if test="#timeReduceFloat!=null">
		<s:if test="#timeReduceFloat>=6">
		<%--低于6小时不显示该按钮 --%>
		<input id="btnDoApproveDelay" type="button" value="申请延期"
			class="btn_red" onclick="doApproveDelay();"
			style="width:70px;display:<s:if test="gbDelay == null">inline</s:if><s:else>none</s:else>;" />
		</s:if>
		</s:if>
		
		<input id="btnCancelApproveDelay" type="button" value="取消延期"
			class="btn_red" onclick="cancelApproveDelay();"
			style="width:70px;display:<s:if test="gbDelay == null">none</s:if><s:else>inline</s:else>;" />
	</s:if>
	</s:if>
	
</div>

<s:set var="curNodeCd">
	<s:if test="nodeCd==99">
	</s:if>
	<s:else>
	${nodeCd}
	</s:else>
</s:set>
<s:set var="curLevel">${approveLevel}</s:set>
<div style="padding-top: 15px;" id="detailPanelDiv">
<div style="padding: 0px 5px 5px 25px;" id="div_position">
	<span style="color: #5a5a5a">位置：</span><span style="color: #5a5a5a">${modulePath}</span>
	<s:if test="approveCd!=null">
	<span style="color: #5a5a5a;margin-left: 20px;">编号：</span><span style="color: #5a5a5a">${approveCd}${serialNo}</span>
	</s:if>
</div>
<div style="padding: 0px 5px 30px 25px;">
<span style="color: blue;">查询号：</span><span style="color: blue;">${displayNo}</span>
<span>发起人：</span><span style="color: blue;">${userName}</span>
<s:if test="startDate!=null">
<span>发起日期：</span><span style="color: blue;"><s:property value="startDate" /></span>
</s:if>
<span>状态：</span><span style="color: blue;"><p:code2name mapCodeName="mapStatus" value="statusCd" /></span>
<s:if test="isMyApprove==1">
	<span style="color: red;">${timeReduce}</span>
</s:if>
<div style="float: right" id="divRandomNo"><span
	style="color: blue; margin-right: 25px;">${randomNo}</span></div>
</div>
<div class="main_input_content" id="templet">
	<div align="center" >
	<span class="subject">${resAuthType.displayName}</span>
	
	<s:if test="resAuthType.resAuthMsgs.size>0 && publish == true">
		<div class="res_tip">
			<div style="margin-left: 80px;">
				<ol>
					<s:iterator  value="resAuthType.resAuthMsgs" var="st" status="s">
					<li>${st.msgContent}</li>
					</s:iterator>
				</ol>
			</div>
		</div>
	</s:if>
	</div>
	<s:if test="userCd==#curUserCd && (statusCd==0||statusCd==3)">
		<jsp:include page="templet/${templetFileName}">
			<jsp:param value="1" name="isMy" />
			<jsp:param value="${nodeCd}" name="nodeCd" />
		</jsp:include>
	</s:if>
	<s:else>
	<jsp:include page="templet/${templetFileName}">
		<jsp:param value="0" name="isMy" />
		<jsp:param value="${nodeCd}" name="nodeCd" />
	</jsp:include>
	</s:else>
</div>
<s:if test="userCd==#curUserCd && statusCd!=2">
<s:if test="publish && (statusCd==0||statusCd==3)">
<div class='noprint'>
<div style="margin-top: -10px;padding: 5px; border-bottom: 1px solid #8C8F94;line-height: 30px;">
	<input type="button" <s:if test="templetCd==9 || templetCd==199 || templetCd==288 || moduleTypeCd==2"> value="发起" </s:if><s:else> value="填写审批人" </s:else>class="btn_blue_75_55"  id="btn_loadUser" onclick="doLoadUser();"/>
	<input type="button" value="编辑" class="btn_blue" id="btn_edit" style="display:none;" onclick="doEdit();"/>
	<input type="button" value="发起" class="btn_blue" id="btn_apply" style="display:none;" onclick="doSubmit();"/>
</div>
<div id="div_approve_user"></div>
</div>
</s:if>
</s:if>
<!-- 若已创建申请 -->
<s:if test="resApproveInfoId!=null">
	<%--特别申请不显示审批人和审批步骤 --%>
	<s:if test="moduleTypeCd!=2">
	<div id="resProccess" class="noprint">
	<div  class="list_header_img"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
	<div class="list_header2"><span>审批流程</span></div>
	<div id="resProcess" class="divProcess">
	<s:iterator value="nodeMap" var="st" status="s">
		<div class='<s:if test="#curNodeCd==#st.value && #curLevel==#st.key">divCurNode</s:if><s:else>divNode</s:else>' >
		${s.index+1}.<%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("#st.value")) %>&nbsp;<s:property value="mapNodesLevelInfo[#st.key]" /></div>
		<s:if test="!#s.last"> 
		<div>--&gt;</div>
		</s:if>
	</s:iterator>
	</div>
	<%-- 申请延期描述 --%>
	<s:if test="isMyApprove==1">
	</s:if>
	</div>
	<div id="resDelayProcess" class="noprint" style="clear: both; padding-top: 10px; padding-bottom: 10px;"></div>
	<div id="resDelayProcessTip" class="noprint" style="color: red; padding-left: 20px"></div>
	<!-- 附件列表 -->
	</s:if>
	<div class='noprint' id="attach_files_div"></div>
	<s:hidden id="uploadFlg" name="upload" />
	<%--普通用户不能看见特别费申请的流程 --%>
	<s:if test="moduleTypeCd!=2 || #isAdmin==1 || isMyApprove==1 || #isResAdmin==1">
	<div style="clear: both; padding-top: 10px; padding-bottom: 10px;" id="divApprove">
	</div>
	</s:if>
	<div class="noprint" style="height: 4px;background-color: #9db7c6;"></div>
	<div class="noprint divNoPrint">
		<s:if test="signFile != null">
		<div class="noprint" id="chiefCopyDiv" style="margin-bottom: 30px;">
			<script language="javascript">
				showChiefCopyDiv('resChief');
			</script>
		</div>
		</s:if>
		<s:if test="signFileX != null">
		<div class="noprint" id="chiefXiaoCopyDiv" style="margin-bottom: 30px;">
			<script language="javascript">
				showChiefCopyDiv('resChiefXiao');
			</script>
		</div>
		</s:if>
		<div class="noprint">
	
		<div style="height: 24px; line-height: 24px;">
		<div style="float: left; padding: 4px 10px 0 10px; overflow-x: hidden; height: 20px; line-height: 20px;">
		<img src="${ctx}/resources/images/res/noprint_16_16.gif"></img>
		</div>
		<div><span style="font-size: 12px; color: red; float: left;">以下内容属于不打印区域</span></div>
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
			<span>留言记录</span>
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
	style="position: absolute; width: 500px; height: 270px; top: 140px; left; 0 px; display: none; background-color: #fff; border: 1px solid #000; padding: 5px;"></div>
<s:if test="resApproveInfoId!=null">
<!-- 加载附件列表 -->
<script type="text/javascript">
	loadApprove();
	loadAttachment('${myMod}', '${lockUser}','${contFollower}');
	loadShare();
	loadDelay();
	reloadMsg();
	$("#delayWindowDiv").css("left", ($(document).width() - 500) / 2);
	$('#btnShare').userSelect( {
		muti : true,
		nameEId : 'shareUserNames',
		cdEId : 'shareUserCds',
		callback : function(map) {
			toShareMsgInput();
		}
	});
	$('#btnPush').userSelect( {
		muti : true,
		nameEId : 'pushUserNames',
		cdEId : 'pushUserCds',
		callback : function(map) {
			doPush();
		}
			
	});
	$('.res_tip').each(function(i){
		$(this).find("div:first").css({ "margin-left": "80px"});
		var initHeight = $(this).find("div:first").height();
		if( initHeight > 20){
			$(this).append('<div><div class="spread" onclick="spread();" style="display:none;">&nbsp;</div><div class="packup noprint" onclick="packup();">&nbsp;</div></div>');
			//packup();
		}
	});
	
</script>
</s:if>
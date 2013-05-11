<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<%@ include file="/common/global.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<head>
<%@ include file="/common/meta.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/res.css"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/css/resApprove.css" />
	
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/fileSuffixes.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/res/resApprove_0220.js"></script>
	<script type="text/javascript" src="${ctx}/js/res/resApproveInput_0220.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/res/resShow.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/res/resSingleUpload.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.highlight.js" ></script>
	
	<%--搜索供应商 --%>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	<title>网批明细-${titleName}</title>
</head>
<script type="text/javascript">
</script>

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

<div id="divAll">


<s:set var="curNodeCd">
	<s:if test="nodeCd==99">
	</s:if>
	<s:else>
	${nodeCd}
	</s:else>
</s:set>
<s:set var="curLevel">${approveLevel}</s:set>
<div style="padding-top: 15px;" id="detailPanelDiv">
<div style="padding: 0px 5px 5px 25px;">
	<span style="color: #5a5a5a">位置：</span><span style="color: #5a5a5a">${modulePath}</span>
	<s:if test="approveCd!=null">
	<span style="color: #5a5a5a;margin-left: 20px;">编号：</span><span style="color: #5a5a5a">${approveCd}${serialNo}</span>
	</s:if>
</div>
<s:if test="statusCd!=0">
	<div style="padding: 0px 5px 30px 25px;">
	<span style="color: blue;">查询号：</span><span style="color: blue;">${displayNo}</span>
	<span>发起人：</span><span style="color: blue;">${userName}</span>
	<span>发起日期：</span><span style="color: blue;"><s:property value="startDate" /></span>
	<span>状态：</span><span style="color: blue;"><p:code2name mapCodeName="mapStatus" value="statusCd" /></span>
	<s:if test="isMyApprove==1">
		<span style="color: red;">${timeReduce}</span>
	</s:if>
	<div style="float: right" id="divRandomNo"><span
		style="color: blue; margin-right: 25px;">${randomNo}</span></div>
	</div>
</s:if>
<div class="main_input_content" id="templet">
	<div align="center" >
	<span class="subject">${resAuthType.displayName}</span>
	<s:if test="resAuthType.resAuthMsgs.size>0">
		<div class="res_tip">
			<div>
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
<!-- 若已创建申请 -->
<s:if test="resApproveInfoId!=null">
	<div id="resProccess" class="noprint">
	<div  class="list_header_img"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
	<div class="list_header2"><span>审批流程</span></div>
	<div id="resProcess" class="divProcess">
	<s:iterator value="nodeMap" var="st" status="s">
		<div class='<s:if test="#curNodeCd==#st.value && #curLevel==#st.key">divCurNode</s:if><s:else>divNode</s:else>' >
		${s.index+1}.<%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("#st.value")) %></div>
		<s:if test="!#s.last"> 
		<div>--></div>
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
	<div class='noprint' id="attach_files_div"></div>
	<s:hidden id="uploadFlg" name="upload" />
	<s:if test="resCenterConfirmInfos.size>0">
		<div style="padding-left: 5px; clear: both;">
		<div class="list_header2"><span>配合中心</span></div>
		<div id="phCenterRecord"><jsp:include
			page="res-approve-info-confirm.jsp"></jsp:include></div>
		</div>
	</s:if>

	<div style="clear: both; padding-top: 10px; padding-bottom: 10px;" id="divApprove">
	</div>
	<div class="noprint" style="height: 4px;background-color: #9db7c6;"></div>
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

<!-- 加载附件列表 -->
<script type="text/javascript">
	loadDetailApprove();
	loadAttachment('${resApproveInfoId}', '${myMod}', '${lockUser}','${contFollower}');
	loadShare();
	loadDelay();
	reloadDetailMsg();

	function loadDetailApprove(){
		var resApproveInfoId = $('#resApproveInfoId').val();  
		if (resApproveInfoId!=''){
			var data={id:resApproveInfoId};
			$.post(_ctx+"/res/res-approve-info!loadApprove.action",data,
					 function(result) {
				 $("#divApprove").html(result);
				});
		}
	}
	
	function reloadDetailMsg(content){
		var resApproveInfoId = $('#resApproveInfoId').val();
		if (resApproveInfoId!=''){
			var replyMsgId = $('#idReplyMsg').val();  
			var isShare = $('#isShare').val();  
			var data={id:resApproveInfoId,'referMsgId':replyMsgId};
			
			if(isNotEmpty(content)){
				data.content=content;
			}
			if(isNotEmpty(isShare)){
				data.isShare=isShare;
			}
			$.post(_ctx+"/res/res-approve-info!say.action",data,
					 function(result) {
					 $("#msgContent").html(result);
					 //清掉发布留言功能
					$("#showTable").remove();
				});
			
		
		}
	}
			
</script>
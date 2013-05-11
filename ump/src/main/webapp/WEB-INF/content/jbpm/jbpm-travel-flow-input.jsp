<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/content.css" type="text/css" />
<link href="${ctx}/css/userChoose.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
<script language="javascript" src="${ctx}/js/jquery.js"></script>
<script type=text/javascript src="${ctx}/js/common.js"></script>
<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
<script src="${ctx}/js/validate/PdValidate.js" type="text/javascript"></script>
<script src="${ctx}/js/common.js" type="text/javascript" type="text/javascript"></script>
<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/travel.js" type="text/javascript"></script>
<script src="${ctx}/js/jbpm/style.js" type="text/javascript"></script>
<script src="${ctx}/js/userChoose.js" type="text/javascript"></script>
<title>出差申请</title>
<script language="javascript">

	$(function(){
		config = {
			ctx : '${ctx}',
			isDesk : '${isDesk}',
			taskId : '${curTaskId}',
			statusCd : '${statusCd}',
			id : '${jbpmTravelApplyId}',
			executionId : '${executionId}',
			isMyTask : '${userCd}'=='<%=SpringSecurityUtils.getCurrentUiid()%>',
			isMyApprove : ('${approver}'+',').indexOf ('<%=SpringSecurityUtils.getCurrentUiid()%>'+',') != -1,
			styleCall : Style.replace
		};
		loadComment();

		$(".jbpmInputTitle").toggle(function() {
			$(this).next().next().hide();
			$(this).children(".arrow_down").attr("class","arrow_right");
		}, function(dom) {
			$(this).next().next().show();
			$(this).children(".arrow_right").attr("class","arrow_down");
		});
		if(config['styleCall'])config['styleCall']();
	});
	function applyNew(uiid){
		var viewUrl='${ctx}/jbpm/jbpm-travel-flow!input.action?taskId='+"&isDesk="+config['isDesk']+"&uiid="+uiid;
		window.location.href = viewUrl;
	}
	function getUserCallback(){
		applyNew($("#userCd").val());
	}
	//选择人员
	function chooseUser(){
		getMember('','','0','userName','userCd','1','1',getUserCallback);
		/*
		$.post(config['ctx'] + '/jbpm/jbpm-travel-flow!isAccredited.action', function(result) {
		      if (result=='true'){
		    	 var dom= getMember('','','0','userName','userCd','1','1',getUserCallback);
		    	 
		      }else{
		    	  
		      }
		 });
		 */
	}
</script>
</head>

<body>
<s:if test="isDesk!=1">
<%@ include file="/common/gridHeader.jsp" %>
</s:if>
<div class="jbpmInputTitle"><div class="arrow_down"></div><span>出差申请表</span></div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<div id="mainContent">
<fieldset>
<s:form id="inputFromTravel" action="jbpm-travel-flow!save.action" method="post" >
		<input type="hidden" name="isDesk" value="${isDesk}" />
		<input type="hidden" name="id" value="${jbpmTravelApplyId}" />
		<input type="hidden" name="taskId" value="${curTaskId}" />
		<input type="hidden" name="uiid" value="${uiid}" />
		<input type="hidden" name="centerCd" value="${centerCd}" />
		<s:hidden name="recordVersion"/>
		<s:hidden name="entityTmpId"/>
	<table class="editTable" cellspacing="5px">
		<tr align="left">
		<td  width="15%" align="right"><s:text name="jbpmJbpmTravelApply.userName"/>:</td>
		<td  width="20%">
		<input type="hidden"  id="userCd" name="userCd" value="${userCd}" />
		<input type="text" class="readonly" readonly="readonly" name="userName" id="userName"  value="${userName}" <s:if test="isAccredited=='true'&&(statusCd==2 || statusCd==-1 )"> style="cursor: pointer;" onclick="chooseUser();" </s:if> />
		</td>
		<td width="10%" align="right"><s:text name="jbpmJbpmTravelApply.deptCd"/>:</td>
		<td width="22.5%">
		<input type="hidden" readonly="readonly" id="deptCd" name="deptCd"  value="${deptCd}"  />
		<input type="text" class="readonly" id="deptName"  readonly="readonly" value="<%=CodeNameUtil.getDeptNameByCd(JspUtil
								.findString("deptCd"))%>"  />
		</td>
		<td  width="10%" align="right"><s:text name="jbpmJbpmTravelApply.positionCd"/>:</td>
		<td  width="22.5%"><input type="hidden"  id="positionCd" name="positionCd" value="${positionCd}" />
		<input type="text" class="readonly" id="positionName" readonly="readonly" value="<%=CodeNameUtil.getPositionNameByCd(JspUtil
								.findString("positionCd"))%>"/>
		</td>
		<td width="5px" rowspan="5"></td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmTravelApply.startDate"/>:</td><td><s:textfield validate="required" id="startDate" name="startDate" onfocus="WdatePicker()" cssClass="Wdate" /></td>
		<td align="right"><s:text name="jbpmJbpmTravelApply.endDate"/>:</td><td><s:textfield validate="required" id="endDate" name="endDate" onfocus="WdatePicker()" cssClass="Wdate" /></td>
		<td  align="right"><s:text name="jbpmJbpmTravelApply.travelPlace"/>:</td><td><input type="text" validate="len[0,20]required" id="travelPlace" name="travelPlace" value="${travelPlace}" /></td>
		</tr>
		<tr>
		<td align="right">随行人员:</td><td colspan="3">
		<s:if test="uiid==null">
		<s:set var="applyCenterOrgCd" ><%=SpringSecurityUtils.getCurrentCenterCd() %></s:set>
		</s:if>
		<s:else>
		<s:set var="applyCenterOrgCd" >${logicOrgCd} </s:set>
		</s:else>
		<input name="otherUserNames" onclick="getMember('other','${applyCenterOrgCd}');" id="otherUserNames" style="width:80%;cursor: pointer;" value="${otherUserNames }" readonly="readonly"/>
		&nbsp;<a onclick="clearMember('other');" class="mailBlueText">清空</a>
		<input type="hidden" id="otherUserCds" name="otherUserCds" value="${otherUserCds}"/>
		</td>
		<td align="right">交通方式:</td><td><s:select name="travelWay" validate="required" list="mapTravelWay" listKey="key" listValue="value"></s:select> </td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmTravelApply.travelReason"/>:</td>
		<td colspan="5"><s:textarea validate="len[0,500]required" id="travelReason" name="travelReason"/>
		</td>
		</tr>
		<tr>
		<td align="right"><s:text name="jbpmJbpmTravelApply.travelPlan"/>:</td>
		<td colspan="5"><s:textarea  validate="len[0,500]" id="travelPlan" name="travelPlan"/>
		</td>
		</tr>
	</table>
</s:form>
<div style="height: 5px;"></div>

<div class="toolBar"  align="center">
	<input type="button" class="btn btnSave" value="<s:text name="common.save" />" onclick="save();"/>
	<s:if test="statusCd==2 || statusCd==-1 ">
	<input type="button" class="btn btnApply" value="<s:text name="common.apply" />" onclick="apply();"/>
	<input type="button" class="btn btnCancel" value="<s:text name="common.cancel" />" onclick="cancel('${taskId}','${jbpmTravelApplyId}');"/>
	</s:if>
	<input type="button" class="btn btnAttach" value="<s:text name="common.attachment" />" onclick="openAttachment('<s:text name="appAttachment.title"/>','${jbpmTravelApplyId==null?entityTmpId:jbpmTravelApplyId}'); return false;"/>
	<s:if test="jbpmTravelApplyId!=null && statusCd==-1">
	<input type="button" class="btn btnDel" value="<s:text name="common.delete" />" onclick="doDelete('${jbpmTravelApplyId}');"/>
	</s:if>
	<input type="button" class="btn btnReturn" value="<s:text name="common.return" />" onclick="doReturn('${pageApprove.pageNo}','${searchScop}');"/>
</div>

</fieldset>
<s:if test="executionId!=null">
<div id="divComment">
</div>
</s:if>
<div style="height: 10px;"></div>
</div>

<div class="clear jbpmInputTitle">
<div class="arrow_down"></div><span>出差申请流程图</span>
</div>
<div style="background-color: #5a5a5a ;height:1px;margin-top: 0;padding-top: 0px;margin-bottom:5px; " ></div>
<fieldset>
<div id="JbpmImage" align="center">
<iframe width="530" height="530" frameborder="0" src="jbpm-travel-flow!track.action?executionId=${executionId}"></iframe>
</div>
</fieldset>

<s:if test="isDesk!=1">
<%@ include file="/common/gridFooter.jsp" %>
</s:if>
</body>
</html>

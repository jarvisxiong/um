<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<s:set var="approveUserName" ><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("approveUserCd"),";") %></s:set>
<s:set var="approveUserCd2Name" ><%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("approveUserCd2"),";") %></s:set>
<c:set var="userCount" ><%=StringUtils.countMatches(JspUtil.findString("approveUserCd"),";") %></c:set>
<c:set var="userCd2Count" ><%=StringUtils.countMatches(JspUtil.findString("approveUserCd2"),";") %></c:set>
<s:set var="authTypeName" ><%=CodeNameUtil.getResAuthTypeNameByCd(JspUtil.findString("authTypeCd")) %></s:set>
<s:set var="timeReduce" value="%{getTimeReduce(timeLimit,lastApproveDate)}"></s:set>
<s:set var="timeReduceString" value="%{getTimeReduceString(timeLimit,lastApproveDate)}"></s:set>
<s:set var="isAdmin" value="0"/>
<security:authorize ifAnyGranted="A_ADMIN">
	<s:set var="isAdmin" value="1"/>
</security:authorize>
<s:set var="isResAdmin" value="0"/>
<security:authorize ifAnyGranted="A_QZSP_ADMIN">
	<s:set var="isResAdmin" value="1"/>
</security:authorize>

<div class="res_row" onclick='
	<s:if test="moduleTypeCd == 2 && statusCd == 1 && creator == #curUserCd">
		<%-- 若是特别费用审批，且处于审批中，发起人不可以查看。 --%>
		alert("正在审批中...");
	</s:if>
	<s:else>
		$("#pdChilltip").remove();
		openDetail("${resApproveInfoId}","${page.pageNo}");
	</s:else>
'>
	<div class="row_top">
		<div class="max_width" style="max-width: 200px;font-weight: bold;" title="${titleName}">${titleName}</div>
		<div class="max_width" style="max-width: 200px;font-weight: bold;" title="【${authTypeName}】">【${authTypeName}】</div>
		<s:if test="moduleTypeCd!=2 || #isAdmin==1 || #isResAdmin==1 ">
	<s:if test="#curUserCd==approveUserCd && approveUserCd!=null ">
	 <s:if test="approveUserCd2 ==null">
	<div><div style="color: #1A9EDE;max-width: 150px;" class="max_width" title="${approveUserName}">${approveUserName}</div>
	<span>
	<c:if test="${userCount>0}">会签中</c:if>
	<c:if test="${userCount==0}">审批中</c:if>
	</span>
	</div>
	 </s:if>
	 <s:else>
	 <div><div style="color: #1A9EDE;max-width: 150px;" class="max_width" title="${approveUserCd2Name}">${approveUserCd2Name}</div>
	 <span>
 	<c:if test="${userCount>0}">会签中</c:if>
	<c:if test="${userCount==0}">审批中</c:if>
	</span>
	</div>
	 </s:else>
	</s:if>
	<s:if test="approveUserCd2.indexOf((#curUserCd).toString())!=-1 && approveUserCd2!=null ">
	<div><div style="color: #1A9EDE;max-width: 150px;" class="max_width" title="${approveUserCd2Name}">${approveUserCd2Name}</div>
	<span>
	<c:if test="${userCount>0}">会签中</c:if>
	<c:if test="${userCount==0}">审批中</c:if>
	</span>
	</div>
	</s:if>
	</s:if>
		<s:if test="statusCd==0"><div class="status_img status_new" title="新增"/></s:if>
		<s:if test="statusCd==1"><div class="status_img status_approving" title="审批中"/></s:if>
		<s:if test="statusCd==2"><div class="status_img status_finish" title="完成"/></s:if>
		<s:if test="statusCd==3"><div class="status_img status_back" title="驳回"/></s:if>
		<s:if test="shareStatusCd!=null">
		<s:if test="shareStatusCd==0"><div class="status_img status_share" title="已共享"/></s:if>
		<s:if test="shareStatusCd==1"><div class="status_img status_reply" title="已回复"/></s:if>
		</s:if>
		<s:if test="#timeReduce!=null">
		<s:if test="#timeReduce>0 && #timeReduce<=3"><div class="status_img status_nDelay" title="接近过期"/></s:if>
		<s:elseif test="#timeReduce<=0"><div class="status_img status_delay" title="已经过期"/></s:elseif>
		</s:if>
		</span>
		<span style="float:right;">
		<s:if test="filter_LIKES_statusCd == 2">
		<s:date name="completeDate" format="yy-MM-dd"/>
		</s:if>
		<s:else>
		<s:date name="lastApproveDate" format="yy-MM-dd"/>
		</s:else>
		</span>
	</div>
	<div class="row_down clear">
		<div style="color: #1A9EDE;">${displayNo}</div><div>${landProject}</div><div>${userName}</div>
		<span style="float:right;color: #1A9EDE">${timeReduceString}</span>
		<s:if test="attachFlg==1">
		<span style="float:right;" class="status_attach" title="有附件"/>
		</s:if>
	</div>
</div>

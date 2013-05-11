<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div class="mailDivBorder" style="background-color: #fff">
<div style="height:35px;padding-bottom: 5px;">
	<%-- 2011-10-10 禁用按钮
	<security:authorize ifNotGranted="A_EMAIL_READ_ONLY">
		<div class="btn_green_55_20" style="float:left;margin: 10px 0 0 10px;" onclick="newMail('${oaEmailBody.oaEmailBodyId}','reply','${oaEmailId}')">回复</div>
		<div class="btn_green_55_20" style="float:left;margin: 10px 0 0 10px;" onclick="newMail('${oaEmailBody.oaEmailBodyId}','replyAll','${oaEmailId}')">全部回复</div>
		<div class="btn_green_55_20" style="float:left;margin: 10px 0 0 10px;" onclick="newMail('${oaEmailBody.oaEmailBodyId}','forward')">转发</div>
		<div class="btn_red_35_20" style="float:left;margin: 10px 0 0 10px;" onclick="deleteMail('delete','${oaEmailBody.oaEmailBodyId}',${boxId},'${oaEmailId}')">删除</div>
	</security:authorize>
	 --%>
	<s:if test="searchFlg == 'true'">
		<div class="btn_green_55_20" style="float:left;margin: 10px 0 0 10px;" onclick="return2SearchDiv()">返回</div>
	</s:if>
	<s:else>
		<div class="btn_green_55_20" style="float:left;margin: 10px 0 0 10px;" onclick="getMailList(${boxId},false,true)">返回</div>
	</s:else>
</div>
<div>
<table class="readMailTable" id="readMailTable" border="0" width="100%" height="100%" cellpadding="0" cellspacing="0" >
	<tr class="mailBlueText">
		<td width="60px" style="padding-left:10px">主题:</td>
		<td limit="80" align="left">
		<s:if test="oaEmailBody.importLevelCd == '2'">
			<span style="color:#FF6600">[重要]</span>
		</s:if>
		<s:elseif test="oaEmailBody.importLevelCd == 3">
          	<span style="color:#FF0000">[非常重要]</span>
	    </s:elseif>
		<s:property value="oaEmailBody.subject"/>
		</td>
	</tr>
	<tr>
		<td style="padding-left:10px">时间:</td>
		<td align="left"><s:property value="oaEmailBody.sendTime"></s:property></td>
	</tr>
	<tr>
		<td style="padding-left:10px">发件人:</td>
		<td align="left"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("oaEmailBody.creator"))%>
		</td>
	</tr>
	<tr>
		<td style="padding-left:10px">收件人:</td>
		<td limit="30" align="left"><s:property value="oaEmailBody.toUserNames"/></td>
	</tr>
	<s:if test="oaEmailBody.copyUserCds != null && oaEmailBody.copyUserCds.length() > 0">
		<tr>
			<td style="padding-left:10px">抄送人:</td>
			<td limit="30" align="left"><s:property value="oaEmailBody.copyUserNames"/></td>
		</tr> 
	</s:if>
	<s:if test="appAttachFiles.size()>0">
		<tr>
			<td align="left" style="padding-left:10px;"><img src="${ctx}/pics/email/atta.gif" alt="附件" title="附件下载"></td>
			<td>
			<s:iterator value="appAttachFiles">
			<div class="attachment">
			<s:url id="down" action="download" namespace="/app"  includeParams="none"  >
		  	  <s:param name="fileName">${fileName}</s:param>
		  	  <s:param name="realFileName">${realFileName}</s:param>
		  	  <s:param name="bizModuleCd">${bizModuleCd}</s:param>
		  	  <s:param name="uiid">${creator}</s:param>
		  	  <s:param name="id">${appAttachFileId}</s:param>
			</s:url>
			<a href="${down}" title="${realFileName}" >${realFileName}</a>&nbsp;|&nbsp;
			</div>
			</s:iterator>
			(附件总大小:<%= JspUtil.getAttaSize(JspUtil.findString("oaEmailBody.oaEmailBodyId")) %>)
			</td>
		</tr>
	</s:if>
</table></div>
<div style="height:1px;background-color:#8c8f94; margin:0 10px;"></div>
<div id="mailContent" class="mailContent" align="left">
	<s:property value="oaEmailBody.content" escape="false"/>
</div>
</div>

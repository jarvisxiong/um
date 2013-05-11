<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<div style="background-color: #FFF;border:1px solid #BFBFBF;width:100%;margin-left:-2px;">
<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0" style="border-bottom: 1px solid #bfbfbf;background-color: #F3F3F3" >
	<tr style="line-height: 20px;">
		<td width="60px" style="padding-left:10px;">主题:</td>
		<td align="left" style="font-weight: bolder;">
		<s:if test="oaEmailBody.importLevelCd == '2'">
			<span style="color:#FF6600">[重要]</span>
		</s:if>
		<s:elseif test="oaEmailBody.importLevelCd == 3">
          	<span style="color:#FF0000">[非常重要]</span>
	    </s:elseif>
		<s:property value="oaEmailBody.subject"/>
		</td>
		<td width="100px" style="font-weight: bolder;">
			<a onclick="javascript:newMail('${oaEmailBody.oaEmailBodyId}','reply')" title='回复'>回复</a>|
			<a onclick="javascript:showMoreOper(this)" title='更多操作'>更多</a>
			<div class="operTip">
				<a title="全部回复" onclick="newMail('${oaEmailBody.oaEmailBodyId}','replyAll')" style="display: block;">全部回复</a>
				<a title="转发" onclick="newMail('${oaEmailBody.oaEmailBodyId}','forward')" style="display: block;">转发</a>
				<a title="删除" onclick="deleteMail('remove','${oaEmailBody.oaEmailBodyId}',${boxId},'${oaEmailId}')" style="display: block;">删除</a>
				<a title="彻底删除" onclick="deleteMail('delete','${oaEmailBody.oaEmailBodyId}',${boxId},'${oaEmailId}')" style="display: block;">彻底删除</a>
			</div>
		</td>
	</tr>
	<s:if test="appAttachFiles.size()>0">
		<tr style="background-color: #f2f2f2;">
			<td align="center"><img src="${ctx}/pics/email/atta.gif" alt="附件" title="附件下载"></td>
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
			<a href="${down}" title="${realFileName}">${realFileName}</a>&nbsp;|&nbsp;
			</div>
			</s:iterator>
			</td>
		</tr>
	</s:if>
</table>
<div class="mailContent" style="background-color: #fff;color:#000;margin-right:20px;" align="left">
	<s:property value="oaEmailBody.content" escape="false"/>
</div>
</div>
<script type="text/javascript">
function showMoreOper(dom){
	var $off = $(dom).offset();
	$(dom).next().css({'left':$off.left-50+'px','top':$off.top+20+'px'}).show();
	$(document).click(function(event){
		var event  = window.event || event;
	    var obj = event.srcElement ? event.srcElement : event.target;
	    if($(obj).attr('className' != 'operTip')&&obj!=dom){
	    	$(dom).next().slideUp(200);
	    }
	});
}
</script>

<%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/common/taglibs.jsp" %>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<s:if test="meetingChairmanResult.size > 0">
	<s:iterator value="meetingChairmanResult">
	<div onClick="openHomeChairman('${oaChairmanReserveId}');"  class="font_notice" style="cursor: pointer;background-color: #53b147;" title="">
		<div style="font-size:14px;color:#fff;padding-left:8px;padding-top:4px;" class="ellipsisDiv">${subject}</div>
		<div style="padding-left:8px;color:#fff">
		<div>
		<div style="float:left;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applicant")) %></div>
		<div style="float:left;">
		<s:if test="remark==null||remark==''">
		</s:if>
		<s:else>
		【${remark}】
		</s:else>
		 </div>
		<div style="float:left;">${fn:substring(beginTime,11,16) } - ${fn:substring(endTime,11,16) } </div>
		</div>
		<div></div>
		</div>
	</div>
	</s:iterator>
</s:if>
<s:else>
	<s:iterator value="meetingResult">
	<div onClick="openHomeAttentionSp('${oaMeetingRoomResId}','${addrType }');"  class="font_notice" style="cursor: pointer;background-color: #53b147;" title="">
		<div style="font-size:14px;color:#fff;padding-left:8px;padding-top:4px;" class="ellipsisDiv">${subject}</div>
		<div style="padding-left:8px;color:#fff">
		<div>
		<div style="float:left;"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applicant")) %></div>
		<div style="float:left;">
		<s:if test="remark==null||remark==''">
		</s:if>
		<s:else>
		【${remark}】
		</s:else>
		 </div>
		<div style="float:left;">${fn:substring(beginTime,11,16) } - ${fn:substring(endTime,11,16) } </div>
		</div>
		<div></div>
		</div>
	</div>
	</s:iterator>
</s:else>
<s:if test="meetingResult.size == 0 && meetingChairmanResult.size == 0">
<div style="font-size:14px;text-align:left;margin-left:4px;color:#fff;height:42px;line-height:42px;">
	没有新的会议提醒
</div>
</s:if>

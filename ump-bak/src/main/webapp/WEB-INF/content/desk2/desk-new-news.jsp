<%@ page contentType="text/html;charset=UTF-8" %>
 <%@ include file="/common/taglibs.jsp" %>
 <%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
	
<div id="div_news_show" style="width:100%;float:left;display:none;margin-left: 0px;">
		<div style="clear:both;height:4px;">&nbsp;</div>
		<s:iterator value="newsResult">
		 <div class="content_left" 
		 style="padding:5px 0px 5px 5px;height:30px;"
		 onclick="$(this).find('img.new_img').hide(); window.open('${ctx}/oa/oa-news!detail.action?id=${oaNewsId}');" title="${subject}">
		 <c:set var="userId"><%=SpringSecurityUtils.getCurrentUiid()+","%></c:set>
	     <c:set var="cReaders"><s:property value="readers" /></c:set>
		 <c:set var="isReaded">${fn:indexOf(cReaders, userId)}</c:set>
			
			<div style="margin-left:0px;float:left;" class="font_title ellipsisDiv">${subject}</div>
			<c:if test="${isReaded==-1}">&nbsp;<img class="new_img" src="${ctx}/resources/images/desk2/new.png" style="float:left;" /></c:if>
			<div class="div_clear"></div>
			<div style="float:left;margin-left:0px;margin-top:2px;" class="font_time">
			<s:property value="newsTime"/>
			</div>
			<div class="div_clear"></div>
		</div>		
		</s:iterator>
</div>
 					
					
					
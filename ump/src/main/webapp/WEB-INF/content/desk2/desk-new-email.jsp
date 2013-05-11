<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<% 
//String outUrlExt = "http://mail1.powerlong.com/coremail/XJS/index.jsp?fid=1&flag=read:false&"+ sid; 

String[] fields = SpringSecurityUtils.getCoreMailFields();
String server = fields[0];
String sid = fields[1];
String outUrlExt = server + "/coremail/XJS/index.jsp?"+ sid; 
%>
		<input type="hidden" id="remindNoReadEmailNum" value="${remindNoReadEmailNum }" />
		<input type="hidden"  id="pageOutEmailNo" value="${pageOutEmailNo }"/>
		<div style="margin-top:4px;margin-left:0px;border-bottom:0;">
			<div class="div_clear"></div>
			<s:if test="result.totalPageCount>0">
			<div id="emailPage_1" class="content_left_page_selected" onclick="refreshMails('1')">1</div>
			 <s:if test="result.totalPageCount>7">
			<div id="emailPage_2" class="content_left_page" onclick="refreshMails('2')">2</div>
			</s:if>
			 <s:if test="result.totalPageCount>13">
			<div id="emailPage_3" class="content_left_page" onclick="refreshMails('3')">3</div>
			</s:if>
			<div id="emailPage" class="content_left_page"  onclick="openWinMailOut('<%= outUrlExt%>')" >...</div>
			
			 </s:if>
			 <s:else>
			 
			 </s:else>
		</div>
		<div style="clear:both;"></div>
		<%--<div style="clear:both;height:4px;">&nbsp;</div> --%>
		<s:iterator value="result.items" status="s" var="tItem">
			<div class="content_left" style="padding:3px 0px 7px 5px;height:30px;margin-top:4px;" onclick="showCmailDetail('<%= server %>','<%= sid %>','${tItem.mid}','${tItem.fid}')" title="${sendDateDesc}">
					<div style="float:left;margin-left:0px;margin-top:0px;"
					<s:if test="#tItem.read">class="ellipsisDiv font_title" </s:if><s:else>class="ellipsisDiv font_title_new"</s:else>
					 >
					<s:if test="subject==null||subject==''">
					(无主题)
					</s:if>
					<s:else>
						${subject}
					</s:else>
					</div>
					<s:if test="!#tItem.read">
					<img src="${ctx}/resources/images/desk2/new.png" stlye="float:left;" />
					</s:if>
					<div class="div_clear"></div>
					<div style="margin-left:0px;float:left;margin-top:2px;white-space: nowrap;max-width: 100px;overflow: hidden;" class="font_name">${fromUserName}&nbsp;</div>
					<div style="float:left;margin-top:2px;" class="font_time"><s:date name="#tItem.sentDate" format="MM-dd(EEE) HH:mm"/></div>
					<div class="div_clear"></div>
			</div>
		</s:iterator>	
	
	<input id="toMail" type="hidden" value="<%= outUrlExt%>" name="toMail"  />
 

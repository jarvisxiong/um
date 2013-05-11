<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
		<input type="hidden"  id="pageRemindNo" value="${pageRemindNo }"/>
		<s:iterator value="deskRemindPager.result" status="s" var="tItem">
				<div class="content_left" onclick="showEmail(this,'${oaEmailId}','${oaEmailBody.oaEmailBodyId }')" title="${subject}">
					<div style="float:left;margin-left:4px;margin-top:8px;"
					<s:if test="readFlg == 0">class="ellipsisDiv font_title_new" </s:if><s:else>class="ellipsisDiv font_title"</s:else>
					 >
					${subject}
					</div>
					<div style="float:left;margin-top:10px;">&nbsp;
					<s:if test="readFlg == 0">
					<img src="${ctx}/resources/images/desk2/new.gif" />
					</s:if>
					<s:else>
					</s:else>
					</div>
					<div class="div_clear"></div>
					<div style="margin-left:8px;float:left;" class="font_name">${sender}&nbsp;</div>
					<div style="float:left;" class="font_time">11-12</div>
					<div class="div_clear"></div>
				</div>
		</s:iterator>	
		
		
		<div style="margin-top:4px;margin-left: 10px;margin-bottom: 4px;" id="pageRemind">
		<s:if test="remindEmailNum > 0">
			<div id="pageRemindNo_1" class="content_left_page_selected" onclick="loadRemindEmail('1');">1</div>
			<s:if test="remindEmailNum >6">
				<div id="pageRemindNo_2" class="content_left_page" onclick="loadRemindEmail('2');">2</div>
			</s:if>
			<s:if test="remindEmailNum >12">
				<div id="pageRemindNo_3" class="content_left_page" onclick="loadRemindEmail('3');">3</div>
			</s:if>
			<div id="more" class="content_left_page"  onclick="parent.TabUtils.newTab('131', '提醒','${ctx}/oa/oa-email!main.action'); ;">...</div>
		</s:if>
		</div>
		
		
  

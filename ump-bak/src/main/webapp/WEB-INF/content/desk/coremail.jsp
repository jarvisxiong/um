<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %> 

<% 
//String outUrlExt = "http://mail1.powerlong.com/coremail/XJS/index.jsp?fid=1&flag=read:false&"+ sid; 

String[] fields = SpringSecurityUtils.getCoreMailFields();
String server = fields[0];
String sid = fields[1];
String outUrlExt = server + "/coremail/XJS/index.jsp?"+ sid; 
%>
<%--
http://mail1.powerlong.com/coremail/XJS/mbox/viewmail.jsp?mid=1:1tbiAQAPCk33WdAAFgAAmh&sid=BAtZLEppuadfWtiBYMppxbgQNNcuJwMg
 --%>
<!--  (外部邮件)标题和分页 -->
<div class="outMailHead">
	<div style="height:28px; width:100%; background-color:#e4e7ec; line-height:27px;">
		<div align="left" style="float:left; width: 300px;padding-left:5px;">
			<span style="cursor: pointer;" onclick="openWinMailOut('<%= outUrlExt%>')" title="点击进入邮箱">
				<span style="font-size:16px;font-weight: bolder; ">邮件</span>
				<%--
				<span style="color:red;"><span class="numOfNoReadOut">${unReadMailOutNum}</span></span>
				 --%>
			</span>
			|
			<span onclick="showTabMailInner()" style="cursor:pointer;" title="点击查看提醒">
				<span>提醒</span>
				<span style="color:red;"><span class="numOfNoRead">${unReadMailNum}</span></span>
			</span>
		</div>
		<div align="right">
			<div style="float:right;">
				<input type="hidden" id="curCoremailPageNo" value="${result.curPageNo}"/>
				<!-- 上一页 -->
				<s:if test="%{result.hasPre}">
					<img 
					 style="margin-top:2px;cursor:pointer;" 
					 src="${ctx}/resources/images/desk/page_up.gif" 
					 onmouseover="$(this).attr('src', '${ctx}/resources/images/desk/page_up_hover.gif');" 
					 onmouseout="$(this).attr('src', '${ctx}/resources/images/desk/page_up.gif');" 
					 onclick='jumpPageToCoremail("${result.curPageNo-1}");'/>
				</s:if>
				<s:else>
					<img 
					 style="margin-top:2px;"
					 src="${ctx}/resources/images/desk/page_up_inactive.gif" />
				</s:else>
				<!-- 下一页 -->
				<s:if test="%{result.hasNext}">
					<img 
					 style="margin-top:2px;cursor:pointer;" 
					 src="${ctx}/resources/images/desk/page_down.gif" 
					 onmouseover="$(this).attr('src', '${ctx}/resources/images/desk/page_down_hover.gif');" 
					 onmouseout="$(this).attr('src', '${ctx}/resources/images/desk/page_down.gif');" 
					 onclick='jumpPageToCoremail("${result.curPageNo+1}");'/>
				</s:if>
				<s:else>
					<img 
					style="margin-top:2px;"
					src="${ctx}/resources/images/desk/page_down_inactive.gif" />
				</s:else>
			</div>
			
			<div style="float:right;">
				<s:if test="result.total != 0">
					<div style="float:left;">
						第  ${result.curPageNo}/${result.totalPageCount} 页
						&nbsp;&nbsp; 
					</div>
				</s:if>
			</div>
		</div>
	</div>
</div>

<!-- 横向线 -->
<div style="height:1px; width:100%; background-color:#8c8f94;"></div>
							
<!-- 邮件列表 -->
<div style="width:100%;" id="coremailResultTip">
	<s:if test="result.total == 0">
		<div class="noResult">没有邮件</div>
	</s:if>
	<s:else>
		<table class="home_table" width="100%" cellpadding="0" cellspacing="0" border="0">
			<col width="45px;">
			<col width="120px;">
			<col>
			<col width="40px;">
			<col width="130px;">
			<tr class="tableHead">
				<td style="border-bottom: 1px solid #8c8f94;"><i class="ico mailList"></i></td>
				<td style="border-bottom: 1px solid #8c8f94;">发件人</td>
				<td style="border-bottom: 1px solid #8c8f94;">主题</td>
				<td style="border-bottom: 1px solid #8c8f94;">附件</td>
				<td style="border-bottom: 1px solid #8c8f94;background: none;">发送时间</td>
			</tr>
			<s:iterator value="result.items" var="tItem">
				<tr style="cursor:pointer;
					<s:if test="!#tItem.read">
						font-weight:bolder;
					</s:if> 
				" onclick="showCmailDetail('<%= server %>','<%= sid %>','${tItem.mid}','${tItem.fid}')">
					<td class="readIcon">
						<s:if test="#tItem.read"><i title="已读" class="ico mNORMAL"></i></s:if>
						<s:else><i title="未读" class="ico mUNREAD"></i></s:else>

						<s:if test="#tItem.priority == 1"><i title="紧急" class="ico pHIGH"></i></s:if>
						<s:if test="#tItem.priority == 5"><i title="缓慢" class="ico pLOW"></i></s:if>
					</td>
					<td>
						<div class="ellipsisDiv" title='${tItem.from}' style="max-width: 100%;">${tItem.fromUserName}</div>
					</td>
					<td title="${tItem.subject}">
						<div style="float:left;" class="ellipsisDiv">${tItem.subject}</div>
						<div style="float:left;">
							<s:if test="!#tItem.read">
							<img class="new_img" src="${ctx}/resources/images/common/new.gif" style="margin-top:5px;"/>
							</s:if>
						</div>
					</td>
					<td>
						<s:if test="#tItem.attached">
							<i title="有附件" class="ico mATTACHED"></i>
						</s:if>
						<s:else>
							&nbsp;
						</s:else>
					</td>
					<td title="${sendDateDesc}">
						<s:date name="#tItem.sentDate" format="MM-dd(EEE) HH:mm"/>
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:else>
</div>

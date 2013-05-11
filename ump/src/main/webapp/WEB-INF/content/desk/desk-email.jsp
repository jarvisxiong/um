<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>


<!--  (内部邮件)标题和分页 -->
<div class="innerMailHead">
	<div style="height:28px; width:100%; background-color:#e4e7ec; line-height:27px;">
		<div align="left" style="float:left; width: 300px;padding-left:5px;">
			
			<span onclick="showTabMailOut()" style="cursor:pointer;">邮件
			|
			<span onclick="openMailInner()" style="cursor:pointer;" title="点击查看提醒" >
				<span style="font-size:16px;font-weight: bolder; ">提醒</span>
				<span style="color:red;"><span class="numOfNoRead"><s:property value="unReadMailNum"/></span></span>
			</span>
			<%--
			<span class="numOfNoReadOut"><s:property value="unReadMailOutNum"/></span>
			 --%>
			</span>
		</div>
		<div align="right">
			<div style="float:right;">
				<s:if test="%{deskHomePager.hasPre}">
					<img 
					style="margin-top:2px;cursor:pointer;" 
					src="${ctx}/resources/images/desk/page_up.gif" 
					onmouseover="$(this).attr('src', '${ctx}/resources/images/desk/page_up_hover.gif');" 
					onmouseout="$(this).attr('src', '${ctx}/resources/images/desk/page_up.gif');" 
					onclick="jumpPageTo('${ctx}/desk/desk-email!email.action', '${deskHomePager.prePage}', 'desk_front_email_div', replaceInnerMailHead); return false;"/>
				</s:if>
				<s:else>
					<img 
					style="margin-top:2px;" 
					src="${ctx}/resources/images/desk/page_up_inactive.gif" />
				</s:else>
				<s:if test="%{deskHomePager.hasNext}">
					<img 
					style="margin-top:2px;cursor:pointer;" 
					src="${ctx}/resources/images/desk/page_down.gif" 
					onmouseover="$(this).attr('src', '${ctx}/resources/images/desk/page_down_hover.gif');" 
					onmouseout="$(this).attr('src', '${ctx}/resources/images/desk/page_down.gif');" 
					onclick="jumpPageTo('${ctx}/desk/desk-email!email.action', '${deskHomePager.nextPage}', 'desk_front_email_div', replaceInnerMailHead); return false;"/>
				</s:if>
				<s:else>
					<img 
					style="margin-top:2px;" 
					src="${ctx}/resources/images/desk/page_down_inactive.gif" />
				</s:else>
			</div>
			
			<div style="float:right;">
				<s:if test="deskHomePager.totalCount != 0">
					第  <s:property value="deskHomePager.pageNo" />/<s:property value="deskHomePager.totalPages" /> 页
				</s:if>
			</div>
		</div>
	</div>
</div>

<!-- 横向线 -->
<div style="height:1px; width:100%; background-color:#8c8f94;"></div>
							
<!-- 提醒列表 -->
<div>
	<s:if test="deskHomePager.result.size() == 0">
		<div class="noResult">没有提醒</div>
	</s:if>
	<s:else>
		<table class="home_table" width="100%" cellpadding="0" cellspacing="0" border="0">
			<col width="20px"/>	
			<col width="30px"/>	
			<col width="120px"/>	
			<col />	
			<col width="30px"/>	
			<col width="100px"/>	
			<tr class="tableHead">
				<td style="border-bottom: 1px solid #8c8f94;"><img src="${ctx}/resources/images/email/mail.gif"/></td>
				<td style="border-bottom: 1px solid #8c8f94;">置顶</td>
				<td style="border-bottom: 1px solid #8c8f94;">发件人</td>
				<td style="border-bottom: 1px solid #8c8f94;">主题</td>
				<td style="border-bottom: 1px solid #8c8f94;">附件</td>
				<td style="border-bottom: 1px solid #8c8f94;background: none;">发送时间</td>
			</tr>
			<s:iterator value="deskHomePager.result">
				<tr
				<s:if test="readFlg == 0">
			       class="unReadEmail" 
			       </s:if>
				>
					<td width="20px" onclick="showEmail(this,'${oaEmailId}','${oaEmailBody.oaEmailBodyId }')">
						<s:if test="readFlg == 0">
							<img src="${ctx}/resources/images/email/unRead.gif">
						</s:if>
						<s:else>
							<img src="${ctx}/resources/images/email/read.gif">
						</s:else>
					</td>
					<td width="20px" align="left" onclick="attentionEmail('${oaEmailId}',this);">
				          <s:if test="attentionFlg == 1"><img title="点击取消置顶" attentionFlg="1" src="${ctx}/pics/email/attention.gif"/></s:if>
				          <s:else><img title="点击置顶显示" attentionFlg="0" src="${ctx}/pics/email/attention_cancel.gif"/></s:else>
		          	</td>
					<td align="left" width="100px" style="padding-left:4px;" onclick="showEmail(this,'${oaEmailId}','${oaEmailBody.oaEmailBodyId }')">
						<% String emailUserName = CodeNameUtil.getUserNameByCd(JspUtil.findString("sender")); %>
						<div class="ellipsisDiv" title="<%=emailUserName %>"><%=emailUserName %></div>
					</td>
					<td onclick="showEmail(this,'${oaEmailId}','${oaEmailBody.oaEmailBodyId }')">
						<div style="float:left;" class="ellipsisDiv" title="${subject}">
						<s:if test="importLevelCd == 2">
				          	<span style="color:#FF6600">[重要邮件]</span>
			          	</s:if>
			          	<s:elseif test="importLevelCd == 3">
				          	<span style="color:#FF0000">[非常重要]</span>
			          	</s:elseif>
							${subject}
						</div>
						<s:if test="readFlg == 0">
							<div style="float:left;"><img style="margin-top:5px;" src="${ctx}/resources/images/common/new.gif" class="new_img"></div>
						</s:if>
					</td>
					<td width="30" align="center" onclick="showEmail(this,'${oaEmailId}','${oaEmailBody.oaEmailBodyId }')">
						<s:if test="uploadFlg == 1">
							<i title="有附件" class="ico mATTACHED" title="该邮件含有附件"></i>
							<%--
							<img src="${ctx}/resources/images/common/atta_y.gif" title="该邮件含有附件">
							 --%>
						</s:if>
						<s:if test="uploadFlg != 1">
							&nbsp;
						</s:if>
					</td>
					<td width="100px" align="left" title="<s:date name='updatedDate' format='yyyy-MM-dd HH:mm'/>" style="padding-right:10px;" onclick="showEmail(this,'${oaEmailId}','${oaEmailBody.oaEmailBodyId }')">
						<s:date name="updatedDate" format="MM-dd HH:mm"></s:date>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:else>
</div>
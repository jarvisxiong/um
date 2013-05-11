<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.uums.utils.CodeNameUtil"%>
<%@page import="com.hhz.uums.utils.JspUtil"%>
<%@page import="java.util.Date"%> 

<!-- 调动明细 -->
<div class="billContent" style="margin:15px;">
	
	
	<div class="div_row" style="margin:5px 0;">
		<span class="wap_title">姓名: ${newName}[${newUiid}]</span>
	</div>
	
	<div class="div_label">
			<div class="div_scroll">
			<table class="content_table" style="border:1px solid #ccc" cellpadding="0" cellspacing="0">
				<col width="70">
				<col width="150">
				<col width="150">
				<tr class="mainTr" style="background-color: #EEEEEE;">
					<td class="mainTd" class="wap_title">调整内容</td>
					<td class="mainTd" class="wap_title">调整前</td>
					<td class="mainTd" class="wap_title">调整后</td>
				</tr>
				<tr class="mainTr">
					<td class="mainTd"><span class="div_value">部门</span></td>
					<td class="mainTd"><div class="div_value">${bubOldOrgName }</div></td>
					<td class="mainTd"><div class="div_value" <s:if test="oldParentId!=newParentId" >style="color:red"</s:if> >${bubNewOrgName}</div>
				</tr>
				<tr class="mainTr">
					<td class="mainTd"><span class="div_value">职务</span></td>
					<td class="mainTd"><span class="div_value">${oldWorkDuty }</span></td>
					<td class="mainTd"><span class="div_value" <s:if test="oldWorkDuty != newWorkDuty" >style="color:red"</s:if> >${newWorkDuty}</span></td>
				</tr>
				<tr class="mainTr">
					<td class="mainTd"><span class="div_value">职级</span></td>
					<td class="mainTd"><span class="div_value"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()" value="oldLevelCd"/></span></td>
					<td class="mainTd"><span class="div_value" <s:if test="oldLevelCd != newLevelCd" >style="color:red"</s:if>>
					<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermLevel()" value="newLevelCd"/>
					</span></td>
				</tr>
				<tr class="mainTr">
					<td class="mainTd"><span class="div_value">职位(编制)</span></td>
					<td class="mainTd"><span class="div_value">${oldSysPosNames}</span></td>
					<td class="mainTd"><span class="div_value" <s:if test="oldSysPosIds != newSysPosIds" >style="color:red"</s:if>>${newSysPosNames}</span></td>
				</tr>
			</table>
		</div>
	</div>
	
	<div class="div_row">
		<span class="wap_title">【调动说明】:</span>
		<s:if test="(approveStatusCd==1||approveStatusCd==4 )&& applyTypeCd==1 ">
			<span class="wap_title" onclick="goRefreshApproveArea('${plasApproveInfoId}')" style="color:red">编辑</span>
		</s:if>
	</div>
	<div class="div_row" style="margin:5px 0;">
		<div style="background-color: #EEEEEE;line-height: 22px;padding:10px;">${contentDesc}</div>
	</div>
	
	<div class="div_row">
		<div id="approve_his"></div>
	</div>
	<div class="div_row" >
		<div class="list_header2"><span>历史审批意见</span></div>
		<div class="div_value">
			<div style="background-color: #EEEEEE;line-height: 22px;padding:10px 10px 10px 20px;">
					${remark}
					
					<%--
				 <ul style="list-style-type: none;">
					 <s:iterator value="nodeArchList" var="voArch" status="st">  
					 
					 	<li>
					 	<span><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermType()" value="approveRoleCd"/></span>
					 	<span>
							<p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermType()" value="approveRoleCd"/> ${approveUserName} (<s:date name="approveDate" format="yyyy-MM-dd HH:mm"/>)
							<s:if test="#st.index == 0">
								 发起
							</s:if>
							<s:else>
								<s:if test="approveOptionCd==1"> 同意</s:if>
								<s:elseif test="approveOptionCd==2"> 驳回</s:elseif>
							</s:else>
						</span>
					 	</li>
					 </s:iterator>
				 </ul>
					 --%>
			</div>
		</div>
	</div>
</div>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.uums.entity.ws.WsPlasOrg"%>
<%@page import="com.hhz.ump.cache.PlasCache"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>

 <s:set var="queryCondition"><%=(String)request.getAttribute("queryCondition") %></s:set>
 <s:iterator value="searchUserResult">
	<div class="module <s:if test="#queryCondition == uiid">queryMatched</s:if>" >
		<a  id="module_user_no_${uiid}"
			class="header"
			onclick="chatWithSomebody('${uiid}','${userCd}','${userName}')" 
			onmouseover='showSearchUserCard(this,"${uiid}","${userCd}")'
			onmouseout="closeCardManager()"
			>
			<u>${userName}[${uiid}]</u>
		</a>
		<div class="module_body">
			<div>部门:${orgName}
				<%
					String userId = JspUtil.findString("plasUserId");
					String torgId = JspUtil.findString("orgId");
					String centerName = PlasCache.getCenterOrgNameByOrgId(torgId);
					if(StringUtils.isNotBlank(centerName)){
				%>
						(<%= centerName %>)
				<%
					}
				%>
			<div>座机:${fixedPhone}</div>
			<%-- 非决策层,不显示 电话 --%>
			<div>手机:
				<s:if test="orgCd != '168'">${mobilePhone}&nbsp;${mobilePhone2}</s:if>
				<s:if test="orgCd == '168' && (uiid =='guojun')">${mobilePhone}</s:if>
			</div>
			<div>职位:${workDutyDesc}</div>
			<div>邮箱:${email}</div>
		</div>
	</div>
</div>
</s:iterator>
<%--
<div>匹配信息有:账号/用户名/邮箱/固定电话/移动电话/其他号码</div>
 --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.cache.PlasCache"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>

 <s:iterator value="searchUserResult">
	<div id="div_srh_${uiid}" class="div_srh_user<c:if test="${fn:contains(shareUserCds, uiid)&& uiid !=null}"> li_user_selected</c:if>" onclick="chooseSrhUser('${uiid}','${userName}')">
		<a  id="module_user_no_${uiid}"
			class="header">
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
			<div>职位:${workDutyDesc}</div>
		</div>
	</div>
</div>
</s:iterator>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.uums.entity.ws.WsPlasOrg"%>
<%@page import="com.hhz.ump.cache.PlasCache"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.apache.commons.lang.StringUtils"%>

 <s:set var="queryCondition"><%=(String)request.getAttribute("queryCondition") %></s:set>
 <s:iterator value="searchUserResult">
	<div class="module <s:if test="#queryCondition == uiid">queryMatched</s:if>" style="margin-bottom: 16px!important;" >
	<table cellpadding="0" border="0" cellspace="0" style="width:100%;">
	<tr>
		<td style="width:231px;">
			<div>
				<a  id="module_user_no_${uiid}"
					class="header"
					onclick="chatWithSomebody('${uiid}','${userCd}','${userName}')" 
					>
					<u>${userName}[${uiid}]</u>
				</a>
			</div>
			<div class="module_body">
					<div>
					部门:
					${orgName}
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
					<div>座机:&nbsp;${fixedPhone}</div>
					<%-- 非决策层,不显示 电话 --%>
					<div>手机:
						<s:if test="orgCd != '168'">${mobilePhone}&nbsp;${mobilePhone2}</s:if>
						<s:if test="orgCd == '168' && (uiid =='guojun')">${mobilePhone}</s:if>
					</div>
					<div>职位:&nbsp;${workDutyDesc}</div>
					<div>
					<div style="float:left;">邮箱:&nbsp;</div>
					<div style="float:left;color:#1ba0e1">${email}</div>
					</div>
					</div>
			</div>
		
		</td>
		<td>
			<div  id="${uiid}_pic" 
				  style="float:left;height:112px;width:90px;cursor:pointer;margin-top:3px;"
				  <s:if test="sexCd == 1">
				  	class="malePic"
				  </s:if>
				  <s:elseif test="sexCd == 2">
				  	class="femalePic"
				  </s:elseif>
				  <s:else>
				  	class="defaultPic"
				  </s:else>
				  onclick="showPic('${uiid}')"
				>
				<div onclick="showPic('${uiid}')" style="font-size:12px;margin:90px 0 0 8px;cursor:pointer;color:#0072bb;" title="点击显示照片">点击显示照片</div>
			</div>
		</td>
	</tr>
	</table>
	 
</div>
<div class="div_clear">&nbsp;</div>
</s:iterator>
<script>
function showPic(uid){
	//$("#"+uid+"_pic").html()
	VisitingCardUtil.getPicPath(uid);
}
</script>
<%--
<div>匹配信息有:账号/用户名/邮箱/固定电话/移动电话/其他号码</div>
 --%>
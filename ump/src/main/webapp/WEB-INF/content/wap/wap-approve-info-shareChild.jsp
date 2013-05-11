<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<s:if test="childUsers.size()>0">
<ul class="ul_next">
<s:iterator value="childUsers">
	<li id="li_${uiid}" onclick="chooseUser('${uiid}')" class="ul_li_user<s:if test="sexCd==1"> ul_li_male</s:if><s:elseif test="sexCd==2"> ul_li_female</s:elseif><s:else> ul_li_none</s:else><c:if test="${fn:contains(shareUserCds, uiid)&& uiid !=null}"> li_user_selected</c:if>">${userName}</li>
</s:iterator>
</ul>
</s:if>

<s:if test="childOrgs.size()>0">
<ul class="ul_next">
<s:iterator value="childOrgs">
	<li id="li_${plasOrgId}" class="ul_li_right" onclick="expandNode('${plasOrgId}')">${orgName}</li>
	<div id="div_tree_${plasOrgId}" style="display: none;"></div>
</s:iterator>
</ul>
</s:if>
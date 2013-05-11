<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.uums.utils.CodeNameUtil"%>
<%@page import="com.hhz.uums.utils.JspUtil"%>
<s:if test="pageType == 'user'">
<div>
	<div id="searchDiv" style="height:30px;line-height:30px;padding-left: 10px;">
		<input type="text" id="searchUser" name="searchUser" style="width:280px;"/>
		<img src="${ctx}/images/webim/search_zoom.jpg" style="vertical-align: middle;margin-top:-5px;">
	</div>
	<div>
		<div id="allSelected" class="memberBtn" style="float:left;margin-left:10px;">已选人员</div>
		<div class="memberBtn" style="float:left;margin-right:2px;margin-left:160px;" id="addAll" >全部选择</div>
		<div class="memberBtn" style="float:left" id="delAll">全部删除</div>
	</div>
</div>
<div style="clear:both;overflow:auto;" id="treeUserContainer">
<div class="mailUserLeft" >
	<div>
		<div><img src="${ctx}/pics/email/mail_member_lt.gif"/></div>
		<div class="memberBorder">
			<div class="memberContainer">
				<div id="deptLi" class="memberTitle">
					按部门选择
				</div>
				<div id="deptDiv" ></div>
				<s:if test="showRank == true">
					<div class="memberTitle">
						按职级选择
					</div>
					<div>
						<s:iterator value="rankMap">
							<div dataId="<s:property value='key'/>"  class="groupNameDiv" id="rankDiv">
								<input type="checkbox"/>
								<span><s:property value="value"/> </span>
							</div>
						</s:iterator>
					</div>
				</s:if>
				
				<s:if test="showGroupFlg == true">
					<div class="memberTitle">
						自定义组
					</div>
					<div>
						<s:iterator value="oaEmailGroups">
							<div dataId="${oaEmailGroupId}"  class="groupNameDiv" id="groupDiv">
								<input type="checkbox"/>
								<span>${groupName}</span>
							</div>
						</s:iterator>
					</div>
				</s:if>
			</div>
		</div>
		<div><img src="${ctx}/pics/email/mail_member_lb.gif"/></div>
	</div>
</div>

<div class="mailUserRight" id="mailUserRight">
	<div><img src="${ctx}/pics/email/mail_member_lt.gif"/></div>
	<div class="memberBorder">
		<div class="memberContainer" id="user_container_id">
			<div id="deptDisplay" class="memberTitle"><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("defaultParentOrgCd")) %></div>
			<ul id="userDisplay">
				<s:iterator value="wsPlasUsers" id="userInfo">
					<li class="userUnSelected"
						id="${userInfo.uiid}" 
						name="${userInfo.userName}" 
						orgName="${userInfo.orgName}" 
						sysPosName="${userInfo.sysPosName}" 
						sysPosCd="${userInfo.sysPosCd}" 
					>
					<div class='userStatus_online'>
						${userInfo.userName}|${userInfo.orgName}
						<s:if test="showSysPos == true">
						|${userInfo.sysPosName}
						</s:if>
					</div>
					</li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div><img src="${ctx}/pics/email/mail_member_lb.gif"/></div>
</div>
</div>
</s:if>
<s:else>
	<div id="deptDiv" style="margin:0 5px 5px 5px;">
	</div>
</s:else>


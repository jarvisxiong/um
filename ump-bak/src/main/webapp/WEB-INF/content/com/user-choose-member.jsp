<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><s:if test="isOnlyUser==0">
<div>
<div id="searchDiv" style="height:30px;line-height:30px;padding-left: 10px;">
	<input type="text" onkeyup="getUser(this);" name="searchUser" style="width:280px;" value=""/>
	<img src="${ctx}/images/webim/search_zoom.jpg" style="vertical-align: middle;margin-top:-5px;">
</div>



<div class="mailUserLeft">

	<div id="selectedLi" onclick="showSelectedUser();" class="memberBtn">已选人员</div>
	<div>
		<div><img src="${ctx}/pics/email/mail_member_lt.gif"/></div>
		<div class="memberBorder">
			<div class="memberContainer">
				<div id="deptLi" class="memberTitle">
					按部门选择
				</div>
				<div id="deptDiv" ></div>
				<div class="memberTitle" title='您可以自己预设置(菜单: 我的PD->自定义用户组 进行)'>
					自定义组
				</div>
				<div>
					<s:iterator value="oaEmailGroups">
						<div dataId="${oaEmailGroupId}"  class="groupNameDiv">
							<input type="checkbox" onclick="showGroupMember(this,true);"/>
							<span onclick="showGroupMember(this);">${groupName}</span>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
		<div><img src="${ctx}/pics/email/mail_member_lb.gif"/></div>
	</div>
</div>

<div class="mailUserRight" id="mailUserRight">
	<div>
		<div class="memberBtn" style="float:left;margin-right:2px;" id="addAll" onclick="addAll();">全部选择</div>
		<div class="memberBtn" style="float:left" id="delAll" onclick="delAll();">全部删除</div>
	</div>
	<div><img src="${ctx}/pics/email/mail_member_rt.gif"/></div>
	<div class="memberBorder">
		<div class="memberContainer">
			<div id="deptDisplay" class="memberTitle"><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("orgCd")) %></div>
			<ul id="userDisplay">
				<s:iterator value="wsPlasUsers" id="userInfo">
					<li id="${userInfo.uiid}" class="userUnSelected"><div class='userStatus_online'>${userInfo.userName}</div></li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div><img src="${ctx}/pics/email/mail_member_rb.gif"/></div>
</div>
</div>

<script type="text/javascript">

getOrgTree();

/////


</script>
</s:if>
<s:else>
<div>
<div style="text-align: center; margin-top: 10px;">
	<div style="overflow: auto; width: 180px; margin: 0 auto; padding: 1px; border: 1px solid #dadada; text-align: center; background-color: #e7e7e7;">
		<div style="height: 3px; overflow: hidden; background-color: #5a6a82"></div>
		<div id="userRight">
			<div style="margin: 0 auto; height: 320px; text-align: left;">
				<div style="height: 20px; line-height: 20px; border-bottom: 1px solid #FFF;">
					请选择用户：
				</div>
				<ul id="userDisplay">
					<s:if test="wsPlasUsers.size == 0">
						<script type="text/javascript">
							listAttendee();
						</script>
					</s:if>
					<s:else>
						<s:iterator value="wsPlasUsers" id="userInfo">
							<li id="${userInfo.uiid}" class="userUnSelected"><div class='userStatus_online'>${userInfo.userName}</div></li>
						</s:iterator>
					</s:else>
					
				</ul>
			</div>
		</div>
	</div>
</div>
</div>
</s:else>



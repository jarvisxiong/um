<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<div>
	<div id="searchDiv" class="person_search_div">
		<input type="text" onkeyup="getUser(this);" id="userSelectionSearchInput" name="searchUser" style="width:355px;" value=""/>
		<img src="${ctx}/pics/tubiao_sousuo.jpg" style="margin:-3px 0 0 -25px;vertical-align: middle;">
	</div>
	
	<div class="user_left">
		<div class="userBorder">
			<div id="selectedLi" onclick="showSelectedUser();" class="memberBtn">
				已选择人员
			</div>
			<div>
				<div>
					<img src="${ctx}/pics/email/mail_member_lt.gif" />
				</div>
				<div class="memberBorder">
					<div class="memberContainer">
						<div id="deptLi" class="memberTitle">
							按部门选择
						</div>
						<div id="deptDiv" ></div>
					</div>
				</div>
				<div>
					<img src="${ctx}/pics/email/mail_member_lb.gif" />
				</div>
			</div>
		</div>
	</div>
	
	<div class="userRight" id="userRight">
		<div>
			<div class="memberBtn" id="addAll" onclick="addAll();" style="float: left; margin-right: 2px;">
				全部选择
			</div>
			<div class="memberBtn" id="delAll" onclick="delAll();" style="float: left;">
				全部删除
			</div>
		</div>
		<div>
			<img src="${ctx}/pics/email/mail_member_rt.gif" />
		</div>
		<div class="memberBorder">
			<div class="memberContainer">
				<div id="deptDisplay" class="memberTitle">
					<%=SpringSecurityUtils.getCurrentDeptName() %>
				</div>
				<ul id="userDisplay">
					<s:iterator value="personList" id="userInfo">
						<li id="${userInfo.uiid}" class="userUnSelected">
							<div>${userInfo.userName}</div>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div>
			<img src="${ctx}/pics/email/mail_member_rb.gif">
		</div>
	</div>
</div>

<script type="text/javascript">
	getOrgTree();
</script>
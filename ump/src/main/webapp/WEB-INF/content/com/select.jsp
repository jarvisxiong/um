<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<s:if test="pageType == 'user'">
<div>
	<div id="searchDiv" style="height:30px;line-height:30px;padding-left: 10px;">
		<input type="text" id="searchUser" name="searchUser" style="width:280px;" title="支持账号或姓名模糊搜索"/>
		<img src="${ctx}/images/webim/search_zoom.jpg" style="vertical-align: middle;margin-top:-5px;">
	</div>
	<div>
		<div class="fLeft btn_new btn_blue_new" id="btnFilterDept" style="width:60px;margin-left:10px;margin-bottom:4px;">按部门</div>
		<div class="fLeft btn_new btn_blue_new" id="btnFilterPerm" style="width:60px;margin-left:5px;margin-bottom:4px;">选职级</div>
		<div class="fLeft btn_new btn_blue_new" id="btnFilterGroup" style="width:80px;margin-left:5px;margin-bottom:4px;">按自定义组</div>
		<div class="fRight btn_new btn_red_new" id="delAll" style="width:70px;margin-right:26px;margin-bottom:4px;">全部删除</div>
		<div class="fRight btn_new btn_green_new" id="addAll" style="width:70px;margin-right:5px;margin-bottom:4px;">全部选择</div>
		<div class="fRight btn_new btn_green_new" id="allSelected" style="width:70px;margin-right:5px;margin-bottom:4px;">已选人员</div>
	</div>
</div>
<div style="clear:both;overflow-x:hidden;overflow-y:auto;" id="treeUserContainer">
<div class="mailUserLeft" >
	<div>
		<div><img src="${ctx}/pics/email/mail_member_lt.gif"/></div>
		<div class="memberBorder">
			<div class="memberContainer" style="padding:5px;">
				<div id="permDivPanel" style="display: none;">
					<div class="memberTitle">按职级选择</div>
					<ul style="list-style-type: none;padding-left: 10px;" id="popSelPos">
						<li>
						    <div style="float:left;width:80px;"><input style="margin-right:5px;" type="checkbox" value="10"/>总裁</div>
						    <div style="float:left;width:80px;"><input style="margin-right:5px;" type="checkbox" value="40"/>高级经理</div>
						</li>
						<li>
						    <div style="float:left;width:80px;"><input style="margin-right:5px;" type="checkbox" value="80"/>副总裁</div>
						    <div style="float:left;width:80px;"><input style="margin-right:5px;" type="checkbox" value="50"/>经理</div>
						</li>
						<li>
						   	<div style="float:left;width:80px;"><input style="margin-right:5px;" type="checkbox" value="20"/>总经理</div>
						    <div style="float:left;width:80px;"><input style="margin-right:5px;" type="checkbox" value="70"/>主管</div>
						</li>
						<li>
						    <div style="float:left;width:80px;"><input style="margin-right:5px;" type="checkbox" value="30"/>副总经理</div>
						    <div style="float:left;width:80px;"><input style="margin-right:5px;" type="checkbox" value="60"/>专员</div>
						</li>
					</ul>
					<div style="padding-top:10px;padding-left:10px;clear: left;">
						<input type="button" class="btn_new btn_green_new" value="确认选择" id="btnPermOk"     style="width:70px;"/>
						<input type="button" class="btn_new btn_green_new" value="取消选择" id="btnPermCancel" style="width:70px;"/>
					</div>
				</div>
				
				<div style="clear: left;">
				</div>
				
				<div id="deptDivPanel">
					<div id="deptLi" class="memberTitle">
						按部门选择
					</div>
					<div id="deptDiv" ></div>
				</div>
				
				<s:if test="showRank == true">
				<div id="posDivPanel">
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
				</div>
				</s:if>
				
				<s:if test="showGroupFlg == true">
				<div id="groupDivPanel" style="display:none;">
					<div class="memberTitle" style="line-height:30px;">
						自定义组 <div class="fRight btn_new btn_green_new" onclick="setCustGroup()" title="设置用户自定义组">设置</div>
						<script language="javascript">
							function setCustGroup(){
								var url = '${ctx}/oa/oa-email-group!list.action';
								if(parent && parent.TabUtils){
									parent.TabUtils.newTab('206','自定义组',url);
								}else{
									window.open(url);
								}
							}
						</script>
					</div>
					<div>
						<s:iterator value="oaEmailGroups">
							<div dataId="${oaEmailGroupId}"  class="groupNameDiv" id="groupDiv">
								<input type="checkbox"/>
								<span>${groupName}</span>
								</div>
							</s:iterator>
						</div>
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


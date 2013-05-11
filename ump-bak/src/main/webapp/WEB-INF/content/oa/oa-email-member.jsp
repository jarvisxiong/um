<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
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
				<div class="memberTitle">
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
	<div style="height:27px;">
		<security:authorize ifAnyGranted="A_EMAIL_USER_ADMIN">
			<div class="memberBtn" style="float:left;margin-right:2px;" id="addAll" onclick="addAll();">全部选择</div>
		</security:authorize>
		<div class="memberBtn" style="float:left" id="delAll" onclick="delAll();">全部删除</div>
	</div>
	<div><img src="${ctx}/pics/email/mail_member_rt.gif"/></div>
	<div class="memberBorder">
		<div class="memberContainer">
			<div id="deptDisplay" class="memberTitle"><%=SpringSecurityUtils.getCurrentDeptName() %></div>
			<ul id="userDisplay" style="min-height: 60px;margin:0;padding:0;list-style-type: none;">
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
function getOrgTree(){
	$.post("${ctx}/oa/oa-email!getOrgTree.action", function(result) {
		var tree = new TreePanel({
			renderTo:"deptDiv",
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.on(function(node){
			getUsersByOrg(node,"click");
		});
		tree.addListener("check",function(node){
			getUsersByOrg(node);
			
		});
		tree.render();
	});
}

getOrgTree();

var outTime;
function getUser(dom){
	if(outTime) clearTimeout(outTime);
	outTime= setTimeout(function(){
		var domValue=$.trim(dom.value);
		if(domValue=="")return;
		$("#mailUserRight").addClass("waiting");
		$("#userDisplay").html("<img align='absMiddle' src='${ctx}/images/loading.gif'>");
		$.post("${ctx}/oa/oa-email!getUsersByFilter.action",{value:domValue},function(result){
			$("#userDisplay").empty();
			$("#deptDisplay").text("搜索结果");
			result = eval(result);
			$.each(result,function(i,n){
				if(n)bindEvents(n,true);
			});
			$("#mailUserRight").removeClass("waiting");
			delete result;
		});
	},300);
}

function showGroupMember(dom,checkFlg){
	var $parent = $(dom).parent();
	var dataId = $parent.attr("dataId");
	var title = $parent.text();
	$("#userDisplay").empty();
	$("#deptDisplay").text(title);
	$.post("${ctx}/oa/oa-email-group!load.action",{id:dataId},function(result){
		result = eval(result);
		$.each(result,function(i,n){
			if(n)bindEvents(n,true);
		});
		if(checkFlg){
			var checked = $(dom).attr("checked");
			if(checked){
				addAll();
			}else{
				delAll();
			} 
		}
	});
}
</script>



<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<input type="hidden" id="personType" value="${personType}" />
<input type="hidden" id="personTaskId" value="${bizEntityId}" />
<div>
	<div id="searchDiv" class="person_search_div">
		<input type="text" onkeyup="getUser(this);" name="searchUser" style="width:355px;" value=""/>
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
			<img src="/PowerDesk/pics/email/mail_member_rb.gif">
		</div>
	</div>
</div>

<script type="text/javascript">

//获取机构树
function getOrgTree() {
	$.post(_PATH + "/oa/special-task!getOrgTree.action",
			{personType : $("#personType").val()},
			function(result) {
		var tree = new TreePanel({
			renderTo:"deptDiv",
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		
		tree.on(function(node){
			if($("#deptDisplay").text() == node.attributes.text) {
				return;
			}
			getUsers(node);
		});
		
		tree.addListener("check",function(node){
			getUsers(node);
			
		});
		
		tree.render();
	});
}

// 获取指定机构下的所有用户
function getUsers(node){
	var orgCds = node.getAllChildren(node);
	var title = node.attributes.text;
	var orgBizCd = node.attributes.extParam;
	var orgCd = node.attributes.id; 
	var checked = node.checked;
	
	if(title == $("#deptDisplay").text()) {
		$("#personDiv").css("cursor","wait");
		if(checked == '0') {
			delAll();
		} else if(checked == '1') {
			addAll();
		};
		$("#personDiv").css("cursor","");
		return;
	}
	
	$("#personDiv").css("cursor","wait");
	$("#userDisplay").empty().addClass("person_waiting");
	$.post(_PATH + "/oa/special-task!getUsersbyOrg.action", 
			{ orgCds : orgCds.join(",")	}, 
			function(result){
		$("#deptDisplay").text(title);
		$("#userDisplay").removeClass("person_waiting");
		result = eval(result);
		$.each(result,function(i,n){
			if(n) {
				bindPersonEvents(n, "userDisplay", $("#personType").val(), true);
			}
		});

		if(checked == '1') {
			addAll();
		}
		
		$("#personDiv").css("cursor","");
	});
}

getOrgTree();

</script>
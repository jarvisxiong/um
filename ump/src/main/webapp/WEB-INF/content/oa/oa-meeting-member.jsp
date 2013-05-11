<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<span class="bizEntityId" style="display: none"><s:text name="bizEntityId" /></span>
<div>
	<div id="searchDiv" style="height:30px;line-height:30px;padding-left: 10px;">
	    <input type="hidden"  id="Personal" name="Personal" value="${Personal}"></input>
		<input type="text" onkeyup="getUser(this,'${Personal}');" name="searchUser" style="width:355px;" value=""/>
		<img src="${ctx}/pics/tubiao_sousuo.jpg" style="margin:-3px 0 0 -25px;vertical-align: middle;">
	</div>
	
	<div class="userLeft">
		<div class="userBorder">
			<div id="selectedLi" onclick="showSelectedUser();" class="memberBtn">
				用户池人员
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
				 
					<s:iterator value="attendeeList" id="userInfo">
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

//获取机构树
function getOrgTree() {
	$.post(_PATH + "/oa/oa-meeting!getOrgTree.action", function(result) {
		var tree = new TreePanel({
			renderTo:"deptDiv",
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.on(function(node){
			if($("#deptDisplay").text()==node.attributes.text)return;
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
    var Personal = $("#Personal").val();
	var orgCds = node.getAllChildren(node);
	var title = node.attributes.text;
	var orgBizCd = node.attributes.extParam;
	var orgCd = node.attributes.id; 
	var checked = node.checked;
	if(title == $("#deptDisplay").text()){
		$("#memberDiv").css("cursor","wait");
		if(checked == '0'){
			delAll();
		} else if(checked == '1'){
			addAll();
		};
		$("#memberDiv").css("cursor","");
		return;
	}
	
	$("#memberDiv").css("cursor","wait");
	$("#mailUserRight").addClass("waiting");
	
	$.post(_PATH + "/oa/oa-meeting!getUsersbyOrg.action", {orgCds:orgCds.join(",")}, function(result){
		$("#userDisplay").empty();
		$("#deptDisplay").text(title);

		result = eval(result);
		$.each(result,function(i,n){
			if(n) {
				bindEvents(n, true,Personal);
			}
		});

		if(checked == '1') {
			addAll();
		}
		
		$("#memberDiv").css("cursor","");
		$("#userRight").removeClass("waiting");
	});
}

getOrgTree();

</script>



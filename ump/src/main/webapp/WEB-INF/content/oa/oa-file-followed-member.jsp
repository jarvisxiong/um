<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<div>
<div id="searchDiv" style="height:30px;line-height:30px;padding-left: 10px;">
	<input type="text" onkeyup="getUser(this);" name="searchUser" style="width:280px;" value=""/>
	<img src="${ctx}/images/webim/search_zoom.jpg" style="vertical-align: middle;margin-top:-5px;">
</div>

<div style="float: left;width:50%;">
	<div>
		<div><img src="${ctx}/pics/email/mail_member_lt.gif"/></div>
		<div class="memberBorder">
			<div class="memberContainer">
				<div id="deptLi" class="memberTitle">
					按部门选择
				</div>
				<div id="deptDiv" ></div>
				<div>
					
				</div>
			</div>
		</div>
		<div><img src="${ctx}/pics/email/mail_member_lb.gif"/></div>
	</div>
</div>

<div id="mailUserRight" style="float:right;">
	<div style="height:27px;">
		选择人员
	</div>
	<div><img src="${ctx}/pics/email/mail_member_rt.gif"/></div>
	<div class="memberBorder">
		<div class="memberContainer">
			<ul id="userDisplay" style="min-height: 60px;">
			</ul>
		</div>
	</div>
	<div><img src="${ctx}/pics/email/mail_member_rb.gif"/></div>
</div>
</div>

<script type="text/javascript">
function getOrgTree(){
	$.post("${ctx}/oa/oa-file-followed!getOrgTree.action", function(result) {
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
		$.post("${ctx}/oa/oa-file-followed!getUsersByFilter.action",{value:domValue},function(result){
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
</script>



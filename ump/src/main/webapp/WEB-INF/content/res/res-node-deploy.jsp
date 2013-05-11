<%@page import="com.hhz.ump.util.DictMapUtil"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" type="text/css" /> 
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript"	src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/table.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
	<title>审批节点配置</title>
</head>

<body>
	<div class="search">
	<s:form id="mainForm" action="res-node-deploy" method="post">
	<%if(session.getAttribute("filter")==null){
	    session.setAttribute("filter","");
	    } 
	 %>
		<fieldset>
		    <legend><s:text name="common.search" /></legend>
		    <div name='searchDiv'>
			    <s:text name="resResNode.nodeCd"/>:<input name="filter_EQS_nodeCd" size="18" maxlength="30"  value="${filter_EQS_nodeCd}"/>
			    <s:text name="resResNode.nodeName"/>:<input name="filter_LIKES_nodeName" size="18" maxlength="30"  value="${filter_LIKES_nodeName}"/>
			          节点类型:<s:select list="mapNodeType" listKey="key" listValue="value" name="filter_EQS_nodeTypeCd"></s:select>		   
			          是否有效:<s:select list="@com.hhz.ump.util.DictMapUtil@getMapActive()" listKey="key" listValue="value" name="filter_EQB_active"></s:select>		   
				<input type="submit" class="buttom" value="<s:text name="common.search" />" />
			</div>
		</fieldset>
	&nbsp;<p:page/>
	</s:form>
	</div>
	<%session.setAttribute("filter",""); %>

	<div id="tableDiv"  style="display: block;">
		<table class="commonTable" id="editTable" align="left" width="99%">
			<thead>
				<tr>
					<th width="40">节点CD</th>
					<th width="120">节点名称</th>
					<th width="70">负责人</th>
					<th width="50">排序序号</th>
					<th width="80">机构种类</th>
					<th width="80">人员类型</th>
					<th width="80">节点级别</th>
					<th width="80">节点类型</th>
					<th width="50">是否有效</th>
					<th width="50">是否锁定</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="page.result">
				<tr id="main_${resNodeId}" class="mainTr">
					<td id="nodeCd">${nodeCd}</td>
					<td id="nodeName">${nodeName}</td>
					<td id="nodeUser"><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("nodeUser")) %></td>
					<td id="sequenceNo">${sequenceNo}</td>
					<td><p:code2name mapCodeName="mapOrgKindCd" value="orgKindCd"/></td>
					<td><p:code2name mapCodeName="mapUserKindCd" value="userKindCd"/></td>
					<td><p:code2name mapCodeName="mapNodeLevelCd" value="nodeLevelCd"/></td>
					<td><p:code2name mapCodeName="mapNodeType" value="nodeTypeCd"/></td>
					<td><%=DictMapUtil.getMapActive().get(JspUtil.findValue("active")) %></td>
					<td><%=DictMapUtil.getMapActive2().get(JspUtil.findValue("isLock")) %></td>
				</tr>
				<tr  id="detail_${resNodeId}" class="detailTr"  style="display:none">
					<td colspan="10">
						<fieldset>
						    <legend>详细信息</legend>
						    <form  id="editForm_${resNodeId}" action="res-node-deploy!save.action" method="post">
						    <table class="innerTable mainTable" >
						   		<s:hidden name="page.pageNo"></s:hidden>
						    	<tr>
						    	<td width="60" align="right">节点CD:</td>
						    	<td width="100"><input type="text" name="nodeCd" id="nodeCd" readonly="readonly" value="${nodeCd}" onblur="validate('${nodeCd}',this)" /></td>
						    	<td width="60" align="right">节点名称:</td>
						    	<td width="100"><input type="text" name="nodeName" id="nodeName" value="${nodeName}" />
						    	<input type="hidden" name="id" value="${resNodeId}" />
						    	</td>
								<td width="60" align="right">负责人:</td>
						      	<td width="100">
								<input type="text" id="nodeUser" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("nodeUser")) %>" onkeyup="showSearchUser(this)" />
								<input type="hidden" name="nodeUser" value="${nodeUser}" />
								</td>
								</tr>
								<tr>
								<td align="right">排序序号:</td>
								<td><input name="sequenceNo" id="sequenceNo" type="text" value="${sequenceNo}"/> </td>
								<td align="right">节点类型:</td>
								<td><s:select list="mapNodeType" listKey="key" listValue="value" id="nodeTypeCd" name="nodeTypeCd" ></s:select></td>
								<td align="right">是否多选:</td>
						      	<td colspan="1"><s:checkbox name="isMuti"></s:checkbox>
								</td >
								</tr>								
								<tr>
								<td align="right">机构种类:</td>								
								<td>
								<s:select list="mapOrgKindCd" listKey="key" listValue="value" id="orgKindCd" name="orgKindCd" ></s:select>
								</td>						
							    <td align="right">人员类型:</td>
								<td>								
								 <s:select list="mapUserKindCd" listKey="key" listValue="value"  id="userKindCd" name="userKindCd" ></s:select>
								</td>							
								<td align="right">节点级别:</td>
						      	<td>					      	
						      	<s:select list="mapNodeLevelCd" listKey="key" listValue="value"  id="nodeLevelCd" name="nodeLevelCd" ></s:select>
								</td>							
								</tr>
								<tr>
								<td align="right">备注:</td>
						      	<td><textarea name="remark" id="remark" >${remark}</textarea>
								</td >
								<td align="right">是否有效:</td>
						      	<td><s:checkbox name="active"></s:checkbox>
								</td >
								<td align="right">是否锁定:</td>
						      	<td><s:checkbox name="isLock"></s:checkbox>
								</td >
								</tr>
						    	<tr><td colspan="6">
						    	<input type="button" class="buttom" id="btnEdit" onclick="save('${resNodeId}');" value="保存" />
						    	<input type="button" class="buttom" id="btnEdit" onclick="deleteTemplet('${resNodeId}');" value="删除" />
						    	<input type="button" class="buttom" id="btnCancel" onclick="cancelEdit();" value="取消" />
						    	<input type="hidden" name="filter_EQS_nodeCd1"/>
						    	<input type="hidden" name="filter_LIKES_nodeName1"/>
						    	<input type="hidden" name="filter_EQS_nodeTypeCd1"/></td>
						    	</tr>
						    	
						    </table>
						    </form>
						 </fieldset>
					</td>
				</tr>
			</s:iterator>
			
			</tbody>
		</table>
	</div>
		
<div id="divNew" style="display: none;" class="clear">
	<fieldset>
	    <legend><s:text name="common.create"/></legend>
	<div>
	<s:form id="createdForm" action="res-node-deploy!save.action" method="post">
		<table class="mainTable" >
			<tr>
			<td width="15%" align="right"><s:text name="resResNode.nodeCd"/>:</td>
			<td width="20%"><input type="text"  name="nodeCd"  onblur="validate('${nodeCd}',this)" /></td>
			<td width="15%" align="right"><s:text name="resResNode.nodeName"/>:</td>
			<td width="20%"><s:textfield name="nodeName"/></td>
			<td width="15%" align="right"><s:text name="resResNode.nodeUser"/>:</td>
			<td width="15%">
			<input type="text" id="nodeUser" value="<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("nodeUser")) %>" onkeyup="showSearchUser(this)" />
			<input type="hidden" name="nodeUser" value="${nodeUser}" />
			</td>
			</tr>
			
			<tr>
			<td align="right">机构种类：</td>
			<td>	<s:select list="mapOrgKindCd" listKey="key" listValue="value"  name="orgKindCd" ></s:select></td>
			<td align="right">人员类型:</td>
			<td> <s:select list="mapUserKindCd" listKey="key" listValue="value" name="userKindCd" ></s:select></td>
			<td align="right">节点级别:</td>
			<td colspan="1">	<s:select list="mapNodeLevelCd" listKey="key" listValue="value"  name="nodeLevelCd" ></s:select>
							</td>
			</tr>
			<tr>
			<td align="right"><s:text name="resResNode.sequenceNo"></s:text>:</td>
			<td><s:textfield  id="sequenceNo" name="sequenceNo" /></td>
			<td align="right">节点类型:</td>
			<td><s:select list="mapNodeType" listKey="key" listValue="value" id="nodeTypeCd" name="nodeTypeCd" ></s:select></td>
			<td align="right"><s:text name="resResNode.remark"/>:</td>
			<td colspan="1"><textarea name="remark" id="remark" ></textarea></td>
			</tr>
			<tr><td colspan="6" >
			<input type="button" class="buttom" id="btnSave" onclick="create();" value="<s:text name="common.save"/>" />
			<input type="button" class="buttom" id="btnCancel" onclick="cancelNew();" value="<s:text name="common.cancel"/>" />
			</td></tr>
		</table>	
	</s:form>	
	</div>
	</fieldset>
</div>
<div id="divToolBar"  class="clear">
<input type="button"  class="buttom" id="btnNew" type="button" value="<s:text name="common.create"/>" />
</div>
<div id="searchUserDiv" class="searchUserDiv"></div>
<script language="javascript">
	//var divNew=$("#divNew").clone();
	//$("#divParent").empty();
	
	$("#btnNew").bind("click", function(){
		$("#divNew").show();
		$("#divToolBar").hide();
		cancelEdit();
	});
	$("#editTable tbody tr.mainTr").toggle(function(){
		$(".foldTr").trigger("click");
		$(this).addClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif");
		$(this).next().show();
		cancelNew();
	},function(){
		$(this).removeClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/down.gif");
		$(this).next().hide();
	});
	$("#editTable tbody tr.detailTr :input").keyup(function(){
		var id = this.id;
		$(this).parents(".detailTr").prev().children("[id="+id+"]").text($(this).val());
	});
	function cancelNew(){
		$("#divNew").hide();
		$("#divToolBar").show();
		
	}
	function cancelEdit(){
		$(".foldTr").trigger("click");
		$(this).addClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif");
		$(this).next().show();
	}
	function create() {
		//$("#createdForm").submit();
		document.getElementById("createdForm").submit();
	}
	function save(resNodeId) {	   
        $("input[name=filter_EQS_nodeCd1]").val($("input[name=filter_EQS_nodeCd]").val());
        $("input[name=filter_LIKES_nodeName1]").val($("input[name=filter_LIKES_nodeName]").val());
	    $("input[name=filter_EQS_nodeTypeCd1]").val($("#mainForm_filter_EQS_nodeTypeCd").val());
	   
	     document.getElementById("editForm_"+resNodeId).submit();
		
	}
	function viewTemplet(resNodeId) {
		window.location.href="${ctx}/res/res-bill-templet!input.action?id="+resNodeId;
	}
	function deleteTemplet(resNodeId) {
		$.post("${ctx}/res/res-node-deploy!delete.action",{id:resNodeId},
				 function(result) {
			 if (result){
				$("#detail_"+resNodeId).remove();
				$("#main_"+resNodeId).remove();
			 }
		});
	}
	function validate(oldCd,dom){
		var newCd =$(dom).val();
		$.post("${ctx}/res/res-node-deploy!checkCd.action",{newCd:newCd,oldCd:oldCd},
				 function(result) {
			 if (result=='false'){
				 $(dom).addClass("error");
				 $(dom).attr("title","已经存在");
			 }else{
				 $(dom).removeClass("error");
				 $(dom).removeAttr("title");
			}
		});
	}
	var searchTime;
	function showSearchUser(dom){
		var $currentDom = $(dom);
		var $next = $(dom).siblings();
		
		if(searchTime)clearTimeout(searchTime);
		searchTime = setTimeout(function(){
			var val = $.trim($currentDom.val());
			$next.val("");
			if(val == "") {
				$("#searchUserDiv").slideUp(200);
				return;
			}
			$.post("${ctx}/com/user-choose!getUsersByFilter.action", {value:val,maxNum:10}, function(result) {
				result = eval(result);
				var $offset = $currentDom.offset();
				$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$currentDom.height() + 5}).empty().show();
				$.each(result, function(i,n) {
					var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
					$("#searchUserDiv").append(html);
				});
			
				$("#searchUserDiv div").click(function() {
					var userName = $(this).children("span:first").text();
					var uiid = $(this).attr("uiid");
					$currentDom.val(userName);
					$next.val(uiid);
					$("#searchUserDiv").slideUp(200);
				});
			});
		}, 300);
		
		$(document).bind('click', function(event) {
			var event  = window.event || event;
		    var obj = event.srcElement ? event.srcElement : event.target;
		    if(obj != dom){
		    	$("#searchUserDiv").slideUp(200);
		    	if($next.val() == ''){
		    		$currentDom.val('');
		    	}
		    }
		    $(document).unbind('click');
		});
		return false;
	}
</script>
</body>
</html>

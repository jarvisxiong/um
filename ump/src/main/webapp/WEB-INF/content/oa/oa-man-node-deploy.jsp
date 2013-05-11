<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/userChoose.css"/>
	<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<script language="javascript" src="${ctx}/js/table.js"></script>
	<link rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" type="text/css" /> 
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script src="${ctx}/js/prompt/ymPrompt.js" type="text/javascript"></script>
	<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/js/orgChoose.js"></script>
	<title>审批节点配置</title>
</head>

<body>
	<div class="search">
	<s:form id="mainForm" action="oa-man-node-deploy" method="post">
		<fieldset>
		    <legend><s:text name="common.search" /></legend>
		    <div>
		    <s:text name="resResNode.nodeCd"/>:<s:textfield name="filter_EQS_nodeCd" id="filter_LIKES_nodeCd" size="18" maxlength="30" />
		    <s:text name="resResNode.nodeName"/>:<s:textfield name="filter_LIKES_nodeName" id="filter_LIKES_nodeName" size="18" maxlength="30" />
		      <input type="submit" class="buttom" value="<s:text name="common.search" />" />
		    </div>
		</fieldset>
	&nbsp;<p:page/>
	</s:form>
	</div>
	<div id="tableDiv"  style="display: block;">
		<table class="commonTable" id="editTable" align="left" width="99%">
			<thead>
				<tr>
					<th width="10%">节点CD</th>
					<th width="15%">节点名称</th>
					<th width="15%">负责人</th>
					<th width="10%">排序序号</th>
					<th width="15%">备注</th>
					<th width="15%">节点类型</th>
					<th width="20%">最近更新时间</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="page.result">
				<tr id="main_${oaManNodeId}" class="mainTr">
					<td id="nodeCd">${nodeCd}</td>
					<td id="nodeName">${nodeName}</td>
					<td id="nodeUser"><%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("nodeUser")) %></td>
					<td id="sequenceNo">${sequenceNo}</td>
					<td id="remark" class="split">${remark}</td>
					<td><p:code2name mapCodeName="mapNodeType" value="nodeTypeCd"/></td>
					<td><s:property value="updatedDate" /></td>
				</tr>
				<tr  id="detail_${oaManNodeId}" class="detailTr"  style="display:none">
					<td colspan="7">
						<fieldset>
						    <legend>详细信息</legend>
						    <form  id="editForm_${oaManNodeId}" action="oa-man-node-deploy!save.action" method="post">
						    <table class="innerTable" >
						    	<tr>
						    	<td width="15%" align="right">节点CD:</td>
						    	<td width="20%"><input type="text" name="nodeCd" id="nodeCd" value="${nodeCd}" onblur="validate('${nodeCd}',this)" /></td>
						    	<td width="15%" align="right">节点名称:</td>
						    	<td width="20%"><input type="text" name="nodeName" id="nodeName" value="${nodeName}" />
						    	<input type="hidden" name="id" value="${oaManNodeId}" />
						    	</td>
								<td width="15%" align="right">负责机构:</td>
						      	<td width="15%">
								<input type="text" id="nodeUserEdit_${oaManNodeId}" value="<%=CodeNameUtil.getDeptNameByCd(JspUtil.findString("nodeUser")) %>"  onclick="getOrgJson({nameId:'nodeUserEdit_${oaManNodeId}',cdId:'nodeUserEditHid_${oaManNodeId}',orgType:'Logical'});" readonly="readonly" style="cursor: pointer;" />
								<input type="hidden" name="nodeUser" id="nodeUserEditHid_${oaManNodeId}" value="${nodeUser}" />
								</td>
								</tr>
								<tr>
								<td align="right">排序序号:</td>
								<td><input name="sequenceNo" id="sequenceNo" type="text" value="${sequenceNo}"/> </td>
								<td align="right">节点类型:</td>
								<td><s:select list="mapNodeType" listKey="key" listValue="value" id="nodeTypeCd" name="nodeTypeCd" ></s:select></td>
								<td align="right">备注:</td>
						      	<td colspan="3"><textarea name="remark" id="remark" >${remark}</textarea>
								</td >
								</tr>
						    	<tr><td colspan="6">
						    	<input type="button" class="buttom" id="btnEdit" onclick="save('${oaManNodeId}');" value="保存" />
						    	<input type="button" class="buttom" id="btnEdit" onclick="deleteTemplet('${oaManNodeId}');" value="删除" />
						    	<input type="button" class="buttom" id="btnCancel" onclick="cancelEdit();" value="取消" />
						    	</td>
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
	<s:form id="createdForm" action="oa-man-node-deploy!save.action">
		<table class="mainTable" >
			<tr>
			<td width="15%" align="right"><s:text name="resResNode.nodeCd"/>:</td>
			<td width="20%"><s:textfield name="nodeCd"/></td>
			<td width="15%" align="right"><s:text name="resResNode.nodeName"/>:</td>
			<td width="20%"><s:textfield name="nodeName"/></td>
			<td width="15%" align="right">负责机构:</td>
			<td width="15%">
			<input class="inputBorder" validate="required" type="text"  id="nodeUserNew" onclick="getOrgJson({nameId:'nodeUserNew',cdId:'nodeUserHid',orgType:'Logical'});" readonly="readonly" style="cursor: pointer;"  />
			<input type="hidden" id="nodeUserHid" name="nodeUser"  />
			</td>
			</tr>
			<tr>
			<td align="right"><s:text name="resResNode.sequenceNo"></s:text>:</td>
			<td><s:textfield  id="sequenceNo" name="sequenceNo" /></td>
			<td align="right"><s:text name="resResNode.remark"/>:</td>
			<td colspan="3"><textarea name="remark" id="remark" ></textarea></td>
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
	function save(oaManNodeId) {
		//$("#editForm_"+oaManNodeId).submit();
		document.getElementById("editForm_"+oaManNodeId).submit();
		
	}
	function viewTemplet(oaManNodeId) {
		window.location.href="${ctx}/res/res-bill-templet!input.action?id="+oaManNodeId;
	}
	function deleteTemplet(oaManNodeId) {
		$.post("${ctx}/oa/oa-man-node-deploy!delete.action",{id:oaManNodeId},
				 function(result) {
			 if (result){
				$("#detail_"+oaManNodeId).remove();
				$("#main_"+oaManNodeId).remove();
			 }
		});
	}
	function validate(oldCd,dom){
		var newCd =$(dom).val();
		$.post("${ctx}/oa/oa-man-node-deploy!checkCd.action",{newCd:newCd,oldCd:oldCd},
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

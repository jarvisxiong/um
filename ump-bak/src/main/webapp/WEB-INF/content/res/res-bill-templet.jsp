<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
	<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<script language="javascript" src="${ctx}/js/table.js"></script>
	<title>审批模板设置</title>
</head>

<body>
	<div class="search">
	<s:form id="mainForm" action="res-bill-templet" method="post">
		<fieldset>
		    <legend><s:text name="common.search" /></legend>
		    <div>
		    <s:text name="resResBillTemplet.templetCd"/>:<s:textfield name="filter_EQS_templetCd" id="filter_LIKES_templetCd" size="18" maxlength="30" />
		    <s:text name="resResBillTemplet.templetName"/>:<s:textfield name="filter_LIKES_templetName" id="filter_LIKES_templetName" size="18" maxlength="30" />
		    是否有效:<s:select list="@com.hhz.ump.util.DictMapUtil@getMapActive()" listKey="key" listValue="value" name="filter_EQB_activeBl"></s:select>
		      <input type="submit" class="buttom" value="<s:text name="common.search" />" />
		    </div>
		</fieldset>
	&nbsp;<p:page/>
	</s:form>
	</div>
	<div id="tableDiv">
		<table class="commonTable" id="editTable" align="left" width="99%">
			<thead>
				<tr>
					<th width="5%"><s:text name="resResBillTemplet.templetCd"/></th>
					<th width="5%">有效</th>
					<th width="20%"><s:text name="resResBillTemplet.templetName"/></th>
					<th width="30%"><s:text name="resResBillTemplet.templetFileName"/></th>
					<th width="40%">实体路径</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="page.result">
				<tr id="main_${resBillTempletId}" class="mainTr">
					<td id="templetCd">${templetCd}</td>
					<td id="activeBl">${activeBl}</td>
					<td id="templetName">${templetName}</td>
					<td id="templetFileName">${templetFileName}</td>
					<td id="entityName">${entityName}</td>
					<%--
					<td id="remark">${remark}</td>
					 --%>
				</tr>
				<tr  id="detail_${resBillTempletId}" class="detailTr"  style="display:none">
					<td colspan="5">
						<fieldset>
						    <legend><s:text name="common.detail"/></legend>
						    <form  id="editForm_${resBillTempletId}" action="res-bill-templet!save.action" method="post">
						    <table class="innerTable" >
						    	<tr >
							    	<td width="15%" align="right"><s:text name="resResBillTemplet.templetCd"/>:</td>
							    	<td width="20%">
							    	<input type="text" readonly="readonly" name="templetCd" id="templetCd" value="${templetCd}" onblur="validate('${templetCd}',this,'editForm_${resBillTempletId}')" />
							    	</td>
							    	<td width="15%" align="right"><s:text name="resResBillTemplet.templetName"/>:</td>
							    	<td width="20%"><input type="text" name="templetName" id="templetName" value="${templetName}" />
							    	<input type="hidden" name="id" value="${resBillTempletId}" />
							      	<input type="hidden" name="recordVersion" value="${recordVersion}" />
							    	</td>
									<td width="15%" align="right"><s:text name="resResBillTemplet.templetFileName"/>:</td>
							      	<td width="15%"><input type="text" name="templetFileName" id="templetFileName" value="${templetFileName}" /></td>
								</tr>
								<tr><td align="right">实体路径:</td><td colspan="5"><input type="text" name="entityName" id="entityName" value="${entityName}" /></td >
								</tr>
								<tr><td align="right">是否有效:</td><td colspan="5"><s:checkbox name="activeBl"/></td></tr>
								<tr><td align="right"><s:text name="resResBillTemplet.remark"/>:</td><td colspan="5"><textarea name="remark" id="remark" >${remark}</textarea></td ></tr>
						    	<tr>
						    		<td colspan="6">
								    	<input type="button" class="buttom" id="btnEdit" onclick="save('${resBillTempletId}');" value="<s:text name="common.save"/>" />
								    	<input type="button" class="buttom" id="btnEdit" onclick="deleteTemplet('${resBillTempletId}');" value="<s:text name="common.delete"/>" />
								    	<input type="button" class="buttom" id="btnCancel" onclick="cancelEdit();" value="<s:text name="common.cancel"/>" />
								    	<input type="button" class="buttom" onclick="viewTemplet('${resBillTempletId}');" value="预览模板" />
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
		
<div id="divParent"  class="clear">
	<div id="divNew"  >
	<fieldset>
	    <legend><s:text name="common.create"/></legend>
	<div>
	<s:form id="createForm" action="res-bill-templet!save.action" >
		<table class="mainTable" >
			<s:hidden name="resBillTempletId"/>
			<tr>
				<td width="15%" align="right"><s:text name="resResBillTemplet.templetCd"/>:</td>
				<td width="20%"><input type="text" name="templetCd" id="templetCd" onblur="validate('',this,'createForm')" /></td>
				<td width="15%" align="right"><s:text name="resResBillTemplet.templetName"/>:</td>
				<td width="20%"><s:textfield name="templetName"/></td>
				<td width="15%" align="right"><s:text name="resResBillTemplet.templetFileName"/>:</td>
				<td width="15%"><s:textfield  id="templetFileName" name="templetFileName" /></td>
			</tr>
			<tr><td align="right">实体路径:</td><td colspan="5"><input type="text" name="entityName" id="entityName" value="${entityName}" /></td></tr>
			<tr><td align="right">是否有效:</td><td colspan="5"><s:checkbox name="activeBl" value="true" /></td></tr>
			<tr><td align="right"><s:text name="resResBillTemplet.remark"/>:</td><td colspan="5"><textarea name="remark" id="remark" ></textarea></td></tr>
			<tr>
				<td colspan="6" >
				<input type="button" class="buttom" id="btnSave" onclick="create();" value="<s:text name="common.save"/>" />
				<input type="button" class="buttom" id="btnCancel" onclick="cancelNew();" value="<s:text name="common.cancel"/>" />
			</td></tr>
		</table>	
	</s:form>	
	</div>
	</fieldset>
</div>
</div>
<div id="divToolBar">
<input type="button"  class="buttom" id="btnNew" type="button" value="<s:text name="common.create"/>" />
</div>
<script language="javascript">
	var divNew=$("#divNew").clone();
	$("#divParent").empty();
	
	$("#btnNew").bind("click", function(){
		divNew.appendTo("#divParent");
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
		$("#divParent").empty();
		$("#divToolBar").show();
		
	}
	function cancelEdit(){
		$(".foldTr").trigger("click");
		$(this).addClass("foldTr");
		$(this).children().eq(0).children().attr("src","${ctx}/images/up.gif");
		$(this).next().show();
	}
	function create() {
		$("#createForm").submit();
	}
	function save(resBillTempletId) {
		$("#editForm_"+resBillTempletId).submit();
	}
	function viewTemplet(resBillTempletId) {
		window.open("${ctx}/res/res-bill-templet!input.action?id="+resBillTempletId);
	}
	function deleteTemplet(resBillTempletId) {
		$.post("${ctx}/res/res-bill-templet!delete.action",{id:resBillTempletId},
				 function(result) {
			 if (result){
				$("#detail_"+resBillTempletId).remove();
				$("#main_"+resBillTempletId).remove();
			 }
		});
	}
	function validate(oldCd,dom,formId){
		var newCd =$(dom).val();
		$.post("${ctx}/res/res-bill-templet!checkCd.action",{templetCd:newCd,oldTempletCd:oldCd},
				 function(result) {
			 if (result=='false'){
				 $(dom).addClass("error");
				 $(dom).attr("title","已经存在");
				 $("#"+formId).bind("submit", function(){
					  return false;
				 });
			 }else{
				 $(dom).removeClass("error");
				 $(dom).removeAttr("title");
				 $("#"+formId).unbind("submit");
			}
		});
	}
</script>
</body>
</html>

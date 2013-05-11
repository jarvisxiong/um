<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<div class="toolBar">
	<input type="button" value="确定" class="btn_blue" onclick="doSure();" />
	<input type="button" value="返回" class="btn_green" onclick="openDetail();" />
	
</div>
<div class="div_row">
<span class="wap_label">【驳回到】</span>
<div>
请输入驳回原因:
</div>
<div>
	<textarea id="approveRemark" rows="3" style="width: 280px;"></textarea>
</div>
</div>
<div class="div_row">
<div>
请选择驳回节点(默认为发起人):
</div>
<table class="content_table" id="editTable" width="100%" >
		
		<tr id="main_0"   class="mainTr"  style="cursor: pointer;background-color:#abc3d2;">
			<td class="pd-chill-tip" style="padding-top: 5px;">
				发起人
				<input type="hidden" id="idRejectTo" value="" />
			</td>
		</tr>
	<s:iterator value="mapReturnToNode" var="s">
		<tr id="main_${s.key}" style="cursor: pointer;" class="mainTr" >
	
			<td class="pd-chill-tip" style="padding-top: 5px;" >
				${s.value }
				<input type="hidden" id="${s.key }" name="${s.key }"  value="${s.key }" />
			</td>
			
		</tr>			
	</s:iterator>
</table>
</div>

<script>
$("#editTable").find("tr").each(function(){
	$(this).click(function(){
		//先把全部的字体色还原
		$("#editTable").find("tr").each(function(){
			$(this).css("background-color","#edeff3");
			$(this).attr("checked","false");
			});
		$(this).css("background-color","#abc3d2");
		$(this).attr("checked","true");
		$("#idRejectTo").val($(this).find(":input:first").val());
		});
	
	
});
function doSure(){
	var approveRemark=$("#approveRemark").val();
	var rejectTo = $("#idRejectTo").val();
	var data = {
			approveRemark:approveRemark,
			id:id};
	if(isEmpty(approveRemark)){
		alert('请填写驳回原因!');
		return;
	}
	if(isNotEmpty(rejectTo)){
		data["rejectTo"] = rejectTo;
	}
	var r =confirm('确定驳回？');
	if(r == true){
		$.post(_ctx+"/res/res-approve-info!back.action",data,
		function(result) {
			alert("已驳回完成");
			openDetail();
		});
	}
	
}
</script>


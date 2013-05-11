<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--战略采购订单审批表-->
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>

<c:set var="isSy">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
<c:set var="isSy">true</c:set>
</c:if>
<div align="center" class="billContent">

	
	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		<%@ include file="template-var.jsp"%>
		
		<table  class="mainTable">
			<col width="100"/>
			<col width="100"/>
			<col width="100"/>
			<col/>
			<tr>
				<td class="td_title">标题</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">发起公司</td>
				<td colspan="2">
				<input validate="required" type="text" name="templateBean.companyName" value="${templateBean.companyName}" id="companyName" class="inputBorderNoTip" />
				</td>
				<td class="chkGroup" align="left"><s:checkbox id="haierFlg" name="templateBean.haierFlg" cssClass="group" onclick=""></s:checkbox>急</td>
			</tr>
			<tr>
				<td class="td_title">费用名称</td>
				<td colspan="3">
					<input validate="required" class="inputBorder"  type="text" name="templateBean.changeName" value="${templateBean.changeName}" id="changeName" />
				</td>
			</tr>
			<tr>
				<td class="td_title">金额</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.changeCrash" value="${templateBean.changeCrash}" id="changeCrash"  onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">文件内容详述</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" name="templateBean.fileDetail">${templateBean.fileDetail}</textarea></td>
			</tr>
			<tr>
				<td class="td_title">附件上传</td>
				<td colspan="3">
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="detailFileId" title="费用报销单及对应的发票" fileValue="${templateBean.detailFileId}"/>
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="detailFileId2" title="出差申请单或会议通知" fileValue="${templateBean.detailFileId2}"/>
				<r:fileUpload canEdit="${canEdit}" bizEntityId="${resApproveInfoId==null?entityTmpId:resApproveInfoId}" fileId="detailFileId3" title="出差人员接受招待用餐申报单" fileValue="${templateBean.detailFileId3}"/>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<%@ include file="template-footer.jsp"%>

<script type="text/javascript">
	//$("#partBName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'partBName',sid:'partB'});
	//$("#contactNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contName','contNo'],{contNo:"contactNoId",contName:"contactNameId"});
//$("#changeCrash").quickSearch("${ctx}/cont/cont-project-code!quickSearch.action",['changeCrash'],{projectCd:'projectCd',changeCrash:'changeCrash'},{codeType:$('#codeType').val()});

//合同编号唯一性校验
function contNoValidate(dom){
	if($(dom).val()){
		$.post("${ctx}/cont/cont-ledger!contNoValidate.action?contNo=" + $(dom).val(),
			function(result){
				//合同新增，不是0条记录
				if(result!="0"){
					alert("该编号已存在，请重新输入");
					$(dom).val("");
				}
	     });
	}
}
function chooseMonopoly() {
	var isProjectAuth=$("#isProjectAuth").attr("checked"); 
	var isMonopoly =$("#isMonopoly").attr("checked"); 
	if(isProjectAuth||isMonopoly){
		$("#unitName").unbind();
		$("#remark").attr("validate","required");
	}else{
		$("#unitName").val('');
		$("#unitName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'unitName',sid:'sid'},'','','');
		$("#remark").attr("class","inputBorder");
		$("#remark").removeAttr("validate");
		$("#remark").removeAttr("title");
	}
}
</script>

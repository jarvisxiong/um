<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!--办公资产调拨单（1）-->
<%@ include file="template-header.jsp"%>

<div class="billContent" align="center">
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="60">
			<col width="120">
			<col>
			<col width="100">
			<col>
			<tr>
				<td rowspan="2" class="td_title">调拨部门</td>
				<td class="td_title">调拨设备申请部门</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.applyDeptName" value="${templateBean.applyDeptName}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">申请人</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.applyUser" value="${templateBean.applyUser}"/>
				</td>
				<td class="td_title">总价(元)</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));" name="templateBean.totalPrice" value="${templateBean.totalPrice}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">调拨原因</td>
				<td colspan="4">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.reason" rows="6" cols="30">${templateBean.reason}</textarea>
				</td>
			</tr>
		</table>
		<table id="tbConItem" class="mainTable" style="margin-top: 5px;">
			<col>
			<col>
			<col>
			<col>
			<col>
			<col>
			<col>
			<col width="40">
			<tr>
				<td align="center">资产编码</td>
				<td align="center">设备名称</td>
				<td align="center">型号</td>
				<td align="center">调出部门</td>
				<td align="center">调入部门</td>
				<td align="center">单价(元)</td>
				<td align="center">调拨日期</td>
				<td align="center">操作</td>
			</tr>
			<s:if test="statusCd==0 || statusCd==3">
			<tr id="trConItem" style="display: none;margin-bottom:5px;">
				<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[0].assetCode"/></td>
				<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[0].assetName"/></td>
				<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[0].model"/></td>
				<td class="tdGroupNodes">
					<input validate="required" class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[0].outDeptName"/>
				</td>
				<td>
					<input validate="required" class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[0].inDeptName"/>
				</td>
				<td>
					<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));" name="templateBean.fixedAssetsMove1Assets[0].unitPrice"/>
				</td>
				<td><input class="inputBorder Wdate" type="text"  name="templateBean.fixedAssetsMove1Assets[0].moveDate" onfocus="WdatePicker()"/></td>
				<td><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a></td>
			</tr>
			</s:if>
			<s:set var="conItemCount"><s:property value="templateBean.fixedAssetsMove1Assets.size()"/></s:set>
			<s:iterator value="templateBean.fixedAssetsMove1Assets" var="item" status="s">
				<tr style="margin-bottom:5px;">
					<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].assetCode" value="${item.assetCode}"/></td>
					<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].assetName" value="${item.assetName}"/></td>
					<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].model" value="${item.model}"/></td>
					<td class="tdGroupNodes">
						<input validate="required" class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].outDeptName" value="${item.outDeptName}"/>
					</td>
					<td>
						<input validate="required" class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].inDeptName" value="${item.inDeptName}"/>
					</td>
					<td>
						<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].unitPrice" value="${item.unitPrice}"/>
					</td>
					<td><input class="inputBorder Wdate" type="text" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].moveDate" value="${item.moveDate}" onfocus="WdatePicker()"/></td>
					<td>
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a>
					</s:if>
					</td>
				</tr>
			</s:iterator>
		</table>
		<s:if test="statusCd==0 || statusCd==3">
		<input type="button"  class="btn_blue_75_55" value="增加条款" onclick="addItem();" />
		</s:if>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=0;
	try{
		index = Number("${conItemCount}");
	}catch(e){}
	function addItem(){
		
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	
	function delItem(dom){
		$(dom).parent().parent().remove();
	}
</script>
<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
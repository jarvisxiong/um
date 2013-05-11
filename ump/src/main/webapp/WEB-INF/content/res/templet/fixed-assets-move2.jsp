<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<%@ include file="template-header.jsp"%>
<!--办公资产调拨单（2）-->
		<div class="billContent" align="center">
			
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<table class="mainTable">
					<col width="80px">
					<col width="80px">
					<col>
					<col width="60px">
					<col>
					<tr>
						<td rowspan="3">调出公司</td>
						<td class="td_title">调出公司</td>
						<td>
							<input validate="required" class="inputBorder" type="text" name="templateBean.outDeptName" value="${templateBean.outDeptName}"/>
						</td>
						<td class="td_title">经办人</td>
						<td>
							<input validate="required"  class="inputBorder"   type="text" name="templateBean.outUser" value="${templateBean.outUser}"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">调出固定<br/>资产原因</td>
						<td colspan="3">
							<textarea  class="inputBorder contentTextArea" validate="required" name="templateBean.outReason" rows="6" cols="30">${templateBean.outReason}</textarea>
						</td>
					</tr>
					<tr>
					<td class="td_title">总价(元)</td>
					<td  colspan="3">
						<input validate="required" class="inputBorder" type="text" onblur="formatVal($(this));" name="templateBean.totalPrice" value="${templateBean.totalPrice}"/>
					</td>
					</tr>
					<tr>
						<td colspan="5">
							<table id="tbConItem" class="mainTable" style="margin-top:5px; width:100%;">
								<col width="20%">
								<col width="15%">
								<col width="15%">
								<col width="15%">
								<col width="15%">
								<col width="14%">
								<col width="6%">
								<tr>
									<td>设备名称</td>
									<td>型号</td>
									<td>固定资产编码</td>
									<td>资产现值(元)</td>
									<td>调拨日期</td>
									<td>注</td>
									<td>操作</td>
								</tr>
								<s:if test="statusCd==0 || statusCd==3">
								<tr id="trConItem" style="display: none;margin-bottom:5px;">
									<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[0].assetName"  /></td>
									<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[0].model"  /></td>
									<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[0].assetCode"  /></td>
									<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[0].assetValue"  onblur="formatVal($(this));" /></td>
									<td><input class="inputBorder Wdate" type="text" name="templateBean.fixedAssetsMove2Assets[0].moveDate" onfocus="WdatePicker()"/></td>
									<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[0].remark"  /></td>
									<td><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a></td>
								</tr>
								</s:if>
								<s:set var="conItemCount"><s:property value="templateBean.fixedAssetsMove2Assets.size()"/></s:set>
								<s:iterator value="templateBean.fixedAssetsMove2Assets" var="item" status="s">
									<tr style="margin-bottom:5px;">
										<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[<s:property value="#s.index" />].assetCode" value="${item.assetCode}"/></td>
										<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[<s:property value="#s.index" />].assetName" value="${item.assetName}"/></td>
										<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[<s:property value="#s.index" />].model" value="${item.model}"/></td>
										<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[<s:property value="#s.index" />].assetValue" value="${item.assetValue}" onblur="formatVal($(this));" /></td>
										<td><input class="inputBorder Wdate" type="text" name="templateBean.fixedAssetsMove2Assets[<s:property value="#s.index" />].moveDate" value="${item.moveDate}" onfocus="WdatePicker()"/></td>
										<td><input class="inputBorder" type="text" name="templateBean.fixedAssetsMove2Assets[<s:property value="#s.index" />].remark" value="${item.remark}"/></td>
									  	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
										<td><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
										</s:if>
									</tr>
								</s:iterator>
							</table>
							<s:if test="statusCd==0 || statusCd==3">
							<input type="button"  class="btn_blue_75_55" value="增加条款" onclick="addItem();" />
							</s:if>
						</td>
					</tr>
					<tr>
						<td rowspan="2">调入公司</td>
						<td class="td_title">调入公司</td>
						<td>
							<input validate="required" class="inputBorder" type="text" name="templateBean.inDeptName" value="${templateBean.inDeptName}"/>
						</td>
						<td class="td_title">经办人</td>
						<td>
							<input validate="required"  class="inputBorder" type="text" name="templateBean.inUser" value="${templateBean.inUser}"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">申请调入<br/>固定资产<br/>原因及配<br/>置需求</td>
						<td colspan="3">
							<textarea  class="inputBorder contentTextArea" validate="required" name="templateBean.inReason" rows="6" cols="30">${templateBean.inReason}</textarea>
						</td>
					</tr>
				</table>
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
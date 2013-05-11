<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!--办公资产调拨单-商业-->
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
			<col>
			<col width="40">
			<tr>
				<td align="center">资产编码</td>
				<td align="center">设备名称</td>
				<td align="center">型号</td>
				<td align="center">调出部门</td>
				<td align="center">调入部门</td>
				<td align="center">调拨方式</td>
				<td align="center">原值(元)</td>
				<td align="center">调拨日期</td>
				<td align="center">操作</td>
			</tr>
			<s:if test="statusCd==0 || statusCd==3">
			<tr id="trConItem" style="display: none;margin-bottom:5px;">
				<td>
				<input type="hidden" class="inputBorder" id="assmAccountId" name="templateBean.fixedAssetsMove1Assets[0].assmAccountId">
				<input class="inputBorder" type="text" id="assetCode" name="templateBean.fixedAssetsMove1Assets[0].assetCode"/>
				</td>
				<td><input class="inputBorder" id="assetName" readonly="readonly"  type="text" name="templateBean.fixedAssetsMove1Assets[0].assetName"/></td>
				<td><input class="inputBorder" id="model" readonly="readonly"  type="text" name="templateBean.fixedAssetsMove1Assets[0].model"/></td>
				<td class="tdGroupNodes">
					<input validate="required" id="outDeptName" readonly="readonly"  class="inputBorder" type="text" name="templateBean.fixedAssetsMove1Assets[0].outDeptName"/>
				</td>
				<td>
					<input validate="required" onclick="initSelectFlag($(this))" class="inputBorderNoTip" type="text" name="templateBean.fixedAssetsMove1Assets[0].inDeptName"/>
					<input type="hidden" class="inputBorderNoTip"  name="templateBean.fixedAssetsMove1Assets[0].inDeptCd"  />
				</td>
				<td>
					<select class="inputBorder" id="addWay" name="templateBean.fixedAssetsMove1Assets[0].addWay">
					<option value="3">其他商业公司转入</option>
					<option value="7">商业集团调拨</option>
					</select>
				</td>
				<td>
					<input validate="required" id="unitPrice" readonly="readonly" class="inputBorder" type="text" onblur="formatVal($(this));" name="templateBean.fixedAssetsMove1Assets[0].unitPrice"/>
				</td>
				<td><input validate="required" class="inputBorder Wdate" type="text"  name="templateBean.fixedAssetsMove1Assets[0].moveDate" onfocus="WdatePicker()"/></td>
				<td><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a></td>
			</tr>
			</s:if>
			<s:set var="conItemCount"><s:property value="templateBean.fixedAssetsMove1Assets.size()"/></s:set>
			<s:iterator value="templateBean.fixedAssetsMove1Assets" var="item" status="s">
				<tr style="margin-bottom:5px;">
					<td>
					<input type="hidden" class="inputBorder" id="assmAccountId<s:property value="#s.index" />" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].assmAccountId" value="${item.assmAccountId}">
					<input class="inputBorder" type="text" id="assetCode<s:property value="#s.index" />" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].assetCode" value="${item.assetCode}"/>
					</td>
					<td><input class="inputBorder"  readonly="readonly"  type="text" id="assetName<s:property value="#s.index" />" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].assetName" value="${item.assetName}"/></td>
					<td><input class="inputBorder" readonly="readonly"  type="text" id="model<s:property value="#s.index" />" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].model" value="${item.model}"/></td>
					<td class="tdGroupNodes">
						<input validate="required" readonly="readonly"  class="inputBorder" type="text" id="outDeptName<s:property value="#s.index" />" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].outDeptName" value="${item.outDeptName}"/>
					</td>
					<td>
						<input <s:if test="#isMy==1"> onclick="initSelectFlag($(this))" style="cursor: pointer;" </s:if > validate="required" class="inputBorderNoTip" type="text" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].inDeptName" value="${item.inDeptName}"/>
						<input type="hidden" class="inputBorderNoTip" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].inDeptCd" value="${item.inDeptCd}" />
					</td>
					<td>
					<select class="inputBorder" id="addWay<s:property value="#s.index" />" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].addWay">
						<option value="3" <s:if test="#item.addWay==3">selected="selected"</s:if>>其他商业公司转入</option>
						<option value="7" <s:if test="#item.addWay==7">selected="selected"</s:if>>商业集团调拨</option>
					</select>
					</td>
					<td>
						<input validate="required" readonly="readonly"  class="inputBorder" id="unitPrice<s:property value="#s.index" />" type="text" onblur="formatVal($(this));" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].unitPrice" value="${item.unitPrice}"/>
					</td>
					<td><input validate="required" class="inputBorder Wdate" type="text" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].moveDate" value="${item.moveDate}" onfocus="WdatePicker()"/></td>
					<td>
					<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a>
					</s:if>
					</td>
				</tr>
				<s:if test="#isMy==1">
					<script type="text/javascript">
					var indexTmp='${s.index}';
					var ids={assmAccountId:'assmAccountId'+indexTmp,code:'assetCode'+indexTmp,assmName:'assetName'+indexTmp,assmModelName:'model'+indexTmp,srcValue:'unitPrice'+indexTmp,remainVal:'remainVal'+indexTmp,outDeptName:'outDeptName'+indexTmp};
					$("#assetCode"+indexTmp).quickSearch("${ctx}/assm/assm-account!quickSearch.action",['code','assmName'],ids);
					</script>
				</s:if>
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
		$(trApprover_new).find(".inputBorder,.inputBorderNoTip").each(function(i){
			this.name=this.name.replace('0',index);
			if (this.id){
				this.id=this.id+index;
			}
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		
		var ids={assmAccountId:'assmAccountId'+index,code:'assetCode'+index,assmName:'assetName'+index,assmModelName:'model'+index,srcValue:'unitPrice'+index,remainVal:'remainVal'+index,outDeptName:'outDeptName'+index};
		$("#tbConItem").append(trApprover_new);
		$("#assetCode"+index).quickSearch("${ctx}/assm/assm-account!quickSearch.action",['code','assmName'],ids);
		index++;
	}
	
	function delItem(dom){
		$(dom).parent().parent().remove();
	}

	function initSelectFlag(jDom){
		if( jDom.attr('initselectflg') != '1'){
			jDom.attr('initselectflg','1'); 
			jDom.orgSelect({
				orgMuti:false
			});
			jDom.trigger('click');
		}
	}
</script>
<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
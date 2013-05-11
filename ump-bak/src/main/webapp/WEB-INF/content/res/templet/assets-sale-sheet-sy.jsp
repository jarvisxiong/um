<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

		<%@ include file="template-header.jsp"%>
	<%-- 办公资产出售/报废/遗失申请单(商业公司/总部)--%>
		<div align="center" class="billContent">
			<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
				<%@ include file="template-var.jsp"%>
				<s:if test="statusCd==0 || statusCd==3">
				<table class="mainTable itemTable" id="trItemTmplate" style="margin-top: 20px;">
					<col width="90px">
					<col />
					<col width="90px">
					<col />
					<tr>
						<td class="td_title">资产编码</td>
						<td>
							<input type="hidden" class="inputBorder" id="assmAccountId" name="templateBean.subProperties[0].assmAccountId">
							<input class="inputBorder code" validate="required" id="assetCode" type="text" name="templateBean.subProperties[0].assetCode"/>
						</td>
						<td class="td_title">资产名称</td>
						<td>
							<input class="inputBorder" style="float:left;width: 85%;" id="assetName" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].assetName"/>
							<img style="float:right;cursor:pointer;" 
								 title="删除本资产" 
								 class="deleteItemImg"
								 onclick="delItem(this)" 
								 imgorder='0'
								 src="${ctx}/resources/images/common/del_22_22.gif" 
								 border="0"
							/>
						</td>
					</tr>
					<tr>
						<td class="td_title">设备型号</td>
						<td>
							<input class="inputBorder" id="model" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].model"/>
						</td>
						<td class="td_title">型号编码</td>
						<td>
							<input class="inputBorder" id="assmCode" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].assmCode"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">使用部门</td>
						<td>
							<input class="inputBorder" id="useDept" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].useDept"/>
						</td>
						<td class="td_title">使用责任人</td>
						<td>
							<input id="usePersonName0" validate="required" type="text" readonly="readonly" name="templateBean.subProperties[0].usePersonName" class="inputBorderNoTip userName" style="cursor: pointer;"/>
							<input id="usePersonCd0" type="hidden" class="userCd" name="templateBean.subProperties[0].usePersonCd"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">购置时间</td>
						<td>
							<input class="inputBorder" id="useDate" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].useDate"/>
						</td>
						<td class="td_title">使用年限</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.subProperties[0].useYear" onblur="formatNum($(this));"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">原值(元)</td>
						<td>
							<input class="inputBorder" id="srcValue" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].srcValue"/>
						</td>
						<td class="td_title">净值(元)</td>
						<td>
							<input class="inputBorder" id="remainVal" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].remainVal"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">当前配置</td>
						<td>
							<input class="inputBorder" id="currNum" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].currNum"/>
						</td>
						<td class="td_title">标准配置</td>
						<td>
							<input class="inputBorder" id="stanNum" readonly="readonly" validate="required" type="text" name="templateBean.subProperties[0].stanNum"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">类型</td>
						<td class="chkGroup" align="left" validate="required" colspan="3">
						<table class="tb_checkbox">
							<col width="100">
							<col width="100">
							<col width="100">
							<tr>
							<td><s:checkbox name="templateBean.subProperties[0].saleType1" cssClass="group"></s:checkbox>出售</td>
							<td><s:checkbox name="templateBean.subProperties[0].saleType2"  cssClass="group"></s:checkbox>报废</td>
							<td><s:checkbox name="templateBean.subProperties[0].saleType3"  cssClass="group"></s:checkbox>遗失</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td class="td_title">情况说明</td>
						<td colspan="3">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.subProperties[0].remark"></textarea>
						</td>
					</tr>
					
				</table>
				</s:if>
				<!-- 列表 -->
				<s:set var="conItemCount"><s:property value="templateBean.subProperties.size()"/></s:set>
				<s:if test="templateBean.subProperties.size()>0">
				<s:iterator value="templateBean.subProperties" var="item" status="s">
				<table class="mainTable itemTable" style="margin-top: 10px;" id="listItem<s:property value="#s.index" />">
					<col width="90px">
					<col />
					<col width="90px">
					<col />
					<tr>
						<td class="td_title">资产编码</td>
						<td>
							<input type="hidden" class="inputBorder" id="assmAccountId<s:property value="#s.index" />" name="templateBean.fixedAssetsMove1Assets[<s:property value="#s.index" />].assmAccountId" value="${item.assmAccountId}">
							<input class="inputBorder" id="assetCode<s:property value="#s.index" />" validate="required" type="text" value="${item.assetCode}" name="templateBean.subProperties[<s:property value="#s.index" />].assetCode"/>
						</td>
						<td class="td_title">资产名称</td>
						<td>
							<input class="inputBorder" 
							<s:if test="statusCd==0||statusCd==3">
					     	  style="float:left;width: 85%;" 
					       </s:if>
					       <s:else>
					       	  style="float:left;width: 100%;" 
					       </s:else> 
					       id="assetName<s:property value="#s.index" />" readonly="readonly" validate="required" type="text" value="${item.assetName}" name="templateBean.subProperties[<s:property value="#s.index" />].assetName"/>
							<s:if test="statusCd==0||statusCd==3">
								<img style="float:right;cursor:pointer;" 
									 title="删除本资产" 
									 class="deleteItemImg"
									 onclick="delItemConfirm(this)" 
									 imgorder='<s:property value="#s.index" />'
									 src="${ctx}/resources/images/common/del_22_22.gif" 
									 border="0"
								/>
							</s:if>
						</td>
					</tr>
					<tr>
						<td class="td_title">设备型号</td>
						<td>
							<input class="inputBorder" id="model<s:property value="#s.index" />" readonly="readonly" validate="required" type="text" value="${item.model}" name="templateBean.subProperties[<s:property value="#s.index" />].model"/>
						</td>
						<td class="td_title">型号编码</td>
						<td>
							<input class="inputBorder" id="assmCode<s:property value="#s.index" />" readonly="readonly" validate="required" type="text" value="${item.assmCode}" name="templateBean.subProperties[<s:property value="#s.index" />].assmCode"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">使用部门</td>
						<td>
							<input class="inputBorder" id="useDept<s:property value="#s.index" />" readonly="readonly" value="${item.useDept}" validate="required" type="text" name="templateBean.subProperties[<s:property value="#s.index" />].useDept"/>
						</td>
						<td class="td_title">使用责任人</td>
						<td>
							<input id="usePersonName<s:property value="#s.index" />" validate="required" type="text" value="${item.usePersonName}" readonly="readonly" name="templateBean.subProperties[<s:property value="#s.index" />].usePersonName" <s:if test="#isMy==1"> class="inputBorderNoTip userName singleSelect" style="cursor: pointer;"</s:if><s:else> class="inputBorderNoTip userName" </s:else>/>
							<input id="usePersonCd<s:property value="#s.index" />" type="hidden" class="userCd" value="${item.usePersonCd}" name="templateBean.subProperties[<s:property value="#s.index" />].usePersonCd"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">购置时间</td>
						<td>
							<input class="inputBorder" id="useDate<s:property value="#s.index" />" readonly="readonly" validate="required" type="text" value="${item.useDate}" name="templateBean.subProperties[<s:property value="#s.index" />].useDate"/>
						</td>
						<td class="td_title">使用年限</td>
						<td>
							<input class="inputBorder" validate="required" type="text" name="templateBean.subProperties[<s:property value="#s.index" />].useYear" value="${item.useYear}" onblur="formatNum($(this));"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">原值(元)</td>
						<td>
							<input class="inputBorder" id="srcValue<s:property value="#s.index" />" readonly="readonly" validate="required" type="text" value="${item.srcValue}" name="templateBean.subProperties[<s:property value="#s.index" />].srcValue"/>
						</td>
						<td class="td_title">净值(元)</td>
						<td>
							<input class="inputBorder" id="remainVal<s:property value="#s.index" />" readonly="readonly" validate="required" type="text" value="${item.remainVal}" name="templateBean.subProperties[<s:property value="#s.index" />].remainVal"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">当前配置</td>
						<td>
							<input class="inputBorder" id="currNum<s:property value="#s.index" />" readonly="readonly" validate="required" type="text" value="${item.currNum}" name="templateBean.subProperties[<s:property value="#s.index" />].currNum"/>
						</td>
						<td class="td_title">标准配置</td>
						<td>
							<input class="inputBorder" id="stanNum<s:property value="#s.index" />" readonly="readonly" validate="required" type="text" value="${item.stanNum}" name="templateBean.subProperties[<s:property value="#s.index" />].stanNum"/>
						</td>
					</tr>
					<tr>
						<td class="td_title">类型</td>
						<td colspan="3" validate="required" class="chkGroup"  align="left">
						<table class="tb_checkbox">
							<col width="100">
							<col width="100">
							<col width="100">
							<tr>
							<td><s:checkbox name="templateBean.subProperties[%{#s.index}].saleType1" cssClass="group"></s:checkbox>出售</td>
							<td><s:checkbox name="templateBean.subProperties[%{#s.index}].saleType2"  cssClass="group"></s:checkbox>报废</td>
							<td><s:checkbox name="templateBean.subProperties[%{#s.index}].saleType3"  cssClass="group"></s:checkbox>遗失</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td class="td_title">情况说明</td>
						<td colspan="3">
							<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.subProperties[<s:property value="#s.index" />].remark">${item.remark}</textarea>
						</td>
					</tr>
				</table>
				<s:if test="#isMy==1">
					<script type="text/javascript">
					var indexTmp='${s.index}';
					
						var ids = {
							assmAccountId : 'assmAccountId' + indexTmp,
							code : 'assetCode' + indexTmp,
							assmName : 'assetName' + indexTmp,
							assmModelName : 'model' + indexTmp,
							srcValue : 'srcValue' + indexTmp,
							remainVal : 'remainVal' + indexTmp,
							assmCode : 'assmCode' + indexTmp,
							outDeptName : 'useDept' + indexTmp,
							stanNum : 'stanNum' + indexTmp,
							currNum : 'currNum' + indexTmp,
							useDate : 'useDate' + indexTmp
						};
						$("#assetCode" + indexTmp).quickSearch("${ctx}/assm/assm-account!quickSearch.action",
								[ 'code', 'assmName' ], ids);
					</script>
				</s:if>
				</s:iterator>
				</s:if>
				<div id="listTable"></div>
				
				<s:if test="statusCd==0||statusCd==3">
					<input type="button" class="btn_blue_75_55" style="border:none;margin: 10px 0 20px 0"  value="增加资产" onclick="addItem();" />
				</s:if>
				<%@ include file="template-approver.jsp"%>
			</form>
		</div>
		<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var trApprover=$("#trItemTmplate").clone();
	$("#trItemTmplate").remove();
	var cloneCount = 0;
	var index=0;
	try{
		index = Number("${conItemCount}");
	}catch(e){}
	function addItem(){
		
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(":input").each(function(i){
			this.name=this.name.replace('0',index);
			if (this.id){
				this.id=this.id+index;
			}
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		var userNameDom=$(trApprover_new).find(".userName");
		userNameDom.attr("id","usePersonName"+index);
		userNameDom.userSelect({
			muti:false
		});
		$(trApprover_new).find(".userCd").attr("id","usePersonCd"+index);
		
		
		var ids = {
			assmAccountId : 'assmAccountId' + index,
			code : 'assetCode' + index,
			assmName : 'assetName' + index,
			assmModelName : 'model' + index,
			srcValue : 'srcValue' + index,
			remainVal : 'remainVal' + index,
			assmCode : 'assmCode' + index,
			outDeptName : 'useDept' + index,
			stanNum : 'stanNum' + index,
			currNum : 'currNum' + index,
			useDate : 'useDate' + index
		};
		$("#listTable").append(trApprover_new);
		addClickAction(trApprover_new, true);
		$("#assetCode" + index).quickSearch("${ctx}/assm/assm-account!quickSearch.action", [ 'code', 'assmName' ], ids);
		index++;
		refreshImg();
	}

	function delItem(dom) {
		$(dom).parent().parent().parent().remove();
		refreshImg();
	}
	function delItemConfirm(dom) {
		if (confirm("确认删除该资产?")) {
			delItem(dom);
		}
	}
	function refreshImg() {
		if ($('.deleteItemImg').length == 1) {
			$('.deleteItemImg').hide();
		} else {
			$('.deleteItemImg').show();
		}
	}
	refreshImg();
</script>
<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
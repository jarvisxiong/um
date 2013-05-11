<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 材料设备结算审批表 --%>
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	<c:set var="isSy">false</c:set>
	<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
	<c:set var="isSy">true</c:set>
	</c:if>
	<s:set var="canEdit">
	<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
	true
	</s:if>
	<s:else>
	false
	</s:else>
	</s:set>
	
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
		<table class="mainTable">
			<col width="120"/>
			<col/>
			<col width="135"/>
			<col/>
			<s:if test="aboutHotel">
			<tr>
			<td class="td_title">审批权限</td>
			<td align="left" colspan="3">
				<table class="tb_checkbox" style="width:100%;">
				    <col width="100">
				    <col width="100">
					<col width="100">
					<col width="100">
					<tr>
					<td><s:checkbox name="templateBean.hotel"  cssClass="group"></s:checkbox>与酒店有关</td>
					<td><s:checkbox name="templateBean.estate"  cssClass="group"></s:checkbox>与商业有关</td>
					</tr>
				</table>
			</td>
			</tr>
			</s:if>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
				<input type="hidden" id="contLedgerId"  name="templateBean.contLedgerId" value="${templateBean.contLedgerId}" />
				
				
				<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input id="contNo" class="inputBorder" validate="required" type="text" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:else>
				</td>
				<td class="td_title">合同名称</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="1">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						<input class="inputBorder" readonly="readonly" type="text" id="projectName" name="templateBean.projectName" value="${templateBean.projectName}" />
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
				<td class="td_title">乙方</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="partB" name="templateBean.partB" value="${templateBean.partB}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">丙方</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="partC" name="templateBean.partC" value="${templateBean.partC}" />
				</td>
				<td class="td_title">实际供方</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="realProvName" name="templateBean.realProvName" value="${templateBean.realProvName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">原合同总价</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="totalPrice" name="templateBean.totalPrice" value="${templateBean.totalPrice}" />
				</td>
				<td class="td_title">合同性质</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="contProperty" name="templateBean.contProperty" value="${templateBean.contProperty}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">已支付金额</td>
				<td>
				<input class="inputBorder" validate="required" type="text" id="payMoney" name="templateBean.payMoney" value="${templateBean.payMoney}"  onblur="formatVal($(this));"  onkeyup="doTotalRate(this);"/>
				</td>
				<td class="td_title">占合同总金额</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="tatalRate" name="templateBean.tatalRate" value="${templateBean.tatalRate}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">乙方报审金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.partBReviewAmount" value="${templateBean.partBReviewAmount}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">
				<c:choose>
					<c:when test="${isSy}">
					商业公司审核费用(元)
					</c:when>
					<c:otherwise>
					地产公司审核费用(元)
					</c:otherwise>
				</c:choose>
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.approvedFee" value="${templateBean.approvedFee}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding-right: 0px; padding-left: 0px; ">
				<table id="tbConItem" border="1px solid #8c8f94;" cellpadding="0" cellspacing="0" frame="void" style="width: 100%; border-collapse:collapse;" >
					<col width="120"/>
					<col/>
					<col width="100"/>
					<col/>
					<col width="70"/>
					<col/>
					<col width="40"/>
					<s:if test="statusCd==0 || statusCd==3">
					<tr id="trConItem" style="display: none;margin-bottom:5px;">
						<td align="right">领货单位名称</td>
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].receiveUnitName" /></td>
						<td align="right">相应合同编号</td>
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].receiveContNo" /></td>
						<td align="right">金额(元)</td>
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[0].receiveAmount" onblur="formatVal($(this));" /></td>
						<td width="15px" align="center"><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a></td>
					</tr>
					</s:if>
					<s:iterator value="templateBean.otherProperties" var="item" status="s">
					<tr style="margin-bottom:5px;">
						<td>领货单位名称</td>
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].receiveUnitName" value="${item.receiveUnitName}" /></td>
						<td>相应合同编号</td>
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].receiveContNo" value="${item.receiveContNo}" /></td>
						<td>金额(元)</td>
						<td><input class="inputBorder"  type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].receiveAmount" value="${item.receiveAmount}" onblur="formatVal($(this));" /></td>
						<td width="15px" align="center">
						<s:if test="#canEdit=='true'">
						<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" border="0"/></a>
						</s:if>
						</td>
					</tr>
					</s:iterator>
				</table>
			</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<s:if test="statusCd==0 || statusCd==3">
				<input type="button"  class="btn_green_65_20" style="border:none;"  value="增加单位" onclick="addItem();" />
				</s:if>
				</td>
			</tr>
			<tr>
				<td class="td_title">库存管理单位名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.storageUnitName" value="${templateBean.storageUnitName}" />
				</td>
				<td class="td_title">库存(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.storageAmount" value="${templateBean.storageAmount}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td class="td_title">供货和领货说明</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.description">${templateBean.description}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="4">附件上传</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.settlementSummaryId}">一审结算汇总表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('一审结算汇总表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','settlementSummaryId',event);"/>
				<input type="hidden" name="templateBean.settlementSummaryId" id="settlementSummaryId" value="${templateBean.settlementSummaryId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_settlementSummaryId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settlementSummaryId}','settlementSummaryId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" value="${templateBean.provideUnitSettlementId}">供货单位结算</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('供货单位结算','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','provideUnitSettlementId',event);"/>
				<input type="hidden" name="templateBean.provideUnitSettlementId" id="provideUnitSettlementId" value="${templateBean.provideUnitSettlementId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_provideUnitSettlementId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.provideUnitSettlementId}','provideUnitSettlementId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" value="${templateBean.threePartReceiveId}">三方签收资料</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('三方签收资料','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','threePartReceiveId',event);"/>
				<input type="hidden" name="templateBean.threePartReceiveId" id="threePartReceiveId" value="${templateBean.threePartReceiveId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_threePartReceiveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.threePartReceiveId}','threePartReceiveId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="center" style="height:40px;" <s:if test="statusCd!=2"> validate="required" </s:if> value="${templateBean.curPicId}">现场验收照片(不少于5张，特别是元器件是否对版，否则退单)</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('现场验收照片','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','curPicId',event);"/>
				<input type="hidden" name="templateBean.curPicId" id="curPicId" value="${templateBean.curPicId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_curPicId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.curPicId}','curPicId','${canEdit}');
				</script>
				</td>
			</tr>
			<s:if test="statusCd==2">
			<tr>
				<td class="td_title">集团核定费用(元)</td>
				<td colspan="3">
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="groupMoney" name="templateBean.groupMoney" value="${templateBean.groupMoney}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			</s:if>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
	}
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=${conItemCount};
	
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
	function doTotalRate(dom){
		if($("#payMoney").val()!=0){
			var totalPrice = formatFloat($("#totalPrice").val());
			$("#tatalRate").val(((getValByJ($(dom))/totalPrice)*100).toFixed(2));
		}
	}
</script>
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
<s:if test="#canEdit=='true'">
	<script type="text/javascript">
		var mapPrama={
			contLedgerId:'contLedgerId',
			contNo:'contNo',
			contName:'contName',
			projectCd:'projectCd',
			projectName:'projectName',
			partB:'partB',
			totalPrice:'totalPrice',
			contProperty:'contProperty',
			partC:'partC',
			realProvName: 'realProvName'
		};
		
	</script>
	<!-- 地产/商业/酒店 codeType==1||codeType==2||codeType==3 -->
	<s:if test="authTypeCd=='BLSY_CBGL_HTJS_10' || authTypeCd=='BLSY_CBGL_HTJS_30' || authTypeCd=='BLSY_CBGL_HTJS_40'
		|| authTypeCd=='SYGS_CBGL_HTJS_10'|| authTypeCd=='SYGS_CBGL_HTJS_30'|| authTypeCd=='SYGS_CBGL_HTJS_40'">
		<script type="text/javascript">
			$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{codeType:'2'});
		</script>
	</s:if>
	<s:elseif test="authTypeCd=='JD_JSGL_50' || authTypeCd=='JD_JSGL_65' || authTypeCd=='JD_JSGL_70'">
		<script type="text/javascript">
			$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{codeType:'3'});
		</script>
	</s:elseif>
	<s:else>
		<script type="text/javascript">
			$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama);
		</script>
	</s:else>
</s:if>

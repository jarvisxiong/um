<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!--材料设备价格批定审批表-->
<%@ include file="template-header.jsp"%>
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
			<col width="120"/>
			<col/>
			<col width="120"/>
			<col width="120"/>
			<col width="120"/>
			<col/>
			<c:choose>
				<c:when test="${isSy}">
				<input type="hidden" id="codeType" value="2"/>
				</c:when>
				<c:otherwise>
				<input type="hidden" id="codeType" value="1"/>
				</c:otherwise>
			</c:choose>
			<tr>
				<td></td>
				<td colspan="5" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.in5percent"  cssClass="group"></s:checkbox>与约定价格差额5%以内
					<s:checkbox name="templateBean.others"  cssClass="group"></s:checkbox>其他情况
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td colspan="2" align="left">
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
				<td align="left" colspan="2">
				<input class="inputBorder" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}" />
				</td>
			</tr>
			
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="2">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">工程名称</td>
				<td colspan="2">
				<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">合同双方</td>
				<td colspan="5" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="40"/>
						<tr>
						<td>甲方:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.sideA" value="${templateBean.sideA}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="40"/>
						<tr>
						<td>乙方:</td>
						<td>
						<input class="inputBorderNoTip" id="sideB" validate="required" type="text" name="templateBean.sideB" value="${templateBean.sideB}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td colspan="5" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="40"/>
						<tr>
						<td>丙方:</td>
						<td>
						<input class="inputBorderNoTip" id="sideC" type="text" name="templateBean.sideC" value="${templateBean.sideC}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td class="td_title">批价类型</td>
				<td colspan="5" class="chkGroup" validate="required">
				<s:checkbox name="templateBean.provideB" cssClass="group" ></s:checkbox>甲定乙供
				<s:checkbox name="templateBean.provideA" cssClass="group" ></s:checkbox>甲供合同增补
				</td>
			</tr>
			<tr>
				<td class="td_title">材料名称</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.equipMaterialName" value="${templateBean.equipMaterialName}"/></td>
				<td class="td_title">进场时间</td>
				<td colspan="2"><input onfocus="WdatePicker()" class="Wdate inputBorder" style="width:150px;" validate="required" type="text" name="templateBean.approachDate" value="${templateBean.approachDate}"/></td>
			</tr>
			<tr>
			
			<tr class="provideA" id="approve_a1" style="display: none">
				<td class="td_title">原合同价(元)</td>
				<td colspan="2"><input class="inputBorder" type="text" name="templateBean.oriContractPrice" value="${templateBean.oriContractPrice}" onblur="formatVal($(this));"/></td>
				<td class="td_title">上报总价(元)</td>
				<td colspan="2"><input class="inputBorder" type="text" name="templateBean.reportTotalPrice" value="${templateBean.reportTotalPrice}" onblur="formatVal($(this));"/></td>
			</tr>
			<tr class="provideA"  id="approve_a2" style="display: none">
				<td class="td_title">增补原因说明</td>
				<td colspan="5" ><textarea class="inputBorder contentTextArea" name="templateBean.addReason">${templateBean.addReason}</textarea></td>
			</tr>
			
			
			<tr class="provideB" id="approve_b1_1">
				<td class="td_title">原合同约定的品牌</td>
				<td colspan="5"><input class="inputBorder" validate="required" type="text" name="templateBean.oriContractBrand" value="${templateBean.oriContractBrand}" /></td>
			</tr>
			<tr class="provideB" id="approve_b1">
				<td class="td_title">原合同单价(元)</td>
				<td colspan="2"><input class="inputBorder" validate="required" type="text" name="templateBean.oriContractUnitPrice" value="${templateBean.oriContractUnitPrice}" onblur="formatVal($(this));"/></td>
				<td class="td_title">原合同总价(元)</td>
				<td colspan="2" align="left"><input class="inputBorder" validate="required" type="text" name="templateBean.oriContractTotalPrice" value="${templateBean.oriContractTotalPrice}" onblur="formatVal($(this));"/></td>
			</tr>
			
			<tr class="provideB"  id="approve_b2_2">
				<td class="td_title">推荐品牌</td>
				<td colspan="5"><input class="inputBorder" type="text" name="templateBean.commendBrand" value="${templateBean.commendBrand}" /></td>
			</tr>
			<tr class="provideB"  id="approve_b2">
				<td class="td_title">推荐品牌单价(元)</td>
				<td colspan="2"><input class="inputBorder" type="text" name="templateBean.commendBrandUnitPrice" value="${templateBean.commendBrandUnitPrice}" onblur="formatVal($(this));"/></td>
				<td class="td_title">推荐品牌总价(元)</td>
				<td colspan="2" align="left"><input class="inputBorder" validate="required" type="text" name="templateBean.commendBrandTotalPrice" value="${templateBean.commendBrandTotalPrice}" onblur="formatVal($(this));"/></td>
			</tr>
			<tr class="provideB" id="approve_b3">
				<td class="td_title">需批定价格原因说明</td>
				<td colspan="5" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.approvePriceReason">${templateBean.approvePriceReason}</textarea></td>
			</tr>
			<tr class="provideB" id="approve_b4">
				<td class="td_title">推荐改品牌的理由</td>
				<td colspan="5" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.commendBrandReason">${templateBean.commendBrandReason}</textarea></td>
			</tr>
			
			<tr>
				<td class="td_title" rowspan="4">应提供细项<br/>(请作为附件上传)</td>
				<td colspan="2" style="height:40px;" validate="required" value="${templateBean.meterialQuantityListId}">材料数量清单（工程副总签字确认）</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('材料数量清单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','meterialQuantityListId',event);"/>
				<input type="hidden" name="templateBean.meterialQuantityListId" id="meterialQuantityListId" value="${templateBean.meterialQuantityListId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_meterialQuantityListId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.meterialQuantityListId}','meterialQuantityListId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="height:40px;" validate="required" value="${templateBean.sideBPriceListId}">乙方报价清单</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('乙方报价清单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','sideBPriceListId',event);"/>
				<input type="hidden" name="templateBean.sideBPriceListId" id="sideBPriceListId" value="${templateBean.sideBPriceListId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_sideBPriceListId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.sideBPriceListId}','sideBPriceListId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="height:40px;" value="${templateBean.teckParamRequireId}">技术参数要求</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('技术参数要求','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','teckParamRequireId',event);"/>
				<input type="hidden" name="templateBean.teckParamRequireId" id="teckParamRequireId" value="${templateBean.teckParamRequireId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_teckParamRequireId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.teckParamRequireId}','teckParamRequireId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="height:40px;" value="${templateBean.relatedDrawingId}">相关图纸</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('相关图纸','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','relatedDrawingId',event);"/>
				<input type="hidden" name="templateBean.relatedDrawingId" id="relatedDrawingId" value="${templateBean.relatedDrawingId}"/>
				</s:if>
				</td>
				<td colspan="2">
				<div id="show_relatedDrawingId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.relatedDrawingId}','relatedDrawingId','${canEdit}');
				</script>
				</td>
			</tr>
			<s:if test="statusCd==2">
			<tr>
				<td align="center">核定费用</td>
				<td colspan="5">
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="groupMoney" name="templateBean.groupMoney" value="${templateBean.groupMoney}" onblur="formatVal($(this));"/>
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
	function onclick_selectPriceType(obj) {
		if(obj.name == "templateBean.provideB" && obj.checked) {
			showType1();
		} else if(obj.name == "templateBean.provideA" && obj.checked) {
			showType2();
		} else {
			
		}
	}
	function showType1() {
		hideTR("approve_a1");
		hideTR("approve_a2");
		showTR("approve_b1_1");
		showTR("approve_b1");
		showTR("approve_b2_2");
		showTR("approve_b2");
		showTR("approve_b3");
		showTR("approve_b4");
	}
	function showType2() {
		showTR("approve_a1");
		showTR("approve_a2");
		hideTR("approve_b1_1");
		hideTR("approve_b1");
		hideTR("approve_b2_2");
		hideTR("approve_b2");
		hideTR("approve_b3");
		hideTR("approve_b4");
	}
	function hideTR(trId) {
		var trObj = $("#"+trId);
		trObj.hide();
		$(trObj).find(".inputBorder").each(function(i){
			$(this).attr("class","inputBorder");
			$(this).removeAttr("validate");
			$(this).removeAttr("title");
		});
	}
	function showTR(trId) {
		var trObj = $("#"+trId);
		trObj.show();
		$(trObj).find(".inputBorder").each(function(i){
			$(this).attr("validate","required");
		});
	}
</script>

<s:if test="#canEdit=='true'">
<script type="text/javascript">

	var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		contName:'contName'
	};
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{codeType:$('#codeType').val()});
</script>
</s:if>

<s:if test="templateBean.provideA=='true'">
<script type="text/javascript">
	showType2();
	//$(".provideB").hide();
	//$(".provideA").show();
</script>
</s:if>
<s:else>
<script type="text/javascript">
	showType1();
	//$(".provideA").hide();
	//$(".provideB").show();
</script>
</s:else>
<s:if test="#isMy==1">
<script type="text/javascript">
	$(":checkbox").bind("click",function(){
		onclick_selectPriceType(this);
	});
</script>
</s:if>
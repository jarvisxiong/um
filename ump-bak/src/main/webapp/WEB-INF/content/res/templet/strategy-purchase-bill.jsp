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

<c:set var="bizEntityId" value="${resApproveInfoId==null?entityTmpId:resApproveInfoId}"/>
<c:set var="isSy">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
<c:set var="isSy">true</c:set>
</c:if>
<div align="center" class="billContent">

	
	<form action="res-approve-info!save.action" method="post" id="templetForm" >
		<%@ include file="template-var.jsp"%>
		<%--不自动设置项目编号 --%>
	     <input type="hidden" id="isAutosetProject" value="0"/>
		<%--合同文本库引用 start--%>
	    <%@ include file="bid-contract-strategy-base.jsp"%>
		<s:if test="statusCd!=0">
		<%@ include file="bid-contract-mark.jsp" %>
		</s:if>
		<%--合同文本引用 end --%>
		<table  class="mainTable" id="mainTable">
			<col width="130"/>
			<col/>
			<col width="140"/>
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
				<td class="td_title">标题</td>
				<td colspan="3">
					<input validate="required" class="inputBorder" type="text" name="templateBean.titleName" value="${templateBean.titleName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="2">
				<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip " title="请输入公司名称,并选中"</s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td align="left"><s:checkbox id="haierFlg" name="templateBean.haierFlg" cssClass="group"></s:checkbox>海尔</td>
			</tr>
			<tr>
				<td class="td_title">战略合同名称</td>
				<td colspan="3"><input class="inputBorder" type="text" validate="required" name="templateBean.contactName" value="${templateBean.contactName}"  /></td>
			</tr>
			<tr>
				<td class="td_title">甲方</td>
				<td colspan="3">
				<input class="inputBorderNoTip" validate="required" type="text" name="templateBean.partA" value="${templateBean.partA}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">乙方</td>
				<td colspan="3">
					<input class="inputBorderNoTip" id="partBName" validate="required" type="text" name="templateBean.partBName" value="${templateBean.partBName}" />
					<input id="partB" type="hidden" name="templateBean.partB" value="${templateBean.partB}" />
				</td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td class="td_title">丙方</td>
				<td colspan="3">
					<input class="inputBorderNoTip" id="partCName" type="text" name="templateBean.partCName" value="${templateBean.partCName}" />
					<input id="partC" type="hidden" name="templateBean.partC" value="${templateBean.partC}" />
				</td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td class="td_title">实际供方</td>
				<td colspan="3">
					<input class="inputBorderNoTip" type="text" name="templateBean.partBReal" value="${templateBean.partBReal}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">材料名称</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.materialName" value="${templateBean.materialName}"/></td>
				<td class="td_title">使用区域</td>
				<td><input  class="inputBorder" validate="required" type="text" name="templateBean.useCoverage" value="${templateBean.useCoverage}"/></td>
			</tr>
			<tr>
				<td class="td_title">采购周期</td>
				<td><input class="inputBorder" validate="required" type="text" name="templateBean.purchasePreiod" value="${templateBean.purchasePreiod}"/></td>
				<td class="td_title">进场时间</td>
				<td align="left"><input onfocus="WdatePicker()" class="inputBorder Wdate" style="width:100px;" validate="required" type="text" name="templateBean.enterDate" value="${templateBean.enterDate}"/></td>
			</tr>
			<tr>
				<td class="td_title">采购总价(元)</td>
				<td colspan="3"><input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.purchaseTotalAmt" value="${templateBean.purchaseTotalAmt}"/></td>
			</tr>	
			<tr otype="attach">
				<td class="td_title" >战略外价格</td>
				<td resattachname="战略外价格"><input class="inputBorder" validate="required" type="text" name="templateBean.beyondStraAmt" value="${templateBean.beyondStraAmt}"/></td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('战略外价格','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','beyondStraAmtId',event);"  />
				<input type="hidden" name="templateBean.beyondStraAmtId" id="beyondStraAmtId" value="${templateBean.beyondStraAmtId}"/>
				</s:if>
				</td>
				<td align="left">
				<div id="show_beyondStraAmtId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.beyondStraAmtId}','beyondStraAmtId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td class="td_title">订单说明</td>
				<td colspan="3" ><textarea class="inputBorder contentTextArea" validate="required" name="templateBean.orderDesc">${templateBean.orderDesc}</textarea></td>
			</tr>
			<tr otype="attach">
				<td id="supplyDetail" class="td_title">应提供细项<br/>(请作为附件上传)</td>
				<td colspan="3">
					
					<%--若网批已通过,则不校验 false ;否则校验 true. --%>
					<s:set var="needValidFlg">
					<s:if test="statusCd==2">false</s:if><s:else>true</s:else>
					</s:set>
					
					<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="supplyTradeFileId" fileValue="${templateBean.supplyTradeFileId}" title="供应商与贸易公司订货合同" isRequired="${needValidFlg}" isRele2Contract="true"/>
					<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="tradeProjectFileId" fileValue="${templateBean.tradeProjectFileId}" title="贸易公司与项目公司订货合同" isRequired="${needValidFlg}" isRele2Contract="true"/>
					<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="tradeHaierFileId" fileValue="${templateBean.tradeHaierFileId}" title="贸易公司与海尔公司订货合同" isRequired="${needValidFlg}"  isRele2Contract="true"/>
					<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="haierProjectFileId" fileValue="${templateBean.haierProjectFileId}" title="海尔公司与项目公司订货合同" isRequired="${needValidFlg}"  isRele2Contract="true"/>
					<r:fileUpload canEdit="${canEdit}" bizEntityId="${bizEntityId}" fileId="purchaseTechConfirmFileId" fileValue="${templateBean.purchaseTechConfirmFileId}" title="采购数量及技术参数确认单<br/>(须工程副总以上人员签字)" isRequired="${needValidFlg }" isRele2Contract="true" />
				</td>
			</tr>
		    <s:if test="statusCd==2">
			<tr>
				<td class="td_title">合同编号(必填)</td>
				<td>
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="contractNo" name="templateBean.contractNo" value="${templateBean.contractNo}"<s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> onclick="contNoValidate(this);"</s:if>/>
				</td>
				<td class="td_title">审定价(元)(必填)</td>
				<td>
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="contractPrice" name="templateBean.contractPrice" value="${templateBean.contractPrice}" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported">onblur="formatVal($(this));"</s:if>/>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号2(非必填)</td>
				<td>
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' </s:if> type="text" id="contractNo2" name="templateBean.contractNo2" value="${templateBean.contractNo2}" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> onclick="contNoValidate(this);"</s:if>/>
				</td>
				<td class="td_title">审定价2(元)(非必填)</td>
				<td>
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' </s:if> type="text" id="contractPrice2" name="templateBean.contractPrice2" value="${templateBean.contractPrice2}" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported">onblur="formatVal($(this));"</s:if>/>
				</td>
			</tr> 
			</s:if>
		</table>
		
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<script type="text/javascript">
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
chooseMonopoly();
function chooseMonopoly() {
	var haierFlg=$("#haierFlg").attr("checked");
	if(haierFlg){
		$("#div_tradeProjectFileId").hide();
		$("#div_tradeHaierFileId").show();
		$("#div_haierProjectFileId").show();
		
		if('${statusCd}' != '2'){
			$("#div_vali_tradeProjectFileId").removeAttr("validate");
			$("#div_vali_tradeHaierFileId").attr("validate","required");
			$("#div_vali_haierProjectFileId").attr("validate","required");
		}
	}else{
		$("#div_tradeProjectFileId").show();
		$("#div_tradeHaierFileId").hide();
		$("#div_haierProjectFileId").hide();

		if('${statusCd}' != '2'){
			$("#div_vali_tradeProjectFileId").attr("validate","required");
			$("#div_vali_tradeHaierFileId").removeAttr("validate");
			$("#div_vali_haierProjectFileId").removeAttr("validate");
		}
	}

	reloadContAttachToRes();
}
$("#haierFlg").bind("click",chooseMonopoly);
</script>
<s:if test="#canEdit== 'true'">
<script type="text/javascript">
	//$("#partBName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'partBName',sid:'partB'});
	//$("#contactNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contName','contNo'],{contNo:"contactNoId",contName:"contactNameId"});
$("#projectName").quickSearch("${ctx}/cont/cont-project-code!quickSearch.action",['projectName'],{projectCd:'projectCd',projectName:'projectName'},{codeType:$('#codeType').val()});
chooseMonopoly();

</script>
</s:if>
<script>
//是否回执标准库选择回调函数
var isCallChooseCont=true;
//是否有选择合同库
if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
	userCont('true');
}else{
	userCont('false');
}
</script>
<%@ include file="template-footer.jsp"%>

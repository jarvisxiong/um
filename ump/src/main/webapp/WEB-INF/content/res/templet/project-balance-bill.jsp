<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 工程结算审批表 --%>
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
	
<c:set var="bizEntityId" value="${resApproveInfoId==null?entityTmpId:resApproveInfoId}"/>
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="125"/>
			<col/>
			<s:if test="aboutHotel">
			<tr>
				<td class="td_title"></td>
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
				<td colspan="3">
				
				<input type="hidden" name="templateBean.contLedgerId" id='contLedgerId' value="${templateBean.contLedgerId}"/>
				
				<s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" id="contNo" name="templateBean.contNo" value="${templateBean.contNo}"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同名称</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="contName" name="templateBean.contName" value="${templateBean.contName}"/>
				</td>
				<td class="td_title">乙方</td>
				<td>
				<input class="inputBorder" id="partB" readonly="readonly" type="text" name="templateBean.partB" value="${templateBean.partB}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						<input class="inputBorder" id="projectName" readonly="readonly" type="text" name="templateBean.projectName" value="${templateBean.projectName}"/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" id="projectPeriod" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
				<td class="td_title">面积(m2)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" id="area" name="templateBean.area" value="${templateBean.area}" onblur="formatVal($(this));callBack();"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">合同性质</td>
				<td colspan="3">
					<input class="inputBorder" id="contCharacter" readonly="readonly" type="text" name="templateBean.contCharacter" value="${templateBean.contCharacter}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">原合同总价</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="totalPrice" name="templateBean.oriTotalPrice" value="${templateBean.oriTotalPrice}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">原单方指标(元/m2)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="oriNorm" name="templateBean.oriNorm" value="${templateBean.oriNorm}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">已确认合同总价</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="confirmToalPrice" name="templateBean.confirmToalPrice" value="${templateBean.confirmToalPrice}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">现单方指标(元/m2)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="confirmNorm" name="templateBean.confirmNorm" value="${templateBean.confirmNorm}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">累计变更费用(元)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="changeTotal" name="templateBean.changeTotal" value="${templateBean.changeTotal}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">累计变更比率(%)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="changeRate" name="templateBean.changeRate" value="${templateBean.changeRate}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">已支付金额</td>
				<td>
				<input class="inputBorder" validate="required" type="text" id="payMoney" name="templateBean.payMoney" value="${templateBean.payMoney}"  onblur="formatVal($(this));callBack();" onkeyup="doTotalRate(this);"/>
				</td>
				<td class="td_title">占合同总金额</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="tatalRate" name="templateBean.tatalRate" value="${templateBean.tatalRate}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">乙方报审金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" id="applyMoneyB" name="templateBean.applyMoneyB" value="${templateBean.applyMoneyB}" onblur="formatVal($(this));callBack();"/>
				</td>
				<td class="td_title">相应单方指标(元/m2)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="normB" name="templateBean.normB" value="${templateBean.normB}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">
				<c:choose>
					<c:when test="${isSy}">
					商业公司审核金额(元)
					</c:when>
					<c:otherwise>
					地产公司审核金额(元)
					</c:otherwise>
				</c:choose>
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text" id="landApproveMoney" name="templateBean.landApproveMoney" value="${templateBean.landApproveMoney}" onblur="formatVal($(this));callBack();"/>
				</td>
				<td class="td_title">相应单方指标(元/m2)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="normland" name="templateBean.normland" value="${templateBean.normland}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">
				<c:choose>
					<c:when test="${isSy}">
					立项金额(元)
					</c:when>
					<c:otherwise>
					目标成本(元)
					</c:otherwise>
				</c:choose>
				</td>
				<td>
				<input class="inputBorder" validate="required" type="text" id="targetMoney" name="templateBean.targetMoney" value="${templateBean.targetMoney}" onblur="formatVal($(this));callBack();"/>
				</td>
				<td class="td_title">相应单方指标(元/m2)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="normTarget" name="templateBean.normTarget" value="${templateBean.normTarget}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">工程结算额及说明</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.remark">${templateBean.remark}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">资料上传<br/>(请作为附件上传)</td>
				<td colspan="3">
				<r:fileUpload canEdit="${canEdit}" fileId="firstApproveFileId" fileValue="${templateBean.firstApproveFileId}" bizEntityId="${bizEntityId}" title="一审结算汇总表" isRequired="true"/>
				<r:fileUpload canEdit="${canEdit}" fileId="balanceItemFileId" fileValue="${templateBean.balanceItemFileId}"  bizEntityId="${bizEntityId}" title="结算争议事项汇总表"  isRequired="false"/>
				<r:fileUpload canEdit="${canEdit}" fileId="leaveProblemFileId"  fileValue="${templateBean.leaveProblemFileId}" bizEntityId="${bizEntityId}" title="工程竣工遗留问题确认单" isRequired="true"/>
				<r:fileUpload canEdit="${canEdit}" fileId="balanceApproveFileId" fileValue="${templateBean.balanceApproveFileId}"  bizEntityId="${bizEntityId}" title="结算审核意见书" isRequired="true"/>
				<r:fileUpload canEdit="${canEdit}" fileId="jgcSbFileId" fileValue="${templateBean.jgcSbFileId}"  bizEntityId="${bizEntityId}" title="甲供材/设备结算核对资料"  isRequired="false"/>
				<r:fileUpload canEdit="${canEdit}" fileId="consUnitFileId" fileValue="${templateBean.consUnitFileId}"  bizEntityId="${bizEntityId}" title="施工单位预算附件"  isRequired="false"/>
				<r:fileUpload canEdit="${canEdit}" fileId="landCostFileId" fileValue="${templateBean.landCostFileId}"  bizEntityId="${bizEntityId}" title="地产公司成本部审核预算附件"  isRequired="false"/>
				<r:fileUpload canEdit="${canEdit}" fileId="drawingListFileId" fileValue="${templateBean.drawingListFileId}"  bizEntityId="${bizEntityId}" title="图纸及目录"  isRequired="false"/>
				<r:fileUpload canEdit="${canEdit}" fileId="oriContractFileId"  fileValue="${templateBean.oriContractFileId}" bizEntityId="${bizEntityId}" title="原合同扫描件"  isRequired="false"/>
				<r:fileUpload canEdit="${canEdit}" fileId="curPicId" fileValue="${templateBean.curPicId}"  bizEntityId="${bizEntityId}" title="现场工程验收照片(不少于10张，否则退单)" isRequired="true"/>
				</td>
			</tr>
			
			<s:if test="statusCd==2">
			<tr>
				<td class="td_title">集团核定费用(元)</td>
				<td>
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required"</s:if> type="text" id="groupMoney" name="templateBean.groupMoney" value="${templateBean.groupMoney}" onblur="formatVal($(this));calcNorm('groupMoney','normGroup');"/>
				</td>
				<td class="td_title">单方指标(元/m2)</td>
				<td>
				<input class="inputBorder" readonly="readonly" type="text" id="normGroup" name="templateBean.normGroup" value="${templateBean.normGroup}"/>
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
	var map={
			contLedgerId:"contLedgerId",
			contNo:"contNo",
			contName:"contName",
			projectCd:"projectCd",
			projectName:"projectName",
			partB:"partB",
			totalPrice:"totalPrice",
			visaTotal:"changeTotal",
			contProperty:"contCharacter",
			updateTotal:"confirmToalPrice"
		};
	
	function callBack(){
		calcAllNorm();
		calcRate();
	}
	function calcAllNorm(){
		calcNorm('totalPrice','oriNorm');
		calcNorm('confirmToalPrice','confirmNorm');
		calcNorm('applyMoneyB','normB');
		calcNorm('landApproveMoney','normland');
		calcNorm('targetMoney','normTarget');
		calcRate();
	}
	function calcRate(){
		var money=$("#changeTotal").val();
		var confirmToalPrice=$("#confirmToalPrice").val();
		if (money&&confirmToalPrice){
			money=formatFloat(money);
			confirmToalPrice=formatFloat(confirmToalPrice);
			var norm=money/confirmToalPrice;
			$("#"+normId).val(norm);
			formatRate($(this));
		}else{
			$("#"+normId).val('');
		}
	}
	function calcNorm(moneyId,normId){
		var money=$("#"+moneyId).val();
		var area=$("#area").val();
		if (money && area){
			money=formatFloat(money);
			area=formatFloat(area);
			var norm=money/area;
			$("#"+normId).val(norm);
			formatVal($("#"+normId));
		}else{
			$("#"+normId).val('');
		}
	}
	function doTotalRate(dom){
		if($("#payMoney").val()!=0){
			var confirmToalPrice = formatFloat($("#confirmToalPrice").val());
			$("#tatalRate").val(((getValByJ($(dom))/confirmToalPrice)*100).toFixed(2));
		}
	}
</script>

<s:if test="authTypeCd=='BLSY_CBGL_HTJS_20' || authTypeCd=='BLSY_CBGL_HTJS_13' || authTypeCd=='BLSY_CBGL_HTJS_15'
|| authTypeCd=='SYGS_CBGL_HTJS_20' || authTypeCd=='SYGS_CBGL_HTJS_13' || authTypeCd=='SYGS_CBGL_HTJS_15'
">
<script type="text/javascript">
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],map,{codeType:'2'},callBack);
</script>
</s:if>
<s:elseif test="authTypeCd=='JD_JSGL_55'">
	<script type="text/javascript">
		$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],map,{codeType:'3'},callBack);
	</script>
</s:elseif>
<s:else>
<script type="text/javascript">
	$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],map,{codeType:'0'},callBack);
</script>
</s:else>
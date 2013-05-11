<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--材料设备结算审批表 --%>
<%@ include file="template-header.jsp"%>

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

<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table  class="mainTable">
			<col width="120"/>
			<col/>
			<col width="80"/>
			<col/>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="3">
					<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" readonly="readonly" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
					<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">工程地点</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringPlace" value="${templateBean.engineeringPlace}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
					
					
					<input type="hidden" id="contLedgerId"  />
					<s:if test="!templateBean.contractNo.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getContByno('${templateBean.contractNo}');">${templateBean.contractNo}</span>
						<input id="contactNoId" type="hidden" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" id="contactNoId" name="templateBean.contractNo" value="${templateBean.contractNo}" />
					</s:else>
				</td>
				<td class="td_title">合同金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.contractAmount" value="${templateBean.contractAmount}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">供货合同名称</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text"  readonly="readonly" id="contractNameId" name="templateBean.contractName" value="${templateBean.contractName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">供货单位</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.vendor" value="${templateBean.vendor}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">供货单位报送金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.renderAmount" value="${templateBean.renderAmount}" />
				</td>
				<td class="td_title">承包方式</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.contractWay" value="${templateBean.contractWay}"  />
				</td>
			</tr>
			<tr>
				<td rowspan="4" class="td_title">
				<c:choose>
					<c:when test="${isSy}">
					商业公司审核金额
					</c:when>
					<c:otherwise>
					地产公司审核金额
					</c:otherwise>
				</c:choose>
				</td>
				<td class="td_title">供货单位(元)</td>
				<td colspan="2">
					<input class="inputBorder" validate="required" onblur="formatVal($(this));" type="text" name="templateBean.vendorAmount" value="${templateBean.vendorAmount}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">领货单位名称1(元)</td>
				<td colspan="2">
					<input class="inputBorder" onblur="formatVal($(this));" type="text" name="templateBean.receiveAmount1" value="${templateBean.receiveAmount1}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">领货单位名称2(元)</td>
				<td colspan="2">
					<input class="inputBorder" onblur="formatVal($(this));" type="text" name="templateBean.receiveAmount2" value="${templateBean.receiveAmount2}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">库存(元)</td>
				<td colspan="2">
					<input class="inputBorder" onblur="formatVal($(this));" type="text" name="templateBean.stockAmount" value="${templateBean.stockAmount}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">供货/领货情况说明</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.info">${templateBean.info}</textarea>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
function getContByno(contNo){
	var data = {contNo:contNo};
    var getContUrl="${ctx}/cont/cont-ledger!detail.action";
	var url;
	$.post(getContUrl,data, function(result){
		$("#contLedgerId").val(result);
		url="${ctx}/cont/cont-ledger!input.action?id="+$("#contLedgerId").val();
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
	});
	
}
	$("#contactNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:"contactNoId",contName:"contractNameId"});
</script>


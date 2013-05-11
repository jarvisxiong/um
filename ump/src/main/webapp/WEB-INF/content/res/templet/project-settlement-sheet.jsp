<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<!--工程结算审批表 -->
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<%@ include file="template-header.jsp"%>
<div align="center" class="billContent">
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<table class="mainTable">
			<col width="110"/>
			<col/>
			<col width="110"/>
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
					<input class="inputBorder" validate="required" type="text" name="templateBean.projectPlace" value="${templateBean.projectPlace}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">工程名称</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">施工单位</td>
				<td colspan="3">
					<input class="inputBorder" validate="required" type="text" name="templateBean.builder" value="${templateBean.builder}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
					
					<input class="inputBorder" type="hidden" id="contactNameId"/>
					
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
				<td class="td_title">结算内容</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.settlementContent" value="${templateBean.settlementContent}" />
				</td>
				<td class="td_title">承包方式</td>
				<td>
					<input class="inputBorder" validate="required" type="text" name="templateBean.contractWay" value="${templateBean.contractWay}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">施工单位报送金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.builderAmount" value="${templateBean.builderAmount}" />
				</td>
				<td class="td_title">地产公司审核金额(元)</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.estateAmount" value="${templateBean.estateAmount}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">面积（m2）</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.acreage" value="${templateBean.acreage}" />
				</td>
				<td class="td_title">单位指标（元/m2）</td>
				<td>
					<input class="inputBorder" validate="required" type="text" onblur="formatVal($(this));" name="templateBean.indicator" value="${templateBean.indicator}"  />
				</td>
			</tr>
			<tr>
				<td align="left" colspan="4">注：主体工程按建筑面积，室外工程按土地面积</td>
			</tr>
			<tr>
				<td class="td_title">工程结算额及说明</td>
				<td colspan="3">
					<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.info">${templateBean.info}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="2">应提供细项<br/>(请作为附件上传)</td>
				<td style="height:40px;"  validate="required" value="${templateBean.initAmountFileId}"s>初审金额汇总表</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('初审金额汇总表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','initAmountFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.initAmountFileId" id="initAmountFileId" value="${templateBean.initAmountFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_initAmountFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.initAmountFileId}','initAmountFileId','${canEdit}');
				</script>
				</td>
				
			</tr>
			<tr>
				<td style="height:40px;" validate="required" value="${templateBean.settDetailFileId}">结算资料明细表</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('结算资料明细表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','settDetailFileId',event);"/>
				<input  validate="required" type="hidden" name="templateBean.settDetailFileId" id="settDetailFileId" value="${templateBean.settDetailFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_settDetailFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.settDetailFileId}','settDetailFileId','${canEdit}');
				</script>
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
	$("#contactNoId").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],{contNo:"contactNoId",contName:"contactNameId"});
</script>

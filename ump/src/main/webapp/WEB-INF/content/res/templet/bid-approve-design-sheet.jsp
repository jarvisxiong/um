<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 定标审批表（设计类） --%>
<%@ include file="template-header.jsp"%>

<script type="text/javascript">
	function onchange_DesignTotalDay() {
		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		if(isEmpty(fromDate) || isEmpty(toDate)) {
			return;
		}
		var fArray = fromDate.split("-");
		var tArray = toDate.split("-");
		var fDate = new Date(fArray[0],fArray[1]-1,fArray[2]);
		var tDate = new Date(tArray[0],tArray[1]-1,tArray[2]);
		if(tDate.getTime()<fDate.getTime()) {
			//alert("结束时间不能小于开始时间");
			$("#totalDay").val("");
			return;
		}
		var day = Math.abs(tDate.getTime()-fDate.getTime())/1000/60/60/24+1;
		$("#totalDay").val(day);
	}
</script>

<div align="center" class="billContent">
	<c:set var="isSy">false</c:set>
	<c:if test='${fn:startsWith(authTypeCd, "BLSY_")}'>
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
		
		<c:if test="${(empty templateBean.isAuto) || (templateBean.isAuto eq 'false')}">
		<input type="hidden" name="templateBean.isAuto" value="true" />
		</c:if>
		<%--删除商业集团公司发起 --%>
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
						</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<tr>
				<td class="td_title">项目名称</td>
				<td colspan="2">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						<s:if test="authTypeCd == 'ZCGL_HTQS_50'">
						   <input type="hidden" id="codeType" value="1"/>
						</s:if>
						<s:elseif test="authTypeCd == 'BLSY_ZCGL_DB_10'||authTypeCd=='SYGS_ZCGL_DB_10'">
						  <input type="hidden" id="codeType" value="2"/>
						</s:elseif>
						<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" class="inputBorderNoTip" <s:if test="#isMy==1"> style="cursor: pointer;" </s:if > resname="projectName" resContLedField="projectName"/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  resname="projectCd" resContLedField="projectCd"/>
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
				<td align="left">
					<s:checkbox name="templateBean.noProjectFlg" id="noProjectFlg" onclick="chooseDisplay();"></s:checkbox>无项目
				</td>
			</tr>
			<tr>
				<td class="td_title">设计阶段</td>
				<td class="chkGroup" align="left"  validate="required" colspan="3" id="designStage">
					<s:checkbox name="templateBean.designStage1"  cssClass="group"></s:checkbox>概念
					<s:checkbox name="templateBean.designStage2"  cssClass="group"></s:checkbox>方案
					<s:checkbox name="templateBean.designStage3"  cssClass="group"></s:checkbox>扩初
					<s:checkbox name="templateBean.designStage4"  cssClass="group"></s:checkbox>施工图
				</td>
			</tr>
			<s:if test="authTypeCd=='BLSY_ZCGL_DB_10' || authTypeCd=='SYGS_ZCGL_DB_10'">
			<tr>
				<td class="td_title require"></td>
				<td colspan="3" class="chkGroup" align="center"  validate="required">
					<table class="tb_checkbox">
					<col width="150">
					<col width="150">
					<tr>
					<td><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外</td>
					<td><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内</td>
					</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<tr>
				<td class="td_title" rowspan="3" valign="middle" align="center">设  计<br/>主  要<br/>内  容</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="70"/>
						<tr>
						<td>设计范围:</td>
						<td>
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.designRange">${templateBean.designRange}</textarea>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="70"/>
						<col/>
						<col width="30"/>
						<col/>
						<col width="30" align="center"/>
						<col/>
						<col width="30"/>
						<tr>
						<td>设计周期:</td>
						<td>
						<input onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" type="text" validate="required" id="fromDate" name="templateBean.fromDate" value="${templateBean.fromDate}" resContLedField="fromDate"/>
						</td>
						<td align="center">至</td>
						<td>
						<input onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" type="text" validate="required" id="toDate" name="templateBean.toDate" value="${templateBean.toDate}" resContLedField="toDate"/>
						</td>
						<td>共</td>
						<td>
						<input class="inputBorder" validate="required" type="text" id="totalDay" name="templateBean.totalDay" value="${templateBean.totalDay}" resContLedField="totalDay"/>
						</td>
						<td>天</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="70"/>
						<tr>
						<td>付款方式:</td>
						<td>
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.paymentType">${templateBean.paymentType}</textarea>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title">项目权限内</td>
				<td class="chkGroup" align="left" >
					<s:checkbox name="templateBean.isProjectAuth" cssClass="group" id="isProjectAuth" onclick="chooseMonopoly()"></s:checkbox>
				</td>
				<td class="td_title">是垄断</td>
				<td class="chkGroup" align="left" >
					<s:checkbox name="templateBean.isMonopoly" cssClass="group" id="isMonopoly" onclick="chooseMonopoly()"></s:checkbox>
				</td>
			</tr>
			<tr style="font-weight: bold">
			    <td class="td_title">中标单位</td>
				<td align="left">
				<%-- 导入合同台账字段 resContLedField（合同台账字段参数标记. supBasicId:为参数名）  --%>
				<input type="hidden" id="sid" name="templateBean.supBasicId" value="${templateBean.supBasicId}" resContLedField="supBasicId">
					<s:if test="!templateBean.supBasicId.isEmpty() && #canEdit == 'false'">
						<span class="link" onclick="getSup('${templateBean.supBasicId}');">${templateBean.bidUnit}</span>
						<input id="bidUnit" type="hidden" name="templateBean.bidUnit" value="${templateBean.bidUnit}"  resContLedField="bidUnit"/>
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" name="templateBean.bidUnit" id="bidUnit" value="${templateBean.bidUnit}" resContLedField="bidUnit" />
					</s:else>
				</td>
				<td class="td_title">备注</td>
				<td>
					<input class="inputBorder" type="text" name="templateBean.remark" id="remark" value="${templateBean.remark}" />
				</td>
				
			</tr>
			<tr>
				<td class="td_title">推荐中标理由</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.successfulBidReason">${templateBean.successfulBidReason}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">暂定面积（平米）</td>
				<td>
				<input id="dArearea" class="inputBorder" validate="required" type="text" name="templateBean.tentativeArea" value="${templateBean.tentativeArea}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">
				<c:choose>
					<c:when test="${isSy}">
					立项审批费用(元)
					</c:when>
					<c:otherwise>
					目标成本(元)
					</c:otherwise>
				</c:choose>
				</td>
				<td>
				<input id="targetCost" class="inputBorder" validate="required" type="text" name="templateBean.targetCost" value="${templateBean.targetCost}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">中标价(元)</td>
				<td>
				<input id="bidPrice" class="inputBorder" validate="required" type="text" name="templateBean.successfulBidPrice" value="${templateBean.successfulBidPrice}" onblur="formatVal($(this));"/>
				</td>
				<td class="td_title">单方造价（元/平米）</td>
				<td>
				<input id="sidePrice" class="inputBorder" validate="required" type="text" name="templateBean.unilateralCost" value="${templateBean.unilateralCost}" onblur="formatVal($(this));"/>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">定标资料附件<br/>(请作为附件上传)</td>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.bidPriceListId}"  resattachname="中标单位报价清单">1、中标单位报价清单</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('中标单位报价清单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidPriceListId',event);"/>
				<input type="hidden" name="templateBean.bidPriceListId" id="bidPriceListId" value="${templateBean.bidPriceListId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_bidPriceListId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidPriceListId}','bidPriceListId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
			<%--resattachname ：网批附件  用于导入合同文本库时抓取附件 --%>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.designTaskBookId}" resattachname="设计任务书">2、设计任务书</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('设计任务书','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','designTaskBookId',event);"/>
				<input type="hidden" name="templateBean.designTaskBookId" id="designTaskBookId" value="${templateBean.designTaskBookId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_designTaskBookId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.designTaskBookId}','designTaskBookId','${canEdit}');
				</script>
				</td>
			</tr> 
			<tr>
				<td align="center" style="height:40px;" validate="required" value="${templateBean.designDepthRequirementId}" resattachname="设计深度要求">3、设计深度要求</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('设计深度要求','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','designDepthRequirementId',event);"/>
				<input type="hidden" name="templateBean.designDepthRequirementId" id="designDepthRequirementId" value="${templateBean.designDepthRequirementId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_designDepthRequirementId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.designDepthRequirementId}','designDepthRequirementId','${canEdit}');
				</script>
				</td>
			</tr>
			<s:if test="statusCd==2">
			<tr>
				<td class="td_title">审定价(元)</td>
				<td>
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required" </s:if> type="text" id="contractPrice" name="templateBean.contractPrice" value="${templateBean.contractPrice}" onblur="formatVal($(this));" resname="contractPrice" resContLedField="contractPrice"/>
				</td>
				<td class="td_title" align="right">
					<s:if test="!isImported">
				  <div id="btn_importContLib" class="btn_green"  title="把当前定标审批表导入到合同文本库" onclick="doImportContLib('${resApproveInfoId}');" style="width:65px;">新建合同</div>
			      </s:if>
			      
			   </td>
				<td>		
			        <s:if test="!isImported">
				 	请到合同文本库新建合同，系统会自动生成合同台账
				 	</s:if>
				 	 <s:else>
				 		 <span>合同台账编号：${templateBean.contractNo}</span>
				 	 </s:else>
			   
					<input class="inputBorder"  type="hidden" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true'  onblur="contNoValidate(this);"</s:if>  id="contractNo" name="templateBean.contractNo" value="${templateBean.contractNo}"/>
			  </td>			
			</tr>
			</s:if>
			
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<s:if test="templateBean.isMonopoly!='true'">
<script type="text/javascript">
$("#bidUnit").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'bidUnit',sid:'sid'});
</script>
</s:if>
<%--导入合同文本库脚本start --%>
<%@include file="bid-contract-import-base.jsp"%>
<%--导入合同文本库脚本end --%>
<s:if test="#isMy==1">
<script type="text/javascript">

$("#projectName").quickSearch("${ctx}/cont/cont-project-code!quickSearch.action",['projectName'],{projectCd:'projectCd',projectName:'projectName'},{codeType:$('#codeType').val()});
function chooseMonopoly() {
	var isProjectAuth=$("#isProjectAuth").attr("checked"); 
	var isMonopoly =$("#isMonopoly").attr("checked"); 
	if(isProjectAuth||isMonopoly){
		$("#bidUnit").unbind();
		$("#remark").attr("validate","required");
	}else{
		$("#bidUnit").val('');
		$("#bidUnit").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'bidUnit',sid:'sid'});
		$("#remark").attr("class","inputBorder");
		$("#remark").removeAttr("validate");
		$("#remark").removeAttr("title");
	}
}
function getSup(sid){
	var url='';
	if(isNotEmpty(sid)){
		url="${ctx}/sup/sup-basic!input.action?id="+sid;
	}else if(isNotEmpty(supName)){
		url="${ctx}/sup/sup-basic!input.action?sName="+supName;
	}
	if (url!=''){
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("supQuery","明细",url,true);
	}
}

chooseDisplay();
function chooseDisplay() {
	var noProjectFlg=$("#noProjectFlg").attr("checked");
	if(noProjectFlg){
		$("#designStage").removeAttr("validate").removeClass('required');
		$("#bidUnit").removeAttr("validate").removeClass('required');
		$("#dArearea").removeAttr("validate").removeClass('required');
		$("#targetCost").removeAttr("validate").removeClass('required');
		$("#bidPrice").removeAttr("validate").removeClass('required');
		$("#sidePrice").removeAttr("validate").removeClass('required');
		$("#projectCd").val('23');
		$("#projectName").val('宝龙地产');
	}else{
		$('#designStage').attr("validate", "required").addClass('required');
		$('#bidUnit').attr("validate", "required").addClass('required');
		$('#dArearea').attr("validate", "required").addClass('required');
		$('#targetCost').attr("validate", "required").addClass('required');
		$('#bidPrice').attr("validate", "required").addClass('required');
		$('#sidePrice').attr("validate", "required").addClass('required');
		$("#projectCd").val('');
		$("#projectName").val('');
	}
}
$("#noProjectFlg").bind("click",chooseDisplay);

</script>
</s:if>
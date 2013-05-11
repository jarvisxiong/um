<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 定标审批表（营销类） --%>
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
		<%--删除商业集团公司发起 --%>
		<table  class="mainTable">
			<col width="100"/>
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
			<s:if test="aboutBefore || aboutAfter">
			<tr>
				<td class="td_title"></td>
				<td align="left" colspan="3" id="beforeafter" validate="required">
					<table class="tb_checkbox" style="width:100%;">
					    <col width="100">
					    <col width="100">
						<col width="100">
						<col width="100">
						<tr>
						<td><s:checkbox name="templateBean.before"  cssClass="group"></s:checkbox>开业前</td>
						<td><s:checkbox name="templateBean.after"  cssClass="group"></s:checkbox>开业后</td>
						</tr>
					</table>
				</td>
			</tr>
			</s:if>
			<tr>
				<td class="td_title require">项目名称</td>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						 <s:if test="authTypeCd == 'ZCGL_HTQS_30'">
						   <input type="hidden" id="codeType" value="1"/>
						</s:if>
						<s:elseif test="authTypeCd == 'BLSY_ZCGL_DB_20'||authTypeCd=='BLSY_ZCGL_DB_30'||authTypeCd=='BLSY_ZCGL_DB_40'||authTypeCd=='BLSY_ZCGL_DB_50'||authTypeCd=='BLSY_ZCGL_DB_60'||authTypeCd=='BLSY_ZCGL_DB_70'||authTypeCd=='SYGS_ZCGL_DB_70'">
						  <input type="hidden" id="codeType" value="2"/>
						</s:elseif>
						<s:if test="authTypeCd == 'ZCGL_HTQS_30'||authTypeCd == 'BLSY_ZCGL_DB_20'||authTypeCd=='BLSY_ZCGL_DB_30'||authTypeCd=='BLSY_ZCGL_DB_40'||authTypeCd=='BLSY_ZCGL_DB_50'||authTypeCd=='BLSY_ZCGL_DB_60'||authTypeCd=='BLSY_ZCGL_DB_70'||authTypeCd=='SYGS_ZCGL_DB_70'">
						<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName"  <s:if test="#isMy==1"> class="inputBorderNoTip " style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else> resname="projectName" resContLedField="projectName"/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" resname="projectCd" resContLedField="projectCd" />
						</s:if>
						<s:else>
						<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else> resname="projectName" resContLedField="projectName"/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"   resname="projectCd" resContLedField="projectCd"/>
						</s:else>
						</td>
						<td style="text-align: right;">(</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.projectPeriod" value="${templateBean.projectPeriod}" />
						</td>
						<td>)期</td>
						</tr>
					</table>
				</td>
				<td class="td_title require">工程名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}"  resContLedField="engineeringName"/>
				</td>
			</tr>
			<s:if test="authTypeCd=='BLSY_ZCGL_DB_20'||authTypeCd=='BLSY_ZCGL_DB_30'
			||authTypeCd=='BLSY_ZCGL_DB_40'||authTypeCd=='BLSY_ZCGL_DB_70'||authTypeCd=='SYGS_ZCGL_DB_70'
			||authTypeCd=='ZCGL_HTQS_53'||authTypeCd=='ZCGL_HTQS_55'">
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
				<td class="td_title" rowspan="5" valign="middle" align="center">招  标<br/>主  要<br/>内  容</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="70"/>
						<tr>
						<td class="require">制作/发布内容:</td>
						<td>
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.bidRange">${templateBean.bidRange}</textarea>
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
						<td class="require">服务周期:</td>
						<td>
						<input onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" type="text" validate="required" id="fromDate" name="templateBean.fromDate" value="${templateBean.fromDate}" resContLedField="fromDate"/>
						</td>
						<td align="center" class="require">至</td>
						<td>
						<input onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" type="text" validate="required" id="toDate" name="templateBean.toDate" value="${templateBean.toDate}" resContLedField="toDate"/>
						</td>
						<td class="require">共</td>
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
						<td class="require">质量要求:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.qualityRequirement" value="${templateBean.qualityRequirement}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="70"/>
						<tr>
						<td class="require">计价模式:</td>
						<td colspan="3" class="chkGroup" align="center" validate="required">
						<table class="tb_checkbox">
						<col>
						<tr>
						<td><s:checkbox name="templateBean.pricingModel1"  cssClass="group" resContLedField="pricingModel1"></s:checkbox>总价包干</td>
						</tr>
						<tr>
						<td><s:checkbox name="templateBean.pricingModel2"  cssClass="group" resContLedField="pricingModel2"></s:checkbox>单价包干</td>
						</tr>
						</table>
						</td>
						<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="70"/>
						<tr>
						<td class="require">付款方式:</td>
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
					<s:checkbox name="templateBean.isMonopoly" id="isMonopoly" cssClass="group" onclick="chooseMonopoly()"></s:checkbox>
				</td>
			</tr>
			<tr style="font-weight: bold">
			    <td class="td_title require">中标单位</td>
				<td align="left">
				  <input type="hidden" id="sid" name="templateBean.supBasicId" value="${templateBean.supBasicId}" resContLedField="supBasicId">
					<s:if test="!templateBean.supBasicId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getSup('${templateBean.supBasicId}');">${templateBean.bidUnit}</span>
						<input id="bidUnit" type="hidden" name="templateBean.bidUnit" value="${templateBean.bidUnit}"  resContLedField="bidUnit"/>
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" name="templateBean.bidUnit" id="bidUnit" value="${templateBean.bidUnit}"  resContLedField="bidUnit"/>
					</s:else>
				</td>
				<td class="td_title">备注</td>
				<td>
					<input class="inputBorder" type="text" name="templateBean.remark" id="remark" value="${templateBean.remark}" />
				</td>
				
			</tr>
			<tr>
				<td class="td_title require">推荐中标理由</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.successfulBidReason">${templateBean.successfulBidReason}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">
				<c:choose>
					<c:when test="${isSy}">
					预算费用(元)
					</c:when>
					<c:otherwise>
					目标成本(元)
					</c:otherwise>
				</c:choose>
				</td>
				<td>
				<input class="inputBorder"  type="text" name="templateBean.targetCost" value="${templateBean.targetCost}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title require">中标价(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.successfulBidPrice" value="${templateBean.successfulBidPrice}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3">定标资料附件<br/>(请作为附件上传)</td>
				<td class="require" align="center" style="height:40px;" validate="required" value="${templateBean.bidPriceListId}" resattachname="中标单位报价清单">1、中标单位报价清单</td>
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
				<td class="require" align="center" style="height:40px;" validate="required" value="${templateBean.bidSummaryId}" resattachname="方案及施工图">2、方案及施工图</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('方案及施工图','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidSummaryId',event);"/>
				<input type="hidden" name="templateBean.bidSummaryId" id="bidSummaryId" value="${templateBean.bidSummaryId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_bidSummaryId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidSummaryId}','bidSummaryId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td class="require" align="center" style="height:40px;" validate="required" value="${templateBean.bidAnswerAndBidQueryId}" resattachname="预算审批表">3、预算审批表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('预算审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidAnswerAndBidQueryId',event);"/>
				<input type="hidden" name="templateBean.bidAnswerAndBidQueryId" id="bidAnswerAndBidQueryId" value="${templateBean.bidAnswerAndBidQueryId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_bidAnswerAndBidQueryId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidAnswerAndBidQueryId}','bidAnswerAndBidQueryId','${canEdit}');
				</script>
				</td>
			</tr>
			<s:if test="statusCd==2">
			<tr>
				<td class="td_title">审定价(元)</td>
				<td>
				<input class="inputBorder" <s:if test="statusCd==2 && userCd==#curUserCd && !isImported"> edit='true' validate="required" </s:if> type="text" id="contractPrice" name="templateBean.contractPrice" value="${templateBean.contractPrice}" onblur="formatVal($(this));"  resname="contractPrice" resContLedField="contractPrice"/>
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
<%--导入合同文本库脚本 --%>
<%@include file="bid-contract-import-base.jsp"%>
<s:if test="templateBean.isMonopoly!='true'">
<script type="text/javascript">
$("#bidUnit").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'bidUnit',sid:'sid'});
</script>
</s:if>
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
//合同编号唯一性校验
function contNoValidate(dom){
	if($(dom).val()){
		$.post(_ctx + "/cont/cont-ledger!contNoValidate.action?contNo=" + $(dom).val(),
			function(result){
				//合同新增，不是0条记录
				if(result!="0"){
					alert("该编号已存在，请重新输入");
					$(dom).val("");
				}
	     });
	}
}
//重写去掉开业前开业后判断
function doImport(){
	var pdv = new Validate("templetForm");
	$("#beforeafter").removeAttr("validate");
	if (pdv.validate()) {
		TB_showMaskLayer('正在执行...',5000);
		$("#templetForm").attr("action",_ctx+"/res/res-approve-info!doImport.action");
		$("#templetForm").ajaxSubmit(function(result) {
			if (result=='success'){
				 $("#btnImport2Cont").hide();//按钮隐藏
				 ymPrompt.succeedInfo({message:"成功执行",title:"提示",width:200,height:180});
			 }
			 TB_removeMaskLayer();
		});
	}
}
</script>

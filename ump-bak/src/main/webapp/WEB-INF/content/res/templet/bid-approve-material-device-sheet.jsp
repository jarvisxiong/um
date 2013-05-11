<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 定标审批表（材料及设备类） --%>
<%@ include file="template-header.jsp"%>

<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
<c:set var="isJD">false</c:set>
<c:if test='${fn:startsWith(authTypeCd, "JD_")}'>
<c:set var="isJD">true</c:set>
</c:if>
<div align="center" class="billContent">

	<form action="res-approve-info!save.action" method="post" id="templetForm" class="contractPaymentBill">
		<%@ include file="template-var.jsp"%>
		<c:if test="${(empty templateBean.isAuto) || (templateBean.isAuto eq 'false')}">
		<input type="hidden" name="templateBean.isAuto" value="true" />
		</c:if>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<col width="110"/>
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
				<td>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col/>
						<col width="20"/>
						<col width="40"/>
						<col width="20"/>
						<tr>
						<td>
						<s:if test="authTypeCd == 'ZCGL_HTQS_40'">
						<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName"  <s:if test="#isMy==1"> class="inputBorderNoTip" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else> resname="projectName" resContLedField="projectName"/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" resname="projectCd" resContLedField="projectCd" />
						</s:if>
						<s:else>
						<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else> resname="projectName" resContLedField="projectName"/>
						<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}" resname="projectCd" resContLedField="projectCd" />
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
				<td class="td_title">工程名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" resContLedField="engineeringName"/>
				</td>
			</tr>
			
			<s:if test="authTypeCd=='BLSY_ZCGL_DB_10'||authTypeCd=='JD_ZCGL_DB_10'||authTypeCd=='JD_ZCGL_DB_20'||authTypeCd=='JD_ZCGL_DB_30'||authTypeCd=='JD_ZCGL_DB_30'||authTypeCd=='JD_ZCGL_DB_40'||authTypeCd=='SYGS_ZCGL_DB_40'">
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
				<td class="td_title" rowspan="6" valign="middle" align="center">招  标<br/>主  要<br/>内  容</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="70"/>
						<tr>
						<td>招标范围:</td>
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
						<tr>
						<td>数&nbsp;&nbsp;&nbsp;&nbsp;量:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.quatity" value="${templateBean.quatity}" />
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
						<td>供货周期:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.supplyPeriod" value="${templateBean.supplyPeriod}" />
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
						<td>技术要求:</td>
						<td>
						<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.teckRequirement">${templateBean.teckRequirement}</textarea>
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
						<td>计价模式:</td>
						<td class="chkGroup" align="left" validate="required">
						<s:checkbox name="templateBean.pricingModel1"  cssClass="group" resContLedField="pricingModel1"></s:checkbox>总价包干
						<s:checkbox name="templateBean.pricingModel2"  cssClass="group" resContLedField="pricingModel2"></s:checkbox>单价包干
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
				</td>
				<td class="td_title">是垄断</td>
				<td class="chkGroup" align="left" >
					<s:checkbox id="isMonopoly" name="templateBean.isMonopoly" cssClass="group" onclick="chooseMonopoly()"></s:checkbox>
				</td>
			</tr>
			<tr style="font-weight: bold">
			    <td class="td_title">中标单位</td>
				<td align="left">
				<input type="hidden" id="sid" name="templateBean.supBasicId" value="${templateBean.supBasicId}">
				
					<s:if test="!templateBean.supBasicId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getSup('${templateBean.supBasicId}');">${templateBean.bidUnit}</span>
						<input id="bidUnit" type="hidden" name="templateBean.bidUnit" value="${templateBean.bidUnit}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" name="templateBean.bidUnit" id="bidUnit" value="${templateBean.bidUnit}" />
					</s:else>
				</td>
				<td class="td_title">备注</td>
				<td>
					<input class="inputBorder" type="text" name="templateBean.remark" id="remark" value="${templateBean.remark}" />
				</td>
			</tr>
			
			<tr>
				<td class="td_title">合同签订</td>
				<td class="chkGroup"  validate="required">
					<%--
					<s list="#{1:'二方合同',2:'三方合同',3:'带贸易公司合同'}"  listKey="templateBean.contTypeCd"  headerKey="" headerValue=""/> 
					 --%>
					
					<table class="tb_checkbox" style="width:100%;">
					    <col width="90">
					    <col width="90">
						<col width="120">
						<tr>
							<td><s:checkbox name="templateBean.contTypeCd1" id="contTypeCd1"  cssClass="group" onclick="processContType('contTypeCd1')" resContLedField="contTypeCd1"></s:checkbox>二方合同</td>
							<td><s:checkbox name="templateBean.contTypeCd2" id="contTypeCd2"  cssClass="group" onclick="processContType('contTypeCd2')" resContLedField="contTypeCd2"></s:checkbox>三方合同</td>
							<td><s:checkbox name="templateBean.contTypeCd3" id="contTypeCd3"  cssClass="group" onclick="processContType('contTypeCd3')" resContLedField="contTypeCd3"></s:checkbox>带贸易公司合同</td>
						</tr>
					</table>
					
					<%--
					<select name="templateBean.contTypeCd">
						<option value=""></option>
						<option value="1">二方合同</option>
						<option value="2">三方合同</option>
						<option value="3">带贸易公司合同</option>
					</select>
					 --%>
				</td>
				<td></td>
			</tr>
			<tr>
				<td class="td_title">甲方</td>
				<td><input validate="required" class="inputBorder" type="text" name="templateBean.parta" id="parta" value="${templateBean.parta}" resContLedField="parta"/></td>
				<td class="td_title">乙方</td>
				<td><input validate="required" class="inputBorder" type="text" name="templateBean.partb" id="partb" value="${templateBean.partb}" resContLedField="partb"/></td>
			</tr>
			<tr title="适用三方合同" id="trRowc" style="display:none;">
				<td class="td_title">丙方</td>
				<td><input class="inputBorder" type="text" name="templateBean.partC" id="partC" value="${templateBean.partC}" resContLedField="partC" /></td>
				<td class="td_title">领料施工单位</td>
				<td><input class="inputBorder" type="text" name="templateBean.realUseName" id="realUseName" value="${templateBean.realUseName}" /></td>
			</tr>
			<tr title="带贸易公司合同" id="trRowd" style="display:none;">
				<td class="td_title">实际供方</td>
				<td><input class="inputBorder" type="text" name="templateBean.realProvName" id="realProvName" value="${templateBean.realProvName}" resContLedField="realProvName"/></td>
			</tr>
			
			
			
			<tr>
				<td class="td_title">推荐中标理由</td>
				<td colspan="3">
				<textarea class="inputBorder contentTextArea" validate="required" name="templateBean.successfulBidReason">${templateBean.successfulBidReason}</textarea>
				</td>
			</tr>
			<tr>
				<td class="td_title">预算金额(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.budgetPrice" value="${templateBean.budgetPrice}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">目标成本(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.targetCost" value="${templateBean.targetCost}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td class="td_title">中标价(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.successfulBidPrice" value="${templateBean.successfulBidPrice}" onblur="formatVal($(this));" />
				</td>
				<td class="td_title">单价(元)</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.unitPrice" value="${templateBean.unitPrice}" onblur="formatVal($(this));" />
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="6">定标资料附件<br/>(请作为附件上传)</td>
				<td align="left" style="height:40px;" validate="required" value="${templateBean.bidQualificationApproveId}" resattachname="邀标单位资质审批表">1、邀标单位资质审批表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('邀标单位资质审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidQualificationApproveId',event);"/>
				<input type="hidden" name="templateBean.bidQualificationApproveId" id="bidQualificationApproveId" value="${templateBean.bidQualificationApproveId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_bidQualificationApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidQualificationApproveId}','bidQualificationApproveId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="left" style="height:40px;" validate="required" value="${templateBean.bidPriceListId}" resattachname="中标单位报价清单">2、中标单位报价清单</td>
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
				<td align="left" style="height:40px;" validate="required" value="${templateBean.bidSummaryId}" resattachname="投标报价汇总表">3、投标报价汇总表</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('投标报价汇总表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidSummaryId',event);"/>
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
				<td align="left" style="height:40px;" <c:if test="${isJD=='false'}">validate="required"</c:if> value="${templateBean.bidAnswerAndBidQueryId}" resattachname="招标答疑及询标问卷">4、招标答疑及询标问卷</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('招标答疑及询标问卷','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidAnswerAndBidQueryId',event);"/>
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
			<tr>
				
				<td align="left" style="height:40px;" <c:if test="${isJD=='false'}">validate="required"</c:if> value="${templateBean.techBidEvaluateId}" resattachname="技术标评结果">5、技术标评结果</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('技术标评结果','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','techBidEvaluateId',event);"/>
				<input type="hidden" name="templateBean.techBidEvaluateId" id="techBidEvaluateId" value="${templateBean.techBidEvaluateId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_techBidEvaluateId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.techBidEvaluateId}','techBidEvaluateId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr>
				<td align="left" style="height:40px;" value="${templateBean.bidPictrueId}" resattachname="招标图纸/技术要求">6、招标图纸/技术要求</td>
				<td align="center">
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('招标图纸/技术要求','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidPictrueId',event);"/>
				<input type="hidden" name="templateBean.bidPictrueId" id="bidPictrueId" value="${templateBean.bidPictrueId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_bidPictrueId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidPictrueId}','bidPictrueId','${canEdit}');
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
		<!-- 战略类型，0：非战略，1：战略 -->
		<input type="hidden" value="0" resContLedField="strageFlg"/>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<%--导入合同文本库脚本start --%>
<%@include file="bid-contract-import-base.jsp"%>
<%--导入合同文本库脚本end --%>

<s:if test="templateBean.isMonopoly!='true'">
<script type="text/javascript">
$("#bidUnit").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'bidUnit',sid:'sid'});
</script>
</s:if>
<script type="text/javascript">
$("#projectName").quickSearch("${ctx}/cont/cont-project-code!quickSearch.action",['projectName'],{projectCd:'projectCd',projectName:'projectName'});
function chooseMoney(dom) {
	if(dom.name == 'templateBean.below10' && dom.checked) {
		$("#bidUnit").unbind();
	} else {
		$("#bidUnit").val('');
		$("#bidUnit").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'bidUnit',sid:'sid'});
	}
}
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

var canEdit="${canEdit}";
//如果是不可编辑 ，则需加载合同类型对应的选项
var iscanDisplay=false;
	var domId="contTypeCd";
	for(var i=1;i<=3;i++){
		var tt = $('#'+domId+i).attr('checked');
		if(tt){
			iscanDisplay=true;
			processContType(domId+i);
			iscanDisplay=false;
			}		 
		}	

//选择合同类型
function processContType(domId){ 
	
	if(canEdit=="false" && !iscanDisplay){	
		//如果不可编辑则直接返回
		return;
	}
	var tt = $('#'+domId).attr('checked');
	switch(domId){
	case "contTypeCd1":
		$('#trRowc').hide();
		$('#trRowd').hide();
		$('#trRowc').find("input").val("");
		$('#trRowd').find("input").val("");
		break;
	case "contTypeCd2":
       if(tt){
    	   $('#trRowc').show();
   		   $('#trRowd').hide();	
		   $('#trRowd').find("input").val("");
           }else{
       	     $('#trRowc').hide();
       		 $('#trRowd').hide();	
       		$('#trRowc').find("input").val("");
    		$('#trRowd').find("input").val("");
               }
		break;
	case "contTypeCd3":

		 if(tt){
			    $('#trRowc').hide();
				$('#trRowd').show();	

				$('#trRowc').find("input").val("");
	           }else{
	       	     $('#trRowc').hide();
	       		 $('#trRowd').hide();
	       		$('#trRowc').find("input").val("");
	    		$('#trRowd').find("input").val("");
	               }
		break;
     default:
			$('#trRowc').hide();
		    $('#trRowd').hide();
			break;

	}
		

	
}
</script>

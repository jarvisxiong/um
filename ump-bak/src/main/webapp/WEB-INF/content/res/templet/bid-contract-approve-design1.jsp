<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 招标文件/合同评审表（商业合同文件设计） --%>
<%@ include file="template-header.jsp"%>

<script type="text/javascript">
var mapPrama={
		contLedgerId:'contLedgerId',
		contNo:'contNo',
		projectCd:'projectCd',
		projectName:'projectName',
		contName:'engineeringName',
		planBeginDate:'fromDate',
		planEndDate:'toDate',
		period:'totalDay',
		totalPrice:'budgetAmount',
		payWay:'paymentType',
		pricingModel:'pricingModel'
	};
//合同台账关联参数 start

var autoRelationCtLedger=true;
var idctLedgerArray=mapPrama;
var ctLedgerParams={isAudit:'noAudit',codeType:'2'};

//合同台账关联参数end

$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,{isAudit:'noAudit',codeType:'2'},showModel);
function showModel(){
	//自动关联合同文本库，如果不存在则保留原始数据
     autoQuickSearch($("#contNo").val());
	var model =$("#pricingModel").val();
	if(model==0||model==1){
		$("#templateBean_pricingModel1").attr("checked",true);
	}else if(model==2){
		$("#templateBean_pricingModel2").attr("checked",true);
	}else if(model==3){
		$("#templateBean_pricingModel3").attr("checked",true);
	}
	
}
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
	
	function onclick_cooperationWays(obj, i) {
		if(obj.checked) {
			$("#textField"+i).attr("validate","required");
			for(var j=1; j<5; j++) {
				if(j!=i) {
					$("#textField"+j).attr("class","inputBorder");
					$("#textField"+j).removeAttr("validate");
					$("#textField"+j).removeAttr("title");
				}
			}
		} else {
			$("#textField"+i).attr("class","inputBorder");
			$("#textField"+i).removeAttr("validate");
			$("#textField"+i).removeAttr("title");
		}
	}
	
	function onclick_approvecontent(item) {
		
		if(item == 'bid') {
			$("#tr_cooperationWays").hide();
			$("#tr_attachment").nextAll().hide();
			$("#td_attachment").attr("rowspan").val(1);
		} else if(item == 'contract') {
			$("#tr_cooperationWays").show();
			$("#tr_attachment").nextAll().show();
		}
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
		<%--不自动设置项目编号 --%>
	     <input type="hidden" id="isAutosetProject" value="0"/>
	     <%--是否判断合库文件编号和合同台账编号一致 --%>
	     <input type="hidden" id="isJudageCtLedgerCons" value="1"/>
		 <%--合同库引用 start --%>
		 <%@ include file="bid-contract-base.jsp"%>
		<s:if test="statusCd!=0">
		<%@ include file="bid-contract-mark.jsp" %>
		</s:if>
		
		<%--合同库引用 end --%>
		<%--删除商业集团公司发起 --%>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<col width="120"/>
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
			
			<s:if test="authTypeCd=='JD_ZCGL_HTQS_20'||authTypeCd=='JD_ZCGL_HTQS_40'||authTypeCd=='JD_ZCGL_HTQS_50'||
			authTypeCd=='JD_ZCGL_HTQS_60'||authTypeCd=='JD_ZCGL_HTQS_70'||authTypeCd=='JD_ZCGL_HTQS_80'
			 ||authTypeCd=='SYGS_ZCGL_HTQS_35' ||authTypeCd=='BLSY_ZCGL_HTQS_35'">
			<tr>
				<td class="td_title require"></td>
				<td colspan="3" class="chkGroup" align="left"  validate="required">
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
			   <td class="td_title">合同台账编号</td>
			   <td colspan="3" >
			    <input type="hidden" id="contLedgerId"  name="templateBean.contLedgerId" value="${templateBean.contLedgerId}" />
			    <input type="text" id="contNo" name="templateBean.contNo" value="${templateBean.contNo}" validate="required" class="inputBorder"/>
			   </td>
			  </tr>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
				<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">工程名称</td>
				<td>
				<input class="inputBorder" validate="required" id="engineeringName" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">项目权限内</td>
				<td class="chkGroup" align="left" >
					<s:checkbox id="isProjectAuth" name="templateBean.isProjectAuth" cssClass="group" onclick="chooseMonopoly()"></s:checkbox>
				</td>
				<td class="td_title">是垄断</td>
				<td class="chkGroup" align="left" >
					<s:checkbox id="isMonopoly" name="templateBean.isMonopoly" cssClass="group" onclick="chooseMonopoly()"></s:checkbox>
				</td>
			</tr>
			<tr>
				<td class="td_title">评审内容</td>
				<td colspan="1" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.approveContent2" cssClass="group" ></s:checkbox>合同文件
					<!--<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="170"/>
						<col/>
						<col width="50"/>
						<tr>
						<td><s:checkbox name="templateBean.approveContent1" cssClass="group" onclick="onclick_approvecontent('bid');"></s:checkbox>招标文件(招标文件编号：</td>
						<td><input class="inputBorder" validate="required" type="text" name="templateBean.oriBidFileCd" value="${templateBean.oriBidFileCd}" /></td>
						<td>)</td>
						</tr>
						<tr>
						<td><s:checkbox name="templateBean.approveContent2" cssClass="group" onclick="onclick_approvecontent('contract');"></s:checkbox>合同文件</td>
						<td></td>
						<td></td>
						</tr>
					</table>-->
				</td>
				<td class="td_title">单位名称</td>
				<td align="left">
				<input type="hidden" id="sid" name="templateBean.supBasicId" value="${templateBean.supBasicId}">
				
					<s:if test="!templateBean.supBasicId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getSup('${templateBean.supBasicId}');">${templateBean.unitName}</span>
						<input id="unitName" type="hidden" name="templateBean.unitName" value="${templateBean.unitName}" />
					</s:if>
					<s:else>
					<input class="inputBorder" validate="required" type="text" name="templateBean.unitName" id="unitName" value="${templateBean.unitName}" />
					</s:else>
				</td>
			</tr>
			
			<tr style="font-weight: bold">
				<td class="td_title">备注</td>
				<td colspan="3">
					<input class="inputBorder" type="text" name="templateBean.remark" id="remark" value="${templateBean.remark}" />
				</td>
			</tr>
			<tr id="tr_cooperationWays">
				<td class="td_title" rowspan="1" valign="middle" align="center">合  作<br/>方  式</td>
				<td colspan="3" class="chkGroup" align="center" validate="required">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="170"/>
						<col/>
						<col width="50"/>
						<tr>
						<td><s:checkbox name="templateBean.cooperationWays1"  cssClass="group" onclick="onclick_cooperationWays(this,1);"></s:checkbox>招标(招标文件编号：</td>
						<td><input class="inputBorder" type="text" id="textField1" name="templateBean.oriBidFileCd" value="${templateBean.oriBidFileCd}" /></td>
						<td>)</td>
						</tr>
						<tr>
						<td><s:checkbox name="templateBean.cooperationWays2"  cssClass="group" onclick="onclick_cooperationWays(this,2);"></s:checkbox>续标(原合同文件编号：</td>
						<td><input class="inputBorder" type="text" id="textField2" name="templateBean.oriContractFileCd" value="${templateBean.oriContractFileCd}" /></td>
						<td>)</td>
						</tr>
						<tr>
						<td><s:checkbox name="templateBean.cooperationWays3"  cssClass="group" onclick="onclick_cooperationWays(this,3);"></s:checkbox>直接委托(理由：</td>
						<td><input class="inputBorder" type="text" id="textField3" name="templateBean.delegateReason" value="${templateBean.delegateReason}" /></td>
						<td>)</td>
						</tr>
						<tr>
						<td><s:checkbox name="templateBean.cooperationWays4"  cssClass="group" onclick="onclick_cooperationWays(this,4);"></s:checkbox>竞争性谈判(理由：</td>
						<td><input class="inputBorder" type="text" id="textField4" name="templateBean.negotiationReason" value="${templateBean.negotiationReason}" /></td>
						<td>)</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="6" valign="middle" align="center">招  标<br/>主  要<br/>内  容</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>设计范围:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.bidRange" value="${templateBean.bidRange}" />
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
						<td>设计工期:</td>
						<td>
						<input onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" type="text" validate="required" id="fromDate" name="templateBean.fromDate" value="${templateBean.fromDate}"/>
						</td>
						<td align="center">至</td>
						<td>
						<input onfocus="WdatePicker()" onchange="onchange_DesignTotalDay()" class="Wdate inputBorder" type="text" validate="required" id="toDate" name="templateBean.toDate" value="${templateBean.toDate}"/>
						</td>
						<td>共</td>
						<td>
						<input class="inputBorder" validate="required" type="text" id="totalDay" name="templateBean.totalDay" value="${templateBean.totalDay}"/>
						</td>
						<td>天</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>质量要求:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.qualityRequirement" value="${templateBean.qualityRequirement}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
				   <input type="hidden" name="templateBean.pricingModel" id="pricingModel" value="${templateBean.pricingModel}"/>
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>计价模式:</td>
						<td colspan="3" class="chkGroup" align="center" validate="required">
						<table class="tb_checkbox">
						<col>
						<tr>
						<td><s:checkbox name="templateBean.pricingModel1"  cssClass="group"></s:checkbox>总价包干</td>
						</tr>
						<tr>
						<td><s:checkbox name="templateBean.pricingModel2"  cssClass="group"></s:checkbox>单价包干</td>
						</tr>
						</table>
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>预算金额(元):</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.budgetAmount" value="${templateBean.budgetAmount}" onblur="formatVal($(this));" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>付款方式:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.paymentType" value="${templateBean.paymentType}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="tr_attachment" otype="attach">
				<td id="td_attachment" class="td_title" rowspan="3">评审附件(请作为附件上传)</td>
				<td style="height:40px;" value="${templateBean.bidContractFileId}"  resattachname="招标合同文件">招标合同文件</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('招标合同文件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidContractFileId',event);"/>
				<input type="hidden" name="templateBean.bidContractFileId" id="bidContractFileId" value="${templateBean.bidContractFileId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_bidContractFileId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidContractFileId}','bidContractFileId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr otype="attach">
				<td style="height:40px;" validate="required" value="${templateBean.bidApproveBillId}" resattachname="定标审批表">定标审批表</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('定标审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidApproveBillId',event);"/>
				<input type="hidden" name="templateBean.bidApproveBillId" id="bidApproveBillId" value="${templateBean.bidApproveBillId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_bidApproveBillId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.bidApproveBillId}','bidApproveBillId','${canEdit}');
				</script>
				</td>
			</tr>
			<tr otype="attach">
				<td style="height:40px;" value="${templateBean.contractItemApproveId}" resattachname="合同条款审批表">合同条款审批表</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('合同条款审批表','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','contractItemApproveId',event);"/>
				<input type="hidden" name="templateBean.contractItemApproveId" id="contractItemApproveId" value="${templateBean.contractItemApproveId}"/>
				</s:if>
				</td>
				<td>
				<div id="show_contractItemApproveId"></div>
				<script type="text/javascript">
				showUploadedFile('${templateBean.contractItemApproveId}','contractItemApproveId','${canEdit}');
				</script>
				</td>
			</tr>
		</table>
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<s:if test="templateBean.isMonopoly!='true'">
<script type="text/javascript">
$("#unitName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'unitName',sid:'sid'},'','','');
</script>
</s:if>
<script type="text/javascript">

function chooseMonopoly() {
	var isProjectAuth=$("#isProjectAuth").attr("checked"); 
	var isMonopoly =$("#isMonopoly").attr("checked"); 
	if(isProjectAuth||isMonopoly){
		$("#unitName").unbind();
		$("#remark").attr("validate","required");
	}else{
		$("#unitName").val('');
		$("#unitName").quickSearch("${ctx}/sup/sup-basic!quickSearch.action",['sname'],{sname:'unitName',sid:'sid'},'','','');
		$("#remark").attr("class","inputBorder");
		$("#remark").removeAttr("validate");
		$("#remark").removeAttr("title");
	}
}

function getSup(sid){
	var url='';
	if(isNotEmpty(sid)){
		url="${ctx}/sup/sup-basic!input.action?id="+sid;
	}
	if (url!=''){
		if(parent.TabUtils==null)
			window.open(url);
		else
		  parent.TabUtils.newTab("supQuery","明细",url,true);
	}
}
//是否回执标准库选择回调函数
var isCallChooseCont=true;
//是否有选择合同库
if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
	userCont('true');
}else{
	userCont('false');
}
</script>

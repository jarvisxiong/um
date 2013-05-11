<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 合同评审表（地产战略） --%>
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
	     <input type="hidden" id="isJudageCtLedgerCons" value="0"/>
		 <%--合同文本库引用 start --%>
		 <%@ include file="bid-contract-base.jsp"%>
		<s:if test="statusCd!=0">
		<%@ include file="bid-contract-mark.jsp" %>
		</s:if>
		<table  class="mainTable">
			<col width="100"/>
			<col/>
			<col width="120"/>
			<col/>
			<tr>
				<td class="td_title">合同编号</td>
				<td align="left">
				 <input type="hidden" id="contLedgerId"  name="templateBean.contLedgerId" value="${templateBean.contLedgerId}" />
		    	 
		    	 
		    	 <s:if test="!templateBean.contLedgerId.isEmpty() && #canEdit == 'false'">
						<span  class="link" onclick="getCont('${templateBean.contLedgerId}');">${templateBean.contNo}</span>
						<input id="contNo" type="hidden" name="templateBean.contNo" value="${templateBean.contNo}" />
					</s:if>
					<s:else>
					<input type="text" id="contNo" name="templateBean.contNo" value="${templateBean.contNo}" validate="required" class="inputBorder"/>
					</s:else>
				</td>
				<td class="td_title">合同名称</td>
				<td>
				<input class="inputBorder" id="engineeringName"  type="text" validate="required" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="3" valign="middle" align="center">合  同<br/>双  方</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>甲方:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.approveContent1" value="${templateBean.approveContent1}" />
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
						<td>乙方:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.approveContent2" value="${templateBean.approveContent2}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- Start Add for part C by zhuxj on 2012.3.31 -->
			<tr>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>丙方:</td>
						<td>
						<input class="inputBorder" type="text" name="templateBean.approveContent3" value="${templateBean.approveContent3}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- End   Add for part C by zhuxj on 2012.3.31 -->
			<tr>
			 <td class="td_title">备注</td>
				<td colspan="3">
					<input class="inputBorder" type="text" name="templateBean.remark" id="remark" value="${templateBean.remark}" />
				</td>
			</tr>
			<tr id="tr_cooperationWays">
				<td class="td_title"  valign="middle" align="center">合  作<br/>方  式</td>
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
				<td class="td_title" rowspan="3" valign="middle" align="center">招  标<br/>主  要<br/>内  容</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>招标范围:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.bidRange" value="${templateBean.bidRange}" />
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
						<td>付款方式:</td>
						<td>
						<input class="inputBorder" id="paymentType" validate="required" type="text" name="templateBean.paymentType" value="${templateBean.paymentType}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="tr_attachment" otype="attach">
				<td id="td_attachment" class="td_title" rowspan="2">评审附件(请作为附件上传)</td>
				<td style="height:40px;" value="${templateBean.bidContractFileId}" resattachname="招标合同文件">招标合同文件</td>
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
function getCont(id){
	var url="${ctx}/cont/cont-ledger!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
	  parent.TabUtils.newTab("cont-ledger-input","合同台账查看",url,true);
}
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
<s:if test="authTypeCd=='ZCGL_HTQS_10'">
var param={isAudit:'noAudit',codeType:'1'};
</s:if>
<s:else>
var param={isAudit:'noAudit',codeType:'2'};
</s:else>
$("#contNo").quickSearch("${ctx}/cont/cont-ledger!quickSearch.action",['contNo','contName'],mapPrama,param,showModel);
function showModel(){
	var model =$("#pricingModel").val();
	if(model==0||model==1){
		$("#templateBean_pricingModel1").attr("checked",true);
	}else if(model==2){
		$("#templateBean_pricingModel2").attr("checked",true);
	}else if(model==3){
		$("#templateBean_pricingModel3").attr("checked",true);
	}
}
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

//合同文本库初始化
init();
function init(){
     //删除按钮组附件上传功能
	$("#funcPanelDiv").find("input[isAttach]").each(function(){
		//$(this).remove();
	});
	//删除评审附件、定标审批单、设计任务书
	//$("tr[otype=attachment]").remove();
	
}
//是否回执标准库选择回调函数
var isCallChooseCont=true
//是否有选择合同库
if($("#contlib").attr("checked") == true || $("#contlib").attr("checked") == "checked"){
	userCont('true');
}else{
	userCont('false');
}
</script>

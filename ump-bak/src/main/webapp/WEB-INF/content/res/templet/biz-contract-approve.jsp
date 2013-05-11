<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 合同评审表（商业） --%>
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
			for(var j=1; j<3; j++) {
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

</script>

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
			<s:if test="authTypeCd=='ZCGL_HTQS_65'||authTypeCd=='ZCGL_HTQS_72'">
			<tr>
			<td></td>
			<td class="chkGroup" align="left" validate="required" colspan="3">
				<table class="tb_checkbox">
					<col width="150">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.businessGroup" cssClass="group"></s:checkbox>总部发起</td>
					<td><s:checkbox name="templateBean.businessCompany" cssClass="group"></s:checkbox>地产公司发起</td>
					</tr>
				</table>
			</td>
			</tr>
			</s:if>
			<tr>
				<td class="td_title">项目名称</td>
				<td>
				<input validate="required" type="text" name="templateBean.projectName" value="${templateBean.projectName}" id="projectName" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip projSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" id="projectCd"  name="templateBean.projectCd" value="${templateBean.projectCd}"  />
				</td>
				<td class="td_title">工程名称</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.engineeringName" value="${templateBean.engineeringName}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">定标审批表编号</td>
				<td>
				
				<input type="hidden" name="templateBean.resApproveId" id="resApproveId" value="${templateBean.resApproveId}"/>
					<s:if test="!templateBean.resApproveId.isEmpty() && #canEdit == 'false'">
						<span  class="link"   onclick="openSheet('${templateBean.resApproveCd}','${templateBean.resApproveId}');">${templateBean.resApproveCd}</span>
						<input class="inputBorder" name="templateBean.resApproveCd"  type="hidden" id="resApproveCd" value="${templateBean.resApproveCd}"/>
					</s:if>
					<s:else>
						<input class="inputBorder" validate="required" name="templateBean.resApproveCd"  type="text" id="resApproveCd" value="${templateBean.resApproveCd}"/>
					</s:else>
				</td>
				<td class="td_title">定标审批表名称</td>
				<td align="left">
				<c:choose>
					<c:when test="${empty templateBean.resApproveTitleName || templateBean.resApproveTitleName eq 'null'}">
						<input  class="inputBorder" readonly="readonly" name="templateBean.resApproveTitleName"  type="text" id="resApproveTitleName"  value=""/>
					</c:when>
					<c:otherwise>
						<input  class="inputBorder" readonly="readonly" name="templateBean.resApproveTitleName"  type="text" id="resApproveTitleName"  value="${templateBean.resApproveTitleName}"/>
					</c:otherwise>
				</c:choose>
				
				
				</td>
			</tr>
			<s:if test="authTypeCd=='JD_ZCGL_HTQS_40'||authTypeCd=='JD_ZCGL_HTQS_70'
			||authTypeCd=='JD_ZCGL_HTQS_90'||authTypeCd=='ZCGL_HTQS_16'||authTypeCd=='ZCGL_HTQS_17'">
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
				<td class="td_title">评审内容</td>
				<td colspan="3" class="chkGroup" align="left"  validate="required">
					<s:checkbox name="templateBean.approveContent2" cssClass="group" ></s:checkbox>合同文件
				</td>
			</tr>
			<tr id="tr_cooperationWays">
				<td class="td_title" rowspan="1" valign="middle" align="center">合  作<br/>方  式</td>
				<td colspan="3" class="chkGroup" align="center" validate="required">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="170"/>
						<col/>
						<col width="50"/>
						 <s:if test="authTypeCd == 'ZCGL_HTQS_16'">
						 	<tr>
							<td><s:checkbox name="templateBean.cooperationWays1"  cssClass="group" onclick="onclick_cooperationWays(this,1);"></s:checkbox>招标(理由：</td>
							<td><input class="inputBorder" type="text" id="textField1" name="templateBean.oriContractFileCd" value="${templateBean.oriContractFileCd}" /></td>
							<td>)</td>
							</tr>
							<tr>
							<td><s:checkbox name="templateBean.cooperationWays2"  cssClass="group" onclick="onclick_cooperationWays(this,2);"></s:checkbox>续标、直接委托、竞争性谈判(理由：</td>
							<td><input class="inputBorder" type="text" id="textField2" name="templateBean.delegateReason" value="${templateBean.delegateReason}" /></td>
							<td>)</td>
							</tr>
						 </s:if>
						 <s:else>
						 	<tr>
							<td><s:checkbox name="templateBean.cooperationWays1"  cssClass="group" onclick="onclick_cooperationWays(this,1);"></s:checkbox>续标(原合同文件编号：</td>
							<td><input class="inputBorder" type="text" id="textField1" name="templateBean.oriContractFileCd" value="${templateBean.oriContractFileCd}" /></td>
							<td>)</td>
							</tr>
							<tr>
							<td><s:checkbox name="templateBean.cooperationWays2"  cssClass="group" onclick="onclick_cooperationWays(this,2);"></s:checkbox>直接委托(理由：</td>
							<td><input class="inputBorder" type="text" id="textField2" name="templateBean.delegateReason" value="${templateBean.delegateReason}" /></td>
							<td>)</td>
							</tr>
							<tr>
							<td><s:checkbox name="templateBean.cooperationWays3"  cssClass="group" onclick="onclick_cooperationWays(this,3);"></s:checkbox>竞争性谈判(理由：</td>
							<td><input class="inputBorder" type="text" id="textField3" name="templateBean.negotiationReason" value="${templateBean.negotiationReason}" /></td>
							<td>)</td>
							</tr>
						 </s:else>
					</table>
				</td>
			</tr>
			<tr>
				<td class="td_title" rowspan="6" valign="middle" align="center">合 同<br/>主  要<br/>内  容</td>
				<td colspan="3" style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="80"/>
						<tr>
						<td>合同范围:</td>
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
						<td>工期:</td>
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
						<tr>
						<td><s:checkbox name="templateBean.pricingModel3"  cssClass="group"></s:checkbox>按实结算</td>
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
				<td id="td_attachment" class="td_title" >评审附件</td>
				<td style="height:40px;" value="${templateBean.bidContractFileId}" resattachname="合同文本 ">合同文本</td>
				<td>
				<s:if test="#canEdit=='true'">
				<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('合同附件','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','bidContractFileId',event);"/>
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
		</table>
		
		
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<%--表类型判断 --%>
<c:choose>
	<%--HOE供应商 --%>
	<c:when test="${authTypeCd == 'JD_ZCGL_HTQS_20' || authTypeCd == 'JD_ZCGL_HTQS_30'}">
	<c:set var="selectAuthTypeCd"  value="JD_ZCGL_DB_10"/>
	</c:when>
	<%--设计单位 --%>
	<c:when test="${authTypeCd == 'JD_ZCGL_HTQS_40' || authTypeCd == 'JD_ZCGL_HTQS_50'}">
	<c:set var="selectAuthTypeCd"  value="JD_ZCGL_DB_20"/>
	</c:when>
	<%--工程承包商 --%>
	<c:when test="${authTypeCd == 'JD_ZCGL_HTQS_60' || authTypeCd == 'JD_ZCGL_HTQS_70'}">
	<c:set var="selectAuthTypeCd"  value="JD_ZCGL_DB_30"/>
	</c:when>
	<%--材料设备供应商 --%>
	<c:when test="${authTypeCd == 'JD_ZCGL_HTQS_80' || authTypeCd == 'JD_ZCGL_HTQS_90'}">
	<c:set var="selectAuthTypeCd"  value="JD_ZCGL_DB_40"/>
	</c:when>
	<%--外包项目保安、保结、绿化 --%>
	<c:when test="${authTypeCd == 'BLSY_ZCGL_HTQS_40'}">
	<c:set var="selectAuthTypeCd"  value="BLSY_ZCGL_DB_50"/>
	</c:when>
	<%--营销推广商业总部发起 --%>
	<c:when test="${authTypeCd == 'BLSY_ZCGL_HTQS_60'}">
	<c:set var="selectAuthTypeCd"  value="BLSY_ZCGL_DB_60"/>
	</c:when>
	<%--营销推广公司发起 --%>
	<c:when test="${authTypeCd == 'BLSY_ZCGL_HTQS_50'}">
	<c:set var="selectAuthTypeCd"  value="BLSY_ZCGL_DB_70"/>
	</c:when>
	<%--设计 --%>
	<c:when test="${authTypeCd == 'BLSY_ZCGL_HTQS_35'}">
	<c:set var="selectAuthTypeCd"  value="BLSY_ZCGL_DB_10"/>
	</c:when>
	<%--IT(IT服务机构) --%>
	<c:when test="${authTypeCd == 'ZCGL_HTQS_41'}">
	<c:set var="selectAuthTypeCd"  value="ZCGL_HTQS_81"/>
	</c:when>
	<%--营销(媒体广告、合作单位、物料道具制作单位、活动推广合作单位) --%>
	<c:when test="${authTypeCd == 'ZCGL_HTQS_16'}">
	<c:set var="selectAuthTypeCd"  value="ZCGL_HTQS_53"/>
	</c:when>
	<c:when test="${authTypeCd == 'ZCGL_HTQS_17'}">
	<c:set var="selectAuthTypeCd"  value="ZCGL_HTQS_55"/>
	</c:when>
	<%--造价咨询机构 --%>
	<c:when test="${authTypeCd == 'ZCGL_HTQS_31'}">
	<c:set var="selectAuthTypeCd"  value="ZCGL_HTQS_71,ZCGL_HTQS_60"/>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
var mapPrama={
		approveCd:'resApproveCd',
		titleName:'resApproveTitleName',
		id:'resApproveId'
	};
	$("#resApproveCd").quickSearch("${ctx}/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama,{authTypeCd:'${selectAuthTypeCd}',statusCd:'2'});
function openSheet(resAuthTypeCd,id){
	var url="${ctx}/res/res-approve-info!detail.action?resAuthTypeCd="+resAuthTypeCd+"&id="+id+"&statusCd=2";
	window.open(url);
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

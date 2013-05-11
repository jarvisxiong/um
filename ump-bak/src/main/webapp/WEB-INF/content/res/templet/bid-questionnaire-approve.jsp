<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%-- 询标问卷审批表 --%>
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
		<table  class="mainTable">
			<col width="110"/>
			<col/>
			<col width="125"/>
			<col/>
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
				<td class="td_title">招标文件编号</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.inviteBidFileCd" value="${templateBean.inviteBidFileCd}" />
				</td>
				<td class="td_title">参加答疑单位</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.accessUnit" value="${templateBean.accessUnit}" />
				</td>
			</tr>
			<tr>
				<td class="td_title">询标回复时间</td>
				<td>
				<input onfocus="WdatePicker()" class="Wdate inputBorder" type="text" validate="required" id="toDate" name="templateBean.bidAnswerDate" value="${templateBean.bidAnswerDate}"/>
				</td>
				<td class="td_title">询标附件页数</td>
				<td>
				<input class="inputBorder" validate="required" type="text" name="templateBean.bidAttachmentPage" value="${templateBean.bidAttachmentPage}" />
				</td>
			</tr>
			<tr>
				<td class="td_title" style="font-weight: bold;">附件</td>
				<td colspan="3">
					<s:url id="downBidQuestionnaire" action="download" namespace="/app" includeParams="none" >
				  	  <s:param name="fileName">BidQuestionnaire.doc</s:param>
				  	  <s:param name="realFileName">询标问卷.doc</s:param>
				  	  <s:param name="bizModuleCd">resDownload</s:param>
					</s:url>
					<a href="${downBidQuestionnaire}">询标问卷</a>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--招标文件与招标合同评审审批表--%>
<%@ include file="template-header.jsp"%>


<%@page import="com.hhz.ump.util.DictContants"%>
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
		<%--删除商业集团公司发起 --%>
		<table  class="mainTable" border="1">
			<col width="110"/>
			<col/>
			<tr>
				<td valign="middle" align="center"></td>
				<td valign="middle" align="center">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="100">
						<col width="100">
						<col width="100">
						<tr>
						<td>
							<s:checkbox name="templateBean.containLawFlg"  id="containLawFlg"></s:checkbox>包含法务
						</td>
						<td>
							<s:checkbox name="templateBean.containFinancialFlg"  id="containFinancialFlg"></s:checkbox>包含财务
						</td>
						<td>
							<s:checkbox name="templateBean.containHotelFlg"  id="containHotelFlg"></s:checkbox>包含酒店
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td valign="middle" align="center">合同双方</td>
				<td align="center" >
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder" align="center">
						<col width="60">
						<col>
						<col width="60" align="center">
						<col>
						<tr>
							<td >甲方:</td>
							<td ><input class="inputBorder" validate="required" type="text" name="templateBean.oriBidConA" value="${templateBean.oriBidConA}" /></td>
							<td style="text-align: center;">签约人:</td>
							<td><input class="inputBorder" validate="required" type="text" name="templateBean.oriBidConPersonA" value="${templateBean.oriBidConPersonA}" /></td>
						</tr>
						<tr>
							<td>乙方:</td>
							<td><input class="inputBorder" validate="required" type="text" name="templateBean.oriBidFileB" value="${templateBean.oriBidFileB}" /></td>
							<td style="text-align: center;">签约人:</td>
							<td><input class="inputBorder" validate="required" type="text" name="templateBean.oriBidConPersonB" value="${templateBean.oriBidConPersonB}" /></td>
						</tr>
						<!-- Start Add for part C by zhuxj on 2012.3.31 -->
						<tr>
							<td>丙方:</td>
							<td><input class="inputBorder" type="text" name="templateBean.oriBidConC" value="${templateBean.oriBidConC}" /></td>
							<td style="text-align: center;">签约人:</td>
							<td><input class="inputBorder" type="text" name="templateBean.oriBidConPersonC" value="${templateBean.oriBidConPersonC}" /></td>
						</tr>
						<!-- End   Add for part C by zhuxj on 2012.3.31 -->
					</table>
				</td>
			</tr>
			<tr>
				<td valign="middle" align="center">文件类型</td>
				<td class="chkGroup" align="center"   validate="required">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<tr>
							<td width="180">
							<s:checkbox name="templateBean.oriBidFileCheckA"  cssClass="group"  id="examType1"></s:checkbox>
							招标文件(招标文件编号)：</td>
							<td colspan="2">
							<input class="inputBorder" readonly="true" type="text" name="templateBean.oriBidFileCode" id="oriBidFileCode" value="${templateBean.oriBidFileCode}" />
							</td>
						</tr>
						<tr>
						<td><s:checkbox name="templateBean.oriBidFileCheckB" cssClass="group"  id="examType2"></s:checkbox>正式合同</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td rowspan="5" valign="middle" align="center">招  标<br/>主  要<br/>内  容</td>
				<td style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="60"/>
						<col>
						<tr>
						<td>合同标段:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.oriBidConSection" value="${templateBean.oriBidConSection}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="60"/>
						<col>
						<tr>
						<td>合同工期:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.oriBidConPeriod" value="${templateBean.oriBidConPeriod}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="60"/>
						<col>
						<tr>
						<td>招标范围:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.oriBidConRangeAmount" value="${templateBean.oriBidConRangeAmount}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="60"/>
						<col>
						<tr>
						<td>预估价款:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.oriBidConEvaluated" value="${templateBean.oriBidConEvaluated}" onblur="formatVal($(this));" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="padding-top:3px;">
					<table border="0" cellpadding="0" cellspacing="0" class="tb_noborder">
						<col width="60"/>
						<col>
						<tr>
						<td>付款方式:</td>
						<td>
						<input class="inputBorder" validate="required" type="text" name="templateBean.oriBidConPayType" value="${templateBean.oriBidConPayType}" />
						</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>

<%@ include file="template-footer.jsp"%>




<!-- 默认1条申请记录 -->
<s:if test="#canEdit== 'true'">
<script>


$(".group").click(function(){

	if ($('#examType1').attr('checked') ) {
		$('#oriBidFileCode').attr("readonly",false);
		$('#oriBidFileCode').attr("validate", "required");
	} else {
		$('#oriBidFileCode').attr("readonly",true);
		$('#oriBidFileCode').val("");
		$('#oriBidFileCode').removeAttr("validate");
	}
	
});



</script>

</s:if>


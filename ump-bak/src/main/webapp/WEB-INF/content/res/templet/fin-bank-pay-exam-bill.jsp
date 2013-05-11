<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	
<!--银行付款审批单-->
<%@ include file="template-header.jsp"%>

<div align="center" class="billContent">
	
<s:set var="canEdit">
<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
true
</s:if>
<s:else>
false
</s:else>
</s:set>
	
	<form action="res-approve-info!save.action" method="post" id="templetForm" class="CertTranReqBill">
		<%@ include file="template-var.jsp"%>
		<input type="hidden" name="templateBean.specialApproveBillId" value="${templateBean.specialApproveBillId}" >
		<table  class="mainTable">
			<col width="100px">
			<col>
			<col width="80px">
			<col width="60px">
			<col>
			<s:if test="authTypeCd!='FKGL_220'">
			<tr>
				<td class="td_title">
				</td>
				<td colspan="4" class="chkGroup" align="left" validate="required">
				<table class="tb_checkbox">
					<col width="150">
					<col>
					<tr>
					<td><s:checkbox name="templateBean.type1" cssClass="group"></s:checkbox>全资公司</td>
					<td><s:checkbox name="templateBean.type2" cssClass="group"></s:checkbox>合资公司(宝龙拍卖、聚龙轩)</td>
					</tr>
				</table>
				</td>
				</tr>
				</s:if>
				<s:if test="authTypeCd=='FKGL_220'">
			<tr>
			<td class="td_title">
			</td>
			<td colspan="4" class="chkGroup" align="left" validate="required">
			<table class="tb_checkbox">
				<col width="150">
				<col width="150">
				<tr>
				<td><s:checkbox name="templateBean.area1" id="area1" cssClass="group area"></s:checkbox>集团内</td>
				<td><s:checkbox name="templateBean.area2" cssClass="group area"></s:checkbox>集团外</td>
				</tr>
			</table>
			</td>
			</tr>
			</s:if>
			<tr class="noprint">
				<td class="td_title">关联资金申请</td>
				<td colspan="3" align="left">
					<input type="hidden" name="templateBean.resApproveId" id="resApproveId" value="${templateBean.resApproveId}"/>
					<s:if test="!templateBean.resApproveId.isEmpty() && #canEdit == 'false'">
						<span class="link"  onclick="openSheet('${templateBean.resApproveId}');">${templateBean.resDisplayNo}</span>
						<input class="inputBorder" name="templateBean.resDisplayNo"  type="hidden" id="resDisplayNo" value="${templateBean.resDisplayNo}"/>
					</s:if>
					<s:else>
						<input class="inputBorder" validate="required" name="templateBean.resDisplayNo" type="text" id="resDisplayNo" value="${templateBean.resDisplayNo}"/>
					</s:else>
				</td>
				<td><s:checkbox id="noApply" name="templateBean.noApply" cssClass="group"></s:checkbox>无资金申请表</td>
			</tr>
			<tr>
				<td class="td_title">付款单位</td>
				<td>
					<input validate="required" class="inputBorder" type="text" id="payDeptName" name="templateBean.payDeptName" value="${templateBean.payDeptName}"/>
				</td>
				<td class="td_title">开户行账号</td>
				<td colspan="2">
					<input validate="required" class="inputBorder" type="text" id="payAccount" name="templateBean.payAccount" value="${templateBean.payAccount}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">付款种类</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" id="payTypeName" name="templateBean.payTypeName" value="${templateBean.payTypeName}"/>
					<input type="hidden" id="payTypeCd"  name="templateBean.payTypeCd" value="${templateBean.payTypeCd}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">付款事由</td>
				<td colspan="4">
					<input validate="required" class="inputBorder" type="text" id="payContentDesc" name="templateBean.payContentDesc" value="${templateBean.payContentDesc}"/>
				</td>
			</tr>
			<tr>
				<td class="td_title">付款金额(元)</td>
				<td colspan="4" style="padding-top:3px;vertical-align: middle;">
					<div style="float:left;">(大写)</div>
					<div style="float:left;">
						<input style="width:220px;text-align: right;" readonly="readonly" class="inputBorder" type="text" id="payAmtCapitalDesc"  name="templateBean.payAmtCapitalDesc" value="${templateBean.payAmtCapitalDesc}"  />
					</div>
					<div style="float:left;margin-left:25px;">(小写)</div>
					<div style="float:left;">
						<input class="inputBorder" <s:if test="templateBean.specialApproveBillId!='' && templateBean.specialApproveBillId!=null">  readonly="readonly"  </s:if> type="text" id="payAmtSmallDesc"  name="templateBean.payAmtSmallDesc" value="${templateBean.payAmtSmallDesc}"  onblur="formatVal($(this));" onchange="formatVal($(this));$('#payAmtCapitalDesc').val(i2c(formatFloat($(this).val())))"/>
					</div>
				</td>
			</tr>	
			<tr>
				<td class="td_title">凭证号码</td>
				<td>
					<input class="inputBorder" type="text" id="voucherNo"  name="templateBean.voucherNo" value="${templateBean.voucherNo}"  />
				</td>
				<td  class="td_title" rowspan="3">收款单位</td>
				<td class="td_title" >全称</td>
				<td id="td_companyName" value="${templateBean.receiveUnitName}">
				<select id="sel_companyName" name="templateBean.receiveUnitName" style="display:none;" class="inputBorder" onchange="selectCompany($(this));">
				<s:iterator value="@com.hhz.ump.util.DictMapUtil@loadCompanies()" var="it">
					<option>${it}</option>
				</s:iterator>
				</select>
				<input class="inputBorder" type="text" id="inp_companyName"  name="templateBean.receiveUnitName" value="${templateBean.receiveUnitName}"  />
				</td>
			</tr> 
			<tr>
				<td class="td_title" rowspan="2">报销单编号</td>
				<td rowspan="2">
					<input class="inputBorder" type="text" id="expenseAccountNo"  name="templateBean.expenseAccountNo" value="${templateBean.expenseAccountNo}"  />
				</td>
				<td class="td_title">帐号</td>
				<td id="td_AccountNo" value="${templateBean.receiveAccountNo}">
					<input id="inp_accountNo" class="inputBorder" type="text" id="receiveAccountNo"  name="templateBean.receiveAccountNo" value="${templateBean.receiveAccountNo}"  />
				</td>
			</tr>
			<tr>
				<td class="td_title">开户银行</td>
				<td id="td_bankName" value="${templateBean.receiveBankName}">
					<input id="inp_bankName" class="inputBorder" type="text" id="receiveBankName"  name="templateBean.receiveBankName" value="${templateBean.receiveBankName}"  />
				</td>
			</tr>
		</table>
		<%@ include file="template-approver.jsp"%>
	</form>
</div>
<%@ include file="template-footer.jsp"%>
<script type="text/javascript">
	var mapPrama={
		approveCd:'resDisplayNo',
		id:'resApproveId'
	};
	$("#resDisplayNo").quickSearch("${ctx}/res/res-approve-info!quickSearch.action",["approveCd","titleName"],mapPrama,{templateCd:'154',statusCd:'2'});
	function openSheet(id){
		var url="${ctx}/res/res-approve-info!detail.action?id="+id;
		window.open(url);
	}
	function selectCompany(dom){
		$.post(_ctx+"/res/res-approve-info!loadBankNo.action",{companyName:dom.val()},
				 function(result) {
 				var arr=eval('('+result+')');
 				var o_accountNo="",sel_accountNo="";
 				var o_bankName="",sel_bankName="";
				for(var i in arr){
					o_accountNo=o_accountNo+"<option>"+i+"</option>";
					o_bankName=o_bankName+"<option>"+arr[i]+"</option>";
				}
				sel_accountNo="<select id='selAccountNo' class='inputBorder' name='templateBean.receiveAccountNo' onchange='selectOption($(this));' >"+o_accountNo+"</select>";
				sel_bankName="<select id='selBankName' class='inputBorder' name='templateBean.receiveBankName' onchange='selectOption($(this));' >"+o_bankName+"</select>";
				$("#td_AccountNo").html(sel_accountNo);
				$("#td_bankName").html(sel_bankName);
				$("#selAccountNo").val($("#td_AccountNo").attr("value"));
				$("#selBankName").val($("#td_bankName").attr("value"));
		});
	}
	function selectOption(dom){
		var index=dom.attr("selectedIndex");
		var domId=dom.attr("id");
		if (domId == "selAccountNo"){
			$("#selBankName").attr("selectedIndex",index);
		}else{
			$("#selAccountNo").attr("selectedIndex",index);
		}
	}
	var _sel_companyName=$("#sel_companyName");
	var _inp_companyName=$("#inp_companyName");
	var _inp_accountNo=$("#inp_accountNo");
	var _inp_bankName=$("#inp_bankName");
	function checkOption(){
		var checked=$("#area1").attr("checked");
		if (checked){
			$("#td_companyName").html(_sel_companyName);
			$("#sel_companyName").show();
			$("#sel_companyName").val($("#td_companyName").attr("value"));
			selectCompany($("#sel_companyName"));
		}else{
			$("#td_companyName").html(_inp_companyName);
			$("#inp_companyName").show();
			$("#td_AccountNo").html(_inp_accountNo);
			$("#td_bankName").html(_inp_bankName);
		}
	}
</script>
<s:if test="#canEdit == 'true'">
<script type="text/javascript">
	$(".area").click(checkOption);
	checkOption();
	$("#noApply").click(function(){
		var noApply=$("#noApply").attr("checked");
		if (noApply){
			$("#resDisplayNo").removeAttr("validate").removeClass('required');
		}else{
			$('#resDisplayNo').attr("validate", "required").addClass('required');
		}
	});
</script>
</s:if>
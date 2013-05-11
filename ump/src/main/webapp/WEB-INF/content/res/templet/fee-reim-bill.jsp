<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%--费用报销审批表--%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
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
<s:set var="isAccount">false</s:set>
<s:if test="(nodeCd==235||nodeCd==237) && isMyApprove==1">
	<s:hidden id="isEdit" value="true"/>
	<s:set var="isAccount">true</s:set>
</s:if>
<div class="billContent" align="center">
	<table class="mainTable" id="js_tbl_fee">
		<col width="90">
		<col>
		<col width="90">
		<col>
		<tr>
			<td class="td_title">申请中心</td>
			<td colspan="3">
				<s:if test="resApproveInfoId==null">
				<c:set var="sendCenterName"><%=CodeNameUtil.getDeptNameByCd(SpringSecurityUtils.getCurrentCenterCd()) %> </c:set>
				<c:set var="sendCenterCd"><%=SpringSecurityUtils.getCurrentCenterCd() %></c:set>
				</s:if>
				<s:else>
				<c:set var="sendCenterName">${templateBean.applyCenterName}</c:set>
				<c:set var="sendCenterCd">${templateBean.applyCenterCd}</c:set>
				</s:else>
				<input validate="required" type="text" name="templateBean.applyCenterName" value="${sendCenterName}" readonly="readonly" <s:if test="#isMy==1"> class="inputBorderNoTip orgSelect" style="cursor: pointer;" </s:if ><s:else> class="inputBorderNoTip" </s:else>/>
				<input type="hidden" name="templateBean.applyCenterCd" value="${sendCenterCd}"  />
			</td>
		</tr>
		<tr>
			<td class="td_title">入账公司</td>
			<td  colspan="3">
				<c:choose>
				<c:when test='${fn:startsWith(authTypeCd, "BLSY_")||fn:startsWith(authTypeCd, "SYGS_")}'>
				<input class="inputBorder" validate="required" readonly="readonly" type="text" name="templateBean.recordedCompany" value="上海商盛投资管理咨询有限公司" />
				</c:when>
				<c:otherwise>
				<s:if test="#isMy!=1">
					<input class="inputBorderNoTip" type="text" value="<%=CodeNameUtil.getDictNameByCd(DictContants.RES_FEE_RECORDED_COMPANY,JspUtil.findString("templateBean.recordedCompany")) %>"  />
				</s:if>
				<s:else>
					<s:select id="js_recordedCompany" cssClass="inputBorder" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapRecordedCompany()" listKey="key" listValue="value" name="templateBean.recordedCompany" onchange="autoSelectAccount();"></s:select>
				</s:else>
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<td class="td_title"></td>
			<td colspan="3" validate="required"  class="chkGroup">
				<table class="tb_checkbox">
				<col width="150">
				<col width="150">
				<tr>
					<td><s:checkbox name="templateBean.inFlag" id="inFlag" cssClass="group"></s:checkbox>预算内</td>
					<td><s:checkbox name="templateBean.outFlag" id="outFlag" cssClass="group"></s:checkbox>预算外</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title"></td>
			<td colspan="3" validate="required"  class="chkGroup">
				<table class="tb_checkbox">
				<col width="150">
				<col >
				<tr>
					<td><s:checkbox name="templateBean.match" cssClass="group"></s:checkbox>符合规定</td>
					<td><s:checkbox name="templateBean.mismatch" cssClass="group" id="mismatch" onchange="checkOhter();"></s:checkbox>不符合规定，原因
					<input class="inputBorder" id="otherInfo" type="text" name="templateBean.otherInfo" value="${templateBean.otherInfo}" style="width:100px;"/>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">开支内容</td>
			<td class="chkGroup" validate="required" colspan="3" align="left">
				<table class="tb_checkbox"  onclick="addTripFile();">
				<col width="150">
				<col width="150">
				<tr>
					<td><s:checkbox name="templateBean.feeType1" id="js_feeType1" cssClass="group"></s:checkbox>补贴</td>
					<td><s:checkbox name="templateBean.feeType2" id="js_feeType2" cssClass="group" ></s:checkbox>差旅费</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_title">报销/付款信息</td>
			<td colspan="3">
				<table id="tbConItem" style="margin-top: 5px;" width="100%">
						<col width="50">
						<col width="100">
						<col>
						<col width="40">
						<tr>
							<td align="center">单据数</td>
							<td align="center">金额</td>
							<td align="center">说明</td>
							<td align="center">操作</td>
						</tr>
						<s:if test="statusCd==0 || statusCd==3">
						<tr id="trConItem" style="display: none;margin-bottom:5px;">
							<td>
								<input validate="required" class="inputBorder" type="text" name="templateBean.otherProperties[0].docNumber"/>
							</td>
							<td>
								<input validate="required" class="inputBorder js_money" type="text" name="templateBean.otherProperties[0].money" onblur="formatVal($(this));" onkeyup="autoSum();" />
							</td>
							<td>
								<input validate="required" class="inputBorder" type="text" name="templateBean.otherProperties[0].remark"/>
							</td>
							<td><a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a></td>
						</tr>
						</s:if>
						<s:set var="conItemCount"><s:property value="templateBean.otherProperties.size()"/></s:set>
						<s:iterator value="templateBean.otherProperties" var="item" status="s">
							<tr style="margin-bottom:5px;">
								<td>
								<input validate="required" class="inputBorder" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].docNumber" value="${item.docNumber}"/>
							</td>
							<td>
								<input validate="required" class="inputBorder js_money" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].money" value="${item.money}" onblur="formatVal($(this));" onkeyup="autoSum();" />
							</td>
							<td>
								<input validate="required" class="inputBorder" type="text" name="templateBean.otherProperties[<s:property value="#s.index" />].remark" value="${item.remark}"/>
							</td>
							<td>	
								<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
								<a href="javascript:void(0)" onclick="delItem(this)"><img src="${ctx}/resources/images/common/del_22_22.gif" title="删除" border="0"/></a>
								</s:if>
							</td>
							</tr>
						</s:iterator>
					</table>
					</td>
				</tr>
				<tr>
					<td class="td_title">报销总计(元)</td>
					<td colspan="3">
						<table style="width:100%; border: 0px;">
							<col width="120"/>
							<col width="170"/>
							<col width="50"/>
							<col width="70"/>
							<tr>
								<td style="border-left-width: 0px;border-top-width: 0px;"><input class="inputBorder" readonly="readonly" validate="required" type="text" id="js_total" name="templateBean.total" value="${templateBean.total}" onblur="formatVal($(this));" onkeypress="change2Big('js_total');"/></td>
								<td style="border-left-width: 0px;border-top-width: 0px;"><input class="inputBorder" readonly="readonly" id="js_totalBig" type="text" name="templateBean.totalBig" value="${templateBean.totalBig}"/></td>
								<td class="td_title" style="border-left-width: 0px;border-top-width: 0px;">币种</td>
								<td style="border-left-width: 0px;border-top-width: 0px;">
								<input class="inputBorder" validate="required" type="text" name="templateBean.currency" value="${templateBean.currency}"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
				<td class="td_title">报销/付款信息</td>
				<td class="chkGroup" validate="required" align="left">
					<table class="tb_checkbox">
					<col width="100"/>
					<col width="100"/>
					<col width="100"/>
					<tr>
						<td><s:checkbox name="templateBean.payType1" cssClass="group"></s:checkbox>现金</td>
						<td><s:checkbox name="templateBean.payType2" cssClass="group"></s:checkbox>电汇</td>
						<td><s:checkbox name="templateBean.payType3" cssClass="group"></s:checkbox>信用证</td>
					</tr>
					<tr>
						<td><s:checkbox name="templateBean.payType4" cssClass="group"></s:checkbox>转账</td>
						<td><s:checkbox name="templateBean.payType5" cssClass="group"></s:checkbox>票汇</td>
						<td><s:checkbox name="templateBean.payType6" cssClass="group"></s:checkbox>其他</td>
					</tr>
					</table>
				</td>
				<td class="td_title">公司代付合计(元)</td>
				<td>
					<input class="inputBorder" <s:if test="#isAccount=='true'">edit='true'</s:if> validate="required" type="text" id="js_companyPay" name="templateBean.companyPay" value="${templateBean.companyPay}"  onblur="formatVal($(this));" onkeyup="countReal();" />
				</td>
				</tr>
				<tr>
				<td class="td_title">冲预借款(元)</td>
				<td>
					<input class="inputBorder" <s:if test="#isAccount=='true'">edit='true'</s:if> validate="required" type="text" id="js_punchAdvance" name="templateBean.punchAdvance" value="${templateBean.punchAdvance}"  onblur="formatVal($(this));" onkeyup="countReal();" />
				</td>
				<td class="td_title">会计核减(元)</td>
				<td>
					<input class="inputBorder"  <s:if test="#isAccount=='true'">edit='true' validate="required"</s:if> type="text" id="js_accountReduce" name="templateBean.accountReduce" value="${templateBean.accountReduce}"  onblur="formatVal($(this));" onkeyup="countReal();" />
				</td>
				</tr>
				<tr>
					<td class="td_title" rowspan="3">转账</td>
					<td class="td_title">收款方全称</td>
					<td colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.payee" value="${templateBean.payee}" />
					</td>
				</tr>
				<tr>
					<td class="td_title">收款账号</td>
					<td colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.payeeAccount" value="${templateBean.payeeAccount}" />
					</td>
				</tr>
				<tr>
					<td class="td_title">开户行</td>
					<td colspan="2">
					<input class="inputBorder" validate="required" type="text" name="templateBean.payeeBank" value="${templateBean.payeeBank}" />
					</td>
				</tr>
				<tr>
					<td class="td_title">实付金额(元)</td>
					<td><input class="inputBorder" readonly="readonly" validate="required" type="text" id="js_payAmount" name="templateBean.payAmount" value="${templateBean.payAmount}" onblur="formatVal($(this));"  onkeypress="change2Big('js_payAmount');"/></td>
					<td colspan="2"><input class="inputBorder" readonly="readonly"  type="text" id="js_payAmountBig" name="templateBean.payAmountBig" value="${templateBean.payAmountBig}"/></td>
				</tr>
				
				<tr id="js_tr_trip" <s:if test="templateBean.feeType2!='true'"> style="display: none;" </s:if>>
					<td class="td_title" >附件上传</td>
					<td align="center" style="height:40px;" id ="js_td_trip" <s:if test="templateBean.feeType2=='true'">validate="required"</s:if>  value="${templateBean.tripFileId}">出差申请单</td>
					<td align="center">
					<s:if test="#canEdit=='true'">
					<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('出差申请单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','tripFileId',event);"/>
					<input type="hidden" name="templateBean.tripFileId" id="tripFileId" value="${templateBean.tripFileId}"/>
					</s:if>
					</td>
					<td>
					<div id="show_tripFileId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.tripFileId}','tripFileId','${canEdit}');
					</script>
					</td>
				</tr>
				<tr id="js_tr_jd" <s:if test="templateBean.feeType1!='true'"> style="display: none;" </s:if>>
					<td class="td_title" >附件上传</td>
					<td align="center" style="height:40px;" id ="js_td_jd" <s:if test="templateBean.feeType1=='true'">validate="required"</s:if>  value="${templateBean.jdFileId}">接待申请单</td>
					<td align="center">
					<s:if test="#canEdit=='true'">
					<input type="button" value="上传附件" class="btn_green_65_20" style="border:none;" onclick="showUploadSingleAttachDialog('接待申请单','${resApproveInfoId==null?entityTmpId:resApproveInfoId}','resCustomFile','jdFileId',event);"/>
					<input type="hidden" name="templateBean.jdFileId" id="jdFileId" value="${templateBean.jdFileId}"/>
					</s:if>
					</td>
					<td>
					<div id="show_jdFileId"></div>
					<script type="text/javascript">
					showUploadedFile('${templateBean.jdFileId}','jdFileId','${canEdit}');
					</script>
					</td>
				</tr>
				</table>
				<s:if test="(statusCd==0||statusCd==3)&&userCd==#curUserCd">
					<input type="button"  class="btn_blue_75_55" value="增加条款" onclick="addItem();" />
				</s:if>
</div>
<script type="text/javascript">
	var trApprover=$("#trConItem").clone();
	$("#trConItem").remove();
	var cloneCount = 0;
	var index=0;
	try{
		index = Number("${conItemCount}");
	}catch(e){}
	function addItem(){
		
		var trApprover_new=trApprover.clone();
		cloneCount++;
		$(trApprover_new).show();
		$(trApprover_new).find(".inputBorder").each(function(i){
			this.name=this.name.replace('0',index);
		});
		//$(trApprover_new).find("#cloneUserCds").attr("id",cloneCount+"cloneUserCds");
		$("#tbConItem").append(trApprover_new);
		index++;
	}
	
	function delItem(dom){
		$(dom).parent().parent().remove();
	}
	<%--小写转大写--%>
	function change2Big(id){
		var val=getVal(id);
		var valBig=i2c(val);
		if(id=='js_total'){
			$("#js_totalBig").val(valBig);
		}
		if(id=='js_payAmount'){
			$("#js_payAmountBig").val(valBig);
		}
	}
	<%--自动合计--%>
	function autoSum(){
		var sum=0;
		$(".js_money").each(function(i){
			var val = getValByJ($(this));
			sum += val;
		});
		$("#js_total").val(formatMoney(sum,2));
		var valBig=i2c(sum);
		$("#js_totalBig").val(valBig);
		countReal();
	}
	<%--实付金额：报销合计-公司代付合计-冲预借款-会计核减--%>
	function countReal(){
		var total=getVal("js_total");//报销合计
		var companyPay=getVal("js_companyPay");//公司代付合计
		var punchAdvance=getVal("js_punchAdvance");//冲预借款
		var accountReduce=getVal("js_accountReduce");//会计核减
		var payAmount=total-companyPay-punchAdvance-accountReduce;
		$("#js_payAmount").val(formatMoney(payAmount,2));
		var valBig=i2c(payAmount);
		$("#js_payAmountBig").val(valBig);
	}
	function addTripFile(){
		var isTrip=$("#js_feeType2").attr('checked');
		var isJd=$("#js_feeType1").attr('checked');
		if (isTrip) {
			$("#js_tr_trip").show();
			$("#js_td_trip").attr("validate","required");
		}else{
			$("#js_tr_trip").hide();
			$("#js_td_trip").removeAttr("validate");
		}
		if (isJd) {
			$("#js_tr_jd").show();
			$("#js_td_jd").attr("validate","required");
		}else{
			$("#js_tr_jd").hide();
			$("#js_td_jd").removeAttr("validate");
		}
	}
	function checkOhter(){
		var checkOther=$("#mismatch").attr("checked");
		if (checkOther){
			$('#otherInfo').attr("validate", "required").addClass('required');
		}else{
			$("#otherInfo").removeAttr("validate").removeClass('required');
		}
	}
</script>
<!-- 默认1条申请记录 -->
<s:if test="resApproveInfoId==null">
<script type="text/javascript">
	addItem();
</script>
</s:if>
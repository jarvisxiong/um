<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="${ctx}/resources/js/jquery/jQuery.artTxtCount.js"></script>
	<div class="toolBar">
	<input type="button" value="确定" class="btn_blue" onclick="saveApproveDelay();" />
	<input type="button" value="返回" class="btn_green" onclick="openDetail();" />
	</div>
	<div class="list_header"><span>延期申请</span></div>
	<div id="resApproveDelayDiv" style="margin:0px;">
	<div style="color:red;" class="div_row">
	<span>友情提示:</span>
	<span id="row_tip_apply_type_cm"    style='display:<s:if test="applyTypeCd != 0">none;</s:if>'>请联系"发起中心总经理"进行处理.</span>
	<span id="row_tip_apply_type_apply" style='display:<s:if test="applyTypeCd != 2">none;</s:if>'>请联系"表单发起人"进行处理.</span>
	<span id="row_tip_apply_type_exec"  style='display:<s:if test="applyTypeCd != 3">none;</s:if>'>您提交申请后，将立即生效.</span>
	</div>
	<div class="div_row">
	<span class="wap_title">延期时间:</span>
	<input type="text" name="delayDay" id="delayDay" style="ime-mode:Disabled;text-align: center;" onkeyup="value=this.value.replace(/\D+/g,'')" maxlength="2" size="5"/>
	<span>天</span>
	<input type="text" name="delayHour" id="delayHour" style="ime-mode:Disabled;text-align: center;" onkeyup="value=this.value.replace(/\D+/g,'')" maxlength="2" size="5"/>
	<span>小时</span>
	</div>
	<div class="div_row">
	<span class="wap_title">审批方式:</span>
	<s:if test="applyTypeCd == 3">
		<div class="div_row"><input type="radio" name="applyTypeCd" id="applyTypeCd3" checked="checked" value="3"/>不需要审批</div>
	</s:if>
	<s:else>
		<div class="div_row"><input onclick="processRowCm($(this).val())" type="radio" name="applyTypeCd" id="applyTypeCd0"  value="0" <s:if test="applyTypeCd == 0">checked="checked"</s:if>/>经发起中心总经理</div>
		<div class="div_row"><input onclick="processRowCm($(this).val())" type="radio" name="applyTypeCd" id="applyTypeCd2" value="2"/>表单发起人</div>
	</s:else>
	</div>
	<div id="row_apply_cm" <s:if test="applyTypeCd != 0">style="display:none;"</s:if> class="div_row">
	<span class="wap_title">发起中心总经理:</span>
	<input type="text" id="delayApproveUserName" style="width:100px;" readonly="readonly" onclick="openChooseUser();"/>
	<input type="hidden" id="delayApproveUserCd" />
	<span style="color:red;">*</span>
	</div>
	<div class="div_row">
	<span class="wap_title">申请原因:</span><span id="applyReasonTip" style="margin-left: 10px;"></span>
	</div>
	<div>
	<textarea name="applyReason" id="applyReason" style="font-size: 12px;width:95%;height:120px;"></textarea>
	</div>
	<div id="applyReasonTip"></div>
	</div>
	
	<script type="text/javascript">
		//是否显示"选择中心总经理"
		function processRowCm(val){
			switch(val){
				case '0':
					$('#row_apply_cm').show();
					$('#row_tip_apply_type_cm').show();
					$('#row_tip_apply_type_apply').hide();
					//$('#row_tip_apply_type_qgb').hide();
					break;
				case '1':
					$('#row_apply_cm').hide();
					$('#row_tip_apply_type_cm').hide();
					$('#row_tip_apply_type_apply').hide();
					//$('#row_tip_apply_type_qgb').show();
					break;
				case '2':
					$('#row_apply_cm').hide();
					$('#row_tip_apply_type_cm').hide();
					$('#row_tip_apply_type_apply').show();
					//$('#row_tip_apply_type_qgb').hide();
					break;
				default:
					;
			} 
		}

		$('#applyReason').artTxtCount($('#applyReasonTip'),300);


		function saveApproveDelay() {
			if($.trim($('#delayDay').val()) == '' && $.trim($('#delayHour').val()) == ''){
				alert('请输入延期时间!');
				$('#delayDay').focus();
				return;
			}
			if($.trim($('#applyReason').val()) == ''){
				alert('请输入延期原因!');
				$('#applyReason').focus();
				return;
			}
			
			//经发起中心经理
			var tmpAcctTypeCd = $('input[name="applyTypeCd"]:checked','#resApproveDelayDiv').val();
			if(tmpAcctTypeCd == '0'){
				if($.trim($('#delayApproveUserCd').val()) == ''){
					alert('请选择发起中心总经理!');
					$('#delayApproveUserName').focus();
					return;
				}
			}else{
				$('#delayApproveUserCd').val('');
				$('#delayApproveUserName').val('');
			}
			var data={
					resApproveId: id,
					delayDay: $('#delayDay').val(),
					delayHour: $('#delayHour').val(),
					approveUserName: $('#delayApproveUserName').val(),
					approveUserCd: $('#delayApproveUserCd').val(),
					applyReason: $('#applyReason').val()
					};

			var applyTypeCd;
			
			var applyTypeCd3=$('#applyTypeCd3').attr('checked');
			var applyTypeCd2=$('#applyTypeCd2').attr('checked');
			var applyTypeCd0=$('#applyTypeCd0').attr('checked');
			if (applyTypeCd3){
				applyTypeCd=$('#applyTypeCd3').val();
			}
			if (applyTypeCd2){
				applyTypeCd=$('#applyTypeCd2').val();
			}
			if (applyTypeCd0){
				applyTypeCd=$('#applyTypeCd0').val();
			}
			data.applyTypeCd=applyTypeCd;
			$.post(_ctx + "/res/res-approve-delay!save.action",data, function(result) {
				if('success' == result){
					alert("已提交延期申请!");
					openDetail();
				}else{
					alert(result);
				}
			});
		}
	</script>

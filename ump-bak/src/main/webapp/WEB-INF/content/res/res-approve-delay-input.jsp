<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<s:if test="pageType == 1 ">
	<style>
	#tbl_delay td{
		margin: 0;
		padding: 0;
	}
	</style>
	<div id="resApproveDelayDiv" style="margin:0px;">
	<form id="resApproveDelayForm" name="resApproveDelayForm" action="${ctx}/res/res-approve-delay!save.action" method="post">
		
		<input type="hidden" name="resApproveId" id='resApproveId' value="${resApproveId}"/>
		<table style="width:100%;" id="tbl_delay">
			<tr style="color:red;">
				<td width="100px;" style="text-align: right;">友情提示：</td>
				<td style="text-align: left;"> 
					<span id="row_tip_apply_type_cm"    style='display:<s:if test="applyTypeCd == 0">block;</s:if><s:else>none;</s:else>'>请联系"发起中心总经理"进行处理.</span>
					<span id="row_tip_apply_type_apply" style='display:<s:if test="applyTypeCd == 2">block;</s:if><s:else>none;</s:else>'>请联系"表单发起人"进行处理.</span>
					<%-- 不经过企管部
					<span id="row_tip_apply_type_qgb"   style='display:<s:if test="applyTypeCd == 1">block;</s:if><s:else>none;</s:else>'>请联系"企管部"进行处理.</span>
					 --%>
					<span id="row_tip_apply_type_exec"  style='display:<s:if test="applyTypeCd == 3">block;</s:if><s:else>none;</s:else>'>您提交申请后，将立即生效.</span>
				</td>
			</tr>
			<tr>
				<td align="right">延期时间：</td>
				<td>
					<input type="text" name="delayDay" id="delayDay" style="ime-mode:Disabled;text-align: center;" onkeyup="value=this.value.replace(/\D+/g,'')" maxlength="2" size="5"/>
					<span>天</span>
					<input type="text" name="delayHour" id="delayHour" style="ime-mode:Disabled;text-align: center;" onkeyup="value=this.value.replace(/\D+/g,'')" maxlength="2" size="5"/>
					<span>小时</span>
				</td> 
			</tr>
			<tr>
				<td align="right">审批方式：</td>
				<td>
					<s:if test="applyTypeCd == 3">
						<input type="radio" name="applyTypeCd" id="applyTypeCd3" checked="checked" value="3">不需要审批</input>
					</s:if>
					<s:else>
						<input onclick="processRowCm($(this).val())" type="radio" name="applyTypeCd" id="applyTypeCd0"  value="0" <s:if test="applyTypeCd == 0">checked="checked"</s:if>>经发起中心总经理</input>
						<input onclick="processRowCm($(this).val())" type="radio" name="applyTypeCd" id="applyTypeCd2" value="2">表单发起人</input>
						<%-- 不经过企管部
						<input onclick="processRowCm($(this).val())" type="radio" name="applyTypeCd" value="1" <s:if test="applyTypeCd == 1">checked="checked"</s:if>>直接至企管部</input>
						 --%>
					</s:else>
				</td> 
			</tr>
			<tr id="row_apply_cm" <s:if test="applyTypeCd != 0">style="display:none;"</s:if>>
				<td align="right">发起中心总经理：</td>
				<td>
					<input type="text" readonly="readonly"  name="approveUserName" id="delayApproveUserName" class="singleSelect" style="width:80px;cursor:pointer;"/>
					<input type="hidden" name="approveUserCd" id="delayApproveUserCd" />
					<span style="color:red;">*</span>
				</td>
			</tr> 
			
			<tr>
				<td align="right" valign="top">申请原因：</td>
				<td style="padding: 3px 0;"><textarea name="applyReason" id="applyReason" style="font-size: 12px;width:95%;height:120px;"></textarea>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><div id="applyReasonTip"></div></td>
			</tr>
			<tr>
				<td align="center" valign="top" colspan="2" style="padding-top:5px;">
					<input style="margin-right:30px;" type="button" name="btnSaveDelay"  class="btn_green" onclick="saveApproveDelay('${resApproveId}');" value="确定"/>
					<input type="button" name="btnCloseDelay" class="btn_red" onclick="$('#delayWindowDiv').hide();" value="取消"/>
				</td>
			</tr>
		</table>
	</form>
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
		$('.singleSelect').userSelect({
			muti:false
		});
	</script>
</s:if>
<s:if test="pageType == 3">
	<s:if test="appliedFlg == 1">
		<div class="list_header_img"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
		<div class="list_header2"><span style="font-size:14px;">延期申请</span></div>
		<div style="margin-left: 20px;padding-bottom:8px;padding-right:10px;">
		
			 申请时间:<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/>
			 申请延期:<s:if test="delayDay != 0">${delayDay}天</s:if>${delayHour}小时
			
			<br/>
			
			 发起人:${applyUserName}
			 
			<!-- 第一级 -->
			<s:if test="approveUserCd != null && approveUserCd != ''">
				<s:if test="approveOptionCd == null">
					-> [<%= CodeNameUtil.getUserNameByCd(JspUtil.findString("approveUserCd")) %>] 未处理
					<s:if test="approveUserCd == curUserCd">
						<s:if test="approveOptionCd == null ">
							<input class="btn_green" type="button" value="同意延期" style="width:80px;" onclick="onChiefAgree('${resApproveDelayId}');"/>
							<input class="btn_red" type="button" value="拒绝延期" style="width:80px;" onclick="onChiefDegree('${resApproveDelayId}');"/>
						</s:if> 
					</s:if>
				</s:if>
				<s:else>
					-> [<%= CodeNameUtil.getUserNameByCd(JspUtil.findString("approveUserCd")) %>] 已处理
				</s:else>
			</s:if>
			
			<%-- hidden by huangbijin 2011-10-20 只要发起人同意即可 --%>
			<%-- 第二级 --%>
			<s:if test="confirmUserCd != null && confirmUserCd != ''">
			<s:if test="confirmOptionCd == null ">
				-> [企管部] 未处理
				<s:if test="confirmUserCd == curUserCd">
					<s:if test="confirmOptionCd == null ">
						<input class="btn_green" type="button" value="同意延期" style="width:80px;" onclick="onConfirmAgree('${resApproveDelayId}');"/>
						<input class="btn_red" type="button" value="拒绝延期" style="width:80px;" onclick="onConfirmDegree('${resApproveDelayId}');"/>
					</s:if> 
				</s:if>
			</s:if>
			<s:else>
				-> [企管部] 已处理
			</s:else>
			</s:if>
			
			
			<div style="word-wrap:break-word;">
				申请事由： ${applyReason}
			</div>
		</div>
	</s:if>
</s:if>
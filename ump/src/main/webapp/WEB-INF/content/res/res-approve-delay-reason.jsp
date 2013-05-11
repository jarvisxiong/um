<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="resApproveReasonDiv">
	<table style="width:100%;">
		<tr>
			<td style="width:100px;" align="right" valign="top">请输入审批意见：</td>
			<td style="padding: 3px 0;"><textarea name="delayReason" id="delayReason" style="font-size: 12px;width:95%;height:60px;"></textarea>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div id="applyReasonTip"></div></td>
		</tr>
		<tr>
			<td align="center" valign="top" colspan="2" style="padding-top:5px;">
				<input style="margin-right:30px;" type="button"  class="btn_green" onclick="submitDelayChiefReason('${resApproveDelayId}','${delayChiefTypeCd}','${resApproveInfo.resApproveInfoId}');" value="确定"/>
				<input type="button" class="btn_red" onclick="ymPrompt.close();" value="取消"/>
			</td>
		</tr>
	</table>
</div>
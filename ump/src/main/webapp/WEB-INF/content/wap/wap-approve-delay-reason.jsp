<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="toolBar">
	<input type="button" class="btn_blue" onclick="submitDelayChiefReason('${resApproveDelayId}','${delayChiefTypeCd}');" value="确定"/>
	<input type="button" value="返回" class="btn_green" onclick="openDetail();" />
</div>
<div class="list_header"><span>延期处理</span></div>
<div id="resApproveReasonDiv">
	<table style="width:100%;">
		<tr>
			<td align="left">请输入审批意见：</td>
		</tr>
		<tr>
			<td><textarea name="delayReason" id="delayReason" style="font-size: 12px;width:95%;height:60px;"></textarea>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><div id="applyReasonTip"></div></td>
		</tr>
	</table>
</div>
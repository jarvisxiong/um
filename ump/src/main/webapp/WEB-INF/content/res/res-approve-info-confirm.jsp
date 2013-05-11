<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ include file="/common/meta.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css" />
<script type="text/javascript"> 
function confirm(confirmId,dom){
	var remark=$(dom).parent().prev().children().eq(0).val();
	$.post("${ctx}/res/res-approve-info!confirm.action",{
		confirmId:confirmId,
		remark:remark,
		id:'${resApproveInfoId}'
		},
			 function(result) {
			 $("#phCenterRecord").html(result);
	});
}
</script>
		<table class="showTable" id="editTable" align="left" width="100%">
			<s:iterator value="resCenterConfirmInfos">
				<tr class="mainTr">
					<td width="15%"><%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("centerCd")) %></td>
					<td width="15%"><s:if test="conOption==1">已确认</s:if><s:else>未确认</s:else> </td>
					<td width="20%"><s:property value="conDate"/> </td>
					<s:set var="isMyConfirmed"><%=SpringSecurityUtils.getCurrentUiid().equals(JspUtil.findString("conUser"))&&JspUtil.findString("conOption").equals("0")%></s:set>
					<td width="40%">
					<s:if test="#isMyConfirmed=='true'">
					<input type="text" name="remark" style="width:100%" />
					</s:if>
					<s:else>
					${remark}
					</s:else>
					</td>
					<td width="10%">
					<s:if test="#isMyConfirmed=='true'">
					<input type="button" value="确认"  class="buttom" onclick="confirm('${resCenterConfirmInfoId}',this);" />
					</s:if>
					</td>
				</tr>
			</s:iterator>
		</table>

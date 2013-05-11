<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%-- 是否有模块管理权限 --%>
<s:set var="isPrimCtrl" value="0" />
<security:authorize ifAnyGranted="A_CONT_MOULD_ADMIN">
	<s:set var="isPrimCtrl" value="1" />
</security:authorize>
<s:set var="canDel" value="0"/>
<div id="conAttachOutputList" >
<s:if test="conAttachOutputList==null || conAttachOutputList.size == 0">
	<div class="attachHeader">该合同暂无附件！</div>
</s:if>
 <s:else>
	<div class="attachHeader">合同附件&nbsp;&nbsp;&nbsp;&nbsp;<input id="autoShow" class="btn_green" type="button" style="width:60px;line-height: 20px;" onclick="showMoreAttach();" value="收起"></div>
	<table cellpadding="0" cellspacing="0" class="attachTable"  border="0" id="attachTable">
		<s:iterator value="conAttachOutputList" status="s">
			<s:if test="creator == currentUiid">
			<s:set var="canDel" value="1"/>
			</s:if>
			<s:else>
			<s:set var="canDel" value="0"/>
			</s:else>
			<tr>
				<td>
				<s:if test="#s.index == 0"></s:if>
                 <div style="width:160px;font-weight:bold;color:#0693e3" class="clswrap" title="${attachTypeCd}">${attachTypeCd}</div>
				</td>
				<td style="padding: 0; margin: 0; text-align: left;" nowrap="nowrap">
					<s:url id="downUrl" action="download" namespace="/sc">
						<s:param name="fileName">${attachName}</s:param>
						<s:param name="realFileName">${realFileName}</s:param>
						<s:param name="bizModuleCd">sctemplet</s:param>
						<s:param name="operator">inline;</s:param>
						<s:param name="attchid">${scContractInfoAttachId}</s:param>
					</s:url>
					 <span style="text-decoration: underline; color: #5A5A5A; cursor: pointer;" title="点击下载附件" onclick="window.open('${downUrl}'); return false;">${realFileName}</span>
					&nbsp;&nbsp;
				</td>
				<td title="上传人" style="width: 90px;">
					<%--上传人 --%>
					<%=CodeNameUtil.getUserNamesByUiids(JspUtil.findString("creator"), ";")%>&nbsp;
					<%-- 上传日期--%>
					<span title="<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss" />"><s:date name="createdDate" format="MM-dd" /></span>
				</td>
				<td title="${remark}" style="color: green; font-size: 10px; width: 100px">
					<div style="width: 100px" class="clswrap">${remark}</div>
				</td>
				<td style="padding: 0; margin: 0; text-align: center; width: 25px">
					<%-- 只有模块管理员，或者负责人，可以删除附件 --%>
					 <s:if test="#isPrimCtrl == 1 || #canDel == 1">
						<img style="cursor: pointer;" mark="delattach" title="删除附件" src="${ctx}/pics/note/note_del.gif" onclick="deleteAttachment('${scContractInfoAttachId}');" />
					</s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
</s:else>
</div>

<script type="text/javascript">
//当前合同文本记录为删除状态，隐藏删除按钮
if($("#isDel").val() == "1") {
	$("#attachTable img[mark='delattach']").hide();
}
showMoreAttach();
function showMoreAttach() {
	var trs = $("#attachTable >tbody >tr");
	if((trs.size()-1) > 5) {
		var text = $.trim($("#autoShow").val());
		if(text == "更多>>") {
			$("#autoShow").val("收起");
			// 点击更多的时候，显示全部信息
			for(var i=5; i<trs.size()-1; i++) {
				trs.eq(i).css("display", "");
			}
			$("#attachList").css("height", "auto");
		} else {
			$("#autoShow").val("更多>>");
			// 点击收起，就从第5行开始以后不显示
			for(var i=5; i<trs.size()-1; i++) {
				trs.eq(i).css("display", "none");
			}
			$("#attachList").css("height", "170px");
		}
	} else {
		$("#autoShow").css("display", "none");
		$("#attachList").css("height", "auto");
	}
}
</script>
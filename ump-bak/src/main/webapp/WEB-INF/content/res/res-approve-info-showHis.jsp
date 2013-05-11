<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />

<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<div style="float:left;padding:7px 5px 0 5px ;"><img src="${ctx}/resources/images/common/down_black.gif"></img></div>
<div class="list_header2" style="height:24px;line-height:24px;"><span style="font-size:14px;">审批历史<s:if test="approveHises.size==0">&nbsp;&nbsp;(无记录)</s:if></span></div>

<div style="background-color: #ffffee;padding:0px 5px;margin:10px;">
	<table class="content_table showTable" id="showTableApproveRec" width="100%" style="font-size:12px;">
		<s:iterator value="approveHises" var="ra">
			<tr class="mainTr">
				<td style="padding:10px 0px;height:100%;">
					<div style="width:80px;float:left;color:#0167a2;padding-left:5px;word-wrap:break-word;">
						<div style="width:80px;float:left;">${userName}</div>
					</div>
					<div style="width:150px;float:left;color:#0167a2;">
						<%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("nodeCd")) %>&nbsp;
					</div>
					<div style="width:80px;float:left;"> 
						<s:if test="groupNodeCd==39 || nodeCd==97">
							<p:code2name mapCodeName="mapOptionZyps" value="approveOptionCd"/>
						</s:if>
						<s:else>
							<p:code2name mapCodeName="mapOption" value="approveOptionCd"/>
						</s:else>
					</div>
					<s:if test="approveDate!=null">
					<div style="float:right;"><s:date name="approveDate" format="yyyy-MM-dd HH:mm" />
					</div>
					</s:if>
					<div style="width:90px;float:right;">
					<%=CodeNameUtil.getResNodeNameByCd(JspUtil.findString("groupNodeCd")) %>
					</div>
					<s:if test="remark!=null">
					<div style="clear:both;padding:5px 0 5px 5px;"><pre style="white-space:pre-wrap;" class="approveOption">${remark}</pre></div>
					</s:if>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>

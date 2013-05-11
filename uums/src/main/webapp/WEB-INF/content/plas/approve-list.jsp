<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.uums.utils.CodeNameUtil"%>
<%@page import="com.hhz.uums.utils.JspUtil"%>
<%@page import="java.util.Date"%> 

<%-- 当前申请对应的审批节点列表 --%>

<div style="margin-top: 10px;">
	<div class="list_header2"><span>审批记录</span></div>
	<table class="approveTable" id="showTableApproveRec" cellpadding="0"  cellspacing="0">
		<s:iterator value="nodeHiss" status="st">
			<tr style="height: 30px;" 
				class="
				<s:if test="plasApproveNodeHisId == plasApproveInfo.nextNodeId">bottom curTr</s:if>
				<s:elseif test="approveOptionCd==1">passTr</s:elseif>
				<s:else>futureTr</s:else> 
			">
				<td width="15px"><span class="wap_title">${st.index+1}</span></td>
				<td width="95%">
					<div class="div_row">
						<s:if test="#st.index == 0">
							<span class="wap_title"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermType()" value="approveRoleCd"/> ${approveUserName} (<s:date name="approveDate" format="yyyy-MM-dd HH:mm"/> 发起)</span>
						</s:if>
						<s:else>
							<s:if test="approveUiid!=null">
								<span class="wap_title"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermType()" value="approveRoleCd"/> ${approveUserName}(<s:date name="approveDate" format="yyyy-MM-dd HH:mm"/> <s:if test="approveOptionCd==1">同意</s:if><s:if test="approveOptionCd==2"> 驳回</s:if>)</span>
							</s:if>
							<s:else>
								<span class="wap_title"><p:code2name mapCodeName="@com.powerlong.plas.utils.DictMapUtil@getMapPermType()" value="approveRoleCd"/> (未审批)</span>
							</s:else>
						</s:else>
						<span class="wap_title">${workDutyDesc}</span>
						<span class="wap_title"></span>
					</div>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>

